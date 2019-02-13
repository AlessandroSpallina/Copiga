package it.aps.cdr.interfaccia.text;

import java.util.Collection;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.DescrizioneComponente;

/**
 * Comando per aggiungere una componente ad un tipo di colazione
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri
 * @author Gabriele Rendina
 * 
 * @version 0.2
 *
 */
public class ComandoAggiungiComponenteColazione implements Comando{
	
	public static final String codiceComando="1";
	public static final String descrizioneComando="aggiungi una componente";
	
	public String getCodiceComando() {
		return codiceComando;
	}
	public String getDescrizioneComando() {
		return descrizioneComando;
	}
	public void esegui(CdR colazione) {
		System.out.println("   Elenco delle componenti: ");
		Collection<DescrizioneComponente> descrizioni = 
			(Collection<DescrizioneComponente>) colazione.getDescrizioniComponenti().values();
		for (DescrizioneComponente dc : descrizioni)
			System.out.println("   " + dc.toString());	
		System.out.println("   Inserisci il codice della componente: ");
		String codice_componente = Parser.getInstance().read();
		System.out.println("   Inserisci la quantità della componente: ");
		String quantita_componente = Parser.getInstance().read();
		System.out.println();
		colazione.aggiungiComponenteColazione(codice_componente, Integer.valueOf(quantita_componente));
	}

}
