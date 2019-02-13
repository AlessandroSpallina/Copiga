import static org.junit.Assert.*;

import org.junit.Test;


public class LibroTest {

	@Test
	public void testIncrementaCopieResidue() {
		int expected;
		Libro libro;
		
		// test valore positivo
		libro = new Libro("1", "Titolo", "Autore", new Editore("Editore"), 10, 10);
		expected=11;
		libro.incrementaCopieResidue(1);
		assertEquals(expected,libro.getCopieResidue());
		
		// test incrementa copie residue con numero negativo
		libro = new Libro("1", "Titolo", "Autore", new Editore("Editore"), 10, 10);
		libro.incrementaCopieResidue(-1);
		expected = 10;
		assertEquals("Incrementa con numero negativo",expected,libro.getCopieResidue());
		
		// test zero
		libro = new Libro("1", "Titolo", "Autore", new Editore("Editore"), 10, 10);
		expected=10;
		libro.incrementaCopieResidue(0);
		assertEquals(expected,libro.getCopieResidue());
	}

	@Test
	public void testAssegna() {
		
		assertEquals(10,(new Libro("1", "Titolo", "Autore", new Editore("Editore"), 0, 10)).assegna(10));
		assertEquals(0,(new Libro("1", "Titolo", "Autore", new Editore("Editore"), 0, 0)).assegna(10));
		assertEquals(5,(new Libro("1", "Titolo", "Autore", new Editore("Editore"), 0, 10)).assegna(5));
		assertEquals(10,(new Libro("1", "Titolo", "Autore", new Editore("Editore"), 0, 10)).assegna(11));
		assertEquals(0,(new Libro("1", "Titolo", "Autore", new Editore("Editore"), 0, 10)).assegna(-11));
	}

	@Test
	public void testSetCopieResidue() {
		Libro libro;
		int expected;
		
		// set un numero positivo
		libro = new Libro("1", "Titolo", "Autore", new Editore("Editore"), 0, 0);
		libro.setCopieResidue(10);
		expected = 10;
		assertEquals("Set numero non negativo",expected,libro.getCopieResidue());
		
		// set zero
		libro = new Libro("1", "Titolo", "Autore", new Editore("Editore"), 10, 10);
		libro.setCopieResidue(0);
		expected = 0;
		assertEquals("Set 0",expected,libro.getCopieResidue());
		
		// set negativo solleva eccezione
		libro = new Libro("1", "Titolo", "Autore", new Editore("Editore"), 0, 0);
		libro.setCopieResidue(0);
		expected = 0;
		assertEquals("Set 0",expected,libro.getCopieResidue());
	}
}
