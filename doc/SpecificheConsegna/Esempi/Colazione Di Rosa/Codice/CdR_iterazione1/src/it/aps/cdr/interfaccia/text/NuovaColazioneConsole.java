package it.aps.cdr.interfaccia.text;

import it.aps.cdr.dominio.CdR;

/**
 * Console per la creazione di una nuova colazione all'interno di un ordine.
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.1
 * 
 */
public class NuovaColazioneConsole {

	public void start(CdR colazione) {
		stampaBenvenuto();
		Comando comando = Parser.getInstance().getComando(ElencoComandi.NUOVA_COLAZIONE);
		while (!comando.getCodiceComando().equals("0")) {
			comando.esegui(colazione);
			System.out.println();
			stampaBenvenuto();
			comando = Parser.getInstance().getComando(ElencoComandi.NUOVA_COLAZIONE);
		}
		comando.esegui(colazione); // sicuramente è il comando esci
	}

    private void stampaBenvenuto() {
        System.out.println("NUOVA COLAZIONE");
		System.out.println(ElencoComandi.elencoTuttiComandi(ElencoComandi.NUOVA_COLAZIONE));
		System.out.println("FAI LA TUA SCELTA");
	}

}
