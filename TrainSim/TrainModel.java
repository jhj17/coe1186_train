import java.awt.EventQueue;
import java.util.*;
import java.lang.Thread;

public class TrainModel implements TrainModelInterface
{
	//public enum TrainModelFailures
	//{
	//	ENGINE,
	//	BRAKE,
	//	SIGNAL;

	//	public static final EnumSet<TrainModelFailures> TOTAL_FAILURE = EnumSet.allOf(TrainModelFailures.class);
	//}

	//public enum DoorStatus
	//{
	//	LEFT,
	//	RIGHT;

	//	public static final EnumSet<DoorStatus> BOTHDOORS = EnumSet.allOf(DoorStatus.class);
	//}

	//Constants
	private final double EMPTYMASS = 36378.1; // kg
	private final double LENGTH = 32.2; // m
	private final double WIDTH = 2.65; // m
	private final double HEIGHT = 3.42; // m
	private final double WHEELRADIUS = 270; // mm
	private final double MAXPOWER = 120000; // W
	private final double BRAKEACC = -1.2; // m/s^2
	private final double EBRAKEACC = -2.73; // m/s^2
	private final double GRAVITY = -9.81; // m/s^2
	private final double ROLLINGCOEFFICIENT = .001;
	private final double KINETICCOEFFICIENT = 0.58; // Sliding
	private final double PERSONMASS = 81; //kg
	private final int    MAXPASSENGERS = 222;
	private final double MAXACCELERATION = 2.73;
	private final int ID;
	
	// Train Info
	private double time = 0; //In seconds.
	private double power = 0; //In Watts.
	private double distance = 0; //In meters.
	private double speed = 0; //In meters per second.
	private double acceleration = 0; //In meters per second per second.
	private double temperature = 69; //In fahrenheit.
	private String lastStop = "Yard";
	
	private double mass = EMPTYMASS;

	private double authority = 0;
	private double commandedSpeed = 0;

	private double commandedTemperature = 69;
	
	private int numCrew = 2;
	private int numPassengers = 0;
	
	private boolean brake = false;
	private boolean eBrake = true;

	//EnumSet<TrainModelFailures> failures = EnumSet.noneOf(TrainModelFailures.class);
	//EnumSet<DoorStatus> doors = EnumSet.noneOf(DoorStatus.class);
	
	private byte failureMask = 0;

	private boolean engineFailure;
	private boolean brakeFailure;
	private boolean signalFailure;
	
	private boolean leftDoor = false;
	private boolean rightDoor = false;
	private boolean lights = false;

	// Simulation Info
	private long lastUpdate = 0;
	
	TrainController controller;
	TrackModel track;
	
	SimClock clock;
	
	//Block getBlock(int ID)
	
	//Block Methods:
	
	//double getGrade()
	//double getTrainAuthority
	//double getTrainCommandedSpeed
	//double getFrictionCoefficient
	//string getBeacon()

	public TrainModel(int ID, TrackModel track, TrainController controller, SimClock clock)
	{
		this.ID = ID;
		this.track = track;
		this.controller = controller;
		this.clock = clock;
	}

	public DynamicTrainValues updateSamples(double power)
	{
		// Take power and update speed and acceleration.
		double frictionForce = 0;
		double gravityForce = 0;
		double engineForce = 0;

		double deltaT = 0;
		
		Block curBlock = track.getBlock(ID);
		
		commandedSpeed = curBlock.getCommandedSpeed();
		authority = curBlock.getAuthority();
		
		String beacon = curBlock.getBeacon();
		if (!beacon.isEmpty())
		{
			controller.passBeacon(beacon);
		}
		
		//double grade = (Call to get grade from track)
		double grade = curBlock.getGrade();

		// Radians
		double theta = Math.atan2(grade, 100);

		long current = System.currentTimeMillis();

		if (lastUpdate == 0)
		{
			deltaT = 0;
		}
		else
		{
			deltaT = (current - lastUpdate) / 1000.0;
		}
		
		lastUpdate = current;

		// Limit power
		if (power > MAXPOWER)
		{
			power = MAXPOWER;
		}
		
		if (engineFailure)
		{
			power = 0;
		}
		
		if (eBrake)
		{
			acceleration = EBRAKEACC;
		}
		else if (brake)
		{
			acceleration = BRAKEACC;
		}

		double oldSpeed = speed;

		// vf = vi + at;
		speed += acceleration*deltaT;

		
		// If the brakes are on, the train stops at 0
		if ((brake || eBrake) && (speed < 0.05))
		{
			speed = 0;
			acceleration = 0;
		}
		// Else do the power calculation
		else if(!eBrake && !brake)
		{
			frictionForce = curBlock.getFrictionCoefficient() * mass * GRAVITY * Math.cos(theta);
			gravityForce = mass * GRAVITY * Math.sin(theta);

			engineForce = power / (speed + 0.00001);
			
			double totalForce = engineForce + gravityForce + frictionForce;
			
			// Engine overcomes gravity and friction
			if (totalForce > 0)
			{
				acceleration = totalForce / mass;
			}
			// Engine overcomes gravity, but not friction
			else if (engineForce > Math.abs(gravityForce))
			{
				acceleration = totalForce / mass;
			}
			// Gravity overcomes both engine and friction and slides backwards
			else if (Math.abs((engineForce - gravityForce)) > Math.abs(frictionForce))
			{
				acceleration = totalForce / mass;
			}
			else
			{
				acceleration = 0;
			}
		}
		
		
		acceleration = Math.min(acceleration, MAXACCELERATION);
		distance += (speed * deltaT) + ( (1.0/2.0)*(acceleration)*(deltaT * deltaT) );

		// Update temperature;
		if (commandedTemperature > temperature)
		{
			temperature += (0.00005 * deltaT);
		}
		else if (commandedTemperature < temperature)
		{
			temperature -= (0.00005 * deltaT);
		}

		if ((commandedTemperature - temperature) > -0.01 && (commandedTemperature - temperature) < 0.01)
		{
			temperature = commandedTemperature;
		}

		// Populate train values and return them
		DynamicTrainValues dtv = new DynamicTrainValues(speed, acceleration, authority, commandedSpeed, distance, temperature);
		return dtv;
	}

