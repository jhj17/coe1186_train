import java.io.IOException;
import java.util.ArrayList;


public class Router 
{
	private ArrayList<String> blockInfo, blockIDs =  new ArrayList<String>(), theAuthorities = new ArrayList<String>();
	private ArrayList<String> theSpeeds = new ArrayList<String>(), sectionIDs =  new ArrayList<String>();
	private String line;
	private double authority;
	private String train;
	private TrackControllerWrapper tcw;
	public Router(String t, String line, TrackControllerWrapper tcw)
	{
		train = t;
		this.line = line;
		this.tcw = tcw;
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
	public void getRouteFB(Track tr, String trainID, String destination) throws IOException
	{
		String lineLowerCase = line.toLowerCase();
		int trainInt = Integer.parseInt(trainID);
		System.out.println("Train Int: " + trainInt + " Line: " + line);
		blockInfo = tr.getRoute(lineLowerCase, destination);
		for(int i = 0; i < blockInfo.size(); i++)
		{
			System.out.println(blockInfo.get(i));
			String[] infoSplit = blockInfo.get(i).split(",");
			blockIDs.add(infoSplit[0]);
			sectionIDs.add(infoSplit[1]);
			theAuthorities.add(infoSplit[2]);
			theSpeeds.add(infoSplit[3]);
		}
		tr.placeTrain(line, trainInt);
		for(int i =0; i < blockIDs.size(); i++)
		{
			int blockInt = Integer.parseInt(blockIDs.get(i));
			if(tcw.getBlockStatus(line, blockInt) == 1);
			{
				createProceedMsgFB(line, i);
			}
		}
	}
	public void getRouteMBO()
	{
		
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
		int minSpeed = Math.min(Integer.parseInt(theSpeeds.get(index)), Integer.parseInt(theSpeeds.get(indexB)));
		authority = authority - Double.parseDouble(theAuthorities.get(index));
		StringBuffer msg = new StringBuffer(l);
		if(indexC == blockIDs.size() - 1)
		{
			tcw.showBeacon(l, Integer.parseInt(blockInfo.get(index)));
		}
		
		if(index == blockIDs.size()-2)
		{
			msg.append(","  + blockIDs.get(index) + "," + blockIDs.get(indexB) +  "," + blockIDs.get(indexB) 
				+ "," + minSpeed + "," + (int)authority);
		}
		else if(index == blockIDs.size()-1)
		{
			msg.append(","  + blockIDs.get(index) + "," + blockIDs.get(index) +  "," + blockIDs.get(index) 
					+ "," + theSpeeds.get(index) + "," + (int)(authority/2));
		}
		else
		{
			msg.append(","  + blockIDs.get(index) + "," + blockIDs.get(indexB) +  "," + blockIDs.get(indexB) 
					+ "," + minSpeed + "," + (int)authority);
		}
		return msg.toString();
	}
	public String createProceedMsgMBO(String l, int index)
	{
		int indexB = index+1;
		int indexC = indexB+1;
		
		StringBuffer msg = new StringBuffer(l);
		msg.append(","  + blockIDs.get(index) + "," + blockIDs.get(indexB) +  "," + blockIDs.get(indexC) 
				+ "," + -1 + "," + -1);
		index++;
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
