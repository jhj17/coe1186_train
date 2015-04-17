import java.io.IOException;
import java.util.ArrayList;


public class Router 
{
	private ArrayList<String> blockInfo, blockIDs =  new ArrayList<String>(), theAuthorities = new ArrayList<String>(), theSpeeds = new ArrayList<String>();
	private String line;
	private double authority;
	private String train;
	int ind;
	public Router(String t, String line, int i)
	{
		train = t;
		this.line = line;
		ind = i;
	}
	public Router()
	{
		
	}
	public String getTrainID()
	{
		return train;
	}
	public void getRouteFB(Track tr, String trainID, String destination, String line) throws IOException
	{
		
		int trainInt = Integer.parseInt(trainID);
		System.out.println("Train Int: " + trainInt + " Line: " + line);
		blockInfo = tr.getRoute(line, destination);
		for(int i = 0; i < blockInfo.size(); i++)
		{
			System.out.println(blockInfo.get(i));
			String[] infoSplit = blockInfo.get(i).split(",");
			for(int k = 0; k < infoSplit.length; k++)
			{
				blockIDs.add(infoSplit[0]);
				theAuthorities.add(infoSplit[1]);
				theSpeeds.add(infoSplit[2]);
			}
		}
		//tr.placeTrain(line, trainInt);
	

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
	public String createProceedMsgFB()
	{
		int indexB = ind+1;
		int indexC = indexB+1;
		//String msg = new String();
		//System.out.println("Full Authority: " + aut);
		
		authority = authority - Double.parseDouble(theAuthorities.get(ind));
		
		StringBuffer msg = new StringBuffer(line);
		msg.append(","  + blockIDs.get(ind) + "," + blockIDs.get(indexB) +  "," + blockIDs.get(indexC) 
				+ "," + theSpeeds.get(ind) + "," + authority);
			
		
		return msg.toString();
		//return null;
	}
	public String createProceedMsgMBO()
	{
		int indexB = ind+1;
		int indexC = indexB+1;
		
		StringBuffer msg = new StringBuffer(line);
		msg.append(","  + blockIDs.get(ind) + "," + blockIDs.get(indexB) +  "," + blockIDs.get(indexC) 
				+ "," + -1 + "," + -1);
		ind++;
		return msg.toString();
	}
	public String createOpenCloseMsg(String line, String blockID, String maint)
	{
		StringBuffer msg = new StringBuffer(line);
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
