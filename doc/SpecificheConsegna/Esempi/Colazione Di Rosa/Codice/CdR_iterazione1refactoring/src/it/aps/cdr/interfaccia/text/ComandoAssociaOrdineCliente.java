package it.aps.cdr.interfaccia.text;

import it.aps.cdr.dominio.CdR;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

/**
 * Comando per associare un cliente registrato o non registrato 
 * all'ordine corrente
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.1
 * 
 */
public class ComandoAssociaOrdineCliente implements Comando{
	
	public static final String codiceComando = "3";
	public static final String descrizioneComando = "definisci il cliente";
	
	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui(CdR colazione) {
		System.out.println("   Nome:");
		String nome_cliente = Parser.getInstance().read();
		System.out.println("   Cognome:");
		String cognome_cliente = Parser.getInstance().read();
		System.out.println("   Indirizzo:");
		String indirizzo_cliente = Parser.getInstance().read();
		System.out.println("   Data della consegna (MM/GG/AAAA):");
		String data = Parser.getInstance().read();
		System.out.println("   Ora della consegna (hh:mm):");
		String ora = Parser.getInstance().read();
		int mese = getCampo(data,1,"/");
		int giorno = getCampo(data,2,"/");
		int anno = getCampo(data,3,"/");
		int ore = getCampo(ora,1,":");
		int minuti = getCampo(ora,2,":"); 
		Calendar gc = new GregorianCalendar(anno,mese,giorno,ore,minuti); 
		Date data_consegna = gc.getTime();
		colazione.associaOrdineCliente(nome_cliente,cognome_cliente,indirizzo_cliente,data_consegna);
	}
	
	private int getCampo(String data, int campo, String separatore){
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
