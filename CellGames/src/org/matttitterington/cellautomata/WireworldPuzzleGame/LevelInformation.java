package org.matttitterington.cellautomata.WireworldPuzzleGame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class LevelInformation extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Level level;


	/**
	 * Create the dialog.
	 */
	public LevelInformation(Level level, GameInterface parent) {
		
		super(parent, true);
		
		this.level = level;
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				dispose();
			}
		});
		
		initialise();
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	private void initialise() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				JLabel lblLevelX = new JLabel("Level "+this.level.ID+".");
				lblLevelX.setFont(new Font("Tahoma", Font.PLAIN, 18));
				panel.add(lblLevelX);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new MigLayout("", "[grow]", "[grow][grow,fill][grow]"));
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, "flowy,cell 0 0,growx,aligny top");
				{
					JLabel lblLevelsummary = new JLabel(this.level.levelSummary);
					lblLevelsummary.setFont(new Font("Tahoma", Font.BOLD, 12));
					panel_1.add(lblLevelsummary);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, "cell 0 1,grow");
				panel_1.setLayout(new BorderLayout(0, 0));
				{
					JTextArea txtpnLeveldesription = new JTextArea();
					txtpnLeveldesription.setBackground(SystemColor.control);
					txtpnLeveldesription.setEditable(false);
					txtpnLeveldesription.setLineWrap(true);
					txtpnLeveldesription.setWrapStyleWord(true);
					panel_1.add(txtpnLeveldesription);
					txtpnLeveldesription.setText(this.level.levelDescription);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, "flowx,cell 0 2,growx,aligny bottom");
				{
					JLabel lblElectronsAllowedTo = new JLabel("Electrons allowed to remain on the board:");
					panel_1.add(lblElectronsAllowedTo);
				}
				{
					JLabel lblElectronallowance = new JLabel(Integer.toString(this.level.electronAllowance));
					panel_1.add(lblElectronallowance);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			{
				JLabel lblPressAnyKey = new JLabel("Press enter to continue.");
				lblPressAnyKey.setHorizontalAlignment(SwingConstants.LEFT);
				buttonPane.add(lblPressAnyKey);
			}
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Hint");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(getContentPane(), level.getLevelHint() ,"Hint", JFrame.DISPOSE_ON_CLOSE);
					}
				});
				cancelButton.setActionCommand("Hint\r\n");
				buttonPane.add(cancelButton);
			}
			{
				JButton btnTutorial = new JButton("Tutorial");
				btnTutorial.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						OpenBrowser.openURL(getClass().getResource("/tutorial/tutorial.html").toString());
					}
				});
				buttonPane.add(btnTutorial);
			}
		}
	}

}
