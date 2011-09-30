package org.matttitterington.cellautomata.WireworldPuzzleGame;

public class PuzzleRunner {

	GameInterface parent;
	int frame;
	int level;
	
	//Statics
	static final int NOTHEAD = 2;
	static final int HEAD = 1;
	
	//variables for scanning for input/output
	int[] inputAPos;
	int[] inputBPos;
	int[] outputPos;
	
	//Level variables
	Level currentLevel;
	LevelParser levelParser;
	
	//Dynamic vars
	int firstOutput;
	int[] outputArray;
	
	
	public PuzzleRunner(GameInterface gameInterface, int level, int[] inputAPos, int[] inputBPos, int[] outputPos) {
		this.inputAPos = inputAPos;
		this.inputBPos = inputBPos;
		this.outputPos = outputPos;
		
		this.levelParser = new LevelParser();
		
		this.parent = gameInterface;
		this.setLevel(level);
		this.newRun();
	}

	void setLevel(int level) {
		
		this.level = level;
		
		this.currentLevel = this.levelParser.getLevel(level);
		
		
		this.newRun();
		
		displayLevelInformation();
		
		
		
	}

	

	public void performActions() {
		//Check to see if we need to output an electron
		if (frame % 6 == 0) {
			//We may be due to emit an electron
			
			//Check for NullPointerException
			if (frame/6 < currentLevel.inputA.length()) {
				//Check if one needs to go out on A
				if (currentLevel.inputA.charAt(frame/6) == "1".charAt(0)) {
					this.parent.cells.get(inputAPos[1]).get(inputAPos[0]).setState(CellCanvas.HEAD);
				}
			
			}
			
			//NullPointer on B
			if (frame/6 < currentLevel.inputB.length()) {
				//Check if one needs to go out on B
				if (currentLevel.inputB.charAt(frame/6) == "1".charAt(0)) {
					this.parent.cells.get(inputBPos[1]).get(inputBPos[0]).setState(CellCanvas.HEAD);
				}
			
			}
		}
		
		
		//Check for incoming electrons
		
		//If a head cell is at the output
		boolean isHeadCell = parent.cells.get(outputPos[1]).get(outputPos[0]).getState() == CellCanvas.HEAD;
		
		//If a head cell is encountered for the first time
		if (firstOutput == 0 && isHeadCell) {
			firstOutput = frame;
		}
		
		//If a head cell has been encountered
		if (firstOutput > 0) {
			
			if (isHeadCell) {
				outputArray[frame-firstOutput] = PuzzleRunner.HEAD;
			} else {
				outputArray[frame-firstOutput] = PuzzleRunner.NOTHEAD;
			}
		}
		
		//Check victory conditions
		
		
		//Check only occurs once, when sufficient time has passed
		boolean sufficientTimePassed;
		if (firstOutput == 0) {
			sufficientTimePassed = false;
		} else {
			System.out.println("First output recieved");
			
			sufficientTimePassed = ((currentLevel.getExpectedOutput().length()-1)*6)+1 < (frame-firstOutput);
		}

		//If so, check for victory
		if (sufficientTimePassed) {
			
			boolean victory = true;
			
			for (int i = 0; i < outputArray.length; i++) {
				
				//If it is a tick we are concerned about
				if (i % 6 == 0) {
					
					int expected;
					
					if (currentLevel.getExpectedOutput().charAt(i/6) == "1".charAt(0)) {
						expected = PuzzleRunner.HEAD;
					} else {
						expected = PuzzleRunner.NOTHEAD;
					}
					
					if (expected != outputArray[i]) {
						victory = false;
						
						System.out.println("Expected " + expected
								+ "at position " + i + ". Got "
								+ outputArray[i]);
						
						break;
					}
					
				} else { //If it is an in-between tick
					if (outputArray[i] == PuzzleRunner.HEAD) {
						victory = false;
						break;
					}
				}
			}
			
			//Refer to the endLevelHandler method
			System.out.println("Victory check complete");
			
			endLevelHandler(victory);
			
		}
		
		
			
		//Increment the frame	
		frame += 1;
	}

	private void endLevelHandler(boolean victory) {
		parent.stopGame();
		
		if (victory) {
			parent.resetCells();
			System.out.println("VICTORY!!! moving to level "+this.level+1);
			this.setLevel(this.level + 1);
		}
		else {
			System.out.println("Failure!");
			parent.resetElectrons();
			//TODO show fail dialogue
		}
		
	}

	public void newRun() {
		this.frame = 0;
		this.firstOutput = 0;
		this.outputArray = new int[(this.currentLevel.expectedOutput.length()*6)];
		this.parent.resetElectrons(this.currentLevel.electronAllowance);
		
	}
	
	private void displayLevelInformation() {
		new LevelInformation(currentLevel, parent);
		
		
	}
	
	

}
