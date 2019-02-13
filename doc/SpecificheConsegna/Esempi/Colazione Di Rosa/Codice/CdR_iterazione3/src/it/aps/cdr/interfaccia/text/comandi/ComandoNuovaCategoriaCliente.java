package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.interfaccia.text.ConsoleNuovaCategoriaCliente;
import it.aps.cdr.interfaccia.text.Parser;

/**
 * Comando per definire una nuova categoria di cliente
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoNuovaCategoriaCliente implements Comando{
	
	public static final String codiceComando="1";
	public static final String descrizioneComando="NUOVA CATEGORIA DI CLIENTE";
	
   	public String getCodiceComando() {
		return codiceComando;
	}
	
   	public String getDescrizioneComando() {
		return descrizioneComando;
	}

    public void esegui() {
		System.out.println("   Inserisci il nome della categoria di cliente: ");
		String nome = Parser.getInstance().read();
		System.out.println();
		if (CdR.getInstance().trovaCategoriaCliente(nome) == null){
			System.out.println("   Inserisci la descrizione della categoria di cliente: ");
			String descr = Parser.getInstance().read();
			System.out.println();
			System.out.println("   Inserisci il fattore moltiplicativo della categoria di cliente: ");
			double fm = Double.parseDouble(Parser.getInstance().read());
			System.out.println();
			System.out.println("   Inserisci il numero di ordini massimo della categoria di cliente: ");
			int om = Integer.parseInt(Parser.getInstance().read());
			System.out.println();
			if (CdR.getInstance().trovaCategoriaCliente(om) == null){
				CdR.getInstance().nuovaCategoriaCliente(nome,descr,fm,om);
				ConsoleNuovaCategoriaCliente nccc = new ConsoleNuovaCategoriaCliente();
				nccc.start();
			} else {
				System.out.println("   Esiste gia' una categoria di ordini : " + om);
			}
		} else {
			System.out.println("   Esiste gia' una categoria di nome : " + nome);
		}

	}
}
