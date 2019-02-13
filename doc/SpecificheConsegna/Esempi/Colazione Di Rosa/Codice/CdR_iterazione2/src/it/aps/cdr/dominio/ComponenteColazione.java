package it.aps.cdr.dominio;

/**
 * 
 * Un oggetto ComponenteColazione rappresenta una componente 
 * di un oggetto TipoColazione, con la relativa quantit�.
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
	/* quantit� del componente */
	private int quantita;
	
	/**
	 * Crea una nuova ComponenteColazione
	 * 
	 * @param dc la descrizione del componente
	 * @param quantita la quantit� desiderata del componente
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
	 * Restituisce la quantit� della componente
	 * @return la quantit� della componente
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
	 * Definisce la quantit� della componente
	 * @param quantita la quantit� della componente
	 */
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	
	
}
