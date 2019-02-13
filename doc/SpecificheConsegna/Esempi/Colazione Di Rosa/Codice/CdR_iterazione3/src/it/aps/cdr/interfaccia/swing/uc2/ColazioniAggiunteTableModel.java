package it.aps.cdr.interfaccia.swing.uc2;

import it.aps.cdr.dominio.ColazioneOrdinata;
import it.aps.cdr.dominio.Ordine;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ColazioniAggiunteTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	private static final String OGGETTO_NULLO = "-";
	
	private List<ColazioneOrdinata> colazioni;
	private List<String> campi;
	
	public ColazioniAggiunteTableModel(Ordine o) {
		this.colazioni = new LinkedList<ColazioneOrdinata>();
		if (o != null)
			this.colazioni = (LinkedList<ColazioneOrdinata>)o.getColazioniOrdinate();
		this.campi = new LinkedList<String>();
		this.campi.add("colazione");
		this.campi.add("modo servizio");
		this.campi.add("modificata");
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
	
	public Object getValueAt(int row, int column) {
		Object result = OGGETTO_NULLO;
		ColazioneOrdinata co = (ColazioneOrdinata)this.colazioni.get(row);
		
        try {
			if (column == 0)
				result = co.getTipoColazione().getNome();
            else if (column == 1)
				result = co.getModoServizio().getNome();
            else if (column == 2) 
				result = co.getVariazioni().isEmpty() ? "No" : "Si";
            else
                result = OGGETTO_NULLO;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
	}
	
	public void aggiungiColazioneOrdinata(ColazioneOrdinata co) {
		this.colazioni.add(co);
	}
	
}
