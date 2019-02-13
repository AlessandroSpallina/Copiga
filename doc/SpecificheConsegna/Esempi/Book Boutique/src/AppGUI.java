import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class AppGUI {

	private BookBoutique bobo;
	private Startup startup;

	private Editore editore;
	private Libro libro;
	private Cliente cliente;
	private LinkedList<Prenotazione> prenotazioni;

	private JFrame frame;
	private Container mainContainer;
	
	private JPanel controlPanel, ricercaCliente, mostraSchedaAcquisto,
			ricercaLibro, inserisciLibro, storicoAcquisti,cercaPrenotazioni,mostraOrdine, storicoOrdini;
		
	private JMenuBar menuBar;	
	private JMenu menuCatalogo, menuAcquisti, menuClienti, menuMagazzino;
	
	private JMenuItem itemRicercaLibro, itemInserisciLibro, itemVisualizzaCat, itemStoricoAcq,
		itemRicercaCliente, itemCreaCliente, itemVisualizzaCliente, itemCreaOrdine, itemStoricoOrdini;
		
	// Ricerca libro
	private JLabel lblLogo, lblIsbn, lblTitolo, lblAutore_1, lblIsbn_2, lblCopieResidue, lblPrezzo_1;
	private JTextField textISBN, ricercaLibro_titolo, ricercaLibro_autore, ricercaLibro_isbn;
	private JButton btnCerca,btnNewButton,btnSchedaAcquisto,btnAggiornaDisponibilita;
	
	private JTextField textField_totale, textField_cliente, textField_totaleScontato, textFieldNome,
		textFieldCognome, ricercaLibro_copie, ricercaLibro_prezzo, quantitaLibri;
	
	private final Action action = new SwingAction();


	private JTable tableSchedaAcquisto,tablePrenotazioni;
	private JTable tablePrenotazioni_1;
	private JTextField textFieldCercaEditore;
	private JTextField textFieldEditore;
	private JTable tableListaPrenotazioni;
	private JTable tableOrdine;
	private JTextField textField;
	private JTextField textField_isbn;
	private JTextField textField_2;
	private JButton btnTermina,btnProcedi,btnAvanti, btnAggiungiNuovo;

	private JTextArea textAreaCliente,textAreaStoricoAcquisti,textAreaStoricoOrdini;
	private JPanel panelCatalogo;
	private JTable tableCatalogo;
	
	/**
	 * Esecuzione dell'applicazione.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppGUI window = new AppGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	/**
	 * Costruttore dell'applicazione.
	 */
	public AppGUI() {
		initialize();
		bobo = BookBoutique.getIstanza();
		startup = new Startup(bobo);
	}

	
	
	/**
	 * Inizializza i contenuti del frame.
	 */
	private void initialize() {
		mostraSchedaAcquisto = new JPanel();
		textAreaCliente = new JTextArea();
		JScrollPane scrollPane = new JScrollPane();
		
		tablePrenotazioni = new JTable(new DefaultTableModel(new Object[]{ "Titolo", "Cliente", "Copie"},0));
		tableSchedaAcquisto = new JTable(new DefaultTableModel(new Object[]{ "Titolo", "Quantita", "Copie residue","Prezzo", "Subtotale" },0));
		tableOrdine = new JTable(new DefaultTableModel(new Object[]{ "Titolo", "Copie"},0));
		tableCatalogo = new JTable(new DefaultTableModel(new Object[]{ "ISBN", "Titolo", "Editore", "Copie"},0));

		frame = new JFrame("BO.BO. - Book Boutique");
		frame.setBounds(100, 100, 663, 420);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainContainer = frame.getContentPane();
		frame.getContentPane().setLayout(new CardLayout(0, 0));

		controlPanel = new JPanel();
		frame.getContentPane().add(controlPanel, "name_8206744650103");
		controlPanel.setLayout(new CardLayout(0, 0));

		//logo
		lblLogo = new JLabel();
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		controlPanel.add(lblLogo, "name_88670985427641");
		lblLogo.setIcon(new javax.swing.ImageIcon(
				"..\\Book Boutique\\src\\resources\\book-boutique-logo.png"));

		ricercaLibro = new JPanel();
		frame.getContentPane().add(ricercaLibro, "name_8385940777074");
		ricercaLibro.setLayout(null);

		lblIsbn = new JLabel("ISBN");
		lblIsbn.setBounds(10, 9, 65, 14);
		ricercaLibro.add(lblIsbn);

		textISBN = new JTextField();
		textISBN.setBounds(85, 6, 144, 20);
		ricercaLibro.add(textISBN);
		textISBN.setColumns(10);

		btnCerca = new JButton("Cerca");
		btnCerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				libro = bobo.ricercaLibroISBN(textISBN.getText());
				
				quantitaLibri.setText("1");

				if (libro != null) {					
					ricercaLibro_titolo.setText(libro.getTitolo());
					ricercaLibro_autore.setText(libro.getAutore());
					ricercaLibro_isbn.setText(libro.getISBN());
					ricercaLibro_copie.setText(String.valueOf(libro.getCopieResidue()));
					ricercaLibro_prezzo.setText(String.valueOf(libro.getPrezzo()));
					
					btnNewButton.setEnabled(true);
					btnSchedaAcquisto.setEnabled(true);
					btnAggiornaDisponibilita.setEnabled(true);
				} else {
					btnAggiungiNuovo.setVisible(true);
					ricercaLibro_titolo.setText("Libro non trovato");
					ricercaLibro_autore.setText("");
					ricercaLibro_isbn.setText("");
					ricercaLibro_copie.setText("");
					ricercaLibro_prezzo.setText("");
					btnNewButton.setEnabled(false);
					btnSchedaAcquisto.setEnabled(false);
					btnAggiornaDisponibilita.setEnabled(false);
				}

				//Email.invia();
			}
		});
		btnCerca.setBounds(10, 33, 73, 23);
		ricercaLibro.add(btnCerca);

		JLabel lblQuantita = new JLabel("Quantit\u00E0");
		lblQuantita.setBounds(10, 285, 65, 14);
		ricercaLibro.add(lblQuantita);

		quantitaLibri = new JTextField();
		quantitaLibri.setText("1");
		quantitaLibri.setBounds(67, 282, 55, 20);
		ricercaLibro.add(quantitaLibri);
		quantitaLibri.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 68, 627, 3);
		ricercaLibro.add(separator);

		lblTitolo = new JLabel("Titolo");
		lblTitolo.setBounds(10, 82, 46, 14);
		ricercaLibro.add(lblTitolo);

		lblAutore_1 = new JLabel("Autore");
		lblAutore_1.setBounds(10, 107, 46, 14);
		ricercaLibro.add(lblAutore_1);

		lblIsbn_2 = new JLabel("ISBN");
		lblIsbn_2.setBounds(10, 132, 46, 14);
		ricercaLibro.add(lblIsbn_2);

		lblCopieResidue = new JLabel("Copie");
		lblCopieResidue.setBounds(10, 157, 46, 14);
		ricercaLibro.add(lblCopieResidue);

		lblPrezzo_1 = new JLabel("Prezzo");
		lblPrezzo_1.setBounds(10, 182, 46, 14);
		ricercaLibro.add(lblPrezzo_1);

		ricercaLibro_titolo = new JTextField();
		ricercaLibro_titolo.setEditable(false);
		ricercaLibro_titolo.setBounds(67, 79, 570, 20);
		ricercaLibro.add(ricercaLibro_titolo);
		ricercaLibro_titolo.setColumns(10);

		ricercaLibro_autore = new JTextField();
		ricercaLibro_autore.setEditable(false);
		ricercaLibro_autore.setColumns(10);
		ricercaLibro_autore.setBounds(67, 104, 570, 20);
		ricercaLibro.add(ricercaLibro_autore);

		ricercaLibro_isbn = new JTextField();
		ricercaLibro_isbn.setEditable(false);
		ricercaLibro_isbn.setColumns(10);
		ricercaLibro_isbn.setBounds(67, 129, 570, 20);
		ricercaLibro.add(ricercaLibro_isbn);

		ricercaLibro_copie = new JTextField();
		ricercaLibro_copie.setEditable(false);
		ricercaLibro_copie.setColumns(10);
		ricercaLibro_copie.setBounds(67, 154, 570, 20);
		ricercaLibro.add(ricercaLibro_copie);

		ricercaLibro_prezzo = new JTextField();
		ricercaLibro_prezzo.setEditable(false);
		ricercaLibro_prezzo.setColumns(10);
		ricercaLibro_prezzo.setBounds(66, 179, 571, 20);
		ricercaLibro.add(ricercaLibro_prezzo);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 207, 627, 3);
		ricercaLibro.add(separator_1);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(10, 310, 627, 40);
		ricercaLibro.add(panel_7);
				panel_7.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
				btnNewButton = new JButton("Aggiungi");
				panel_7.add(btnNewButton);
				
						btnSchedaAcquisto = new JButton("Scheda acquisto");
						panel_7.add(btnSchedaAcquisto);
						
						btnAggiornaDisponibilita = new JButton("Aggiorna disponibilita'");
						btnAggiornaDisponibilita.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								libro = bobo.getCatalogo().aggiornaCopieLibro(libro, Integer.valueOf(quantitaLibri.getText()));
								ricercaLibro_copie.setText(String.valueOf(libro.getCopieResidue()));
							}
						});
						btnAggiornaDisponibilita.setVisible(false);
						
						panel_7.add(btnAggiornaDisponibilita);
						
						btnAggiungiNuovo = new JButton("Aggiungi nuovo");
						btnAggiungiNuovo.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								hideAllPanels();
								inserisciLibro.setVisible(true);
								
							}
						});
						btnAggiungiNuovo.setBounds(95, 33, 134, 23);
						ricercaLibro.add(btnAggiungiNuovo);
						btnAggiungiNuovo.setVisible(false);
						
						btnSchedaAcquisto.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								
								aggiornaTableAcquisti();
								
								// textAreaSchedaAcquisto.setText(bobo.mostraSchedaAcquisto());
								textField_totale.setText(bobo.mostraTotale());
								hideAllPanels();
								mostraSchedaAcquisto.setVisible(true);
								// textField_totaleScontato.setText(bobo.mostraTotaleScontato());
								//textField_totaleScontato.setText(textField_totale.getText());

								clearRicercaLibro();
							}
						});
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						bobo.aggiungiLibro(libro,
								Integer.parseInt(quantitaLibri.getText()));

						if(libro.getCopieResidue() < Integer.valueOf(quantitaLibri.getText())){
							JOptionPane.showMessageDialog(frame,
									"La disponibilita' del libro e'inferiore alla quantita' richiesta.",
									"Attenzione", JOptionPane.WARNING_MESSAGE);
						}
					}
				});

		inserisciLibro = new JPanel();
		frame.getContentPane().add(inserisciLibro, "name_8473984966570");
		inserisciLibro.setLayout(null);

		JLabel lblTitolo_2 = new JLabel("Titolo");
		lblTitolo_2.setBounds(10, 15, 46, 14);
		inserisciLibro.add(lblTitolo_2);

		JLabel lblAutore = new JLabel("Autore");
		lblAutore.setBounds(10, 40, 46, 14);
		inserisciLibro.add(lblAutore);

		JLabel lblIsbn_1 = new JLabel("ISBN");
		lblIsbn_1.setBounds(10, 65, 46, 14);
		inserisciLibro.add(lblIsbn_1);

		JLabel lblPrezzo = new JLabel("Prezzo");
		lblPrezzo.setBounds(10, 90, 46, 14);
		inserisciLibro.add(lblPrezzo);

		JTextPane textPane = new JTextPane();
		textPane.setBounds(66, 15, 308, 20);
		inserisciLibro.add(textPane);

		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBounds(66, 40, 308, 20);
		inserisciLibro.add(textPane_1);

		JTextPane textPane_2 = new JTextPane();
		textPane_2.setBounds(66, 65, 308, 20);
		inserisciLibro.add(textPane_2);

		JTextPane textPane_3 = new JTextPane();
		textPane_3.setBounds(66, 90, 308, 20);
		inserisciLibro.add(textPane_3);
		
		JTextPane textPane_4 = new JTextPane();
		textPane_4.setBounds(66, 115, 308, 20);
		inserisciLibro.add(textPane_4);
		
		JTextPane textPane_5 = new JTextPane();
		textPane_5.setBounds(66, 140, 308, 20);
		inserisciLibro.add(textPane_5);

		JButton btnAggiungi = new JButton("Aggiungi");
		btnAggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//inserisci nuovo libro
				String isbn = textPane_2.getText();
				String titolo = textPane.getText();
				String autore = textPane_1.getText();
				double prezzo = Double.valueOf(textPane_3.getText());
				Editore editore = bobo.getCatalogo().cercaEditore(textPane_4.getText());
				int copieResidue = Integer.valueOf(textPane_5.getText());
				
				bobo.getCatalogo().addLibro(new Libro(isbn,titolo,autore,editore,prezzo,copieResidue));
				
				textPane_2.setText("");
				textPane.setText("");
				textPane_1.setText("");
				textPane_3.setText("");
				textPane_4.setText("");
				textPane_5.setText("");
			}
		});
		btnAggiungi.setBounds(285, 180, 89, 23);
		inserisciLibro.add(btnAggiungi);
		
		
		
		JLabel lblEditore = new JLabel("Editore");
		lblEditore.setBounds(10, 115, 46, 14);
		inserisciLibro.add(lblEditore);
		
		JLabel lblCopie = new JLabel("Copie");
		lblCopie.setBounds(10, 140, 46, 14);
		inserisciLibro.add(lblCopie);
		
		//mostra scheda di acquisto
		frame.getContentPane().add(mostraSchedaAcquisto, "name_9059374424115");
		mostraSchedaAcquisto.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Cliente");
		lblNewLabel_1.setBounds(10, 11, 46, 14);
		mostraSchedaAcquisto.add(lblNewLabel_1);

		JLabel lblTotale = new JLabel("Totale");
		lblTotale.setBounds(10, 36, 46, 14);
		mostraSchedaAcquisto.add(lblTotale);

		JLabel lblDettagli = new JLabel("Dettagli");
		lblDettagli.setBounds(10, 61, 46, 14);
		mostraSchedaAcquisto.add(lblDettagli);

		JLabel lblTotaleSconto = new JLabel("Totale con sconto");
		lblTotaleSconto.setEnabled(false);
		lblTotaleSconto.setBounds(165, 36, 110, 14);
		mostraSchedaAcquisto.add(lblTotaleSconto);

		textField_totale = new JTextField();
		textField_totale.setBounds(66, 33, 86, 20);
		mostraSchedaAcquisto.add(textField_totale);
		textField_totale.setColumns(10);
		textField_totale.setEditable(false);

		textField_cliente = new JTextField();
		textField_cliente.setBounds(66, 8, 139, 20);
		mostraSchedaAcquisto.add(textField_cliente);
		textField_cliente.setColumns(10);
		textField_cliente.setEditable(false);

		textField_totaleScontato = new JTextField();
		textField_totaleScontato.setEditable(false);
		//textField_totaleScontato.setEnabled(false);
		textField_totaleScontato.setColumns(10);
		textField_totaleScontato.setBounds(273, 33, 86, 20);
		mostraSchedaAcquisto.add(textField_totaleScontato);

		JPanel panel = new JPanel();
		panel.setBounds(10, 86, 627, 207);
		mostraSchedaAcquisto.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		//Object rowData[][] = {};
		//Object columnNames[] = { "Titolo", "Quantita", "Copie residue","Prezzo", "Subtotale" };
		

		panel.add(scrollPane);
		scrollPane.setViewportView(tableSchedaAcquisto);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 306, 627, 41);
		mostraSchedaAcquisto.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnProcedi = new JButton("Procedi");
		btnProcedi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				hideAllPanels();
				ricercaCliente.setVisible(true);
				cliente = null;
				clearRicercaCliente();
			}
		});
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				clearRicercaLibro();
				clearMostraSchedaAcquisto();
				bobo.annullaOrdine();
				hideAllPanels();
				controlPanel.setVisible(true);
			}
		});
		panel_3.add(btnAnnulla);
		panel_3.add(btnProcedi);

		btnTermina = new JButton("Termina");
		btnTermina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bobo.terminaAcquisto();
				JOptionPane.showMessageDialog(frame,
						"Stampare la scheda d'acquisto e andare alla cassa.",
						"Acquisto terminato", JOptionPane.PLAIN_MESSAGE);
				hideAllPanels();
				controlPanel.setVisible(true);
				clearMostraSchedaAcquisto();
			}
		});
		btnTermina.setEnabled(false);
		panel_3.add(btnTermina);
		
		JLabel lblPromozione = new JLabel("Promozione");
		lblPromozione.setEnabled(false);
		lblPromozione.setBounds(371, 36, 84, 14);
		mostraSchedaAcquisto.add(lblPromozione);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setEnabled(false);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String selected = (String) comboBox.getSelectedItem();
				
				for(Promozione p: bobo.getPromozioni())
				{
					if(p.getNome().equals(selected)){
						textField_totaleScontato.setText(String.valueOf(bobo.getAcquistoInCorso().associaPromozione(p)));
					} 
				}
			}
		});
		comboBox.setBounds(449, 33, 188, 20);
		mostraSchedaAcquisto.add(comboBox);

		ricercaCliente = new JPanel();
		frame.getContentPane().add(ricercaCliente, "name_14035141283359");
		ricercaCliente.setLayout(null);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 11, 46, 14);
		ricercaCliente.add(lblNome);

		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setBounds(10, 36, 64, 14);
		ricercaCliente.add(lblCognome);

		textFieldNome = new JTextField();
		textFieldNome.setBounds(86, 8, 101, 20);
		ricercaCliente.add(textFieldNome);
		textFieldNome.setColumns(10);

		textFieldCognome = new JTextField();
		textFieldCognome.setBounds(86, 33, 101, 20);
		ricercaCliente.add(textFieldCognome);
		textFieldCognome.setColumns(10);

		btnAvanti = new JButton("Avanti");
		btnAvanti.setEnabled(false);
		btnAvanti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				hideAllPanels();
				mostraSchedaAcquisto.setVisible(true);
				btnTermina.setEnabled(true);
				btnProcedi.setEnabled(false);
				String datiCliente = cliente.getNome() + " "
						+ cliente.getCognome();
				textField_cliente.setText(datiCliente);
				bobo.setClienteAcquisto(cliente);
				
				clearRicercaCliente();
				
				comboBox.setEnabled(true);
				lblTotaleSconto.setEnabled(true);
				lblPromozione.setEnabled(true);
				
				comboBox.addItem("---");
				
				for(Promozione p: bobo.getPromozioni()){
					comboBox.addItem(p.getNome());
				}
			}
		});
		btnAvanti.setBounds(10, 309, 89, 23);
		ricercaCliente.add(btnAvanti);

		JButton btnInserisciNuovo = new JButton("Inserisci Nuovo");
		btnInserisciNuovo.setBounds(111, 85, 138, 25);
		ricercaCliente.add(btnInserisciNuovo);
		btnInserisciNuovo.setVisible(false);

		JButton btnCerca_1 = new JButton("Cerca");
		btnCerca_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				cliente = bobo.ricercaCliente(textFieldNome.getText(),
						textFieldCognome.getText());
				if (cliente != null) {
					textAreaCliente.setText(cliente.toString());
					btnAvanti.setEnabled(true);
				} else {
					textAreaCliente.setText("Cliente non trovato");
					btnAvanti.setEnabled(false);
					// crea nuovo cliente
					btnInserisciNuovo.setVisible(true);
				}

			}
		});
		btnCerca_1.setBounds(10, 86, 89, 23);
		ricercaCliente.add(btnCerca_1);

		JLabel lblRisultatoRicerca = new JLabel("Risultato ricerca");
		lblRisultatoRicerca.setBounds(10, 120, 109, 14);
		ricercaCliente.add(lblRisultatoRicerca);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 145, 364, 153);
		ricercaCliente.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1);
		scrollPane_1.setViewportView(textAreaCliente);

		storicoAcquisti = new JPanel();
		frame.getContentPane().add(storicoAcquisti, "name_1413224673023");
		storicoAcquisti.setLayout(null);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 36, 364, 314);
		storicoAcquisti.add(panel_4);
		panel_4.setLayout(new GridLayout(1, 0, 0, 0));

		JScrollPane scrollPane_3 = new JScrollPane();
		panel_4.add(scrollPane_3);

		textAreaStoricoAcquisti = new JTextArea();
		scrollPane_3.setViewportView(textAreaStoricoAcquisti);

		JLabel lblId = new JLabel("ID - Cliente - Data - Totale");
		lblId.setBounds(10, 11, 364, 14);
		storicoAcquisti.add(lblId);
		
		cercaPrenotazioni = new JPanel();
		frame.getContentPane().add(cercaPrenotazioni, "name_41332121056086");
		cercaPrenotazioni.setLayout(null);
		
		JLabel lblNome_1 = new JLabel("Editore");
		lblNome_1.setBounds(10, 11, 46, 14);
		cercaPrenotazioni.add(lblNome_1);
		
		textFieldCercaEditore = new JTextField();
		textFieldCercaEditore.setBounds(66, 8, 216, 20);
		cercaPrenotazioni.add(textFieldCercaEditore);
		textFieldCercaEditore.setColumns(10);
		
		JButton btnCreaOrdine = new JButton("Crea ordine");
		btnCreaOrdine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(prenotazioni == null){
					prenotazioni = new LinkedList<Prenotazione>();
				}
				
				bobo.getMagazzino().creaOrdine(prenotazioni,editore);
				aggiornaTableOrdine();
				hideAllPanels();
				mostraOrdine.setVisible(true);
				svuotaTablePrenotazioni();
				textFieldEditore.setText("");
			}
		});
		btnCreaOrdine.setEnabled(false);
		btnCreaOrdine.setBounds(10, 325, 122, 23);
		cercaPrenotazioni.add(btnCreaOrdine);
		
		JButton btnPrenotazioni = new JButton("Prenotazioni");
		btnPrenotazioni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Vector<String> row;
				
				DefaultTableModel dtm = (DefaultTableModel) tablePrenotazioni.getModel();
				
				int rows = dtm.getRowCount(); 
				for(int i = rows - 1; i >=0; i--)
				{
				   dtm.removeRow(i); 
				}
				
				System.out.println("editore");
				System.out.println(editore.getId());
				
				prenotazioni = bobo.getPrenotazioniEditore(editore);
				
				for(Prenotazione p: prenotazioni)
				{
					row = new Vector<String>();
					
					row.addElement(p.getTitoloLibro());
					row.addElement(p.getCliente().getNome() +" "+ p.getCliente().getCognome());
					row.addElement(String.valueOf(p.getQuantita()));
					
					dtm.addRow(row);
				}
			}
		});
		btnPrenotazioni.setEnabled(false);
		btnPrenotazioni.setBounds(162, 36, 142, 23);
		cercaPrenotazioni.add(btnPrenotazioni);
		
		JButton btnCercaEditore = new JButton("Cerca");
		btnCercaEditore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editore = bobo.getCatalogo().cercaEditore(textFieldCercaEditore.getText());
				
				if(editore != null){
					btnPrenotazioni.setEnabled(true);
					textFieldEditore.setText(editore.getNome());
					btnCreaOrdine.setEnabled(true);
				} else {
					textFieldEditore.setText("Editore non trovato");
				}
			}
		});
		btnCercaEditore.setBounds(10, 36, 142, 23);
		cercaPrenotazioni.add(btnCercaEditore);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 70, 627, 2);
		cercaPrenotazioni.add(separator_2);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(10, 83, 627, 231);
		cercaPrenotazioni.add(panel_5);
		panel_5.setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		panel_5.add(scrollPane_2);
		
		
		scrollPane_2.setViewportView(tablePrenotazioni);
		
		textFieldEditore = new JTextField();
		textFieldEditore.setEditable(false);
		textFieldEditore.setBounds(292, 8, 345, 20);
		cercaPrenotazioni.add(textFieldEditore);
		textFieldEditore.setColumns(10);
		
		
		
		mostraOrdine = new JPanel();
		frame.getContentPane().add(mostraOrdine, "name_46674620255956");
		mostraOrdine.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 11, 627, 230);
		mostraOrdine.add(panel_2);
		panel_2.setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPane_4 = new JScrollPane();
		panel_2.add(scrollPane_4);
		
		scrollPane_4.setViewportView(tableOrdine);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(10, 252, 627, 2);
		mostraOrdine.add(separator_3);
		
		JLabel lblQuantita_1 = new JLabel("Quantita'");
		lblQuantita_1.setBounds(410, 272, 54, 14);
		mostraOrdine.add(lblQuantita_1);
		
		textField = new JTextField();
		textField.setBounds(474, 269, 64, 20);
		mostraOrdine.add(textField);
		textField.setColumns(10);
		
		JButton btnAggiungi_1 = new JButton("Aggiungi");
		btnAggiungi_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					boolean stessoEditore = bobo.getMagazzino().aggiungiLibroAdOrdine(libro,Integer.valueOf(textField.getText()));
					if(!stessoEditore) {
						JOptionPane.showMessageDialog(frame,
								"Editore diverso da quello specificato.",
								"Attenzione", JOptionPane.ERROR_MESSAGE);
					}
				}
				catch (Exception ex)
				{
					System.out.println("Riempire tutti i campi richiesti!");
					JOptionPane.showMessageDialog(frame,
							"Riempire tutti i campi richiesti!",
							"Attenzione", JOptionPane.ERROR_MESSAGE);
				}
					
				libro = null;
				textField_isbn.setText("");
				textField_2.setText("");
				textField.setText("");
				
				aggiornaTableOrdine();
			}
		});
		btnAggiungi_1.setBounds(548, 268, 89, 23);
		mostraOrdine.add(btnAggiungi_1);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(10, 308, 627, 2);
		mostraOrdine.add(separator_4);
		
		JButton btnConfermaOrdine = new JButton("Conferma ordine");
		btnConfermaOrdine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hideAllPanels();
				
				// reset variables
				bobo.getMagazzino().confermaOrdine();
				
				prenotazioni = null;
				
				JOptionPane.showMessageDialog(frame,
						"Ordine creato con successo.",
						"Ordine creato", JOptionPane.PLAIN_MESSAGE);
			}
		});
		btnConfermaOrdine.setBounds(10, 327, 139, 23);
		mostraOrdine.add(btnConfermaOrdine);
		
		textField_isbn = new JTextField();
		textField_isbn.setColumns(10);
		textField_isbn.setBounds(49, 269, 64, 20);
		mostraOrdine.add(textField_isbn);
		
		JLabel lblIsbn_3 = new JLabel("ISBN");
		lblIsbn_3.setBounds(10, 272, 54, 14);
		mostraOrdine.add(lblIsbn_3);
		
		JLabel lblTitolo_1 = new JLabel("Titolo");
		lblTitolo_1.setBounds(221, 272, 46, 14);
		mostraOrdine.add(lblTitolo_1);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(277, 269, 123, 20);
		mostraOrdine.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnCerca_2 = new JButton("Cerca");
		btnCerca_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				libro = bobo.ricercaLibroISBN(textField_isbn.getText());
				if (libro != null) {
					textField_2.setText(libro.getTitolo());
				} else {
					textField_2.setText("Libro non trovato");
				}
			}
		});
		btnCerca_2.setBounds(122, 268, 89, 23);
		mostraOrdine.add(btnCerca_2);
		
		storicoOrdini = new JPanel();
		frame.getContentPane().add(storicoOrdini, "name_434734023432778");
		storicoOrdini.setLayout(null);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(12, 51, 621, 283);
		storicoOrdini.add(panel_6);
		panel_6.setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPane_5 = new JScrollPane();
		panel_6.add(scrollPane_5);
		
		textAreaStoricoOrdini = new JTextArea();
		scrollPane_5.setViewportView(textAreaStoricoOrdini);
		
		JLabel lblNewLabel_2 = new JLabel("Dettagli Ordini");
		lblNewLabel_2.setBounds(272, 13, 108, 16);
		storicoOrdini.add(lblNewLabel_2);
		
		panelCatalogo = new JPanel();
		frame.getContentPane().add(panelCatalogo, "name_8364013083761");
		panelCatalogo.setLayout(null);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBounds(10, 11, 609, 308);
		panelCatalogo.add(panel_8);
		panel_8.setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPane_6 = new JScrollPane();
		panel_8.add(scrollPane_6);
		
		//tableCatalogo = new JTable();
		scrollPane_6.setViewportView(tableCatalogo);
		
		// panel_1.add(textAreaCliente);

		// Crea la barra del menu
		menuBar = new JMenuBar();

		// primo menu - CATALOGO
		menuCatalogo = new JMenu("Catalogo");
		// gruppo di JMenuItems del menu Catalogo

		// ricerca
		itemRicercaLibro = new JMenuItem("Ricerca");
		itemRicercaLibro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hideAllPanels();
				clearRicercaLibro();
				ricercaLibro.setVisible(true);
				btnNewButton.setVisible(true);
				btnSchedaAcquisto.setVisible(true);
				btnAggiornaDisponibilita.setVisible(false);
			}
		});

		menuCatalogo.add(itemRicercaLibro);

		// inserisci
		itemInserisciLibro = new JMenuItem("Inserisci");
		itemInserisciLibro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				hideAllPanels();
				inserisciLibro.setVisible(true);
			}
		});
		menuCatalogo.add(itemInserisciLibro);

		menuBar.add(menuCatalogo);

		itemVisualizzaCat = new JMenuItem("Visualizza");
		itemVisualizzaCat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HashMap<String,Libro> hm = bobo.getCatalogo().getLibri();
				
				Vector<String> row;
				
				DefaultTableModel dtm = (DefaultTableModel) tableCatalogo.getModel();
				
				int rows = dtm.getRowCount(); 
				for(int i = rows - 1; i >=0; i--)
				{
				   dtm.removeRow(i); 
				}
				
				System.out.println(hm);
				
				for(Libro l: hm.values())
				{
					row = new Vector<String>();
					
					row.addElement(l.getISBN());
					row.addElement(l.getTitolo());
					row.addElement(l.getEditore().getNome());
					row.addElement(String.valueOf(l.getCopieResidue()));
					
					dtm.addRow(row);
				}
				
				hideAllPanels();
				panelCatalogo.setVisible(true);
			}
		});
		menuCatalogo.add(itemVisualizzaCat);

		// secondo menu - ACQUISTI
		menuAcquisti = new JMenu("Acquisti");

		// crea nuovo acquisto
		itemStoricoAcq = new JMenuItem("Storico");
		itemStoricoAcq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				hideAllPanels();
				storicoAcquisti.setVisible(true);

				textAreaStoricoAcquisti.setText(bobo.mostraStoricoAcquisti());
			}
		});
		menuAcquisti.add(itemStoricoAcq);

		menuBar.add(menuAcquisti);

		frame.setJMenuBar(menuBar);

		JMenu menuClienti = new JMenu("Clienti");
		menuBar.add(menuClienti);

		itemCreaCliente = new JMenuItem("Crea");
		menuClienti.add(itemCreaCliente);

		itemRicercaCliente = new JMenuItem("Ricerca");
		itemRicercaCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				hideAllPanels();
				ricercaCliente.setVisible(true);
				btnAvanti.setVisible(false);
				//btnInserisciNuovo.setVisible(false);
			}
		});
		menuClienti.add(itemRicercaCliente);

		itemVisualizzaCliente = new JMenuItem("Visualizza");
		menuClienti.add(itemVisualizzaCliente);
		
		//menu magaazzino
		JMenu menuMagazzino = new JMenu("Magazzino");
		menuBar.add(menuMagazzino);
		
		//crea ordine
		itemCreaOrdine = new JMenuItem("Crea ordine");
		itemCreaOrdine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hideAllPanels();
				cercaPrenotazioni.setVisible(true);
				btnCreaOrdine.setEnabled(false);
				textFieldCercaEditore.setText("");
				
				clearCercaPrenotazioni();
			}
		});
		menuMagazzino.add(itemCreaOrdine);
		
		//storico ordini
		itemStoricoOrdini = new JMenuItem("Storico Ordini");
		itemStoricoOrdini.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				hideAllPanels();
				storicoOrdini.setVisible(true);
				textAreaStoricoOrdini.setText(bobo.getMagazzino().mostraStoricoOrdini());
			}
		});
		menuMagazzino.add(itemStoricoOrdini);
		
		JMenuItem mntmAggiornaMagazzino = new JMenuItem("Aggiorna magazzino");
		mntmAggiornaMagazzino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				hideAllPanels();
				clearRicercaLibro();
				ricercaLibro.setVisible(true);
				btnNewButton.setVisible(false);
				btnSchedaAcquisto.setVisible(false);
				btnAggiornaDisponibilita.setVisible(true);
			}
		});
		menuMagazzino.add(mntmAggiornaMagazzino);
		
	}

	
	protected void aggiornaTableOrdine() {
		Vector<String> row;
		
		DefaultTableModel dtm = (DefaultTableModel) tableOrdine.getModel();
		
		int rows = dtm.getRowCount(); 
		for(int i = rows - 1; i >=0; i--)
		{
		   dtm.removeRow(i); 
		}
		
		Ordine oic = bobo.getMagazzino().getOrdineInCorso();
		
		System.out.println(oic);
		
		for(RigaOrdine ro: oic.getRigheOrdine())
		{
			row = new Vector<String>();
			
			row.addElement(ro.getLibro().getTitolo());
			row.addElement(String.valueOf(ro.getQuantita()));
			
			dtm.addRow(row);
		}
	}
	
	protected void aggiornaTableAcquisti() {
		Vector<String> row;
		
		DefaultTableModel dtm = (DefaultTableModel) tableSchedaAcquisto.getModel();
		
		int rows = dtm.getRowCount(); 
		for(int i = rows - 1; i >=0; i--)
		{
		   dtm.removeRow(i); 
		}
		
		for(RigaDiAcquisto rda: bobo.getAcquistoInCorso().getRigheDiAcquisto())
		{
			row = new Vector<String>();
			
			row.addElement(rda.getLibro().getTitolo());
			row.addElement(String.valueOf(rda.getQuantita()));
			row.addElement(String.valueOf(rda.getLibro().getCopieResidue()));
			row.addElement(String.valueOf(rda.getLibro().getPrezzo()));
			row.addElement(String.valueOf(rda.getSubtotale()));
			
			dtm.addRow(row);
		}
	}
	
	protected void aggiornaTablePrenotazioni() {
		Vector<String> row;
		
		DefaultTableModel dtm = (DefaultTableModel) tablePrenotazioni.getModel();
		
		int rows = dtm.getRowCount(); 
		for(int i = rows - 1; i >=0; i--)
		{
		   dtm.removeRow(i); 
		}
	}

	
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
		}
	}

	
	private void hideAllPanels() {
		ricercaCliente.setVisible(false);
		controlPanel.setVisible(false);
		ricercaLibro.setVisible(false);
		inserisciLibro.setVisible(false);
		mostraSchedaAcquisto.setVisible(false);
		storicoAcquisti.setVisible(false);
		cercaPrenotazioni.setVisible(false);
		mostraOrdine.setVisible(false);
		storicoOrdini.setVisible(false);
		panelCatalogo.setVisible(false);
	}
	
	
	public void svuotaTablePrenotazioni(){
		DefaultTableModel dtm = (DefaultTableModel) tablePrenotazioni.getModel();
		
		int rows = dtm.getRowCount(); 
		for(int i = rows - 1; i >=0; i--)
		{
		   dtm.removeRow(i); 
		}
	}
	
	public void clearRicercaLibro()
	{
		textISBN.setText("");
		ricercaLibro_titolo.setText("");
		ricercaLibro_autore.setText("");
		ricercaLibro_isbn.setText("");
		ricercaLibro_copie.setText("");
		ricercaLibro_prezzo.setText("");
		quantitaLibri.setText("1");
		
		btnAggiornaDisponibilita.setEnabled(false);

		// reset attribute libro?
	}

	public void clearMostraSchedaAcquisto()
	{
		textField_cliente.setText("");
		textField_totale.setText("");
		textField_totaleScontato.setText("");

		// reset table
		btnProcedi.setEnabled(true);
		btnTermina.setEnabled(false);
	}

	public void clearRicercaCliente()
	{
		textFieldNome.setText("");
		textFieldCognome.setText("");
		textAreaCliente.setText("");
		
		btnAvanti.setVisible(true);
		btnAvanti.setEnabled(false);
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
		aggiornaTablePrenotazioni();
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
}


