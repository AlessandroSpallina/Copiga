package it.aps.cdr.interfaccia.swing.uc1.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.TipoColazione;
import it.aps.cdr.interfaccia.swing.Comando;
import it.aps.cdr.interfaccia.swing.uc1.ComponentiColazioneAggiunteTable;
import it.aps.cdr.interfaccia.swing.uc1.NuovoTipoColazioneDialog;
import it.aps.cdr.logging.Logger;

public class ComandoNuovoTipoColazione implements Comando {
	
	public void esegui(Object o) {
		NuovoTipoColazioneDialog dialog = (NuovoTipoColazioneDialog)o;
		String nome = dialog.getNomeColazione();
		
		if (nome!=null && !nome.equals("")) {
			TipoColazione tc = CdR.getInstance().nuovoTipoColazione(nome);
			/* creato il nuovo tipo di colazione la tabella delle componenti
			 * della finestra di dialogo viene aggiunta agli observer del
			 * tipo di colazione */
			ComponentiColazioneAggiunteTable table = 
				(ComponentiColazioneAggiunteTable)dialog.getComponentiColazione();
			tc.addPropertyListener(table);
		} else {
			String messaggio = "Specificare un nome per il nuovo tipo di colazione.";
			Logger.getInstance().errore("errore",messaggio);
		}
	}
	
}
