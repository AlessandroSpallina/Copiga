package it.aps.cdr.interfaccia.text;

import it.aps.cdr.interfaccia.text.comandi.Comando;

/**
 * Console per la definizione di un nuovo ordine.
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ConsoleNuovoOrdine {

	public void start() {
		stampaBenvenuto();
		Comando comando = Parser.getInstance().getComando(ElencoComandi.NUOVO_ORDINE);

		while (!comando.getCodiceComando().equals("0")) {
			comando.esegui();
			System.out.println();
			stampaBenvenuto();
			comando = Parser.getInstance().getComando(ElencoComandi.NUOVO_ORDINE);
		}
		comando.esegui(); // sicuramente � il comando esci
	}

    private void stampaBenvenuto() {
        System.out.println("NUOVO ORDINE");
		System.out.println(ElencoComandi.elencoTuttiComandi(ElencoComandi.NUOVO_ORDINE));
		System.out.println("FAI LA TUA SCELTA");
	}
	
}
