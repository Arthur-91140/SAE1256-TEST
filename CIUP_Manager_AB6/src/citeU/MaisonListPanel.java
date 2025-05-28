package citeU;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Panneau affichant la liste des maisons de la CIUP
 */
public class MaisonListPanel extends JPanel {
    
    // Composants d'interface graphique
    private JLabel titleLabel;
    private JPanel listPanel;
    private JButton addButton;
    private JScrollPane scrollPane;
    
    // Référence à la fenêtre principale
    private MainFrame mainFrame;
    
    // Liste temporaire des maisons (à remplacer par un accès aux données réelles)
    private ArrayList<Maison> maisons;
    
    /**
     * Constructeur du panneau de liste des maisons
     * @param mainFrame référence à la fenêtre principale
     */
    public MaisonListPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        
        // Initialisation du panneau
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        // Chargement des données de test
        loadTestData();
        
        // Initialisation des composants
        initComponents();
        
        // Construction de l'interface
        buildUI();
    }
    
    /**
     * Charge des données de test pour la démonstration
     */
    private void loadTestData() {
        maisons = new ArrayList<>();
        
        // Ajout de quelques maisons de test
        maisons.add(new MaisonEtudiant("Croatie", 150, "Hrvatska Kuća", "32 boulevard Jourdan", "Ivan Horvat"));
        maisons.add(new MaisonEtudiant("France", 230, "Maison France", "27 boulevard Jourdan", "Marie Dupont"));
        maisons.add(new MaisonEtudiant("Japon", 120, "Maison du Japon", "7 boulevard Jourdan", "Takahashi Yuki"));
        maisons.add(new MaisonEtudiant("Espagne", 175, "Colegio de España", "7E boulevard Jourdan", "Carlos Rodriguez"));
        maisons.add(new MaisonInternationale("Maison Internationale", "17 boulevard Jourdan", "Jean Martin"));
    }
    
    /**
     * Initialise les composants du panneau
     */
    private void initComponents() {
        // Titre du panneau
        titleLabel = new JLabel("Maisons");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(70, 90, 150));
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));
        
        // Bouton d'ajout
        addButton = new JButton("+");
        addButton.setFont(new Font("Arial", Font.BOLD, 16));
        addButton.setBackground(new Color(100, 180, 100));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.setBorderPainted(false);
        addButton.setPreferredSize(new Dimension(30, 30));
        addButton.addActionListener(e -> mainFrame.showPanel(MainFrame.MAISON_ADD_PANEL));
        
        // Panneau de liste
        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(Color.WHITE);
        
        // Scroll pane contenant la liste
        scrollPane = new JScrollPane(listPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
    }
    
    /**
     * Construit l'interface utilisateur
     */
    private void buildUI() {
        // Panneau de titre avec bouton d'ajout
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        headerPanel.add(addButton, BorderLayout.EAST);
        
        // Ajout des composants au panneau principal
        add(headerPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
        // Remplissage de la liste avec les données
        populateList();
    }
    
    /**
     * Remplit la liste des maisons avec les données disponibles
     */
    private void populateList() {
        // Nettoyage de la liste existante
        listPanel.removeAll();
        
        // Ajout de chaque maison à la liste
        for (Maison maison : maisons) {
            JPanel maisonPanel = createMaisonPanel(maison);
            listPanel.add(maisonPanel);
            listPanel.add(Box.createVerticalStrut(5));
        }
        
        // Ajout d'un espace en bas pour le scroll
        listPanel.add(Box.createVerticalGlue());
        
        // Mise à jour de l'affichage
        revalidate();
        repaint();
    }
    
    /**
     * Crée un panneau pour afficher une maison dans la liste
     * @param maison la maison à afficher
     * @return le panneau créé
     */
    private JPanel createMaisonPanel(Maison maison) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 245, 255));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 210, 230), 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        
        // Création du drapeau (représenté par un carré coloré)
        JPanel flagPanel = new JPanel();
        flagPanel.setPreferredSize(new Dimension(30, 20));
        
        // Sélection d'une couleur basée sur le nom (pour la démonstration)
        if (maison instanceof MaisonEtudiant) {
            String nationalite = ((MaisonEtudiant) maison).getNationalite();
            if (nationalite.equals("Croatie")) {
                flagPanel.setBackground(Color.RED);
            } else if (nationalite.equals("France")) {
                flagPanel.setBackground(Color.BLUE);
            } else if (nationalite.equals("Japon")) {
                flagPanel.setBackground(Color.WHITE);
                flagPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
            } else {
                flagPanel.setBackground(new Color(220, 220, 220));
            }
        } else {
            flagPanel.setBackground(new Color(70, 90, 150));
        }
        
        // Informations de la maison
        JLabel nameLabel = new JLabel(maison.getNom());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        // Bouton de recherche
        JButton searchButton = new JButton();
        searchButton.setIcon(createMagnifierIcon());
        searchButton.setBorderPainted(false);
        searchButton.setContentAreaFilled(false);
        searchButton.setFocusPainted(false);
        searchButton.addActionListener(e -> {
            // Afficher les détails de la maison (à implémenter)
            JOptionPane.showMessageDialog(mainFrame, 
                "Détails de la maison: " + maison.getNom() + "\n" +
                "Localisation: " + maison.getLocalisation() + "\n" +
                "Directeur: " + maison.getNomDirecteur(),
                "Détails de la Maison", 
                JOptionPane.INFORMATION_MESSAGE);
        });
        
        // Panneau contenant le nom et le drapeau
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        infoPanel.setOpaque(false);
        infoPanel.add(flagPanel);
        infoPanel.add(nameLabel);
        
        // Ajout des composants au panneau de maison
        panel.add(infoPanel, BorderLayout.CENTER);
        panel.add(searchButton, BorderLayout.EAST);
        
        return panel;
    }
    
    /**
     * Crée une icône de loupe pour le bouton de recherche
     * @return l'icône créée
     */
    private ImageIcon createMagnifierIcon() {
        // Création d'une icône de loupe simple
        int size = 20;
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(70, 90, 150));
        
        // Dessin du cercle de la loupe
        g2d.drawOval(3, 3, 10, 10);
        g2d.setStroke(new BasicStroke(2.0f));
        
        // Dessin du manche de la loupe
        g2d.drawLine(13, 13, 17, 17);
        
        g2d.dispose();
        
        return new ImageIcon(image);
    }
    
    /**
     * Met à jour la liste des maisons (peut être appelée après modification)
     */
    public void refreshData() {
        // À terme, cette méthode devrait récupérer les données à jour
        populateList();
    }
}
