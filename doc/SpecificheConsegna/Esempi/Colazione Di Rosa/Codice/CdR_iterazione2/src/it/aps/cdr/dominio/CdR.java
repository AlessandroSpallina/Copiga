package it.aps.cdr.dominio;

import java.util.*;

/**
 * Controller per l'accesso al package it.aps.cdr.dominio, 
 * acceduto come Singleton.
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

public class CdR {
	
	/* istanza singleton di CdR */ 
	private static CdR singleton;

	/* l'ordine corrente */
	private Ordine ordineCorrente;	
	
	/* il menu (comprende descrizioni componenti, tipi di colazione e modi di servizio) */ 
	private Menu menu; 

	/* i clienti registrati */
	private List<Cliente> clienti; 
	/* gli ordini registrati */
	private List<Ordine> ordini;

	/**
	 * Restituisce il riferimento all'istanza singleton di CdR
	 * 
	 * @return il riferimento all'istanza singleton di CdR
	 */
	public static CdR getInstance(){
		if (singleton == null)
			singleton = new CdR();
		return singleton;
	}
		
	/**
	 * Crea l'istanza (singleton) di CdR.
	 */
	private CdR() {
		this.clienti = new LinkedList<Cliente>();
		this.ordini = new LinkedList<Ordine>();
		this.menu = new Menu(); 
		/* carica dei dati iniziali di prova */
		caricaDatiProva(); 
	}
	
	/* Carica dei dati iniziali di prova. */ 
	private void caricaDatiProva() {
		/* inizializza i clienti */
		caricaClienti();
	}

	/* inizializza i clienti */
	private void caricaClienti() {
		Cliente c1 = new Cliente("Mario","Rossi","Via Rossi");
		this.clienti.add(c1);
		Cliente c2 = new Cliente("Andrea","Bianchi","Via Bianchi");
		this.clienti.add(c2);
		Cliente c3 = new Cliente("Gabriele","Verdi","Via Verdi");
		this.clienti.add(c3);
	}
		
	/* ************************************************************************************ */
	/* *********************************** BEANS ****************************************** */
	/* ************************************************************************************ */
	
	/**
	 * Restituisce il catalogo delle descrizioni dei prodotti
	 * 
	 * @return il catalogo delle descrizioni dei prodotti
	 */
	public Map<String, DescrizioneComponente> getDescrizioniComponenti() {
		return menu.getDescrizioniComponenti();
	}
	
	/**
	 * Restituisce il menù delle colazioni disponibili
	 * 
	 * @return il menù delle colazioni disponibili
	 */
	public Map<String, TipoColazione> getTipiColazione() {
		return menu.getTipiColazione();
	}
	
	/**
	 * Restituisce i clienti registrati nel sistema 
	 * 
	 * @return i clienti registrati nel sistema
	 */
	public List<Cliente> getClienti() {
		return this.clienti;
	}
	
	/**
	 * Definisce i clienti registrati nel sistema 
	 * 
	 * @param clienti i clienti registrati nel sistema
	 */
	public void setClienti(List<Cliente> clienti) {
		this.clienti = clienti;
	}
	
	/**
	 * Restituisce le modalità di servizio disponibili 
	 * 
	 * @return le modalità di servizio disponibili
	 */
	public Map<String, ModoServizio> getModiServizio() {
		return menu.getModiServizio();
	}
	
	/**
	 * Restituisce l'ordine corrente
	 * 
	 * @return l'ordine corrente 
	 */
	public Ordine getOrdineCorrente() {
		return ordineCorrente;
	}
	
	/**
	 * Definisce l'ordine corrente
	 *  
	 * @param ordineCorrente l'ordine corrente
	 */
	public void setOrdineCorrente(Ordine ordineCorrente) {
		this.ordineCorrente = ordineCorrente;
	}
	
	/**
	 * Restituisce gli ordini registrati dal sistema
	 *  
	 * @return gli ordini registrati dal sistema
	 */
	public List<Ordine> getOrdini() {
		return ordini;
	}
	
	/**
	 * Definisce gli ordini registrati dal sistema
	 *  
	 * @param ordini gli ordini registrati dal sistema
	 */
	public void setOrdini(List<Ordine> ordini) {
		this.ordini = ordini;
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
		return this.menu.nuovoTipoColazione(codice, nome);
	}
	
	/**
	 * Aggiunge un componente al nuovo tipo di colazione 
	 * 
	 * @param codice il codice del componente
	 * @param quantita la quantità desiderata per il componente
	 * @return true se l'inserimento è andato a buon fine, false altrimenti
	 */
	public boolean aggiungiComponenteColazione(String codice, int quantita){
		return this.menu.aggiungiComponenteColazione(codice, quantita); 
	}
	
	/**
	 * Definisce il prezzo base per il nuovo tipo di colazione 
	 * 
	 * @param prezzo il prezzo base per il tipo di colazione
	 */
	public void definisciPrezzo(double prezzo){
		this.menu.definisciPrezzo(prezzo);
	}
	
	/**
	 * Registra le informazioni sul nuovo tipo di colazione. La colazione 
	 * viene aggiunta al menù delle colazioni disponibili
	 *
	 */
	public void confermaTipoColazione(){
		this.menu.confermaTipoColazione();
	}
	
	/* ************************************************************************************ */
	/* **************************** USE CASE 2 : NUOVO ORDINE ***************************** */
	/* ************************************************************************************ */
	
	/**
	 * Crea un nuovo ordine inizialmente privo di colazioni ordinate
	 * 
	 * @return il nuovo ordine
	 */
	public Ordine nuovoOrdine(){
		this.ordineCorrente = new Ordine();
		return this.ordineCorrente;
	}
	
	/**
	 * Crea una nuova colazione da associare all'ordine corrente  
	 * 
	 * @param codice il codice del tipo di colazione
	 * @return la colazione ordinata, null se non esiste un tipo
	 * 		   di colazione associato a codice	  
	 */
	public ColazioneOrdinata nuovaColazioneOrdinata(String codice) { 
		TipoColazione tc = menu.getTipoColazione(codice);
		ColazioneOrdinata co = null;
		if (tc != null)	{								
			System.out.println(ordineCorrente);
		}
		co = this.ordineCorrente.nuovaColazioneOrdinata(tc);
		return co;
	}
	
	/**
	 * Modifica la quantita' di una componente della colazione ordinata 
	 * nell'ordine corrente. Rimuove la componente se la quantita' a' 
	 * pari a 0 
	 * @param descrizioneComponenteID il codice della componente da modificare
	 * @param quantita la nuova quantita' desiderata
	 * @return true se la modifica e' stata effettuata, false altrimenti
	 */
	public boolean modificaCompColazioneOrdinata(String descrizioneComponenteID, int quantita){
		boolean risultato = false;
		DescrizioneComponente dc = this.menu.getDescrizioneComponente(descrizioneComponenteID);
		if (dc != null) {
			risultato = this.ordineCorrente.modificaCompColazioneOrdinata(dc,quantita);
		}
		return risultato;
	}
	
	/**
	 * Aggiunge una nuova componente e relativa quantita' alla colazione ordinata 
	 * nell'ordine corrente 
	 * @param descrizioneComponenteID il codice della nuova componente da aggiungere 
	 * @param quantita la quantita' desiderata della nuova componente
	 * @return true se la componente e' stata aggiunta, false altrimenti
	 */
	public boolean aggiungiCompColazioneOrdinata(String descrizioneComponenteID, int quantita) {
		boolean risultato = false;
		DescrizioneComponente dc = this.menu.getDescrizioneComponente(descrizioneComponenteID);
		if (dc != null) {
			risultato = this.ordineCorrente.aggiungiCompColazioneOrdinata(dc,quantita);
		}
		return risultato;
	}

	/**
	 * Definisce la modalita' di servizio alla colazione ordinata 
	 * nell'ordina corrente
	 * @param modoServizioID il codice della modalita' di servizio 
	 * @return true se la modalita' e' stata definita, false altrimenti
	 */
	public boolean definisciModoServizio(String modoServizioID) {
		boolean risultato = false;
		ModoServizio modoServizio = this.menu.getModoServizio(modoServizioID);
		if (modoServizio != null) {
			this.ordineCorrente.definisciModoServizio(modoServizio);
			risultato = true;
		}
		return risultato;
	}
	
	/**
	 * Calcola il prezzo della colazione corrente dell'ordine corrente
	 * 
	 * @return il prezzo della colazione corrente dell'ordine corrente  
	 */
	public double calcolaPrezzoColazione() {
		return this.ordineCorrente.calcolaPrezzoColazioneCorrente();
	}
	
	/**
	 * Calcola il prezzo dell'ordine corrente
	 * 
	 * @return il prezzo dell'ordine corrente  
	 */
	public double calcolaPrezzoOrdine() {
		return this.ordineCorrente.calcolaPrezzo();
	}
	
	/**
	 * Associa un cliente all'ordine
	 * 
	 * @param nome il nome del cliente
	 * @param cognome il cognome del cliente
	 * @param indirizzo l'indirizzo del cliente e della consegna dell'ordine
	 * @param data la data e l'ora della consegna
	 */
	public void associaOrdineCliente(String nome, String cognome, String indirizzo, Date data){
		Cliente cl = trovaCliente(nome,cognome,indirizzo);
		if (cl == null){
			cl = new Cliente(nome,cognome,indirizzo);
			this.clienti.add(cl);
		}
		this.ordineCorrente.setCliente(cl);
		this.ordineCorrente.setData(data);
	}

	/* Ricerca il cliente, sulla base di nome, cognome e indirizzo. */
	private Cliente trovaCliente(String nome, String cognome, String indirizzo) {
		Cliente cliente = null;
		for(Cliente c : this.clienti)
			if (nome.equals(c.getNome()) && cognome.equals(c.getCognome()) && 
				indirizzo.equals(c.getIndirizzo()))
				cliente = c;
		return cliente;
	}
		
	/**
	 * Registra l'ordine.
	 */
	public void confermaOrdine() {
		ordini.add(this.ordineCorrente);
	}

}
