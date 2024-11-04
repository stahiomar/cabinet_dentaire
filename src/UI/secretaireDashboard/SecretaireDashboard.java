package UI.secretaireDashboard;

import Database.dao.DossierDao;
import Database.dao.FactureDao;
import Database.dao.PatientDao;
import UI.secretaireDashboard.panels.ContentPanelS;
import UI.secretaireDashboard.panels.NavbarPanelS;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SecretaireDashboard extends JFrame {
    PatientDao patientDao;
    DossierDao dossierDao;
    FactureDao factureDao;
    public SecretaireDashboard() {
        patientDao = new PatientDao();
        dossierDao = new DossierDao();
        factureDao = new FactureDao();
        setTitle("Dentist Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 700);

        ContentPanelS contentPanel = new ContentPanelS(patientDao, dossierDao, factureDao);
        NavbarPanelS navbarPanel = new NavbarPanelS(contentPanel);

        JPanel topPanel = new JPanel(new BorderLayout());

        ImageIcon icon = new ImageIcon("src/Static/icons/logo2.png");
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setBounds(0, 0, 20, 20);

        // Add text
        JLabel titleLabel = new JLabel("      Dental desk");
        titleLabel.setFont(new Font("Monospaced", Font.ITALIC, 30));

        topPanel.add(iconLabel, BorderLayout.WEST);
        topPanel.add(titleLabel, BorderLayout.CENTER);

        // Add shadowed separator
        JSeparator separator = createShadowedSeparator();
        topPanel.add(separator, BorderLayout.SOUTH);

        // Create the main layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(navbarPanel, BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        add(mainPanel);

        setLocationRelativeTo(null);
        setVisible(true);

    }

    private JSeparator createShadowedSeparator() {
        JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
        Border border = separator.getBorder();
        Border margin = new EmptyBorder(5, 0, 0, 0);
        separator.setBorder(new CompoundBorder(border, margin));
        return separator;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UI.secretaireDashboard.SecretaireDashboard());
    }
}
