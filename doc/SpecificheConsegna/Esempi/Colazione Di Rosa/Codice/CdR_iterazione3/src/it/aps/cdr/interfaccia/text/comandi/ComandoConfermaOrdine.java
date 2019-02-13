package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.Ordine;
import it.aps.cdr.persistenza.dao.DAOCliente;
import it.aps.cdr.persistenza.dao.DAOException;
import it.aps.cdr.persistenza.dao.DAOOrdine;

/**
 * Comando per registrare l'ordine corrente. 
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoConfermaOrdine implements Comando{
	
	public static final String codiceComando = "3";
	public static final String descrizioneComando = "CONFERMA ORDINE";
	
	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui() {
		// Ordine o = CdR.getInstance().getOrdineCorrente();
		CdR.getInstance().confermaOrdine();
	}

}
