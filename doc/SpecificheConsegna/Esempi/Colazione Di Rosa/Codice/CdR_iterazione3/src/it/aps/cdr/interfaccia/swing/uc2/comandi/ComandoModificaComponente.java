package it.aps.cdr.interfaccia.swing.uc2.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.DescrizioneComponente;
import it.aps.cdr.interfaccia.swing.Comando;
import it.aps.cdr.interfaccia.swing.uc2.VariazioniOrdineDialog;
import it.aps.cdr.logging.Logger;


public class ComandoModificaComponente implements Comando {

	public void esegui(Object o) {
		VariazioniOrdineDialog dialog = (VariazioniOrdineDialog)o;
		DescrizioneComponente dc = dialog.getComponenteModifica();
		String quantita = dialog.getQuantitaModifica();
		
		if (quantita!=null && !quantita.equals("")) {
			int qta = Integer.parseInt(quantita);
			CdR.getInstance().modificaCompColazioneOrdinata(dc.getCodice(),qta);
		} else {
			String messaggio = "Specificare una quantita' per la componente modificata.";
			Logger.getInstance().errore("errore",messaggio);
		}
	}
		
}
