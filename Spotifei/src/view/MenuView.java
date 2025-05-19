package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuView extends JFrame {
    
    public MenuView() {
        setTitle("Spotifei - Menu Principal");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

       
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1, 10, 10));

      
        JButton btnBuscarMusicas = new JButton("Buscar músicas");
        JButton btnListarInfo = new JButton("Listar informações da música");
        JButton btnCurtirDescurtir = new JButton("Curtir / Descurtir músicas");
        JButton btnPlaylists = new JButton("Gerenciar playlists");
        JButton btnHistorico = new JButton("Visualizar histórico");
        JButton btnVoltar = new JButton("Voltar");

        
        panel.add(btnBuscarMusicas);
        panel.add(btnListarInfo);
        panel.add(btnCurtirDescurtir);
        panel.add(btnPlaylists);
        panel.add(btnHistorico);
        panel.add(btnVoltar);

       
        btnVoltar.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                
                dispose();
                new LoginView(); 
            }
        });

        add(panel);
        setVisible(true);
    }
}