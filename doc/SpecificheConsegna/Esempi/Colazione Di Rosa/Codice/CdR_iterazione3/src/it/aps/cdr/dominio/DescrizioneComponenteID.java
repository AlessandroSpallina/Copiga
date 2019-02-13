package it.aps.cdr.dominio;

/**
 * Un oggetto di tipo DescrizioneComponenteID rappresenta 
 * il codice identificativo per un oggetto di tipo 
 * DescrizioneComponente.
 * 
 * @see DescrizioneComponente
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri
 * @author Gabriele Rendina
 * 
 * @version 0.3
 *
 */
public class DescrizioneComponenteID {
	
	/** il codice identificativo della descrizione della componente */
	private int codiceDescrizioneComponente;
	
	/**
	 * Crea una nuova DescrizioneComponenteID
	 * @param codiceComponenteColazione il codice identificativo della componente della colazione
	 */
	public DescrizioneComponenteID(int codiceDescrizioneComponente){
		this.codiceDescrizioneComponente = codiceDescrizioneComponente;
	}
	
	/**
	 * Restituisce il codice hash per la descrizione della componente
	 * @return il codice hash per la descrizione della componente
	 */
	public int hashCode(){
		return this.codiceDescrizioneComponente;
	}
	
	/**
	 * Restituisce il codice della componente della colazione
	 * @return il codice della componente della colazione
	 */
	public int getCodiceDescrizioneComponente() {
		return this.codiceDescrizioneComponente;
	}

	/**
	 * Definisci il codice della componente della colazione
	 * @param codiceComponenteColazione il codice della componente della colazione
	 */
	public void setCodiceDescrizioneComponente(int codiceDescrizioneComponente) {
		this.codiceDescrizioneComponente = codiceDescrizioneComponente;
	}

	/**
	 * Verifica se questo identificatore della componente della colazione � 
	 * uguale a object
	 * @param object l'ID della componente della colazione 
	 * @return true se i due codici sono uguali, false altrimenti
	 */
	public boolean equals(Object object){
		return this.equals((DescrizioneComponenteID) object);
	}
	
	/**
	 * Verifica se questo identificatore della componente della colazione � 
	 * uguale a componenteColazioneID
	 * @param componenteColazioneID l'ID della componente della colazione 
	 * @return true se i due codici sono uguali, false altrimenti
	 */
	public boolean equals(DescrizioneComponenteID descrizioneComponenteID){
		return this.codiceDescrizioneComponente == descrizioneComponenteID.codiceDescrizioneComponente;
	}
	
	/**
	 * Restituisce una rappresentazione testuale di DescrizioneComponenteID
	 * @return la rappresentazione testuale di DescrizioneComponenteID
	 */
	public String toString(){
		return "" + this.codiceDescrizioneComponente;
	}

}
