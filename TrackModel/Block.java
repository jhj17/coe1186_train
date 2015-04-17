import java.util.ArrayList;


public class Block implements BlockInterface {

	private String line;
	private String section;
	private int blockNumber;
	private double blockLength;
	private double grade;
	private int speedLimit;
	private String station; //***
	private String switchBlock; //***
	private String underground; //***
	private double elevation;
	private double cumElevation;
	private String arrow;
	private String switchNumber; //***
	private int direction;
	private String crossing; //***
	private String switchType;
	private String stationSide;
//
	private boolean toYard = false;
	private boolean fromYard = false;
	private Block next;
	private Block previous;
	private int seen = 0;
//
	private Switch switcher = null;

	String stationName;
	double friction = 0.001;


	//..User configurable attributes..
	private boolean brokenBlock = false;
	private boolean closedBlock = false;

	//boolean brokenCircuit;
	
	//Usage attributes
	
	
//	..Usage attributes..

	private int trainID = 0;
	private boolean blockOccupied = false;
	boolean crossingOccurence;
	double commandedAuthority = 0;
	double commandedSpeed = 0;
	double distanceTraveled = 0;
	boolean lightsGreenTrueRedFalse;
	boolean beacon;
/*
	beaconPosition: double
	
	
	
	Crossing crossing = null;
	Station station = null; 
	
	
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

	
	if(station.equals("TO YARD") || station.equals("TO YARD/FROM YARD"))
	{
		toYard = true;
	}
	if(station.equals("FROM YARD") || station.equals("TO YARD/FROM YARD"))
	{
		fromYard = true;
	}

	if(toYard == false && fromYard == false && station.length()>0)
	{
		String[] stationStuff = station.split("-");
		station = stationStuff[0];
		stationSide = stationStuff[1];
	}

	if(lastCreated == null)
	{

	}
	else if(direction == 1 || direction == 2) //set the path 
	{
		next = lastCreated;

		if(next.getDirection() == -1)
			next.setNext(this);

		next.setPrevious(this);
	}
	else if(direction == -1)
	{
		previous = lastCreated;

		if(previous.getDirection() == 2)
			previous.setPrevious(this);
		else
			previous.setNext(this);
	}


//switches
//stations
//crossings
	}

	public void setNext(Block nextBlock)
	{

		next = nextBlock;
	}

	public String getLine()
	{
		return line;
	}

	public void setPrevious(Block previousBlock)
	{

		previous = previousBlock;
	}

	public void setSwitch(Switch aSwitch)
	{
		switcher = aSwitch;
	}

	public void toggleSwitch()
	{
		if(switcher!=null)
			switcher.toggleSwitch();
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
	public String getStation()
	{
		return station;
	}

	public String getStationSide()
	{
		return stationSide;
	}

	public String getSwitchNumber()
	{

		return switchNumber;
	}

	public Switch getSwitch()
	{
		return switcher;
	}
	
	public String getSwitchBlock()
	{
		return switchBlock;
	}
		
	public void setSeen(int i)
	{
		seen = i;
	}

	private void setTrainID(int ID)
	{
		trainID = ID;
	}
	public Block traverse()
	{
		Block returnBlock = null;
		seen = 1;
		boolean zeroNext = false;
		boolean zeroPrevious = false;

		if(direction == 1 || direction == -1)
		{

			if(this.getNext() == null)
			{
				returnBlock = this;

			}
			else
			{
				returnBlock = this.getNext();
			}

			if(this.getPrevious()!= null)
			{
				this.getPrevious().setSeen(0);
			}
		}

		else
		{
			if(this.station.equals("TO YARD/FROM YARD"))
			{
				returnBlock = this.getNext();
				if(returnBlock == null)
				{
					returnBlock = this;
				}
			}
			else if(this.getNext() == null)
			{
				returnBlock = this;
			}
			else if(this.getPrevious() == null)
			{
				returnBlock = this;
			}
			else if(this.getNext().getSeen() == 1)
			{
				returnBlock = this.getPrevious();
				zeroNext = true;
			}
			else if(this.getPrevious().getSeen() == 1)
			{
				returnBlock = this.getNext();
				zeroPrevious = true;

			}

			if(returnBlock != null && this.getArrow().equals("Head") && returnBlock.getArrow().equals("Head") && (returnBlock.getDirection() == 1 || returnBlock.getDirection() == -1)) //going wrong way on 1-way case
			{
				returnBlock = this;
				zeroPrevious = false;
				zeroNext = false;
			}
		}

		if(zeroPrevious)
		{
			this.getPrevious().setSeen(0);
		}

		if(zeroNext)
		{
			this.getNext().setSeen(0);
		}
		//System.out.println(this.getSection() + this.getBlockNumber());

		return returnBlock;

	}

	public String getSwitchType()
	{
		return switchType;
	}		
	public String getArrow()
	{

		return arrow;
	}

	public Block placeTrain(int train, double distanceMoved)
	{
		//System.out.println("Train place " + this.section + " " + this.blockNumber);
		trainID = train;
		blockOccupied = true;
		return this.moveTrain(distanceMoved);	
	}
	public Block moveTrain(double moved)
	{
		
		double newDist = moved + distanceTraveled;
		//System.out.println("In " + this.section + " " + this.blockNumber + " moved: " + newDist + "Length:"+ this.blockLength);

		Block currentBlock = this;
		if(newDist>=blockLength)
		{

			Block temp = currentBlock;
			blockOccupied = false;
			distanceTraveled = 0;
			newDist = newDist - blockLength;
			//((Block) this.getNext()).placeTrain(trainID, newDist); <----------- traversal move 
			currentBlock = this.traverseTrain(trainID); 
			if(temp == currentBlock) //THIS IS THE JOB OF THE WAYSIDE... DO NOT ACTUALLY IMPLEMENT
			{
				currentBlock.toggleSwitch();
				currentBlock = currentBlock.traverseTrain(trainID);
				System.out.println("toggle");
			}
			currentBlock =currentBlock.placeTrain(trainID,newDist);
			//trainID = 0;
		}
		else
		{
			distanceTraveled = newDist;
		}
		
		//System.out.print("Train is currently in block: " + currentBlock.getSection() + " " + currentBlock.getBlockNumber());
		return currentBlock;
		
	}
	
public Block traverseTrain(int train)
	{
		Block returnBlock = null;
		//System.out.println(this.getSection() + this.getBlockNumber());
		//System.out.println(this.getNext());
		//System.out.println(this.getPrevious());
		boolean zeroNext = false;
		boolean zeroPrevious = false;

		if(direction == 1 || direction == -1)
		{

			if(this.getNext() == null)
			{
				returnBlock = this;

			}
			else
			{
				returnBlock = this.getNext();
			}

			if(this.getPrevious()!= null)
			{
				zeroPrevious = true;
			}
		}

		else
		{
			if(this.station.equals("TO YARD/FROM YARD"))
			{
				returnBlock = this.getNext();
				if(returnBlock == null)
				{
					returnBlock = this;
				}
				System.out.println("got here");
				System.out.println(returnBlock);
			}
			else if(this.getNext() == null)
			{
				returnBlock = this;
			}
			else if(this.getPrevious() == null)
			{
				returnBlock = this;
			}
			else if(this.getNext().getTrainID() == train)
			{
				returnBlock = this.getPrevious();
				zeroNext = true;
			}
			else if(this.getPrevious().getTrainID() == train)
			{
				returnBlock = this.getNext();
				zeroPrevious = true;

			}

			if(returnBlock != null && this.getArrow().equals("Head") && returnBlock.getArrow().equals("Head") && (returnBlock.getDirection() == 1 || returnBlock.getDirection() == -1)) //going wrong way on 1-way case
			{
				returnBlock = this;
				zeroPrevious = false;
				zeroNext = false;
			}
		}

		if(zeroPrevious)
		{
			this.getPrevious().setTrainID(0);
		}

		if(zeroNext)
		{
			this.getNext().setTrainID(0);
		}
		//System.out.println(this.getSection() + this.getBlockNumber());

		return returnBlock;

	}

	public void printBlock()
	{
		

		
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
		
		if(switchNumber.length()>0)
			return true;
		else
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


	public double getTrainCommandedSpeed() {
		// TODO Auto-generated method stub
		return commandedSpeed;
	}


	public double getTrainAuthority() {
		// TODO Auto-generated method stub
		return commandedAuthority;
	}
	
	public void setAuthority(double authority)
	{
		commandedAuthority = authority;
	}

	@Override
	public double getFrictionCoefficient() {
		// TODO Auto-generated method stub
		return friction;
	}


	public double getGrade() {
		// TODO Auto-generated method stub
		return grade;
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
	
		closedBlock = true;
	}

	public void openBlock()
	{
		closedBlock = false;
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

	public String toString()
	{
		return this.getSection() + "\t" + this.getBlockNumber() + "\t" + this.isBlockOccupied() + "\t" + this.isBroken() + "\t" +this.isClosed();
	}

	public boolean isBroken()
	{
		return brokenBlock;
	}

	public boolean isClosed()
	{
		return closedBlock;
	}

}
