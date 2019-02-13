package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.DescrizioneComponente;
import it.aps.cdr.dominio.DescrizioneComponenteID;
import it.aps.cdr.interfaccia.text.Parser;
import it.aps.cdr.persistenza.dao.DAODescrizioneComponente;
import it.aps.cdr.persistenza.dao.DAOException;

/**
 * Comando per cancellare una descrizione di una componente 
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoCancellaDescrizioneComponente implements Comando{

	public static final String codiceComando = "5";
	public static final String descrizioneComando = "CANCELLA DESCRIZIONE DI UNA COMPONENTE";
	
	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui() {
		System.out.println("   Inserisci il codice della descrizione della componente da cancellare: ");
		int codice = Integer.parseInt(Parser.getInstance().read());
		System.out.println();
		DescrizioneComponenteID dcID = new DescrizioneComponenteID(codice);
		DescrizioneComponente dc = CdR.getInstance().cancellaDescrizioneComponente(dcID);
		if (dc == null) {
			System.out.println("   ERRORE: DESCRIZIONE COMPONENTE INESISTENTE");
		}

	}
	
}
