/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package copigaes;

import java.awt.Desktop;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manlio
 */
public class OpenURL {
    //openWebpage
    OpenURL(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
        try {
            desktop.browse(uri);
            //return true;
        } 
        catch (Exception e) {
            e.printStackTrace();
            }
        }
    }

    /*
    public boolean openWebpage(URL url) {
        try {;
            return openWebPage(url.toURI());
        } catch (URISyntaxException ex) {
            Logger.getLogger(OpenURL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    } */
    
}
