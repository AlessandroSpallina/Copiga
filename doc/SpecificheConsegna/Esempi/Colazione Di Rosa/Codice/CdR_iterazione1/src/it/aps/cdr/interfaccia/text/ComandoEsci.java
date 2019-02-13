package it.aps.cdr.interfaccia.text;

import it.aps.cdr.dominio.CdR;

/**
 * Comando per uscire dalla console corrente e tornare alla console 
 * precedente. Esce dal programma se la console corrente è la console 
 * principale.
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.1
 * 
 */
public class ComandoEsci implements Comando {
	
	public static final String codiceComando="0";
	public static final String descrizioneComando="esci";

   	public String getCodiceComando() {
		return codiceComando;
	}
	
   	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui(CdR colazione) {
		//torna al menu precedente oppure esce se non ci sono menu precedenti
	}
	
}
