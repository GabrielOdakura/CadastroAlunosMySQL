package view;

import model.indetificadores.DadosTabela;
import view.custom.RoundedBorder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PainelCamadas extends JLayeredPane {

    private JPanel painelTabela;
    private JPanel painelCadAlunos;
    private JTable tabela;
    private JScrollPane scrollPane;
    private JPanel boxTabelas;
    private CardLayout cardLayout;
    private JComboBox<String> comboBox;
    private JButton botaoCadastrar;
    private JButton botaoAtualizarAluno;
    private JButton botaoDeletarAluno;
    private JButton botaoAdicionarCurso;
    private JButton botaoAdicionarDisciplina;
    private JButton botaoAdicionarTurma;
    private final int tamanhoCelula = 20;

    public PainelCamadas() {
        gerarPainelCamadas();
    }

    private void gerarPainelCamadas() {

        painelCadAlunos = gerarPainelCadastro();
        painelTabela = gerarPainelTabela();

        this.setPreferredSize(new Dimension(1200, 800));
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);
        this.add(painelCadAlunos, "CadAlunos");
        this.add(painelTabela, "Tabela");
    }

    private JPanel gerarPainelCadastro() {
        JPanel painel = new JPanel();
        painel.setBackground(new Color(255, 255, 255));
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.add(Box.createVerticalGlue());
        painel.setBounds(0, 0, 1200, 800);

        botaoCadastrar = new JButton("Cadastrar Aluno");
        botaoCadastrar.setAlignmentX(Component.CENTER_ALIGNMENT);

        botaoAtualizarAluno = new JButton("Atualizar Aluno");
        botaoAtualizarAluno.setAlignmentX(Component.CENTER_ALIGNMENT);

        botaoDeletarAluno = new JButton("Deletar Cadastro Aluno");
        botaoDeletarAluno.setAlignmentX(Component.CENTER_ALIGNMENT);

        botaoAdicionarCurso = new JButton("Adicionar Curso");
        botaoAdicionarCurso.setAlignmentX(Component.CENTER_ALIGNMENT);

        botaoAdicionarDisciplina = new JButton("Adicionar Disciplina");
        botaoAdicionarDisciplina.setAlignmentX(Component.CENTER_ALIGNMENT);

        botaoAdicionarTurma = new JButton("Adicionar Turma");
        botaoAdicionarTurma.setAlignmentX(Component.CENTER_ALIGNMENT);

        Dimension tamanhoBotoes = new Dimension(200, 50);
        botaoCadastrar.setPreferredSize(tamanhoBotoes);
        botaoCadastrar.setMinimumSize(tamanhoBotoes);
        botaoAtualizarAluno.setPreferredSize(tamanhoBotoes);
        botaoAtualizarAluno.setMinimumSize(tamanhoBotoes);
        botaoDeletarAluno.setPreferredSize(tamanhoBotoes);
        botaoDeletarAluno.setMinimumSize(tamanhoBotoes);
        botaoAdicionarCurso.setPreferredSize(tamanhoBotoes);
        botaoAdicionarCurso.setMinimumSize(tamanhoBotoes);
        botaoAdicionarDisciplina.setPreferredSize(tamanhoBotoes);
        botaoAdicionarDisciplina.setMinimumSize(tamanhoBotoes);
        botaoAdicionarTurma.setPreferredSize(tamanhoBotoes);
        botaoAdicionarTurma.setMinimumSize(tamanhoBotoes);

        painel.add(botaoCadastrar);
        painel.add(Box.createVerticalStrut(20));
        painel.add(botaoAtualizarAluno);
        painel.add(Box.createVerticalStrut(20));
        painel.add(botaoDeletarAluno);
        painel.add(Box.createVerticalStrut(20));
        painel.add(botaoAdicionarCurso);
        painel.add(Box.createVerticalStrut(20));
        painel.add(botaoAdicionarDisciplina);
        painel.add(Box.createVerticalStrut(20));
        painel.add(botaoAdicionarTurma);
        painel.add(Box.createVerticalGlue());
        return painel;
    }

    private JPanel gerarPainelTabela() {
        JPanel painel = new JPanel();
        painel.setBackground(new Color(255, 255, 255));
        painel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        boxTabelas = gerarBoxTabelas();
        tabela = new JTable();
        tabela.setRowHeight(tamanhoCelula);

        scrollPane = new JScrollPane(tabela);
        scrollPane.setPreferredSize(new Dimension(600, 600));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(new RoundedBorder(10));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.insets = new Insets(40, 20, 40, 0);

        painel.add(boxTabelas, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(0, 20, 20, 20);

        painel.add(scrollPane, gbc);

        return painel;
    }

    private JPanel gerarBoxTabelas() {
        JPanel painel = new JPanel();
        painel.setBackground(new Color(255, 255, 255));
        painel.setLayout(new FlowLayout());

        JLabel labelTabelas = new JLabel("Tabela");
        comboBox = new JComboBox<>(new String[]{"Alunos", "Cursos", "Disciplinas"});

        painel.add(labelTabelas);
        painel.add(comboBox);

        return painel;
    }

    public void atualizarTabela(DadosTabela dados) {
        DefaultTableModel modeloTabela = new DefaultTableModel(dados.vectorDados, dados.vectorNomeColunas);

        tabela.setModel(modeloTabela);

        int qtdCelulas = modeloTabela.getRowCount();
        int tamanho = Math.min(qtdCelulas * tamanhoCelula, 600);
        scrollPane.setPreferredSize(new Dimension(600, tamanho));

        tabela.revalidate();
        scrollPane.revalidate();
        tabela.repaint();
        scrollPane.repaint(); // Also repaint the scroll pane
    }

    public void trocarPainelTabelas() {
        cardLayout.show(this, "Tabela");
    }

    public void trocarPainelCadAlunos() {
        cardLayout.show(this, "CadAlunos");
    }

    //getters

    public JComboBox<String> getComboBox() {
        return comboBox;
    }

    public JButton getBotaoCadastrar() {
        return botaoCadastrar;
    }

    public JButton getBotaoAtualizarAluno() {
        return botaoAtualizarAluno;
    }

    public JButton getBotaoDeletarAluno() {
        return botaoDeletarAluno;
    }

    public JButton getBotaoAdicionarCurso() {
        return botaoAdicionarCurso;
    }

    public JButton getBotaoAdicionarDisciplina() {
        return botaoAdicionarDisciplina;
    }

    public JButton getBotaoAdicionarTurma() {
        return botaoAdicionarTurma;
    }
}
