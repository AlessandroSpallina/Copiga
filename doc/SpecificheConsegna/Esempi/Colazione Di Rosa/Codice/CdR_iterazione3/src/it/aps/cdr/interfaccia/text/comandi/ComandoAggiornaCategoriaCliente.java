package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.dominio.CategoriaCliente;
import it.aps.cdr.dominio.CategoriaClienteID;
import it.aps.cdr.dominio.CdR;
import it.aps.cdr.interfaccia.text.Parser;
import it.aps.cdr.persistenza.PersistenceFacade;
import it.aps.cdr.persistenza.dao.DAOException;

/**
 * Aggiorna una categoria di cliente  
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoAggiornaCategoriaCliente implements Comando {

	public static final String codiceComando = "4";
	public static final String descrizioneComando = "MODIFICA LA CATEGORIA DI UN CLIENTE";
	
	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui() {
		System.out.println("   Inserisci il codice della categoria di cliente da aggiornare: ");
		int codice = Integer.parseInt(Parser.getInstance().read());
		System.out.println();
		CategoriaCliente cc = CdR.getInstance().trovaCategoriaCliente(new CategoriaClienteID(codice));
		if (cc != null) {
			System.out.println(cc);
			System.out.println("   Inserisci il nuovo nome: ");
			String nome = Parser.getInstance().read();
			System.out.println();
			System.out.println("   Inserisci la nuova descrizione: ");
			String descrizione = Parser.getInstance().read();
			System.out.println();
			System.out.println("   Inserisci il nuovo numero di ordini massimo: ");
			int ordini = Integer.parseInt(Parser.getInstance().read());
			System.out.println();
			System.out.println("   Inserisci il nuovo fattore moltiplicativo: ");
			double fm = Double.parseDouble(Parser.getInstance().read());
			System.out.println();

			cc.setNome(nome);
			cc.setDescrizione(descrizione);
			cc.setOrdini(ordini);
			cc.setFattoreCliente(fm);
			
			PersistenceFacade.getInstance().aggiornaCategoriaCliente(cc); 
		} else
			System.out.println("   ERRORE: CATEGORIA CLIENTE INESISTENTE");
	}
	
}
