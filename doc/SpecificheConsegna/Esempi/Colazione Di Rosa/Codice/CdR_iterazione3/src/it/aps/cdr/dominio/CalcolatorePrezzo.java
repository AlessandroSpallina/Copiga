package it.aps.cdr.dominio;

/**
 * 
 * Strategy per il calcolo del prezzo totale di un ordine, 
 * acceduta come singleton.
 * 
 * @see Ordine
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class CalcolatorePrezzo {
	
	/** l'istanza singleton di CalcolatorePrezzo */
	private static CalcolatorePrezzo singleton;
	
	/**
	 * Restituisce il riferimento all'istanza singleton di CalcolatorePrezzo
	 * @return il riferimento all'istanza singleton di CalcolatorePrezzo
	 */
	public static CalcolatorePrezzo getInstance(){
		if (singleton == null)
			singleton = new CalcolatorePrezzo();
		return singleton;
	}
	
	/** crea un CalcolatorePrezzo */
	protected CalcolatorePrezzo() {}
	
	/**
	 * Calcola il prezzo totale di un ordine 
	 * @param ordine l'ordine contesto
	 * @return il prezzo totale
	 */
	public Prezzo calcolaPrezzo(Ordine ordine){
		Prezzo totale = new Prezzo(0);
		for(ColazioneOrdinata co : ordine.getColazioniOrdinate()){
			totale = totale.somma(calcolaPrezzoColazione(co));
		}
		/* applica un eventuale sconto dato il cliente */
		if (ordine.getCliente()!=null) {
			Cliente cliente = ordine.getCliente();
			CategoriaCliente categoriaAttuale = cliente.getCategoriaCliente();
			if (cliente.getOrdiniEffettuati() == categoriaAttuale.getOrdini())
				categoriaAttuale = GestoreClienti.getInstance().trovaCategoriaSuccessiva(categoriaAttuale);
			totale = totale.moltiplica(categoriaAttuale.getFattoreCliente());
		}
		/* definisce il prezzo dell'ordine contesto */
		ordine.setPrezzo(totale);
		return totale;
	}
	
	/**
	 * Calcola il prezzo di una colazione ordinata
	 * @param co la colazione ordinata di cui calcolare il prezzo
	 * @return il prezzo della colazione ordinata co
	 */
	public Prezzo calcolaPrezzoColazione(ColazioneOrdinata co) {
		/* prezzo base */
		Prezzo prezzoColazione = co.getTipoColazione().getPrezzo();
		/* modifica il prezzo in base alle eventuali variazioni */
		for (Variazione v : co.getVariazioni()){
			int variazione = v.calcolaVariazioneQuantita();
			DescrizioneComponente dv = v.getDescrizioneComponente();
			if (variazione >= 0)
				/* aggiunta o modifica incrementale */
				prezzoColazione = prezzoColazione.somma(dv.getPrezzoAggiuntivo().moltiplica(variazione));
			else
				/* modifica decrementale */
				prezzoColazione = prezzoColazione.somma(dv.getPrezzoRiduttivo().moltiplica(variazione));
		}
		/* modifica il prezzo in base alla modalità di consegna */
		if (co.getModoServizio()!=null)
			prezzoColazione = prezzoColazione.moltiplica(co.getModoServizio().getFattoreMoltiplicativo());
		return prezzoColazione;
	}

}
