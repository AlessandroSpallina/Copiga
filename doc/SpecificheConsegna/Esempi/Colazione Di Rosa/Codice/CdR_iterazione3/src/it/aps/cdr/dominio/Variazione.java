package it.aps.cdr.dominio;

/**
 * Un oggetto di tipo Variazione rappresenta una componente modificata 
 * o aggiunta di un tipo di colazione ordinato.
 * 
 * @see ColazioneOrdinata
 * @see DescrizioneComponente
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri
 * @author Gabriele Rendina
 * 
 * @version 0.3
 *
 */
public class Variazione {
	
	/** la colazione alla quale appartiene la variazione */
	private ColazioneOrdinata colazioneOrdinata;
	/** la descrizione della variazione */
	private DescrizioneComponente descrizioneComponente;
	/** la quantita' della variazione */
	private int quantita; 
	
	/**
	 * Crea una nuova Variazione
	 * @param co la colazione ordinata a cui e' associata la variazione
	 * @param dc la descrizione della variazione
	 * @param quantita la nuova quantita' della componente
	 */
	public Variazione(ColazioneOrdinata co, DescrizioneComponente dc, int quantita) {
		this.colazioneOrdinata = co;
		this.quantita = quantita;
		this.descrizioneComponente = dc;
	}
	
	/* ************************************************************************************ */
	/* ************************************** GETTERS ************************************* */
	/* ************************************************************************************ */
	
	/**
	 * Restituisce la descrizione della componente associata 
	 * alla variazione
	 * @return la descrizione della componente associata 
	 * alla variazione
	 */
	public DescrizioneComponente getDescrizioneComponente(){
		return this.descrizioneComponente;
	}
	
	/**
	 * Restituisce la quantita' della variazione
	 * @return la quantita' della variazione
	 */
	public int getQuantita() {
		return this.quantita;
	}

	/**
	 * Rstituisce la colazione ordinata alla quale appartiene la 
	 * variazione 
	 * @return la colazione ordinata
	 */
	public ColazioneOrdinata getColazioneOrdinata() {
		return this.colazioneOrdinata;
	}
	
	/* ************************************************************************************ */
	/* ************************************** SETTERS ************************************* */
	/* ************************************************************************************ */
	
	/**
	 * Definisce la colazione ordinata alla quale appartiene la 
	 * variazione 
	 * @param colazioneOrdinata la colazione ordinata
	 */
	public void setColazioneOrdinata(ColazioneOrdinata colazioneOrdinata) {
		this.colazioneOrdinata = colazioneOrdinata;
	}
	
	/**
	 * Definisce la descrizione della variazione
	 * @param descrizioneComponente la dscrizione della variazione
	 */
	public void setDescrizioneComponente(DescrizioneComponente descrizioneComponente) {
		this.descrizioneComponente = descrizioneComponente;
	}
	
	/**
	 * Definisce la quantita' della variazione
	 * @param quantita la quantita' della variazione
	 */
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	
	/**
	 * Restituisce la variazione di quantita' 
	 * @return la variazione di quantita'
	 */
	public int calcolaVariazioneQuantita(){
		int variazione;
		TipoColazione tc = this.colazioneOrdinata.getTipoColazione();
		ComponenteColazione cc = tc.trovaComponenteColazione(this.descrizioneComponente.getCodice());
		if (cc==null)
			variazione = this.quantita;
		else
			variazione = this.quantita - cc.getQuantita();

		return variazione;
	}
	
}
