package view;

import dao.HistoricoDAO;
import dao.HistoricoCurtidasDAO;
import model.Historico;
import model.HistoricoCurtidas;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HistoricoView extends JFrame {
    private int idUsuario;
    private DefaultListModel<String> modelHistorico;
    private JList<String> listaHistorico;
    private HistoricoDAO historicoDAO;
    private HistoricoCurtidasDAO curtidasDAO;

    public HistoricoView(int idUsuario) {
        this.idUsuario = idUsuario;
        this.historicoDAO = new HistoricoDAO();
        this.curtidasDAO = new HistoricoCurtidasDAO();

        setTitle("Histórico do Usuário");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        modelHistorico = new DefaultListModel<>();
        listaHistorico = new JList<>(modelHistorico);
        add(new JScrollPane(listaHistorico), BorderLayout.CENTER);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> {
            dispose();
            new MenuView(idUsuario);
        });
        add(btnVoltar, BorderLayout.SOUTH);

        carregarHistoricoCompleto();
        setVisible(true);
    }

    private void carregarHistoricoCompleto() {
        modelHistorico.clear();

        // Histórico de buscas
        List<Historico> buscas = historicoDAO.buscarHistoricoPorUsuario(idUsuario);
        for (Historico h : buscas) {
            modelHistorico.addElement("[Busca] " + h.getDataHora() + " - " + h.getTermo());
        }

        // Histórico de curtidas
        List<HistoricoCurtidas> curtidas = curtidasDAO.listarPorUsuario(idUsuario);
        for (HistoricoCurtidas hc : curtidas) {
            modelHistorico.addElement("[Curtida] " + hc.getTituloMusica());
        }
    }
}