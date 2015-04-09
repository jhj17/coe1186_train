package trackControllerFinal;

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
		// grab expression to verify proceed
		Expression e = jexl.createExpression(canProceedExpr);

		// populate the context
		JexlContext context = new MapContext();
		context.set("b1_occupied", nextBlock.isBlockOccupied());
		context.set("b2_occupied", destinationBlock.isBlockOccupied());

		// evaluate expression with variables
		boolean result = (boolean) e.evaluate(context);

		return result;
	}

	public boolean verifyToggleSwitch(Block nextBlock, Block destinationBlock) {
		// grab expression to verify we can switch
		Expression e = jexl.createExpression(canSwitchExpr);

		// populate the context
		JexlContext context = new MapContext();
		context.set("b1_occupied", nextBlock.isBlockOccupied());
		context.set("b2_occupied", destinationBlock.isBlockOccupied());

		// evaluate expression with variables
		boolean result = (boolean) e.evaluate(context);

		return result;
	}

	/**
	 * Function to decide if the CTC can close a block for maintenance
	 * @param prevBlock object of the previous block to the one to be closed
	 * @param desiredBlock object of block to be closed
	 * @return whether or not that block can be closed
	 */
	public boolean verifySetMaint(Block prevBlock, Block desiredBlock) {
		// grab expression to verify maintenance
		Expression e = jexl.createExpression(canMaintExpr);

		// populate the context
		JexlContext context = new MapContext();
		//context.set("b1_occupied", b1_occupied);

		// evaluate expression with variables
		boolean result = (boolean) e.evaluate(context);

		return result;
	}
}
