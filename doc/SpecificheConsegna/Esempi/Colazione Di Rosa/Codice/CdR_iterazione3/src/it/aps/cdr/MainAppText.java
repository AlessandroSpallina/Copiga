package it.aps.cdr;

import it.aps.cdr.dominio.*;
import it.aps.cdr.interfaccia.text.RosaConsole;

/**
 * Classe di test. 
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri
 * @author Gabriele Rendina
 * 
 * @version 0.3
 *
 */
public class MainAppText {
	
	public static void main(String[] arguments){
		/* inizializza CdR */ 
		CdR cdr = CdR.getInstance();
		/* avvia l'interfaccia utente */
		RosaConsole console = new RosaConsole();
		console.start();
	}
}
