/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package elektronischerwürfel;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 * Builder Class
 * @author Anna
 */
public class Start
{
  public Start()
  {
    Datenmodell model = new Datenmodell();
    WuerfelView view = new WuerfelView();
    StartStopController controller = new StartStopController(view, model);
    WertAdapter wAdapter = new WertAdapter(view, model);
    controller.registerEvents();
    wAdapter.einschreiben();
    view.setVisible(true);
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) 
  {
    try
    {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch (Exception ex)
    {
      JOptionPane.showMessageDialog(null, ex.toString());
    }
    new Start();
  }
}
