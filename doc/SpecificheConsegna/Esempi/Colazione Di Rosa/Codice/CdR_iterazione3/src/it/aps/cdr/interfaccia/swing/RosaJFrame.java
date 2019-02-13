package it.aps.cdr.interfaccia.swing;

import it.aps.cdr.dominio.CdR; 

import javax.swing.*;

import java.awt.*;

public class RosaJFrame extends JFrame {
	
	public static final String TITLE = "CdR " + CdR.VERSIONE; 
	
	private JPanel mainPanel;
	
	public RosaJFrame() {
		super(); 
		this.buildGUI();
	}
	
	protected void buildGUI() {
		this.setTitle(TITLE); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/* dimensioni della finestra */
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int larg = (screenSize.width/4)*3;
		int alt = (screenSize.height/4)*3;
		this.setSize(larg, alt);
		this.setLocation(screenSize.width/2-larg/2,screenSize.height/2-alt/2);
		this.setResizable(true);

		this.mainPanel = new RosaMainPanel(this);
		
		this.getContentPane().add(this.mainPanel);
		this.setVisible(true);	
	}

}
