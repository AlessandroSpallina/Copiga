package it.aps.cdr.interfaccia.text;

import it.aps.cdr.dominio.CdR;

/**
 * Comando per modificare la quantità di una componente di una colazione ordinata.
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.2
 * 
 */
public class ComandoModificaCompColazioneOrdinata implements Comando{

	public static final String codiceComando = "1";
	public static final String descrizioneComando = "modifica la quantità di una componente";

	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui(CdR colazione) {
		System.out.println("   Inserisci il codice della componente:");
		String codice_componente = Parser.getInstance().read();
		System.out.println("   Inserisci la nuova quantita:");
		int nuova_quantita = Integer.valueOf(Parser.getInstance().read());
		colazione.modificaCompColazioneOrdinata(codice_componente, nuova_quantita);
	}

}
