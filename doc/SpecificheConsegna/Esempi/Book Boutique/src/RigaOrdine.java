
public class RigaOrdine {
	private int id;
	private Libro libro;
	private int quantita;
	private int libroId;
	
	
	/*
	 *********************
	 * COSTRUTTORE
	 *********************
	 */	
	public RigaOrdine(Libro libro, int quantita) {
		// TODO Auto-generated constructor stub
		this.libro = libro;
		this.quantita = quantita;
	}
	
	public RigaOrdine()
	{
		
	}
	
	/*
	 *********************
	 * METODI VARI
	 *********************
	 */
	public Libro getLibro() {
		return libro;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int i) {
		this.quantita = i;
	}
	
	public int getLibroId() {
		return libroId;
	}

	public void setLibroId(int i) {
		this.libroId = i;
	}
	
	/**
	 * override del metodo toString
	 */
	public String toString(){
		return "Titolo: " + libro.getTitolo() + " quantita: "+ quantita + "\n";
	}
}
