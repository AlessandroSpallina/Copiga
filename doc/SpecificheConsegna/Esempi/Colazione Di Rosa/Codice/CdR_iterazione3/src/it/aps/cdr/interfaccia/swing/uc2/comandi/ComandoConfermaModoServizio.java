package it.aps.cdr.interfaccia.swing.uc2.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.ModoServizio;
import it.aps.cdr.interfaccia.swing.Comando;
import it.aps.cdr.interfaccia.swing.uc2.VariazioniOrdineDialog;

public class ComandoConfermaModoServizio implements Comando {

	public void esegui(Object o) {
		VariazioniOrdineDialog dialog = (VariazioniOrdineDialog)o;
		ModoServizio ms = dialog.getModoServizio();
		CdR.getInstance().definisciModoServizio(ms.getCodice());	
	}
	
}
