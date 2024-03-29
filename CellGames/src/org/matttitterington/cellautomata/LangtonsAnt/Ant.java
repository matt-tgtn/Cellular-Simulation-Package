package org.matttitterington.cellautomata.LangtonsAnt;

public class Ant {
	
	int x;
	int y;
	int direction;
	GameInterface parent;
	int frame;
	

	public Ant(GameInterface gameInterface) {
		this.parent = gameInterface;
		this.frame = 1;
	}

	public void syncAnt() {
		//If the ant position has changed from last check then reset ant direction
		int[] position = new int[2];
		for (int y = 0; y < this.parent.cells.size(); y++) {
			for (int x = 0; x < this.parent.cells.get(y).size(); x++) {
				if (this.parent.cells.get(y).get(x).ant) {
					position[0]=x;
					position[1]=y;
				}
			}
		}
		
		//If position has changed
		
		if ((position[0]==x)&&(position[1]==y)) {
			//Tada! the ant is in the same position
			System.out.println("Ant not changed");
		}
		else {
			//If the ant has changed then reset the direction
			this.x = position[0];
			this.y = position[1];
			this.direction = 3;
			this.frame = 1;
			
			System.out.println("Ant had moved");
		}
		
	}


	public void performOneIteration() {
		CellCanvas currentCell = this.parent.cells.get(y).get(x);
		
		//turn the ant
		this.rotateAnt(currentCell.getTurnDirection());
		
		currentCell.incrementState();
		
		this.moveAnt();
		
		frame += 1;
		
	}
	
	private void moveAnt() {
		int newx = this.x;
		int newy = this.y;
		
		//Clear the old cell of antness
		this.parent.cells.get(y).get(x).removeAnt();
		
		
		//Find the new position
		switch (this.direction) {
		case 0:
			newx = x;
			newy = y - 1;
			
			if (newy < 0) {
				newy += this.parent.cellArrayHeight;
			}
			
			break;
		
		case 1:
			newx = (x+1)%this.parent.cellArrayWidth;
			newy = y;
			break;
		
		case 2:
			newx = x;
			newy = (y+1)%this.parent.cellArrayHeight;
			break;
			
		case 3:
			newx = x-1;
			newy = y;
			
			
			if (newx < 0) {
				newx += this.parent.cellArrayWidth;
			}
			
			break;
		}
		
		
		//Let the new cell know it is anty
		this.parent.cells.get(newy).get(newx).setAnt();
		
		//Set the direction fields
		this.x = newx;
		this.y = newy;
		
	}

	private void rotateAnt(char dir) {
		if (dir == "L".charAt(0)) {
			this.direction -= 1;
			
			while (this.direction < 0) {
				this.direction += 4;
			}
		}
		else if (dir == "R".charAt(0)) {
			this.direction += 1;
			this.direction %= 4;
		} else {
			throw new Error("Incorrect arguments passed");
		}
	}

}
