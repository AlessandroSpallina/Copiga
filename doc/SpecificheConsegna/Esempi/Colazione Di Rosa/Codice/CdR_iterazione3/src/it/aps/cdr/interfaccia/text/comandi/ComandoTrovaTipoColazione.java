package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.TipoColazione;
import it.aps.cdr.dominio.TipoColazioneID;
import it.aps.cdr.interfaccia.text.Parser;

/**
 * Comando per trovare un tipo di colazione registrato
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoTrovaTipoColazione implements Comando {

	public static final String codiceComando = "2";
	public static final String descrizioneComando = "TROVA UN TIPO DI COLAZIONE";
	
	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui() {
		System.out.println("   Inserisci il codice del tipo di colazione da cercare: ");
		int codice = Integer.parseInt(Parser.getInstance().read());
		System.out.println();
		TipoColazioneID tcID = new TipoColazioneID(codice);
		TipoColazione tc = CdR.getInstance().trovaTipoColazione(tcID);
		if (tc != null) {
			System.out.println("   " + tc);
		} else {
			System.out.println("   ERRORE: TIPO COLAZIONE INESISTENTE");
		}
	}

}
