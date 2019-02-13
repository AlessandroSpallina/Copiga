package it.aps.cdr.interfaccia.swing;

import it.aps.cdr.interfaccia.swing.comandi.ComandoRicaricaDati;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class RicaricaDatiDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	/** costanti di utilità */
    private static final int ALTEZZA = 200;
    private static final int LARGHEZZA = 250;
    private static final int INTERLINEA = 40;
    private static final int MARGINE_SUPERIORE = 15;
    private static final int MARGINE_SINISTRO = 15;
    private static final int MARGINE_DESTRO = 15;
  
    private JFrame owner;

    private JButton ok;
    private JButton annulla;

    public RicaricaDatiDialog(JFrame frame) {
        super(frame,"ricarica dati",true);
        this.owner = frame;
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
	
    public RicaricaDatiDialog(JDialog dialog) {
        super(dialog,"ricarica dati",true);
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
        JLabel domandaCaricaDati = new JLabel("Ricaricare i dati? ");
        domandaCaricaDati.setBounds(MARGINE_SINISTRO,MARGINE_SUPERIORE,80,20);  
        		
        /** collocamento delle componenti */
        //c.add(clienteID);
		c.add(domandaCaricaDati);
        c.add(this.ok);
        c.add(this.annulla);    
    }
    
    protected void buildComponents() {
    	this.buildTextFields();
    	this.buildButtons();
    	this.registerListeners();
    }
    
    protected void buildTextFields() {
    }
	
    protected void buildButtons() {
    	int largBott = 160;    	
        this.ok = createButton("OK",LARGHEZZA/2-largBott/2-10,MARGINE_SUPERIORE+3*INTERLINEA,largBott/2,20,true);
        this.annulla = createButton("Annulla",LARGHEZZA/2+10,MARGINE_SUPERIORE+3*INTERLINEA,largBott/2,20,true);
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
		
	/** gestione degli eventi */
	
    protected void registerListeners() {
    	this.ok.addActionListener(this);
    	this.annulla.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        
		if (o.equals(this.ok))
			(new ComandoRicaricaDati()).esegui(this);
		else if (o.equals(this.annulla))
			this.chiudiFinestra();
    }

    public void chiudiFinestra() {
    	this.setVisible(false);
    }
}
