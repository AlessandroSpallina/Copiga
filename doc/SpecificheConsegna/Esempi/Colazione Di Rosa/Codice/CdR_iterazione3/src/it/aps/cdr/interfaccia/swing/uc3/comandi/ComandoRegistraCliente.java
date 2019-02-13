package it.aps.cdr.interfaccia.swing.uc3.comandi;

import it.aps.cdr.dominio.CdR;
import it.aps.cdr.dominio.Cliente;
import it.aps.cdr.dominio.ClienteID;
import it.aps.cdr.interfaccia.swing.Comando;
import it.aps.cdr.interfaccia.swing.uc3.NuovoClienteDialog;
import it.aps.cdr.logging.Logger;

public class ComandoRegistraCliente implements Comando {

	public void esegui(Object o) {
		NuovoClienteDialog dialog = (NuovoClienteDialog)o;
		String nome = dialog.getNomeCliente();
		String cognome = dialog.getCognomeCliente();
		String indirizzo = dialog.getIndirizzoCliente();
		
		if (isValid(nome) && isValid(cognome) && isValid(indirizzo)) {
			Cliente c = CdR.getInstance().nuovoCliente(nome,cognome,indirizzo);
			CdR.getInstance().confermaCliente(); 
			dialog.chiudiFinestra();
		} else
			Logger.getInstance().errore("errore","Parametri mancanti.");
	}
	
	private boolean isValid(String s) {
		return s!=null && !s.equals("");
	}
	
}
