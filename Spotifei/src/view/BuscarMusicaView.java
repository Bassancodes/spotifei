package view;

import dao.MusicaDAO;
import model.Musica;

import dao.HistoricoDAO;
import model.Historico;
import java.sql.Timestamp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class BuscarMusicaView extends JFrame {
    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JList<Musica> listaResultados;
    private DefaultListModel<Musica> modeloLista;
    private JButton btnVoltar;

    private int idUsuario;

    public BuscarMusicaView(int idUsuario) {
        this.idUsuario = idUsuario;
        setTitle("Spotifei - Buscar Músicas");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

       
        JPanel painelBusca = new JPanel(new FlowLayout());
        JLabel lblBuscar = new JLabel("Buscar por nome, artista ou gênero:");
        txtBuscar = new JTextField(20);
        btnBuscar = new JButton("Buscar");
        painelBusca.add(lblBuscar);
        painelBusca.add(txtBuscar);
        painelBusca.add(btnBuscar);
        add(painelBusca, BorderLayout.NORTH);

      
        modeloLista = new DefaultListModel<>();
        listaResultados = new JList<>(modeloLista);
        add(new JScrollPane(listaResultados), BorderLayout.CENTER);

     
        btnVoltar = new JButton("Voltar");
        JPanel painelInferior = new JPanel(new FlowLayout());
        painelInferior.add(btnVoltar);
        add(painelInferior, BorderLayout.SOUTH);

     
        btnBuscar.addActionListener((ActionEvent e) -> {
            String termo = txtBuscar.getText().trim();
            if (!termo.isEmpty()) {
                MusicaDAO dao = new MusicaDAO();
                List<Musica> lista = dao.buscarPorTermo(termo);
                modeloLista.clear();

                if (lista.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nenhuma música encontrada.");
                } else {
                    for (Musica m : lista) {
                        modeloLista.addElement(m);
                    }

                   
                    Historico h = new Historico(idUsuario, termo, new Timestamp(System.currentTimeMillis()));
                    HistoricoDAO hDao = new HistoricoDAO();
                    hDao.salvarHistorico(h);
                }
            }
        });

      
        listaResultados.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Musica musicaSelecionada = listaResultados.getSelectedValue();
                if (musicaSelecionada != null) {
                    new DetalhesMusicaView(musicaSelecionada, idUsuario);
                    dispose();
                }
            }
        });

       
        btnVoltar.addActionListener(e -> {
            dispose();
            new MenuView(idUsuario);
        });

        setVisible(true);
    }
}