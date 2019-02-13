package it.aps.cdr.interfaccia.text;

import it.aps.cdr.dominio.CdR;

/**
 * Comando per creare un nuovo tipo di colazione
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri
 * @author Gabriele Rendina
 * 
 * @version 0.1
 *
 */
public class ComandoNuovoTipoColazione implements Comando{

	public static final String codiceComando="2";
	public static final String descrizioneComando="nuovo tipo di colazione";
	
   	public String getCodiceComando() {
		return codiceComando;
	}
	
   	public String getDescrizioneComando() {
		return descrizioneComando;
	}

    public void esegui(CdR colazione) {
		System.out.println("   Inserisci il codice della colazione: ");
		String codice_colazione = Parser.getInstance().read();
		System.out.println("   Inserisci il nome della colazione: ");
		String nome_colazione = Parser.getInstance().read();
		System.out.println();
		colazione.nuovoTipoColazione(codice_colazione, nome_colazione);
		NuovoTipoColazioneConsole ntcc= new NuovoTipoColazioneConsole();
		ntcc.start(colazione);
	}

}
