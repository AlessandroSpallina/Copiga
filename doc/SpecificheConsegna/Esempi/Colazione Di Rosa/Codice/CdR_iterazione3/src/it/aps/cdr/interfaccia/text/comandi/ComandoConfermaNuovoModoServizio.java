package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.ModoServizio;
import it.aps.cdr.persistenza.dao.DAOException;
import it.aps.cdr.persistenza.dao.DAOModoServizio;

/**
 * Comando per registrare la modalita' di servizio corrente
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoConfermaNuovoModoServizio implements Comando {

	public static final String codiceComando = "1";
	public static final String descrizioneComando = "CONFERMA NUOVO MODO SERVIZIO";
	
	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui() {
		// ModoServizio ms = CdR.getInstance().getModoServizioCorrente();
		CdR.getInstance().confermaModoServizio();
	}
	
}
