import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;

public class BookBoutique {

	/* istanza singleton di BookBoutique */ 
	private static BookBoutique singleton;
	
	private Catalogo catalogo;	
	private Acquisto acquistoInCorso;
	private Magazzino magazzino;
	private LinkedList<Promozione> promozioni;
	
	
	/*
	 *********************
	 * GET ISTANZA SINGLETON E COSTRUTTORE 
	 *********************
	 */	
	
	/**
	 * Restituisce il riferimento all'istanza singleton di BookBoutique
	 */
	public static BookBoutique getIstanza(){
		if (singleton == null)
			singleton = new BookBoutique();
		return singleton;
	}
	
	private BookBoutique()	{
		
		this.catalogo = new Catalogo();
		acquistoInCorso = new Acquisto();
		this.magazzino = new Magazzino();
		promozioni = new LinkedList<Promozione>();
	}
	
	
	/*
	 *********************
	 * METODI VARI
	 *********************
	 */
	
	/**
	 * inserimento di un cliente alla lista
	 */	
	public void addCliente(Cliente cliente)	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		
		session.save(cliente);
		
		session.getTransaction().commit();
		
		session.close(); 
	}
	
	
	/**
	 * ricerca di un cliente nella lista
	 */	
	public Cliente ricercaCliente(String nomeCl, String cognomeCl) {
		
		Cliente cliente = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		List result = session.createQuery( "from Cliente" ).list();

		for(Cliente c: (List<Cliente>) result) {
			if(nomeCl.equalsIgnoreCase(c.getNome()) && cognomeCl.equalsIgnoreCase(c.getCognome())) {
				cliente = c;
			}
		}
		
		session.getTransaction().commit();
		session.close();

		return cliente;
	}
	
	
	/**
	 * inserimento di un acquisto alla lista
	 */	
	private void addAcquisto(Acquisto acquisto)	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		
		session.save(acquisto);
		
		for(RigaDiAcquisto rda: acquisto.getRigheDiAcquisto()){
			
			rda.setAcquisto(acquisto);
			rda.setAcquistoId(acquisto.getId());
			System.out.println("addAcquisto rda: "+rda);
			session.save(rda);
		}
		
		session.getTransaction().commit();
		
		session.close(); 
	}
	
	
	/**
	 * ricerca un libro a partire dall'ISBN
	 */
	public Libro ricercaLibroISBN(String ISBN) {
		
		Libro libro = catalogo.getLibro(ISBN);
		return libro;
	}
	
	
	/**
	 * inserisci un libro nel catalogo
	 */
	public void inserisciLibro(Libro libro) {
		catalogo.addLibro(libro);
	}

	
	/**
	 * restituisce le informazioni su tutti gli acquisti in archivio
	 */
	public String mostraStoricoAcquisti(){

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		List result = session.createQuery( "from Acquisto" ).list();
		
		for(Acquisto acq: (List<Acquisto>) result){
			System.out.println("mostraStoricoAcquisti() "+acq.getRigheDiAcquisto().size());
		}

		session.getTransaction().commit();
		session.close();
		
		return result.toString();
	}
	
	
	/*
	 *********************
	 * GESTIONE ACQUISTI
	 *********************
	 */
	
	/**
	 * inserimento di un libro ad una vendita esistente
	 */
	public void aggiungiLibro(Libro libro, int quantita) {
		
		acquistoInCorso.addRigaAcquisto(libro, quantita);
	}
		
	
	/**
	 * settaggio degli attributi di acquistoInCorso mancanti 
	 */
	public double procediAcquisto(Cliente cliente) {
		
		// associa il cliente all'acquisto in corso
		this.acquistoInCorso.setCliente(cliente);
		
		// calcola il subtotale
		return acquistoInCorso.calcolaTotale();
	}

	
	/**
	 * terminazione dell'acquisto 
	 */
	public void terminaAcquisto() {
		
		Acquisto nuovoAcquisto = null;
		
		//aggiorna le copie residue e verifica se ci sono libri da prenotare
		aggiornaCopieEPrenotazioni();
		
		// salva il riepilogo dell'acquisto su un file di testo
		String riepilogo = acquistoInCorso.stampaRiepilogo();
		try	{
			PrintWriter fw = new PrintWriter(new File("riepilogo.txt"));
			fw.println(riepilogo);
			fw.close();
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
		
		// crea la nuova istanza di Acquisto e la aggiunge alla lista degli acquisti
		//nuovoAcquisto = (Acquisto) acquistoInCorso.clone();
		addAcquisto(acquistoInCorso);
		System.out.println("Acquisto completato: " + acquistoInCorso.toString() + "\n");
		
		acquistoInCorso = new Acquisto();
	}
	
	
	/**
	 * restituisce il riepilogo dell'acquisto in corso
	 */
	public String mostraSchedaAcquisto()
	{
		return acquistoInCorso.stampaRiepilogo();
	}
	
	
	/**
	 * restituisce il totale dell'acquisto in corso
	 */
	public String mostraTotale(){
		return String.format("%.2f", acquistoInCorso.calcolaTotale());
	}
	
	
	/**
	 * associa il cliente all'acquisto in corso
	 */
	public void setClienteAcquisto(Cliente cliente)	{
		acquistoInCorso.setCliente(cliente);
	}
	
	/**
	 * Restituisce l'acquisto in corso
	 */
	public Acquisto getAcquistoInCorso(){
		return acquistoInCorso;
	}
	
	/**
	 * restituisce istanza di magazzino
	 */
	public Magazzino getMagazzino(){
		return magazzino;
	}
	
	/**
	 * restituisce istanza di catalogo
	 */
	public Catalogo getCatalogo(){
		return catalogo;
	}
	
	/*
	 *********************
	 * GESTIONE PRENOTAZIONI
	 *********************
	 */
	
	/**
	 * aggiorna le copie residue dei libri venduti e crea l'eventuale prenotazione per i libri mancanti
	 */
	public void aggiornaCopieEPrenotazioni()	{
		HashMap<Libro,Integer> libriDaPrenotare = acquistoInCorso.verificaDisponibilità();
		
		if(!libriDaPrenotare.isEmpty())	{
			Prenotazione prenotazione = null;
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			
			session.beginTransaction();
			
			//scorrere la mappa e creare una prenotazione per ciascun libro con la relativa quantità
			for (HashMap.Entry<Libro, Integer> entry : libriDaPrenotare.entrySet()) {
				prenotazione = new Prenotazione(acquistoInCorso.getCliente(),entry.getKey(),entry.getValue());
				session.save(prenotazione);
				System.out.println("Prenotazione creata per il libro: " + prenotazione.getLibro().getTitolo() + " - quantità: " + prenotazione.getQuantita());
			}
			
			session.getTransaction().commit();
			
			session.close(); 
		}
	}

	/**
	 * ricerca le prenotazioni relative a un particolare Editore
	 */
	public LinkedList<Prenotazione> getPrenotazioniEditore(Editore editore) {
		LinkedList<Prenotazione> prenotazioniEditore = new LinkedList<Prenotazione>();
		
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		List result = session.createQuery( "from Prenotazione" ).list();
		
		session.getTransaction().commit();
		session.close();

		for ( Prenotazione p : (List<Prenotazione>) result ) {
			
			if((p.getEditoreLibro().getId() == editore.getId()) && (p.getOrdinato() == false)){
				prenotazioniEditore.add(p);
			}
		}
		
		return prenotazioniEditore;
	}

	public LinkedList<Prenotazione> getListaPrenotazioni() {
		LinkedList<Prenotazione> prenotazioni = new LinkedList<Prenotazione>();
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		List result = session.createQuery( "from Prenotazione" ).list();

		for ( Prenotazione p : (List<Prenotazione>) result ) {
			prenotazioni.add(p);
		}
		session.getTransaction().commit();
		session.close();
		
		return prenotazioni;
	}
	
	public void addPrenotazione(Prenotazione prenotazione){
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		System.out.println(prenotazione);
		System.out.println("pippo");
		
		session.beginTransaction();
		
		session.save(prenotazione);
		
		session.getTransaction().commit();
		
		session.close(); 
	}
	
	
	public void addPromozione(Promozione p){
		promozioni.add(p);
	}
	
	public LinkedList<Promozione> getPromozioni(){
		return promozioni;
	}
	
	public LinkedList<Prenotazione> getPrenotazioniLibro(Libro libro) {
		LinkedList<Prenotazione> prenotazioniLibro = new LinkedList<Prenotazione>();
		
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		List result = session.createQuery( "from Prenotazione" ).list();
		
		session.getTransaction().commit();
		session.close();

		for ( Prenotazione p : (List<Prenotazione>) result ) {
			
			if(p.getLibro().getId() == libro.getId()){
				prenotazioniLibro.add(p);
			}
		}
		
		return prenotazioniLibro;
	}

	public void annullaOrdine() {
		this.acquistoInCorso = new Acquisto();
	}
}
