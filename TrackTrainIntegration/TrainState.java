
public class TrainState {
final double SampleRate = .01;
final double EBrakeRate = 2.73; //need real value
final double SBrakeRate = 1.2; //need real value
final double StationStopTime = 300; //seconds
final double MAXPOWER = 120000;
public DynamicTrainValues tv; //contains curSpeed, curAuthority, commandedSpeed, distance, curTemp
public boolean isAutopilot = false;
public boolean userEmergencyBrake = false;
public boolean userServiceBrake = false;
public boolean shouldEmergency = true; //should eBrake
public boolean shouldService = false; //should servBrake
public boolean shouldLight = false;
public boolean shouldLeftDoor = false;
public boolean shouldRightDoor = false;
public double desTemp;
public double userDesSpeed = 0;
public double commandedPower = 0;
public boolean atStation = false;
public boolean approachingStation = false;
public byte fails; //4 different kinds, 00,01,10,11
public String curBeacon;
public String stationName;
public double dwellTime;
public String stationSide; //LEFT, RIGHT
public boolean stationAnnounced;
public double timeOfStationArrival;
public boolean isEmergency = true;
public boolean isService = false;
public boolean isLeftDoor = false;
public boolean isRightDoor = false;
public boolean isLight = false;
public double curTime;
public String curTimeString;
public double calculatedAuthority = 0;
public double previousDistance = 0;
public double commandedSpeed =0;
	public TrainState()
	{
		tv = new DynamicTrainValues(0,0,0,0,0,0);
	}
	public TrainState( TrainState other){
		 this.tv = other.tv;
		 this.isAutopilot = other.isAutopilot;
		 this.userEmergencyBrake = other.userEmergencyBrake;
		 this.userServiceBrake = other.userServiceBrake;
		 this.shouldEmergency = other.shouldEmergency;
		 this.shouldService = other.shouldService;
		 this.shouldLight = other.shouldLight;
		 this.shouldLeftDoor = other.shouldLeftDoor;
		 this.shouldRightDoor = other.shouldRightDoor;
		 this.desTemp = other.desTemp;
		 this.userDesSpeed = other.userDesSpeed;
		 this.calculatedAuthority=other.calculatedAuthority;
		 this.commandedPower = other.commandedPower;
		 this.atStation = other.atStation;
		 this.approachingStation = other.approachingStation;
		 this.fails = other.fails;
		 this.curBeacon = other.curBeacon;
		 this.stationName = other.stationName;
		 this.dwellTime = other.dwellTime;
		 this.stationSide = other.stationSide;
		 this.stationAnnounced = other.stationAnnounced;
		 this.timeOfStationArrival = other.timeOfStationArrival;
		 this.isEmergency = other.isEmergency;
		 this.isService = other.isService;
		 this.isLeftDoor = other.isLeftDoor;
		 this.isRightDoor =  other.isRightDoor;
		 this.isLight = other.isLight;
		 this.curTime = other.curTime;
		 this.curTimeString = other.curTimeString;
		 this.previousDistance = other.previousDistance;
		 this.commandedSpeed = other.commandedSpeed;
	}
}
