package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.TipoColazione;
import it.aps.cdr.persistenza.dao.DAOException;
import it.aps.cdr.persistenza.dao.DAOTipoColazione;

/**
 * Comando per registrare il tipo di colazione corrente.
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri
 * @author Gabriele Rendina
 * 
 * @version 0.3
 *
 */
public class ComandoConfermaTipoColazione implements Comando{
	
	public final static String codiceComando = "3";
	public final static String descrizioneComando = "CONFERMA";
	
	public String getCodiceComando() {
		return codiceComando;
	}
	
	public String getDescrizioneComando() {
		return descrizioneComando;
	}
	
	public void esegui() {		
		// TipoColazione cl = CdR.getInstance().getTipoColazioneCorrente();
		CdR.getInstance().confermaTipoColazione();
	}

}
