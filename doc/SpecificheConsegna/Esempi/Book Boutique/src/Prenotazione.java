import java.util.Observable;
import java.util.Observer;

import org.hibernate.Session;


public class Prenotazione implements Observer {
	private int id;
	private Cliente cliente;
	private int clienteId;
	private Libro libro;
	private int libroId;
	private int quantita;
	private int copieConsegnate;
	private boolean ordinato;
	
	
	/*
	 *********************
	 * COSTRUTTORE
	 *********************
	 */	
	
	public Prenotazione(){
		
	}
	
	public Prenotazione(Cliente cliente, Libro libro,int quantita)	{
		this.cliente = cliente;
		this.libro = libro;
		this.quantita = quantita;
		this.copieConsegnate = 0;
		this.ordinato = false;
		
		this.libro.addObserver(this);
	}
		
	
	/*
	 *********************
	 * VARI METODI
	 *********************
	 */	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	public int getCopieConsegnate() {
		return copieConsegnate;
	}


	public void setCopieConsegnate(int copieConsegnate) {
		this.copieConsegnate = copieConsegnate;
	}
	
	public int getClienteId() {
		return clienteId;
	}

	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}

	public int getLibroId() {
		return libroId;
	}

	public void setLibroId(int libroId) {
		this.libroId = libroId;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	
	public int getQuantita()	{
		return quantita;
	}

	public Libro getLibro()	{
		return libro;
	}
	
	public void setLibro(Libro libro)	{
		this.libro = libro;
	}

	public void setCliente(Cliente cliente)	{
		this.cliente = cliente;
	}
	
	public Cliente getCliente()	{
		return cliente;
	}
	
	public Editore getEditoreLibro() {
		return libro.getEditore();
	}


	public String getTitoloLibro() {
		return libro.getTitolo();
	}
	
	public boolean getOrdinato(){
		return ordinato;
	}
	
	public void setOrdinato(boolean stato){
		this.ordinato = stato;
	}

	@Override
	public void update(Observable libro1, Object arg1) {
		
		int copieAssegnate = ((Libro) libro1).assegna(quantita - copieConsegnate);
		
		System.out.println("Sono arrivati dei libri");
		
		if(copieAssegnate > 0)
		{
			String testo = "";
			
			if(copieAssegnate == (quantita - copieConsegnate)){
				testo = "Gentile " + this.getCliente().getNome() + " " + this.getCliente().getCognome() + "," + "\n\n Tutte le copie di " + libro.getTitolo()+ " sono disponibili in negozio. \n\nBook Boutique";
				
				// prenotazione evasa del tutto
				// non resto piu in ascolto su subject
				this.libro.deleteObserver(this);
				System.out.println("Tutti i libri sono stati assegnati");
				copieConsegnate = quantita;
				
				
			} else {
				testo = "Gentile " + this.getCliente().getNome() + " " + this.getCliente().getCognome() + "," + "\n\n Sono disponibili " +String.valueOf(copieAssegnate)+" su " +String.valueOf(quantita - copieConsegnate)+"  copie di " + libro.getTitolo()+ " sono disponibili in negozio. \n\nBook Boutique";
				
				// prenotazione evasa solo in parte
				System.out.println("Alcuni libri sono stati assegnati");
				copieConsegnate =+ copieAssegnate;
				
				
			}
			System.out.println("Libro assegnato");
			Email.invia(this.getCliente().getEmail(),testo);
		} else {
			System.out.println("Libro non assegnato");
		}
	}
	
	public int nomeMoltoBello(Libro libro) {

		int copieAssegnate = ((Libro) libro).assegna(quantita - copieConsegnate);
		
		System.out.println("Sono arrivati dei libri");
		
		if(copieAssegnate > 0)
		{
			String testo = "";
			
			if(copieAssegnate == (quantita - copieConsegnate)){
				testo = "Gentile " + this.getCliente().getNome() + " " + this.getCliente().getCognome() + "," + "\n\n Tutte le copie di " + libro.getTitolo()+ " sono disponibili in negozio. \n\nBook Boutique";
				
				// prenotazione evasa del tutto
				// non resto piu in ascolto su subject
				System.out.println("Tutti i libri sono stati assegnati");
				copieConsegnate = quantita;
				
			} else {
				testo = "Gentile " + this.getCliente().getNome() + " " + this.getCliente().getCognome() + "," + "\n\n Sono disponibili " +String.valueOf(copieAssegnate)+" su " +String.valueOf(quantita - copieConsegnate)+"  copie di " + libro.getTitolo()+ " sono disponibili in negozio. \n\nBook Boutique";
				
				// prenotazione evasa solo in parte
				System.out.println("Alcuni libri sono stati assegnati");
				copieConsegnate =+ copieAssegnate;
				
				
			}
			System.out.println("Libro assegnato");
			Email.invia(this.getCliente().getEmail(),testo);
		} else {
			System.out.println("Libro non assegnato");
		}
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		((Prenotazione) session.load(Prenotazione.class, this.getId())).setCopieConsegnate(copieConsegnate);
		session.getTransaction().commit();
		session.close();
		
		return copieConsegnate;
	}
}
