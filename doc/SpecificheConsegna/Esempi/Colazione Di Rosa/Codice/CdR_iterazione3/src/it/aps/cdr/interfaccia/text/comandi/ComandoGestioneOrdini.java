package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.interfaccia.text.ConsoleGestioneOrdini;

/**
 * Comando per accedere alla console principale per la gestione 
 * degli ordini.
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoGestioneOrdini implements Comando{

	public static final String codiceComando="3";
	public static final String descrizioneComando="MENU' GESTIONE ORDINI";
	
	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui() {
		ConsoleGestioneOrdini goc = new ConsoleGestioneOrdini();
		goc.start();
	}
	
}
