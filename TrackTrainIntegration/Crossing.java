
public class Crossing  {



	Block redLineBlock = null;
	Block greenLineBlock = null;
	String currentLineDown = null;
	boolean isBroken = false;


	public Crossing(Block newBlock)
	{
		if(newBlock.getLine().equals("red"))
		{
			redLineBlock = newBlock;
			currentLineDown = "red";
		}
		else
		{
			greenLineBlock = newBlock;
			currentLineDown = "green";
		}

	}
	public void addBlock(Block newBlock)
	{
		if(newBlock.getLine().equals("red"))
		{
			redLineBlock = newBlock;
		}
		else
		{
			greenLineBlock = newBlock;
		}

	}
	public void toggleCrossing() {

		if(currentLineDown.equals("green"))
		{
			currentLineDown = "red";
		}
		else
		{
			currentLineDown = "green";
		}

	}

	public boolean isBroken()
	{
		return isBroken;

	}


	public String toString()
	{

		//curent line and block
		if(currentLineDown.equals("green"))
		{
			return "green" + "\t" + greenLineBlock.getSection() + greenLineBlock.getBlockNumber();
		}
		else
		{
			return "red" + "\t" + redLineBlock.getSection() + redLineBlock.getBlockNumber();
		}

	}

	public boolean getCrossingState(String line) 
	{
		if(currentLineDown.equals(line))
		{
			return true;
		}
		else
		{
			return false;
		}

	}



}
