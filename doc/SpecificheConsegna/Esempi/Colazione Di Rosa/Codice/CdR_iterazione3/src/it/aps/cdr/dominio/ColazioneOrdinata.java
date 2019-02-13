package it.aps.cdr.dominio;

import it.aps.cdr.eventi.Evento;
import it.aps.cdr.eventi.PropertyListener;

import java.util.*;

/**
 * Un oggetto ColazioneOrdinata rappresenta una colazione ordinata 
 * in relazione ad un ordine, costituita da un tipo di colazione 
 * standard e da possibili variazioni.
 * 
 * @see TipoColazione
 * @see Variazione
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ColazioneOrdinata {

	/** il codice della colazione ordinata */
	private ColazioneOrdinataID codice;
	/** il tipo di colazione al quale e' associata la colazione */
	private TipoColazione tipoColazione;
	/** le variazioni effettuate rispetto al tipo di colazione */
	private List<Variazione> variazioni;
	/** la modalita' di servizio della colazione ordinata */
	private ModoServizio modoServizio;	
	/** listeners interessati ai cambiamenti della colazione ordinata */
	private List<PropertyListener> listeners;
	
	/**
	 * Crea una nuova ColazioneOrdinata
	 * @param codice il codice della colazione ordinata
	 * @param tipoColazione il tipo di colazione
	 */
	public ColazioneOrdinata(ColazioneOrdinataID codice, TipoColazione tipoColazione) {
		this(tipoColazione);
		this.codice = codice;
	}
	
	/**
	 * Crea una nuova ColazioneOrdinata
	 * @param tipoColazione il tipo di colazione
	 */
	public ColazioneOrdinata(TipoColazione tipoColazione) {
		this.tipoColazione = tipoColazione;
		this.variazioni = new LinkedList<Variazione>();
		this.listeners = new LinkedList<PropertyListener>();
	}
	
	/* ************************************************************************************ */
	/* ************************************** GETTERS ************************************* */
	/* ************************************************************************************ */
	
	/**
	 * Restituisce il tipo di colazione senza variazioni
	 * @return il tipo di colazione senza variazioni
	 */
	public TipoColazione getTipoColazione() {
		return this.tipoColazione;
	}
	
	/**
	 * Restituisce le variazioni introdotte al tipo di colazione 
	 * @return la lista di variazioni
	 */
	public List<Variazione> getVariazioni() {
		return this.variazioni;
	}
	
	/**
	 * Restituisce la modalita' di servizio 
	 * @return la modalita' di servizio
	 */
	public ModoServizio getModoServizio() {
		return this.modoServizio;
	}
	
	/**
	 * Restituisce il codice della colazione ordinata
	 * @return il codice della colazione ordinata
	 */
	public ColazioneOrdinataID getCodice() {
		return this.codice;
	}

	/* ************************************************************************************ */
	/* ************************************** SETTERS ************************************* */
	/* ************************************************************************************ */

	/**
	 * Definisce il tipo di colazione a cui e' associata questa 
	 * colazione ordinata 
	 * @param tipoColazione
	 */
	public void setTipoColazione(TipoColazione tipoColazione) {
		this.tipoColazione = tipoColazione;
	}
	
	/**
	 * Definisce le variazioni introdotte al tipo di colazione
	 * @param variazioni
	 */
	public void setVariazioni(List<Variazione> variazioni) {
		this.variazioni = variazioni;
	}

	/**
	 * Definisce la modalita' di servizio
	 * @param modoServizio la modalita' di servizio
	 */
	public void setModoServizio(ModoServizio modoServizio) {
		this.modoServizio = modoServizio;
		this.publishPropertyEvent(Evento.DEFINISCI_MODO_SERVIZIO,this.modoServizio);
	}

	/**
	 * Definisce il codice della colazione ordinata
	 * @param codice il codice della colazione ordinata
	 */
	public void setCodice(ColazioneOrdinataID codice) {
		this.codice = codice;
	}
	
	/**
	 * Modifica la quantita' di una componente della colazione ordinata 
	 * nell'ordine corrente. Rimuove la componente se la quantita' e' 
	 * pari a 0 
	 * @param descrizioneComponente la componente da modificare
	 * @param quantita la nuova quantita' desiderata
	 * @return true se la modifica e' stata effettuata, false altrimenti
	 */
	public boolean modificaComponente(DescrizioneComponente dc, int quantita) {
		boolean risultato = false;
		if (dc != null){
			/* controlla se già esiste una variazione su quella descrizione 
			 * componente. In tal caso aggiorna la variazione già esistente */
			Variazione v = this.getVariazione(dc);
			if (v!=null) {
				v.setQuantita(quantita);
				risultato = true;
				this.publishPropertyEvent(Evento.VARIAZIONE_COMPONENTE_COLAZIONE,null);
			} else {
				v = new Variazione(this,dc,quantita);
				risultato = this.variazioni.add(v);
				this.publishPropertyEvent(Evento.VARIAZIONE_COMPONENTE_COLAZIONE,v);
			}	
		}
		return risultato;
	}
	
	private Variazione getVariazione(DescrizioneComponente dc) {
		Variazione result = null;
		for (Variazione v : this.variazioni)
			if (v.getDescrizioneComponente().getCodice().equals(dc.getCodice()))
				result = v;
		return result;
	}
	
	/**
	 * Aggiunge una nuova componente e relativa quantita' alla colazione ordinata 
	 * nell'ordine corrente. 
	 * @param dc la descrizione della nuova componente da aggiungere
	 * @param quantita la quantita' desiderata della nuova componente
	 * @return true se la componente e' stata aggiunta, false altrimenti
	 */
	public boolean aggiungiComponente(DescrizioneComponente dc, int quantita) {
		boolean risultato = false;
		/* controlla se già esiste una variazione su quella descrizione 
		 * componente. In tal caso aggiorna la variazione già esistente */
		Variazione v = this.getVariazione(dc);
		if (v!=null) {
			v.setQuantita(quantita);
			risultato = true;
			this.publishPropertyEvent(Evento.VARIAZIONE_COMPONENTE_COLAZIONE,null);
		} else {
			v = new Variazione(this,dc,quantita);
			risultato = this.variazioni.add(v);
			this.publishPropertyEvent(Evento.VARIAZIONE_COMPONENTE_COLAZIONE,v);
		}	
		return risultato;
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
