/**
* MBOScheduler.java
*
* @author John Abraham
*
*/

import java.util.ArrayList;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;


public class MBOScheduler{

	/*set*/
	private ArrayList<String> throughputInfo = new ArrayList<String>();
	private String scheduleStartTime;
	private String scheduleStartTimeFinal;
	/*return*/
	private ArrayList<String> trainSchedule = new ArrayList<String>();
 	private ArrayList<String> operatorSchedule = new ArrayList<String>();

	public void setThroughputInfo(ArrayList<String> throughputInfo) {
		this.throughputInfo = throughputInfo;
	}

	public void setScheduleStartTime(String scheduleStartTime) {
		this.scheduleStartTime = scheduleStartTime.split("\\s")[3].substring(0, 5);
		scheduleStartTimeFinal = scheduleStartTime.split("\\s")[3].substring(0, 5);
	}

	/**
	* Start creating schedules after all private variables are set
	*/
	public ArrayList<String> getTrainSchedule() {
		for(int i=0; i<throughputInfo.size(); i+=2)
		{
			BufferedReader br = null;
			if(throughputInfo.get(i).equals("SHADYSIDE")||throughputInfo.get(0).equals("HERRON AVE")|| throughputInfo.get(0).equals("SWISSVILLE")||throughputInfo.get(0).equals("PENN STATION")|| throughputInfo.get(0).equals("STEEL PLAZA")||throughputInfo.get(0).equals("FIRST AVE")||throughputInfo.get(0).equals("STATION SQAURE")||throughputInfo.get(0).equals("SOUTH HILLS")) {

				//red line
				try {
					br = new BufferedReader(new FileReader(new File("redLine.txt")));
				} catch(FileNotFoundException e){
						System.out.println("File Not Found!");
				}
			} else if(throughputInfo.get(i).equals("DORMONT")||throughputInfo.get(i).equals("MT LEBANON")||throughputInfo.get(i).equals("POPLAR")||throughputInfo.get(i).equals("CASTLE SHANNON")) {
				//green line, bottom section
				try {
					br = new BufferedReader(new FileReader(new File("greenLineBottomSection.txt")));
				} catch(FileNotFoundException e){
						System.out.println("File Not Found!");
				}
			} else if(throughputInfo.get(i).equals("OVERBROOK")||throughputInfo.get(i).equals("INGLEWOOD")) {
				//green line, middle section
				try {
					br = new BufferedReader(new FileReader(new File("greenLineMiddleSection.txt")));
				} catch(FileNotFoundException e){
						System.out.println("File Not Found!");
				}
			} else if(throughputInfo.get(i).equals("south BANK")||throughputInfo.get(i).equals("WHITED")||throughputInfo.get(i).equals("STATION")||throughputInfo.get(i).equals("EDGEBROOK")||throughputInfo.get(i).equals("PIONEER")) {
				//green line, top section
				try {
					br = new BufferedReader(new FileReader(new File("greenLineTopSection.txt")));
				} catch(FileNotFoundException e){
						System.out.println("File Not Found!");
				}
			} else{
				trainSchedule.add("STATION NOT FOUND!");trainSchedule.add("STATION NOT FOUND!");
				trainSchedule.add("STATION NOT FOUND!");trainSchedule.add("STATION NOT FOUND!");
				trainSchedule.add("STATION NOT FOUND!");
			}

			try {
				int delta_x = 0; int delta_y = 0; int counter = 0; int trainID = 1;
				String s;
				ArrayList<String> stations = new ArrayList<String>();
				while ((s = br.readLine()) != null) {
					stations.add(s);
				}
				for(int j=0; j<stations.size(); j++) {
					String[] stationAndTime = stations.get(j).split(",");
					trainSchedule.add("1");
					trainSchedule.add("RED");
					trainSchedule.add(stationAndTime[0]);
					trainSchedule.add(stationAndTime[1]);
					//increment arrival time by total time to station
					String[] startTime24Hr = scheduleStartTime.split(":");
					delta_x = Integer.parseInt(startTime24Hr[1]);
					double finalMin = 0;
					double finalHr = 0;
					if(Double.parseDouble(startTime24Hr[1]) + Double.parseDouble(stationAndTime[1]) > 60) {
						finalHr = Double.parseDouble(startTime24Hr[0]) + 1;
						double temp = 60 + Double.parseDouble(stationAndTime[1]);
						finalMin = temp - 60;
					} else {
						finalHr = Double.parseDouble(startTime24Hr[0]);
						finalMin = Double.parseDouble(startTime24Hr[1]) + Double.parseDouble(stationAndTime[1]);
					}
					
					scheduleStartTime = String.valueOf((int)Math.round(finalHr))+":"+String.valueOf((int)Math.round(finalMin));
					SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
					Date dateObj = sdf.parse(scheduleStartTime);
					trainSchedule.add(new SimpleDateFormat("hh:mm a").format(dateObj).toString());

					delta_y = (int)Math.round(finalMin);
					if(delta_y<delta_x) {
						delta_y = 60+delta_y;
					}
					
					counter += delta_y-delta_x;
					if(counter >= Integer.parseInt(throughputInfo.get(i+1))) {
						/*************throughput time has passed, dispatch another train*****************/
						String scheduleStartTime2 = scheduleStartTime;
						counter = 0;
						trainID++;
						for(int k=0; k<stations.size(); k++) {
							String[] stationAndTime2 = stations.get(k).split(",");
							trainSchedule.add(trainID+"");
							trainSchedule.add("RED");
							trainSchedule.add(stationAndTime2[0]);
							trainSchedule.add(stationAndTime2[1]);
							//increment arrival time by total time to station
							String[] startTime24Hr2 = scheduleStartTime2.split(":");
							delta_x = Integer.parseInt(startTime24Hr2[1]);
							double finalMin2 = 0;
							double finalHr2 = 0;
							if(Double.parseDouble(startTime24Hr2[1]) + Double.parseDouble(stationAndTime2[1]) > 60) {
								finalHr2 = Double.parseDouble(startTime24Hr2[0]) + 1;
								double temp = 60 + Double.parseDouble(stationAndTime2[1]);
								finalMin2 = temp - 60;
							} else {
								finalHr2 = Double.parseDouble(startTime24Hr2[0]);
								finalMin2 = Double.parseDouble(startTime24Hr2[1]) + Double.parseDouble(stationAndTime2[1]);
							}
							
							scheduleStartTime2 = String.valueOf((int)Math.round(finalHr2))+":"+String.valueOf((int)Math.round(finalMin2));
							trainSchedule.add( toAmPm(scheduleStartTime2) );
						}
						/***********************************************************************/
					}
				}
			} catch(IOException ee){
				System.out.println("IO Exception!");
			} catch(ParseException eee) {
				System.out.println("Parse Exception!");
			}
		}

		return trainSchedule;
	}

