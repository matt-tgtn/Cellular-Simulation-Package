package org.matttitterington.cellautomata.GameOfLife;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CellCanvas extends JPanel implements MouseListener{
	
	private boolean state;
	private GameInterface parent;
	
	public CellCanvas(GameInterface parent) {
		this.setState(false);
		this.addMouseListener(this);
		this.parent = parent;
	}
	
	public CellCanvas(GameInterface parent, boolean initState) {
		this.setState(initState);
		this.addMouseListener(this);
		this.parent = parent;
	}
	
	public CellCanvas(boolean initState) {
		this.setState(initState);
	}
	
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

	@Override
	public void mouseReleased(MouseEvent e) {
		//If the game is not running then flip the cell
		if (this.parent.running == false) {
			this.flipState();
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
