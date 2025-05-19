// RegistroView.java
package view;

import dao.UsuarioDAO;
import model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class RegistroView extends JFrame {

    public RegistroView() {
        setTitle("Registrar novo usuário");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel lblNome = new JLabel("Nome:");
        JTextField txtNome = new JTextField(20);

        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField(20);

        JLabel lblSenha = new JLabel("Senha:");
        JPasswordField txtSenha = new JPasswordField(20);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(e -> {
            String nome = txtNome.getText();
            String email = txtEmail.getText();
            String senha = new String(txtSenha.getPassword());

            try {
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                if (usuarioDAO.emailJaCadastrado(email)) {
                    JOptionPane.showMessageDialog(this, "Esse email já está cadastrado.");
                } else {
                    int novoId = usuarioDAO.proximoIdPessoa();
                    Usuario novoUsuario = new Usuario(novoId, nome, email, senha);
                    usuarioDAO.cadastrarUsuario(novoUsuario);
                    JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
                    dispose();
                    new LoginView();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao registrar: " + ex.getMessage());
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));
        panel.add(lblNome); panel.add(txtNome);
        panel.add(lblEmail); panel.add(txtEmail);
        panel.add(lblSenha); panel.add(txtSenha);
        panel.add(new JLabel()); panel.add(btnRegistrar);

        add(panel);
        setVisible(true);
    }
}
