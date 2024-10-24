package controler;

import model.Model;
import model.indetificadores.DadosTabela;
import model.indetificadores.MySQLUser;
import view.FramePrincipal;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class ControlerModoGrafico {
    private FramePrincipal frame;
    private Model model;
    public void start() {
        frame = new FramePrincipal();
        //model = new Model(new MySQLUser("","Louco123@"));
       model = new Model(pegarInfoBD());
        frame.atualizarTabela(model.getDados());
        setarBotoesMenuPrincipal();
        setarBotoesCadastro();
        setarComboBox();
    }

    //Pega o usuário e senha pra logar na database do MySQL
    private MySQLUser pegarInfoBD() {
        String usuario = "";
        String senha = "";

        usuario = JOptionPane.showInputDialog("Digite o nome de usuário (Clique cancelar para usar root)");

        if(usuario == null) usuario = new String("");

        JPasswordField passwordField = new JPasswordField();
        passwordField.setEchoChar('*');

        JOptionPane optionPane = new JOptionPane(passwordField, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);

        JDialog dialog = optionPane.createDialog("Digite a senha (Clique cancelar para usar root)");

        dialog.setSize(400, 200);// manualmente seta o tamanho da box

        dialog.setVisible(true);

        Object selectedValue = optionPane.getValue();

        if (selectedValue != null && selectedValue.equals(JOptionPane.OK_OPTION)) {
            senha = new String(passwordField.getPassword());
        }

        return new MySQLUser(usuario,senha);
    }

    // Coloca as funções nos botões do menu da esquerda do programa
    private void setarBotoesMenuPrincipal(){
        JButton bCadastrarAlunos = frame.getbMenuCadastro();
        JButton bMenuTabelas = frame.getbMenuTabelas();
        JButton bMenuSair = frame.getbMenuSair();
        JComboBox<String> comboBox = frame.getComboBox(); //apenas usado para um bug marcado abaixo
        Object defaultAlunos = comboBox.getSelectedItem(); //apenas usado para um bug marcado abaixo

        bCadastrarAlunos.addActionListener(e ->{
            frame.trocarPainelCadAlunos();
        });

        bMenuTabelas.addActionListener(e ->{
            frame.trocarPainelTabelas();

            //para evitar bugs futuros (seta a tabela sempre pra Alunos como default)
            comboBox.setSelectedItem(defaultAlunos);
            frame.atualizarTabela(model.pegarTabelaAlunos());
        });

        bMenuSair.addActionListener(e -> System.exit(0));
    }

    // Adiciona a função de cadastro ao botão no painel de cadastrar alunos
    private void setarBotoesCadastro() {
        setarBotoesRelAlunos();
        setarBotoesExtras();
    }

    private void setarBotoesRelAlunos(){
        JButton botaoCadastroAlunos = frame.getBotaoCadastrar();
        JButton botaoAtualizarMatricula = frame.getBotaoAtualizarAluno();
        JButton botaoDeletarAluno = frame.getBotaoDeletarAluno();

        botaoCadastroAlunos.addActionListener(e ->{
            boolean cancelar = false;
            boolean sairLoop = false;

            String nome = "";
            while(!sairLoop) {
                nome = JOptionPane.showInputDialog("Digite o nome do aluno a cadastrar");

                if (nome == null) {
                    cancelar = true;
                    sairLoop = true;
                    JOptionPane.showMessageDialog(null, "Operação Cancelada");
                }else if(nome.isEmpty()) JOptionPane.showMessageDialog(null, "Digite um nome válido");
                        else sairLoop = true;
            }
            String matricula = "";
            if(!cancelar){
                sairLoop = false;
                while(!sairLoop){
                    matricula = JOptionPane.showInputDialog("Digite o status da matrícula\n1 - Matriculado\n2 - Nao Matriculado\n3 - Trancado");

                    if(matricula == null){
                        sairLoop = true;
                        cancelar = true;
                        JOptionPane.showMessageDialog(null, "Operação Cancelada");
                    }else if(matricula.equals("1")){
                        matricula = "MATRICULADO";
                        sairLoop = true;
                    }else if(matricula.equals("2")){
                        matricula = "NAO MATRICULADO";
                        sairLoop = true;
                    }else if(matricula.equals("3")){
                        matricula = "TRANCADO";
                        sairLoop = true;
                    }else JOptionPane.showMessageDialog(null, "Opção Inválida, tente novamente");
                }
            }

            if(!cancelar){
                model.cadastrarAluno(nome, matricula);
            }
        });

        botaoAtualizarMatricula.addActionListener(e ->{
            boolean cancelar = false;
            boolean sairLoop = false;

            String ra = "";
            while(!sairLoop) {
                ra = JOptionPane.showInputDialog("Digite RA do aluno a alterar");

                if (ra == null) {
                    cancelar = true;
                    sairLoop = true;
                    JOptionPane.showMessageDialog(null, "Operação Cancelada");
                }else if(ra.isEmpty()) JOptionPane.showMessageDialog(null, "Digite um valor válido!");
                else sairLoop = true;
            }


            String matricula = "";
            if(!cancelar){
                sairLoop = false;
                while(!sairLoop){
                    matricula = JOptionPane.showInputDialog("Digite o status da matrícula\n1 - Matriculado\n2 - Não Matriculado\n3 - Trancado");

                    if(matricula == null){
                        sairLoop = true;
                        cancelar = true;
                        JOptionPane.showMessageDialog(null, "Operação Cancelada");
                    }else if(matricula.equals("1")){
                        matricula = "MATRICULADO";
                        sairLoop = true;
                    }else if(matricula.equals("2")){
                        matricula = "NAO MATRICULADO";
                        sairLoop = true;
                    }else if(matricula.equals("3")){
                        matricula = "TRANCADO";
                        sairLoop = true;
                    }else JOptionPane.showMessageDialog(null, "Opção Inválida, tente novamente");
                }
            }

            if(!cancelar){
                model.atualizarMatricula(ra, matricula);
            }
        });

        botaoDeletarAluno.addActionListener(e ->{
            boolean cancelar = false;
            boolean sairLoop = false;

            String ra = "";
            while(!sairLoop) {
                 ra = JOptionPane.showInputDialog("Digite RA do aluno a deletar");

                if (ra == null) {
                    cancelar = true;
                    sairLoop = true;
                    JOptionPane.showMessageDialog(null, "Operação Cancelada");
                }else if(ra.isEmpty()) JOptionPane.showMessageDialog(null, "Digite um valor válido!");
                else sairLoop = true;
            }

            if(!cancelar){
                model.deletarAluno(ra);
            }
        });
    }

    private void setarBotoesExtras(){
        JButton botaoAdicionarCurso = frame.getBotaoAdicionarCurso();
        JButton botaoAdicionarDisciplina = frame.getBotaoAdicionarDisciplina();
        JButton botaoAdicionarTurma = frame.getBotaoAdicionarTurma();

        botaoAdicionarCurso.addActionListener(e ->{
            boolean cancelar = false;
            boolean sairLoop = false;

            String curso = "";
            while(!sairLoop) { //loop para ver se as informações entradas foram corretas ou cancelar operação
                curso = JOptionPane.showInputDialog("Digite o nome do curso a inserir");

                if (curso == null) {
                    cancelar = true;
                    sairLoop = true;
                    JOptionPane.showMessageDialog(null, "Operação Cancelada");
                }else if(curso.isEmpty()) JOptionPane.showMessageDialog(null, "Digite um valor válido!");
                else sairLoop = true;
            }

            if(!cancelar) model.adicionarCurso(curso);
        });

        botaoAdicionarDisciplina.addActionListener(e ->{
            boolean cancelar = false;
            boolean sairLoop = false;

            String disciplina = "";
            while(!sairLoop) {//loop para ver se as informações entradas foram corretas ou cancelar operação
                disciplina = JOptionPane.showInputDialog("Digite o nome da disciplina a inserir: ");

                if (disciplina == null) {
                    cancelar = true;
                    sairLoop = true;
                    JOptionPane.showMessageDialog(null, "Operação Cancelada");
                }else if(disciplina.isEmpty()) JOptionPane.showMessageDialog(null, "Digite um valor válido!");
                else sairLoop = true;
            }

            String curso;
            if(!cancelar){
                DadosTabela dados = model.pegarTabelaCursos();
                String cursos = "";
                for(Vector<Object> linha : dados.vectorDados){
                    ArrayList<String> dadosLinha = new ArrayList<>();
                    for(Object celula : linha){
                        dadosLinha.add(celula.toString());
                    }
                    cursos += String.join(" - ", dadosLinha) + "\n";
                }
                sairLoop = false;
                while(!sairLoop) {
                    //gerando a caixa de texto para os cursos
                    JTextArea textArea = new JTextArea(dados.vectorDados.size()-1, 30); // Create a text area for input
                    textArea.setText(cursos);
                    textArea.setLineWrap(true);
                    textArea.setWrapStyleWord(true);
                    textArea.setEditable(false);
                    JScrollPane scrollPane = new JScrollPane(textArea);


                    JTextField inputField = new JTextField(20); // Adjust width if needed

                    // Create a panel to hold both the scrollable message and the input field
                    JPanel panel = new JPanel(new BorderLayout(10, 10));
                    panel.setBackground(new Color(255,255,255));
                    panel.add(scrollPane, BorderLayout.CENTER);  // Add scrollable message at the center
                    panel.add(inputField, BorderLayout.SOUTH);   // Add input field at the bottom

                    // Display the JOptionPane with the scrollable message and input field
                    int result = JOptionPane.showConfirmDialog(null, panel, "Digite o curso relacionado a disciplina", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
//                    int result = JOptionPane.showConfirmDialog(null, scrollPane, "Enter your input", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                    if (result == JOptionPane.OK_OPTION) {
                        String input = inputField.getText();
                        int id = 0;
                        try{
                            id = Integer.parseInt(input);
                        }catch (Exception ex){
                            id = -1;
                        }
                        if(id != -1 && id <= dados.vectorDados.size()){
                            model.adicionarDisciplina(disciplina,id);
                            sairLoop = true;
                        }else{
                            JOptionPane.showMessageDialog(null,"Opção Inválida, tente novamente");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Operação Cancelada");
                        sairLoop = true;
                    }
                }
            }
        });

        botaoAdicionarTurma.addActionListener(e ->{
            //aqui Pedro Marques Prado
            boolean cancelar = false;
            boolean sairLoop = false;

            String turma = "";
            while(!sairLoop) {//loop para ver se as informações entradas foram corretas ou cancelar operação
                turma = JOptionPane.showInputDialog("Digite o nome da turma a inserir: ");

                if (turma == null) {
                    cancelar = true;
                    sairLoop = true;
                    JOptionPane.showMessageDialog(null, "Operação Cancelada");
                }else if(turma.isEmpty()) JOptionPane.showMessageDialog(null, "Digite um valor válido!");
                else sairLoop = true;
            }

            String disciplina;
            if(!cancelar){
                DadosTabela dados = model.pegarTabelaCursos();
                String cursos = "";
                for(Vector<Object> linha : dados.vectorDados){
                    ArrayList<String> dadosLinha = new ArrayList<>();
                    for(Object celula : linha){
                        dadosLinha.add(celula.toString());
                    }
                    cursos += String.join(" - ", dadosLinha) + "\n";
                }
                sairLoop = false;
                while(!sairLoop) {
                    //gerando a caixa de texto para os cursos
                    JTextArea textArea = new JTextArea(dados.vectorDados.size()-1, 30); // Create a text area for input
                    textArea.setText(cursos);
                    textArea.setLineWrap(true);
                    textArea.setWrapStyleWord(true);
                    textArea.setEditable(false);
                    JScrollPane scrollPane = new JScrollPane(textArea);


                    JTextField inputField = new JTextField(20); // Adjust width if needed

                    // Create a panel to hold both the scrollable message and the input field
                    JPanel panel = new JPanel(new BorderLayout(10, 10));
                    panel.setBackground(new Color(255,255,255));
                    panel.add(scrollPane, BorderLayout.CENTER);  // Add scrollable message at the center
                    panel.add(inputField, BorderLayout.SOUTH);   // Add input field at the bottom

                    // Display the JOptionPane with the scrollable message and input field
                    int result = JOptionPane.showConfirmDialog(null, panel, "Digite o curso relacionado a turma", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
//                    int result = JOptionPane.showConfirmDialog(null, scrollPane, "Enter your input", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                    if (result == JOptionPane.OK_OPTION) {
                        String input = inputField.getText();
                        int id = 0;
                        try{
                            id = Integer.parseInt(input);
                        }catch (Exception ex){
                            id = -1;
                        }
                        if(id != -1 && id <= dados.vectorDados.size()){
                            model.adicionarTurma(turma,id);
                            sairLoop = true;
                        }else{
                            JOptionPane.showMessageDialog(null,"Opção Inválida, tente novamente");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Operação Cancelada");
                        sairLoop = true;
                    }
                }
            }
        });
    }

    // faz a caixa da página de tabelas mudar as tabelas
    private void setarComboBox(){
        JComboBox<String> comboBox = frame.getComboBox();
        comboBox.addActionListener(e ->{
            String itemSelecionado = comboBox.getSelectedItem().toString();

            switch(itemSelecionado){
                case "Alunos" -> frame.atualizarTabela(model.pegarTabelaAlunos());

                case "Cursos" -> frame.atualizarTabela(model.pegarTabelaCursos());

                case "Disciplinas" -> frame.atualizarTabela(model.pegarTabelaDisciplinas());

                case "turmas" -> frame.atualizarTabela(model.pegarTabelaTurmas());
            }
        });
    }
}
