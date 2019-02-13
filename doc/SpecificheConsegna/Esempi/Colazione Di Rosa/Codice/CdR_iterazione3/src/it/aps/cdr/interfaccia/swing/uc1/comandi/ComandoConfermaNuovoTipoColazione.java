package it.aps.cdr.interfaccia.swing.uc1.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.Prezzo;
import it.aps.cdr.dominio.TipoColazione;
import it.aps.cdr.interfaccia.swing.Comando;
import it.aps.cdr.interfaccia.swing.uc1.NuovoTipoColazioneDialog;
import it.aps.cdr.logging.Logger;

public class ComandoConfermaNuovoTipoColazione implements Comando {

	public void esegui(Object o) {
		NuovoTipoColazioneDialog dialog = (NuovoTipoColazioneDialog)o;
		String prezzo = dialog.getPrezzoColazione();
		
		if (prezzo!=null && !prezzo.equals("")) {
			CdR.getInstance().definisciPrezzo(new Prezzo(Double.parseDouble(prezzo)));
			TipoColazione cl = CdR.getInstance().getTipoColazioneCorrente();
			if (cl.getComponenti().size() != 0) {
				CdR.getInstance().confermaTipoColazione();
				dialog.chiudiFinestra();
			} else {
				String messaggio = "Specificare almeno un componente per il nuovo tipo di colazione.";
				Logger.getInstance().errore("errore",messaggio);
			}
		} else {	
			String messaggio = "Specificare un prezzo per il nuovo tipo di colazione.";
			Logger.getInstance().errore("errore",messaggio);
		}
	}	

}
