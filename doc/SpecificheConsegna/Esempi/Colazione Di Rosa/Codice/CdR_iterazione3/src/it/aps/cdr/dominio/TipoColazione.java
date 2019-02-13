package it.aps.cdr.dominio;

import it.aps.cdr.eventi.Evento;
import it.aps.cdr.eventi.PropertyListener;

import java.util.*;

/**
 * Un oggetto TipoColazione rappresenta un tipo di colazione, 
 * costituito da oggetti ComponenteColazione.
 * 
 * @see ComponenteColazione
 * @see TipoColazioneID
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class TipoColazione {

	/** il nome del tipo di colazione */
	private String nome;
	/** il codice identificativo del tipo di colazione */
	private TipoColazioneID codice;	
	/** il prezzo del tipo di colazione */
	private Prezzo prezzo;	
	/** le componenti aapartenenti al tipo di colazione */
	private List<ComponenteColazione> componenti;
	/** listeners interessati ai cambiamenti del tipo di colazione */
	private List<PropertyListener> listeners;	
	
	/**
	 * Crea un nuovo TipoColazione
	 * @param codice il codice per il nuovo tipo di colazione
	 * @param nome il nome del nuovo tipo di colazione
	 */
	public TipoColazione(TipoColazioneID codice, String nome) {
		this(nome);
		this.codice = codice;
	}
	
	/**
	 * Crea un nuovo TipoColazione
	 * @param nome il nome del nuovo tipo di colazione
	 */	
	public TipoColazione(String nome){
		this.nome = nome;
		this.componenti = new LinkedList<ComponenteColazione>();
		this.listeners = new LinkedList<PropertyListener>();
	}
	
	/* ************************************************************************************ */
	/* ************************************** GETTERS ************************************* */
	/* ************************************************************************************ */
	
	/**
	 * Restituisce il codice del tipo di colazione
	 * @return il codice del tipo di colazione
	 */
	public TipoColazioneID getCodice() {
		return this.codice;
	}
	
	/**
	 * Restituisce il nome del tipo di colazione
	 * @return il nome del tipo di colazione 
	 */
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * Restituisce le componenti del tipo di colazione
	 * @return le componenti del tipo di colazione
	 */
	public List<ComponenteColazione> getComponenti() {
		return this.componenti;
	}
	
	/**
	 * Restituisce il prezzo del tipo di colazione 
	 * @return il prezzo del tipo di colazione
	 */
	public Prezzo getPrezzo() {
		return this.prezzo;
	}
	
	/* ************************************************************************************ */
	/* ************************************** SETTERS ************************************* */
	/* ************************************************************************************ */

	/**
	 * Definisce il codice identificativo del tipo di colazione
	 * @param codice il codice del tipo di colazione 
	 */
	public void setCodice(TipoColazioneID codice) {
		this.codice = codice;
	}
	
	/**
	 * Definisce il nome del tipo di colazione 
	 * @param nome il nome del tipo di colazione 
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Definisce le componenti del tipo di colazione
	 * @param componenti le componenti del tipo di colazione
	 */
	public void setComponenti(List<ComponenteColazione> componenti) {
		this.componenti = componenti;
	}
	
	/**
	 * Definisce il prezzo per il tipo di colazione
	 * @param prezzo il prezzo del tipo di colazione 
	 */
	public void setPrezzo(Prezzo prezzo) {
		this.prezzo = prezzo;
	}
		
	/* ************************************************************************************ */
	/* ************************************** USE CASE ************************************ */
	/* ************************************************************************************ */
	
	/**
	 * Aggiunge un componente al nuovo tipo di colazione
	 * @param dc la descrizione del componente 
	 * @param quantita la quantita' desiderata del componente
	 * @return true se l'inserimento e' andato a buon fine, false altrimenti
	 */
	public boolean aggiungiComponenteColazione(DescrizioneComponente dc, int quantita) {
		ComponenteColazione cc = new ComponenteColazione(dc,quantita);
		boolean result = this.componenti.add(cc); 
		if (result)
			this.publishPropertyEvent(Evento.AGGIUNGI_COMPONENTE_TIPO_COLAZIONE,cc);
		return result;
	}

	/**
	 * Restituisce la descrizione della componente corrispondente 
	 * al codice specificato
	 * @param codice il codice della componente desiderata
	 * @return la descrizione della componente desiderata
	 */
	public ComponenteColazione trovaComponenteColazione(DescrizioneComponenteID codice) {
		ComponenteColazione cc = null;
		for (ComponenteColazione componente : this.componenti){
			DescrizioneComponente temp = componente.getDescrizioneComponente();
			if (temp.getCodice().equals(codice))
				cc = componente;
		}
		return cc;
	}

	/**
	 * Restituisce una rappresentazione testuale del tipo di colazione 
	 * @return la rappresentazione testuale del tipo di colazione 
	 */
	public String toString(){
		return this.nome;
	}
	
	/**
	 * Restituisce una rappresentazione testuale del tipo di colazione 
	 * @return la rappresentazione testuale del tipo di colazione 
	 */
	public String stampa(){
		StringBuffer result = new StringBuffer();
		result.append("   CODICE :\t" + this.codice.toString());
		result.append("\n");
		result.append("   NOME :\t" + this.nome);
		result.append("\n");
		result.append("   COMPONENTI :\t");
		for (ComponenteColazione componente : this.componenti)
			result.append(componente.toString() + "; ");
		result.append("\n");
		result.append("   PREZZO :\t" + this.prezzo.toString());
		return result.toString();
	}
		
	/* ************************************************************************************ */
	/* ************************************* OBSERVABLE *********************************** */
	/* ************************************************************************************ */
	
	public void addPropertyListener(PropertyListener pl) {
		this.listeners.add(pl);
	}
	
	public void publishPropertyEvent(String property,Object value) {
		for (PropertyListener pl : this.listeners)
			pl.onPropertyEvent(this,property,value);
	}
	
}
