package it.aps.cdr.interfaccia.swing;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.Ordine;
import it.aps.cdr.dominio.OrdineID;
import it.aps.cdr.eventi.Evento;
import it.aps.cdr.eventi.PropertyListener;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class OrdiniTable extends JTable implements PropertyListener, MouseListener {

	private static final long serialVersionUID = 1L;

	/** il font utilizzato */
	private static final Font FONT = new Font("Verdana",Font.PLAIN,12);
	
	/** il modello di questa tabella */
    private AbstractTableModel etm;    
    private JPanel owner;
    
    private int rigaClic;
    
    private boolean ascendingOrder = true;
 	
	public OrdiniTable(JPanel panel,AbstractTableModel atm) {
        super(atm);
        this.owner = panel;
        this.etm = atm;
        this.setFont(FONT);
        this.buildGUI();
        this.registerListeners();
		CdR.getInstance().addPropertyListener(this);
	}
	
	protected void buildGUI() {
		defineColumnWidth(0,100);
		defineColumnWidth(1,200);
		defineColumnWidth(3,150);
		defineColumnWidth(4,100);
		this.setShowVerticalLines(true);
        
        /* impedisce lo spostamento delle colonne */
        JTableHeader header = this.getTableHeader();
        header.setReorderingAllowed(false);
        header.setFont(FONT.deriveFont(Font.BOLD));
          
		/* allineamento del testo nelle celle */
        for (int i = 0; i < this.etm.getColumnCount(); i++)
			this.defineColumnAlignment(this.etm.getColumnName(i),JLabel.CENTER);
		this.defineColumnAlignment("cliente",JLabel.LEFT);
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
	
	/* gestione degli eventi */
	
	protected void registerListeners() {
		this.tableHeader.addMouseListener(this);
		this.addMouseListener(this);
	}

	public void mousePressed(MouseEvent e) {
		this.rigaClic = this.rowAtPoint(e.getPoint());
	}
	
	public void mouseClicked(MouseEvent e) {
		this.rigaClic = this.rowAtPoint(e.getPoint());
		int codiceO = (Integer)etm.getValueAt(rigaClic,0);
		Ordine o = CdR.getInstance().getOrdine(new OrdineID(codiceO));
		((ColazioniOrdinateTable)((OrdiniPanel)this.owner).getTabellaColazioniOrdinate()).setOrdine(o);
	}
	    
	public void mouseReleased(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}

	/* ************************************************************************************ */
	/* ************************************** OBSERVER ************************************ */
	/* ************************************************************************************ */
	
	public void onPropertyEvent(Object source, String property, Object value) {
		if (property.equals(Evento.CONFERMA_NUOVO_ORDINE)){
			Ordine o = (Ordine)value; 
			((OrdiniTableModel)this.etm).aggiungiOrdine(o);
			this.resizeAndRepaint();
		} else if (property.equals(Evento.RICARICA_DATI)) {
			((OrdiniTableModel)this.etm).ricaricaOrdini();
			this.resizeAndRepaint();
		}	
	}
	
}
