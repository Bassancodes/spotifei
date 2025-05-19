package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CurtidaDAO {

    public void curtirMusica(int idUsuario, int idMusica) {
        String sql = "INSERT INTO curtidas (id_usuario, id_musica) VALUES (?, ?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idMusica);
            stmt.executeUpdate();
            System.out.println("Música curtida com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao curtir música: " + e.getMessage());
        }
    }

    public void descurtirMusica(int idUsuario, int idMusica) {
        String sql = "DELETE FROM curtidas WHERE id_usuario = ? AND id_musica = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idMusica);
            stmt.executeUpdate();
            System.out.println("Música descurtida com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao descurtir música: " + e.getMessage());
        }
    }

    public boolean jaCurtiu(int idUsuario, int idMusica) {
        String sql = "SELECT 1 FROM curtidas WHERE id_usuario = ? AND id_musica = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idMusica);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // true se encontrou uma curtida

        } catch (SQLException e) {
            System.out.println("Erro ao verificar curtida: " + e.getMessage());
            return false;
        }
    }

    public int contarCurtidas(int idMusica) {
        String sql = "SELECT COUNT(*) FROM curtidas WHERE id_musica = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idMusica);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao contar curtidas: " + e.getMessage());
        }
        return 0;
    }
}