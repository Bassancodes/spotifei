package dao;
import model.Usuario;
import java.sql.*;

public class UsuarioDAO {

   public void cadastrarUsuario(Usuario u) {
    String sql = "INSERT INTO usuario (nome, email, senha) VALUES (?, ?, ?)";
    try (Connection conn = Conexao.conectar()) {
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, u.getNome());
        stmt.setString(2, u.getEmail());
        stmt.setString(3, u.getSenha());
        stmt.executeUpdate();
        System.out.println("Usuário cadastrado com sucesso!");
    } catch (SQLException e) {
        System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
    }
 }


    public boolean verificarLogin(String email, String senha) {
        String sql = "SELECT * FROM pessoa p JOIN usuario u ON p.id = u.id WHERE p.email = ? AND p.senha = ?";
        try (Connection conn = Conexao.conectar()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Erro ao verificar login: " + e.getMessage());
            return false;
        }
    }

    public int obterIdUsuario(String email) {
        String sql = "SELECT id FROM pessoa WHERE email = ?";
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
        return -1;
    }
}