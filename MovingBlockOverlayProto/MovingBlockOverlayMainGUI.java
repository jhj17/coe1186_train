import java.awt.*;
import java.awt.event.*;

public class MovingBlockOverlayMainGUI {

	private static Frame mainFrame;
	private static TextField startInput;
	private static Label headerLabel, modeLabel, startLabel;
	private static Button mboButton, fixedBlockButton, viewButton, exportButton, helpButton, startButton, dispatchButton;

	public static void main(String[] args) {

		mainFrame = new Frame("Moving Block Overlay Module");
		mainFrame.setSize(800,600);
		mainFrame.setVisible(true);

		Panel panel = new Panel(new GridLayout(20, 1));

		startInput = new TextField();

		headerLabel = new Label("John Abraham - Moving Block Overlay Scheduler", Label.CENTER);
		modeLabel = new Label("Mode of Operation:");
		startLabel = new Label("Start Schedule at Time:");

		mboButton        = new Button("MBO");
		fixedBlockButton = new Button("Fixed Block");
		viewButton       = new Button("View Operator Schedule");
		exportButton     = new Button("Export Schedule to CTC");
		helpButton       = new Button("Help");
		startButton      = new Button("Start");
		dispatchButton   = new Button("Dispatch Trains from the Yard");

		panel.add(headerLabel);
		panel.add(modeLabel);
		panel.add(startLabel);
		panel.add(mboButton);
		panel.add(fixedBlockButton);
		panel.add(startInput);
		panel.add(startButton);
		panel.add(viewButton);
		panel.add(exportButton);
		panel.add(helpButton);
		panel.add(dispatchButton);
		mainFrame.add(panel);

		mainFrame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				System.exit(0);
			}
		});
	}
}