package org.matttitterington.cellautomata.WireworldPuzzleGame;

public class Level {
	
	public int ID;
	
	public String inputA;
	public String inputB;
	public String expectedOutput;
	public int electronAllowance;
	
	public String levelSummary;
	public String levelDescription;
	public String levelHint;
	public String levelPassword;
	
	
	//GETTERS AND SETTERS
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getInputA() {
		return inputA;
	}
	public void setInputA(String inputA) {
		this.inputA = inputA;
	}
	public String getInputB() {
		return inputB;
	}
	public void setInputB(String inputB) {
		this.inputB = inputB;
	}
	public String getExpectedOutput() {
		return expectedOutput;
	}
	public void setExpectedOutput(String expectedOutput) {
		this.expectedOutput = expectedOutput;
	}
	public int getElectronAllowance() {
		return electronAllowance;
	}
	public void setElectronAllowance(int electronAllowance) {
		this.electronAllowance = electronAllowance;
	}
	public String getLevelSummary() {
		return levelSummary;
	}
	public void setLevelSummary(String levelSummary) {
		this.levelSummary = levelSummary;
	}
	public String getLevelDescription() {
		return levelDescription;
	}
	public void setLevelDescription(String levelDescription) {
		this.levelDescription = levelDescription;
	}
	public String getLevelHint() {
		return levelHint;
	}
	public void setLevelHint(String levelHint) {
		this.levelHint = levelHint;
	}
	public String getLevelPassword() {
		return levelPassword;
	}
	public void setLevelPassword(String levelPassword) {
		this.levelPassword = levelPassword;
	}
	
	
	
	
}
