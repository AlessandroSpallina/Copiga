package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.interfaccia.text.ConsoleGestioneModiServizio;

/**
 * Comando per accedere alla console principale per la gestione 
 * delle modalita' di servizio.
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoGestioneModiServizio implements Comando{

	public static final String codiceComando="6";
	public static final String descrizioneComando="MENU' GESTIONE MODALITA' SERVIZIO";
	
	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui() {
		ConsoleGestioneModiServizio gmsc = new ConsoleGestioneModiServizio();
		gmsc.start();
	}
	
}
