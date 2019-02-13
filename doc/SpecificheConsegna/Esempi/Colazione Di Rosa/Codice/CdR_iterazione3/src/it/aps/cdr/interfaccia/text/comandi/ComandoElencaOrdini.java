package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.Ordine;

import java.util.Collection;

/**
 * Comando per elencare gli ordini registrati
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoElencaOrdini implements Comando{

	public static final String codiceComando = "3";
	public static final String descrizioneComando = "ELENCA GLI ORDINI";
	
	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui() {
		Collection<Ordine> ordini = CdR.getInstance().getOrdini();
		for (Ordine o : ordini)
			System.out.println(o + "\n");
	}

}
