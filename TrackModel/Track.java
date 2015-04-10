import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Track implements TrackInterface {

	private Block redFromYard = null;
	private Block greenFromYard = null;
	private ArrayList<Block> allRedBlocks;
	private ArrayList<Block> allGreenBlocks;
	private double coeffFriction;
	private int weather;
	private ArrayList<Switch> redSwitches;
	private ArrayList<Switch> greenSwitches;
	
	public Track() throws IOException
	{
		loadTrack("redLineFix.csv");	
		loadTrack("greenLineFix.csv");	

	}
	
	@Override
	public void loadTrack(String csvIn) throws IOException {
		// TODO Auto-generated method stub
		//String inFile = "redLine.csv";
		String inFile = csvIn;
		BufferedReader reader = new BufferedReader(new FileReader(inFile));
		
		String splitStrings[];
		
		//	reader.readLine(); if including the first line in the csv 
		Block currentBlock = null;
		String directionTracker = "";
		ArrayList<Switch> currentSwitches = null;
		ArrayList<Block >currentAll = null;

		while(reader.ready()) //read until end of file 
		{
			String blockString = reader.readLine();
			splitStrings = new String[12];
			String[] inStrings = blockString.split(",");

			for(int i = 0; i<inStrings.length; i++)
			{
				splitStrings[i] = inStrings[i];
				
			}
			
			if(firstRedBlock == null && splitStrings[0].equals("Red")) //if first block and red line 
			{
				firstRedBlock = new Block(splitStrings, currentBlock);  // instantiate firstBlock
				currentBlock = firstRedBlock;
				
				redSwitches = new ArrayList<Switch>(); //instantiate set of switches
				currentSwitches = redSwitches;
				
				allRedBlocks = new ArrayList<Block>(); //instantiate array to hold all blocks 
				currentAll = allRedBlocks;
			}
			
			else if(firstGreenBlock == null && splitStrings[0].equals("Green")) //same for green blocks 
			{
				firstGreenBlock = new Block(splitStrings, currentBlock);
				currentBlock = firstGreenBlock;

				greenSwitches = new ArrayList<Switch>();
				currentSwitches = greenSwitches;

				allGreenBlocks = new ArrayList<Block>();
				currentAll = allGreenBlocks;
			}
			
			else //if not the first, go through and make the list. 
			{
				currentBlock.setNext2(new Block(splitStrings, currentBlock));
				currentBlock = currentBlock.getNext2();				
			}

			currentAll.add(currentBlock); //add currentBlock to the list 
			
			if(splitStrings[10] != null && !splitStrings[10].equals("")) //create joined switch blocks 
			{
				switchMaker(splitStrings, currentBlock, currentSwitches);
			}
			
			if(splitStrings[6].equals("YARD")) // to create the yard blocks... e.g. the real starting blocks 
			{
				if(splitStrings[0].equals("Red"))
				{
					redFromYard = currentBlock;
				}
				else
				{
					greenFromYard = currentBlock;
				}
			}
		}

		for(Block allBlocks: currentAll) // correct the directionality of blocks 
		{
			directionTracker = directionCorrection(allBlocks, directionTracker);
		}

		for(Switch allSwitches: currentSwitches) //correct switch connections based on directionality 
		{
			allSwitches.adjustConnections();	
		}
		
		for(Block allBlocks: currentAll) //print all final blocks and associations 
		{
			allBlocks.printBlock();
			
		}
		
		printAllSwitches(currentSwitches); //print all switches 
		
	}

	public ArrayList<String> getRoute(String line, String destination)
	{
		
		ArrayList<Block >allBlocks; //begin with right blocks 
		if(line.equals("Red"))
		{
			allBlocks = allRedBlocks;
		}
		else
		{
			allBlocks = allGreenBlocks;
		}
		
		toggleSwitch("Green", 1); //HARD CODED-CHEATSY THING. 
		
		ArrayList<String> path = new ArrayList<String>(); //Store the path 
		for(int i = 0; i<allBlocks.size();i++)
		{
			Block currentBlock = allBlocks.get(i);
			System.out.println(allBlocks.get(i).getSection());
			path.add(currentBlock.getBlockNumber() + "|" + currentBlock.getBlockLength() + "|" + currentBlock.getSpeedLimit());
	
			if(allBlocks.get(i).stationName != null && (allBlocks.get(i).stationName).equals(" " + destination))
			{
				//System.out.println("found it!");
				return path;
			}
		}		
		return path;
	}
	private void printAllSwitches(ArrayList<Switch> currentSwitches) {
		for(Switch temp: currentSwitches)
		{
			temp.printBlocks();	
		}
	}

	private void switchMaker(String[] splitStrings, Block currentBlock,
			ArrayList<Switch> currentSwitches) {
		//System.out.println("Switch group");
		System.out.println(splitStrings[10]);
		String[] switchStrings = splitStrings[10].split(" ");
		System.out.println(Arrays.toString(switchStrings));
		int switchNumber = Integer.parseInt(switchStrings[1]);
		
		int switchPosition = -1;
		System.out.println(currentSwitches);
		for(int i = 0; i<currentSwitches.size();i++)
		{
			if(currentSwitches.get(i).getSwitchNumber() == switchNumber)
			{
				switchPosition = i;
			}	
		}
		
		if(switchPosition == -1)
		{
			currentSwitches.add(new Switch(currentBlock, switchNumber));	
		}
		else
		{	
			currentSwitches.get(switchPosition).addBlock(currentBlock);
		}

	}

	private String directionCorrection(Block curBlock, String tracker) {
		String arrow = curBlock.getArrow();
		String directionTracker = tracker;
		
		if(arrow.equals("Head"))
		{
			directionTracker = directionTracker + "H";	
		}
		else if(arrow.equals("Tail"))
		{
			directionTracker = directionTracker + "T";
		}
		else if(arrow.equals("Head/Head"))
		{
			directionTracker = "HH";	
		}
		else if(arrow.equals("Tail/Head"))
		{
			directionTracker = "TH";
		}

		if(directionTracker.length() == 2) //if there are two ends 
		{
			if(directionTracker.equals("HH"))
			{
				while(curBlock != null && curBlock.getDirection() == 0) // if two sided, set all directions to 2 
				{
					curBlock.setDirection(2);
					curBlock = curBlock.getNext1();
				}
			}
			else if(directionTracker.equals("TH")) //correct next for 1-sided that were formed backwards 
			{	
				while(curBlock != null && curBlock.getDirection() == 0) 				//switch directions 1 & 2
				{
					Block temp = curBlock.getNext1();
					curBlock.setNext1(curBlock.getNext2());
					curBlock.setNext2(temp);
					curBlock.setDirection(1);
					curBlock = curBlock.getNext2();	
				}
					
			}
			else if(directionTracker.equals("HT")) // if 1-sided and formed as expected, leave it 
			{
				while(curBlock != null && curBlock.getDirection() == 0)
				{
					
					curBlock.setDirection(1);
					curBlock = curBlock.getNext1();	
				}	
			}

			directionTracker = ""; // set back to empty string for next arrow set 
		}
		
		return directionTracker;
	}

	@Override
	public boolean breakBlock(int blockNumber) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean breakBlockCircuit(int blockNumber) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setWeather(int weatherType) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean commandCrossingDown(int blockNumber) {
		// TODO Auto-generated method stub
		return false;
	}

	public void commandAuthority(String line, double commandedAuthority, int blockNumber) {
		// TODO Auto-generated method stub
		getBlock(blockNumber, line).setAuthority(commandedAuthority);
	}

	@Override
	public Block toggleSwitch(String line, int blockNumber) {
		// TODO Auto-generated method stub

		ArrayList<Switch> x;

		if(line.equals("Red"))
		{
			x = redSwitches;
			allBlockSwitchToggler(blockNumber, x);
			
		}
		else
		{
			if(line.equals("Green"))
			{
				x = greenSwitches;
				allBlockSwitchToggler(blockNumber, x);				
			}
		}
		
		return null;
		
	}
	private void allBlockSwitchToggler(int blockNumber, ArrayList<Switch> x) {
		for(Switch temp: x)
		{
			for(Block switchBlocks: temp.getSwitchBlocks())
			{
				if(switchBlocks.getBlockNumber() == blockNumber)
				{
					temp.toggleSwitch();
					displayTrack();
				}	
			}
		}
	}

	@Override
	public boolean closeBlock(String line, int blockNumber) {
		// TODO Auto-generated method stub
		
		getBlock(blockNumber, line).closeBlock();
		return false;
	}

	@Override
	public void commandSpeed(String line, double commandedSpeed, int blockNumber) {
		// TODO Auto-generated method stub

		getBlock(blockNumber, line).setCommandedSpeed(commandedSpeed);
		
	}

	@Override
	public boolean toggleRedGreen(String line, int blockNumber) {
		// TODO Auto-generated method stub
		return getBlock(blockNumber, line).toggleRedGreen();

	}
	
	@Override
	public void updateDistance(int TrainID, double distance) {
		// TODO Auto-generated method stub
		System.out.println();
		getTrainBlock(TrainID).moveTrain(distance);
		
	}

	public void placeTrain(String line, int trainID )
	{	
		if(line.equals("Red"))
		{
			redFromYard.placeTrain(trainID,0);
		}
		else
		{
			greenFromYard.placeTrain(trainID,0);
			//System.out.println("1 " +greenFromYard.getNext1() + " 2 "+ greenFromYard.getNext2());
		}
	}
	
	public Block getBlock(int TrainID)
	{
		return getTrainBlock(TrainID);	
	}
	
	@Override
	public Block getBlock(int blockNumber, String line) {
		// TODO Auto-generated method stub
		
		Block currentBlock;
		if(line.equals("Red"))
		{
			return allRedBlocks.get(blockNumber-1);
		}
		else 
		{
			return allGreenBlocks.get(blockNumber-1);
		}
	}
	
	@Override
	public Block getTrainBlock(int TrainID) {
		// TODO Auto-generated method stub
		
		if(allRedBlocks != null)
		{
			for(Block reds: allRedBlocks)
			{
				if(reds.getTrainID() == TrainID)
				{
					return reds;
				}
			}
		}

		if(allGreenBlocks != null)
		{
			for(Block greens: allGreenBlocks)
			{
				if(greens.getTrainID() == TrainID)
				{
					return greens;
				}
			}	
		}

		
		return null;
	}
	@Override
	public void displayTrack() {
		// TODO Auto-generated method stub
		if(allRedBlocks!=null)
		{
			for(Block redBlocks: allRedBlocks)
			{
				redBlocks.printBlock();
			}
		}
		if(allGreenBlocks != null)
		{
			
			for(Block greenBlocks: allGreenBlocks)
			{
				greenBlocks.printBlock();
				
			}
		}
	}
	
	public void jeffTrack() {
		// TODO Auto-generated method stub
		if(allRedBlocks!=null)
		{
			for(Block redBlocks: allRedBlocks)
			{
				System.out.println("here");
				System.out.println(redBlocks.getBlockNumber());
				System.out.println("Num:"+redBlocks.getBlockNumber() + " next Num:" + redBlocks.getNext().getBlockNumber());
			}
		}

		if(allGreenBlocks!=null)
		{
			for(Block greenBlocks: allGreenBlocks)
			{
				System.out.println("Num:"+greenBlocks.getBlockNumber() + " next Num:" + greenBlocks.getNext().getBlockNumber());
			}
		}
	}

	@Override
	public void displayBlock(int blockNumber) {
		// TODO Auto-generated method stub

	}
}