package it.aps.cdr.interfaccia.swing.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.interfaccia.swing.Comando;
import it.aps.cdr.interfaccia.swing.RicaricaDatiDialog;

public class ComandoRicaricaDati implements Comando {

	public void esegui(Object o) {
		RicaricaDatiDialog dialog = (RicaricaDatiDialog)o;
		CdR.getInstance().ricaricaDati(); 
		dialog.chiudiFinestra();
	}
}
