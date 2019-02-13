import java.util.Observable;
import java.util.Observer;

public class Libro extends Observable {

	private int id;
	private String ISBN;
	private String titolo;
	private String autore;
	private int editoreId;
	private Editore editore;
	private double prezzo;
	private int copieResidue;

	
	/*
	 *********************
	 * COSTRUTTORE
	 *********************
	 */	
	
	public Libro(){
		
	}
	
	//costruttore senza copie residue
	public Libro(String ISBN, String titolo, String autore, Editore editore, double prezzo)	{
		
		this.ISBN = ISBN;
		this.titolo = titolo;
		this.autore = autore;
		this.editore = editore;
		this.prezzo = prezzo;
		this.copieResidue = 0; //default
	}
	
	
	//costruttore con copie residue
	public Libro(String ISBN, String titolo, String autore, Editore editore, double prezzo, int copieResidue)	{
		
		this.ISBN = ISBN;
		this.titolo = titolo;
		this.autore = autore;
		this.editore = editore;
		this.prezzo = prezzo;
		this.copieResidue = copieResidue;
	}
	
	/*
	 *********************
	 * METODI VARI
	 *********************
	 */

	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getEditoreId(){
		return editoreId;
	}
	
	public void setEditoreId(int editoreId){
		this.editoreId = editoreId;
	}
	
	public String getISBN() {
		return ISBN;
	}
	
	public void setISBN(String ISBN){
		this.ISBN = ISBN;
	}
	
	public String getTitolo() {
		return titolo;
	}
	
	public void setTitolo(String titolo){
		this.titolo = titolo;
	}
	
	public String getAutore() {
		return autore;
	}
	
	public void setAutore(String autore){
		this.autore = autore;
	}
	
	public Editore getEditore() {
		return editore;
	}
	
	public void setEditore(Editore editore){
		this.editore = editore;
	}
	
	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo){
		this.prezzo = prezzo;
	}
	
	public int getCopieResidue() {
		return copieResidue;
	}
	
	public void setCopieResidue(int copieResidue) {
		this.copieResidue = copieResidue;
	}
	
	public void incrementaCopieResidue(int quantita){
		
		if(quantita > 0){
			this.setCopieResidue(quantita + this.getCopieResidue());

			System.out.println("Notifico");
			setChanged();
			notifyObservers();
		}
	}
	
	/**
	 * override del metodo toString
	 */
	public String toString(){
		return "Titolo: " + this.titolo + " prezzo: " + this.prezzo + " ID: " + this.getId();
	}

	/*
	 * Restituisce quante copie effettivamente e' possibile assegnare
	 */
	public int assegna(int quantita) {
		int assegnate=0;
		
		// TODO Auto-generated method stub
		while((this.getCopieResidue() > 0) && (quantita > 0)){
			this.setCopieResidue(this.getCopieResidue() - 1);
			assegnate++;
			quantita--;
		}

		return assegnate;
	}
}
