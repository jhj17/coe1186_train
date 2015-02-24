import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MovingBlockOverlayMainGUI {

	private static JFrame mainFrame;
	private static JMenuBar menuBar;
	private static JMenu helpMenu;
	private static JMenuItem manualMenu, aboutMenu;
	private static JTextField startInput;
	private static JLabel headerLabel, modeLabel, startLabel, lineLabel;
	private static JButton mboButton, fixedBlockButton, viewButton, exportButton, startButton, dispatchButton;
	private static Boolean mboMode, fixedBlockMode;

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch (Exception e) {
			System.out.println("Class Not Found!");
		}

		mboMode = false;
		fixedBlockMode = true;

		mainFrame = new JFrame("Moving Block Overlay Module");
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setSize(650,500);

		menuBar = new JMenuBar();
		helpMenu = new JMenu("Help");
		manualMenu = new JMenuItem("User's Manual");
		aboutMenu = new JMenuItem("About...");
		helpMenu.add(manualMenu);
		helpMenu.add(aboutMenu);
		menuBar.add(helpMenu);

		startInput        = new JTextField();
		startInput.setColumns(4);

		headerLabel       = new JLabel("John Abraham - Moving Block Overlay Scheduler");
		lineLabel         = new JLabel("_______________________________________________________________________________________________");
		modeLabel         = new JLabel("Mode of Operation:");
		startLabel        = new JLabel("Start Schedule at Time:");

		mboButton         = new JButton("MBO");
		fixedBlockButton  = new JButton("Fixed Block");
		viewButton        = new JButton("View Operator Schedule");
		exportButton      = new JButton("Export Schedule to CTC");
		startButton       = new JButton("Start");
		dispatchButton    = new JButton("Dispatch Trains from the Yard");

		mboButton.setBackground(Color.WHITE);
		fixedBlockButton.setBackground(Color.GREEN);
		viewButton.setBackground(Color.WHITE);
		exportButton.setBackground(Color.WHITE);
		startButton.setBackground(Color.WHITE);
		dispatchButton.setBackground(Color.WHITE);

		/***************SET CONDITION DEPENDING ON MODE OF OPERATION****************/
		String columnNames[] = { "Train ID", "Line", "Station", "MBO Mode", "Fixed Block Mode" };

		String dataValues[][] =
		{
			{ "1", "GREEN", "67", "1", "1", "1" },
			{ "2", "GREEN", "853", "1", "1", "1" },
			{ "3", "GREEN", "109", "1", "1", "1" },
			{ "4", "GREEN", "3092", "1", "1", "1"}
		};

		JTable scheduleTable = new JTable(dataValues, columnNames);
		JScrollPane jScrollPane = new JScrollPane(scheduleTable);
		/****************************************************************************/


		Panel topPanel      = new Panel();
		Panel centerPanel   = new Panel(new FlowLayout());

		mainFrame.add(topPanel, BorderLayout.NORTH);
		mainFrame.add(centerPanel, BorderLayout.CENTER);
		mainFrame.setJMenuBar(menuBar);

		topPanel.add(headerLabel);
		centerPanel.add(lineLabel);
		centerPanel.add(modeLabel);
		centerPanel.add(mboButton);
		centerPanel.add(fixedBlockButton);
		centerPanel.add(startLabel);
		centerPanel.add(startInput);
		centerPanel.add(startButton);
		centerPanel.add(dispatchButton);
		centerPanel.add(viewButton);
		centerPanel.add(exportButton);
		centerPanel.add(jScrollPane);

		mainFrame.setVisible(true);

		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		manualMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent unused) {
				JOptionPane.showMessageDialog(mainFrame, "This will be the \"User's Manual\" section...");
			}
		});

		aboutMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent unused) {
				JOptionPane.showMessageDialog(mainFrame, "This will be the \"About\" section...");
			}
		});

		mboButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent unused) {
				mboMode = true;
				fixedBlockMode = false;
				mboButton.setBackground(Color.GREEN);
				fixedBlockButton.setBackground(Color.WHITE);
			}
		});

		fixedBlockButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent unused) {
				mboMode = false;
				fixedBlockMode = true;
				mboButton.setBackground(Color.WHITE);
				fixedBlockButton.setBackground(Color.GREEN);
			}
		});

		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent unused) {
				JOptionPane.showMessageDialog(mainFrame, "Boo!");
			}
		});

		dispatchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent unused) {
				JOptionPane.showMessageDialog(mainFrame, "This will dipatch trains from the yard showing success or failure...");
			}
		});

		exportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent unused) {
				JOptionPane.showMessageDialog(mainFrame, "This will export the current schedule to the CTC showing success or failure...");
			}
		});

		viewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent unused) {
				JFrame operatorScheduleFrame = new JFrame("Transit Operator Schedule");
				operatorScheduleFrame.setLayout(new BorderLayout());
				operatorScheduleFrame.setSize(650,200);
				operatorScheduleFrame.setVisible(true);
			}
		});

	}
}