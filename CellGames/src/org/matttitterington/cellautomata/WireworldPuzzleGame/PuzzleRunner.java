package org.matttitterington.cellautomata.WireworldPuzzleGame;

public class PuzzleRunner {

	GameInterface parent;
	int level;
	int frame;
	
	//variables for scanning for input/output
	int[] inputAPos;
	int[] inputBPos;
	int[] outputPos;
	
	//Level variables
	String inputA;
	String inputB;
	String expectedOutput;
	
	
	public PuzzleRunner(GameInterface gameInterface, int level, int[] inputAPos, int[] inputBPos, int[] outputPos) {
		this.inputAPos = inputAPos;
		this.inputBPos = inputBPos;
		this.outputPos = outputPos;
		
		this.parent = gameInterface;
		this.setLevel(1);
	}

	private void setLevel(int level) {
		// TODO Auto-generated method stub
		
	}

	public void performActions(boolean real) {
		//Check to see if we need to output an electron
		
		//Check for incoming electrons
		
		//Check victory conditions
		
	}

	public void newRun() {
		this.frame = 0;
		
	}
	
	

}
