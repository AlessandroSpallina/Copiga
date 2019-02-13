package it.aps.cdr.logging;

import javax.swing.JOptionPane;

import it.aps.cdr.dominio.CdR;

public class Logger {

	/** l'istanza singleton di Logger */
	private static Logger singleton;	

	/** crea un nuovo Logger */ 
	public Logger() {
	}

	/** 
	 * Restituisce il riferimento all'istanza singleton di Logger
	 * @return il riferimento all'istanza singleton di Logger 
	 */
	public static synchronized Logger getInstance(){
		if (singleton == null)
			singleton = new Logger();
		return singleton;
	}
		
	public void errore(String titolo, String messaggio) {
		JOptionPane.showMessageDialog(null,messaggio,titolo,JOptionPane.ERROR_MESSAGE,null);	
	}

	public void messaggio(String titolo,String messaggio) {
		JOptionPane.showMessageDialog(null,messaggio,titolo,JOptionPane.INFORMATION_MESSAGE,null);	
	}
	
}
