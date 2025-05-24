package dao;
import model.HistoricoDescurtida;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoricoDescurtidaDAO {
    public void salvar(HistoricoDescurtida h) {
        String sql = "INSERT INTO historico_descurtidas (id_usuario, id_musica, data_hora) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, h.getIdUsuario());
            stmt.setInt(2, h.getIdMusica());
            stmt.setTimestamp(3, h.getDataHora());
            stmt.executeUpdate();
            System.out.println("Descurtida registrada no histórico.");
        } catch (SQLException e) {
            System.out.println("Erro ao registrar descurtida: " + e.getMessage());
        }
    }
    public List<HistoricoDescurtida> listarPorUsuario(int idUsuario) {
    List<HistoricoDescurtida> lista = new ArrayList<>();
    String sql = """
        SELECT hd.id, hd.id_usuario, hd.id_musica, hd.data_hora, m.titulo
        FROM historico_descurtidas hd
        JOIN musica m ON hd.id_musica = m.id
        WHERE hd.id_usuario = ?
        ORDER BY hd.data_hora DESC
    """;
    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idUsuario);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            HistoricoDescurtida h = new HistoricoDescurtida();
            h.setId(rs.getInt("id"));
            h.setIdUsuario(rs.getInt("id_usuario"));
            h.setIdMusica(rs.getInt("id_musica"));
            h.setDataHora(rs.getTimestamp("data_hora"));
            h.setTituloMusica(rs.getString("titulo")); // <-- novo
            lista.add(h);
        }

    } catch (SQLException e) {
        System.out.println("Erro ao buscar descurtidas: " + e.getMessage());
    }
    return lista;
}
}