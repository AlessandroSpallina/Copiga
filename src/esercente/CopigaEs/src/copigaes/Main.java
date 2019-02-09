/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package copigaes;
import static java.awt.EventQueue.invokeLater;
/**
 *
 * @author manlio
 */
public class Main {
    static Login login = new Login();
    public static void main(String args[]) {
        invokeLater(new Runnable() {
            public void run() {
                new LoginGUI(login).setVisible(true);
            }
        });
    }
}
