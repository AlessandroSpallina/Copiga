package it.aps.cdr.interfaccia.swing.uc2;

import it.aps.cdr.dominio.CalcolatorePrezzo;
import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.Cliente;
import it.aps.cdr.dominio.Ordine;
import it.aps.cdr.dominio.Prezzo;
import it.aps.cdr.dominio.TipoColazione;
import it.aps.cdr.eventi.Evento;
import it.aps.cdr.eventi.PropertyListener;
import it.aps.cdr.interfaccia.swing.RosaMainPanel;
import it.aps.cdr.interfaccia.swing.uc2.comandi.ComandoAggiungiColazione;
import it.aps.cdr.interfaccia.swing.uc2.comandi.ComandoConfermaNuovoOrdine;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class NuovoOrdineDialog extends JDialog implements PropertyListener, ActionListener {

	private static final long serialVersionUID = 1L;
	
	/** costanti di utilità */
    private static final int ALTEZZA = 455;
    private static final int LARGHEZZA = 450;
    private static final int INTERLINEA = 40;
    private static final int MARGINE_SUPERIORE = 15;
    private static final int MARGINE_SINISTRO = 15;
    private static final int MARGINE_DESTRO = 15;
  
    private JPanel owner;
	private JComboBox colazioni;
	private JButton aggiungiColazione;
	private JButton completaOrdine;
    private JButton ok;
    private JButton annulla;
	private JTable colazioniAggiunte;
	private JLabel prezzoOrdine;
	private JLabel consegna;
	private JLabel cliente;
		
    public NuovoOrdineDialog(JPanel panel) {
        super(((RosaMainPanel)panel).getOwnerFrame(),"nuovo ordine",true);
        this.owner = panel;      
		/** l'ordine viene creato nel momento in cui si apre la finestra di dialogo */
		CdR.getInstance().nuovoOrdine();
		CdR.getInstance().getOrdineCorrente().addPropertyListener(this);
        this.buildGUI();        
        /** dimensioni della finestra di dialogo */
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int larg = LARGHEZZA;
        int alt = ALTEZZA;
        this.setSize(larg, alt);
        this.setLocation(screenSize.width/2-larg/2,screenSize.height/2-alt/2);
        this.setResizable(false);
        this.setVisible(true);
    }

    protected void buildGUI() {
        Container c = this.getContentPane();
        c.setLayout(null);
        this.buildComponents();
        
        /** costruzione delle etichette */
        JLabel colazione = new JLabel("Colazione: ");
        colazione.setBounds(MARGINE_SINISTRO,MARGINE_SUPERIORE,80,20);
		JLabel consegna = new JLabel("Consegna: ");
		consegna.setBounds(MARGINE_SINISTRO,2*MARGINE_SUPERIORE+6*INTERLINEA,200,20);
		JLabel clienteLabel = new JLabel("Cliente: ");
		clienteLabel.setBounds(MARGINE_SINISTRO,2*MARGINE_SUPERIORE+7*INTERLINEA,200,20);
		
		/** lista delle colazioni aggiunte all'ordine */
		JScrollPane listScroller = new JScrollPane(this.colazioniAggiunte);
		listScroller.setBounds(MARGINE_SINISTRO,MARGINE_SUPERIORE+INTERLINEA,LARGHEZZA-MARGINE_SINISTRO-MARGINE_DESTRO-10,4*INTERLINEA); 
		listScroller.setVisible(true);
		
        /** collocamento delle componenti */
        c.add(colazione);
		c.add(this.colazioni);
		c.add(this.aggiungiColazione);  
		c.add(listScroller);
		c.add(consegna);
		c.add(this.consegna);
		c.add(clienteLabel);
		c.add(this.cliente);
		c.add(this.completaOrdine);
		c.add(this.prezzoOrdine);
		c.add(this.ok);
        c.add(this.annulla);    
    }
    
    protected void buildComponents() {
    	this.buildLabels();
		this.buildComboBoxes();
		this.buildTables();
    	this.buildButtons();
    	this.registerListeners();
    }
	
	protected void buildLabels() {
        this.prezzoOrdine = new JLabel("PREZZO:  -  €");
		this.prezzoOrdine.setBounds(MARGINE_SINISTRO,2*MARGINE_SUPERIORE+8*INTERLINEA,LARGHEZZA,20);
		this.consegna = new JLabel(" da definire ");
		this.consegna.setBounds(MARGINE_SINISTRO+80,2*MARGINE_SUPERIORE+6*INTERLINEA,LARGHEZZA,20);		
		this.cliente = new JLabel(" da definire ");
		this.cliente.setBounds(MARGINE_SINISTRO+60,2*MARGINE_SUPERIORE+7*INTERLINEA,LARGHEZZA,20);		
	}
	
	protected void buildComboBoxes() {
		this.colazioni = new JComboBox();
        Collection<TipoColazione> c = CdR.getInstance().getMenu().getTipiColazione();
		for (TipoColazione tc : c)
            this.colazioni.addItem(tc);
		this.colazioni.setBounds(MARGINE_SINISTRO+90,MARGINE_SUPERIORE,LARGHEZZA/3+20,20);
		this.colazioni.setEnabled(true);
	}
	
	protected void buildTables() {
		this.colazioniAggiunte = new ColazioniAggiunteTable(this,new ColazioniAggiunteTableModel(null));
	}
	
    protected void buildButtons() {
    	int largBott = 160;    	
        this.aggiungiColazione = createButton("aggiungi",LARGHEZZA-MARGINE_SINISTRO-(largBott/2+40),MARGINE_SUPERIORE,largBott/2+30,20,true);
		this.ok = createButton("OK",LARGHEZZA/2-largBott/2-10,2*MARGINE_SUPERIORE+9*INTERLINEA,largBott/2,20,false);
        this.annulla = createButton("Annulla",LARGHEZZA/2+10,2*MARGINE_SUPERIORE+9*INTERLINEA,largBott/2,20,true);
        this.completaOrdine = createButton("completa ordine",MARGINE_SINISTRO,2*MARGINE_SUPERIORE+5*INTERLINEA,largBott,20,false);
    }
   
	protected JTextField createTextField(int margSin,int margSup,int larg,int alt,boolean enabled) {
    	JTextField tf = new JTextField();
    	tf.setMargin(new Insets(0,3,0,0));
    	tf.select(0,tf.getText().length());
    	tf.setBounds(margSin,margSup,larg,alt);
		tf.setEnabled(enabled);
		return tf;
	}
	
	protected JButton createButton(String label,int margSin,int margSup,int larg,int alt,boolean enabled) {
		JButton button = new JButton(label);
		button.setFocusPainted(false);
        button.setBounds(margSin,margSup,larg,alt);
		button.setEnabled(enabled);
		return button;
	}
	
	public TipoColazione getTipoColazione() {
		return (TipoColazione)this.colazioni.getSelectedItem();
	}
		
	/** gestione degli eventi */
	
    protected void registerListeners() {
    	this.ok.addActionListener(this);
    	this.annulla.addActionListener(this);
		this.aggiungiColazione.addActionListener(this);
		this.completaOrdine.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();        
		if (o.equals(this.aggiungiColazione))
			(new ComandoAggiungiColazione()).esegui(this);
		else if (o.equals(this.ok))
			(new ComandoConfermaNuovoOrdine()).esegui(this);
		else if (o.equals(this.completaOrdine))
			new CompletaOrdineDialog(this);
		else if (o.equals(this.annulla))
			this.chiudiFinestra();
    }

    public void chiudiFinestra() {
    	this.setVisible(false);
    }
	
	/* ************************************************************************************ */
	/* ************************************** OBSERVER ************************************ */
	/* ************************************************************************************ */
	
	public void onPropertyEvent(Object source,String property,Object value) {
		if (property.equals(Evento.CONFERMA_COLAZIONE_ORDINATA)) {
			Ordine o = (Ordine)source;
			Prezzo totale = CalcolatorePrezzo.getInstance().calcolaPrezzo(o);
			DecimalFormat df = new DecimalFormat();
			df.setMaximumFractionDigits(2);
			this.prezzoOrdine.setText("PREZZO:  " + df.format(totale.getImporto()) + "  €"); 
			this.completaOrdine.setEnabled(true);
		}
		if (property.equals(Evento.COMPLETA_ORDINE)) {
			Ordine o = (Ordine)source;
			Cliente c = o.getCliente();
			Date d = o.getData();
			Prezzo p = o.getPrezzo();
			DecimalFormat df = new DecimalFormat();
			df.setMaximumFractionDigits(2);
			this.prezzoOrdine.setText("PREZZO:  " + df.format(p.getImporto()) + "  €");
			this.cliente.setText(c.getNome() + " " + c.getCognome());
			this.consegna.setText(formattaData(d));
			this.ok.setEnabled(true);
		}		
	}
	
	private String formattaData(Date d) {
		Object[] params = new Object[]{d};
		return MessageFormat.format("{0,date,dd/MM/yyyy}",params);
	}
	
}
