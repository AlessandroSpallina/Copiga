package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.DescrizioneComponente;
import it.aps.cdr.dominio.DescrizioneComponenteID;
import it.aps.cdr.dominio.Prezzo;
import it.aps.cdr.interfaccia.text.Parser;
import it.aps.cdr.persistenza.PersistenceFacade;
import it.aps.cdr.persistenza.dao.DAOException;

/**
 * Comando per aggiornare una descrizione di una componente  
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoAggiornaDescrizioneComponente implements Comando {

	public static final String codiceComando = "4";
	public static final String descrizioneComando = "MODIFICA LA DESCRIZIONE DI UNA COMPONENTE";
	
	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui() {
		System.out.println("   Inserisci il codice della descrizione della componente da aggiornare: ");
		int codice = Integer.parseInt(Parser.getInstance().read());
		System.out.println();
		DescrizioneComponente dc = CdR.getInstance().trovaDescrizioneComponente(new DescrizioneComponenteID(codice));
		if (dc != null) {
			System.out.println(dc);
			System.out.println("   Inserisci il nuovo nome: ");
			String nome = Parser.getInstance().read();
			System.out.println();		
			System.out.println("   Inserisci il nuovo prezzo aggiuntivo: ");
			double pa = Double.parseDouble(Parser.getInstance().read());
			System.out.println();
			System.out.println("   Inserisci il nuovo prezzo riduttivo: ");
			double pr = Double.parseDouble(Parser.getInstance().read());
			System.out.println();
			dc.setNome(nome);
			dc.setPrezzoAggiuntivo(new Prezzo(pa));
			dc.setPrezzoRiduttivo(new Prezzo(pr));
			
			PersistenceFacade.getInstance().aggiornaDescrizioneComponente(dc); 
		} else 
			System.out.println("   ERRORE: DESCRIZIONE COMPONENTE INESISTENTE");
	}
	
}
