package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.interfaccia.text.ConsoleGestioneTipiColazione;

/**
 * Comando per accedere alla console principale per la gestione 
 * dei tipi di colazione.
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoGestioneTipiColazione implements Comando {

	public static final String codiceComando = "1";
	public static final String descrizioneComando = "MENU' GESTIONE TIPI COLAZIONE";
	
	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui() {
		ConsoleGestioneTipiColazione gtcc = new ConsoleGestioneTipiColazione();
		gtcc.start();
	}

}
