package dao;

import model.HistoricoCurtidas;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoricoCurtidasDAO {
    public void salvar(int idUsuario, int idMusica) {
        String sql = "INSERT INTO historico_curtidas (id_usuario, id_musica) VALUES (?, ?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idMusica);
            stmt.executeUpdate();
            System.out.println("Curtida salva no histórico.");
        } catch (SQLException e) {
            System.out.println("Erro ao salvar curtida: " + e.getMessage());
        }
    }

    public List<HistoricoCurtidas> listarPorUsuario(int idUsuario) {
        List<HistoricoCurtidas> lista = new ArrayList<>();
        String sql = """
            SELECT hc.id, hc.id_usuario, hc.id_musica, m.titulo
            FROM historico_curtidas hc
            JOIN musica m ON hc.id_musica = m.id
            WHERE hc.id_usuario = ?
        """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                HistoricoCurtidas hc = new HistoricoCurtidas();
                hc.setId(rs.getInt("id"));
                hc.setIdUsuario(rs.getInt("id_usuario"));
                hc.setIdMusica(rs.getInt("id_musica"));
                hc.setTituloMusica(rs.getString("titulo"));
                lista.add(hc);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar histórico de curtidas: " + e.getMessage());
        }
        return lista;
    }
}