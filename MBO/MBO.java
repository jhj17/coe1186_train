/**
* MBO.java
*
* @author John Abraham
*
*/

public class MBO{

	/*
	* Calculates the safe speed based on a given train and next train's locations. 
	* Get train location from TrainModel objects container using trainID.
	*/
	public double getSpeed(int trainID) {
		if(mboMode)
			return -1;
		else {


			return 0;
		}
	}

	/*
	* Calculates the safe authority based on a given train's suggested speed.
	*/
	public double getAuthority(int trainID) {
		if(mboMode)
			return -1;
		else {

			
			return 0;
		}
	}
}