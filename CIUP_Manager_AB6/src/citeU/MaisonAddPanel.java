package citeU;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;

/**
 * Panneau pour l'ajout d'une nouvelle maison à la CIUP
 */
public class MaisonAddPanel extends JPanel {
    
    // Composants d'interface graphique
    private JLabel titleLabel;
    private JPanel formPanel;
    private JButton saveButton;
    private JButton cancelButton;
    
    // Champs du formulaire
    private JTextField nomField;
    private JTextField nationaliteField;
    private JTextField directeurField;
    private JTextField nombreChambresField;
    private JTextField localisationField;
    
    // Référence à la fenêtre principale
    private MainFrame mainFrame;
    
    /**
     * Constructeur du panneau d'ajout de maison
     * @param mainFrame référence à la fenêtre principale
     */
    public MaisonAddPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        
        // Initialisation du panneau
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        // Initialisation des composants
        initComponents();
        
        // Construction de l'interface
        buildUI();
    }
    
    /**
     * Initialise les composants du panneau
     */
    private void initComponents() {
        // Titre du panneau
        titleLabel = new JLabel("Ajout d'une maison");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(70, 90, 150));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        
        // Champs du formulaire
        nomField = createTextField(20);
        nationaliteField = createTextField(20);
        directeurField = createTextField(20);
        nombreChambresField = createTextField(5);
        localisationField = createTextField(30);
        
        // Boutons
        saveButton = new JButton("Valider");
        saveButton.setBackground(new Color(100, 180, 100));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        saveButton.addActionListener(e -> saveMaison());
        
        cancelButton = new JButton("Annuler");
        cancelButton.setBackground(new Color(220, 220, 220));
        cancelButton.setFocusPainted(false);
        cancelButton.addActionListener(e -> mainFrame.showPanel(MainFrame.MAISON_LIST_PANEL));
    }
    
    /**
     * Crée un champ de texte standardisé
     * @param columns nombre de colonnes du champ
     * @return le champ de texte créé
     */
    private JTextField createTextField(int columns) {
        JTextField field = new JTextField(columns);
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        return field;
    }
    
    /**
     * Construit l'interface utilisateur
     */
    private void buildUI() {
        // Panneau pour le titre
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        
        // Panneau pour le drapeau du pays (à droite du titre)
        JPanel flagPanel = createFlagPanel();
        headerPanel.add(flagPanel, BorderLayout.EAST);
        
        // Formulaire
        formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
        
        // Création des composants du formulaire avec GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Nom de la maison
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Nom:"), gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        formPanel.add(nomField, gbc);
        
        // Nationalité
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        formPanel.add(new JLabel("Nationalité:"), gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        formPanel.add(nationaliteField, gbc);
        
        // Directeur
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        formPanel.add(new JLabel("Directeur:"), gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        formPanel.add(directeurField, gbc);
        
        // Nombre de chambres
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        formPanel.add(new JLabel("Nombre de chambres:"), gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        formPanel.add(nombreChambresField, gbc);
        
        // Localisation
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        formPanel.add(new JLabel("Localisation:"), gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        formPanel.add(localisationField, gbc);
        
        // Espace vertical
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        formPanel.add(Box.createVerticalGlue(), gbc);
        
        // Panneau des boutons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        
        // Ajout des composants au panneau principal
        add(headerPanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    /**
     * Crée un panneau pour afficher le drapeau du pays
     * @return le panneau de drapeau
     */
    private JPanel createFlagPanel() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(40, 30));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 20));
        panel.setOpaque(false);
        
        // Le drapeau est initialement vide, il pourrait être mis à jour selon la nationalité
        return panel;
    }
    
    /**
     * Enregistre la maison à partir des données du formulaire
     */
    private void saveMaison() {
        // Validation des champs
        if (nomField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Le nom de la maison est obligatoire.",
                "Erreur de validation", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (nationaliteField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "La nationalité est obligatoire.",
                "Erreur de validation", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Validation du nombre de chambres
        int nombreChambres = 0;
        try {
            nombreChambres = Integer.parseInt(nombreChambresField.getText().trim());
            if (nombreChambres <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Le nombre de chambres doit être un nombre entier positif.",
                "Erreur de validation", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Création de la maison (pour l'instant, juste un message de confirmation)
        String message = "Maison créée avec succès :\n" +
                        "Nom: " + nomField.getText() + "\n" +
                        "Nationalité: " + nationaliteField.getText() + "\n" +
                        "Directeur: " + directeurField.getText() + "\n" +
                        "Nombre de chambres: " + nombreChambres + "\n" +
                        "Localisation: " + localisationField.getText();
        
        JOptionPane.showMessageDialog(this, message, "Maison créée", JOptionPane.INFORMATION_MESSAGE);
        
        // Réinitialisation du formulaire
        resetForm();
        
        // Retour à la liste des maisons
        mainFrame.showPanel(MainFrame.MAISON_LIST_PANEL);
    }
    
    /**
     * Réinitialise le formulaire
     */
    private void resetForm() {
        nomField.setText("");
        nationaliteField.setText("");
        directeurField.setText("");
        nombreChambresField.setText("");
        localisationField.setText("");
    }
}
