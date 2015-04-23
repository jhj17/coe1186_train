import java.awt.*;
import javax.swing.*;


public class TransitSys {
	private JFrame theWindow;
	private JPanel trackPicturePane, textPanel;
	private JScrollPane trainTextPane;
	private ImageIcon imageTransit;
	private JLabel labelTransit;
	public JTextArea trainInfo;
	private JSplitPane split;
	
	public TransitSys()
	{
		theWindow = new JFrame("Transit System");
		theWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theWindow.setVisible(true);
		theWindow.pack();

		trackPicturePane = new JPanel();
		trackPicturePane.setLayout(new FlowLayout());
		imageTransit = new ImageIcon(getClass().getResource("/TrackLayout.png"));
		labelTransit = new JLabel(imageTransit);
		trackPicturePane.add(labelTransit);
		
		trainInfo = new JTextArea();
		trainInfo.setText("Train Location: \n");
		//trainInfo.append("Train: 1 Block: Yard\n");
		trainInfo.setEditable(false); 
		trainInfo.setPreferredSize(new Dimension(328, 415));
		trainTextPane = new JScrollPane(trainInfo);
		
		textPanel = new JPanel();
		textPanel.add(trainTextPane);
		
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, trackPicturePane, trainTextPane);
		theWindow.getContentPane().add(split);
		
		
	}
	public void locateTrain(int trainID, Track tk)
	{
		Block b;
		String section;
		int blockNumber;
		while(true)
		{
			b = tk.getBlock(trainID);
			section = b.getSection();
			blockNumber = b.getBlockNumber();
			trainInfo.append("Train: " + trainID + "Section: " + section + "Block: " + blockNumber + "\n");
			//System.out.println("Train: " + trainID + "Section: " + section + "Block: " + blockNumber);
		}
		
	}
	public static void main(String [] args)
	{
		new TransitSys();
		
	}
	

}
