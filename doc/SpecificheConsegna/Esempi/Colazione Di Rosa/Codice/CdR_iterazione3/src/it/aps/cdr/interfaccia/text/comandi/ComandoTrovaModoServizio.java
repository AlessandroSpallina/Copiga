package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.ModoServizio;
import it.aps.cdr.dominio.ModoServizioID;
import it.aps.cdr.interfaccia.text.Parser;

/**
 * Comando per trovare una modalita' di servizio registrata
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoTrovaModoServizio implements Comando {

	public static final String codiceComando = "2";
	public static final String descrizioneComando = "TROVA UNA MODALITA' SERVIZIO";

	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui() {
		System.out.println("   Inserisci il codice della modalità di servizio da cercare: ");
		int codice = Integer.parseInt(Parser.getInstance().read());
		System.out.println();
		ModoServizioID msID = new ModoServizioID(codice);
		ModoServizio ms = CdR.getInstance().getModoServizio(msID);
		if (ms != null) {
			System.out.println("   " + ms);
		} else {
			System.out.println("   ERRORE: MODO SERVIZIO INESISTENTE");
		}
	}
	
}
