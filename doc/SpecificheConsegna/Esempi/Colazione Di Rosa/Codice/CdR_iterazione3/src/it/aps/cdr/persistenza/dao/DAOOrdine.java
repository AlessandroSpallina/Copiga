package it.aps.cdr.persistenza.dao;

import it.aps.cdr.dominio.CategoriaCliente;
import it.aps.cdr.dominio.CategoriaClienteID;
import it.aps.cdr.dominio.Cliente;
import it.aps.cdr.dominio.ClienteID;
import it.aps.cdr.dominio.ColazioneOrdinata;
import it.aps.cdr.dominio.ColazioneOrdinataID;
import it.aps.cdr.dominio.DescrizioneComponente;
import it.aps.cdr.dominio.DescrizioneComponenteID;
import it.aps.cdr.dominio.ModoServizio;
import it.aps.cdr.dominio.ModoServizioID;
import it.aps.cdr.dominio.Ordine;
import it.aps.cdr.dominio.OrdineID;
import it.aps.cdr.dominio.Prezzo;
import it.aps.cdr.dominio.TipoColazione;
import it.aps.cdr.dominio.TipoColazioneID;
import it.aps.cdr.dominio.Variazione;
import it.aps.cdr.persistenza.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Oggetto DAO per gli oggetti di tipo Ordine, acceduto come singleton.
 * 
 * @see Ordine
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri
 * @author Gabriele Rendina
 * 
 * @version 0.3
 *
 */
public class DAOOrdine {

	/** l'istanza singleton di DAOOrdine */
	private static DAOOrdine singleton;	
	
	/** 
	 * Restituisce il riferimento all'istanza singleton di DAOOrdine
	 * @return il riferimento all'istanza singleton di DAOOrdine 
	 */
	public static synchronized DAOOrdine getInstance(){
		if (singleton == null)
			singleton = new DAOOrdine();
		return singleton;
	}
	
