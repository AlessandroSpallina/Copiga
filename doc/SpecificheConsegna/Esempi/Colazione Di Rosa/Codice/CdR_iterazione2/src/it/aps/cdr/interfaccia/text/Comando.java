package it.aps.cdr.interfaccia.text;
import it.aps.cdr.dominio.CdR;

/**
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.2
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
	
	/**
	 * Esegue il comando 
	 *
	 */
    public void esegui(CdR colazione);
}
