package UI.dentistDashboard.panels;

import Static.Themes;
import UI.dentistDashboard.panels.ContentPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class NavbarPanel extends JPanel {
    private ContentPanel contentPanel;

    public NavbarPanel(ContentPanel contentPanel) {
        this.contentPanel = contentPanel;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Themes.BUTTONCOLOR);

        // Increase vertical spacing
        add(Box.createVerticalStrut(20));

        JButton profileButton = new JButton("<html><font color='white'>Profile</font></html>", resizeIcon(new ImageIcon("src/Static/icons/secretary.png"), 40, 40));
        profileButton.setPreferredSize(new Dimension(150, 40));
        profileButton.setFont(Themes.DEFAULTFONT);
        JButton patientButton = new JButton("<html><font color='white'>Patients</font></html>", new ImageIcon("src/Static/icons/patient.png"));
        patientButton.setPreferredSize(new Dimension(150, 40));
        patientButton.setFont(Themes.DEFAULTFONT);
        JButton caisseButton = new JButton("<html><font color='white'>Caisse</font></html>", resizeIcon(new ImageIcon("src/Static/icons/caisse.png"), 40, 40));
        caisseButton.setPreferredSize(new Dimension(150, 40));
        caisseButton.setFont(Themes.DEFAULTFONT);
        JButton disconnectButton = new JButton("<html><font color='white'>Disconnect</font></html>", resizeIcon(new ImageIcon("src/Static/icons/disconnect.png"), 30, 30));
        disconnectButton.setPreferredSize(new Dimension(150, 40));
        disconnectButton.setFont(Themes.DEFAULTFONT);
        profileButton.setContentAreaFilled(false);
        patientButton.setContentAreaFilled(false);
        caisseButton.setContentAreaFilled(false);
        disconnectButton.setContentAreaFilled(false);
        add(profileButton);
        add(Box.createVerticalStrut(10));
        add(patientButton);
        add(Box.createVerticalStrut(10));
        add(caisseButton);
        add(Box.createVerticalStrut(getMaximumSize().height));
        add(disconnectButton);

        // Set action listeners for navbar buttons
        profileButton.addActionListener(e -> contentPanel.profileContent());

        patientButton.addActionListener(e -> contentPanel.patientContent());

        caisseButton.addActionListener(e -> contentPanel.caisseContent());

        disconnectButton.addActionListener(e -> {
            // Exit the application
            System.exit(0);
        });
    }

    private Icon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }
}
