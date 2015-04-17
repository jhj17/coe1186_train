
public class TrackControllerTest 
{
	
	public TrackControllerTest()
	{
		
	}
	public boolean newProceedMsg(String msg) {
		System.out.println("Got Your Message: " + msg);
		return true;
	}
	public boolean newMaintMsg(String msg) {
		System.out.print("Got Your Message: " + msg);
		return true;
	}
	public boolean showBeacon(String line, int blockID) {
		System.out.println("Show Beacan on Line: " + line + " block: " + blockID);
		
		return true;
	}
	public byte getBlockStatus(String line, int blockID) {
		System.out.println("Get Status block at line: " + line + "Block ID: " +  blockID);
		return 1;
	}
	

}
