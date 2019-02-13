package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.Cliente;
import it.aps.cdr.persistenza.dao.DAOCliente;
import it.aps.cdr.persistenza.dao.DAOException;

/**
 * Comando per registrare il cliente corrente
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoConfermaNuovoCliente implements Comando{

	public static final String codiceComando = "1";
	public static final String descrizioneComando = "CONFERMA NUOVO CLIENTE";
	
	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui() {
		// Cliente cl = CdR.getInstance().getClienteCorrente();
		CdR.getInstance().confermaCliente();
	}

}
