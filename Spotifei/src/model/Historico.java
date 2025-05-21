package model;

import java.sql.Timestamp;

public class Historico {
    private int id;
    private int idUsuario;
    private String termo;
    private Timestamp dataHora;

    public Historico() {}

    public Historico(int idUsuario, String termo, Timestamp dataHora) {
        this.idUsuario = idUsuario;
        this.termo = termo;
        this.dataHora = dataHora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTermo() {
        return termo;
    }

    public void setTermo(String termo) {
        this.termo = termo;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }

    public void setDataHora(Timestamp dataHora) {
        this.dataHora = dataHora;
    }
}