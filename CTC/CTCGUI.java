import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.custom.ScrolledComposite;


public class CTCGUI {

	protected Shell shell;
	private Text trainID;
	private Button btnFixedBlock, btnMbo, btnRequestSchedule, btnLoadStations;
	private Combo comboLine;
	public static ArrayList<String> redLine = new ArrayList<String>();
	public static ArrayList<String> greenLine = new ArrayList<String>();
	private Button btnSchedule;
	private Combo comboStationsRed;
	private Combo comboStationsGreen;
	private Table table;
	private TableItem [] tableTrain;
	private Text textRouting;
	private Text textBlock;
	private Text textOpenClose;
	private Text textTime;
	private Text textBlockStatus;
	private Text textStatus;
	public static ArrayList<Router> routers = new ArrayList<Router>();
	private Scheduler s;
	private Track tk;
	private TrackControllerTest tct = new TrackControllerTest();;
	public static double authority;
	public static int indexSched = 0, indexFB = 0, indMBO = 0;
	private Text textOpenCloseLine;
	private Text textStatusLine;
	public static ArrayList<String> trainIDs = new ArrayList<String>();
	private Text textTrainRoute;
	private Router mainRouter = new Router();

	/**
	 * Launch the application.
	 * @param args
	 * @throws FileNotFoundException 
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) throws FileNotFoundException {
		Scanner outScan = new Scanner(new File("redLine.txt"));
		while(outScan.hasNextLine())
		{
			redLine.add(outScan.nextLine());
		}
		outScan = new Scanner(new File("greenLine.txt"));
		while(outScan.hasNextLine())
		{
			greenLine.add(outScan.nextLine());
		}
		for(int i =0; i < greenLine.size(); i++)
			System.out.println(greenLine.get(i));
		System.out.println();
		for(int i = 0; i < redLine.size(); i++)
			System.out.println(redLine.get(i));
		try {
			CTCGUI window = new CTCGUI();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
		shell.setSize(1063, 659);
		shell.setText("SWT Application");
		
		Label lblSchedule = new Label(shell, SWT.NONE);
		lblSchedule.setBounds(159, 32, 97, 15);
		lblSchedule.setText("       Schedule");
		
		btnFixedBlock = new Button(shell, SWT.RADIO);
		btnFixedBlock.setBounds(58, 51, 90, 16);
		btnFixedBlock.setText("Fixed Block");
		btnFixedBlock.setSelection(true);
		btnFixedBlock.addSelectionListener(new SelectionAdapter(){
			@Override 
			public void widgetSelected(SelectionEvent e)
			{
				if(btnFixedBlock.getSelection() == true)
					btnRequestSchedule.setEnabled(false);
					trainID.setEnabled(true);
					comboLine.setEnabled(true);
			}
		});
		
		btnMbo = new Button(shell, SWT.RADIO);
		btnMbo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(btnMbo.getSelection() == true)
					trainID.setEnabled(false);
					comboLine.setEnabled(false);
					btnRequestSchedule.setEnabled(true);
			}
		});
		btnMbo.setBounds(193, 53, 55, 16);
		btnMbo.setText("MBO ");
		
		trainID = new Text(shell, SWT.BORDER);
		trainID.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		trainID.setBounds(58, 94, 75, 21);
		
		Label lblTrain = new Label(shell, SWT.NONE);
		lblTrain.setBounds(80, 73, 40, 15);
		lblTrain.setText("Train");
		
		comboLine = new Combo(shell, SWT.NONE);
		comboLine.setBounds(177, 95, 79, 21);
		comboLine.add("Red");
		comboLine.add("Green");
		
		Label lblLine = new Label(shell, SWT.NONE);
		lblLine.setBounds(193, 75, 55, 15);
		lblLine.setText("Line");
		
		btnLoadStations = new Button(shell, SWT.NONE);
		btnLoadStations.setBounds(58, 121, 90, 25);
		btnLoadStations.setText("Load Stations");
		btnLoadStations.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				System.out.println("ComboLine.getText() " + comboLine.getText());
				if(comboLine.getText().equals("Green"))
				{
					comboStationsGreen.setVisible(true);
					comboStationsRed.setVisible(false);
				}
				else if(comboLine.getText().equals("Red"))
				{
					comboStationsRed.setVisible(true);
					comboStationsGreen.setVisible(false);
				}
				
			}
			
		});
		
		
		Label lblStations = new Label(shell, SWT.NONE);
		lblStations.setBounds(297, 75, 55, 15);
		lblStations.setText("Stations");
		
		btnRequestSchedule = new Button(shell, SWT.NONE);
		btnRequestSchedule.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				//get schedule from the MBO as ArrayList of Strings
				//do all configuration here
			}
		});
		btnRequestSchedule.setBounds(276, 47, 105, 25);
		btnRequestSchedule.setText("Request Schedule");
		btnRequestSchedule.setEnabled(false);
		
		btnSchedule = new Button(shell, SWT.NONE);
		btnSchedule.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				String [] sched = null;
				s = new Scheduler();
				System.out.println("Train ID: " + trainID.getText() + " Line: " +  comboLine.getText());
				Router newRouter = new Router(trainID.getText(), comboLine.getText());
				if(comboLine.getText().equals("Green"))
				{
					sched = s.createScheduleFB(comboStationsGreen.getText(), comboLine.getText(), trainID.getText());
				}
				else if(comboLine.getText().equals("Red"))
				{
					sched = s.createScheduleFB(comboStationsRed.getText(), comboLine.getText(), trainID.getText());
				}
				trainIDs.add(trainID.getText());
				tableTrain[indexSched] = new TableItem(table, SWT.NONE, 0);
				tableTrain[indexSched].setText(sched);
				indexSched++;
				routers.add(newRouter);
				// create train
			}
		});
		btnSchedule.setBounds(177, 124, 75, 25);
		btnSchedule.setText("Schedule");
		
		comboStationsRed = new Combo(shell, SWT.NONE);
		comboStationsRed.setBounds(276, 95, 91, 23);
		comboStationsRed.setVisible(false);
		//comboStationsRed.setVisible(true);
		for(int i = 0; i < redLine.size(); i++)
				comboStationsRed.add(redLine.get(i));
		
		comboStationsGreen = new Combo(shell, SWT.NONE);
		comboStationsGreen.setBounds(276, 94, 91, 23);
		comboStationsGreen.setVisible(false);
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 152, 434, 388);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnTrain = new TableColumn(table, SWT.NONE);
		tblclmnTrain.setWidth(150);
		tblclmnTrain.setText("              Train");
		
		TableColumn tblclmnLine = new TableColumn(table, SWT.NONE);
		tblclmnLine.setWidth(136);
		tblclmnLine.setText("              Line");
		
		TableColumn tblclmnDestination = new TableColumn(table, SWT.NONE);
		tblclmnDestination.setWidth(140);
		tblclmnDestination.setText("         Destination");
		
		tableTrain = new TableItem [greenLine.size()];
		
		Button btnRoute = new Button(shell, SWT.NONE);
		btnRoute.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				try {
					tk = new Track();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				if(btnFixedBlock.getSelection() == true)
				{
					if(comboLine.getText().equals("Green"))
					{
						try {
							for(int i = 0; i < routers.size(); i++)
							{
								if(routers.get(i).getTrainID() == i+1)
								{
									System.out.println("Routing Train: " + routers.get(i).getTrainID());
									routers.get(i).getRouteFB(tk, trainID.getText(), comboStationsGreen.getText());
									authority = routers.get(i).getFullAuthority();
									System.out.println("Full Authority: " +  authority);
									break;
								}
							}
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					else if(comboLine.getText().equals("Red"))
					{
						try {
							for(int i = 0; i < routers.size(); i++)
							{
								if(routers.get(i).getTrainID() == i+1)
								{
									System.out.println("Routing Train: " + routers.get(i).getTrainID());
									routers.get(i).getRouteFB(tk, trainID.getText(), comboStationsRed.getText());
									authority = routers.get(i).getFullAuthority();
									System.out.println("Full Authority: " +  authority);
								}
							
							}
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
				else if(btnMbo.getSelection() == true)
				{
					//route each one from source to destination
				}
				// create train
				
				//add router to array list
				
			}
		});
		btnRoute.setBounds(286, 124, 75, 25);
		btnRoute.setText("Route");
		
		Label lblDispatchMessages = new Label(shell, SWT.NONE);
		lblDispatchMessages.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblDispatchMessages.setBounds(616, 147, 241, 25);
		lblDispatchMessages.setText("     Messages to Track Controller");
		
		textRouting = new Text(shell, SWT.MULTI | SWT.BORDER | SWT.WRAP);
		textRouting.setBounds(594, 199, 297, 142);
		textRouting.setText("Proceed Msgs: \n");
		
		Button btnDispatch = new Button(shell, SWT.NONE);
		btnDispatch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				//send message to the track controller 
				//append that message to the text box
				//find the correct router to send the right message to proceed
				if(btnFixedBlock.getSelection() == true)
				{
					for(int i = 0; i < routers.size(); i++)
					{
						if(routers.get(i).getTrainID() == i+1)
						{
							String msg = routers.get(i).createProceedMsgFB(routers.get(i).getLine(), indexFB);
							textRouting.append(msg + "\n");
							tct.newProceedMsg(msg);
							indexFB++;
							break;	
						}
					}
				}
				else if(btnMbo.getSelection() == true)
				{
					for(int i = 0; i < routers.size(); i++)
					{
						if(textTrainRoute.getText().equals(routers.get(i).getTrainID()))
						{
							String msg = routers.get(i).createProceedMsgMBO(routers.get(i).getLine(),indMBO);
							textRouting.append(msg + "\n");
							tct.newProceedMsg(msg);
							indMBO++;
							break;	
						}
					}
					
				}
				
			}
		});
		btnDispatch.setBounds(808, 347, 75, 25);
		btnDispatch.setText("Dispatch");
		
		Label lblCloseTrack = new Label(shell, SWT.NONE);
		lblCloseTrack.setBounds(548, 383, 105, 15);
		lblCloseTrack.setText("Open/Close Track");
		
		textBlock = new Text(shell, SWT.BORDER);
		textBlock.setBounds(506, 416, 49, 21);
		
		Label lblBlock = new Label(shell, SWT.NONE);
		lblBlock.setBounds(506, 400, 40, 15);
		lblBlock.setText("   Block");
		
		Button btnOpen = new Button(shell, SWT.NONE);
		btnOpen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				//send message that block is opened 
				String msgOpen = mainRouter.createOpenCloseMsg(textBlock.getText(), textOpenCloseLine.getText(), "open");
				textOpenClose.append(msgOpen + "\n");
				tct.newMaintMsg(msgOpen);
				//append that message to text area 
			}
		});
		btnOpen.setBounds(493, 572, 75, 25);
		btnOpen.setText("Open");
		
		Button btnClose = new Button(shell, SWT.NONE);
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				//send message that block is closed to tct
				//append that message to text area 
				String msgClose = mainRouter.createOpenCloseMsg(textBlock.getText(), textOpenCloseLine.getText(), "close");
				textOpenClose.append(msgClose + "\n");
				tct.newMaintMsg(msgClose);
				
			}
		});
		btnClose.setBounds(616, 572, 75, 25);
		btnClose.setText("Close ");
		
		textOpenClose = new Text(shell, SWT.MULTI | SWT.BORDER | SWT.WRAP);
		textOpenClose.setBounds(461, 443, 277, 123);
		textOpenClose.setText("Open/Close Messages: \n");
		
		Button btnShowBeacan = new Button(shell, SWT.NONE);
		btnShowBeacan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				//send message to track controller to tell train to show beacan 
				//append to routing list
				for(int i = 0; i < routers.size(); i++)
				{
					if(routers.get(i).getTrainID() == i+1)
					{
						int blockID = routers.get(i).getLastBlock();
						tct.showBeacon(comboLine.getText(), blockID);
						textRouting.append("Line: " + routers.get(i).getLine() + " Block ID: " + blockID + " Show Beacan\n");
						break;
					}
				}
			}
		});
		btnShowBeacan.setBounds(714, 347, 75, 25);
		btnShowBeacan.setText("Show Beacan");
		
		Label lblClock = new Label(shell, SWT.NONE);
		lblClock.setBounds(740, 10, 49, 15);
		lblClock.setText("Clock");
		
		textTime = new Text(shell, SWT.BORDER);
		textTime.setBounds(634, 29, 270, 84);
		
		Button btnGetTime = new Button(shell, SWT.NONE);
		btnGetTime.setBounds(816, 116, 75, 25);
		btnGetTime.setText("Get Time ");
		
		Label lblRouting = new Label(shell, SWT.NONE);
		lblRouting.setBounds(715, 178, 55, 15);
		lblRouting.setText("Routing ");
		
		Label lblBlockStatus = new Label(shell, SWT.NONE);
		lblBlockStatus.setBounds(862, 383, 75, 15);
		lblBlockStatus.setText("Block Status");
		
		Label lblBlock_1 = new Label(shell, SWT.NONE);
		lblBlock_1.setBounds(795, 419, 29, 15);
		lblBlock_1.setText("Block");
		
		textBlockStatus = new Text(shell, SWT.BORDER);
		textBlockStatus.setBounds(830, 416, 40, 21);
		
		textStatus = new Text(shell, SWT.MULTI | SWT.BORDER | SWT.WRAP);
		textStatus.setBounds(766, 443, 271, 123);
		
		Button btnGetStatus = new Button(shell, SWT.NONE);
		btnGetStatus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				//get the status of block from Track Controller
				
				byte stat = tct.getBlockStatus(textStatusLine.getText(), Integer.parseInt(textBlockStatus.getText()));
				if(stat == 1)
					textStatus.append("Line: " +  textStatusLine.getText() + "Block: " + textBlockStatus.getText() + " Occupied.\n");
			}
		});
		btnGetStatus.setBounds(950, 572, 75, 25);
		btnGetStatus.setText("Get Status");
		
		textOpenCloseLine = new Text(shell, SWT.BORDER);
		textOpenCloseLine.setBounds(615, 416, 76, 21);
		
		Label lblLine_1 = new Label(shell, SWT.NONE);
		lblLine_1.setBounds(634, 400, 55, 15);
		lblLine_1.setText("Line");
		
		textStatusLine = new Text(shell, SWT.BORDER);
		textStatusLine.setBounds(933, 416, 76, 21);
		
		Label lblLine_2 = new Label(shell, SWT.NONE);
		lblLine_2.setBounds(892, 419, 40, 15);
		lblLine_2.setText("Line");
		
		textTrainRoute = new Text(shell, SWT.BORDER);
		textTrainRoute.setBounds(651, 349, 40, 21);
		
		Label lblTrain_1 = new Label(shell, SWT.NONE);
		lblTrain_1.setBounds(604, 352, 40, 15);
		lblTrain_1.setText("Train");
		
		Button btnTransitSystem = new Button(shell, SWT.NONE);
		btnTransitSystem.setBounds(80, 572, 201, 25);
		btnTransitSystem.setText("Transit System");
		for(int i = 0; i < greenLine.size(); i++)
			comboStationsGreen.add(greenLine.get(i));

	}
}

