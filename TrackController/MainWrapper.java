package trackControllerFinal;

import java.awt.EventQueue;
import java.util.LinkedList;

public class MainWrapper {

 private static LinkedList<Block> track;
 
 public static void main(String[] args) {
  EventQueue.invokeLater(new Runnable() {
   public void run() {
    try {
     TrackControllerWrapper trackControllerWrapper = new TrackControllerWrapper(track);
    } catch (Exception e) {
     e.printStackTrace();
    }
   }
  });
 }
}
