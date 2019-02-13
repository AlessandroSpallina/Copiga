package it.aps.cdr.interfaccia.swing.uc2.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.Cliente;
import it.aps.cdr.interfaccia.swing.Comando;
import it.aps.cdr.interfaccia.swing.uc2.ClientiRicercaTable;
import it.aps.cdr.interfaccia.swing.uc2.CompletaOrdineDialog;
import it.aps.cdr.logging.Logger;

import java.util.Collection;


public class ComandoTrovaCliente implements Comando {

	public void esegui(Object o) {
		CompletaOrdineDialog dialog = (CompletaOrdineDialog)o;
		String nome = dialog.getNome();
		String cognome = dialog.getCognome();
		/* almeno uno tra nome e cognome deve essere valido */ 
		if (isValid(nome) || isValid(cognome)) {
			Collection<Cliente> clienti = CdR.getInstance().trovaCliente(nome,cognome);
			((ClientiRicercaTable)dialog.getClientiRicercatiTable()).setClienti(clienti);
			if (clienti.isEmpty()) {
				String messaggio = "Non esistono clienti registrati con il nome e cognome specificati.";
				Logger.getInstance().messaggio("cliente non trovato",messaggio);
			}
		} else
			Logger.getInstance().errore("errore","Parametri mancanti.");
	}

	private boolean isValid(String s) {
		return s!=null && !s.equals("");
	}

}
