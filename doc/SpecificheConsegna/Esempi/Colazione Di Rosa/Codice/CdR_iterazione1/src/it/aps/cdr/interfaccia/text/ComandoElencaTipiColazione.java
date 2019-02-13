package it.aps.cdr.interfaccia.text;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.TipoColazione;

import java.util.Collection;

/**
 * Comando per elencare i tipi di colazione registrati dal sistema
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri
 * @author Gabriele Rendina
 * 
 * @version 0.1
 *
 */
public class ComandoElencaTipiColazione implements Comando {
	
	public static final String codiceComando="1";
	public static final String descrizioneComando="elenca tipi di colazione";
	
   	public String getCodiceComando() {
		return codiceComando;
	}
	
   	public String getDescrizioneComando() {
		return descrizioneComando;
	}

    public void esegui(CdR colazione) {
		Collection<TipoColazione> tipi = 
			(Collection<TipoColazione>) colazione.getTipiColazione().values();
		for (TipoColazione tipo : tipi)
			System.out.println("   " + tipo.toString());	
	}
}