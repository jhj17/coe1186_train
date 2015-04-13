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
		//System.out.println("switch: " + switchBlock.getSection() + switchBlock.getBlockNumber());
		if(sourceBlock.getNext() != null && sourceBlock.getNext().getSection().equals(sourceBlock.getSection())) //next in same section
			sourceBlock.setPrevious(targetBlock);
		else //previous in same section
			sourceBlock.setNext(targetBlock);

	}
	public void setup()
	{

		//3 cases... 3-section junction, 2-section junction with switch before, 2-section junction with switch after
		//KNOWN BUGS:: ISSUE FOR SINGLE SECTION BLOCKS... EITHER SIDE WILL BE OUT OF SECTION
		//^^^ THIS IS THE TAIL/HEAD CASE... SHOULD BE EASILY FIXED USING TAIL/HEAD CHECK
		if(switchBlock.getSwitchType().equals("-"))
		{
			setOutOfSection(switchBlock, position1);
			setOutOfSection(position1, switchBlock);
			setOutOfSection(position2, null);
		}
		else if(switchBlock.getSwitchType().equals("BEFORE"))
		{

		}
		else if(switchBlock.getSwitchType().equals("AFTER")) //mid-section switch is after fork

		{
			
			/*if(position1.getSection().equals(switchBlock.getSection())) // if in section
			{

				//getNext()... is always of lower position based on the way this is created. 
			} 
			else
			{

				//make the same type of connection change, but with the other block... 
			}*/
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
