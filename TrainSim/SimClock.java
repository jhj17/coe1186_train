/**
* SimClock.java
* 
* @author John Abraham
*/

class SimClock implements Runnable {

 Thread clkThread;
 private int factor;
 private int seconds;
 private int minutes;
 private int hours;
 
 public SimClock(int factor, int hours, int minutes, int seconds) {
  this.factor = factor;
  this.hours   = hours;
  this.minutes = minutes;
  this.seconds = seconds; 
 }
 
 public void setSpeedFactor(int factor) {
  this.factor  = factor;
 }
 
 public String getCurrentTime() {

  if (seconds < 10) {
  return String.valueOf(hours)+":"+
      String.valueOf(minutes)+":0"+
      String.valueOf(seconds);
  } else {
  return String.valueOf(hours)+":"+
      String.valueOf(minutes)+":"+
      String.valueOf(seconds);
  }
 }
 
 public void run() {
   while(true) {
     try {
       seconds++;
       Thread.sleep(factor);
     } catch(InterruptedException ex) {
       Thread.currentThread().interrupt();
     }

   if(seconds == 60) {seconds = 0; minutes++;}
   if(minutes == 60) {minutes = 0; hours++;}
   if(hours == 24)   {hours = 0;} 
   }
 }
}