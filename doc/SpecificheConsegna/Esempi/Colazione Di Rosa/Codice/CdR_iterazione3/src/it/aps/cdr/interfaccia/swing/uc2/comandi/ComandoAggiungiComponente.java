package it.aps.cdr.interfaccia.swing.uc2.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.DescrizioneComponente;
import it.aps.cdr.interfaccia.swing.Comando;
import it.aps.cdr.interfaccia.swing.uc2.VariazioniOrdineDialog;
import it.aps.cdr.logging.Logger;


public class ComandoAggiungiComponente implements Comando {

	public void esegui(Object o) {
		VariazioniOrdineDialog dialog = (VariazioniOrdineDialog)o;
		DescrizioneComponente dc = dialog.getComponenteAggiunta();
		String quantita = dialog.getQuantitaAggiunta();
		
		if (quantita!=null && !quantita.equals("")) {
			int qta = Integer.parseInt(quantita);
			CdR.getInstance().aggiungiCompColazioneOrdinata(dc.getCodice(),qta);
		} else {
			String messaggio = "Specificare una quantita' per la componente aggiunta.";
			Logger.getInstance().errore("errore",messaggio);
		}
	}
	
}
