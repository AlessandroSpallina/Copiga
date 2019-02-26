package copigaes;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author manlio
 */
public class ProvaQuery {
    private List<Map<String, String>> listaJobs = new ArrayList<Map<String, String>>();
    private Map<String, String> job = new HashMap<String, String>();
    private Login login;
    
    // test per (actionlistener)alvisualizza
    public static int provaRowIndex;// questo parametro viene settato nell'ulti-
                                    // -ma funzione, prendendo il valore di row
    
    // avendo creato la frame "a mano", è necessario specificare la visibilità
    // iniziale con un booleano settato su "false".
    // più in basso viene definito il metodo che viene richiamato da EsercenteGUI
    // per settare la visibilità a true
    private boolean visibilita = false;
    
    public ProvaQuery(Login login, List<Map<String, String>> listaJobs){
        this.login = login;
        this.listaJobs = listaJobs;
        
        // console view - debug only
        System.out.println(listaJobs.toString());
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
    private void createAndShowGUI() {
        JFrame frame = new JFrame("Prova Esercente");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JTable table = new JTable(new JTableModel()); 
        JScrollPane scrollPane = new JScrollPane(table);
	table.setFillsViewportHeight(true);	
		
	TableCellRenderer buttonRenderer = new JTableButtonRenderer();
	table.getColumn("Visualizza File").setCellRenderer(buttonRenderer);
	table.getColumn("Accetta File").setCellRenderer(buttonRenderer);
        table.getColumn("Notifica Ritiro").setCellRenderer(buttonRenderer);
        table.addMouseListener(new JTableButtonMouseListener(table));
        
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().setPreferredSize(new Dimension(1280, 720));
        frame.pack();
        frame.setVisible(visibilita);
    }

    public void setVisible(boolean v) {
        visibilita = v;
    }
	
    public class JTableModel extends AbstractTableModel {
        
	private static final long serialVersionUID = 1L;
	
        private final String[] COLUMN_NAMES = new String[] {"Data", "Cliente", "Tipo Carta", "Rilegatura", "Fronte/Retro", "Colore", "Prezzo", "Pagine per lato", "Visualizza File", "Accetta File", "Notifica Ritiro"};
	
        private final Class<?>[] COLUMN_TYPES = new Class<?>[] {String.class, String.class, String.class, String.class, String.class, String.class, String.class, JButton.class,  JButton.class,  JButton.class};
	
        
	@Override public int getColumnCount() {
            return COLUMN_NAMES.length;
	}
        
        
        @Override public int getRowCount() {
            return listaJobs.size();
	}
	
        
	@Override public String getColumnName(int columnIndex) { 
            return COLUMN_NAMES[columnIndex];
	}
	
        
	@Override public Class<?> getColumnClass(int columnIndex) {
        	return COLUMN_TYPES[columnIndex];
	}
        
        JButton visualizzaFileButton;
        JButton accettaFileButton;
        JButton notificaRitiroButton;
        
        
        ActionListener alvisualizza = new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                URI uri;
                try {
                    uri = new URI(job.get("filelink"));
                    OpenURL compurl = new OpenURL(uri);
                } catch (URISyntaxException ex) {
                    Logger.getLogger(LoginGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        ActionListener alaccetta = new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                String accettato = (String) job.get("accepted");
                if(accettato.equals("yes")){
                JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(accettaFileButton), 
                                    "Task già accettato.");
                } else {
                    login.accettaTask(job.get("id"));
                    JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(accettaFileButton), 
                                    "Task accettato.");
                }
            }
        };
        ActionListener alnotifica = new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(notificaRitiroButton), 
                                    "Bottone per notificare il ritiro");
            }
            
        };
        
        
	@Override public Object getValueAt(final int rowIndex, final int columnIndex) {
            
            // TODO: implementare un metodo che generalizzi la creazione di questi bottoni
            visualizzaFileButton = new JButton(COLUMN_NAMES[8]);
            visualizzaFileButton.addActionListener(alvisualizza);
            
            accettaFileButton = new JButton(COLUMN_NAMES[9]);
            accettaFileButton.addActionListener(alaccetta);
            
            notificaRitiroButton = new JButton(COLUMN_NAMES[10]);
            notificaRitiroButton.addActionListener(alnotifica);
            
            job = listaJobs.get(rowIndex);
            
            // a seconda dell'indice della colonna fa qualcosa
            switch (columnIndex) {
		case 0: return job.get("time"); 
		case 1: return job.get("customer");
		case 2: return job.get("paper");
                case 3: return job.get("bookbinding");
                case 4: return job.get("bothsides");
                case 5: return job.get("colour");
                case 6: return job.get("price") + " €";
                case 7: return job.get("pagesforside");
		case 8: return visualizzaFileButton;
                case 9: return accettaFileButton;
                case 10: return notificaRitiroButton;
		default: return "Error";
            }
	}	
    }

    private static class JTableButtonRenderer implements TableCellRenderer {		
        @Override public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JButton button = (JButton)value;
            if (isSelected) {
                button.setForeground(table.getSelectionForeground());
                button.setBackground(table.getSelectionBackground());
            } else {
                button.setForeground(table.getForeground());
                button.setBackground(UIManager.getColor("Button.background"));
            }
            return button;	
        }
    }
	
    private static class JTableButtonMouseListener extends MouseAdapter {
        private final JTable table;
        
        public JTableButtonMouseListener(JTable table) {
            this.table = table;
	}
        
        public void mouseClicked(MouseEvent e) {
            int column = table.getColumnModel().getColumnIndexAtX(e.getX());
            int row = e.getY()/table.getRowHeight(); 
            if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
                Object value = table.getValueAt(row, column);
                provaRowIndex = row;
                if (value instanceof JButton) {
                    ((JButton)value).doClick();
		}
            }
	}
    }
}
