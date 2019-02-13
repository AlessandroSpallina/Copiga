package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.ModoServizio;
import it.aps.cdr.dominio.ModoServizioID;
import it.aps.cdr.interfaccia.text.Parser;
import it.aps.cdr.persistenza.PersistenceFacade;
import it.aps.cdr.persistenza.dao.DAOException;

/**
 * Comando per aggiornare una modalita' di servizio  
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoAggiornaModoServizio implements Comando {

	public static final String codiceComando = "4";
	public static final String descrizioneComando = "MODIFICA UNA MODALITA' DI SERVIZIO";
	
	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui() {
		System.out.println("   Inserisci il codice della modalita' di servizio da aggiornare: ");
		int codice = Integer.parseInt(Parser.getInstance().read());
		System.out.println();
		ModoServizio ms = CdR.getInstance().getModoServizio(new ModoServizioID(codice));
		if (ms != null) {
			System.out.println(ms);
			System.out.println("   Inserisci il nuovo nome: ");
			String nome = Parser.getInstance().read();
			System.out.println();		
			System.out.println("   Inserisci la nuova descrizione: ");
			String descrizione = Parser.getInstance().read();
			System.out.println();	
			System.out.println("   Inserisci il nuovo fattore moltiplicativo: ");
			double fm = Double.parseDouble(Parser.getInstance().read());
			System.out.println();
		
			ms.setNome(nome);
			ms.setDescrizione(descrizione);
			ms.setFattoreMoltiplicativo(fm);
			
			PersistenceFacade.getInstance().aggiornaModoServizio(ms); 
		} else 
			System.out.println("   ERRORE: MODALITA' DI SERVIZIO INESISTENTE");
	}
	
}
