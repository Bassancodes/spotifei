package controller;

import dao.UsuarioDAO;
import dao.PlaylistDAO;
import model.Usuario;
import model.Playlist;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import dao.Conexao;

public class Main {
    public static void main(String[] args) {
        try {
            // 1. Inserir pessoa manualmente (pois usuario herda dela)
            Connection conn = Conexao.conectar();
            String insertPessoa = "INSERT INTO pessoa (id, nome, email, senha) VALUES (?, ?, ?, ?)";
            PreparedStatement stmtPessoa = conn.prepareStatement(insertPessoa);
            stmtPessoa.setInt(1, 1);
            stmtPessoa.setString(2, "Henrique");
            stmtPessoa.setString(3, "henrique@email.com");
            stmtPessoa.setString(4, "1234");
            stmtPessoa.executeUpdate();
            stmtPessoa.close();
            conn.close();
            System.out.println("Pessoa cadastrada!");

            // 2. Cadastra o usuario na tabela usuario (referente à herança)
            Usuario novo = new Usuario(1, "Henrique", "henrique@email.com", "1234");
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.cadastrarUsuario(novo);

            // 3. Verificando login
            boolean logado = usuarioDAO.verificarLogin("henrique@email.com", "1234");

            if (logado) {
                System.out.println("Login bem-sucedido!");

                // 4. Criando e cadastrando playlist
                Playlist novaPlaylist = new Playlist(0, "Mix do Henrique", 1);
                PlaylistDAO playlistDAO = new PlaylistDAO();
                playlistDAO.cadastrarPlaylist(novaPlaylist);
            } else {
                System.out.println("Email ou senha incorretos.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar pessoa: " + e.getMessage());
        }
    }
}