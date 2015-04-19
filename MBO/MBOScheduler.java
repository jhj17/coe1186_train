/**
* Scheduler.java
*
* @author John Abraham
*
*/

import java.util.ArrayList;

public class MBOScheduler{

	/*set*/
	private ArrayList<String> timeTableInfo = new ArrayList<String>();
	private ArrayList<String> operatorScheduleInfo = new ArrayList<String>();
	private String scheduleStartTime;
	/*return*/
	private ArrayList<String> trainSchedule = new ArrayList<String>();
 	private ArrayList<String> operatorSchedule = new ArrayList<String>();

	public void setTimeTable(ArrayList<String> timeTableInfo) {
		this.timeTableInfo = timeTableInfo;
	}

	public void setOperatorSchedule(ArrayList<String> operatorScheduleInfo) {
		this.operatorScheduleInfo = operatorScheduleInfo;
	}

	public void setScheduleStartTime(String scheduleStartTime) {
		this.scheduleStartTime = scheduleStartTime.split("\\s")[3].substring(0, 5);
	}

	/**
	* Start creating schedules after all private variables are set
	*/
	public ArrayList<String> getTrainSchedule() {




		return trainSchedule;
	}

	/**
	* Given the number of trains used in the schedule assign operators
	*/
	public ArrayList<String> getOperatorSchedule() {

		return operatorSchedule;
	}
}
