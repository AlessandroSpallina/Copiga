package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.Cliente;
import it.aps.cdr.dominio.ClienteID;
import it.aps.cdr.interfaccia.text.Parser;
import it.aps.cdr.persistenza.dao.DAOCliente;
import it.aps.cdr.persistenza.dao.DAOException;

/**
 * Comando per cancellare un cliente 
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoCancellaCliente implements Comando{

	public static final String codiceComando = "5";
	public static final String descrizioneComando = "CANCELLA UN CLIENTE REGISTRATO";
	
	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui() {
		System.out.println("   Inserisci il codice del cliente da cancellare: ");
		int codice = Integer.parseInt(Parser.getInstance().read());
		System.out.println();
		ClienteID clID = new ClienteID(codice);
		Cliente cl = CdR.getInstance().cancellaCliente(clID);
		if (cl == null) {
			System.out.println("   ERRORE: CLIENTE INESISTENTE");
		}
	}
	
}
