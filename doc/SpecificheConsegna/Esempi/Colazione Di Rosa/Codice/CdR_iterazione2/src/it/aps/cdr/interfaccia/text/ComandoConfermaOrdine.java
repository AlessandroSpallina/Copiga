package it.aps.cdr.interfaccia.text;

import it.aps.cdr.dominio.CdR;

/**
 * Comando per confermare e registrare l'ordine corrente 
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.2
 * 
 */
public class ComandoConfermaOrdine implements Comando{
	
	public static final String codiceComando = "3";
	public static final String descrizioneComando = "conferma ordine";
	
	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui(CdR colazione) {
		colazione.confermaOrdine();
	}

}
