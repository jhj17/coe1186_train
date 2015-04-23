import java.awt.EventQueue;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.swing.JFrame;
import javax.swing.JToggleButton;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JSplitPane;

import java.awt.Insets;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.GridLayout;

import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JRadioButton;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.text.DecimalFormat;

public class tcGUI {

	private JFrame frame;
	private JPanel panel_1;
	private JPanel panel;
	private JSlider slider;
	private JLabel lblCurrentSpeed;
	private JLabel lblCurrentSpeedLimit;
	private JLabel lblCurrentAuthority;
	private JLabel lblErrors;
	private JLabel lblBeaconMessage;
	private JLabel lblTrainControllerV;
	private JLabel lblNextStation;
	private JToggleButton toggle_service;
	private JLabel lblArrivingAtStation;
	private JLabel lblLightsOnoff;
	private JLabel lblLeftDoorsOpen;
	private JLabel lblThermostat;
	private JSlider slider_desTherm;
	private JLabel label_7;
	private JLabel lblSpeed_1;
	private JLabel lblAcutal;
	private JLabel lblCommandedSpeed;
	private JSlider slider_2;
	
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private DecimalFormat db = new DecimalFormat("0.00");
	private JLabel lblInManualMode;
	private JToggleButton tglbtnAutopilot;
	private JToggleButton toggle_emergency;
	private JRadioButton radioButton_leftDoor;
	private JRadioButton radioButton_lights;
	private JRadioButton radioButton_station;
	private JLabel lblNoBraking;
	
	JLabel lblTrainID;
	JLabel lblID;
	JButton btnHorn;
	JLabel lblAnnouncements;
	JRadioButton radioButton_rightDoor;
	
	
	
	private TrainState localState = new TrainState();
	private JSlider slider_actTherm;
	private JTextField textField_ads;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TrainState ts = new TrainState();
//					tcGUI window = new tcGUI(ts);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
		TrainControllerGUI g = new TrainControllerGUI(new TrainState());
		System.out.println("here");
		TrainState newState = new TrainState();
		for(int i = 0; i<10; i++){
			g.updateUserSamples(newState);
			System.out.println("In TC " + newState.userDesSpeed);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(newState.userDesSpeed);
	}

