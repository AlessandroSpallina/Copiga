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
 * @version 0.1
 * 
 */
public class Ordine {

	/* il prezzo totale dell'ordine */ 
	private double prezzo;	
	/* la data di consegna dell'ordine */
	private Date data;	
	/* il cliente che ha effettuato l'ordine */ 
	private Cliente cliente; 
	/* la modalità di servizio delle colazioni ordinate */
	private ModoServizio modoServizio;	
	/* le colazioni ordinate nell'ordine */ 
	private List<ColazioneOrdinata> colazioniOrdinate;	

	/**
	 * Crea un nuovo oggetto di tipo Ordine
	 */
	public Ordine(){
		this.colazioniOrdinate = new LinkedList<ColazioneOrdinata>();
	}
	
	/**
	 * Crea una nuova ColazioneOrdinata
	 * 
	 * @param tc1 il tipo di colazione 
	 * @return la colazione ordinata
	 */
	public ColazioneOrdinata nuovaColazioneOrdinata(TipoColazione tc){
		ColazioneOrdinata co = new ColazioneOrdinata(tc);
		this.colazioniOrdinate.add(co);
		return co;
	}
	
	/**
	 * Definisce la modalità di servizio dell'ordine, ovvero di 
	 * tutte le colazioni appartenenti all'ordine
	 * 
	 * @param ms la modalità di servizio
	 */
	public void setModoServizio(ModoServizio ms){
		this.modoServizio = ms;
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
	 * Restituisce la modalità di servizio 
	 * 
	 * @return la modalità di servizio 
	 */
	public ModoServizio getModoServizio() {
		return this.modoServizio;
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
	 * Calcola il prezzo totale di un ordine 
	 * 
	 * @param ordine l'ordine contesto
	 * @return il prezzo totale
	 */
	public double calcolaPrezzo(){
		double totale; 
		totale = 0;
		for(ColazioneOrdinata co : this.colazioniOrdinate){
			totale = totale + co.calcolaPrezzo();
		}
		/* modifica il prezzo in base alla modalità di consegna */
		totale *= this.modoServizio.getFattoreMoltiplicativo();
		/* definisce il prezzo dell'ordine contesto */
		this.prezzo=totale;
		return totale;
		
	}
	
}
