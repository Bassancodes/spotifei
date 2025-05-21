package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuView extends JFrame {
    private int idUsuario;

    public MenuView(int idUsuario) {
        this.idUsuario = idUsuario;

        setTitle("Spotifei - Menu Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 1));

        JButton btnBuscarMusicas = new JButton("Buscar músicas");
        JButton btnListarInfos = new JButton("Listar informações da música");
        JButton btnGerenciarPlaylists = new JButton("Gerenciar playlists");
        JButton btnHistorico = new JButton("Visualizar histórico");
        JButton btnVoltar = new JButton("Voltar");

        add(btnBuscarMusicas);
        add(btnListarInfos);
        add(btnGerenciarPlaylists);
        add(btnHistorico);
        add(btnVoltar);

        // AÇÕES DOS BOTÕES
        btnBuscarMusicas.addActionListener(e -> {
            dispose();
            new BuscarMusicaView(idUsuario);
        });
        
        
        btnListarInfos.addActionListener(e -> {
            dispose();
            new ListarMusicasView(idUsuario);
        });

        


        btnGerenciarPlaylists.addActionListener(e -> {
            dispose();
            new GerenciarPlaylistView(idUsuario);
        });

        btnHistorico.addActionListener(e -> {
            dispose();
         ///   new HistoricoView(idUsuario);
        });

        btnVoltar.addActionListener(e -> {
            dispose();
            new InicioView();
        });

        setVisible(true);
    }
}
