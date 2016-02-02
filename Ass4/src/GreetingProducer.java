/**
 * An action that prints out what thread number it is on
 * @author Nicholas Liu (nl2523)
 *
 */
public class GreetingProducer implements Runnable
{
   /**
      Constructs the producer object.
      @param threadNum the number of Threads running
   */
   public GreetingProducer(int threadNum)
   {
      this.threadNum = threadNum;
   }

   /**
    * This runs the threads
    */
   public void run()
   {
      try 
      {
         for (int i = 1; i <= 1; i++)
         {
            System.out.println("Thread #" + threadNum);
            Thread.sleep(Long.MAX_VALUE);                     
         }
      }
      catch (InterruptedException exception)
      {
      }
   }

   private int threadNum;

   private static final int REPETITIONS = 1;
   private static final int DELAY = 100;
}