package it.aps.cdr.dominio;

import it.aps.cdr.eventi.Evento;
import it.aps.cdr.eventi.PropertyListener;
import it.aps.cdr.logging.Logger;
import it.aps.cdr.persistenza.PersistenceFacade;
import it.aps.cdr.persistenza.dao.DAOException;

import java.util.*;

/**
 * Controller per l'accesso al package it.psi.cdr.dominio, 
 * acceduto come un singleton.
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class CdR {
	
	/** versione */ 
	public static final String VERSIONE = "0.4.6"; 
	
	/** l'istanza singleton di CdR */
	private static CdR singleton;	
	/** il menu delle colazioni offerte da Rosa, anche con componenti e modi di servizio */
	private Menu menu;
	/** gli ordini gestiti da CdR */
	// private Map<OrdineID,Ordine> ordini;
	/** l'ordine corrente */
	private Ordine ordineCorrente;
	/** listeners interessati ai cambiamenti degli ordini */
	private List<PropertyListener> listeners;
	
	/** Crea un nuovo CdR */ 
	protected CdR() {
		// Map<OrdineID,Ordine> ordini = new HashMap<OrdineID,Ordine>();
		
		/* crea gli altri oggetti singleton di dominio */ 
		this.menu = new Menu();		
		// this.ordini = ordini;
		GestoreClienti gestoreClienti = GestoreClienti.getInstance();

		this.listeners = new LinkedList<PropertyListener>();

		/* carica tutte le informazioni registrate nella base di dati */
		this.ricaricaDati(); 
	}
	
	/** 
	 * Restituisce il riferimento all'istanza singleton di CdR
	 * @return il riferimento all'istanza singleton di CdR 
	 */
	public static synchronized CdR getInstance(){
		if (singleton == null)
			singleton = new CdR();
		return singleton;
	}
		
	/**
	 * Carica/ricarica i dati in memoria dalla base di dati, 
	 * e li fa ricaricare a tutti gli altri macro-oggetti di dominio collegati. 
	 */
	public void ricaricaDati() {
		/* ricarica i dati sugli ordini */ 
/*
		try {
			// ordini = DAOOrdine.getInstance().doSelect();
		} catch (DAOException e) {
			e.printStackTrace();
			System.out.println("   Errore nell'inizializzazione");
		}
*/
		/* fa ricaricare agli altri macro-oggetti di dominio i propri dati */ 
		this.menu.ricaricaDati(); 
		GestoreClienti.getInstance().ricaricaDati(); 
		this.publishPropertyEvent(Evento.RICARICA_DATI,null);
	}
		
	/* ************************************************************************************ */
	/* ************************************** GETTERS ************************************* */
	/* ************************************************************************************ */
	
	/**
	 * Restituisce la categoria di cliente corrente
	 * @return la categoria di cliente corrente
	 */
	public CategoriaCliente getCategoriaClienteCorrente(){
		return GestoreClienti.getInstance().getCategoriaClienteCorrente();
	}
	
	/**
	 * Restituisce le categorie di clienti registrate nel sistema
	 * @return le categorie di clienti
	 */
	public Collection<CategoriaCliente> getCategorieClienti() {
		return GestoreClienti.getInstance().getCategorieClienti();
	}
	
	/**
	 * Restituisce il cliente corrente
	 * @return il cliente corrente
	 */
	public Cliente getClienteCorrente(){
		return GestoreClienti.getInstance().getClienteCorrente();
	}
	
	/**
	 * Restituisce i clienti registrati nel sistema
	 * @return i clienti
	 */
	public Collection<Cliente> getClienti() {
		return GestoreClienti.getInstance().getClienti();
	}
	
	/**
	 * Restituisce la descrizione della componente corrente
	 * @return la descrizione della componente corrente 
	 */
	public DescrizioneComponente getDescrizioneComponenteCorrente(){
		return this.menu.getDescrizioneComponenteCorrente();
	}
	
	/**
	 * Restituisce il menu delle colazioni offerte da Rosa
	 * @return il menu delle colazioni
	 */
	public Menu getMenu() {
		return this.menu;
	}
	
	/**
	 * Restituisce la modalita' di servizio corrente
	 * @return la modalita' di servizio corrente
	 */
	public ModoServizio getModoServizioCorrente() {
		return this.menu.getModoServizioCorrente();
	}
	
	/**
	 * Restituisce la modalita' di servizio associata al codice 
	 * modoServizioID
	 * @param modoServizioID il codice della modalita' di servizio
	 * @return la modalita' di servizio
	 */
	public ModoServizio getModoServizio(ModoServizioID modoServizioID) {
		return this.menu.getModoServizio(modoServizioID);
	}
	
	/**
	 * Restituisce la modalita' di servizio associata a nomeModoServizio
	 * @param nomeModoServizio il nome della modalita' di servizio
	 * @return la modalita' di servizio
	 */
	public ModoServizio getModoServizio(String nomeModoServizio) {
		return this.menu.getModoServizio(nomeModoServizio);
	}
	
	/**
	 * Restituisce le modalita' di servizio registrate nel sistema
	 * @return le modalita' di servizio
	 */
	public Collection<ModoServizio> getModiServizio() {
		return this.menu.getModiServizio();
	}
	
	/**
	 * Restituisce l'ordine corrente
	 * @return l'ordine corrente 
	 */
	public Ordine getOrdineCorrente() {
		return this.ordineCorrente;
	}
	
	/**
	 * Restituisce l'ordine associato a ordineID
	 * @param ordineID il codice associato all'ordine
	 * @return l'ordine cercato
	 */
	public Ordine getOrdine(OrdineID ordineID) {
		return PersistenceFacade.getInstance().trovaOrdine(ordineID);
	}
	
	/**
	 * Restituisce gli ordini registrati nel sistema
	 * @return gli ordini
	 */
	public Collection<Ordine> getOrdini() {
		return PersistenceFacade.getInstance().trovaOrdini();
	}
			
	/**
	 * Restituisce il tipo di colazione corrente
	 * @return il tipo di colazione corrente
	 */
	public TipoColazione getTipoColazioneCorrente(){
		return this.menu.getTipoColazioneCorrente();
	}
		
	/* ************************************************************************************ */
	/* ************************************** SETTERS ************************************* */
	/* ************************************************************************************ */
	
	/**
	 * Definisce la modalita' di servizio corrente
	 * @param modoServizioCorrente la modalita' di servizio corrente
	 */
	public void setModoServizioCorrente(ModoServizio modoServizioCorrente) {
		this.menu.setModoServizioCorrente(modoServizioCorrente);
	}
	
	/**
	 * Definisce le modalita' di servizio 
	 * @param modiServizio le modalita' di servizio
	 */
	public void setModiServizio(Map<ModoServizioID, ModoServizio> modiServizio) {
		this.menu.setModiServizio(modiServizio);
	}

	/**
	 * Definisce l'ordine corrente
	 * @param ordineCorrente l'ordine corrente
	 */
	public void setOrdineCorrente(Ordine ordineCorrente) {
		this.ordineCorrente = ordineCorrente;
	}

	/**
	 * Definisce il menu
	 * @param menu il menu
	 */
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
	/* ************************************************************************************ */
	/* ************************************** RICERCA ************************************* */
	/* ************************************************************************************ */
	
	/**
	 * Restituisce il cliente registrato associato al codice specificato
	 * @param clienteID il codice del cliente
	 * @return il cliente associato a clienteID
	 */
	public Cliente trovaCliente(ClienteID clienteID) {
		return GestoreClienti.getInstance().trovaCliente(clienteID);
	}
	
	/**
	 * Restituisce l'insieme dei clienti registrati aventi il nome e 
	 * cognome dato 
	 * @param nome il nome del cliente
	 * @param cognome il cognome del cliente
	 * @return l'insieme dei clienti omonimi
	 */
	public Collection<Cliente> trovaCliente(String nome, String cognome) {
		return GestoreClienti.getInstance().trovaClienti(nome,cognome);
	}
	
	/**
	 * Restituisce il tipo di colazione associato a tipoColazioneID
	 * @param tipoColazioneID il codice del tipo di colazione
	 * @return il tipo di colazione
	 */
	public TipoColazione trovaTipoColazione(TipoColazioneID tipoColazioneID) {
		return this.menu.getTipoColazione(tipoColazioneID);
	}
	
	/**
	 * Restituisce il tipo di colazione associato a nomeTipoColazione
	 * @param nomeTipoColazione il nome del tipo di colazione
	 * @return il tipo di colazione
	 */
	public TipoColazione trovaTipoColazione(String nomeTipoColazione) {
		return this.menu.getTipoColazione(nomeTipoColazione);
	}
	
	/**
	 * Restituisce la descrizione della componente associata a descrizioneComponenteID
	 * @param descrizioneComponenteID il codice della descrizione della componente
	 * @return la descrizione della componente
	 */
	public DescrizioneComponente trovaDescrizioneComponente(DescrizioneComponenteID descrizioneComponenteID) {
		return this.menu.trovaDescrizioneComponente(descrizioneComponenteID);
	}
	
	/**
	 * Restituisce la descrizione della componente associata a nomeDescrizioneComponente
	 * @param nomeDescrizioneComponente il nome della descrizione della componente
	 * @return la descrizione della componente
	 */
	public DescrizioneComponente trovaDescrizioneComponente(String nomeDescrizioneComponente) {
		return this.menu.trovaDescrizioneComponente(nomeDescrizioneComponente);
	}
	
	/**
	 * Restituisce la categoria di cliente associata a categoriaClienteID
	 * @param categoriaClienteID il codice della categoria di cliente
	 * @return la categoria di cliente
	 */
	public CategoriaCliente trovaCategoriaCliente(CategoriaClienteID categoriaClienteID) {
		return GestoreClienti.getInstance().trovaCategoriaCliente(categoriaClienteID);
	}
	
	/**
	 * Restituisce la categoria di cliente associata a nomeCategoria
	 * @param nomeCategoria il nome della categoria di cliente
	 * @return la categoria di cliente
	 */
	public CategoriaCliente trovaCategoriaCliente(String nomeCategoria) {
		return GestoreClienti.getInstance().trovaCategoriaCliente(nomeCategoria);
	}
	
	/**
	 * Restituisce la categoria di cliente associata a ordini
	 * @param ordini gli ordini della categoria di cliente
	 * @return la categoria di cliente
	 */
	public CategoriaCliente trovaCategoriaCliente(int ordini) {
		return GestoreClienti.getInstance().trovaCategoriaCliente(ordini);
	}
	
	/**
	 * Restituisce il modo servizio associato a modoServizioID
	 * @param modoServizioID il codice della modalita' di servizio
	 * @return la modalita' di servizio
	 */
	public ModoServizio trovaModoServizio(ModoServizioID modoServizioID) {
		return this.menu.trovaModoServizio(modoServizioID);
	}
	
	/**
	 * Restituisce l'ordine associato a ordineID
	 * @param ordineID il codice dell'ordine
	 * @return l'ordine
	 */
	public Ordine trovaOrdine(OrdineID ordineID) {
		return this.getOrdine(ordineID);
	}
	
	/* ************************************************************************************ */
	/* *********************************** CANCELLAZIONE ********************************** */
	/* ************************************************************************************ */
	
	/**
	 * Cancella il tipo di colazione associato a tipoColazioneID
	 * @param tipoColazioneID il codice del tipo di colazione
	 * @return il tipo di colazione
	 */
	public TipoColazione cancellaTipoColazione(TipoColazioneID tipoColazioneID) {
		return this.menu.cancellaTipoColazione(tipoColazioneID);
	}
	
	/**
	 * Cancella la descrizione della componente associata a descrizioneComponenteID
	 * @param descrizioneComponenteID il codice della descrizione della componente
	 * @return la descrizione della componente
	 */
	public DescrizioneComponente cancellaDescrizioneComponente(DescrizioneComponenteID descrizioneComponenteID) {
		return this.menu.cancellaDescrizioneComponente(descrizioneComponenteID);
	}
	
	/**
	 * Cancella l'ordine associato a ordineID
	 * @param ordineID il codice dell'ordine
	 * @return l'ordine
	 */
	public Ordine cancellaOrdine(OrdineID ordineID) {
		Ordine o = this.trovaOrdine(ordineID); 
		PersistenceFacade.getInstance().cancellaOrdine(ordineID); 
		return o; 
		//// return this.ordini.remove(ordineID);		
	}
	
	/**
	 * Cancella il cliente associato a clienteID
	 * @param clienteID il codice del cliente
	 * @return il cliente
	 */
	public Cliente cancellaCliente(ClienteID clienteID) {
		return GestoreClienti.getInstance().cancellaCliente(clienteID);
	}
		
	/**
	 * Cancella la categoria di cliente associata a categoriaCLienteID
	 * @param categoriaClienteID il codice della categoria di cliente
	 * @return la categoria di cliente
	 */
	public CategoriaCliente cancellaCategoriaCliente(CategoriaClienteID categoriaClienteID) {
		return GestoreClienti.getInstance().cancellaCategoriaCliente(categoriaClienteID);
	}
	
	/**
	 * Cancella la modalita' di servizio associata a modoServizioID
	 * @param modoServizioID il codice della modalita' di servizio
	 * @return la modalita' di servizio
	 */
	public ModoServizio cancellaModoServizio(ModoServizioID modoServizioID) {
		return this.menu.cancellaModoServizio(modoServizioID);
	}

	/* ************************************************************************************ */
	/* ******************** USE CASE A : INSERIMENTO NUOVA COMPONENTE ********************* */
	/* ************************************************************************************ */
	
	/**
	 * Definisce una nuova componente
	 * @return la nuova componente
	 */
	public DescrizioneComponente nuovaDescrizioneComponente(String nome, Prezzo prezzoAggiuntivo,Prezzo prezzoRiduttivo){
		return this.menu.nuovaDescrizioneComponente(nome,prezzoAggiuntivo,prezzoRiduttivo);
	}
	
	/**
	 * Definisce una nuova componente
	 * @return la nuova componente
	 */
	public DescrizioneComponente nuovaDescrizioneComponente(DescrizioneComponenteID id, String nome, Prezzo prezzoAggiuntivo,Prezzo prezzoRiduttivo){
		return this.menu.nuovaDescrizioneComponente(id,nome,prezzoAggiuntivo,prezzoRiduttivo);
	}
	
	/**
	 * Registra le informazioni della nuova componente. La componente 
	 * viene aggiunta al catalogo delle componenti disponibili
	 */
	public void confermaDescrizioneComponente() {
		this.menu.confermaDescrizioneComponente();
	}
	
	/* ************************************************************************************ */
	/* ********************** USE CASE B : NUOVO MODO SERVIZIO **************************** */
	/* ************************************************************************************ */
	
	/**
	 * Definisce una nuova modalita' di servizio
	 * @return la nuova modalita' di servizio
	 */
	public ModoServizio nuovoModoServizio(String nome, String descrizione, double fattoreMoltiplicativo) {
		return this.menu.nuovoModoServizio(nome, descrizione, fattoreMoltiplicativo);
	}
	
	/**
	 * Definisce una nuova modalita' di servizio
	 * @return la nuova modalita' di servizio
	 */
	public ModoServizio nuovoModoServizio(ModoServizioID id, String nome, String descrizione, double fattoreMoltiplicativo) {
		return this.menu.nuovoModoServizio(id, nome, descrizione, fattoreMoltiplicativo);
	}

	/**
	 * Registra le informazioni della nuova modalita' di servizio. La 
	 * modalita' di servizio viene aggiunta alle modalita' disponibili
	 */
	public void confermaModoServizio() {
		this.menu.confermaModoServizio();
	}
	
	/* ************************************************************************************ */
	/* ******************** USE CASE C : NUOVA CATEGORIA CLIENTE ************************** */
	/* ************************************************************************************ */
	
	/**
	 * Definisce una nuova categoria di cliente
	 * @return la nuova categoria di cliente
	 */
	public CategoriaCliente nuovaCategoriaCliente(String nome, String descrizione, double fattoreCliente, int ordini){
		return GestoreClienti.getInstance().nuovaCategoriaCliente(nome,descrizione,fattoreCliente,ordini);
	}
	
	/**
	 * Definisce una nuova categoria di cliente
	 * @return la nuova categoria di cliente
	 */
	public CategoriaCliente nuovaCategoriaCliente(CategoriaClienteID id, String nome, String descrizione, double fattoreCliente, int ordini){
		return GestoreClienti.getInstance().nuovaCategoriaCliente(id,nome,descrizione,fattoreCliente,ordini);
	}
	
	/**
	 * Registra le informazioni della nuova categoria di cliente. La 
	 * categoria di cliente viene aggiunta alle categorie disponibili
	 */
	public void confermaCategoriaCliente() {
		GestoreClienti.getInstance().confermaCategoriaCliente();
	}
	
	/* ************************************************************************************ */
	/* ********************** USE CASE D : GESTIONE DEI CLIENTI *************************** */
	/* ************************************************************************************ */
	
	/**
	 * Definisce un nuovo cliente
	 * @param nome il nome del cliente
	 * @param cognome il cognome del cliente
	 * @param indirizzo l'indirizzo del cliente
	 * @return il nuovo cliente
	 */
	public Cliente nuovoCliente(String nome, String cognome, String indirizzo) {
		return GestoreClienti.getInstance().nuovoCliente(nome,cognome,indirizzo);
	}
	
	/**
	 * Definisce un nuovo cliente
	 * @param id il codice associato al cliente
	 * @param nome il nome del cliente
	 * @param cognome il cognome del cliente
	 * @param indirizzo l'indirizzo del cliente
	 * @return il nuovo cliente
	 */
	public Cliente nuovoCliente(ClienteID id, String nome, String cognome, String indirizzo) {
		return GestoreClienti.getInstance().nuovoCliente(id,nome,cognome,indirizzo);
	}
		
	/**
	 * Registra le informazioni del nuovo cliente. Il cliente viene aggiunto 
	 * ai clienti registrati
	 */
	public void confermaCliente() {
		GestoreClienti.getInstance().confermaCliente();
	}

	/* ************************************************************************************ */
	/* ****************** USE CASE 1 : INSERIMENTO NUOVO TIPO COLAZIONE ******************* */
	/* ************************************************************************************ */
	
	/**
	 * Definisce un nuovo tipo di colazione inizialmente privo di componenti
	 * @param nome il nome del nuovo tipo di colazione
	 * @return il nuovo tipo di colazione privo di componenti
	 */
	public TipoColazione nuovoTipoColazione(String nome){
		return this.menu.nuovoTipoColazione(nome);
	}
	
	/**
	 * Aggiunge una componente al nuovo tipo di colazione 
	 * @param descrizioneComponenteID il codice della componente
	 * @param quantita la quantita' desiderata per la componente
	 * @return true se l'inserimento e' andato a buon fine, false altrimenti
	 */
	public boolean aggiungiComponenteColazione(DescrizioneComponenteID descrizioneComponenteID, int quantita) {
		return this.menu.aggiungiComponenteColazione(descrizioneComponenteID,quantita);
	}
