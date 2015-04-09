package trackControllerFinal;

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
					//SimClock clock = new SimClock(100,0,0,0);
					//TrackModelWrapper trackModelWrapper = new TrackModelWrapper();
					Track trackModel = new Track();
					TrackControllerWrapper trackControllerWrapper = new TrackControllerWrapper(trackModel);
					//CTCWrapper ctcWrapper = new CTCWrapper(trackModelWrapper,trackControllerWrapper,clock);
					//MBOWrapper mboWrapper = new MBOWrapper(trackModelWrapper,ctcWrapper,clock);
					
					// test proceed plc functionality with block
					CtcMessageThread fakeCTC = new CtcMessageThread(trackControllerWrapper);
					Thread fakeCTCThread = new Thread(fakeCTC, "thread1");
					//Start the threads
					fakeCTCThread.start();
					try {
						//delay for one second
						Thread.currentThread().sleep(1000);
					} 
					catch (InterruptedException e) 
					{ }
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
