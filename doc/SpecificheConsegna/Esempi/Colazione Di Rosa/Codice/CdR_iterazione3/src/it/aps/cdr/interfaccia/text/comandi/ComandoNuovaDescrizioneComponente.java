package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.Prezzo;
import it.aps.cdr.interfaccia.text.ConsoleNuovaDescrizioneComponente;
import it.aps.cdr.interfaccia.text.Parser;

/**
 * Comando per definire una nuova descrizione di una componente
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoNuovaDescrizioneComponente implements Comando {

	public static final String codiceComando = "1";
	public static final String descrizioneComando = "NUOVA DESCRIZIONE COMPONENTE";
	
	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui() {
		System.out.println("   Inserisci il nome della componente: ");
		String nome = Parser.getInstance().read();
		System.out.println();
		if (CdR.getInstance().trovaDescrizioneComponente(nome) == null) {
			System.out.println("   Inserisci il prezzo aggiuntivo della componente: ");
			double pa = Double.parseDouble(Parser.getInstance().read());
			System.out.println();
			System.out.println("   Inserisci il prezzo riduttivo della componente: ");
			double pr = Double.parseDouble(Parser.getInstance().read());
			System.out.println();		
			CdR.getInstance().nuovaDescrizioneComponente(nome,new Prezzo(pa),new Prezzo(pr));
			ConsoleNuovaDescrizioneComponente ndcc= new ConsoleNuovaDescrizioneComponente();
			ndcc.start();
		} else {
			System.out.println("   Esiste gia' una componente di nome : " + nome);
		}
	}
	
}
