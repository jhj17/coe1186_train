
public class Train {
TrackModel trackM;
int trainID;
SimClock sm;
TrainController tc;
TrainModel trainM;
	public Train (TrackModel trackM, int trainID, SimClock sm){
		tc = new TrainController(sm);
		trainM = new TrainModel(trainID, trackM, tc, sm);
		tc.initTrainModel(trainM);
		new Thread(){
			public void run(){
				tc.tick();
				Thread.sleep((long)tc.ts.SampleRate);
			}
		}.start();
	}
}
