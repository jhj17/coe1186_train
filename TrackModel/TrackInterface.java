import java.io.FileNotFoundException;
import java.io.IOException;


public interface TrackInterface {
		  
	/*	allLinesFirstBlock: block[]
		allLinesSwitches: switch[]
		weather: int
		coeffFriction: double 
	
	*/
	
//USER METHODS	
		/*
		 * Load entire track from CSV file
		 * Parameters: String filename of CSV file in current directory 
		 * Return: None-- track fully initialized 
		 * 
		 */
		public void loadTrack(String csvIn) throws FileNotFoundException, IOException;
		
		/*
		 * Allow user to toggle break any block or block circuit-- if broken, fixes, if works, breaks 
		 * 
		 * Return: true if now broken, false if currently fixed 
		 */
		public boolean breakBlock(int blockNumber);
		public boolean breakBlockCircuit(int blockNumber);
		
		
		/*
		 * Params: 0 dry, 1 raining, 2 snowing 
		 * 
		 */
		public void setWeather(int weatherType);
		
//..FROM WAYSIDE ..
		
		/*
		 * Toggles crossing down
		 * Returns: true if now down, false if now up 
		 * 
		 */
		public boolean commandCrossingDown(int blockNumber);
		
		
		/*
		 * Commands authority for that specific block 
		 * Returns: 
		 * 
		 */
		
		public void commandAuthority(String line, double commandedAuthority, int blockNumber);
		
		/*
		 * Toggles Switch to other state 
		 * Returns: new next block 
		 * 
		 */		
		
		/*
		 * Closes block 
		 * Returns: true if block closed, false if block not closed
		 * 
		 */
		public boolean closeBlock(String line, int blockNumber);
		
		
		/*
		 * Commands speed in specific block
		 * Returns: 
		 * 
		 */
		public void commandSpeed(String line, double commandedSpeed, int blockNumber);
		
		/*
		 * Switches lights between green and red state for specific block? 
		 * Returns: true if green, false if red 
		 * 
		 */
		public boolean toggleRedGreen(String line, int blockNumber);

		
//..FROM TRAIN..
		/*
		 * Train updates current distance
		 * Returns: new current train block 
		 * 
		 */
		public void updateDistance(int TrainID, double distance);

		
//	..TO WAYSIDE AND TRAIN ..
		
		
		/*
		 * Retrieves specific block 
		 * Returns: Block in question
		 * 
		 */
		public BlockInterface getBlock(int blockNumber, String line);
		public BlockInterface getTrainBlock(int TrainID);


	//	..general..
		public void displayTrack();
		public void displayBlock(int blockNumber);

		Block toggleSwitch(String line, int switchNumber);
		
		
		
		
		
/*		-initBlock();
		-putTrain();
		-getBlock();
		-setSpeed();
		-updateDistance();
		*/
	
	
}
