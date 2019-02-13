package it.aps.cdr.interfaccia.swing.uc2;

import it.aps.cdr.dominio.Cliente;

import java.awt.Font;
import java.util.Collection;
import java.util.LinkedList;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener; 
import javax.swing.event.ListSelectionEvent; 
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class ClientiRicercaTable extends JTable implements ListSelectionListener {
	
	private static final long serialVersionUID = 1L;

	/** il font utilizzato */
	private static final Font FONT = new Font("Verdana",Font.PLAIN,12);
	
	/** il modello di questa tabella */
    private AbstractTableModel etm;    
    private JDialog owner;
    
    private int rigaClic;
    
    private boolean ascendingOrder = true;
 	
	public ClientiRicercaTable(JDialog dialog,AbstractTableModel atm) {
        super(atm);
        this.owner = dialog;
        this.etm = atm;
        this.setFont(FONT);
        this.buildGUI(); 
		//GestoreClienti.getInstance().addPropertyListener(this);
        this.abilitaSelezione(); 
	}
	
	protected void buildGUI() {
		defineColumnWidth(0,70);
		this.setShowVerticalLines(true);
        
        /* impedisce lo spostamento delle colonne */
        JTableHeader header = this.getTableHeader();
        header.setReorderingAllowed(false);
        header.setFont(FONT.deriveFont(Font.BOLD));
          
		/* allineamento del testo nelle celle */
        this.defineColumnAlignment("codice",JLabel.CENTER);
		this.defineColumnAlignment("nome",JLabel.LEFT);
		this.defineColumnAlignment("cognome",JLabel.LEFT);
		this.defineColumnAlignment("indirizzo",JLabel.LEFT);
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
	
	/* abilita la selezione di una riga (un cliente) */ 
	protected void abilitaSelezione() {
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        ListSelectionModel rowSM = this.getSelectionModel();
        rowSM.addListSelectionListener(this);		
	}
	
	/* ascolta la selezione delle righe */ 
	public void valueChanged(ListSelectionEvent e) {
        //Ignore extra messages.
        if (e.getValueIsAdjusting()) return;

        ListSelectionModel lsm =
            (ListSelectionModel)e.getSource();
        if (!lsm.isSelectionEmpty()) {
            int selectedRow = lsm.getMinSelectionIndex();
            //selectedRow is selected
            this.gestisciSelezione(selectedRow); 
        }
    }
	
	/* gestisce la selezione di una riga */ 
	protected void gestisciSelezione(int riga) {
		CompletaOrdineDialog o = (CompletaOrdineDialog) this.owner; 
		/* la colonna dell'ID è colonna 0 */
		o.setClienteID(etm.getValueAt(riga,0).toString()); 
	}

    public void setClienti(Collection<Cliente> clienti) {
		((ClientiRicercaTableModel)this.etm).setClienti(new LinkedList<Cliente>(clienti));
		this.resizeAndRepaint();
	}
	
}
