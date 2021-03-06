import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class Router 
{
	private ArrayList<String> blockInfo, blockIDs =  new ArrayList<String>(), theAuthorities = new ArrayList<String>();
	private ArrayList<String> theSpeeds = new ArrayList<String>(), sectionIDs =  new ArrayList<String>();
	public ArrayList<Train> theTrains = new ArrayList<Train>();
	private ArrayList<String> proceedMsgs = new ArrayList<String>();
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
		
		if(isMBO == false)
		{
			tr.placeTrain(line, trainInt);
			Train t = new Train(tr, trainInt, sm);
			theTrains.add(t);
			CTCGUI.ts = new TransitSys();
			int i = 0;
			//System.out.println("Last Block Sent: " + getLastBlock());
			while(i < blockIDs.size())
			{
					
					int blockInt = Integer.parseInt(blockIDs.get(i));
					if(i == 0)
					{
						String firstMsg = createProceedMsgFB(line, i);
						tcw.newProceedMsg(firstMsg);
						i++;
					}
					else if(tcw.getBlockStatus(line, blockInt) == 1)
					{
						String msg = createProceedMsgFB(line, i);
						System.out.println("From CTC: " + msg);
						tcw.newProceedMsg(msg);
						i++;
					}
					else
						continue;
			}
			/*for(int i1 =0; i1 < blockIDs.size(); i1++)
			{
				//int blockInt = Integer.parseInt(blockIDs.get(i));
					String msg = createProceedMsgFB(line, i1);
					proceedMsgs.add(msg);
					
			}*/
			/*while(i < proceedMsgs.size())
			{
					
					int blockInt = Integer.parseInt(blockIDs.get(i));
					if(i == 0)
					{
						tcw.newProceedMsg(proceedMsgs.get(i));
						i++;
					}
					else if(tcw.getBlockStatus(line, blockInt) == 1)
					{
						tcw.newProceedMsg(proceedMsgs.get(i));
						i++;
					}
					else if(tcw.getBlockStatus(line, blockInt) != 1)
					{
						i++;
					}
					else
						continue;
			}*/
			/*for(int i = 0; i < proceedMsgs.size(); i++) {
				tcw.newProceedMsg(proceedMsgs.get(i));
				System.out.println("From CTC: " + proceedMsgs.get(i));
				while (tcw.getBlockStatus("green", Integer.parseInt(blockIDs.get(i))) != 1) {
					if ((i+1) < proceedMsgs.size() && tcw.getBlockStatus("green",  Integer.parseInt(blockIDs.get(i))) == 1 ) 
					{
						i++;
						break;
					}
					// track is not on next block
					//Thread.sleep(50);
				}
			}*/		
		}
		else if(isMBO == true)
		{
			int i = 0;
			while(i < blockIDs.size())
			{
				int blockInt = Integer.parseInt(blockIDs.get(i));
				if(tcw.getBlockStatus(line, blockInt) == 1)
				{
					String msg = createProceedMsgMBO(line, i);
					tcw.newProceedMsg(msg);
					System.out.println("From CTC: " + msg);
					i++;
				}
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
		System.out.println("New Authority: " + authority);
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
		StringBuffer msg = new StringBuffer(blockID);
		msg.append("," + l + "," + maint);
		return msg.toString();
		
	}
	public int getLastBlock()
	{
		int lastBlock = Integer.parseInt(blockIDs.get(blockIDs.size()-1));
		return lastBlock;
	}

}
