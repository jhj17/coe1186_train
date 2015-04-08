
public interface TrainModelInterface {
	public DynamicTrainValues updateSamples(double power);
	public boolean emergencyBrake(boolean shouldEmergencyBrake);
	public boolean serviceBrake(boolean shouldServiceBrake);
	public boolean setLights(boolean shouldLights);
	public double setTemp(double cmdTemp);
	public boolean setLeftDoor(boolean leftDoor);
	public boolean setRightDoor(boolean rightDoor);
}
