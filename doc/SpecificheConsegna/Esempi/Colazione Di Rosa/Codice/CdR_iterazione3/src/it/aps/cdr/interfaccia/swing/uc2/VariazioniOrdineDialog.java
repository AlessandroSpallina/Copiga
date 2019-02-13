package it.aps.cdr.interfaccia.swing.uc2;

import it.aps.cdr.dominio.CalcolatorePrezzo;
import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.ColazioneOrdinata;
import it.aps.cdr.dominio.ComponenteColazione;
import it.aps.cdr.dominio.DescrizioneComponente;
import it.aps.cdr.dominio.ModoServizio;
import it.aps.cdr.dominio.Prezzo;
import it.aps.cdr.dominio.TipoColazione;
import it.aps.cdr.eventi.Evento;
import it.aps.cdr.eventi.PropertyListener;
import it.aps.cdr.interfaccia.swing.uc2.comandi.ComandoAggiungiComponente;
import it.aps.cdr.interfaccia.swing.uc2.comandi.ComandoConfermaModoServizio;
import it.aps.cdr.interfaccia.swing.uc2.comandi.ComandoConfermaNuovaColazioneOrdinata;
import it.aps.cdr.interfaccia.swing.uc2.comandi.ComandoModificaComponente;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class VariazioniOrdineDialog extends JDialog implements PropertyListener, ActionListener {

	private static final long serialVersionUID = 1L;
	
	/** costanti di utilità */
    private static final int ALTEZZA = 670;
    private static final int LARGHEZZA = 450;
    private static final int INTERLINEA = 40;
    private static final int MARGINE_SUPERIORE = 15;
    private static final int MARGINE_SINISTRO = 15;
    private static final int MARGINE_DESTRO = 15;
  
    private JDialog owner;
	private JComboBox componentiModifica;
	private JComboBox componentiAggiungi;
	private JComboBox modiServizio;
	private JTextField qtaModifica;
	private JTextField qtaAggiunta;
    private JButton ok;
    private JButton annulla;
	private JButton confermaModifica;
	private JButton confermaAggiungi;
	private JButton confermaModoServizio;
	private JTable componentiColazione;
	private JTable variazioniColazione;
	private JLabel prezzoColazioneOrdinata;
		
    public VariazioniOrdineDialog(JDialog dialog) {
        super(dialog,"dettagli colazione ordinata",true);
        this.owner = dialog;
		CdR.getInstance().getOrdineCorrente().getColazioneOrdinataCorrente().addPropertyListener(this);
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
		String tipoColazione = CdR.getInstance().getOrdineCorrente().
			getColazioneOrdinataCorrente().getTipoColazione().getNome();
        JLabel colazione = new JLabel("Componenti colazione - " + tipoColazione + ":");
        colazione.setBounds(MARGINE_SINISTRO,MARGINE_SUPERIORE,LARGHEZZA,20);		
		JLabel variazioni = new JLabel("Variazioni:");
		variazioni.setBounds(MARGINE_SINISTRO,2*MARGINE_SUPERIORE+5*INTERLINEA,LARGHEZZA,20);
		JLabel modifica = new JLabel("Modifica:");
		modifica.setBounds(MARGINE_SINISTRO,3*MARGINE_SUPERIORE+10*INTERLINEA,LARGHEZZA,20);
		JLabel aggiungi = new JLabel("Aggiungi:");
		aggiungi.setBounds(MARGINE_SINISTRO,3*MARGINE_SUPERIORE+11*INTERLINEA,LARGHEZZA,20);
		JLabel qtaM = new JLabel("QTA");
		qtaM.setBounds(MARGINE_SINISTRO+35+LARGHEZZA/3+70,3*MARGINE_SUPERIORE+10*INTERLINEA,30,20);
		JLabel qtaV = new JLabel("QTA");
		qtaV.setBounds(MARGINE_SINISTRO+35+LARGHEZZA/3+70,3*MARGINE_SUPERIORE+11*INTERLINEA,30,20);
		JLabel modoServizio = new JLabel("Modalita' di servizio:");
		modoServizio.setBounds(MARGINE_SINISTRO,3*MARGINE_SUPERIORE+12*INTERLINEA,LARGHEZZA,20);
		
		/** etichetta col prezzo */
        this.prezzoColazioneOrdinata = new JLabel("PREZZO:  -  €");
		this.prezzoColazioneOrdinata.setBounds(MARGINE_SINISTRO,2*MARGINE_SUPERIORE+13*INTERLINEA+15,LARGHEZZA,20);
		
		/** lista delle componenti della colazione */
		JScrollPane listScroller1 = new JScrollPane(this.componentiColazione);
		listScroller1.setBounds(MARGINE_SINISTRO,MARGINE_SUPERIORE+INTERLINEA,LARGHEZZA-MARGINE_SINISTRO-MARGINE_DESTRO-10,4*INTERLINEA); 
		listScroller1.setVisible(true);
		
		/** lista delle variazioni sulla colazione */
		JScrollPane listScroller2 = new JScrollPane(this.variazioniColazione);
		listScroller2.setBounds(MARGINE_SINISTRO,2*MARGINE_SUPERIORE+6*INTERLINEA,LARGHEZZA-MARGINE_SINISTRO-MARGINE_DESTRO-10,4*INTERLINEA); 
		listScroller2.setVisible(true);
		
        /** collocamento delle componenti */
		c.add(colazione); 
		c.add(listScroller1);
		c.add(variazioni);
		c.add(listScroller2);
		c.add(modifica);
		c.add(this.componentiModifica);
		c.add(this.qtaModifica);
		c.add(qtaM);
		c.add(this.confermaModifica);
		c.add(aggiungi);
		c.add(this.componentiAggiungi);
		c.add(this.qtaAggiunta);
		c.add(qtaV);
		c.add(this.confermaAggiungi);
		c.add(modoServizio);
		c.add(this.modiServizio);
		c.add(this.confermaModoServizio);
		c.add(this.ok);
        c.add(this.annulla);   
		c.add(this.prezzoColazioneOrdinata); 
    }
    
    protected void buildComponents() {
    	this.buildTextFields();
		this.buildComboBoxes();
		this.buildTables();
    	this.buildButtons();
    	this.registerListeners();
    }
    
    protected void buildTextFields() {	
		this.qtaModifica = createTextField(MARGINE_SINISTRO+70+LARGHEZZA/3+70,3*MARGINE_SUPERIORE+10*INTERLINEA,40,20,true);
		this.qtaAggiunta = createTextField(MARGINE_SINISTRO+70+LARGHEZZA/3+70,3*MARGINE_SUPERIORE+11*INTERLINEA,40,20,true);
    }
	
	protected void buildComboBoxes() {
		this.componentiModifica = new JComboBox();
        Collection<ComponenteColazione> c2 = 
			CdR.getInstance().getOrdineCorrente().getColazioneOrdinataCorrente().getTipoColazione().getComponenti();
		for (ComponenteColazione cc : c2)
            this.componentiModifica.addItem(cc.getDescrizioneComponente());
		this.componentiModifica.setBounds(MARGINE_SINISTRO+70,3*MARGINE_SUPERIORE+10*INTERLINEA,LARGHEZZA/3+20,20);
		this.componentiModifica.setEnabled(true);		
		
		this.componentiAggiungi = new JComboBox();
		Collection<DescrizioneComponente> c1 = this.getDescrizioniComponentiDiff();
			//CdR.getInstance().getCatalogoComponenti().getDescrizioniComponenti();
		for (DescrizioneComponente dc : c1)
            this.componentiAggiungi.addItem(dc);
		this.componentiAggiungi.setBounds(MARGINE_SINISTRO+70,3*MARGINE_SUPERIORE+11*INTERLINEA,LARGHEZZA/3+20,20);
		this.componentiAggiungi.setEnabled(true);
		
		this.modiServizio = new JComboBox();
        Collection<ModoServizio> c3 = 
			CdR.getInstance().getModiServizio();
		for (ModoServizio ms : c3)
            this.modiServizio.addItem(ms);
		this.modiServizio.setBounds(MARGINE_SINISTRO+160,3*MARGINE_SUPERIORE+12*INTERLINEA,LARGHEZZA/3+20,20);
		this.modiServizio.setEnabled(true);
	}
	
	private Collection<DescrizioneComponente> getDescrizioniComponentiDiff() {
		Collection<DescrizioneComponente> diff = 
			new LinkedList<DescrizioneComponente>(CdR.getInstance().getMenu().getDescrizioniComponenti());
		Collection<ComponenteColazione> daeliminare = 
			CdR.getInstance().getOrdineCorrente().getColazioneOrdinataCorrente().getTipoColazione().getComponenti();
		Iterator<DescrizioneComponente> it = diff.iterator();
		while (it.hasNext()) {
			DescrizioneComponente temp = it.next();
			if (this.contains(daeliminare,temp))
				it.remove();
		}	
		return diff;
	}
	
	private boolean contains(Collection<ComponenteColazione> componenti,DescrizioneComponente dc) {
		boolean result = false;
		for (ComponenteColazione cc : componenti) {
			if (cc.getDescrizioneComponente().getCodice().equals(dc.getCodice()))
				result = true;
		}
		return result;
	}
	
	protected void buildTables() {
		TipoColazione tc = CdR.getInstance().getOrdineCorrente().getColazioneOrdinataCorrente().getTipoColazione();
		this.componentiColazione = new ComponentiColazioneTable(this,new ComponentiColazioneTableModel(tc));
		this.variazioniColazione = new VariazioniColazioneTable(this,new VariazioniColazioneTableModel(null));
	}
	
    protected void buildButtons() {
    	int largBott = 160;    	
		this.confermaModoServizio = createButton("ok",LARGHEZZA+10-(largBott/3+40),3*MARGINE_SUPERIORE+12*INTERLINEA,largBott/3,20,true);
        this.confermaModifica = createButton("ok",LARGHEZZA+10-(largBott/3+40),3*MARGINE_SUPERIORE+10*INTERLINEA,largBott/3,20,true);
		this.confermaAggiungi = createButton("ok",LARGHEZZA+10-(largBott/3+40),3*MARGINE_SUPERIORE+11*INTERLINEA,largBott/3,20,true);
		this.ok = createButton("OK",LARGHEZZA/2-largBott/2-10,4*MARGINE_SUPERIORE+13*INTERLINEA+30,largBott/2,20,false);
        this.annulla = createButton("Annulla",LARGHEZZA/2+10,4*MARGINE_SUPERIORE+13*INTERLINEA+30,largBott/2,20,true);
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
	
	public DescrizioneComponente getComponenteModifica() {
		return (DescrizioneComponente)this.componentiModifica.getSelectedItem();
	}
	
	public String getQuantitaModifica() {
		return this.qtaModifica.getText();
	}
	
	public DescrizioneComponente getComponenteAggiunta() {
		return (DescrizioneComponente)this.componentiAggiungi.getSelectedItem();
	}
	
	public String getQuantitaAggiunta() {
		return this.qtaAggiunta.getText();
	}
	
	public ModoServizio getModoServizio() {
		return (ModoServizio)this.modiServizio.getSelectedItem();
	}
	
	/** gestione degli eventi */
	
    protected void registerListeners() {
		this.confermaModifica.addActionListener(this);
		this.confermaAggiungi.addActionListener(this);
		this.confermaModoServizio.addActionListener(this);
    	this.ok.addActionListener(this);
    	this.annulla.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();  
		if (o.equals(this.confermaAggiungi))
			(new ComandoAggiungiComponente()).esegui(this);
        else if (o.equals(this.confermaModifica))
			(new ComandoModificaComponente()).esegui(this);
        else if (o.equals(this.confermaModoServizio))
			(new ComandoConfermaModoServizio()).esegui(this);
		else if (o.equals(this.ok))
			(new ComandoConfermaNuovaColazioneOrdinata()).esegui(this);
		else if (o.equals(this.annulla))
			this.chiudiFinestra();
    }

    public void chiudiFinestra() {
    	this.setVisible(false);
    }
	
	/* observer */
	public void onPropertyEvent(Object source,String property,Object value) {
		if (property.equals(Evento.DEFINISCI_MODO_SERVIZIO) || property.equals(Evento.VARIAZIONE_COMPONENTE_COLAZIONE)) {
			ColazioneOrdinata co = (ColazioneOrdinata)source;
			Prezzo totale = CalcolatorePrezzo.getInstance().calcolaPrezzoColazione(co);
			DecimalFormat df = new DecimalFormat();
			df.setMaximumFractionDigits(2);
			this.prezzoColazioneOrdinata.setText("PREZZO:  " + df.format(totale.getImporto()) + "  €");
			if (property.equals(Evento.DEFINISCI_MODO_SERVIZIO))
				this.ok.setEnabled(true);
		} 
    }
	
}
