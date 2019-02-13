
public class Startup {

	private Libro libro1,libro2,libro3,libro4;
	private Cliente cliente1,cliente2,cliente3,cliente4;
	private BookBoutique bobo;
	private Catalogo catalogo;
	
	/*
	 *********************
	 * COSTRUTTORE
	 *********************
	 */	
	
	public Startup(BookBoutique bobo) {
		
		this.bobo = bobo;
		
		this.catalogo = bobo.getCatalogo();
		
		catalogo.aggiungiEditore("Pearson");
		catalogo.aggiungiEditore("McGraw-Hill Education");
		
		// alcuni libri di esempio
		libro1 = new Libro("1", "Il linguaggio C. Principi di programmazione e manuale di riferimento", "Kernighan & Ritchie", catalogo.cercaEditore("Pearson"), 22.95, 2);
		bobo.inserisciLibro(libro1);
		
		libro2 = new Libro("2", "Introduzione agli algoritmi e strutture dati", "Cormen", catalogo.cercaEditore("McGraw-Hill Education"), 52.7, 2);
		bobo.inserisciLibro(libro2);
		
		libro3 = new Libro("3", "Sistemi operativi. Concetti ed esempi", "Abraham Silberschatz", catalogo.cercaEditore("Pearson"), 41.65, 2);
		bobo.inserisciLibro(libro3);
		
		libro4 = new Libro("4", "Architettura dei calcolatori. Un approccio strutturale", "N.D.", catalogo.cercaEditore("Pearson"), 41.65, 2);
		bobo.inserisciLibro(libro4);
		
		// alcuni clienti di esempio
		/*
		cliente1 = new Cliente("Mario", "Rossi", "095001234", "ingsw_lm32@email.it");
		bobo.addCliente(cliente1);
		
		cliente2 = new Cliente("Mark", "Loria", "095051234", "mark.loria@gmail.com");
		bobo.addCliente(cliente2);
		
		cliente3 = new Cliente("Mary", "Spada", "333222111", "swordy07@gmail.com");
		bobo.addCliente(cliente3);
		
		cliente4 = new Cliente("Maria", "Rossi", "3400012333", "ingsw_lm32@email.it");
		bobo.addCliente(cliente4);
		*/

		libro3 = bobo.getCatalogo().getLibro("3");
		libro4 = bobo.getCatalogo().getLibro("4");
		
		cliente1 = bobo.ricercaCliente("Mario", "rossi");
		cliente4 = bobo.ricercaCliente("Maria", "rossi");
		
		// alcune prenotazioni di esempio
		//bobo.addPrenotazione(new Prenotazione(cliente1,libro3,3));
		//bobo.addPrenotazione(new Prenotazione(cliente4,libro3,3));
		//bobo.addPrenotazione(new Prenotazione(cliente1,libro4,3));
		
		// alcune promozioni di esempio
		Promozione dataPromo = new PromozioneData("Data",5,1425477752,1435477952);
		Promozione riduzionePromo = new PromozioneRiduzione("Contributo statale",50);
		Promozione sogliaPromo = new PromozioneSogliaMinima("-5%",100,5);
		Promozione comboPromo = new PromozioneComposite("Promozione Combo");
		comboPromo.add(dataPromo);
		comboPromo.add(riduzionePromo);
		comboPromo.add(sogliaPromo);
		
		bobo.addPromozione(dataPromo);
		bobo.addPromozione(riduzionePromo);
		bobo.addPromozione(sogliaPromo);
		bobo.addPromozione(comboPromo);
		
		System.out.println("End of startup");
	}
}
