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

import copigaes.Connector;

/**
 * // id: root, password: manlioMANLIO
 * // jdbc:sqlserver://167.99.188.179:3306/copiga"
 * @author manlio
 */
public class CopigaEs {
    public static void main(String[] args) {
        Connector connector = new Connector();
        connector.Connect();
    }
    
}
