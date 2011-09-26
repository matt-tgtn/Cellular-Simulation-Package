package org.matttitterington.cellautomata.GameOfLife;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

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
		frmAbout.setBounds(100, 100, 540, 204);
		frmAbout.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		panel.add(btnClose);
		
		JTextPane txtpnHelloWorld = new JTextPane();
		txtpnHelloWorld.setBackground(SystemColor.control);
		txtpnHelloWorld.setText("Game of Life cellular automata.\r\n\r\nCredit for the idea goes to John Horton Conway.\r\n\r\n(C) Matt Titterington 2011. Licensed under the GPL public license.");
		txtpnHelloWorld.setEditable(false);
		frmAbout.getContentPane().add(txtpnHelloWorld, BorderLayout.NORTH);
	}

}