	// Setters
	public double setAuthority(double auth)
	{
		authority = auth;
		return authority;
	}

	public double setCommandedSpeed(double comSpeed)
	{
		commandedSpeed = comSpeed;
		return commandedSpeed;
	}

	public boolean emergencyBrake(boolean state)
	{
		eBrake = state;
		return eBrake;
	}

	public boolean serviceBrake(boolean state)
	{
		brake = state;
		return brake;
	}

	public boolean setLights(boolean state)
	{
		lights = state;
		return lights;
	}

	public double setTemp(double cmdTemp)
	{
		commandedTemperature = cmdTemp;
		return commandedTemperature;
	}
	
	public boolean setLeftDoor(boolean leftStatus)
	{
		leftDoor = leftStatus;
		return leftDoor;
	}
	
	public boolean setRightDoor(boolean rightStatus)
	{
		rightDoor = rightStatus;
		return rightDoor;
	}

	// Update the number of passengers on the train
	// Remove a random number of passengers and then add as many as possible up to a max of numBoarding
	// return the number of passengers that boarded the train
	public int updatePassengers(int numBoarding)
	{
		// Remove a random number of passengers
		Random rand = new Random();
		numPassengers -= numPassengers * rand.nextDouble();

		// Add more passengers up to the maximum, but no more than numBoarding
		int emptySpots = MAXPASSENGERS - numPassengers;

		if (emptySpots > numBoarding)
		{
			numPassengers += numBoarding;
			mass = EMPTYMASS + (PERSONMASS * numPassengers);
			return numBoarding;
		}
		else
		{
			numPassengers += emptySpots;
			mass = EMPTYMASS * (PERSONMASS * numPassengers);
			return emptySpots;
		}
	}

	// Getters
	public Position getPosition()
	{
		return new Position(distance, lastStop);
	}

	public int getNumPassengers()
	{
		return numPassengers;
	}

	public double getMass()
	{
		return mass;
	}
	
	public double getAuthority()
	{
		return authority;
	}
	
	public double getCommandedSpeed()
	{
		return commandedSpeed;
	}
}

class DynamicTrainValues
{
	public final double curSpeed;
	public final double curAcceleration;
	public final double curAuthority;
	public final double commandedSpeed;
	public final double distance;
	public final double curTemp;

	public DynamicTrainValues(double speed, double acceleration, double authority, double commandedSpeed, double distance, double temp)
	{
		curSpeed = speed;
		curAcceleration = acceleration;
		curAuthority = authority;
		this.commandedSpeed = commandedSpeed;
		this.distance = distance;
		curTemp = temp;
	}
	
	public String toString()
	{
		return "\nSpeed: " + curSpeed + 
			   "\nAcceleration: " + curAcceleration + 
			   "\nAuthority: " + curAuthority + 
			   "\nCommanded Speed: " + commandedSpeed + 
			   "\nDistance: " + distance + 
			   "\nTemperature: " + curTemp;
	}
}

class Position
{
	public final double distance;
	public final String lastStop;

	public Position(double distance, String lastStop)
	{
		this.distance = distance;
		this.lastStop = lastStop;
	}
}