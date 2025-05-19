package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InicioView extends JFrame {

    public InicioView() {
        setTitle("Spotifei - Bem-vindo");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titulo = new JLabel("Bem-vindo ao Spotifei!");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        JButton btnLogin = new JButton("Login");
        JButton btnRegistrar = new JButton("Registrar");

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginView();
            }
        });

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new RegistroView();
            }
        });

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.add(titulo);
        panel.add(btnLogin);
        panel.add(btnRegistrar);

        add(panel);
        setVisible(true);
    }
}