package citeU;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * Panneau d'accueil de l'application CIUP
 * Affiche un écran de bienvenue avec le logo et une brève description
 */
public class HomePanel extends JPanel {
    
    // Composants d'interface graphique
    private JLabel titleLabel;
    private JLabel logoLabel;
    private JTextArea descriptionArea;
    private JPanel contentPanel;
    
    /**
     * Constructeur du panneau d'accueil
     */
    public HomePanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        // Initialisation des composants
        initComponents();
        
        // Ajout des composants
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(titleLabel);
        contentPanel.add(Box.createVerticalStrut(30));
        contentPanel.add(logoLabel);
        contentPanel.add(Box.createVerticalStrut(30));
        contentPanel.add(descriptionArea);
        
        add(contentPanel, BorderLayout.CENTER);
    }
    
    /**
     * Initialise les composants du panneau
     */
    private void initComponents() {
        // Titre principal
        titleLabel = new JLabel("Bienvenue à la Cité Internationale Universitaire de Paris");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Logo (représenté par un texte pour le moment)
        logoLabel = createLogoLabel();
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Description
        descriptionArea = new JTextArea();
        descriptionArea.setText(
            "La Cité internationale universitaire de Paris est un campus de 40 hectares situé dans " +
            "le 14e arrondissement de Paris. Elle accueille chaque année environ 12 000 étudiants, " +
            "chercheurs, artistes et sportifs de haut niveau de plus de 150 nationalités.\n\n" +
            "Cette application vous permet de gérer les maisons, les étudiants et les restaurants " +
            "de la Cité Universitaire.\n\n" +
            "Utilisez le menu en haut pour naviguer entre les différentes sections."
        );
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 14));
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        descriptionArea.setBackground(Color.WHITE);
        descriptionArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        descriptionArea.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
    }
    
    /**
     * Crée un label pour le logo
     * @return le label du logo
     */
    private JLabel createLogoLabel() {
        // Création d'un panneau coloré pour représenter le logo
        JPanel logoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                
                // Dégradé de bleu pour le logo
                GradientPaint gradient = new GradientPaint(
                    0, 0, new Color(70, 90, 150), 
                    getWidth(), getHeight(), new Color(100, 130, 200)
                );
                g2d.setPaint(gradient);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                
                // Texte du logo
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font("Arial", Font.BOLD, 18));
                String text = "CIUP";
                FontMetrics fm = g2d.getFontMetrics();
                int textWidth = fm.stringWidth(text);
                int textHeight = fm.getHeight();
                g2d.drawString(text, (getWidth() - textWidth) / 2, 
                                    getHeight() / 2 + textHeight / 4);
                
                g2d.dispose();
            }
            
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(150, 100);
            }
        };
        
        logoPanel.setOpaque(false);
        return new JLabel(new ImageIcon(createImage(logoPanel)));
    }
    
    /**
     * Crée une image à partir d'un composant
     * @param component le composant à convertir en image
     * @return l'image créée
     */
    private Image createImage(JComponent component) {
        Dimension size = component.getPreferredSize();
        BufferedImage image = new BufferedImage(
            size.width, size.height, BufferedImage.TYPE_INT_ARGB
        );
        Graphics2D g2d = image.createGraphics();
        component.paint(g2d);
        g2d.dispose();
        return image;
    }
}
