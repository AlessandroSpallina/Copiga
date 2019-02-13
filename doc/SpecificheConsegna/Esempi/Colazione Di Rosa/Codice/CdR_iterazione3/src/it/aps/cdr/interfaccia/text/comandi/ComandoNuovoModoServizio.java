package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.interfaccia.text.ConsoleNuovoModoServizio;
import it.aps.cdr.interfaccia.text.Parser;

/**
 * Comando per definire una nuova modalita' di servizio
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoNuovoModoServizio implements Comando{
	
	public static final String codiceComando = "1";
	public static final String descrizioneComando = "NUOVA MODALITA' DI SERVIZIO";
	
	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui() {
		System.out.println("   Inserisci il nome della modalita' di servizio: ");
		String nome = Parser.getInstance().read();
		System.out.println();
		if (CdR.getInstance().getModoServizio(nome) == null) {
			System.out.println("   Inserisci la descrizione della modalita' di servizio: ");
			String descr = Parser.getInstance().read();
			System.out.println();
			System.out.println("   Inserisci il fattore moltiplicativo della modalita' di servizio: ");
			double fm = Double.parseDouble(Parser.getInstance().read());
			System.out.println();		
			CdR.getInstance().nuovoModoServizio(nome,descr,fm);
			ConsoleNuovoModoServizio nmsc = new ConsoleNuovoModoServizio();
			nmsc.start();
		} else {
			System.out.println("   Esiste gia' una modalita' di servizio di nome : " + nome);
		}
	}
}
