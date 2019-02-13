package it.aps.cdr.interfaccia.swing.uc2;

import it.aps.cdr.dominio.ColazioneOrdinata;
import it.aps.cdr.dominio.Variazione;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class VariazioniColazioneTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	private static final String OGGETTO_NULLO = "-";
	
	private List<Variazione> variazioni;
	private List<String> campi;
	
	public VariazioniColazioneTableModel(ColazioneOrdinata co) {
		this.variazioni = new LinkedList<Variazione>();
		if (co != null)
			this.variazioni = (LinkedList<Variazione>)co.getVariazioni();
		this.campi = new LinkedList<String>();
		this.campi.add("codice");
		this.campi.add("nome componente");
		this.campi.add("quantita'");
	}

	public int getRowCount() {
		return this.variazioni.size();
	}

	public int getColumnCount() {
		return this.campi.size();
	}

    public String getColumnName(int colonna) {
        return this.campi.get(colonna).toString();
    }
	
	public Object getValueAt(int row, int column) {
		Object result = OGGETTO_NULLO;
		Variazione v = (Variazione)this.variazioni.get(row);
		
        try {
			if (column == 0)
				result = v.getDescrizioneComponente().getCodice();
			else if (column == 1)
				result = v.getDescrizioneComponente().getNome();
            else if (column == 2) 
				result = v.getQuantita();
            else
                result = OGGETTO_NULLO;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
	}
	
	public void aggiungiVariazione(Variazione v) {
		this.variazioni.add(v);
	}

}
