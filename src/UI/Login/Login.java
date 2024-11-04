package UI.Login;
import UI.Login.Panels.ImagePanel;
import UI.Login.Panels.LoginPanel;
import javax.swing.*;
import java.awt.*;

public class Login extends JFrame{
    public Login(){
        setTitle("Authentication");
        ImageIcon img = new ImageIcon("src/Static/icons/logo.png");
        setIconImage(img.getImage());
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // Create two panels
        JPanel leftPanel = new ImagePanel();
        JPanel rightPanel = new LoginPanel();

        // Set layout for the main frame
        setLayout(new GridLayout(1, 2));

        // Add panels to the main frame
        add(leftPanel);
        add(rightPanel);

        setVisible(true);

    }
}
