package dao;

import model.Usuario;
import java.sql.*;

public class UsuarioDAO {

    public void cadastrarUsuario(Usuario u) {
        String sql = "INSERT INTO usuario (id, nome, email, senha) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, u.getId());
            stmt.setString(2, u.getNome());
            stmt.setString(3, u.getEmail());
            stmt.setString(4, u.getSenha());
            stmt.executeUpdate();
            System.out.println("Usuário cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

    public boolean verificarLogin(String email, String senha) {
        String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";

        try (Connection conn = Conexao.conectar()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();

            return rs.next(); // se encontrou usuário, login é válido
        } catch (SQLException e) {
            System.out.println("Erro ao verificar login: " + e.getMessage());
            return false;
        }
    }

    public int obterIdUsuario(String email) {
        String sql = "SELECT id FROM usuario WHERE email = ?";

        try (Connection conn = Conexao.conectar()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao obter ID do usuário: " + e.getMessage());
        }

        return -1; // erro ou não encontrado
    }
}