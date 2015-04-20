import java.awt.EventQueue;

public class SystemWrapper {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SystemGUI window = new SystemGUI();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
