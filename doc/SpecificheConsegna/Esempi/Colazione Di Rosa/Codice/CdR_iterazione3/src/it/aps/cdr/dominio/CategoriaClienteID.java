package it.aps.cdr.dominio;

/**
 * Un oggetto di tipo CategoriaClienteID rappresenta il codice identificativo 
 * per un oggetto di tipo CategoriaCliente.
 * 
 * @see CategoriaCliente 
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri
 * @author Gabriele Rendina
 * 
 * @version 0.3
 *
 */
public class CategoriaClienteID {

	/** il codice identificativo della categoria di cliente */
	private int codiceCategoriaCliente;	
	
	/**
	 * Crea una nuova CategoriaClienteID
	 * @param codiceCategoriaCliente il codice della categoria di cliente
	 */
	public CategoriaClienteID(int codiceCategoriaCliente){
		this.codiceCategoriaCliente = codiceCategoriaCliente;
	}

	/**
	 * Restituisce il codice della categoria di cliente
	 * @return il codice della categoria di cliente
	 */
	public int getCodiceCategoriaCliente(){
		return this.codiceCategoriaCliente;
	}
	
	/**
	 * Definisce il codice della categoria di cliente
	 * @param codiceCategoriaCliente il codice della categoria di cliente
	 */
	public void setCodiceCategoriaCliente(int codiceCategoriaCliente){
		this.codiceCategoriaCliente = codiceCategoriaCliente;
	}
	
	/**
	 * Restituisce il codice hash per la categoria di cliente
	 * @return il codice hash per la categoria di cliente
	 */
	public int hashCode(){
		return this.codiceCategoriaCliente;
	}
	
	/**
	 * Verifica se questo identificatore della categoria di cliente e' 
	 * uguale a object
	 * @param object l'ID della categoria di cliente 
	 * @return true se i due codici sono uguali, false altrimenti
	 */
	public boolean equals(Object object){
		return this.equals((CategoriaClienteID) object);
	}
	
	/**
	 * Verifica se questo identificatore della categoria di cliente � 
	 * uguale a categoriaClienteID
	 * @param categoriaClienteID l'ID della categoria di cliente
	 * @return true se i due codici sono uguali, false altrimenti
	 */
	public boolean equals(CategoriaClienteID categoriaClienteID){
		return this.codiceCategoriaCliente == categoriaClienteID.codiceCategoriaCliente;
	}
	
	/**
	 * Restituisce una rappresentazione testuale per il codice della categoria di cliente
	 * @return la rappresentazione testuale per il codice della categoria di cliente
	 */
	public String toString(){
		return "" + this.codiceCategoriaCliente;
	}
}
