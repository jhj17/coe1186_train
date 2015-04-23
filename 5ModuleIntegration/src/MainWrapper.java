import java.io.IOException;


public class MainWrapper {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		SimClock sm = new SimClock(10,0,0,0);
		Thread clockThread = new Thread(sm, "clockThread");
		clockThread.start();
		Track track = new Track();
		TrackControllerWrapper tcw = new TrackControllerWrapper(track);
		CTCGUI ctc = new CTCGUI(track, tcw, sm);
		

	}

}
