package it.aps.cdr.interfaccia.swing;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;

public class OrdiniPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JPanel owner;
	private JSplitPane splitPanel;
	private JTable tabellaOrdini;
	private JTable tabellaColazioniOrdinate;
	
	public OrdiniPanel(JPanel panel) {
		super(new BorderLayout());
		this.owner = panel;
		this.buildGUI();
	}
	
	protected void buildGUI() {
		this.buildComponents();
	}
	
	protected void buildComponents() {
		this.tabellaOrdini = this.createTabellaOrdini();
		this.tabellaColazioniOrdinate = this.createTabellaColazioniOrdinate();
		this.splitPanel = this.createSplitPane(this.tabellaOrdini,this.tabellaColazioniOrdinate);
		this.add(this.splitPanel,BorderLayout.CENTER);
	}
	
	protected JSplitPane createSplitPane(JComponent cmp1,JComponent cmp2) {
		JSplitPane splitPane = new JSplitPane(
				JSplitPane.VERTICAL_SPLIT,
				new JScrollPane(cmp1),
				new JScrollPane(cmp2));
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(350);
		return splitPane;
	}
	
	protected JTable createTabellaOrdini() {
		return new OrdiniTable(this,new OrdiniTableModel());
	}
	
	protected JTable createTabellaColazioniOrdinate() {
		return new ColazioniOrdinateTable(this,new ColazioniOrdinateTableModel(null));
	}
	
	protected JTable getTabellaOrdini() {
		return this.tabellaOrdini;
	}
	
	protected JTable getTabellaColazioniOrdinate() {
		return this.tabellaColazioniOrdinate;
	}
	
}
