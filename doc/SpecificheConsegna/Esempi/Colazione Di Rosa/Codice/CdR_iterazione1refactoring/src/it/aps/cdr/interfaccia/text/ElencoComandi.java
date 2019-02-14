package it.aps.cdr.interfaccia.text;

/**
 * Elenca i comandi validi delle console 
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.1
 * 
 */
class ElencoComandi {
    
	public static final int ROSA = 0;
	public static final int NUOVO_TIPO_COLAZIONE = 1;
	public static final int NUOVO_ORDINE = 2;
	public static final int NUOVA_COLAZIONE = 3;
	
	/* MENU' PRINCIPALE */
    private static final String comandiValidiRosaConsole[][] = {
		{ComandoElencaTipiColazione.codiceComando,ComandoElencaTipiColazione.descrizioneComando},
		{ComandoNuovoTipoColazione.codiceComando,ComandoNuovoTipoColazione.descrizioneComando},
		{ComandoNuovoOrdine.codiceComando,ComandoNuovoOrdine.descrizioneComando},
		{ComandoEsci.codiceComando, ComandoEsci.descrizioneComando}
    };
	
	/* USE CASE 1 : INSERIMENTO NUOVO TIPO DI COLAZIONE */
	private static final String comandiValidiNuovoTipoColazioneConsole[][] = {
		{ComandoAggiungiComponenteColazione.codiceComando, ComandoAggiungiComponenteColazione.descrizioneComando},
		{ComandoDefinisciPrezzo.codiceComando, ComandoDefinisciPrezzo.descrizioneComando},
		{ComandoConfermaTipoColazione.codiceComando, ComandoConfermaTipoColazione.descrizioneComando},
		{ComandoEsci.codiceComando, ComandoEsci.descrizioneComando}
	};
	
	/* USE CASE 2 : NUOVO ORDINE */
	private static final String comandiValidiNuovoOrdineConsole[][] = {
		{ComandoNuovaColazione.codiceComando,ComandoNuovaColazione.descrizioneComando},
		{ComandoDefinisciModoServizio.codiceComando,ComandoDefinisciModoServizio.descrizioneComando},
		{ComandoAssociaOrdineCliente.codiceComando,ComandoAssociaOrdineCliente.descrizioneComando},
		{ComandoConfermaOrdine.codiceComando,ComandoConfermaOrdine.descrizioneComando},
		{ComandoEsci.codiceComando, ComandoEsci.descrizioneComando}
	};
	
   /* USE CASE 2 : NUOVA COLAZIONE */
	private static final String comandiValidiNuovaColazioneConsole[][] = {
		{ComandoEsci.codiceComando,ComandoEsci.descrizioneComando}
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
			case ROSA: comandi = comandiValidiRosaConsole; break;
			case NUOVO_TIPO_COLAZIONE: comandi = comandiValidiNuovoTipoColazioneConsole; break;
			case NUOVO_ORDINE: comandi = comandiValidiNuovoOrdineConsole; break;
			case NUOVA_COLAZIONE: comandi = comandiValidiNuovaColazioneConsole; break;
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