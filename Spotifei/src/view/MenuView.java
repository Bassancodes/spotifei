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
        JButton btnCurtirDescurtir = new JButton("Curtir / Descurtir músicas");
        JButton btnGerenciarPlaylists = new JButton("Gerenciar playlists");
        JButton btnHistorico = new JButton("Visualizar histórico");
        JButton btnVoltar = new JButton("Voltar");

        add(btnBuscarMusicas);
        add(btnListarInfos);
        add(btnCurtirDescurtir);
        add(btnGerenciarPlaylists);
        add(btnHistorico);
        add(btnVoltar);

        // AÇÕES DOS BOTÕES
        btnBuscarMusicas.addActionListener(e -> {
            dispose();
            new BuscarMusicaView(idUsuario);
        });

        btnListarInfos.addActionListener(e -> {
            // você pode implementar ou remover caso esteja dentro da BuscarMusicaView
            JOptionPane.showMessageDialog(this, "Funcionalidade ainda não implementada.");
        });

        btnCurtirDescurtir.addActionListener(e -> {
            // futuro: chamar nova view passando idUsuario
            JOptionPane.showMessageDialog(this, "Funcionalidade ainda não implementada.");
        });

        btnGerenciarPlaylists.addActionListener(e -> {
            // futuro: chamar nova view passando idUsuario
            JOptionPane.showMessageDialog(this, "Funcionalidade ainda não implementada.");
        });

        btnHistorico.addActionListener(e -> {
            // futuro: chamar nova view passando idUsuario
            JOptionPane.showMessageDialog(this, "Funcionalidade ainda não implementada.");
        });

        btnVoltar.addActionListener(e -> {
            dispose();
            new InicioView();
        });

        setVisible(true);
    }
}