package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.interfaccia.text.ConsoleGestioneDescrizioniComponenti;

/**
 * Comando per accedere alla console principale per la gestione 
 * delle descrizioni delle componenti.
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoGestioneDescrizioniComponenti implements Comando {

	public static final String codiceComando="2";
	public static final String descrizioneComando="MENU' GESTIONE DESCRIZIONI COMPONENTI";
	
	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui() {
		ConsoleGestioneDescrizioniComponenti gdcc = new ConsoleGestioneDescrizioniComponenti();
		gdcc.start();
	}

}
