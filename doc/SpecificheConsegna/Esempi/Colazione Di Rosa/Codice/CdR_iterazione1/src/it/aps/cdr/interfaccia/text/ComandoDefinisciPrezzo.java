package it.aps.cdr.interfaccia.text;

import it.aps.cdr.dominio.CdR;

/**
 * Comando per definire il prezzo di un tipo di colazione
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri
 * @author Gabriele Rendina
 * 
 * @version 0.1
 *
 */
public class ComandoDefinisciPrezzo implements Comando{
	
	public static final String codiceComando="2";
	public static final String descrizioneComando="definisci il prezzo";
	
	public String getCodiceComando() {
		return codiceComando;
	}
	public String getDescrizioneComando() {
		return descrizioneComando;
	}
	
	public void esegui(CdR colazione) {
		System.out.println("   Definisci il prezzo della colazione: ");
		String prezzo_colazione = Parser.getInstance().read();
		System.out.println();
		colazione.definisciPrezzo(Double.valueOf(prezzo_colazione));
	}

}
