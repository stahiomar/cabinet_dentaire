package UI.Login.Panels;
import Static.Themes;
import UI.dentistDashboard.DentistDashboard;
import UI.secretaireDashboard.SecretaireDashboard;
import models.Dentiste;
import models.Secretaire;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private Dentiste dentiste = new Dentiste();
    private Secretaire secretaire = new Secretaire();

    public LoginPanel() {
        placeComponents();
    }

    private void placeComponents() {
        setLayout(new BorderLayout());
        // Top Panel for the login text
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel loginText = new JLabel("<html><font color='blue'>Login </font><font color='black'>your account</font></html>");
        loginText.setFont(Themes.DEFAULTFONT);
        topPanel.add(loginText);
        topPanel.setBackground(Color.WHITE);

        add(topPanel, BorderLayout.NORTH);
        // Center Panel for the form
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(null);
        centerPanel.setBackground(Color.WHITE);


        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(35, 40, 80, 25);
        centerPanel.add(userLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(125, 40, 165, 25);
        centerPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(35, 100, 80, 25);
        centerPanel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(125, 100, 165, 25);
        centerPanel.add(passwordField);

        JButton loginButton = new JButton("<html><font color='white'>Login</font></html>");
        loginButton.setBounds(155, 180, 80, 25);
        loginButton.setBackground(Themes.BUTTONCOLOR);
        centerPanel.add(loginButton);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            char[] password = passwordField.getPassword();

            if (dentiste.getUsername().equals(username) && dentiste.getPassword().equals(new String(password))) {
                new DentistDashboard();
            } else if (secretaire.getUsername().equals(username) && secretaire.getPassword().
                    equals(new String(password))) {
                new SecretaireDashboard();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password. Try again.");
            }

            usernameField.setText("");
            passwordField.setText("");
        });

        add(centerPanel, BorderLayout.CENTER);
    }
}
