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
		//trackTester.getRoute("green","PIONEER");
	/*	trackTester.getRoute("green","EDGEBROOK");
		trackTester.getRoute("green","BLANK");
		trackTester.getRoute("green","WHITED");
		trackTester.getRoute("green","SOUTH BANK");
*/


		trackTester.getRoute("red","SHADYSIDE");
		trackTester.getRoute("red","HERRON AVE");
		trackTester.getRoute("red","SWISSVILLE");



		trackTester.placeTrain("green", 1);
		trackTester.placeTrain("red", 5);



//TESTING PLACE OTHER TRAINS. 
		//trackTester.placeTrain("green", 2);
		//trackTester.placeTrain("green", 3);
		//trackTester.placeTrain("green", 4);
		//trackTester.placeTrain("Red", 15);

//TESTING TRAIN TRAVERSAL. 
		/*for(int i = 0;i<1000;i++)
		{
			trackTester.updateDistance(5,100);
			//trackTester.updateDistance(TrainID, distance)
		}
		*/

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



