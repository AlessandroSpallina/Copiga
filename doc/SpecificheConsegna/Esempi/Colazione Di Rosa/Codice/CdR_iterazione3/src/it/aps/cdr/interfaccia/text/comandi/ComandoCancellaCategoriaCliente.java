package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.dominio.CategoriaCliente;
import it.aps.cdr.dominio.CategoriaClienteID;
import it.aps.cdr.dominio.CdR;
import it.aps.cdr.interfaccia.text.Parser;
import it.aps.cdr.persistenza.dao.DAOCategoriaCliente;
import it.aps.cdr.persistenza.dao.DAOException;

/**
 * Comando per cancellare una categoria di cliente 
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoCancellaCategoriaCliente implements Comando {

	public static final String codiceComando = "5";
	public static final String descrizioneComando = "CANCELLA UNA CATEGORIA DI CLIENTE";
	
	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui() {
		System.out.println("   Inserisci il codice della categoria del cliente da cancellare: ");
		int codice = Integer.parseInt(Parser.getInstance().read());
		System.out.println();
		CategoriaClienteID ccID = new CategoriaClienteID(codice);
		CategoriaCliente cc = CdR.getInstance().cancellaCategoriaCliente(ccID);
		if (cc == null) {
			System.out.println("   ERRORE: CATEGORIA CLIENTE INESISTENTE");
		}
		
	}
	
}
