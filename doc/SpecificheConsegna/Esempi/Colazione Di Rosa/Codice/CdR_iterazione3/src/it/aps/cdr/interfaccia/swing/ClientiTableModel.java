package it.aps.cdr.interfaccia.swing;

import it.aps.cdr.dominio.Cliente;
import it.aps.cdr.dominio.GestoreClienti;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ClientiTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	private static final String OGGETTO_NULLO = "-";
	
	private List<Cliente> clienti;
	private List<String> campi;
	
	public ClientiTableModel() {
		/* this.clienti = new LinkedList<Cliente>(); */  
		this.clienti = new LinkedList<Cliente>
			(GestoreClienti.getInstance().getClienti());
	    this.campi = new LinkedList<String>();
	    this.campi.add("codice");
		this.campi.add("nome");
		this.campi.add("cognome");
		this.campi.add("indirizzo");
		this.campi.add("ordini");
		this.campi.add("categoria");
	}
	
	public int getRowCount() {
		return this.clienti.size();
	}

	public int getColumnCount() {
		return this.campi.size();
	}

	public String getColumnName(int colonna) {
		return this.campi.get(colonna).toString();
	}
	
	public Object getValueAt(int riga, int colonna) {
		Object result = OGGETTO_NULLO;
		Cliente c = (Cliente)this.clienti.get(riga);
        try {            
            if (colonna == 0)
                result = c.getCodice().getCodiceCliente();
            else if (colonna == 1) 
				result = c.getNome();
            else if (colonna == 2) 
				result = c.getCognome();
            else if (colonna == 3)
				result = c.getIndirizzo();
            else if (colonna == 4)
				result = c.getOrdiniEffettuati();
            else if (colonna == 5)
				result = c.getCategoriaCliente();
            else 
				result = OGGETTO_NULLO;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
	}
	
	public void aggiungiCliente(Cliente cliente) {
		this.clienti.add(cliente);
	}

	public void ricaricaClienti() {
		this.clienti = new LinkedList<Cliente>(GestoreClienti.getInstance().getClienti());
	}
	
}
