package it.aps.cdr.interfaccia.text;

import it.aps.cdr.interfaccia.text.comandi.Comando;

/**
 * 
 * Console principale per la gestione dei clienti
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri
 * @author Gabriele Rendina
 * 
 * @version 0.3
 *
 */
public class ConsoleGestioneClienti {

	public void start() {
		stampaBenvenuto();
		Comando comando = Parser.getInstance().getComando(ElencoComandi.GESTIONE_CLIENTI);

		while (!comando.getCodiceComando().equals("0")) {
			comando.esegui();
			System.out.println();
			stampaBenvenuto();
			comando = Parser.getInstance().getComando(ElencoComandi.GESTIONE_CLIENTI);
		}
		comando.esegui(); // sicuramente � il comando esci
	}

    private void stampaBenvenuto() {
        System.out.println("GESTIONE CLIENTI");
		System.out.println(ElencoComandi.elencoTuttiComandi(ElencoComandi.GESTIONE_CLIENTI));
		System.out.println("FAI LA TUA SCELTA");
	}

}
