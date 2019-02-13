package it.aps.cdr.interfaccia.swing;

import it.aps.cdr.dominio.CategoriaCliente;
import it.aps.cdr.dominio.GestoreClienti;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class CategorieClientiTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	private static final String OGGETTO_NULLO = "-";
	
	private List<CategoriaCliente> categorieClienti;
	private List<String> campi;
	
	/** l'oggetto utilizzato per formattare i numeri decimali */
    private DecimalFormat df = new DecimalFormat();

	public CategorieClientiTableModel() {
		this.df.setMaximumFractionDigits(2);
		this.categorieClienti = new LinkedList<CategoriaCliente>
			(GestoreClienti.getInstance().getCategorieClienti());
	    this.campi = new LinkedList<String>();
	    this.campi.add("codice");
		this.campi.add("nome categoria");
		this.campi.add("descrizione");
		this.campi.add("ordini");
		this.campi.add("fattore moltiplicativo");
	}
	
	public int getRowCount() {
		return this.categorieClienti.size();
	}

	public int getColumnCount() {
		return this.campi.size();
	}

	public String getColumnName(int colonna) {
		return this.campi.get(colonna).toString();
	}
	
	public Object getValueAt(int riga, int colonna) {
		Object result = OGGETTO_NULLO;
		CategoriaCliente cc = (CategoriaCliente)this.categorieClienti.get(riga);
        try {            
            if (colonna == 0)
                result = cc.getCodice().getCodiceCategoriaCliente();
            else if (colonna == 1) 
				result = cc.getNome();
            else if (colonna == 2) 
                result = cc.getDescrizione();
            else if (colonna == 3)
				result = cc.getOrdini();
            else if (colonna == 4)	
				result = this.df.format(cc.getFattoreCliente());
            else 
				result = OGGETTO_NULLO;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
	}
	
	public List getCategorieClienti() {
		return this.categorieClienti;
	}

	public void aggiungiCategoriaCliente(CategoriaCliente cc) {
		this.categorieClienti.add(cc);
	}
	
	public void ricaricaCategorieClienti() {
		this.categorieClienti = 
			new LinkedList<CategoriaCliente>(GestoreClienti.getInstance().getCategorieClienti());
	}

}
