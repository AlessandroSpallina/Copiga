public class RigaDiAcquisto {

	private int id;
	private int quantita;
	private double subtotale=0;
	private Libro libro;
	private int libroId;
	private Acquisto acquisto;
	private int acquistoId;

	
	/*
	 *********************
	 * COSTRUTTORE
	 *********************
	 */	
	public RigaDiAcquisto(Libro libro, int quantita) {
		setLibro(libro);
		setQuantita(quantita);
		calcolaSubtotale();
	}
	
	public RigaDiAcquisto(){
		
	}

	
	/*
	 *********************
	 * METODI VARI
	 *********************
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setAcquisto(Acquisto acquisto) {
		this.acquisto = acquisto;
	}
	
	public Acquisto getAcquisto() {
		return acquisto;
	}
	
	public void setAcquistoId(int acquistoId) {
		this.acquistoId = acquistoId;
	}
	
	public int getAcquistoId() {
		return acquistoId;
	}
	
	public void setLibroId(int id) {
		this.libroId = id;
	}
	
	public int getLibroId() {
		return libroId;
	}
	
	public void setLibro(Libro libro) {
		this.libro = libro;
	}
	
	public Libro getLibro() {
		return libro;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	
	/**
	 * restituisce la quantita' di libri da acquistare
	 */
	public int getQuantita(){
		return quantita;
	}

	
	public void setSubtotale(double subtotale) {
		this.subtotale = subtotale;
	}
	
	public double getSubtotale() {
		return (Math.round(this.subtotale*100D))/100D;
	}
	
	

	
	/**
	 * calcola il subtotale di una riga di acquisto
	 */
	public double calcolaSubtotale() {
		this.subtotale = this.libro.getPrezzo() * this.quantita;
		return (Math.round(this.subtotale*100D))/100D;
	}

	
	/**
	 * restituisce una stringa di descrizione della riga
	 */ 
	public String getDescrizione() {
		String descrizione = "Titolo: " + libro.getTitolo() + " Prezzo: " + libro.getPrezzo() + " N. copie: " + this.quantita + " Subtotale: " + String.format("%.2f", getSubtotale()) + " \n";
		return descrizione;
	}
	
	/**
	 * verifica la disponibilità e aggiorna le giacenze 
	 */ 
	public int aggiornaCopieResidue()	{
		int copieResidue = libro.getCopieResidue();
		int newCopieResidue = copieResidue - quantita;
		int copieDaPrenotare = 0;
		
		if(newCopieResidue<0)	{
			libro.setCopieResidue(0);	//non ci sono più copie del libro in magazzino
			copieDaPrenotare = - newCopieResidue;
		}
		else	{
			libro.setCopieResidue(newCopieResidue);	//aggiornamento delle copie residue
		}
		
		return copieDaPrenotare;
	}

	public String toString(){
		return "id "+ String.valueOf(this.getId()) + " id libro: "+String.valueOf(this.getLibro().getId());
	}
}
