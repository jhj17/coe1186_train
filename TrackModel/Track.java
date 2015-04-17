import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Track implements TrackInterface {

	private Block redYard = null;
	private Block greenYard = null;
	private ArrayList<Block> redBlocks = new ArrayList<Block>();
	private ArrayList<Block> greenBlocks = new ArrayList<Block>();
	private ArrayList<Switch> redSwitches = new ArrayList<Switch>();
	private ArrayList<Switch> greenSwitches = new ArrayList<Switch>();
	private	ArrayList<Block> trainBlocks = new ArrayList<Block>();

	private double coeffFriction;
	private int weather;

	public Track() throws IOException
	{
		loadTrack("REDFINALV2.csv");	
		loadTrack("GREENFINALV2.csv");	

	}
	public void loadTrack(String csvIn) throws IOException {

		String inFile = csvIn;
		BufferedReader reader = new BufferedReader(new FileReader(inFile));
		String splitStrings[];
		ArrayList<Switch> currentSwitches = null;
		ArrayList<Block >currentAll = null;
		Block currentBlock = null;
		Block currentYard = null;

		while(reader.ready()) //read until end of file 
		{
			splitStrings = reader.readLine().split(",");
			//System.out.println(Arrays.toString(splitStrings));			
			if(currentBlock == null && splitStrings[0].equals("red")) //if first block and red line 
			{
				currentSwitches = redSwitches;//instantiate set of switches
				currentAll = redBlocks; //instantiate array to hold all blocks 
			}
			
			else if(currentBlock == null && splitStrings[0].equals("green")) //same for green blocks 
			{
				currentSwitches = greenSwitches;
				currentAll = greenBlocks;
			}

			currentBlock = new Block(splitStrings, currentBlock);  // instantiate block				
			currentAll.add(currentBlock); // add block to list for easy lookup

			switchMaker(splitStrings,currentBlock,currentSwitches); //connect all switch blocks and put them into ArrayList
			if(splitStrings[6].equals("FROM YARD") || splitStrings[6].equals("TO YARD/FROM YARD"))
			{
				if(splitStrings[0].equals("green"))
					greenYard = currentBlock;
				else
					redYard = currentBlock;
			}
		}

		//printSwitchList(currentSwitches);
		for(Switch setupSwitch: currentSwitches) //setup switch connections 
		{
			setupSwitch.setup();
		}

//TEST CURRENT BLOCKS AS WELL AS SWITCH TOGGLING. 
		/*printBlockList(currentAll);

		for(Switch setupSwitch: currentSwitches) //setup switch connections 
		{
			setupSwitch.toggleSwitch();
		}
		printBlockList(currentAll);
*/

//TEST FOR PROPER ROUTING! 
		//printSwitchList(currentSwitches);


		/*System.out.println();
		Block traverseBlock = redYard;
		Block lastTraverse = null;
		if(traverseBlock != null)
			System.out.println(traverseBlock.getSection()+traverseBlock.getBlockNumber());
		while(traverseBlock !=null)
		{
			//System.out.println(traverseBlock);
			lastTraverse = traverseBlock;
			//printBlockList(currentAll);
			traverseBlock = traverseBlock.traverse();
			System.out.println(traverseBlock.getSection() + traverseBlock.getBlockNumber());
			if(traverseBlock == lastTraverse)
			{
				System.out.println("toggling");
				traverseBlock.toggleSwitch();
			}
		}
*/

		//store all current train blocks 
	}


public void printSwitchList(ArrayList<Switch> printList)
{
	System.out.println();
	for(Switch currentSwitch: printList)
	{
		System.out.println(currentSwitch.getSwitchNumber());
		System.out.println("Switch: " + currentSwitch.getSwitchBlock().getSection() + currentSwitch.getSwitchBlock().getBlockNumber());
		printBlockConnections(currentSwitch.getSwitchBlock());
		System.out.println("switchedBlock: " + currentSwitch.getswitchedBlockBlock().getSection() + currentSwitch.getswitchedBlockBlock().getBlockNumber());
		printBlockConnections(currentSwitch.getswitchedBlockBlock());
		System.out.println("unSwitchedBlock: " + currentSwitch.getunSwitchedBlockBlock().getSection() + currentSwitch.getunSwitchedBlockBlock().getBlockNumber());
		printBlockConnections(currentSwitch.getunSwitchedBlockBlock());
		System.out.println();
		//printBlockConnections()
	}
}

public void printBlockConnections(Block block)
{
		Block next;
		Block previous;
		next = block.getNext();
		previous = block.getPrevious();
		System.out.print(block.getSection() + block.getBlockNumber()); 
		System.out.print(" next: ");
		if(next != null)
			System.out.print(next.getSection() + next.getBlockNumber() + " "); 
		else
			System.out.print(next + " ");

		System.out.print("previous: ");
		if(previous != null)
			System.out.print(previous.getSection() + previous.getBlockNumber() + " "); 
		else
			System.out.print(previous + " ");

		System.out.println();
}

public void printBlockList(ArrayList<Block> printList)
{

	System.out.println();
	for(Block block: printList)
	{
		printBlockConnections(block);
	}

}

	public ArrayList<String> getRoute(String line, String destination)
	{
		ArrayList<Block> pathBlocks = new ArrayList<Block>();
		ArrayList<String> pathBlockStrings = new ArrayList<String>();
		Block currentBlock = null;
		if(line.equals("red"))
			currentBlock = redYard;
		else
			currentBlock = greenYard;

		pathBlocks.add(currentBlock);
		while(!currentBlock.getStation().equals(destination))
		{
			Block lastTraverse = currentBlock;
//			System.out.println(currentBlock.getSection() + currentBlock.getBlockNumber() + " " + currentBlock.getStation());

			currentBlock = currentBlock.traverse();


			if(lastTraverse == currentBlock)
			{
				currentBlock.toggleSwitch();
			}
			else
			{
					pathBlocks.add(currentBlock);
			}
		}

		currentBlock.traverse();
		currentBlock.setSeen(0);


		String routeBlocks;
		for(Block path: pathBlocks)
		{
			routeBlocks = path.getBlockNumber() + "," + path.getSection() + "," + path.getBlockLength() + "," + path.getSpeedLimit();
			pathBlockStrings.add(routeBlocks);
		}

		//System.out.println(pathBlockStrings.toString());
		return pathBlockStrings;


		//"blockNumber, section, blockLength, speed limit" in each string, separated by commas.


	}
	private void printAllSwitches(ArrayList<Switch> currentSwitches) {
		for(Switch temp: currentSwitches)
		{
			//temp.printBlocks();	
		}
	}

	private void switchMaker(String[] splitStrings, Block currentBlock,
			ArrayList<Switch> currentSwitches) {

		if(splitStrings[11].length()>0)
			{
				boolean newSwitch = true;
				Switch existingSwitch = null;
				for(Switch checkSwitches: currentSwitches)
				{
					if(checkSwitches.getSwitchNumber().equals(splitStrings[11]))
					{
						existingSwitch = checkSwitches;
						newSwitch = false;
					}
				}
				if(newSwitch)
				{
					currentSwitches.add(new Switch(currentBlock));
				}
				else
				{
					existingSwitch.addBlock(currentBlock);
				}
			}

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

		/*for(Switch setupSwitch: currentSwitches) //setup switch connections 
		{
			setupSwitch.setup();
		}*/
		
		return null;
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
	public void updateDistance(int trainID, double distance) {
 		for(int i = 0; i<trainBlocks.size();i++)
		{
			if(trainBlocks.get(i).getTrainID() == trainID)
			{
				System.out.println(trainBlocks.get(i).getSection() + trainBlocks.get(i).getBlockNumber() + " has " +trainBlocks.get(i).getTrainID());
				Block nextBlock = trainBlocks.get(i).moveTrain(distance);
				if(nextBlock != trainBlocks.get(i))
				{
					trainBlocks.remove(i);
					trainBlocks.add(nextBlock);
				}
				//printBlockList(trainBlocks);
				return;
			}
		}

	//	printBlockList(greenBlocks);

	}

	public void placeTrain(String line, int trainID )
	{	
		Block trainBlock = null;
		if(line.equals("red"))
		{
			trainBlock = redYard.placeTrain(trainID,0);
		}
		else
		{
			trainBlock = greenYard.placeTrain(trainID,0);
		}

			trainBlocks.add(trainBlock);
	}
	
	/*public Block getBlock(int TrainID)
	{
		return getTrainBlock(TrainID);	
	}
	*/
	
	@Override
	public Block getBlock(int blockNumber, String line) {
		// TODO Auto-generated method stub
		
		Block currentBlock;
		if(line.equals("Red"))
		{
			return redBlocks.get(blockNumber-1);
		}
		else 
		{
			return greenBlocks.get(blockNumber-1);
		}
	}
	
	@Override
	public Block getTrainBlock(int TrainID) {
		// TODO Auto-generated method stub
		
		if(redBlocks != null)
		{
			for(Block reds: redBlocks)
			{
				if(reds.getTrainID() == TrainID)
				{
					return reds;
				}
			}
		}

		if(greenBlocks != null)
		{
			for(Block greens: greenBlocks)
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
		if(redBlocks!=null)
		{
			for(Block reddies: redBlocks)
			{
				reddies.printBlock();
			}
		}
		if(greenBlocks != null)
		{
			
			for(Block greenies: greenBlocks)
			{
				greenies.printBlock();
				
			}
		}
	}
	
	@Override
	public void displayBlock(int blockNumber) {
		// TODO Auto-generated method stub

	}
}