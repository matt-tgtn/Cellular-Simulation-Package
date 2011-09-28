package org.matttitterington.cellautomata.WireworldPuzzleGame;

public class PuzzleRunner {

	GameInterface parent;
	int level;
	int frame;
	
	//Statics
	static final int NOTHEAD = 2;
	static final int HEAD = 1;
	
	//variables for scanning for input/output
	int[] inputAPos;
	int[] inputBPos;
	int[] outputPos;
	
	//Level variables
	String inputA;
	String inputB;
	String expectedOutput;
	String levelDescription;
	String levelHint;
	int electronAllowance;
	
	//Dynamic vars
	int firstOutput;
	int[] outputArray;
	
	
	public PuzzleRunner(GameInterface gameInterface, int level, int[] inputAPos, int[] inputBPos, int[] outputPos) {
		this.inputAPos = inputAPos;
		this.inputBPos = inputBPos;
		this.outputPos = outputPos;
		
		this.parent = gameInterface;
		this.setLevel(1);
		this.newRun();
	}

	private void setLevel(int level) {
		//TODO add capabilities for real levels
		
		this.level = level;
		
		if (level == 1) {
			inputA = "1011";
			inputB = "0000";
			expectedOutput = "1011";
			electronAllowance = 0;
			
			levelDescription = "In this first level, route all input from A to the output node";
			levelHint = "Draw a straight line of wire from  A to output";
			
		} else if (level == 2) {
			inputA = "1010";
			inputB = "0101";
			expectedOutput = "1111";
			electronAllowance = 0;
			
			levelDescription = "Here, input will come from A and B, but not at the same time. Route it all to the output node.";
			levelHint = "Connect A and B in the middle. Try to think of a way to prevent the electrons running back down the opposite input line.";
		} else {
			setLevel(1);
		}
		
		this.newRun();
		
		if (level == 1) {
			displayLevelInformation(false);
		} else {
			displayLevelInformation(true);
		}
		
		
		
	}

	

	public void performActions() {
		//Check to see if we need to output an electron
		if (frame % 6 == 0) {
			//We may be due to emit an electron
			
			//Check for NullPointerException
			if (frame/6 < inputA.length()) {
				//Check if one needs to go out on A
				if (inputA.charAt(frame/6) == "1".charAt(0)) {
					this.parent.cells.get(inputAPos[1]).get(inputAPos[0]).setState(CellCanvas.HEAD);
				}
			
			}
			
			//NullPointer on B
			if (frame/6 < inputB.length()) {
				//Check if one needs to go out on B
				if (inputB.charAt(frame/6) == "1".charAt(0)) {
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
			
			sufficientTimePassed = ((expectedOutput.length()-1)*6)+1 < (frame-firstOutput);
		}

		//If so, check for victory
		if (sufficientTimePassed) {
			
			boolean victory = true;
			
			for (int i = 0; i < outputArray.length; i++) {
				
				//If it is a tick we are concerned about
				if (i % 6 == 0) {
					
					int expected;
					
					if (expectedOutput.charAt(i/6) == "1".charAt(0)) {
						expected = PuzzleRunner.HEAD;
					} else {
						expected = PuzzleRunner.NOTHEAD;
					}
					
					if (expected != outputArray[i]) {
						victory = false;
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
		this.outputArray = new int[(this.expectedOutput.length()*6)];
		this.parent.resetElectrons(this.electronAllowance);
		
	}
	
	private void displayLevelInformation(boolean completedPrevious) {
		//new LevelInformation(this.level, this.levelDescription, this.levelHint, completedPrevious);
		
		System.out.println(level);
		
	}
	
	

}
