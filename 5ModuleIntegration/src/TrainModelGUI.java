import java.awt.EventQueue;
import java.text.DecimalFormat;
import java.util.*;
import java.lang.Thread;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Panel;
import java.awt.Font;


public class TrainModelGUI 
{

	private JFrame frame;
	private JTextField txtAuthority;
	private JTextField txtPower;
	private JTextField txtSpeedLimit;
	private JTextField txtTempIn;
	private JTextField txtLength;
	private JTextField txtWidth;
	private JTextField txtHeight;
	private JTextField txtMass;
	private JTextField txtCrewCount;
	private JTextField txtPassengerCount;
	private JTextField txtCars;
	private JTextField txtSpeed;
	private JTextField txtAcceleration;
	private JTextField txtTemp;
	private JTextField txtDoors;
	private JTextField txtLights;
	private JTextField txtGrade;
	private JTextField txtRoute;
	private JTextField txtID;
	private JTextField txtDistance;
	private DecimalFormat db = new DecimalFormat("0.00");

	//How to call
	//TrainModelGUI window = new TrainModelGUI();
	//window.frame.setVisible(true);
	
	/**
	 * Create the application.
	 */
	public TrainModelGUI() 
	{
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 789, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblInputs = new JLabel("Inputs:");
		lblInputs.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 16));
		lblInputs.setBounds(262, 57, 231, 16);
		
		JLabel lblAuthority = new JLabel("Authority:");
		lblAuthority.setBounds(262, 86, 75, 16);
		
		JLabel lblPower = new JLabel("Power:");
		lblPower.setBounds(262, 122, 41, 16);
		
		JLabel lblCommandedSpeed = new JLabel("Comm. Speed:");
		lblCommandedSpeed.setBounds(262, 155, 121, 16);
		
		JLabel lblTemperature = new JLabel("Temperature:");
		lblTemperature.setBounds(262, 190, 98, 16);
		
		txtAuthority = new JTextField();
		txtAuthority.setEditable(false);
		txtAuthority.setBounds(359, 83, 150, 22);
		txtAuthority.setText("0");
		txtAuthority.setHorizontalAlignment(SwingConstants.RIGHT);
		txtAuthority.setColumns(10);
		
		txtPower = new JTextField();
		txtPower.setEditable(false);
		txtPower.setBounds(359, 116, 150, 22);
		txtPower.setText("0");
		txtPower.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPower.setColumns(10);
		
		txtSpeedLimit = new JTextField();
		txtSpeedLimit.setEditable(false);
		txtSpeedLimit.setBounds(359, 151, 150, 22);
		txtSpeedLimit.setText("43.496");
		txtSpeedLimit.setHorizontalAlignment(SwingConstants.RIGHT);
		txtSpeedLimit.setColumns(10);
		
		txtTempIn = new JTextField();
		txtTempIn.setEditable(false);
		txtTempIn.setBounds(359, 186, 150, 22);
		txtTempIn.setText("69");
		txtTempIn.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTempIn.setColumns(10);
		
		JLabel lblOutputs = new JLabel("Outputs:");
		lblOutputs.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 16));
		lblOutputs.setBounds(262, 231, 264, 16);
		
		JLabel lblActualSpeed = new JLabel("Actual Speed:");
		lblActualSpeed.setBounds(262, 260, 95, 16);
		
		JLabel lblAcceleration = new JLabel("Acceleration:");
		lblAcceleration.setBounds(262, 296, 95, 16);
		
		JLabel lblTemperature_1 = new JLabel("Temperature:");
		lblTemperature_1.setBounds(262, 329, 95, 16);
		
		JLabel lblDoors = new JLabel("Doors:");
		lblDoors.setBounds(262, 364, 61, 16);
		
		JLabel lblLights = new JLabel("Lights:");
		lblLights.setBounds(262, 399, 75, 16);
		
		JLabel lblNewLabel = new JLabel("Grade:");
		lblNewLabel.setBounds(6, 402, 101, 16);
		
		JLabel lblInformation = new JLabel("Information:");
		lblInformation.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 16));
		lblInformation.setBounds(6, 57, 223, 16);
		
		JLabel lblLength = new JLabel("Length:");
		lblLength.setBounds(6, 107, 95, 16);
		
		JLabel lblNewLabel_1 = new JLabel("Width:");
		lblNewLabel_1.setBounds(6, 140, 95, 16);
		
		JLabel lblHeight = new JLabel("Height:");
		lblHeight.setBounds(6, 175, 80, 16);
		
		JLabel lblMass = new JLabel("Mass:");
		lblMass.setBounds(6, 210, 95, 16);
		
		JLabel lblCrewCount = new JLabel("Crew Count:");
		lblCrewCount.setBounds(6, 283, 95, 16);
		
		JLabel lblPassengerCount = new JLabel("Pass. Count:");
		lblPassengerCount.setBounds(6, 315, 101, 16);
		
		txtLength = new JTextField();
		txtLength.setBounds(113, 104, 116, 22);
		txtLength.setText("105.643 ft");
		txtLength.setHorizontalAlignment(SwingConstants.RIGHT);
		txtLength.setEditable(false);
		txtLength.setColumns(10);
		
		txtWidth = new JTextField();
		txtWidth.setBounds(113, 137, 116, 22);
		txtWidth.setText("8.694 ft");
		txtWidth.setHorizontalAlignment(SwingConstants.RIGHT);
		txtWidth.setEditable(false);
		txtWidth.setColumns(10);
		
		txtHeight = new JTextField();
		txtHeight.setBounds(113, 172, 116, 22);
		txtHeight.setText("11.221 ft");
		txtHeight.setHorizontalAlignment(SwingConstants.RIGHT);
		txtHeight.setEditable(false);
		txtHeight.setColumns(10);
		
		txtMass = new JTextField();
		txtMass.setBounds(113, 207, 116, 22);
		txtMass.setText("80200 lbs");
		txtMass.setHorizontalAlignment(SwingConstants.RIGHT);
		txtMass.setEditable(false);
		txtMass.setColumns(10);
		
		txtCrewCount = new JTextField();
		txtCrewCount.setEditable(false);
		txtCrewCount.setBounds(113, 280, 116, 22);
		txtCrewCount.setText("2");
		txtCrewCount.setHorizontalAlignment(SwingConstants.RIGHT);
		txtCrewCount.setColumns(10);
		
		txtPassengerCount = new JTextField();
		txtPassengerCount.setEditable(false);
		txtPassengerCount.setBounds(113, 312, 116, 22);
		txtPassengerCount.setText("0");
		txtPassengerCount.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPassengerCount.setColumns(10);
		
		JLabel lblCars = new JLabel("Cars:");
		lblCars.setBounds(6, 248, 95, 16);
		
		txtCars = new JTextField();
		txtCars.setBounds(113, 245, 116, 22);
		txtCars.setHorizontalAlignment(SwingConstants.RIGHT);
		txtCars.setText("1");
		txtCars.setEditable(false);
		txtCars.setColumns(10);
		
		txtSpeed = new JTextField();
		txtSpeed.setBounds(359, 257, 204, 22);
		txtSpeed.setHorizontalAlignment(SwingConstants.RIGHT);
		txtSpeed.setEditable(false);
		txtSpeed.setColumns(10);
		
		txtAcceleration = new JTextField();
		txtAcceleration.setBounds(359, 293, 204, 22);
		txtAcceleration.setHorizontalAlignment(SwingConstants.RIGHT);
		txtAcceleration.setEditable(false);
		txtAcceleration.setColumns(10);
		
		txtTemp = new JTextField();
		txtTemp.setBounds(359, 326, 204, 22);
		txtTemp.setText("69");
		txtTemp.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTemp.setEditable(false);
		txtTemp.setColumns(10);
		
		txtDoors = new JTextField();
		txtDoors.setBounds(359, 361, 204, 22);
		txtDoors.setHorizontalAlignment(SwingConstants.RIGHT);
		txtDoors.setEditable(false);
		txtDoors.setColumns(10);
		
		txtLights = new JTextField();
		txtLights.setBounds(359, 396, 204, 22);
		txtLights.setHorizontalAlignment(SwingConstants.RIGHT);
		txtLights.setEditable(false);
		txtLights.setColumns(10);
		
		txtGrade = new JTextField();
		txtGrade.setEditable(false);
		txtGrade.setBounds(113, 399, 116, 22);
		txtGrade.setText("0");
		txtGrade.setHorizontalAlignment(SwingConstants.RIGHT);
		txtGrade.setColumns(10);
		
		JLabel lblFailures = new JLabel("Failures:");
		lblFailures.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 16));
		lblFailures.setBounds(574, 57, 113, 16);
		
		txtRoute = new JTextField();
		txtRoute.setBounds(113, 341, 116, 22);
		txtRoute.setEditable(false);
		txtRoute.setHorizontalAlignment(SwingConstants.RIGHT);
		txtRoute.setColumns(10);
		
		JLabel lblRoute = new JLabel("Stop:");
		lblRoute.setBounds(6, 344, 38, 16);
		
		JRadioButton rdbtnAutomatic = new JRadioButton("Automatic");
		rdbtnAutomatic.setBounds(626, 344, 113, 33);
		
		JRadioButton rdbtnManual = new JRadioButton("Manual");
		rdbtnManual.setBounds(626, 306, 101, 33);
		rdbtnManual.setSelected(true);
		
		JLabel lblMode = new JLabel("Mode:");
		lblMode.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 16));
		lblMode.setBounds(626, 282, 101, 16);
		
		JToggleButton tglbtnEngineFailure = new JToggleButton("Engine Failure");
		tglbtnEngineFailure.setBounds(574, 85, 189, 41);
		
		JToggleButton tglbtnSignalPickupFailure = new JToggleButton("Signal Pickup Failure");
		tglbtnSignalPickupFailure.setBounds(574, 128, 189, 41);
		
		JToggleButton tglbtnBrakeFailure = new JToggleButton("Brake Failure");
		tglbtnBrakeFailure.setBounds(574, 173, 189, 41);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(lblCars);
		frame.getContentPane().add(txtCars);
		frame.getContentPane().add(lblMass);
		frame.getContentPane().add(txtMass);
		frame.getContentPane().add(lblHeight);
		frame.getContentPane().add(txtHeight);
		frame.getContentPane().add(lblNewLabel_1);
		frame.getContentPane().add(txtWidth);
		frame.getContentPane().add(lblLength);
		frame.getContentPane().add(txtLength);
		frame.getContentPane().add(lblCrewCount);
		frame.getContentPane().add(txtCrewCount);
		frame.getContentPane().add(lblPassengerCount);
		frame.getContentPane().add(txtPassengerCount);
		frame.getContentPane().add(lblNewLabel);
		frame.getContentPane().add(txtGrade);
		frame.getContentPane().add(lblRoute);
		frame.getContentPane().add(txtRoute);
		frame.getContentPane().add(lblInformation);
		frame.getContentPane().add(lblAuthority);
		frame.getContentPane().add(txtAuthority);
		frame.getContentPane().add(lblPower);
		frame.getContentPane().add(txtPower);
		frame.getContentPane().add(lblCommandedSpeed);
		frame.getContentPane().add(txtSpeedLimit);
		frame.getContentPane().add(lblTemperature);
		frame.getContentPane().add(txtTempIn);
		frame.getContentPane().add(lblInputs);
		frame.getContentPane().add(lblMode);
		frame.getContentPane().add(rdbtnAutomatic);
		frame.getContentPane().add(rdbtnManual);
		frame.getContentPane().add(lblOutputs);
		frame.getContentPane().add(lblLights);
		frame.getContentPane().add(txtLights);
		frame.getContentPane().add(lblDoors);
		frame.getContentPane().add(txtDoors);
		frame.getContentPane().add(lblTemperature_1);
		frame.getContentPane().add(txtTemp);
		frame.getContentPane().add(lblAcceleration);
		frame.getContentPane().add(txtAcceleration);
		frame.getContentPane().add(lblActualSpeed);
		frame.getContentPane().add(txtSpeed);
		frame.getContentPane().add(tglbtnEngineFailure);
		frame.getContentPane().add(tglbtnSignalPickupFailure);
		frame.getContentPane().add(tglbtnBrakeFailure);
		frame.getContentPane().add(lblFailures);
		
		JLabel lblId = new JLabel("ID #");
		lblId.setBounds(6, 79, 31, 16);
		frame.getContentPane().add(lblId);
		
		txtID = new JTextField();
		txtID.setHorizontalAlignment(SwingConstants.RIGHT);
		txtID.setEditable(false);
		txtID.setBounds(113, 75, 116, 22);
		frame.getContentPane().add(txtID);
		txtID.setColumns(10);
		
		JLabel lblDistance = new JLabel("Distance:");
		lblDistance.setBounds(6, 373, 101, 16);
		frame.getContentPane().add(lblDistance);
		
		txtDistance = new JTextField();
		txtDistance.setHorizontalAlignment(SwingConstants.RIGHT);
		txtDistance.setEditable(false);
		txtDistance.setBounds(113, 370, 116, 22);
		frame.getContentPane().add(txtDistance);
		txtDistance.setColumns(10);
		
		JLabel lblTrainModelV = new JLabel("Train Model V4:");
		lblTrainModelV.setVerticalAlignment(SwingConstants.TOP);
		lblTrainModelV.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 35));
		lblTrainModelV.setBounds(6, 6, 463, 48);
		frame.getContentPane().add(lblTrainModelV);
	}
	
	public void updateGUI(TrainData data)
	{
		txtID.setText(Integer.toString(data.ID));
		txtMass.setText(db.format(data.mass * 2.2) + " lbs");
		txtPassengerCount.setText(Integer.toString(data.passengers));
		txtRoute.setText(data.lastStop);
		txtDistance.setText(db.format(data.dtv.distance * 3.2) + " ft");
		txtGrade.setText(db.format(data.grade));
		txtAuthority.setText(db.format(data.dtv.curAuthority * 3.2) + " ft");
		txtPower.setText(db.format(data.power) + " W");
		txtSpeedLimit.setText(db.format(data.dtv.commandedSpeed * 2.23) + " mph");
		txtTempIn.setText(db.format(data.commandedTemperature) + " F");
		txtSpeed.setText(db.format(data.dtv.curSpeed * 2.23) + " mph");
		txtAcceleration.setText(db.format(data.dtv.curAcceleration * 3.28) + "ft/s^2");
		txtTemp.setText(db.format(data.dtv.curTemp) + " F");
		if (data.leftDoor && data.rightDoor)
			txtDoors.setText("Both Open");
		else if(data.leftDoor && !data.rightDoor)
			txtDoors.setText("Left Open");
		else if(!data.leftDoor && data.rightDoor)
			txtDoors.setText("Right Open");
		else
			txtDoors.setText("Closed");
		if (data.lights)
			txtLights.setText("On");
		else
			txtLights.setText("Off");
	}
}