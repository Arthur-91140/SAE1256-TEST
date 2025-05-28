package citeU;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Panneau de menu supérieur de l'application CIUP
 * Contient les boutons pour naviguer entre les différentes sections
 */
public class MenuPanel extends JPanel {
    
    // Référence à la fenêtre principale
    private MainFrame mainFrame;
    
    // Composants d'interface graphique
    private JLabel logoLabel;
    private JButton maisonButton;
    private JButton etudiantButton;
    private JButton restoUButton;
    
    /**
     * Constructeur du panneau de menu
     * @param mainFrame référence à la fenêtre principale
     */
    public MenuPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        
        // Initialisation du panneau
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        setBackground(new Color(70, 90, 150)); // Bleu foncé comme dans les captures d'écran
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        // Création des composants
        initComponents();
        
        // Ajout des composants au panneau
        addComponents();
        
        // Configuration des événements
        setupEventHandlers();
    }
    
    /**
     * Initialise les composants du panneau de menu
     */
    private void initComponents() {
        // Logo CIUP
        logoLabel = createLogoLabel();
        
        // Boutons de navigation
        maisonButton = createMenuButton("Maison");
        etudiantButton = createMenuButton("Etudiant");
        restoUButton = createMenuButton("Resto U");
    }
    
    /**
     * Crée un label pour le logo
     * @return le label du logo
     */
    private JLabel createLogoLabel() {
        JLabel label = new JLabel("CITÉ INTERNATIONALE UNIVERSITAIRE DE PARIS");
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setForeground(Color.WHITE);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        return label;
    }
    
    /**
     * Crée un bouton de menu avec le style approprié
     * @param text le texte du bouton
     * @return le bouton créé
     */
    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(50, 70, 130)); // Bleu un peu plus foncé pour le bouton
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(120, 30));
        
        // Effet de survol
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(60, 80, 140)); // Bleu légèrement plus clair au survol
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(50, 70, 130)); // Retour à la couleur normale
            }
        });
        
        return button;
    }
    
    /**
     * Ajoute les composants au panneau
     */
    private void addComponents() {
        add(logoLabel);
        add(maisonButton);
        add(etudiantButton);
        add(restoUButton);
    }
    
    /**
     * Configure les gestionnaires d'événements pour les boutons
     */
    private void setupEventHandlers() {
        // Action pour le bouton Maison
        maisonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel(MainFrame.MAISON_LIST_PANEL);
            }
        });
        
        // Action pour le bouton Etudiant
        etudiantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel(MainFrame.ETUDIANT_LIST_PANEL);
            }
        });
        
        // Action pour le bouton Resto U
        restoUButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel(MainFrame.RESTAURANT_LIST_PANEL);
            }
        });
    }
}
