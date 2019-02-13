package it.aps.cdr.interfaccia.text;

import it.aps.cdr.interfaccia.text.comandi.Comando;

/**
 * Console per la definizione di una nuova colazione all'interno di un ordine.
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ConsoleNuovaColazione {

	public void start() {
		stampaBenvenuto();
		Comando comando = Parser.getInstance().getComando(ElencoComandi.NUOVA_COLAZIONE_ORDINATA);

		while (!comando.getCodiceComando().equals("0")) {
			comando.esegui();
			System.out.println();
			stampaBenvenuto();
			comando = Parser.getInstance().getComando(ElencoComandi.NUOVA_COLAZIONE_ORDINATA);
		}
		comando.esegui(); // sicuramente � il comando esci
	}

    private void stampaBenvenuto() {
        System.out.println("NUOVA COLAZIONE ORDINATA");
		System.out.println(ElencoComandi.elencoTuttiComandi(ElencoComandi.NUOVA_COLAZIONE_ORDINATA));
		System.out.println("FAI LA TUA SCELTA");
	}

}
