/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package copigaes;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author manlio
 * @see this guide http://www.cordinc.com/blog/2010/01/jbuttons-in-a-jtable.html
 */
public class JTableButtonRenderer implements TableCellRenderer {
    
    // metodo che returna il bottone
    @Override public Component getTableCellRendererComponent(JTable table, 
            Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
        JButton button = (JButton) value;
        
        if(isSelected){
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        }
        else{
            button.setForeground(table.getForeground());
            button.setBackground(UIManager.getColor("Button.background"));
        }
        
        return button;
    }
}
