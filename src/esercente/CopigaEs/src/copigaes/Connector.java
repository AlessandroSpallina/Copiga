/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package copigaes;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author manlio
 */
public class Connector {
    private static String name = "com.mysql.jdbc.Driver";
    private static String host = "jdbc:mysql://167.99.188.179:3306/copiga";
    private static String husr = "root";
    private static String hpsw = "manlioMANLIO";
    
    private String id, pw;

    public String getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }
    
    public void Connect(){
        try {
            // Trying to connect
            Class.forName(name).newInstance();
            Connection conn = (Connection) DriverManager.getConnection(host, husr, hpsw);
            
            // Verifying connection
            System.out.println("Connected to "+ conn.getHost() );
        }
        // catching exeptions
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
