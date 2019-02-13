package it.aps.cdr.interfaccia.text;

import it.aps.cdr.interfaccia.text.comandi.Comando;

/**
 * 
 * Console per la definizione di nuovo tipo di colazione.
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri
 * @author Gabriele Rendina
 * 
 * @version 0.3
 *
 */
public class ConsoleNuovoTipoColazione {
	
	/**
	 * Avvia la console per il caso d'uso : "Inserimento nuovo tipo di colazione" 
	 *
	 */
	public void start() {
		stampaBenvenuto();
		Comando comando = Parser.getInstance().getComando(ElencoComandi.NUOVO_TIPO_COLAZIONE);

		while (!comando.getCodiceComando().equals("0")) {
			comando.esegui();
			System.out.println();
			stampaBenvenuto();
			comando = Parser.getInstance().getComando(ElencoComandi.NUOVO_TIPO_COLAZIONE);
		}
		comando.esegui(); // sicuramente è il comando esci
	}

    private void stampaBenvenuto() {
        System.out.println("INSERIMENTO NUOVO TIPO DI COLAZIONE");
		System.out.println(ElencoComandi.elencoTuttiComandi(ElencoComandi.NUOVO_TIPO_COLAZIONE));
		System.out.println("FAI LA TUA SCELTA");
	}
	
}
