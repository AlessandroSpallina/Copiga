package it.aps.cdr.dominio;

/**
 * Un oggetto di tipo CategoriaCliente definisce la categoria 
 * per un oggetto di tipo Cliente
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
public class CategoriaCliente {
	
	/** codice identificativo della categoria del cliente */
	private CategoriaClienteID codice;
	/** nome della categoria */
	private String nome;
	/** descrizione della categoria */
	private String descrizione;	
	/** fattore moltiplicativo */
	private double fattoreCliente;
	/** limite superiore di ordini della categoria */
	private int ordini;
	
	
	/**
	 * Crea una nuova CategoriaCliente
	 * @param codice il codice identificativo della categoria
	 * @param nome il nome della categoria
	 * @param descrizione la descrizione della categoria
	 * @param fattoreCliente il fattore moltiplicativo per il calcolo del prezzo
	 * @param ordini il numero massimo di ordini della categoria
	 */
	public CategoriaCliente(CategoriaClienteID codice, String nome, String descrizione, double fattoreCliente, int ordini){
		this(nome,descrizione,fattoreCliente,ordini);
		this.codice = codice;
	}
	
	/**
	 * Crea una nuova CategoriaCliente
	 * @param nome il nome della categoria
	 * @param descrizione la descrizione della categoria
	 * @param fattoreCliente il fattore moltiplicativo per il calcolo del prezzo
	 * @param ordini il numero massimo di ordini della categoria
	 */
	public CategoriaCliente(String nome, String descrizione, double fattoreCliente, int ordini){
		this.nome = nome;
		this.descrizione = descrizione;
		this.fattoreCliente = fattoreCliente;
		this.ordini = ordini; 
	}
	
	/* ************************************************************************************ */
	/* ************************************** GETTERS ************************************* */
	/* ************************************************************************************ */
	
	/**
	 * Restituisce il codice della categoria
	 * @return il codice della categoria
	 */
	public CategoriaClienteID getCodice() {
		return this.codice;
	}

	/**
	 * Restituisce il nome identificativo della categoria
	 * @return il nome della categoria
	 */
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * Restituisce la descrizione della categoria
	 * @return la descrizione della categoria
	 */
	public String getDescrizione() {
		return this.descrizione;
	}
	
	/**
	 * Restituisce il fattore moltiplicativo della categoria, utilizzato 
	 * per il calcolo del prezzo di un'ordinazione
	 * @return il fattore moltiplicativo 
	 */
	public double getFattoreCliente() {
		return this.fattoreCliente;
	}
	
	/**
	 * Restituisce il massimo numero di ordini della categoria
	 * @return il massimo numero di ordini della categoria
	 */
	public int getOrdini() {
		return this.ordini;
	}
	
	/* ************************************************************************************ */
	/* ************************************** SETTERS ************************************* */
	/* ************************************************************************************ */
	
	/**
	 * Definisce il codice della categoria
	 * @param codice il codice della categoria
	 */
	public void setCodice(CategoriaClienteID codice) {
		this.codice = codice;
	}
	
	/**
	 * Definisce il nome della categoria
	 * @param nome il nome della categoria
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Definisce la descrizione della categoria 
	 * @param descrizione la descrizione della categoria
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	/**
	 * Definisce il fattore moltiplicativo della categoria, utilizzato 
	 * per il calcolo del prezzo di un'ordinazione
	 * @param fattoreCliente il fattore moltiplicativo
	 */
	public void setFattoreCliente(double fattoreCliente) {
		this.fattoreCliente = fattoreCliente;
	}
	
	/**
	 * Definisce il massimo numero di ordini della categoria
	 * @param ordini il massimo numero di ordini della categoria
	 */
	public void setOrdini(int ordini) {
		this.ordini = ordini;
	}
	
	/**
	 * Restituisce una rappresentazione testuale della categoria di cliente
	 * @return la rappresentazione testuale della categoria di cliente
	 */
	public String toString(){
		return this.nome;
	}
	
	/**
	 * Restituisce una rappresentazione testuale della categoria di cliente
	 * @return la rappresentazione testuale della categoria di cliente
	 */
	public String stampa(){
		return "(cod:" + this.codice.toString() + ") " + this.nome;
	}
	
}
