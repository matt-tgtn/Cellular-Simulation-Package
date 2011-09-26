package org.matttitterington.cellautomata.Wireworld;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CellCanvas extends JPanel implements MouseListener{
	
	//STATIC VARS
	//State variables
	static final int EMPTY = 0;
	static final int WIRE = 1;
	static final int HEAD = 3;
	static final int TAIL = 2;
	
	//Color variables
	static final int EMPTYCOLOR = 0x000000;
	static final int WIRECOLOR = 0xFFD700;
	static final int HEADCOLOR = 0x6495ED;
	static final int TAILCOLOR = 0xFF4500;
	
	private int state;
	private GameInterface parent;
	
	public CellCanvas(int state) {
		this.setState(state);
	}
	
	public CellCanvas(GameInterface parent) {
		this.setState(CellCanvas.EMPTY);
		this.addMouseListener(this);
		this.parent = parent;
	}
	
	public CellCanvas(GameInterface parent, int initState) {
		this.setState(initState);
		this.addMouseListener(this);
		this.parent = parent;
	}
	
	public int getState() {
		return this.state;
	}
	
	public void setState(int state) {
		this.state = state;
		
		switch (state) {
		
		case CellCanvas.EMPTY:
			this.setBackground(new Color(CellCanvas.EMPTYCOLOR));
			break;

		case CellCanvas.WIRE:
			this.setBackground(new Color(CellCanvas.WIRECOLOR));
			break;
			
		case CellCanvas.HEAD:
			this.setBackground(new Color(CellCanvas.HEADCOLOR));
			break;

		case CellCanvas.TAIL:
			this.setBackground(new Color(CellCanvas.TAILCOLOR));
			break;


		}
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		
		if (e.getButton() == MouseEvent.BUTTON1) {
			parent.mouse1Down = false;
		} else if (e.getButton() == MouseEvent.BUTTON3) {//if rightclick
			
			//Cycle between head and tail
			if (this.state == CellCanvas.HEAD) {
				this.setState(CellCanvas.TAIL);
			} else if (this.state == CellCanvas.TAIL) {
				this.setState(CellCanvas.HEAD);
			} else {
				this.setState(CellCanvas.HEAD);
			}
		}
		
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {	
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//can only edit while the game is not running
		if (!this.parent.running && parent.mouse1Down) {
			
				
			//Cycle between wire and empty
			if (this.state == CellCanvas.EMPTY) {
				this.setState(CellCanvas.WIRE);
			} else if (this.state == CellCanvas.WIRE) {
				this.setState(CellCanvas.EMPTY);
			} else {
				this.setState(CellCanvas.WIRE);
			}
				
			
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			parent.mouse1Down = true;
			
			//Cycle between wire and empty
			if (this.state == CellCanvas.EMPTY) {
				this.setState(CellCanvas.WIRE);
			} else if (this.state == CellCanvas.WIRE) {
				this.setState(CellCanvas.EMPTY);
			} else {
				this.setState(CellCanvas.WIRE);
			}
		}
	}
	
	@Override
	public String toString(){
		
		switch (state) {
		
		case CellCanvas.EMPTY:
			return "Empty";
		case CellCanvas.WIRE:
			return "Wire";
		case CellCanvas.HEAD:
			return "Head";
		case CellCanvas.TAIL:
			return "Tail";

		
		}
		return "Error";
	}
}
