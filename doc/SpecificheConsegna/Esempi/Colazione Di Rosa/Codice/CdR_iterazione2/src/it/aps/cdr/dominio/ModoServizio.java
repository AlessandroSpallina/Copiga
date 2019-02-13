package it.aps.cdr.dominio;

/**
 * Un oggetto ModoServizio rappresenta una modalità di consegna di 
 * un ordine.
 * 
 * @see Ordine
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
public class ModoServizio {
	
	private String codice;					// codice identificativo della modalità di servizio
	private String nome;					// nome della modalità di servizio
	private String descrizione;				// descrizione della modalità di servizio
	private double fattoreMoltiplicativo;	// fattore moltiplicativo per il calcolo del prezzo 
	
	/**
	 * Crea un nuovo oggetto di tipo ModoServizio
	 * 
	 * @param codice il codice identificativo della modalità di servizio
	 * @param nome il nome della modalità di servizio
	 * @param descrizione la descrizione della modalità di servizio
	 * @param fattoreMoltiplicativo il fattore di aumento di prezzo dovuto alla modalità di 
	 * 		  consegna (1.00: prezzo base; 1.25: aumento del 25%; etc.)
	 */
	public ModoServizio(String codice, String nome, String descrizione, double fattoreMoltiplicativo) {
		this.codice = codice;
		this.nome = nome;
		this.descrizione = descrizione;
		this.fattoreMoltiplicativo = fattoreMoltiplicativo;
	}
	
	/**
	 * Restituisce il fattore moltiplicativo, utilizzato per il calcolo 
	 * del prezzo di un ordine, associato alla modalità di servizio
	 * 
	 * @return il fattore moltiplicativo
	 */
	public double getFattoreMoltiplicativo() {
		return this.fattoreMoltiplicativo;
	}
	
	/**
	 * Restituisce il codice identificativo della modalità di servizio
	 * 
	 * @return il codice della modalità di servizio
	 */
	public String getCodice() {
		return this.codice;
	}
	
	/**
	 * Definisce il codice identificativo della modalità di servizio
	 * 
	 * @param codice il codice identificative della modalità di servizio
	 */
	public void setCodice(String codice) {
		this.codice = codice;
	}
	
	/**
	 * Restituisce la descrizione della modalità di servizio
	 * 
	 * @return la descrizione della modalità di servizio
	 */
	public String getDescrizione() {
		return this.descrizione;
	}
	
	/**
	 * Definisce la descrizione della modalità del servizio 
	 * 
	 * @param descrizione la descrizione della modalità di servizio
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	/**
	 * Restituisce il nome della modalità di servizio
	 * 
	 * @return il nome della modalità di servizio
	 */
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * Definisce il nome della modalità di servizio
	 * 
	 * @param nome il nome della modalità di servizio
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Definisce il fattore moltiplicativo, utilizzato per il calcolo 
	 * del prezzo di un ordine, associato alla modalità di servizio
	 * 
	 * @param fattoreMoltiplicativo il fattore moltiplicativo
	 */
	public void setFattoreMoltiplicativo(double fattoreMoltiplicativo) {
		this.fattoreMoltiplicativo = fattoreMoltiplicativo;
	}
	
	/**
	 * Restituisce una descrizione testuale della modalità di servizio
	 * 
	 * @return una descrizione testuale della modalità di servizio
	 */
	public String toString() {
		return "( " + this.codice + ", " + this.nome + " )";
	}
	
}
