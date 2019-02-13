package it.aps.cdr.interfaccia.swing;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ClientiPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JPanel owner;
	private JTable tabellaClienti;
	
	public ClientiPanel(JPanel panel) {
		super(new BorderLayout());
		this.owner = panel;
		this.buildGUI();
	}
	
	protected void buildGUI() {
		this.buildComponents();
	}
	
	protected void buildComponents() {
		this.tabellaClienti = createTabellaClienti();
		this.add(new JScrollPane(this.tabellaClienti),BorderLayout.CENTER);
	}

	protected JTable createTabellaClienti() {
		return new ClientiTable(this,new ClientiTableModel());
	}
	
}
