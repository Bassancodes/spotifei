package model;

public class Musica {
    private int id;
    private String titulo;
    private int duracao;
    private String genero;
    private int idArtista;

    public Musica() {}

    public Musica(int id, String titulo, int duracao, String genero, int idArtista) {
        this.id = id;
        this.titulo = titulo;
        this.duracao = duracao;
        this.genero = genero;
        this.idArtista = idArtista;
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
}