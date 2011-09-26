package org.matttitterington.cellautomata.Launcher;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class Launcher {

	private JFrame frmCellularAutomata;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Launcher window = new Launcher();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Launcher() {
		initialize();
		frmCellularAutomata.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCellularAutomata = new JFrame();
		frmCellularAutomata.setTitle("Cellular Automata");
		frmCellularAutomata.setBounds(100, 100, 465, 424);
		frmCellularAutomata.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCellularAutomata.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelTitle = new JPanel();
		frmCellularAutomata.getContentPane().add(panelTitle, BorderLayout.NORTH);
		
		JLabel lblSelectTheCellular = new JLabel("Select the cellular automata you wish to launch:");
		lblSelectTheCellular.setFont(new Font("Calibri", Font.BOLD, 16));
		panelTitle.add(lblSelectTheCellular);
		
		JPanel panelGames = new JPanel();
		frmCellularAutomata.getContentPane().add(panelGames, BorderLayout.SOUTH);
		panelGames.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panelGameLife = new JPanel();
		panelGameLife.setBorder(new TitledBorder(null, "Conwy's Game Of Life", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelGames.add(panelGameLife);
		panelGameLife.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTheWellKnown = new JLabel("<HTML>The well known and most researched of all the cellula automata.</HTML>");
		lblTheWellKnown.setFont(new Font("Arial", Font.PLAIN, 12));
		lblTheWellKnown.setForeground(Color.DARK_GRAY);
		panelGameLife.add(lblTheWellKnown, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		panelGameLife.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JButton btnLaunch = new JButton("Launch");
		btnLaunch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new org.matttitterington.cellautomata.GameOfLife.GameInterface("Conwy's Game of Life");
				frmCellularAutomata.dispose();
			}
		});
		panel_1.add(btnLaunch, BorderLayout.EAST);
		
		JPanel panelGameAnt = new JPanel();
		panelGameAnt.setBorder(new TitledBorder(null, "Langton's Ant", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelGames.add(panelGameAnt);
		panelGameAnt.setLayout(new BorderLayout(0, 0));
		
		JLabel lblInThisAutomata = new JLabel("<HTML> In this automata, Langton's Ant tracks around the grid turning left on black <BR> cells and right on white cells, flipping the colour as it goes.</HTML>\r\n");
		lblInThisAutomata.setFont(new Font("Arial", Font.PLAIN, 12));
		lblInThisAutomata.setForeground(Color.DARK_GRAY);
		panelGameAnt.add(lblInThisAutomata, BorderLayout.NORTH);
		
		JPanel panel_2 = new JPanel();
		panelGameAnt.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JButton btnLaunch_1 = new JButton("Launch");
		btnLaunch_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new org.matttitterington.cellautomata.LangtonsAnt.GameInterface("Langton's ant");
				frmCellularAutomata.dispose();
			}
		});
		btnLaunch_1.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_2.add(btnLaunch_1, BorderLayout.EAST);
		
		JPanel panelGameWire = new JPanel();
		panelGameWire.setBorder(new TitledBorder(null, "WireWorld", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelGames.add(panelGameWire);
		panelGameWire.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panelGameWire.add(panel, BorderLayout.EAST);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnSmall = new JButton("Small");
		btnSmall.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new org.matttitterington.cellautomata.Wireworld.GameInterface("Wireworld", 50,50);
				frmCellularAutomata.dispose();
			}
		});
		panel.add(btnSmall);
		
		JButton btnMedium = new JButton("Medium");
		btnMedium.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new org.matttitterington.cellautomata.Wireworld.GameInterface("Wireworld", 75,75);
				frmCellularAutomata.dispose();
			}
		});
		panel.add(btnMedium);
		
		JButton btnLarge = new JButton("Large");
		btnLarge.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new org.matttitterington.cellautomata.Wireworld.GameInterface("Wireworld", 90,90);
				frmCellularAutomata.dispose();
			}
		});
		panel.add(btnLarge);
		
		JLabel lblATuringcompleteAutomata = new JLabel("<HTML>A Turing-complete automata which tracks electrons on a wire. It allows for the creation of logic gates more complicated logical structures. See inside for details. </HTML>");
		lblATuringcompleteAutomata.setFont(new Font("Arial", Font.PLAIN, 12));
		lblATuringcompleteAutomata.setForeground(Color.DARK_GRAY);
		panelGameWire.add(lblATuringcompleteAutomata, BorderLayout.NORTH);
	}

}
