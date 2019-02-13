package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.dominio.CategoriaCliente;
import it.aps.cdr.dominio.CdR;
import it.aps.cdr.persistenza.dao.DAOCategoriaCliente;
import it.aps.cdr.persistenza.dao.DAOException;

/**
 * Comando per registrare la categoria di cliente corrente 
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoConfermaNuovaCategoriaCliente implements Comando{

	public static final String codiceComando = "1";
	public static final String descrizioneComando = "CONFERMA";
	
	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui() {
		// CategoriaCliente cl = CdR.getInstance().getCategoriaClienteCorrente();		
		CdR.getInstance().confermaCategoriaCliente();
	}
}
