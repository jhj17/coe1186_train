import java.util.ArrayList;


public class Switch implements SwitchInterface {


	private ArrayList<Block> switchBlocks;
	
	private String switchNumber;
	private Block switchedBlock = null;
	private Block unSwitchedBlock = null;
	private Block switchBlock = null;
	private int setupCount = 0;
	
	//switchState: boolean
	//nextBlock: blocK ?
	//switchBroken: boolean; 
	
	
	public Switch(Block blockIn)
	{
		switchNumber = blockIn.getSwitchNumber();
		addBlock(blockIn);
	}

	public void addBlock(Block blockIn)
	{

		blockIn.setSwitch(this);

		if(blockIn.getSwitchBlock().length()>0)
			switchBlock = blockIn;
		else if(switchedBlock == null)
			switchedBlock = blockIn;
		else
			unSwitchedBlock = blockIn;
	}

	public String getSwitchNumber()
	{
		return switchNumber;
	}
	public Block getSwitchBlock()
	{
		return switchBlock;
	}

	public Block getswitchedBlockBlock()
	{
		return switchedBlock;
	}

	public Block getunSwitchedBlockBlock()
	{
		return unSwitchedBlock;
	}

	private void setOutOfSection(Block sourceBlock, Block targetBlock)
	{
		//System.out.println("switch: " + switchBlock.getSection() + switchBlock.getBlockNumber());
		if(sourceBlock.getNext() != null && sourceBlock.getNext().getSection().equals(sourceBlock.getSection())) //next in same section
			sourceBlock.setPrevious(targetBlock);
		else //previous in same section
			sourceBlock.setNext(targetBlock);

	}
	
	public void setup()
	{

		if(switchBlock.getSwitchType().equals("-"))
		{
			if((switchedBlock.getDirection() == 1 || switchedBlock.getDirection() == -1) && switchedBlock.getArrow().equals("HEAD"))
			{
				setOutOfSection(switchBlock, null);
			}
			else
			{
				setOutOfSection(switchBlock, switchedBlock);
			}

			setOutOfSection(switchedBlock, switchBlock);
			setOutOfSection(unSwitchedBlock, null);
		}
		else if(switchBlock.getSwitchType().equals("BEFORE"))
		{
			switchBlock.setPrevious(switchedBlock);
			if(switchedBlock.getSection().equals(switchBlock.getSection()))// if same section
			{
				switchedBlock.setNext(switchBlock);
				setOutOfSection(unSwitchedBlock, null);
			}
			else //not same section
			{
				setOutOfSection(switchedBlock,switchBlock);
				unSwitchedBlock.setNext(null);
			}
		}
		else if(switchBlock.getSwitchType().equals("AFTER")) //mid-section switch is after fork
		{

			if(switchBlock.getDirection()==-1)
			{
				switchBlock.setPrevious(switchedBlock);
				if(switchedBlock.getSection().equals(switchBlock.getSection()))
				{
					switchedBlock.setNext(switchBlock);
					setOutOfSection(unSwitchedBlock, null);
				}
				else
				{
					setOutOfSection(switchedBlock, switchBlock);
					unSwitchedBlock.setNext(null);
				}
			}
			else
			{
				switchBlock.setNext(switchedBlock);
				if(switchedBlock.getSection().equals(switchBlock.getSection()))
				{
					switchedBlock.setPrevious(switchBlock);
					setOutOfSection(unSwitchedBlock,null);
				}
				else
				{
					setOutOfSection(switchedBlock, switchBlock);
					unSwitchedBlock.setPrevious(null);	
				}
			}
		}

		if(setupCount<2)
		{	
			setupCount++;
			toggleSwitch();
		}
	}

	@Override
	public void toggleSwitch() {
		// TODO Auto-generated method stub
		//System.out.println("Toggling " + switchNumber);
		Block temp = switchedBlock;
		switchedBlock = unSwitchedBlock;
		unSwitchedBlock = temp;
		setup();
	}

	@Override
	public boolean isSwitchWorking() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getSwitchPosition() {
		// TODO Auto-generated method stub
		return false;
	}

	public void adjustConnections() {
		// TODO Auto-generated method stub
	
	}
}