	/**
	* Given the number of trains used in the schedule assign operators
	*/
	public ArrayList<String> getOperatorSchedule() {
		ArrayList<String> temp = new ArrayList<String>();
		for(int i=0; i<trainSchedule.size(); i=i+5) {
			temp.add(trainSchedule.get(i));
		}

		Collections.sort(temp);
		operatorSchedule.add("Operator_"+1+"_Name ");
		operatorSchedule.add("1");
		operatorSchedule.add(toAmPm( scheduleStartTimeFinal ));
		operatorSchedule.add(toAmPm( addFour(scheduleStartTimeFinal ) ) );
		operatorSchedule.add(toAmPm( addMin( addFour(scheduleStartTimeFinal ), 30 ) ) );
		operatorSchedule.add(toAmPm ( addEightAndahalf( scheduleStartTimeFinal ) ) );

		for(int i=0; i<temp.size(); i++) {
			if(i!=temp.size()-1) {
				if(!(temp.get(i)).equals(temp.get(i+1))) {
					operatorSchedule.add("Operator_"+temp.get(i+1)+"_Name ");					
					operatorSchedule.add(temp.get(i+1));
					scheduleStartTimeFinal = addMin(scheduleStartTimeFinal, Integer.parseInt(throughputInfo.get(1)));
					operatorSchedule.add(toAmPm( scheduleStartTimeFinal ) );
					operatorSchedule.add(toAmPm( addFour(scheduleStartTimeFinal ) ) );
					operatorSchedule.add(toAmPm( addMin( addFour(scheduleStartTimeFinal ), 30 ) ) );
					operatorSchedule.add(toAmPm( addEightAndahalf( scheduleStartTimeFinal ) ) );
				}
			}
		}

		return operatorSchedule;
	}

	private String toAmPm(String input) {
		try {
			SimpleDateFormat sdf2 = new SimpleDateFormat("H:mm");
			Date dateObj2 = sdf2.parse(input);
			return new SimpleDateFormat("hh:mm a").format(dateObj2).toString();
		} catch (ParseException e) {
			System.out.println("Parse Exception!");
			return "";
		}
	}

	private String addEightAndahalf(String input) {
		String[] startTime24Hr = input.split(":");
		String finalHr;
		String finalMin;
		if((Integer.parseInt(startTime24Hr[1])+30 < 60)) {
			finalHr = (Integer.parseInt(startTime24Hr[0])+8)+"";
			finalMin = (Integer.parseInt(startTime24Hr[1])+30)+"";
		} else {
			finalHr = (Integer.parseInt(startTime24Hr[0])+9)+"";
			int tt = 60 + (Integer.parseInt(startTime24Hr[1])+30);
			finalMin = (60 - tt)+"";
		}

		return finalHr+":"+finalMin;
	}

	private String addFour(String input) {
		String[] startTime24Hr = input.split(":");
		String finalHr = "";
		String finalMin;
		if((Integer.parseInt(startTime24Hr[0])+4 <= 20)) {
			finalHr = (Integer.parseInt(startTime24Hr[0])+4)+"";
			finalMin = (Integer.parseInt(startTime24Hr[1]))+"";
		} else {
			if((Integer.parseInt(startTime24Hr[0]) == 21)) finalHr = 1+"";
			else if((Integer.parseInt(startTime24Hr[0])) == 21) finalHr = 2+"";
			else if((Integer.parseInt(startTime24Hr[0])) == 21) finalHr = 3+"";
			else if((Integer.parseInt(startTime24Hr[0])) == 21) finalHr = 4+"";
			finalMin = (Integer.parseInt(startTime24Hr[1]))+"";
		}

		return finalHr+":"+finalMin;
	}

	private String addMin(String input, int min) {
		String[] startTime24Hr = input.split(":");
		String finalHr;
		String finalMin;
		if((Integer.parseInt(startTime24Hr[1])+min <= 60)) {
			finalHr = (Integer.parseInt(startTime24Hr[0]))+"";
			finalMin = (Integer.parseInt(startTime24Hr[1])+min)+"";
		} else {
			finalHr = (Integer.parseInt(startTime24Hr[0]))+"";
			int tt = 60 + (Integer.parseInt(startTime24Hr[1])+min);
			finalMin = (tt - 60)+"";
		}

		return finalHr+":"+finalMin;		
	}
}
