package it.aps.cdr.dominio;

import java.util.*;

/**
 * Controller per l'accesso al package it.aps.cdr.dominio.
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
public class CdR {
	
	/* l'ordine corrente */
	private Ordine ordineCorrente;	
	/* il tipo di colazione corrente */ 
	private TipoColazione tipoColazioneCorrente;  
	
	/* il catalogo delle componenti */
	private Map<String, DescrizioneComponente> descrizioniComponenti;	
	/* il menù dei tipi di colazione */
	private Map<String, TipoColazione> tipiColazione;	
	/* i clienti registrati */
	private List<Cliente> clienti; 
	/* gli ordini registrati */
	private List<Ordine> ordini;
	/* i modi di servizio disponibili */
	private Map<String, ModoServizio> modiServizio;	

	
	/**
	 * Crea l'istanza (singleton) di CdR.
	 * 
	 * @return il riferimento all'istanza (singleton) di CdR
	 */
	public CdR() {
		this.clienti = new LinkedList<Cliente>();
		this.ordini = new LinkedList<Ordine>();
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
		/* inizializza i clienti */
		caricaClienti();
		/* inizializza le modalità di servizio */
		caricaModiServizio();
		/* inizializza il menù dei tipi di colazione */
		caricaTipiColazione();		
	}

	/* inizializza il catalogo dei componenti */
	private void caricaDescrizioniComponenti() {
		this.descrizioniComponenti.put("a10", new DescrizioneComponente("a10","cornetto"));
		this.descrizioniComponenti.put("b20", new DescrizioneComponente("b20","caffe"));
		this.descrizioniComponenti.put("c30", new DescrizioneComponente("c30","latte"));
		this.descrizioniComponenti.put("d40", new DescrizioneComponente("d40","miele"));
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

	/* inizializza i clienti */
	private void caricaClienti() {
		Cliente c1 = new Cliente("Mario","Rossi","Via Rossi");
		this.clienti.add(c1);
		Cliente c2 = new Cliente("Andrea","Bianchi","Via Bianchi");
		this.clienti.add(c2);
		Cliente c3 = new Cliente("Gabriele","Verdi","Via Verdi");
		this.clienti.add(c3);
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
		
	/* ************************************************************************************ */
	/* *********************************** BEANS ****************************************** */
	/* ************************************************************************************ */
	
	/**
	 * Restituisce il catalogo delle descrizioni dei prodotti
	 * 
	 * @return il catalogo delle descrizioni dei prodotti
	 */
	public Map<String, DescrizioneComponente> getDescrizioniComponenti() {
		return this.descrizioniComponenti;
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
		return this.modiServizio;
	}
	
	/**
	 * Definisce le modalità di servizio disponibili
	 * 
	 * @param modiServizio le modalità di servizio disponibili
	 */
	public void setModiServizio(Map<String, ModoServizio> modiServizio) {
		this.modiServizio = modiServizio;
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
	
	/**
	 * Definisce il catalogo delle componenti registrate dal sistema 
	 * 
	 * @param catalogo il catalogo delle componenti
	 */
	public void setDescrizioniComponenti(Map<String, DescrizioneComponente> descrizioniComponenti) {
		this.descrizioniComponenti = descrizioniComponenti;
	}
	
	/**
	 * Definisce il menù dei tipi di colazione registrati dal sistema 
	 * 
	 * @param tipiColazione il menù dei tipi di colazione
	 */
	public void setTipiColazione(Map<String, TipoColazione> tipiColazione) {
		this.tipiColazione = tipiColazione;
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
		TipoColazione tc = tipiColazione.get(codice);
		ColazioneOrdinata co = null;
		if (tc != null)	{								
			System.out.println(ordineCorrente);
		}
		co = this.ordineCorrente.nuovaColazioneOrdinata(tc);
		return co;
	}
	
	/**
	 * Definisce la modalità di servizio dell'ordine
	 * 
	 * @param codice il codice della modalità di servizio 
	 * @return true se è stato associato il modo servizio all'ordine, false altrimenti 
	 */
	public boolean definisciModoServizio(String codice) {
		boolean ok = false;
		ModoServizio ms = this.modiServizio.get(codice);
		if (ms != null) {									
			this.ordineCorrente.setModoServizio(ms);
			ok = true; 
		}
		return ok;
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
