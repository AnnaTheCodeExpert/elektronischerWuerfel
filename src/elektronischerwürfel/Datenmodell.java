/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package elektronischerwürfel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 *
 * @author Anna
 */
public class Datenmodell implements Runnable
{
  private int faktor;
  private int wert;
  private volatile boolean laufend;
  private SubmissionPublisher<Integer> iPublisher;
  private ExecutorService eService;
  
  public Datenmodell()
  {
    faktor = 10;
    wert = 1;
    laufend = false;
    iPublisher = new SubmissionPublisher<>();
    eService = Executors.newSingleThreadExecutor(); 
  }
  
  public void start()
  {
    laufend = true;
    eService.submit(this);
  }
  
  public void stop()
  {
    laufend = false;
  }
  
  public void addWertSubscription(Flow.Subscriber<Integer> subscriber)
  {
    iPublisher.subscribe(subscriber);
  }
  
 
  @Override
  public void run()
  {
    while (laufend)
    {
      try
      {
        Thread.sleep(10);
      }
      catch (Exception e)
      {
        System.err.println(e);
      }
      wert = (int )(Math.random()*6+1);
      iPublisher.submit(wert);
            
    }
  }
}
