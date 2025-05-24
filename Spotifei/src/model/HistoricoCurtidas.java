package model;

public class HistoricoCurtidas {
    private int id;
    private int idUsuario;
    private int idMusica;
    private String tituloMusica;

    public HistoricoCurtidas() {}

    public HistoricoCurtidas(int id, int idUsuario, int idMusica, String tituloMusica) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idMusica = idMusica;
        this.tituloMusica = tituloMusica;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public int getIdMusica() { return idMusica; }
    public void setIdMusica(int idMusica) { this.idMusica = idMusica; }

    public String getTituloMusica() { return tituloMusica; }
    public void setTituloMusica(String tituloMusica) { this.tituloMusica = tituloMusica; }
}