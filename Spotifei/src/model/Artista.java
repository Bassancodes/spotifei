package model;

public class Artista extends Pessoa {

    private String nomeArtistico;

    public Artista() {
        super();
    }

    public Artista(int id, String nome, String email, String senha, String nomeArtistico) {
        super(id, nome, email, senha);
        this.nomeArtistico = nomeArtistico;
    }

    public String getNomeArtistico() {
        return nomeArtistico;
    }

    public void setNomeArtistico(String nomeArtistico) {
        this.nomeArtistico = nomeArtistico;
    }

    @Override
    public String toString() {
        return "Artista: " + getNome() + " | Nome Artístico: " + nomeArtistico + " | Email: " + getEmail();
    }
}
