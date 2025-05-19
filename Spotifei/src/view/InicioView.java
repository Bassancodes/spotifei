// InicioView.java
package view;

import javax.swing.*;
import java.awt.*;

public class InicioView extends JFrame {
    public InicioView() {
        setTitle("Spotifei - Início");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton btnLogin = new JButton("Login");
        JButton btnRegistro = new JButton("Registrar");

        btnLogin.addActionListener(e -> {
            dispose();
            new LoginView();
        });

        btnRegistro.addActionListener(e -> {
            dispose();
            new RegistroView();
        });

        JPanel panel = new JPanel();
        panel.add(btnLogin);
        panel.add(btnRegistro);

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }
}
