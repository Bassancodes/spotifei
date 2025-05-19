package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Musica;

public class MusicaDAO {
    public void cadastrarMusica(Musica m) {
        String sql = "INSERT INTO musica (titulo, duracao, genero, id_artista) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
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

    public int obterUltimoIdMusica() {
        String sql = "SELECT MAX(id) FROM musica";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao obter último ID da música: " + e.getMessage());
        }
        return -1;
    }
}
