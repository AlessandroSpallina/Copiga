package it.aps.cdr.interfaccia.text;


import it.aps.cdr.interfaccia.text.comandi.Comando;
import it.aps.cdr.interfaccia.text.comandi.ComandoAggiornaCategoriaCliente;
import it.aps.cdr.interfaccia.text.comandi.ComandoAggiornaCliente;
import it.aps.cdr.interfaccia.text.comandi.ComandoAggiornaDescrizioneComponente;
import it.aps.cdr.interfaccia.text.comandi.ComandoAggiornaModoServizio;
import it.aps.cdr.interfaccia.text.comandi.ComandoAggiornaOrdine;
import it.aps.cdr.interfaccia.text.comandi.ComandoAggiornaTipoColazione;
import it.aps.cdr.interfaccia.text.comandi.ComandoAggiungiCompColazioneOrdinata;
import it.aps.cdr.interfaccia.text.comandi.ComandoAggiungiComponenteColazione;
import it.aps.cdr.interfaccia.text.comandi.ComandoCancellaCategoriaCliente;
import it.aps.cdr.interfaccia.text.comandi.ComandoCancellaCliente;
import it.aps.cdr.interfaccia.text.comandi.ComandoCancellaDescrizioneComponente;
import it.aps.cdr.interfaccia.text.comandi.ComandoCancellaModoServizio;
import it.aps.cdr.interfaccia.text.comandi.ComandoCancellaOrdine;
import it.aps.cdr.interfaccia.text.comandi.ComandoCancellaTipoColazione;
import it.aps.cdr.interfaccia.text.comandi.ComandoChiudiOrdine;
import it.aps.cdr.interfaccia.text.comandi.ComandoCompletaOrdine;
import it.aps.cdr.interfaccia.text.comandi.ComandoConfermaDescrizioneComponente;
import it.aps.cdr.interfaccia.text.comandi.ComandoConfermaNuovaCategoriaCliente;
import it.aps.cdr.interfaccia.text.comandi.ComandoConfermaNuovaColazione;
import it.aps.cdr.interfaccia.text.comandi.ComandoConfermaNuovoCliente;
import it.aps.cdr.interfaccia.text.comandi.ComandoConfermaNuovoModoServizio;
import it.aps.cdr.interfaccia.text.comandi.ComandoConfermaOrdine;
import it.aps.cdr.interfaccia.text.comandi.ComandoConfermaTipoColazione;
import it.aps.cdr.interfaccia.text.comandi.ComandoDefinisciModoServizio;
import it.aps.cdr.interfaccia.text.comandi.ComandoDefinisciPrezzo;
import it.aps.cdr.interfaccia.text.comandi.ComandoElencaCategorieClienti;
import it.aps.cdr.interfaccia.text.comandi.ComandoElencaClienti;
import it.aps.cdr.interfaccia.text.comandi.ComandoElencaDescrizioniComponenti;
import it.aps.cdr.interfaccia.text.comandi.ComandoElencaModiServizio;
import it.aps.cdr.interfaccia.text.comandi.ComandoElencaOrdini;
import it.aps.cdr.interfaccia.text.comandi.ComandoElencaTipiColazione;
import it.aps.cdr.interfaccia.text.comandi.ComandoEsci;
import it.aps.cdr.interfaccia.text.comandi.ComandoGestioneCategorieClienti;
import it.aps.cdr.interfaccia.text.comandi.ComandoGestioneClienti;
import it.aps.cdr.interfaccia.text.comandi.ComandoGestioneDescrizioniComponenti;
import it.aps.cdr.interfaccia.text.comandi.ComandoGestioneModiServizio;
import it.aps.cdr.interfaccia.text.comandi.ComandoGestioneOrdini;
import it.aps.cdr.interfaccia.text.comandi.ComandoGestioneTipiColazione;
import it.aps.cdr.interfaccia.text.comandi.ComandoModificaCompColazioneOrdinata;
import it.aps.cdr.interfaccia.text.comandi.ComandoNonValido;
import it.aps.cdr.interfaccia.text.comandi.ComandoNuovaCategoriaCliente;
import it.aps.cdr.interfaccia.text.comandi.ComandoNuovaColazioneOrdinata;
import it.aps.cdr.interfaccia.text.comandi.ComandoNuovaDescrizioneComponente;
import it.aps.cdr.interfaccia.text.comandi.ComandoNuovoCliente;
import it.aps.cdr.interfaccia.text.comandi.ComandoNuovoModoServizio;
import it.aps.cdr.interfaccia.text.comandi.ComandoNuovoOrdine;
import it.aps.cdr.interfaccia.text.comandi.ComandoNuovoTipoColazione;
import it.aps.cdr.interfaccia.text.comandi.ComandoTrovaCategoriaCliente;
import it.aps.cdr.interfaccia.text.comandi.ComandoTrovaCliente;
import it.aps.cdr.interfaccia.text.comandi.ComandoTrovaDescrizioneComponente;
import it.aps.cdr.interfaccia.text.comandi.ComandoTrovaModoServizio;
import it.aps.cdr.interfaccia.text.comandi.ComandoTrovaOrdine;
import it.aps.cdr.interfaccia.text.comandi.ComandoTrovaTipoColazione;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Parser per la lettura da tastiera acceduto come singleton.
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class Parser {

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
			if (console == ElencoComandi.ROSA) {
				if (parola.equals("1"))
					comando = new ComandoGestioneTipiColazione();
				if (parola.equals("2"))
					comando = new ComandoGestioneDescrizioniComponenti();
				if (parola.equals("3"))
					comando = new ComandoGestioneOrdini();
				if (parola.equals("4"))
					comando = new ComandoGestioneClienti();
				if (parola.equals("5"))
					comando = new ComandoGestioneCategorieClienti();
				if (parola.equals("6"))
					comando = new ComandoGestioneModiServizio();
			}			
			
			if (console == ElencoComandi.GESTIONE_TIPI_COLAZIONE) {
				if (parola.equals("1"))
					comando = new ComandoNuovoTipoColazione();
				if (parola.equals("2"))
					comando = new ComandoTrovaTipoColazione();
				if (parola.equals("3"))
					comando = new ComandoElencaTipiColazione();
				if (parola.equals("4"))
					comando = new ComandoAggiornaTipoColazione();
				if (parola.equals("5"))
					comando = new ComandoCancellaTipoColazione();
			}
			
			if (console == ElencoComandi.NUOVO_TIPO_COLAZIONE) {
				if (parola.equals("1"))
					comando = new ComandoAggiungiComponenteColazione();
				if (parola.equals("2"))
					comando = new ComandoDefinisciPrezzo();
				if (parola.equals("3"))
					comando = new ComandoConfermaTipoColazione();
			}
			
			if (console == ElencoComandi.GESTIONE_DESCRIZIONI_COMPONENTI) {
				if (parola.equals("1"))
					comando = new ComandoNuovaDescrizioneComponente();
				if (parola.equals("2"))
					comando = new ComandoTrovaDescrizioneComponente();
				if (parola.equals("3"))
					comando = new ComandoElencaDescrizioniComponenti();
				if (parola.equals("4"))
					comando = new ComandoAggiornaDescrizioneComponente();
				if (parola.equals("5"))
					comando = new ComandoCancellaDescrizioneComponente();
			}
			
			if (console == ElencoComandi.NUOVA_DESCRIZIONE_COMPONENTE) {
				if (parola.equals("1"))
					comando = new ComandoConfermaDescrizioneComponente();
			}
			
			if (console == ElencoComandi.GESTIONE_ORDINI) {
				if (parola.equals("1"))
					comando = new ComandoNuovoOrdine();
				if (parola.equals("2"))
					comando = new ComandoTrovaOrdine();
				if (parola.equals("3"))
					comando = new ComandoElencaOrdini();
				if (parola.equals("4"))
					comando = new ComandoAggiornaOrdine();
				if (parola.equals("5"))
					comando = new ComandoCancellaOrdine();
				if (parola.equals("6")) {
					comando = new ComandoChiudiOrdine();
				}
			}
			
			if (console == ElencoComandi.NUOVO_ORDINE) {
				if (parola.equals("1"))
					comando = new ComandoNuovaColazioneOrdinata();
				if (parola.equals("2"))
					comando = new ComandoCompletaOrdine();
				if (parola.equals("3"))
					comando = new ComandoConfermaOrdine();
			}
			
			if (console == ElencoComandi.NUOVA_COLAZIONE_ORDINATA) {
				if (parola.equals("1"))
					comando = new ComandoModificaCompColazioneOrdinata();
				if (parola.equals("2"))
					comando = new ComandoAggiungiCompColazioneOrdinata();
				if (parola.equals("3"))
					comando = new ComandoDefinisciModoServizio();
				if (parola.equals("4"))
					comando = new ComandoConfermaNuovaColazione();
			}
			
			if (console == ElencoComandi.GESTIONE_CLIENTI) {
				if (parola.equals("1"))
					comando = new ComandoNuovoCliente();
				if (parola.equals("2"))
					comando = new ComandoTrovaCliente();
				if (parola.equals("3"))
					comando = new ComandoElencaClienti();
				if (parola.equals("4"))
					comando = new ComandoAggiornaCliente();
				if (parola.equals("5"))
					comando = new ComandoCancellaCliente();
			}
			
			if (console == ElencoComandi.NUOVO_CLIENTE) {
				if (parola.equals("1"))
					comando = new ComandoConfermaNuovoCliente();
			}
			
			if (console == ElencoComandi.GESTIONE_CATEGORIE_CLIENTI) {
				if (parola.equals("1"))
					comando = new ComandoNuovaCategoriaCliente();
				if (parola.equals("2"))
					comando = new ComandoTrovaCategoriaCliente();
				if (parola.equals("3"))
					comando = new ComandoElencaCategorieClienti();
				if (parola.equals("4"))
					comando = new ComandoAggiornaCategoriaCliente();
				if (parola.equals("5"))
					comando = new ComandoCancellaCategoriaCliente();
			}
			
			if (console == ElencoComandi.NUOVA_CATEGORIA_CLIENTE) {
				if (parola.equals("1"))
					comando = new ComandoConfermaNuovaCategoriaCliente();
			}

			if (console == ElencoComandi.GESTIONE_MODO_SERVIZIO) {
				if (parola.equals("1"))
					comando = new ComandoNuovoModoServizio();
				if (parola.equals("2"))
					comando = new ComandoTrovaModoServizio();
				if (parola.equals("3"))
					comando = new ComandoElencaModiServizio();
				if (parola.equals("4"))
					comando = new ComandoAggiornaModoServizio();
				if (parola.equals("5"))
					comando = new ComandoCancellaModoServizio();
			}
			
			if (console == ElencoComandi.NUOVO_MODO_SERVIZIO) {
				if (parola.equals("1"))
					comando = new ComandoConfermaNuovoModoServizio();
			}			
			
			/* TORNA AL MENU' PRECEDENTE O ESCI */
			if (parola.equals("0"))
				comando = new ComandoEsci();
	   } else comando = new ComandoNonValido();
		
       return comando;
    }
}
