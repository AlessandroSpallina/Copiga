package it.aps.cdr.dominio;

/**
 * Un oggetto ColazioneOrdinata rappresenta una colazione ordinata 
 * in relazione ad un ordine, costituita da un tipo di colazione 
 * standard e da possibili variazioni.
 * 
 * @see TipoColazione
 * 
 * @author Luca Cabibbo
 * @author Marco Canu
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.1
 * 
 */
public class ColazioneOrdinata {

	/* il tipo di colazione al quale è associata la colazione */
	private TipoColazione tipoColazione; 
	
	/**
	 * Crea una nuova ColazioneOrdinata
	 * 
	 * @param tc il tipo di colazione
	 */
	public ColazioneOrdinata(TipoColazione tc) {
		this.tipoColazione = tc;
	}
	
	/**
	 * Calcola il prezzo di questa colazione ordinata. 
	 * 
	 * @return il prezzo di questa colazione 
	 */
	public double calcolaPrezzo() {
		return this.tipoColazione.getPrezzo();
	}
	
	/**
	 * Restituisce il tipo di colazione 
	 * 
	 * @return il tipo di colazione 
	 */
	public TipoColazione getTipoColazione() {
		return this.tipoColazione;
	}
	
	/**
	 * Definisce il tipo di colazione a cui è associata questa 
	 * colazione ordinata 
	 * 
	 * @param tipoColazione
	 */
	public void setTipoColazione(TipoColazione tipoColazione) {
		this.tipoColazione = tipoColazione;
	}
	
}
