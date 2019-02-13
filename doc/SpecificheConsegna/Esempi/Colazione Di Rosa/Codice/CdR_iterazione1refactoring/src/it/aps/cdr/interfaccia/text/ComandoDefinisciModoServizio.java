package it.aps.cdr.interfaccia.text;

import java.util.Collection;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.ModoServizio;

/**
 * Comando per definire la modalità di consegna delle colazioni di un ordine 
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.1
 * 
 */
public class ComandoDefinisciModoServizio implements Comando{
	
	public static final String codiceComando = "2";
	public static final String descrizioneComando = "definisci la modalità di servizio";
	
	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui(CdR colazione) {
		System.out.println("   Modi di servizio:");
		Collection<ModoServizio> modi = 
			(Collection<ModoServizio>) colazione.getModiServizio().values();
		for (ModoServizio modo : modi)
			System.out.println("   " + modo.toString());	
		System.out.println("   Inserisci il codice del modo di servizio:");
		String codice_servizio = Parser.getInstance().read();
		colazione.definisciModoServizio(codice_servizio);
		System.out.println("Il prezzo dell'ordine è:");
		double prezzo = colazione.calcolaPrezzoOrdine();
		System.out.println(prezzo);
	}
}
