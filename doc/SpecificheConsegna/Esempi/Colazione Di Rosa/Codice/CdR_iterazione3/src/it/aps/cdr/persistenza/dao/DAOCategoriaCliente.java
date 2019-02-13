package it.aps.cdr.persistenza.dao;

import it.aps.cdr.dominio.CategoriaCliente;
import it.aps.cdr.dominio.CategoriaClienteID;
import it.aps.cdr.persistenza.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * Oggetto DAO per gli oggetti di tipo CategoriaCliente, 
 * acceduto come singleton.
 * 
 * @see CategoriaCliente
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri
 * @author Gabriele Rendina
 * 
 * @version 0.3
 *
 */
public class DAOCategoriaCliente {
	
	/** l'istanza singleton di DAOCategoriaCliente */
	private static DAOCategoriaCliente singleton;	
	
	/** 
	 * Restituisce il riferimento all'istanza singleton di DAOCategoriaCliente
	 * @return il riferimento all'istanza singleton di DAOCategoriaCliente 
	 */
	public static synchronized DAOCategoriaCliente getInstance(){
		if (singleton == null)
			singleton = new DAOCategoriaCliente();
		return singleton;
	}
	
	/**
	 * Registra una categoria di cliente nella base di dati 
	 * @param categoriaCliente la categoria di cliente
	 * @throws DAOException
	 */
	public void doInsert(CategoriaCliente categoriaCliente) throws DAOException{
		Connection connessione = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			connessione = dataSource.getConnessione();
			doInsert(categoriaCliente,connessione);
		} catch (SQLException sqle) {
			throw new DAOException("doInsert_1_DAOCategoriaCliente : " + sqle);
		} finally {
			dataSource.chiudi(connessione);
		}
	}
	
	/**
	 * Registra una categoria di cliente nella base di dati. La chiave viene
	 * generata dal DBMS.
	 * Attenzione : da usare con MySQL e DB2 
	 * @param categoriaCliente la categoria di cliente
	 * @throws DAOException
	 */
	public void doInsertAGK(CategoriaCliente categoriaCliente) throws DAOException {
		Connection connessione = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			connessione = dataSource.getConnessione();
			doInsertAGK(categoriaCliente,connessione);
		} catch (SQLException sqle) {
			throw new DAOException("doInsertAGK_1_DAOCategoriaCliente : " + sqle);
		} finally {
			dataSource.chiudi(connessione);
		}
	}

	/**
	 * Registra una categoria di cliente nella base di dati. La chiave viene
	 * generata dal GeneratoreID 
	 * @param categoriaCliente la categoria di cliente
	 * @throws DAOException
	 */
	public void doInsertGID(CategoriaCliente categoriaCliente) throws DAOException {
		Connection connessione = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			connessione = dataSource.getConnessione();
			CategoriaClienteID ccID = new CategoriaClienteID(GeneratoreID.getInstance().getNuovoCodice());
			categoriaCliente.setCodice(ccID);
			doInsert(categoriaCliente,connessione);
		} catch (SQLException sqle) {
			throw new DAOException("doInsertGID_1_DAOCategoriaCliente : " + sqle);
		} finally {
			dataSource.chiudi(connessione);
		}
	}
	
	/**
	 * Registra una categoria di cliente nella base di dati 
	 * @param categoriaCliente la categoria di cliente
	 * @param connessione la connessione alla base di dati 
	 * @throws DAOException
	 */
	public void doInsert(CategoriaCliente categoriaCliente, Connection connessione) throws DAOException {
		DataSource dataSource = DataSource.getInstance();
		PreparedStatement stmt = null;
		try {
			String query = "insert into CATEGORIACLIENTE values (?,?,?,?,?)";
			stmt = connessione.prepareStatement(query);
			stmt.setInt(1,categoriaCliente.getCodice().getCodiceCategoriaCliente());
			stmt.setString(2,categoriaCliente.getNome());
			stmt.setString(3,categoriaCliente.getDescrizione());
			stmt.setInt(4,categoriaCliente.getOrdini());
			stmt.setDouble(5,categoriaCliente.getFattoreCliente());
			stmt.executeUpdate();
		} catch (SQLException sqle) {
			throw new DAOException("doInsert_2_DAOCategoriaCliente : " + sqle);
		} finally {
			dataSource.chiudi(stmt);
		}
		
	}
	
	/**
	 * Registra una categoria di cliente nella base di dati. La chiave
	 * viene generata automaticamente dal DBMS
	 * @param categoriaCliente la categoria di cliente
	 * @param connessione la connessione alla base di dati 
	 * @throws DAOException
	 */
	public void doInsertAGK(CategoriaCliente categoriaCliente, Connection connessione)	throws DAOException {
		DataSource dataSource = DataSource.getInstance();
		PreparedStatement stmt = null;	
		ResultSet resultSet = null;		
		try {
			String query = "insert into CATEGORIACLIENTE (nome, descrizione, ordini, fattorecliente) values (?,?,?,?)";
			stmt = connessione.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1,categoriaCliente.getNome());
			stmt.setString(2,categoriaCliente.getDescrizione());
			stmt.setInt(3,categoriaCliente.getOrdini());
			stmt.setDouble(4,categoriaCliente.getFattoreCliente());
			stmt.executeUpdate();
			resultSet = stmt.getGeneratedKeys();
			resultSet.next();
			CategoriaClienteID ccID = new CategoriaClienteID(resultSet.getBigDecimal(1).intValue());
			categoriaCliente.setCodice(ccID);		
		} catch (SQLException sqle) {
			throw new DAOException("doInsertAGK_2_DAOCategoriaCliente : " + sqle);
		} finally {
			dataSource.chiudi(resultSet);
			dataSource.chiudi(stmt);
		}				
	}
	
	/**
	 * Restituisce una categoria di clienti registrata nella base di dati
	 * @param categoriaClienteID il codice della categoria di cliente
	 * @return la categoria di cliente
	 * @throws DAOException
	 */
	public CategoriaCliente doSelect(CategoriaClienteID categoriaClienteID) throws DAOException{
		Connection connessione = null;
		CategoriaCliente categoriaCliente = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			connessione = dataSource.getConnessione();
			categoriaCliente = doSelect(categoriaClienteID,connessione);
		} catch (SQLException sqle) {
			throw new DAOException("doSelect_1_DAOCategoriaCliente : " + sqle);
		} finally {
			dataSource.chiudi(connessione);
		}
		return categoriaCliente;
	}

	/**
	 * Restituisce una categoria di clienti registrata nella base di dati
	 * @param categoriaClienteID il codice della categoria di cliente
	 * @param connessione la connessione alla base di dati
	 * @return la categoria di cliente
	 * @throws DAOException
	 */
	public CategoriaCliente doSelect(CategoriaClienteID categoriaClienteID, Connection connessione) throws DAOException {
		DataSource dataSource = DataSource.getInstance();
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		CategoriaCliente categoriaCliente = null;
		try {
			String query = "select * from CATEGORIACLIENTE where codice = ?";
			stmt = connessione.prepareStatement(query);
			stmt.setInt(1,categoriaClienteID.getCodiceCategoriaCliente());
			resultSet = stmt.executeQuery();
			resultSet.next();
			int ordini = resultSet.getInt("ordini");
			String nome = resultSet.getString("nome");
			String descrizione = resultSet.getString("descrizione");
			double fattoreCliente = resultSet.getDouble("fattorecliente");
			categoriaCliente = new CategoriaCliente(categoriaClienteID,nome,descrizione,fattoreCliente,ordini);

		} catch (SQLException sqle) {
			throw new DAOException("doSelect_2_DAOCategoriaCliente : " + sqle);
		} finally {
			dataSource.chiudi(resultSet);
			dataSource.chiudi(stmt);
		}
		return categoriaCliente;
	}

	/**
	 * Restituisce tutte le categorie di clienti registrate nella base di dati
	 * @return le categorie di clienti
	 * @throws DAOException
	 */
	public Map<CategoriaClienteID,CategoriaCliente> doSelect() throws DAOException{
		Connection connessione = null;
		Map<CategoriaClienteID,CategoriaCliente> categoriaCliente = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			connessione = dataSource.getConnessione();
			categoriaCliente = doSelect(connessione);
		} catch (SQLException sqle) {
			throw new DAOException("doSelectAll_1_DAOCategoriaCliente : " + sqle);
		} finally {
			dataSource.chiudi(connessione);
		}
		return categoriaCliente;
	}
	
	/**
	 * Restituisce tutte le categorie di clienti registrate nella base di dati
	 * @return le categorie di clienti
	 * @param connessione la connessione alla base di dati
	 * @throws DAOException 
	 * @throws DAOException
	 */
	public Map<CategoriaClienteID,CategoriaCliente> doSelect(Connection connessione) throws DAOException {
		Statement stmt = null;
		ResultSet resultSet = null;
		Map<CategoriaClienteID,CategoriaCliente> clientiOmonimi = new HashMap<CategoriaClienteID,CategoriaCliente>();
		DataSource dataSource = DataSource.getInstance();
		try {
			stmt = connessione.createStatement();
			String query = "select * from CATEGORIACLIENTE";
			resultSet = stmt.executeQuery(query);
			while (resultSet.next()) {
				int codice = resultSet.getInt("codice");
				CategoriaClienteID ccID = new CategoriaClienteID(codice);
				String nome = resultSet.getString("nome");
				String descrizione = resultSet.getString("descrizione");
				double fattore = resultSet.getDouble("fattorecliente");
				int ordini = resultSet.getInt("ordini");
				CategoriaCliente cc = new CategoriaCliente(ccID,nome,descrizione,fattore,ordini);
				clientiOmonimi.put(ccID,cc);
			}
		} catch (SQLException sqle) {
			throw new DAOException("doSelectAll_2_DAOCategoriaCliente : " + sqle);
		} finally {
			dataSource.chiudi(resultSet);
			dataSource.chiudi(stmt);
		}
		return clientiOmonimi;
	}

	/**
	 * Aggiorna una categoria di clienti registrata nella base di dati 
	 * @param categoriaCliente la categoria di clienti
	 * @throws DAOException 
	 */
	public void doUpdate(CategoriaCliente categoriaCliente) throws DAOException{
		Connection connessione = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			connessione = dataSource.getConnessione();
			doUpdate(categoriaCliente,connessione);
		} catch (SQLException sqle) {
			throw new DAOException("doUpdate_1_DAOCategoriaCliente : " + sqle);
		} finally {
			dataSource.chiudi(connessione);
		}
	}

	/**
	 * Aggiorna una categoria di clienti registrata nella base di dati 
	 * @param categoriaCliente la categoria di clienti
	 * @param connessione la connessione alla base di dati
	 * @throws DAOException 
	 */
	public void doUpdate(CategoriaCliente categoriaCliente, Connection connessione) throws DAOException {
		DataSource dataSource = DataSource.getInstance();
		PreparedStatement stmt = null;
		try {
			String query = "update CATEGORIACLIENTE set ordini = ?, nome = ?, descrizione = ?, fattorecliente = ? where codice = ?";
			stmt = connessione.prepareStatement(query);
			stmt.setInt(1,categoriaCliente.getOrdini());
			stmt.setString(2,categoriaCliente.getNome());
			stmt.setString(3,categoriaCliente.getDescrizione());
			stmt.setDouble(4,categoriaCliente.getFattoreCliente());
			stmt.setInt(5,categoriaCliente.getCodice().getCodiceCategoriaCliente());
			stmt.executeUpdate();
		} catch (SQLException sqle) {
			throw new DAOException("doUpdate_2_DAOCategoriaCliente : " + sqle);
		} finally {
			dataSource.chiudi(stmt);
		}
	}


	/**
	 * Cancella una categoria di clienti registrata ella base di dati 
	 * @param categoriaCliente la categoria di clienti
	 * @throws DAOException
	 */
	public void doDelete(CategoriaClienteID categoriaClienteID) throws DAOException{
		Connection connessione = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			connessione = dataSource.getConnessione();
			doDelete(categoriaClienteID,connessione);
		} catch (SQLException sqle) {
			throw new DAOException("doDelete_1_DAOCategoriaCliente : " + sqle);
		} finally {
			dataSource.chiudi(connessione);
		}
	}

	/**
	 * Cancella una categoria di clienti registrata ella base di dati 
	 * @param categoriaCliente la categoria di clienti
	 * @param connessione la connessione alla base di dati
	 * @throws DAOException
	 */
	public void doDelete(CategoriaClienteID categoriaClienteID, Connection connessione) throws DAOException {
		DataSource dataSource = DataSource.getInstance();
		PreparedStatement stmt = null;
		try {
			String query = "delete from CATEGORIACLIENTE where codice = ?";
			stmt = connessione.prepareStatement(query);
			stmt.setInt(1,categoriaClienteID.getCodiceCategoriaCliente());
			stmt.executeUpdate();
		} catch (SQLException sqle) {
			throw new DAOException("doDelete_2_DAOCategoriaCliente : " + sqle);
		} finally {
			dataSource.chiudi(stmt);
		}
		
	}
}
