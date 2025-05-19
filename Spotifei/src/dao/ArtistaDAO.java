package dao;

import java.sql.*;
import model.Artista;

public class ArtistaDAO {

    public void cadastrarArtista(Artista a) {
        String sql = "INSERT INTO artista (id, nome, email, senha, nome_artista) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.conectar()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, a.getId());
            stmt.setString(2, a.getNome());
            stmt.setString(3, a.getEmail());
            stmt.setString(4, a.getSenha());
            stmt.setString(5, a.getNomeArtista());
            stmt.executeUpdate();
            System.out.println("Artista cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar artista: " + e.getMessage());
        }
    }
}