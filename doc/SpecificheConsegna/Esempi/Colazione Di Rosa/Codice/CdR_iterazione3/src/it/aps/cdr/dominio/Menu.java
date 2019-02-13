package it.aps.cdr.dominio;

import it.aps.cdr.eventi.Evento;
import it.aps.cdr.eventi.PropertyListener;
import it.aps.cdr.logging.Logger;
import it.aps.cdr.persistenza.PersistenceFacade;
import it.aps.cdr.persistenza.dao.DAOException;

import java.util.*;

/**
 * Menu per la gestione dei tipi di colazione.
 * Gestisce anche i componenti e i modi di servizio. 
 * 
 * @see TipoColazione
 * @see TipoColazioneID
 * @see DescrizioneComponente
 * @see DescrizioneComponenteID
 * @see ModoServizio
 * @see ModoServizioID
 *
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class Menu {
	
	/** i tipi di colazione presenti nel menu */ 
	private Map<TipoColazioneID,TipoColazione> tipiColazione;
	/** il tipo di colazione corrente */
	private TipoColazione tipoColazioneCorrente;
	
	/** le descrizioni delle componenti */
	private Map<DescrizioneComponenteID,DescrizioneComponente> descrizioniComponenti;
	/** la descrizione della componente corrente */
	private DescrizioneComponente descrizioneComponenteCorrente;

	/** le modalita' con cui puo' essere servita una colazione */
	private Map<ModoServizioID,ModoServizio> modiServizio;
	/** la modalita' di servizio corrente */
	private ModoServizio modoServizioCorrente;

	/** listeners interessati ai cambiamenti del tipo di colazione */
	private List<PropertyListener> listeners;	
	
	/** Crea un nuovo Menu */
	public Menu() {
		// this.tipiColazione = new HashMap<TipoColazioneID,TipoColazione>();
		this.descrizioniComponenti = new HashMap<DescrizioneComponenteID,DescrizioneComponente>();  
		this.modiServizio = new HashMap<ModoServizioID,ModoServizio>(); 
		this.tipiColazione = new HashMap<TipoColazioneID,TipoColazione>();  

		this.listeners = new LinkedList<PropertyListener>();
	}
	
	/**
	 * Carica/ricarica i dati in memoria dalla base di dati, 
	 * e li fa ricaricare a tutti gli altri macro-oggetti di dominio collegati. 
	 */
	public void ricaricaDati() {
		/* carica tutte le informazioni registrate nella base di dati */
		this.descrizioniComponenti = PersistenceFacade.getInstance().trovaDescrizioniComponenti(); 
		this.modiServizio = PersistenceFacade.getInstance().trovaModiServizio();
		this.tipiColazione = PersistenceFacade.getInstance().trovaTipiColazione(); 
	}

		/* ************************************************************************************ */
	/* ************************************** GETTERS ************************************* */
	/* ************************************************************************************ */
	
	/**
	 * Restituisce il tipo di colazione corrente
	 * @return il tipo di colazione corrente
	 */
	public TipoColazione getTipoColazioneCorrente() {
		return tipoColazioneCorrente;
	}
	
	/**
	 * Restituisce i tipi di colazione presenti nel menu
	 * @return i tipi di colazione presenti nel menu
	 */
	public Collection<TipoColazione> getTipiColazione() {
		return this.tipiColazione.values();
	}
	
	/**
	 * Restituisce il tipo di colazione presente nel menu associato a tipoColazioneID
	 * @param tipoColazioneID il codice del tipo di colazione
	 * @return il tipo di colazione associato a tipoColazioneID
	 */
	public TipoColazione getTipoColazione(TipoColazioneID tipoColazioneID) {
		return this.tipiColazione.get(tipoColazioneID);
	}
	
	/**
	 * Restituisce il tipo di colazione associato a nomeTipoColazione
	 * @param nomeTipoColazione il nome del tipo di colazione
	 * @return il tipo di colazione
	 */
	public TipoColazione getTipoColazione(String nomeTipoColazione) {
		TipoColazione tipoColazione = null;
		for (TipoColazione tc : getTipiColazione()) {
			if (tc.getNome().equalsIgnoreCase(nomeTipoColazione))
				tipoColazione = tc;
		}
		return tipoColazione;
	}
	
	/**
	 * Restituisce la descrizione componente corrente
	 * @return la descrizione componente corrente
	 */
	public DescrizioneComponente getDescrizioneComponenteCorrente() {
		return this.descrizioneComponenteCorrente;
	}
	
	/**
	 * Restituisce le descrizioni delle componenti registrate nel sistema
	 * @return le descrizioni delle componenti registrate
	 */
	public Collection<DescrizioneComponente> getDescrizioniComponenti() {
		return this.descrizioniComponenti.values();
	}
	
	/**
	 * Restituisce la modalita' di servizio corrente
	 * @return la modalita' di servizio corrente
	 */
	public ModoServizio getModoServizioCorrente() {
		return this.modoServizioCorrente;
	}

	/**
	 * Restituisce la modalita' di servizio associata al codice 
	 * modoServizioID
	 * @param modoServizioID il codice della modalita' di servizio
	 * @return la modalita' di servizio
	 */
	public ModoServizio getModoServizio(ModoServizioID modoServizioID) {
		return this.modiServizio.get(modoServizioID);
	}
	
	/**
	 * Restituisce la modalita' di servizio associata a nomeModoServizio
	 * @param nomeModoServizio il nome della modalita' di servizio
	 * @return la modalita' di servizio
	 */
	public ModoServizio getModoServizio(String nomeModoServizio) {
		ModoServizio modoServizio = null;
		for (ModoServizio ms : getModiServizio()) {
			if (ms.getNome().equalsIgnoreCase(nomeModoServizio))
				modoServizio = ms;
		}
		return modoServizio;
	}
	
	/**
	 * Restituisce le modalita' di servizio registrate nel sistema
	 * @return le modalita' di servizio
	 */
	public Collection<ModoServizio> getModiServizio() {
		return this.modiServizio.values();
	}
	
	/* ************************************************************************************ */
	/* ************************************** SETTERS ************************************* */
	/* ************************************************************************************ */
	
	/**
	 * Definisce il tipo di colazione corrente 
	 * @param tipoColazioneCorrente il tipo di colazione corrente
	 */
	public void setTipoColazioneCorrente(TipoColazione tipoColazioneCorrente) {
		this.tipoColazioneCorrente = tipoColazioneCorrente;
	}
	
	/**
	 * Definisce i tipi di colazione
	 * @param tipiColazione i tipi di colazione
	 */
	public void setTipiColazione(Map<TipoColazioneID, TipoColazione> tipiColazione) {
		this.tipiColazione = tipiColazione;
	}
	
	/**
	 * Definisce la descrizione componente corrente 
	 * @param descrizioneComponenteCorrente la descrizione componente corrente
	 */
	public void setDescrizioneComponenteCorrente(DescrizioneComponente descrizioneComponenteCorrente) {
		this.descrizioneComponenteCorrente = descrizioneComponenteCorrente;
	}
	
	/**
	 * Definisce la modalita' di servizio corrente
	 * @param modoServizioCorrente la modalita' di servizio corrente
	 */
	public void setModoServizioCorrente(ModoServizio modoServizioCorrente) {
		this.modoServizioCorrente = modoServizioCorrente;
	}
	
	/**
	 * Definisce le modalita' di servizio 
	 * @param modiServizio le modalita' di servizio
	 */
	public void setModiServizio(Map<ModoServizioID, ModoServizio> modiServizio) {
		this.modiServizio = modiServizio;
	}

	/* ************************************************************************************ */
	/* *********************************** CANCELLAZIONE ********************************** */
	/* ************************************************************************************ */

	/**
	 * Rimuove dal menu il tipo di colazione associato a tipoColazioneID 
	 * @param tipoColazioneID il codice del tipo di colazione
	 * @return il tipo di colazione
	 */	
	public TipoColazione cancellaTipoColazione(TipoColazioneID tipoColazioneID) {
		// TipoColazione tc = this.getTipoColazione(tipoColazioneID); 
		PersistenceFacade.getInstance().cancellaTipoColazione(tipoColazioneID); 
		return this.tipiColazione.remove(tipoColazioneID);
	}
	
	/**
	 * Rimuove dal catalogo la descrizione componennte associata a descrizioneComponenteID
	 * @param descrizioneComponenteID il codice della descrizione della componente 
	 * @return la descrizione della componente
	 */	
	public DescrizioneComponente cancellaDescrizioneComponente(DescrizioneComponenteID descrizioneComponenteID) {
		// DescrizioneComponente dc = this.trovaDescrizioneComponente(descrizioneComponenteID); 
		PersistenceFacade.getInstance().cancellaDescrizioneComponente(descrizioneComponenteID); 
		return this.descrizioniComponenti.remove(descrizioneComponenteID);
	}
	
	/**
	 * Cancella la modalita' di servizio associata a modoServizioID
	 * @param modoServizioID il codice della modalita' di servizio
	 * @return la modalita' di servizio
	 */
	public ModoServizio cancellaModoServizio(ModoServizioID modoServizioID) {
		// ModoServizio ms = this.trovaModoServizio(modoServizioID); 
		PersistenceFacade.getInstance().cancellaModoServizio(modoServizioID); 
		return this.modiServizio.remove(modoServizioID);
	}

	/* ************************************************************************************ */
	/* ************************************* USE CASE ************************************* */
	/* ************************************************************************************ */
	
	/**
	 * Definisce un nuovo tipo di colazione inizialmente privo di componenti
	 * @param il nome del nuovo tipo di colazione
	 * @return il nuovo tipo di colazione privo di componenti
	 */
	public TipoColazione nuovoTipoColazione(String nome) {
		this.tipoColazioneCorrente = new TipoColazione(nome);
		this.publishPropertyEvent(Evento.NUOVO_TIPO_COLAZIONE,this.tipoColazioneCorrente);
		return this.tipoColazioneCorrente;
	}
	
	/**
	 * Aggiunge una componente al nuovo tipo di colazione
	 * @param descrizioneComponenteID il codice della componente
	 * @param quantita la quantita' desiderata della componente
	 * @return true se l'inserimento e' andato a buon fine, false altrimenti
	 */
 	public boolean aggiungiComponenteColazione(DescrizioneComponenteID descrizioneComponenteID, int quantita) {
		boolean risultato = false;
		DescrizioneComponente dc = this.trovaDescrizioneComponente(descrizioneComponenteID);
		if (dc != null) {
			risultato = this.aggiungiComponenteColazione(dc,quantita);
		}
		return risultato;
	}

	/**
	 * Aggiunge una componente al nuovo tipo di colazione
	 * @param descrizioneComponente la componente da aggiungere 
	 * @param quantita la quantita' desiderata della componente
	 * @return true se l'inserimento e' andato a buon fine, false altrimenti
	 */
	public boolean aggiungiComponenteColazione(DescrizioneComponente descrizioneComponente, int quantita){		
		return this.tipoColazioneCorrente.aggiungiComponenteColazione(descrizioneComponente,quantita);
	}
	
	/**
	 * Definisce il prezzo base per il nuovo tipo di colazione 
	 * @param prezzo il prezzo base per il tipo di colazione
	 */
	public void definisciPrezzo(Prezzo prezzo) {
		this.tipoColazioneCorrente.setPrezzo(prezzo);
	}

	/**
	 * Registra le informazioni sul nuovo tipo di colazione. La colazione 
	 * viene aggiunta al menu delle colazioni disponibili 
	 */
	public void confermaTipoColazione() {
		PersistenceFacade.getInstance().salvaTipoColazione(this.tipoColazioneCorrente); 
		this.tipiColazione.put(this.tipoColazioneCorrente.getCodice(),this.tipoColazioneCorrente);
		this.publishPropertyEvent(Evento.CONFERMA_NUOVO_TIPO_COLAZIONE,this.tipoColazioneCorrente);
	}
	
	/**
	 * Definisce una nuova componente
	 * @param nome il nome della componente
	 * @param prezzoAggiuntivo il prezzo aggiuntivo unitario della componente
	 * @param prezzoRiduttivo il prezzo riduttivo unitario della componente
	 * @return la descrizione della componente
	 */
	public DescrizioneComponente nuovaDescrizioneComponente(String nome, Prezzo prezzoAggiuntivo,Prezzo prezzoRiduttivo){
		this.descrizioneComponenteCorrente = new DescrizioneComponente(nome,prezzoAggiuntivo,prezzoRiduttivo);
		return this.descrizioneComponenteCorrente;
	}
	
	/**
	 * Definisce una nuova componente
	 * @param id il codice della componente
	 * @param nome il nome della componente
	 * @param prezzoAggiuntivo il prezzo aggiuntivo unitario della componente
	 * @param prezzoRiduttivo il prezzo riduttivo unitario della componente
	 * @return la descrizione della componente
	 */
	public DescrizioneComponente nuovaDescrizioneComponente(DescrizioneComponenteID id, String nome, Prezzo prezzoAggiuntivo,Prezzo prezzoRiduttivo){
		this.descrizioneComponenteCorrente = new DescrizioneComponente(id,nome,prezzoAggiuntivo,prezzoRiduttivo);
		return this.descrizioneComponenteCorrente;
	}
	
	/**
	 * Registra le informazioni sulla descrizione della componente
	 * @param descrizioneComponente la descrizione della componente
	 */
	public void confermaDescrizioneComponente() {
		PersistenceFacade.getInstance().salvaDescrizioneComponente(this.descrizioneComponenteCorrente); 
		this.descrizioniComponenti.put(this.descrizioneComponenteCorrente.getCodice(),this.descrizioneComponenteCorrente);
	}
	
	/**
	 * Restituisce descrizione componente presente nel catalogo associata 
	 * a descrizioneComponenteID
	 * @param id il codice della descrizione della componente
	 * @return la descrizione componente associata a descrizioneComponenteID
	 */
	public DescrizioneComponente trovaDescrizioneComponente(DescrizioneComponenteID descrizioneComponenteID) {
		return this.descrizioniComponenti.get(descrizioneComponenteID);
	}
	
	/**
	 * Restituisce la descrizione della componente associata a nomeDescrizioneComponente
	 * @param nomeDescrizioneComponente il nome della descrizione della componente
	 * @return la descrizione della componente
	 */
	public DescrizioneComponente trovaDescrizioneComponente(String nomeDescrizioneComponente) {
		DescrizioneComponente descrizioneComponente = null;
		for (DescrizioneComponente dc : getDescrizioniComponenti()) {
			if (dc.getNome().equalsIgnoreCase(nomeDescrizioneComponente))
				descrizioneComponente = dc;
		}
		return descrizioneComponente;
	}

	/**
	 * Restituisce il modo servizio associato a modoServizioID
	 * @param modoServizioID il codice della modalita' di servizio
	 * @return la modalita' di servizio
	 */
	public ModoServizio trovaModoServizio(ModoServizioID modoServizioID) {
		return this.modiServizio.get(modoServizioID);
	}

	/* ************************************************************************************ */
	/* ********************** USE CASE B : NUOVO MODO SERVIZIO **************************** */
	/* ************************************************************************************ */
	
	/**
	 * Definisce una nuova modalita' di servizio
	 * @return la nuova modalita' di servizio
	 */
	public ModoServizio nuovoModoServizio(String nome, String descrizione, double fattoreMoltiplicativo) {
		this.modoServizioCorrente = new ModoServizio(nome,descrizione,fattoreMoltiplicativo);
		return this.modoServizioCorrente;
	}
	
	/**
	 * Definisce una nuova modalita' di servizio
	 * @return la nuova modalita' di servizio
	 */
	public ModoServizio nuovoModoServizio(ModoServizioID id, String nome, String descrizione, double fattoreMoltiplicativo) {
		this.modoServizioCorrente = new ModoServizio(id,nome,descrizione,fattoreMoltiplicativo);
		return this.modoServizioCorrente;
	}

	/**
	 * Registra le informazioni della nuova modalita' di servizio. La 
	 * modalita' di servizio viene aggiunta alle modalita' disponibili
	 */
	public void confermaModoServizio() {
		PersistenceFacade.getInstance().salvaModoServizio(this.modoServizioCorrente); 
		this.modiServizio.put(this.modoServizioCorrente.getCodice(),this.modoServizioCorrente);
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
