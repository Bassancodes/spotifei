package controller;

import dao.Conexao;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection conexao = Conexao.getConexao();
        if (conexao != null) {
            System.out.println("Conexão bem-sucedida!");
        } else {
            System.out.println("Falha na conexão.");
        }
    }
}
