package it.aps.cdr.interfaccia.text;

import java.util.Collection;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.DescrizioneComponente;

/**
 * Comando per aggiungere una componente ad una colazione ordinata 
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.2
 * 
 */
public class ComandoAggiungiCompColazioneOrdinata implements Comando{

	public static final String codiceComando = "2";
	public static final String descrizioneComando = "aggiungi una nuova componente";
	
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
		System.out.println("   Inserisci il codice della componente da aggiungere:");
		String codice_componente = Parser.getInstance().read();
		System.out.println("   Inserisci la quantita:");
		int quantita = Integer.valueOf(Parser.getInstance().read());
		colazione.aggiungiCompColazioneOrdinata(codice_componente, quantita);
	}

}
