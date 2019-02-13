package it.aps.cdr.interfaccia.text.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.DescrizioneComponente;
import it.aps.cdr.dominio.DescrizioneComponenteID;
import it.aps.cdr.interfaccia.text.Parser;

/**
 * Comando per trovare una descrizione di una componente registrata
 * 
 * @author Fabrizio Martorelli
 * @author Andrea Petreri 
 * @author Gabriele Rendina
 * 
 * @version 0.3
 * 
 */
public class ComandoTrovaDescrizioneComponente implements Comando{
	
	public static final String codiceComando = "2";
	public static final String descrizioneComando = "TROVA UNA DESCRIZIONE COMPONENTE";
	
	public String getCodiceComando() {
		return codiceComando;
	}

	public String getDescrizioneComando() {
		return descrizioneComando;
	}

	public void esegui() {
		System.out.println("   Inserisci il codice della descrizione componente da cercare: ");
		int codice = Integer.parseInt(Parser.getInstance().read());
		System.out.println();
		DescrizioneComponenteID dcID = new DescrizioneComponenteID(codice);
		DescrizioneComponente dc = CdR.getInstance().trovaDescrizioneComponente(dcID);
		if (dc != null) {
			System.out.println("   " + dc);
		} else {
			System.out.println("   ERRORE: DESCRIZIONE COMPONENTE INESISTENTE");
		}
	}
	
}
