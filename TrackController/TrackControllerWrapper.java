package trackControllerFinal;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import javax.swing.JComboBox;

public class TrackControllerWrapper {

	Track track;
	public boolean running = false;
	
	private JFrame frmTrackController;
	private JTextField plcProgramPathTextbox;
	private JSlider changeViewSlider;
	private JList<String> blocksListbox;
	private JList<String> switchListbox;
	private JList<String> railwayListbox;
	private JButton breakBlockButton;
	private JButton maintBlockButton;
	private JButton switchButton;
	private JButton railwayButton;
	private JComboBox<Integer> tcComboBox;
	private final DefaultListModel<String> blockModel = new DefaultListModel<String>();
	private final DefaultListModel<String> railwayModel = new DefaultListModel<String>();
	private final DefaultListModel<String> switchModel = new DefaultListModel<String>();

	private ArrayList<TrackController> greenLineTrackControllers;
	private ArrayList<TrackController> redLineTrackControllers;

	// block assignments to the track controllers, at most 50 blocks per TC with a 2 block overlap between controllers
	private final int greenTc1Blocks[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,
			27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,147,148,148,149,150};
	private final int greenTc2Blocks[] = {44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,151,152,63,64,65,	
			66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,101,102,86,87,98,99};
	private final int greenTc3Blocks[] = {84,85,86,87,88,77,78,75,76,101,89,90,91,92,93,94,95,96,97,98,99,100,102,103,	
			104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123,	
			124,125,126,127,128,129};
	private final int greenTc4Blocks[] = {128,129,130,131,132,133,134,135,136,137,138,139,140,141,142,143,144,145,146,147,
			148,149,150,27,28,29,30};

	private final int redTc1Blocks[] = {1};
	private final int redTc2Blocks[] = {2};

	public boolean plcLoaded = true;
	
	// defined block status values
	public final byte BLOCK_OPEN = 0;
	public final byte BLOCK_OCCUPIED = 1;
	public final byte BLOCK_MAINT = 2;
	public final byte RAIL_BROKEN = 3;
	public final byte SWITCH_BROKEN = 4;
	public final byte RAILWAY_BROKEN = 5;
	public final byte SIGNAL_BROKEN = 6;

	/**
	 * Create the application.
	 */
	public TrackControllerWrapper(Track track) {
		this.track = track;
		
		initialize();
		loadTrackModel();
		populateDefaultDisplay();

		// after loading data, make GUI visible
		running = true;
		this.frmTrackController.setVisible(true);
	}

	/**
	 * Function to load all blocks and divide them up into a certain number of track controllers
	 */
	private void loadTrackModel() {
		// load in all blocks from track model and 50 blocks per track controller
		// track controllers will have an overlap of 2 blocks at the beginning and the end
		greenLineTrackControllers = new ArrayList<TrackController>();
		redLineTrackControllers = new ArrayList<TrackController>();

		greenLineTrackControllers.add(new TrackController("green", 0, greenTc1Blocks));
		greenLineTrackControllers.add(new TrackController("green", 1, greenTc2Blocks));
		greenLineTrackControllers.add(new TrackController("green", 2, greenTc3Blocks));
		greenLineTrackControllers.add(new TrackController("green", 3, greenTc4Blocks));

		redLineTrackControllers.add(new TrackController("red", 0, redTc1Blocks));
		redLineTrackControllers.add(new TrackController("red", 1, redTc2Blocks));
		
		// TODO: loaded plc through GUI
		// set default plc program for track controllers - only for demo
		greenLineTrackControllers.get(0).initPLC("testPLC.plc");
		greenLineTrackControllers.get(1).initPLC("testPLC.plc");
		greenLineTrackControllers.get(2).initPLC("testPLC.plc");
		greenLineTrackControllers.get(3).initPLC("testPLC.plc");
		
		redLineTrackControllers.get(0).initPLC("testPLC.plc");
		redLineTrackControllers.get(1).initPLC("testPLC.plc");
	}

	/**
	 * Function to set initial display before showing window
	 */
	private void populateDefaultDisplay() {
		// add track controller IDs to combo box - default set to green line view
		updateComboBox();

		// default controller to view is ID = 0, populate list boxes
		updateListBoxes();
		
		// update plc program file
		plcProgramPathTextbox.setText(greenLineTrackControllers.get(0).plcFile);
	}
	
