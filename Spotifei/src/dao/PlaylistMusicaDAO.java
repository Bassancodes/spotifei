package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.PlaylistMusica;
import model.Musica;

public class PlaylistMusicaDAO {

    public void vincularMusica(PlaylistMusica pm) {
        String sql = "INSERT INTO playlist_musica (id_playlist, id_musica) VALUES (?, ?)";
        try (Connection conn = Conexao.conectar()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, pm.getIdPlaylist());
            stmt.setInt(2, pm.getIdMusica());
            stmt.executeUpdate();
            System.out.println("Música vinculada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao vincular música à playlist: " + e.getMessage());
        }
    }

    public List<Musica> listarMusicasDaPlaylist(int idPlaylist) {
        List<Musica> musicas = new ArrayList<>();
        String sql = """
            SELECT m.id, m.titulo, m.duracao, m.genero, m.id_artista
            FROM musica m
            JOIN playlist_musica pm ON m.id = pm.id_musica
            WHERE pm.id_playlist = ?
        """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPlaylist);
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
            System.out.println("Erro ao listar músicas da playlist: " + e.getMessage());
        }
        return musicas;
    }

    public void removerMusicaDaPlaylist(int idPlaylist, int idMusica) {
        String sql = "DELETE FROM playlist_musica WHERE id_playlist = ? AND id_musica = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPlaylist);
            stmt.setInt(2, idMusica);
            stmt.executeUpdate();
            System.out.println("Música removida da playlist com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao remover música da playlist: " + e.getMessage());
        }
    }
}
