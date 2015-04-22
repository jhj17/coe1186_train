
public class TrainController {
	TrainState ts;
	VitalHandler vh;
	TrainControllerGUI gui;
	TrainModelInterface tm;
	SimClock sm;
	
	public TrainController(SimClock sm){
		ts = new TrainState();
		vh = new VitalHandler();
		gui = new TrainControllerGUI(ts);
		this.sm = sm;
	}
	public void initTrainModel(TrainModel tmodel)
	{
		tm = tmodel;
		System.out.println("update samples called");
		ts.tv = tm.updateSamples(0);
	}
	public void tick(){
		updateTime();
		updateFromGUI();
		powerCalculation();
		updateFromTrackModel();
		vitalCalculations();
	}
	private void updateTime(){
		String time = sm.getCurrentTime();
		String[] parts= time.split(":");
		double hours = Double.parseDouble(parts[0]);
		double minutes = Double.parseDouble(parts[1]);
		double seconds = Double.parseDouble(parts[2]);
		ts.curTime = 3600*hours + 60*minutes + seconds;
		ts.curTimeString = time;
	}
	public boolean passBeacon(String b){
		if(!ts.approachingStation){
			ts.approachingStation = true;
			ts.curBeacon = b;
			decodeBeacon();
		}
		return true;
	}
	public boolean passFailure(byte fails){ //0 is no failure, 1 is Engine, 2 is Brake, 3 is Signal
		ts.fails = 1;
		return true;
	}
	public void updateFromGUI(){
		gui.updateUserSamples(ts);
	}
	public void updateFromTrackModel(){
		if(ts.isService || ts.isEmergency)
			ts.commandedPower = 0;
		if(ts.commandedPower> ts.MAXPOWER){
			ts.commandedPower = ts.MAXPOWER;
		}
		ts.tv=tm.updateSamples(ts.commandedPower);
		ts.calculatedAuthority = ts.calculatedAuthority - (ts.tv.distance-ts.previousDistance);
		ts.previousDistance = ts.tv.distance;
		if(ts.tv.curAuthority != -1){
			ts.calculatedAuthority = ts.tv.curAuthority;
		}
//		if(ts.tv.commandedSpeed != -1){
//			ts.commandedSpeed = ts.tv.commandedSpeed;
//		}
		//System.out.println("Authority "+ ts.calculatedAuthority);
		//System.out.println("Speed "+ ts.commandedSpeed);
	}
	public boolean vitalCalculations(){
		int brakeDecision = brakeCalculation();
		//System.out.println("Current Time: "+ ts.curTime + "   Station arrival time: " + ts.timeOfStationArrival);
		if(ts.approachingStation && !ts.atStation){
			stationArriveSequence();
		}
		else if(ts.atStation && ts.curTime - ts.timeOfStationArrival > ts.dwellTime){
			stationDepartSequence();
		}
		return true;
	}
	public int brakeCalculation(){
		int brakeDecision = vh.shouldBrake(ts);
		if(!transmitBraking()){
			System.out.println("ERROR IN BRAKE TRANSMISSION....trying again");
		}
		return brakeDecision;
	}
	public boolean transmitBraking(){
		if(ts.shouldEmergency != ts.isEmergency)
			ts.isEmergency = tm.emergencyBrake(ts.shouldEmergency);
		if(ts.shouldService != ts.isService)
			ts.isService = tm.serviceBrake(ts.shouldService);
		return ts.shouldEmergency == ts.isEmergency && ts.shouldService == ts.isService;
	}
	public void decodeBeacon(){
		String[] stuff = ts.curBeacon.split(",");
		ts.stationName = stuff[0];
		ts.stationSide = stuff[1];
		ts.dwellTime = Double.parseDouble(stuff[2]);
	}
	public boolean stationArriveSequence(){
		if(!ts.stationAnnounced){
			announceStation(ts.stationName);
			ts.stationAnnounced = true;
		}
		if(isAtStation()){
			ts.shouldLight = true;
			if(ts.stationSide.equals("LEFT"))
				ts.shouldLeftDoor = true;
			else
				ts.shouldRightDoor = true;
			communicateStationSignals();
		}
		return true;
	}
	public boolean stationDepartSequence(){
		System.out.println("DEPARTING STATION");
		announceStation("");
		ts.stationAnnounced = false;
		ts.shouldLight = false;
		if(ts.stationSide.equals("LEFT"))
			ts.shouldLeftDoor = false;
		else
			ts.shouldRightDoor = false;
		communicateStationSignals();
		ts.atStation = false;
		return true;
	}
	public double powerCalculation(){
		if(!ts.isAutopilot){
			ts.commandedSpeed = Math.min(ts.tv.commandedSpeed, ts.userDesSpeed);
		}
		else
			ts.commandedSpeed = ts.tv.commandedSpeed;
		return vh.calculatePower(ts);
	}
	public boolean isAtStation(){
		if(ts.approachingStation && ts.tv.curSpeed == 0 ){
			ts.atStation = true;
			ts.approachingStation = false;
			ts.timeOfStationArrival = ts.curTime;
			System.out.println("AT THE STATION");
		}
		return ts.atStation;
	}
	public boolean communicateStationSignals(){
		if(ts.isLight != ts.shouldLight)
			ts.isLight = tm.setLights(ts.shouldLight);
		if(ts.isLeftDoor != ts.shouldLeftDoor)
			ts.isLeftDoor = tm.setLeftDoor(ts.shouldLeftDoor);
		if(ts.isRightDoor != ts.shouldRightDoor)
			ts.isRightDoor = tm.setRightDoor(ts.shouldRightDoor);
		return true;	
	}
	private void announceStation(String name){
		System.out.println("NEXT STATION: "+ name);
	}
}
