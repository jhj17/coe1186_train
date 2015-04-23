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

		System.out.println("toggled crossing");
		if(currentLineDown.equals("green"))
		{
			currentLineDown = "red";
			System.out.println("to red");
		}
		else
		{
			currentLineDown = "green";
			System.out.println("to green");

		}

	}

	public boolean isBroken()
	{
		return isBroken;

	}


/* Crossing.java toString() */
public String toString()
{

		//curent line and block
		if(currentLineDown.equals("green"))
		{
			return "green" + "                  " + greenLineBlock.getSection() + "                " + greenLineBlock.getBlockNumber();
		}
		else
		{
			return "red" + "                       " + redLineBlock.getSection() + "               " + redLineBlock.getBlockNumber();
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