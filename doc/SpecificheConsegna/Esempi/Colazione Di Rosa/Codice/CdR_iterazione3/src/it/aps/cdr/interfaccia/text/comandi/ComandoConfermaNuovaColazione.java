package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.dominio.CdR;

/**
 * Comando per registrare la colazione corrente dell'ordine
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoConfermaNuovaColazione implements Comando{

	public static final String codiceComando = "4";
	public static final String descrizioneComando = "CONFERMA";
	
	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui() {
		CdR.getInstance().confermaColazioneOrdinata();
	}

}
