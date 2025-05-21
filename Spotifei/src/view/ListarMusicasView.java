package view;

import dao.MusicaDAO;
import dao.CurtidaDAO;
import model.Musica;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ListarMusicasView extends JFrame {

    private int idUsuario;
    private JTextArea areaMusicas;

    public ListarMusicasView(int idUsuario) {
        this.idUsuario = idUsuario;

        setTitle("Spotifei - Listar Músicas");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Músicas Cadastradas", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);

        areaMusicas = new JTextArea();
        areaMusicas.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaMusicas);
        add(scroll, BorderLayout.CENTER);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> {
            dispose();
            new MenuView(idUsuario);
        });
        add(btnVoltar, BorderLayout.SOUTH);

        carregarMusicas();
        setVisible(true);
    }

    private void carregarMusicas() {
        MusicaDAO musicaDAO = new MusicaDAO();
        CurtidaDAO curtidaDAO = new CurtidaDAO();
        List<Musica> musicas = musicaDAO.buscarPorTermo(""); // "" traz todas

        StringBuilder sb = new StringBuilder();
        for (Musica m : musicas) {
            int curtidas = curtidaDAO.contarCurtidas(m.getId());
            sb.append("Título: ").append(m.getTitulo())
              .append(" | Gênero: ").append(m.getGenero())
              .append(" | Duração: ").append(m.getDuracao()).append("s")
              .append(" | Curtidas: ").append(curtidas)
              .append("\n");
        }

        if (musicas.isEmpty()) {
            sb.append("Nenhuma música cadastrada.");
        }

        areaMusicas.setText(sb.toString());
    }
}