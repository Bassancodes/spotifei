package controller;

import dao.UsuarioDAO;
import model.Usuario;

public class Main {
    public static void main(String[] args) {
        // Criando um novo usuário
        Usuario novo = new Usuario(0, "Henrique", "henrique@email.com", "1234");

        // Instanciando o DAO
        UsuarioDAO dao = new UsuarioDAO();

        // Cadastrando o usuário
        dao.cadastrarUsuario(novo);

        // Verificando login com os mesmos dados
        boolean logado = dao.verificarLogin("henrique@email.com", "1234");

        if (logado) {
            System.out.println("Login bem-sucedido!");
        } else {
            System.out.println("Email ou senha incorretos.");
        }
    }
}
