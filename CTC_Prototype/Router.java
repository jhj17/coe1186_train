import java.io.IOException;
import java.util.ArrayList;


public class Router 
{
	private ArrayList<String> blockInfo, blockIDs =  new ArrayList<String>(), theAuthorities = new ArrayList<String>(), theSpeeds = new ArrayList<String>();
	private String line;
	private double authority;
	public Router()
	{
		this.line = line;
	}
	public void getRouteFB(/*Track tr,*/ String trainID, String destination, String line) throws IOException
	{
		
		int trainInt = Integer.parseInt(trainID);
		//blockInfo = tr.getRoute(line, destination);
		for(int i = 0; i < blockInfo.size(); i++)
		{
			System.out.println(blockInfo.get(i));
			String[] infoSplit = blockInfo.get(i).split(",");
			//System.out.println("Info Split Length: " + infoSplit.length);
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
		return aut;
	}
	public String createMsg(int index, double aut)
	{
		int indexB = index+1;
		int indexC = indexB+1;
		//String msg = new String();
		//System.out.println("Full Authority: " + aut);
		
		aut = aut - Double.parseDouble(theAuthorities.get(index));
		
		StringBuffer msg = new StringBuffer(line);
		msg.append(","  + blockIDs.get(index) + "," + blockIDs.get(indexB) +  "," + blockIDs.get(indexC) 
				+ "," + theSpeeds.get(index) + "," + theAuthorities.get(index));
			
		
		return msg.toString();
		//return null;
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
