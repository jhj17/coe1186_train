import java.util.ArrayList;


public class Block implements BlockInterface {

	String line;
	String section;
	int blockNumber;
	double blockLength;
	double grade;
	int speedLimit;
	String station; //***
	String switchBlock; //***
	String underground; //***
	double elevation;
	double cumElevation;
	String arrow;
	String switchNumber; //***
	int direction;
	String crossing; //***
	String switchType;


//String infrastructure
// ... to yard ... 

	Block next;
	Block previous;

//
	String stationName;
	double friction = 0.001;

	//ArrayList<Block> switchNext1;
	//ArrayList<Block> switchNext2;
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
	
public Block(String[] splitStrings, Block lastCreated) {
		// TODO Auto-generated constructor stub
	
	line = splitStrings[0];
	section = splitStrings[1];
	blockNumber = Integer.parseInt(splitStrings[2]);
	blockLength = Double.parseDouble(splitStrings[3]);
	grade = Double.parseDouble(splitStrings[4]);
	speedLimit = Integer.parseInt(splitStrings[5]);
	station = splitStrings[6];
	switchBlock = splitStrings[7];
	underground = splitStrings[8];
	elevation = Double.parseDouble(splitStrings[9]);
	cumElevation = Double.parseDouble(splitStrings[10]);
	switchNumber = splitStrings[11];
	arrow=splitStrings[12]; //head no longer needed ???
	direction = Integer.parseInt(splitStrings[13]);
	crossing = splitStrings[14];
	switchType = splitStrings[15];

	

	if(lastCreated == null)
	{

	}
	else if(direction == 1 || direction == 2) //set the path 
	{
		next = lastCreated;
		
		if(next.getDirection() == -1)
		{
			next.setNext(this);
		}

		next.setPrevious(this);
	}
	else if(direction == -1)
	{
		
		previous = lastCreated;

		if(previous.getDirection() == 2)
		{
			previous.setPrevious(this);
		}
		else{
			previous.setNext(this);
		}
		
	}


//switches
//stations
//crossings

/*
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
	
	
	//make a switch.......
	
	*/

	}

	public void setNext(Block nextBlock)
	{

		next = nextBlock;
	}

	public void setPrevious(Block previousBlock)
	{

		previous = previousBlock;
	}



	public Block getNext() {
	// TODO Auto-generated method stub
		return next;
	}
	
	public Block getPrevious() {
		// TODO Auto-generated method stub
		return previous;
	}


	public String getSection()
	{
		
		return section;
		
	}

	public String getSwitchNumber()
	{

		return switchNumber;
	}
	
	public String getSwitchBlock()
	{
		return switchBlock;
	}
		


	public String getSwitchType()
	{

		return switchType;
	}		


	public String getArrow()
	{

		return arrow;
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
		
		
	//	blockLength = Integer.parseInt(splitStrings[3]);
	//	grade = Double.parseDouble(splitStrings[4]);
	//  speedLimit = Integer.parseInt(splitStrings[5]);
	//	infrastructure = splitStrings[6];
		
	}
	
	
	public void printSwitch()
	{
		
		
		
		

		
	}

	
	public int getDirection()
	{
		
		return direction;
	}
	
	public boolean getBeacon()
	{
		
		return beacon;
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

	
	private void setSeen(int i) {
		// TODO Auto-generated method stub
		seen = i;
		
	}

	private int getSeen() {
		// TODO Auto-generated method stub
		return seen;
	}

	// TODO Auto-generated method stub
	
	


	
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
		return direction;
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
