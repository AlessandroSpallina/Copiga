package copigaes;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    
    // lista dei jobs
    List<Map<String, String>> listaJobs; // il suo getter è diffJobs
    
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
    
    public List diffJobs(String date){
        
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
            
            listaJobs = new ArrayList<Map<String, String>>();
            
            System.out.println(content);
            Object obj = JSONValue.parse(content.toString());
            JSONArray array = (JSONArray)obj;
            
            // Test per gettare il tempo da un array json
            JSONObject object = (JSONObject)array.get(1);
            System.out.println("time esempio: "+object.get("time")+"\n\n");
            
            for(int i = 0; i<= array.size(); i++){
                
                Map<String, String> elementoJob = new HashMap<String,String>();
                
                // accede all'i-esimo ogge dell'array json
                object = (JSONObject)array.get(i);
                
                elementoJob.put("time", (String) object.get("time"));
                elementoJob.put("customer", (String) object.get("customer"));
                elementoJob.put("paper", (String) object.get("paper"));
                elementoJob.put("bookbinding", (String) object.get("bookbinding"));
                elementoJob.put("filename", (String) object.get("filename"));
                elementoJob.put("filelink", (String) object.get("filelink"));
                elementoJob.put("bothsides", (String) object.get("bothsides"));
                elementoJob.put("colour", (String) object.get("colour"));
                elementoJob.put("pagesforside", (String) object.get("pagesforside"));
                elementoJob.put("price", (String) object.get("price"));
                
                listaJobs.add(i, elementoJob);
                
                System.out.println("\n");
            }
            
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        /*
        System.out.println("Lista delle Map di jobs\n");
        for(Map<String, String> job : listaJobs){        
            System.out.println("time: " + job.get("time"));
            System.out.println("customer: " + job.get("customer"));
            System.out.println("paper: " + job.get("paper"));
            System.out.println("bookbinding: " + job.get("bookbinding"));
            System.out.println("filename: " + job.get("filename"));
            System.out.println("filelink: " + job.get("filelink"));
            System.out.println("bothsides: " + job.get("bothsides"));
            System.out.println("colour: " + job.get("colour"));
            System.out.println("pagesforside: " + job.get("pagesforside"));
            System.out.println("price: " + job.get("price"));
            System.out.println("\n");
        } */
        return listaJobs;
    }
    // [{"time":"16:47 25-02-2019"},{"time":"17:23 25-02-2019"},{"time":"17:29 25-02-2019"}]
    /*
    public static void main(String args[]) throws IOException, ParseException{
        
        Login login = new Login();
        String id = "dadanilo@a.a";
        String pw = "aaaaaaaa";
        login.connect(id, pw);
        System.out.println(login.token);
        
        String data = "00:00 1-1-2019";
        login.diffJobs(data);
    }
    */
}
    
