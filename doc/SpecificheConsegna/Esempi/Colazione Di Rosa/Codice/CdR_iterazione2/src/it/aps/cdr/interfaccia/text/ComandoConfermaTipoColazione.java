package it.aps.cdr.interfaccia.text;

import it.aps.cdr.dominio.CdR;

/**
 * Comando per confermare un tipo di colazione
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri
 * @author Gabriele Rendina
 * 
 * @version 0.2
 *
 */
public class ComandoConfermaTipoColazione implements Comando{
	
	public final static String codiceComando = "3";
	public final static String descrizioneComando = "conferma";
	
	public String getCodiceComando() {
		return codiceComando;
	}
	public String getDescrizioneComando() {
		return descrizioneComando;
	}
	
	public void esegui(CdR colazione) {
		colazione.confermaTipoColazione();
	}

}
