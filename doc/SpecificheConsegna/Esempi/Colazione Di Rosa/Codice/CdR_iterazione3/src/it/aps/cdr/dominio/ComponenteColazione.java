package it.aps.cdr.dominio;

/**
 * 
 * Un oggetto ComponenteColazione rappresenta una componente 
 * di un oggetto TipoColazione, con la relativa quantita'.
 * 
 * @see DescrizioneComponente
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComponenteColazione {
	
	/** descrizione della componente */
	private DescrizioneComponente descrizioneComponente;
	/** quantita' del componente */
	private int quantita; 
	
	/**
	 * Crea una nuova ComponenteColazione
	 * @param dc la descrizione del componente
	 * @param quantita la quantita' desiderata del componente
	 */
	public ComponenteColazione(DescrizioneComponente dc , int quantita) {
		this.descrizioneComponente = dc;
		this.quantita = quantita;
	}
	
	/* ************************************************************************************ */
	/* ************************************** GETTERS ************************************* */
	/* ************************************************************************************ */
	
	/**
	 * Restituisce la descrizione della componente 
	 * @return la descrizione della componente 
	 */
	public DescrizioneComponente getDescrizioneComponente() {
		return this.descrizioneComponente;
	}
	
	/**
	 * Restituisce la quantita' della componente
	 * @return la quantita' della componente
	 */
	public int getQuantita() {
		return this.quantita;
	}

	/* ************************************************************************************ */
	/* ************************************** SETTERS ************************************* */
	/* ************************************************************************************ */
	
	/**
	 * Definisce la descrizione della componente
	 * @param descrizioneComponente la descrizione della componente
	 */
	public void setDescrizioneComponente(DescrizioneComponente descrizioneComponente) {
		this.descrizioneComponente = descrizioneComponente;
	}
	
	/**
	 * Definisce la quantita' della componente
	 * @param quantita la quantita' della componente
	 */
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	
	/**
	 * Restituisce una rappresentazione testuale della componente della colazione
	 * @return la rappresentazione testuale della componente della colazione
	 */
	public String toString(){
		StringBuffer result = new StringBuffer();
		result.append(this.descrizioneComponente.toString());
		result.append(" x " + this.quantita);
		return result.toString();
	}
	
}
