public interface SwitchInterface {
	
	
	/*switchBlocks: block[]
	switchState: boolean
	switchNumber: int
	nextBlock: blocK ?
	switchBroken: boolean; 
	*/

	public void toggleSwitch();
	public boolean isSwitchWorking();
	public boolean getSwitchPosition();

}