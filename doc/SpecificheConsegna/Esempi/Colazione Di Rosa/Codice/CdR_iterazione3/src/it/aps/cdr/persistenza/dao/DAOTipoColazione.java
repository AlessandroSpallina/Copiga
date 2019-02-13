package it.aps.cdr.persistenza.dao;

import it.aps.cdr.dominio.ComponenteColazione;
import it.aps.cdr.dominio.DescrizioneComponente;
import it.aps.cdr.dominio.DescrizioneComponenteID;
import it.aps.cdr.dominio.Prezzo;
import it.aps.cdr.dominio.TipoColazione;
import it.aps.cdr.dominio.TipoColazioneID;
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
 * Oggetto DAO per gli oggetti di tipo TipoColazione, acceduto come
 * singleton.
 * 
 * @see TipoColazione
 * 
 * @author Fabrizio Martorelli 
 * @author Andrea Petreri 
 * @author Gabriele Rendina 
 * 
 * @version 0.3
 *
 */
public class DAOTipoColazione {
	
	/** l'istanza singleton di DAOTipoColazione */
	private static DAOTipoColazione singleton;	
	
	/** 
	 * Restituisce il riferimento all'istanza singleton di DAOTipoColazione
	 * @return il riferimento all'istanza singleton di DAOTipoColazione 
	 */
	public static synchronized DAOTipoColazione getInstance(){
		if (singleton == null)
			singleton = new DAOTipoColazione();
		return singleton;
	}
	
