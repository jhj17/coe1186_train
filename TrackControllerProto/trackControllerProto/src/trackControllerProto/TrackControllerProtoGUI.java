package trackControllerProto;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.JSlider;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.SystemColor;
import java.io.File;
import java.io.IOException;

import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.SwingConstants;

public class TrackControllerProtoGUI {

	private JFrame frmTrackController;
	private JTextField plcProgramPathTextbox;
	private JButton switchButton;
	private JTextField setSpeedTextbox;
	private JTextField setAuthorityTextbox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrackControllerProtoGUI window = new TrackControllerProtoGUI();
					window.frmTrackController.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TrackControllerProtoGUI() {
		/* Initialize GUI */
		initialize();
		
		/* Populate list boxes with attributes of the track */
		populateTrack();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTrackController = new JFrame();
		frmTrackController.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Jeff\\Documents\\College\\Senior Year\\COE 1186\\Train_Controller\\train_pic.png"));
		frmTrackController.setTitle("The Little Engine That Code - Track Controller");
		frmTrackController.getContentPane().setLayout(null);
		frmTrackController.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTrackController.setPreferredSize(new Dimension(802, 630));
		frmTrackController.pack();
		frmTrackController.setLocationRelativeTo(null);
		frmTrackController.setVisible(true);
		
		JButton startSystemButton = new JButton("START");
		startSystemButton.setBounds(236, 525, 134, 56);
		startSystemButton.setBackground(Color.GREEN);
		startSystemButton.setForeground(Color.BLACK);
		startSystemButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		startSystemButton.setEnabled(false);
		startSystemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		frmTrackController.getContentPane().add(startSystemButton);
		
		JButton stopSystemButton = new JButton("STOP");
		stopSystemButton.setBounds(383, 525, 126, 56);
		stopSystemButton.setBackground(Color.RED);
		stopSystemButton.setForeground(Color.BLACK);
		stopSystemButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		stopSystemButton.setEnabled(false);
		frmTrackController.getContentPane().add(stopSystemButton);
		
		plcProgramPathTextbox = new JTextField();
		plcProgramPathTextbox.setBounds(90, 11, 384, 20);
		plcProgramPathTextbox.setEditable(false);
		plcProgramPathTextbox.setText("C:/");
		frmTrackController.getContentPane().add(plcProgramPathTextbox);
		plcProgramPathTextbox.setColumns(10);
		
		JButton plcProgramBrowseButton = new JButton("Browse");
		plcProgramBrowseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser= new JFileChooser(plcProgramPathTextbox.getText());
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
				        "PLC File", "plc");
				fileChooser.setFileFilter(filter);
			    int returnVal = fileChooser.showOpenDialog(frmTrackController);
			    
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	plcProgramPathTextbox.setText(fileChooser.getSelectedFile().getAbsolutePath());
			     }
			}
		});
		plcProgramBrowseButton.setBounds(484, 10, 89, 23);
		frmTrackController.getContentPane().add(plcProgramBrowseButton);
		
		JButton plcProgramLoadButton = new JButton("Load");
		plcProgramLoadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					File file = new File(plcProgramPathTextbox.getText());
					String filename = file.getName();
					String extension = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
					
					if(file.exists() && extension.equals("plc")){
						JOptionPane.showMessageDialog(frmTrackController, "File", "File Good", JOptionPane.INFORMATION_MESSAGE);
						
						//TODO: load PLC program into PLC class
						
						/* Enable start system button */
						startSystemButton.setEnabled(true);
					}
					else{
						/* Show error message */
						JOptionPane.showMessageDialog(frmTrackController, "File Invalid!", "Error", JOptionPane.ERROR_MESSAGE);
					}
			}
		});
		plcProgramLoadButton.setBounds(583, 10, 89, 23);
		frmTrackController.getContentPane().add(plcProgramLoadButton);
		
		JLabel plcProgramLabel = new JLabel("PLC Program");
		plcProgramLabel.setBounds(10, 14, 109, 14);
		frmTrackController.getContentPane().add(plcProgramLabel);
		
		JSlider changeViewSlider = new JSlider();
		changeViewSlider.setValue(0);
		changeViewSlider.setMaximum(1);
		changeViewSlider.setBounds(345, 42, 28, 23);
		frmTrackController.getContentPane().add(changeViewSlider);
		
		JLabel redLineLabel = new JLabel("Red Line");
		redLineLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		redLineLabel.setBounds(269, 42, 101, 23);
		frmTrackController.getContentPane().add(redLineLabel);
		
		JLabel greenLineLabel = new JLabel("Green Line");
		greenLineLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		greenLineLabel.setBounds(383, 42, 134, 23);
		frmTrackController.getContentPane().add(greenLineLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 74, 360, 440);
		frmTrackController.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel blocksLabel = new JLabel("Blocks");
		blocksLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		blocksLabel.setBounds(154, 11, 109, 14);
		panel.add(blocksLabel);
		
		JList<String> blocksListbox = new JList<String>();
		blocksListbox.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		blocksListbox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		blocksListbox.setBounds(10, 31, 340, 351);
		panel.add(blocksListbox);
		
		JButton breakBlockButton = new JButton("Break");
		breakBlockButton.setBounds(82, 393, 89, 36);
		panel.add(breakBlockButton);
		
		JButton maintBlockButton = new JButton("Maintanence");
		maintBlockButton.setBounds(173, 393, 109, 36);
		panel.add(maintBlockButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(SystemColor.control);
		panel_1.setBounds(383, 76, 190, 197);
		frmTrackController.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Switches");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(65, 11, 80, 14);
		panel_1.add(lblNewLabel);
		
		JList<String> switchListbox = new JList<String>();
		switchListbox.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		switchListbox.setBounds(10, 34, 170, 117);
		panel_1.add(switchListbox);
		
		switchButton = new JButton("Switch Track");
		switchButton.setBounds(31, 162, 127, 23);
		panel_1.add(switchButton);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBackground(SystemColor.control);
		panel_2.setBounds(583, 76, 194, 197);
		frmTrackController.getContentPane().add(panel_2);
		
		JLabel lblRailways = new JLabel("Railways");
		lblRailways.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRailways.setBounds(58, 11, 80, 14);
		panel_2.add(lblRailways);
		
		JList<String> railwayListbox = new JList<String>();
		railwayListbox.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		railwayListbox.setBounds(10, 31, 170, 122);
		panel_2.add(railwayListbox);
		
		JButton railwayButton = new JButton("Activate Crossing");
		railwayButton.setBounds(31, 163, 134, 23);
		panel_2.add(railwayButton);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(383, 284, 394, 230);
		frmTrackController.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JLabel trainLabel = new JLabel("Trains");
		trainLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		trainLabel.setBounds(161, 11, 46, 14);
		panel_3.add(trainLabel);
		
		JList<String> trainListbox = new JList<String>();
		trainListbox.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		trainListbox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		trainListbox.setBounds(10, 31, 374, 118);
		panel_3.add(trainListbox);
		
		JButton setSpeedButton = new JButton("Set Speed");
		setSpeedButton.setBounds(84, 196, 99, 23);
		panel_3.add(setSpeedButton);
		
		JButton setAuthorityButton = new JButton("Set Authority");
		setAuthorityButton.setBounds(193, 196, 118, 23);
		panel_3.add(setAuthorityButton);
		
		setSpeedTextbox = new JTextField();
		setSpeedTextbox.setHorizontalAlignment(SwingConstants.CENTER);
		setSpeedTextbox.setBounds(119, 172, 64, 20);
		panel_3.add(setSpeedTextbox);
		setSpeedTextbox.setColumns(10);
		
		setAuthorityTextbox = new JTextField();
		setAuthorityTextbox.setHorizontalAlignment(SwingConstants.CENTER);
		setAuthorityTextbox.setBounds(193, 172, 64, 20);
		panel_3.add(setAuthorityTextbox);
		setAuthorityTextbox.setColumns(10);
		
		JLabel setSpeedLabel1 = new JLabel("Set Speed");
		setSpeedLabel1.setBounds(36, 174, 72, 17);
		panel_3.add(setSpeedLabel1);
		
		JLabel setAuthorityLabel1 = new JLabel("Set Authority");
		setAuthorityLabel1.setBounds(277, 175, 117, 14);
		panel_3.add(setAuthorityLabel1);
		
		JLabel setSpeedLabel2 = new JLabel("(km/h)");
		setSpeedLabel2.setBounds(36, 196, 58, 17);
		panel_3.add(setSpeedLabel2);
		
		JLabel setAuthorityLabel2 = new JLabel("(m)");
		setAuthorityLabel2.setBounds(321, 200, 58, 17);
		panel_3.add(setAuthorityLabel2);
	}
	
	/**
	 * This function will be called on load of the program.
	 * Function will load Excel file containing the Red and Green Line track
	 * and populate the list boxes for the attributes of the track.
	 */
	private void populateTrack(){
		/* Show waiting cursor while the track attributes load */
		frmTrackController.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		frmTrackController.setEnabled(false);
		
		//TODO: Open CSV file and parse contents to populate list boxes
		
		/* Reset cursor back to default, able to use GUI */
		frmTrackController.setCursor(Cursor.getDefaultCursor());
		frmTrackController.setEnabled(true);
	}
}
