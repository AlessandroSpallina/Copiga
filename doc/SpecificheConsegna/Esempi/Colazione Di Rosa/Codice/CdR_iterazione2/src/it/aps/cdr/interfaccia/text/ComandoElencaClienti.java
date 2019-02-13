package it.aps.cdr.interfaccia.text;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.Cliente;

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
public class ComandoElencaClienti implements Comando {
	
	public static final String codiceComando="3";
	public static final String descrizioneComando="elenca clienti di Rosa";
	
   	public String getCodiceComando() {
		return codiceComando;
	}
	
   	public String getDescrizioneComando() {
		return descrizioneComando;
	}

    public void esegui(CdR colazione) {
		Iterator<Cliente> clienti = 
			(Iterator<Cliente>) colazione.getClienti().iterator();
		while (clienti.hasNext()){
			Cliente cliente=(Cliente)clienti.next();
			System.out.println("   " + cliente.toString());	
		}
	}		
}