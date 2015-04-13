import java.util.ArrayList;


public class Switch implements SwitchInterface {


	ArrayList<Block> switchBlocks;
	
	//switchState: boolean
	String switchNumber;
	Block position1 = null;
	Block position2 = null;
	Block switchBlock = null;
	//nextBlock: blocK ?
	//switchBroken: boolean; 
	
	
	public Switch(Block blockIn)
	{
		switchNumber = blockIn.getSwitchNumber();
		addBlock(blockIn);
	}

	public void addBlock(Block blockIn)
	{
		//System.out.println(switchNumber + "in: " + blockIn.getSection() + blockIn.getBlockNumber());
		
		if(blockIn.getSwitchBlock().length()>0)
			switchBlock = blockIn;
		else if(position1 == null)
			position1 = blockIn;
		else
			position2 = blockIn;
	}

	public String getSwitchNumber()
	{
		return switchNumber;
	}
	public Block getSwitchBlock()
	{
		return switchBlock;
	}

	public Block getPosition1Block()
	{
		return position1;
	}

	public Block getPosition2Block()
	{
		return position2;
	}

	private void setOutOfSection(Block sourceBlock, Block targetBlock)
	{

		if(sourceBlock.getNext().getSection().equals(sourceBlock.getSection()) //next in same section
			sourceBlock.setPrevious(targetBlock);
		else //previous in same section
			sourceBlock.setNext(targetBlock);

	}
	public void setup()
	{

		//3 cases... 3-section junction, 2-section junction with switch before, 2-section junction with switch after
		
		if(switchBlock.getSwitchType().equals("-"))
		{
			setOutOfSection(switchBlock, position1);
			setOutofSection(position1, switchBlock);
			setOutOfSection(position2, null);
		}
		else if(switchBlock.getSwitchType().equals("BEFORE"))
		{

		}
		else if(switchBlock.getSwitchType().equals("AFTER"))
		{
			
		}


	}

	@Override
	public void toggleSwitch() {
		// TODO Auto-generated method stub
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
