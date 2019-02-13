package it.aps.cdr.interfaccia.swing.uc2.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.TipoColazione;
import it.aps.cdr.interfaccia.swing.Comando;
import it.aps.cdr.interfaccia.swing.uc2.NuovoOrdineDialog;
import it.aps.cdr.interfaccia.swing.uc2.VariazioniOrdineDialog;

public class ComandoAggiungiColazione implements Comando {

	public void esegui(Object o) {
		NuovoOrdineDialog dialog = (NuovoOrdineDialog)o;
		TipoColazione tc = dialog.getTipoColazione();
		CdR.getInstance().nuovaColazioneOrdinata(tc.getCodice());
		new VariazioniOrdineDialog(dialog);
	}

}
