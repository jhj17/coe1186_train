import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.EventQueue;

public class Simulator 
{ 

	public static void main(String[] args) throws Exception {
		Track track = new Track();
		TrackControllerWrapper trackControllerWrapper = new TrackControllerWrapper(track);
		
		SimClock sm = new SimClock(10,0,0,0);
		Thread clockThread = new Thread(sm, "clockThread");
		clockThread.start();
		
		//CTCGUI ctc = new CTCGUI(track, trackControllerWrapper, sm);

		int trainID = 10;
		String line = "green";

		//int trainID2 = 15;
		//String line2 = "green";

		track.placeTrain(line, trainID);
		//track.commandAuthority(line,1000000,track.getBlock(trainID).getBlockNumber());

		track.getBlock(trainID);
		Train train1 = new Train(track, trainID, sm);
		//Thread.sleep(10000);

		/*track.placeTrain(line2,trainID2);
			track.commandAuthority(line2,1000000,track.getBlock(trainID2).getBlockNumber());

			Train train2 = new Train(track, trainID2, sm);
		 */

		// Thread.sleep(10000);

		// track.placeTrain(line2,trainID2);
		// track.commandAuthority(line2,1000000,track.getBlock(trainID2).getBlockNumber());

		// Train train2 = new Train(track, trainID2, sm);

		/*//TESTING TRAIN TRAVERSAL. 
			for(int i = 0;i<1000;i++)
			{
				trackTester.updateDistance(5,100);
				//trackTester.updateDistance(TrainID, distance)
			} */

		
		// Read in test csv file to route a train to a station
		
		//Input file which needs to be parsed
		ArrayList<String> commandsToTrackController = new ArrayList<String>();
		ArrayList<Integer> nextBlocks = new ArrayList<Integer>();
		String fileToParse = "simulatedCTC.csv";
		BufferedReader fileReader = null;

		//Delimiter used in CSV file
		final String DELIMITER = ",";
		
		try {
			String fileLine = "";
			//Create the file reader
			fileReader = new BufferedReader(new FileReader(fileToParse));

			//Read the file line by line
			while ((fileLine = fileReader.readLine()) != null) {
				//Get all tokens available in line
				String[] tokens = fileLine.split(DELIMITER);
				String command = tokens[0] + "," + tokens[1] + "," + tokens[2] + "," + tokens[3] + "," + 
						tokens[4] + "," + tokens[5];
				
				nextBlocks.add(Integer.parseInt(tokens[2]));
				commandsToTrackController.add(command);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				fileReader.close();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}

		Thread.sleep(2000);
		
		System.out.println("Start");
		
		trackControllerWrapper.showBeacon("green", 9);
		
		// Run the system with the test
		for(int i = 0; i < commandsToTrackController.size(); i++) {
			trackControllerWrapper.newProceedMsg(commandsToTrackController.get(i));
			
			while (trackControllerWrapper.getBlockStatus("green", nextBlocks.get(i)) != 1) {
				if ((i+1) < commandsToTrackController.size() && 
						trackControllerWrapper.getBlockStatus("green", nextBlocks.get(i+1)) == 1 ) {
					i++;
					break;
				}
				// track is not on next block
				Thread.sleep(50);
			}
		}
		
		
	}
}
