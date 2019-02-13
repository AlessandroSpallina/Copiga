package it.aps.cdr.dominio;

import java.util.*;

/**
 * Un oggetto TipoColazione rappresenta un tipo di colazione, 
 * costituito da oggetti ComponenteColazione.
 * 
 * @see ComponenteColazione
 * 
 * @author Luca Cabibbo
 * @author Marco Canu
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.1
 * 
 */
public class TipoColazione {

	/* il nome del tipo di colazione */
	private String nome; 
	/* il codice identificativo del tipo di colazione */
	private String codice;	
	/* il prezzo base del tipo di colazione */
	private double prezzo;
	/* le componenti appartenenti al tipo di colazione */
	private List<ComponenteColazione> componenti; 
	
	/**
	 * Crea un nuovo TipoColazione
	 * 
	 * @param codice il codice per il nuovo tipo di colazione
	 * @param nome il nome del nuovo tipo di colazione
	 * 
	 */
	public TipoColazione(String codice, String nome) {
		this.nome = nome;
		this.codice = codice;
		this.componenti = new LinkedList<ComponenteColazione>();
	}
	
	/**
	 * Aggiunge un componente al nuovo tipo di colazione
	 * 
	 * @param dc la descrizione del componente 
	 * @param quantita la quantità desiderata del componente
	 * @return true se l'inserimento è andato a buon fine, false altrimenti
	 * 
	 */
	public boolean aggiungiComponenteColazione(DescrizioneComponente dc, int quantita) {
		ComponenteColazione cc = new ComponenteColazione(dc, quantita);
		return this.componenti.add(cc);
	}

	/**
	 * Definisce il prezzo base per il tipo di colazione
	 * 
	 * @param prezzo il prezzo base del tipo di colazione 
	 */
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
		
	}

	/**
	 * Restituisce il codice del tipo di colazione
	 * 
	 * @return il codice del tipo di colazione
	 */
	public String getCodice() {
		return this.codice;
	}
	
	/**
	 * Restituisce il prezzo base del tipo di colazione 
	 * 
	 * @return il prezzo base del tipo di colazione
	 */
	public double getPrezzo() {
		return this.prezzo;
	}
	
	
	/**
	 * Restituisce le componenti del tipo di colazione
	 * 
	 * @return le componenti del tipo di colazione
	 */
	public List<ComponenteColazione> getComponenti() {
		return this.componenti;
	}
	
	/**
	 * Restituisce il nome del tipo di colazione
	 * 
	 * @return il nome del tipo di colazione 
	 */
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * Definisce il nome del tipo di colazione 
	 * 
	 * @param nome il nome del tipo di colazione 
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Definisce il codice identificativo del tipo di colazione
	 * 
	 * @param codice il codice del tipo di colazione 
	 */
	public void setCodice(String codice) {
		this.codice = codice;
	}
	
	/**
	 * Definisce le componenti del tipo di colazione
	 * 
	 * @param componenti le componenti del tipo di colazione
	 */
	public void setComponenti(List<ComponenteColazione> componenti) {
		this.componenti = componenti;
	}
	

	/**
	 * Restituisce una rappresentazione testuale del tipo di colazione 
	 * 
	 * @return la rappresentazione testuale del tipo di colazione 
	 */
	public String toString(){
		StringBuffer result = new StringBuffer();
		result.append(this.codice);
		result.append(" ");
		result.append(this.nome);
		result.append("\n\t");
		result.append(" (");
		for (ComponenteColazione componente : this.componenti)
			result.append(componente.toString());
		result.append(" )");
		return result.toString();
	}
	
	/**
	 * Restituisce la descrizione della componente corrispondente al codice
	 * specificato
	 * 
	 * @param codice il codice della componente desiderata
	 * @return la descrizione della componente desiderata
	 */
	public ComponenteColazione trovaComponenteColazione(String codice) {
		ComponenteColazione cc = null;
		for (ComponenteColazione componente : this.componenti){
			DescrizioneComponente temp = componente.getDescrizioneComponente();
			if (temp.getCodice().equals(codice))
				cc = componente;
		}
		return cc;
	}
}
