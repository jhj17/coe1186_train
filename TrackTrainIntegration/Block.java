import java.util.ArrayList;
import java.util.Random;

//initialization: Authority: -1, Commanded Speed: speed limit
//for yard: Authority = first 2 blocks, Commanded Speed = 10 (arbitrarily chosen)

public class Block {

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
	private String crossing = null; //***
	private String switchType;
	private String stationSide;
	private int stationPeople = 0;
	private boolean toYard = false;
	private boolean fromYard = false;
	private Block next;
	private Block previous;
	private int seen = 0;
	private Switch switcher = null;
	private String stationName;
	private double friction = 0.001;

	//..User configurable attributes..
	private boolean brokenBlock = false;
	private boolean closedBlock = false;
	private boolean signalWorking = true;		
	private Crossing railroadCrossing= null;
	private int trainID = 0;
	private boolean blockOccupied = false;
	private boolean lightsGreenTrueRedFalse;
	private boolean beaconCommanded = false;
	private double commandedAuthority = -1;
	private double commandedSpeed = 0;
	private double distanceTraveled = 0;

	
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
	commandedAuthority = -1;
	commandedSpeed = speedLimit;
	
	if(station.equals("TO YARD") || station.equals("TO YARD/FROM YARD"))
	{
		toYard = true;
		//station = "";
	}
	if(station.equals("FROM YARD") || station.equals("TO YARD/FROM YARD"))
	{
		fromYard = true;
		commandedAuthority = 100;
		commandedSpeed = 10;
		//station = "";
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

	if(isStation())
	{
		Random pls = new Random();
		stationPeople = pls.nextInt(50);
	}

	commandedSpeed = speedLimit;

}


/*
	[Internal]
	Sets next block
*/
	public void setNext(Block nextBlock)  
	{
		next = nextBlock; 
	}
	//complete
	public String getLine()
	{
		return line;
	}
	//complete
	public void setPrevious(Block previousBlock)
	{
		previous = previousBlock;
	}
	//complete
	public void setSwitch(Switch aSwitch)
	{
		switcher = aSwitch;
	}
	//complete


	public int getStationPeople()  
	{
		return stationPeople;
	}


/*
	[Internal, Wayside]
	Toggles switch and all its connections if attached to a switch block. 
*/
	public void toggleSwitch()
	{
		if(switcher!=null)
			switcher.toggleSwitch();
	}
/*
	[Internal]
	Used for traversal.
*/	
	public Block getNext() 
	{
		return next;
	}
/*
	[Internal]
	Used for traversal.
*/	
	public Block getPrevious() {
		// TODO Auto-generated method stub
		return previous;
	}
	//complete
	public String getSection()
	{
		return section;	
	}
	//complete
	public String getStation()
	{
		return station;
	}
	//complete
	public String getStationSide()
	{
		return stationSide;
	}
	//complete
	public String getSwitchNumber()
	{

		return switchNumber; /*e.g. "Switch 6" */
	}
	//complete
	public Switch getSwitch()
	{
		return switcher;
	}
	//complete
	public String getSwitchBlock()
	{
		return switchBlock; /* e.g. SWITCH or "" */ 
	}
	/*
		This method is used only internally for setting up switches.  switchType contains a string "AFTER", "BEFORE", or "-", signifying whether the switch is part of a middle section
		that comes before or after the fork, as well as - to signify a normal 3-junction switch.
	*/	
	public String getSwitchType()
	{
		return switchType;
	}	
	/*
		This method is used only internally for two-directional traversal, since trains can go in two ways on some blocks and need to know where they've come from.
	*/	
	public void setSeen(int i)
	{
		seen = i; /*for traversal use */
	}
	/*
		Puts a train in a block. 
	*/
	private void setTrainID(int ID)
	{
		trainID = ID;
	}

	/*
		Chooses next block for track traversal based on where the train came from. 
	*/
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

