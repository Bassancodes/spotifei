package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Playlist;

public class PlaylistDAO {

    public void criarPlaylist(String nome, int idUsuario) {
        String sql = "INSERT INTO playlist (nome, id_usuario) VALUES (?, ?)";
        try (Connection conn = Conexao.conectar()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setInt(2, idUsuario);
            stmt.executeUpdate();
            System.out.println("Playlist criada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao criar playlist: " + e.getMessage());
        }
    }

    public List<Playlist> listarPlaylistsPorUsuario(int idUsuario) {
        List<Playlist> playlists = new ArrayList<>();
        String sql = "SELECT * FROM playlist WHERE id_usuario = ?";
        try (Connection conn = Conexao.conectar()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Playlist p = new Playlist();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setIdUsuario(rs.getInt("id_usuario"));
                playlists.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar playlists: " + e.getMessage());
        }
        return playlists;
    }
}