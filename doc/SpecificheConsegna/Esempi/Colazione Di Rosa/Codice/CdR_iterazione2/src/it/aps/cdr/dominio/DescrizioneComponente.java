package it.aps.cdr.dominio;

/**
 * 
 * Un oggetto DescrizioneComponente definisce la descrizione 
 * per un oggetto ComponenteColazione
 * 
 * @author Luca Cabibbo
 * @author Marco Canu
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @see ComponenteColazione
 * 
 * @version 0.2
 * 
 */
public class DescrizioneComponente {
	
	/* il nome del componente */
	private String nome; 
	/* codice identificativo della componente */
	private String codice;	
	/* prezzo unitario aggiuntivo della componente */
	private double prezzoAggiuntivo;
	/* decremento di prezzo unitario della componente */
	private double prezzoRiduttivo;	

	/**
	 * Crea una nuova DescrizioneComponente
	 * @param codice il codice della descrizione
	 * @param nome il nome del componente
	 */
	public DescrizioneComponente(String codice, String nome){
		this.nome = nome;
		this.codice = codice;
	}
	
	/**
	 * Crea una nuova DescrizioneComponente
	 * @param nome il nome della componente
	 * @param prezzoAggiuntivo il prezzo aggiuntivo unitario della componente
	 * @param prezzoRiduttivo ilprezzo riduttivo unitario della componente
	 */
	public DescrizioneComponente(String codice, String nome, double prezzoAggiuntivo, double prezzoRiduttivo){
		this(nome, codice); 
		this.prezzoAggiuntivo = prezzoAggiuntivo;
		this.prezzoRiduttivo = prezzoRiduttivo;
	}

	/**
	 * Restituisce una rappresentazione testuale della descrizione di un componente
	 * 
	 * @return la rappresentazione testuale della descrizione di un componente
	 */
	public String toString(){
		return " " + this.codice + "-" + this.nome;
	}
	
	/**
	 * Restituisce il codice della descrizione della componente
	 * 
	 * @return il codice della componente
	 */
	public String getCodice() {
		return this.codice;
	}
	
	/**
	 * Restituisce il nome della componente
	 * 
	 * @return il nome della componente
	 */
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * Definisce il nome della componente
	 * 
	 * @param nome il nome della componente
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Definisce il codice della descrizione della componente
	 * 
	 * @param codice il codice della componente
	 */
	public void setCodice(String codice) {
		this.codice = codice;
	}
	
	/**
	 * Restituisce il prezzo della componente aggiunta ad un 
	 * tipo di colazione 
	 * @return il prezzo della componente aggiunta al tipo di colazione
	 */
	public double getPrezzoAggiuntivo() {
		return this.prezzoAggiuntivo;
	}
	
	/**
	 * Restituisce il prezzo della componente rimossa da un
	 * tipo di colazione
	 * @return il prezzo della componente rimossa dal tipo di colazione
	 */
	public double getPrezzoRiduttivo() {
		return this.prezzoRiduttivo;
	}
	
	/**
	 * Definisce il prezzo della componente aggiunta ad un  
	 * tipo di colazione
	 * @param prezzoAggiuntivo il prezzo della componente aggiunta al tipo di colazione
	 */
	public void setPrezzoAggiuntivo(double prezzoAggiuntivo) {
		this.prezzoAggiuntivo = prezzoAggiuntivo;
	}
	
	/**
	 * Definisce il prezzo della componente rimossa 
	 * dal tipo di colazione
	 * @param prezzoRiduttivo il prezzo della componente rimossa dal tipo di colazione
	 */
	public void setPrezzoRiduttivo(double prezzoRiduttivo) {
		this.prezzoRiduttivo = prezzoRiduttivo;
	}
	
	
}
