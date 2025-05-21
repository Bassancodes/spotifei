package view;

import dao.HistoricoDAO;
import model.Historico;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HistoricoView extends JFrame {
    private int idUsuario;
    private DefaultListModel<String> modelHistorico;
    private JList<String> listaHistorico;
    private HistoricoDAO historicoDAO;

    public HistoricoView(int idUsuario) {
        this.idUsuario = idUsuario;
        this.historicoDAO = new HistoricoDAO();

        setTitle("Histórico de Buscas");
        setSize(400, 300);
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

        carregarHistorico();
        setVisible(true);
    }

    private void carregarHistorico() {
        modelHistorico.clear();
        List<Historico> historicoList = historicoDAO.buscarHistoricoPorUsuario(idUsuario);
        for (Historico h : historicoList) {
            modelHistorico.addElement(h.getDataHora() + " - " + h.getTermo());
        }
    }
}