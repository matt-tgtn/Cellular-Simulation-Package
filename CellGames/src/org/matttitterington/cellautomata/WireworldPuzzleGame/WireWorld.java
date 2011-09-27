package org.matttitterington.cellautomata.WireworldPuzzleGame;

import java.util.ArrayList;

public class WireWorld {
	ArrayList<ArrayList<Integer>> cellsArray;
	GameInterface parent;
	
	public WireWorld(GameInterface GUIParent) {
		this.parent = GUIParent;
		this.syncToInternal();
	}
	
	public WireWorld(GameInterface GUIParent, ArrayList<ArrayList<Integer>> cellsArray) {
		this.parent = GUIParent;
		this.cellsArray = cellsArray;
	}
	
	public void syncToInternal() {
		cellsArray = new ArrayList<ArrayList<Integer>>();
		
		for (int y = 0; y < this.parent.cells.size(); y++) {
			
			cellsArray.add(new ArrayList<Integer>());
			
			for (int x = 0; x < this.parent.cells.get(y).size(); x++) {
				cellsArray.get(y).add(this.parent.cells.get(y).get(x).getState());
				
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
	
	public WireWorld createNextGenerationWorld() {
		ArrayList<ArrayList<Integer>> newWorldArray = new ArrayList<ArrayList<Integer>>();
		
		for (int y = 0; y < cellsArray.size(); y++) {
			
			newWorldArray.add(new ArrayList<Integer>());
			
			for (int x = 0; x < cellsArray.get(0).size(); x++) {
				int nextState = this.getNextCellState(x, y);
				newWorldArray.get(y).add(nextState);
			}
		}
		
		return new WireWorld(this.parent, newWorldArray);
		
	}
	
	public int getNextCellState(int xc, int yc) {
		int currentState = cellsArray.get(yc).get(xc);
		int nextState = currentState;
		
		if (currentState == CellCanvas.EMPTY){
			nextState = CellCanvas.EMPTY;
		}
		else if (currentState == CellCanvas.HEAD) {
			nextState = CellCanvas.TAIL;
		}
		else if (currentState == CellCanvas.TAIL) {
			nextState = CellCanvas.WIRE;
		}
		else if (currentState == CellCanvas.WIRE) {
			
			int neighbouringHead = this.getNeighbourStates(xc, yc);
			
			if ((neighbouringHead == 1) || (neighbouringHead == 2)) {
				nextState = CellCanvas.HEAD;
			} else {
				nextState = CellCanvas.WIRE;
			}
			
		}
		
		
		
		return nextState;
		
	}
	
	public int getNeighbourStates(int xc,int yc) {
		int head = 0;
		
		int xmax = 1;
		int xmin = -1;
		int ymax = 1;
		int ymin = -1;
		
		//If the wire is at the edge of the map then cap the min and max
		
		if (xc == 0) {
			xmin = 0;
		} else if (xc == this.parent.cellArrayWidth-1) {
			xmax = 0;
		}
		
		if (yc == 0) {
			ymin = 0;
		} else if (yc == this.parent.cellArrayHeight-1) {
			ymax = 0;
		}
		
		
		
		for (int y = ymin; y <= ymax; y++) {
			for (int x = xmin; x <= xmax; x++) {
				
				//If x and y are both not equal 0. (that is the cell under question).
				if (((x==0)&&(y==0))==false) {
					
					if (cellsArray.get(yc+y).get(xc+x) == CellCanvas.HEAD) {
						head += 1;
					}
					
				}
			}
		}
		
		return head;
		
	}
}
