package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.Ordine;
import it.aps.cdr.dominio.OrdineID;
import it.aps.cdr.interfaccia.text.Parser;
import it.aps.cdr.persistenza.dao.DAOException;
import it.aps.cdr.persistenza.dao.DAOOrdine;

/**
 * Comando per cancellare un ordine 
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoCancellaOrdine implements Comando {


	public static final String codiceComando = "5";
	public static final String descrizioneComando = "CANCELLA UN ORDINE";
	
	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui() {
		System.out.println("   Inserisci il codice dell' ordine da cancellare: ");
		int codice = Integer.parseInt(Parser.getInstance().read());
		System.out.println();
		OrdineID oID = new OrdineID(codice);
		Ordine o = CdR.getInstance().cancellaOrdine(oID);
		if (o == null) {
			System.out.println("   ERRORE: ORDINE INESISTENTE");
		}

	}
	
}
