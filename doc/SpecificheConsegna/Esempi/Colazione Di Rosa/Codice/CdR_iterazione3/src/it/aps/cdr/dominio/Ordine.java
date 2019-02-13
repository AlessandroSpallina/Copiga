package it.aps.cdr.dominio;

import it.aps.cdr.eventi.Evento;
import it.aps.cdr.eventi.PropertyListener;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Un oggetto di tipo Ordine rappresenta un ordine costituito 
 * da un insieme di colazioni ordinate da consegnare secondo una 
 * specifica modalita' di servizio.
 * 
 * @see ColazioneOrdinata
 * @see OrdineID
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class Ordine {
	
	/** le colazioni ordinate nell'ordine */
	private List<ColazioneOrdinata> colazioniOrdinate;
	/** il cliente associato all'ordine */
	private Cliente cliente;
	/** il codice identificativo dell'ordine */ 
	private OrdineID codice;
	/** la colazione corrente dell'ordine */
	private ColazioneOrdinata colazioneOrdinataCorrente;
	/** la data di consegna dell'ordine */
	private Date data;
	/** il prezzo dell'ordine */
	private Prezzo prezzo;
	/** lo stato di consegna dell'ordine */
	private boolean consegnato;
	/** listeners interessati ai cambiamenti dell'ordine */
	private List<PropertyListener> listeners;	
	
	/** Crea un nuovo Ordine */
	public Ordine(){
		this.colazioniOrdinate = new LinkedList<ColazioneOrdinata>();
		this.consegnato = false;
		this.listeners = new LinkedList<PropertyListener>();
	}
	
	/**
	 * Crea un nuovo Ordine
	 * @param ordineID il codice dell'ordine
	 */
	public Ordine(OrdineID ordineID) {
		this();
		this.codice = ordineID;
	}
	
	/* ************************************************************************************ */
	/* ************************************** GETTERS ************************************* */
	/* ************************************************************************************ */
	
	/**
	 * Restituisce il cliente
	 * @return il cliente
	 */
	public Cliente getCliente() {
		return this.cliente;
	}

	/**
	 * Restituisce il codice dell'ordine
	 * @return il codice dell'ordine
	 */
	public OrdineID getCodice() {
		return this.codice;
	}
	
	/**
	 * Restituisce la colazione corrente dell'ordine 
	 * @return la colazione corrente dell'ordine  
	 */
	public ColazioneOrdinata getColazioneCorrente() {
		return this.colazioneOrdinataCorrente;
	}
		
	/**
	 * Restituisce le colazioni ordinate 
	 * @return le colazioni ordinate 
	 */
	public List<ColazioneOrdinata> getColazioniOrdinate() {
		return this.colazioniOrdinate;
	}
	
	/**
	 * Restituisce la data di consegna dell'ordine 
	 * @return la data di consegna dell'ordine 
	 */
	public Date getData() {
		return this.data;
	}
	
	/**
	 * Restituisce il prezzo dell'ordine 
	 * @return il prezzo dell'ordine 
	 */
	public Prezzo getPrezzo() {
		return this.prezzo;
	}
	
	/**
	 * Restituisce la colazione ordinata corrente
	 * @return la colazione ordinata corrente
	 */
	public ColazioneOrdinata getColazioneOrdinataCorrente() {
		return this.colazioneOrdinataCorrente;
	}

	/**
	 * Restituisce lo stato di consegna dell'ordine
	 * @return lo stato di consegna dell'ordine
	 */
	public boolean isConsegnato() {
		return this.consegnato;
	}
		
	/* ************************************************************************************ */
	/* ************************************** SETTERS ************************************* */
	/* ************************************************************************************ */
	
	/**
	 * Associa un cliente all'ordine
	 * @param cliente il cliente registrato 
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	/**
	 * Definisce il codice dell'ordine
	 * @param codice il codice dell'ordine
	 */
	public void setCodice(OrdineID codice) {
		this.codice = codice;
	}
	
	/**
	 * Definisce la colazione corrente dell'ordine
	 * @param colazioneCorrente la colazione corrente dell'ordine
	 */
	public void setColazioneCorrente(ColazioneOrdinata colazioneCorrente) {
		this.colazioneOrdinataCorrente = colazioneCorrente;
	}
	
	/**
	 * Definisce le colazioni appartenenti all'ordine 
	 * @param colazioniOrdinate le colazioni appartenenti all'ordine
	 */
	public void setColazioniOrdinate(List<ColazioneOrdinata> colazioniOrdinate) {
		this.colazioniOrdinate = colazioniOrdinate;
	}
	
	/**
	 * Associa una data ed un'ora di consegna all'ordine
	 * @param data la data e l'ora di consegna
	 */
	public void setData(Date data) {
		this.data = data;
	}

	/**
	 * Assegna un prezzo all'ordine
	 * @param prezzo il prezzo dell'ordine
	 */
	public void setPrezzo(Prezzo prezzo) {
		this.prezzo = prezzo;
	}
	
	/**
	 * Definisce la colazione ordinata corrente
	 * @param colazioneOrdinataCorrente la colazione ordinata corrente
	 */
	public void setColazioneOrdinataCorrente(ColazioneOrdinata colazioneOrdinataCorrente) {
		this.colazioneOrdinataCorrente = colazioneOrdinataCorrente;
	}
	
	/**
	 * Definisce lo stato di consegna dell'ordine
	 * @param consegnato lo stato di consegna dell'ordine
	 */
	public void setConsegnato(boolean consegnato) {
		this.consegnato = consegnato;
	}
	
	/* ************************************************************************************ */
	/* ************************************** USE CASE ************************************ */
	/* ************************************************************************************ */

	/**
	 * Crea una nuova colazione ordinata
	 * @param tipoColazione il tipo di colazione 
	 * @return la colazione ordinata
	 */
	public ColazioneOrdinata nuovaColazioneOrdinata(TipoColazione tipoColazione){
		this.colazioneOrdinataCorrente = new ColazioneOrdinata(tipoColazione);
		return this.colazioneOrdinataCorrente;
	}
	
	/**
	 * 
	 * Aggiunge una nuova componente e relativa quantita' alla colazione ordinata 
	 * nell'ordine corrente
	 * @param dc la descrizione della nuova componente da aggiungere
	 * @param quantita la quantita' desiderata della nuova componente
	 * @return true se la componente e' stata aggiunta, false altrimenti
	 */
	public boolean aggiungiCompColazioneOrdinata(DescrizioneComponente dc, int quantita) {
		return this.colazioneOrdinataCorrente.aggiungiComponente(dc,quantita);
	}
	
	/**
	 * Definisce la modalita' di servizio della colazione ordinata corrente
	 * @param modoServizio la modalita' di servizio
	 * @return true se la modalita' e' stata definita, false altrimenti
	 */
	public boolean definisciModoServizio(ModoServizio modoServizio) {
		boolean risultato = false;
		if (modoServizio != null) {
			this.colazioneOrdinataCorrente.setModoServizio(modoServizio);
			risultato = true;
		}
		return risultato;
	}

	/**
	 * Modifica la quantita' di una componente della colazione ordinata 
	 * nell'ordine corrente. Rimuove la componente se la quantita' e' 
	 * pari a 0 
	 * @param dc la descrizione della componente da modificare
	 * @param quantita la nuova quantita' desiderata
	 * @return true se la modifica e' stata effettuata, false altrimenti
	 */
	public boolean modificaCompColazioneOrdinata(DescrizioneComponente dc, int quantita) {
		return this.colazioneOrdinataCorrente.modificaComponente(dc,quantita);
	}
	
	/**
	 * Definisce il cliente e la data di consegna 
	 * dell'ordine corrente
	 * @param clienteID il codice del cliente registrato
	 * @param data la data di consegna dell'ordine
	 * @return l'ordine corrente
	 */
	public void completaOrdine(Cliente cliente, Date data) {
		this.setCliente(cliente);
		this.setData(data);
		CalcolatorePrezzo.getInstance().calcolaPrezzo(this);
		this.publishPropertyEvent(Evento.COMPLETA_ORDINE,null);
	}
	
	/**
	 * Aggiunge la colazione ordinata corrente nell'ordine  
	 */
	public void confermaColazioneOrdinata() {
		this.colazioniOrdinate.add(this.colazioneOrdinataCorrente);
		this.publishPropertyEvent(Evento.CONFERMA_COLAZIONE_ORDINATA,this.colazioneOrdinataCorrente);
	}
	
	/**
	 * Restituisce una rappresentazione testuale dell'ordine
	 * @return la rappresentazione testuale dell'ordine
	 */
	public String toString(){
		StringBuffer result = new StringBuffer();
		result.append("   CODICE :\t" + this.codice);
		result.append("\n");
		result.append("   DATA :\t" + this.data);
		result.append("\n");
		result.append("   CLIENTE :\t" + this.cliente);
		result.append("\n");
		result.append("   PREZZO :\t" + this.prezzo);
		result.append("\n");
		result.append("   CONSEGNATO :\t" + this.consegnato);
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
