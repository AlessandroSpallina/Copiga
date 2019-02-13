package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.Prezzo;
import it.aps.cdr.interfaccia.text.Parser;

/**
 * Comando per definire il prezzo di un tipo di colazione.
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri
 * @author Gabriele Rendina
 * 
 * @version 0.3
 *
 */
public class ComandoDefinisciPrezzo implements Comando{
	
	public static final String codiceComando="2";
	public static final String descrizioneComando="DEFINISCI IL PREZZO";
	
	public String getCodiceComando() {
		return codiceComando;
	}
	public String getDescrizioneComando() {
		return descrizioneComando;
	}
	
	public void esegui() {
		System.out.println("   Definisci il prezzo della colazione: ");
		double prezzo = Double.parseDouble(Parser.getInstance().read());
		System.out.println();
		CdR.getInstance().definisciPrezzo(new Prezzo(prezzo));
	}

}
