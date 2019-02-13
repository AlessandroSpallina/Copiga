package it.aps.cdr.persistenza.dao;

import it.aps.cdr.dominio.DescrizioneComponente;
import it.aps.cdr.dominio.DescrizioneComponenteID;
import it.aps.cdr.dominio.Prezzo;
import it.aps.cdr.persistenza.DataSource;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * Oggetto DAO per gli oggetti di tipo DescrizioneComponente, acceduto come
 * singleton.
 * 
 * @see DescrizioneComponente
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri
 * @author Gabriele Rendina
 * 
 * @version 0.3
 *
 */
public class DAODescrizioneComponente {

	/** l'istanza singleton di DAODescrizioneComponente */
	private static DAODescrizioneComponente singleton;	
	
	/** 
	 * Restituisce il riferimento all'istanza singleton di DAODescrizioneComponente
	 * @return il riferimento all'istanza singleton di DAODescrizioneComponente 
	 */
	public static synchronized DAODescrizioneComponente getInstance(){
		if (singleton == null)
			singleton = new DAODescrizioneComponente();
		return singleton;
	}
	
	/**
	 * Registra una descrizione di una componente nella base di dati
	 * @param descrizioneComponente la descrizione di una componente 
	 * @throws DAOException
	 */
	public void doInsert(DescrizioneComponente descrizioneComponente) throws DAOException {
		Connection connessione = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			connessione = dataSource.getConnessione();
			doInsert(descrizioneComponente,connessione);
		} catch (SQLException sqle) {
			throw new DAOException("doInsert_1_DAODescrizioneComponente" + sqle);
		} finally {
			dataSource.chiudi(connessione);
		}
	}
	
	/**
	 * Registra una descrizione di una componente nella base di dati. La chiave viene
	 * generata dal DBMS.
	 * Attenzione : da usare con MySQL e DB2  
	 * @param descrizioneComponente la descrizione della componente
	 * @throws DAOException
	 */
	public void doInsertAGK(DescrizioneComponente descrizioneComponente) throws DAOException {
		Connection connessione = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			connessione = dataSource.getConnessione();
			doInsertAGK(descrizioneComponente,connessione);
		} catch (SQLException sqle) {
			throw new DAOException("doInsertAGK_1_DAODescrizioneComponente : " + sqle);
		} finally {
			dataSource.chiudi(connessione);
		}
	}

	/**
	 * Registra una descrizione di una componente nella base di dati. La chiave viene
	 * generata dal GeneratoreID 
	 * @param descrizioneComponente la descrizione della componente
	 * @throws DAOException
	 */
	public void doInsertGID(DescrizioneComponente descrizioneComponente) throws DAOException {
		Connection connessione = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			connessione = dataSource.getConnessione();
			DescrizioneComponenteID dcID = new DescrizioneComponenteID(GeneratoreID.getInstance().getNuovoCodice());
			descrizioneComponente.setCodice(dcID);
			doInsert(descrizioneComponente,connessione);
		} catch (SQLException sqle) {
			throw new DAOException("doInsertGID_1_DAODescrizioneComponente : " + sqle);
		} finally {
			dataSource.chiudi(connessione);
		}
	}
	
	/**
	 * Registra una descrizione di una componente nella base di dati
	 * @param descrizioneComponente la descrizione di una componente 
	 * @param connessione la connessione alla base di dati
	 * @throws DAOException
	 */
	public void doInsert(DescrizioneComponente descrizioneComponente, Connection connessione) throws DAOException {
		PreparedStatement stmt = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			String query = "insert into DESCRIZIONECOMPONENTE values (?,?,?,?)";
			stmt = connessione.prepareStatement(query);
			stmt.setInt(1,descrizioneComponente.getCodice().getCodiceDescrizioneComponente());
			stmt.setString(2,descrizioneComponente.getNome());
			stmt.setBigDecimal(3,descrizioneComponente.getPrezzoAggiuntivo().getImporto());
			stmt.setBigDecimal(4,descrizioneComponente.getPrezzoRiduttivo().getImporto());
			stmt.executeUpdate();
		} catch (SQLException sqle) {
			throw new DAOException("doInsert_2_DAODescrizioneComponente" + sqle);
		} finally {
			dataSource.chiudi(stmt);
		}
	}
	
	/**
	 * Registra una descrizione di una componente nella base di dati. La chiave
	 * viene generata automaticamente dal DBMS
	 * @param descrizioneComponente il tipo di colazione
	 * @param connessione la connessione alla base di dati 
	 * @throws DAOException
	 */
	public void doInsertAGK(DescrizioneComponente descrizioneComponente, Connection connessione) throws DAOException {
		DataSource dataSource = DataSource.getInstance();
		PreparedStatement stmt = null;	
		ResultSet resultSet = null;		
		try {
			String query = "insert into DESCRIZIONECOMPONENTE (nome, prezzoaggiuntivo, prezzoriduttivo) values (?,?,?)";
			stmt = connessione.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1,descrizioneComponente.getNome());
			stmt.setBigDecimal(2,descrizioneComponente.getPrezzoAggiuntivo().getImporto());
			stmt.setBigDecimal(3,descrizioneComponente.getPrezzoRiduttivo().getImporto());
			stmt.executeUpdate();
			resultSet = stmt.getGeneratedKeys();
			resultSet.next();
			DescrizioneComponenteID dcID = new DescrizioneComponenteID(resultSet.getBigDecimal(1).intValue());
			descrizioneComponente.setCodice(dcID);		
		} catch (SQLException sqle) {
			throw new DAOException("doInsertAGK_2_DAODescrizioneComponente : " + sqle);
		} finally {
			dataSource.chiudi(resultSet);
			dataSource.chiudi(stmt);
		}				
	}
	
	/**
	 * Restituisce la descrizione di una componente registrata nella base di dati
	 * @param descrizioneComponenteID il codice della componente
	 * @return la descrizione della componente
	 * @throws DAOException
	 */
	public DescrizioneComponente doSelect(DescrizioneComponenteID descrizioneComponenteID) throws DAOException {
		DescrizioneComponente descrizioneComponente = null;
		Connection connessione = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			connessione = dataSource.getConnessione();
			descrizioneComponente = doSelect(descrizioneComponenteID,connessione);
		} catch (SQLException sqle) {
			throw new DAOException("doSelect_1_DAODescrizioneComponente" + sqle);
		} finally {
			dataSource.chiudi(connessione);
		}
		return descrizioneComponente;
	}

	/**
	 * Restituisce la descrizione di una componente registrata nella base di dati
	 * @param descrizioneComponenteID il codice della componente
	 * @param connessione la connessione alla base di dati
	 * @return la descrizione della componente
	 * @throws DAOException
	 */
	public DescrizioneComponente doSelect(DescrizioneComponenteID descrizioneComponenteID, Connection connessione) throws DAOException {
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		DescrizioneComponente descrizioneComponente = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			String query = "select * from DESCRIZIONECOMPONENTE where codice = ?";
			stmt = connessione.prepareStatement(query);
			stmt.setInt(1,descrizioneComponenteID.getCodiceDescrizioneComponente());
			resultSet = stmt.executeQuery();
			resultSet.next();
			String nome = resultSet.getString("nome");
			BigDecimal importoAggiuntivoComponente = new BigDecimal(resultSet.getDouble("prezzoaggiuntivo"));
			Prezzo prezzoAggiuntivo = new Prezzo(importoAggiuntivoComponente);
			BigDecimal importoRiduttivoComponente = new BigDecimal(resultSet.getDouble("prezzoriduttivo"));
			Prezzo prezzoRiduttivo = new Prezzo(importoRiduttivoComponente);
			descrizioneComponente = new DescrizioneComponente(descrizioneComponenteID,nome,prezzoAggiuntivo,prezzoRiduttivo);
		} catch (SQLException sqle) {
			throw new DAOException("doSelect_2_DAODescrizioneComponente" + sqle);
		} finally {
			dataSource.chiudi(resultSet);
			dataSource.chiudi(stmt);
		}
		return descrizioneComponente;
	}
	
	/**
	 * Restituisce tutte le descrizioni delle componenti registrate nella base di dati
	 * @return il catalogo delle descrizioni delle componenti
	 * @throws DAOException
	 */
	public Map<DescrizioneComponenteID,DescrizioneComponente> doSelect() throws DAOException{
		Connection connessione = null;
		Map<DescrizioneComponenteID,DescrizioneComponente> catalogo = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			connessione = dataSource.getConnessione();
			catalogo = doSelect(connessione);
		} catch (SQLException sqle) {
			throw new DAOException("doSelectAll_1_DAODescrizioneComponente" + sqle);
		} finally {
			dataSource.chiudi(connessione);
		}
		return catalogo;
	}
	
	/**
	 * Restituisce tutte le descrizioni delle componenti registrate nella base di dati
	 * @param connessione la connessione alla base di dati
	 * @return il catalogo delle descrizioni delle componenti
	 * @throws DAOException
	 */
	public Map<DescrizioneComponenteID, DescrizioneComponente> doSelect(Connection connessione) throws DAOException {
		Map<DescrizioneComponenteID,DescrizioneComponente> catalogo = new HashMap<DescrizioneComponenteID,DescrizioneComponente>();
		ResultSet resultSet = null;
		Statement stmt = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			stmt = connessione.createStatement();
			String query = "select * from DESCRIZIONECOMPONENTE";
			resultSet = stmt.executeQuery(query);
			while (resultSet.next()) {
				int codice = resultSet.getInt("codice");
				DescrizioneComponenteID dcID = new DescrizioneComponenteID(codice);
				String nome = resultSet.getString("nome");
				BigDecimal pa = new BigDecimal(resultSet.getDouble("prezzoaggiuntivo"));
				Prezzo prezzoAgg = new Prezzo(pa);
				BigDecimal pr = new BigDecimal(resultSet.getDouble("prezzoriduttivo"));
				Prezzo prezzoRid = new Prezzo(pr);
				DescrizioneComponente descrizioneComponente = new DescrizioneComponente(dcID,nome,prezzoAgg,prezzoRid);
				
				catalogo.put(dcID,descrizioneComponente);
			}
		} catch (SQLException sqle) {
			throw new DAOException("doSelectAll_2_DAODescrizioneComponente" + sqle);
		} finally {
			dataSource.chiudi(resultSet);
			dataSource.chiudi(stmt);
		}
		return catalogo;
	}

	/**
	 * Aggiorna una descrizione di una componente registrata nella base di dati
	 * @param descrizioneComponente la descrizione della componente
	 * @throws DAOException
	 */
	public void doUpdate(DescrizioneComponente descrizioneComponente) throws DAOException {
		Connection connessione = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			connessione = dataSource.getConnessione();
			doUpdate(descrizioneComponente,connessione);
		} catch (SQLException sqle) {
			throw new DAOException("doUpdate_1_DAODescrizioneComponente" + sqle);
		} finally {
			dataSource.chiudi(connessione);
		}
	}

	/**
	 * Aggiorna una descrizione di una componente registrata nella base di dati
	 * @param descrizioneComponente la descrizione della componente
	 * @param connessione la connessione alla base di dati
	 * @throws DAOException
	 */
	public void doUpdate(DescrizioneComponente descrizioneComponente, Connection connessione) throws DAOException {
		PreparedStatement stmt = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			String query = "update DESCRIZIONECOMPONENTE set nome = ?, prezzoaggiuntivo = ?, prezzoriduttivo = ? where codice = ?";
			stmt = connessione.prepareStatement(query);
			stmt.setString(1,descrizioneComponente.getNome());
			stmt.setBigDecimal(2,descrizioneComponente.getPrezzoAggiuntivo().getImporto());
			stmt.setBigDecimal(3,descrizioneComponente.getPrezzoRiduttivo().getImporto());
			stmt.setInt(4,descrizioneComponente.getCodice().getCodiceDescrizioneComponente());
			stmt.executeUpdate();
		} catch (SQLException sqle) {
			throw new DAOException("doUpdate_2_DAODescrizioneComponente" + sqle);
		} finally {
			dataSource.chiudi(stmt);
		}
	}
	
	/**
	 * Cancella una descrizione di una componente dalla base di dati
	 * @param descrizioneComponente la descrizione della componente
	 * @throws DAOException
	 */
	public void doDelete(DescrizioneComponenteID descrizioneComponenteID) throws DAOException {
		Connection connessione = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			connessione = dataSource.getConnessione();
			doDelete(descrizioneComponenteID,connessione);
		} catch (SQLException sqle) {
			throw new DAOException("doDelete_1_DAODescrizioneComponente" + sqle);
		} finally {
			dataSource.chiudi(connessione);
		}
	}

	/**
	 * Cancella una descrizione di una componente dalla base di dati
	 * @param descrizioneComponente la descrizione della componente
	 * @param connessione la connessione alla base di dati
	 * @throws DAOException
	 */
	public void doDelete(DescrizioneComponenteID descrizioneComponenteID, Connection connessione) throws DAOException {
		PreparedStatement stmt = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			String query = "delete from DESCRIZIONECOMPONENTE where codice = ?";
			stmt = connessione.prepareStatement(query);
			stmt.setInt(1,descrizioneComponenteID.getCodiceDescrizioneComponente());
			stmt.executeUpdate();
		} catch (SQLException sqle) {
			throw new DAOException("doDelete_2_DAODescrizioneComponente" + sqle);
		} finally {
			dataSource.chiudi(stmt);
		}
		
	}
}
