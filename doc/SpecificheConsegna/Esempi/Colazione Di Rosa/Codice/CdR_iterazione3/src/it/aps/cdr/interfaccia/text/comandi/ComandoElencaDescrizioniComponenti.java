package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.DescrizioneComponente;

import java.util.Collection;

/**
 * Comando per elencare le descrizioni delle componenti registrate
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoElencaDescrizioniComponenti implements Comando {

	public static final String codiceComando="3";
	public static final String descrizioneComando="ELENCA LE DESCRIZIONI DELLE COMPONENTI";
	
	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui() {
		Collection<DescrizioneComponente> descrizioniComponenti 
			= CdR.getInstance().getMenu().getDescrizioniComponenti();
		for (DescrizioneComponente dc : descrizioniComponenti)
			System.out.println("   " + dc.stampa());
	}

}
