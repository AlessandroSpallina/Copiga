package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.TipoColazione;

import java.util.Collection;


/**
 * Comando per elencare i tipi di colazione registrati
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri
 * @author Gabriele Rendina
 * 
 * @version 0.3
 *
 */
public class ComandoElencaTipiColazione implements Comando {
	
	public static final String codiceComando="3";
	public static final String descrizioneComando="ELENCA I TIPI DI COLAZIONE";
	
   	public String getCodiceComando() {
		return codiceComando;
	}
	
   	public String getDescrizioneComando() {
		return descrizioneComando;
	}

    public void esegui() {
		Collection<TipoColazione> tipi = CdR.getInstance().getMenu().getTipiColazione();
		for (TipoColazione tipo : tipi)
			System.out.println(tipo.stampa() + "\n");
	}
	
}