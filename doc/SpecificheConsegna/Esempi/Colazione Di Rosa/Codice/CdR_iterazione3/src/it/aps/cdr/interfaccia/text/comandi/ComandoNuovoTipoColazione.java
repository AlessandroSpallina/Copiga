package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.interfaccia.text.ConsoleNuovoTipoColazione;
import it.aps.cdr.interfaccia.text.Parser;

/**
 * Comando per definire un nuovo tipo di colazione
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri
 * @author Gabriele Rendina
 * 
 * @version 0.3
 *
 */
public class ComandoNuovoTipoColazione implements Comando{

	public static final String codiceComando="1";
	public static final String descrizioneComando="CREA UN NUOVO TIPO DI COLAZIONE";
	
   	public String getCodiceComando() {
		return codiceComando;
	}
	
   	public String getDescrizioneComando() {
		return descrizioneComando;
	}

    public void esegui() {
		System.out.println("   Inserisci il nome del nuovo tipo di colazione: ");
		String nome = Parser.getInstance().read();
		System.out.println();
		if (CdR.getInstance().trovaTipoColazione(nome) == null) {
			CdR.getInstance().nuovoTipoColazione(nome);
			ConsoleNuovoTipoColazione ntcc= new ConsoleNuovoTipoColazione();
			ntcc.start();
		} else {
			System.out.println("   Esiste gia' un tipo di colazione di nome : " + nome);
		}
	}

}
