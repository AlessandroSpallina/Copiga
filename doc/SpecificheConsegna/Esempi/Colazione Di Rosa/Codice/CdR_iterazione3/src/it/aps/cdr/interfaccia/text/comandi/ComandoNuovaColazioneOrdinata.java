package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.ColazioneOrdinata;
import it.aps.cdr.dominio.TipoColazioneID;
import it.aps.cdr.interfaccia.text.ConsoleNuovaColazione;
import it.aps.cdr.interfaccia.text.Parser;

/**
 * Comando per inserire una nuova colazione ad un ordine. 
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoNuovaColazioneOrdinata implements Comando {

	public static final String codiceComando = "1";
	public static final String descrizioneComando = "AGGIUNGI UNA COLAZIONE ALL'ORDINE";
	
	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui() {
		System.out.println("   Inserisci il codice del tipo di colazione:");
		int codice = Integer.parseInt(Parser.getInstance().read());
		TipoColazioneID tcID = new TipoColazioneID(codice);
		ColazioneOrdinata co = CdR.getInstance().nuovaColazioneOrdinata(tcID);
		if (co != null) {
			ConsoleNuovaColazione ncc = new ConsoleNuovaColazione();
			ncc.start();
		} else {
			System.out.println("   ERRORE: TIPO COLAZIONE INESISTENTE");
		}
	}

}
