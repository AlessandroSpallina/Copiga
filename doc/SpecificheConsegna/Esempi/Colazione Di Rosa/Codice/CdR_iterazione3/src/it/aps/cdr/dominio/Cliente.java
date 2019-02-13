package it.aps.cdr.dominio;

import it.aps.cdr.logging.Logger;

/**
 * Un oggetto Cliente rappresenta un cliente registrato dal 
 * sistema.
 * 
 * @see ClienteID
 * @see CategoriaCliente
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class Cliente {
	
	/** codice identificativo del cliente */
	private ClienteID codice;			
	/** nome del cliente */
	private String nome;	
	/** cognome del cliente */
	private String cognome;		
	/** indirizzo del cliente e della consegna  */
	private String indirizzo;
	/** categoria di appartenenza del cliente */
	private CategoriaCliente categoriaCliente;
	/** numero di ordini effettuati dal cliente */
	private int ordiniEffettuati; 
	
	/**
	 * Crea un nuovo Cliente
	 * @param codice il codice identificativo del cliente
	 * @param nome il nome del nuovo cliente
	 * @param cognome il cognome del nuovo cliente
	 * @param indirizzo l'indirizzo del nuovo cliente
	 */
	public Cliente(ClienteID codice, String nome, String cognome, String indirizzo) {
		this(nome,cognome,indirizzo);
		this.codice = codice;
	}

	/**
	 * Crea un nuovo Cliente
	 * @param nome il nome del nuovo cliente
	 * @param cognome il cognome del nuovo cliente
	 * @param indirizzo l'indirizzo del nuovo cliente
	 */
	public Cliente(String nome, String cognome, String indirizzo){
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
		this.ordiniEffettuati = 0;
		this.categoriaCliente = null;
	}
	
	/* ************************************************************************************ */
	/* ************************************** GETTERS ************************************* */
	/* ************************************************************************************ */
	
	/**
	 * Restituisce il codice identificativo del cliente
	 * @return il codice identificativo del cliente
	 */
	public ClienteID getCodice() {
		return this.codice;
	}

	/**
	 * Restituisce il nome del cliente
	 * @return il nome del cliente 
	 */
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * Restituisce il cognome del cliente 
	 * @return il cognome del cliente
	 */
	public String getCognome() {
		return this.cognome;
	}
	
	/**
	 * Restituisce l'indirizzo del cliente
	 * @return l'indirizzo del cliente
	 */
	public String getIndirizzo() {
		return this.indirizzo;
	}
	
	/**
	 * Restituisce la categoria associata al cliente
	 * @return la categoria associata al cliente
	 */
	public CategoriaCliente getCategoriaCliente() {
		return this.categoriaCliente;
	}
	
	/**
	 * Restituisce il numero di ordini effettuati dal cliente
	 * @return il numero di ordini effettuati dal cliente
	 */
	public int getOrdiniEffettuati() {
		return this.ordiniEffettuati;
	}

	/* ************************************************************************************ */
	/* ************************************** SETTERS ************************************* */
	/* ************************************************************************************ */
	
	/**
	 * Definisce il codice identificativo del cliente
	 * @param codice il codice identificativo del cliente
	 */
	public void setCodice(ClienteID codice) {
		this.codice = codice;
	}
	
	/**
	 * Definisce il nome del cliente 
	 * @param nome il nome del cliente
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Definisce il cognome del cliente
	 * @param cognome il cognome del cliente
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	/**
	 * Definisce l'indirizzo del cliente
	 * @param indirizzo l'indirizzo del cliente
	 */
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	
	/**
	 * Definisce la categoria di appartenenza del cliente
	 * @param categoriaCliente la categoria di appartenenza del cliente
	 */
	public void setCategoriaCliente(CategoriaCliente categoriaCliente) {
		this.categoriaCliente = categoriaCliente;
	}
	
	/**
	 * Definisce il numero di ordini effettuati dal cliente
	 * @param ordiniEffettuati il numero di ordini effettuati dal cliente
	 */
	public void setOrdiniEffettuati(int ordiniEffettuati) {
		this.ordiniEffettuati = ordiniEffettuati;
	}
	
	/**
	 * Restituisce la rappresentazione testuale del cliente
	 * @return la rappresentazione testuale del cliente
	 */
	public String toString(){
		return this.nome + " " + this.cognome;
	}
	
	/**
	 * Restituisce la rappresentazione testuale del cliente
	 * @return la rappresentazione testuale del cliente
	 */
	public String stampa(){
		StringBuffer result = new StringBuffer();
		result.append("(cod:" + this.codice.getCodiceCliente() + ") " + this.nome + " " + this.cognome);
		result.append(" (cat: " + this.categoriaCliente.toString() + ") ");
		result.append("- " + this.indirizzo);
		return result.toString();
	}

}
