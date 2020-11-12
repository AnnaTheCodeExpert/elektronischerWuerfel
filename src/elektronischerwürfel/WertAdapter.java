/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package elektronischerwürfel;

import java.util.concurrent.Flow;

/**
 *
 * @author Anna
 */
public class WertAdapter implements Flow.Subscriber<Integer>
{
  private WuerfelView view;
  private Datenmodell model;
  private Flow.Subscription subscription;
  
  public WertAdapter(WuerfelView view, Datenmodell model)
  {
    this.view = view;
    this.model = model;
  }
  
  public void einschreiben()
  {
    model.addWertSubscription(this);
  }

  @Override
  public void onSubscribe(Flow.Subscription subscription)
  {
    this.subscription = subscription;
    subscription.request(1);
  }

  @Override
  public void onNext(Integer item)
  {
    view.getLblZahl().setText(String.valueOf(item));
    subscription.request(1);
  }

  @Override
  public void onError(Throwable throwable)
  {
  }

  @Override
  public void onComplete()
  {
  }

 
}
