package it.aps.cdr.interfaccia.text;

import it.aps.cdr.interfaccia.text.comandi.Comando;

/**
 * 
 * Console principale per la gestione delle modalita' di servizio
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri
 * @author Gabriele Rendina
 * 
 * @version 0.3
 *
 */
public class ConsoleGestioneModiServizio {

	public void start() {
		stampaBenvenuto();
		Comando comando = Parser.getInstance().getComando(ElencoComandi.GESTIONE_MODO_SERVIZIO);

		while (!comando.getCodiceComando().equals("0")) {
			comando.esegui();
			System.out.println();
			stampaBenvenuto();
			comando = Parser.getInstance().getComando(ElencoComandi.GESTIONE_MODO_SERVIZIO);
		}
		comando.esegui(); // sicuramente e' il comando esci
	}

    private void stampaBenvenuto() {
        System.out.println("GESTIONE MODALITA' DI SERVIZIO");
		System.out.println(ElencoComandi.elencoTuttiComandi(ElencoComandi.GESTIONE_MODO_SERVIZIO));
		System.out.println("FAI LA TUA SCELTA");
	}

}
