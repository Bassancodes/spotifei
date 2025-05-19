package view;

import dao.CurtidaDAO;
import model.Musica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DetalhesMusicaView extends JFrame {

    private Musica musica;
    private int idUsuario;
    private JLabel lblTitulo, lblGenero, lblDuracao, lblCurtidas, lblStatus;
    private JButton btnCurtir, btnVoltar;

    private CurtidaDAO curtidaDAO = new CurtidaDAO();

    public DetalhesMusicaView(Musica musica, int idUsuario) {
        this.musica = musica;
        this.idUsuario = idUsuario;

        setTitle("Detalhes da Música");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        lblTitulo = new JLabel("Título: " + musica.getTitulo());
        lblGenero = new JLabel("Gênero: " + musica.getGenero());
        lblDuracao = new JLabel("Duração: " + musica.getDuracao() + " segundos");
        lblCurtidas = new JLabel("Curtidas: " + curtidaDAO.contarCurtidas(musica.getId()));

        boolean jaCurtiu = curtidaDAO.jaCurtiu(idUsuario, musica.getId());
        lblStatus = new JLabel(jaCurtiu ? "Você já curtiu esta música." : "Você não curtiu esta música ainda.");

        btnCurtir = new JButton(jaCurtiu ? "Descurtir" : "Curtir");
        btnCurtir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (curtidaDAO.jaCurtiu(idUsuario, musica.getId())) {
                    curtidaDAO.descurtirMusica(idUsuario, musica.getId());
                    lblStatus.setText("Você não curtiu esta música ainda.");
                    btnCurtir.setText("Curtir");
                } else {
                    curtidaDAO.curtirMusica(idUsuario, musica.getId());
                    lblStatus.setText("Você já curtiu esta música.");
                    btnCurtir.setText("Descurtir");
                }
                lblCurtidas.setText("Curtidas: " + curtidaDAO.contarCurtidas(musica.getId()));
            }
        });

        btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MenuView(idUsuario);
            }
        });

        setLayout(new GridLayout(7, 1));
        add(lblTitulo);
        add(lblGenero);
        add(lblDuracao);
        add(lblCurtidas);
        add(lblStatus);
        add(btnCurtir);
        add(btnVoltar);

        setVisible(true);
    }
}
