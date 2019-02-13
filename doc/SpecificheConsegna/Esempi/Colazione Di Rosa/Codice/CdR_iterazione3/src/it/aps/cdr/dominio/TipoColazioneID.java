package it.aps.cdr.dominio;

/**
 * Un oggetto di tipo TipoColazioneID rappresenta il codice identificativo 
 * per un oggetto di tipo TipoColazione.
 * 
 * @see TipoColazione
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri
 * @author Gabriele Rendina
 * 
 * @version 0.3
 *
 */
public class TipoColazioneID {

	/** codice identificativo del tipo di colazione */
	private int codiceTipoColazione;
	
	/**
	 * Crea un nuovo TipoColazioneID
	 * @param codiceTipoColazione il codice del tipo di colazione
	 */
	public TipoColazioneID(int codiceTipoColazione){
		this.codiceTipoColazione = codiceTipoColazione;
	}

	/**
	 * Restituisce il codice hash per il tipo di colazione 
	 * @return il codice hash per il tipo di colazione
	 */
	public int hashCode(){
		return this.codiceTipoColazione;
	}
	
	/**
	 * Restituisce il codice del tipo di colazione 
	 * @return il codice del tipo di colazione
	 */
	public int getCodiceTipoColazione() {
		return this.codiceTipoColazione;
	}
	
	/**
	 * Definisce il codice del tipo di colazione
	 * @param codiceTipoColazione il codice del tipo di colazione
	 */
	public void setCodiceTipoColazione(int codiceTipoColazione) {
		this.codiceTipoColazione = codiceTipoColazione;
	}

	/**
	 * Verifica se questo identificatore del tipo di colazione e' uguale
	 * a object
	 * @param object l'ID del tipo di colazione
	 * @return true se i due codici sono uguali, false altrimenti
	 */
	public boolean equals(Object object){
		return this.equals((TipoColazioneID) object);
	}
	
	/**
	 * Verifica se questo identificatore del tipo di colazione e' uguale
	 * a tipoColazioneID
	 * @param tipoColazioneID l'ID del tipo di colazione
	 * @return true se i due codici sono uguali, false altrimenti
	 */
	public boolean equals(TipoColazioneID tipoColazioneID){
		return this.codiceTipoColazione == tipoColazioneID.codiceTipoColazione;		
	}
	
	/**
	 * Restituisce una rappresentazione testuale di TipoColazioneID
	 * @return la rappresentazione testuale di TipoColazioneID
	 */
	public String toString(){
		return "" + this.codiceTipoColazione;
	}
}
