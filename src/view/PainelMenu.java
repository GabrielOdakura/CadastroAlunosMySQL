package view;

import javax.swing.*;
import java.awt.*;

public class PainelMenu extends JPanel{

    JButton bMenuCadastro;
    JButton bMenuTabelas;
    JButton bMenuSair;

    public PainelMenu(){
        gerarPainelMenu();
    }

    private void gerarPainelMenu(){
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Centraliza os itens no painel
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;

        // Carrega e renderiza a imagem da logo
        String currentDir = System.getProperty("user.dir");
        currentDir += "\\src\\model\\imagensPrograma\\FullLogoResized.png"; // Ensure correct path
        ImageIcon logoGrande = new ImageIcon(currentDir);
        Image imagemOriginal = logoGrande.getImage();

        ImagePanel imagePanel = new ImagePanel(imagemOriginal);
        imagePanel.setPreferredSize(new Dimension(250, 164)); // Set preferred size for the image panel

        // Adiciona a imagem junto com o layout
        gbc.gridx = 0; // Column
        gbc.gridy = 0; // Row
        gbc.insets = new Insets(0, 0, 20, 0); // Add some bottom margin
        this.add(imagePanel, gbc);

        // Instancia e config botões
        bMenuCadastro = new JButton("Cadastrar Alunos");
        bMenuSair = new JButton("Sair do Programa");
        bMenuTabelas = new JButton("Ver Tabelas");

        Dimension tamanhoBotoes = new Dimension(200, 50);
        bMenuCadastro.setPreferredSize(tamanhoBotoes);
        bMenuSair.setPreferredSize(tamanhoBotoes);
        bMenuTabelas.setPreferredSize(tamanhoBotoes);

        // Colocar os constrains e insets pros botões
        gbc.insets = new Insets(10, 0, 10, 0); // Top and bottom margin for buttons

        gbc.gridx = 0; // Reset column for buttons
        gbc.gridy = 1; // Next row for the first button
        this.add(bMenuCadastro, gbc);

        gbc.gridy = 2; // Next row for the second button
        this.add(bMenuTabelas, gbc);

        gbc.gridy = 3; // Next row for the third button
        this.add(bMenuSair, gbc);

    }

    // ImagePanel class for custom image rendering
    class ImagePanel extends JPanel {
        private Image image;

        public ImagePanel(Image image) {
            this.image = image;
            this.setPreferredSize(new Dimension(image.getWidth(null), image.getHeight(null)));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.drawImage(image, 0, 0, this);
            g2d.dispose();
        }
    }
    //getters

    public JButton getbMenuCadastro() {
        return bMenuCadastro;
    }

    public JButton getbMenuTabelas() {
        return bMenuTabelas;
    }

    public JButton getbMenuSair() {
        return bMenuSair;
    }
}
