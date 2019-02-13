package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.Cliente;
import it.aps.cdr.dominio.ClienteID;
import it.aps.cdr.interfaccia.text.Parser;
import it.aps.cdr.persistenza.PersistenceFacade;
import it.aps.cdr.persistenza.dao.DAOException;

/**
 * Comando per aggiornare un cliente  
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoAggiornaCliente implements Comando {

	public static final String codiceComando = "4";
	public static final String descrizioneComando = "MODIFICA I DATI DI UN CLIENTE REGISTRATO";
	
	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui() {
		System.out.println("   Inserisci il codice del cliente da aggiornare: ");
		int codice = Integer.parseInt(Parser.getInstance().read());
		System.out.println();
		Cliente cl = CdR.getInstance().trovaCliente(new ClienteID(codice));
		if (cl != null) {
			System.out.println(cl);
			System.out.println("   Inserisci il nuovo nome: ");
			String nome = Parser.getInstance().read();
			System.out.println();
			System.out.println("   Inserisci il nuovo cognome: ");
			String cognome = Parser.getInstance().read();
			System.out.println();
			System.out.println("   Inserisci il nuovo indirizzo: ");
			String indirizzo = Parser.getInstance().read();
			System.out.println();	
		
			cl.setNome(nome);
			cl.setCognome(cognome);
			cl.setIndirizzo(indirizzo);
			
			PersistenceFacade.getInstance().aggiornaCliente(cl); 
		} else 
			System.out.println("   ERRORE: CLIENTE INESISTENTE");
	}
	
}
