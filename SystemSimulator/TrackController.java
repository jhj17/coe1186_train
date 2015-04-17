//package trackControllerFinal;

import java.util.ArrayList;
import java.util.Iterator;

public class TrackController {
	
	private String trackLine;
	private int trackControllerID;
	public String plcFile;
	public PLCClass plc;
	private boolean plcLoaded;
	private int blockIDs[];
	
	/**
	 * Constructor for track controller object
	 * @param line		track line
	 * @param id		track controller id
	 * @param blocks	all blocks covered by this instance of track controller
	 */
	public TrackController(String line, int id, int blockIDs[]) {
		this.trackLine = line;
		this.trackControllerID = id;
		this.blockIDs = blockIDs;
		this.plcLoaded = false;
	}
	
	/**
	 * Function to initialize track controller with selected PLC program
	 * @param plcFile	file path to PLC program
	 */
	public void initPLC(String plcFile) {
		this.plcFile = plcFile;
		
		// create PLC instance for individual track controller
		this.plc = new PLCClass(plcFile);
		this.plcLoaded = true;
	}
	
	/**
	 * Function to get ID from selected track controller
	 * @return	track controller id of current instance of track controllers
	 */
	public int getID() {
		return this.trackControllerID;
	}
	
	/**
	 * Function to set the maintenance state of a requested block
	 * @param maintState
	 * @param blockID
	 */
	public void setMaintenace(int blockID) {
			
	}
}
