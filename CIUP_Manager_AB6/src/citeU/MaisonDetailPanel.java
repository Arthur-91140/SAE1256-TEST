package citeU;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;

/**
 * Panneau affichant les détails d'une maison de la CIUP
 */
public class MaisonDetailPanel extends JPanel {
    
    // Référence à la fenêtre principale
    private MainFrame mainFrame;
    
    // Maison affichée
    private Maison maison;
    
    // Composants d'interface graphique
    private JLabel titleLabel;
    private JPanel infoPanel;
    private JPanel buttonPanel;
    private JPanel etudiantsPanel;
    private JPanel restaurationsPanel;
    private JScrollPane etudiantsScrollPane;
    
    // Boutons d'action
    private JButton backButton;
    private JButton editButton;
    private JButton addEtudiantButton;
    
    /**
     * Constructeur du panneau de détails d'une maison
     * 
     * @param mainFrame référence à la fenêtre principale
     */
    public MaisonDetailPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        
        // Initialisation du panneau
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        // Initialisation des composants
        initComponents();
        
        // Construction de l'interface (sans données)
        buildUI();
    }
    
    /**
     * Initialise les composants du panneau
     */
    private void initComponents() {
        // Titre du panneau (sera mis à jour avec le nom de la maison)
        titleLabel = new JLabel("");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(70, 90, 150));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        
        // Panneau d'informations
        infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        
        // Panneau pour les étudiants
        etudiantsPanel = new JPanel();
        etudiantsPanel.setLayout(new BoxLayout(etudiantsPanel, BoxLayout.Y_AXIS));
        etudiantsPanel.setBackground(Color.WHITE);
        
        // Scroll pane pour la liste des étudiants
        etudiantsScrollPane = new JScrollPane(etudiantsPanel);
        etudiantsScrollPane.setBorder(BorderFactory.createEmptyBorder());
        etudiantsScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        // Panneau pour les restaurations
        restaurationsPanel = new JPanel();
        restaurationsPanel.setLayout(new BoxLayout(restaurationsPanel, BoxLayout.Y_AXIS));
        restaurationsPanel.setBackground(Color.WHITE);
        restaurationsPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        // Boutons
        backButton = new JButton("Retour");
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        backButton.setBackground(new Color(220, 220, 220));
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> mainFrame.showPanel(MainFrame.MAISON_LIST_PANEL));
        
        editButton = new JButton("Modifier");
        editButton.setFont(new Font("Arial", Font.PLAIN, 14));
        editButton.setBackground(new Color(70, 130, 180));
        editButton.setForeground(Color.WHITE);
        editButton.setFocusPainted(false);
        
        addEtudiantButton = new JButton("Ajouter un étudiant");
        addEtudiantButton.setFont(new Font("Arial", Font.PLAIN, 14));
        addEtudiantButton.setBackground(new Color(100, 180, 100));
        addEtudiantButton.setForeground(Color.WHITE);
        addEtudiantButton.setFocusPainted(false);
        addEtudiantButton.addActionListener(e -> {
            if (maison instanceof MaisonEtudiant) {
                // Passer à l'écran d'ajout d'étudiant en fournissant la référence de la maison
                mainFrame.showPanel(MainFrame.MAISON_ETUDIANT_ADD_PANEL);
                // Ici, il faudrait un mécanisme pour transmettre la maison sélectionnée
                // au panneau MaisonEtudiantAddPanel
            } else {
                JOptionPane.showMessageDialog(mainFrame,
                    "Cette maison n'est pas une maison d'étudiants.",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        // Panneau de boutons
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(backButton);
        buttonPanel.add(editButton);
    }
    
    /**
     * Construit l'interface utilisateur de base (sans données)
     */
    private void buildUI() {
        // Panneau d'en-tête avec le titre et le drapeau
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        
        // Conteneur principal pour organiser les différentes sections
        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.Y_AXIS));
        mainContainer.setBackground(Color.WHITE);
        
        // Ajout des sections au conteneur principal
        mainContainer.add(infoPanel);
        
        // Section des étudiants (uniquement pour MaisonEtudiant)
        JPanel etudiantsSectionPanel = new JPanel(new BorderLayout());
        etudiantsSectionPanel.setBackground(Color.WHITE);
        
        JPanel etudiantHeaderPanel = new JPanel(new BorderLayout());
        etudiantHeaderPanel.setBackground(Color.WHITE);
        etudiantHeaderPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        JLabel etudiantsLabel = new JLabel("Étudiants de la maison");
        etudiantsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        etudiantsLabel.setForeground(new Color(70, 90, 150));
        
        etudiantHeaderPanel.add(etudiantsLabel, BorderLayout.CENTER);
        etudiantHeaderPanel.add(addEtudiantButton, BorderLayout.EAST);
        
        etudiantsSectionPanel.add(etudiantHeaderPanel, BorderLayout.NORTH);
        etudiantsSectionPanel.add(etudiantsScrollPane, BorderLayout.CENTER);
        
        mainContainer.add(etudiantsSectionPanel);
        
        // Section des restaurations
        JPanel restaurationSectionPanel = new JPanel(new BorderLayout());
        restaurationSectionPanel.setBackground(Color.WHITE);
        
        JLabel restaurationsLabel = new JLabel("Restaurations");
        restaurationsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        restaurationsLabel.setForeground(new Color(70, 90, 150));
        restaurationsLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 5, 20));
        
        restaurationSectionPanel.add(restaurationsLabel, BorderLayout.NORTH);
        restaurationSectionPanel.add(restaurationsPanel, BorderLayout.CENTER);
        
        mainContainer.add(restaurationSectionPanel);
        
        // Ajout des composants au panneau principal
        add(headerPanel, BorderLayout.NORTH);
        add(new JScrollPane(mainContainer), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    /**
     * Définit la maison à afficher et met à jour l'interface
     * 
     * @param maison la maison à afficher
     */
    public void setMaison(Maison maison) {
        this.maison = maison;
        
        if (maison != null) {
            updateUI();
        }
    }
    
    /**
     * Met à jour l'interface avec les données de la maison
     */
    private void updateUI() {
        // Mise à jour du titre
        titleLabel.setText(maison.getNom());
        
        // Nettoyage des panneaux
        infoPanel.removeAll();
        etudiantsPanel.removeAll();
        restaurationsPanel.removeAll();
        
        // Ajout des informations de base
        addInfoField("Nom", maison.getNom());
        addInfoField("Localisation", maison.getLocalisation());
        addInfoField("Directeur", maison.getNomDirecteur());
        
        // Si c'est une maison d'étudiants, ajouter les informations spécifiques
        if (maison instanceof MaisonEtudiant) {
            MaisonEtudiant maisonEtudiant = (MaisonEtudiant) maison;
            addInfoField("Nationalité", maisonEtudiant.getNationalite());
            addInfoField("Nombre de chambres", String.valueOf(maisonEtudiant.getNombreChambres()));
            addInfoField("Places disponibles", String.valueOf(maisonEtudiant.placesDisponibles()));
            
            // Ajout des étudiants
            addEtudiants(maisonEtudiant);
        }
        
        // Ajout des restaurations
        addRestaurations();
        
        // Mise à jour de l'affichage
        revalidate();
        repaint();
    }
    
    /**
     * Ajoute un champ d'information au panneau d'informations
     * 
     * @param label étiquette du champ
     * @param value valeur du champ
     */
    private void addInfoField(String label, String value) {
        JPanel fieldPanel = new JPanel(new BorderLayout(10, 0));
        fieldPanel.setBackground(Color.WHITE);
        fieldPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        
        JLabel labelComponent = new JLabel(label + ":");
        labelComponent.setFont(new Font("Arial", Font.BOLD, 14));
        labelComponent.setPreferredSize(new Dimension(150, 25));
        
        JLabel valueComponent = new JLabel(value);
        valueComponent.setFont(new Font("Arial", Font.PLAIN, 14));
        
        fieldPanel.add(labelComponent, BorderLayout.WEST);
        fieldPanel.add(valueComponent, BorderLayout.CENTER);
        
        infoPanel.add(fieldPanel);
        infoPanel.add(Box.createVerticalStrut(5));
    }
    
    /**
     * Ajoute la liste des étudiants au panneau des étudiants
     * 
     * @param maisonEtudiant la maison d'étudiants
     */
    private void addEtudiants(MaisonEtudiant maisonEtudiant) {
        // Affichage des étudiants si disponibles
        if (maisonEtudiant.getSesEtudiants() != null && !maisonEtudiant.getSesEtudiants().isEmpty()) {
            for (Etudiant etudiant : maisonEtudiant.getSesEtudiants()) {
                etudiantsPanel.add(createEtudiantPanel(etudiant));
                etudiantsPanel.add(Box.createVerticalStrut(5));
            }
        } else {
            // Message si aucun étudiant
            JLabel emptyLabel = new JLabel("Aucun étudiant dans cette maison");
            emptyLabel.setFont(new Font("Arial", Font.ITALIC, 14));
            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            emptyLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
            
            etudiantsPanel.add(Box.createVerticalStrut(20));
            etudiantsPanel.add(emptyLabel);
        }
    }
    
    /**
     * Crée un panneau pour afficher un étudiant
     * 
     * @param etudiant l'étudiant à afficher
     * @return le panneau créé
     */
    private JPanel createEtudiantPanel(Etudiant etudiant) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 245, 255));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 210, 230), 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        
        // Icône d'étudiant
        JLabel iconLabel = new JLabel("👤");
        iconLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        iconLabel.setPreferredSize(new Dimension(30, 30));
        
        // Nom et prénom
        JLabel nameLabel = new JLabel(etudiant.getPrenom() + " " + etudiant.getNom());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        // Bouton de détail
        JButton detailButton = new JButton("👁");
        detailButton.setBorderPainted(false);
        detailButton.setContentAreaFilled(false);
        detailButton.setFocusPainted(false);
        detailButton.addActionListener(e -> {
            // Afficher les détails de l'étudiant
            mainFrame.showPanel(MainFrame.ETUDIANT_DETAIL_PANEL);
            // Ici, il faudrait un mécanisme pour transmettre l'étudiant sélectionné
            // au panneau EtudiantDetailPanel
        });
        
        // Panneau pour le nom et l'icône
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        infoPanel.setOpaque(false);
        infoPanel.add(iconLabel);
        infoPanel.add(nameLabel);
        
        // Ajout des composants au panneau d'étudiant
        panel.add(infoPanel, BorderLayout.CENTER);
        panel.add(detailButton, BorderLayout.EAST);
        
        return panel;
    }
    
    /**
     * Ajoute la liste des restaurations au panneau des restaurations
     */
    private void addRestaurations() {
        // Affichage des restaurations si disponibles
        if (maison.getSesRestaurations() != null && !maison.getSesRestaurations().isEmpty()) {
            for (Restauration restauration : maison.getSesRestaurations()) {
                JPanel restaurationPanel = new JPanel(new BorderLayout());
                restaurationPanel.setBackground(new Color(250, 250, 240));
                restaurationPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(220, 220, 200), 1),
                    BorderFactory.createEmptyBorder(8, 8, 8, 8)
                ));
                restaurationPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
                
                // Icône de restauration
                JLabel iconLabel = new JLabel("🍴");
                iconLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
                iconLabel.setPreferredSize(new Dimension(30, 30));
                
                // Nom et capacité
                JLabel nameLabel = new JLabel(restauration.getNom() + " - Capacité: " + restauration.getCapacite());
                nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                
                JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
                infoPanel.setOpaque(false);
                infoPanel.add(iconLabel);
                infoPanel.add(nameLabel);
                
                restaurationPanel.add(infoPanel, BorderLayout.CENTER);
                
                restaurationsPanel.add(restaurationPanel);
                restaurationsPanel.add(Box.createVerticalStrut(5));
            }
        } else {
            // Message si aucune restauration
            JLabel emptyLabel = new JLabel("Aucune restauration dans cette maison");
            emptyLabel.setFont(new Font("Arial", Font.ITALIC, 14));
            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            emptyLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
            
            restaurationsPanel.add(emptyLabel);
        }
    }
}