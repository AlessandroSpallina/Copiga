import java.text.SimpleDateFormat;
import java.util.LinkedList;


public class Ordine implements Cloneable {
	private Editore editore;
	private LinkedList<RigaOrdine> righeOrdine;
	
	
	/*
	 *********************
	 * COSTRUTTORI
	 *********************
	 */	
	
	// costruttore senza editore
	public Ordine(){
		righeOrdine = new LinkedList<RigaOrdine>();
		//this.editore = editore;
	}
	
	// costruttore con editore
	public Ordine(Editore editore){
		righeOrdine = new LinkedList<RigaOrdine>();
		this.editore = editore;
	}
	

	/*
	 *********************
	 * GESTIONE ORDINI
	 *********************
	 */
	
	/**
	 * aggiunge una riga all'ordine in corso
	 */
	public boolean aggiungiRigaOrdine(Libro libro, int quantita) {
		boolean stessoEditore = false;
		
		// controlla se esiste una riga corrispondente
		if((editore.getId() == libro.getEditore().getId()) && (quantita > 0)){
			stessoEditore = true;
			boolean trovato = false;
			
			for(RigaOrdine ro: righeOrdine){
				if(ro.getLibro().getId() == libro.getId()){
					ro.setQuantita(ro.getQuantita() + quantita);
					trovato = true;
				}
			}
			
			if(trovato == false){
				righeOrdine.add(new RigaOrdine(libro,quantita));
			}
		}
		return stessoEditore;
	}
	

	/*
	 *********************
	 * METODI VARI
	 *********************
	 */
	
	public LinkedList<RigaOrdine> getRigheOrdine() {
		return righeOrdine;
	}
	
	public void setEditore(Editore editore){
		this.editore = editore;
	}
	
	public Editore getEditore(){
		return editore;
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
		return editore.getNome() + "\n - " + getRigheOrdine().toString();
	}
}
