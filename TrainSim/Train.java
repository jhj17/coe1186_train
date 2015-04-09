
public class Train {
Track trackM;
int trainID;
SimClock sm;
TrainController tc;
TrainModel trainM;
boolean wannaRun = true;
	public Train (Track trackM, int trainID, SimClock sm){
		tc = new TrainController(sm);
		trainM = new TrainModel(trainID, trackM, tc, sm);
		tc.initTrainModel(trainM);
		new Thread(){
			public void run(){
				while(wannaRun){
				tc.tick();
				try {
					Thread.sleep((long)tc.ts.SampleRate);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
			}
		}.start();
	}
	public void kill(){
		wannaRun = false;
	}
}
