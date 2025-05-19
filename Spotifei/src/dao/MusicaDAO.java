package dao;

import java.sql.*;
import java.util.*;
import model.Musica;

public class MusicaDAO {

    public List<Musica> buscarPorTermo(String termo) {
        List<Musica> lista = new ArrayList<>();
        String sql = "SELECT * FROM musica WHERE titulo ILIKE ? OR genero ILIKE ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String termoComLike = "%" + termo + "%";
            stmt.setString(1, termoComLike);
            stmt.setString(2, termoComLike);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Musica m = new Musica();
                m.setId(rs.getInt("id"));
                m.setTitulo(rs.getString("titulo"));
                m.setDuracao(rs.getInt("duracao"));
                m.setGenero(rs.getString("genero"));
                m.setIdArtista(rs.getInt("id_artista"));
                lista.add(m);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar músicas: " + e.getMessage());
        }

        return lista;
    }
}