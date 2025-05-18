package controller;

import dao.Conexao;
import dao.UsuarioDAO;
import dao.PlaylistDAO;
import dao.MusicaDAO;
import model.Usuario;
import model.Playlist;
import model.Musica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            // 1. Inserir pessoa manualmente (sem o ID)
            Connection conn = Conexao.conectar();
            String insertPessoa = "INSERT INTO pessoa (nome, email, senha) VALUES (?, ?, ?)";
            PreparedStatement stmtPessoa = conn.prepareStatement(insertPessoa);
            stmtPessoa.setString(1, "Henrique");
            stmtPessoa.setString(2, "henrique@email.com");
            stmtPessoa.setString(3, "1234");
            stmtPessoa.executeUpdate();
            stmtPessoa.close();
            conn.close();
            System.out.println("Pessoa cadastrada!");

            // 2. Cadastra o usuario na tabela usuario (referente à herança)
            Usuario novo = new Usuario(4, "Henrique", "henrique@email.com", "1234"); // Altere para o ID correspondente na tabela pessoa
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.cadastrarUsuario(novo);

            // 3. Verificando login
            boolean logado = usuarioDAO.verificarLogin("henrique@email.com", "1234");
            if (logado) {
                System.out.println("Login bem-sucedido!");

                // 4. Criando e cadastrando playlist
                Playlist novaPlaylist = new Playlist(0, "Mix do Henrique", 2); // Altere o ID do usuário conforme necessário
                PlaylistDAO playlistDAO = new PlaylistDAO();
                playlistDAO.cadastrarPlaylist(novaPlaylist);

                // 5. Criando e cadastrando música
                Musica novaMusica = new Musica(0, "Oceano", 270, "MPB", 1); // id_artista = 1
                MusicaDAO musicaDAO = new MusicaDAO();
                musicaDAO.cadastrarMusica(novaMusica);
            } else {
                System.out.println("Email ou senha incorretos.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar pessoa: " + e.getMessage());
        }
    }
}