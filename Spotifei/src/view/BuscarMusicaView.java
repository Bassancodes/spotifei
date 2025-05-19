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
    private JTextArea txtResultado;

    public BuscarMusicaView() {
        setTitle("Spotifei - Buscar Músicas");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Parte superior (busca)
        JPanel painelBusca = new JPanel(new BorderLayout(5, 5));
        JLabel lblBuscar = new JLabel("Buscar por nome, artista ou gênero:");
        txtBuscar = new JTextField();
        JButton btnBuscar = new JButton("Buscar");

        painelBusca.add(lblBuscar, BorderLayout.NORTH);
        painelBusca.add(txtBuscar, BorderLayout.CENTER);
        painelBusca.add(btnBuscar, BorderLayout.EAST);

        // Área de resultado
        txtResultado = new JTextArea();
        txtResultado.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtResultado);

        // Botão voltar
        JButton btnVoltar = new JButton("Voltar");

        // Adicionando ao painel principal
        painel.add(painelBusca, BorderLayout.NORTH);
        painel.add(scroll, BorderLayout.CENTER);
        painel.add(btnVoltar, BorderLayout.SOUTH);

        add(painel);
        setVisible(true);

        // Ação buscar
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String termo = txtBuscar.getText().trim();
                if (!termo.isEmpty()) {
                    MusicaDAO dao = new MusicaDAO();
                    List<Musica> lista = dao.buscarPorTermo(termo);
                    txtResultado.setText("");

                    if (lista.isEmpty()) {
                        txtResultado.setText("Nenhuma música encontrada.");
                    } else {
                        for (Musica m : lista) {
                            txtResultado.append("Título: " + m.getTitulo() +
                                    " | Gênero: " + m.getGenero() +
                                    " | Duração: " + m.getDuracao() + "s\n");
                        }
                    }
                } else {
                    txtResultado.setText("Digite um termo para buscar.");
                }
            }
        });

        // Ação voltar
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // fecha apenas esta janela
            }
        });
    }
}