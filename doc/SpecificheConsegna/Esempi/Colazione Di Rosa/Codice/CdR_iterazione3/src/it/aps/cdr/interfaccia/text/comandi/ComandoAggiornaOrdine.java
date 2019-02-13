package it.aps.cdr.interfaccia.text.comandi;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.Cliente;
import it.aps.cdr.dominio.ClienteID;
import it.aps.cdr.dominio.Ordine;
import it.aps.cdr.dominio.OrdineID;
import it.aps.cdr.dominio.Prezzo;
import it.aps.cdr.interfaccia.text.Parser;
import it.aps.cdr.persistenza.PersistenceFacade;
import it.aps.cdr.persistenza.dao.DAOException;

/**
 * Comando per aggiornare un ordine
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoAggiornaOrdine implements Comando {

	public static final String codiceComando = "4";
	public static final String descrizioneComando = "MODIFICA LE INFORMAZIONI DI UN ORDINE";
	
	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui() {
		System.out.println("   Inserisci il codice dell' ordine da aggiornare: ");
		int codice = Integer.parseInt(Parser.getInstance().read());
		System.out.println();
		Ordine o = CdR.getInstance().getOrdine(new OrdineID(codice));
		if (o != null) {
			System.out.println(o);
			System.out.println("   Inserisci il nuovo prezzo: ");
			double prezzo = Double.parseDouble(Parser.getInstance().read());
			System.out.println();		
			System.out.println("   Inserisci la nuova data della consegna (MM/GG/AAAA): ");
			String data = Parser.getInstance().read();
			System.out.println();
			System.out.println("   Inserisci la nuova ora della consegna (hh:mm):");
			String ora = Parser.getInstance().read();
		
			int mese = getCampo(data,1,"/");
			int giorno = getCampo(data,2,"/");
			int anno = getCampo(data,3,"/");
			int ore = getCampo(ora,1,":");
			int minuti = getCampo(ora,2,":"); 
		
			Calendar gc = new GregorianCalendar(anno,mese,giorno,ore,minuti); 
			Date data_consegna = gc.getTime();
		
			System.out.println();
			System.out.println("   Inserisci il codice del nuovo cliente:");
			int codiceCliente = Integer.parseInt(Parser.getInstance().read());
			System.out.println();
			Cliente cl = CdR.getInstance().trovaCliente(new ClienteID(codiceCliente));
		
			o.setCliente(cl);
			o.setData(data_consegna);
			o.setPrezzo(new Prezzo(prezzo));
			
			PersistenceFacade.getInstance().aggiornaOrdine(o); 
		} else
			System.out.println("   ERRORE: ORDINE INESISTENTE");
		
	}

	private int getCampo(String data, int campo, String separatore) {
		StringTokenizer st = new StringTokenizer(data,separatore);
		int valore = -1;
		int i = 1;
		while (st.hasMoreTokens()){
			String temp = st.nextToken();
			if (i == campo)
				valore = Integer.parseInt(temp);
			else 
				i++;
		}
		return valore;
	}
	
}
