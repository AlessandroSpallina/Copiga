import static org.junit.Assert.*;
import org.junit.Test;


public class AcquistoTest {
	
	@Test
	public void testAddRigaAcquisto(){
		Acquisto acq;
		Libro libro = new Libro("1", "Titolo", "Autore", new Editore("Editore"), 25.0, 5);
		
		// test riga vuota non aggiunta
		acq = new Acquisto();
		acq.addRigaAcquisto(libro,0);
		assertEquals(0,acq.getRigheDiAcquisto().size());
		
		// test positivo
		acq = new Acquisto();
		acq.addRigaAcquisto(libro,10);
		assertEquals(1,acq.getRigheDiAcquisto().size());
		
		// test quantita negativa non viene aggiunta
		acq = new Acquisto();
		acq.addRigaAcquisto(libro,-10);
		assertEquals(0,acq.getRigheDiAcquisto().size());
	}
	
	@Test
	public void testCalcolaTotale() {
		Libro libro = new Libro("1", "Titolo", "Autore", new Editore("Editore"), 25.0, 5);
		Libro libro2 = new Libro("2", "Titolo2", "Autore2", new Editore("Editore2"), 25.0, 3);


		Acquisto acquisto = new Acquisto();
		acquisto.addRigaAcquisto(libro, 2);
		acquisto.addRigaAcquisto(libro2, 2);
		
		double expected = 100.0;
			
		assertEquals("Calcola totale su acquisto regolare",expected,acquisto.calcolaTotale(),0.1);
		
		// calcola totale su acquisto vuoto
		Acquisto acquisto2 = new Acquisto();
		assertEquals("Calcola totale su acquisto vuoto",0,acquisto2.calcolaTotale(),0.1);
	}

	@Test
	public void testAssociaPromozione() {
		Promozione p = new PromozioneSogliaMinima("Soglia 100", 100, 10);
		Promozione p2 = new PromozioneSogliaMinima("Soglia 80", 80, 10);

		
		Libro libro = new Libro("1", "Titolo", "Autore", new Editore("Editore"), 25.0, 5);
		Libro libro2 = new Libro("2", "Titolo2", "Autore2", new Editore("Editore2"), 25.0, 3);


		Acquisto acquisto = new Acquisto();
		acquisto.addRigaAcquisto(libro, 2);
		acquisto.addRigaAcquisto(libro2, 2);
		double expected = 90.0;
		
		assertEquals(expected,acquisto.associaPromozione(p),0.1);
		
		// associa promozione su acquisto vuoto
		Acquisto acquisto2 = new Acquisto();
		assertEquals(0,acquisto2.associaPromozione(p),0.1);
		
	}

}
