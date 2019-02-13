package it.aps.cdr.interfaccia.text;

import it.aps.cdr.dominio.CdR;

/**
 * Console per la creazione di un nuovo ordine.
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.2
 * 
 */
public class NuovoOrdineConsole {

	public void start(CdR colazione) {
		stampaBenvenuto();
		Comando comando = Parser.getInstance().getComando(ElencoComandi.NUOVO_ORDINE);
		while (!comando.getCodiceComando().equals("0")) {
			comando.esegui(colazione);
			System.out.println();
			System.out.println("Il prezzo dell'ordine è " + colazione.calcolaPrezzoOrdine());
			stampaBenvenuto();
			comando = Parser.getInstance().getComando(ElencoComandi.NUOVO_ORDINE);
		}
		comando.esegui(colazione); // sicuramente è il comando esci
	}

    private void stampaBenvenuto() {
        System.out.println("NUOVO ORDINE");
		System.out.println(ElencoComandi.elencoTuttiComandi(ElencoComandi.NUOVO_ORDINE));
		System.out.println("FAI LA TUA SCELTA");
	}
	
}
