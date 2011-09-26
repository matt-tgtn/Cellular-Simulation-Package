package org.matttitterington.cellautomata.GameOfLife;

import java.util.ArrayList;

public class LifeWorld {
	ArrayList<ArrayList<Boolean>> cellsArray;
	ArrayList<ArrayList<CellCanvas>> GUIArray;
	GameInterface parent;
	
	public LifeWorld(GameInterface GUIParent) {
		this.parent = GUIParent;
		this.GUIArray = GUIParent.cells;
		this.syncToInternal();
	}
	
	public LifeWorld(GameInterface GUIParent, ArrayList<ArrayList<Boolean>> cellsArray) {
		this.parent = GUIParent;
		this.GUIArray = GUIParent.cells;
		this.cellsArray = cellsArray;
	}
	
	public void syncToInternal() {
		cellsArray = new ArrayList<ArrayList<Boolean>>();
		
		for (int y = 0; y < this.parent.cells.size(); y++) {
			
			cellsArray.add(new ArrayList<Boolean>());
			
			for (int x = 0; x < this.parent.cells.get(y).size(); x++) {
				cellsArray.get(y).add((Boolean) this.parent.cells.get(y).get(x).getState());
				
			}
		}
	}
	
	public void syncToGUI() {
		for (int y = 0; y < cellsArray.size(); y++) {
			for (int x = 0; x < cellsArray.get(y).size(); x++) {
				this.parent.cells.get(y).get(x).setState(cellsArray.get(y).get(x));
				
			}
		}
	}
	
	public LifeWorld createNextGenerationWorld() {
		ArrayList<ArrayList<Boolean>> newWorldArray = new ArrayList<ArrayList<Boolean>>();
		
		for (int y = 0; y < cellsArray.size(); y++) {
			
			newWorldArray.add(new ArrayList<Boolean>());
			
			for (int x = 0; x < cellsArray.get(0).size(); x++) {
				boolean nextState = this.getNextCellState(x, y);
				newWorldArray.get(y).add(nextState);
			}
		}
		
		return new LifeWorld(this.parent, newWorldArray);
		
	}
	
	public boolean getNextCellState(int xc, int yc) {
		boolean currentState = cellsArray.get(yc).get(xc);
		boolean nextState = currentState;
		int dead = 0;
		int alive = 0;
		int[] result = new int[2];
		
		if (xc == 0){ //On left boundary
			
			if (yc == 0) {//At top
				result = this.getNeighbourStates(xc, yc, 5, 0, 1, 0, 1);
			}
			else if (yc == (cellsArray.size()-1)) {//At bottom
				result = this.getNeighbourStates(xc, yc, 5, 0, 1, -1, 0);
			}
			else {//Fine on y axis
				result = this.getNeighbourStates(xc, yc, 3, 0, 1, -1, 1);
			}
			
		}
		else if (xc == (cellsArray.get(0).size() - 1)) { //On right boundary
			
			if (yc == 0) {//At top
				result = this.getNeighbourStates(xc, yc, 5, -1, 0, 0, 1);
			}
			else if (yc == (cellsArray.size()-1)) {//At bottom
				result = this.getNeighbourStates(xc, yc, 5, -1, 0, -1, 0);
			}
			else {//Fine on y axis
				result = this.getNeighbourStates(xc, yc, 3, -1, 0, -1, 1);
			}

		}
		else { //OK on x axis
			
			if (yc == 0) {//At top
				result = this.getNeighbourStates(xc, yc, 3, -1, 1, 0, 1);
			}
			else if (yc == (cellsArray.size()-1)) {//At bottom
				result = this.getNeighbourStates(xc, yc, 3, -1, 1, -1, 0);
			}
			else {//Fine on y axis
				result = this.getNeighbourStates(xc, yc, 0, -1, 1, -1, 1);
			}

		}
		
		
		alive = result[0];
		dead = result[1];
		
		
		if (alive+dead!=8) {
			throw new Error("Neighbour calculation error");
		}

		
		
		
		if (currentState) {
			//Is currently alive
			if (alive < 2) {
				nextState = false;
			}
			else if ((alive == 2) || (alive == 3)) {
				nextState = true;
			}
			else if (alive > 3) {
				nextState = false;
			}
			
		} else {
			//Is currently dead
			if (alive == 3) {
				nextState = true;
			}
			
		}
		
		return nextState;
		
	}
	
	public int[] getNeighbourStates(int xc,int yc, int deadSupplement, int xlower, int xupper, int ylower, int yupper) {
		int alive = 0;
		int dead = 0;
		
		for (int y = ylower; y <= yupper; y++) {
			for (int x = xlower; x <= xupper; x++) {
				
				//If x and y are both not equal 0.
				if (((x==0)&&(y==0))==false) {
					if (cellsArray.get(yc+y).get(xc+x)) {
						alive += 1;
					} else {
						dead += 1;
					}
				}
			}
		}
		
		dead += deadSupplement;
		
		int[] result = new int[2];
		result[0] = alive;
		result[1] = dead;
		
		return result;
		
	}
	
	public void debug() {
		for (ArrayList<CellCanvas> array : this.GUIArray) {
			for (CellCanvas cellCanvas : array) {
				cellCanvas.setState(false);
			}
		}
	}
}
