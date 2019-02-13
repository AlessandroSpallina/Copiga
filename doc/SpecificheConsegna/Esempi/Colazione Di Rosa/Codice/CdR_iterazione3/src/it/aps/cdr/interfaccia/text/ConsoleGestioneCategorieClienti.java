package it.aps.cdr.interfaccia.text;

import it.aps.cdr.interfaccia.text.comandi.Comando;

/**
 * 
 * Console principale per la gestione delle categorie di clienti
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri
 * @author Gabriele Rendina
 * 
 * @version 0.3
 *
 */
public class ConsoleGestioneCategorieClienti {

	public void start() {
		stampaBenvenuto();
		Comando comando = Parser.getInstance().getComando(ElencoComandi.GESTIONE_CATEGORIE_CLIENTI);

		while (!comando.getCodiceComando().equals("0")) {
			comando.esegui();
			System.out.println();
			stampaBenvenuto();
			comando = Parser.getInstance().getComando(ElencoComandi.GESTIONE_CATEGORIE_CLIENTI);
		}
		comando.esegui(); // sicuramente � il comando esci
	}

    private void stampaBenvenuto() {
        System.out.println("GESTIONE CATEGORIE CLIENTI");
		System.out.println(ElencoComandi.elencoTuttiComandi(ElencoComandi.GESTIONE_CATEGORIE_CLIENTI));
		System.out.println("FAI LA TUA SCELTA");
	}

}
