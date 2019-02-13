import static org.junit.Assert.*;

import org.junit.Test;


public class RigaDiAcquistoTest {

	@Test
	public void testAggiornaCopieResidue() {
		Libro libro = new Libro("1", "Titolo", "Autore", new Editore("ed"), 20, 5);
		
		//test quantita = copie residue
		RigaDiAcquisto rda = new RigaDiAcquisto(libro, 5);
		int expected = 0;
		assertEquals(expected,rda.aggiornaCopieResidue());

		//test quantita > copie residue
		RigaDiAcquisto rda2 = new RigaDiAcquisto(libro, 5);
		expected = 5;
		assertEquals(expected,rda.aggiornaCopieResidue());	
		
		//test quantita < copie residue
		libro.setCopieResidue(10);
		RigaDiAcquisto rda3 = new RigaDiAcquisto(libro, 2);
		expected = 0;
		assertEquals(expected,rda.aggiornaCopieResidue());	
	}

}
