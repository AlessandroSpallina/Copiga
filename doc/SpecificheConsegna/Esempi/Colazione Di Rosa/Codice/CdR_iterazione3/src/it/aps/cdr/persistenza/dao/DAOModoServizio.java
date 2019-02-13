package it.aps.cdr.persistenza.dao;

import it.aps.cdr.dominio.ModoServizio;
import it.aps.cdr.dominio.ModoServizioID;
import it.aps.cdr.persistenza.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * Oggetto DAO per gli oggetti di tipo ModoServizio, acceduto come 
 * singleton.
 * 
 * @see ModoServizio
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri
 * @author Gabriele Rendina
 * 
 * @version 0.3
 *
 */
public class DAOModoServizio {

	/** l'istanza singleton di DAOModoServizio */
	private static DAOModoServizio singleton;	
	
	/** 
	 * Restituisce il riferimento all'istanza singleton di DAOModoServizio
	 * @return il riferimento all'istanza singleton di DAOModoServizio 
	 */
	public static synchronized DAOModoServizio getInstance(){
		if (singleton == null)
			singleton = new DAOModoServizio();
		return singleton;
	}
	
	/**
	 * Registra una modalita' di servizio nella base di dati
	 * @param modoServizio la modalita' di servizio
	 * @throws DAOException
	 */
	public void doInsert(ModoServizio modoServizio) throws DAOException{
		Connection connessione = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			connessione = dataSource.getConnessione();
			doInsert(modoServizio,connessione);
		} catch (SQLException sqle) {
			throw new DAOException("doInsert_1_DAOModoServizio : " + sqle);
		} finally {
			dataSource.chiudi(connessione);
		}
	}

	/**
	 * Registra una modalita' di servizio nella base di dati. La chiave viene
	 * generata dal DBMS.
	 * Attenzione : da usare con MySQL e DB2  
	 * @param modoServizio la modalita' di servizio
	 * @throws DAOException
	 */
	public void doInsertAGK(ModoServizio modoServizio) throws DAOException{
		Connection connessione = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			connessione = dataSource.getConnessione();
			doInsertAGK(modoServizio,connessione);
		} catch (SQLException sqle) {
			throw new DAOException("doInsertAGK_1_DAOModoServizio : " + sqle);
		} finally {
			dataSource.chiudi(connessione);
		}
	}
	
	/**
	 * Registra una modalita' di servizio nella base di dati. La chiave viene
	 * generata dal GeneratoreID 
	 * @param modoServizio la modalita' di servizio
	 * @throws DAOException
	 */
	public void doInsertGID(ModoServizio modoServizio) throws DAOException{
		Connection connessione = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			connessione = dataSource.getConnessione();
			ModoServizioID msID = new ModoServizioID(GeneratoreID.getInstance().getNuovoCodice());
			modoServizio.setCodice(msID);
			doInsert(modoServizio,connessione);
		} catch (SQLException sqle) {
			throw new DAOException("doInsertGID_1_DAOModoServizio : " + sqle);
		} finally {
			dataSource.chiudi(connessione);
		}
	}
	
	/**
	 * Registra una modalita' di servizio nella base di dati
	 * @param modoServizio la modalita' di servizio
	 * @param connessione la connessione alla base di dati
	 * @throws DAOException
	 */
	public void doInsert(ModoServizio modoServizio, Connection connessione) throws DAOException {
		DataSource dataSource = DataSource.getInstance();
		PreparedStatement stmt = null;
		try {
			String query = "insert into MODOSERVIZIO values (?,?,?,?)";
			stmt = connessione.prepareStatement(query);
			stmt.setInt(1,modoServizio.getCodice().getCodiceModoServizio());
			stmt.setString(2,modoServizio.getNome());
			stmt.setString(3,modoServizio.getDescrizione());
			stmt.setDouble(4,modoServizio.getFattoreMoltiplicativo());
			stmt.executeUpdate();
		} catch (SQLException sqle) {
			throw new DAOException("doInsert_2_DAOModoServizio : " + sqle);
		} finally {
			dataSource.chiudi(stmt);
		}
		
	}
	
	/**
	 * Registra una modalita' di servizio nella base di dati. La chiave
	 * viene generata automaticamente dal DBMS
	 * @param modoServizio la modalita' di servizio
	 * @param connessione la connessione alla base di dati 
	 * @throws DAOException
	 */
	public void doInsertAGK(ModoServizio modoServizio, Connection connessione)	throws DAOException {
		DataSource dataSource = DataSource.getInstance();
		PreparedStatement stmt = null;	
		ResultSet resultSet = null;		
		try {
			String query = "insert into MODOSERVIZIO (nome, descrizione, fattore) values (?,?,?)";
			stmt = connessione.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1,modoServizio.getNome());
			stmt.setString(2,modoServizio.getDescrizione());
			stmt.setDouble(3,modoServizio.getFattoreMoltiplicativo());
			stmt.executeUpdate();
			resultSet = stmt.getGeneratedKeys();
			resultSet.next();
			ModoServizioID msID = new ModoServizioID(resultSet.getBigDecimal(1).intValue());
			modoServizio.setCodice(msID);		
		} catch (SQLException sqle) {
			throw new DAOException("doInsertAGK_2_DAOModoServizio : " + sqle);
		} finally {
			dataSource.chiudi(resultSet);
			dataSource.chiudi(stmt);
		}				
	}
	
	/**
	 * Restituisce una modalita' di servizio registrata nella base di dati
	 * @param modoServizioID il codice della modalita' di servizio
	 * @return la modalita' di servizio
	 * @throws DAOException
	 */
	public ModoServizio doSelect(ModoServizioID modoServizioID) throws DAOException{
		Connection connessione = null;
		ModoServizio modoServizio = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			connessione = dataSource.getConnessione();
			modoServizio = doSelect(modoServizioID,connessione);
		} catch (SQLException sqle) {
			throw new DAOException("doSelect_1_DAOModoServizio : " + sqle);
		} finally {
			dataSource.chiudi(connessione);
		}
		return modoServizio;
	}

	/**
	 * Restituisce una modalita' di servizio registrata nella base di dati
	 * @param modoServizioID il codice della modalita' di servizio
	 * @param connessione la connessione alla base di dati
	 * @return la modalita' di servizio
	 * @throws DAOException
	 */
	public ModoServizio doSelect(ModoServizioID modoServizioID, Connection connessione) throws DAOException {
		DataSource dataSource = DataSource.getInstance();
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		ModoServizio modoServizio = null;
		try {
			String query = "select * from MODOSERVIZIO where codice = ?";
			stmt = connessione.prepareStatement(query);
			stmt.setInt(1,modoServizio.getCodice().getCodiceModoServizio());
			resultSet = stmt.executeQuery();
			resultSet.next();
			String nome = resultSet.getString("nome");
			String descrizione = resultSet.getString("descrizione");
			double fattore = resultSet.getDouble("fattore");
			modoServizio = new ModoServizio(modoServizioID,nome,descrizione,fattore);
			
		} catch (SQLException sqle) {
			throw new DAOException("doSelect_2_DAOModoServizio : " + sqle);
		} finally {
			dataSource.chiudi(resultSet);
			dataSource.chiudi(stmt);
		}
		return modoServizio;
	}

	/**
	 * Restituisce tutte le modalita' di servizio registrate nella base di dati
	 * @return le modalita' di servizio registrate
	 * @throws DAOException
	 */
	public Map<ModoServizioID,ModoServizio> doSelect() throws DAOException {
		Map<ModoServizioID,ModoServizio> modiServizio = null;
		Connection connessione = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			connessione = dataSource.getConnessione();
			modiServizio = doSelect(connessione);
		} catch (SQLException sqle) {
			throw new DAOException("doSelectAll_1_DAOModoServizio : " + sqle);
		} finally {
			dataSource.chiudi(connessione);
		}
		return modiServizio;
	}
	
	/**
	 * Restituisce tutte le modalita' di servizio registrate nella base di dati
	 * @param connessione la connessione alla base di dati
	 * @return le modalita' di servizio registrate
	 * @throws DAOException
	 */
	public Map<ModoServizioID,ModoServizio> doSelect(Connection connessione) throws DAOException {
		Map<ModoServizioID,ModoServizio> modiServizio = new HashMap<ModoServizioID,ModoServizio>();
		DataSource dataSource = DataSource.getInstance();
		Statement stmt = null;
		ResultSet resultSet = null;
		try {
			stmt = connessione.createStatement();
			String query = "select * from MODOSERVIZIO";
			resultSet = stmt.executeQuery(query);
			while (resultSet.next()) {
				int codice = resultSet.getInt("codice");
				ModoServizioID msID = new ModoServizioID(codice);
				String nome = resultSet.getString("nome");
				String descrizione = resultSet.getString("descrizione");
				double fattore = resultSet.getDouble("fattore");
				ModoServizio ms = new ModoServizio(msID,nome,descrizione,fattore);
				
				modiServizio.put(msID,ms);
			}
		} catch (SQLException sqle) {
			throw new DAOException("doSelectAll_2_DAOModoServizio : " + sqle);
		} finally {
			dataSource.chiudi(resultSet);
			dataSource.chiudi(stmt);
		}
		return modiServizio;
	}
	
	/**
	 * Aggiorna una modalita' di servizio registrata nella base di dati
	 * @param modoServizio la modalita' di servizio 
	 * @throws DAOException
	 */
	public void doUpdate(ModoServizio modoServizio) throws DAOException{
		Connection connessione = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			connessione = dataSource.getConnessione();
			doUpdate(modoServizio,connessione);
		} catch (SQLException sqle) {
			throw new DAOException("doUpdate_1_DAOModoServizio : " + sqle);
		} finally {
			dataSource.chiudi(connessione);
		}
	}

	/**
	 * Aggiorna una modalita' di servizio registrata nella base di dati
	 * @param modoServizio la modalita' di servizio 
	 * @param connessione la connessione alla base di dati
	 * @throws DAOException
	 */
	public void doUpdate(ModoServizio modoServizio, Connection connessione) throws DAOException {
		DataSource dataSource = DataSource.getInstance();
		PreparedStatement stmt = null;
		try {
			String query = "update MODOSERVIZIO set nome = ?, descrizione = ?, fattore = ? where codice = ?";
			stmt = connessione.prepareStatement(query);
			stmt.setString(1,modoServizio.getNome());
			stmt.setString(2,modoServizio.getDescrizione());
			stmt.setDouble(3,modoServizio.getFattoreMoltiplicativo());
			stmt.setInt(4,modoServizio.getCodice().getCodiceModoServizio());
			stmt.executeUpdate();
		} catch (SQLException sqle) {
			throw new DAOException("doUpdate_2_DAOModoServizio : " + sqle);
		} finally {
			dataSource.chiudi(stmt);
		}
	}
	
	/**
	 * Cancella una modalita' di servizio registrata nella base di dati
	 * @param modoServizio la modalita' di servizio
	 * @throws DAOException
	 */
	public void doDelete(ModoServizioID modoServizioID) throws DAOException{
		Connection connessione = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			connessione = dataSource.getConnessione();
			doDelete(modoServizioID,connessione);
		} catch (SQLException sqle) {
			throw new DAOException("doDelete_1_DAOModoServizio : " + sqle);
		} finally {
			dataSource.chiudi(connessione);
		}
	}

	/**
	 * Cancella una modalita' di servizio registrata nella base di dati
	 * @param modoServizio la modalita' di servizio
	 * @param connessione la connessione alla base di dati
	 * @throws DAOException
	 */
	public void doDelete(ModoServizioID modoServizioID, Connection connessione) throws DAOException {
		DataSource dataSource = DataSource.getInstance();
		PreparedStatement stmt = null;
		try {
			String query = "delete from MODOSERVIZIO where codice = ?";
			stmt = connessione.prepareStatement(query);
			stmt.setInt(1,modoServizioID.getCodiceModoServizio());
			stmt.executeUpdate();
		} catch (SQLException sqle) {
			throw new DAOException("doDelete_2_DAOModoServizio : " + sqle);
		} finally {
			dataSource.chiudi(stmt);
		}
		
	}
	
}