	/**
	 * Function to update the track controller id combo box
	 */
	private void updateComboBox() {
		try {
			tcComboBox.removeAllItems();
			if(changeViewSlider.getValue() == 0) {
				/* View is set to Red Line */
				tcComboBox.addItem(0);
				tcComboBox.addItem(1);
			}
			else {
				/* View is set to Green Line */
				tcComboBox.addItem(0);
				tcComboBox.addItem(1);
				tcComboBox.addItem(2);
				tcComboBox.addItem(3);
			}
		}
		catch(Exception e) {
			//e.printStackTrace();
		}
	}

	/**
	 * Function to populate the block, switch, and railway list box
	 */
	private void updateListBoxes() {
		// clear existing data in list boxes
		blockModel.clear();
		//railwayModel.clear();
		//switchModel.clear();

		try {
			if(changeViewSlider.getValue() == 0) {
				/* View is set to Red Line */
				if(redTc1Blocks != null && redTc2Blocks != null){
					if(tcComboBox.getSelectedIndex() == 0) {
						// update plc program file
						plcProgramPathTextbox.setText(redLineTrackControllers.get(0).plcFile);
						
						for(int id : redTc1Blocks) {
							// Add block to list box
							blockModel.addElement(Integer.toString(id));
							
							// TODO: check if block contains railway and/or switch to add to respective list box
						}
					}
					else if(tcComboBox.getSelectedIndex() == 1) {
						// update plc program file
						plcProgramPathTextbox.setText(redLineTrackControllers.get(1).plcFile);
						
						for(int id : redTc2Blocks) {
							// Add block to list box
							blockModel.addElement(Integer.toString(id));
							
							// TODO: check if block contains railway and/or switch to add to respective list box
						}
					}
				}
			}
			else {
				/* View is set to Green Line */
				if(greenTc1Blocks != null && greenTc2Blocks != null && greenTc3Blocks != null && greenTc4Blocks != null){
					if(tcComboBox.getSelectedIndex() == 0) {
						// update plc program file
						plcProgramPathTextbox.setText(greenLineTrackControllers.get(0).plcFile);
						
						for(int id : greenTc1Blocks) {
							// Add block to list box
							blockModel.addElement(Integer.toString(id));
							
							// TODO: check if block contains railway and/or switch to add to respective list box
						}
					}
					else if(tcComboBox.getSelectedIndex() == 1) {
						// update plc program file
						plcProgramPathTextbox.setText(greenLineTrackControllers.get(1).plcFile);
						
						for(int id : greenTc2Blocks) {
							// Add block to list box
							blockModel.addElement(Integer.toString(id));
							
							// TODO: check if block contains railway and/or switch to add to respective list box
						}
					}
					else if(tcComboBox.getSelectedIndex() == 2) {
						// update plc program file
						plcProgramPathTextbox.setText(greenLineTrackControllers.get(2).plcFile);
						
						for(int id : greenTc3Blocks) {
							// Add block to list box
							blockModel.addElement(Integer.toString(id));
							
							// TODO: check if block contains railway and/or switch to add to respective list box
						}					
					}
					else if(tcComboBox.getSelectedIndex() == 3) {
						// update plc program file
						plcProgramPathTextbox.setText(greenLineTrackControllers.get(3).plcFile);
						
						for(int id : greenTc4Blocks) {
							// Add block to list box
							blockModel.addElement(Integer.toString(id));
							
							// TODO: check if block contains railway and/or switch to add to respective list box
						}
					}
				}
			}
		}
		catch(Exception e){
			//e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTrackController = new JFrame();
		frmTrackController.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Jeff\\Documents\\College\\Senior Year\\COE 1186\\Train_Controller\\train_pic.png"));
		frmTrackController.setTitle("The Little Engine That Code - Track Controller");
		frmTrackController.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTrackController.setPreferredSize(new Dimension(676, 630));
		frmTrackController.pack();
		frmTrackController.setLocationRelativeTo(null);
		frmTrackController.addWindowListener(new WindowAdapter() {
		    public void WindowClosing(WindowEvent e) {
		        running = false;
		        frmTrackController.dispose();
		    }
		});
		frmTrackController.setVisible(true);

		changeViewSlider = new JSlider();
		changeViewSlider.setBounds(307, 11, 28, 23);
		changeViewSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				updateComboBox();
				// update boxes
				updateListBoxes();
			}
		});
		frmTrackController.getContentPane().setLayout(null);
		changeViewSlider.setValue(1);
		changeViewSlider.setMaximum(1);
		frmTrackController.getContentPane().add(changeViewSlider);

		JLabel redLineLabel = new JLabel("Red Line");
		redLineLabel.setBounds(225, 11, 101, 23);
		redLineLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frmTrackController.getContentPane().add(redLineLabel);

		JLabel greenLineLabel = new JLabel("Green Line");
		greenLineLabel.setBounds(345, 11, 134, 23);
		greenLineLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frmTrackController.getContentPane().add(greenLineLabel);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 39, 640, 542);
		frmTrackController.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel comboBoxLabel = new JLabel("Track Controller ID");
		comboBoxLabel.setBounds(207, 11, 129, 14);
		panel.add(comboBoxLabel);

		tcComboBox = new JComboBox<Integer>();
		tcComboBox.setBounds(322, 8, 81, 20);
		tcComboBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	// update boxes
				updateListBoxes();
		    }
		});
		panel.add(tcComboBox);

		JLabel plcProgramLabel = new JLabel("PLC Program");
		plcProgramLabel.setBounds(10, 45, 109, 14);
		panel.add(plcProgramLabel);

		plcProgramPathTextbox = new JTextField();
		plcProgramPathTextbox.setBounds(94, 42, 340, 20);
		panel.add(plcProgramPathTextbox);
		plcProgramPathTextbox.setEditable(false);
		plcProgramPathTextbox.setText("C:/");
		plcProgramPathTextbox.setColumns(10);

		JButton plcProgramBrowseButton = new JButton("Browse");
		plcProgramBrowseButton.setBounds(444, 41, 89, 23);
		panel.add(plcProgramBrowseButton);

		JButton plcProgramLoadButton = new JButton("Load");
		plcProgramLoadButton.setBounds(540, 41, 89, 23);
		plcProgramLoadButton.setEnabled(false);
		panel.add(plcProgramLoadButton);
		plcProgramLoadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loadPLCProgram();
			}
		});

		plcProgramBrowseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser= new JFileChooser(plcProgramPathTextbox.getText());
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"PLC File", "plc");
				fileChooser.setFileFilter(filter);
				int returnVal = fileChooser.showOpenDialog(frmTrackController);

				if(returnVal == JFileChooser.APPROVE_OPTION) {
					plcProgramPathTextbox.setText(fileChooser.getSelectedFile().getAbsolutePath());
					if(tcComboBox.getSelectedIndex() != -1) {
						plcProgramLoadButton.setEnabled(true);
					}
				}
			}
		});

		tcComboBox.addActionListener (new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				if(tcComboBox.getSelectedIndex() == -1) {
					plcProgramLoadButton.setEnabled(false);
				}
				else {
					plcProgramLoadButton.setEnabled(true);
				}
			}
		});

		JLabel blocksLabel = new JLabel("Blocks");
		blocksLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		blocksLabel.setBounds(10, 92, 46, 14);
		panel.add(blocksLabel);

		JLabel lblSectionNumberOccupied = new JLabel("Section      Number       Occupied      Broken      Maintanence   ");
		lblSectionNumberOccupied.setBounds(10, 124, 340, 14);
		panel.add(lblSectionNumberOccupied);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 149, 340, 335);
		panel.add(scrollPane);

		blocksListbox = new JList<String>(blockModel);
		scrollPane.setViewportView(blocksListbox);
		blocksListbox.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		blocksListbox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		blocksListbox.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if(blocksListbox.isSelectionEmpty() || plcLoaded == false) {
					breakBlockButton.setEnabled(false);
					maintBlockButton.setEnabled(false);
				}
				else {
					breakBlockButton.setEnabled(true);
					maintBlockButton.setEnabled(true);
				}
			}
		});

		breakBlockButton = new JButton("Break");
		breakBlockButton.setEnabled(false);
		breakBlockButton.setBounds(84, 495, 89, 36);
		breakBlockButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				breakBlock();
			}
		});
		panel.add(breakBlockButton);

		maintBlockButton = new JButton("Maintanence");
		maintBlockButton.setEnabled(false);
		maintBlockButton.setBounds(178, 495, 109, 36);
		maintBlockButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				maintBlock();
			}
		});
		panel.add(maintBlockButton);

		JLabel railwayLabel = new JLabel("Railways");
		railwayLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		railwayLabel.setBounds(366, 92, 146, 14);
		panel.add(railwayLabel);

		JLabel lblBlockStatus = new JLabel("Block              Status");
		lblBlockStatus.setBounds(360, 124, 170, 14);
		panel.add(lblBlockStatus);

		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(366, 370, 229, 114);
		panel.add(scrollPane2);

		railwayListbox = new JList<String>(railwayModel);
		scrollPane2.setViewportView(railwayListbox);
		railwayListbox.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		railwayListbox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		railwayListbox.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if(railwayListbox.isSelectionEmpty() || plcLoaded == false) {
					railwayButton.setEnabled(false);
				}
				else {
					railwayButton.setEnabled(true);
				}
			}
		});

		railwayButton = new JButton("Activate Crossing");
		railwayButton.setEnabled(false);
		railwayButton.setBounds(401, 279, 160, 36);
		railwayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				railwayCrossing();
			}
		});
		panel.add(railwayButton);

		JLabel switchLabel = new JLabel("Switches");
		switchLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		switchLabel.setBounds(366, 326, 146, 14);
		panel.add(switchLabel);

		JLabel lblNewLabel_1 = new JLabel("Block      Switch    Status");
		lblNewLabel_1.setBounds(366, 351, 160, 14);
		panel.add(lblNewLabel_1);

		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(366, 149, 229, 119);
		panel.add(scrollPane1);

		switchListbox = new JList<String>(switchModel);
		scrollPane1.setViewportView(switchListbox);
		switchListbox.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		switchListbox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		switchListbox.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if(switchListbox.isSelectionEmpty() || plcLoaded == false) {
					switchButton.setEnabled(false);
				}
				else {
					switchButton.setEnabled(true);
				}
			}
		});

		switchButton = new JButton("Switch Track");
		switchButton.setEnabled(false);
		switchButton.setBounds(424, 495, 109, 36);
		switchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchBlock();
			}
		});
		panel.add(switchButton);
	}

	/**
	 * Function to load a selected PLC program into the track controller
	 */
	private void loadPLCProgram() {
		if(changeViewSlider.getValue() == 0) {
			redLineTrackControllers.get((int) tcComboBox.getSelectedItem()).initPLC(plcProgramPathTextbox.getText());
		}
		else {
			greenLineTrackControllers.get((int) tcComboBox.getSelectedItem()).initPLC(plcProgramPathTextbox.getText());
		}
	}

	/**
	 * Function for the CTC to send a train proceed message to the track controller to verified
	 * @param msg message containing the track line, targeted blocks, speed and authority
	 * @return boolean value if message could be verified or not
	 */
	public boolean newProceedMsg(String msg) {
		String delimeter = ",";
		String[] msgContents;
		
		TrackController trackController;

		msgContents = msg.split(delimeter);
		String line = msgContents[0].toLowerCase();
		int currentBlock = Integer.parseInt(msgContents[1]);
		int nextBlock = Integer.parseInt(msgContents[2]);
		int destinationBlock = Integer.parseInt(msgContents[3]);
		int suggestedSpeed = Integer.parseInt(msgContents[4]);
		int suggestedAuthority = Integer.parseInt(msgContents[5]);
		
		boolean returnResult = true;

		// based on the track line and current block, decide which track controller to request
		if(line.equals("red")) {
			// track controller part of the red line
			if(Arrays.binarySearch(redTc1Blocks, currentBlock) >= 0 &&
					Arrays.binarySearch(redTc1Blocks, nextBlock) >= 0 &&
					Arrays.binarySearch(redTc1Blocks, destinationBlock) >= 0)  {
				// targeted blocks will be in track controller 1
				trackController = redLineTrackControllers.get(0);
			}
			else if(Arrays.binarySearch(redTc2Blocks, currentBlock) >= 0 &&
					Arrays.binarySearch(redTc2Blocks, nextBlock) >= 0 &&
					Arrays.binarySearch(redTc2Blocks, destinationBlock) >= 0) {
				// targeted blocks will be in track controller 2
				trackController = redLineTrackControllers.get(1);
				
			}
			else {
				trackController = redLineTrackControllers.get(1);
			}
			
			// pass suggestion to PLC for it to verify
			Block currBlock = track.getBlock(currentBlock,"red");
			Block nxtBlock = track.getBlock(nextBlock,"red");
			Block destBlock = track.getBlock(destinationBlock,"red");
			returnResult = trackController.plc.verifyProceed(nxtBlock, destBlock);
			
			if(returnResult) {
				// check if track needs to switch the switch to get to the destination block
				if(nxtBlock.isSwitch()) {
					returnResult = trackController.plc.verifyToggleSwitch(nxtBlock, destBlock);
					
					if(returnResult) {
						// toggle switch for the next block
						//if(nxtBlock.getNext().getBlockNumber() != destBlock.getBlockNumber()) {
							// need to toggle switch
							//track.toggleSwitch("red", nextBlock);
						//}
						
						// send block suggested speed and authority
						track.commandAuthority("red", suggestedAuthority, nextBlock);
						track.commandSpeed("red", suggestedSpeed, nextBlock);
					}
					else {
						// send block speed and authority of 0
						track.commandAuthority("red", 0, nextBlock);
						track.commandSpeed("red", 0, nextBlock);
					}
				}
				else {
					// send block suggested speed and authority
					track.commandAuthority("red", suggestedAuthority, nextBlock);
					track.commandSpeed("red", suggestedSpeed, nextBlock);
				}
				
			}
			else {
				// send block speed and authority of 0
				track.commandAuthority("red", 0, nextBlock);
				track.commandSpeed("red", 0, nextBlock);
			}
		}
		else {
			// track controller part of the green line
			if(Arrays.binarySearch(greenTc1Blocks, currentBlock) >= 0 &&
					Arrays.binarySearch(greenTc1Blocks, nextBlock) >= 0 &&
					Arrays.binarySearch(greenTc1Blocks, destinationBlock) >= 0) {
				// targeted blocks will be in track controller 1
				trackController = greenLineTrackControllers.get(0);
			}
			else if(Arrays.binarySearch(greenTc2Blocks, currentBlock) >= 0 &&
					Arrays.binarySearch(greenTc2Blocks, nextBlock) >= 0 &&
					Arrays.binarySearch(greenTc2Blocks, destinationBlock) >= 0)  {
				// targeted blocks will be in track controller 2
				trackController = greenLineTrackControllers.get(1);
			}
			else if(Arrays.binarySearch(greenTc3Blocks, currentBlock) >= 0 &&
					Arrays.binarySearch(greenTc3Blocks, nextBlock) >= 0 &&
					Arrays.binarySearch(greenTc3Blocks, destinationBlock) >= 0)  {
				// targeted blocks will be in track controller 3
				trackController = greenLineTrackControllers.get(2);
			}
			else if(Arrays.binarySearch(greenTc4Blocks, currentBlock) >= 0 &&
					Arrays.binarySearch(greenTc4Blocks, nextBlock) >= 0 &&
					Arrays.binarySearch(greenTc4Blocks, destinationBlock) >= 0)  {
				// targeted blocks will be in track controller 4
				trackController = greenLineTrackControllers.get(3);
			}
			else {
				trackController = greenLineTrackControllers.get(0);
			}
			
			// pass suggestion to PLC for it to verify
			Block currBlock = track.getBlock(currentBlock,"green");
			Block nxtBlock = track.getBlock(nextBlock,"green");
			Block destBlock = track.getBlock(destinationBlock,"green");
			returnResult = trackController.plc.verifyProceed(nxtBlock, destBlock);
			
			if(returnResult) {
				// check if track needs to switch the switch to get to the destination block
				if(nxtBlock.isSwitch()) {
					returnResult = trackController.plc.verifyToggleSwitch(nxtBlock, destBlock);
					
					if(returnResult) {
						// toggle switch for the next block
						//if(nxtBlock.getNext().getBlockNumber() != destBlock.getBlockNumber()) {
							// need to toggle switch
							//track.toggleSwitch("green", nextBlock);
						//}
						
						// send block suggested speed and authority
						track.commandAuthority("green", suggestedAuthority, nextBlock);
						track.commandSpeed("green", suggestedSpeed, nextBlock);
					}
					else {
						// send block speed and authority of 0
						track.commandAuthority("green", 0, nextBlock);
						track.commandSpeed("green", 0, nextBlock);
					}
				}
				else {
					// next block is not a switch so just pass suggested speed and authority
					track.commandAuthority("green", suggestedAuthority, nextBlock);
					track.commandSpeed("green", suggestedSpeed, nextBlock);
				}
				
			}
			else {
				// send block speed and authority of 0
				track.commandAuthority("green", 0, nextBlock);
				track.commandSpeed("green", 0, nextBlock);
			}
		}

		return returnResult;
	}

	/**
	 * Function for the CTC to open/close block for maintenance
	 * @param msg message containing track line, block and open/close status
	 * @return
	 */
	public boolean newMaintMsg(String msg) {
		String delimeter = ",";
		String[] msgContents;

		msgContents = msg.split(delimeter);
		String line = msgContents[0].toLowerCase();
		int currentBlock = Integer.parseInt(msgContents[1]);
		String maintStatus = msgContents[2].toLowerCase();

		// based on the track line and current block, decide which track controller to request
		if(line.equals("red")) {
			// track controller part of the red line

		}
		else {
			// track controller part of the green line

		}

		return true;
	}
	
	/**
	 * Tell track to flash beacon at selected station
	 * @param line	track line station is on
	 * @param blockID	ID of block the station is on
	 * @return
	 */
	public boolean showBeacon(String line, int blockID) {
		//track.showBeacon(blockID, line);
		
		return true;
	}

	/**
	 * Function for the CTC to gather status of the blocks
	 * @param blockID requested block's status
	 * @return defined value for block status
	 */
	public byte getBlockStatus(String line, int blockID) {
		// based on the track line and the requested block, select the appropriate track controller
		byte returnStatus = BLOCK_OPEN;
		
		Block requestedBlock = track.getBlock(blockID,line);
		if(requestedBlock.isBlockOccupied()) {
			returnStatus = BLOCK_OCCUPIED;
		}
		
		return returnStatus;
	}

	/**
	 * Function for user to break/fix a selected block on the track
	 */
	private void breakBlock() {
		int selectedTC = 0;
		int selectedBlock = 0;

		selectedTC = (int) tcComboBox.getSelectedItem();
		selectedBlock = Integer.parseInt(blocksListbox.getSelectedValue());

		if(changeViewSlider.getValue() == 0) {

		}
		else {
			track.breakBlock(selectedBlock);
		}
	}

	/**
	 * Function for user to open/close a block for maintenance
	 */
	private void maintBlock() {
		int selectedTC = 0;
		int selectedBlock = 0;

		selectedTC = (int) tcComboBox.getSelectedItem();
		selectedBlock = blocksListbox.getSelectedIndex();

		if(changeViewSlider.getValue() == 0) {
			redLineTrackControllers.get(selectedTC).setMaintenace(selectedBlock);
		}
		else {
			greenLineTrackControllers.get(selectedTC).setMaintenace(selectedBlock);
		}
	}

	/**
	 * Function for the user to manually switch the track
	 */
	private void switchBlock() {
		int selectedTC = 0;
		int selectedBlock = 0;

		selectedTC = (int) tcComboBox.getSelectedItem();
		selectedBlock = blocksListbox.getSelectedIndex();

		if(changeViewSlider.getValue() == 0) {

		}
		else {

		}
	}

	/**
	 * Function for the user to activate/terminate a railway crossing
	 */
	private void railwayCrossing() {
		int selectedTC = 0;
		int selectedBlock = 0;

		selectedTC = (int) tcComboBox.getSelectedItem();
		selectedBlock = blocksListbox.getSelectedIndex();

		if(changeViewSlider.getValue() == 0) {

		}
		else {

		}
	}	
}
