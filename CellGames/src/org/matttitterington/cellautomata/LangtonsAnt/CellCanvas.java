package org.matttitterington.cellautomata.LangtonsAnt;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CellCanvas extends JPanel implements MouseListener{
	
	private boolean state;
	private GameInterface parent;
	boolean ant;
	
	public CellCanvas(GameInterface parent) {
		this.setState(false);
		this.addMouseListener(this);
		this.parent = parent;
		this.ant = false;
	}
	
	public CellCanvas(GameInterface parent, boolean initState) {
		this.setState(initState);
		this.addMouseListener(this);
		this.parent = parent;
	}
	
	public CellCanvas(boolean state) {
		this.setState(state);
		this.ant = false;
	}
	//State handling code
	
	public boolean getState() {
		return this.state;
	}
	
	public void setState(boolean state) {
		this.state = state;
		
		if (state) {
			this.setBackground(Color.BLACK);
		} else {
			this.setBackground(Color.WHITE);
		}
	}
	
	public void flipState() {
		if (this.state){
			this.setState(false);
		} else {
			this.setState(true);
		}
	}
	
	//ANT CODE
	
	public void setAnt() {
		this.ant = true;
		this.setBackground(Color.RED);
	}
	
	public void removeAnt() {
		this.ant = false;
		this.setState(this.state);
	}
	
	
	//Event handlers
	
	@Override
	public void mouseReleased(MouseEvent e) {
		//If the game is not running then flip the cell
		
		if (e.getButton() == MouseEvent.BUTTON1) {
			if (this.parent.running == false) {
				this.flipState();
			}
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			if (this.parent.running == false && !this.ant) {
				//Check that no other cell has been set to be an ant
				boolean foundAnt = false;
				for (ArrayList<CellCanvas> array : this.parent.cells) {
					for (CellCanvas canvas : array) {
						if (canvas.ant) {
							foundAnt = true;
						}
					}
				}
				
				//If no cell was found setAnt
				
				if (!foundAnt) {
					this.setAnt();
				}
				
			} else if (!this.parent.running && this.ant) {
				this.removeAnt();
			}
		}
		
		
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {	
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}
	
	@Override
	public String toString(){
		
		if (this.getState()) {
			return "True";
		}
		
		return "False";
	}
}
