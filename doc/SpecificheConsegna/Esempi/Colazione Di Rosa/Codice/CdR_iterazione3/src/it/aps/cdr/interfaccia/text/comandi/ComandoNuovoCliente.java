package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.interfaccia.text.ConsoleNuovoCliente;
import it.aps.cdr.interfaccia.text.Parser;

/**
 * Comando per definire un nuovo cliente
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoNuovoCliente implements Comando{
	public static final String codiceComando = "1";
	public static final String descrizioneComando = "REGISTRA UN NUOVO CLIENTE";
	
	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui() {
		System.out.println("   Inserisci il nome del cliente: ");
		String nome = Parser.getInstance().read();
		System.out.println();
		System.out.println("   Inserisci il cognome del cliente: ");
		String cognome = Parser.getInstance().read();
		System.out.println();
		System.out.println("   Inserisci l'indirizzo del cliente: ");
		String indirizzo = Parser.getInstance().read();
		System.out.println();
		CdR.getInstance().nuovoCliente(nome,cognome,indirizzo);
		ConsoleNuovoCliente ncc = new ConsoleNuovoCliente();
		ncc.start();
	}
	
}
