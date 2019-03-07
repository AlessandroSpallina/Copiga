/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package copigaes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author manlio
 */
public class APIclientTest {
    
    public APIclientTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of connect method, of class APIclient.
     */
    @Test
    public void testConnect() throws Exception {
        System.out.println("connect normale");
        String email = "dadanilo@a.a";
        String passw = "aaaaaaaa";
        APIclient instance = new APIclient();
        instance.connect(email, passw);
        int actual = instance.getHttpStatus();
        int expected = 200;
        assertEquals(expected, actual);
        // TODO review the generated test code and remove the default call to fail.
        System.out.println("\n"+actual);
    }
    
    @Test
    public void testWrongConnection() throws Exception {
        System.out.println("connect se id e pw errati");
        String email = "manlio";
        String passw = "alessandro";
        APIclient instance = new APIclient();
        instance.connect(email, passw);
        int actual = instance.getHttpStatus();
        int expected = 401;
        assertEquals(expected, actual);
        // TODO review the generated test code and remove the default call to fail.
        System.out.println("\n"+actual);
    }

    
    //Test of diffJobs method, of class APIclient.
    // Al momento di questo test, la lista dei job è di due elementi.
    @Test
    public void testDiffJobs() throws IOException {
        System.out.println("diffJobs normale");
        List<Map<String, String>> actual = new ArrayList<Map<String, String>>();
        String date = "00:00 1-1-2019";
        APIclient instance = new APIclient();
        instance.connect("dadanilo@a.a", "aaaaaaaa");
        actual = instance.diffJobs(date);
        int expected = 2;
        assertEquals(expected, actual.size());
    }
    
    // in questo test viene effettuata una query al database che richiede l'elenco
    // dei jobs con date che presentano formati differenti.
    // a seconda del formato, possiamo vedere se il cast a date viene eseguito
    // correttamente nel lato server.
    @Test
    public void testDiffJobsWrongDate() throws IOException {
        System.out.println("diffJobs con data errata");
        List<Map<String, String>> actual = new ArrayList<Map<String, String>>();
        String dates [] = {
            "00:00 1-1-3019",
            "01-01-2019 00:00",
            "00:00 1/1/2019",
            "stringa random"
        };
        int i = 0;
        int expected;
        APIclient instance = new APIclient();
        instance.connect("dadanilo@a.a", "aaaaaaaa");
        for(i=0; i< dates.length; i++){
            switch(i){
                case 0:
                    actual = instance.diffJobs(dates[i]);
                    expected = 0;
                    if(expected!=actual.size()){
                        fail("diffJobs con data errata: errore nel caso 0");
                    }
                    else assertEquals(expected, actual.size());
                    break;
                case 1:
                    actual = instance.diffJobs(dates[i]);
                    expected = 2;
                    if(expected!=actual.size()){
                        fail("diffJobs con data errata: errore nel caso 1");
                    }
                    else assertEquals(expected, actual.size());
                    break;
                case 2:
                    actual = instance.diffJobs(dates[i]);
                    expected = 2;
                    if(expected!=actual.size()){
                        fail("diffJobs con data errata: errore nel caso 2");
                    }
                    else assertEquals(expected, actual.size());
                    break;
                case 3:
                    // è stato riscontrato un bug lato server!!!!
                    actual = instance.diffJobs(dates[i]);
                    expected = 0;
                    if(expected!=actual.size()){
                        fail("diffJobs con data errata: errore nel caso 3");
                    }
                    else assertEquals(expected, actual.size());
                    break;
            }
        }
    }
    
    
    // in questo test si effettua una query per UNO SPECIFICO task, partendo dalla
    // sua ora esatta in cui esso è stato generato
    @Test
    public void testDiffJobsExactDate() throws IOException {
        System.out.println("diffJobs con specifica data");
        List<Map<String, String>> actual = new ArrayList<Map<String, String>>();
        String date = "17:18 05-03-2019";
        APIclient instance = new APIclient();
        instance.connect("dadanilo@a.a", "aaaaaaaa");
        actual = instance.diffJobs(date);
        int expected = 1;
        assertEquals(expected, actual.size());
    }
    
    
    /*
    //Test of accettaTask method, of class APIclient.
    
    @Test
    public void testAccettaTask() {
    System.out.println("accettaTask");
    String id = "";
    APIclient instance = new APIclient();
    instance.accettaTask(id);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
    }
    
    //Test of printed method, of class APIclient.
    
    @Test
    public void testPrinted() {
    System.out.println("printed");
    String id = "";
    APIclient instance = new APIclient();
    instance.printed(id);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
    }
    */
}
