package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.Cliente;

import java.util.Collection;

/**
 * Comando per elencare i clienti registrati
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoElencaClienti implements Comando {
	
	public static final String codiceComando="3";
	public static final String descrizioneComando="ELENCA I CLIENTI REGISTRATI";
	
	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui() {
		Collection<Cliente> clienti = CdR.getInstance().getClienti();
		for (Cliente cl : clienti)
			System.out.println("   " + cl.stampa());
	}

}
