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
import java.util.*;

public class MovingBlockOverlayMainGUI {

	private static boolean mboMode;
	private static Scheduler scheduler = new Scheduler();

	public static void main(String[] args) {

		JFrame mainFrame;
		JTable scheduleJTable, operatorScheduleJTable;
		JScrollPane jScrollPane, oPjScrollPane;
		JLabel startLabel, modeOfOperationLabel, fixedBlockModeLabel, mboModeLabel;
		JSpinner startInput;
		JButton setTimeTableButton, operatorScheduleButton, passengerCountButton, gernerateScheduleButton;
		JPanel topPanel, centerPanel, southPanel;
		String timeToDispatch;

		mainFrame = new JFrame("Moving Block Overlay Scheduler GUI");
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setSize(1000,710);

		startInput = new JSpinner();
		startInput.setModel(new SpinnerDateModel());
		startInput.setEditor(new JSpinner.DateEditor(startInput, "hh:mm a"));

		startLabel = new JLabel("Start Schedule at Time:");
		modeOfOperationLabel = new JLabel("Mode of Operation: ");
		fixedBlockModeLabel = new JLabel("Fixed Block");
		fixedBlockModeLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		fixedBlockModeLabel.setBackground(Color.GREEN);
		fixedBlockModeLabel.setOpaque(true);
		mboModeLabel = new JLabel("MBO");
		mboModeLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		mboModeLabel.setOpaque(true);

		setTimeTableButton = new JButton("Set Time Table");
		operatorScheduleButton = new JButton("Set Operator Schedule");
		passengerCountButton = new JButton("Passenger Count");
		gernerateScheduleButton = new JButton("Generate Operator and Train Schedules");

		setTimeTableButton.setEnabled(true);
		operatorScheduleButton.setEnabled(true);
		passengerCountButton.setEnabled(true);
		gernerateScheduleButton.setEnabled(false);

		String columnNames[]  = {"Train ID", "Line", "Station", "Total Time to Station w/ Dwell (min)"};
		String dataValues[][] = { { "", "", "", ""}, { "", "", "", ""}, { "", "", "", ""}, { "", "", "", ""},
								  { "", "", "", ""}, { "", "", "", ""}, { "", "", "", ""}, { "", "", "", ""},
								  { "", "", "", ""}, { "", "", "", ""}, { "", "", "", ""}, { "", "", "", ""},
								  { "", "", "", ""}, { "", "", "", ""}, { "", "", "", ""}, { "", "", "", ""},
								  { "", "", "", ""}, { "", "", "", ""}, { "", "", "", ""}, { "", "", "", ""},
								  { "", "", "", ""}, { "", "", "", ""}, { "", "", "", ""} };

		scheduleJTable = new JTable(dataValues, columnNames);
		jScrollPane = new JScrollPane(scheduleJTable);
		jScrollPane.setPreferredSize(new Dimension(950, 391));

		String opColumnNames[] = { "Operator", "Train ID", "Shift Start", "Break Start", "Break End", "Shift End"};
		String opDataValues[][] = { { "", "", "", "", "", ""}, { "", "", "", "", "", ""}, { "", "", "", "", "", ""},
									{ "", "", "", "", "", ""}, { "", "", "", "", "", ""}, { "", "", "", "", "", ""} };

		operatorScheduleJTable = new JTable(opDataValues, opColumnNames);
		oPjScrollPane = new JScrollPane(operatorScheduleJTable);
		oPjScrollPane.setPreferredSize(new Dimension(950, 119));


		topPanel      = new JPanel();
		centerPanel   = new JPanel();
		southPanel    = new JPanel();

		JPanel buttonsPanel = new JPanel(new BorderLayout());
		JPanel northButtonsPanel = new JPanel(new BorderLayout());
		JPanel northButtonsPanelTop = new JPanel(new FlowLayout());
		JPanel northButtonsPanelBottom = new JPanel(new FlowLayout());
		JPanel centerButtonsPanel = new JPanel(new FlowLayout());
		JPanel southButtonsPanel = new JPanel(new FlowLayout());

		/**North of buttonsPanel**/
		northButtonsPanelTop.add(modeOfOperationLabel);
		northButtonsPanelTop.add(fixedBlockModeLabel);
		northButtonsPanelTop.add(mboModeLabel);
		northButtonsPanelBottom.add(setTimeTableButton);
		northButtonsPanelBottom.add(operatorScheduleButton);
		northButtonsPanelBottom.add(passengerCountButton);
		northButtonsPanel.add(northButtonsPanelTop, BorderLayout.NORTH);
		northButtonsPanel.add(northButtonsPanelBottom, BorderLayout.SOUTH);

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


		setTimeTableButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent unused) {
				final JFrame timeTableFrame = new JFrame("Set Time Table");
				timeTableFrame.setLayout(new FlowLayout());
				timeTableFrame.setSize(580,200);

				JButton submitButton = new JButton("Submit");

				String columnNames[] = { "Train ID", "Station", "Throughput (min)"};
				String dataValues[][] = { { "", "", "" }, { "", "", "" }, { "", "", "" },
										  { "", "", "" }, { "", "", "" }, { "", "", "" },
										  { "", "", "" }, { "", "", "" }, { "", "", "" },
										  { "", "", "" }, { "", "", "" }, { "", "", "" },
										  { "", "", "" }, { "", "", "" }, { "", "", "" },
										  { "", "", "" }, { "", "", "" }, { "", "", "" },
										  { "", "", "" }, { "", "", "" }, { "", "", "" },
										  { "", "", "" }, { "", "", "" }, { "", "", "" } };

				JTable scheduleJTable = new JTable(dataValues, columnNames);
				JScrollPane jScrollPane = new JScrollPane(scheduleJTable);
				jScrollPane.setPreferredSize(new Dimension(550, 119));
				timeTableFrame.add(jScrollPane);
				timeTableFrame.add(submitButton);

				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				timeTableFrame.setLocation(dim.width/2-timeTableFrame.getSize().width/2,
					dim.height/2-timeTableFrame.getSize().height/2);
				timeTableFrame.setVisible(true);

				submitButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent unused) {
						//add time table information to scheduler
						timeTableFrame.dispose();
					}
				});
			}
		});

		operatorScheduleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent unused) {
				final JFrame operatorScheduleFrame = new JFrame("Set Train Operator Schedule");
				operatorScheduleFrame.setLayout(new FlowLayout());
				operatorScheduleFrame.setSize(580,200);
				JButton submitButton = new JButton("Submit");

				String columnNames[] = { "Operator", "Train ID", "Shift #"};
				String dataValues[][] = { { "", "", "" }, { "", "", "" }, { "", "", "" },
										  { "", "", "" }, { "", "", "" }, { "", "", "" } };

				JTable scheduleJTable = new JTable(dataValues, columnNames);
				JScrollPane jScrollPane = new JScrollPane(scheduleJTable);
				jScrollPane.setPreferredSize(new Dimension(550, 119));
				operatorScheduleFrame.add(jScrollPane);
				operatorScheduleFrame.add(submitButton);

				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				operatorScheduleFrame.setLocation(dim.width/2-operatorScheduleFrame.getSize().
					width/2, dim.height/2-operatorScheduleFrame.getSize().height/2);
				operatorScheduleFrame.setVisible(true);

				submitButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent unused) {
						//add operator information to scheduler
						operatorScheduleFrame.dispose();
					}
				});
			}

		});

		passengerCountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent unused) {
				final JFrame passengerCountFrame = new JFrame("Passenger Count");
				passengerCountFrame.setLayout(new BorderLayout());
				passengerCountFrame.setSize(580,200);
				JButton dismissButton = new JButton("Dismiss");
				JPanel topPanel = new JPanel(new FlowLayout());
				JPanel bottomPanel = new JPanel(new FlowLayout());
				bottomPanel.add(dismissButton);
				topPanel.add(new JLabel("Train0: #"));
				passengerCountFrame.add(topPanel, BorderLayout.NORTH);
				passengerCountFrame.add(bottomPanel, BorderLayout.SOUTH);

				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				passengerCountFrame.setLocation(dim.width/2-passengerCountFrame.getSize().width/2,
						dim.height/2-passengerCountFrame.getSize().height/2);
				passengerCountFrame.setVisible(true);

				dismissButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent unused) {
						passengerCountFrame.dispose();
					}
				});
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