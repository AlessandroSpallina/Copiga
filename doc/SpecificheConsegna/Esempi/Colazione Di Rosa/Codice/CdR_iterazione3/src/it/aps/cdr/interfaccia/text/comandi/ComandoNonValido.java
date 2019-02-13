package it.aps.cdr.interfaccia.text.comandi;


/**
 * Comando non valido.
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
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

    public void esegui() {
		System.out.println("   COMANDO INESISTENTE");
	}
}
