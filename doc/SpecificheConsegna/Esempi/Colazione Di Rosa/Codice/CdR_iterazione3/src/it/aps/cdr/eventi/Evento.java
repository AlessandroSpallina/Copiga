package it.aps.cdr.eventi;

public interface Evento {

	/** generato dal menu */
	public static final String NUOVO_TIPO_COLAZIONE = "creato un nuovo tipo di colazione";

	/** generato dal tipo colazione corrente */
	public static final String AGGIUNGI_COMPONENTE_TIPO_COLAZIONE = "aggiunta una componente al tipo colazione corrente";

	/** generato dal menu */
	public static final String CONFERMA_NUOVO_TIPO_COLAZIONE = "aggiunto un nuovo tipo di colazione alle colazioni registrate";

	/** generato dalla colazione ordinata corrente */
	public static final String VARIAZIONE_COMPONENTE_COLAZIONE = "aggiunta una variazione alla colazione ordinata corrente";

	/** generato dalla colazione ordinata corrente */
	public static final String DEFINISCI_MODO_SERVIZIO = "associata la modalita' di servizio alla colazione ordinata corrente";

	/** generato dall'ordine corrente */
	public static final String CONFERMA_COLAZIONE_ORDINATA = "aggiunta la colazione ordinata all'ordine corrente";

	/** generato dall'ordine corrente */
	public static final String COMPLETA_ORDINE = "completato l'ordine corrente con l'aggiunta di data e cliente";

	/** generato da CdR */
	public static final String CONFERMA_NUOVO_ORDINE = "aggiunto l'ordine corrente agli ordini registrati";

	/** generato dal gestore dei clienti */
	public static final String CONFERMA_NUOVO_CLIENTE = "aggiunto un nuovo cliente ai clienti registrati";

	/** generato da CdR */
	public static final String RICARICA_DATI = "ricaricati i dati registrati";

	/** generato dal menu */
	public static final String CONFERMA_NUOVA_DESCRIZIONE_COMPONENTE = "creata una nuova descrizione di componente";

	/** generato dal gestore dei clienti */
	public static final String CONFERMA_NUOVA_CATEGORIA_CLIENTE = "aggiunto una nuova categoria di cliente alle categoria registrate";

	/** generato dal menu */
	public static final String CONFERMA_NUOVO_MODO_SERVIZIO = "creato un nuovo modo di servizio";

}