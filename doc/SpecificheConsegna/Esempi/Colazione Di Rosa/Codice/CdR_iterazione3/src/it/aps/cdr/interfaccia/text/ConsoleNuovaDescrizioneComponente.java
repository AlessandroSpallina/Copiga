package it.aps.cdr.interfaccia.text;

import it.aps.cdr.interfaccia.text.comandi.Comando;

/**
 * Console per la definizione di una nuova descrizione di una componente.
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ConsoleNuovaDescrizioneComponente {

	public void start() {
		stampaBenvenuto();
		Comando comando = Parser.getInstance().getComando(ElencoComandi.NUOVA_DESCRIZIONE_COMPONENTE);

		while (!comando.getCodiceComando().equals("0")) {
			comando.esegui();
			System.out.println();
			stampaBenvenuto();
			comando = Parser.getInstance().getComando(ElencoComandi.NUOVA_DESCRIZIONE_COMPONENTE);
		}
		comando.esegui(); // sicuramente � il comando esci
	}

    private void stampaBenvenuto() {
        System.out.println("NUOVA DESCRIZIONE COMPONENTE");
		System.out.println(ElencoComandi.elencoTuttiComandi(ElencoComandi.NUOVA_DESCRIZIONE_COMPONENTE));
		System.out.println("FAI LA TUA SCELTA");
	}

}
