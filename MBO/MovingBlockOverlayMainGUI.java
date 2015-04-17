/**
* MovingBlockOverlayMainGUI.java
*
* @author John Abraham
*
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;  
import java.io.FileNotFoundException;
import java.lang.*;
import java.util.ArrayList;

public class MovingBlockOverlayMainGUI {

	private static boolean mboMode;

	public static void main(String[] args) {

		JFrame mainFrame;
		JTable scheduleJTable, operatorScheduleJTable;
		JScrollPane jScrollPane, oPjScrollPane;
		JTextField startInput;
		JLabel startLabel;
		JButton trainScheduleButton, operatorScheduleButton, gernerateScheduleButton, trainsStatusButton;
		JPanel topPanel, centerPanel, southPanel;
		String timeToDispatch;

		mainFrame       = new JFrame("Moving Block Overlay Scheduler GUI");
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setSize(1000,710);

		startInput 		     = new JTextField("00:00", 4);
		startLabel           = new JLabel("Start Schedule at Time:");

		trainScheduleButton = new JButton("Set Time Table");
		operatorScheduleButton = new JButton("Set Operator Schedule");
		trainsStatusButton = new JButton("Train Status");
		gernerateScheduleButton = new JButton("Generate Schedule");

		trainScheduleButton.setEnabled(true);
		operatorScheduleButton.setEnabled(true);
		trainsStatusButton.setEnabled(true);
		gernerateScheduleButton.setEnabled(false);

		String columnNames[]  = {"Train ID", "Line", "Station", "Total Time to Station w/ Dwell (min)"};
		String dataValues[][] = { { "", "", "", ""}, { "", "", "", ""}, { "", "", "", ""}, { "", "", "", ""},
								  { "", "", "", ""}, { "", "", "", ""}, { "", "", "", ""}, { "", "", "", ""},
								  { "", "", "", ""}, { "", "", "", ""}, { "", "", "", ""}, { "", "", "", ""},
								  { "", "", "", ""}, { "", "", "", ""}, { "", "", "", ""}, { "", "", "", ""},
								  { "", "", "", ""}, { "", "", "", ""}, { "", "", "", ""}, { "", "", "", ""} };

		scheduleJTable = new JTable(dataValues, columnNames);
		jScrollPane = new JScrollPane(scheduleJTable);
		jScrollPane.setPreferredSize(new Dimension(950, 350));

		String opColumnNames[] = { "Operator", "Train ID", "Shift Start", "Break Start", "Break End", "Shift End"};
		String opDataValues[][] = { { "", "", "", "", "", ""}, { "", "", "", "", "", ""}, { "", "", "", "", "", ""},
									{ "", "", "", "", "", ""}, { "", "", "", "", "", ""}, { "", "", "", "", "", ""},
									{ "", "", "", "", "", ""}, { "", "", "", "", "", ""}, { "", "", "", "", "", ""},
									{ "", "", "", "", "", ""}, { "", "", "", "", "", ""} };

		operatorScheduleJTable = new JTable(opDataValues, opColumnNames);
		oPjScrollPane = new JScrollPane(operatorScheduleJTable);
		oPjScrollPane.setPreferredSize(new Dimension(950, 200));


		topPanel      = new JPanel();
		centerPanel   = new JPanel();
		southPanel    = new JPanel();

		JPanel buttonsPanel = new JPanel(new BorderLayout());
		JPanel northButtonsPanel = new JPanel(new FlowLayout());
		JPanel centerButtonsPanel = new JPanel(new FlowLayout());
		JPanel southButtonsPanel = new JPanel(new FlowLayout());

		/**North of buttonsPanel**/
		northButtonsPanel.add(trainScheduleButton);
		northButtonsPanel.add(operatorScheduleButton);
		northButtonsPanel.add(trainsStatusButton);

		/**Center of buttonsPanel**/
		centerButtonsPanel.add(startLabel);
		centerButtonsPanel.add(startInput);

		/**South of buttonsPanel**/
		southButtonsPanel.add(gernerateScheduleButton);

		buttonsPanel.add(northButtonsPanel, BorderLayout.NORTH);
		buttonsPanel.add(centerButtonsPanel, BorderLayout.CENTER);
		buttonsPanel.add(southButtonsPanel, BorderLayout.SOUTH);

		topPanel.add(buttonsPanel);
		topPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		centerPanel.add(jScrollPane, BorderLayout.CENTER);
		southPanel.add(oPjScrollPane, BorderLayout.SOUTH);

		/************CENTER THE JFRAME IN THE MIDDLE OF THE SCREEN***********/
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		mainFrame.setLocation(dim.width/2-mainFrame.getSize().width/2, dim.height/2-mainFrame.getSize().height/2);
		mainFrame.add(topPanel, BorderLayout.NORTH);
		mainFrame.add(centerPanel, BorderLayout.CENTER);
		mainFrame.add(southPanel, BorderLayout.SOUTH);
		mainFrame.setVisible(true);

		/**
		* Window Listeners: listener interface for receiving window events.
		*/
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		operatorScheduleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent unused) {
				JFrame operatorScheduleFrame = new JFrame("Transit Operator Schedule");
				operatorScheduleFrame.setLayout(new FlowLayout());
				operatorScheduleFrame.setSize(580,200);
				JButton secondOperatorScheduleButton = new JButton("Submit");

				String columnNames[] = { "Operator", "Train ID", "Shift #"};
				String dataValues[][] = { { "", "", "" }, { "", "", "" }, { "", "", "" },
										  { "", "", "" }, { "", "", "" }, { "", "", "" } };

				JTable scheduleJTable = new JTable(dataValues, columnNames);
				JScrollPane jScrollPane = new JScrollPane(scheduleJTable);
				jScrollPane.setPreferredSize(new Dimension(550, 119));
				operatorScheduleFrame.add(jScrollPane);
				operatorScheduleFrame.add(secondOperatorScheduleButton);

				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				operatorScheduleFrame.setLocation(dim.width/2-operatorScheduleFrame.getSize().
					width/2, dim.height/2-operatorScheduleFrame.getSize().height/2);

				operatorScheduleFrame.setVisible(true);
			}

		});

		trainScheduleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent unused) {

			}
		});

		gernerateScheduleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent unused) {

			}
		});
	}

	public void setMBOMode(boolean mboMode) {
			this.mboMode = mboMode;
	}
}