	/**
	 * Create the application.
	 * @throws InterruptedException 
	 * @wbp.parser.entryPoint
	 */
	public tcGUI(TrainState ts) throws InterruptedException {
		initialize();
		initializeValues(ts);
		frame.setVisible(true);
		beginPowerHandler();
		beginAutoPilotHandler();
		beginServiceBrakeHandler();
		beginEmergencyBrakeHandler();
		beginThermHandler();
		beginRadioHandler();
		setTrainID(ts);
		PowerThread pt = new PowerThread();
		Thread t1 = new Thread(pt);
		t1.start();
	}
	private void setTrainID(TrainState ts){
		//System.out.println("asdasd " + ts.trainID);
		lblID.setText("" + ts.trainID);
	}
	public synchronized boolean updateValues(TrainState newState){
		//System.out.println("IN GUI " + localState.userDesSpeed);
		refreshDisplay();
		newState.isAutopilot = localState.isAutopilot;
		newState.userEmergencyBrake = localState.userEmergencyBrake;
		newState.userServiceBrake = localState.userServiceBrake;
		newState.userDesSpeed = localState.userDesSpeed;
		newState.desTemp = localState.desTemp;
		newState.shouldLight = localState.shouldLight;
		newState.shouldLeftDoor = localState.shouldLeftDoor;
		newState.shouldRightDoor = localState.shouldRightDoor;
		localState = new TrainState(newState);
		//System.out.println("IN GUI -- AFTER " + localState.userDesSpeed);
		refreshDisplay();
		return true;
	}
	public void refreshDisplay(){
		initializeValues(localState);
		brakeString();
	}
	public void brakeString(){
		if(localState.isEmergency){
			lblNoBraking.setText("EMERGENCY BRAKE ENGAGED!");
		}
		else if(localState.isService){
			lblNoBraking.setText("SERVICE BRAKE ENGAGED!");
		}
		else{
			lblNoBraking.setText("");
		}
	}
	private class PowerThread implements Runnable {
		  private boolean isAlive = true;
		  public void run() {
		      while (isAlive) {
		    	  tcGUI.this.updatePower(); 
		    	  try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      }
		   }
		  public void kill(){
				isAlive = false;
			}
		}
	private synchronized void updatePower(){
		setCommandedPowerBox(localState.commandedPower);
	}
	private void initializeValues(TrainState ts){
		setDesiredVelocitySlider(ts.userDesSpeed);
		setActualVelocitySlider(ts.tv.curSpeed);
		setCommandedPowerBox(ts.commandedPower);
		setCurrentSpeedBox(ts.tv.curSpeed);
		setCommandedSpeedBox(ts.userDesSpeed);
		setSpeedLimitBox(ts.tv.commandedSpeed);
		setAuthorityBox(ts.calculatedAuthority);
		setBeaconBox(""+ts.approachingStation);
		setErrorBox(""+ ts.fails);
		setNextStationBox(ts.stationName);
		calcPower();
		setService();
		setEmergency();
		setAutopilot();
		setActTherm();
		setDesTherm();
		setLeftDoor();
		setRightDoor();
		setLights();
		setAtStation();
	}
	private void setActTherm(){
		slider_actTherm.setValue((int)localState.tv.curTemp);
	}
	private void setDesTherm(){
		slider_desTherm.setValue((int)localState.desTemp);
	}
	private void setDesiredVelocitySlider( double value){
		slider.setValue((int) value);
	}
	private void setActualVelocitySlider(double value){
		slider_2.setValue((int) value);
	}
	private void setCommandedPowerBox(double value){
		textField.setText("" + db.format(value) + " W");
	}
	private void setCurrentSpeedBox(double value){
		textField_1.setText("" + db.format(value) + " m/s");
	}
	private void setCommandedSpeedBox(double value){
		textField_2.setText("" + db.format(value) + " m/s");
	}
	private void setSpeedLimitBox(double value){
		textField_3.setText("" + db.format(value) + " m/s");
	}
	private void setAuthorityBox(double value){
		textField_4.setText("" + db.format(value) + " m");
	}
	private void setBeaconBox(String value){
		textField_5.setText(value);
	}
	private void setErrorBox(String value){
		textField_6.setText(value);
	}
	private void setNextStationBox(String value){
		textField_7.setText(value);
	}
	private double getCommandedSpeedSlider(){
		return (double) slider.getValue();
	}
	private void setLights(){
		radioButton_lights.setSelected(localState.shouldLight);
	}
	private void setAtStation(){
		radioButton_station.setSelected(localState.atStation);
	}
	private void setLeftDoor(){
		//localState.shouldLeftDoor = radioButton_leftDoor.isSelected();
		if(localState.tv.curSpeed > 0){
			radioButton_leftDoor.setEnabled(false);
		}
		else
			radioButton_leftDoor.setEnabled(true);
		radioButton_leftDoor.setSelected(localState.shouldLeftDoor);
	}
	private void setRightDoor(){
		//localState.shouldRightDoor = radioButton_rightDoor.isSelected();
		if(localState.tv.curSpeed > 0){
			radioButton_rightDoor.setEnabled(false);
		}
		else
			radioButton_rightDoor.setEnabled(true);
		radioButton_rightDoor.setSelected(localState.shouldRightDoor);
	}
	private void calcPower(){
		localState.userDesSpeed = getCommandedSpeedSlider();
        setCommandedSpeedBox(localState.userDesSpeed);
	}
	private void setService(){
		if(localState.userServiceBrake){
			toggle_service.setSelected(true);
			slider.setEnabled(false);
		}
		else{
			toggle_service.setSelected(false);
			//setDesiredVelocitySlider(localState.tv.commandedSpeed);
        	slider.setEnabled(true);
		}
	}
	private void setEmergency(){
		if(localState.userEmergencyBrake){
			toggle_emergency.setSelected(true);
			slider.setEnabled(false);
		}
		else{
			toggle_emergency.setSelected(false);
			//setDesiredVelocitySlider(localState.tv.commandedSpeed);
        	slider.setEnabled(true);
		}
	}
	private void setAutopilot(){
		if(localState.isAutopilot){
			lblInManualMode.setText("Automatic Mode is ON");
        	lblInManualMode.setForeground(Color.RED);
        	slider.setEnabled(false);
        	toggle_service.setEnabled(false);
        	//toggle_emergency.setEnabled(false);
        	//radioButton_station.setEnabled(false);
        	radioButton_leftDoor.setEnabled(false);
        	radioButton_rightDoor.setEnabled(false);
        	radioButton_lights.setEnabled(false);
        	//radioButton_announcement.setEnabled(false);
        	setCommandedSpeedBox(localState.tv.commandedSpeed);
            setDesiredVelocitySlider(localState.tv.commandedSpeed);
		}
		else{
			lblInManualMode.setText("Manual Mode is ON");
        	lblInManualMode.setForeground(Color.BLACK);
        	slider.setEnabled(true);
        	toggle_service.setEnabled(true);
        	//toggle_emergency.setEnabled(true);
        	//radioButton_station.setEnabled(true);
        	radioButton_leftDoor.setEnabled(true);
        	radioButton_rightDoor.setEnabled(true);
        	radioButton_lights.setEnabled(true);
        	//radioButton_announcement.setEnabled(true);
		}
	}
	private synchronized void beginPowerHandler(){
		slider.addChangeListener(new ChangeListener() {
	        public void stateChanged(ChangeEvent e) {
	        	calcPower();
	        }
	    });
	}
	private void beginThermHandler(){
		slider_desTherm.addChangeListener(new ChangeListener() {
	        public void stateChanged(ChangeEvent e) {
	        	localState.desTemp = (double)slider_desTherm.getValue();
	        }
	    });
	}
	private void beginRadioHandler(){
		radioButton_rightDoor.addChangeListener(new ChangeListener() {
	        public void stateChanged(ChangeEvent e) {
	        	localState.shouldRightDoor = radioButton_rightDoor.isSelected();
	        }
	    });
		radioButton_leftDoor.addChangeListener(new ChangeListener() {
	        public void stateChanged(ChangeEvent e) {
	        	localState.shouldLeftDoor = radioButton_leftDoor.isSelected();
	        }
	    });
		radioButton_lights.addChangeListener(new ChangeListener() {
	        public void stateChanged(ChangeEvent e) {
	        	localState.shouldLight = radioButton_lights.isSelected();
	        }
	    });
	}
	
