package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.DescrizioneComponente;
import it.aps.cdr.persistenza.dao.DAODescrizioneComponente;
import it.aps.cdr.persistenza.dao.DAOException;

/**
 * Comando per registrare la descrizione della componente corrente 
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoConfermaDescrizioneComponente implements Comando{

	public static final String codiceComando = "1";
	public static final String descrizioneComando = "CONFERMA DESCRIZIONE COMPONENTE";
	
	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui() {
		// DescrizioneComponente dc = CdR.getInstance().getDescrizioneComponenteCorrente();
		CdR.getInstance().confermaDescrizioneComponente();
	}
	
}
