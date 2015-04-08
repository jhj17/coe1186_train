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
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JComboBox;

public class TrackControllerWrapper {

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
	
	private LinkedList<Block> blocks;
	private ArrayList<TrackController> greenLineTrackControllers;
	private ArrayList<TrackController> redLineTrackControllers;
	
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
	public TrackControllerWrapper(LinkedList<Block> track) {
		this.blocks = track;
		
		initialize();
		loadTrackModel();
		
		// after loading data, make GUI visible
		this.frmTrackController.setVisible(true);
	}
	
	/**
	 * Function to load all blocks and divide them up into a certain number of track controllers
	 */
	private void loadTrackModel() {
		// load in all blocks from track model and number of track controllers is mod 50 blocks
		// track controllers will have an overlap of 2 blocks at the beginning and the end
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
		frmTrackController.setVisible(true);

		changeViewSlider = new JSlider();
		changeViewSlider.setBounds(307, 11, 28, 23);
		changeViewSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
					// update boxes
			}
		});
		frmTrackController.getContentPane().setLayout(null);
		changeViewSlider.setValue(0);
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
						if(blocksListbox.isSelectionEmpty()) {
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
		lblBlockStatus.setBounds(366, 351, 170, 14);
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
						if(railwayListbox.isSelectionEmpty()) {
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
		lblNewLabel_1.setBounds(366, 124, 160, 14);
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
						if(switchListbox.isSelectionEmpty()) {
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
		String delimeter = "|";
		String[] msgContents;
		
		msgContents = msg.split(delimeter);
		String line = msgContents[0];
		int currentBlock = Integer.parseInt(msgContents[1]);
		int nextBlock = Integer.parseInt(msgContents[2]);
		int destinationBlock = Integer.parseInt(msgContents[3]);
		int suggestedSpeed = Integer.parseInt(msgContents[4]);
		int suggestedAuthority = Integer.parseInt(msgContents[5]);
		
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
	 * Function for the CTC to open/close block for maintenance
	 * @param msg message containing track line, block and open/close status
	 * @return
	 */
	public boolean newMaintMsg(String msg) {
		String delimeter = "|";
		String[] msgContents;
		
		msgContents = msg.split(delimeter);
		String line = msgContents[0];
		int currentBlock = Integer.parseInt(msgContents[1]);
		String maintStatus = msgContents[2];
		
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
	 * Function for the CTC to gather status of the blocks
	 * @param blockID requested block's status
	 * @return defined value for block status
	 */
	public byte getBlockStatus(String line, int blockID) {
		// based on the track line and the requested block, select the appropriate track controller
		byte returnStatus = BLOCK_OPEN;
		
		return returnStatus;
	}
	
	/**
	 * Function for user to break/fix a selected block on the track
	 */
	private void breakBlock() {
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
