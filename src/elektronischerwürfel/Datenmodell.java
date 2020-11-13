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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anna
 */
public class Datenmodell implements Runnable
{
  private int faktor;
  private int wert;
  private volatile boolean laufend;
  private volatile boolean stoppen;
  private SubmissionPublisher<Integer> iPublisher;
  private ExecutorService eService;
  
  public Datenmodell()
  {
    faktor = 10;
    wert = 1;
    laufend = false;
    stoppen = false;
    
    iPublisher = new SubmissionPublisher<>();
    eService = Executors.newSingleThreadExecutor(); 
  }
  
  public synchronized void start()
  {
    stoppen = false;
    notify();
    laufend = true;
    eService.submit(this);
  }
  
  public void stop() 
  {
    stoppen = true;
  }
  
  public void addWertSubscription(Flow.Subscriber<Integer> subscriber)
  {
    iPublisher.subscribe(subscriber);
  }
  
  @Override
  public synchronized void run() 
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
      if(stoppen)
      {
        try
        {
          wait();
        }
        catch (InterruptedException ex)
        {
          Logger.getLogger(Datenmodell.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
           
    }
  }
}
