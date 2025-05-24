package dao;

import model.Historico;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import dao.HistoricoCurtidasDAO;
public class HistoricoDAO {

    public void salvarHistorico(Historico h) {
        String sql = "INSERT INTO historico (id_usuario, termo, data_hora) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.conectar()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, h.getIdUsuario());
            stmt.setString(2, h.getTermo());
            stmt.setTimestamp(3, h.getDataHora());
            stmt.executeUpdate();
            System.out.println("Histórico salvo com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao salvar histórico: " + e.getMessage());
        }
    }

    public List<Historico> buscarHistoricoPorUsuario(int idUsuario) {
        List<Historico> lista = new ArrayList<>();
        String sql = "SELECT * FROM historico WHERE id_usuario = ? ORDER BY data_hora DESC";
        try (Connection conn = Conexao.conectar()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Historico h = new Historico();
                h.setId(rs.getInt("id"));
                h.setIdUsuario(rs.getInt("id_usuario"));
                h.setTermo(rs.getString("termo"));
                h.setDataHora(rs.getTimestamp("data_hora"));
                lista.add(h);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar histórico: " + e.getMessage());
        }
        return lista;
    }
}