import java.io.IOException;
import java.util.ArrayList;


public class TrackDriver {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		Track trackTester = new Track();
		trackTester.placeTrain("green", 1);
		trackTester.placeTrain("red", 5);

		//trackTester.placeTrain("green", 2);
		//trackTester.placeTrain("green", 3);
		//trackTester.placeTrain("green", 4);

		//trackTester.placeTrain("Red", 15);

		for(int i = 0;i<1000;i++)
		{
			trackTester.updateDistance(1,100);
			//trackTester.updateDistance(TrainID, distance)
		}

		/*
		trackTester.toggleSwitch("Green", 153);
		trackTester.toggleSwitch("Green", 1);
		
		
		
		for(int i = 0;i<100;i++)
		{
			
			trackTester.updateDistance(10,50);
			//trackTester.updateDistance(TrainID, distance)

			
		}

		for(int i = 0;i<100;i++)
		{
			
			trackTester.updateDistance(10,50);
			//trackTester.updateDistance(TrainID, distance)

			
		}
		
		//trackTester.displayTrack();

		trackTester.getBlock(10, "Green").printBlock();

		
		
		

		ArrayList<String> soyoStrings = trackTester.getRoute("Green", "EDGEBROOK");

		for(String pls: soyoStrings)
		{
			System.out.println(pls);
			
		}
		
		//System.out.println(trackTester.getBlock(15,"Green").getBeacon());
	
		
		*/
		//trackTester.getBlock(100, "Green");
		
		//int trainID = 5;
		
		
		//trackTester.placeTrain(5,"Green",trainID);
		
		
		
	}

}
