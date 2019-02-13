package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.ModoServizio;

import java.util.Collection;

/**
 * Comando per elencare le modalita' di servizio registrate
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoElencaModiServizio implements Comando{

	public static final String codiceComando = "3";
	public static final String descrizioneComando = "ELENCA LE MODALITA' DI SERVIZIO";
	
	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui() {
		Collection<ModoServizio> modiServizio = CdR.getInstance().getModiServizio();
		for (ModoServizio ms : modiServizio)
			System.out.println("   " + ms.stampa());
	}
	
}
