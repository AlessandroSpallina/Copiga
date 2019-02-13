package it.aps.cdr.persistenza.dao;

/**
 * Eccezione sollevata dalle classi DAO
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri
 * @author Gabriele Rendina
 * 
 * @version 0.3
 *
 */
public class DAOException extends Exception {

	/** ID autogenerato */
	private static final long serialVersionUID = 3691035474252739888L;

	/** Crea una nuova DAOException */
	public DAOException(){
		super();
	}
	
	/**
	 * Crea un nuovo oggetto di tipo DAOException 
	 * @param eccezione
	 */
	public DAOException(Exception eccezione){
		super(eccezione);
	}
	
	/**
	 * Crea un nuovo oggetto di tipo DAOException 
	 * @param eccezione
	 */
	public DAOException(String eccezione){
		super(eccezione);
	}
}
