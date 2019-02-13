public void clearRicercaLibro()
{
	textISBN.setText("");
	ricercaLibro_titolo.setText("");
	ricercaLibro_autore.setText("");
	ricercaLibro_isbn.setText("");
	ricercaLibro_copie.setText("");
	ricercaLibro_prezzo.setText("");
	quantitaLibri.setText("1");

	// reset attribute libro?
}

public void clearMostraSchedaAcquisto()
{
	textField_cliente.setText("");
	textField_totale.setText("");
	textField_totaleScontato.setText("");

	// reset table
}

public void clearRicercaCliente()
{
	textFieldNome.setText("");
	textFieldCognome.setText("");
	textAreaCliente.setText("");
}

public void clearStoricoAcquisti()
{
	textAreaStoricoAcquisti.setText("");
}

public void clearCercaPrenotazioni()
{
	textFieldCercaEditore.setText("");
	textFieldEditore.setText("");

	// clear tablePrenotazioni
}

public void clearMostraOrdine()
{
	textField_isbn.setText("");
	textField_2.setText("");
	textField.setText("");
}

public void clearStoricoOrdini()
{
	textAreaStoricoOrdini.setText("");
}