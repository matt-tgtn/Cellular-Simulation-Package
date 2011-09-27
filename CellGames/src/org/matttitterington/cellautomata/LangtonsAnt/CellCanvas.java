package org.matttitterington.cellautomata.LangtonsAnt;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CellCanvas extends JPanel implements MouseListener{
	
	private int state;
	private GameInterface parent;
	boolean ant;
	String configuration;
	HashMap<Integer, Color> colourMap;
	
	public static final int DEFAULT = 0;
	
	public CellCanvas(GameInterface parent, String configuration) {
		this.configuration = configuration;
		this.addMouseListener(this);
		this.parent = parent;
		
		
		
		
		initColourMap();
		
		this.ant = false;
		this.setState(DEFAULT);
	}


	public void initColourMap() {
		//Create the colour map
		this.colourMap = new HashMap<Integer, Color>();
		this.colourMap.put(0, new Color(0x000000)); //Black
		this.colourMap.put(1, new Color(0x660099)); //Violet
		this.colourMap.put(2, new Color(0x000066)); //Dark blue
		this.colourMap.put(3, new Color(0x0099FF)); //Blue
		this.colourMap.put(4, new Color(0x00FFCC)); //Blue-green
		this.colourMap.put(5, new Color(0x33FF33)); //Green
		this.colourMap.put(6, new Color(0xCCFF33)); //Yellow
		this.colourMap.put(7, new Color(0xFF9933)); //Orange
		this.colourMap.put(8, new Color(0xFF0000)); //Red
	}
	
	
	public CellCanvas(int state) {
		this.initColourMap();
		
		this.configuration = "ABCDEFGHIJKLMNO";
		this.setState(state);
		this.ant = false;
	}
	//State handling code
	
	public int getState() {
		return this.state;
	}
	
	public void setState(int state) {
		if (state < configuration.length()) {
			this.state = state;
		} else {throw new Error("Incorrect state passed");}
		
		
		this.setBackground(colourMap.get(state));
	}
	
	public void incrementState() {
		int nextState = (this.state + 1);
		
		if (nextState >= (configuration.length())) {
			nextState %= configuration.length();
		}
		
		this.setState(nextState);
	}
	
	public char getTurnDirection() {
		return configuration.charAt(this.state);
	}
	
	//ANT CODE
	
	public void setAnt() {
		this.ant = true;
		this.setBackground(Color.WHITE);
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
				this.incrementState();
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
		
		return Integer.toString(this.getState());
	}
}
