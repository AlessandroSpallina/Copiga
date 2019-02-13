package it.aps.cdr.persistenza.dao;

import it.aps.cdr.dominio.CategoriaCliente;
import it.aps.cdr.dominio.CategoriaClienteID;
import it.aps.cdr.dominio.Cliente;
import it.aps.cdr.dominio.ClienteID;
import it.aps.cdr.persistenza.DataSource;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Oggetto DAO per gli oggetti di tipo Cliente, acceduto come singleton.
 * 
 * @see Cliente
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri
 * @author Gabriele Rendina
 * 
 * @version 0.3
 *
 */
public class DAOCliente {

	/** l'istanza singleton di DAOCliente */
	private static DAOCliente singleton;	
	
	/** 
	 * Restituisce il riferimento all'istanza singleton di DAOCliente
	 * @return il riferimento all'istanza singleton di DAOCliente 
	 */
	public static synchronized DAOCliente getInstance(){
		if (singleton == null)
			singleton = new DAOCliente();
		return singleton;
	}
	
	/**
	 * Registra un cliente nella base di dati 
	 * @param cliente il cliente
	 * @throws DAOException 
	 */
	public void doInsert(Cliente cliente) throws DAOException{
		Connection connessione = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			connessione = dataSource.getConnessione();
			doInsert(cliente,connessione);
		} catch (SQLException sqle) {
			throw new DAOException("doInsert_1_DAOCliente : " + sqle);
		} finally {
			dataSource.chiudi(connessione);
		}
	}
	
	/**
	 * Registra un cliente nella base di dati. La chiave viene
	 * generata dal DBMS.
	 * Attenzione : da usare con MySQL e DB2  
	 * @param cliente il cliente
	 * @throws DAOException
	 */
	public void doInsertAGK(Cliente cliente) throws DAOException{
		Connection connessione = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			connessione = dataSource.getConnessione();
			doInsertAGK(cliente,connessione);
		} catch (SQLException sqle) {
			throw new DAOException("doInsertAGK_1_DAOCliente : " + sqle);
		} finally {
			dataSource.chiudi(connessione);
		}
	}

	/**
	 * Registra un cliente nella base di dati. La chiave viene
	 * generata dal GeneratoreID
	 * @param cliente il cliente
	 * @throws DAOException
	 */
	public void doInsertGID(Cliente cliente) throws DAOException {
		Connection connessione = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			connessione = dataSource.getConnessione();
			ClienteID cID = new ClienteID(GeneratoreID.getInstance().getNuovoCodice());
			cliente.setCodice(cID);
			doInsert(cliente,connessione);
		} catch (SQLException sqle) {
			throw new DAOException("doInsertGID_1_DAOCliente : " + sqle);
		} finally {
			dataSource.chiudi(connessione);
		}
	}
	
	/**
	 * Registra un cliente nella base di dati 
	 * @param cliente il cliente
	 * @param connessione la connessione alla base di dati 
	 * @throws DAOException 
	 */
	public void doInsert(Cliente cliente, Connection connessione) throws DAOException {
		DataSource dataSource = DataSource.getInstance();
		PreparedStatement stmt = null;
		try {
			String query = "insert into CLIENTE values (?,?,?,?,?,?)";
			stmt = connessione.prepareStatement(query);
			stmt.setInt(1,cliente.getCodice().getCodiceCliente());
			stmt.setString(2,cliente.getNome());
			stmt.setString(3,cliente.getCognome());
			stmt.setString(4,cliente.getIndirizzo());
			stmt.setInt(5,cliente.getOrdiniEffettuati());
			stmt.setInt(6,cliente.getCategoriaCliente().getCodice().getCodiceCategoriaCliente());
			stmt.executeUpdate();
		} catch (SQLException sqle) {
			throw new DAOException("doInsert_2_DAOCliente : " + sqle);
		} finally {
			dataSource.chiudi(stmt);
		}
		
	}
	
	/**
	 * Registra un cliente nella base di dati. La chiave
	 * viene generata automaticamente dal DBMS
	 * @param cliente il cliente
	 * @param connessione la connessione alla base di dati 
	 * @throws DAOException
	 */
	public void doInsertAGK(Cliente cliente, Connection connessione)	throws DAOException {
		DataSource dataSource = DataSource.getInstance();
		PreparedStatement stmt = null;	
		ResultSet resultSet = null;		
		try {
			String query = "insert into CLIENTE (nome, cognome, indirizzo, ordini, categoria) values (?,?,?,?,?)";
			stmt = connessione.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1,cliente.getNome());
			stmt.setString(2,cliente.getCognome());
			stmt.setString(3,cliente.getIndirizzo());
			stmt.setInt(4,cliente.getOrdiniEffettuati());
			stmt.setInt(5,cliente.getCategoriaCliente().getCodice().getCodiceCategoriaCliente());
			stmt.executeUpdate();
			resultSet = stmt.getGeneratedKeys();
			resultSet.next();
			ClienteID cID = new ClienteID(resultSet.getBigDecimal(1).intValue());
			cliente.setCodice(cID);		
		} catch (SQLException sqle) {
			throw new DAOException("doInsertAGK_2_DAOCliente : " + sqle);
		} finally {
			dataSource.chiudi(resultSet);
			dataSource.chiudi(stmt);
		}				
	}
	
	/**
	 * Restituisce un cliente registrato nella base di dati
	 * @param clienteID il codice identificativo del cliente
	 * @return il cliente registrato
	 * @throws DAOException
	 */
	public Cliente doSelect(ClienteID clienteID) throws DAOException{
		Connection connessione = null;
		Cliente cliente = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			connessione = dataSource.getConnessione();
			cliente = doSelect(clienteID,connessione);
		} catch (SQLException sqle) {
			throw new DAOException("doSelect_1_DAOCliente : " + sqle);
		} finally {
			dataSource.chiudi(connessione);
		}
		return cliente;
	}

	/**
	 * Restituisce un cliente registrato nella base di dati
	 * @param clienteID il codice identificativo del cliente
	 * @param connessione la connessione alla base di dati
	 * @return il cliente registrato
	 * @throws DAOException
	 */
	public Cliente doSelect(ClienteID clienteID, Connection connessione) throws DAOException {
		DataSource dataSource = DataSource.getInstance();
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		Cliente cliente = null;
		try {
			String query = 
			"select CLIENTE.codice, CLIENTE.nome, cognome, indirizzo, CLIENTE.ordini, CLIENTE.categoria, " +
			"CATEGORIACLIENTE.nome AS nomecategoria, descrizione, fattorecliente, " + 
			"CATEGORIACLIENTE.ordini AS ordinicategoria " +		
			"from CLIENTE join CATEGORIACLIENTE on CLIENTE.categoria = CATEGORIACLIENTE.codice " + 
			"where CLIENTE.codice = ?";
			stmt = connessione.prepareStatement(query);
			stmt.setInt(1,clienteID.getCodiceCliente());
			resultSet = stmt.executeQuery();
			resultSet.next();
			/* creazione del cliente */
			String nome = resultSet.getString("nome");
			String cognome = resultSet.getString("cognome");
			String indirizzo = resultSet.getString("indirizzo");
			cliente = new Cliente(clienteID,nome,cognome,indirizzo);
			int ordiniEffettuati = resultSet.getInt("ordini");
			cliente.setOrdiniEffettuati(ordiniEffettuati);
			/* creazione della categoria del cliente */
			int codiceCategoriaCliente = resultSet.getInt("categoria");
			CategoriaClienteID categoriaClienteID = new CategoriaClienteID(codiceCategoriaCliente);
			String nomeCategoria = resultSet.getString("nomecategoria");
			String descrizione = resultSet.getString("descrizione");
			double fattore = resultSet.getDouble("fattorecliente");
			int ordiniCategoria = resultSet.getInt("ordini");
			CategoriaCliente categoriaCliente = new CategoriaCliente(categoriaClienteID,nome,descrizione,fattore,ordiniCategoria);
			/* associa la categoria al cliente */
			cliente.setCategoriaCliente(categoriaCliente);
			
		} catch (SQLException sqle) {
			throw new DAOException("doSelect_2_DAOCliente : " + sqle);
		} finally {
			dataSource.chiudi(resultSet);
			dataSource.chiudi(stmt);
		}
		return cliente;
	}

	/**
	 * Restituisce i clienti omonimi registrati nella base di dati
	 * @param nome il nome del cliente
	 * @param cognome il cognome del cliente
	 * @return i clienti omonimi registrati
	 * @throws DAOException
	 */
	public Map<ClienteID,Cliente> doSelect(String nome, String cognome) throws DAOException{
		Connection connessione = null;
		Map<ClienteID,Cliente> clientiOmonimi = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			connessione = dataSource.getConnessione();
			clientiOmonimi = doSelect(nome,cognome,connessione);
		} catch (SQLException sqle) {
			throw new DAOException("doSelectNomeCognome_1_DAOCliente : " + sqle);
		} finally {
			dataSource.chiudi(connessione);
		}
		return clientiOmonimi;
	}
	
	/**
	 * Restituisce i clienti omonimi registrati nella base di dati
	 * @param nome il nome del cliente
	 * @param cognome il cognome del cliente
	 * @param connessione la connessione alla base di dati
	 * @return i clienti omonimi registrati
	 * @throws DAOException
	 */
	public Map<ClienteID,Cliente> doSelect(String nome, String cognome, Connection connessione) throws DAOException {
		DataSource dataSource = DataSource.getInstance();
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		Map<ClienteID,Cliente> clientiOmonimi = new HashMap<ClienteID,Cliente>();
		try {
			/* selezione esatta (=) */
/*			
			String query = 
				"select CLIENTE.codice, CLIENTE.nome, cognome, indirizzo, CLIENTE.ordini, CLIENTE.categoria, " +
				"CATEGORIACLIENTE.nome AS nomecategoria, descrizione, fattorecliente, " + 
				"CATEGORIACLIENTE.ordini AS ordinicategoria " +	
				"from CLIENTE join CATEGORIACLIENTE on CLIENTE.categoria = CATEGORIACLIENTE.codice " + 
				"where CLIENTE.nome = ? AND cognome = ?";
*/
			/* selezione approssimata (LIKE) */
			String query = 
				"select CLIENTE.codice, CLIENTE.nome, cognome, indirizzo, CLIENTE.ordini, CLIENTE.categoria, " +
				"CATEGORIACLIENTE.nome AS nomecategoria, descrizione, fattorecliente, " + 
				"CATEGORIACLIENTE.ordini AS ordinicategoria " +	
				"from CLIENTE join CATEGORIACLIENTE on CLIENTE.categoria = CATEGORIACLIENTE.codice " + 
				"where CLIENTE.nome LIKE ? AND cognome LIKE ?";
			stmt = connessione.prepareStatement(query);
			stmt.setString(1,nome);
			stmt.setString(2,cognome);
			resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				/* creazione del cliente */
				int codice = resultSet.getInt("codice");
				ClienteID clienteID = new ClienteID(codice);
				String nomeCliente = resultSet.getString("nome");
				String cognomeCliente = resultSet.getString("cognome");
				String indirizzo = resultSet.getString("indirizzo");
				Cliente cliente = new Cliente(clienteID,nomeCliente,cognomeCliente,indirizzo);
				int ordiniEffettuati = resultSet.getInt("ordini");
				cliente.setOrdiniEffettuati(ordiniEffettuati);
				/* creazione della categoria del cliente */
				int codiceCategoriaCliente = resultSet.getInt("categoria");
				CategoriaClienteID categoriaClienteID = new CategoriaClienteID(codiceCategoriaCliente);
				String nomeCategoria = resultSet.getString("nomecategoria");
				String descrizione = resultSet.getString("descrizione");
				double fattore = resultSet.getDouble("fattorecliente");
				int ordiniCategoria = resultSet.getInt("ordinicategoria");
				CategoriaCliente categoriaCliente = new CategoriaCliente(categoriaClienteID,nome,descrizione,fattore,ordiniCategoria);
				/* associa la categoria al cliente */
				cliente.setCategoriaCliente(categoriaCliente);
				/* aggiunge il cliente alla mappa dei clienti omonimi */
				clientiOmonimi.put(clienteID,cliente);
			}
		} catch (SQLException sqle) {
			throw new DAOException("doSelectNomeCognome_2_DAOCliente : " + sqle);
		} finally {
			dataSource.chiudi(resultSet);
			dataSource.chiudi(stmt);
		}
		return clientiOmonimi;
	}

	/**
	 * Restituisce tutti i clienti registrati nella base di dati
	 * @return tutti i clienti registrati
	 * @throws DAOException 
	 */
	public Map<ClienteID,Cliente> doSelect() throws DAOException {
		Map<ClienteID,Cliente> clienti = null;
		DataSource dataSource = DataSource.getInstance();
		Connection connessione = null;
		try {
			connessione = dataSource.getConnessione();
			clienti = doSelect(connessione);
		} catch (SQLException sqle) {
			throw new DAOException("doSelectAll_1_DAOCliente : " + sqle);
		} finally {
			dataSource.chiudi(connessione);
		}
		return clienti;
	}
	
	/**
	 * Restituisce tutti i clienti registrati nella base di dati
	 * @param connessione la connessione alla base di dati
	 * @return tutti i clienti registrati
	 * @throws DAOException 
	 */
	public Map<ClienteID, Cliente> doSelect(Connection connessione) throws DAOException {
		Map<ClienteID,Cliente> clienti = new HashMap<ClienteID,Cliente>();
		DataSource dataSource = DataSource.getInstance();
		Statement stmt = null;
		ResultSet resultSet = null;
		try {
			String query = 			
			"select CLIENTE.codice, CLIENTE.nome, cognome, indirizzo, CLIENTE.ordini, CLIENTE.categoria, " +
			"CATEGORIACLIENTE.nome AS nomecategoria, descrizione, fattorecliente, " + 
			"CATEGORIACLIENTE.ordini AS ordinicategoria " +
			"from CLIENTE join CATEGORIACLIENTE on CLIENTE.categoria = CATEGORIACLIENTE.codice";
			stmt = connessione.createStatement();
			resultSet = stmt.executeQuery(query);
			while (resultSet.next()) {
				/* creazione del cliente */
				int codice = resultSet.getInt("codice");
				ClienteID clienteID = new ClienteID(codice);
				String nome = resultSet.getString("nome");
				String cognome = resultSet.getString("cognome");
				String indirizzo = resultSet.getString("indirizzo");
				Cliente cliente = new Cliente(clienteID,nome,cognome,indirizzo);
				int ordiniEffettuati = resultSet.getInt("ordini");
				cliente.setOrdiniEffettuati(ordiniEffettuati);
				/* creazione della categoria del cliente */
				int codiceCategoriaCliente = resultSet.getInt("categoria");
				CategoriaClienteID categoriaClienteID = new CategoriaClienteID(codiceCategoriaCliente);
				String nomeCategoria = resultSet.getString("nomecategoria");
				String descrizione = resultSet.getString("descrizione");
				double fattore = resultSet.getDouble("fattorecliente");
				int ordiniCategoria = resultSet.getInt("ordinicategoria");
				CategoriaCliente categoriaCliente = new CategoriaCliente(categoriaClienteID,nomeCategoria,descrizione,fattore,ordiniCategoria);
				/* associa la categoria al cliente */
				cliente.setCategoriaCliente(categoriaCliente);
				
				clienti.put(clienteID,cliente);
			}
		} catch (SQLException sqle) {
			throw new DAOException("doSelectAll_2_DAOCliente : " + sqle);
		} finally {
			dataSource.chiudi(resultSet);
			dataSource.chiudi(stmt);
		}
		return clienti;
	}

	/**
	 * Aggiorna un cliente registrato nella base di dati 
	 * @param cliente il cliente
	 * @throws DAOException 
	 */
	public void doUpdate(Cliente cliente) throws DAOException{
		Connection connessione = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			connessione = dataSource.getConnessione();
			doUpdate(cliente,connessione);
		} catch (SQLException sqle) {
			throw new DAOException("doUpdate_1_DAOCliente : " + sqle);
		} finally {
			dataSource.chiudi(connessione);
		}
	}

	/**
	 * Aggiorna un cliente registrato nella base di dati 
	 * @param cliente il cliente
	 * @param connessione la connessione alla base di dati
	 * @throws DAOException 
	 */
	public void doUpdate(Cliente cliente, Connection connessione) throws DAOException {
		DataSource dataSource = DataSource.getInstance();
		PreparedStatement stmt = null;
		try {
			String query = 
			"update CLIENTE set nome = ?, cognome = ?, indirizzo = ?, ordini = ?, categoria = ? where codice = ?";
			stmt = connessione.prepareStatement(query);
			stmt.setString(1,cliente.getNome());
			stmt.setString(2,cliente.getCognome());
			stmt.setString(3,cliente.getIndirizzo());
			stmt.setInt(4,cliente.getOrdiniEffettuati());
			stmt.setInt(5,cliente.getCategoriaCliente().getCodice().getCodiceCategoriaCliente());
			stmt.setInt(6,cliente.getCodice().getCodiceCliente());
			stmt.executeUpdate();
		} catch (SQLException sqle) {
			throw new DAOException("doUpdate_2_DAOCliente : " + sqle);
		} finally {
			dataSource.chiudi(stmt);
		}
	}

	/**
	 * Cancella un cliente registrato dalla base di dati 
	 * @param cliente il cliente
	 * @throws DAOException
	 */
	public void doDelete(ClienteID clienteID) throws DAOException{
		Connection connessione = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			connessione = dataSource.getConnessione();
			doDelete(clienteID,connessione);
		} catch (SQLException sqle) {
			throw new DAOException("doDelete_1_DAOCliente : " + sqle);
		} finally {
			dataSource.chiudi(connessione);
		}
	}

	/**
	 * Cancella un cliente registrato dalla base di dati 
	 * @param cliente il cliente
	 * @param connessione la connessione alla base di dati 
	 * @throws DAOException
	 */
	public void doDelete(ClienteID clienteID, Connection connessione) throws DAOException {
		DataSource dataSource = DataSource.getInstance();
		PreparedStatement stmt = null;
		try {
			String query = "delete from CLIENTE where codice = ?";
			stmt = connessione.prepareStatement(query);
			stmt.setInt(1,clienteID.getCodiceCliente());
			stmt.executeUpdate();
		} catch (SQLException sqle) {
			throw new DAOException("doDelete_2_DAOCliente : " + sqle);
		} finally {
			dataSource.chiudi(stmt);
		}
	}

}
