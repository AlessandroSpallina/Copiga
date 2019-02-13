package it.aps.cdr.interfaccia.swing;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ModiServizioPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JPanel owner;
	private JTable tabellaDescrizioniComponenti;
	
	public ModiServizioPanel(JPanel panel) {
		super(new BorderLayout());
		this.owner = panel;
		this.buildGUI();
	}
	
	protected void buildGUI() {
		this.buildComponents();
	}
	
	protected void buildComponents() {
		this.tabellaDescrizioniComponenti = createTabellaModiServizio();
		this.add(new JScrollPane(this.tabellaDescrizioniComponenti),BorderLayout.CENTER);
	}
	
	protected JTable createTabellaModiServizio() {
		return new ModiServizioTable(this,new ModiServizioTableModel());
	}
}
