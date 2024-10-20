package view;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import model.indetificadores.DadosTabela;

import javax.swing.*;
import java.awt.*;

public class FramePrincipal {
    private JFrame frame = new JFrame("Cadastro Alunos");
    private JSplitPane splitPane;
    private PainelCamadas painelCamadas;
    private PainelMenu painelMenu;
    private JButton bMenuCadastro;
    private JButton bMenuSair;
    private JButton bMenuTabelas;

    public FramePrincipal(){
        gerarIntefaceVisual();
        bMenuCadastro = painelMenu.getbMenuCadastro();
        bMenuSair = painelMenu.getbMenuSair();
        bMenuTabelas = painelMenu.getbMenuTabelas();
    }

    private void gerarIntefaceVisual() {

        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
            UIManager.put("LayeredPane.background", new Color(255, 255, 255));
            UIManager.put("Panel.background", new Color(48, 51, 49));
            UIManager.put("Table.alternateRowColor", new Color(240, 240, 240)); // Light gray
            UIManager.put("Table.alternateRowColor2", Color.WHITE); // White
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame.setSize(1200,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        String currentDir = System.getProperty("user.dir");
        currentDir += "\\src\\model\\imagensPrograma\\Small Logo.png";
        ImageIcon imagemLogo = new ImageIcon(currentDir);
        Image imagemConvertida = imagemLogo.getImage();
        frame.setIconImage(imagemConvertida);


        painelMenu = new PainelMenu();
        painelCamadas = new PainelCamadas();

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, painelMenu, painelCamadas);
        splitPane.setDividerLocation(frame.getWidth() / 3);
        splitPane.setDividerSize(0);

        frame.add(splitPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public void trocarPainelCadAlunos(){
        painelCamadas.trocarPainelCadAlunos();
    }

    public void trocarPainelTabelas(){
        painelCamadas.trocarPainelTabelas();
    }

    public void atualizarTabela(DadosTabela dados){
        painelCamadas.atualizarTabela(dados);
    }

    //getters

    public JButton getbMenuCadastro() {
        return bMenuCadastro;
    }

    public JButton getbMenuSair() {
        return bMenuSair;
    }

    public JButton getbMenuTabelas() {
        return bMenuTabelas;
    }

    public JComboBox<String> getComboBox() {
        return painelCamadas.getComboBox();
    }

    public JButton getBotaoCadastrar() {
        return painelCamadas.getBotaoCadastrar();
    }
}
