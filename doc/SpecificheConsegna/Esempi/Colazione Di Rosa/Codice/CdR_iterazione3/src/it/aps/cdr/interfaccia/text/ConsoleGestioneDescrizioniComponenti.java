package it.aps.cdr.interfaccia.text;

import it.aps.cdr.interfaccia.text.comandi.Comando;

/**
 * 
 * Console principale per la gestione delle descrizioni delle componenti
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri
 * @author Gabriele Rendina
 * 
 * @version 0.3
 *
 */
public class ConsoleGestioneDescrizioniComponenti {

	public void start() {
		stampaBenvenuto();
		Comando comando = Parser.getInstance().getComando(ElencoComandi.GESTIONE_DESCRIZIONI_COMPONENTI);

		while (!comando.getCodiceComando().equals("0")) {
			comando.esegui();
			System.out.println();
			stampaBenvenuto();
			comando = Parser.getInstance().getComando(ElencoComandi.GESTIONE_DESCRIZIONI_COMPONENTI);
		}
		comando.esegui(); // sicuramente � il comando esci
	}

    private void stampaBenvenuto() {
        System.out.println("GESTIONE DESCRIZIONI COMPONENTI");
		System.out.println(ElencoComandi.elencoTuttiComandi(ElencoComandi.GESTIONE_DESCRIZIONI_COMPONENTI));
		System.out.println("FAI LA TUA SCELTA");
	}

}
