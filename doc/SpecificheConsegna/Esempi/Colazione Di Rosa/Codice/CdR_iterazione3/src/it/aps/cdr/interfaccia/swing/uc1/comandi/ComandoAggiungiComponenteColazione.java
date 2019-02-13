package it.aps.cdr.interfaccia.swing.uc1.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.DescrizioneComponente;
import it.aps.cdr.dominio.DescrizioneComponenteID;
import it.aps.cdr.interfaccia.swing.Comando;
import it.aps.cdr.interfaccia.swing.uc1.NuovoTipoColazioneDialog;
import it.aps.cdr.logging.Logger;


public class ComandoAggiungiComponenteColazione implements Comando {

	public void esegui(Object o) {
		NuovoTipoColazioneDialog dialog = (NuovoTipoColazioneDialog)o;
		DescrizioneComponente dc = dialog.getDescrizioneComponente();
		String qta = dialog.getQuantitaComponente();
				
		if (qta!=null && !qta.equals("")) {
			int quantita = Integer.parseInt(qta);
			DescrizioneComponenteID codice = dc.getCodice();
			CdR.getInstance().aggiungiComponenteColazione(codice,quantita);
		} else 
			Logger.getInstance().errore("errore","Specificare una quantita' per la componente.");
	}
	
}
