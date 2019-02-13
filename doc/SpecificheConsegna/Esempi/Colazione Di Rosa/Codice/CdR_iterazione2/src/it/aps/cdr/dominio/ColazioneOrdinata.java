package it.aps.cdr.dominio;

import java.util.LinkedList;
import java.util.List;

/**
 * Un oggetto ColazioneOrdinata rappresenta una colazione ordinata 
 * in relazione ad un ordine, costituita da un tipo di colazione 
 * standard e da possibili variazioni.
 * 
 * @see TipoColazione
 * 
 * @author Luca Cabibbo
 * @author Marco Canu
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.2
 * 
 */
public class ColazioneOrdinata {

	/* il tipo di colazione al quale è associata la colazione */
	private TipoColazione tipoColazione; 
	/* le variazioni effettuate rispetto al tipo di colazione */
	private List<Variazione> variazioni;
	/* la modalita' di servizio della colazione ordinata */
	private ModoServizio modoServizio;

	/**
	 * Crea una nuova ColazioneOrdinata
	 * 
	 * @param tc il tipo di colazione
	 */
	public ColazioneOrdinata(TipoColazione tc) {
		this.tipoColazione = tc;
		this.variazioni = new LinkedList<Variazione>();
	}
	
	/**
	 * Calcola il prezzo di questa colazione ordinata. 
	 * 
	 * @return il prezzo di questa colazione 
	 */
	public double calcolaPrezzo() {
		/* prezzo base */
		double prezzoColazione = this.getTipoColazione().getPrezzo();
		/* modifica il prezzo in base alle eventuali variazioni */
		for (Variazione v : this.getVariazioni()){
			int variazione = v.calcolaVariazioneQuantita();
			DescrizioneComponente dv = v.getDescrizioneComponente();
			if (variazione >= 0)
				/* aggiunta o modifica incrementale */
				prezzoColazione += dv.getPrezzoAggiuntivo()*variazione;
			else
				/* modifica decrementale */
				prezzoColazione -= dv.getPrezzoRiduttivo()*variazione;
		}
		/* modifica il prezzo in base alla modalità di consegna */
		prezzoColazione *= this.getModoServizio().getFattoreMoltiplicativo();
		return prezzoColazione;
	}
	
	/**
	 * Restituisce il tipo di colazione 
	 * 
	 * @return il tipo di colazione 
	 */
	public TipoColazione getTipoColazione() {
		return this.tipoColazione;
	}
	
	/**
	 * Definisce il tipo di colazione a cui è associata questa 
	 * colazione ordinata 
	 * 
	 * @param tipoColazione
	 */
	public void setTipoColazione(TipoColazione tipoColazione) {
		this.tipoColazione = tipoColazione;
	}
	
	/**
	 * Restituisce le variazioni introdotte al tipo di colazione 
	 * @return la lista di variazioni
	 */
	public List<Variazione> getVariazioni() {
		return this.variazioni;
	}
	
	/**
	 * Restituisce la modalita' di servizio 
	 * @return la modalita' di servizio
	 */
	public ModoServizio getModoServizio() {
		return this.modoServizio;
	}
	
	/**
	 * Definisce le variazioni introdotte al tipo di colazione
	 * @param variazioni
	 */
	public void setVariazioni(List<Variazione> variazioni) {
		this.variazioni = variazioni;
	}

	/**
	 * Definisce la modalita' di servizio
	 * @param modoServizio la modalita' di servizio
	 */
	public void setModoServizio(ModoServizio modoServizio) {
		this.modoServizio = modoServizio;
	}

	/**
	 * Modifica la quantita' di una componente della colazione ordinata 
	 * nell'ordine corrente. Rimuove la componente se la quantita' e' 
	 * pari a 0 
	 * @param descrizioneComponenteID il codice della componente da modificare
	 * @param quantita la nuova quantita' desiderata
	 * @return true se la modifica e' stata effettuata, false altrimenti
	 */
	public boolean modificaComponente(DescrizioneComponente dc, int quantita) {
		boolean risultato = false;
		if (dc != null){
			int quantitaBase = this.tipoColazione.quantitaComponente(dc); 
			Variazione v = new Variazione(this,dc,quantita-quantitaBase);
			risultato = this.variazioni.add(v);
		}
		return risultato;
	}
	
	/**
	 * Aggiunge una nuova componente e relativa quantita' alla colazione ordinata 
	 * nell'ordine corrente. 
	 * @param dc la descrizione della nuova componente da aggiungere
	 * @param quantita la quantita' desiderata della nuova componente
	 * @return true se la componente e' stata aggiunta, false altrimenti
	 */
	public boolean aggiungiComponente(DescrizioneComponente dc, int quantita) {
		Variazione v = new Variazione(this,dc,quantita);
		return this.variazioni.add(v);
	}

}
