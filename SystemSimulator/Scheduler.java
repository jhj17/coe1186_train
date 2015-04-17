import java.sql.Time;
import java.util.ArrayList;


public class Scheduler
{
	
	public Scheduler()
	{
		
	}
	public String[] createScheduleFB(String destination, String line, String trainID)
	{
		String[] scheduleTable = new String[3];
		scheduleTable[0] = trainID;
		scheduleTable[1] = line;
		scheduleTable[2] = destination;
		return scheduleTable;
		
	}

}
