
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dosse
 */
public class ErrorMessage {
    /*class constructor*/
    ErrorMessage(){

    }
    public void showError(){
        JOptionPane.showMessageDialog(new JOptionPane(),"Cannot connect to the specified location!");
    }
}
