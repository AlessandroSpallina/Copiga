package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.DescrizioneComponenteID;
import it.aps.cdr.interfaccia.text.Parser;

/**
 * Comando per aggiungere una componente ad un tipo di colazione
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri
 * @author Gabriele Rendina
 * 
 * @version 0.3
 *
 */
public class ComandoAggiungiComponenteColazione implements Comando{
	
	public static final String codiceComando="1";
	public static final String descrizioneComando="AGGIUNGI UNA COMPONENTE";
	
	public String getCodiceComando() {
		return codiceComando;
	}
	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui() {
		boolean risultato;
		System.out.println("   Inserisci il codice della componente: ");
		int codice = Integer.parseInt(Parser.getInstance().read());
		System.out.println("   Inserisci la quantita' della componente: ");
		int quantita = Integer.parseInt(Parser.getInstance().read());
		System.out.println();
		DescrizioneComponenteID dcID = new DescrizioneComponenteID(codice);
		risultato = CdR.getInstance().aggiungiComponenteColazione(dcID,quantita);	
		if (!risultato)
			System.out.println("   ERRORE: DESCRIZIONE COMPONENTE INESISTENTE");
	}

}
