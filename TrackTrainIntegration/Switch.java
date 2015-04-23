import java.util.ArrayList;


public class Switch implements SwitchInterface {


	private ArrayList<Block> switchBlocks;
	
	private String switchNumber;
	private Block switchedBlock = null;
	private Block unSwitchedBlock = null;
	private Block switchBlock = null;
	private int setupCount = 0;
	private boolean brokenSwitch = false;

	//switchState: boolean
	//nextBlock: blocK ?
		
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

		if(switchBlock.getSwitchType().equals("-")) //normal 3-way junction case.  3 sepearate section blocks
		{

			setOutOfSection(switchBlock, switchedBlock);
			setOutOfSection(switchedBlock, switchBlock);
			setOutOfSection(unSwitchedBlock, null);
		}
		else if(switchBlock.getSwitchType().equals("BEFORE"))  //actual switch is before its attachments 
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


			if(switchBlock.getDirection()==-1) //if backwards 1-way 
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

	public void breakSwitch()
	{
		brokenSwitch = (!brokenSwitch);
	}

	@Override
	public boolean isSwitchWorking() {
		return brokenSwitch;
	}

	@Override
	public boolean getSwitchPosition() {
		// TODO Auto-generated method stub
		return false;
	}
	public String toString()
	{
		if(brokenSwitch)
			return switchedBlock.getLine() + "\t" + switchNumber + "\t" + brokenSwitch;
		else
			return switchedBlock.getLine() + "\t" + switchNumber + "\t" + switchBlock.getSection() + switchBlock.getBlockNumber() + "\t" + switchedBlock.getSection()  + switchedBlock.getBlockNumber() + "\t" + unSwitchedBlock.getSection() + unSwitchedBlock.getBlockNumber();
	}
}