		return returnBlock;

	}
	/*
		This method returns the type of arrow "Head" or "Tail" of the current block.
	*/	
	public String getArrow()
	{
		return arrow;
	}

	/*
		This method physically places a train in a block and changes the appropriate parameters. 
	*/	
	public Block placeTrain(int train, double distanceMoved)
	{
		System.out.println(train+" train moved to: " + this.section + " " + this.blockNumber);
		trainID = train;
		blockOccupied = true;
		return this.moveTrain(distanceMoved);	
	}
	/*
		This method simulates train movement.  Distance updates and is stored.  If it surpasses length of the block, train proceeds.  
	*/	
	public Block moveTrain(double moved)
	{
		
		double newDist = moved + distanceTraveled;
		//System.out.println("In " + this.section + " " + this.blockNumber + " moved: " + newDist + "Length:"+ this.blockLength);

		Block currentBlock = this;
		if(newDist>blockLength)
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
	
	/*
		This method simulates train movement.  Equivalent to traverse() but is used solely for trains.  
	*/	
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

	/*
		Returns 1 or -1 for a one-way block (-1 means constructed from tail to head), 2 for two-way. 
	*/			
	public int getDirection()
	{
		
		return direction;
	}

/*
	Returns true if part of a switch group.  
*/	
	public boolean isSwitch() {
		
		if(switchNumber.length()>0)
			return true;
		else
			return false;
	}
	
/*
	[Wayside] 
	sets the current authority;
*/
	public void setAuthority(double authority)
	{
		commandedAuthority = authority;
	}


/*
	[Internal]
	Returns 1 if this block has already been traversed, 0 if not.
*/	
	private int getSeen() {
		return seen;
	}
		
/*
	Returns length of current block.
*/			
	public double getBlockLength() {
		// TODO Auto-generated method stub
		return blockLength;
	}

/*
	Returns current block speed limit. ??? REDUNDANT ! GET RID OF THIS
*/	
	public int getBlockSpeedLimit() {
		return speedLimit;
	}

/*
	[Wayside]
	Returns boolean true if occupied, false if not occupied.
*/	
	public boolean isBlockOccupied() {
		return blockOccupied;
	}

/*
	[Internal]
	Returns 1 if this block has already been traversed, 0 if not. ??? REDUNDANT... remove. 
*/	
	public int getBlockDirection() {
		// TODO Auto-generated method stub
		return direction;
	}

/*
	hmmmm ??? not complete
*/	
	public boolean isBlockWorking() {
		// TODO Auto-generated method stub
		return false;
	}

/*
	[Internal]
	returns the integer number of the block
*/	
	public int getBlockNumber() {
		return blockNumber;
	}


/*
	[Internal]
	returns the ID of the train currently in the block.  if not there, 0. 
*/	
	public int getTrainID() 
	{
		return trainID;
	}


/*
	[Internal]
	returns speed limit of block
*/	
	public int getSpeedLimit() {
		// TODO Auto-generated method stub
		return speedLimit;
	}


/*
	??? not done yet
*/
	public boolean isCrossing() {
		
		if(crossing.equals("-"))
		{
			return false;
		}
		else
		{
			return true;
		} 
	}

/*
	[Wayside]
*/
	public Crossing getCrossing() {

		return railroadCrossing;
	}

	public void addCrossing(Crossing newCrossing)
	{

		railroadCrossing = newCrossing;
	}

/*
	[Internal]
*/
	public boolean isStation() {
		// TODO Auto-generated method stub
		
		if(station.length()>0 && (!toYard||!fromYard))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

													/*			TRAIN RELEVANT METHODS 			*/


/*
	[Train]
	Returns friction coefficient to the train.
*/
	public double getFrictionCoefficient() {
		return friction;
	}

/*
	[Train]
	Returns the grade to the train. 
*/
	public double getGrade() {
		// TODO Auto-generated method stub
		return grade;
	}

/*
	[Train]
	returns the current commanded authority from the Wayside.  
*/
	public double getTrainAuthority() {
		// TODO Auto-generated method stub
		double temp = commandedAuthority;
		commandedAuthority = -1;
		return temp;
	}

/*
	[Train]
	Returns the current commanded speed from the Wayside. 
*/	
	public double getTrainCommandedSpeed() {
		return commandedSpeed;
	}
/*
	[Train]
	If a station block and indicated to stop by the Wayside, beacon will send string of information to train like "station name", "L/R" side, 
	dwell time and passengers at stop. 
*/	
	public String getBeacon()
	{

		if(station.length()>0 && beaconCommanded && (!toYard||!fromYard))
		{
			beaconCommanded = false;
			return station + "," + stationSide + "," + "90" + "," + stationPeople;
		}
		else
		{
			return "";
		}
	}


													/*			WAYSIDE RELEVANT METHODS 			*/
/*
	[Wayside]
*/	
	public void toggleBroken()
	{
		brokenBlock= (!brokenBlock);
	}
/*
	[Wayside]
	Returns a string of this block including: section, block number, occupied (boolean), broken (boolean), closed (boolean)
*/	
	public String toString()
	{
		return (this.getSection() + "\t" + this.getBlockNumber() + "\t" + this.isBlockOccupied() + "\t" + this.isBroken() + "\t" +this.isClosed());
	}

/*
	[Wayside]
	Returns true if broken, false if not broken.
*/
	public boolean isBroken()
	{
		return brokenBlock;
	}

/*
	[Wayside]
	Returns true if closed, false if not closed.
*/
	public boolean isClosed()
	{
		return closedBlock;
	}


/*
	WAYSIDE
	Sets the commanded speed of the block. 
*/	
	public void setCommandedSpeed(double commandedSpeed2) 
	{		
		commandedSpeed = commandedSpeed2;
	}

/*
	[Wayside]
*/	
	public void toggleRedGreen(boolean trueGreen) {
		// TODO Auto-generated method stub
		lightsGreenTrueRedFalse	= trueGreen;
	}
/*
	[Wayside]
*/	
	public boolean getRedFalseGreenTrue()
	{
		return lightsGreenTrueRedFalse;
	}
/*
	[Wayside]
*/	
	public boolean isSignalWorking()
	{
		return signalWorking;
	}

	/*
	[Wayside]
	Closes a block for maintenance.
*/	
	public void closeBlock()
	{
		closedBlock = true;
	}

/*
	[Wayside]
	Opens a block for maintenance. 
*/	
	public void openBlock()
	{
		closedBlock = false;
	}

	public void setBeaconOn()
	{
		beaconCommanded = true;	
	}
}
