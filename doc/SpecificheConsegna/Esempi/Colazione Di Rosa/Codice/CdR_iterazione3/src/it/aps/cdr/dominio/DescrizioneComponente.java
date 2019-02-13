package it.aps.cdr.dominio;

/**
 * 
 * Un oggetto DescrizioneComponente definisce la descrizione 
 * per un oggetto ComponenteColazione
 * 
 * @see DescrizioneComponenteID
 * @see ComponenteColazione
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class DescrizioneComponente {
	
	/** nome del componente */
	private String nome;	
	/** codice identificativo della componente */
	private DescrizioneComponenteID codice;
	/** prezzo unitario della componente */
	private Prezzo prezzoAggiuntivo;
	/** decremento di prezzo unitario della componente */
	private Prezzo prezzoRiduttivo;	
	
	/**
	 * Crea una nuova DescrizioneComponente
	 * @param codice il codice identificativo della componente
	 * @param nome il nome della componente
	 * @param prezzoAggiuntivo il prezzo aggiuntivo unitario della componente
	 * @param prezzoRiduttivo ilprezzo riduttivo unitario della componente
	 */
	public DescrizioneComponente(DescrizioneComponenteID codice, String nome, Prezzo prezzoAggiuntivo, Prezzo prezzoRiduttivo){
		this(nome,prezzoAggiuntivo,prezzoRiduttivo);
		this.codice = codice;
	}
	
	/**
	 * Crea una nuova DescrizioneComponente
	 * @param nome il nome della componente
	 * @param prezzoAggiuntivo il prezzo aggiuntivo unitario della componente
	 * @param prezzoRiduttivo ilprezzo riduttivo unitario della componente
	 */
	public DescrizioneComponente(String nome, Prezzo prezzoAggiuntivo, Prezzo prezzoRiduttivo){
		this.nome = nome;
		this.prezzoAggiuntivo = prezzoAggiuntivo;
		this.prezzoRiduttivo = prezzoRiduttivo;
	}
	
	/* ************************************************************************************ */
	/* ************************************** GETTERS ************************************* */
	/* ************************************************************************************ */
	
	/**
	 * Restituisce il codice della descrizione della componente
	 * @return il codice della componente
	 */
	public DescrizioneComponenteID getCodice() {
		return this.codice;
	}
	
	/**
	 * Restituisce il nome della componente
	 * @return il nome della componente
	 */
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * Restituisce il prezzo della componente aggiunta ad un 
	 * tipo di colazione 
	 * @return il prezzo della componente aggiunta al tipo di colazione
	 */
	public Prezzo getPrezzoAggiuntivo() {
		return this.prezzoAggiuntivo;
	}
	
	/**
	 * Restituisce il prezzo della componente rimossa da un
	 * tipo di colazione
	 * @return il prezzo della componente rimossa dal tipo di colazione
	 */
	public Prezzo getPrezzoRiduttivo() {
		return this.prezzoRiduttivo;
	}
	
	/* ************************************************************************************ */
	/* ************************************** SETTERS ************************************* */
	/* ************************************************************************************ */
	
	/**
	 * Definisce il codice della descrizione della componente
	 * @param codice il codice della componente
	 */
	public void setCodice(DescrizioneComponenteID codice) {
		this.codice = codice;
	}
	
	/**
	 * Definisce il nome della componente
	 * @param nome il nome della componente
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Definisce il prezzo della componente aggiunta ad un  
	 * tipo di colazione
	 * @param prezzoAggiuntivo il prezzo della componente aggiunta al tipo di colazione
	 */
	public void setPrezzoAggiuntivo(Prezzo prezzoAggiuntivo) {
		this.prezzoAggiuntivo = prezzoAggiuntivo;
	}
	
	/**
	 * Definisce il prezzo della componente rimossa 
	 * dal tipo di colazione
	 * @param prezzoRiduttivo il prezzo della componente rimossa dal tipo di colazione
	 */
	public void setPrezzoRiduttivo(Prezzo prezzoRiduttivo) {
		this.prezzoRiduttivo = prezzoRiduttivo;
	}
	
	/**
	 * Restituisce una rappresentazione testuale della descrizione di un componente
	 * @return la rappresentazione testuale della descrizione di una componente
	 */
	public String toString(){
		return this.nome;
	}
	
	/**
	 * Restituisce una rappresentazione testuale della descrizione di un componente
	 * @return la rappresentazione testuale della descrizione di una componente
	 */
	public String stampa() {
		return "(cod:" + this.codice.toString() + ") " + this.nome;
	}

}
