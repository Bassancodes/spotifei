package dao;

import java.sql.*;
import model.Playlist;

public class PlaylistDAO {

    public void cadastrarPlaylist(Playlist p) {
        String sql = "INSERT INTO playlist (nome, id_usuario) VALUES (?, ?)";
        try (Connection conn = Conexao.conectar()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, p.getNome());
            stmt.setInt(2, p.getIdUsuario());
            stmt.executeUpdate();
            System.out.println("Playlist cadastrada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar playlist: " + e.getMessage());
        }
    }
}