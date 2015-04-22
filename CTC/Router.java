import java.io.IOException;
import java.util.ArrayList;


public class Router 
{
	private ArrayList<String> blockInfo, blockIDs =  new ArrayList<String>(), theAuthorities = new ArrayList<String>();
	private ArrayList<String> theSpeeds = new ArrayList<String>(), sectionIDs =  new ArrayList<String>();
	public ArrayList<Train> theTrains = new ArrayList<Train>();
	private String line;
	private double authority;
	private String train;
	private TrackControllerWrapper tcw;
	private SimClock sm;
	private boolean isMBO;
	public Router(String t, String line, TrackControllerWrapper tcw, SimClock sm, boolean mode)
	{
		train = t;
		this.line = line;
		this.tcw = tcw;
		this.sm = sm;
		isMBO = mode;
	}
	public Router()
	{
		
	}
	public int getTrainID()
	{
		return Integer.parseInt(train);
	}
	public String getLine()
	{
		return line;
	}
	public boolean isMBOMode()
	{
		return isMBO;
	}
	public void getRoute(Track tr, String trainID, String destination) throws IOException
	{
		//String lineLowerCase = line.toLowerCase();
		int trainInt = Integer.parseInt(trainID);
		tr.placeTrain(line, trainInt);
		System.out.println("Train Int: " + trainInt + " Line: " + line);
		blockInfo = tr.getRoute(line, destination);
		
		for(int i = 0; i < blockInfo.size(); i++)
		{
			//System.out.println(blockInfo.get(i));
			String[] infoSplit = blockInfo.get(i).split(",");
			blockIDs.add(infoSplit[0]);
			sectionIDs.add(infoSplit[1]);
			theAuthorities.add(infoSplit[2]);
			theSpeeds.add(infoSplit[3]);
		}
		//System.out.println("Size of BlockIDs: " +  blockIDs.size());
		authority = getFullAuthority();
		System.out.println("Full Authority: " + authority);
		Train t = new Train(tr, trainInt, sm);
		theTrains.add(t);
		if(isMBO == false)
		{
			for(int i =0; i < blockIDs.size(); i++)
			{
					String msg = createProceedMsgFB(line, i);
					tcw.newProceedMsg(msg);
					System.out.println("From CTC: " + msg);
			}
		}
		else if(isMBO == true)
		{
			for(int i = 0; i < blockIDs.size(); i++)
			{
				String msg = createProceedMsgMBO(line, i);
				tcw.newProceedMsg(msg);
				System.out.println("From CTC: " + msg);
			}
		}
	}
	public double getFullAuthority()
	{
		double aut = 0;
		for(int i = 0; i < theAuthorities.size(); i++)
			authority = authority + Double.parseDouble(theAuthorities.get(i));
		aut = authority;
		return aut;
	}
	public String createProceedMsgFB(String l, int index)
	{
		int indexB = index+1;
		int indexC = indexB+1;
		int minSpeed;
		authority = authority - Double.parseDouble(theAuthorities.get(index));
		StringBuffer msg = new StringBuffer(l);
		if(indexC == blockIDs.size() - 1) //index 128
		{
			System.out.println("Show Beacon");
			tcw.showBeacon(l, Integer.parseInt(blockIDs.get(index)));
		}
		if(index == blockIDs.size()-1)  //last one 
		{
			msg.append(","  + blockIDs.get(index) + "," + blockIDs.get(index) +  "," + blockIDs.get(index) 
					+ "," + theSpeeds.get(index) + "," + (int)Double.parseDouble(theAuthorities.get(index)));
		}
		else if(index == blockIDs.size()-2) //index 127: second to last 
		{
			minSpeed = Math.min(Integer.parseInt(theSpeeds.get(index)), Integer.parseInt(theSpeeds.get(indexB)));
			msg.append(","  + blockIDs.get(index) + "," + blockIDs.get(indexB) +  "," + blockIDs.get(indexB) 
				+ "," + minSpeed + "," + (int)authority);
		}
		else
		{
			minSpeed =  Math.min(Integer.parseInt(theSpeeds.get(index)), Integer.parseInt(theSpeeds.get(indexB)));
			msg.append(","  + blockIDs.get(index) + "," + blockIDs.get(indexB) +  "," + blockIDs.get(indexC) 
					+ "," + minSpeed + "," + (int)authority);
		}
		return msg.toString();
	}
	public String createProceedMsgMBO(String l, int index)
	{
		int indexB = index+1;
		int indexC = indexB+1;
		
		if(indexC == blockIDs.size() - 1) //index 128
		{
			System.out.println("Show Beacon");
			tcw.showBeacon(l, Integer.parseInt(blockIDs.get(index)));
		}
		StringBuffer msg = new StringBuffer(l);
		if(index == blockIDs.size()-1)
		{
			msg.append(","  + blockIDs.get(index) + "," + blockIDs.get(index) +  "," + blockIDs.get(index) 
				+ "," + -1 + "," + -1);
		}
		else if(index == blockIDs.size()-2)
		{
			msg.append(","  + blockIDs.get(indexB) + "," + blockIDs.get(indexB) +  "," + blockIDs.get(index) 
					+ "," + -1 + "," + -1);
		}
		else
		{
			msg.append(","  + blockIDs.get(index) + "," + blockIDs.get(indexB) +  "," + blockIDs.get(indexC) 
					+ "," + -1 + "," + -1);
		}
		return msg.toString();
	}
	public String createOpenCloseMsg(String l, String blockID, String maint)
	{
		StringBuffer msg = new StringBuffer(l);
		msg.append("," + blockID + "," + maint);
		return msg.toString();
		
	}
	public int getLastBlock()
	{
		int lastBlock = Integer.parseInt(blockIDs.get(blockIDs.size()-1));
		return lastBlock;
	}
	public void printStatus(/*TrackControllerWrapper tcw,*/ int ind, String trainID)
	{
		int j = 0;
		/* for(int i = 0; i < blockIDs.size(); i++)
		 {
			 //byte t = tcw.getBlockStatus(line, Integer.parseInt(blockIDs.get(i)));
			 if(t == 1);
			 	j = i;
		 }
			 
		System.out.println("Train: " + trainID + " Current: " +  blockIDs.get(j) + " Next: " + blockIDs.get(j+1));*/
	}

}
