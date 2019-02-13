package it.aps.cdr.interfaccia.swing;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.TipoColazione;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TipiColazioneTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	private static final String OGGETTO_NULLO = "-";
	
	private List<TipoColazione> colazioni;
	private List<String> campi;
	
	/** l'oggetto utilizzato per formattare i numeri decimali */
    private DecimalFormat df = new DecimalFormat();

	public TipiColazioneTableModel() {
		this.df.setMaximumFractionDigits(2);
		this.colazioni = new LinkedList<TipoColazione>
			(CdR.getInstance().getMenu().getTipiColazione());
	    this.campi = new LinkedList<String>();
        this.campi.add("codice");
		this.campi.add("nome colazione");
		this.campi.add("prezzo (€)");
	}
	
	public int getRowCount() {
		return this.colazioni.size();
	}

	public int getColumnCount() {
		return this.campi.size();
	}

	public String getColumnName(int colonna) {
		return this.campi.get(colonna).toString();
	}
	
	public Object getValueAt(int riga, int colonna) {
		Object result = OGGETTO_NULLO;
		TipoColazione tc = (TipoColazione)this.colazioni.get(riga);
        try {            
            if (colonna == 0)
                result = tc.getCodice().getCodiceTipoColazione();
            else if (colonna == 1) 
				result = tc.getNome();
            else if (colonna == 2) 
				result = this.df.format(tc.getPrezzo().getImporto());
            else 
				result = OGGETTO_NULLO;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
	}
	
	public void aggiungiTipoColazione(TipoColazione tc) {
		this.colazioni.add(tc);
	}
	
	public void ricaricaTipiColazione() {
		this.colazioni = 
			new LinkedList<TipoColazione>(CdR.getInstance().getMenu().getTipiColazione());
	}

}
