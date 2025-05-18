package model;

public class Usuario extends Pessoa {

    public Usuario() {
        super();
    }

    public Usuario(int id, String nome, String email, String senha) {
        super(id, nome, email, senha);
    }

    @Override
    public String toString() {
        return "Usuário: " + getNome() + " | Email: " + getEmail();
    }
}
