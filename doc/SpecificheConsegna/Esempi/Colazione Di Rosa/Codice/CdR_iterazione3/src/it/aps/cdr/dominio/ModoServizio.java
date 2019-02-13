package it.aps.cdr.dominio;

import java.text.DecimalFormat;

/**
 * Un oggetto ModoServizio rappresenta una modalità di consegna di 
 * un ordine.
 * 
 * @see ModoServizioID
 * @see ColazioneOrdinata
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ModoServizio {
	
	/** codice identificativo della modalita' di servizio */
	private ModoServizioID codice;
	/** nome della modalita' di servizio */
	private String nome;
	/** descrizione della modalita' di servizio */
	private String descrizione;	
	/** fattore moltiplicativo per il calcolo del prezzo */
	private double fattoreMoltiplicativo;
	
	/**
	 * Crea un nuovo ModoServizio
	 * @param codice il codice identificativo della modalita' di servizio
	 * @param nome il nome della modalita' di servizio
	 * @param descrizione la descrizione della modalita' di servizio
	 * @param fattoreMoltiplicativo il fattore di aumento di prezzo dovuto alla modalita' di 
	 * 		  consegna (1.00: prezzo base; 1.25: aumento del 25%; etc.)
	 */
	public ModoServizio(ModoServizioID codice, String nome, String descrizione, double fattoreMoltiplicativo) {
		this(nome,descrizione,fattoreMoltiplicativo);
		this.codice = codice;
	}
	
	/**
	 * Crea un nuovo ModoServizio
	 * @param nome il nome della modalita' di servizio
	 * @param descrizione la descrizione della modalita' di servizio
	 * @param fattoreMoltiplicativo il fattore di aumento di prezzo dovuto alla modalita' di 
	 * 		  consegna (1.00: prezzo base; 1.25: aumento del 25%; etc.)
	 */
	public ModoServizio(String nome, String descrizione, double fattoreMoltiplicativo){
		this.nome = nome;
		this.descrizione = descrizione;
		this.fattoreMoltiplicativo = fattoreMoltiplicativo;
	}
	
	/* ************************************************************************************ */
	/* ************************************** GETTERS ************************************* */
	/* ************************************************************************************ */
	
	/**
	 * Restituisce il codice identificativo della modalita' di servizio
	 * @return il codice della modalita' di servizio
	 */
	public ModoServizioID getCodice() {
		return this.codice;
	}
	
	/**
	 * Restituisce il nome della modalita' di servizio
	 * @return il nome della modalita' di servizio
	 */
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * Restituisce il fattore moltiplicativo, utilizzato per il calcolo 
	 * del prezzo di un ordine, associato alla modalita' di servizio
	 * @return il fattore moltiplicativo
	 */
	public double getFattoreMoltiplicativo() {
		return this.fattoreMoltiplicativo;
	}
	
	/**
	 * Restituisce la descrizione della modalita' di servizio
	 * @return la descrizione della modalita' di servizio
	 */
	public String getDescrizione() {
		return this.descrizione;
	}
	
	/* ************************************************************************************ */
	/* ************************************** SETTERS ************************************* */
	/* ************************************************************************************ */
	
	/**
	 * Definisce il codice identificativo della modalita' di servizio
	 * @param codice il codice identificative della modalita' di servizio
	 */
	public void setCodice(ModoServizioID codice) {
		this.codice = codice;
	}
	
	/**
	 * Definisce il nome della modalita' di servizio
	 * @param nome il nome della modalita' di servizio
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Definisce la descrizione della modalita' del servizio 
	 * @param descrizione la descrizione della modalita' di servizio
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	/**
	 * Definisce il fattore moltiplicativo, utilizzato per il calcolo 
	 * del prezzo di un ordine, associato alla modalita' di servizio
	 * @param fattoreMoltiplicativo il fattore moltiplicativo
	 */
	public void setFattoreMoltiplicativo(double fattoreMoltiplicativo) {
		this.fattoreMoltiplicativo = fattoreMoltiplicativo;
	}
	
	/**
	 * Restituisce una rappresentazione testuale della modalita' di servizio
	 * @return la rappresentazione testuale della componente della modalita' di servizio
	 */
	public String toString(){
		return this.nome;
	}
	
	/**
	 * Restituisce una rappresentazione testuale della modalita' di servizio
	 * @return la rappresentazione testuale della componente della modalita' di servizio
	 */
	public String stampa(){
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		StringBuffer result = new StringBuffer();
		result.append("(cod:" + this.codice + ") ");
		result.append(this.nome);
		result.append(" (" + this.descrizione + ")");
		result.append(" - " + df.format(this.fattoreMoltiplicativo));
		return result.toString();
	}
	
}
