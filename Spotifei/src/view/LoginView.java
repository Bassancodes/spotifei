package view;

import dao.UsuarioDAO;
import model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginView extends JFrame {

    private JTextField txtEmail;
    private JPasswordField txtSenha;

    public LoginView() {
        setTitle("Spotifei - Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel lblEmail = new JLabel("Email:");
        JLabel lblSenha = new JLabel("Senha:");
        txtEmail = new JTextField(20);
        txtSenha = new JPasswordField(20);
        JButton btnLogin = new JButton("Entrar");

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarLogin();
            }
        });

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(lblEmail);
        panel.add(txtEmail);
        panel.add(lblSenha);
        panel.add(txtSenha);
        panel.add(new JLabel());
        panel.add(btnLogin);

        add(panel);
        setVisible(true);
    }

    private void realizarLogin() {
        String email = txtEmail.getText();
        String senha = new String(txtSenha.getPassword());

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        try {
            if (usuarioDAO.verificarLogin(email, senha)) {
                JOptionPane.showMessageDialog(this, "Login realizado com sucesso!");
                int idUsuario = usuarioDAO.obterIdUsuario(email);
                dispose();
                new MenuView(idUsuario); // Passando ID para a próxima tela
            } else {
                JOptionPane.showMessageDialog(this, "Email ou senha incorretos.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro inesperado: " + ex.getMessage());
        }
    }
}