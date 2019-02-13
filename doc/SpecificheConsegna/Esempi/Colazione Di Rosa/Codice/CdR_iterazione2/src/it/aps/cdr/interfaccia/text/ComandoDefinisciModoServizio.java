package it.aps.cdr.interfaccia.text;

import java.util.Collection;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.ModoServizio;

/**
 * Comando per definire la modalità di consegna 
 * della colazione corrente dell'ordine corrente  
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.2
 * 
 */
public class ComandoDefinisciModoServizio implements Comando{
	
	public static final String codiceComando = "3";
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
		System.out.println("Il prezzo della colazione è " + colazione.calcolaPrezzoColazione());
	}
}
