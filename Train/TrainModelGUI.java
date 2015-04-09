import java.awt.EventQueue;
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
		frame.setBounds(100, 100, 900, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblInputs = new JLabel("Inputs");
		lblInputs.setBounds(362, 13, 35, 16);
		
		JLabel lblAuthority = new JLabel("Authority:");
		lblAuthority.setBounds(281, 42, 56, 16);
		
		JLabel lblPower = new JLabel("Power:");
		lblPower.setBounds(281, 75, 41, 16);
		
		JLabel lblCommandedSpeed = new JLabel("Commanded Speed:");
		lblCommandedSpeed.setBounds(281, 110, 121, 16);
		
		JLabel lblTemperature = new JLabel("Temperature:");
		lblTemperature.setBounds(281, 145, 80, 16);
		
		txtAuthority = new JTextField();
		txtAuthority.setEditable(false);
		txtAuthority.setBounds(349, 39, 160, 22);
		txtAuthority.setText("0");
		txtAuthority.setHorizontalAlignment(SwingConstants.RIGHT);
		txtAuthority.setColumns(10);
		
		txtPower = new JTextField();
		txtPower.setEditable(false);
		txtPower.setBounds(327, 72, 182, 22);
		txtPower.setText("0");
		txtPower.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPower.setColumns(10);
		
		txtSpeedLimit = new JTextField();
		txtSpeedLimit.setEditable(false);
		txtSpeedLimit.setBounds(408, 107, 101, 22);
		txtSpeedLimit.setText("43.496");
		txtSpeedLimit.setHorizontalAlignment(SwingConstants.RIGHT);
		txtSpeedLimit.setColumns(10);
		
		txtTempIn = new JTextField();
		txtTempIn.setEditable(false);
		txtTempIn.setBounds(366, 142, 143, 22);
		txtTempIn.setText("69");
		txtTempIn.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTempIn.setColumns(10);
		
		JLabel lblOutputs = new JLabel("Outputs");
		lblOutputs.setBounds(698, 13, 51, 16);
		
		JLabel lblActualSpeed = new JLabel("Actual Speed:");
		lblActualSpeed.setBounds(552, 42, 80, 16);
		
		JLabel lblAcceleration = new JLabel("Acceleration:");
		lblAcceleration.setBounds(552, 75, 75, 16);
		
		JLabel lblTemperature_1 = new JLabel("Temperature:");
		lblTemperature_1.setBounds(552, 110, 80, 16);
		
		JLabel lblDoors = new JLabel("Doors:");
		lblDoors.setBounds(552, 145, 38, 16);
		
		JLabel lblLights = new JLabel("Lights:");
		lblLights.setBounds(552, 183, 38, 16);
		
		JLabel lblNewLabel = new JLabel("Grade:");
		lblNewLabel.setBounds(12, 373, 39, 16);
		
		JLabel lblInformation = new JLabel("Information");
		lblInformation.setBounds(98, 13, 66, 16);
		
		JLabel lblLength = new JLabel("Length:");
		lblLength.setBounds(12, 78, 43, 16);
		
		JLabel lblNewLabel_1 = new JLabel("Width:");
		lblNewLabel_1.setBounds(12, 111, 38, 16);
		
		JLabel lblHeight = new JLabel("Height:");
		lblHeight.setBounds(12, 146, 41, 16);
		
		JLabel lblMass = new JLabel("Mass:");
		lblMass.setBounds(12, 181, 34, 16);
		
		JLabel lblCrewCount = new JLabel("Crew Count:");
		lblCrewCount.setBounds(12, 254, 72, 16);
		
		JLabel lblPassengerCount = new JLabel("Passenger Count:");
		lblPassengerCount.setBounds(12, 286, 101, 16);
		
		txtLength = new JTextField();
		txtLength.setBounds(60, 75, 175, 22);
		txtLength.setText("105.643 ft");
		txtLength.setHorizontalAlignment(SwingConstants.RIGHT);
		txtLength.setEditable(false);
		txtLength.setColumns(10);
		
		txtWidth = new JTextField();
		txtWidth.setBounds(55, 108, 180, 22);
		txtWidth.setText("8.694 ft");
		txtWidth.setHorizontalAlignment(SwingConstants.RIGHT);
		txtWidth.setEditable(false);
		txtWidth.setColumns(10);
		
		txtHeight = new JTextField();
		txtHeight.setBounds(58, 143, 177, 22);
		txtHeight.setText("11.221 ft");
		txtHeight.setHorizontalAlignment(SwingConstants.RIGHT);
		txtHeight.setEditable(false);
		txtHeight.setColumns(10);
		
		txtMass = new JTextField();
		txtMass.setBounds(51, 178, 184, 22);
		txtMass.setText("80200 lbs");
		txtMass.setHorizontalAlignment(SwingConstants.RIGHT);
		txtMass.setEditable(false);
		txtMass.setColumns(10);
		
		txtCrewCount = new JTextField();
		txtCrewCount.setEditable(false);
		txtCrewCount.setBounds(90, 251, 145, 22);
		txtCrewCount.setText("2");
		txtCrewCount.setHorizontalAlignment(SwingConstants.RIGHT);
		txtCrewCount.setColumns(10);
		
		txtPassengerCount = new JTextField();
		txtPassengerCount.setEditable(false);
		txtPassengerCount.setBounds(119, 283, 116, 22);
		txtPassengerCount.setText("0");
		txtPassengerCount.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPassengerCount.setColumns(10);
		
		JLabel lblCars = new JLabel("Cars:");
		lblCars.setBounds(12, 219, 31, 16);
		
		txtCars = new JTextField();
		txtCars.setBounds(48, 216, 187, 22);
		txtCars.setHorizontalAlignment(SwingConstants.RIGHT);
		txtCars.setText("1");
		txtCars.setEditable(false);
		txtCars.setColumns(10);
		
		txtSpeed = new JTextField();
		txtSpeed.setBounds(649, 39, 167, 22);
		txtSpeed.setHorizontalAlignment(SwingConstants.RIGHT);
		txtSpeed.setEditable(false);
		txtSpeed.setColumns(10);
		
		txtAcceleration = new JTextField();
		txtAcceleration.setBounds(644, 72, 172, 22);
		txtAcceleration.setHorizontalAlignment(SwingConstants.RIGHT);
		txtAcceleration.setEditable(false);
		txtAcceleration.setColumns(10);
		
		txtTemp = new JTextField();
		txtTemp.setBounds(644, 107, 172, 22);
		txtTemp.setText("69");
		txtTemp.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTemp.setEditable(false);
		txtTemp.setColumns(10);
		
		txtDoors = new JTextField();
		txtDoors.setBounds(612, 142, 204, 22);
		txtDoors.setHorizontalAlignment(SwingConstants.RIGHT);
		txtDoors.setEditable(false);
		txtDoors.setColumns(10);
		
		txtLights = new JTextField();
		txtLights.setBounds(601, 180, 215, 22);
		txtLights.setHorizontalAlignment(SwingConstants.RIGHT);
		txtLights.setEditable(false);
		txtLights.setColumns(10);
		
		txtGrade = new JTextField();
		txtGrade.setEditable(false);
		txtGrade.setBounds(57, 370, 178, 22);
		txtGrade.setText("0");
		txtGrade.setHorizontalAlignment(SwingConstants.RIGHT);
		txtGrade.setColumns(10);
		
		JLabel lblFailures = new JLabel("Failures");
		lblFailures.setBounds(406, 240, 45, 16);
		
		txtRoute = new JTextField();
		txtRoute.setBounds(48, 312, 187, 22);
		txtRoute.setEditable(false);
		txtRoute.setHorizontalAlignment(SwingConstants.RIGHT);
		txtRoute.setColumns(10);
		
		JLabel lblRoute = new JLabel("Stop:");
		lblRoute.setBounds(12, 315, 38, 16);
		
		JRadioButton rdbtnAutomatic = new JRadioButton("Automatic");
		rdbtnAutomatic.setBounds(297, 340, 85, 25);
		
		JRadioButton rdbtnManual = new JRadioButton("Manual");
		rdbtnManual.setBounds(424, 340, 69, 25);
		rdbtnManual.setSelected(true);
		
		JLabel lblMode = new JLabel("Mode");
		lblMode.setBounds(383, 315, 31, 16);
		
		JToggleButton tglbtnEngineFailure = new JToggleButton("Engine Failure");
		tglbtnEngineFailure.setBounds(247, 269, 113, 25);
		
		JToggleButton tglbtnSignalPickupFailure = new JToggleButton("Signal Pickup Failure");
		tglbtnSignalPickupFailure.setBounds(366, 269, 160, 25);
		
		JToggleButton tglbtnBrakeFailure = new JToggleButton("Brake Failure");
		tglbtnBrakeFailure.setBounds(538, 269, 116, 25);
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
		lblId.setBounds(12, 42, 31, 16);
		frame.getContentPane().add(lblId);
		
		txtID = new JTextField();
		txtID.setHorizontalAlignment(SwingConstants.RIGHT);
		txtID.setEditable(false);
		txtID.setBounds(48, 39, 187, 22);
		frame.getContentPane().add(txtID);
		txtID.setColumns(10);
		
		JLabel lblDistance = new JLabel("Distance:");
		lblDistance.setBounds(12, 344, 56, 16);
		frame.getContentPane().add(lblDistance);
		
		txtDistance = new JTextField();
		txtDistance.setHorizontalAlignment(SwingConstants.RIGHT);
		txtDistance.setEditable(false);
		txtDistance.setBounds(75, 341, 160, 22);
		frame.getContentPane().add(txtDistance);
		txtDistance.setColumns(10);
	}
	
	public void updateGUI(TrainData data)
	{
		txtID.setText(Integer.toString(data.ID));
		txtMass.setText(Double.valueOf(data.mass * 2.2).toString() + " lbs");
		txtPassengerCount.setText(Integer.toString(data.passengers));
		txtRoute.setText(data.lastStop);
		txtDistance.setText(Double.valueOf(data.dtv.distance * 3.2).toString() + " ft");
		txtGrade.setText(Double.valueOf(data.grade).toString());
		txtAuthority.setText(Double.valueOf(data.dtv.curAuthority * 3.2).toString() + " ft");
		txtPower.setText(Double.valueOf(data.power).toString() + " W");
		txtSpeedLimit.setText(Double.valueOf(data.dtv.commandedSpeed * 2.23).toString() + " mph");
		txtTempIn.setText(Double.valueOf(data.commandedTemperature).toString() + " F");
		txtSpeed.setText(Double.valueOf(data.dtv.curSpeed * 2.23).toString() + " mph");
		txtAcceleration.setText(Double.valueOf(data.dtv.curAcceleration * 3.28).toString() + "ft/s^2");
		txtTemp.setText(Double.valueOf(data.dtv.curTemp).toString() + " F");
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