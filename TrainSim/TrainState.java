
public class TrainState {
final double SampleRate = .002;
final double EBrakeRate = 2.73; //need real value
final double SBrakeRate = 1.2; //need real value
final double StationStopTime = 2; //need real values
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
public double userDesSpeed;
public double commandedPower = 0;
public boolean atStation = false;
public boolean approachingStation = false;
public byte fails; //4 different kinds, 00,01,10,11
public String curBeacon;
public String stationName;
public double distToStation;
public String stationSide; //LEFT, RIGHT
public boolean stationAnnounced;
public double timeOfStationArrival;
public boolean isEmergency = false;
public boolean isService = false;
public boolean isLeftDoor = false;
public boolean isRightDoor = false;
public boolean isLight = false;
public double curTime;
	public TrainState()
	{
	}
}
