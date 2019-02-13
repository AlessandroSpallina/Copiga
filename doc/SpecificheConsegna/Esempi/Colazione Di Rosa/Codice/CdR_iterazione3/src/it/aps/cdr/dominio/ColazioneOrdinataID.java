package it.aps.cdr.dominio;

/**
 * Un oggetto di tipo ColazioneOrdinataID rappresenta il codice identificativo 
 * per un oggetto di tipo ColazioneOrdinata.
 * 
 * @see ColazioneOrdinata 
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri
 * @author Gabriele Rendina
 * 
 * @version 0.3
 *
 */
public class ColazioneOrdinataID {
	
	/** il codice identificativo della colazione ordinata */
	private int codiceColazioneOrdinata;
	
	/**
	 * Crea un nuovo ColazioneOrdinataID
	 * @param codiceColazioneOrdinata il codice della colazione ordinata
	 */
	public ColazioneOrdinataID(int codiceColazioneOrdinata) {
		this.codiceColazioneOrdinata = codiceColazioneOrdinata;
	}
	
	/**
	 * Restituisce il codice della colazione ordinata
	 * @return il codice della colazione ordinata
	 */
	public int getCodiceColazioneOrdinata() {
		return this.codiceColazioneOrdinata;
	}
	
	/**
	 * Definisce il codice della colazione ordinata
	 * @param codiceColazioneOrdinata il codice della colazione ordinata
	 */
	public void setCodiceColazioneOrdinata(int codiceColazioneOrdinata) {
		this.codiceColazioneOrdinata = codiceColazioneOrdinata;
	}
	
	/**
	 * Restituisce il codice hash per la colazione ordinata
	 * @return il codice hash per la colazione ordinata
	 */
	public int hashCode() {
		return this.codiceColazioneOrdinata;
	}
	
	/**
	 * Verifica se questo identificatore della colazione ordinata e' 
	 * uguale a object
	 * @param object l'ID della colazione ordinata 
	 * @return true se i due codici sono uguali, false altrimenti
	 */
	public boolean equals(Object object) {
		return this.equals((ColazioneOrdinataID) object);
	}
	
	/**
	 * Verifica se questo identificatore della colazione ordinata e' 
	 * uguale a colazioneOrdinataID
	 * @param colazioneOrdinataID l'ID della colazione ordinata 
	 * @return true se i due codici sono uguali, false altrimenti
	 */
	public boolean equals(ColazioneOrdinataID colazioneOrdinataID) {
		return this.codiceColazioneOrdinata == colazioneOrdinataID.getCodiceColazioneOrdinata();
	}
	
	/**
	 * Restituisce una rappresentazione testuale per il codice della colazione ordinata
	 * @return la rappresentazione testuale per il codice della colazione ordinata
	 */ 
	public String toString() {
		return "" + this.codiceColazioneOrdinata;
	}
}
