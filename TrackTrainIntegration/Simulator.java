import java.io.IOException;


public class Simulator {

	public static void main(String[] args) {
			SimClock sm = new SimClock();
			Thread clockThread = new Thread(sm, "clockThread");
			clockThread.start();
			Track track = new Track();
			Train train1 = new Train(track, 10, sm);
	}
}
