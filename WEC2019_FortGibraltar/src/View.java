import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

import com.sun.rowset.internal.Row;

import java.awt.event.*;

public class View extends JFrame
{
	
	private JButton[][] gridButtons;
	private JPanel centerGrid;
	private JMenuBar menuBar;
	private JMenu size;
	private JMenuItem small;
	private JMenuItem medium;
	private JMenuItem large;
	
	public int row;
	public int col;
	
	private Grid theGrid;
	
	public View(String s, int n)
	{
		super(s);
		theGrid = new Grid(n);
		setLayout(new BorderLayout());
		makeCenterGrid(n);
		//makeMenuBar();
		makeWindowListener();
	}
	
	private void makeCenterGrid(int n)
	{
		centerGrid = new JPanel(new GridLayout(n, n));
		gridButtons = new JButton[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				gridButtons[i][j] = new JButton();
				gridButtons[i][j].setVisible(true);
				row = i;
				col = j;
				gridButtons[i][j].addActionListener(new ButtonListener());
				centerGrid.add(gridButtons[i][j]);
			}
		}
		
		this.add(centerGrid, BorderLayout.CENTER);
		System.out.println("Adding ceneter panel");
	}
	
	
	public class ButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			theGrid.makeMove(row, col);
		}
	}
	
	
	private void makeWindowListener()
	{
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog((JFrame) e.getSource(), "Are you sure you want to exit the application?",
						"Exit Application", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION)
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				if (result==JOptionPane.NO_OPTION)
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			}
		});
	}
	
}