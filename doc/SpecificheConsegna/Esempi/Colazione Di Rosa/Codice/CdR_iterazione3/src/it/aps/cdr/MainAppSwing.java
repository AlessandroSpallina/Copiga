package it.aps.cdr;

import it.aps.cdr.dominio.*;
import it.aps.cdr.interfaccia.swing.RosaJFrame;

public class MainAppSwing {

	public static void main(String[] arguments){
		/* inizializza CdR */ 
		CdR cdr = CdR.getInstance();
		/* avvia l'interfaccia utente */ 
		RosaJFrame rosa = new RosaJFrame();
	}
}
