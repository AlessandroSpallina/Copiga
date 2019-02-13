package it.aps.cdr.dominio;

/**
 * 
 * Un oggetto ComponenteColazione rappresenta una componente 
 * di un oggetto TipoColazione, con la relativa quantità.
 * 
 * @author Luca Cabibbo
 * @author Marco Canu
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @see DescrizioneComponente
 * 
 * @version 0.2
 * 
 */
public class ComponenteColazione {
	
	/* descrizione della component */
	private DescrizioneComponente descrizioneComponente;
	/* quantità del componente */
	private int quantita;
	
	/**
	 * Crea una nuova ComponenteColazione
	 * 
	 * @param dc la descrizione del componente
	 * @param quantita la quantità desiderata del componente
	 */
	public ComponenteColazione(DescrizioneComponente dc, int quantita) {
		this.descrizioneComponente = dc;
		this.quantita = quantita;
	}
	
	/**
	 * Restituisce una rappresentazione testuale della componente della colazione
	 */
	public String toString(){
		return this.descrizioneComponente.toString() + " x " + this.quantita;
	}
	
	/**
	 * Restituisce la descrizione della componente 
	 * @return la descrizione della componente 
	 */
	public DescrizioneComponente getDescrizioneComponente() {
		return this.descrizioneComponente;
	}
	
	/**
	 * Restituisce la quantità della componente
	 * @return la quantità della componente
	 */
	public int getQuantita() {
		return this.quantita;
	}

	/**
	 * Definisce la descrizione della componente
	 * @param descrizioneComponente la descrizione della componente
	 */
	public void setDescrizioneComponente(DescrizioneComponente descrizioneComponente) {
		this.descrizioneComponente = descrizioneComponente;
	}
	
	/**
	 * Definisce la quantità della componente
	 * @param quantita la quantità della componente
	 */
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	
	
}
