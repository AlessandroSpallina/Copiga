package it.aps.cdr.dominio;

import it.aps.cdr.eventi.Evento;
import it.aps.cdr.eventi.PropertyListener;
import it.aps.cdr.logging.Logger;
import it.aps.cdr.persistenza.PersistenceFacade;
import it.aps.cdr.persistenza.dao.DAOException;

import java.util.*;

/**
 * Gestore dei clienti e delle relative categorie.
 * 
 * @see Cliente
 * @see CategoriaCliente
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class GestoreClienti {

	/** istanza singleton del gestore clienti */
	private static GestoreClienti singleton;
	/** le categorie clienti */
	private Map<CategoriaClienteID,CategoriaCliente> categorieClienti;
	/** la categoria cliente corrente */
	private CategoriaCliente categoriaClienteCorrente;
	/** i clienti registrati nel sistema */
	// private Map<ClienteID,Cliente> clienti;
	/** il cliente corrente */
	private Cliente clienteCorrente;
	/** listeners interessati ai cambiamenti dei clienti */
	private List<PropertyListener> listeners;
	
	/** crea un nuovo GestoreClienti */
	protected GestoreClienti() {
		// this.clienti = new HashMap<ClienteID,Cliente>(); 
		this.categorieClienti = new HashMap<CategoriaClienteID,CategoriaCliente>(); 
		
		this.listeners = new LinkedList<PropertyListener>();
	}
	
	/**
	 * Restituisce il riferimento all'istanza singleton di GestoreClienti
	 * @return il riferimento all'istanza singleton di GestoreClienti
	 */
	public static synchronized GestoreClienti getInstance(){
		if (singleton == null)
			singleton = new GestoreClienti();
		return singleton;
	}
	
	/**
	 * Carica/ricarica i dati in memoria dalla base di dati, 
	 * e li fa ricaricare a tutti gli altri macro-oggetti di dominio collegati. 
	 */
	public void ricaricaDati() {
		/* carica tutte le informazioni registrate nella base di dati */
		// this.clienti = PersistenceFacade.getInstance().trovaClienti();  		
		this.categorieClienti = PersistenceFacade.getInstance().trovaCategorieClienti();  		
	}

	/* ************************************************************************************ */
	/* ************************************** GETTERS ************************************* */
	/* ************************************************************************************ */

	/**
	 * Restituisce la categoria di cliente corrente
	 * @return la categoria di cliente corrente 
	 */
	public CategoriaCliente getCategoriaClienteCorrente() {
		return categoriaClienteCorrente;
	}
	
	/**
	 * Restituisce le categorie di clienti registrate nel sistema
	 * @return le categorie di clienti
	 */
	public Collection<CategoriaCliente> getCategorieClienti() {
		return this.categorieClienti.values();
	}
	
	/**
	 * Restituisce il cliente corrente
	 * @return il cliente corrente
	 */
	public Cliente getClienteCorrente() {
		return clienteCorrente;
	}
	
	/**
	 * Restituisce i clienti registrati nel sistema
	 * @return i clienti
	 */
	public Collection<Cliente> getClienti() {
		//// return this.clienti.values();
		return PersistenceFacade.getInstance().trovaClienti();  
	}
	
	/* ************************************************************************************ */
	/* ************************************** SETTERS ************************************* */
	/* ************************************************************************************ */
	
	/**
	 * Definisce il cliente corrente
	 * @param clienteCorrente il cliente corrente 
	 */
	public void setClienteCorrente(Cliente clienteCorrente) {
		this.clienteCorrente = clienteCorrente;
	}

	/**
	 * Definisce le categorie di clienti
	 * @param categorieClienti le categorie di clienti
	 */
	public void setCategorieClienti(
			Map<CategoriaClienteID,CategoriaCliente> categorieClienti) {
		this.categorieClienti = categorieClienti;
	}

	/**
	 * Definisce la categoria di cliente corrente
	 * @param categoriaClienteCorrente la categoria di cliente corrente
	 */
	public void setCategoriaClienteCorrente(CategoriaCliente categoriaClienteCorrente) {
		this.categoriaClienteCorrente = categoriaClienteCorrente;
	}
	
	/* ************************************************************************************ */
	/* ************************************* USE CASE ************************************* */
	/* ************************************************************************************ */
	
	/**
	 * Definisce un nuovo cliente
	 * @param nome il nome del cliente
	 * @param cognome il cognome del cliente
	 * @param indirizzo l'indirizzo del cliente
	 * @return il nuovo cliente
	 */
	public Cliente nuovoCliente(String nome, String cognome, String indirizzo) {
		this.clienteCorrente = new Cliente(nome,cognome,indirizzo);
		this.clienteCorrente.setCategoriaCliente(trovaCategoriaIniziale());
		return this.clienteCorrente;
	}
	
	/**
	 * Definisce un nuovo cliente
	 * @param id l'id del cliente
	 * @param nome il nome del cliente
	 * @param cognome il cognome del cliente
	 * @param indirizzo l'indirizzo del cliente
	 * @return il nuovo cliente
	 */
	public Cliente nuovoCliente(ClienteID id, String nome, String cognome, String indirizzo) {
		this.clienteCorrente = new Cliente(id,nome,cognome,indirizzo);
		this.clienteCorrente.setCategoriaCliente(trovaCategoriaIniziale());
		return this.clienteCorrente;
	}
	
	/**
	 * Registra le informazioni del nuovo cliente. Il cliente viene aggiunto 
	 * ai clienti registrati
	 */
	public void confermaCliente(){
		PersistenceFacade.getInstance().salvaCliente(this.clienteCorrente); 
		ClienteID id = this.clienteCorrente.getCodice();
		Logger.getInstance().messaggio("operazione completata",
				"Il codice del cliente e' " + id + ".");
		//// this.clienti.put(this.clienteCorrente.getCodice(),this.clienteCorrente);
		this.publishPropertyEvent(Evento.CONFERMA_NUOVO_CLIENTE,this.clienteCorrente);
	}
	
	/**
	 * Restituisce il cliente cercato in base all'id
	 * @param id l'id del cliente
	 * @return il cliente
	 */
	public Cliente trovaCliente(ClienteID clienteID) {
		//// return this.clienti.get(clienteID);
		return PersistenceFacade.getInstance().trovaCliente(clienteID); 
	}
	
	/**
	 * Restituisce i clienti cercati in base a nome e/o al cognome 
	 * @param nome il nome del cliente  
	 * @param cognome il cognome del cliente  
	 * @return una collezione di clienti con il nome e cognome specificati
	 */
	public Collection<Cliente> trovaClienti(String nome, String cognome) {
		return PersistenceFacade.getInstance().trovaClienti(nome, cognome); 
	}
	
