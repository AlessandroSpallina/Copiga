package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.dominio.CategoriaCliente;
import it.aps.cdr.dominio.CdR;

import java.util.Collection;

/**
 * Comando per elencare le categorie di clienti registrate 
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoElencaCategorieClienti implements Comando {
	
	public static final String codiceComando="3";
	public static final String descrizioneComando="ELENCA LE CATEGORIE DI CLIENTI";
	
	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui() {
		Collection<CategoriaCliente> categorieClienti = CdR.getInstance().getCategorieClienti();
		for (CategoriaCliente cc : categorieClienti)
			System.out.println("   " + "(cod:" + cc.getCodice() + ") " + cc.toString());
	}
	
}
