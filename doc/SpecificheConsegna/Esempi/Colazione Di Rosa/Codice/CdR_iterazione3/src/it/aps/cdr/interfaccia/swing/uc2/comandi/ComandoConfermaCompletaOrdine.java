package it.aps.cdr.interfaccia.swing.uc2.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.Cliente;
import it.aps.cdr.dominio.ClienteID;
import it.aps.cdr.interfaccia.swing.Comando;
import it.aps.cdr.interfaccia.swing.uc2.CompletaOrdineDialog;
import it.aps.cdr.logging.Logger;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class ComandoConfermaCompletaOrdine implements Comando {

	public void esegui(Object o) {
		CompletaOrdineDialog dialog = (CompletaOrdineDialog)o;
		String giorno = dialog.getGiorno();
		String mese = dialog.getMese();
		String anno = dialog.getAnno();
		String ora = dialog.getOra();
		String minuto = dialog.getMinuto();
		String clienteId = dialog.getClienteID();
		
		if (isValid(giorno) && isValid(mese) && isValid(anno) && 
			isValid(ora) && isValid(minuto) && isValid(clienteId)) {
			ClienteID id = new ClienteID(Integer.parseInt(clienteId));
			Cliente c = CdR.getInstance().trovaCliente(id);
			if (c!=null) {
				int gg = Integer.parseInt(giorno);
				int mm = Integer.parseInt(mese)-1;
				int aaaa = Integer.parseInt(anno);
				int oo = Integer.parseInt(ora);
				int min = Integer.parseInt(minuto);
				Calendar gc = new GregorianCalendar(aaaa,mm,gg,oo,min);
				Date data_consegna = gc.getTime();
				CdR.getInstance().completaOrdine(id,data_consegna);	
				dialog.chiudiFinestra();
			} else {
				String messaggio = "L'ID specificato non corrisponde ad alcun cliente.";
				Logger.getInstance().errore("errore",messaggio);
			}
		} else
			Logger.getInstance().errore("errore","Parametri mancanti.");
	}
	
	private boolean isValid(String s) {
		return s!=null && !s.equals("");
	}
	
}
