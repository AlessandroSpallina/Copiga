package it.aps.cdr.interfaccia.swing;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.TipoColazione;
import it.aps.cdr.dominio.TipoColazioneID;
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

public class TipiColazioneTable extends JTable implements PropertyListener, MouseListener {

	private static final long serialVersionUID = 1L;

	/** il font utilizzato */
	private static final Font FONT = new Font("Verdana",Font.PLAIN,12);
	
	/** il modello di questa tabella */
    private AbstractTableModel etm;    
    private JPanel owner;
    
    private int rigaClic;
    
    private boolean ascendingOrder = true;
 	
	public TipiColazioneTable(JPanel panel, AbstractTableModel atm) {
        super(atm);
        this.owner = panel;
        this.etm = atm;
        this.setFont(FONT);
        this.buildGUI();
        this.registerListeners();
		CdR.getInstance().addPropertyListener(this);
		CdR.getInstance().getMenu().addPropertyListener(this);
	}
	
	protected void buildGUI() {
		defineColumnWidth(0,100);
		defineColumnWidth(2,200);
		this.setShowVerticalLines(true);
        
        /* impedisce lo spostamento delle colonne */
        JTableHeader header = this.getTableHeader();
        header.setReorderingAllowed(false);
        header.setFont(FONT.deriveFont(Font.BOLD));
          
		/* allineamento del testo nelle celle */
        for (int i = 0; i < this.etm.getColumnCount(); i++)
			this.defineColumnAlignment(this.etm.getColumnName(i),JLabel.CENTER);
		this.defineColumnAlignment("nome colazione",JLabel.LEFT);
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
		int codiceTC = (Integer)etm.getValueAt(rigaClic,0);
		TipoColazione tc = CdR.getInstance().trovaTipoColazione(new TipoColazioneID(codiceTC));
		((ComponentiColazioneTable)((TipiColazionePanel)this.owner).getTabellaComponentiColazione()).setTipoColazione(tc);
	}
	    
	public void mouseReleased(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}

	/* ************************************************************************************ */
	/* ************************************** OBSERVER ************************************ */
	/* ************************************************************************************ */
	
	public void onPropertyEvent(Object source,String property,Object value) {
		if (property.equals(Evento.CONFERMA_NUOVO_TIPO_COLAZIONE)) {
			TipoColazione tc = (TipoColazione)value;
			((TipiColazioneTableModel)this.etm).aggiungiTipoColazione(tc);
			this.resizeAndRepaint();
		} else if (property.equals(Evento.RICARICA_DATI)) {
			((TipiColazioneTableModel)this.etm).ricaricaTipiColazione();
			this.resizeAndRepaint();
		}	
	}
	
}
