package model;

public class Playlist {
    private int id;
    private String nome;
    private int idUsuario;

    public Playlist() {}

    public Playlist(int id, String nome, int idUsuario) {
        this.id = id;
        this.nome = nome;
        this.idUsuario = idUsuario;
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "Playlist: " + nome + " | ID Usuário: " + idUsuario;
    }
}
