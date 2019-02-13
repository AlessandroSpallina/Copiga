package it.aps.cdr.interfaccia.text;

import it.aps.cdr.interfaccia.text.comandi.Comando;

/**
 * 
 * Console principale per la gestione degli ordini
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri
 * @author Gabriele Rendina
 * 
 * @version 0.3
 *
 */
public class ConsoleGestioneOrdini {

	public void start() {
		stampaBenvenuto();
		Comando comando = Parser.getInstance().getComando(ElencoComandi.GESTIONE_ORDINI);

		while (!comando.getCodiceComando().equals("0")) {
			comando.esegui();
			System.out.println();
			stampaBenvenuto();
			comando = Parser.getInstance().getComando(ElencoComandi.GESTIONE_ORDINI);
		}
		comando.esegui(); // sicuramente è il comando esci
	}

    private void stampaBenvenuto() {
        System.out.println("GESTIONE ORDINI");
		System.out.println(ElencoComandi.elencoTuttiComandi(ElencoComandi.GESTIONE_ORDINI));
		System.out.println("FAI LA TUA SCELTA");
	}
	
}
