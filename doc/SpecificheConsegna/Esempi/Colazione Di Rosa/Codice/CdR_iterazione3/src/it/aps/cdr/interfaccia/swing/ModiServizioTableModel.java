package it.aps.cdr.interfaccia.swing;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.ModoServizio;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ModiServizioTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	private static final String OGGETTO_NULLO = "-";
	
	private List<ModoServizio> modiServizio;
	private List<String> campi;
	
	/** l'oggetto utilizzato per formattare i numeri decimali */
    private DecimalFormat df = new DecimalFormat();

	public ModiServizioTableModel() {
		this.df.setMaximumFractionDigits(2);
		this.modiServizio = 
			new LinkedList<ModoServizio>(CdR.getInstance().getModiServizio());
	    this.campi = new LinkedList<String>();
	    this.campi.add("codice");
		this.campi.add("nome modalita'");
		this.campi.add("descrizione");
		this.campi.add("fattore moltiplicativo");
	}
	
	public int getRowCount() {
		return this.modiServizio.size();
	}

	public int getColumnCount() {
		return this.campi.size();
	}

	public String getColumnName(int colonna) {
		return this.campi.get(colonna).toString();
	}
	
	public Object getValueAt(int riga, int colonna) {
		Object result = OGGETTO_NULLO;
		ModoServizio ms = (ModoServizio)this.modiServizio.get(riga);
        try {            
            if (colonna == 0)
                result = ms.getCodice().getCodiceModoServizio();
            else if (colonna == 1) 
				result = ms.getNome();
            else if (colonna == 2) 
                result = ms.getDescrizione();
            else if (colonna == 3)
				result = this.df.format(ms.getFattoreMoltiplicativo());
            else 
				result = OGGETTO_NULLO;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
	}
	
	public void aggiungiModoServizio(ModoServizio ms) {
		this.modiServizio.add(ms);
	}
	
	public void ricaricaModiServizio() {
		this.modiServizio = 
			new LinkedList<ModoServizio>(CdR.getInstance().getModiServizio());
	}

}
