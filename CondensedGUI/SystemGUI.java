import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSlider;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.border.BevelBorder;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextArea;
import java.awt.Component;
import java.awt.Dimension;


public class SystemGUI {

	private JFrame frmTheLittleEngine;
	private JTextField plcProgramPathTextbox;
	private JTextField txtID;
	private JTextField txtLength;
	private JTextField txtWidth;
	private JTextField txtHeight;
	private JTextField txtMass;
	private JTextField txtCars;
	private JTextField txtCrewCount;
	private JTextField txtPassengerCount;
	private JTextField txtRoute;
	private JTextField txtDistance;
	private JTextField txtGrade;
	private JTextField txtAuthority;
	private JTextField txtPower;
	private JTextField txtSpeedLimit;
	private JTextField txtTempIn;
	private JTextField txtSpeed;
	private JTextField txtAcceleration;
	private JTextField txtTemp;
	private JTextField txtDoors;
	private JTextField txtLights;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField trainID;
	private JTable table;
	private JTextField textTime;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField startInput;
	private JTable scheduleJTable;
	private JTable operatorScheduleJTable;

	/**
	 * Create the application.
	 */
	public SystemGUI() {
		initialize();
		frmTheLittleEngine.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTheLittleEngine = new JFrame();
		frmTheLittleEngine.setTitle("The Little Engine That Code");
		frmTheLittleEngine.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Jeff\\Documents\\College\\Senior Year\\COE 1186\\System\\train_pic.png"));
		frmTheLittleEngine.setBounds(100, 100, 878, 561);
		frmTheLittleEngine.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTheLittleEngine.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabbedPane.setBounds(10, 11, 842, 501);
		frmTheLittleEngine.getContentPane().add(tabbedPane);
		
		JPanel ctcTab = new JPanel();
		tabbedPane.addTab("CTC", null, ctcTab, null);
		ctcTab.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(0, 0, 743, 494);
		ctcTab.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Schedule");
		lblNewLabel_1.setBounds(120, 11, 69, 14);
		panel_4.add(lblNewLabel_1);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Fixed Block");
		rdbtnNewRadioButton.setBounds(6, 32, 98, 23);
		panel_4.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnMbo = new JRadioButton("MBO");
		rdbtnMbo.setBounds(104, 32, 52, 23);
		panel_4.add(rdbtnMbo);
		
		JButton btnRequestSchedule = new JButton("Request Schedule");
		btnRequestSchedule.setBounds(175, 32, 139, 23);
		panel_4.add(btnRequestSchedule);
		
		JLabel lblNewLabel_2 = new JLabel("Train");
		lblNewLabel_2.setBounds(49, 62, 46, 14);
		panel_4.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Line");
		lblNewLabel_3.setBounds(160, 62, 46, 14);
		panel_4.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Stations");
		lblNewLabel_4.setBounds(254, 62, 60, 14);
		panel_4.add(lblNewLabel_4);
		
		JButton btnLoadStations = new JButton("Load Stations");
		btnLoadStations.setBounds(6, 106, 116, 23);
		panel_4.add(btnLoadStations);
		
		JButton btnSchedule = new JButton("Schedule");
		btnSchedule.setBounds(130, 106, 105, 23);
		panel_4.add(btnSchedule);
		
		JButton btnRoute = new JButton("Route");
		btnRoute.setBounds(245, 106, 69, 23);
		panel_4.add(btnRoute);
		
		trainID = new JTextField();
		trainID.setBounds(33, 76, 60, 23);
		panel_4.add(trainID);
		trainID.setColumns(10);
		
		JComboBox comboLine = new JComboBox();
		comboLine.setBounds(130, 76, 89, 23);
		panel_4.add(comboLine);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(6, 140, 308, 325);
		panel_4.add(scrollPane_3);
		
		table = new JTable();
		scrollPane_3.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Train", "Line", "Destination"
			}
		));
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JLabel lblNewLabel_5 = new JLabel("Clock:");
		lblNewLabel_5.setBounds(390, 11, 46, 14);
		panel_4.add(lblNewLabel_5);
		
		textTime = new JTextField();
		textTime.setBounds(431, 8, 151, 20);
		panel_4.add(textTime);
		textTime.setColumns(10);
		
		JButton btnGetTime = new JButton("Get Time");
		btnGetTime.setBounds(592, 7, 89, 23);
		panel_4.add(btnGetTime);
		
		JLabel lblMessagesToTrack = new JLabel("Messages To Track Controller");
		lblMessagesToTrack.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMessagesToTrack.setBounds(453, 61, 228, 14);
		panel_4.add(lblMessagesToTrack);
		
		JLabel lblRouting = new JLabel("Routing");
		lblRouting.setBounds(515, 80, 60, 14);
		panel_4.add(lblRouting);
		
		JLabel lblOpencloseTrack = new JLabel("Open/Close Track");
		lblOpencloseTrack.setBounds(380, 283, 139, 14);
		panel_4.add(lblOpencloseTrack);
		
		JLabel lblBlockStatus = new JLabel("Block Status");
		lblBlockStatus.setBounds(600, 283, 116, 14);
		panel_4.add(lblBlockStatus);
		
		JButton btnShowBeacon = new JButton("Show Beacon");
		btnShowBeacon.setBounds(380, 245, 116, 23);
		panel_4.add(btnShowBeacon);
		
		JButton btnDispatch = new JButton("Dispatch");
		btnDispatch.setBounds(590, 245, 98, 23);
		panel_4.add(btnDispatch);
		
		JLabel lblBlock = new JLabel("Block");
		lblBlock.setBounds(336, 308, 52, 14);
		panel_4.add(lblBlock);
		
		JLabel lblLine = new JLabel("Line");
		lblLine.setBounds(431, 308, 46, 14);
		panel_4.add(lblLine);
		
		JLabel label_42 = new JLabel("Block");
		label_42.setBounds(530, 308, 52, 14);
		panel_4.add(label_42);
		
		JLabel label_56 = new JLabel("Line");
		label_56.setBounds(627, 308, 46, 14);
		panel_4.add(label_56);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(375, 304, 46, 23);
		panel_4.add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(459, 304, 60, 23);
		panel_4.add(textField_9);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(572, 304, 45, 23);
		panel_4.add(textField_10);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(658, 304, 60, 23);
		panel_4.add(textField_11);
		
		JButton btnOpen = new JButton("Open");
		btnOpen.setBounds(336, 442, 76, 23);
		panel_4.add(btnOpen);
		
		JButton btnClose = new JButton("Close");
		btnClose.setBounds(443, 442, 76, 23);
		panel_4.add(btnClose);
		
		JButton btnGetStatus = new JButton("Get Status");
		btnGetStatus.setBounds(572, 442, 105, 23);
		panel_4.add(btnGetStatus);
		
		JTextArea txtrOpencloseMessages = new JTextArea();
		txtrOpencloseMessages.setText("Open/Close Messages:");
		txtrOpencloseMessages.setBounds(336, 333, 183, 98);
		txtrOpencloseMessages.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.add(txtrOpencloseMessages);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(533, 333, 183, 98);
		textArea_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.add(textArea_1);
		
		JTextArea txtrProceedMessages = new JTextArea();
		txtrProceedMessages.setText("Proceed Messages:");
		txtrProceedMessages.setBounds(380, 106, 308, 134);
		txtrProceedMessages.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.add(txtrProceedMessages);
		
		JPanel trackControllerTab = new JPanel();
		tabbedPane.addTab("Track Controller", null, trackControllerTab, null);
		trackControllerTab.setLayout(null);
		
		JPanel trackControllerPanel = new JPanel();
		trackControllerPanel.setBorder(null);
		trackControllerPanel.setBounds(10, 40, 711, 443);
		trackControllerTab.add(trackControllerPanel);
		trackControllerPanel.setLayout(null);
		
		JLabel label_2 = new JLabel("Track Controller ID");
		label_2.setBounds(240, 11, 129, 14);
		trackControllerPanel.add(label_2);
		
		JComboBox<Integer> tcComboBox = new JComboBox<Integer>();
		tcComboBox.setBounds(349, 8, 81, 20);
		trackControllerPanel.add(tcComboBox);
		
		JLabel label_3 = new JLabel("PLC Program");
		label_3.setBounds(10, 55, 109, 14);
		trackControllerPanel.add(label_3);
		
		plcProgramPathTextbox = new JTextField();
		plcProgramPathTextbox.setText("C:/");
		plcProgramPathTextbox.setEditable(false);
		plcProgramPathTextbox.setColumns(10);
		plcProgramPathTextbox.setBounds(93, 52, 375, 20);
		trackControllerPanel.add(plcProgramPathTextbox);
		
		JButton button = new JButton("Browse");
		button.setBounds(478, 51, 89, 23);
		trackControllerPanel.add(button);
		
		JButton button_1 = new JButton("Load");
		button_1.setEnabled(false);
		button_1.setBounds(590, 51, 89, 23);
		trackControllerPanel.add(button_1);
		
		JLabel label_4 = new JLabel("Blocks");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_4.setBounds(10, 102, 46, 14);
		trackControllerPanel.add(label_4);
		
		JLabel label_5 = new JLabel("Section      Number       Occupied      Broken      Maintanence   ");
		label_5.setBounds(10, 127, 340, 14);
		trackControllerPanel.add(label_5);
		
		JLabel label_6 = new JLabel("Railways");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_6.setBounds(397, 102, 146, 14);
		trackControllerPanel.add(label_6);
		
		JLabel label_7 = new JLabel("Block              Status");
		label_7.setBounds(397, 127, 170, 14);
		trackControllerPanel.add(label_7);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 152, 359, 242);
		trackControllerPanel.add(scrollPane);
		
		JList<String> blocksListBox = new JList<String>();
		scrollPane.setViewportView(blocksListBox);
		blocksListBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		blocksListBox.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JButton breakBlockButton = new JButton("Break");
		breakBlockButton.setEnabled(false);
		breakBlockButton.setBounds(84, 405, 89, 27);
		trackControllerPanel.add(breakBlockButton);
		
		JButton maintBlockButton = new JButton("Maintanence");
		maintBlockButton.setEnabled(false);
		maintBlockButton.setBounds(183, 405, 109, 27);
		trackControllerPanel.add(maintBlockButton);
		
		JLabel label_8 = new JLabel("Switches");
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_8.setBounds(397, 266, 146, 14);
		trackControllerPanel.add(label_8);
		
		JLabel label_9 = new JLabel("Block      Switch    Status");
		label_9.setBounds(397, 291, 160, 14);
		trackControllerPanel.add(label_9);
		
		JButton button_2 = new JButton("Activate Crossing");
		button_2.setEnabled(false);
		button_2.setBounds(456, 233, 160, 27);
		trackControllerPanel.add(button_2);
		
		JButton button_3 = new JButton("Switch Track");
		button_3.setEnabled(false);
		button_3.setBounds(489, 405, 109, 27);
		trackControllerPanel.add(button_3);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(396, 152, 306, 70);
		trackControllerPanel.add(scrollPane_1);
		
		JList<String> railwayListbox = new JList<String>();
		railwayListbox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		railwayListbox.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane_1.setViewportView(railwayListbox);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(396, 316, 306, 78);
		trackControllerPanel.add(scrollPane_2);
		
		JList<String> switchListbox = new JList<String>();
		switchListbox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		switchListbox.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane_2.setViewportView(switchListbox);
		
		JLabel label = new JLabel("Red Line");
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(252, 11, 101, 23);
		trackControllerTab.add(label);
		
		JSlider changeViewSlider = new JSlider();
		changeViewSlider.setValue(1);
		changeViewSlider.setMaximum(1);
		changeViewSlider.setBounds(325, 11, 28, 23);
		trackControllerTab.add(changeViewSlider);
		
		JLabel label_1 = new JLabel("Green Line");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_1.setBounds(363, 11, 134, 23);
		trackControllerTab.add(label_1);
		
		JPanel TrainModelTab = new JPanel();
		tabbedPane.addTab("Track Model", null, TrainModelTab, null);
		
		JPanel trainControllerTab = new JPanel();
		tabbedPane.addTab("Train Controller", null, trainControllerTab, null);
		trainControllerTab.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(10, 48, 723, 341);
		trainControllerTab.add(panel_1);
		
		JSlider slider = new JSlider();
		slider.setSnapToTicks(true);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setOrientation(SwingConstants.VERTICAL);
		slider.setMinorTickSpacing(1);
		slider.setMaximum(70);
		slider.setMajorTickSpacing(10);
		slider.setBounds(0, 108, 81, 211);
		panel_1.add(slider);
		
		JToggleButton toggleButton = new JToggleButton("Toggle AutoPilot");
		toggleButton.setForeground(Color.BLUE);
		toggleButton.setBounds(201, 29, 290, 29);
		panel_1.add(toggleButton);
		
		JLabel label_36 = new JLabel("Desired");
		label_36.setHorizontalAlignment(SwingConstants.CENTER);
		label_36.setFont(new Font("Modern No. 20", Font.BOLD, 20));
		label_36.setBounds(0, 54, 81, 29);
		panel_1.add(label_36);
		
		JLabel label_37 = new JLabel("Current Speed");
		label_37.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		label_37.setBounds(245, 114, 88, 16);
		panel_1.add(label_37);
		
		JLabel label_38 = new JLabel("Current Speed Limit");
		label_38.setHorizontalAlignment(SwingConstants.RIGHT);
		label_38.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		label_38.setBounds(192, 189, 142, 16);
		panel_1.add(label_38);
		
		JLabel label_39 = new JLabel("Current Authority");
		label_39.setHorizontalAlignment(SwingConstants.RIGHT);
		label_39.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		label_39.setBounds(202, 224, 133, 16);
		panel_1.add(label_39);
		
		JLabel label_40 = new JLabel("ERRORS");
		label_40.setHorizontalAlignment(SwingConstants.RIGHT);
		label_40.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		label_40.setBounds(201, 303, 132, 16);
		panel_1.add(label_40);
		
		JLabel label_41 = new JLabel("Beacon Message");
		label_41.setHorizontalAlignment(SwingConstants.RIGHT);
		label_41.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		label_41.setBounds(192, 259, 132, 16);
		panel_1.add(label_41);
		
		JLabel label_43 = new JLabel("NEXT STATION");
		label_43.setHorizontalAlignment(SwingConstants.CENTER);
		label_43.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		label_43.setBounds(525, 13, 198, 16);
		panel_1.add(label_43);
		
		JToggleButton toggle_service = new JToggleButton("SERVICE BRAKE");
		toggle_service.setForeground(Color.GREEN);
		toggle_service.setBackground(Color.BLACK);
		toggle_service.setBounds(541, 77, 162, 80);
		panel_1.add(toggle_service);
		
		JLabel label_44 = new JLabel("16");
		label_44.setBackground(Color.BLACK);
		label_44.setBounds(584, -14, 25, 16);
		panel_1.add(label_44);
		
		JToggleButton toggle_emergency = new JToggleButton("EMERGENCY BRAKE");
		toggle_emergency.setForeground(Color.RED);
		toggle_emergency.setBackground(Color.BLACK);
		toggle_emergency.setBounds(541, 166, 162, 80);
		panel_1.add(toggle_emergency);
		
		JSlider slider_1 = new JSlider();
		slider_1.setSnapToTicks(true);
		slider_1.setPaintTicks(true);
		slider_1.setPaintLabels(true);
		slider_1.setOrientation(SwingConstants.VERTICAL);
		slider_1.setMinorTickSpacing(1);
		slider_1.setMaximum(70);
		slider_1.setMajorTickSpacing(10);
		slider_1.setEnabled(false);
		slider_1.setBounds(91, 108, 81, 211);
		panel_1.add(slider_1);
		
		JLabel label_45 = new JLabel("Speed");
		label_45.setHorizontalAlignment(SwingConstants.CENTER);
		label_45.setFont(new Font("Modern No. 20", Font.BOLD, 20));
		label_45.setBounds(91, 77, 71, 35);
		panel_1.add(label_45);
		
		JLabel label_46 = new JLabel("Commanded Power");
		label_46.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		label_46.setBounds(212, 81, 121, 16);
		panel_1.add(label_46);
		
		JLabel label_47 = new JLabel("Speed");
		label_47.setHorizontalAlignment(SwingConstants.CENTER);
		label_47.setFont(new Font("Modern No. 20", Font.BOLD, 20));
		label_47.setBounds(0, 77, 81, 29);
		panel_1.add(label_47);
		
		JLabel label_48 = new JLabel("Acutal");
		label_48.setHorizontalAlignment(SwingConstants.CENTER);
		label_48.setFont(new Font("Modern No. 20", Font.BOLD, 20));
		label_48.setBounds(93, 54, 65, 29);
		panel_1.add(label_48);
		
		JLabel label_49 = new JLabel("Desired Speed");
		label_49.setHorizontalAlignment(SwingConstants.RIGHT);
		label_49.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		label_49.setBounds(191, 153, 142, 16);
		panel_1.add(label_49);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(346, 71, 185, 28);
		panel_1.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(346, 107, 185, 28);
		panel_1.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(346, 146, 185, 28);
		panel_1.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(346, 182, 185, 28);
		panel_1.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(346, 218, 185, 28);
		panel_1.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBounds(346, 252, 185, 28);
		panel_1.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBounds(344, 291, 187, 28);
		panel_1.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setEditable(false);
		textField_7.setColumns(10);
		textField_7.setBounds(542, 33, 162, 28);
		panel_1.add(textField_7);
		
		JLabel label_50 = new JLabel("Manual Mode in ON");
		label_50.setHorizontalAlignment(SwingConstants.CENTER);
		label_50.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		label_50.setBounds(265, 11, 162, 16);
		panel_1.add(label_50);
		
		JLabel label_51 = new JLabel("");
		label_51.setHorizontalAlignment(SwingConstants.CENTER);
		label_51.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		label_51.setBounds(524, 317, 247, 16);
		panel_1.add(label_51);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(10, 400, 723, 83);
		trainControllerTab.add(panel_2);
		
		JLabel label_35 = new JLabel("Arriving At Station");
		label_35.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		label_35.setBounds(18, 11, 129, 16);
		panel_2.add(label_35);
		
		JLabel label_52 = new JLabel("Lights On");
		label_52.setHorizontalAlignment(SwingConstants.CENTER);
		label_52.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		label_52.setBounds(127, 11, 129, 16);
		panel_2.add(label_52);
		
		JLabel label_53 = new JLabel("Doors Open");
		label_53.setHorizontalAlignment(SwingConstants.CENTER);
		label_53.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		label_53.setBounds(243, 11, 107, 16);
		panel_2.add(label_53);
		
		JLabel label_54 = new JLabel("Announcement in Progress");
		label_54.setHorizontalAlignment(SwingConstants.CENTER);
		label_54.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		label_54.setBounds(358, 11, 180, 16);
		panel_2.add(label_54);
		
		JLabel label_55 = new JLabel("Thermostat");
		label_55.setHorizontalAlignment(SwingConstants.CENTER);
		label_55.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		label_55.setBounds(577, 11, 107, 16);
		panel_2.add(label_55);
		
		JSlider slider_thermo = new JSlider();
		slider_thermo.setSnapToTicks(true);
		slider_thermo.setPaintTicks(true);
		slider_thermo.setPaintLabels(true);
		slider_thermo.setMaximum(80);
		slider_thermo.setMajorTickSpacing(15);
		slider_thermo.setBounds(559, 28, 144, 49);
		panel_2.add(slider_thermo);
		
		JRadioButton radioButton_announcement = new JRadioButton("");
		radioButton_announcement.setHorizontalAlignment(SwingConstants.CENTER);
		radioButton_announcement.setBounds(368, 39, 141, 23);
		panel_2.add(radioButton_announcement);
		
		JRadioButton radioButton_doors = new JRadioButton("");
		radioButton_doors.setHorizontalAlignment(SwingConstants.CENTER);
		radioButton_doors.setBounds(223, 39, 141, 23);
		panel_2.add(radioButton_doors);
		
		JRadioButton radioButton_lights = new JRadioButton("");
		radioButton_lights.setHorizontalAlignment(SwingConstants.CENTER);
		radioButton_lights.setBounds(115, 39, 141, 23);
		panel_2.add(radioButton_lights);
		
		JRadioButton radioButton_station = new JRadioButton("");
		radioButton_station.setHorizontalAlignment(SwingConstants.CENTER);
		radioButton_station.setBounds(6, 39, 141, 23);
		panel_2.add(radioButton_station);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(194, 11, 319, 37);
		trainControllerTab.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Train Controller ID");
		lblNewLabel.setBounds(40, 11, 134, 14);
		panel_3.add(lblNewLabel);
		
		JComboBox trainControllerIdComboBox = new JComboBox();
		trainControllerIdComboBox.setBounds(165, 8, 69, 20);
		panel_3.add(trainControllerIdComboBox);
		
		JPanel trainModelTab = new JPanel();
		tabbedPane.addTab("Train Model", null, trainModelTab, null);
		trainModelTab.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 723, 472);
		trainModelTab.add(panel);
		panel.setLayout(null);
		
		JComboBox trainModelComboBox = new JComboBox();
		trainModelComboBox.setBounds(338, 11, 47, 20);
		panel.add(trainModelComboBox);
		
		JLabel trainIdLabel = new JLabel("Train ID");
		trainIdLabel.setBounds(269, 14, 46, 14);
		panel.add(trainIdLabel);
		
		JLabel label_10 = new JLabel("Information");
		label_10.setBounds(64, 66, 66, 16);
		panel.add(label_10);
		
		JLabel label_11 = new JLabel("ID #");
		label_11.setBounds(10, 111, 31, 16);
		panel.add(label_11);
		
		JLabel label_12 = new JLabel("Length:");
		label_12.setBounds(10, 141, 43, 16);
		panel.add(label_12);
		
		JLabel label_13 = new JLabel("Width:");
		label_13.setBounds(10, 174, 38, 16);
		panel.add(label_13);
		
		JLabel label_14 = new JLabel("Height:");
		label_14.setBounds(10, 207, 41, 16);
		panel.add(label_14);
		
		JLabel label_15 = new JLabel("Mass:");
		label_15.setBounds(10, 240, 34, 16);
		panel.add(label_15);
		
		JLabel label_16 = new JLabel("Cars:");
		label_16.setBounds(10, 273, 31, 16);
		panel.add(label_16);
		
		JLabel label_17 = new JLabel("Crew Count:");
		label_17.setBounds(10, 303, 72, 16);
		panel.add(label_17);
		
		JLabel label_18 = new JLabel("Passenger Count:");
		label_18.setBounds(10, 333, 101, 16);
		panel.add(label_18);
		
		JLabel label_19 = new JLabel("Stop:");
		label_19.setBounds(10, 366, 38, 16);
		panel.add(label_19);
		
		JLabel label_20 = new JLabel("Distance:");
		label_20.setBounds(10, 396, 56, 16);
		panel.add(label_20);
		
		JLabel label_21 = new JLabel("Grade:");
		label_21.setBounds(10, 429, 39, 16);
		panel.add(label_21);
		
		JLabel label_22 = new JLabel("Inputs");
		label_22.setBounds(317, 66, 35, 16);
		panel.add(label_22);
		
		JLabel label_23 = new JLabel("Outputs");
		label_23.setBounds(587, 66, 51, 16);
		panel.add(label_23);
		
		txtID = new JTextField();
		txtID.setHorizontalAlignment(SwingConstants.RIGHT);
		txtID.setEditable(false);
		txtID.setColumns(10);
		txtID.setBounds(63, 108, 112, 22);
		panel.add(txtID);
		
		txtLength = new JTextField();
		txtLength.setText("105.643 ft");
		txtLength.setHorizontalAlignment(SwingConstants.RIGHT);
		txtLength.setEditable(false);
		txtLength.setColumns(10);
		txtLength.setBounds(63, 138, 112, 22);
		panel.add(txtLength);
		
		txtWidth = new JTextField();
		txtWidth.setText("8.694 ft");
		txtWidth.setHorizontalAlignment(SwingConstants.RIGHT);
		txtWidth.setEditable(false);
		txtWidth.setColumns(10);
		txtWidth.setBounds(55, 171, 120, 22);
		panel.add(txtWidth);
		
		txtHeight = new JTextField();
		txtHeight.setText("11.221 ft");
		txtHeight.setHorizontalAlignment(SwingConstants.RIGHT);
		txtHeight.setEditable(false);
		txtHeight.setColumns(10);
		txtHeight.setBounds(55, 204, 120, 22);
		panel.add(txtHeight);
		
		txtMass = new JTextField();
		txtMass.setText("80200 lbs");
		txtMass.setHorizontalAlignment(SwingConstants.RIGHT);
		txtMass.setEditable(false);
		txtMass.setColumns(10);
		txtMass.setBounds(55, 237, 120, 22);
		panel.add(txtMass);
		
		txtCars = new JTextField();
		txtCars.setText("1");
		txtCars.setHorizontalAlignment(SwingConstants.RIGHT);
		txtCars.setEditable(false);
		txtCars.setColumns(10);
		txtCars.setBounds(64, 270, 111, 22);
		panel.add(txtCars);
		
		txtCrewCount = new JTextField();
		txtCrewCount.setText("2");
		txtCrewCount.setHorizontalAlignment(SwingConstants.RIGHT);
		txtCrewCount.setEditable(false);
		txtCrewCount.setColumns(10);
		txtCrewCount.setBounds(90, 300, 85, 22);
		panel.add(txtCrewCount);
		
		txtPassengerCount = new JTextField();
		txtPassengerCount.setText("0");
		txtPassengerCount.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPassengerCount.setEditable(false);
		txtPassengerCount.setColumns(10);
		txtPassengerCount.setBounds(119, 330, 56, 22);
		panel.add(txtPassengerCount);
		
		txtRoute = new JTextField();
		txtRoute.setHorizontalAlignment(SwingConstants.RIGHT);
		txtRoute.setEditable(false);
		txtRoute.setColumns(10);
		txtRoute.setBounds(48, 363, 127, 22);
		panel.add(txtRoute);
		
		txtDistance = new JTextField();
		txtDistance.setHorizontalAlignment(SwingConstants.RIGHT);
		txtDistance.setEditable(false);
		txtDistance.setColumns(10);
		txtDistance.setBounds(76, 393, 99, 22);
		panel.add(txtDistance);
		
		txtGrade = new JTextField();
		txtGrade.setText("0");
		txtGrade.setHorizontalAlignment(SwingConstants.RIGHT);
		txtGrade.setEditable(false);
		txtGrade.setColumns(10);
		txtGrade.setBounds(55, 426, 120, 22);
		panel.add(txtGrade);
		
		JLabel label_24 = new JLabel("Authority:");
		label_24.setBounds(218, 111, 56, 16);
		panel.add(label_24);
		
		txtAuthority = new JTextField();
		txtAuthority.setText("0");
		txtAuthority.setHorizontalAlignment(SwingConstants.RIGHT);
		txtAuthority.setEditable(false);
		txtAuthority.setColumns(10);
		txtAuthority.setBounds(283, 108, 160, 22);
		panel.add(txtAuthority);
		
		txtPower = new JTextField();
		txtPower.setText("0");
		txtPower.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPower.setEditable(false);
		txtPower.setColumns(10);
		txtPower.setBounds(283, 138, 160, 22);
		panel.add(txtPower);
		
		JLabel label_25 = new JLabel("Power:");
		label_25.setBounds(218, 141, 41, 16);
		panel.add(label_25);
		
		JLabel label_26 = new JLabel("Commanded Speed:");
		label_26.setBounds(218, 174, 121, 16);
		panel.add(label_26);
		
		txtSpeedLimit = new JTextField();
		txtSpeedLimit.setText("43.496");
		txtSpeedLimit.setHorizontalAlignment(SwingConstants.RIGHT);
		txtSpeedLimit.setEditable(false);
		txtSpeedLimit.setColumns(10);
		txtSpeedLimit.setBounds(338, 171, 105, 22);
		panel.add(txtSpeedLimit);
		
		txtTempIn = new JTextField();
		txtTempIn.setText("69");
		txtTempIn.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTempIn.setEditable(false);
		txtTempIn.setColumns(10);
		txtTempIn.setBounds(300, 204, 143, 22);
		panel.add(txtTempIn);
		
		JLabel label_27 = new JLabel("Temperature:");
		label_27.setBounds(218, 207, 80, 16);
		panel.add(label_27);
		
		JLabel label_28 = new JLabel("Failures");
		label_28.setBounds(398, 288, 45, 16);
		panel.add(label_28);
		
		JToggleButton tglbtnEngineFailure = new JToggleButton("Engine Failure");
		tglbtnEngineFailure.setBounds(218, 320, 113, 29);
		panel.add(tglbtnEngineFailure);
		
		JToggleButton tglbtnSignalPickupFailure = new JToggleButton("Signal Pickup Failure");
		tglbtnSignalPickupFailure.setBounds(338, 318, 160, 31);
		panel.add(tglbtnSignalPickupFailure);
		
		JToggleButton tglbtnBrakeFailure = new JToggleButton("Brake Failure");
		tglbtnBrakeFailure.setBounds(508, 320, 116, 29);
		panel.add(tglbtnBrakeFailure);
		
		JLabel label_29 = new JLabel("Mode");
		label_29.setBounds(398, 366, 45, 16);
		panel.add(label_29);
		
		JRadioButton rdbtnAutomatic = new JRadioButton("Automatic");
		rdbtnAutomatic.setBounds(325, 392, 85, 25);
		panel.add(rdbtnAutomatic);
		
		JRadioButton rdbtnManual = new JRadioButton("Manual");
		rdbtnManual.setSelected(true);
		rdbtnManual.setBounds(440, 392, 69, 25);
		panel.add(rdbtnManual);
		
		txtSpeed = new JTextField();
		txtSpeed.setHorizontalAlignment(SwingConstants.RIGHT);
		txtSpeed.setEditable(false);
		txtSpeed.setColumns(10);
		txtSpeed.setBounds(582, 108, 113, 22);
		panel.add(txtSpeed);
		
		txtAcceleration = new JTextField();
		txtAcceleration.setHorizontalAlignment(SwingConstants.RIGHT);
		txtAcceleration.setEditable(false);
		txtAcceleration.setColumns(10);
		txtAcceleration.setBounds(582, 138, 113, 22);
		panel.add(txtAcceleration);
		
		txtTemp = new JTextField();
		txtTemp.setText("69");
		txtTemp.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTemp.setEditable(false);
		txtTemp.setColumns(10);
		txtTemp.setBounds(582, 171, 113, 22);
		panel.add(txtTemp);
		
		txtDoors = new JTextField();
		txtDoors.setHorizontalAlignment(SwingConstants.RIGHT);
		txtDoors.setEditable(false);
		txtDoors.setColumns(10);
		txtDoors.setBounds(542, 204, 153, 22);
		panel.add(txtDoors);
		
		txtLights = new JTextField();
		txtLights.setHorizontalAlignment(SwingConstants.RIGHT);
		txtLights.setEditable(false);
		txtLights.setColumns(10);
		txtLights.setBounds(542, 237, 153, 22);
		panel.add(txtLights);
		
		JLabel label_30 = new JLabel("Lights:");
		label_30.setBounds(494, 240, 38, 16);
		panel.add(label_30);
		
		JLabel label_31 = new JLabel("Doors:");
		label_31.setBounds(494, 207, 38, 16);
		panel.add(label_31);
		
		JLabel label_32 = new JLabel("Temperature:");
		label_32.setBounds(494, 174, 80, 16);
		panel.add(label_32);
		
		JLabel label_33 = new JLabel("Acceleration:");
		label_33.setBounds(494, 141, 75, 16);
		panel.add(label_33);
		
		JLabel label_34 = new JLabel("Actual Speed:");
		label_34.setBounds(494, 111, 80, 16);
		panel.add(label_34);
		
		JPanel mboTab = new JPanel();
		tabbedPane.addTab("MBO", null, mboTab, null);
		mboTab.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(0, 0, 743, 494);
		mboTab.add(panel_5);
		panel_5.setLayout(null);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(0, 100, 733, 193);
		panel_5.add(scrollPane_4);
		
		scheduleJTable = new JTable();
		scheduleJTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
					"Train ID", "Line", "Station", "Total Time to Station (min)"
			}
		));
		scrollPane_4.setViewportView(scheduleJTable);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(0, 304, 733, 179);
		panel_5.add(scrollPane_5);
		
		operatorScheduleJTable = new JTable();
		operatorScheduleJTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
					"Operator", "Train ID", "Shift Start", "Break Start", "Break End", "Shift End"
			}
		));
		scrollPane_5.setViewportView(operatorScheduleJTable);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6.setBounds(175, 11, 347, 84);
		panel_5.add(panel_6);
		panel_6.setLayout(null);
		
		JButton trainScheduleButton = new JButton("Set Train Schedule");
		trainScheduleButton.setEnabled(true);
		trainScheduleButton.setBounds(10, 2, 152, 23);
		panel_6.add(trainScheduleButton);
		
		JButton operatorScheduleButton = new JButton("Set Operator Schedule");
		operatorScheduleButton.setEnabled(true);
		operatorScheduleButton.setBounds(164, 2, 173, 23);
		panel_6.add(operatorScheduleButton);
		
		JLabel startLabel = new JLabel("Start Schedule at Time:");
		startLabel.setBounds(48, 39, 158, 14);
		panel_6.add(startLabel);
		
		startInput = new JTextField("00:00", 4);
		startInput.setBounds(201, 36, 38, 20);
		panel_6.add(startInput);
		
		JButton gernerateScheduleButton = new JButton("Generate Schedule");
		gernerateScheduleButton.setEnabled(true);
		gernerateScheduleButton.setBounds(87, 60, 152, 23);
		panel_6.add(gernerateScheduleButton);
	}
}
