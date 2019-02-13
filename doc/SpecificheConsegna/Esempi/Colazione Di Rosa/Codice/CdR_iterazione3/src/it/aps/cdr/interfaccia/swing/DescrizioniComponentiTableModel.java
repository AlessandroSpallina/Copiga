package it.aps.cdr.interfaccia.swing;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.DescrizioneComponente;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class DescrizioniComponentiTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	private static final String OGGETTO_NULLO = "-";
	
	private List<DescrizioneComponente> descrizioniComponenti;
	private List<String> campi;
	
	/** l'oggetto utilizzato per formattare i numeri decimali */
    private DecimalFormat df = new DecimalFormat();

	public DescrizioniComponentiTableModel() {
		this.df.setMaximumFractionDigits(2);
		this.descrizioniComponenti = new LinkedList<DescrizioneComponente>
			(CdR.getInstance().getMenu().getDescrizioniComponenti());
	    this.campi = new LinkedList<String>();
	    this.campi.add("codice");
		this.campi.add("nome componente");
		this.campi.add("prezzo aggiuntivo (€)");
		this.campi.add("prezzo riduttivo (€)");
	}
	
	public int getRowCount() {
		return this.descrizioniComponenti.size();
	}

	public int getColumnCount() {
		return this.campi.size();
	}

	public String getColumnName(int colonna) {
		return this.campi.get(colonna).toString();
	}
	
	public Object getValueAt(int riga, int colonna) {
		Object result = OGGETTO_NULLO;
		DescrizioneComponente dc = (DescrizioneComponente)this.descrizioniComponenti.get(riga);
        try {
            if (colonna == 0)
                result = dc.getCodice().getCodiceDescrizioneComponente();
            else if (colonna == 1) 
				result = dc.getNome();
            else if (colonna == 2) 
                result = this.df.format(dc.getPrezzoAggiuntivo().getImporto());
            else if (colonna == 3)
				result = this.df.format(dc.getPrezzoRiduttivo().getImporto());
            else 
				result = OGGETTO_NULLO;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
	}
	
	public List getDescrizioniComponenti() {
		return this.descrizioniComponenti;
	}

	public void aggiungiDescrizioneComponente(DescrizioneComponente dc) {
		this.descrizioniComponenti.add(dc);
	}
	
	public void ricaricaDescrizioniComponenti() {
		this.descrizioniComponenti = new LinkedList<DescrizioneComponente>
			(CdR.getInstance().getMenu().getDescrizioniComponenti());
	}

}
