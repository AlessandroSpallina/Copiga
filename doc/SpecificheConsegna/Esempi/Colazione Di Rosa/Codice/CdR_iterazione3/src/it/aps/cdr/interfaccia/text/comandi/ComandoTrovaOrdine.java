package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.Ordine;
import it.aps.cdr.dominio.OrdineID;
import it.aps.cdr.interfaccia.text.Parser;

/**
 * Comando per trovare un ordine registrato
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoTrovaOrdine implements Comando{

	public static final String codiceComando = "2";
	public static final String descrizioneComando = "TROVA UN ORDINE";
	
	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui() {
		System.out.println("   Inserisci il codice dell' ordine da cercare: ");
		int codice = Integer.parseInt(Parser.getInstance().read());
		System.out.println();
		OrdineID oID = new OrdineID(codice);
		Ordine o = CdR.getInstance().getOrdine(oID);
		if (o != null) {
			System.out.println("   " + o);
		} else {
			System.out.println("   ERRORE: ORDINE INESISTENTE");
		}
	}
	
}
