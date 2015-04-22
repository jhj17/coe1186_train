import java.io.IOException;


public class Simulator 
{ 

	public static void main(String[] args) throws IOException {
			SimClock sm = new SimClock();
		//	Thread clockThread = new Thread(sm, "clockThread");
		//	clockThread.start();
			Track track = new Track();
			
			int trainID = 10;
			String line = "green";

			track.placeTrain(line, trainID);
			track.getBlock(trainID);
			//Train train1 = new Train(track, trainID, sm);



			/*//TESTING TRAIN TRAVERSAL. 
			for(int i = 0;i<1000;i++)
			{
				trackTester.updateDistance(5,100);
				//trackTester.updateDistance(TrainID, distance)
			} */



	}
}
