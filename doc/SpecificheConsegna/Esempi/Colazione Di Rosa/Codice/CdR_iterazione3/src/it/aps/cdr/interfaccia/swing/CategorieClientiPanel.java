package it.aps.cdr.interfaccia.swing;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CategorieClientiPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel owner;
	private JTable tabellaCategorieClienti;
	
	public CategorieClientiPanel(JPanel panel) {
		super(new BorderLayout());
		this.owner = panel;
		this.buildGUI();
	}
	
	protected void buildGUI() {
		this.buildComponents();
	}
	
	protected void buildComponents() {
		this.tabellaCategorieClienti = createTabellaCategorieClienti();
		this.add(new JScrollPane(this.tabellaCategorieClienti),BorderLayout.CENTER);
	}
	
	protected JTable createTabellaCategorieClienti() {
		return new CategorieClientiTable(this,new CategorieClientiTableModel());
	}
}
