/**
 * 
 */
package it.aps.cdr.interfaccia.swing;

import it.aps.cdr.dominio.CategoriaCliente;
import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.GestoreClienti;
import it.aps.cdr.eventi.Evento;
import it.aps.cdr.eventi.PropertyListener;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class CategorieClientiTable extends JTable implements PropertyListener {

	private static final long serialVersionUID = 1L;

	/** il font utilizzato */
	private static final Font FONT = new Font("Verdana",Font.PLAIN,12);
	
	/** il modello di questa tabella */
    private AbstractTableModel etm;    
    private JPanel owner;
    
    private int rigaClic;
    
    private boolean ascendingOrder = true;
 	
	public CategorieClientiTable(JPanel panel,AbstractTableModel atm) {
        super(atm);
        this.owner = panel;
        this.etm = atm;
        this.setFont(FONT);
        this.buildGUI(); 	
		CdR.getInstance().addPropertyListener(this);
		GestoreClienti.getInstance().addPropertyListener(this);
	}
	
	protected void buildGUI() {
		defineColumnWidth(0,100);
		defineColumnWidth(3,100);		
		defineColumnWidth(4,200);		
		this.setShowVerticalLines(true);
        
        /* impedisce lo spostamento delle colonne */
        JTableHeader header = this.getTableHeader();
        header.setReorderingAllowed(false);
        header.setFont(FONT.deriveFont(Font.BOLD));
          
		/* allineamento del testo nelle celle */
        for (int i = 0; i < this.etm.getColumnCount(); i++)
			this.defineColumnAlignment(this.etm.getColumnName(i),JLabel.CENTER);
		this.defineColumnAlignment("nome categoria",JLabel.LEFT);
		this.defineColumnAlignment("descrizione",JLabel.LEFT);
	}
	
	protected void defineColumnWidth(int column,int width) {
		TableColumnModel tcm = this.getColumnModel();
		TableColumn tc = tcm.getColumn(column);
        tc.setPreferredWidth(width);
        tc.setMinWidth(width);
        tc.setMaxWidth(width);
	}
	
	protected void defineColumnAlignment(String columnName,int alignment) {
        TableColumn column = this.getColumn(columnName);
        DefaultTableCellRenderer columnRenderer = new DefaultTableCellRenderer();
        columnRenderer.setHorizontalAlignment(alignment);
        column.setCellRenderer(columnRenderer);
	}

	/* ************************************************************************************ */
	/* ************************************** OBSERVER ************************************ */
	/* ************************************************************************************ */
	
	public void onPropertyEvent(Object source,String property,Object value) {
		if (property.equals(Evento.CONFERMA_NUOVA_CATEGORIA_CLIENTE)) {
			CategoriaCliente cc = (CategoriaCliente)value;
			((CategorieClientiTableModel)this.etm).aggiungiCategoriaCliente(cc);
			this.resizeAndRepaint();
		} else if (property.equals(Evento.RICARICA_DATI)) {
			((CategorieClientiTableModel)this.etm).ricaricaCategorieClienti();
			this.resizeAndRepaint();
		}	
	}

}
