package it.aps.cdr.persistenza.dao;

import it.aps.cdr.persistenza.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Generatore di codici univoci in tutta la base di dati che 
 * utilizza la tecnica "High-Low" (Scott Ambler, 1998) 
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri
 * @author Gabriele Rendina
 * 
 * @version 0.3
 *
 */
public class GeneratoreID {

	/** l'istanza singleton di GeneratoreID */
	private static GeneratoreID singleton;
	/** valore unico per la parte alta, acquisito dalla base di dati */
	private int high;
	/** numero di inserimenti del client nel blocco per la transazione */
	private int low;
	/** numero di inserimenti nella base di dati prima di una nuova transazione, 
	 *  ovvero la dimensione del blocco */
	private int maxlow;

	/**
	 * Restituisce il riferimento all'istanza singleton di GeneratoreID
	 * @return il riferimento all'istanza singleton di GeneratoreID
	 */
	public static GeneratoreID getInstance() {
		if (singleton == null)
			try {
				singleton = new GeneratoreID();
			} catch (SQLException sqle) {
				singleton = null;
				System.err.println(sqle);
			}
		return singleton;
	}
	
	/** Crea un GeneratoreID */
	private GeneratoreID() throws SQLException {
		init();
	}
	
	/** inizializza l'istanza di GeneratoreID */
	private void init() throws SQLException {
		DataSource dataSource = DataSource.getInstance();
		Connection connessione = null;
		Statement stmt = null;
		ResultSet resultSet = null;
		try {
			connessione = dataSource.getConnessione();
			stmt = connessione.createStatement();
			String query = "select maxlow from TABELLAID";
			resultSet = stmt.executeQuery(query);
			resultSet.next();
			this.maxlow = resultSet.getInt("maxlow");
			this.low = this.maxlow;
		} catch (SQLException sqle) {
			throw sqle;
		} finally {
			dataSource.chiudi(resultSet);
			dataSource.chiudi(stmt);
			dataSource.chiudi(connessione);
		}
	}
	
	/**
	 * Restituisce il prossimo codice valido per la base di dati
	 * @return il prossimo codice valido per la base di dati
	 * @throws SQLException
	 */
	public int getNuovoCodice() throws SQLException {
		int nuovoCodice;
		if (this.low == this.maxlow) {
			DataSource dataSource = DataSource.getInstance();
			Connection connessione = null;
			Statement stmt = null;
			ResultSet resultSet = null;
			try {
				connessione = dataSource.getConnessione();
				connessione.setAutoCommit(false);
				connessione.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
				stmt = connessione.createStatement();
				String queryUpdate = "update TABELLAID set high = high + " + this.maxlow;
				stmt.executeUpdate(queryUpdate);
				String querySelect = "select high from TABELLAID";
				resultSet = stmt.executeQuery(querySelect);
				resultSet.next();
				this.high = resultSet.getInt("high");
				this.low = 0;
				connessione.commit();
				connessione.setAutoCommit(true);
			} catch (SQLException sqle1) {
				try {
					connessione.rollback();
				} catch (SQLException sqle2) {
					throw new SQLException("getNuovoCodice : " + sqle2);
				}
			} finally {
				dataSource.chiudi(resultSet);
				dataSource.chiudi(stmt);
				dataSource.chiudi(connessione);
			}
		}
		nuovoCodice = this.high + this.low;
		this.low = this.low + 1;
		return nuovoCodice;
	}
}
