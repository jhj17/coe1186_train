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
  * @param filename path for the PLC file
  */
 private static void readFile(String filename) {
  try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
   String currentLine;
 
   while ((currentLine = reader.readLine()) != null) {
    System.out.println(currentLine);
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
 
 public boolean verifySetMaint() {
  return true;
 }
}
