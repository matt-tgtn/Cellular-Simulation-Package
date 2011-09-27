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
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.Box;
import net.miginfocom.swing.MigLayout;

public class Launcher {

	private JFrame frmCellularAutomata;
	private JTextField txtConfiguration;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Launcher();
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
		frmCellularAutomata.setBounds(100, 100, 540, 384);
		frmCellularAutomata.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCellularAutomata.getContentPane().setLayout(new MigLayout("", "[500:540:550]", "[][grow,center][center][grow,center]"));
		
		JPanel panelTitle = new JPanel();
		frmCellularAutomata.getContentPane().add(panelTitle, "cell 0 0,growx,aligny top");
		
		JLabel lblSelectTheCellular = new JLabel("Select the cellular automata you wish to launch:");
		lblSelectTheCellular.setFont(new Font("Calibri", Font.BOLD, 16));
		panelTitle.add(lblSelectTheCellular);
		
		JPanel panelGameLife = new JPanel();
		frmCellularAutomata.getContentPane().add(panelGameLife, "cell 0 1,growx");
		panelGameLife.setBorder(new TitledBorder(null, "Conwy's Game Of Life", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelGameLife.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTheWellKnown = new JLabel("<HTML>The well known and most researched of all the cellula automata. In it, a simple set of rules <BR>define if a cell is born or dies each turn.</HTML>");
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
		frmCellularAutomata.getContentPane().add(panelGameAnt, "cell 0 2");
		panelGameAnt.setBorder(new TitledBorder(null, "Langton's Ant", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelGameAnt.setLayout(new BorderLayout(0, 0));
		
		JLabel lblInThisAutomata = new JLabel("<HTML> In this automata, Langton's Ant tracks around the grid following a set of rules. The configuration pane allows you to set when it turns left and right. See the tutorial for more.<BR> </HTML>\r\n");
		lblInThisAutomata.setFont(new Font("Arial", Font.PLAIN, 12));
		lblInThisAutomata.setForeground(Color.DARK_GRAY);
		panelGameAnt.add(lblInThisAutomata);
		
		JPanel panel_3 = new JPanel();
		panelGameAnt.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		Component horizontalGlue = Box.createHorizontalGlue();
		panel_3.add(horizontalGlue);
		
		JPanel panel_2 = new JPanel();
		panel_3.add(panel_2, BorderLayout.WEST);
		
		JLabel lblConfiguration = new JLabel("Configuration:");
		panel_2.add(lblConfiguration);
		
		txtConfiguration = new JTextField();
		panel_2.add(txtConfiguration);
		txtConfiguration.setHorizontalAlignment(SwingConstants.LEFT);
		txtConfiguration.setText("RL");
		txtConfiguration.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4, BorderLayout.EAST);
		
		JButton button = new JButton("Small");
		panel_4.add(button);
		button.setHorizontalAlignment(SwingConstants.TRAILING);
		
		JButton button_1 = new JButton("Medium");
		panel_4.add(button_1);
		button_1.setHorizontalAlignment(SwingConstants.TRAILING);
		
		JButton button_2 = new JButton("Large");
		panel_4.add(button_2);
		button_2.setHorizontalAlignment(SwingConstants.TRAILING);
		
		JPanel panelGameWire = new JPanel();
		frmCellularAutomata.getContentPane().add(panelGameWire, "cell 0 3");
		panelGameWire.setBorder(new TitledBorder(null, "WireWorld", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
