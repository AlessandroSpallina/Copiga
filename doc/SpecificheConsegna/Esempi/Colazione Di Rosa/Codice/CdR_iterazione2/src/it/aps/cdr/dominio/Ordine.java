package it.aps.cdr.dominio;

import java.util.*;

/**
 * Un oggetto di tipo Ordine rappresenta un ordine costituito 
 * da un insieme di colazioni ordinate da consegnare secondo una 
 * specifica modalità di servizio.
 * 
 * @see ColazioneOrdinata
 * @see ModoServizio
 * 
 * @author Luca Cabibbo
 * @author Marco Canu
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.2
 * 
 */
public class Ordine {

	/* il prezzo totale dell'ordine */ 
	private double prezzo;	
	/* la data di consegna dell'ordine */
	private Date data;	
	/* il cliente che ha effettuato l'ordine */ 
	private Cliente cliente; 
	/* le colazioni ordinate nell'ordine */ 
	private List<ColazioneOrdinata> colazioniOrdinate;	
	/* la colazione corrente dell'ordine */
	private ColazioneOrdinata colazioneOrdinataCorrente;	
	/* lo stato di consegna dell'ordine */
	private boolean consegnato;

	/**
	 * Crea un nuovo oggetto di tipo Ordine
	 */
	public Ordine(){
		this.colazioniOrdinate = new LinkedList<ColazioneOrdinata>();
		this.consegnato = false;
	}
	
	/**
	 * Crea una nuova colazione ordinata
	 * @param tipoColazione il tipo di colazione 
	 * @return la colazione ordinata
	 */
	public ColazioneOrdinata nuovaColazioneOrdinata(TipoColazione tipoColazione){
		this.colazioneOrdinataCorrente = new ColazioneOrdinata(tipoColazione);
		this.colazioniOrdinate.add(this.colazioneOrdinataCorrente);
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
	 * Modifica la quantita' di una componente della colazione ordinata 
	 * nell'ordine corrente. Rimuove la componente se la quantita' e' 
	 * pari a 0 
	 * @param codice il codice della componente da modificare
	 * @param quantita la nuova quantita' desiderata
	 * @return true se la modifica e' stata effettuata, false altrimenti
	 */
	public boolean modificaCompColazioneOrdinata(DescrizioneComponente dc, int quantita) {
		return this.colazioneOrdinataCorrente.modificaComponente(dc,quantita);
	}

	/**
	 * Definisce la modalita' di servizio della colazione ordinata corrente
	 * @param modoServizio la modalita' di servizio
	 */
	public void definisciModoServizio(ModoServizio modoServizio) {
		this.colazioneOrdinataCorrente.setModoServizio(modoServizio);
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

	/**
	 * Associa un cliente all'ordine
	 * 
	 * @param cl il cliente registrato 
	 */
	public void setCliente(Cliente cl) {
		this.cliente = cl;
	}
	
	/**
	 * Associa una data ed un'ora di consegna all'ordine
	 * 
	 * @param data la data e l'ora di consegna
	 */
	public void setData(Date data) {
		this.data = data;
	}
		
	/**
	 * Restituisce le colazioni ordinate 
	 * 
	 * @return le colazioni ordinate 
	 */
	public List<ColazioneOrdinata> getColazioniOrdinate() {
		return this.colazioniOrdinate;
	}
	
	/**
	 * Restituisce il cliente
	 * 
	 * @return il cliente
	 */
	public Cliente getCliente() {
		return this.cliente;
	}
	
	/**
	 * Assegna un prezzo all'ordine
	 * 
	 * @param prezzo il prezzo dell'ordine
	 */
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	
	/**
	 * Restituisce la data di consegna dell'ordine 
	 * 
	 * @return la data di consegna dell'ordine 
	 */
	public Date getData() {
		return this.data;
	}
	
	/**
	 * Restituisce il prezzo dell'ordine 
	 * 
	 * @return il prezzo dell'ordine 
	 */
	public double getPrezzo() {
		return this.prezzo;
	}
	
	/**
	 * Definisce le colazioni appartenenti all'ordine 
	 * 
	 * @param colazioniOrdinate le colazioni appartenenti all'ordine
	 */
	public void setColazioniOrdinate(List<ColazioneOrdinata> colazioniOrdinate) {
		this.colazioniOrdinate = colazioniOrdinate;
	}
		
	/**
	 * Calcola il prezzo della colazione corrente 
	 * @param ordine l'ordine contesto
	 * @return il prezzo totale
	 */
	public double calcolaPrezzoColazioneCorrente(){
		return this.colazioneOrdinataCorrente.calcolaPrezzo();
	}
	
	/**
	 * Calcola il prezzo totale di un ordine 
	 * @param ordine l'ordine contesto
	 * @return il prezzo totale
	 */
	public double calcolaPrezzo(){
		double totale = 0;
		for(ColazioneOrdinata co : colazioniOrdinate){
			totale += co.calcolaPrezzo();
		}
		/* definisce il prezzo dell'ordine contesto */
		this.prezzo=totale;
		return totale;
	}
	
	/**
	 * Restituisce una rappresentazione testuale dell'ordine
	 * @return la rappresentazione testuale dell'ordine
	 */
	public String toString(){
		StringBuffer result = new StringBuffer();
		result.append("   DATA :\t" + this.data);
		result.append("\n");
		result.append("   CLIENTE :\t" + this.cliente);
		result.append("\n");
		result.append("   PREZZO :\t" + this.prezzo);
		result.append("\n");
		result.append("   CONSEGNATO :\t" + this.consegnato);
		return result.toString();
	}

}
