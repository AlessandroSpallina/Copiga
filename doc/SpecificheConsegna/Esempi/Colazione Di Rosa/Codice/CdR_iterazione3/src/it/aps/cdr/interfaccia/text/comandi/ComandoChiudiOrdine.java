package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.Ordine;
import it.aps.cdr.dominio.OrdineID;
import it.aps.cdr.interfaccia.text.Parser;
import it.aps.cdr.persistenza.dao.DAOException;
import it.aps.cdr.persistenza.dao.DAOOrdine;

/**
 * Comando per chiudere un'ordine 
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoChiudiOrdine implements Comando {

	public static final String codiceComando = "6";
	public static final String descrizioneComando = "CHIUDE UN'ORDINE";
	
	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui() {
		boolean risultato = false;
		System.out.println("   Inserisci il codice dell' ordine da chiudere: ");
		int codice = Integer.parseInt(Parser.getInstance().read());
		System.out.println();
		OrdineID oID = new OrdineID(codice);
		risultato = CdR.getInstance().chiudiOrdine(oID);
		if(!risultato){
			System.out.println("   ERRORE: ORDINE INESISTENTE");
		}
	}
	
}
