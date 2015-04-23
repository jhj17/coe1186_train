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


public class TrackModelGUI {

	private JFrame frame;

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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Block 1", "Block 2", "Block 3", "Block 4", "Block 5"}));
		comboBox.setToolTipText("BLOCK");
		comboBox.setBounds(133, 51, 197, 38);
		panel.add(comboBox);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(6, 88, 484, 167);
		panel.add(textArea);
		
		JLabel lblWeather = new JLabel("Weather:");
		lblWeather.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblWeather.setBounds(542, 526, 79, 16);
		panel.add(lblWeather);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(6, 467, 484, 95);
		panel.add(textArea_1);
		
		JLabel lblSwitchInfo = new JLabel("Switch Info: ");
		lblSwitchInfo.setFont(new Font("Lucida Grande", Font.BOLD, 22));
		lblSwitchInfo.setBounds(6, 435, 141, 20);
		panel.add(lblSwitchInfo);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Switch 1", "Switch 2", "Switch 3", "Switch 4"}));
		comboBox_1.setToolTipText("BLOCK");
		comboBox_1.setBounds(145, 432, 197, 23);
		panel.add(comboBox_1);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Icy");
		rdbtnNewRadioButton.setBounds(571, 551, 50, 23);
		panel.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnRaining = new JRadioButton("Raining");
		rdbtnRaining.setBounds(632, 551, 79, 23);
		panel.add(rdbtnRaining);
		
		JRadioButton rdbtnNormal = new JRadioButton("Normal");
		rdbtnNormal.setBounds(633, 523, 78, 23);
		panel.add(rdbtnNormal);
		
		JLabel lblBlockInfo = new JLabel("Block Info: ");
		lblBlockInfo.setFont(new Font("Lucida Grande", Font.BOLD, 22));
		lblBlockInfo.setBounds(6, 52, 141, 27);
		panel.add(lblBlockInfo);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setBounds(6, 267, 484, 153);
		panel.add(textArea_2);
		
		JButton btnSubmit = new JButton("REFRESH");
		btnSubmit.setBounds(750, 523, 178, 57);
		panel.add(btnSubmit);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("/Users/zachbarnes/Desktop/trackpic.png"));
		lblNewLabel_1.setBounds(502, 6, 436, 508);
		panel.add(lblNewLabel_1);
	}
}
