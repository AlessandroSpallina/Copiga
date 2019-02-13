package it.aps.cdr.dominio;

/**
 * Un oggetto di tipo ModoServizioID rappresenta il codice identificativo 
 * per un oggetto di tipo ModoServizio.
 * 
 * @see ModoServizio 
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri
 * @author Gabriele Rendina
 * 
 * @version 0.3
 *
 */
public class ModoServizioID {
	
	/** codice identificativo della modalita' di servizio */
	private int codiceModoServizio;
	
	/**
	 * Crea un nuovo ModoServizioID
	 * @param codiceModoServizio il codice della modalita' di servizio
	 */
	public ModoServizioID(int codiceModoServizio){
		this.codiceModoServizio = codiceModoServizio;
	}

	/**
	 * Restituisce il codice hash per la modalita' di servizio
	 * @return il codice hash per la modalita' di sevizio 
	 */
	public int hashCode(){
		return this.codiceModoServizio;
	}
	
	/**
	 * Restituisce il codice della modalita' di servizio 
	 * @return il codice della modalita' di servizio 
	 */
	public int getCodiceModoServizio() {
		return this.codiceModoServizio;
	}
	
	/**
	 * Definisce il codice della modalita' di servizio
	 * @param codiceModoServizio il codice della modalita' di servizio 
	 */
	public void setCodiceModoServizio(int codiceModoServizio) {
		this.codiceModoServizio = codiceModoServizio;
	}
	
	/**
	 * Verifica se questo identificatore della modalita' di servizio e' 
	 * uguale a object
	 * @param object l'ID della modalita' di servizio 
	 * @return true se i due codici sono uguali, false altrimenti
	 */
	public boolean equals(Object object){
		return this.equals((ModoServizioID) object);
	}
	
	/**
	 * Verifica se questo identificatore della modalita' di servizio e' 
	 * uguale a modoServizioID
	 * @param modoServizioID l'ID della modalita' di servizio 
	 * @return true se i due codici sono uguali, false altrimenti
	 */
	public boolean equals(ModoServizioID modoServizioID){
		return this.codiceModoServizio == modoServizioID.codiceModoServizio;
	}
	
	/**
	 * Restituisce una rappresentazione testuale del codice della modalita' 
	 * di servizio 
	 * @return la rappresentazione testuale del codice della modalita' 
	 * di servizio 
	 */
	public String toString(){
		return "" + this.codiceModoServizio;
	}

}
