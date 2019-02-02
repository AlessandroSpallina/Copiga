package copigaes;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author manlio
 */

public class Login {
    
    public void Login(){
        System.out.println("Starting connection to copi.ga...");
    }
    
    private static HttpURLConnection con;
    
    public void Connect(String email, String passw) throws MalformedURLException, IOException{
        
        String url = "https://copi.ga/api/v1/login";
        
        String urlParameters = "email="+email+"&password="+passw;
        System.out.println(urlParameters);
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        
        try {
            
            URL myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(postData);
            }
            
            StringBuilder content;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String line;
                content = new StringBuilder();
                while ((line = in.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }
            
            System.out.println(content.toString());
            
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public int getHttpStatus(){
        int code = 0;
        try {
            code = con.getResponseCode();
            return code;
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return code;
    }
    
    public void disconnect(){
        con.disconnect();
    }
}
    
