package it.aps.cdr.dominio;

/**
 * Un oggetto di tipo ClienteID rappresenta il codice identificativo 
 * per un oggetto di tipo Cliente.
 * 
 * @see Cliente 
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri
 * @author Gabriele Rendina
 * 
 * @version 0.3
 *
 */
public class ClienteID {

	/** il codice identificativo del cliente */
	private int codiceCliente;
	
	/**
	 * Crea un nuovo ClienteID
	 * @param codiceCliente il codice del cliente
	 */
	public ClienteID(int codiceCliente){
		this.codiceCliente = codiceCliente;
	}
	
	/**
	 * Restituisce il codice del cliente
	 * @return il codice del cliente
	 */
	public int getCodiceCliente(){
		return this.codiceCliente;
	}
	
	/**
	 * Definisce il codice del cliente
	 * @param codiceCliente il codice del cliente
	 */
	public void setCodiceCliente(int codiceCliente){
		this.codiceCliente = codiceCliente;
	}
	
	/**
	 * Restituisce il codice hash per il cliente
	 * @return il codice hash per il cliente
	 */
	public int hashCode(){
		return this.codiceCliente;
	}
	
	/**
	 * Verifica se questo identificatore del cliente e' 
	 * uguale a object
	 * @param object l'ID del cliente 
	 * @return true se i due codici sono uguali, false altrimenti
	 */
	public boolean equals(Object object){
		return this.equals((ClienteID) object);
	}
	
	/**
	 * Verifica se questo identificatore del cliente e' 
	 * uguale a clienteID
	 * @param clienteID l'ID del cliente 
	 * @return true se i due codici sono uguali, false altrimenti
	 */
	public boolean equals(ClienteID clienteID){
		return this.codiceCliente == clienteID.codiceCliente;
	}
	
	/**
	 * Restituisce una rappresentazione testuale del codice del cliente
	 * @return la rappresentazione testuale del codice del cliente
	 */
	public String toString(){
		return "" + this.codiceCliente;
	}
}
