
public class TrainControllerGUI {
	tcGUI gui;
	boolean guiAlive;
	public TrainControllerGUI(TrainState ts){
		try {
			gui = new tcGUI(ts);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public synchronized boolean updateUserSamples(TrainState ts){
		return gui.updateValues(ts);	
	}
}
