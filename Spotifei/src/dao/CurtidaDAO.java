package dao;

import model.HistoricoCurtidas;
import model.HistoricoDescurtida;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CurtidaDAO {

    public void curtirMusica(int idUsuario, int idMusica) {
        String sql = "INSERT INTO curtidas (id_usuario, id_musica) VALUES (?, ?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idMusica);
            stmt.executeUpdate();
            System.out.println("Música curtida com sucesso!");

            // Salvar no histórico de curtidas
            HistoricoCurtidasDAO historicoDAO = new HistoricoCurtidasDAO();
            historicoDAO.salvar(idUsuario, idMusica);

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

            
            HistoricoDescurtida h = new HistoricoDescurtida(idUsuario, idMusica, new Timestamp(System.currentTimeMillis()));
            new HistoricoDescurtidaDAO().salvar(h);

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
            return rs.next();

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