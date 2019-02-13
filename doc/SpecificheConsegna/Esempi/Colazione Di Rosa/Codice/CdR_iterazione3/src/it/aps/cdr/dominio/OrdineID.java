package it.aps.cdr.dominio;

/**
 * Un oggetto di tipo OrdineID rappresenta il codice identificativo 
 * per un oggetto di tipo Ordine.
 * 
 * @see Ordine
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri
 * @author Gabriele Rendina
 * 
 * @version 0.3
 *
 */
public class OrdineID {

	/** il codice identificativo dell'ordine */
	private int codiceOrdine;
	
	/**
	 * Crea un nuovo OrdineID
	 * @param codiceOrdine il codice dell'ordine
	 */
	public OrdineID(int codiceOrdine){
		this.codiceOrdine = codiceOrdine;
	}
	
	/**
	 * Restituisce il codice dell'ordine
	 * @return il codice dell'ordine
	 */
	public int getCodiceOrdine() {
		return this.codiceOrdine;
	}

	/**
	 * Definisce il codice dell'ordine
	 * @param codiceOrdine il codice dell'ordine
	 */
	public void setCodiceOrdine(int codiceOrdine) {
		this.codiceOrdine = codiceOrdine;
	}

	/**
	 * Restituisce il codice hash per l'ordine
	 * @return il codice hash per l'ordine
	 */
	public int hashCode(){
		return this.codiceOrdine;
	}
	
	/**
	 * Verifica se questo identificatore dell'ordine e' 
	 * uguale a object
	 * @param object l'ID dell'ordine 
	 * @return true se i due codici sono uguali, false altrimenti
	 */
	public boolean equals(Object object){
		return this.equals((OrdineID) object);
	}
	
	/**
	 * Verifica se questo identificatore dell'ordine e' 
	 * uguale a ordineID
	 * @param ordineID l'ID dell'ordine 
	 * @return true se i due codici sono uguali, false altrimenti
	 */
	public boolean equals(OrdineID ordineID){
		return this.codiceOrdine == ordineID.codiceOrdine;
	}
	
	/**
	 * Restituisce una rappresentazione testuale del codice dell'ordine
	 * @return la rappresentazione testuale del codice dell'ordine
	 */
	public String toString(){
		return "" + this.codiceOrdine;
	}
}
