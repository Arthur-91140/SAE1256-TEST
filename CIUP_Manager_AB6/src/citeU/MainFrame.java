package citeU;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Fenêtre principale de l'application CIUP
 * Contient le panneau de menu et gère l'affichage des différents écrans
 */
public class MainFrame extends JFrame {
    
    // Constantes
    private static final String TITLE = "CIUP - Cité Internationale Universitaire de Paris";
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    
    // Composants d'interface graphique
    private MenuPanel menuPanel;
    private JPanel contentPanel;
    private CardLayout cardLayout;
    
    // Panneaux de contenu (à implémenter par la suite)
    private JPanel homePanel; // Sera remplacé par HomePanel
    private JPanel maisonListPanel; // Sera remplacé par MaisonListPanel
    private JPanel etudiantListPanel; // Sera remplacé par EtudiantListPanel
    private JPanel restaurantListPanel; // Sera remplacé par RestaurantListPanel
    
    // Noms des cartes pour CardLayout
    public static final String HOME_PANEL = "HOME";
    public static final String MAISON_LIST_PANEL = "MAISON_LIST";
    public static final String MAISON_ADD_PANEL = "MAISON_ADD";
    public static final String MAISON_DETAIL_PANEL = "MAISON_DETAIL";
    public static final String ETUDIANT_LIST_PANEL = "ETUDIANT_LIST";
    public static final String ETUDIANT_ADD_PANEL = "ETUDIANT_ADD";
    public static final String ETUDIANT_DETAIL_PANEL = "ETUDIANT_DETAIL";
    public static final String RESTAURANT_LIST_PANEL = "RESTAURANT_LIST";
    public static final String RESTAURANT_DETAIL_PANEL = "RESTAURANT_DETAIL";
    public static final String MENU_DETAIL_PANEL = "MENU_DETAIL";
    
    /**
     * Constructeur de la fenêtre principale
     */
    public MainFrame() {
        // Initialisation de la fenêtre
        setTitle(TITLE);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centre la fenêtre
        
        // Initialisation du gestionnaire de disposition principal
        setLayout(new BorderLayout());
        
        // Création du panneau de menu
        menuPanel = new MenuPanel(this);
        add(menuPanel, BorderLayout.NORTH);
        
        // Initialisation du panneau de contenu avec CardLayout
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        add(contentPanel, BorderLayout.CENTER);
        
        // Création des panneaux temporaires (à remplacer par les vrais panneaux)
        initPanels();
        
        // Afficher l'écran d'accueil par défaut
        cardLayout.show(contentPanel, HOME_PANEL);
    }
    
    /**
     * Initialise les panneaux de contenu temporaires
     * Ces panneaux seront remplacés par les vraies implémentations
     */
    private void initPanels() {
        // Panneau d'accueil temporaire
        homePanel = createTempPanel("Écran d'accueil", Color.WHITE);
        contentPanel.add(homePanel, HOME_PANEL);
        
        // Panneau de liste des maisons temporaire
        maisonListPanel = createTempPanel("Liste des Maisons", new Color(230, 240, 250));
        contentPanel.add(maisonListPanel, MAISON_LIST_PANEL);
        
        // Panneau de liste des étudiants temporaire
        etudiantListPanel = createTempPanel("Liste des Étudiants", new Color(240, 250, 230));
        contentPanel.add(etudiantListPanel, ETUDIANT_LIST_PANEL);
        
        // Panneau de liste des restaurants temporaire
        restaurantListPanel = createTempPanel("Liste des Restaurants", new Color(250, 240, 230));
        contentPanel.add(restaurantListPanel, RESTAURANT_LIST_PANEL);
    }
    
    /**
     * Crée un panneau temporaire avec un titre et une couleur de fond
     * @param title le titre à afficher dans le panneau
     * @param bgColor la couleur de fond du panneau
     * @return le panneau créé
     */
    private JPanel createTempPanel(String title, Color bgColor) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(bgColor);
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        JLabel infoLabel = new JLabel("Ce panneau sera implémenté prochainement");
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(infoLabel, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * Change l'écran affiché
     * @param panelName le nom du panneau à afficher
     */
    public void showPanel(String panelName) {
        cardLayout.show(contentPanel, panelName);
    }
}
