package model;

import model.indetificadores.DadosTabela;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class ConectorDB {
    private String url = "jdbc:mysql://localhost:3306/mydb";
    private String usuario = "";
    private String senha = "";
    private Connection connection;

    public ConectorDB(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
        getConnection();
    }

    public ConectorDB(){
        getConnection();
    }

    private void getConnection() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Fazendo a conexão
            connection = DriverManager.getConnection(url, usuario, senha);

            System.out.println("Database conectada com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro estabelecendo conexão com a database: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "O Programa será fechado!");
            System.out.println("Erro estabelecendo conexão com a database: " + e.getMessage());
            System.exit(0);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver MySql não foi encontrado: " + e.getMessage());
        }
    }

    public DadosTabela testeTabela(){
        Vector<Vector<Object>> vectorDados = new Vector<>();
        Vector<String> vectorNomeColunas = new Vector<>();
        String query = "describe `mydb`.`aluno`;";

        try{
            PreparedStatement stmt = connection.prepareCall(query);
            ResultSet rs = stmt.executeQuery();

            ArrayList<String> temp = new ArrayList<>();

            //pega dinamicamente todos os campos que serão escolhidos
            while (rs.next()) {
                temp.add(rs.getString("Field"));
            }

            vectorNomeColunas = new Vector<>(temp);

            query = "select * from `mydb`.`aluno`;";
            stmt = connection.prepareCall(query);
            rs = stmt.executeQuery();

            // adiciona os dados de cada coluna 1 por 1 e passa pra prox coluna
            while(rs.next()){
                Vector<Object> vectorColuna = new Vector<>();
                for (String col: temp){
                    vectorColuna.add(rs.getObject(col));
                }
                vectorDados.add(vectorColuna);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new DadosTabela(vectorDados,vectorNomeColunas);
    }

    public DadosTabela pegarTabelaAlunos(){
        Vector<Vector<Object>> vectorDados = new Vector<>();
        Vector<String> vectorNomeColunas = new Vector<>();
        String query = "describe `mydb`.`aluno`;";

        try{
            PreparedStatement stmt = connection.prepareCall(query);
            ResultSet rs = stmt.executeQuery();

            ArrayList<String> temp = new ArrayList<>();

            //pega dinamicamente todos os campos que serão escolhidos
            while (rs.next()) {
                temp.add(rs.getString("Field"));
            }

            vectorNomeColunas = new Vector<>(temp);

            query = "select * from `mydb`.`aluno`;";
            stmt = connection.prepareCall(query);
            rs = stmt.executeQuery();

            // adiciona os dados de cada coluna 1 por 1 e passa pra prox coluna
            while(rs.next()){
                Vector<Object> vectorColuna = new Vector<>();
                for (String col: temp){
                    vectorColuna.add(rs.getObject(col));
                }
                vectorDados.add(vectorColuna);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new DadosTabela(vectorDados,vectorNomeColunas);
    }

    public DadosTabela pegarTabelaCursos(){
        Vector<Vector<Object>> vectorDados = new Vector<>();
        Vector<String> vectorNomeColunas = new Vector<>();
        String query = "describe `mydb`.`curso`;";

        try{
            PreparedStatement stmt = connection.prepareCall(query);
            ResultSet rs = stmt.executeQuery();

            ArrayList<String> temp = new ArrayList<>();

            //pega dinamicamente todos os campos que serão escolhidos
            while (rs.next()) {
                temp.add(rs.getString("Field"));
            }

            vectorNomeColunas = new Vector<>(temp);

            query = "select * from `mydb`.`curso`;";
            stmt = connection.prepareCall(query);
            rs = stmt.executeQuery();

            // adiciona os dados de cada coluna 1 por 1 e passa pra prox coluna
            while(rs.next()){
                Vector<Object> vectorColuna = new Vector<>();
                for (String col: temp){
                    vectorColuna.add(rs.getObject(col));
                }
                vectorDados.add(vectorColuna);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new DadosTabela(vectorDados,vectorNomeColunas);
    }

    public DadosTabela pegarTabelaDisciplinas(){
        Vector<Vector<Object>> vectorDados = new Vector<>();
        Vector<String> vectorNomeColunas = new Vector<>();
        String query = "describe `mydb`.`disciplina`;";

        try{
            PreparedStatement stmt = connection.prepareCall(query);
            ResultSet rs = stmt.executeQuery();

            ArrayList<String> temp = new ArrayList<>();

            //pega dinamicamente todos os campos que serão escolhidos
            while (rs.next()) {
                temp.add(rs.getString("Field"));
            }

            vectorNomeColunas = new Vector<>(temp);

            query = "select * from `mydb`.`disciplina`;";
            stmt = connection.prepareCall(query);
            rs = stmt.executeQuery();

            // adiciona os dados de cada coluna 1 por 1 e passa pra prox coluna
            while(rs.next()){
                Vector<Object> vectorColuna = new Vector<>();
                for (String col: temp){
                    vectorColuna.add(rs.getObject(col));
                }
                vectorDados.add(vectorColuna);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new DadosTabela(vectorDados,vectorNomeColunas);
    }

    public void inserirAluno(String RA, String nome, String statusMatricula){
        String query = "insert into `mydb`.`aluno`(`ra`, `nome_aluno`, `matricula`) values ('" + RA + "','" + nome + "','" + statusMatricula + "');";

        try{
            PreparedStatement stmt = connection.prepareCall(query);
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Colunas Afetadas: " + rowsAffected);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void atualizarMatricula(String RA, String statusMatricula){
        String query = "update `mydb`.`aluno` set `aluno`.`matricula` = ? where ra = ? ;";
        try{
            PreparedStatement stmt = connection.prepareCall(query);
            stmt.setString(1, statusMatricula);
            stmt.setString(2, RA);
            int rowsAffected = stmt.executeUpdate();
            if(rowsAffected == 0) JOptionPane.showMessageDialog(null, "RA não encontrado");
            else System.out.println("Colunas Afetadas: " + rowsAffected);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void deletarAluno(String RA){
        String query = "update `mydb`.`aluno` set `aluno`.`matricula` = 'NAO MATRICULADO' where ra = ? ;";
        try{
            PreparedStatement stmt = connection.prepareCall(query);
            stmt.setString(1, RA);
            int rowsAffected = stmt.executeUpdate();
            if(rowsAffected == 0) JOptionPane.showMessageDialog(null, "RA não encontrado");
            else System.out.println("Colunas Afetadas: " + rowsAffected);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void inserirCurso(String nomeCurso){
        String query = "insert into `mydb`.`curso`(nome_curso) values ( ? );";
        try{
            PreparedStatement stmt = connection.prepareCall(query);
            stmt.setString(1, nomeCurso);
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Colunas Afetadas: " + rowsAffected);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void inserirDisciplina(String nomeDisciplina, int id){
        String query = "insert into `mydb`.`disciplina`(nome_disciplina) values ( ? );";
        try{
            PreparedStatement stmt = connection.prepareCall(query);
            stmt.setString(1, nomeDisciplina);
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Colunas Afetadas(tabela disciplina): " + rowsAffected);

            /*
            query = "select id from `mydb`.`disciplina` where nome_disciplina = ? ;";
            stmt = connection.prepareCall(query);
            stmt.setString(1, nomeDisciplina);
            ResultSet rs = stmt.executeQuery();
            int idDisciplina = 0;
            while(rs.next()){
                idDisciplina = rs.getInt("ID");
            }

            query = "insert into `mydb`.`curso_turma_disciplina`(ID_curso,ID_disciplina) values ( ? , ? );";
            stmt = connection.prepareCall(query);
            stmt.setInt(1, id);
            stmt.setInt(2, idDisciplina);
            rowsAffected = stmt.executeUpdate();
            System.out.println("Colunas Afetadas(tabela curso_turma_disciplina): " + rowsAffected);
            */
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    //setters
    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