	/**
	 * Registra un tipo di colazione nella base di dati
	 * @param tipoColazione il tipo di colazione
	 * @param connessione la connessione alla base di dati
	 * @throws DAOException
	 */
	public void doInsert(TipoColazione tipoColazione) throws DAOException{
		Connection connessione = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			connessione = dataSource.getConnessione();
			doInsert(tipoColazione,connessione);
		} catch (SQLException sqle) {
			throw new DAOException("doInsert_1_DAOTipoColazione : " + sqle);
		} finally {
			dataSource.chiudi(connessione);
		}
	}

	/**
	 * Registra un tipo di colazione nella base di dati. La chiave viene
	 * generata dal DBMS.
	 * Attenzione : da usare con MySQL e DB2  
	 * @param tipoColazione il tipo di colazione
	 * @throws DAOException
	 */
	public void doInsertAGK(TipoColazione tipoColazione) throws DAOException{
		Connection connessione = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			connessione = dataSource.getConnessione();
			doInsertAGK(tipoColazione,connessione);
		} catch (SQLException sqle) {
			throw new DAOException("doInsertAGK_1_DAOTipoColazione : " + sqle);
		} finally {
			dataSource.chiudi(connessione);
		}
	}
	
	/**
	 * Registra un tipo di colazione nella base di dati. La chiave viene
	 * generata dal GeneratoreID 
	 * @param tipoColazione il tipo di colazione
	 * @throws DAOException
	 */
	public void doInsertGID(TipoColazione tipoColazione) throws DAOException{
		Connection connessione = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			connessione = dataSource.getConnessione();
			TipoColazioneID tcID = new TipoColazioneID(GeneratoreID.getInstance().getNuovoCodice());
			tipoColazione.setCodice(tcID);
			doInsert(tipoColazione,connessione);
		} catch (SQLException sqle) {
			throw new DAOException("doInsertGID_1_DAOTipoColazione : " + sqle);
		} finally {
			dataSource.chiudi(connessione);
		}
	}
	
	/**
	 * Registra un tipo di colazione nella base di dati
	 * @param tipoColazione il tipo di colazione
	 * @param connessione la connessione alla base di dati
	 * @throws DAOException
	 */
	public void doInsert(TipoColazione tipoColazione, Connection connessione) throws DAOException {
		PreparedStatement stmt = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			connessione.setAutoCommit(false);
			/* inserisce il tipo di colazione */
			String query1 = "insert into TIPOCOLAZIONE values (?,?,?)";
			stmt = connessione.prepareStatement(query1); 
			stmt.setInt(1,tipoColazione.getCodice().getCodiceTipoColazione());
			stmt.setString(2,tipoColazione.getNome());
			stmt.setBigDecimal(3,tipoColazione.getPrezzo().getImporto());
			stmt.executeUpdate();
			/* associa le descrizioni delle componenti al tipo di colazione */
			String query2 = "insert into COMPOSIZIONE values (?,?,?)";
			stmt = connessione.prepareStatement(query2);
			stmt.setInt(1,tipoColazione.getCodice().getCodiceTipoColazione());
			for (ComponenteColazione cc : tipoColazione.getComponenti()) {
				stmt.setInt(2,cc.getDescrizioneComponente().getCodice().getCodiceDescrizioneComponente());
				stmt.setInt(3,cc.getQuantita());
				stmt.executeUpdate();
			}
			connessione.commit();
			connessione.setAutoCommit(true);
		} catch (SQLException sqle) {
			try {
				connessione.rollback();
			} catch (SQLException sqle2) {
				throw new DAOException("doInsert_2_DAOTipoColazione : " + sqle2);
			}
		} finally {
			dataSource.chiudi(stmt);
		}
		
	}
	
	/**
	 * Registra un tipo di colazione nella base di dati. La chiave
	 * viene generata automaticamente dal DBMS
	 * @param tipoColazione il tipo di colazione
	 * @param connessione la connessione alla base di dati 
	 * @throws DAOException
	 */
	public void doInsertAGK(TipoColazione tipoColazione, Connection connessione) throws DAOException {
		DataSource dataSource = DataSource.getInstance();
		PreparedStatement stmt = null;
		ResultSet resultSet = null;		
		try {
			connessione.setAutoCommit(false);
			/* inserisce il tipo di colazione */
			String query1 = "insert into TIPOCOLAZIONE (nome, prezzo) values (?,?)";
			stmt = connessione.prepareStatement(query1,Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1,tipoColazione.getNome());
			stmt.setBigDecimal(2,tipoColazione.getPrezzo().getImporto());
			stmt.executeUpdate();
			resultSet = stmt.getGeneratedKeys();
			resultSet.next();
			TipoColazioneID tcID = new TipoColazioneID(resultSet.getBigDecimal(1).intValue());
			tipoColazione.setCodice(tcID);
			/* associa le descrizioni delle componenti al tipo di colazione */
			String query2 = "insert into COMPOSIZIONE values (?,?,?)";
			stmt = connessione.prepareStatement(query2);
			stmt.setInt(1,tipoColazione.getCodice().getCodiceTipoColazione());
			for (ComponenteColazione cc : tipoColazione.getComponenti()) {
				stmt.setInt(2,cc.getDescrizioneComponente().getCodice().getCodiceDescrizioneComponente());
				stmt.setInt(3,cc.getQuantita());
				stmt.executeUpdate();
			}
			connessione.commit();
			connessione.setAutoCommit(true);
		} catch (SQLException sqle) {
			throw new DAOException("doInsertAGK_2_DAOTipoColazione : " + sqle);
		} finally {
			dataSource.chiudi(resultSet);
			dataSource.chiudi(stmt);
		}				
	}
	
	/**
	 * Restituisce un tipo di colazione registrato nella base di dati
	 * @param tipoColazioneID il codice del tipo di colazione
	 * @param connessione la connessione alla base di dati
	 * @return il tipo di colazione
	 * @throws DAOException
	 */
	public TipoColazione doSelect(TipoColazioneID tipoColazioneID) throws DAOException{
		Connection connessione = null;
		TipoColazione tipoColazione = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			connessione = dataSource.getConnessione();
			tipoColazione = doSelect(tipoColazioneID,connessione);
		} catch (SQLException sqle) {
			throw new DAOException("doSelect_1_DAOTipoColazione : " + sqle);
		} finally {
			dataSource.chiudi(connessione);
		}
		return tipoColazione;
	}
	
	/**
	 * Restituisce un tipo di colazione registrato nella base di dati
	 * @param tipoColazioneID il codice del tipo di colazione
	 * @param connessione la connessione alla base di dati
	 * @return il tipo di colazione
	 * @throws DAOException
	 */
	public TipoColazione doSelect(TipoColazioneID tipoColazioneID, Connection connessione) throws DAOException {
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		TipoColazione tipoColazione = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			String query = 
			"select TIPOCOLAZIONE.nome, prezzo, componente, DESCRIZIONECOMPONENTE.nome AS nomecomponente, prezzoaggiuntivo, " +
			"prezzoriduttivo, quantita " +
			"from (TIPOCOLAZIONE join COMPOSIZIONE on TIPOCOLAZIONE.codice = tipocolazione) join DESCRIZIONECOMPONENTE on DESCRIZIONECOMPONENTE.codice = componente " +
			"where TIPOCOLAZIONE.codice = ?";
			stmt = connessione.prepareStatement(query);
			stmt.setInt(1,tipoColazioneID.getCodiceTipoColazione());
			resultSet = stmt.executeQuery();
			while (resultSet.next()){
				/* alla prima ennupla inizializzo l'oggetto tipoColazione */
				if (tipoColazione == null) {
					String nomeTipoColazione = resultSet.getString("nome");
					tipoColazione = new TipoColazione(tipoColazioneID,nomeTipoColazione);
					BigDecimal importo = new BigDecimal(resultSet.getDouble("prezzo"));
					Prezzo prezzoTipoColazione = new Prezzo(importo);
					tipoColazione.setPrezzo(prezzoTipoColazione);
				}
				/* aggiungo le componenti al tipo di colazione */
				int codiceComponente = resultSet.getInt("componente");
				DescrizioneComponenteID  descrizioneComponenteID = new DescrizioneComponenteID(codiceComponente);
				String nomeComponente = resultSet.getString("nomecomponente");
				BigDecimal importoAggiuntivoComponente = new BigDecimal(resultSet.getDouble("prezzoaggiuntivo"));
				Prezzo prezzoAggiuntivo = new Prezzo(importoAggiuntivoComponente);
				BigDecimal importoRiduttivoComponente = new BigDecimal(resultSet.getDouble("prezzoriduttivo"));
				Prezzo prezzoRiduttivo = new Prezzo(importoRiduttivoComponente);
				DescrizioneComponente dc = new DescrizioneComponente(descrizioneComponenteID,nomeComponente,prezzoAggiuntivo,prezzoRiduttivo);
				int quantita = resultSet.getInt("quantita");
				/* aggiungo la componente */
				tipoColazione.aggiungiComponenteColazione(dc,quantita);
			}
			
		} catch (SQLException sqle) {
			throw new DAOException("doSelect_2_DAOTipoColazione : " +sqle);
		} finally {
			dataSource.chiudi(resultSet);
			dataSource.chiudi(stmt);
		}
		return tipoColazione;
	}

	/**
	 * Restituisce tutti i tipi di colazione registrati nella base di dati
	 * @return i tipi di colazione regisrtati
	 * @throws DAOException
	 */
	public Map<TipoColazioneID,TipoColazione> doSelect() throws DAOException {
		Connection connessione = null;
		Map<TipoColazioneID,TipoColazione> tipiColazione = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			connessione = dataSource.getConnessione();
			tipiColazione = doSelect(connessione);
		} catch (SQLException sqle) {
			throw new DAOException("doSelectAll_1_DAOTipoColazione : " + sqle);
		} finally {
			dataSource.chiudi(connessione);
		}
		return tipiColazione;
	}
	
	/**
	 * Restituisce tutti i tipi di colazione registrati nella base di dati
	 * @param connessione la connessione alla base di dati
	 * @return i tipi di colazione regisrtati
	 * @throws DAOException
	 */
	public Map<TipoColazioneID,TipoColazione> doSelect(Connection connessione) throws DAOException {
		Statement stmt = null;
		ResultSet resultSet = null;
		TipoColazione tipoColazione = null; // tipo corrente
		DataSource dataSource = DataSource.getInstance();
		Map<TipoColazioneID,TipoColazione> tipiColazione = new HashMap<TipoColazioneID,TipoColazione>();
		try {
			stmt = connessione.createStatement();
			String query = 
			"select TIPOCOLAZIONE.codice, TIPOCOLAZIONE.nome, prezzo, componente, DESCRIZIONECOMPONENTE.nome AS nomecomponente, prezzoaggiuntivo, " +
			"prezzoriduttivo, quantita " +
			"from DESCRIZIONECOMPONENTE join (TIPOCOLAZIONE join COMPOSIZIONE on TIPOCOLAZIONE.codice = COMPOSIZIONE.tipocolazione) on DESCRIZIONECOMPONENTE.codice = COMPOSIZIONE.componente";
			resultSet = stmt.executeQuery(query);
			while (resultSet.next()) {
				int codiceTC = resultSet.getInt("codice");
				TipoColazioneID tcID = new TipoColazioneID(codiceTC);
				if (tipoColazione == null || !tipoColazione.getCodice().equals(tcID)) {
					/* altrimenti crea il tipo di colazione */
					String nomeTC = resultSet.getString("nome");
					tipoColazione = new TipoColazione(tcID,nomeTC);
					BigDecimal importoTC = new BigDecimal(resultSet.getDouble("prezzo"));
					Prezzo prezzoTipoColazione = new Prezzo(importoTC);
					tipoColazione.setPrezzo(prezzoTipoColazione);
					tipiColazione.put(tipoColazione.getCodice(),tipoColazione);
				}
				/* crea ed aggiungi le componenti al tipo di colazione */
				int codiceComponente = resultSet.getInt("componente");
				DescrizioneComponenteID  descrizioneComponenteID = new DescrizioneComponenteID(codiceComponente);
				String nomeComponente = resultSet.getString("nomecomponente");
				BigDecimal importoAggiuntivoComponente = new BigDecimal(resultSet.getDouble("prezzoaggiuntivo"));
				Prezzo prezzoAggiuntivo = new Prezzo(importoAggiuntivoComponente);
				BigDecimal importoRiduttivoComponente = new BigDecimal(resultSet.getDouble("prezzoriduttivo"));
				Prezzo prezzoRiduttivo = new Prezzo(importoRiduttivoComponente);
				DescrizioneComponente dc = new DescrizioneComponente(descrizioneComponenteID,nomeComponente,prezzoAggiuntivo,prezzoRiduttivo);
				int quantita = resultSet.getInt("quantita");
				/* aggiungo la componente */
				tipoColazione.aggiungiComponenteColazione(dc,quantita);

			}
		} catch (SQLException sqle) {
			throw new DAOException("doSelectAll_2_DAOTipoColazione : " + sqle);
		} finally {
			dataSource.chiudi(resultSet);
			dataSource.chiudi(stmt);
		}
		return tipiColazione;
	}

	/**
	 * Aggiorna un tipo di colazione registrato nella base di dati 
	 * @param tipoColazione il tipo di colazione
	 * @throws DAOException
	 */
	public void doUpdate(TipoColazione tipoColazione) throws DAOException {
		Connection connessione = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			connessione = dataSource.getConnessione();
			doUpdate(tipoColazione,connessione);
		} catch (SQLException sqle) {
			throw new DAOException("doUpdate_1_DAOTipoColazione : " + sqle);
		} finally {
			dataSource.chiudi(connessione);
		}
	}
	
	/**
	 * Aggiorna un tipo di colazione registrato nella base di dati 
	 * @param tipoColazione il tipo di colazione
	 * @param connessione la connessione alla base di dati
	 * @throws DAOException
	 */
	public void doUpdate(TipoColazione tipoColazione, Connection connessione) throws DAOException{
		PreparedStatement stmt = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			String query = "update TIPOCOLAZIONE set nome = ?, prezzo = ? where codice = ?";
			stmt = connessione.prepareStatement(query);
			stmt.setString(1,tipoColazione.getNome());
			stmt.setBigDecimal(2,tipoColazione.getPrezzo().getImporto());
			stmt.setInt(3,tipoColazione.getCodice().getCodiceTipoColazione());
			stmt.executeUpdate();
		} catch (SQLException sqle) {
			throw new DAOException("doUpdate_2_DAOTipoColazione : " + sqle);
		} finally {
			dataSource.chiudi(stmt);
		}
	}
	
	/**
	 * Cancella un tipo di colazione registrato nella base di dati
	 * @param tipoColazione un tipo di colazione 
	 * @throws DAOException
	 */
	public void doDelete(TipoColazioneID tipoColazioneID) throws DAOException{
		Connection connessione = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			connessione = dataSource.getConnessione();
			doDelete(tipoColazioneID,connessione);
		} catch (SQLException sqle) {
			throw new DAOException("doDelete_1_DAOTipoColazione : " + sqle);
		} finally {
			dataSource.chiudi(connessione);
		}
	}

	/**
	 * Cancella un tipo di colazione registrato nella base di dati
	 * @param tipoColazione un tipo di colazione 
	 * @param connessione la connessione alla base di dati
	 * @throws DAOException
	 */
	public void doDelete(TipoColazioneID tipoColazioneID, Connection connessione) throws DAOException {
		PreparedStatement stmt = null;
		DataSource dataSource = DataSource.getInstance();
		try {
			String query = "delete from TIPOCOLAZIONE where codice = ?";
			stmt = connessione.prepareStatement(query);
			stmt.setInt(1,tipoColazioneID.getCodiceTipoColazione());
			stmt.executeUpdate();
		} catch (SQLException sqle) {
			throw new DAOException("doDelete_2_DAOTipoColazione : " + sqle);
		} finally {
			dataSource.chiudi(stmt);
		}
		
	}

}
