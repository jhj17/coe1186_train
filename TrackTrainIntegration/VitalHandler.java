
public class VitalHandler {
	//private final double KI = .5;
	//private final double KP = .8;
	private final double SCALAR = 1000;
	private final double MAXSPEED = 19;
	private final double KI = .01*SCALAR;
	private final double KP = 10*SCALAR;
	private double uk = 0;
	private double uk_prev = 0;
	private double ek = 0;
	private double ek_prev = 0;
	private double maxEnginePower = 120000; //in W
	private final double BUFFER = 5;
	public VitalHandler(){
		
	}
	public double calculatePower(TrainState ts){
		//System.out.println(ts.commandedSpeed);
		ek = ts.commandedSpeed - ts.tv.curSpeed;
		uk = uk_prev + ts.SampleRate/2 * (ek + ek_prev);
		ts.commandedPower = KP*(ek) + KI*(uk);
		if(ts.commandedPower > maxEnginePower){
			uk = uk_prev;
			ts.commandedPower = KP*(ek) + KI*(uk);
		}
		//System.out.println("ek: " + ek + " uk: " + uk);
		ek_prev = ek;
		uk_prev = uk;
		return ts.commandedPower;
	}
	public int shouldBrake(TrainState ts){ // either 1,2,or 0 
		if(shouldBrakeError(ts) || shouldBrakeUserEmergency(ts) || velError(ts) || noBackwards(ts)){
			ts.shouldEmergency = true;
			ts.shouldService = false;
			return 1;
		}
		else if(shouldBrakeAuthority(ts) || shouldStationBrake(ts) || shouldBrakeUserService(ts)){
			ts.shouldService = true;
			ts.shouldEmergency = false;
			return 2;
		}
		else{
			ts.shouldEmergency = false;
			ts.shouldService = false;
			return 0;
		}
	}
	private boolean velError(TrainState ts){
		if(ts.commandedSpeed<ts.tv.curSpeed){
			//System.out.println("VEL ERROR");
			return true;
		}
		return false;
	}
	private boolean noBackwards(TrainState ts){
		if(ts.tv.curSpeed < 0){
			return true;
		}
		return false;
	}
	private boolean shouldBrakeAuthority(TrainState ts){
		double vel = ts.tv.curSpeed + ts.tv.curAcceleration*ts.SampleRate;
		double brake = 2*ts.SBrakeRate;
		double auth = ts.calculatedAuthority;
		if(Math.pow(vel, 2)/brake >= auth - this.BUFFER){
			//System.out.println("Brake for AUTHORITY");
			return true;
		}
		return false;
	}
	private boolean shouldBrakeError(TrainState ts){
		if(ts.fails != 0){
			return true;
		}
		return false;
	}
	private boolean shouldBrakeUserEmergency(TrainState ts){
		return ts.userEmergencyBrake;
	}
	private boolean shouldStationBrake(TrainState ts){
		if(ts.atStation){ //make better later
			return true;
		}
		return false;
	}
	private boolean shouldBrakeUserService(TrainState ts){
		return ts.userServiceBrake;
	}
	
}