/* 
 	public boolean aggiungiComponenteColazione(DescrizioneComponenteID descrizioneComponenteID, int quantita) {
		boolean risultato = false;
		DescrizioneComponente dc = this.menu.trovaDescrizioneComponente(descrizioneComponenteID);
		if (dc != null) {
			risultato = this.menu.aggiungiComponenteColazione(dc,quantita);
		}
		return risultato;
	}
*/

	/**
	 * Definisce il prezzo base per il nuovo tipo di colazione 
	 * @param prezzo il prezzo base per il tipo di colazione
	 */
	public void definisciPrezzo(Prezzo prezzo){
		this.menu.definisciPrezzo(prezzo);
	}
	
	/**
	 * Registra le informazioni sul nuovo tipo di colazione. La colazione 
	 * viene aggiunta al menu delle colazioni disponibili
	 */
	public void confermaTipoColazione() {
		this.menu.confermaTipoColazione();
	}
	
	/* ************************************************************************************ */
	/* **************************** USE CASE 2 : NUOVO ORDINE ***************************** */
	/* ************************************************************************************ */
	
	/**
	 * Crea un nuovo ordine inizialmente privo di colazioni ordinate
	 * @return il nuovo ordine
	 */
	public Ordine nuovoOrdine(){
		this.ordineCorrente = new Ordine();
		return this.ordineCorrente;
	}
	
	/**
	 * Crea una nuova colazione da associare all'ordine corrente  
	 * @param tipoColazioneID il codice del tipo di colazione
	 * @return la colazione ordinata, null se non esiste un tipo
	 * di colazione associato a tipoColazioneID	  
	 */
	public ColazioneOrdinata nuovaColazioneOrdinata(TipoColazioneID tipoColazioneID) {
		ColazioneOrdinata colazioneOrdinata = null;
		TipoColazione tc = this.menu.getTipoColazione(tipoColazioneID);
		if (tc != null) {
			colazioneOrdinata = this.ordineCorrente.nuovaColazioneOrdinata(tc);
		}
		return colazioneOrdinata;
	}
	
	/**
	 * Modifica la quantita' di una componente della colazione ordinata 
	 * nell'ordine corrente. Rimuove la componente se la quantita' a' 
	 * pari a 0 
	 * @param descrizioneComponenteID il codice della componente da modificare
	 * @param quantita la nuova quantita' desiderata
	 * @return true se la modifica e' stata effettuata, false altrimenti
	 */
	public boolean modificaCompColazioneOrdinata(DescrizioneComponenteID descrizioneComponenteID, int quantita){
		DescrizioneComponente dc = this.menu.trovaDescrizioneComponente(descrizioneComponenteID);
		return this.ordineCorrente.modificaCompColazioneOrdinata(dc,quantita);
	}
	
	/**
	 * Aggiunge una nuova componente e relativa quantita' alla colazione ordinata 
	 * nell'ordine corrente 
	 * @param descrizioneComponenteID il codice della nuova componente da aggiungere 
	 * @param quantita la quantita' desiderata della nuova componente
	 * @return true se la componente e' stata aggiunta, false altrimenti
	 */
	public boolean aggiungiCompColazioneOrdinata(DescrizioneComponenteID descrizioneComponenteID, int quantita) {
		DescrizioneComponente dc = this.menu.trovaDescrizioneComponente(descrizioneComponenteID);
		return this.ordineCorrente.aggiungiCompColazioneOrdinata(dc,quantita);
	}
	
	/**
	 * Definisce la modalita' di servizio alla colazione ordinata 
	 * nell'ordina corrente
	 * @param modoServizioID il codice della modalita' di servizio 
	 * @return true se la modalita' e' stata definita, false altrimenti
	 */
	public boolean definisciModoServizio(ModoServizioID modoServizioID) {
		ModoServizio modoServizio = this.getModoServizio(modoServizioID);
		return this.ordineCorrente.definisciModoServizio(modoServizio);
	}
	
	/**
	 * Registra una colazione nell'ordine corrente  
	 */
	public void confermaColazioneOrdinata() {
		this.ordineCorrente.confermaColazioneOrdinata();
	}
	
	/**
	 * Definisce il cliente e la data di consegna 
	 * dell'ordine corrente
	 * @param clienteID il codice del cliente registrato
	 * @param data la data di consegna dell'ordine
	 * @return l'ordine corrente
	 */
	public Ordine completaOrdine(ClienteID clienteID, Date data){
		Cliente cliente = GestoreClienti.getInstance().trovaCliente(clienteID);
		this.ordineCorrente.completaOrdine(cliente,data);
		// CalcolatorePrezzo.getInstance().calcolaPrezzo(this.ordineCorrente);
		return this.ordineCorrente;
	}

	/**
	 * Calcola il totale e registra l'ordine
	 */
	public void confermaOrdine() {
		PersistenceFacade.getInstance().salvaOrdine(ordineCorrente); 
		GestoreClienti.getInstance().aggiornaCliente(this.ordineCorrente.getCliente());
		//// this.ordini.put(this.ordineCorrente.getCodice(),this.ordineCorrente);
		this.publishPropertyEvent(Evento.CONFERMA_NUOVO_ORDINE,this.ordineCorrente);
	}

	/* ************************************************************************************ */
	/* ****************************** CONSEGNA DI UN ORDINE ******************************* */
	/* ************************************************************************************ */

	/**
	 * Chiude un ordine consegnato
	 * @param ordineID il codice dell'ordine
	 * @return true se l'ordine è stato chiuso, false altrimenti
	 */
	public boolean chiudiOrdine(OrdineID ordineID) {
		boolean risultato = false;
		Ordine ordine = this.getOrdine(ordineID);
		if (ordine != null) {
			ordine.setConsegnato(true);
			PersistenceFacade.getInstance().aggiornaOrdine(ordine); 
			risultato = true;
		}
		return risultato;
	}
			
	/* ************************************************************************************ */
	/* ************************************* OBSERVABLE *********************************** */
	/* ************************************************************************************ */
	
	public void addPropertyListener(PropertyListener pl) {
		this.listeners.add(pl);
	}
	
	public void publishPropertyEvent(String property, Object value) {
		for (PropertyListener pl : this.listeners)
			pl.onPropertyEvent(this,property,value);
	}

}
