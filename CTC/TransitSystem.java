import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;


public class TransitSystem {

	protected Shell shell;
	private Text textTrainList;
	public TransitSystem()
	{
		
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	/*public static void main(String[] args) {
		try {
			TransitSystem window = new TransitSystem();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(515, 579);
		shell.setText("Entire Transit System");
		
		Button btnUpdate = new Button(shell, SWT.NONE);
		btnUpdate.setBounds(382, 457, 75, 25);
		btnUpdate.setText("Update");
		
		Label transitPicture = new Label(shell, SWT.NONE);
		transitPicture.setBounds(10, 27, 268, 480);
		
		textTrainList = new Text(shell, SWT.MULTI | SWT.BORDER | SWT.WRAP);
		textTrainList.setBounds(297, 32, 192, 388);

	}
}
