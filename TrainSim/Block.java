import java.util.ArrayList;


public class Block implements BlockInterface {

	String line;
	String section;
	int blockNumber;
	double blockLength;
	double grade;
	int speedLimit;
	String infrastructure;
	double elevation;
	double cumElevation;
	int directionBlock;
	String arrow;
	String stationName;
	double friction = 0.001;
	
	Block next1;
	Block next2;

	ArrayList<Block> switchNext1;
	ArrayList<Block> switchNext2;
	
	int seen = 0;

	
	//..User configurable attributes..
	//boolean brokenBlock;
	//boolean brokenCircuit;
	
	//Usage attributes
	
	
//	..Usage attributes..

	int trainID;
	boolean blockOccupied;
	boolean crossingOccurence;
	double commandedAuthority = 0;
	double commandedSpeed;
	
	
	
	double distanceTraveled;

	boolean lightsGreenTrueRedFalse;
	boolean blockClosed;


	boolean beacon;
	
/*
	beaconPosition: double
	
	
	
	Crossing crossing = null;
	Station station = null; 
	Switch switch = null;
	
	
	*/
	
	
	
	
	
	
	
	
	
	public Block(String[] splitStrings, Block currentBlock) {
		// TODO Auto-generated constructor stub
	
	line = splitStrings[0];
	section = splitStrings[1];
	blockNumber = Integer.parseInt(splitStrings[2]);
	blockLength = Double.parseDouble(splitStrings[3]);
	grade = Double.parseDouble(splitStrings[4]);
	speedLimit = Integer.parseInt(splitStrings[5]);
	infrastructure = splitStrings[6];
	
	
	if(infrastructure != null && infrastructure.length()>0)
	{
		String[] infrastructureArray;
		if(infrastructure.substring(0,2).equals("ST"))
		{
			
			infrastructureArray = infrastructure.split(";");
			stationName = infrastructureArray[1];
			//System.out.println(this.getSection() + " testtt" + this.stationName);
		}
		
		
		
	}
	 	
	
	if(splitStrings[7]!= null && splitStrings[7].equals("B"))
	{

		beacon = true;
	}
	//if types of infrastructures... make station/crossing 
	
	elevation = Double.parseDouble(splitStrings[8]);
	cumElevation = Double.parseDouble(splitStrings[9]);
	
	
	if(splitStrings[11] == null)
	{
		arrow = "mid";
		
	}
	else
	{
		arrow = splitStrings[11];

	}
	
	// change this when direction has been determined 
	directionBlock = 0;

	
	
	//make a switch.......
	
	setNext1(currentBlock);
	

	}

	public String getSection()
	{
		
		return section;
		
	}
	public void addSwitchBlock1(Block switchBlock)
	{
		
		if(switchNext1 == null)
		{
			switchNext1 = new ArrayList<Block>();
		}
		
		switchNext1.add(switchBlock);
	}
	
	public void addSwitchBlock2(Block switchBlock)
	{
		if(switchNext2 == null)
		{
			switchNext2 = new ArrayList<Block>();
		}
		
		switchNext2.add(switchBlock);
	}
	
		
	public void placeTrain(int train, double distanceMoved)
	{
		
		System.out.println("Train place " + this.section + " " + this.blockNumber);
		trainID = train;
		blockOccupied = true;
		this.moveTrain(distanceMoved);
		
	}
	
	public double moveTrain(double moved)
	{
		
		double newDist = moved + distanceTraveled;
		System.out.println("In " + this.section + " " + this.blockNumber + " moved: " + newDist + "Length:"+ this.blockLength);

		Block currentBlock = this;
		if(newDist>blockLength)
		{
			
			blockOccupied = false;
			distanceTraveled = 0;
			newDist = newDist - blockLength;
			((Block) this.getNext()).placeTrain(trainID, newDist);
			trainID = 0;
			currentBlock = (Block) this.getNext();

		}
		else
		{
			
			distanceTraveled = newDist;

		}
		
		//System.out.print("Train is currently in block: " + currentBlock.getSection() + " " + currentBlock.getBlockNumber());
		return 0.0;
		
	}
	
