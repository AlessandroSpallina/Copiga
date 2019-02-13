package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.Cliente;
import it.aps.cdr.dominio.ClienteID;
import it.aps.cdr.interfaccia.text.Parser;
import it.aps.cdr.persistenza.dao.DAOCliente;
import it.aps.cdr.persistenza.dao.DAOException;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

/**
 * Comando per definire le informazioni di consegna 
 * di un ordine.
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoCompletaOrdine implements Comando {

	public static final String codiceComando = "2";
	public static final String descrizioneComando = "COMPLETA ORDINE";
	
	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui() {
		System.out.println("   Inserisci la data della consegna (MM/GG/AAAA): ");
		String data = Parser.getInstance().read();
		System.out.println();
		System.out.println("   Inserisci l' ora della consegna (hh:mm):");
		String ora = Parser.getInstance().read();
	
		int mese = getCampo(data,1,"/");
		int giorno = getCampo(data,2,"/");
		int anno = getCampo(data,3,"/");
		int ore = getCampo(ora,1,":");
		int minuti = getCampo(ora,2,":"); 
	
		Calendar gc = new GregorianCalendar(anno,mese,giorno,ore,minuti); 
		Date data_consegna = gc.getTime();
	
		System.out.println();
		System.out.println("   Inserisci il nome del cliente: ");
		String nome = Parser.getInstance().read();
		System.out.println();
		System.out.println("   Inserisci il cognome del cliente: ");
		String cognome = Parser.getInstance().read();
		System.out.println();

		Collection<Cliente> clienti = CdR.getInstance().trovaCliente(nome,cognome);
		if (clienti.isEmpty()) { // non esiste alcun cliente cercato
			System.out.println();
			System.out.println("   Inserisci l'indirizzo del nuovo cliente: ");
			String indirizzo = Parser.getInstance().read();
			Cliente nuovoCliente = CdR.getInstance().nuovoCliente(nome,cognome,indirizzo);
			CdR.getInstance().confermaCliente();
			CdR.getInstance().completaOrdine(nuovoCliente.getCodice(),data_consegna);
		} else { // il cliente esiste tra quelli trovati oppure non esiste tra quelli trovati 
			for (Cliente cl : clienti) {
				System.out.println(cl.toString());
				System.out.println();
			}
			System.out.println();
			System.out.println("   Il cliente e' tra quelli visualizzati? (s/n) ");
			String risposta = Parser.getInstance().read();
			if (risposta.equalsIgnoreCase("s")) {
				System.out.println();
				System.out.println("   Inserisci il codice del cliente: ");
				int codice = Integer.parseInt(Parser.getInstance().read());
				CdR.getInstance().completaOrdine(new ClienteID(codice),data_consegna);
			} else {	// "n"
				System.out.println();
				System.out.println("   Inserisci l'indirizzo del nuovo cliente: ");
				String i = Parser.getInstance().read();
				Cliente nuovoCliente = CdR.getInstance().nuovoCliente(nome,cognome,i);
				CdR.getInstance().confermaCliente();
				CdR.getInstance().completaOrdine(nuovoCliente.getCodice(),data_consegna);
			}
		}
	}
	
	/* METODO DI SUPPORTO PER LA LETTURA DELLA DATA DI CONSEGNA DELL'ORDINE */
	private int getCampo(String data, int campo, String separatore) {
		StringTokenizer st = new StringTokenizer(data,separatore);
		int valore = -1;
		int i = 1;
		while (st.hasMoreTokens() && valore!=-1){
			String temp = st.nextToken();
			if (i == campo)
				valore = Integer.parseInt(temp);
			else 
				i++;
		}
		return valore;
	}

}
