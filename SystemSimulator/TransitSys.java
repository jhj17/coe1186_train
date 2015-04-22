import java.awt.*;
import javax.swing.*;

public class TransitSys extends JFrame {
	private ImageIcon imageTransit;
	private JLabel labelTransit;
	private JTextArea trainInfo;
	private JSplitPane split;
	
	TransitSys()
	{
		getContentPane().setLayout(new FlowLayout());
		imageTransit = new ImageIcon(getClass().getResource("/TrackLayout.png"));
		labelTransit = new JLabel(imageTransit);
		getContentPane().add(labelTransit);
		
		/*trainInfo = new JTextArea();
		trainInfo.setEditable(false); 
		trainInfo.setSize(328, 415);
		getContentPane().add(trainInfo);*/
		
		//split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, labelTransit, trainInfo);
		
	}
	public static void main(String [] args)
	{
		TransitSys ts = new TransitSys();
		ts.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ts.setVisible(true);
		ts.pack();
		ts.setTitle("Transit System");
	}
	

}
