package org.matttitterington.cellautomata.Launcher;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

public class PuzzleGameLauncher {

	private JFrame frmCellularAutomata;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new PuzzleGameLauncher();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PuzzleGameLauncher() {
		initialize();
		frmCellularAutomata.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCellularAutomata = new JFrame();
		frmCellularAutomata.setTitle("Wireworld game");
		frmCellularAutomata.setBounds(100, 100, 542, 328);
		frmCellularAutomata.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCellularAutomata.getContentPane().setLayout(new MigLayout("", "[500:540:550,grow]", "[][grow,center][center][grow,center][grow]"));
		
		JPanel panelTitle = new JPanel();
		frmCellularAutomata.getContentPane().add(panelTitle, "cell 0 0,growx,aligny top");
		
		JLabel lblSelectTheCellular = new JLabel("Launch the game, or practice in sandbox mode.");
		lblSelectTheCellular.setFont(new Font("Calibri", Font.BOLD, 16));
		panelTitle.add(lblSelectTheCellular);
		
		JPanel panelGameWire = new JPanel();
		frmCellularAutomata.getContentPane().add(panelGameWire, "cell 0 3");
		panelGameWire.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Wireworld Sandbox", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelGameWire.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panelGameWire.add(panel, BorderLayout.EAST);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnSmall = new JButton("Small");
		btnSmall.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmCellularAutomata.dispose();
				new org.matttitterington.cellautomata.Wireworld.GameInterface("Wireworld", 50,50);
			}
		});
		panel.add(btnSmall);
		
		JButton btnMedium = new JButton("Medium");
		btnMedium.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmCellularAutomata.dispose();
				new org.matttitterington.cellautomata.Wireworld.GameInterface("Wireworld", 75,75);
				
			}
		});
		panel.add(btnMedium);
		
		JButton btnLarge = new JButton("Large");
		btnLarge.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmCellularAutomata.dispose();
				new org.matttitterington.cellautomata.Wireworld.GameInterface("Wireworld", 90,90);
			}
		});
		panel.add(btnLarge);
		
		JLabel lblATuringcompleteAutomata = new JLabel("<HTML>A Turing-complete automata which tracks electrons on a wire. It allows for the creation of logic gates more complicated logical structures. See inside for details. </HTML>");
		lblATuringcompleteAutomata.setFont(new Font("Arial", Font.PLAIN, 12));
		lblATuringcompleteAutomata.setForeground(Color.DARK_GRAY);
		panelGameWire.add(lblATuringcompleteAutomata, BorderLayout.NORTH);
		
		JPanel panelPuzzleGame = new JPanel();
		panelPuzzleGame.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Wireworld Puzzle Game", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		frmCellularAutomata.getContentPane().add(panelPuzzleGame, "cell 0 4,growx");
		panelPuzzleGame.setLayout(new BorderLayout(0, 0));
		
		JLabel lblThisIsA = new JLabel("<HTML> This is a puzzle and logic game based off of wireworld. Take the two input streams and use <BR> your logical thinking to combine the data and create the correct output. </HTML>");
		lblThisIsA.setFont(new Font("Arial", Font.PLAIN, 12));
		lblThisIsA.setForeground(Color.DARK_GRAY);
		panelPuzzleGame.add(lblThisIsA, BorderLayout.NORTH);
		
		JPanel panel_5 = new JPanel();
		panelPuzzleGame.add(panel_5, BorderLayout.EAST);
		
		JButton btnSmall_1 = new JButton("Small");
		btnSmall_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmCellularAutomata.dispose();
				new org.matttitterington.cellautomata.WireworldPuzzleGame.GameInterface("Wireworld puzzle game", 50, 50);
			}
		});
		panel_5.add(btnSmall_1);
		
		JButton btnMedium_1 = new JButton("Medium");
		btnMedium_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmCellularAutomata.dispose();
				new org.matttitterington.cellautomata.WireworldPuzzleGame.GameInterface("Wireworld puzzle game", 75, 75);
			}
		});
		panel_5.add(btnMedium_1);
		
		JButton btnLarge_1 = new JButton("Large");
		btnLarge_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmCellularAutomata.dispose();
				new org.matttitterington.cellautomata.WireworldPuzzleGame.GameInterface("Wireworld puzzle game", 90, 90);
			}
		});
		panel_5.add(btnLarge_1);
	}

}
