/**
* MBO.java
*
* @author John Abraham
*
*/

//BRAKEACC = -1.2; m/s^2
//EBRAKEACC = -2.73; m/s^2
//d = (v^2)/(2ug)

public class MBOController{

	private boolean mboMode;

	/*
	* Calculates the safe speed based on a given train and next train's locations. 
	* Get train location from TrainModel objects container using trainID.
	*/
	public double getSpeed(int trainID) {
		if(!mboMode)
			return -1;
		else {


			return 0;
		}
	}

	/*
	* Calculates the safe authority based on a given train's suggested speed.
	*/
	public double getAuthority(int trainID) {
		if(!mboMode)
			return -1;
		else {

			
			return 0;
		}
	}

	public void setMBOMode(boolean mboMode) {
		this.mboMode = mboMode;
	}
}
