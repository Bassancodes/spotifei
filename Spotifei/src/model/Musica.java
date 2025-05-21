package model;

public class Musica {
    private int id;
    private String titulo;
    private int duracao;
    private String genero;
    private int idArtista;
    private String nomeArtista; // Novo campo

    public Musica() {}

    public Musica(int id, String titulo, int duracao, String genero, int idArtista, String nomeArtista) {
        this.id = id;
        this.titulo = titulo;
        this.duracao = duracao;
        this.genero = genero;
        this.idArtista = idArtista;
        this.nomeArtista = nomeArtista;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public int getDuracao() { return duracao; }
    public void setDuracao(int duracao) { this.duracao = duracao; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public int getIdArtista() { return idArtista; }
    public void setIdArtista(int idArtista) { this.idArtista = idArtista; }

    public String getNomeArtista() { return nomeArtista; }
    public void setNomeArtista(String nomeArtista) { this.nomeArtista = nomeArtista; }

    @Override
    public String toString() {
        return titulo + " - " + nomeArtista + " (" + genero + ")";
    }
}