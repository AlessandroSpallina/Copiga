package it.aps.cdr.interfaccia.text;

import it.aps.cdr.dominio.CdR;
/**
 * Console testuale per La colazione di Rosa.
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.1
 * 
 */
public class RosaConsole {
    

	public void start() {
		CdR cdr = CdR.getInstance();
		stampaBenvenuto();
		Comando comando = Parser.getInstance().getComando(ElencoComandi.ROSA);
		
		while (!comando.getCodiceComando().equals("0")) {
			comando.esegui(cdr);
			System.out.println();
			stampaBenvenuto();
			comando = Parser.getInstance().getComando(ElencoComandi.ROSA);
		}
		comando.esegui(cdr); // sicuramente è il comando esci
		System.out.println("   BYE...");
	}

    private void stampaBenvenuto() {
        System.out.println("LA COLAZIONE DI ROSA");
		System.out.println(ElencoComandi.elencoTuttiComandi(ElencoComandi.ROSA));
		System.out.println("FAI LA TUA SCELTA");
	}
	
}
