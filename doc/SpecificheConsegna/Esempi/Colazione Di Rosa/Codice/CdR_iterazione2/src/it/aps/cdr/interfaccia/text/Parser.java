package it.aps.cdr.interfaccia.text;


import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Parser per la lettura da tastiera acceduto come singleton.
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.2
 * 
 */
class Parser {

    private ElencoComandi comandi;
	private static Parser singleton;
	
    public Parser() {
        comandi = new ElencoComandi();
    }

	public static Parser getInstance() {
		if (singleton==null)
			singleton=new Parser();
		return singleton;
	}

    public String read() {
        String inputLine = "";

        System.out.print(" > ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            inputLine = reader.readLine();
        }
        catch(java.io.IOException exc) {
            System.out.println ("Errore durante la lettura: " + exc.getMessage());
        }
		return inputLine;
    }
		
    public Comando getComando(int console) {
        String parola = read();
		Comando comando = null;
		
		if(comandi.comandoValido(parola,console)) {
			/* CONSOLE PRINCIPALE */
			if (console == ElencoComandi.ROSA){
				if (parola.equals("1"))
					comando = new ComandoElencaTipiColazione();
				if (parola.equals("2"))
					comando = new ComandoElencaOrdini();
				if (parola.equals("3"))
					comando = new ComandoElencaClienti();
				if (parola.equals("4"))
					comando = new ComandoNuovoTipoColazione();
				if (parola.equals("5"))
					comando = new ComandoNuovoOrdine();
			}
			/* CONSOLE NUOVO TIPO COLAZIONE */
			if (console == ElencoComandi.NUOVO_TIPO_COLAZIONE){
				if (parola.equals("1"))
					comando = new ComandoAggiungiComponenteColazione();
				if (parola.equals("2"))
					comando = new ComandoDefinisciPrezzo();
				if (parola.equals("3"))
					comando = new ComandoConfermaTipoColazione();
			}
			/* CONSOLE NUOVO ORDINE */
			if (console == ElencoComandi.NUOVO_ORDINE){
				if (parola.equals("1"))
					comando = new ComandoNuovaColazione();
//				if (parola.equals("2"))
//					comando = new ComandoDefinisciModoServizio();
				if (parola.equals("2"))
					comando = new ComandoAssociaOrdineCliente();
				if (parola.equals("3"))
					comando = new ComandoConfermaOrdine();
			}
			/* CONSOLE NUOVA COLAZIONE ORDINATA */
			if (console == ElencoComandi.NUOVA_COLAZIONE){
				if (parola.equals("1"))
					comando = new ComandoModificaCompColazioneOrdinata();
				if (parola.equals("2"))
					comando = new ComandoAggiungiCompColazioneOrdinata();
				// if (parola.equals("3"))
				// 	comando = new ComandoConfermaNuovaColazione();
				if (parola.equals("3"))
					comando = new ComandoDefinisciModoServizio();
			}
			/* TORNA AL MENU' PRECEDENTE O ESCI */
			if (parola.equals("0"))
				comando = new ComandoEsci();
	   } else comando = new ComandoNonValido();
		
       return comando;
    }
}