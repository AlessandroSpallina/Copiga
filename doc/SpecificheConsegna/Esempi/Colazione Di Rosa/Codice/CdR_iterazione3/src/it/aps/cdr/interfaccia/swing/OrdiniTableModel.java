package it.aps.cdr.interfaccia.swing;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.Ordine;

import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class OrdiniTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	private static final String OGGETTO_NULLO = "-";
	
	private List<Ordine> ordini;
	private List<String> campi;
	
	/** l'oggetto utilizzato per formattare i numeri decimali */
    private DecimalFormat df = new DecimalFormat();

	public OrdiniTableModel() {
		this.df.setMaximumFractionDigits(2);
		this.ordini = 
			new LinkedList<Ordine>(CdR.getInstance().getOrdini());
	    this.campi = new LinkedList<String>();
	    this.campi.add("codice");
		this.campi.add("data di consegna");
		this.campi.add("cliente");
		this.campi.add("prezzo");
		this.campi.add("consegnato");
	}
	public int getRowCount() {
		return this.ordini.size();
	}

	public int getColumnCount() {
		return this.campi.size();
	}

	public String getColumnName(int colonna) {
		return this.campi.get(colonna);
	}
	
	public Object getValueAt(int riga, int colonna) {
		Object result = OGGETTO_NULLO;
		Ordine o = (Ordine) this.ordini.get(riga);
        try {            
            if (colonna == 0)
                result = o.getCodice().getCodiceOrdine();
            else if (colonna == 1) 
				result = this.formattaData(o.getData());
            else if (colonna == 2) 
                result = o.getCliente().getNome() + " " + o.getCliente().getCognome();
            else if (colonna == 3)
				result = this.df.format(o.getPrezzo().getImporto());
            else if (colonna == 4)
				result = o.isConsegnato() ? "Si" : "No";
            else 
				result = OGGETTO_NULLO;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
	}
	
	private String formattaData(Date d) {
		Object[] params = new Object[]{d};
		return MessageFormat.format("{0,date,dd/MM/yyyy}",params);
	}
	
	public void aggiungiOrdine(Ordine ordine) {
		this.ordini.add(ordine);		
	}
	
	public void ricaricaOrdini() {
		this.ordini = 
			new LinkedList<Ordine>(CdR.getInstance().getOrdini());
	}

}
