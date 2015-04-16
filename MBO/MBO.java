/**
* MBO.java
*
* @author John Abraham
*
*/

public class MBO{

	private boolean mboMode;

	/*
	* Contructor
	*/
	public MBO(boolean mboMode) {
		this.mboMode = mboMode;
	}

	/*
	* Calculates the safe speed based on a given train and next train's locations. 
	*/
	public double calculateSuggestedSpeed() {
		if(mboMode)
			return -1;
		else {


			return 0;
		}
	}

	/*
	* Calculates the safe authority based on a given train's speed.
	*/
	public double calculateSuggestedAuthority() {
		if(mboMode)
			return -1;
		else {

			
			return 0;
		}
	}
}