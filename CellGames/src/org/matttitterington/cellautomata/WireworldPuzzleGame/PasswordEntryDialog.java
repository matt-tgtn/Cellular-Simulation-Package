package org.matttitterington.cellautomata.WireworldPuzzleGame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class PasswordEntryDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textLevel;
	private JTextField textPassword;
	private JTextPane textPnFeedback;
	@SuppressWarnings("unused")
	private GameInterface parent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PasswordEntryDialog(final GameInterface parent) {
		
		this.parent = parent;
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
		
		setBounds(100, 100, 451, 209);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			{
				JLabel lblLevel = new JLabel("Level:");
				panel.add(lblLevel);
			}
			{
				textLevel = new JTextField();
				panel.add(textLevel);
				textLevel.setColumns(10);
			}
			{
				Component rigidArea = Box.createRigidArea(new Dimension(30, 0));
				panel.add(rigidArea);
			}
			{
				JLabel lblPassword = new JLabel("Password:");
				panel.add(lblPassword);
			}
			{
				textPassword = new JTextField();
				panel.add(textPassword);
				textPassword.setColumns(10);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				JLabel lblEnterTheLevel = new JLabel("Enter the level and password below.");
				lblEnterTheLevel.setFont(new Font("Tahoma", Font.PLAIN, 16));
				panel.add(lblEnterTheLevel);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.SOUTH);
			{
				textPnFeedback = new JTextPane();
				textPnFeedback.setBackground(SystemColor.control);
				textPnFeedback.setText("Press OK to continue.");
				panel.add(textPnFeedback);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int level = Integer.parseInt(textLevel.getText());
						String password = textPassword.getText();
						
						
						
						//Get the correct password hash
						String correctPass = parent.puzzleRunner.levelParser.getLevel(level).getLevelPassword();
						
						if (password.equalsIgnoreCase(correctPass)) {
							parent.puzzleRunner.setLevel(level);
							dispose();
						}
						else {
							textPnFeedback.setText("Incorrect password for level "+level+". Please try again.");
						}
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
