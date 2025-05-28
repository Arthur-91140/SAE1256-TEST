package vues;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.ArrayList;
import citeU.*;

/**
 * Vue pour la gestion des étudiants avec tableau et formulaire
 * @author Arthur Pruvost
 */
public class VueGestionEtudiants extends JPanel implements InterfaceVue {
    
    //-----------------------------
    // CONSTANTES D'ACTIONS
    //-----------------------------
    public static final String ACTION_AJOUTER = "AJOUTER_ETUDIANT";
    public static final String ACTION_MODIFIER = "MODIFIER_ETUDIANT";
    public static final String ACTION_SUPPRIMER = "SUPPRIMER_ETUDIANT";
    public static final String ACTION_AFFECTER = "AFFECTER_ETUDIANT";
    public static final String ACTION_RECHERCHER = "RECHERCHER_ETUDIANT";
    
    //-----------------------------
    // ATTRIBUTS
    //-----------------------------
    private JTable tableEtudiants;
    private DefaultTableModel modeleTable;
    private JScrollPane scrollPane;
    
    // Formulaire
    private JTextField champNom;
    private JTextField champPrenom;
    private JTextField champNationalite;
    private JComboBox<String> comboMaisons;
    private JTextField champRecherche;
    
    // Boutons
    private JButton boutonAjouter;
    private JButton boutonModifier;
    private JButton boutonSupprimer;
    private JButton boutonAffecter;
    private JButton boutonRechercher;
    
    //-----------------------------
    // CONSTRUCTEUR
    //-----------------------------
    public VueGestionEtudiants() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Gestion des Étudiants"));
        
