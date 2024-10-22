package model;

import model.indetificadores.DadosTabela;
import model.indetificadores.MySQLUser;

import java.util.Random;
import java.util.Vector;

public class Model {
    private ConectorDB conector;
    private DadosTabela dados;

    public Model(MySQLUser info){
        conector = new ConectorDB(info.getUsuario(), info.getSenha());
        dados = conector.pegarTabelaAlunos();
        gerarRA();
    }

    public DadosTabela pegarTabelaAlunos(){
        return dados = conector.pegarTabelaAlunos();
    }

    public DadosTabela pegarTabelaCursos(){
        return dados = conector.pegarTabelaCursos();
    }

    public DadosTabela pegarTabelaDisciplinas(){
        return dados = conector.pegarTabelaDisciplinas();
    }


    public void cadastrarAluno(String nome, String matricula) {
        conector.inserirAluno(gerarRA(), nome, matricula);
    }

    public void atualizarMatricula(String RA, String statusMatricula){
        conector.atualizarMatricula(RA.toUpperCase(), statusMatricula);
    }

    public void deletarAluno(String RA){
        conector.deletarAluno(RA.toUpperCase());
    }

    public void adicionarCurso(String nomeCurso){
        conector.inserirCurso(nomeCurso);
    }

    public void adicionarDisciplina(String nomeDisciplina, int id){
        conector.inserirDisciplina(nomeDisciplina,id);
    }

    private String gerarRA(){
        String raGerado = "";
        boolean sairLoop = false;
        Random r = new Random();
        r = new Random(r.nextLong());
        pegarTabelaAlunos();
        Vector<Vector<Object>> vector = dados.vectorDados;

        while(!sairLoop){
            raGerado = "RA00" + r.nextInt(320000,322000);
            boolean raRepetido = false;
            for(Object celula : vector){
                int comecoIndex = celula.toString().indexOf("RA");
                int fimIndex = celula.toString().indexOf(",", comecoIndex);
                String extraido = celula.toString().substring(comecoIndex,fimIndex).trim();
                if(raGerado.equals(extraido)){
                    raRepetido = true;
                    break;
                }
            }
            if(!raRepetido) sairLoop = true;
        }
        return raGerado;
    }

    public DadosTabela getDados() {
        return dados;
    }


}
