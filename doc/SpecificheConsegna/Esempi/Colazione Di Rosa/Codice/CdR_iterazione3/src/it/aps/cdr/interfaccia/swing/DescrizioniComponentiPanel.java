package it.aps.cdr.interfaccia.swing;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class DescrizioniComponentiPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JPanel owner;
	private JTable tabellaDescrizioniComponenti;
	
	public DescrizioniComponentiPanel(JPanel panel) {
		super(new BorderLayout());
		this.owner = panel;
		this.buildGUI();
	}
	
	protected void buildGUI() {
		this.buildComponents();
	}
	
	protected void buildComponents() {
		this.tabellaDescrizioniComponenti = createTabellaDescrizioniComponenti();
		this.add(new JScrollPane(this.tabellaDescrizioniComponenti),BorderLayout.CENTER);
	}
	
	protected JTable createTabellaDescrizioniComponenti() {
		return new DescrizioniComponentiTable(this,new DescrizioniComponentiTableModel());
	}

}
