package org.matttitterington.cellautomata.LangtonsAnt;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

public class Tutorial {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tutorial window = new Tutorial();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Tutorial() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 560, 425);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(1, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setBorder(new TitledBorder(null, "Basics", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.WEST);
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4, BorderLayout.EAST);
		
		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5, BorderLayout.NORTH);
		
		JPanel panel_6 = new JPanel();
		panel_1.add(panel_6, BorderLayout.SOUTH);
		
		JPanel BasicsMainContainer = new JPanel();
		panel_1.add(BasicsMainContainer, BorderLayout.CENTER);
		BasicsMainContainer.setLayout(new MigLayout("", "[25.00px,grow][grow]", "[grow][grow][grow][grow]"));
		
		JPanel CellCanvasDeadContainer = new JPanel();
		CellCanvasDeadContainer.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		BasicsMainContainer.add(CellCanvasDeadContainer, "cell 0 0");
		
		JPanel CellCanvasDead = new CellCanvas(false);
		CellCanvasDeadContainer.add(CellCanvasDead);
		
		JTextPane txtpnDeadCell = new JTextPane();
		txtpnDeadCell.setBackground(SystemColor.control);
		txtpnDeadCell.setText("Dead cells. These will become alive if they have exactly two live neighbours (as if by reproduction).");
		txtpnDeadCell.setEditable(false);
		BasicsMainContainer.add(txtpnDeadCell, "cell 1 0,growx,aligny center");
		
		JPanel CellCanvasAliveContainer = new JPanel();
		CellCanvasAliveContainer.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		BasicsMainContainer.add(CellCanvasAliveContainer, "cell 0 1");
		
		JPanel CellCanvasAlive = new CellCanvas(true);
		CellCanvasAliveContainer.add(CellCanvasAlive);
		
		JTextPane txtpnAliveCell = new JTextPane();
		txtpnAliveCell.setBackground(SystemColor.control);
		txtpnAliveCell.setEditable(false);
		txtpnAliveCell.setText("Alive cells. These will remain alive if they have exactly two or three live neighbours BUT will die if they have less than two (undercrowding) or over 3 (overcrowding).");
		BasicsMainContainer.add(txtpnAliveCell, "cell 1 1,growx,aligny center");
		
		JPanel panelAntCellContainer = new JPanel();
		panelAntCellContainer.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		BasicsMainContainer.add(panelAntCellContainer, "cell 0 2,growx,aligny center");
		
		CellCanvas panelAntCell = new CellCanvas(true);
		panelAntCell.setAnt();
		panelAntCellContainer.add(panelAntCell);
		
		JTextPane txtpnThisIsThe = new JTextPane();
		txtpnThisIsThe.setText("This is the ant (set with the right mouse button). Each generation it will move forward one cell. Then, if the cell is white it turns 90 degrees right. If it is black, it turns 90 degrees left. It then flips the colour of the cell and waits for the next turn.");
		txtpnThisIsThe.setBackground(SystemColor.control);
		BasicsMainContainer.add(txtpnThisIsThe, "cell 1 2,growx,aligny center");
		
		JTextPane txtpnSeeTheAbout = new JTextPane();
		txtpnSeeTheAbout.setBackground(SystemColor.control);
		txtpnSeeTheAbout.setText("By setting the initial configuration we can see various patterns emerge. Interestingly enough, the ant will always move randomly for a number of turns (up to 10000 and beyond) but will almost always fall into a regular, repeating pattern creating a 'highway' across the screen.");
		BasicsMainContainer.add(txtpnSeeTheAbout, "flowx,cell 1 3,growx,aligny center");
		
		JLabel lblBasicTutorial = new JLabel("Basic tutorial.");
		lblBasicTutorial.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBasicTutorial.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblBasicTutorial, BorderLayout.NORTH);
	}
}
