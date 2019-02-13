package it.aps.cdr.interfaccia.text;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.Ordine;

import java.util.Iterator;

/**
 * Comando per elencare i tipi di colazione registrati dal sistema
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri
 * @author Gabriele Rendina
 * 
 * @version 0.2
 *
 */
public class ComandoElencaOrdini implements Comando {
	
	public static final String codiceComando="2";
	public static final String descrizioneComando="elenca ordini effettuati";
	
   	public String getCodiceComando() {
		return codiceComando;
	}
	
   	public String getDescrizioneComando() {
		return descrizioneComando;
	}

    public void esegui(CdR colazione) {
		Iterator<Ordine> colazioniIt = 
			(Iterator<Ordine>) colazione.getOrdini().iterator();
		while (colazioniIt.hasNext()){
			Ordine ordine=(Ordine)colazioniIt.next();
			System.out.println("   " + ordine.toString());	
		}
	}		
}