package UI.dentistDashboard;

import Database.dao.*;
import UI.dentistDashboard.panels.ContentPanel;
import UI.dentistDashboard.panels.NavbarPanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DentistDashboard extends JFrame {
    PatientDao patientDao;
    DossierDao dossierDao;
    ConsultationDao consultationDao;
    FactureDao factureDao;
    public DentistDashboard() {
        patientDao = new PatientDao();
        dossierDao = new DossierDao();
        consultationDao = new ConsultationDao();
        factureDao = new FactureDao();
        setTitle("Dentist Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 700);

        ContentPanel contentPanel = new ContentPanel(patientDao, dossierDao, consultationDao, factureDao);
        NavbarPanel navbarPanel = new NavbarPanel(contentPanel);

        JPanel topPanel = new JPanel(new BorderLayout());

        ImageIcon icon = new ImageIcon("src/Static/icons/logo2.png");
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setBounds(0, 0, 20, 20);

        JLabel titleLabel = new JLabel("      Dental desk");
        titleLabel.setFont(new Font("Monospaced", Font.ITALIC, 30));

        topPanel.add(iconLabel, BorderLayout.WEST);
        topPanel.add(titleLabel, BorderLayout.CENTER);

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
        SwingUtilities.invokeLater(() -> new DentistDashboard());
    }
}
