/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package copigaes;
import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manlio
 */
public class Connection {
    public void connect(){
        try {
            URL url = new URL("https://copi.ga/api/v1/"); // questo link è per le API
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            
            // @Ale: implementa un metodo GET che mi permetta di verificarne l'esistenza;
            con.setRequestMethod("GET");
        } catch (MalformedURLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
