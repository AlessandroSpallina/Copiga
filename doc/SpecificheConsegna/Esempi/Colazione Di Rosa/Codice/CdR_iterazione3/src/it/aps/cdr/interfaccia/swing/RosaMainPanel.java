package it.aps.cdr.interfaccia.swing;

import it.aps.cdr.interfaccia.swing.comandi.ComandoRicaricaDati;
import it.aps.cdr.interfaccia.swing.uc1.NuovoTipoColazioneDialog;
import it.aps.cdr.interfaccia.swing.uc2.NuovoOrdineDialog;
import it.aps.cdr.interfaccia.swing.uc3.NuovoClienteDialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;

public class RosaMainPanel extends JPanel implements ActionListener {

	/** Seriale */
	private static final long serialVersionUID = 1L;

	/** il font utilizzato */
	private static final Font FONT = new Font("Verdana",Font.BOLD,10);
	
	private JFrame window;
	private JToolBar tools;
	private JButton nuovoTipoColazione;
	private JButton nuovoOrdine;
	private JButton nuovoCliente;
	private JButton ricaricaDati;
	private JButton crediti;

	private JPanel pannelloColazioni;
	private JPanel pannelloDescrizioniComponenti;
	private JPanel pannelloClienti;
	private JPanel pannelloCategorieClienti;
	private JPanel pannelloModiServizio;
	private JPanel pannelloOrdini;
			
	public RosaMainPanel(JFrame window) {
		super(new BorderLayout());
		this.window = window;
		this.buildGUI();
		this.registerListeners();
	}
	
	protected void buildGUI() {
		//togliendo il commento si utilizza il look and feel di sistema
		//this.setLookAndFeel();				
		this.buildComponents();
		this.add(this.tools,BorderLayout.NORTH);
		this.add(this.createTabbedPane(),BorderLayout.CENTER);
	}
	
	protected void buildComponents() {
		this.buildButtons();
		this.buildToolBar();
		this.buildPanels();
	}
	
	protected void buildButtons() {
		String separator = System.getProperty("file.separator"); 
		this.nuovoTipoColazione = 
			(JButton)createButton("nuovo tipo colazione","nuovo tipo colazione",true);
		this.nuovoOrdine = 
			(JButton)createButton("nuovo ordine","nuovo ordine",true);
		this.nuovoCliente = 
			(JButton)createButton("nuovo cliente","nuovo cliente",true);
		this.ricaricaDati = 
			(JButton)createButton("ricarica dati","ricarica dati",true);
		this.crediti = 
			(JButton)createButton("crediti","crediti",true);
		// esempio di creazione di un pulsante con le icone
		// (JButton)createButton(new ImageIcon("img" + separator + "new.gif"),"nuovo",true);	
	} 
	
	protected void buildToolBar() {
		this.tools = new JToolBar();
		this.tools.add(this.nuovoTipoColazione);
		this.tools.add(this.nuovoOrdine);
		this.tools.add(this.nuovoCliente);
		this.tools.add(this.ricaricaDati);
		this.tools.add(this.crediti);
	}
	
	protected void buildPanels() {
		this.pannelloColazioni = createTipiColazionePanel();
		this.pannelloDescrizioniComponenti = createDescrizioniComponentiPanel();
		this.pannelloClienti = createClientiPanel(); 
		this.pannelloCategorieClienti = createCategorieClientiPanel();
		this.pannelloModiServizio = createModiServizioPanel();
		this.pannelloOrdini = createOrdiniPanel();
	}

	private JPanel createTipiColazionePanel() {
		return new TipiColazionePanel(this);
	}
	
	private JPanel createDescrizioniComponentiPanel() {
		return new DescrizioniComponentiPanel(this);
	}

	private JPanel createClientiPanel() {
		return new ClientiPanel(this);
	}
	
	private JPanel createCategorieClientiPanel() {
		return new CategorieClientiPanel(this);	
	}
	
	private JPanel createModiServizioPanel() {
		return new ModiServizioPanel(this);
	}
	
	private JPanel createOrdiniPanel() {
		return new OrdiniPanel(this);
	}
	
	private JPanel createTabbedPane() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.white);
		panel.setBorder(new BevelBorder(1));
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.NORTH);
		tabbedPane.setFont(FONT);
		tabbedPane.add("COLAZIONI",this.pannelloColazioni);
		tabbedPane.add("COMPONENTI",this.pannelloDescrizioniComponenti);
		tabbedPane.add("MODI SERVIZIO",this.pannelloModiServizio);
		tabbedPane.add("CATEGORIE CLIENTI",this.pannelloCategorieClienti);
		tabbedPane.add("ORDINI",this.pannelloOrdini);
		tabbedPane.add("CLIENTI",this.pannelloClienti);
		panel.add(tabbedPane,BorderLayout.CENTER);
		return panel;
	}
	
	protected AbstractButton createButton(Icon icon,String toolTipText,boolean enabled) {
		JButton button = new JButton(icon);
		button.setFocusPainted(false);
		button.setMargin(new Insets(0,0,0,0));
		button.setToolTipText(toolTipText);
		button.setEnabled(enabled);
		return button;
	}
	
	protected AbstractButton createButton(String label,String toolTipText,boolean enabled) {
		JButton button = new JButton(label);
		button.setFocusPainted(false);
		button.setMargin(new Insets(0,0,0,0));
		button.setToolTipText(toolTipText);
		button.setEnabled(enabled);
		return button;
	}
	
	protected void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (InstantiationException e) {
		} catch (ClassNotFoundException e) {
		} catch (UnsupportedLookAndFeelException e) {
		} catch (IllegalAccessException e) {
		}
	}
		
	public JFrame getOwnerFrame() {
		return this.window;
	}
		
	private void credits() {
		String credits = 
			"Copyright (C) 2005" +
			"\nFabrizio Martorelli"+
			"\nAndrea Petreri" +
			"\nGabriele Rendina" +
			"\n";
		JOptionPane.showMessageDialog(
			null,credits,"Crediti",JOptionPane.INFORMATION_MESSAGE, null);	
	}

	/* Gestione degli eventi */
	
	public void registerListeners() {
		this.nuovoTipoColazione.addActionListener(this);
		this.nuovoCliente.addActionListener(this);
		this.nuovoOrdine.addActionListener(this);
		this.ricaricaDati.addActionListener(this);
		this.crediti.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if (o.equals(this.nuovoTipoColazione))
			new NuovoTipoColazioneDialog(this);
		else if (o.equals(this.nuovoCliente))
			new NuovoClienteDialog(this.window);
		else if (o.equals(this.nuovoOrdine))
			new NuovoOrdineDialog(this);
		else if (o.equals(this.ricaricaDati))
			new RicaricaDatiDialog(this.window);
		else if (o.equals(this.crediti))
			this.credits();
	}

}
