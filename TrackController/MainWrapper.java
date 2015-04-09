package trackControllerFinal;

import java.awt.EventQueue;
import java.util.LinkedList;

public class MainWrapper {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//SimClock clock = new SimClock(100,0,0,0);
					//TrackModelWrapper trackModelWrapper = new TrackModelWrapper();
					//TrackControllerWrapper trackControllerWrapper = new TrackControllerWrapper(trackModel);
					TrackControllerWrapper trackControllerWrapper = new TrackControllerWrapper();
					//CTCWrapper ctcWrapper = new CTCWrapper(trackModelWrapper,trackControllerWrapper,clock);
					//MBOWrapper mboWrapper = new MBOWrapper(trackModelWrapper,ctcWrapper,clock);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
