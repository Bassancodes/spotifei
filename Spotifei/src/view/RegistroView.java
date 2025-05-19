package view;

import dao.UsuarioDAO;
import model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistroView extends JFrame {

    private JTextField txtNome;
    private JTextField txtEmail;
    private JPasswordField txtSenha;

    public RegistroView() {
        setTitle("Spotifei - Registro");
        setSize(350, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel lblNome = new JLabel("Nome:");
        txtNome = new JTextField(20);

        JLabel lblEmail = new JLabel("Email:");
        txtEmail = new JTextField(20);

        JLabel lblSenha = new JLabel("Senha:");
        txtSenha = new JPasswordField(20);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
            }
        });

        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
        panel.add(lblNome);
        panel.add(txtNome);
        panel.add(lblEmail);
        panel.add(txtEmail);
        panel.add(lblSenha);
        panel.add(txtSenha);
        panel.add(new JLabel());
        panel.add(btnRegistrar);

        add(panel);
        setVisible(true);
    }

    private void registrarUsuario() {
        String nome = txtNome.getText();
        String email = txtEmail.getText();
        String senha = new String(txtSenha.getPassword());

        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }

        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        try {
            // Verifica se email já existe
            if (usuarioDAO.verificarLogin(email, senha)) {
                JOptionPane.showMessageDialog(this, "Usuário já cadastrado!");
            } else {
                usuarioDAO.cadastrarUsuario(usuario);
                JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!");
                dispose();
                new LoginView();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar: " + e.getMessage());
        }
    }
}