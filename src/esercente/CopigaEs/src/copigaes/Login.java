package copigaes;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *  https://code.google.com/archive/p/json-simple/wikis/DecodingExamples.wiki
 * @author manlio
 */

public class Login {
    
    public void Login(){
        System.out.println("Starting connection to copi.ga...");
    }
    
    
    // connection
    private static HttpURLConnection con;
    
    // token
    private String token;
    
    public void connect(String email, String passw) throws MalformedURLException, IOException{
        
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
            
            //qui viene scritto il contenuto della risposta delle API
            StringBuilder content;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String line;
                content = new StringBuilder();
                while ((line = in.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }
            
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(content.toString());
            token = (String) json.get("access_token");
            System.out.println(token);
            
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
    
    public void logout(){
        String url = "https://copi.ga/api/v1/logout";
        
        String urlParameters = "token="+token;
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
            /*
            StringBuilder content;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String line;
            content = new StringBuilder();
            while ((line = in.readLine()) != null) {
            content.append(line);
            content.append(System.lineSeparator());
            }
            }*/
            
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    
    public void accepted(){
        String url = "https://copi.ga/api/v1/me";
        
        String urlParameters = "token="+token;
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
            
            
            System.out.println(content);
            
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
    public void diffJobs(String date){
        String url = "https://copi.ga/api/v1/diffjobs";
        
        String urlParameters = "token="+token+"&time="+date;
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
            System.out.println(content);
            Object obj = JSONValue.parse(content.toString());
            JSONArray array = (JSONArray)obj;
            JSONObject object = (JSONObject)array.get(1);
            System.out.println(object.get("time"));
            /*JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(content.toString());
            
            
            String tempo = (String) json.get("time");
            System.out.println(tempo);*/
            
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    // [{"time":"16:47 25-02-2019"},{"time":"17:23 25-02-2019"},{"time":"17:29 25-02-2019"}]
    
    public static void main(String args[]) throws IOException, ParseException{
        
        Login login = new Login();
        String id = "dadanilo@a.a";
        String pw = "aaaaaaaa";
        login.connect(id, pw);
        System.out.println(login.token);
        
        String data = "00:00 1-1-2019";
        login.diffJobs(data);
        
        
    }
}
    
