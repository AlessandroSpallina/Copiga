package it.aps.cdr.persistenza;

import java.util.*;

import it.aps.cdr.dominio.CategoriaCliente;
import it.aps.cdr.dominio.CategoriaClienteID;
import it.aps.cdr.dominio.Cliente;
import it.aps.cdr.dominio.ClienteID;
import it.aps.cdr.dominio.DescrizioneComponente;
import it.aps.cdr.dominio.DescrizioneComponenteID;
import it.aps.cdr.dominio.ModoServizio;
import it.aps.cdr.dominio.ModoServizioID;
import it.aps.cdr.dominio.Ordine;
import it.aps.cdr.dominio.OrdineID;
import it.aps.cdr.dominio.TipoColazione;
import it.aps.cdr.dominio.TipoColazioneID;
import it.aps.cdr.logging.Logger;
import it.aps.cdr.persistenza.dao.DAOCategoriaCliente;
import it.aps.cdr.persistenza.dao.DAOCliente;
import it.aps.cdr.persistenza.dao.DAODescrizioneComponente;
import it.aps.cdr.persistenza.dao.DAOException;
import it.aps.cdr.persistenza.dao.DAOModoServizio;
import it.aps.cdr.persistenza.dao.DAOOrdine;
import it.aps.cdr.persistenza.dao.DAOTipoColazione;

/**
 * Facade allo strato di persistenza, acceduto come singleton.
 * 
 * @author Luca Cabibbo 
 * 
 * @version 0.3
 *
 */

public class PersistenceFacade {

	/** l'istanza singleton di PersistenceFacade */
	private static PersistenceFacade singleton;	
	
	/** 
	 * Restituisce il riferimento all'istanza singleton di PersistenceFacade
	 * @return il riferimento all'istanza singleton di PersistenceFacade 
	 */
	public static synchronized PersistenceFacade getInstance(){
		if (singleton == null)
			singleton = new PersistenceFacade();
		return singleton;
	}
	
	/* ************************************************************************************ */
	/* ************************************** CLIENTI ************************************* */
	/* ************************************************************************************ */

	/**
	 * Restituisce i clienti registrati nel sistema
	 * @return i clienti
	 */
	public Collection<Cliente> trovaClienti() {
		Collection<Cliente> clienti = null;
		/* carica le informazioni registrate nella base di dati sui clienti */
		try {
			clienti = DAOCliente.getInstance().doSelect().values();
		} catch (DAOException e) {
			e.printStackTrace();
			System.out.println("   Errore nel caricamento dei clienti");
		}
		return clienti; 
	}

	/**
	 * Restituisce il cliente cercato in base all'id
	 * @param id l'id del cliente
	 * @return il cliente
	 */
	public Cliente trovaCliente(ClienteID clienteID) {
		Cliente c = null; 
		try {
			c = DAOCliente.getInstance().doSelect(clienteID);
		} catch (DAOException e) {
			e.printStackTrace();
			System.out.println("   Errore nella ricerca");
		}
		return c; 
	}
	
	/**
	 * Restituisce i clienti cercati in base a nome e al cognome 
	 * @param nome il nome del cliente  
	 * @param cognome il cognome del cliente  
	 * @return una collezione di clienti con il nome e cognome specificati
	 */
	public Collection<Cliente> trovaClienti(String nome, String cognome) {
		Collection<Cliente> clienti = new HashSet<Cliente>(); 
		try {
			Map<ClienteID,Cliente> mc = DAOCliente.getInstance().doSelect(nome,cognome);  
			clienti = mc.values();		
		} catch (DAOException e) {
			e.printStackTrace();
			System.out.println("   Errore nella ricerca");
		}
		return clienti;
	}
		
	/**
	 * Registra un cliente nella base di dati
	 * @param cliente il cliente
	 */
	public void salvaCliente(Cliente cliente) {
		try {
			DAOCliente.getInstance().doInsertGID(cliente);
		} catch (DAOException daoe) {
			Logger.getInstance().errore("errore","Impossibile completare l'operazione.");
		}		
	}
	
	/**
	 * Aggiorna un cliente nella base di dati
	 * @param cliente il cliente
	 */
	public void aggiornaCliente(Cliente cliente) {
		try {
			DAOCliente.getInstance().doUpdate(cliente);
		} catch (DAOException e) {
			Logger.getInstance().errore("errore","Impossibile completare l'operazione.");
		}	
	}

	/**
	 * Cancella il cliente associato a clienteID
	 * @param clienteID il codice del cliente
	 * @return il cliente
	 */
	public void cancellaCliente(ClienteID clienteID) {
		try {			
			DAOCliente.getInstance().doDelete(clienteID);
		} catch (DAOException e) {
			e.printStackTrace();
		}		
	}

	/* ************************************************************************************ */
	/* ****************************** CATEGORIE CLIENTI *********************************** */
	/* ************************************************************************************ */

