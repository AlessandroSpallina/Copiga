package it.aps.cdr.interfaccia.swing.uc2.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.interfaccia.swing.Comando;
import it.aps.cdr.interfaccia.swing.uc2.NuovoOrdineDialog;

public class ComandoConfermaNuovoOrdine implements Comando {

	public void esegui(Object o) {
		NuovoOrdineDialog dialog = (NuovoOrdineDialog)o;
		CdR.getInstance().confermaOrdine();
		dialog.chiudiFinestra();
	}	
	
}