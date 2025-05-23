package model;

public class Artista {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String nomeArtista;

    public Artista() {}

    public Artista(int id, String nome, String email, String senha, String nomeArtista) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.nomeArtista = nomeArtista;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getNomeArtista() { return nomeArtista; }
    public void setNomeArtista(String nomeArtista) { this.nomeArtista = nomeArtista; }
}