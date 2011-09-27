package org.matttitterington.cellautomata.WireworldPuzzleGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.matttitterington.cellautomata.Launcher.Launcher;

@SuppressWarnings("serial")
public class GameInterface extends JFrame{
	
	//Objects
	ArrayList<ArrayList<CellCanvas>> cells; //Will be used in the form .get(y).get(x)
	WireGameRunner gameRunner;
	JToggleButton buttonTestRun;
	JToggleButton buttonRealRun;
	PuzzleRunner puzzleRunner;
	
	//Variables
	int cellArrayWidth;
	int cellArrayHeight;
	boolean running;
	int fps;
	boolean mouse1Down;
	boolean realRun;
	
	int[] inputAPos;
	int[] inputBPos;
	int[] outputPos;
	

	
	
	public GameInterface(String title, int width, int height) {
		
		
		
		
		//instantiate variables
		this.running = false;
		this.mouse1Down = false;
		this.realRun = false;
		
		//SET GLOBAL PARAMETERS
		this.cellArrayWidth = width;
		this.cellArrayHeight = height;
		this.fps = 6;
		
		//Positional variables
		this.inputAPos = new int[2];
		this.inputAPos[0] = (width/2)-10;
		this.inputAPos[1] = height-1;
		
		this.inputBPos = new int[2];
		this.inputBPos[0] = (width/2)+10;
		this.inputBPos[1] = height-1;
		
		this.outputPos = new int[2];
		this.outputPos[0] = (width/2);
		this.outputPos[1] = 0;
		
		//Create objects
		this.cells = new ArrayList<ArrayList<CellCanvas>>();
		this.gameRunner = new WireGameRunner(this, false);
		this.puzzleRunner = new PuzzleRunner(this, 1, this.inputAPos, this.inputBPos, this.outputPos);
		
		//Set swing  params
		this.setTitle(title);
		this.setSize((cellArrayWidth*9)+(40)+(cellArrayWidth-1),(cellArrayHeight*9)+(40)+(cellArrayHeight + 150)); //HINT: 838x960
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
		
		//Run the interface builder in a safe thread
		
		initialiseInterface();
		
		
		this.setVisible(true);
		
	}
	
	
	private void initialiseInterface() {
		
		//CREATING THE MENU BAR
		JMenuBar menuBar = new JMenuBar();
		
		//Menu for the rate of the game
		JMenu menuSpeed = new JMenu("Speed");
		
		ButtonGroup group = new ButtonGroup();
		final JRadioButtonMenuItem speed1 = new JRadioButtonMenuItem("6 FPS");
		speed1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				GameInterface i = GameInterface.this;
				
				fps = 6;

				
				if (running && speed1.isSelected()) {
					gameRunner.cancel(true);
					gameRunner = new WireGameRunner(i, realRun);
					gameRunner.execute();
				} 
			}
		});
		speed1.setSelected(true);
		final JRadioButtonMenuItem speed2 = new JRadioButtonMenuItem("15 FPS");
		speed2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				GameInterface i = GameInterface.this;
				
				fps = 15;

				
				if (running && speed2.isSelected()) {
					gameRunner.cancel(true);
					gameRunner = new WireGameRunner(i, realRun);
					gameRunner.execute();
				} 
			}
		});
		
		final JRadioButtonMenuItem speed3 = new JRadioButtonMenuItem("30 FPS");
		speed3.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				GameInterface i = GameInterface.this;
				
				fps = 30;

				
				if (running && speed3.isSelected()) {
					gameRunner.cancel(true);
					gameRunner = new WireGameRunner(i, realRun);
					gameRunner.execute();
				} 
			}
		});
		final JRadioButtonMenuItem speed4 = new JRadioButtonMenuItem("60 FPS");
		speed4.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				GameInterface i = GameInterface.this;
				
				fps = 60;
				
				if (running && speed4.isSelected()) {
					gameRunner.cancel(true);
					gameRunner = new WireGameRunner(i, realRun);
					gameRunner.execute();
				} 
			}
		});
		
		group.add(speed1);
		menuSpeed.add(speed1);
		group.add(speed2);
		menuSpeed.add(speed2);
		group.add(speed3);
		menuSpeed.add(speed3);
		group.add(speed4);
		menuSpeed.add(speed4);
		
		menuBar.add(menuSpeed);
		
		this.setJMenuBar(menuBar);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmTutorial = new JMenuItem("Tutorial");
		mntmTutorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Tutorial();
			}
		});
		
		JMenuItem mntmLauncher = new JMenuItem("Launcher");
		mntmLauncher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Launcher();
				GameInterface i = GameInterface.this;
				i.dispose();
			}
		});
		mnHelp.add(mntmLauncher);
		mnHelp.add(mntmTutorial);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new About();
			}
		});
		mnHelp.add(mntmAbout);
		
		//CREATE THE MAIN PART OF THE INTERFACE
		JPanel panelMain = new JPanel();
		
		panelMain.setLayout(new BorderLayout());
		this.getContentPane().add(panelMain);
		
		panelMain.setBorder(null);
		
		//Declare the top panel
		
			JPanel panelGrid = new JPanel(new GridLayout(cellArrayWidth, cellArrayHeight, 1, 1));
			//JPanel panelGrid = new JPanel(new GridLayout(3, 3, 1, 1));
			panelGrid.setForeground(Color.DARK_GRAY);
			panelGrid.setBackground(new Color(240, 240, 240));
			panelGrid.setBorder(new EmptyBorder(10, 10, 10, 10));
			
			for (int y = 0; y < cellArrayHeight; y++) {
				
				this.cells.add(new ArrayList<CellCanvas>()); //Create the array for this
				
				for (int x = 0; x < cellArrayWidth; x++) {
					
					boolean isInputAPos = (x == this.inputAPos[0]) && (y==this.inputAPos[1] || y==this.inputAPos[1]-1);
					boolean isInputBPos = (x == this.inputBPos[0]) && (y==this.inputBPos[1] || y==this.inputBPos[1]-1);
					boolean isOutputPos = (x == this.outputPos[0]) && (y==this.outputPos[1] || y==this.outputPos[1]+1);
					
					CellCanvas temp;
					
					if (isInputAPos || isInputBPos || isOutputPos) {
						temp = new CellCanvas(this, false);
						temp.setState(CellCanvas.WIRE);
					} else {
						temp = new CellCanvas(this, true);
						temp.setState(CellCanvas.EMPTY);
					}
					
					
					
					temp.setPreferredSize(new Dimension(9,9));
					
					panelGrid.add(temp);
					this.cells.get(y).add(temp); //Add this cell to the array
					
					//Cell initialisation stuff
					
				}
			}
		
		panelMain.add(panelGrid, BorderLayout.PAGE_START);
			
		//Declare the bottom panel
		
			JPanel panelButton = new JPanel();
			panelButton.setBorder(new EmptyBorder(0, 10, 10, 5));
		
			buttonTestRun = new JToggleButton("Test Run");
			buttonTestRun.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					if (running && realRun) {
						stopGame();
						startGame(false);
					} else if (!running) {
						startGame(false);
					} else if (running && !realRun) {
						stopGame();
					}
				}

			});
			
			buttonRealRun = new JToggleButton("Run");
			buttonRealRun.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					if (running && realRun) {
						stopGame();
					} else if (!running) {
						startGame(true);
					} else if (running && !realRun) {
						stopGame();
						startGame(true);
					}
				}

			});
			
			JButton buttonStep = new JButton("Step");
			buttonStep.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (!running) {
						gameRunner.step();
					}
				}
			});
		
			panelButton.add(buttonRealRun);
			panelButton.add(buttonTestRun);
			panelButton.add(buttonStep);
			
		
		panelMain.add(panelButton, BorderLayout.PAGE_END);
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 0));
		panelButton.add(rigidArea);
		
		JPanel panelReset = new JPanel();
		panelReset.setBorder(new TitledBorder(null, "Reset", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelButton.add(panelReset);
		
		JButton btnAll = new JButton("All");
		btnAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if (running) {
					stopGame();
					
					resetCells();
				} else {
					resetCells();
				}
			}
		});
		panelReset.add(btnAll);
		
		JButton btnElectrons = new JButton("Electrons");
		btnElectrons.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (running) {
					stopGame();
					
					resetElectrons();
					
				} else {
					resetElectrons();
				}
			}
		});
		panelReset.add(btnElectrons);
			
		


		
	}


	public static void main(String [] args) {
		new GameInterface("Wireworld", 80, 80);
	}

	
	private void resetCells() {
		for (ArrayList<CellCanvas> array : this.cells) {
			for (CellCanvas cellCanvas : array) {
				if (cellCanvas.isEditable()){
					cellCanvas.setState(CellCanvas.EMPTY);
				} else {
					cellCanvas.setState(CellCanvas.WIRE);
				}
			}
		}
	}
	
	private void resetElectrons() {
		for (ArrayList<CellCanvas> array : this.cells) {
			for (CellCanvas cellCanvas : array) {
				if ((cellCanvas.getState() == CellCanvas.HEAD)||(cellCanvas.getState() == CellCanvas.TAIL)) {
					//This allows people to have the regular output from the input nodules when testing the game
					this.puzzleRunner.newRun();
					cellCanvas.setState(CellCanvas.WIRE);
				}
			}
		}
	}
	
	public void step() {
		this.gameRunner.step();
	}
	
	public void stopGame() {
		running = false;
		gameRunner.cancel(true);
		gameRunner = new WireGameRunner(this, realRun);
		
		System.out.println(realRun);
		
		if (realRun) {
			buttonRealRun.setSelected(false);
		} else {
			buttonTestRun.setSelected(false);
		}
	}
	
	public void startGame(boolean real) {
		running = true;
		realRun = real;
		
		//Make sure that you can't cheat in a real run of the game
		if (realRun) {
			this.resetElectrons();
		}
		
		gameRunner.execute();
	}
}
