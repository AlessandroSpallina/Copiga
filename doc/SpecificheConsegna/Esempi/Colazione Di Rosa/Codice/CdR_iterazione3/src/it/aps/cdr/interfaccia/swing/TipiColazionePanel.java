package it.aps.cdr.interfaccia.swing;


import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;

public class TipiColazionePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel owner;
	private JSplitPane splitPanel;
	private JTable tabellaTipiColazione;
	private JTable tabellaSpecificaColazione;
	
	public TipiColazionePanel(JPanel panel) {
		super(new BorderLayout());
		this.owner = panel;
		this.buildGUI();
	}
	
	protected void buildGUI() {
		this.buildComponents();
	}
	
	protected void buildComponents() {
		this.tabellaTipiColazione = this.createTabellaTipiColazione();
		this.tabellaSpecificaColazione = this.createTabellaComponenti();
		this.splitPanel = this.createSplitPane(this.tabellaTipiColazione,this.tabellaSpecificaColazione);
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
	
	protected JTable createTabellaTipiColazione() {
		return new TipiColazioneTable(this,new TipiColazioneTableModel());
	}
	
	protected JTable createTabellaComponenti() {
		return new ComponentiColazioneTable(this,new ComponentiColazioneTableModel(null));
	}
	
	protected JTable getTabellaComponentiColazione() {
		return this.tabellaSpecificaColazione;
	}
	
	protected JTable getTabellaTipiColazione() {
		return this.tabellaTipiColazione;
	}

}
