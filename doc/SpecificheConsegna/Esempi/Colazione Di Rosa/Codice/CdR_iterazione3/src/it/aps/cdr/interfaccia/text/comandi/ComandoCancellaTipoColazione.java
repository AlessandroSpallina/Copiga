package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.TipoColazione;
import it.aps.cdr.dominio.TipoColazioneID;
import it.aps.cdr.interfaccia.text.Parser;
import it.aps.cdr.persistenza.dao.DAOException;
import it.aps.cdr.persistenza.dao.DAOTipoColazione;

/**
 * Comando per cancellare un tipo di colazione 
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoCancellaTipoColazione implements Comando {

	public static final String codiceComando = "5";
	public static final String descrizioneComando = "CANCELLA UN TIPO DI COLAZIONE";
	
	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui() {
		System.out.println("   Inserisci il codice del tipo di colazione da cancellare: ");
		int codice = Integer.parseInt(Parser.getInstance().read());
		System.out.println();
		TipoColazioneID tcID = new TipoColazioneID(codice);
		TipoColazione tc = CdR.getInstance().cancellaTipoColazione(tcID);
		if (tc == null) {
			System.out.println("   ERRORE: TIPO COLAZIONE INESISTENTE");
		}
	}
	
}
