/**
* Scheduler.java
*
* @author John Abraham
*
*/

import java.util.ArrayList;

public class MBOScheduler{

//	private Track track = new Track();
	/*set*/
	private ArrayList<String> timeTableInfo = new ArrayList<String>();
	private String scheduleStartTime;
	/*return*/
	private ArrayList<String> trainSchedule = new ArrayList<String>();
 	private ArrayList<String> operatorSchedule = new ArrayList<String>();

	public void setTimeTable(ArrayList<String> timeTableInfo) {
		this.timeTableInfo = timeTableInfo;
	}

	public void setScheduleStartTime(String scheduleStartTime) {
		this.scheduleStartTime = scheduleStartTime.split("\\s")[3].substring(0, 5);
	}

	/**
	* Start creating schedules after all private variables are set
	*/
	public ArrayList<String> getTrainSchedule() {
		//start time


		//use getRoute() method in track.java
		//ArrayList<String> route = track.getRoute("", "");
		//
		//
		//
		//


		return trainSchedule;
	}

	/**
	* Given the number of trains used in the schedule assign operators
	*/
	public ArrayList<String> getOperatorSchedule() {

		return operatorSchedule;
	}
}
