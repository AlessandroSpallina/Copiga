package it.aps.cdr.interfaccia.text;

import java.util.Collection;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.TipoColazione;

/**
 * Comando per inserire una nuova colazione ad un ordine. 
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.2
 * 
 */
public class ComandoNuovaColazione implements Comando {

	public static final String codiceComando = "1";
	public static final String descrizioneComando = "aggiungi una colazione all'ordine";
	
	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui(CdR colazione) {
		System.out.println("   Elenco dei tipi di colazione:");
		Collection<TipoColazione> tipi 
			= (Collection<TipoColazione>) colazione.getTipiColazione().values();
		for (TipoColazione tipo : tipi)
			System.out.println("   " + tipo.toString());	
		System.out.println("   Inserisci il codice del tipo di colazione:");
		String codice_colazione = Parser.getInstance().read();
		colazione.nuovaColazioneOrdinata(codice_colazione);
		NuovaColazioneConsole ncc = new NuovaColazioneConsole();
		ncc.start(colazione);
	}

}
