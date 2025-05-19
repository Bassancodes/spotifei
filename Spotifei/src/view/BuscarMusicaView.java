package view;

import dao.MusicaDAO;
import model.Musica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        JPanel painelBusca = new JPanel();
        painelBusca.setLayout(new FlowLayout());

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

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                    }
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
            new MenuView(idUsuario);
            dispose();
        });

        setVisible(true);
    }
}