	private void beginServiceBrakeHandler(){
		toggle_service.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        	if(toggle_service.isSelected()){
        		if(toggle_emergency.isSelected()){
        			localState.userEmergencyBrake = false;
        		}
        		localState.userServiceBrake = true;
        	}
        	else{
        		localState.userServiceBrake = false;
        	}
        	setService();
        	setEmergency();
        }
    });
	}
	private void beginEmergencyBrakeHandler(){
		toggle_emergency.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        	if(toggle_emergency.isSelected()){
        		if(toggle_service.isSelected()) {
        			localState.userServiceBrake = false;
        		}
        		localState.userEmergencyBrake = true;
        	}
        	else{
        		localState.userEmergencyBrake = false;
        	}
        	setService();
        	setEmergency();
        }
    });
	}
	
	private void beginAutoPilotHandler(){
		tglbtnAutopilot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tglbtnAutopilot.isSelected()){
                	localState.isAutopilot = true;
                }
                else{
                	localState.isAutopilot = false;
                }
            }
        });
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 792, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel_1 = new JPanel();
		
		panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 777, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_1.setLayout(null);
		
		lblArrivingAtStation = new JLabel("At Station");
		lblArrivingAtStation.setHorizontalAlignment(SwingConstants.CENTER);
		lblArrivingAtStation.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		lblArrivingAtStation.setBounds(6, 6, 73, 16);
		panel_1.add(lblArrivingAtStation);
		
		lblLightsOnoff = new JLabel("Lights");
		lblLightsOnoff.setHorizontalAlignment(SwingConstants.CENTER);
		lblLightsOnoff.setFont(new Font("Modern No. 20", Font.BOLD, 15));
		lblLightsOnoff.setBounds(6, 69, 73, 16);
		panel_1.add(lblLightsOnoff);
		
		lblLeftDoorsOpen = new JLabel("Left Doors Open");
		lblLeftDoorsOpen.setHorizontalAlignment(SwingConstants.CENTER);
		lblLeftDoorsOpen.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		lblLeftDoorsOpen.setBounds(93, 6, 110, 16);
		panel_1.add(lblLeftDoorsOpen);
		
		lblThermostat = new JLabel("Thermostat");
		lblThermostat.setHorizontalAlignment(SwingConstants.CENTER);
		lblThermostat.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		lblThermostat.setBounds(621, 6, 107, 16);
		panel_1.add(lblThermostat);
		
		slider_desTherm = new JSlider();
		slider_desTherm.setMinorTickSpacing(1);
		slider_desTherm.setMinimum(50);
		slider_desTherm.setMaximum(90);
		slider_desTherm.setMajorTickSpacing(10);
		slider_desTherm.setSnapToTicks(true);
		slider_desTherm.setPaintTicks(true);
		slider_desTherm.setPaintLabels(true);
		slider_desTherm.setBounds(584, 23, 190, 40);
		panel_1.add(slider_desTherm);
		
		radioButton_leftDoor = new JRadioButton("");
		radioButton_leftDoor.setHorizontalAlignment(SwingConstants.CENTER);
		radioButton_leftDoor.setBounds(93, 23, 110, 23);
		panel_1.add(radioButton_leftDoor);
		
		radioButton_lights = new JRadioButton("");
		radioButton_lights.setHorizontalAlignment(SwingConstants.CENTER);
		radioButton_lights.setBounds(6, 86, 73, 23);
		panel_1.add(radioButton_lights);
		
		radioButton_station = new JRadioButton("");
		radioButton_station.setEnabled(false);
		radioButton_station.setHorizontalAlignment(SwingConstants.CENTER);
		radioButton_station.setBounds(6, 23, 73, 23);
		panel_1.add(radioButton_station);
		
		slider_actTherm = new JSlider();
		slider_actTherm.setEnabled(false);
		slider_actTherm.setMinorTickSpacing(1);
		slider_actTherm.setMinimum(50);
		slider_actTherm.setSnapToTicks(true);
		slider_actTherm.setPaintTicks(true);
		slider_actTherm.setPaintLabels(true);
		slider_actTherm.setMaximum(90);
		slider_actTherm.setMajorTickSpacing(10);
		slider_actTherm.setBounds(584, 69, 190, 40);
		panel_1.add(slider_actTherm);
		
		JLabel lblRightDoorsOpen = new JLabel("Right Doors Open");
		lblRightDoorsOpen.setHorizontalAlignment(SwingConstants.CENTER);
		lblRightDoorsOpen.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		lblRightDoorsOpen.setBounds(91, 69, 112, 16);
		panel_1.add(lblRightDoorsOpen);
		
		radioButton_rightDoor = new JRadioButton("");
		radioButton_rightDoor.setHorizontalAlignment(SwingConstants.CENTER);
		radioButton_rightDoor.setBounds(91, 86, 112, 23);
		panel_1.add(radioButton_rightDoor);
		
		JLabel lblDesired = new JLabel("Desired");
		lblDesired.setHorizontalAlignment(SwingConstants.CENTER);
		lblDesired.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		lblDesired.setBounds(532, 30, 52, 16);
		panel_1.add(lblDesired);
		
		JLabel lblActual = new JLabel("Actual");
		lblActual.setHorizontalAlignment(SwingConstants.CENTER);
		lblActual.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		lblActual.setBounds(543, 75, 41, 16);
		panel_1.add(lblActual);
		
		textField_ads = new JTextField();
		textField_ads.setEditable(false);
		textField_ads.setBounds(249, 23, 245, 28);
		panel_1.add(textField_ads);
		textField_ads.setColumns(10);
		
		lblAnnouncements = new JLabel("Advertisements");
		lblAnnouncements.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnouncements.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		lblAnnouncements.setBounds(308, 5, 110, 16);
		panel_1.add(lblAnnouncements);
		
		btnHorn = new JButton("HORN!!!!!");
		btnHorn.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 15));
		btnHorn.setBounds(249, 63, 245, 46);
		panel_1.add(btnHorn);
		panel.setLayout(null);
		
		slider = new JSlider();
		slider.setSnapToTicks(true);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setOrientation(SwingConstants.VERTICAL);
		slider.setMaximum(70);
		slider.setMajorTickSpacing(10);
		slider.setMinorTickSpacing(1);
		slider.setBounds(16, 106, 81, 211);
		panel.add(slider);
		
		tglbtnAutopilot = new JToggleButton("Toggle AutoPilot");
		tglbtnAutopilot.setForeground(Color.BLUE);
		tglbtnAutopilot.setBounds(318, 31, 437, 29);
		panel.add(tglbtnAutopilot);
		
		JLabel lblThrottle = new JLabel("Desired");
		lblThrottle.setHorizontalAlignment(SwingConstants.CENTER);
		lblThrottle.setFont(new Font("Modern No. 20", Font.BOLD, 20));
		lblThrottle.setBounds(16, 58, 81, 29);
		panel.add(lblThrottle);
		
		lblCurrentSpeed = new JLabel("Current Speed");
		lblCurrentSpeed.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		lblCurrentSpeed.setBounds(217, 103, 88, 16);
		panel.add(lblCurrentSpeed);
		
		lblCurrentSpeedLimit = new JLabel("Current Speed Limit");
		lblCurrentSpeedLimit.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		lblCurrentSpeedLimit.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCurrentSpeedLimit.setBounds(164, 178, 142, 16);
		panel.add(lblCurrentSpeedLimit);
		
		lblCurrentAuthority = new JLabel("Current Authority");
		lblCurrentAuthority.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		lblCurrentAuthority.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCurrentAuthority.setBounds(174, 213, 133, 16);
		panel.add(lblCurrentAuthority);
		
		lblErrors = new JLabel("ERRORS");
		lblErrors.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		lblErrors.setHorizontalAlignment(SwingConstants.RIGHT);
		lblErrors.setBounds(174, 283, 132, 16);
		panel.add(lblErrors);
		
		lblBeaconMessage = new JLabel("Beacon Message");
		lblBeaconMessage.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		lblBeaconMessage.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBeaconMessage.setBounds(164, 248, 132, 16);
		panel.add(lblBeaconMessage);
		
		lblTrainControllerV = new JLabel("Train Controller V4:");
		lblTrainControllerV.setFont(new Font("Modern No. 20", Font.BOLD, 30));
		lblTrainControllerV.setHorizontalAlignment(SwingConstants.LEFT);
		lblTrainControllerV.setBounds(16, 10, 254, 24);
		panel.add(lblTrainControllerV);
		
		lblNextStation = new JLabel("NEXT STATION");
		lblNextStation.setHorizontalAlignment(SwingConstants.CENTER);
		lblNextStation.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		lblNextStation.setBounds(536, 64, 219, 16);
		panel.add(lblNextStation);
		
		toggle_service = new JToggleButton("SERVICE BRAKE");
		toggle_service.setForeground(Color.GREEN);
		//toggle_service.setBackground(Color.BLACK);
		toggle_service.setBounds(536, 122, 219, 80);
		panel.add(toggle_service);
		
		label_7 = new JLabel("16");
		label_7.setBackground(Color.BLACK);
		label_7.setBounds(584, -14, 25, 16);
		panel.add(label_7);
		
		toggle_emergency = new JToggleButton("EMERGENCY BRAKE");
		toggle_emergency.setForeground(Color.RED);
		//toggle_emergency.setBackground(Color.BLACK);
		toggle_emergency.setBounds(536, 205, 219, 80);
		panel.add(toggle_emergency);
		
		slider_2 = new JSlider();
		slider_2.setEnabled(false);
		slider_2.setSnapToTicks(true);
		slider_2.setPaintTicks(true);
		slider_2.setPaintLabels(true);
		slider_2.setOrientation(SwingConstants.VERTICAL);
		slider_2.setMaximum(70);
		slider_2.setMajorTickSpacing(10);
		slider_2.setMinorTickSpacing(1);
		slider_2.setBounds(101, 106, 81, 211);
		panel.add(slider_2);
		
		JLabel lblSpeed = new JLabel("Speed");
		lblSpeed.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpeed.setFont(new Font("Modern No. 20", Font.BOLD, 20));
		lblSpeed.setBounds(99, 81, 71, 35);
		panel.add(lblSpeed);
		
		JLabel lblCommandedPower = new JLabel("Commanded Power");
		lblCommandedPower.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		lblCommandedPower.setBounds(184, 70, 121, 16);
		panel.add(lblCommandedPower);
		
		lblSpeed_1 = new JLabel("Speed");
		lblSpeed_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpeed_1.setFont(new Font("Modern No. 20", Font.BOLD, 20));
		lblSpeed_1.setBounds(16, 81, 81, 29);
		panel.add(lblSpeed_1);
		
		lblAcutal = new JLabel("Acutal");
		lblAcutal.setHorizontalAlignment(SwingConstants.CENTER);
		lblAcutal.setFont(new Font("Modern No. 20", Font.BOLD, 20));
		lblAcutal.setBounds(101, 58, 65, 29);
		panel.add(lblAcutal);
		
		lblCommandedSpeed = new JLabel("Desired Speed");
		lblCommandedSpeed.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCommandedSpeed.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		lblCommandedSpeed.setBounds(163, 142, 142, 16);
		panel.add(lblCommandedSpeed);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(318, 63, 185, 28);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(318, 96, 185, 28);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(318, 135, 185, 28);
		panel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(318, 171, 185, 28);
		panel.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(318, 207, 185, 28);
		panel.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBounds(318, 241, 185, 28);
		panel.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBounds(318, 276, 185, 28);
		panel.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setEditable(false);
		textField_7.setColumns(10);
		textField_7.setBounds(536, 86, 219, 28);
		panel.add(textField_7);
		
		lblInManualMode = new JLabel("Manual Mode in ON");
		lblInManualMode.setHorizontalAlignment(SwingConstants.CENTER);
		lblInManualMode.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		lblInManualMode.setBounds(455, 18, 162, 16);
		panel.add(lblInManualMode);
		
		lblNoBraking = new JLabel("");
		lblNoBraking.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoBraking.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
			lblNoBraking.setBounds(524, 288, 247, 29);
			panel.add(lblNoBraking);
			
			lblTrainID = new JLabel("Train ID:");
			lblTrainID.setBounds(26, 36, 61, 16);
			panel.add(lblTrainID);
			
			lblID = new JLabel("");
			lblID.setBounds(83, 36, 61, 16);
			panel.add(lblID);
			frame.getContentPane().setLayout(groupLayout);
	}
}

