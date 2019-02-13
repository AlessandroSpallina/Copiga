import static org.junit.Assert.*;

import org.junit.Test;


public class OrdineTest {

	Editore ed = new Editore("Zanichelli");
	Ordine ordine = new Ordine(ed);
	
	@Test
	public void testAggiungiRigaOrdine() {
		// libro stesso editore ordine
		Libro libro1 = new Libro("1", "Titolo1", "Autore1", ed, 10, 0);
		// libro editore diverso dall'ordine
		Libro libro2 = new Libro("2", "Titolo2", "Autore2", new Editore("ed2"), 20, 0);
		
		int expected = 0;
		ordine.aggiungiRigaOrdine(libro1, 0);
		//test inserimento di 0 copie di libro1 all'ordine
		assertEquals("e' stato aggiunto un libro con quantita' nulla",expected,ordine.getRigheOrdine().size());
		
		 //test inserimento di un libro dello stesso editore dell'ordine
		 assertTrue(ordine.aggiungiRigaOrdine(libro1, 1));
		 
		 //test inserimento di un libro di un altro editore rispetto a quello dell'ordine
		 assertFalse(ordine.aggiungiRigaOrdine(libro2, 1));

		
	}

}
