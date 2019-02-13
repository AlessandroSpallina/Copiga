package it.aps.cdr.interfaccia.text;

import it.aps.cdr.dominio.CdR;

/**
 * Comando non valido.
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.1
 * 
 */
public class ComandoNonValido implements Comando {
	
	public static final String codiceComando="-1";
	public static final String descrizioneComando="comando non valido";

   	public String getCodiceComando() {
		return codiceComando;
	}
	
   	public String getDescrizioneComando() {
		return descrizioneComando;
	}

    public void esegui(CdR colazione) {
		System.out.println("   COMANDO INESISTENTE");
	}
}
