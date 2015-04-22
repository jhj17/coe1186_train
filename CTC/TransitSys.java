import java.awt.*;
import javax.swing.*;


public class TransitSys {
	private JFrame theWindow;
	private JPanel trackPicturePane, textPanel;
	private JScrollPane trainTextPane;
	private ImageIcon imageTransit;
	private JLabel labelTransit;
	private JTextArea trainInfo;
	private JSplitPane split;
	
	TransitSys()
	{
		theWindow = new JFrame("Transit System");
		theWindow.setVisible(true);
		theWindow.pack();

		trackPicturePane = new JPanel();
		trackPicturePane.setLayout(new FlowLayout());
		imageTransit = new ImageIcon(getClass().getResource("/TrackLayout.png"));
		labelTransit = new JLabel(imageTransit);
		trackPicturePane.add(labelTransit);
		
		trainInfo = new JTextArea();
		trainInfo.setEditable(false); 
		trainInfo.setPreferredSize(new Dimension(328, 415));
		trainTextPane = new JScrollPane(trainInfo);
		
		textPanel = new JPanel();
		textPanel.add(trainTextPane);
		
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, trackPicturePane, trainTextPane);
		theWindow.getContentPane().add(split);
	
		
	}
	public static void main(String [] args)
	{
		new TransitSys();
		
	}
	

}
