
public class TrainState {
final double SampleRate = .002;
final double EBrakeRate = 100; //need real value
final double SBrakeRate = 100; //need real value
final double StationStopTime = 2; //need real values
public DynamicTrainValues tv; //contains curSpeed, curAuthority, commandedSpeed, distance, curTemp
public boolean isAutopilot;
public boolean userEmergencyBrake;
public boolean userServiceBrake;
public boolean shouldEmergency; //should eBrake
public boolean shouldService; //should servBrake
public boolean shouldLight;
public boolean shouldLeftDoor;
public boolean shouldRightDoor;
public double desTemp;
public double userDesSpeed;
public double commandedPower;
public boolean atStation;
public boolean approachingStation;
public byte fails; //4 different kinds, 00,01,10,11
public String curBeacon;
public String stationName;
public double distToStation;
public String stationSide; //LEFT, RIGHT
public boolean stationAnnounced;
public double timeOfStationArrival;
public boolean isEmergency;
public boolean isService;
public boolean isLeftDoor;
public boolean isRightDoor;
public boolean isLight;
public double curTime;
	public TrainState()
	{
	}
}
