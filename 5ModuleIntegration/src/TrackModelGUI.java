import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TrackModelGUI {

	private JFrame frame;
	private Track theTrack;
	private String[] blockNames;
	private String[] switchNames;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrackModelGUI window = new TrackModelGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();

				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TrackModelGUI() {

		//theTrack = trackIn;
		//System.out.println(theTrack.getRoute("green","PIONEER"));

	}

	public void sendTrack(Track tracker)
	{
	

	}


	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize(Track tracker) {


		theTrack = tracker;
	//	System.out.println(theTrack.getRoute("green","PIONEER"));


		blockNames = theTrack.getGUIBlocks();
		/*for(int i= 0;i<blockNames.length;i++)
			System.out.println(blockNames[i]);
		System.out.println(blockNames);
*/

		switchNames = theTrack.getGUISwitches();

	
		//System.out.println(new String[] {"Block 1", "Block 2", "Block 3", "Block 4"});

		frame = new JFrame();
		frame.setBounds(100, 100, 960, 619);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 947, 586);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblTrainModel = new JLabel("Track Model:");
		lblTrainModel.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 26));
		lblTrainModel.setBounds(6, 6, 197, 32);
		panel.add(lblTrainModel);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(blockNames));
		comboBox.setToolTipText("BLOCK");
		comboBox.setBounds(133, 51, 197, 38);
		panel.add(comboBox);
		
		final JTextArea textArea = new JTextArea();
		textArea.setBounds(6, 88, 484, 167);
		panel.add(textArea);
		
		JLabel lblWeather = new JLabel("Weather:");
		lblWeather.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblWeather.setBounds(542, 526, 79, 16);
		panel.add(lblWeather);
		
		final JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(6, 467, 484, 95);
		panel.add(textArea_1);
		
		JLabel lblSwitchInfo = new JLabel("Switch Info: ");
		lblSwitchInfo.setFont(new Font("Lucida Grande", Font.BOLD, 22));
		lblSwitchInfo.setBounds(6, 435, 141, 20);
		panel.add(lblSwitchInfo);
		
		final JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(switchNames));
		comboBox_1.setToolTipText("BLOCK");
		comboBox_1.setBounds(145, 432, 197, 23);
		panel.add(comboBox_1);
		
		final JRadioButton rdbtnNewRadioButton = new JRadioButton("Icy");
		rdbtnNewRadioButton.setBounds(571, 551, 50, 23);
		panel.add(rdbtnNewRadioButton);
		
		final JRadioButton rdbtnRaining = new JRadioButton("Raining");
		rdbtnRaining.setBounds(632, 551, 79, 23);
		panel.add(rdbtnRaining);
		
		final JRadioButton rdbtnNormal = new JRadioButton("Normal");
		rdbtnNormal.setBounds(633, 523, 78, 23);
		panel.add(rdbtnNormal);
		
		JLabel lblBlockInfo = new JLabel("Block Info: ");
		lblBlockInfo.setFont(new Font("Lucida Grande", Font.BOLD, 22));
		lblBlockInfo.setBounds(6, 52, 141, 27);
		panel.add(lblBlockInfo);
		
		final JTextArea textArea_2 = new JTextArea();
		textArea_2.setBounds(6, 267, 484, 153);
		panel.add(textArea_2);
		
		/*JButton btnSubmit = new JButton("REFRESH");
		btnSubmit.setBounds(750, 523, 178, 57);
		panel.add(btnSubmit); */
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("trackpic.png"));
		lblNewLabel_1.setBounds(502, 6, 436, 508);
		panel.add(lblNewLabel_1);

		JButton btnSubmit = new JButton("REFRESH");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				


				
				System.out.println("REFRESSSSH");

				String chosenBlock = (String) comboBox.getSelectedItem();
				String chosenSwitch = (String) comboBox_1.getSelectedItem();

				String[] blockParams = chosenBlock.split(" ");
				Block block = theTrack.getBlock(Integer.parseInt(blockParams[2]), blockParams[0]);

				Switch switcher = theTrack.getSwitch(chosenSwitch);
				
				textArea.setText("");
				textArea.append(block.gui1());


				textArea_2.setText("");
				textArea_2.append(block.gui2());


				textArea_1.setText("");
				textArea_1.append(switcher.toGUI());


				if(rdbtnNewRadioButton.isSelected())
				{
					System.out.println("icy");
					theTrack.setFriction(0.001);
				}
				else if(rdbtnRaining.isSelected())
				{

					System.out.println("raining");
					theTrack.setFriction(0.001);


				}

				else if(rdbtnNormal.isSelected())
				{
					System.out.println("dry");
					theTrack.setFriction(0.001);
				}


			}
		});
		btnSubmit.setBounds(750, 523, 178, 57);
		panel.add(btnSubmit);


		frame.setVisible(true);

	}
}
