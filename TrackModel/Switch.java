import java.util.ArrayList;


public class Switch implements SwitchInterface {

	
	ArrayList<Block> switchBlocks;
	
	//switchState: boolean
	int switchNumber;
	//nextBlock: blocK ?
	//switchBroken: boolean; 
	
	
	public Switch(Block block, int switchNum)
	{
		
		switchBlocks = new ArrayList<Block>();
		switchBlocks.add(block);
		switchNumber = switchNum;
		
		
	}

	
	public ArrayList<Block> getSwitchBlocks()
	{
		
		return switchBlocks;
	}
	
	@Override
	public void toggleSwitch() {
		// TODO Auto-generated method stub

		// for all blocks in the switch 
		for(Block setupSwitch: switchBlocks)
		{
		//	System.out.println("Connections to: " + setupSwitch.getBlockNumber());
			//if it has multiple alternatives 
			if(setupSwitch.getSwitchBlocks1() != null && setupSwitch.getSwitchBlocks1().size()>1)
			{
				//for each of those alternatives 
				for(Block innerBlocks: setupSwitch.getSwitchBlocks1())
				{
				//	System.out.println("are: " + innerBlocks.getBlockNumber());

					//if that alternative is not the current, switch it 
					if(innerBlocks != setupSwitch.getNext1())
					{
						//System.out.println("current: " + setupSwitch.getNext1().getBlockNumber());
						//System.out.println("Set new to:" +innerBlocks.getBlockNumber());
						setupSwitch.setNext1(innerBlocks);
						return;
						
					}
					
				}
				
			}
				
			if(setupSwitch.getSwitchBlocks2() != null && setupSwitch.getSwitchBlocks2().size()>1)
			{
				for(Block innerBlocks: setupSwitch.getSwitchBlocks1())
				{
					
					if(innerBlocks != setupSwitch.getNext2())
					{
						setupSwitch.setNext2(innerBlocks);
					}
					
				}
				
			}
			
		}

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

	public int getSwitchNumber() {
		// TODO Auto-generated method stub
		return switchNumber;
	}

	public void addBlock(Block currentBlock) {
		// TODO Auto-generated method stub
		switchBlocks.add(currentBlock);
	}


	public void adjustConnections() {
		// TODO Auto-generated method stub
		

		for(Block blocks: switchBlocks)
		{
			
			//1 way head
			if(blocks.getDirectionArrow().equals("1Head"))
			{

				// change 1-way head nexts to tails, 2-sided heads, 
				for(Block makeAdjusts: switchBlocks)
				{

					if(makeAdjusts != blocks) 
					{
							if(makeAdjusts.getDirectionArrow().equals("1Tail")||makeAdjusts.getDirectionArrow().equals("2Head"))
							{
								makeAdjusts.printBlock();
								blocks.addSwitchBlock1(makeAdjusts);
							}
							
							if(makeAdjusts.getDirectionArrow().equals("1mid") && !makeAdjusts.getSection().equals(blocks.getSection()))
							{
								
								blocks.addSwitchBlock1(makeAdjusts);
								
							}
							if(makeAdjusts.getDirectionArrow().equals("1mid") && makeAdjusts.getSection().equals(blocks.getSection()))
							{
								
								blocks.addSwitchBlock2(makeAdjusts);
								
							}
			
					}
	
				}
				
				// 1-head nexts = tail or 2-head node 
				//previous is previous 
				
			}
			//tail 
			else if(blocks.getDirectionArrow().equals("1Tail"))
			{
				
				//1-headed tail nexts = same
				//previous is any other heads 
				
				
				for(Block makeAdjusts: switchBlocks)
				{

					
					if(makeAdjusts != blocks) 
					{
						
						if(makeAdjusts.getDirectionArrow().equals("1Head") ||makeAdjusts.getDirectionArrow().equals("2Head"))
						{
							blocks.addSwitchBlock2(makeAdjusts);
							
						}						
							
					}
	
				}
					
			}
			//2-sided head
			else if(blocks.getDirectionArrow().equals("2Head"))
			{
				
				int nextChoice = whichNext(blocks);
	
				if(nextChoice == 1)
				{
					
					for(Block makeAdjusts: switchBlocks)
					{

						
						if(makeAdjusts != blocks) 
						{
							
							if(makeAdjusts.getDirectionArrow().equals("1Tail")||makeAdjusts.getDirectionArrow().equals("2Head")||makeAdjusts.getDirectionArrow().equals("2mid"))
							{
								blocks.addSwitchBlock1(makeAdjusts);
								
							}
								
						}
		
					}
					
				}
				else if(nextChoice == 2)
				{
					
					for(Block makeAdjusts: switchBlocks)
					{	
						if(makeAdjusts != blocks) 
						{
							
							if(makeAdjusts.getDirectionArrow().equals("1Tail")||makeAdjusts.getDirectionArrow().equals("2Head")||makeAdjusts.getDirectionArrow().equals("2mid"))
							{
								blocks.addSwitchBlock2(makeAdjusts);
							}
								
						}
		
					}
	
				}
				
				//previous is the same
				//make sure changing right side by comparing with the other 
				//nexts: either tail or another 2-head
				// previous: same 
				
				
			}
			
			//1-sided mid
			else if(blocks.getDirectionArrow().equals("1mid"))
			{
				
				for(Block makeAdjusts: switchBlocks)
				{	
						if(makeAdjusts != blocks) 
						{
						
							if(makeAdjusts.getDirectionArrow().equals("1Head") && !makeAdjusts.getSection().equals(blocks.getSection()))
							{
								blocks.addSwitchBlock2(makeAdjusts);
							}
						}
				}
			}
			//2-sided mid
			else if(blocks.getDirectionArrow().equals("2mid"))
			{
				
				
			}
			
			for(Block setupSwitch: switchBlocks)
			{
	
				blocks.addSwitchBlock1(setupSwitch);
				blocks.addSwitchBlock2(setupSwitch);
				
	/*				if(whichNext(setupSwitch) == 1)
					{
						blocks.addSwitchBlock1(setupSwitch);
						
						//setupSwitch.setNext1(setupSwitch.getSwitchBlocks1().get(0));
					}
					else if(whichNext(setupSwitch) == 2)
					{
						blocks.addSwitchBlock2(setupSwitch);

						//setupSwitch.setNext2(setupSwitch.getSwitchBlocks2().get(0));	
					}	
				*/
				
			}
		}
		
		
		
		
		
		
		
		
			
	}


	private int whichNext(Block blocks) {
		// TODO Auto-generated method stub
		
		System.out.println(blocks.getSection());
		blocks.printBlock();
		if(blocks.getNext1() == null)
		{
			
			return 1;
		}
		else if(blocks.getNext2() == null)
		{
			
			return 2;
		}
		else if(blocks.getNext1().getSection().equals(blocks.getSection()))
		{
			return 2;
		}
		
		return 1;
	}


	public void printBlocks() {
		// TODO Auto-generated method stub
		
		System.out.println("Switch " + switchNumber);
		for(Block temp: switchBlocks)
		{
			temp.printSwitch();
			
		}
		System.out.println();
	}

}