	/**
	 * Restituisce le categorie di cliente registrate nel sistema
	 * @return le categorie di cliente
	 */
	public Map<CategoriaClienteID,CategoriaCliente> trovaCategorieClienti() {
		Map<CategoriaClienteID,CategoriaCliente> categorieClienti = null;
		/* carica le informazioni registrate nella base di dati sulle categorie di clienti */
		try {
			categorieClienti = DAOCategoriaCliente.getInstance().doSelect();
			//// this.clienti = DAOCliente.getInstance().doSelect();
		} catch (DAOException e) {
			e.printStackTrace();
			System.out.println("   Errore nell'inizializzazione");
		}
		return categorieClienti; 
	}

	/**
	 * Registra un categoria di cliente nella base di dati
	 * @param categoriaCliente la categoria di cliente
	 */
	public void salvaCategoriaCliente(CategoriaCliente categoriaCliente) {
		try {			
			DAOCategoriaCliente.getInstance().doInsertGID(categoriaCliente);
		} catch (DAOException e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * Aggiorna un categoria di cliente nella base di dati
	 * @param categoriaCliente la categoria di cliente
	 */
	public void aggiornaCategoriaCliente(CategoriaCliente categoriaCliente) {
		try {			
			DAOCategoriaCliente.getInstance().doUpdate(categoriaCliente);
		} catch (DAOException e) {
			e.printStackTrace();
		}		
	}

	/**
	 * Cancella la categoria cliente associato a categoriaClienteID
	 * @param categoriaClienteID il codice della categoria di cliente
	 */
	public void cancellaCategoriaCliente(CategoriaClienteID categoriaClienteID) {
		try {			
			DAOCategoriaCliente.getInstance().doDelete(categoriaClienteID);
		} catch (DAOException e) {
			e.printStackTrace();
		}		
	}

	/* ************************************************************************************ */
	/* ************************************** ORDINI ************************************** */
	/* ************************************************************************************ */

	/**
	 * Restituisce l'ordine associato a ordineID
	 * @param ordineID il codice associato all'ordine
	 * @return l'ordine cercato
	 */
	public Ordine trovaOrdine(OrdineID ordineID) {
		Ordine ordine = null; 
		try {
			ordine = DAOOrdine.getInstance().doSelect(ordineID);
		} catch (DAOException e) {
			e.printStackTrace();
			System.out.println("   Errore nella ricerca");
		}		
		return ordine;
	}
	
	/**
	 * Restituisce gli ordini registrati nel sistema
	 * @return gli ordini
	 */
	public Collection<Ordine> trovaOrdini() {
		Map<OrdineID,Ordine> ordini = null;
		/* carica le informazioni registrate nella base di dati sugli ordini */
		try {
			ordini = DAOOrdine.getInstance().doSelect();
		} catch (DAOException e) {
			e.printStackTrace();
			System.out.println("   Errore nel caricamento degli ordini");
		}
		return ordini.values();
	}
			
	/**
	 * Registra un ordine nella base di dati
	 * @param ordine l'ordine
	 */
	public void salvaOrdine(Ordine ordine) {
		try {
			DAOOrdine.getInstance().doInsertGID(ordine);
		} catch (DAOException daoe) {
			Logger.getInstance().errore("errore","Impossibile completare l'operazione.");
		}		
	}
	
	/**
	 * Aggiorna un ordine nella base di dati
	 * @param ordine l'ordine
	 */
	public void aggiornaOrdine(Ordine ordine) {
		try {
			DAOOrdine.getInstance().doUpdate(ordine);
		} catch (DAOException e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * Cancella l'ordine associato a ordineID
	 * @param ordineID il codice dell'ordine
	 */
	public void cancellaOrdine(OrdineID ordineID) {
		try {			
			DAOOrdine.getInstance().doDelete(ordineID);
		} catch (DAOException e) {
			e.printStackTrace();
		}		
	}
	
	/* ************************************************************************************ */
	/* ******************************** TIPI COLAZIONE ************************************ */
	/* ************************************************************************************ */
	
	/**
	 * Restituisce i tipi di colazione registrati nel sistema
	 * @return i tipi di colazione
	 */
	public Map<TipoColazioneID,TipoColazione> trovaTipiColazione() {
		Map<TipoColazioneID,TipoColazione> tipiColazione = null;
		/* carica le informazioni registrate nella base di dati sui tipi di colazione */
		try {
			tipiColazione = DAOTipoColazione.getInstance().doSelect();
		} catch (DAOException e) {
			e.printStackTrace();
			System.out.println("   Errore nel caricamento dei tipi di colazione");
		}
		return tipiColazione;
	}
			
	/**
	 * Registra un tipo di colazione nella base di dati
	 * @param tipoColazione il tipo di colazione
	 */
	public void salvaTipoColazione(TipoColazione tipoColazione) {
		try {
			DAOTipoColazione.getInstance().doInsertGID(tipoColazione);
		} catch (DAOException daoe) {
			Logger.getInstance().errore("errore","Impossibile completare l'operazione.");
		}		
	}
	
	/**
	 * Aggiorna un tipo di colazione nella base di dati
	 * @param tipoColazione il tipo di colazione
	 */
	public void aggiornaTipoColazione(TipoColazione tipoColazione) {
		try {
			DAOTipoColazione.getInstance().doUpdate(tipoColazione);
		} catch (DAOException e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * Cancella il tipo di colazione associato a tipoColazioneID
	 * @param tipoColazioneID il codice del tipo di colazione
	 */
	public void cancellaTipoColazione(TipoColazioneID tipoColazioneID) {
		try {			
			DAOTipoColazione.getInstance().doDelete(tipoColazioneID);
		} catch (DAOException e) {
			e.printStackTrace();
		}		
	}
		
	/* ************************************************************************************ */
	/* ***************************** DESCRIZIONI COMPONENTI ******************************* */
	/* ************************************************************************************ */

	/**
	 * Restituisce le descrizione dei componenti registrate nel sistema
	 * @return le descrizione dei componenti
	 */
	public Map<DescrizioneComponenteID,DescrizioneComponente> trovaDescrizioniComponenti() {
		Map<DescrizioneComponenteID,DescrizioneComponente> descrizioniComponenti = null;
		/* carica le informazioni registrate nella base di dati sulle descrizioni di componenti */
		try {
			descrizioniComponenti = DAODescrizioneComponente.getInstance().doSelect();
		} catch (DAOException e) {
			e.printStackTrace();
			System.out.println("   Errore nell'inizializzazione");
		}	
		return descrizioniComponenti; 
	}

	/**
	 * Registra una descrizione componente nella base di dati
	 * @param descrizioneComponente la descrizione componente
	 */
	public void salvaDescrizioneComponente(DescrizioneComponente descrizioneComponente) {
		try {			
			DAODescrizioneComponente.getInstance().doInsertGID(descrizioneComponente);
		} catch (DAOException e) {
			e.printStackTrace();
		}		
	}

	/**
	 * Aggiorna una descrizione componente nella base di dati
	 * @param descrizioneComponente la descrizione componente
	 */
	public void aggiornaDescrizioneComponente(DescrizioneComponente descrizioneComponente) {
		try {			
			DAODescrizioneComponente.getInstance().doUpdate(descrizioneComponente);
		} catch (DAOException e) {
			e.printStackTrace();
		}		
	}

	/**
	 * Cancella la descrizione componente associata a descrizioneComponenteID
	 * @param descrizioneComponenteID il codice della descrizione componente
	 */
	public void cancellaDescrizioneComponente(DescrizioneComponenteID descrizioneComponenteID) {
		try {			
			DAODescrizioneComponente.getInstance().doDelete(descrizioneComponenteID);
		} catch (DAOException e) {
			e.printStackTrace();
		}		
	}

	/* ************************************************************************************ */
	/* ******************************** MODI SERVIZIO ************************************* */
	/* ************************************************************************************ */

	/**
	 * Restituisce i modi di servizio registrati nel sistema
	 * @return i modi di servizio
	 */
	public Map<ModoServizioID,ModoServizio> trovaModiServizio() {
		Map<ModoServizioID,ModoServizio> modiServizio = null;
		/* carica le informazioni registrate nella base di dati sui modi di servizio */
		try {
			modiServizio = DAOModoServizio.getInstance().doSelect();
		} catch (DAOException e) {
			e.printStackTrace();
			System.out.println("   Errore nell'inizializzazione");
		}	
		return modiServizio; 
	}

	/**
	 * Registra un modo di servizio nella base di dati
	 * @param modoServizio il modo di servizio 
	 */
	public void salvaModoServizio(ModoServizio modoServizio) {
		try {			
			DAOModoServizio.getInstance().doInsertGID(modoServizio);
		} catch (DAOException e) {
			e.printStackTrace();
			System.out.println("   Operazione non effettuata");
		}		
	}

	/**
	 * Aggiorna un modo di servizio nella base di dati
	 * @param modoServizio il modo di servizio 
	 */
	public void aggiornaModoServizio(ModoServizio modoServizio) {
		try {			
			DAOModoServizio.getInstance().doUpdate(modoServizio);
		} catch (DAOException e) {
			e.printStackTrace();
		}		
	}

	/**
	 * Cancella il modo di servizio associato a modoServizioID
	 * @param modoServizioID il codice del modo di servizio
	 */
	public void cancellaModoServizio(ModoServizioID modoServizioID) {
		try {			
			DAOModoServizio.getInstance().doDelete(modoServizioID);
		} catch (DAOException e) {
			e.printStackTrace();
		}		
	}

}
