package it.aps.cdr.interfaccia.text;

import it.aps.cdr.interfaccia.text.comandi.Comando;

/**
 * 
 * Console principale per la gestione dei tipi di colazione
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri
 * @author Gabriele Rendina
 * 
 * @version 0.3
 *
 */
public class ConsoleGestioneTipiColazione {

	public void start() {
		stampaBenvenuto();
		Comando comando = Parser.getInstance().getComando(ElencoComandi.GESTIONE_TIPI_COLAZIONE);

		while (!comando.getCodiceComando().equals("0")) {
			comando.esegui();
			System.out.println();
			stampaBenvenuto();
			comando = Parser.getInstance().getComando(ElencoComandi.GESTIONE_TIPI_COLAZIONE);
		}
		comando.esegui(); // sicuramente � il comando esci
	}

    private void stampaBenvenuto() {
        System.out.println("GESTIONE TIPI DI COLAZIONE");
		System.out.println(ElencoComandi.elencoTuttiComandi(ElencoComandi.GESTIONE_TIPI_COLAZIONE));
		System.out.println("FAI LA TUA SCELTA");
	}

}
