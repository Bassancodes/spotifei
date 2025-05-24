
package model;
import java.sql.Timestamp;

public class HistoricoDescurtida {
    private int id;
    private int idUsuario;
    private int idMusica;
    private Timestamp dataHora;
    private String tituloMusica;
    public HistoricoDescurtida() {}

    public HistoricoDescurtida(int idUsuario, int idMusica, Timestamp dataHora) {
        this.idUsuario = idUsuario;
        this.idMusica = idMusica;
        this.dataHora = dataHora;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    public int getIdMusica() { return idMusica; }
    public void setIdMusica(int idMusica) { this.idMusica = idMusica; }
    public Timestamp getDataHora() { return dataHora; }
    public void setDataHora(Timestamp dataHora) { this.dataHora = dataHora; }
    
    public String getTituloMusica() { return tituloMusica; }
    public void setTituloMusica(String tituloMusica) { this.tituloMusica = tituloMusica; }

}