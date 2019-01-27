/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package copigaes;

import copigaes.Connection;

/**
 * // id: root, password: manlioMANLIO
 * // jdbc:sqlserver://167.99.188.179:3306/copiga"
 * @author manlio
 */
public class CopigaEs { 
    /* ex main
    public static void main(String[] args) {
        ExConnector connector = new ExConnector();
        connector.Connect();
    }*/
    public static void main(String[] args){
        Connection conn = null;
        conn.connect();
    }
}
