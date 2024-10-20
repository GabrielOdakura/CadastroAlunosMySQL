package controler;

import model.Model;
import model.indetificadores.MySQLUser;
import view.FramePrincipal;

import javax.swing.*;

public class ControlerModoGrafico {
    private FramePrincipal frame;
    private Model model;
    public void start() {
        frame = new FramePrincipal();
        model = new Model(new MySQLUser("","Louco123@"));
//        model = new Model(pegarInfoBD());
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
        JButton botaoCadastro = frame.getBotaoCadastrar();

        botaoCadastro.addActionListener(e ->{
            boolean cancelar = false;

            String nome = JOptionPane.showInputDialog("Digite o nome do aluno a cadastrar");

            if(nome == null) {
                cancelar = true;
                JOptionPane.showMessageDialog(null, "Cadastro Cancelado");
            }
            String matricula = "";
            if(!cancelar){
                boolean sairLoop = false;
                while(!sairLoop){
                     matricula = JOptionPane.showInputDialog("Digite o status da matrícula\n1 - Matriculado\n2 - Nao Matriculado\n3 - Trancado");

                    if(matricula == null){
                        sairLoop = true;
                        cancelar = true;
                        JOptionPane.showMessageDialog(null, "Cadastro Cancelado");
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
            }
        });
    }
}
