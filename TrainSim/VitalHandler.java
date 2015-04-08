
public class VitalHandler {
	private final double KI = .5;
	private final double KP = .8;
	private double uk = 0;
	private double uk_prev = 0;
	private double ek = 0;
	private double ek_prev = 0;
	private double maxEnginePower = 120; //in kiloWatts
	public VitalHandler(){
		
	}
	public double calculatePower(TrainState ts){
		ek = ts.tv.commandedSpeed - ts.tv.curSpeed;
		uk = uk_prev + ts.SampleRate/2 * (ek + ek_prev);
		ts.commandedPower = KI*(ek) + KP*(uk);
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
		if(shouldBrakeAuthority(ts) || shouldBrakeError(ts) || shouldBrakeUserEmergency(ts) || velError(ts)){
			ts.shouldEmergency = true;
			ts.shouldService = false;
			return 1;
		}
		else if(shouldStationBrake(ts) || shouldBrakeUserService(ts)){
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
		if(ts.tv.commandedSpeed<ts.tv.curSpeed)
			return true;
		return false;
	}
	private boolean shouldBrakeAuthority(TrainState ts){
		double vel = ts.tv.curSpeed + ts.tv.curAcceleration*ts.SampleRate;
		double brake = 2*ts.EBrakeRate;
		double auth = ts.tv.curAuthority;
		if(Math.pow(vel, 2)/brake >= auth){
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
		if(ts.approachingStation || ts.atStation){ //make better later
			return true;
		}
		return false;
	}
	private boolean shouldBrakeUserService(TrainState ts){
		return ts.userServiceBrake;
	}
	
}
