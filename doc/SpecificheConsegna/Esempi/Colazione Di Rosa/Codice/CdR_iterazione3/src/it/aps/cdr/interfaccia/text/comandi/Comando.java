package it.aps.cdr.interfaccia.text.comandi;

/**
 * Interfaccia dei comandi 
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public interface Comando {

	/**
	 * Restituisce il codice del comendo
	 * @return il codice del comando
	 */
    public String getCodiceComando();
	
	/**
	 * Restituisce la descrizione del comando
	 * @return la descrizione del comando
	 */
	public String getDescrizioneComando();
	
	/** Esegue il comando */
    public void esegui();
}
