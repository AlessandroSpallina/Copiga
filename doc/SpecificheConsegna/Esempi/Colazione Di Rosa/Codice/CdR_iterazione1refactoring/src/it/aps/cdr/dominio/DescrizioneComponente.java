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
 * @version 0.1
 * 
 */
public class DescrizioneComponente {
	
	/* il nome del componente */
	private String nome; 
	/* codice identificativo della componente */
	private String codice;	
	
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
	
	
	
}
