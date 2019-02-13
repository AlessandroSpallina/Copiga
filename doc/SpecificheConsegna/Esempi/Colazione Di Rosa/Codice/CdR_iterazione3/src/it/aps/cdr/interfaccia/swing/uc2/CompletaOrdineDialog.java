package it.aps.cdr.interfaccia.swing.uc2;

import it.aps.cdr.interfaccia.swing.uc2.comandi.ComandoConfermaCompletaOrdine;
import it.aps.cdr.interfaccia.swing.uc2.comandi.ComandoTrovaCliente;
import it.aps.cdr.interfaccia.swing.uc3.NuovoClienteDialog;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.util.Calendar; 
import java.util.GregorianCalendar; 

public class CompletaOrdineDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	/** costanti di utilità */
    private static final int ALTEZZA = 425;
    private static final int LARGHEZZA = 450;
    private static final int INTERLINEA = 40;
    private static final int MARGINE_SUPERIORE = 15;
    private static final int MARGINE_SINISTRO = 15;
    private static final int MARGINE_DESTRO = 15;
  
    private JDialog owner;
	private JTextField clienteId;
	private JTextField clienteNome;	
	private JTextField clienteCognome;
	private JTextField giorno;
	private JTextField mese;
	private JTextField anno;
	private JTextField ora;
	private JTextField minuto;
    private JButton ok;
    private JButton annulla;
	private JButton trovaCliente;
	private JButton registraCliente;
	private JTable clienti;
		
    public CompletaOrdineDialog(JDialog dialog) {
        super((NuovoOrdineDialog)dialog,"completa ordine",true);
        this.owner = dialog;
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
		JLabel clienteId = new JLabel("Cliente (ID):");
		clienteId.setBounds(MARGINE_SINISTRO,MARGINE_SUPERIORE,100,20);
		JLabel ricercaCliente = new JLabel("Ricerca cliente in base a nome e cognome:");
		ricercaCliente.setBounds(MARGINE_SINISTRO,MARGINE_SUPERIORE+INTERLINEA,250,20);
		JLabel ricercaClienteNome = new JLabel("Nome :");
		ricercaClienteNome.setBounds(MARGINE_SINISTRO,MARGINE_SUPERIORE+2*INTERLINEA,100,20);
		JLabel ricercaClienteCognome = new JLabel("Cognome :");
		ricercaClienteCognome.setBounds(MARGINE_SINISTRO+180,MARGINE_SUPERIORE+2*INTERLINEA,100,20);
		JLabel consegna = new JLabel("Data consegna:");
		consegna.setBounds(MARGINE_SINISTRO,2*MARGINE_SUPERIORE+7*INTERLINEA,100,20);
		JLabel slash1 = new JLabel("/");
        slash1.setBounds(MARGINE_SINISTRO+150,2*MARGINE_SUPERIORE+7*INTERLINEA,10,20);
        JLabel slash2 = new JLabel("/");
        slash2.setBounds(MARGINE_SINISTRO+210,2*MARGINE_SUPERIORE+7*INTERLINEA,10,20);
        JLabel dots = new JLabel(":");
        dots.setBounds(MARGINE_SINISTRO+365,2*MARGINE_SUPERIORE+7*INTERLINEA,10,20);
        
		/** lista delle colazioni aggiunte all'ordine */
		JScrollPane listScroller = new JScrollPane(this.clienti);
		listScroller.setBounds(MARGINE_SINISTRO,MARGINE_SUPERIORE+3*INTERLINEA,LARGHEZZA-MARGINE_SINISTRO-MARGINE_DESTRO-10,4*INTERLINEA); 
		listScroller.setVisible(true);
		
        /** collocamento delle componenti */
		c.add(clienteId);
		c.add(this.clienteId);
		c.add(ricercaCliente);
		c.add(this.clienteNome);
		c.add(this.clienteCognome);
		c.add(this.trovaCliente);
		c.add(listScroller);
		c.add(consegna);
        c.add(this.giorno);
		c.add(slash1);
		c.add(this.mese);
		c.add(slash2);
		c.add(this.anno);
		c.add(this.ora);
		c.add(dots);
		c.add(this.minuto);
		c.add(this.registraCliente);
		c.add(this.ok);
        c.add(this.annulla);    
    }
    
    protected void buildComponents() {
    	this.buildTextFields();
		this.buildTables();
    	this.buildButtons();
    	this.registerListeners();
    }
    
    protected void buildTextFields() {
		this.clienteId = createTextField(MARGINE_SINISTRO+80,MARGINE_SUPERIORE,80,20,true);       
        this.clienteNome = createTextField(MARGINE_SINISTRO,MARGINE_SUPERIORE+2*INTERLINEA,140,20,true);       
		this.clienteCognome = createTextField(MARGINE_SINISTRO+160,MARGINE_SUPERIORE+2*INTERLINEA,150,20,true);       	
		this.giorno = createTextField(MARGINE_SINISTRO+105,2*MARGINE_SUPERIORE+7*INTERLINEA,30,20,true);       
        this.mese = createTextField(MARGINE_SINISTRO+165,2*MARGINE_SUPERIORE+7*INTERLINEA,30,20,true);
        this.anno = createTextField(MARGINE_SINISTRO+225,2*MARGINE_SUPERIORE+7*INTERLINEA,60,20,true);
		this.ora = createTextField(MARGINE_SINISTRO+320,2*MARGINE_SUPERIORE+7*INTERLINEA,30,20,true);       
		this.minuto = createTextField(MARGINE_SINISTRO+380,2*MARGINE_SUPERIORE+7*INTERLINEA,30,20,true);
		/* inizializza data e ora con l'ora corrente */ 
		Calendar c = new GregorianCalendar(); 
		this.setGiorno(String.valueOf(c.get(Calendar.DAY_OF_MONTH))); 
		this.setMese(String.valueOf(c.get(Calendar.MONTH)+1)); 
		this.setAnno(String.valueOf(c.get(Calendar.YEAR))); 
		this.setOra(String.valueOf(c.get(Calendar.HOUR_OF_DAY))); 
		this.setMinuto(String.valueOf(c.get(Calendar.MINUTE))); 
    }
	
	protected void buildTables() {
		this.clienti = new ClientiRicercaTable(this,new ClientiRicercaTableModel());
	}
	
    protected void buildButtons() {
    	int largBott = 160;    	
		this.trovaCliente = createButton("trova",MARGINE_SINISTRO+330,MARGINE_SUPERIORE+2*INTERLINEA,largBott/2,20,true);
		this.ok = createButton("OK",LARGHEZZA/2-largBott/2-10,2*MARGINE_SUPERIORE+8*INTERLINEA+10,largBott/2,20,true);
        this.annulla = createButton("Annulla",LARGHEZZA/2+10,2*MARGINE_SUPERIORE+8*INTERLINEA+10,largBott/2,20,true);
		this.registraCliente = createButton("registra nuovo cliente",LARGHEZZA/2+40,MARGINE_SUPERIORE,largBott,20,true);
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

	public String getClienteID() {
		return this.clienteId.getText();
	}
	
	public String getNome() {
		return this.clienteNome.getText();
	}
	
	public String getCognome() {
		return this.clienteCognome.getText();
	}
	
	public String getGiorno() {
		return this.giorno.getText();
	}
	
	public String getMese() {
		return this.mese.getText();
	}
	
	public String getAnno() {
		return this.anno.getText();
	}
	
	public String getOra() {
		return this.ora.getText();
	}
	
	public String getMinuto() {
		return this.minuto.getText();
	}	
	
	public void setClienteID(String clienteId) {
		this.clienteId.setText(clienteId);
	}
	
	public void setGiorno(String giorno) {
		this.giorno.setText(giorno);
	}
	
	public void setMese(String mese) {
		this.mese.setText(mese);
	}
	
	public void setAnno(String anno) {
		this.anno.setText(anno);
	}
	
	public void setOra(String ora) {
		this.ora.setText(ora);
	}
	
	public void setMinuto(String minuto) {
		this.minuto.setText(minuto);
	}	

	public JTable getClientiRicercatiTable() {
		return this.clienti;
	}
	
	/** gestione degli eventi */
	
    protected void registerListeners() {
    	this.ok.addActionListener(this);
    	this.annulla.addActionListener(this);
		this.trovaCliente.addActionListener(this);
		this.registraCliente.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();        
		if (o.equals(this.registraCliente))
			new NuovoClienteDialog(this);
		else if (o.equals(this.ok))
			(new ComandoConfermaCompletaOrdine()).esegui(this);
		else if (o.equals(this.trovaCliente))
			(new ComandoTrovaCliente()).esegui(this);
		else if (o.equals(this.annulla))
			this.chiudiFinestra();
    }

    public void chiudiFinestra() {
    	this.setVisible(false);
    }
	
}
