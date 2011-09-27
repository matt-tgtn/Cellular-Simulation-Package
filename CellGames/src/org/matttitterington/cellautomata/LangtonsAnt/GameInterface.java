package org.matttitterington.cellautomata.LangtonsAnt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import org.matttitterington.cellautomata.Launcher.Launcher;

@SuppressWarnings("serial")
public class GameInterface extends JFrame{
	
	ArrayList<ArrayList<CellCanvas>> cells; //Will be used in the form .get(y).get(x)
	int cellArrayWidth;
	int cellArrayHeight;
	boolean running;
	AntGameRunner gameRunner;
	Ant ant;
	JToggleButton buttonRun;
	int fps;
	JLabel frameLabel;
	String configuration;
	
	
	public GameInterface(String title, int width, int height, String configuration) {
		
		//TODO implement error checking for the configuration
		configuration = configuration.toUpperCase().replaceAll("[^RL]", "");
		
		System.out.println(configuration);
		
		boolean isLongEnough = configuration.length() > 1;
		boolean containsR = configuration.contains("R");
		boolean containsL = configuration.contains("L");
		
		if (isLongEnough && (containsL || containsR)) {
			this.configuration = configuration;
		} else {
			this.configuration = "RL"; //Standard ant configuration
		}
		
		
		//SET GLOBAL PARAMETERS
		this.cellArrayWidth = width;
		this.cellArrayHeight = height;
		this.fps = 60;
		
		
		//instantiate variables
		this.cells = new ArrayList<ArrayList<CellCanvas>>();
		this.running = false;
		this.gameRunner = new AntGameRunner(this);
		this.ant = new Ant(this);		
				
		//Set swing  params
		this.setTitle(title);
		this.setSize((cellArrayWidth*9)+(58)+(cellArrayWidth-1),(cellArrayHeight*9)+(40)+(cellArrayHeight + 150)); //HINT: 838x960
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
		
		//Run the interface builder in a safe thread
		
		initialiseInterface();
		
		
		
		this.setVisible(true);
		
	}
	
	
	private void initialiseInterface() {
		JPanel panelMain = new JPanel();
		
		panelMain.setLayout(new BorderLayout());
		this.getContentPane().add(panelMain);
		
		panelMain.setBorder(new EmptyBorder(new Insets(20,20,20,20)));
		
		//MENU BAR
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnAbout = new JMenu("Help");
		menuBar.add(mnAbout);
		
		JMenuItem mntmLauncher = new JMenuItem("Launcher");
		mntmLauncher.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Launcher();
				dispose();
			}
		});
		mnAbout.add(mntmLauncher);
		
		JMenuItem mntmTutorial = new JMenuItem("Tutorial");
		mntmTutorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Tutorial();
			}
		});
		mnAbout.add(mntmTutorial);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new About();
			}
		});
		mnAbout.add(mntmAbout);
		
		
		
		//Declare the top panel
		
			JPanel panelGrid = new JPanel(new GridLayout(cellArrayWidth, cellArrayHeight, 1, 1));
			panelGrid.setBackground(Color.GRAY);
			panelGrid.setBorder(BorderFactory.createLineBorder(getBackground()));
			
			for (int y = 0; y < cellArrayHeight; y++) {
				
				this.cells.add(new ArrayList<CellCanvas>()); //Create the array for this
				
				for (int x = 0; x < cellArrayWidth; x++) {
					
					CellCanvas temp = new CellCanvas(this, configuration);
			
					temp.setState(CellCanvas.DEFAULT);
					temp.setPreferredSize(new Dimension(9,9));
					
					panelGrid.add(temp);
					this.cells.get(y).add(temp); //Add this cell to the array
					
					//Cell initialisation stuff
					
				}
			}
		
		panelMain.add(panelGrid, BorderLayout.PAGE_START);
			
		//Declare the bottom panel
		
			JPanel panelButton = new JPanel();
		
			buttonRun = new JToggleButton("Run");
			buttonRun.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
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
						step();
					}
				}
			});
			JButton buttonReset = new JButton("Reset");
			buttonReset.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					//Will cancel the thread and clear cells if clicked when running
					GameInterface i = GameInterface.this;
					
					if (i.running) {
						stopGame();
						
						i.resetCells();
						
						
					} else {
						i.resetCells();
					}
				}
			});
			this.frameLabel = new JLabel("Frame: 1");
		
			panelButton.add(buttonRun);
			panelButton.add(buttonStep);
			panelButton.add(buttonReset);
			panelButton.add(Box.createRigidArea(new Dimension(50,0)));
			panelButton.add(this.frameLabel);
			

		
		panelMain.add(panelButton, BorderLayout.PAGE_END);
			

		
	}


	public static void main(String [] args) {
		new GameInterface("Langton's ant", 50,50, "RLLR");
	}


	private void resetCells() {
		for (ArrayList<CellCanvas> array : this.cells) {
			for (CellCanvas cellCanvas : array) {
				cellCanvas.setState(CellCanvas.DEFAULT);
				cellCanvas.removeAnt();
				this.frameLabel.setText("Frame: 1");
			}
		}
	}
	
	public void step() {
		this.gameRunner.step();
		
	}
	
	public void stopGame() {
		this.running = false;
		this.gameRunner.cancel(true);
		this.buttonRun.setSelected(false);
		this.gameRunner = new AntGameRunner(this);
	}
	
	public void startGame() {
		this.gameRunner.execute();
		this.running = true;
	}
}
