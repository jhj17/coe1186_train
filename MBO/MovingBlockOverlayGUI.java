/**
* MovingBlockOverlayMainGUI.java
*
* @author John Abraham
*
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import java.lang.*;
import java.util.*;

public class MovingBlockOverlayGUI {

	private static JFrame mainFrame;
	private static JTable scheduleJTable, operatorScheduleJTable;
	private static JScrollPane jScrollPane, oPjScrollPane;
	private static JSpinner startInput;
	private static MBOScheduler mboScheduler = new MBOScheduler();
	private static JPanel topPanel, centerPanel, southPanel;

	public static void main(String[] args) {

		JLabel startLabel, modeOfOperationLabel, fixedBlockModeLabel, mboModeLabel;
		JButton setThroughputButton, passengerCountButton, gernerateScheduleButton;
		String timeToDispatch;

		mainFrame = new JFrame("Moving Block Overlay Scheduler GUI");
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setSize(1000,710);

		startInput = new JSpinner();
		startInput.setModel(new SpinnerDateModel());
		startInput.setEditor(new JSpinner.DateEditor(startInput, "hh:mm a"));
		mboScheduler.setScheduleStartTime( startInput.getValue().toString() );

		startLabel = new JLabel("Start Schedule at Time:");
		modeOfOperationLabel = new JLabel("Mode of Operation: ");
		fixedBlockModeLabel = new JLabel("Fixed Block");
		fixedBlockModeLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		fixedBlockModeLabel.setOpaque(true);
		mboModeLabel = new JLabel("MBO");
		mboModeLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		mboModeLabel.setOpaque(true);

		setThroughputButton = new JButton("Set Throughput");
		passengerCountButton = new JButton("Passenger Count");
		gernerateScheduleButton = new JButton("Generate Operator and Train Schedules");

		setThroughputButton.setEnabled(true);
		passengerCountButton.setEnabled(true);
		gernerateScheduleButton.setEnabled(true);

		String columnNames[]  = {"Train ID", "Line", "Station", "Total Time to Station(min)", "Arrival Time"};
		String dataValues[][] = { { "", "", "", "", ""}, { "", "", "", "", ""}, { "", "", "", "", ""}, { "", "", "", "", ""},
								  { "", "", "", "", ""}, { "", "", "", "", ""}, { "", "", "", "", ""}, { "", "", "", "", ""},
								  { "", "", "", "", ""}, { "", "", "", "", ""}, { "", "", "", "", ""}, { "", "", "", "", ""},
								  { "", "", "", "", ""}, { "", "", "", "", ""}, { "", "", "", "", ""}, { "", "", "", "", ""},
								  { "", "", "", "", ""}, { "", "", "", "", ""}, { "", "", "", "", ""}, { "", "", "", "", ""},
								  { "", "", "", "", ""}, { "", "", "", "", ""}, { "", "", "", "", ""} };

		scheduleJTable = new JTable(dataValues, columnNames);
		scheduleJTable.setEnabled(false);
		jScrollPane = new JScrollPane(scheduleJTable);
		jScrollPane.setPreferredSize(new Dimension(950, 391));

		String opColumnNames[] = { "Operator Name", "Train ID", "Shift Start", "Break Start", "Break End", "Shift End"};
		String opDataValues[][] = { { "", "", "", "", "", ""}, { "", "", "", "", "", ""}, { "", "", "", "", "", ""},
									{ "", "", "", "", "", ""}, { "", "", "", "", "", ""}, { "", "", "", "", "", ""} };

		operatorScheduleJTable = new JTable(opDataValues, opColumnNames);
		operatorScheduleJTable.setEnabled(false);
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
		northButtonsPanelBottom.add(setThroughputButton);
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
		topPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		centerPanel.add(jScrollPane, BorderLayout.CENTER);
		southPanel.add(oPjScrollPane, BorderLayout.SOUTH);

		/************CENTER THE JFRAME IN THE MIDDLE OF THE SCREEN***********/
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		mainFrame.setLocation(dim.width/2-mainFrame.getSize().width/2, dim.height/2-mainFrame.getSize().height/2);
		mainFrame.add(topPanel, BorderLayout.NORTH);
		mainFrame.add(centerPanel, BorderLayout.CENTER);
		mainFrame.add(southPanel, BorderLayout.SOUTH);
		mainFrame.setVisible(true);

		/********************************************************************************************************
		/********************************************************************************************************
		* Window Listeners: listener interface for receiving window events.
		*/
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});


		setThroughputButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent unused) {
				final JFrame setThroughputFrame = new JFrame("Set Time Table");
				setThroughputFrame.setLayout(new FlowLayout());
				setThroughputFrame.setSize(580,200);

				JButton submitButton = new JButton("Submit");

				String columnNames[] = { "Station", "Throughput (min)"};
				String dataValues[][] = { { "", "" }, { "", "" }, { "", "" },
										  { "", "" }, { "", "" }, { "", "" },
										  { "", "" }, { "", "" }, { "", "" },
										  { "", "" }, { "", "" }, { "", "" },
										  { "", "" }, { "", "" }, { "", "" },
										  { "", "" }, { "", "" }, { "", "" },
										  { "", "" }, { "", "" }, { "", "" },
										  { "", "" }, { "", "" }, { "", "" } };

				final JTable timeTable = new JTable(dataValues, columnNames);
				JScrollPane jScrollPane = new JScrollPane(timeTable);
				jScrollPane.setPreferredSize(new Dimension(550, 119));
				setThroughputFrame.add(jScrollPane);
				setThroughputFrame.add(submitButton);

				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				setThroughputFrame.setLocation(dim.width/2-setThroughputFrame.getSize().width/2,
					dim.height/2-setThroughputFrame.getSize().height/2);
				setThroughputFrame.setVisible(true);

				submitButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent unused) {
						//add time table information to mbo scheduler
						if(timeTable.getCellEditor() != null)
							timeTable.getCellEditor().stopCellEditing();
						mboScheduler.setThroughputInfo(jTableToArrayList(timeTable));
						setThroughputFrame.dispose();
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

		startInput.addChangeListener(new ChangeListener() {
			Object lastValue;

			@Override
			public void stateChanged(ChangeEvent evt) {
				if (lastValue != null && !startInput.getValue().equals(lastValue)) {
					mboScheduler.setScheduleStartTime( startInput.getValue().toString() );
				}
				lastValue = startInput.getValue();
			}
		});

		gernerateScheduleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent unused) {
				/*****Generate train schedule*****/
				String columnNames[]  = {"Train ID", "Line", "Station", "Total Time to Station (min)", "Arrival Time"};
				String dataValues[][] = arrayListTo2DArray(mboScheduler.getTrainSchedule(), 5);
				centerPanel.remove(jScrollPane);
				scheduleJTable = new JTable(dataValues, columnNames);
				scheduleJTable.setEnabled(false);
				jScrollPane = new JScrollPane(scheduleJTable);
				jScrollPane.setPreferredSize(new Dimension(950, 391));
				centerPanel.add(jScrollPane, BorderLayout.CENTER);

				/*****Generate operator schedule*****/
				String opColumnNames[] = { "Operator Name", "Train ID", "Shift Start", "Break Start", "Break End", "Shift End"};
				String opDataValues[][] = arrayListTo2DArray(mboScheduler.getOperatorSchedule(), 6);
				southPanel.remove(oPjScrollPane);
				operatorScheduleJTable = new JTable(opDataValues, opColumnNames);
				operatorScheduleJTable.setEnabled(false);
				oPjScrollPane = new JScrollPane(operatorScheduleJTable);
				oPjScrollPane.setPreferredSize(new Dimension(950, 119));
				southPanel = new JPanel(new FlowLayout());
				southPanel.add(oPjScrollPane, BorderLayout.SOUTH);

				mainFrame.add(centerPanel, BorderLayout.CENTER);
				mainFrame.add(southPanel, BorderLayout.SOUTH);

				mainFrame.setVisible(true);
			}
		});
	}

	private static ArrayList<String> jTableToArrayList(JTable table) {
		ArrayList<String> list = new ArrayList<String>();
		for(int row = 0; row < table.getRowCount(); row++) {
		    for(int column = 0; column < table.getColumnCount(); column++) {
				if(table.getValueAt(row, column).toString() != "")
					list.add(table.getValueAt(row, column).toString());
		    }
		}
		return list;
	}

	private static String[][] arrayListTo2DArray(ArrayList<String> table, int numColumns) {
		String[][] array = new String[table.size()/numColumns][numColumns];
		int j = 0;
		for(int row=0; row<(table.size()/numColumns); row++) {
			for(int col=0; col<numColumns; col++) {
				array[row][col] = table.get(j);
				j++;
			}
		}

		return array;
	}
}
