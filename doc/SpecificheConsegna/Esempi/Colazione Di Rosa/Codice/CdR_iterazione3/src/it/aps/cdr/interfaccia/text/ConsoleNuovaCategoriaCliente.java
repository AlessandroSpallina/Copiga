package it.aps.cdr.interfaccia.text;

import it.aps.cdr.interfaccia.text.comandi.Comando;

/**
 * Console per la definizione di una nuova categoria cliente.
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ConsoleNuovaCategoriaCliente {

	public void start() {
		stampaBenvenuto();
		Comando comando = Parser.getInstance().getComando(ElencoComandi.NUOVA_CATEGORIA_CLIENTE);

		while (!comando.getCodiceComando().equals("0")) {
			comando.esegui();
			System.out.println();
			stampaBenvenuto();
			comando = Parser.getInstance().getComando(ElencoComandi.NUOVA_CATEGORIA_CLIENTE);
		}
		comando.esegui(); // sicuramente � il comando esci
	}

    private void stampaBenvenuto() {
        System.out.println("NUOVA CATEGORIA CLIENTE");
		System.out.println(ElencoComandi.elencoTuttiComandi(ElencoComandi.NUOVA_CATEGORIA_CLIENTE));
		System.out.println("FAI LA TUA SCELTA");
	}


}