	/**
	 * Registra un ordine nella base di dati
	 * @param ordine l'ordine
	 * @throws DAOException
	 */
	public void doInsert(Ordine ordine) throws DAOException {
		Connection connessione = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			connessione = dataSource.getConnessione();
			doInsert(ordine,connessione);
		} catch (SQLException sqle) {
			throw new DAOException("doInsert_1_DAOOrdine : " + sqle);
		} finally {
			dataSource.chiudi(connessione);
		}
	}
	
	/**
	 * Registra un ordine nella base di dati. La chiave viene
	 * generata dal DBMS.
	 * Attenzione : da usare con MySQL e DB2  
	 * @param ordine l'ordine
	 * @throws DAOException
	 */
	public void doInsertAGK(Ordine ordine) throws DAOException{
		Connection connessione = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			connessione = dataSource.getConnessione();
			doInsertAGK(ordine,connessione);
		} catch (SQLException sqle) {
			throw new DAOException("doInsertAGK_1_DAOOrdine : " + sqle);
		} finally {
			dataSource.chiudi(connessione);
		}
	}
	
	/**
	 * Registra un ordine nella base di dati. La chiave viene
	 * generata dal GeneratoreID 
	 * @param ordine l'ordine
	 * @throws DAOException
	 */
	public void doInsertGID(Ordine ordine) throws DAOException{
		Connection connessione = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			connessione = dataSource.getConnessione();
			OrdineID oID = new OrdineID(GeneratoreID.getInstance().getNuovoCodice());
			ordine.setCodice(oID);
			for (ColazioneOrdinata co : ordine.getColazioniOrdinate()) {
				ColazioneOrdinataID coID = new ColazioneOrdinataID(GeneratoreID.getInstance().getNuovoCodice());
				co.setCodice(coID);
			}
			doInsert(ordine,connessione);
		} catch (SQLException sqle) {
			throw new DAOException("doInsertGID_1_DAOOrdine : " + sqle);
		} finally {
			dataSource.chiudi(connessione);
		}
	}
	
	/**
	 * Registra un ordine nella base di dati
	 * @param ordine l'ordine
	 * @param connessione la connessione alla base di dati
	 * @throws DAOException
	 */
	public void doInsert(Ordine ordine, Connection connessione) throws DAOException {
		DataSource dataSource = DataSource.getInstance();
		PreparedStatement stmt = null;
		PreparedStatement stmtv = null;
		try {
			connessione.setAutoCommit(false);
			/* inserisce l'ordine */
			String query1 = "insert into ORDINE values (?,?,?,?,?)";
			stmt = connessione.prepareStatement(query1);
			stmt.setInt(1,ordine.getCodice().getCodiceOrdine());
			stmt.setBigDecimal(2,ordine.getPrezzo().getImporto());
			java.sql.Date data = new java.sql.Date(ordine.getData().getTime());
			stmt.setDate(3,data);
			stmt.setBoolean(4,ordine.isConsegnato());
			stmt.setInt(5,ordine.getCliente().getCodice().getCodiceCliente());
			stmt.executeUpdate();
			/* inserisce le colazioni ordinate associate all'ordine */
			String query2 = "insert into COLAZIONEORDINATA values (?,?,?,?)";
			stmt = connessione.prepareStatement(query2);
			stmt.setInt(4,ordine.getCodice().getCodiceOrdine());
			for (ColazioneOrdinata co : ordine.getColazioniOrdinate()) {
				stmt.setInt(1,co.getCodice().getCodiceColazioneOrdinata());
				stmt.setInt(2,co.getTipoColazione().getCodice().getCodiceTipoColazione());
				stmt.setInt(3,co.getModoServizio().getCodice().getCodiceModoServizio());
				stmt.executeUpdate();
				/* inserisce le variazioni associate alla colazione ordinata */
				String query3 = "insert into VARIAZIONE values (?,?,?)";
				stmtv = connessione.prepareStatement(query3);
				stmtv.setInt(1,co.getCodice().getCodiceColazioneOrdinata());
				for (Variazione v : co.getVariazioni()) {
					stmtv.setInt(2,v.getDescrizioneComponente().getCodice().getCodiceDescrizioneComponente());
					stmtv.setInt(3,v.getQuantita());
					stmtv.executeUpdate();
				}
			}
			connessione.commit();
			connessione.setAutoCommit(true);
		} catch (SQLException sqle) {
			try {
				connessione.rollback();
			} catch (SQLException sqle2) {
				throw new DAOException("doInsert_2_DAOOrdine : " + sqle2);
			}
		} finally {
			dataSource.chiudi(stmtv);
			dataSource.chiudi(stmt);
		}
	}
	
	/**
	 * Registra un ordine nella base di dati. La chiave
	 * viene generata automaticamente dal DBMS
	 * @param ordine l'ordine
	 * @param connessione la connessione alla base di dati 
	 * @throws DAOException
	 */
	public void doInsertAGK(Ordine ordine, Connection connessione)	throws DAOException {
		DataSource dataSource = DataSource.getInstance();
		PreparedStatement stmt = null;
		PreparedStatement stmtv = null;
		ResultSet resultSet = null;		
		try {
			connessione.setAutoCommit(false);
			/* inserisce l'ordine */
			String query = "insert into ORDINE (prezzo, data, cliente, consegnato) values (?,?,?,?)";
			stmt = connessione.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			stmt.setBigDecimal(1,ordine.getPrezzo().getImporto());
			stmt.setDate(2,new java.sql.Date(ordine.getData().getTime()));
			stmt.setInt(3,ordine.getCliente().getCodice().getCodiceCliente());
			stmt.setBoolean(4,ordine.isConsegnato());
			stmt.executeUpdate();
			resultSet = stmt.getGeneratedKeys();
			resultSet.next();
			OrdineID oID = new OrdineID(resultSet.getBigDecimal(1).intValue());
			ordine.setCodice(oID);
			/* inserisce le colazioni ordinate associate all'ordine */
			String query2 = "insert into COLAZIONEORDINATA (tipocolazione, modoservizio, ordine) values (?,?,?)";
			stmt = connessione.prepareStatement(query2,Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(3,ordine.getCodice().getCodiceOrdine());
			for (ColazioneOrdinata co : ordine.getColazioniOrdinate()) {
				stmt.setInt(1,co.getTipoColazione().getCodice().getCodiceTipoColazione());
				stmt.setInt(2,co.getModoServizio().getCodice().getCodiceModoServizio());
				stmt.executeUpdate();
				resultSet = stmt.getGeneratedKeys();
				resultSet.next();
				co.setCodice(new ColazioneOrdinataID(resultSet.getBigDecimal(1).intValue()));
				/* inserisce le variazioni della colazione ordinata */
				String query3 = "insert into VARIAZIONE values (?,?,?)";
				stmtv = connessione.prepareStatement(query3);
				stmtv.setInt(1,co.getCodice().getCodiceColazioneOrdinata());
				for (Variazione v : co.getVariazioni()) {
					stmtv.setInt(2,v.getDescrizioneComponente().getCodice().getCodiceDescrizioneComponente());
					stmtv.setInt(3,v.getQuantita());
					stmtv.executeUpdate();
				}
			}
			connessione.commit();
			connessione.setAutoCommit(true);
		} catch (SQLException sqle) {
			throw new DAOException("doInsertAGK_2_DAOOrdine : " + sqle);
		} finally {
			dataSource.chiudi(resultSet);
			dataSource.chiudi(stmtv);
			dataSource.chiudi(stmt);
		}				
	}
	
	/**
	 * Restituisce un ordine registrato nella base di dati
	 * @param ordineID il codice dell'ordine
	 * @param connessione la connessione alla base di dati
	 * @return l'ordine
	 * @throws DAOException
	 */
	public Ordine doSelect(OrdineID ordineID) throws DAOException {
		Connection connessione = null;
		Ordine ordine = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			connessione = dataSource.getConnessione();
			ordine = doSelect(ordineID,connessione);
		} catch (SQLException sqle) {
			throw new DAOException("doSelect_1_DAOOrdine : " + sqle);
		} finally {
			dataSource.chiudi(connessione);
		}
		return ordine;
	}

	/**
	 * Restituisce un ordine registrato nella base di dati
	 * @param ordineID il codice dell'ordine
	 * @param connessione la connessione alla base di dati
	 * @return l'ordine
	 * @throws DAOException
	 */
	public Ordine doSelect(OrdineID ordineID, Connection connessione) throws DAOException {
		DataSource dataSource = DataSource.getInstance();
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		Ordine ordine = null;
		ColazioneOrdinata colazioneOrdinata = null;
		try {
			String query =
				"select ORDINE.codice, ORDINE.prezzo, ORDINE.data, ORDINE.consegnato, ORDINE.cliente, " +
				"CLIENTE.nome, CLIENTE.cognome, CLIENTE.indirizzo, CLIENTE.ordini, CLIENTE.categoria, " +
				"CATEGORIACLIENTE.nome AS nomecat, CATEGORIACLIENTE.descrizione, " + 
				"CATEGORIACLIENTE.ordini AS ordinicat, " + 
				"CATEGORIACLIENTE.fattorecliente, COLAZIONEORDINATA.codice AS codiceCO, " + 
				"COLAZIONEORDINATA.tipocolazione, COLAZIONEORDINATA.modoservizio, " +
				"TIPOCOLAZIONE.nome AS nomeTC, TIPOCOLAZIONE.prezzo AS prezzoTC, " +
				"DESCRIZIONECOMPONENTE.nome AS nomeDC, DESCRIZIONECOMPONENTE.prezzoaggiuntivo, " + 
				"DESCRIZIONECOMPONENTE.prezzoriduttivo, " +
				"VARIAZIONE.quantita AS nuovaqta, VARIAZIONE.componente, " +
				"MODOSERVIZIO.nome AS nomeMS, MODOSERVIZIO.descrizione AS descrizioneMS, MODOSERVIZIO.fattore " +
				"from ((((((ORDINE join CLIENTE on ORDINE.cliente = CLIENTE.codice) " +
				"join CATEGORIACLIENTE on CLIENTE.categoria = CATEGORIACLIENTE.codice) " +
				"join COLAZIONEORDINATA on ORDINE.codice = COLAZIONEORDINATA.ordine) " +
				"left join VARIAZIONE on COLAZIONEORDINATA.codice = VARIAZIONE.colazioneordinata) " +
				"left join DESCRIZIONECOMPONENTE on VARIAZIONE.componente = DESCRIZIONECOMPONENTE.codice) " +
				"join TIPOCOLAZIONE on COLAZIONEORDINATA.tipocolazione = TIPOCOLAZIONE.codice) " +
				"join MODOSERVIZIO on COLAZIONEORDINATA.modoservizio = MODOSERVIZIO.codice " +
				"where ORDINE.codice = ?";
			stmt = connessione.prepareStatement(query);
			stmt.setInt(1,ordineID.getCodiceOrdine());
			resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				if (ordine == null ) {
					/* crea l'ordine */
					int codiceOrdine = resultSet.getInt("codice");
					OrdineID oID = new OrdineID(codiceOrdine);
					ordine = new Ordine(oID);
					Prezzo prezzoOrdine = new Prezzo(resultSet.getDouble("prezzo"));
					ordine.setPrezzo(prezzoOrdine);
					Date data = resultSet.getDate("data");
					ordine.setData(data);
					boolean statoConsegna = resultSet.getBoolean("consegnato");
					ordine.setConsegnato(statoConsegna);
					/* crea il cliente */
					ClienteID clID = new ClienteID(resultSet.getInt("cliente"));
					String nomeCliente = resultSet.getString("nome");
					String cognome = resultSet.getString("cognome");
					String indirizzo = resultSet.getString("indirizzo");
					Cliente cliente = new Cliente(clID,nomeCliente,cognome,indirizzo);
					int ordiniEffettuati = resultSet.getInt("ordini");
					cliente.setOrdiniEffettuati(ordiniEffettuati);
					/* crea la categoria del cliente */
					CategoriaClienteID ccID = new CategoriaClienteID(resultSet.getInt("categoria"));
					String nomeCategoria = resultSet.getString("nomecat");
					String descrizione = resultSet.getString("descrizione");
					double fattore = resultSet.getDouble("fattorecliente");
					int ordiniCat = resultSet.getInt("ordinicat");
					CategoriaCliente categoria = new CategoriaCliente(ccID,nomeCategoria,descrizione,fattore,ordiniCat);
					
					cliente.setCategoriaCliente(categoria);
					ordine.setCliente(cliente);
				}
				/* crea la colazione ordinata */
				int codiceCO = resultSet.getInt("codiceCO");
				ColazioneOrdinataID coID = new ColazioneOrdinataID(codiceCO);
				if (colazioneOrdinata == null || !coID.equals(colazioneOrdinata.getCodice())) {
					int codiceTC = resultSet.getInt("tipocolazione");
					TipoColazioneID tcID = new TipoColazioneID(codiceTC);
					TipoColazione tipoColazione = DAOTipoColazione.getInstance().doSelect(tcID,connessione);
					colazioneOrdinata = new ColazioneOrdinata(coID,tipoColazione);
					/* modo servizio */
					ModoServizioID msID = new ModoServizioID(resultSet.getInt("modoservizio"));
					String nomeMS = resultSet.getString("nomeMS");
					String descrMS = resultSet.getString("descrizioneMS");
					int fattoreMS = resultSet.getInt("fattore");
					ModoServizio ms = new ModoServizio(msID,nomeMS,descrMS,fattoreMS);
					colazioneOrdinata.setModoServizio(ms);
					ordine.getColazioniOrdinate().add(colazioneOrdinata);
				}
				/* variazioni */
				String nomeDC = resultSet.getString("nomeDC");
				if (nomeDC != null) {
					int nuovaQta = resultSet.getInt("nuovaqta");
					int codiceDC = resultSet.getInt("componente");
					DescrizioneComponenteID dcID = new DescrizioneComponenteID(codiceDC);				
					Prezzo pa = new Prezzo(resultSet.getDouble("prezzoaggiuntivo"));
					Prezzo pr = new Prezzo(resultSet.getDouble("prezzoriduttivo"));
					DescrizioneComponente dc = new DescrizioneComponente(dcID,nomeDC,pa,pr);
					Variazione variazione = new Variazione(colazioneOrdinata,dc,nuovaQta);
					colazioneOrdinata.getVariazioni().add(variazione);
				}
			}
		} catch (SQLException sqle) {
			throw new DAOException("doSelect_2_DAOOrdine : " + sqle);
		} finally {
			dataSource.chiudi(resultSet);
			dataSource.chiudi(stmt);
		}
		return ordine;
	}
	
	/**
	 * Restituisce tutti gli ordini registrati nella base di dati
	 * @return gli ordini registrati nella base di dati
	 * @throws DAOException
	 */
	public Map<OrdineID,Ordine> doSelect() throws DAOException {
		Connection connessione = null;
		Map<OrdineID,Ordine> ordini = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			connessione = dataSource.getConnessione();
			ordini = doSelect(connessione);
		} catch (SQLException sqle) {
			throw new DAOException("doSelectAll_1_DAOOrdine : " + sqle);
		} finally {
			dataSource.chiudi(connessione);
		}
		return ordini;
	}

	/**
	 * Restituisce tutti gli ordini registrati nella base di dati
	 * @param connessione la connessione alla base di dati
	 * @return gli ordini registrati nella base di dati
	 * @throws DAOException
	 */
	public Map<OrdineID,Ordine> doSelect(Connection connessione) throws DAOException {
		DataSource dataSource = DataSource.getInstance();
		Statement stmt = null;
		ResultSet resultSet = null;
		Ordine ordine = null;
		ColazioneOrdinata colazioneOrdinata = null;
		Map<OrdineID,Ordine> ordini = new HashMap<OrdineID,Ordine>();
		try {
			String query =
				"select ORDINE.codice, ORDINE.prezzo, ORDINE.data, ORDINE.consegnato, ORDINE.cliente, " +
				"CLIENTE.nome, CLIENTE.cognome, CLIENTE.indirizzo, CLIENTE.ordini, CLIENTE.categoria, " +
				"CATEGORIACLIENTE.nome AS nomecat, CATEGORIACLIENTE.descrizione, " + 
				"CATEGORIACLIENTE.ordini AS ordinicat, CATEGORIACLIENTE.fattorecliente, " +
				"COLAZIONEORDINATA.codice AS codiceCO, COLAZIONEORDINATA.tipocolazione, " + 
				"COLAZIONEORDINATA.modoservizio, " +
				"TIPOCOLAZIONE.nome AS nomeTC, TIPOCOLAZIONE.prezzo AS prezzoTC, " +
				"DESCRIZIONECOMPONENTE.nome AS nomeDC, DESCRIZIONECOMPONENTE.prezzoaggiuntivo, " +
				"DESCRIZIONECOMPONENTE.prezzoriduttivo, " +
				"VARIAZIONE.quantita AS nuovaqta, VARIAZIONE.componente, " +
				"MODOSERVIZIO.nome AS nomeMS, MODOSERVIZIO.descrizione AS descrizioneMS, MODOSERVIZIO.fattore " +
				"from ((((((ORDINE join CLIENTE on ORDINE.cliente = CLIENTE.codice) " +
				"join CATEGORIACLIENTE on CLIENTE.categoria = CATEGORIACLIENTE.codice) " +
				"join COLAZIONEORDINATA on ORDINE.codice = COLAZIONEORDINATA.ordine) " +
				"left join VARIAZIONE on COLAZIONEORDINATA.codice = VARIAZIONE.colazioneordinata) " +
				"left join DESCRIZIONECOMPONENTE on VARIAZIONE.componente = DESCRIZIONECOMPONENTE.codice) " +
				"join TIPOCOLAZIONE on COLAZIONEORDINATA.tipocolazione = TIPOCOLAZIONE.codice) " +
				"join MODOSERVIZIO on COLAZIONEORDINATA.modoservizio = MODOSERVIZIO.codice";
			stmt = connessione.createStatement();
			resultSet = stmt.executeQuery(query);
			while (resultSet.next()) {
				int codiceOrdine = resultSet.getInt("codice");
				OrdineID oID = new OrdineID(codiceOrdine);
				if (ordine == null || !ordine.getCodice().equals(oID)) {
					/* crea l'ordine */
					ordine = new Ordine(oID);
					Prezzo prezzoOrdine = new Prezzo(resultSet.getDouble("prezzo"));
					ordine.setPrezzo(prezzoOrdine);
					Date data = resultSet.getDate("data");
					ordine.setData(data);
					boolean statoConsegna = resultSet.getBoolean("consegnato");
					ordine.setConsegnato(statoConsegna);
					/* crea il cliente */
					ClienteID clID = new ClienteID(resultSet.getInt("cliente"));
					String nomeCliente = resultSet.getString("nome");
					String cognome = resultSet.getString("cognome");
					String indirizzo = resultSet.getString("indirizzo");
					Cliente cliente = new Cliente(clID,nomeCliente,cognome,indirizzo);
					int ordiniEffettuati = resultSet.getInt("ordini");
					cliente.setOrdiniEffettuati(ordiniEffettuati);
					/* crea la categoria del cliente */
					CategoriaClienteID ccID = new CategoriaClienteID(resultSet.getInt("categoria"));
					String nomeCategoria = resultSet.getString("nomecat");
					String descrizione = resultSet.getString("descrizione");
					double fattore = resultSet.getDouble("fattorecliente");
					int ordiniCat = resultSet.getInt("ordinicat");
					CategoriaCliente categoria = new CategoriaCliente(ccID,nomeCategoria,descrizione,fattore,ordiniCat);
					
					cliente.setCategoriaCliente(categoria);
					ordine.setCliente(cliente);
				}
				/* crea la colazione ordinata */
				int codiceCO = resultSet.getInt("codiceCO");
				ColazioneOrdinataID coID = new ColazioneOrdinataID(codiceCO);
				if (colazioneOrdinata == null || !coID.equals(colazioneOrdinata.getCodice())) {
					int codiceTC = resultSet.getInt("tipocolazione");
					TipoColazioneID tcID = new TipoColazioneID(codiceTC);
					TipoColazione tipoColazione = DAOTipoColazione.getInstance().doSelect(tcID,connessione);
					colazioneOrdinata = new ColazioneOrdinata(coID,tipoColazione);
					/* modo servizio */
					ModoServizioID msID = new ModoServizioID(resultSet.getInt("modoservizio"));
					String nomeMS = resultSet.getString("nomeMS");
					String descrMS = resultSet.getString("descrizioneMS");
					int fattoreMS = resultSet.getInt("fattore");
					ModoServizio ms = new ModoServizio(msID,nomeMS,descrMS,fattoreMS);
					colazioneOrdinata.setModoServizio(ms);
					ordine.getColazioniOrdinate().add(colazioneOrdinata);
				}
				/* variazioni */
				String nomeDC = resultSet.getString("nomeDC");
				if (nomeDC != null) {
					int nuovaQta = resultSet.getInt("nuovaqta");
					int codiceDC = resultSet.getInt("componente");
					DescrizioneComponenteID dcID = new DescrizioneComponenteID(codiceDC);
					Prezzo pa = new Prezzo(resultSet.getDouble("prezzoaggiuntivo"));
					Prezzo pr = new Prezzo(resultSet.getDouble("prezzoriduttivo"));
					DescrizioneComponente dc = new DescrizioneComponente(dcID,nomeDC,pa,pr);
					Variazione variazione = new Variazione(colazioneOrdinata,dc,nuovaQta);
					colazioneOrdinata.getVariazioni().add(variazione);
				}
				ordini.put(ordine.getCodice(),ordine);
			}
		} catch (SQLException sqle) {
			throw new DAOException("doSelectAll_2_DAOOrdine : " + sqle);
		} finally {
			dataSource.chiudi(resultSet);
			dataSource.chiudi(stmt);
		}
		return ordini;
	}
	
	/**
	 * Aggiorna un ordine registrato nella base di dati
	 * @param ordine l'ordine
	 * @throws DAOException
	 */
	public void doUpdate(Ordine ordine) throws DAOException {
		Connection connessione = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			connessione = dataSource.getConnessione();
			doUpdate(ordine,connessione);
		} catch (SQLException sqle) {
			throw new DAOException("doUpdate_1_DAOOrdine : " + sqle);
		} finally {
			dataSource.chiudi(connessione);
		}
	}
	
	/**
	 * Aggiorna un ordine registrato nella base di dati
	 * @param ordine l'ordine
	 * @param connessione la connessione alla base di dati
	 * @throws DAOException
	 */
	public void doUpdate(Ordine ordine, Connection connessione) throws DAOException {
		DataSource dataSource = DataSource.getInstance();
		PreparedStatement stmt = null;
		try {
			String query = "update ORDINE set prezzo = ?, data = ?, cliente = ?, consegnato = ? where codice = ?";
			stmt = connessione.prepareStatement(query);
			stmt.setBigDecimal(1,ordine.getPrezzo().getImporto());
			stmt.setDate(2,new java.sql.Date(ordine.getData().getTime()));
			stmt.setInt(3,ordine.getCliente().getCodice().getCodiceCliente());
			stmt.setBoolean(4,ordine.isConsegnato());
			stmt.setInt(5,ordine.getCodice().getCodiceOrdine());
			stmt.executeUpdate();
		} catch (SQLException sqle) {
			throw new DAOException("doUpdate_2_DAOOrdine : " + sqle);
		} finally {
			dataSource.chiudi(stmt);
		}
	}

	/**
	 * Cancella un ordine dalla base di dati
	 * @param ordine l'ordine
	 * @throws DAOException
	 */
	public void doDelete(OrdineID ordineID) throws DAOException {
		Connection connessione = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			connessione = dataSource.getConnessione();
			doDelete(ordineID,connessione);
		} catch (SQLException sqle) {
			throw new DAOException("doDelete_1_DAOOrdine : " + sqle);
		} finally {
			dataSource.chiudi(connessione);
		}
	}
	
	/**
	 * Cancella un ordine dalla base di dati
	 * @param ordine l'ordine
	 * @param connessione la connessione alla base di dati
	 * @throws DAOException
	 */
	public void doDelete(OrdineID ordineID, Connection connessione) throws DAOException {
		DataSource dataSource = DataSource.getInstance();
		PreparedStatement stmt = null;
		try {
			String query = "delete from ORDINE where codice = ?";
			stmt = connessione.prepareStatement(query);
			stmt.setInt(1,ordineID.getCodiceOrdine());
			stmt.executeUpdate();
		} catch (SQLException sqle) {
			throw new DAOException("doDelete_2_DAOOrdine : " + sqle);
		} finally {
			dataSource.chiudi(stmt);
		}
	}
}
