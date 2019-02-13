package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.interfaccia.text.ConsoleGestioneCategorieClienti;

/**
 * Comando per accedere alla console principale per la gestione 
 * delle categorie di clienti.
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoGestioneCategorieClienti implements Comando{

	public static final String codiceComando="5";
	public static final String descrizioneComando="MENU' GESTIONE CATEGORIE CLIENTI";
	
	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui() {
		ConsoleGestioneCategorieClienti gccc = new ConsoleGestioneCategorieClienti();
		gccc.start();
	}
	
}