	public void printBlock()
	{
		
		if(this.getNext1()!= null && this.getNext2()!= null)
		{
			System.out.println(line + " " + section + " " + blockNumber + " " + "direct. " + directionBlock + " next1 " + this.getNext1().getSection() + " next2 " + this.getNext2().getSection());

		}
		else if(this.getNext1()!= null)
		{
			System.out.println(line + " " + section + " " + blockNumber + " " + "direct. " + directionBlock + " next1 " + this.getNext1().getSection() + " next2 " + this.getNext2());

			
		}
		
		else if(this.getNext2()!= null)
		{
			System.out.println(line + " " + section + " " + blockNumber + " " + "direct. " + directionBlock + " next1 " + this.getNext1() + " next2 " + this.getNext2().getSection());

			
		}
		
		else
		{
			
			System.out.println(line + " " + section + " " + blockNumber + " " + "direct. " + directionBlock + " next1 " + this.getNext1() + " next2 " + this.getNext2());

		}
		

//		blockLength = Integer.parseInt(splitStrings[3]);
	//	grade = Double.parseDouble(splitStrings[4]);
		//speedLimit = Integer.parseInt(splitStrings[5]);
	//	infrastructure = splitStrings[6];
		
		
	}
	
	
	public void printSwitch()
	{
		
		this.printBlock();
		
		if(switchNext1 != null)
		{
			System.out.print("next1: ");
			for(int i=0;i<switchNext1.size();i++)
			{
				System.out.print(switchNext1.get(i).getSection() + " " + switchNext1.get(i).getBlockNumber() + ", ");
			}
			System.out.println();


		}
		if(switchNext2 != null)
		{
			System.out.print("next2: ");
			for(int i=0;i<switchNext2.size();i++)
			{
				System.out.print(switchNext2.get(i).getSection() + " " + switchNext2.get(i).getBlockNumber() + ", ");
			}
			System.out.println();

		}
		

		
	}
	public String getDirectionArrow()
	{
		return directionBlock + arrow;
	}
	
	public String getArrow()
	{
		
		return arrow;
	}
	
	public int getDirection()
	{
		
		return directionBlock;
	}
	
	public void setDirection(int direction)
	{
		
		directionBlock = direction;
	}

	
	public void setNext2(Block next2Block)
	{
		
		next2 = next2Block;
	}
	
	public void setNext1(Block next1Block)
	{
		
		next1 = next1Block;
	}
	
	public Block getNext2()
	{
		
		return next2;
	}
	
	public Block getNext1()
	{
		
		return next1;
	}
	
	public String getBeacon()
	{
		if(beacon)
		{	
			return stationName;
		}
		else
		{	
			return "";
		}

	}
	@Override
	public boolean isSwitch() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCrossing() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isStation() {
		// TODO Auto-generated method stub
		return false;
	}


	public double getTrainCommandedSpeed(int TrainID) {
		// TODO Auto-generated method stub
		return commandedSpeed;
	}


	public double getTrainAuthority(int TrainID) {
		// TODO Auto-generated method stub
		return commandedAuthority;
	}
	
	public void setAuthority(double authority)
	{
		
		commandedAuthority = authority;
	}

	@Override
	public double getFrictionCoefficient(int TrainID) {
		// TODO Auto-generated method stub
		return friction;
	}


	public double getGrade(int TrainID) {
		// TODO Auto-generated method stub
		return grade;
	}
	public Block getNext() {
		// TODO Auto-generated method stub
		
		if(this.getDirection() == 1)
		{
			return this.getNext1();
		}
		else
		{
			seen = 1;
			// this is a bit suspect
			if(this.getNext1().getSeen() == 0 && this.getNext2().getSeen() == 0)
			{
				if(this.getSection().equals(this.getNext1().getSection()))
				{
					return this.getNext1();
				}
				else
				{
					return this.getNext2();
				}
			}
			else if(this.getNext1().getSeen() == 0)
			{
				this.getNext2().setSeen(0);
				return this.getNext1();
			}
			else
			{
				this.getNext1().setSeen(0);
				return this.getNext2();
			}
		}
	}
	private void setSeen(int i) {
		// TODO Auto-generated method stub
		seen = i;
		
	}

	private int getSeen() {
		// TODO Auto-generated method stub
		return seen;
	}

	// TODO Auto-generated method stub
	
	

	
	
	@Override
	public BlockInterface getPrevious() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public double getBlockLength() {
		// TODO Auto-generated method stub
		return blockLength;
	}

	
	public int getBlockSpeedLimit() {
		// TODO Auto-generated method stub
		return speedLimit;
	}

	
	public boolean isBlockOccupied() {
		// TODO Auto-generated method stub
		return blockOccupied;
	}

	

	public int getBlockDirection() {
		// TODO Auto-generated method stub
		return directionBlock;
	}

	@Override
	public boolean isBlockWorking() {
		// TODO Auto-generated method stub
		return false;
	}


	public int getBlockNumber() {
		// TODO Auto-generated method stub
		return blockNumber;
	}

	public ArrayList<Block> getSwitchBlocks1() {
		// TODO Auto-generated method stub
		return switchNext1;
	}
	
	public ArrayList<Block> getSwitchBlocks2() {
		// TODO Auto-generated method stub
		return switchNext1;
		
	}
	
	public void closeBlock()
	{
		
		blockClosed = true;
	}

	public int getTrainID() {
		// TODO Auto-generated method stub
		return trainID;
	}

	public void setCommandedSpeed(double commandedSpeed2) {
		// TODO Auto-generated method stub
		
		commandedSpeed = commandedSpeed2;
		
	}

	public boolean toggleRedGreen() {
		// TODO Auto-generated method stub
		
		return !(lightsGreenTrueRedFalse);
		

	}

	public int getSpeedLimit() {
		// TODO Auto-generated method stub
		return speedLimit;
	}

}
