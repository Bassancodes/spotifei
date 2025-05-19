package dao;

import model.Musica;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MusicaDAO {

    public void cadastrarMusica(Musica m) {
        String sql = "INSERT INTO musica (titulo, duracao, genero, id_artista) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, m.getTitulo());
            stmt.setInt(2, m.getDuracao());
            stmt.setString(3, m.getGenero());
            stmt.setInt(4, m.getIdArtista());

            stmt.executeUpdate();
            System.out.println("Música cadastrada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar música: " + e.getMessage());
        }
    }

    public List<Musica> buscarPorTermo(String termo) {
        List<Musica> musicas = new ArrayList<>();
        String sql = "SELECT * FROM musica WHERE LOWER(titulo) LIKE ? OR LOWER(genero) LIKE ?";

        try (Connection conn = Conexao.conectar()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            String termoBusca = "%" + termo.toLowerCase() + "%";
            stmt.setString(1, termoBusca);
            stmt.setString(2, termoBusca);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Musica m = new Musica();
                m.setId(rs.getInt("id"));
                m.setTitulo(rs.getString("titulo"));
                m.setDuracao(rs.getInt("duracao"));
                m.setGenero(rs.getString("genero"));
                m.setIdArtista(rs.getInt("id_artista"));
                musicas.add(m);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar músicas: " + e.getMessage());
        }

        return musicas;
    }
}