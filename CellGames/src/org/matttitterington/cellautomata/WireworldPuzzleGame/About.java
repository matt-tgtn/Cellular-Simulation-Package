package org.matttitterington.cellautomata.WireworldPuzzleGame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.awt.SystemColor;

public class About {

	private JFrame frmAbout;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new About();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public About() {
		initialize();
		frmAbout.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAbout = new JFrame();
		frmAbout.setTitle("About");
		frmAbout.setBounds(100, 100, 545, 295);
		frmAbout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAbout.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frmAbout.getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton btnClose = new JButton("Close");
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmAbout.dispose();
			}
		});
		
		JButton btnWikipedia = new JButton("Wikipedia");
		btnWikipedia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				OpenBrowser.openURL("http://en.wikipedia.org/wiki/Wireworld");
			}
		});
		panel.add(btnWikipedia);
		
		JButton btnWireworldComputer = new JButton("Wireworld computer");
		btnWireworldComputer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				OpenBrowser.openURL("http://www.quinapalus.com/wi-index.html");
			}
		});
		panel.add(btnWireworldComputer);
		
		JButton btnWireworldWorld = new JButton("Wireworld world");
		btnWireworldWorld.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				OpenBrowser.openURL("http://www.heise.ws/wireworld.html");
			}
		});
		panel.add(btnWireworldWorld);
		
		Component glue = Box.createGlue();
		panel.add(glue);
		panel.add(btnClose);
		
		JTextPane txtpnHelloWorld = new JTextPane();
		txtpnHelloWorld.setBackground(SystemColor.control);
		txtpnHelloWorld.setText("Wireworld puzzle game.\r\n\r\nWireworld is a turing complete cellular simulation. See the tutorial for more details on the game itself and click on the links below for more details and articles relating to wireworld.\r\n\r\nIn the puzzle game, the two inputs must be manipulated and returned to the output wire in the correct format. The game uses 6-tick detection.\r\n\r\nCredit for the idea goes to Brian Silverman.\r\n\r\n(C) Matt Titterington 2011. Licensed under the GPL public license.");
		txtpnHelloWorld.setEditable(false);
		frmAbout.getContentPane().add(txtpnHelloWorld, BorderLayout.NORTH);
	}

}
