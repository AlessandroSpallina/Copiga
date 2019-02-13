package it.aps.cdr.dominio;

/**
 * Un oggetto Cliente rappresenta un cliente registrato dal 
 * sistema.
 * 
 * @author Luca Cabibbo
 * @author Marco Canu
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.2
 * 
 */
public class Cliente {
	
	/* nome del cliente */
	private String nome;
	/* cognome del cliente */
	private String cognome; 
	/* indirizzo del cliente e della consegna */
	private String indirizzo;  
	
	/**
	 * Crea un nuovo oggetto di tipo Cliente
	 * 
	 * @param nome il nome del nuovo cliente
	 * @param cognome il cognome del nuovo cliente
	 * @param indirizzo l'indirizzo del nuovo cliente
	 */
	public Cliente(String nome, String cognome, String indirizzo) {
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
	}

	/**
	 * Restituisce il cognome del cliente 
	 * 
	 * @return il cognome del cliente
	 */
	public String getCognome() {
		return this.cognome;
	}
	
	/**
	 * Restituisce l'indirizzo del cliente
	 * 
	 * @return l'indirizzo del cliente
	 */
	public String getIndirizzo() {
		return this.indirizzo;
	}
	
	/**
	 * Restituisce il nome del cliente
	 * 
	 * @return il nome del cliente 
	 */
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * Definisce il cognome del cliente
	 * 
	 * @param cognome il cognome del cliente
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	/**
	 * Definisce l'indirizzo del cliente
	 * 
	 * @param indirizzo l'indirizzo del cliente
	 */
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	
	/**
	 * Definisce il nome del cliente 
	 * 
	 * @param nome il nome del cliente
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Restituisce una descrizione testuale del cliente
	 * 
	 * @return una descrizione testuale del cliente 
	 */
	public String toString(){
		StringBuffer result = new StringBuffer();
		result.append("   Nome :\t" + this.nome);
		result.append("\n");
		result.append("   Cognome :\t" + this.cognome);
		result.append("\n");
		result.append("   Indirizzo :\t" + this.indirizzo);
		result.append("\n");
		return result.toString();
	}
	
}
