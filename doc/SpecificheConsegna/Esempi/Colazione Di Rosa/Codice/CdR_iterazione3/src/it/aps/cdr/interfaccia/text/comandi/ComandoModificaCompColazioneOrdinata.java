package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.DescrizioneComponenteID;
import it.aps.cdr.interfaccia.text.Parser;

/**
 * Comando per modificare la quantita' di una componente di una colazione ordinata.
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoModificaCompColazioneOrdinata implements Comando{

	public static final String codiceComando = "1";
	public static final String descrizioneComando = "MODIFICA LA QUANTITA' DI UNA COMPONENTE";

	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui() {
		boolean modificata;
		System.out.println("   Inserisci il codice della componente:");
		int codice = Integer.parseInt(Parser.getInstance().read());
		System.out.println("   Inserisci la nuova quantita:");
		int quantita = Integer.parseInt(Parser.getInstance().read());
		modificata = CdR.getInstance().modificaCompColazioneOrdinata(new DescrizioneComponenteID(codice),quantita);
		if (!modificata)
			System.out.println("   Il codice specificato non esiste nella colazione");
	}

}
