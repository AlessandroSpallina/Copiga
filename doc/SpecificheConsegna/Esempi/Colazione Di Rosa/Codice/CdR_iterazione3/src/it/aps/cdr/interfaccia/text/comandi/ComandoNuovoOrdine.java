package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.interfaccia.text.ConsoleNuovoOrdine;

/**
 * Comando per definire un nuovo ordine.
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoNuovoOrdine implements Comando {

	public static final String codiceComando="1";
	public static final String descrizioneComando="NUOVO ORDINE";
	
   	public String getCodiceComando() {
		return codiceComando;
	}
	
   	public String getDescrizioneComando() {
		return descrizioneComando;
	}

    public void esegui() {
		CdR.getInstance().nuovoOrdine();
		ConsoleNuovoOrdine noc = new ConsoleNuovoOrdine();
		noc.start();
	}
	
}
