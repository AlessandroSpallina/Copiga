import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;

public class Acquisto implements Cloneable {

	public static int currentID = 0;	
	private int id;
	private long data;	
	private Cliente cliente;
	private int clienteId;
	private LinkedList<RigaDiAcquisto> righeDiAcquisto;
	private HashMap<Libro,Integer> libriDaPrenotare;
	private Promozione promozione;
	private int promozioneId;
	private double totale;


	
	/*
	 *********************
	 * COSTRUTTORE
	 *********************
	 */	
	
	public Acquisto() {
		righeDiAcquisto = new LinkedList<RigaDiAcquisto>();
		libriDaPrenotare = new HashMap<Libro,Integer>();
		data = ((Date) new Date()).getTime();
		
		//id = currentID;
		//currentID++;
	}
	
	
	/*
	 *********************
	 * METODI VARI
	 *********************
	 */
	
	public int getPromozioneId() {
		return promozioneId;
	}


	public void setPromozioneId(int promozioneId) {
		this.promozioneId = promozioneId;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public double getTotale() {
		return totale;
	}

	public void setTotale(double totale) {
		this.totale = totale;
	}
	
	public long getData() {
		return data;
	}


	public void setData(long data) {
		this.data = data;
	}


	public int getClienteId() {
		return clienteId;
	}


	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}


	/**
	 * imposta il cliente a cui è relativo l'acquisto
	 */
	public void setCliente(Cliente c){
		this.cliente = c;
	}

	/**
	 * restituisce il cliente dell'acquisto
	 */
	public Cliente getCliente(){
		return cliente;
	}
	
	/**
	 * Restituisce le righe di acquisto
	 */
	public LinkedList<RigaDiAcquisto> getRigheDiAcquisto(){
		return righeDiAcquisto;
	}
	
	public void setRigheDiAcquisto(LinkedList<RigaDiAcquisto> righeDiAcquisto){
		this.righeDiAcquisto = righeDiAcquisto;
	}
	
	/**
	 * inserimento di una nuova riga di acquisto
	 */
	public void addRigaAcquisto(Libro libro, int quantita) {
		if(quantita > 0){
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Libro l = (Libro) session.load(Libro.class, libro.getId());
			session.getTransaction().commit();
			session.close();
			
			RigaDiAcquisto rigaDiAcquisto = new RigaDiAcquisto(l,quantita);
			
			righeDiAcquisto.add(rigaDiAcquisto);
		}
	}

	
	/*
	 *********************
	 * GESTIONE ACQUISTI E PRENOTAZIONI
	 *********************
	 */
	
	/**
	 * calcola il totale di un acquisto
	 */ 
	public double calcolaTotale() {

		double totale=0;
		for(RigaDiAcquisto rda: righeDiAcquisto){
			totale = totale + rda.calcolaSubtotale();
		}
		
		this.totale = (Math.round(totale*100D))/100D;
		return this.totale;
	}

	
	/**
	 * restituisce una stringa di riepilogo dell'acquisto
	 */ 
	public String stampaRiepilogo() {
		
		String riepilogo = "";
		
		if(this.cliente != null)	{
				riepilogo = riepilogo + this.cliente.getNome() + " " + this.cliente.getCognome() + " - ";
		}
		
		for(RigaDiAcquisto rda: righeDiAcquisto)
		{
			riepilogo = riepilogo + rda.getDescrizione() +  "\r";
		}
		return riepilogo;
	}
	
	
	/**
	 * restituisce la lista dei libri da prenotare
	 */
	public HashMap<Libro,Integer> verificaDisponibilità()	{
		
		for(RigaDiAcquisto rda: righeDiAcquisto)	{
			int copieDaPrenotare = rda.aggiornaCopieResidue();
			if(copieDaPrenotare != 0)	{
				libriDaPrenotare.put(rda.getLibro(), copieDaPrenotare);
			}
		}
		return libriDaPrenotare;
	}
	
	
	/*
	 *********************
	 * GESTIONE PROMOZIONI
	 *********************
	 */
	
	/**
	 * imposta una promozione
	 */ 
	public void setPromozione(Promozione p) {
		this.promozione = p;
	}
	
	/**
	 * associa una promozione all'acquisto e calcola il totale scontato
	 */ 
	public double associaPromozione(Promozione p) {
		double totaleScontato = 0;
		
		setPromozione(p);
		
		totaleScontato = p.calcolaTotaleScontato(this,0);
		
		return totaleScontato;
	}
	
	
	/**
	 * override del metodo clone
	 */
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * override del metodo toString
	 */
	public String toString(){
		SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
		return id + " - " + cliente.getNome() + " - " + cliente.getCognome() + " - " + ft.format(data) + " - " + this.getTotale();
	}
}
