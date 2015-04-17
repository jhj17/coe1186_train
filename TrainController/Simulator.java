import java.io.IOException;


public class Simulator {

	public static void main(String[] args) {
			SimClock sm = new SimClock();
			Track track = new Track();
			Train train1 = new Train(track, 10, sm);
	}
}
