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

public class MovingBlockOverlayMainGUI {

	private static JFrame mainFrame;
	private static JMenuBar menuBar;
	private static JMenu helpMenu;
	private static JMenuItem manualMenu, aboutMenu, endMenu;
	private static JTable scheduleJTable;
	private static JScrollPane jScrollPane;
	private static JTextField startInput;
	private static JLabel modeLabel, startLabel, dispatchLabel;
	private static JButton mboButton, fixedBlockButton, viewButton, exportButton, 
						   dispatchAtTimeButton, dispatchNowButton, setScheduleButton;
	private static Boolean mboMode, fixedBlockMode, scheduleIsSetUp;
	private static JPanel topPanel, centerPanel, southPanel;
	private static String timeToDispatch;

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch (Exception e) {
			System.out.println("Class Not Found!");
		}

		mboMode = false;
		fixedBlockMode = false;
		scheduleIsSetUp = false;

		mainFrame = new JFrame("Moving Block Overlay Scheduler");
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setSize(1000,700);

		menuBar = new JMenuBar();
		helpMenu = new JMenu("Help");
		manualMenu = new JMenuItem("User's Manual");
		aboutMenu = new JMenuItem("About...");
		endMenu = new JMenuItem("End");
		helpMenu.add(manualMenu);
		helpMenu.add(aboutMenu);
		helpMenu.add(endMenu);
		menuBar.add(helpMenu);

		startInput = new JTextField("00:00", 4);

		modeLabel         = new JLabel("Mode of Operation:");
		startLabel        = new JLabel("Start Schedule at Time:");

		mboButton         = new JButton("MBO");
		fixedBlockButton  = new JButton("Fixed Block");
		viewButton        = new JButton("View Operator Schedule");
		setScheduleButton = new JButton("Set Schedule");
		exportButton      = new JButton("Export Current Schedule to CTC");
		dispatchAtTimeButton       = new JButton("Dispatch Trains at Time");
		dispatchNowButton    = new JButton("Dispatch Trains from the Yard Now");

		viewButton.setEnabled(false);
		exportButton.setEnabled(false);
		dispatchAtTimeButton.setEnabled(false);
		dispatchNowButton.setEnabled(false);
		setScheduleButton.setEnabled(false);

		/***************SET SCHEDULE DEPENDING ON WHAT THE SCHEDULER WANTS****************/
		String columnNames[] = {"Train ID", "Line", "Station", "Total Time to Station w/ dwell (min)", "Mode"};
		String dataValues[][] = { { "", "", "", "", "", "", ""} };
		scheduleJTable = new JTable(dataValues, columnNames);
		jScrollPane = new JScrollPane(scheduleJTable);
		jScrollPane.setPreferredSize(new Dimension(950, 400));

		topPanel      = new JPanel();
		centerPanel   = new JPanel();
		southPanel    = new JPanel();

		mainFrame.add(topPanel, BorderLayout.NORTH);
		mainFrame.add(centerPanel, BorderLayout.CENTER);
		mainFrame.add(southPanel, BorderLayout.SOUTH);

		topPanel.add(modeLabel);
		topPanel.add(mboButton);
		topPanel.add(fixedBlockButton);
		topPanel.add(startLabel);
		topPanel.add(startInput);
		topPanel.add(dispatchAtTimeButton);
		centerPanel.add(setScheduleButton);
		centerPanel.add(dispatchNowButton);
		centerPanel.add(viewButton);
		centerPanel.add(exportButton);
		centerPanel.add(jScrollPane);


		/*******HYPOTHETICAL SITUATION OF A TRAIN FOR SUB-SYSTEM DEMO********/
		JLabel aLabel = new JLabel("Enter the speed of a train (km/h): ");
		aLabel.setFont(new Font(aLabel.getName(), Font.PLAIN, 15));
		southPanel.add(aLabel);
		
		final JTextField someSpeed = new JTextField("40.0", 4);
		southPanel.add(someSpeed);

