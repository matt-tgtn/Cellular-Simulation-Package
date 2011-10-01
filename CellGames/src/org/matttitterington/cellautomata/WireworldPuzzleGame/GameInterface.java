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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.matttitterington.cellautomata.Launcher.PuzzleGameLauncher;

@SuppressWarnings("serial")
public class GameInterface extends JFrame{
	
	//Objects
	ArrayList<ArrayList<CellCanvas>> cells; //Will be used in the form .get(y).get(x)
	WireGameRunner gameRunner;
	JToggleButton buttonRealRun;
	PuzzleRunner puzzleRunner;
	
	//Variables
	int cellArrayWidth;
	int cellArrayHeight;
	boolean running;
	int fps;
	boolean mouse1Down;
	
	int[] inputAPos;
	int[] inputBPos;
	int[] outputPos;
	

	
	
	public GameInterface(String title, int width, int height) {
		
		
		
		
		//instantiate variables
		this.running = false;
		this.mouse1Down = false;
		
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
		this.gameRunner = new WireGameRunner(this);
		
		
		//Set swing  params
		this.setTitle(title);
		this.setSize((cellArrayWidth*9)+(40)+(cellArrayWidth-1),(cellArrayHeight*9)+(40)+(cellArrayHeight + 150)); //HINT: 838x960
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
		
		//Run the interface builder in a safe thread
		
		initialiseInterface();
		this.setVisible(true);
		
		//So the interface displays before the level dialog
		this.puzzleRunner = new PuzzleRunner(this, 1, this.inputAPos, this.inputBPos, this.outputPos);
		
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
				
				fps = 6;

				
				if (running && speed1.isSelected()) {
					stopGame();
					startGame();
				} 
			}
		});
		speed1.setSelected(true);
		final JRadioButtonMenuItem speed2 = new JRadioButtonMenuItem("15 FPS");
		speed2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				
				fps = 15;

				
				if (running && speed2.isSelected()) {
					stopGame();
					startGame();
				} 
			}
		});
		
		final JRadioButtonMenuItem speed3 = new JRadioButtonMenuItem("30 FPS");
		speed3.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				
				fps = 30;

				
				if (running && speed3.isSelected()) {
					stopGame();
					startGame();
				} 
			}
		});
		final JRadioButtonMenuItem speed4 = new JRadioButtonMenuItem("60 FPS");
		speed4.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				
				fps = 60;
				
				if (running && speed4.isSelected()) {
					stopGame();
					startGame();
				} 
			}
		});
		
		JMenu mnGame = new JMenu("Game");
		menuBar.add(mnGame);
		
		JMenuItem mntmEnterPassword = new JMenuItem("Enter password");
		mntmEnterPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new PasswordEntryDialog(GameInterface.this);
			}
		});
		
		JMenuItem mntmShowObjectives = new JMenuItem("Show objectives");
		mntmShowObjectives.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LevelInformation(puzzleRunner.currentLevel, GameInterface.this);
			}
		});
		mnGame.add(mntmShowObjectives);
		mnGame.add(mntmEnterPassword);
		
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
				new PuzzleGameLauncher();
				GameInterface.this.dispose();
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
			
			buttonRealRun = new JToggleButton("Run");
			buttonRealRun.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
					if (running) {
						stopGame();
					} else {
						startGame();
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
				
				int optionPane = JOptionPane.showConfirmDialog(new JFrame(), "Are you sure you want to clear all the cells?","Clear All", JOptionPane.YES_NO_OPTION);
				
				if (running && optionPane == JOptionPane.YES_OPTION) {
					stopGame();
					resetCells();
				} else if (optionPane == JOptionPane.YES_OPTION){
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
		new GameInterface("Wireworld", 50, 50);
	}

	
	void resetCells() {
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
	
	void resetElectrons() {
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
	
	void resetElectrons(int numberToLeave) {
		int headLeft = 0;
		int tailLeft = 0;
		for (ArrayList<CellCanvas> array : this.cells) {
			for (CellCanvas cellCanvas : array) {
				if (cellCanvas.getState() == CellCanvas.HEAD){
					//If head cell and left enough already
					if (headLeft == numberToLeave) {
						cellCanvas.setState(CellCanvas.WIRE);
					} else {
						headLeft += 1;
					}
				}
				
				if (cellCanvas.getState() == CellCanvas.TAIL){
					//If tail cell and left enough already
					if (tailLeft == numberToLeave) {
						cellCanvas.setState(CellCanvas.WIRE);
					} else {
						tailLeft += 1;
					}
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
		gameRunner = new WireGameRunner(this);
		

		
		
		buttonRealRun.setSelected(false);
		
	}
	
	public void startGame() {
		running = true;
		
		gameRunner.execute();
	}
}
