import java.io.IOException;


public class Simulator 
{ 

	public static void main(String[] args) throws Exception {
			SimClock sm = new SimClock(10,0,0,0);
			Thread clockThread = new Thread(sm, "clockThread");
			clockThread.start();
			Track track = new Track();
			
			int trainID = 10;
			String line = "red";

			int trainID2 = 15;
			String line2 = "red";

			track.placeTrain(line, trainID);
			track.commandAuthority(line,1000000,track.getBlock(trainID).getBlockNumber());

			track.getBlock(trainID);
			Train train1 = new Train(track, trainID, sm);
<<<<<<< HEAD
			//Thread.sleep(10000);

			/*track.placeTrain(line2,trainID2);
			track.commandAuthority(line2,1000000,track.getBlock(trainID2).getBlockNumber());

			Train train2 = new Train(track, trainID2, sm);
*/
=======
			
			// Thread.sleep(10000);

			// track.placeTrain(line2,trainID2);
			// track.commandAuthority(line2,1000000,track.getBlock(trainID2).getBlockNumber());

			// Train train2 = new Train(track, trainID2, sm);

>>>>>>> f5db82ccc3a2c347e276e7763ee8305a2f24c874

			/*//TESTING TRAIN TRAVERSAL. 
			for(int i = 0;i<1000;i++)
			{
				trackTester.updateDistance(5,100);
				//trackTester.updateDistance(TrainID, distance)
			} */



	}
}
