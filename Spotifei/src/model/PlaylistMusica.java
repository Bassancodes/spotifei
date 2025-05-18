package model;

public class PlaylistMusica {
    private int idPlaylist;
    private int idMusica;

    public PlaylistMusica() {}

    public PlaylistMusica(int idPlaylist, int idMusica) {
        this.idPlaylist = idPlaylist;
        this.idMusica = idMusica;
    }

    // Getters e Setters

    public int getIdPlaylist() {
        return idPlaylist;
    }

    public void setIdPlaylist(int idPlaylist) {
        this.idPlaylist = idPlaylist;
    }

    public int getIdMusica() {
        return idMusica;
    }

    public void setIdMusica(int idMusica) {
        this.idMusica = idMusica;
    }

    @Override
    public String toString() {
        return "Playlist ID: " + idPlaylist + " | Música ID: " + idMusica;
    }
}
