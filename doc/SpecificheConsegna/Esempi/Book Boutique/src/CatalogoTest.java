import static org.junit.Assert.*;

import org.junit.Test;


public class CatalogoTest {
	Catalogo catalogo = new Catalogo();

	@Test
	public void testAggiungiEditore() {
		catalogo.aggiungiEditore("A.Mondadori");
		catalogo.aggiungiEditore("B.Mondadori");
		catalogo.aggiungiEditore("Zanichelli");

		int expected = 3;		
		assertEquals(expected,catalogo.getEditori().size());
		
		//test aggiunta di un editore con nome "vuoto"
		catalogo.aggiungiEditore("");
		assertEquals("e' stato aggiunto un editore con un nome non ammesso",expected,catalogo.getEditori().size());
		
		//test aggiunta di un editore "omonimo"
		catalogo.aggiungiEditore("Zanichelli");
		assertEquals("e' stato aggiunto un editore con un nome non ammesso",expected,catalogo.getEditori().size());
	}
	
	@Test
	public void testCercaEditore() {
		catalogo.aggiungiEditore("A.Mondadori");
		catalogo.aggiungiEditore("B.Mondadori");
		catalogo.aggiungiEditore("Zanichelli");
		
		String expected = "Zanichelli";

		//test di ricerca di editore con sottostringa univoca
		assertEquals("non e' stato trovato l'editore atteso",expected,catalogo.cercaEditore("zan").getNome());
		
		// test con sottostringa non univoca
		assertNull(catalogo.cercaEditore("dad"));
	}
	
	@Test
	public void testAddLibro() {
		Libro libro1 = new Libro("", "", "", new Editore("Editore"), 10, 0);
		Libro libro2 = new Libro("2", "Titolo2", "Autore2", new Editore("Editore"), 20, 0);
		Libro libro3 = new Libro("2", "Titolo2", "Autore2", new Editore("Editore"), 20, 0);
		
		
		catalogo = new Catalogo();
		catalogo.addLibro(libro2);	
		assertEquals("aggiunta di un libro valido",1,catalogo.getLibri().size());
		
		//test aggiunta di un libro con codice "vuoto"
		catalogo = new Catalogo();
		catalogo.addLibro(libro1);
		assertEquals("e' stato aggiunto un libro con un codice non ammesso",0,catalogo.getLibri().size());
		
		//test aggiunta di un libro con codice già presente in archivio
		catalogo = new Catalogo();
		catalogo.addLibro(libro2);
		catalogo.addLibro(libro3);
		assertEquals("libro gia' presente nell'archivio ",1,catalogo.getLibri().size());
	}
	
	@Test
	public void testGetLibro() {
		Libro libro1 = new Libro("1", "Titolo1", "Autore1", new Editore("Editore"), 10, 0);
		Libro libro2 = new Libro("2", "Titolo2", "Autore2", new Editore("Editore"), 20, 0);
		Libro libro3 = new Libro("3", "Titolo3", "Autore3", new Editore("Editore"), 30, 0);
		
		catalogo.addLibro(libro1);
		catalogo.addLibro(libro2);
		catalogo.addLibro(libro3);

		assertNotNull(catalogo.getLibro("1"));
		assertNull(catalogo.getLibro("5"));

	}
}
