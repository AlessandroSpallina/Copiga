package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.dominio.CategoriaCliente;
import it.aps.cdr.dominio.CategoriaClienteID;
import it.aps.cdr.dominio.CdR;
import it.aps.cdr.interfaccia.text.Parser;

/**
 * Comando per trovare una categoria di cliente registrata
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoTrovaCategoriaCliente implements Comando{

	public static final String codiceComando = "2";
	public static final String descrizioneComando = "TROVA UNA CATEGORIA DI CLIENTE";
	
	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui() {
		System.out.println("   Inserisci il codice della categoria di cliente da cercare: ");
		int codice = Integer.parseInt(Parser.getInstance().read());
		System.out.println();
		CategoriaClienteID ccID = new CategoriaClienteID(codice);
		CategoriaCliente cc = CdR.getInstance().trovaCategoriaCliente(ccID);
		if (cc != null) {
			System.out.println("   " + cc);
		} else {
			System.out.println("   ERRORE: CATEGORIA CLIENTE INESISTENTE");
		}
	}
	
}
