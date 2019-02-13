package it.aps.cdr.interfaccia.swing.uc1;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.DescrizioneComponente;
import it.aps.cdr.eventi.Evento;
import it.aps.cdr.eventi.PropertyListener;
import it.aps.cdr.interfaccia.swing.RosaMainPanel;
import it.aps.cdr.interfaccia.swing.uc1.comandi.ComandoAggiungiComponenteColazione;
import it.aps.cdr.interfaccia.swing.uc1.comandi.ComandoConfermaNuovoTipoColazione;
import it.aps.cdr.interfaccia.swing.uc1.comandi.ComandoNuovoTipoColazione;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class NuovoTipoColazioneDialog extends JDialog implements PropertyListener, ActionListener {

	private static final long serialVersionUID = 1L;
	
	/** costanti di utilità */
    private static final int ALTEZZA = 330;
    private static final int LARGHEZZA = 450;
    private static final int INTERLINEA = 40;
    private static final int MARGINE_SUPERIORE = 15;
    private static final int MARGINE_SINISTRO = 15;
    private static final int MARGINE_DESTRO = 15;
  
    private JPanel owner;
    private JTextField nomeValue; 
	private JComboBox componenti;
	private JTextField quantitaValue;
    private JTextField prezzoValue;
	private JButton crea;
	private JButton aggiungiComponente;
    private JButton ok;
    private JButton annulla;
	private JTable componentiAggiunti;
		
    public NuovoTipoColazioneDialog(JPanel panel) {
        super(((RosaMainPanel)panel).getOwnerFrame(),"nuovo tipo colazione",true);
        this.owner = panel;      
		CdR.getInstance().getMenu().addPropertyListener(this);
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
        JLabel nome = new JLabel("Nome: ");
        nome.setBounds(MARGINE_SINISTRO,MARGINE_SUPERIORE,80,20);   
        JLabel prezzo = new JLabel("Prezzo: € ");
        prezzo.setBounds(MARGINE_SINISTRO+290,MARGINE_SUPERIORE+5*INTERLINEA+15,80,20);
		JLabel componenti = new JLabel("Componente: ");
		componenti.setBounds(MARGINE_SINISTRO,MARGINE_SUPERIORE+INTERLINEA,80,20);
		JLabel quantita = new JLabel("Qta:");
		quantita.setBounds(MARGINE_SINISTRO+210,MARGINE_SUPERIORE+INTERLINEA,80,20);
		
		/** lista delle componenti aggiunte alla colazione */
		JScrollPane listScroller = new JScrollPane(this.componentiAggiunti);
		listScroller.setBounds(MARGINE_SINISTRO,MARGINE_SUPERIORE+2*INTERLINEA,LARGHEZZA-MARGINE_SINISTRO-MARGINE_DESTRO-10,120); 
		listScroller.setVisible(true);
		
        /** collocamento delle componenti */
        c.add(nome);
        c.add(this.nomeValue);
		c.add(this.crea);
		c.add(componenti);
		c.add(this.componenti);
		c.add(quantita);
		c.add(this.quantitaValue);
        c.add(prezzo);
        c.add(this.prezzoValue);
		c.add(this.aggiungiComponente);  
		c.add(listScroller);
		c.add(this.ok);
        c.add(this.annulla);    
    }
    
    protected void buildComponents() {
    	this.buildTextFields();
		this.buildComboBoxes();
		this.buildTables();
    	this.buildButtons();
    	this.registerListeners();
    }
    
    protected void buildTextFields() {
    	/** nome tipo colazione */
		this.nomeValue = createTextField(MARGINE_SINISTRO+50,MARGINE_SUPERIORE,
			LARGHEZZA-MARGINE_SINISTRO-MARGINE_DESTRO-160,20,true);		
		/** prezzo tipo colazione */
    	this.prezzoValue = createTextField(MARGINE_SINISTRO+350,MARGINE_SUPERIORE+5*INTERLINEA+15,
			LARGHEZZA-MARGINE_SINISTRO-MARGINE_DESTRO-360,20,false);		
		/** quantita' componente */
		this.quantitaValue = createTextField(MARGINE_SINISTRO+250,MARGINE_SUPERIORE+INTERLINEA,
			LARGHEZZA/3-MARGINE_SINISTRO-MARGINE_DESTRO-60,20,false);
    }
	
	protected void buildComboBoxes() {
		this.componenti = new JComboBox();
        Collection<DescrizioneComponente> c = CdR.getInstance().getMenu().getDescrizioniComponenti();
		for (DescrizioneComponente dc : c)
            this.componenti.addItem(dc);
		this.componenti.setBounds
			(MARGINE_SINISTRO+90,MARGINE_SUPERIORE+INTERLINEA,LARGHEZZA/3-MARGINE_SINISTRO-MARGINE_DESTRO-20,20);
		this.componenti.setEnabled(false);
	}
	
	protected void buildTables() {
		this.componentiAggiunti = new ComponentiColazioneAggiunteTable(this,new ComponentiColazioneAggiunteTableModel(null));
	}
	
    protected void buildButtons() {
    	int largBott = 160;    	
        this.crea = createButton("crea",LARGHEZZA-MARGINE_SINISTRO-(largBott/2+20),MARGINE_SUPERIORE,largBott/2+10,20,true);
		this.aggiungiComponente = createButton("aggiungi",LARGHEZZA-MARGINE_SINISTRO-(largBott/2+20),MARGINE_SUPERIORE+INTERLINEA,largBott/2+10,20,false);
        this.ok = createButton("OK",LARGHEZZA/2-largBott/2-10,MARGINE_SUPERIORE+6*INTERLINEA+12,largBott/2,20,false);
        this.annulla = createButton("Annulla",LARGHEZZA/2+10,MARGINE_SUPERIORE+6*INTERLINEA+12,largBott/2,20,true);
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
	
	public String getNomeColazione() {
		return this.nomeValue.getText();
	}

	public String getPrezzoColazione() {
		return this.prezzoValue.getText();
	}
	
	public String getQuantitaComponente() {
		return this.quantitaValue.getText();
	}
	
	public DescrizioneComponente getDescrizioneComponente() {
		return (DescrizioneComponente)this.componenti.getSelectedItem();
	}
	
	public JTable getComponentiColazione() {
		return this.componentiAggiunti;
	}
	
	/** gestione degli eventi */
	
    protected void registerListeners() {
		this.crea.addActionListener(this);
    	this.ok.addActionListener(this);
    	this.annulla.addActionListener(this);
		this.aggiungiComponente.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        
		if (o.equals(this.crea))
			(new ComandoNuovoTipoColazione()).esegui(this);
        else if (o.equals(this.aggiungiComponente))
			(new ComandoAggiungiComponenteColazione()).esegui(this);
		else if (o.equals(this.ok))
			(new ComandoConfermaNuovoTipoColazione()).esegui(this);
		else if (o.equals(this.annulla))
			this.chiudiFinestra();
    }

    public void chiudiFinestra() {
    	this.setVisible(false);
    }
	
	/* observer */
	public void onPropertyEvent(Object source,String property,Object value) {
		if (property.equals(Evento.NUOVO_TIPO_COLAZIONE)) {
			this.crea.setEnabled(false);
			this.componenti.setEnabled(true);
			this.quantitaValue.setEnabled(true);
			this.aggiungiComponente.setEnabled(true);
			this.prezzoValue.setEnabled(true);
			this.ok.setEnabled(true);
		}
	}
	
}
