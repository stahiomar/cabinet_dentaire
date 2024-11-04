package UI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CustomFontRenderer extends DefaultTableCellRenderer {
    Font font = new Font("SansSerif", Font.PLAIN, 16); // Adjust the font as needed

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component renderer = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        renderer.setFont(font);
        return renderer;
    }
}
