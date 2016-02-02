/**
 * This program runs as many threads in parallel as possible
 * @author Nicholas Liu (nl2523)
 * @version 1.0
 */
public class ThreadTester
{
	/**
	 * Main method for the Tester
	 * @param args These params are not used
	 */
   public static void main(String[] args)
   {
      for (int x = 0; x< 100000 ;x++) {
    	  Runnable test = new GreetingProducer(x);
    	  Thread testThread = new Thread(test);
    	  testThread.start();
      }	
   }
}
