
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class MainWrapper {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SimClock clock = new SimClock(10, 7, 30,00);
					Track trackModel = new Track();
					TrackControllerWrapper trackControllerWrapper = new TrackControllerWrapper(trackModel);
					CTCGUI ctcWrapper = new CTCGUI(trackModel,trackControllerWrapper,clock);
					//MBOWrapper mboWrapper = new MBOWrapper(trackModelWrapper,ctcWrapper,clock);
					
					// test proceed plc functionality with block
				    Thread clockThread = new Thread(clock, "clockThread");
					clockThread.start();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
