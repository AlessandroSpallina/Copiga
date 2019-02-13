package it.aps.cdr.interfaccia.swing.uc2.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.interfaccia.swing.Comando;
import it.aps.cdr.interfaccia.swing.uc2.VariazioniOrdineDialog;

public class ComandoConfermaNuovaColazioneOrdinata implements Comando {

	public void esegui(Object o) {
		VariazioniOrdineDialog dialog = (VariazioniOrdineDialog)o;
		CdR.getInstance().confermaColazioneOrdinata();	
		dialog.chiudiFinestra();
	}	
	
}