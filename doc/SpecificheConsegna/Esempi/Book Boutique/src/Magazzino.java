import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import org.hibernate.Session;

public class Magazzino {
	private LinkedList<Ordine> ordini;
	private Ordine ordineInCorso;
	private LinkedList<Prenotazione> prenotazioniEditore;

	
	/*
	 *********************
	 * COSTRUTTORE
	 *********************
	 */	
	public Magazzino()	{
		ordini = new LinkedList<Ordine>();
		ordineInCorso = new Ordine();
	}
	
	
	/*
	 *********************
	 * GESTIONE ORDINI
	 *********************
	 */

	
	
	/**
	 * crea un ordineInCorso per l'editore specificato e inserisce eventuali prenotazioni presenti
	 */
	public Ordine creaOrdine(LinkedList<Prenotazione> prenotazioniEditore, Editore editore) {
		ordineInCorso = new Ordine(editore);
		this.prenotazioniEditore = prenotazioniEditore;
		
		System.out.println("creaOrdine() - prenotazioniEditore.size() " + prenotazioniEditore.size());
				
		for(Prenotazione p: prenotazioniEditore)	{
			System.out.println("creaOrdine() - p.getLibro.getEditore().getId() " + p.getLibro().getEditore().getId());
			ordineInCorso.aggiungiRigaOrdine(p.getLibro(),p.getQuantita());
		}
		
		System.out.println("creaOrdine() - Editore " + ordineInCorso.getEditore().getNome());
		System.out.println("creaOrdine() - ordineInCorso.righeOrdine.size() " + ordineInCorso.getRigheOrdine().size());
		return ordineInCorso;
	}

	/**
	 * aggiunge un libro all'ordine in corso
	 */
	public boolean aggiungiLibroAdOrdine(Libro libro, int quantita) {
		boolean stessoEditore = ordineInCorso.aggiungiRigaOrdine(libro,quantita);
		return stessoEditore;
	}

	/**
	 * restituisce l'ordine in corso
	 */
	public Ordine getOrdineInCorso() {
		return ordineInCorso;
	}

	/**
	 * conferma l'ordine in corso
	 */
	public void confermaOrdine(){
		
		// salva il riepilogo dell'acquisto su un file di testo
		String riepilogo = ordineInCorso.toString();
		try	{
			PrintWriter fw = new PrintWriter(new File("ordine.txt"));
			fw.println(riepilogo);
			fw.close();
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
				
		Ordine nuovoOrdine = (Ordine) ordineInCorso.clone();
		ordini.add(nuovoOrdine);
		System.out.println("ordine: "+ ordineInCorso.toString());
		ordineInCorso = new Ordine();
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		//((Prenotazione) session.load(Prenotazione.class, p.getId())).setOrdinato(true);
		for(Prenotazione p: prenotazioniEditore){
			//p.setOrdinato(true);
			((Prenotazione) session.load(Prenotazione.class, p.getId())).setOrdinato(true);
		}
		
		session.getTransaction().commit();
		session.close();
	}
	
	/**
	 * restituisce tutti gli ordini archiviati
	 */
	public String mostraStoricoOrdini()	{
		String storico="";
		
		for(Ordine o: ordini){
			storico = storico + o.toString() + "\n";
		}
		
		return storico;
	}
}
