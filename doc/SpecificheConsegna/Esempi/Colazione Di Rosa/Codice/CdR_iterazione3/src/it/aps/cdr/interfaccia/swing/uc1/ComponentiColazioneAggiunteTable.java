package it.aps.cdr.interfaccia.swing.uc1;

import it.aps.cdr.dominio.ComponenteColazione;
import it.aps.cdr.eventi.Evento;
import it.aps.cdr.eventi.PropertyListener;

import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class ComponentiColazioneAggiunteTable extends JTable implements PropertyListener {

	private static final long serialVersionUID = 1L;

	/** il font utilizzato */
	private static final Font FONT = new Font("Verdana",Font.PLAIN,12);
	
	/** il modello di questa tabella */
    private AbstractTableModel etm;    
    private JDialog owner;
    
    private int rigaClic;
    
    private boolean ascendingOrder = true;
 	
	public ComponentiColazioneAggiunteTable(JDialog dialog,AbstractTableModel atm) {
        super(atm);
        this.owner = dialog;
        this.etm = atm;
        this.setFont(FONT);
        this.buildGUI(); 
	}
	
	protected void buildGUI() {	
		defineColumnWidth(0,100);
		defineColumnWidth(2,100);
		this.setShowVerticalLines(true);
        
        /* impedisce lo spostamento delle colonne */
        JTableHeader header = this.getTableHeader();
        header.setReorderingAllowed(false);
        header.setFont(FONT.deriveFont(Font.BOLD));
          
		/* allineamento del testo nelle celle */
		this.defineColumnAlignment("codice",JLabel.CENTER);
		this.defineColumnAlignment("componente",JLabel.LEFT);
		this.defineColumnAlignment("quantita'",JLabel.CENTER);
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
		if (property.equals(Evento.AGGIUNGI_COMPONENTE_TIPO_COLAZIONE)) {
			ComponenteColazione cc = (ComponenteColazione)value;
			((ComponentiColazioneAggiunteTableModel)this.etm).aggiungiComponenteColazione(cc);
			this.resizeAndRepaint();
		}
    }
	
}
