package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.ModoServizio;
import it.aps.cdr.dominio.ModoServizioID;
import it.aps.cdr.interfaccia.text.Parser;
import it.aps.cdr.persistenza.dao.DAOException;
import it.aps.cdr.persistenza.dao.DAOModoServizio;

/**
 * Comando per cancellare una modalita' di servizio 
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoCancellaModoServizio implements Comando {

	public static final String codiceComando = "5";
	public static final String descrizioneComando = "CANCELLA UNA MODALITA' DI SERVIZIO";

	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui() {
		System.out.println("   Inserisci il codice della modalita' di servizio da cancellare: ");
		int codice = Integer.parseInt(Parser.getInstance().read());
		System.out.println();
		ModoServizioID msID = new ModoServizioID(codice);
		ModoServizio ms = CdR.getInstance().cancellaModoServizio(msID);
		if (ms == null) {
			System.out.println("   ERRORE: MODO SERVIZIO INESISTENTE");
		}

	}

}
