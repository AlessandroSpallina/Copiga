package it.aps.cdr.persistenza;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Oggetto per l'accesso ai dati, acceduto come singleton
 *
 * @author Fabrizio Martorelli
 * @author Andrea Petreri
 * @author Gabriele Rendina
 *
 * @version 0.3
 *
 */
public class DataSource {

	/** file di configurazione */
	private final static String CONFIG_FILE = 
		System.getProperty("user.dir") + 
		System.getProperty("file.separator") + 
		"datasource.cfg";
	
	/** l'istanza singleton del data source */
	private static DataSource singleton;
	/** URL della base di dati */
	private String databaseURL;
	/** username */
	private String username;
	/** password */
	private String password;

	/**
	 * Restituisce l'istanza singleton di DataSource
	 * @return l'istanza singleton di DataSource
	 */
	public static DataSource getInstance(){
		if (singleton == null)
			singleton = new DataSource();
		return singleton;
	}

	/** crea un nuovo DataSource */
	private DataSource(){
		this.init();
	}
	
	private void init() {
		try {
			FileInputStream file = new FileInputStream(CONFIG_FILE);
			Properties proprieta = new Properties();
			proprieta.load(file);
			this.databaseURL = proprieta.getProperty("database.uri");
			this.username = proprieta.getProperty("database.username");
			this.password = proprieta.getProperty("database.password");
		} catch (IOException ioe) {
			System.err.println("Errore nel caricamento del file di configurazione");
		}
	}
	
	/**
	 * Restituisce la connessione alla base di dati
	 * @return la connessione alla base di dati
	 * @throws SQLException
	 */
	public Connection getConnessione() throws SQLException {
		Connection connessione = null;
		try {
			Class.forName("org.postgresql.Driver");
			connessione = DriverManager.getConnection(this.databaseURL,this.username,this.password);
		} catch (SQLException sqle) {
			this.chiudi(connessione);
			throw new SQLException("getConnessione : " + sqle);
		} catch (ClassNotFoundException cnfe) {
			System.err.println("ClassNotFoundException");
		}
		return connessione;
	}

	/**
	 * Chiude la connessione con la base di dati
	 * @param connessione la connessione con la base di dati
	 * @throws SQLException
	 */
	public void chiudi(Connection connessione) {
		try {
			if (connessione != null)
				connessione.close();
		} catch (SQLException sqle){
			System.err.println(sqle);
		}
	}

	/**
	 * Chiude lo statement della connessione
	 * @param statement lo statement della connessione
	 * @throws SQLException
	 */
	public void chiudi(Statement statement) {
		try {
			if (statement != null)
				statement.close();
		} catch (SQLException sqle){
			System.err.println(sqle);
		}
	}

	/**
	 * Chiude il risultato di una interrogazione alla base di dati
	 * @param resultSet il risultato di una interrogazione
	 * @throws SQLException
	 */
	public void chiudi(ResultSet resultSet) {
		try {
		if (resultSet != null)
			resultSet.close();
		} catch (SQLException sqle){
			System.err.println(sqle);
		}
	}

}
