package it.aps.cdr.dominio;

import java.util.*;

/**
 * Menu della colazione di Rosa.
 * Comprende il catalogo delle descrizioni delle componenti, 
 * i tipi di colazione e i modi di servizio. 
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

public class Menu {

	/* il catalogo delle componenti */
	private Map<String, DescrizioneComponente> descrizioniComponenti;	
	/* il menù dei tipi di colazione */
	private Map<String, TipoColazione> tipiColazione;	
	/* i modi di servizio disponibili */
	private Map<String, ModoServizio> modiServizio;	

	/* il tipo di colazione corrente */ 
	private TipoColazione tipoColazioneCorrente;  
	
	/**
	 * Restituisce il riferimento all'istanza singleton di Menu
	 *
	 * @return il riferimento all'istanza singleton di Menu
	 */

	/**
	 * Crea l'istanza (singleton) di Menu.
	 */
	public Menu() {
		this.modiServizio = new HashMap<String,ModoServizio>();
		this.tipiColazione = new HashMap<String,TipoColazione>();
		this.descrizioniComponenti = new HashMap<String,DescrizioneComponente>();
		/* carica dei dati iniziali di prova */
		caricaDatiProva(); 
	}

	/* Carica dei dati iniziali di prova. */ 
	private void caricaDatiProva() {
		/* inizializza il catalogo dei componenti */
		caricaDescrizioniComponenti();
		/* inizializza le modalità di servizio */
		caricaModiServizio();
		/* inizializza il menù dei tipi di colazione */
		caricaTipiColazione();		
	}

	/* inizializza il catalogo dei componenti */
	private void caricaDescrizioniComponenti() {
		this.descrizioniComponenti.put("a10", new DescrizioneComponente("a10","cornetto",1.0,0.75));
		this.descrizioniComponenti.put("b20", new DescrizioneComponente("b20","caffe",1.20,1.0));
		this.descrizioniComponenti.put("c30", new DescrizioneComponente("c30","latte",1.20,1.10));
		this.descrizioniComponenti.put("d40", new DescrizioneComponente("d40","miele",1.50,1.0));
	}
	
	/* inizializza il menù dei tipi di colazione */
	private void caricaTipiColazione() {
		TipoColazione tc1 = new TipoColazione("tc1","Colazione Italiana");
		tc1.aggiungiComponenteColazione(this.descrizioniComponenti.get("a10"),2);
		tc1.aggiungiComponenteColazione(this.descrizioniComponenti.get("b20"),1);
		tc1.setPrezzo(2);
		tipiColazione.put("tc1",tc1);
		TipoColazione tc2 = new TipoColazione("tc2","Colazione Francese");
		tc2.aggiungiComponenteColazione(this.descrizioniComponenti.get("c30"),1);
		tc2.aggiungiComponenteColazione(this.descrizioniComponenti.get("d40"),2);
		tc2.setPrezzo(3);
		tipiColazione.put("tc2",tc2);
	}

	/* inizializza le modalità di servizio */
	private void caricaModiServizio() {
		ModoServizio ms1 = new ModoServizio("001","normale","tutto di plastica",1.0);
		this.modiServizio.put("001",ms1);
		ModoServizio ms2 = new ModoServizio("002","lusso","tutto d'argento",1.5);
		this.modiServizio.put("002",ms2);
		ModoServizio ms3 = new ModoServizio("003","superiore","tutto d'oro",2.0);
		this.modiServizio.put("003",ms3);
	}

	/**
	 * Restituisce il catalogo delle descrizioni dei prodotti
	 *
	 * @return il catalogo delle descrizioni dei prodotti
	 */
	public Map<String, DescrizioneComponente> getDescrizioniComponenti() {
		return descrizioniComponenti;
	}

	/**
	 * Restituisce il menù delle colazioni disponibili
	 *
	 * @return il menù delle colazioni disponibili
	 */
	public Map<String, TipoColazione> getTipiColazione() {
		return tipiColazione;
	}
	/**
	 * Restituisce le modalità di servizio disponibili
	 *
	 * @return le modalità di servizio disponibili
	 */
	public Map<String, ModoServizio> getModiServizio() {
		return modiServizio;
	}

	/**
	 * Definisce le modalità di servizio disponibili
	 *
	 * @param modiServizio le modalità di servizio disponibili
	 */
	public void setModiServizio(Map<String, ModoServizio> modServizio) {
		modiServizio = modServizio;
	}

	/**
	 * Definisce il catalogo delle componenti registrate dal sistema
	 *
	 * @param descrizioniComponenti il catalogo delle componenti
	 */
	public void setDescrizioniComponenti(Map<String, DescrizioneComponente> catal) {
		descrizioniComponenti = catal;
	}
	
	/**
	 * Restituisce il tipo di colazione corrente
	 *  
	 * @return il tipo di colazione corrente
	 */
	public TipoColazione getTipoColazioneCorrente() {
		return tipoColazioneCorrente;
	}
	
	/**
	 * Definisce il tipo di colazione corrente
	 *  
	 * @param tipoColazioneCorrente il tipo di colazione corrente
	 */
	public void setTipoColazioneCorrente(TipoColazione tipoColazioneCorrente) {
		this.tipoColazioneCorrente = tipoColazioneCorrente;
	}
	
	/* ************************************************************************************ */
	/* ****************** USE CASE 1 : INSERIMENTO NUOVO TIPO COLAZIONE ******************* */
	/* ************************************************************************************ */

	/**
	 * Definisce un nuovo tipo di colazione inizialmente privo di componenti
	 * 
	 * @param codice il codice che identifica il nuovo tipo di colazione
	 * @param nome il nome del nuovo tipo di colazione
	 * @return il nuovo tipo di colazione
	 */
	public TipoColazione nuovoTipoColazione(String codice, String nome){
		this.tipoColazioneCorrente= new TipoColazione(codice,nome);
		return this.tipoColazioneCorrente;
	}
	
	/**
	 * Aggiunge un componente al nuovo tipo di colazione 
	 * 
	 * @param codice il codice del componente
	 * @param quantita la quantità desiderata per il componente
	 * @return true se l'inserimento è andato a buon fine, false altrimenti
	 */
	public boolean aggiungiComponenteColazione(String codice, int quantita){
		boolean risultato = false;
		DescrizioneComponente dc = this.descrizioniComponenti.get(codice);
		if (dc!=null){
			risultato = this.tipoColazioneCorrente.aggiungiComponenteColazione(dc,quantita);
		}
		return risultato;
	}
	
	/**
	 * Definisce il prezzo base per il nuovo tipo di colazione 
	 * 
	 * @param prezzo il prezzo base per il tipo di colazione
	 */
	public void definisciPrezzo(double prezzo){
		this.tipoColazioneCorrente.setPrezzo(prezzo);
	}
	
	/**
	 * Registra le informazioni sul nuovo tipo di colazione. La colazione 
	 * viene aggiunta al menù delle colazioni disponibili
	 *
	 */
	public void confermaTipoColazione(){
		String codice = this.tipoColazioneCorrente.getCodice();
		tipiColazione.put(codice, this.tipoColazioneCorrente);
	}
	
	/* ************************************************************************************ */
	/* **************************** USE CASE 2 : NUOVO ORDINE ***************************** */
	/* ************************************************************************************ */
	
	public DescrizioneComponente getDescrizioneComponente(String codice) {
		DescrizioneComponente dc = descrizioniComponenti.get(codice);
		return dc;
	}

	public TipoColazione getTipoColazione(String codice) {
		TipoColazione tc = tipiColazione.get(codice);
		return tc;
	}

	public ModoServizio getModoServizio(String codice) {
		ModoServizio ms = modiServizio.get(codice);
		return ms;
	}


}