/*
	public Collection<Cliente> trovaCliente(String nome, String cognome) {
		Set<Cliente> clientiOmonimi = new HashSet<Cliente>();		
		for (Cliente c : this.clienti.values()) {
			if (isValid(nome) && c.getNome().equalsIgnoreCase(nome) || 
				isValid(cognome) && c.getCognome().equalsIgnoreCase(cognome))
				clientiOmonimi.add(c);
		}		
		return clientiOmonimi;
	}
*/
	
/*
	private boolean isValid(String s) {
		return s!=null && !s.equals("");
	}
*/
	
	/* ************************************************************************************ */
	
	/**
	 * Definisce una nuova categoria cliente
	 * @param nome il nome della categoria
	 * @param descrizione la descrizione della categoria
	 * @param fattoreCliente il fattore moltiplicativo associato alla categoria
	 * @param ordini il limite superiore di ordini della categoria
	 * @return la nuova categoria cliente
	 */
	public CategoriaCliente nuovaCategoriaCliente(String nome, String descrizione, double fattoreCliente, int ordini) {
		this.categoriaClienteCorrente = new CategoriaCliente(nome,descrizione,fattoreCliente,ordini);
		return this.categoriaClienteCorrente;
	}
	
	/**
	 * Definisce una nuova categoria cliente
	 * @param id l'id della nuova categoria
	 * @param nome il nome della categoria
	 * @param descrizione la descrizione della categoria
	 * @param fattoreCliente il fattore moltiplicativo associato alla categoria
	 * @param ordini il limite superiore di ordini della categoria
	 * @return la nuova categoria cliente
	 */
	public CategoriaCliente nuovaCategoriaCliente(CategoriaClienteID id, String nome, String descrizione, double fattoreCliente, int ordini) {
		this.categoriaClienteCorrente = new CategoriaCliente(id,nome,descrizione,fattoreCliente,ordini);
		return this.categoriaClienteCorrente;
	}

	/**
	 * Registra le informazioni della nuova categoria di cliente. La categoria di cliente 
	 * viene aggiunta alle categorie registrate
	 */
	public void confermaCategoriaCliente() {
		PersistenceFacade.getInstance().salvaCategoriaCliente(this.categoriaClienteCorrente); 
		this.categorieClienti.put(this.categoriaClienteCorrente.getCodice(),this.categoriaClienteCorrente);
	}
	
	/**
	 * Restituisce la categoria di cliente associata a categoriaClienteID
	 * @param categoriaClienteID il codice della categoria di cliente
	 * @return la categoria di cliente
	 */
	public CategoriaCliente trovaCategoriaCliente(CategoriaClienteID categoriaClienteID) {
		return this.categorieClienti.get(categoriaClienteID);
	}
	
	/**
	 * Restituisce la categoria di cliente associata a nomeCategoria
	 * @param nomeCategoria il nome della categoria di cliente
	 * @return la categoria di cliente
	 */
	public CategoriaCliente trovaCategoriaCliente(String nomeCategoria) {
		CategoriaCliente categoriaCliente = null;
		for (CategoriaCliente cc : getCategorieClienti()) {
			if (cc.getNome().equalsIgnoreCase(nomeCategoria))
				categoriaCliente = cc;
		}
		return categoriaCliente;
	}
	
	/**
	 * Restituisce la categoria di cliente associata a ordini
	 * @param ordini gli ordini della categoria di cliente
	 * @return la categoria di cliente
	 */
	public CategoriaCliente trovaCategoriaCliente(int ordini) {
		CategoriaCliente categoriaCliente = null;
		for (CategoriaCliente cc : getCategorieClienti()) {
			if (cc.getOrdini() == ordini)
				categoriaCliente = cc;
		}
		return categoriaCliente;
	}
	
	/* ************************************************************************************ */
	/* *********************************** CANCELLAZIONE ********************************** */
	/* ************************************************************************************ */
	
	/**
	 * Cancella il cliente associato a clienteID
	 * @param clienteID il codice del cliente
	 * @return il cliente
	 */
	public Cliente cancellaCliente(ClienteID clienteID) {
		Cliente c = this.trovaCliente(clienteID); 
		PersistenceFacade.getInstance().cancellaCliente(clienteID); 
		//// return this.clienti.remove(clienteID);
		return c; 
	}
	
	/**
	 * Cancella la categoria di cliente associata a categoriaClienteID
	 * @param categoriaClienteID il codice della categoria del cliente
	 * @return la categoria di cliente
	 */
	public CategoriaCliente cancellaCategoriaCliente(CategoriaClienteID categoriaClienteID) {
		CategoriaCliente cc = this.trovaCategoriaCliente(categoriaClienteID); 
		PersistenceFacade.getInstance().cancellaCategoriaCliente(categoriaClienteID); 
		return this.categorieClienti.remove(categoriaClienteID);
	}
	
	/* ************************************************************************************ */
	/* *********************************** AGGIORNAMENTO ********************************** */
	/* ************************************************************************************ */
		
	/**
	 * Aggiorna i dati del cliente in seguito ad un ordine confermato
	 * @param cliente il cliente da aggiornare
	 */
	public void aggiornaCliente(Cliente cliente) {
		cliente.setOrdiniEffettuati(cliente.getOrdiniEffettuati() + 1);
		CategoriaCliente categoriaCliente = cliente.getCategoriaCliente();
		CategoriaCliente categoriaSuccessiva = trovaCategoriaSuccessiva(categoriaCliente);
		if (cliente.getOrdiniEffettuati() > categoriaCliente.getOrdini() && categoriaSuccessiva != null){
			cliente.setCategoriaCliente(categoriaSuccessiva);
		}
		PersistenceFacade.getInstance().aggiornaCliente(cliente); 
	}
	
	/* ************************************************************************************ */
	/* ********************************* METODI DI SUPPORTO ******************************* */
	/* ************************************************************************************ */
	
	/* TROVA LA CATEGORIA DI CLIENTE INIZIALE */
	private CategoriaCliente trovaCategoriaIniziale() {
		Collection<CategoriaCliente> categorie = this.categorieClienti.values();
		int minimo = -1;
		CategoriaCliente categoriaIniziale = null;		
		for (CategoriaCliente cc : categorie) {
			if (cc.getOrdini() < minimo || minimo < 0) {
				minimo = cc.getOrdini();
				categoriaIniziale = cc;
			}
		}
		return categoriaIniziale;
	}
	
	/* TROVA LA CATEGORIA DI CLIENTE SUCCESSIVA */
	protected CategoriaCliente trovaCategoriaSuccessiva(CategoriaCliente categoriaCliente){
		CategoriaCliente categoriaSuccessiva = null;
		int ordiniCategoriaCliente = categoriaCliente.getOrdini();
		Collection<CategoriaCliente> categorie = this.categorieClienti.values();
		for (CategoriaCliente cc : categorie)
			if (cc.getOrdini() > ordiniCategoriaCliente && (categoriaSuccessiva == null || cc.getOrdini() < categoriaSuccessiva.getOrdini()))
				categoriaSuccessiva = cc;
		if (categoriaSuccessiva == null)
			categoriaSuccessiva = categoriaCliente;
		return categoriaSuccessiva;
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
