package it.aps.cdr.interfaccia.text;

import it.aps.cdr.dominio.CdR;

/**
 * Comando per iniziare un nuovo ordine.
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.1
 * 
 */
public class ComandoNuovoOrdine implements Comando {

	public static final String codiceComando="3";
	public static final String descrizioneComando="nuovo ordine";
	
   	public String getCodiceComando() {
		return codiceComando;
	}
	
   	public String getDescrizioneComando() {
		return descrizioneComando;
	}

    public void esegui(CdR colazione) {
		colazione.nuovoOrdine();
		NuovoOrdineConsole noc = new NuovoOrdineConsole();
		noc.start(colazione);
	}
	
}
