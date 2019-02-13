package it.aps.cdr.interfaccia.text;

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

/**
 * Elenca i comandi validi delle console 
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
class ElencoComandi {
    
	public static final int ROSA = 0;
	
	public static final int GESTIONE_TIPI_COLAZIONE = 10;
	public static final int NUOVO_TIPO_COLAZIONE = 	11;
	
	public static final int GESTIONE_DESCRIZIONI_COMPONENTI = 20;
	public static final int NUOVA_DESCRIZIONE_COMPONENTE = 21;
	
	public static final int GESTIONE_ORDINI = 30;
	public static final int NUOVO_ORDINE = 31;
	public static final int NUOVA_COLAZIONE_ORDINATA = 32;
	
	public static final int GESTIONE_CLIENTI = 40;
	public static final int NUOVO_CLIENTE = 41;
	
	public static final int GESTIONE_CATEGORIE_CLIENTI = 50;
	public static final int NUOVA_CATEGORIA_CLIENTE = 51;
	
	public static final int GESTIONE_MODO_SERVIZIO = 60;
	public static final int NUOVO_MODO_SERVIZIO = 61;
	
	
	/* MENU' PRINCIPALE */
    private static final String comandiValidiConsoleRosa[][] = {
		{ComandoGestioneTipiColazione.codiceComando,ComandoGestioneTipiColazione.descrizioneComando},
		{ComandoGestioneDescrizioniComponenti.codiceComando,ComandoGestioneDescrizioniComponenti.descrizioneComando},
		{ComandoGestioneOrdini.codiceComando,ComandoGestioneOrdini.descrizioneComando},
		{ComandoGestioneClienti.codiceComando,ComandoGestioneClienti.descrizioneComando},
		{ComandoGestioneCategorieClienti.codiceComando,ComandoGestioneCategorieClienti.descrizioneComando},
		{ComandoGestioneModiServizio.codiceComando,ComandoGestioneModiServizio.descrizioneComando},		
		{ComandoEsci.codiceComando, ComandoEsci.descrizioneComando}
    };
	
	/* GESTIONE DEI TIPI DI COLAZIONE */
	private static final String comandiValidiConsoleGestioneTipiColazione[][] = {
		{ComandoNuovoTipoColazione.codiceComando,ComandoNuovoTipoColazione.descrizioneComando},
		{ComandoTrovaTipoColazione.codiceComando,ComandoTrovaTipoColazione.descrizioneComando},
		{ComandoElencaTipiColazione.codiceComando,ComandoElencaTipiColazione.descrizioneComando},
		{ComandoAggiornaTipoColazione.codiceComando,ComandoAggiornaTipoColazione.descrizioneComando},
		{ComandoCancellaTipoColazione.codiceComando,ComandoCancellaTipoColazione.descrizioneComando},
		{ComandoEsci.codiceComando, ComandoEsci.descrizioneComando}
	};
	
	/* USE CASE 1 : INSERIMENTO NUOVO TIPO DI COLAZIONE */
	private static final String comandiValidiConsoleNuovoTipoColazione[][] = {
		{ComandoAggiungiComponenteColazione.codiceComando, ComandoAggiungiComponenteColazione.descrizioneComando},
		{ComandoDefinisciPrezzo.codiceComando, ComandoDefinisciPrezzo.descrizioneComando},
		{ComandoConfermaTipoColazione.codiceComando, ComandoConfermaTipoColazione.descrizioneComando},
		{ComandoEsci.codiceComando, ComandoEsci.descrizioneComando}
	};
	
	/* GESTIONE DELLE DESCRIZIONI DELLE COMPONENTI */
	private static final String comandiValidiConsoleGestioneDescrizioniComponenti[][] = {
		{ComandoNuovaDescrizioneComponente.codiceComando,ComandoNuovaDescrizioneComponente.descrizioneComando},
		{ComandoTrovaDescrizioneComponente.codiceComando,ComandoTrovaDescrizioneComponente.descrizioneComando},
		{ComandoElencaDescrizioniComponenti.codiceComando,ComandoElencaDescrizioniComponenti.descrizioneComando},
		{ComandoAggiornaDescrizioneComponente.codiceComando,ComandoAggiornaDescrizioneComponente.descrizioneComando},
		{ComandoCancellaDescrizioneComponente.codiceComando,ComandoCancellaDescrizioneComponente.descrizioneComando},
		{ComandoEsci.codiceComando, ComandoEsci.descrizioneComando}
	};
	
	/* NUOVA DESCRIZIONE COMPONENTE */
	private static final String comandiValidiConsoleNuovaDescrizioneComponente[][] = {
		{ComandoConfermaDescrizioneComponente.codiceComando,ComandoConfermaDescrizioneComponente.descrizioneComando},
		{ComandoEsci.codiceComando, ComandoEsci.descrizioneComando}
	};
	
	/* GESTIONE DEGLI ORDINI */
	private static final String comandiValidiConsoleGestioneOrdini[][] = {
		{ComandoNuovoOrdine.codiceComando,ComandoNuovoOrdine.descrizioneComando},
		{ComandoTrovaOrdine.codiceComando,ComandoTrovaOrdine.descrizioneComando},
		{ComandoElencaOrdini.codiceComando,ComandoElencaOrdini.descrizioneComando},
		{ComandoAggiornaOrdine.codiceComando,ComandoAggiornaOrdine.descrizioneComando},
		{ComandoCancellaOrdine.codiceComando,ComandoCancellaOrdine.descrizioneComando},
		{ComandoChiudiOrdine.codiceComando,ComandoChiudiOrdine.descrizioneComando},
		{ComandoEsci.codiceComando, ComandoEsci.descrizioneComando}
	};
	
	/* USE CASE 2 : NUOVO ORDINE */
	private static final String comandiValidiConsoleNuovoOrdine[][] = {
		{ComandoNuovaColazioneOrdinata.codiceComando,ComandoNuovaColazioneOrdinata.descrizioneComando},
		{ComandoCompletaOrdine.codiceComando,ComandoCompletaOrdine.descrizioneComando},
		{ComandoConfermaOrdine.codiceComando,ComandoConfermaOrdine.descrizioneComando},
		{ComandoEsci.codiceComando, ComandoEsci.descrizioneComando}
	};
		
   /* USE CASE 2 : NUOVA COLAZIONE ORDINATA */
	private static final String comandiValidiConsoleNuovaColazioneOrdinata[][] = {
		{ComandoModificaCompColazioneOrdinata.codiceComando,ComandoModificaCompColazioneOrdinata.descrizioneComando},
		{ComandoAggiungiCompColazioneOrdinata.codiceComando,ComandoAggiungiCompColazioneOrdinata.descrizioneComando},
		{ComandoDefinisciModoServizio.codiceComando,ComandoDefinisciModoServizio.descrizioneComando},
		{ComandoConfermaNuovaColazione.codiceComando,ComandoConfermaNuovaColazione.descrizioneComando},
		{ComandoEsci.codiceComando,ComandoEsci.descrizioneComando}
	};

	/* GESTIONE DEI CLIENTI */
	private static final String comandiValidiConsoleGestioneClienti[][] = {
		{ComandoNuovoCliente.codiceComando,ComandoNuovoCliente.descrizioneComando},
		{ComandoTrovaCliente.codiceComando,ComandoTrovaCliente.descrizioneComando},
		{ComandoElencaClienti.codiceComando,ComandoElencaClienti.descrizioneComando},
		{ComandoAggiornaCliente.codiceComando,ComandoAggiornaCliente.descrizioneComando},
		{ComandoCancellaCliente.codiceComando,ComandoCancellaCliente.descrizioneComando},
		{ComandoEsci.codiceComando, ComandoEsci.descrizioneComando}
	};
	
	/* NUOVO CLIENTE */
	private static final String comandiValidiConsoleNuovoCliente[][] = {
		{ComandoConfermaNuovoCliente.codiceComando,ComandoConfermaNuovoCliente.descrizioneComando},
		{ComandoEsci.codiceComando, ComandoEsci.descrizioneComando}
	};
	
	/* GESTIONE DELE CATEGORIE DI CLIENTI */
	private static final String comandiValidiConsoleGestioneCategorieClienti[][] = {
		{ComandoNuovaCategoriaCliente.codiceComando,ComandoNuovaCategoriaCliente.descrizioneComando},
		{ComandoTrovaCategoriaCliente.codiceComando,ComandoTrovaCategoriaCliente.descrizioneComando},
		{ComandoElencaCategorieClienti.codiceComando,ComandoElencaCategorieClienti.descrizioneComando},
		{ComandoAggiornaCategoriaCliente.codiceComando,ComandoAggiornaCategoriaCliente.descrizioneComando},
		{ComandoCancellaCategoriaCliente.codiceComando,ComandoCancellaCategoriaCliente.descrizioneComando},
		{ComandoEsci.codiceComando, ComandoEsci.descrizioneComando}
	};
	
	/* NUOVA CATEGORIA DI CLIENTE */
	private static final String comandiValidiConsoleNuovaCategoriaCliente[][] = {
		{ComandoConfermaNuovaCategoriaCliente.codiceComando,ComandoConfermaNuovaCategoriaCliente.descrizioneComando},
		{ComandoEsci.codiceComando, ComandoEsci.descrizioneComando}
	};
	
	/* GESTIONE DELLE MODALITA' DI SERVIZIO */
	private static final String comandiValidiConsoleGestioneModoServizio[][] = {
		{ComandoNuovoModoServizio.codiceComando,ComandoNuovoModoServizio.descrizioneComando},
		{ComandoTrovaModoServizio.codiceComando,ComandoTrovaModoServizio.descrizioneComando},
		{ComandoElencaModiServizio.codiceComando,ComandoElencaModiServizio.descrizioneComando},
		{ComandoAggiornaModoServizio.codiceComando,ComandoAggiornaModoServizio.descrizioneComando},
		{ComandoCancellaModoServizio.codiceComando,ComandoCancellaModoServizio.descrizioneComando},
		{ComandoEsci.codiceComando, ComandoEsci.descrizioneComando}
	};
	
	/* NUOVO MODO SERVIZIO */
	private static final String comandiValidiConsoleNuovoModoServizio[][] = {
		{ComandoConfermaNuovoModoServizio.codiceComando,ComandoConfermaNuovoModoServizio.descrizioneComando},
		{ComandoEsci.codiceComando, ComandoEsci.descrizioneComando}
	};
	
   public static String elencoTuttiComandi(int console){
    	int i=0;
    	StringBuffer elenco = new StringBuffer();
		String comandi[][]=getComandi(console);
		
		
    	for (i=0; i<comandi.length-1; i++) {
			elenco.append(comando(i,console));
			elenco.append("\n");
		}
		elenco.append(comando(i,console));
		return elenco.toString();
    }
	
	private static String comando(int i, int console) {
		String comandi[][]= getComandi(console);
		return " " + comandi[i][0] + ")" + comandi[i][1];
	}

	public static String[][] getComandi(int console){
		
		String comandi[][]=null;
		
		switch (console){
		
			case ROSA: comandi = comandiValidiConsoleRosa; break;
			
			case GESTIONE_TIPI_COLAZIONE: comandi = comandiValidiConsoleGestioneTipiColazione; break;
			case NUOVO_TIPO_COLAZIONE: comandi = comandiValidiConsoleNuovoTipoColazione; break;
			
			case GESTIONE_DESCRIZIONI_COMPONENTI: comandi = comandiValidiConsoleGestioneDescrizioniComponenti; break;
			case NUOVA_DESCRIZIONE_COMPONENTE: comandi = comandiValidiConsoleNuovaDescrizioneComponente; break;
			
			case GESTIONE_ORDINI: comandi = comandiValidiConsoleGestioneOrdini; break;
			case NUOVO_ORDINE: comandi = comandiValidiConsoleNuovoOrdine; break;
			case NUOVA_COLAZIONE_ORDINATA: comandi = comandiValidiConsoleNuovaColazioneOrdinata; break;
			
			case GESTIONE_CLIENTI: comandi = comandiValidiConsoleGestioneClienti; break;
			case NUOVO_CLIENTE: comandi = comandiValidiConsoleNuovoCliente; break;
			
			case GESTIONE_CATEGORIE_CLIENTI: comandi = comandiValidiConsoleGestioneCategorieClienti; break;
			case NUOVA_CATEGORIA_CLIENTE: comandi = comandiValidiConsoleNuovaCategoriaCliente; break;
			
			case GESTIONE_MODO_SERVIZIO: comandi = comandiValidiConsoleGestioneModoServizio; break;
			case NUOVO_MODO_SERVIZIO: comandi = comandiValidiConsoleNuovoModoServizio; break;
			
		};
		
		return comandi;
	}
	
    public boolean comandoValido(String stringa, int console) {
		String comandi[][]= getComandi(console);
		for(int i = 0; i < comandi.length; i++) {
            if(comandi[i][0].equals(stringa))
                return true;
        }
        return false;
    }
	
}
