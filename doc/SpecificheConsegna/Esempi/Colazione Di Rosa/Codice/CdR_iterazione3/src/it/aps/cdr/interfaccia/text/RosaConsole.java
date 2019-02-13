package it.aps.cdr.interfaccia.text;

import it.aps.cdr.dominio.CdR; 
import it.aps.cdr.interfaccia.text.comandi.Comando;

/**
 * Console principale del programma.
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class RosaConsole {
    
	public void start() {
		stampaBenvenuto();
		Comando comando = Parser.getInstance().getComando(ElencoComandi.ROSA);

		while (!comando.getCodiceComando().equals("0")) {
			comando.esegui();
			System.out.println();
			stampaBenvenuto();
			comando = Parser.getInstance().getComando(ElencoComandi.ROSA);
		}
		comando.esegui(); // sicuramente è il comando esci
		System.out.println("   BYE...");
	}

    private void stampaBenvenuto() {
        System.out.println("LA COLAZIONE DI ROSA (" + CdR.VERSIONE + ")");
		System.out.println(ElencoComandi.elencoTuttiComandi(ElencoComandi.ROSA));
		System.out.println("FAI LA TUA SCELTA");
	}

}
