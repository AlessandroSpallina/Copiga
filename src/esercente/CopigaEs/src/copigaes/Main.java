/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package copigaes;
import static java.awt.EventQueue.invokeLater;
import java.io.IOException;
import java.util.Scanner;
/**
 *
 * @author manlio
 */
public class Main {
   // /*
    static Login login = new Login();
    public static void main(String args[]) throws IOException {
        invokeLater(new Runnable() {
            public void run() {
                new LoginGUI(login).setVisible(true);
            }
        });
    }
   // */
}
