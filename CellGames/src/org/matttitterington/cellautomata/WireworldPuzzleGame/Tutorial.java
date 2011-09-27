package org.matttitterington.cellautomata.WireworldPuzzleGame;

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
		BasicsMainContainer.setLayout(new MigLayout("", "[25.00px][grow]", "[grow][grow][grow][grow][grow]"));
		
		JPanel CellCanvasEmptyContainer = new JPanel();
		CellCanvasEmptyContainer.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		BasicsMainContainer.add(CellCanvasEmptyContainer, "cell 0 0");
		
		JPanel CellCanvasEmpty = new CellCanvas(CellCanvas.EMPTY);
		CellCanvasEmptyContainer.add(CellCanvasEmpty);
		
		JTextPane txtpnEmptyCell = new JTextPane();
		txtpnEmptyCell.setBackground(SystemColor.control);
		txtpnEmptyCell.setText("Empty cell. These cells do nothing and remain empty all the time.");
		txtpnEmptyCell.setEditable(false);
		BasicsMainContainer.add(txtpnEmptyCell, "cell 1 0,grow");
		
		JPanel CellCanvasWireContainer = new JPanel();
		CellCanvasWireContainer.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		BasicsMainContainer.add(CellCanvasWireContainer, "cell 0 1");
		
		JPanel CellCanvasWire = new CellCanvas(CellCanvas.WIRE);
		CellCanvasWireContainer.add(CellCanvasWire);
		
		JTextPane txtpnWireCell = new JTextPane();
		txtpnWireCell.setBackground(SystemColor.control);
		txtpnWireCell.setEditable(false);
		txtpnWireCell.setText("Wire cell. These cells will turn into an electron head if it is surrounded by one or two existing head cells. Otherwise, it stays as a wire cell.");
		BasicsMainContainer.add(txtpnWireCell, "cell 1 1,grow");
		
		JPanel CellCanvasHeadContainer = new JPanel();
		CellCanvasHeadContainer.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		BasicsMainContainer.add(CellCanvasHeadContainer, "cell 0 2");
		
		JPanel CellCanvasHead = new CellCanvas(CellCanvas.HEAD);
		CellCanvasHeadContainer.add(CellCanvasHead);
		
		JTextPane txtpnHeadCell = new JTextPane();
		txtpnHeadCell.setBackground(SystemColor.control);
		txtpnHeadCell.setText("Head cell. This cell always turns into a tail cell.");
		BasicsMainContainer.add(txtpnHeadCell, "cell 1 2,grow");
		
		JPanel CellCanvasTailContainer = new JPanel();
		CellCanvasTailContainer.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		BasicsMainContainer.add(CellCanvasTailContainer, "cell 0 3");
		
		JPanel CellCanvasTail = new CellCanvas(CellCanvas.TAIL);
		CellCanvasTailContainer.add(CellCanvasTail);
		
		JTextPane txtpnTailCell = new JTextPane();
		txtpnTailCell.setBackground(SystemColor.control);
		txtpnTailCell.setText("Tail cell. This cell always turns back into wire.");
		BasicsMainContainer.add(txtpnTailCell, "cell 1 3,grow");
		
		JTextPane txtpnSeeTheAbout = new JTextPane();
		txtpnSeeTheAbout.setBackground(SystemColor.control);
		txtpnSeeTheAbout.setText("See the about menu for links to resources including logic gates and more complicated structures.");
		BasicsMainContainer.add(txtpnSeeTheAbout, "cell 1 4,grow");
		
		JLabel lblBasicTutorial = new JLabel("Basic tutorial.");
		lblBasicTutorial.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBasicTutorial.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblBasicTutorial, BorderLayout.NORTH);
	}
}