        initialiserComposants();
        configurerLayout();
    }
    
    //-----------------------------
    // MÉTHODES D'INITIALISATION
    //-----------------------------
    
    private void initialiserComposants() {
        // Création du tableau
        String[] colonnes = {"Nom", "Prénom", "Nationalité", "Maison"};
        modeleTable = new DefaultTableModel(colonnes, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tableEtudiants = new JTable(modeleTable);
        tableEtudiants.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableEtudiants.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        
        scrollPane = new JScrollPane(tableEtudiants);
        scrollPane.setPreferredSize(new Dimension(500, 300));
        
        // Champs du formulaire
        champNom = new JTextField(15);
        champPrenom = new JTextField(15);
        champNationalite = new JTextField(15);
        comboMaisons = new JComboBox<>();
        champRecherche = new JTextField(15);
        
        // Boutons
        boutonAjouter = new JButton("Ajouter");
        boutonModifier = new JButton("Modifier");
        boutonSupprimer = new JButton("Supprimer");
        boutonAffecter = new JButton("Affecter");
        boutonRechercher = new JButton("Rechercher");
        
        // Configuration des actions
        boutonAjouter.setActionCommand(ACTION_AJOUTER);
        boutonModifier.setActionCommand(ACTION_MODIFIER);
        boutonSupprimer.setActionCommand(ACTION_SUPPRIMER);
        boutonAffecter.setActionCommand(ACTION_AFFECTER);
        boutonRechercher.setActionCommand(ACTION_RECHERCHER);
    }
    
    private void configurerLayout() {
        // Panneau principal avec JSplitPane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        
        // Panneau gauche - Tableau
        JPanel panneauGauche = new JPanel(new BorderLayout());
        panneauGauche.setBorder(BorderFactory.createTitledBorder("Liste des Étudiants"));
        
        // Barre de recherche
        JPanel panneauRecherche = new JPanel(new FlowLayout());
        panneauRecherche.add(new JLabel("Rechercher:"));
        panneauRecherche.add(champRecherche);
        panneauRecherche.add(boutonRechercher);
        
        panneauGauche.add(panneauRecherche, BorderLayout.NORTH);
        panneauGauche.add(scrollPane, BorderLayout.CENTER);
        
        // Panneau droit - Formulaire
        JPanel panneauDroit = new JPanel();
        panneauDroit.setLayout(new BoxLayout(panneauDroit, BoxLayout.Y_AXIS));
        panneauDroit.setBorder(BorderFactory.createTitledBorder("Informations Étudiant"));
        
        // Formulaire avec GridBagLayout
        JPanel formulaire = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Ligne 1 - Nom
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        formulaire.add(new JLabel("Nom:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        formulaire.add(champNom, gbc);
        
        // Ligne 2 - Prénom
        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE;
        formulaire.add(new JLabel("Prénom:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        formulaire.add(champPrenom, gbc);
        
        // Ligne 3 - Nationalité
        gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE;
        formulaire.add(new JLabel("Nationalité:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        formulaire.add(champNationalite, gbc);
        
        // Ligne 4 - Maison
        gbc.gridx = 0; gbc.gridy = 3; gbc.fill = GridBagConstraints.NONE;
        formulaire.add(new JLabel("Maison:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        formulaire.add(comboMaisons, gbc);
        
        panneauDroit.add(formulaire);
        panneauDroit.add(Box.createVerticalStrut(20));
        
        // Panneau des boutons
        JPanel panneauBoutons = new JPanel(new FlowLayout());
        panneauBoutons.add(boutonAjouter);
        panneauBoutons.add(boutonModifier);
        panneauBoutons.add(boutonSupprimer);
        panneauBoutons.add(boutonAffecter);
        
        panneauDroit.add(panneauBoutons);
        
        // Configuration du SplitPane
        splitPane.setLeftComponent(panneauGauche);
        splitPane.setRightComponent(panneauDroit);
        splitPane.setDividerLocation(500);
        splitPane.setResizeWeight(0.7);
        
        add(splitPane, BorderLayout.CENTER);
    }
    
    //-----------------------------
    // MÉTHODES PUBLIQUES
    //-----------------------------
    
    /**
     * Met à jour le tableau avec la liste des étudiants
     */
    public void mettreAJourTableau(ArrayList<Etudiant> etudiants) {
        modeleTable.setRowCount(0);
        
        for (Etudiant etudiant : etudiants) {
            String nomMaison = (etudiant.getSaMaison() != null) ? 
                etudiant.getSaMaison().getNom() : "Non affecté";
            
            Object[] ligne = {
                etudiant.getNom(),
                etudiant.getPrenom(),
                etudiant.getNationalite(),
                nomMaison
            };
            modeleTable.addRow(ligne);
        }
    }
    
    /**
     * Met à jour la liste des maisons dans la combobox
     */
    public void mettreAJourComboMaisons(ArrayList<MaisonEtudiant> maisons) {
        comboMaisons.removeAllItems();
        comboMaisons.addItem("Sélectionner une maison");
        
        for (MaisonEtudiant maison : maisons) {
            String texte = maison.getNom() + " (" + maison.placesDisponibles() + " places)";
            comboMaisons.addItem(texte);
        }
    }
    
    /**
     * Remplit le formulaire avec les données de l'étudiant sélectionné
     */
    public void remplirFormulaire(Etudiant etudiant) {
        if (etudiant != null) {
            champNom.setText(etudiant.getNom());
            champPrenom.setText(etudiant.getPrenom());
            champNationalite.setText(etudiant.getNationalite());
            
            if (etudiant.getSaMaison() != null) {
                String nomMaison = etudiant.getSaMaison().getNom();
                for (int i = 0; i < comboMaisons.getItemCount(); i++) {
                    if (comboMaisons.getItemAt(i).contains(nomMaison)) {
                        comboMaisons.setSelectedIndex(i);
                        break;
                    }
                }
            }
        }
    }
    
    /**
     * Vide le formulaire
     */
    public void viderFormulaire() {
        champNom.setText("");
        champPrenom.setText("");
        champNationalite.setText("");
        comboMaisons.setSelectedIndex(0);
    }
    
    /**
     * Obtient l'étudiant sélectionné dans le tableau
     */
    public int getEtudiantSelectionne() {
        return tableEtudiants.getSelectedRow();
    }
    
    //-----------------------------
    // ACCESSEURS
    //-----------------------------
    
    public JTextField getChampNom() {
        return champNom;
    }
    
    public JTextField getChampPrenom() {
        return champPrenom;
    }
    
    public JTextField getChampNationalite() {
        return champNationalite;
    }
    
    public JComboBox<String> getComboMaisons() {
        return comboMaisons;
    }
    
    public JTextField getChampRecherche() {
        return champRecherche;
    }
    
    public JButton getBoutonAjouter() {
        return boutonAjouter;
    }
    
    public JButton getBoutonModifier() {
        return boutonModifier;
    }
    
    public JButton getBoutonSupprimer() {
        return boutonSupprimer;
    }
    
    public JButton getBoutonAffecter() {
        return boutonAffecter;
    }
    
    public JButton getBoutonRechercher() {
        return boutonRechercher;
    }
    
    public JTable getTableEtudiants() {
        return tableEtudiants;
    }
    
    //-----------------------------
    // IMPLÉMENTATION INTERFACE
    //-----------------------------
    
    @Override
    public void redessiner() {
        repaint();
        revalidate();
    }
}