		JLabel bLabel = new JLabel("Enter the weight of a train (tons): ");
		bLabel.setFont(new Font(bLabel.getName(), Font.PLAIN, 15));
		southPanel.add(bLabel);

		final JTextField someWeight = new JTextField("40.9", 4);
		southPanel.add(someWeight);

		JButton someSafeBreakingDistanceButton = new JButton("Calculate Safe Breaking Distance");
		southPanel.add(someSafeBreakingDistanceButton);

		/************CENTER THE JFRAME IN THE MIDDLE OF THE SCREEN***********/
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		mainFrame.setLocation(dim.width/2-mainFrame.getSize().width/2, dim.height/2-mainFrame.getSize().height/2);
		mainFrame.setJMenuBar(menuBar);
		mainFrame.setVisible(true);


		/**********GO BACK TO STARTUP STATE WHEN THE DAY IS OVER**************/
		/*************TEMPRARILY SET MANUALLY FROM HELP MENU******************/


		/**************************************************************************************************************************
		* Window Listeners: listener interface for receiving window events.
		*
		*/
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		mboButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent unused) {
				mboMode = true;
				fixedBlockMode = false;
				mboButton.setBackground(Color.GREEN);
				mboButton.setEnabled(false);
				fixedBlockButton.setBackground(Color.WHITE);
				fixedBlockButton.setEnabled(false);
				setScheduleButton.setEnabled(true);
			}
		});

		fixedBlockButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent unused) {
				mboMode = false;
				fixedBlockMode = true;
				fixedBlockButton.setBackground(Color.GREEN);
				fixedBlockButton.setEnabled(false);
				mboButton.setBackground(Color.WHITE);
				mboButton.setEnabled(false);
				setScheduleButton.setEnabled(true);
			}
		});

		setScheduleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent unused) {

				final JFrame setScheduleFrame = new JFrame("Set Schedule");
				setScheduleFrame.setSize(400, 110);

				JButton setDefaultScheduleButton = new JButton("Set Default Schedule");
				JButton setNewScheduleButton = new JButton("Set New Schedule");
				JLabel selectionLabel = new JLabel("Would you like to use the default schedule or input a new schedule?");
				JPanel labelPanel = new JPanel(new FlowLayout());
				JPanel buttonPanel = new JPanel(new FlowLayout());

				labelPanel.add(selectionLabel);
				buttonPanel.add(setDefaultScheduleButton);
				buttonPanel.add(setNewScheduleButton);

				setScheduleFrame.add(labelPanel, BorderLayout.NORTH);
				setScheduleFrame.add(buttonPanel, BorderLayout.SOUTH);

				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				setScheduleFrame.setLocation(dim.width/2-setScheduleFrame.getSize().width/2, dim.height/2-setScheduleFrame.getSize().height/2);

				setScheduleFrame.setVisible(true);

				setDefaultScheduleButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent unused) {
						viewButton.setEnabled(true);
						exportButton.setEnabled(true);
						dispatchNowButton.setEnabled(true);
						dispatchAtTimeButton.setEnabled(true);
						centerPanel.remove(jScrollPane);

						DefaultTableModel model = new DefaultTableModel(); 
						scheduleJTable = new JTable(model);
						model.addColumn("Train ID"); 
						model.addColumn("Line");
						model.addColumn("Station");
						model.addColumn("Total Time to Station w/ dwell (min)");
						model.addColumn("Mode");

						BufferedReader br = null;
						String line = "";

						try{
							br = new BufferedReader(new FileReader("Default_Schedule.csv"));
							while ((line = br.readLine()) != null) {
								if (fixedBlockMode) {line += ",Fixed Block";} else {line += ",MBO";}
								String[] scheduleRow = line.split(",");
								model.addRow(scheduleRow);
							}
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						} finally {
							if (br != null) {
								try {
									br.close();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}

						jScrollPane = new JScrollPane(scheduleJTable);
						jScrollPane.setPreferredSize(new Dimension(950, 400));
						centerPanel.add(jScrollPane);
						setScheduleButton.setEnabled(false);
						setScheduleFrame.setVisible(false);
 						mainFrame.setVisible(true);
					}
				});

				setNewScheduleButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent unused) {
						viewButton.setEnabled(true);
						exportButton.setEnabled(true);
						dispatchNowButton.setEnabled(true);
						dispatchAtTimeButton.setEnabled(true);
						centerPanel.remove(jScrollPane);

						DefaultTableModel model = new DefaultTableModel(); 
						scheduleJTable = new JTable(model);
						model.addColumn("Train ID"); 
						model.addColumn("Line");
						model.addColumn("Station");
						model.addColumn("Total Time to Station w/ dwell (min)");
						model.addColumn("Mode");

						JFileChooser fileChooser = new JFileChooser();
						fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
						int result = fileChooser.showOpenDialog(mainFrame);
						File selectedFile = fileChooser.getSelectedFile();


						BufferedReader br = null;
						String line = "";

						try{
							br = new BufferedReader(new FileReader(selectedFile));
							while ((line = br.readLine()) != null) {
								String[] scheduleRow = line.split(",");
								model.addRow(scheduleRow);
							}
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						} finally {
							if (br != null) {
								try {
									br.close();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}

						jScrollPane = new JScrollPane(scheduleJTable);
						jScrollPane.setPreferredSize(new Dimension(950, 400));
						centerPanel.add(jScrollPane);
						setScheduleButton.setEnabled(false);
						setScheduleFrame.setVisible(false);
						mainFrame.setVisible(true);
					}
				});

				scheduleIsSetUp = true;
			}
		});

		dispatchNowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent unused) {
				JOptionPane.showMessageDialog(mainFrame, "Trains have been dispatched from the yard...");
				dispatchNowButton.setEnabled(false);
				dispatchAtTimeButton.setEnabled(false);
			}
		});

		dispatchAtTimeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent unused) {
				JOptionPane.showMessageDialog(mainFrame, "Trains will be dispatched from the yard at " + startInput.getText() + "...");
				dispatchNowButton.setEnabled(false);
				dispatchAtTimeButton.setEnabled(false);
				dispatchLabel = new JLabel("Trains will be dispatched from the yard at " + startInput.getText() + "...");
				dispatchLabel.setFont(new Font(dispatchLabel.getName(), Font.PLAIN, 20));
				centerPanel.add(dispatchLabel);
				mainFrame.setVisible(true);
			}
		});

		exportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent unused) {
				JOptionPane.showMessageDialog(mainFrame, "Current schedule has been exported to the CTC...");
			}
		});

		viewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent unused) {
				JFrame operatorScheduleFrame = new JFrame("Transit Operator Schedule");
				operatorScheduleFrame.setSize(750,200);

				String columnNames[] = { "conductor", "Train ID", "Station (Shift)", "Time in (Shift)",
										 "Time out (Shift)", "Station (Break)", "Time in (Break)", "Time out (Break)"};

				String dataValues[][] = { { "name", "1", "station", "00:00", "00:00", "station", "00:00", "00:00"} };

				JTable scheduleJTable = new JTable(dataValues, columnNames);
				JScrollPane jScrollPane = new JScrollPane(scheduleJTable);
				operatorScheduleFrame.add(jScrollPane);

				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				operatorScheduleFrame.setLocation(dim.width/2-operatorScheduleFrame.getSize().width/2, dim.height/2-operatorScheduleFrame.getSize().height/2);

				operatorScheduleFrame.setVisible(true);
			}
		});

		someSafeBreakingDistanceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent unused) {
				/******CALCULATE THE SAFE BRAKING DISTANCE BASED ON SPEED AND WEIGHT ONLY******/
				/*formula: D = v^(2) / [2(mu)g]************************************************/
				double v  = Double.parseDouble(someSpeed.getText()) / 3.6; //convert to m/s
				double g  = 9.81; //in m/s^2
				double mu = 0.8; //coefficient of friction of steel rail
				double safeBreakingDistance = (Double.parseDouble(someWeight.getText())*v*v) / (2*mu*g);
				JOptionPane.showMessageDialog(mainFrame, "The safe braking distance is " + Math.round(safeBreakingDistance) +" meters");
			}
		});

		endMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent unused) {
					mboMode = false;
					fixedBlockMode = false;
					mboButton.setEnabled(true);
					fixedBlockButton.setEnabled(true);
					viewButton.setEnabled(false);
					exportButton.setEnabled(false);
					dispatchAtTimeButton.setEnabled(false);
					dispatchNowButton.setEnabled(false);
					setScheduleButton.setEnabled(false);

					centerPanel.remove(jScrollPane);
					String columnNames[] = {"Train ID", "Line", "Station", "Total Time to Station w/ dwell (min)", "Mode"};
					String dataValues[][] = { { "", "", "", "", "", "", ""} };
					scheduleJTable = new JTable(dataValues, columnNames);
					jScrollPane = new JScrollPane(scheduleJTable);
					jScrollPane.setPreferredSize(new Dimension(950, 400));
					centerPanel.add(jScrollPane);
					mainFrame.setVisible(true);
					centerPanel.remove(dispatchLabel);
					mainFrame.setVisible(true);
					
			}
		});

		manualMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent unused) {
				JOptionPane.showMessageDialog(mainFrame, "                                                 "+
						"Sub-System Prototype v.01 \n\n"+
						"1. MBO Button: When the MBO button is pressed, the transit system will operate in MBO\n"+
						"   mode and the schedule will produce a daily schedule that can be changed each day. The color\n"+
						"   of the MBO button will change to indicate that this button is pressed and the transit system is\n"+
						"   currently running in MBO mode.\n\n"+
						"2.	Fixed Block Button: When the Fixed Block mode button is pressed, the transit system will operate\n"+
						"   in Fixed Block mode and the same schedule is used for each day. The color of the MBO button will change\n"+
						"   to indicate that this button is pressed and the transit system is currently running in Fixed Block mode.\n\n"+
						"3.	Export Schedule to CTC: When this button is pressed the schedule is exported to the CTC Office.\n"+
						"   A Success/Failure message will pop up indicating if the CTC received the schedule successfully.\n\n"+
						"4.	View Operator Schedule: When this button is pressed a new window will open that contains information\n"+
						"   about Transit Operator Schedule accounting for breaks and length of shift. For each conductor the following\n"+
						"   information is shown: the train id, the train\'s location, and start and end time for each of their shifts.\n\n"+
						"5.	Start: When this button is pressed the schedule will start at the time inputted in the text field by the \n"+
						"    user. The MBO schedule accepts input for throughput in 1 hour intervals.\n\n"+
						"6.	Dispatch Trains from the Yard: When this button is pressed trains will be dispatched from the yard and will\n"+
						"   follow the given schedule under MBO mode or Fixed Block Mode.");
			}
		});

		aboutMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent unused) {
				JOptionPane.showMessageDialog(mainFrame, "                                                 "+
						"Sub-System Prototype v.01 \n\n"+
					    "This is a user interface for the MBO module and is controlled by the scheduler.\n"+
						"The Moving Block Overlay is a vital centralized controller that calculates the safe\n"+
						"stopping distance of each train in real time. This distance is used to determine the\n"+
						"safe authority of each train. The MBO also contains a scheduler. This user interface \n"+
						"shows the train schedule as well as the operator schedule. This user interface also \n "+
						"allows the user to simulate certain situations and gain access to display features. \n"+
						"It also controls the mode of operation between MBO and Fixed Block. In Fixed Block mode \n"+
						"the same schedule is used for each day; whereas, in MBO mode the scheduler will produce a \n"+
						"daily schedule that can be changed each day.\n\n"+
						"In this sub-system prototype only two use cases of the MBO module are supported:\n"+
						"a. Generate Train Schedule.\n"+
						"b. Calculate Safe Breaking Distance given train Position, Velocity, and Mass.");
			}
		});
	}
}