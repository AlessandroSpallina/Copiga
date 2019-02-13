package it.aps.cdr.interfaccia.text;

import it.aps.cdr.dominio.CdR;

/**
 * 
 * Console per la creazione di nuovo tipo di colazione.
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri
 * @author Gabriele Rendina
 * 
 * @version 0.2
 *
 */
public class NuovoTipoColazioneConsole {
	
	/**
	 * Avvia la console per il caso d'uso : "Inserimento nuovo tipo di colazione" 
	 *
	 */
	public void start(CdR colazione) {
		stampaBenvenuto();
		Comando comando = Parser.getInstance().getComando(ElencoComandi.NUOVO_TIPO_COLAZIONE);
		while (!comando.getCodiceComando().equals("0")) {
			comando.esegui(colazione);
			System.out.println();
			stampaBenvenuto();
			comando = Parser.getInstance().getComando(ElencoComandi.NUOVO_TIPO_COLAZIONE);
		}
		comando.esegui(colazione); // sicuramente è il comando esci
	}

    private void stampaBenvenuto() {
        System.out.println("INSERIMENTO NUOVO TIPO DI COLAZIONE");
		System.out.println(ElencoComandi.elencoTuttiComandi(ElencoComandi.NUOVO_TIPO_COLAZIONE));
		System.out.println("FAI LA TUA SCELTA");
	}
	
}
