/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package copigaes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
//import javax.swing.JTable;

/**
 *
 * @author manlio
 */
public class RefreshTable extends Thread{
    private JFrame frame;
    private Login login;
    private List<Map<String, String>> listaJobs = new ArrayList<Map<String, String>>();
    
    RefreshTable(JFrame frame, Login login){
        super();
        this.frame = frame;
        this.login = login;
    }
    
    public void run(){
        try {
            while(true){
                Thread.sleep(300000);
                System.out.println("troppo forte");
                frame.dispose();
                listaJobs = login.diffJobs("00:00 1-1-2019");
                new JobsTable(login, listaJobs).setVisible(true);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(RefreshTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
