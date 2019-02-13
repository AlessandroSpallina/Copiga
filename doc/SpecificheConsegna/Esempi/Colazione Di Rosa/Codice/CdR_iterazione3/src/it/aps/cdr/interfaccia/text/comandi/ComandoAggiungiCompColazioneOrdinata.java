package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.DescrizioneComponenteID;
import it.aps.cdr.interfaccia.text.Parser;

/**
 * Comando per aggiungere una componente ad una colazione ordinata 
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoAggiungiCompColazioneOrdinata implements Comando{

	public static final String codiceComando = "2";
	public static final String descrizioneComando = "AGGIUNGI UNA NUOVA COMPONENTE";
	
	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}
    	
	public void esegui() {
		boolean risultato = false;
		System.out.println("   Inserisci il codice della componente:");
		int codice = Integer.parseInt(Parser.getInstance().read());
		System.out.println("   Inserisci la quantita:");
		int quantita = Integer.parseInt(Parser.getInstance().read());
		DescrizioneComponenteID dcID = new DescrizioneComponenteID(codice);
		risultato = CdR.getInstance().aggiungiCompColazioneOrdinata(dcID,quantita);
		if (!risultato)
			System.out.println("   ERRORE: DESCRIZIONE COMPONENTE INESISTENTE");
	}

}
