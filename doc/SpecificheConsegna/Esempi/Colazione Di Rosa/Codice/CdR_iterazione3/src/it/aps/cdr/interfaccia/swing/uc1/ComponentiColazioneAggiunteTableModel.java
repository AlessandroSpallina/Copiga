package it.aps.cdr.interfaccia.swing.uc1;

import it.aps.cdr.dominio.ComponenteColazione;
import it.aps.cdr.dominio.TipoColazione;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ComponentiColazioneAggiunteTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	private static final String OGGETTO_NULLO = "-";
	
	private List<ComponenteColazione> componentiColazione;
	private List<String> campi;
	
	public ComponentiColazioneAggiunteTableModel(TipoColazione tc) {
		this.componentiColazione = new ArrayList<ComponenteColazione>();
		if (tc != null)
			this.componentiColazione = (ArrayList<ComponenteColazione>)tc.getComponenti();
		this.campi = new ArrayList<String>();
		this.campi.add("codice");
		this.campi.add("componente");
		this.campi.add("quantita'");
	}

	public int getRowCount() {
		return this.componentiColazione.size();
	}

	public int getColumnCount() {
		return this.campi.size();
	}

    public String getColumnName(int colonna) {
        return this.campi.get(colonna).toString();
    }
	
	public Object getValueAt(int row, int column) {
		Object result = OGGETTO_NULLO;
		ComponenteColazione cc = (ComponenteColazione)this.componentiColazione.get(row);
		
        try {
			if (column == 0)
				result = cc.getDescrizioneComponente().getCodice().getCodiceDescrizioneComponente();
			else if (column == 1)
				result = cc.getDescrizioneComponente().getNome();
            else if (column == 2)
				result = cc.getQuantita();
            else
                result = OGGETTO_NULLO;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
	}
	
	public void aggiungiComponenteColazione(ComponenteColazione cc) {
		this.componentiColazione.add(cc);
	}
	
}
