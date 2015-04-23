public interface BlockInterface {

/*..CSV input data..
		trackSection: string
		blockNumber: int
		blockLength: int
		grade: double
		elevation: double
		cumulativeElevation: double
		directionBlock: int
		speedLimit: int


		..User configurable attributes..
		brokenBlock: boolean
		brokenCircuit: boolean
		..Usage attributes..
		next: Block
		previous: Block
		trainID: Train[]
		blockOccupied: boolean
		crossingOccurence: boolean
		commandedAuthority: double
		commandedSpeed: double

		blockClosed: boolean
		lightsGreenTrueRedFalse: boolean
		distanceTraveled: double
		beaconPosition: double
		
		
		
		Crossing crossing = null;
		Station station = null; 
		Switch switch = null;
*/
	
	
	
		/*
		 * 
		 * returns: true if switch/crossing/station, false if not 
		 */
		public boolean isSwitch();
		public boolean isCrossing();
		public boolean isStation();


		/*
		 * 
		 * returns: quantity asked for 
		 */
		public double getTrainCommandedSpeed();
		public double getTrainAuthority();
		public double getFrictionCoefficient();
		public double getGrade();

		
		/*
		 * for use in traversal--
		 * returns: next or previous block 
		 * 
		 */
		public BlockInterface getNext();
		public BlockInterface getPrevious();

		
		/*
		 * 
		 * return appropriate values
		 * 
		 */
		public double getBlockLength();
		public int getBlockSpeedLimit();
		public boolean isBlockOccupied();
		
		
		/*
		 * 
		 * 
		 * CHECK ON THIS 
		 * 
		 */
		public int getBlockDirection();
		
		
		/*
		 * closed/broken blocks here... if circuit is broken, will not register as broken...also, also when blocks fail the track seems occupied 
		 * 
		 * return: true if working, false if broken 
		 * 
		 */
		public boolean isBlockWorking();

		
		
	/*	-moveTrain();
		-findSwitch();
		-setNext();
		-breakBlock();
		-setBlockSpeed();
		-addBlockDistance();
		-moveTrain();
		-displayBlock();
		
		*/
	
	
}