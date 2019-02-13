import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Catalogo {
	private LinkedList<Editore> editori;
	
	/*
	 *********************
	 * COSTRUTTORE
	 *********************
	 */	
	
	public Catalogo() {
		editori = new LinkedList<Editore>();
	}
	
	
	/*
	 *********************
	 * METODI VARI
	 *********************
	 */
	
	/**
	 * restituisce un libro presente nel catalogo
	 */ 
	public Libro getLibro(String ISBN) {
		Libro risultato = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		List result = session.createQuery( "from Libro" ).list();

		for ( Libro libro : (List<Libro>) result ) {
			
			if(libro.getISBN().equalsIgnoreCase(ISBN)){
				
				risultato = libro;
			}
		}
		session.getTransaction().commit();
		session.close();
		
		return risultato;
	}

	/**
	 * restituisce la lista dei libri
	 */ 
	public HashMap<String,Libro> getLibri() {
		HashMap<String,Libro> elencoLibri = new HashMap<String,Libro>();
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		List result = session.createQuery( "from Libro" ).list();

		for ( Libro libro : (List<Libro>) result ) {
			elencoLibri.put(libro.getISBN(), libro);
		}
		session.getTransaction().commit();
		session.close();
		
		return elencoLibri;
	}
	
	/**
	 * inserisce un libro nel catalogo
	 */ 
	public void addLibro(Libro libro) {
		
		if((libro.getISBN().length() > 0) && (this.getLibro(libro.getISBN()) == null)){
			Session session = HibernateUtil.getSessionFactory().openSession();
			
			session.beginTransaction();
			
			session.save(libro);
			
			session.getTransaction().commit();
			
			session.close(); 
		}
	}

	/**
	 * ricerca un editore nella lista degli editori
	 */ 
	public Editore cercaEditore(String nomeEditore) {
		Editore risultato = null;
		int trovati = 0;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		List result = session.createQuery( "from Editore" ).list();

		for ( Editore editore : (List<Editore>) result ) {
			
			if(editore.getNome().toLowerCase().contains(nomeEditore.toLowerCase())){
				
				risultato = editore;
				trovati++;
			}
		}
		session.getTransaction().commit();
		session.close();
		
		if(trovati == 1){
			return risultato;
		} else {
			return null;
		}
	}

	/**
	 * inserisce un editore nella lista degli editori
	 */ 
	public void aggiungiEditore(String string) {
		if((string.length() > 0) && (this.cercaEditore(string) == null)){
			Session session = HibernateUtil.getSessionFactory().openSession();
			
			session.beginTransaction();
			
			Editore editore = new Editore(string);
			session.save(editore);
			
			session.getTransaction().commit();
			
			session.close(); 
		}
	}
	
	/**
	 * restituisce la lista degli editori
	 */ 
	public LinkedList<Editore> getEditori() {
		LinkedList<Editore> editori = new LinkedList<Editore>();
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		List result = session.createQuery( "from Editore" ).list();

		for ( Editore editore : (List<Editore>) result ) {
			editori.add(editore);
		}
		session.getTransaction().commit();
		session.close();
		
		return editori;
	}
	
	/**
	 * aggiorna le copie di un libro residue in magazzino
	 */
	public Libro aggiornaCopieLibro(Libro libro, int quantita){
		LinkedList<Prenotazione> prenotazioni;
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Libro l = (Libro) session.load(Libro.class, libro.getId());
		l.incrementaCopieResidue(quantita);
		
		prenotazioni = BookBoutique.getIstanza().getPrenotazioniLibro(l);
		
		for(Prenotazione p: prenotazioni){
			p.nomeMoltoBello(l);
		}
		
		// scorriamo la lista delle prenotazioni e aggiorniamole
		
		session.getTransaction().commit();
		session.close();
		
		return l;
	}
}
