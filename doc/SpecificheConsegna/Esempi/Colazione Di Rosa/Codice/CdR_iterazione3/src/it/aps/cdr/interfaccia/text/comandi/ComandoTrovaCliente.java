package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.Cliente;
import it.aps.cdr.dominio.ClienteID;
import it.aps.cdr.interfaccia.text.Parser;

/**
 * Comando per trovare un cliente registrato
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoTrovaCliente implements Comando{

	public static final String codiceComando = "2";
	public static final String descrizioneComando = "TROVA UN CLIENTE REGISTRATO";
	
	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui() {
		System.out.println("   Inserisci il codice del cliente da cercare: ");
		int codice = Integer.parseInt(Parser.getInstance().read());
		System.out.println();
		ClienteID clID = new ClienteID(codice);
		Cliente cl = CdR.getInstance().trovaCliente(clID);
		if (cl != null) {
			System.out.println("   " + cl);
		} else {
			System.out.println("   ERRORE: CLIENTE INESISTENTE");
		}
		
	}
	
}
