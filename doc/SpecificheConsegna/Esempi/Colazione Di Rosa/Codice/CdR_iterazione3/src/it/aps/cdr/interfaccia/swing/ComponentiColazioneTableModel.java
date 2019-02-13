/**
 * 
 */
package it.aps.cdr.interfaccia.swing;

import it.aps.cdr.dominio.ComponenteColazione;
import it.aps.cdr.dominio.TipoColazione;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ComponentiColazioneTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	private static final String OGGETTO_NULLO = "-";
	
	private List<ComponenteColazione> componenti;
	private List<String> campi;
		
	public ComponentiColazioneTableModel(TipoColazione tc) {
		this.componenti = new LinkedList<ComponenteColazione>();
		if (tc != null)
			this.componenti = (LinkedList<ComponenteColazione>)tc.getComponenti();
		this.campi = new LinkedList<String>();
		this.campi.add("codice");
		this.campi.add("nome componente");
		this.campi.add("quantita'");
	}

	public int getRowCount() {
		return this.componenti.size();
	}

	public int getColumnCount() {
		return this.campi.size();
	}

    public String getColumnName(int colonna) {
        return this.campi.get(colonna).toString();
    }
	
	public Object getValueAt(int row, int column) {
		Object result = OGGETTO_NULLO;
		ComponenteColazione cc = (ComponenteColazione)this.componenti.get(row);
		
        try {
			if (column == 0)
				result = cc.getDescrizioneComponente().getCodice();
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
	
	public void setTipoColazione(TipoColazione tc) {
		this.componenti = (LinkedList<ComponenteColazione>)tc.getComponenti();
	}

}
