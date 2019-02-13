package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.ModoServizioID;
import it.aps.cdr.interfaccia.text.Parser;

/**
 * Comando per definire la modalita' di consegna delle colazioni di un ordine. 
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoDefinisciModoServizio implements Comando{
	
	public static final String codiceComando = "3";
	public static final String descrizioneComando = "DEFINISCI LA MODALITA' DI SERVIZIO";
	
	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui() {
		boolean risultato = false;
		System.out.println("   Inserisci il tipo di servizio:");
		int codice = Integer.parseInt(Parser.getInstance().read());
		ModoServizioID msID = new ModoServizioID(codice);
		risultato = CdR.getInstance().definisciModoServizio(msID);
		if (!risultato) {
			System.out.println("   ERRORE: MODO SERVIZIO INESISTENTE");
		}
	}
}
