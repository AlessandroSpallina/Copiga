package Testing;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author manlio
 */

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;


public class ProvaEsercente {
    /*public static void main(String[] args) {
        final ProvaEsercente ejemplo = new ProvaEsercente();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ejemplo.createAndShowGUI();
            }
        });
    } */
	
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
        frame.getContentPane().setPreferredSize(new Dimension(800, 400));
        frame.pack();
        frame.setVisible(true);
    }
	
    public static class JTableModel extends AbstractTableModel {
        
	private static final long serialVersionUID = 1L;
	
        private static final String[] COLUMN_NAMES = new String[] {"File", "Cliente", "Visualizza File", "Accetta File", "Notifica Ritiro"};
	
        private static final Class<?>[] COLUMN_TYPES = new Class<?>[] {String.class, String.class, JButton.class,  JButton.class,  JButton.class};
	
        
	@Override public int getColumnCount() {
            return COLUMN_NAMES.length;
	}
        
        
        @Override public int getRowCount() {
            return 4;
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
                String rowIndex = null;
                JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(visualizzaFileButton), 
                        "\nBottone per Visualizzare il file inviato dal cliente\n\nButton clicked for row "+rowIndex);
            }
        };
        ActionListener alaccetta = new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(accettaFileButton), 
                                    "Bottone per notificare l'accettazione del file al cliente");
            }
        };
        ActionListener alnotifica = new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(notificaRitiroButton), 
                                    "Bottone per notificare il ritiro del materiale al cliente");
            }
        };
        
        
	@Override public Object getValueAt(final int rowIndex, final int columnIndex) {
            
            // TODO: implementare un metodo che generalizzi la creazione di questi bottoni
            visualizzaFileButton = new JButton(COLUMN_NAMES[2]);
            visualizzaFileButton.addActionListener(alvisualizza);
            
            accettaFileButton = new JButton(COLUMN_NAMES[3]);
            accettaFileButton.addActionListener(alaccetta);
            
            notificaRitiroButton = new JButton(COLUMN_NAMES[4]);
            notificaRitiroButton.addActionListener(alnotifica);
            
            // a seconda dell'indice della colonna fa qualcosa
            switch (columnIndex) {
		case 0: return "File numero "+rowIndex;
		case 1: return "Text for "+rowIndex;
		//case 2: // fall through
		case 2: return visualizzaFileButton;
                case 3: return accettaFileButton;
                case 4: return notificaRitiroButton;
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
                if (value instanceof JButton) {
                    ((JButton)value).doClick();
		}
            }
	}
    }
}
