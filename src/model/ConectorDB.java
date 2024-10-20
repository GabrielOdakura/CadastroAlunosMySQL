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
