import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.jexl2.*;

public class PLCClass {

	private static JexlEngine jexl;
	private static String canProceedExpr;
	private static String canSwitchExpr;
	private static String canDoCrossingExpr;
	private static String canMaintExpr;

	boolean isOperational = true;
	public boolean isSafe = true;

	/**
	 * Constructor for PLCClass
	 * @param plcFile path to PLC file that will be loaded
	 */
	public PLCClass(String plcFile) {
		readFile(plcFile);
		jexl = new JexlEngine();
	}

	/**
	 * Function to load PLC file and create expressions for the different actions
	 * @param filename	path for the PLC file
	 */
	private static void readFile(String filename) {
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String currentLine;

			while ((currentLine = reader.readLine()) != null) {
				String[] parts = currentLine.split(":");

				if(parts[0].equals("proceed")) {
					canProceedExpr = parts[1];
				}
				else if(parts[0].equals("crossing")) {
					canDoCrossingExpr = parts[1];
				}
				else if(parts[0].equals("switch")) {
					canSwitchExpr = parts[1];
				}
				else if(parts[0].equals("maintenance")) {
					canMaintExpr = parts[1];
				}
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
	}

	/**
	 * Function to decide if the train can proceed to the next block
	 * @param nextBlock object of the next block the train would proceed to
	 * @param destinationBlock object of the 2nd next block
	 * @return whether or not the train can proceed
	 */
	public boolean verifyProceed(Block nextBlock, Block destinationBlock) {
		// Return the vital evaluation in order to proceed
		return vitalProceed(nextBlock, destinationBlock);
	}

	/**
	 * Helper function for verifyProceed that evaluates the operation safely
	 * @param nextBlock object of the next block the train would proceed to
	 * @param destinationBlock object of the 2nd next block
	 * @return whether or not the train can proceed
	 */
	private boolean vitalProceed(Block nextBlock, Block destinationBlock) {
		boolean result = true;

		// grab expression to verify proceed
		Expression e = jexl.createExpression(canProceedExpr);

		// populate the context
		JexlContext context = new MapContext();

		// do evaluation 5 times to assure correct action
		for(int iii = 0; iii < 5; iii++) {
			context.set("b1_occupied", nextBlock.isBlockOccupied());
			context.set("b2_occupied", destinationBlock.isBlockOccupied());

			context.set("b1_broken", nextBlock.isBroken());
			context.set("b2_broken", destinationBlock.isBroken());
			
			context.set("b1_closed", nextBlock.isClosed());
			context.set("b2_closed", destinationBlock.isClosed());

			// evaluate expression with variables 
			// all evaluations must be true for it to verify the proceed
			result &= (boolean) e.evaluate(context);
		}

		return result;
	}

	/**
	 * Function to decide if the track can switch or not
	 * @param nextBlock object of the next block the train would proceed to
	 * @param destinationBlock object of the 2nd next block
	 * @return whether or not there can be a switch
	 */
	public boolean verifyToggleSwitch(Block nextBlock, Block destinationBlock) {
		// Return the vital evaluation in order to switch
		return vitalSwitch(nextBlock, destinationBlock);
	}

	/**
	 * Helper function for verifyToggleSwitch that evaluates the operation safely
	 * @param nextBlock object of the next block the train would proceed to
	 * @param destinationBlock object of the 2nd next block
	 * @return whether or not there can be a switch
	 */
	private boolean vitalSwitch(Block nextBlock, Block destinationBlock) {
		boolean result = true;

		// grab expression to verify switch
		Expression e = jexl.createExpression(canSwitchExpr);

		// populate the context
		JexlContext context = new MapContext();

		// do evaluation 5 times to assure correct action
		for(int iii = 0; iii < 5; iii++) {
			context.set("b1_occupied", nextBlock.isBlockOccupied());
			context.set("b2_occupied", destinationBlock.isBlockOccupied());
			
			context.set("switch_Broken", !nextBlock.getSwitch().isSwitchWorking());

			// evaluate expression with variables 
			// all evaluations must be true for it to verify the switch
			result &= (boolean) e.evaluate(context);
		}

		return result;
	}

	/**
	 * Function to decide if the CTC can close a block for maintenance
	 * @param prevBlock object of the previous block to the one to be closed
	 * @param desiredBlock object of block to be closed
	 * @return whether or not that block can be closed
	 */
	public boolean verifySetMaint(Block prevBlock, Block desiredBlock) {
		// Return the vital evaluation in order to switch
		return vitalMaintenance(prevBlock, desiredBlock);
	}

	/**
	 * Helper function for verifySetMaint that evaluates the operation safely
	 * @param prevBlock object of the previous block to the one to be closed
	 * @param desiredBlock object of block to be closed
	 * @return whether or not the block can be closed for maintenance
	 */
	private boolean vitalMaintenance(Block prevBlock, Block desiredBlock) {
		boolean result = true;

		// grab expression to verify maintenance
		Expression e = jexl.createExpression(canMaintExpr);

		// populate the context
		JexlContext context = new MapContext();

		// do evaluation 5 times to assure correct action
		for(int iii = 0; iii < 5; iii++) {
			context.set("b1_occupied", prevBlock.isBlockOccupied());
			context.set("b2_occupied", desiredBlock.isBlockOccupied());

			// evaluate expression with variables 
			// all evaluations must be true for it to verify the closure of the block
			result &= (boolean) e.evaluate(context);
		}

		return result;
	}

	/**
	 * Function to decide whether or not the railway crossing can be activated
	 * @param prevBlock object of the previous block to the one to be closed
	 * @param desiredBlock object of block to be closed
	 * @return whether or not the railway crossing can be activated
	 */
	public boolean verifyRailwayCrossing(Block prevBlock, Block desiredBlock) {
		// Return the vital evaluation in order to do the railway crossing
		return vitalCrossing(prevBlock, desiredBlock);
	}

	/**
	 * Helper function for verifyRailwayCrossing that evaluates the operation safely
	 * @param prevBlock object of the previous block to the one to be closed
	 * @param desiredBlock object of block to be closed
	 * @return whether or not the railway crossing can be activated
	 */
	private boolean vitalCrossing(Block prevBlock, Block desiredBlock) {
		boolean result = true;

		// grab expression to verify maintenance
		Expression e = jexl.createExpression(canDoCrossingExpr);

		// populate the context
		JexlContext context = new MapContext();

		// do evaluation 5 times to assure correct action
		for(int iii = 0; iii < 5; iii++) {
			context.set("b1_occupied", prevBlock.isBlockOccupied());
			context.set("b2_occupied", desiredBlock.isBlockOccupied());
			
			context.set("crossing_broken", desiredBlock.getCrossing().isBroken());

			// evaluate expression with variables 
			// all evaluations must be true for it to verify the crossing
			result &= (boolean) e.evaluate(context);
		}

		return result;
	}
}
