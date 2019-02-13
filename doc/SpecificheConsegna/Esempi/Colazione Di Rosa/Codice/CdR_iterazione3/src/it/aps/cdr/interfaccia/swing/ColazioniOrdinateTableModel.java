package it.aps.cdr.interfaccia.swing;

import it.aps.cdr.dominio.CalcolatorePrezzo;
import it.aps.cdr.dominio.ColazioneOrdinata;
import it.aps.cdr.dominio.Ordine;
import it.aps.cdr.dominio.Prezzo;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ColazioniOrdinateTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	private static final String OGGETTO_NULLO = "-";
	
	private List<ColazioneOrdinata> colazioniOrdinate;
	private List<String> campi;
	
	/** l'oggetto utilizzato per formattare i numeri decimali */
    private DecimalFormat df = new DecimalFormat();
	
	public ColazioniOrdinateTableModel(Ordine o) {
		this.df.setMaximumFractionDigits(2);
		this.colazioniOrdinate = new LinkedList<ColazioneOrdinata>();
		if (o != null)
			this.colazioniOrdinate = (LinkedList<ColazioneOrdinata>)o.getColazioniOrdinate();
		this.campi = new LinkedList<String>();
		this.campi.add("codice");
		this.campi.add("colazione");
		this.campi.add("modalita' di servizio");
		this.campi.add("modificata");
		this.campi.add("prezzo (€)");
	}

	public int getRowCount() {
		return this.colazioniOrdinate.size();
	}

	public int getColumnCount() {
		return this.campi.size();
	}

    public String getColumnName(int colonna) {
        return this.campi.get(colonna).toString();
    }
	
	public Object getValueAt(int row, int column) {
		Object result = OGGETTO_NULLO;
		ColazioneOrdinata co = (ColazioneOrdinata)this.colazioniOrdinate.get(row);
		Prezzo p = CalcolatorePrezzo.getInstance().calcolaPrezzoColazione(co);
		
        try {            
            if (column == 0)
				result = co.getCodice().getCodiceColazioneOrdinata();
            else if (column == 1)
				result = co.getTipoColazione().getNome();
            else if (column == 2) 
				result = co.getModoServizio().getNome();
            else if (column == 3) 
				result = co.getVariazioni().isEmpty() ? "No" : "Si";
            else if (column == 4) 
				result = this.df.format(p.getImporto());
            else
                result = OGGETTO_NULLO;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
	}
	
	public void setOrdine(Ordine o) {
		this.colazioniOrdinate = (LinkedList<ColazioneOrdinata>)o.getColazioniOrdinate();
	}
	
}
