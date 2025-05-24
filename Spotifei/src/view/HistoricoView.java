package view;

import dao.HistoricoCurtidasDAO;
import dao.HistoricoDAO;
import dao.HistoricoDescurtidaDAO;
import model.Historico;
import model.HistoricoCurtidas;
import model.HistoricoDescurtida;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HistoricoView extends JFrame {
    private int idUsuario;
    private HistoricoDAO historicoDAO;
    private HistoricoCurtidasDAO curtidasDAO;
    private HistoricoDescurtidaDAO descurtidaDAO;

    private JTabbedPane abas;
    private DefaultListModel<String> modelBuscas;
    private DefaultListModel<String> modelCurtidas;
    private DefaultListModel<String> modelDescurtidas;

    public HistoricoView(int idUsuario) {
        this.idUsuario = idUsuario;
        this.historicoDAO = new HistoricoDAO();
        this.curtidasDAO = new HistoricoCurtidasDAO();
        this.descurtidaDAO = new HistoricoDescurtidaDAO();

        setTitle("Histórico Completo");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        abas = new JTabbedPane();

        
        modelBuscas = new DefaultListModel<>();
        JList<String> listaBuscas = new JList<>(modelBuscas);
        abas.add("Buscas", new JScrollPane(listaBuscas));

       
        modelCurtidas = new DefaultListModel<>();
        JList<String> listaCurtidas = new JList<>(modelCurtidas);
        abas.add("Curtidas", new JScrollPane(listaCurtidas));

      
        modelDescurtidas = new DefaultListModel<>();
        JList<String> listaDescurtidas = new JList<>(modelDescurtidas);
        abas.add("Descurtidas", new JScrollPane(listaDescurtidas));

        add(abas, BorderLayout.CENTER);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> {
            dispose();
            new MenuView(idUsuario);
        });
        add(btnVoltar, BorderLayout.SOUTH);

        carregarDados();
        setVisible(true);
    }

    private void carregarDados() {
     
        modelBuscas.clear();
        List<Historico> buscas = historicoDAO.buscarHistoricoPorUsuario(idUsuario);
        for (Historico h : buscas) {
            modelBuscas.addElement(h.getDataHora() + " - " + h.getTermo());
        }

        
        modelCurtidas.clear();
        List<HistoricoCurtidas> curtidas = curtidasDAO.listarPorUsuario(idUsuario);
        for (HistoricoCurtidas hc : curtidas) {
            modelCurtidas.addElement("Curtiu: " + hc.getTituloMusica());
        }

        
        modelDescurtidas.clear();
        List<HistoricoDescurtida> descurtidas = descurtidaDAO.listarPorUsuario(idUsuario);
        for (HistoricoDescurtida hd : descurtidas) {
        modelDescurtidas.addElement("Descurtiu: " + hd.getTituloMusica());
    }
       
        }
 }
