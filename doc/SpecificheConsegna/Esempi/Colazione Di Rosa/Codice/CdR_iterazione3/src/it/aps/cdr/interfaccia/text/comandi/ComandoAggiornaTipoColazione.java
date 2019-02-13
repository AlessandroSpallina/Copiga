package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.Prezzo;
import it.aps.cdr.dominio.TipoColazione;
import it.aps.cdr.dominio.TipoColazioneID;
import it.aps.cdr.interfaccia.text.Parser;
import it.aps.cdr.persistenza.PersistenceFacade;
import it.aps.cdr.persistenza.dao.DAOException;

/**
 * Comando per aggiornare un tipo di colazione 
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoAggiornaTipoColazione implements Comando {
	
	public static final String codiceComando = "4";
	public static final String descrizioneComando = "MODIFICA NOME E PREZZO DI UN TIPO DI COLAZIONE";
	
	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui() {
		System.out.println("   Inserisci il codice del tipo di colazione da aggiornare: ");
		int codice = Integer.parseInt(Parser.getInstance().read());
		System.out.println();
		TipoColazione tc = CdR.getInstance().trovaTipoColazione(new TipoColazioneID(codice));
		if (tc != null) {
			System.out.println(tc);
			System.out.println("   Inserisci il nuovo nome: ");
			String nome = Parser.getInstance().read();
			System.out.println();		
			System.out.println("   Inserisci il nuovo prezzo: ");
			double prezzo = Double.parseDouble(Parser.getInstance().read());
			System.out.println();
			tc.setNome(nome);
			tc.setPrezzo(new Prezzo(prezzo));
			
			PersistenceFacade.getInstance().aggiornaTipoColazione(tc); 
		} else {
			System.out.println("   ERRORE: TIPO COLAZIONE INESISTENTE");
		}
	}
}
