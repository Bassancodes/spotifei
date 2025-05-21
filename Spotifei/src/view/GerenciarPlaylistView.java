package view;

import dao.MusicaDAO;
import dao.PlaylistDAO;
import dao.PlaylistMusicaDAO;
import model.Musica;
import model.Playlist;
import model.PlaylistMusica;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GerenciarPlaylistView extends JFrame {
    private int idUsuario;
    private JTextField txtNomePlaylist;
    private JTextField txtBuscarMusica;
    private DefaultListModel<Playlist> modelPlaylists;
    private DefaultListModel<Musica> modelMusicas;
    private DefaultListModel<Musica> modelMusicasPlaylist;
    private JList<Playlist> listaPlaylists;
    private JList<Musica> listaMusicas;
    private JList<Musica> listaMusicasPlaylist;
    private PlaylistDAO playlistDAO;
    private MusicaDAO musicaDAO;
    private PlaylistMusicaDAO playlistMusicaDAO;

    public GerenciarPlaylistView(int idUsuario) {
        this.idUsuario = idUsuario;
        this.playlistDAO = new PlaylistDAO();
        this.musicaDAO = new MusicaDAO();
        this.playlistMusicaDAO = new PlaylistMusicaDAO();

        setTitle("Gerenciar Playlists");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel de criação
        JPanel painelTopo = new JPanel(new FlowLayout());
        txtNomePlaylist = new JTextField(20);
        JButton btnCriar = new JButton("Criar Playlist");
        btnCriar.addActionListener(e -> criarPlaylist());
        painelTopo.add(new JLabel("Nome da Playlist:"));
        painelTopo.add(txtNomePlaylist);
        painelTopo.add(btnCriar);
        add(painelTopo, BorderLayout.NORTH);

        // Painel de conteúdo principal
        JPanel painelCentro = new JPanel(new GridLayout(1, 3));

        // Lista de playlists
        modelPlaylists = new DefaultListModel<>();
        listaPlaylists = new JList<>(modelPlaylists);
        listaPlaylists.addListSelectionListener(e -> atualizarMusicasDaPlaylist());
        painelCentro.add(new JScrollPane(listaPlaylists));

        // Busca e lista de músicas
        JPanel painelBusca = new JPanel(new BorderLayout());
        JPanel painelBuscar = new JPanel();
        txtBuscarMusica = new JTextField(10);
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> buscarMusicas());
        painelBuscar.add(new JLabel("Buscar música:"));
        painelBuscar.add(txtBuscarMusica);
        painelBuscar.add(btnBuscar);
        painelBusca.add(painelBuscar, BorderLayout.NORTH);

        modelMusicas = new DefaultListModel<>();
        listaMusicas = new JList<>(modelMusicas);
        JButton btnAdicionar = new JButton("Adicionar à playlist");
        btnAdicionar.addActionListener(e -> adicionarMusicaNaPlaylist());
        painelBusca.add(new JScrollPane(listaMusicas), BorderLayout.CENTER);
        painelBusca.add(btnAdicionar, BorderLayout.SOUTH);
        painelCentro.add(painelBusca);

        // Lista de músicas da playlist selecionada
        JPanel painelMusicasPlaylist = new JPanel(new BorderLayout());
        modelMusicasPlaylist = new DefaultListModel<>();
        listaMusicasPlaylist = new JList<>(modelMusicasPlaylist);
        JButton btnRemover = new JButton("Remover da playlist");
        btnRemover.addActionListener(e -> removerMusicaDaPlaylist());
        painelMusicasPlaylist.add(new JLabel("Músicas na playlist:"), BorderLayout.NORTH);
        painelMusicasPlaylist.add(new JScrollPane(listaMusicasPlaylist), BorderLayout.CENTER);
        painelMusicasPlaylist.add(btnRemover, BorderLayout.SOUTH);
        painelCentro.add(painelMusicasPlaylist);

        add(painelCentro, BorderLayout.CENTER);

        // Botão voltar
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> {
            dispose();
            new MenuView(idUsuario);
        });
        add(btnVoltar, BorderLayout.SOUTH);

        atualizarPlaylists();
        setVisible(true);
    }

    private void criarPlaylist() {
        String nome = txtNomePlaylist.getText().trim();
        if (!nome.isEmpty()) {
            playlistDAO.criarPlaylist(nome, idUsuario);
            txtNomePlaylist.setText("");
            atualizarPlaylists();
        } else {
            JOptionPane.showMessageDialog(this, "Informe um nome para a playlist.");
        }
    }

    private void atualizarPlaylists() {
        modelPlaylists.clear();
        List<Playlist> playlists = playlistDAO.listarPlaylistsPorUsuario(idUsuario);
        for (Playlist p : playlists) {
            modelPlaylists.addElement(p);
        }
    }

    private void buscarMusicas() {
        modelMusicas.clear();
        String termo = txtBuscarMusica.getText().trim();
        if (!termo.isEmpty()) {
            List<Musica> musicas = musicaDAO.buscarPorTermo(termo);
            for (Musica m : musicas) {
                modelMusicas.addElement(m);
            }
        }
    }

    private void adicionarMusicaNaPlaylist() {
        Playlist playlist = listaPlaylists.getSelectedValue();
        Musica musica = listaMusicas.getSelectedValue();
        if (playlist != null && musica != null) {
            PlaylistMusica pm = new PlaylistMusica(playlist.getId(), musica.getId());
            playlistMusicaDAO.vincularMusica(pm);
            atualizarMusicasDaPlaylist();
        }
    }

    private void atualizarMusicasDaPlaylist() {
        modelMusicasPlaylist.clear();
        Playlist playlist = listaPlaylists.getSelectedValue();
        if (playlist != null) {
            List<Musica> musicas = playlistMusicaDAO.listarMusicasDaPlaylist(playlist.getId());
            for (Musica m : musicas) {
                modelMusicasPlaylist.addElement(m);
            }
        }
    }

    private void removerMusicaDaPlaylist() {
        Playlist playlist = listaPlaylists.getSelectedValue();
        Musica musica = listaMusicasPlaylist.getSelectedValue();
        if (playlist != null && musica != null) {
            playlistMusicaDAO.removerMusicaDaPlaylist(playlist.getId(), musica.getId());
            atualizarMusicasDaPlaylist();
        }
    }
}
