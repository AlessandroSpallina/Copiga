package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.interfaccia.text.ConsoleGestioneClienti;

/**
 * Comando per accedere alla console principale per la gestione 
 * dei clienti.
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoGestioneClienti implements Comando{

	public static final String codiceComando="4";
	public static final String descrizioneComando="MENU' GESTIONE DEI CLIENTI";
	
	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui() {
		ConsoleGestioneClienti gcc = new ConsoleGestioneClienti();
		gcc.start();
	}
	
}
