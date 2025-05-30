package vues;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.ArrayList;
import citeU.*;
import citeU.Menu;

/**
 * Vue pour la gestion des restaurations et menus
 * @author Arthur Pruvost
 */
public class VueGestionRestaurations extends JPanel implements InterfaceVue {
    
    //-----------------------------
    // CONSTANTES D'ACTIONS
    //-----------------------------
    public static final String ACTION_AJOUTER_MENU = "AJOUTER_MENU";
    public static final String ACTION_SUPPRIMER_MENU = "SUPPRIMER_MENU";
    public static final String ACTION_AFFECTER_MENU = "AFFECTER_MENU";
    public static final String ACTION_AJOUTER_RESTAURATION = "AJOUTER_RESTAURATION";
    public static final String ACTION_AFFECTER_RESTAURATION_MAISON = "AFFECTER_RESTAURATION_MAISON";
    
    //-----------------------------
    // ATTRIBUTS
    //-----------------------------
    private JTable tableRestaurations;
    private DefaultTableModel modeleTableRestaurations;
    private JTable tableMenus;
    private DefaultTableModel modeleTableMenus;
    
    // Formulaire restauration
    private JTextField champNomRestauration;
    private JTextField champCapaciteRestauration;
    private JComboBox<String> comboTypeRestauration;
    private JComboBox<String> comboMaisonsPourRestauration;
    private JButton boutonAjouterRestauration;
    private JButton boutonAffecterRestaurationMaison;
    
    // Formulaire menu
    private JTextField champNomMenu;
    private JTextField champEntree;
    private JTextField champPlat;
    private JTextField champDessert;
    private JTextField champPrix;
    private JComboBox<String> comboRestaurations;
    private JComboBox<String> comboTousLesMenus; // Nouvelle ComboBox pour tous les menus
    
    // Boutons
    private JButton boutonAjouterMenu;
    private JButton boutonSupprimerMenu;
    private JButton boutonAffecterMenu;
    
    //-----------------------------
    // CONSTRUCTEUR
    //-----------------------------
    public VueGestionRestaurations() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Gestion des Restaurations"));
        
        initialiserComposants();
        configurerLayout();
    }
    
    //-----------------------------
    // MÉTHODES D'INITIALISATION
    //-----------------------------
    
    private void initialiserComposants() {
        // Tableau des restaurations
        String[] colonnesRestaurations = {"Nom", "Type", "Capacité", "Nb Menus", "Maison"};
        modeleTableRestaurations = new DefaultTableModel(colonnesRestaurations, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tableRestaurations = new JTable(modeleTableRestaurations);
        tableRestaurations.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Tableau des menus
        String[] colonnesMenus = {"Nom", "Entrée", "Plat", "Dessert", "Prix"};
        modeleTableMenus = new DefaultTableModel(colonnesMenus, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tableMenus = new JTable(modeleTableMenus);
        tableMenus.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Champs du formulaire menu
        champNomMenu = new JTextField(15);
        champEntree = new JTextField(15);
        champPlat = new JTextField(15);
        champDessert = new JTextField(15);
        champPrix = new JTextField(15);
        comboRestaurations = new JComboBox<>();
        comboTousLesMenus = new JComboBox<>(); // Initialisation de la nouvelle ComboBox
        
        // Boutons menu
        boutonAjouterMenu = new JButton("Ajouter Menu");
        boutonSupprimerMenu = new JButton("Supprimer Menu");
        boutonAffecterMenu = new JButton("Affecter Menu");
        
        // Configuration des actions
        boutonAjouterMenu.setActionCommand(ACTION_AJOUTER_MENU);
        boutonSupprimerMenu.setActionCommand(ACTION_SUPPRIMER_MENU);
        boutonAffecterMenu.setActionCommand(ACTION_AFFECTER_MENU);
        
        // Champs du formulaire restauration
        champNomRestauration = new JTextField(15);
        champCapaciteRestauration = new JTextField(15);
        comboTypeRestauration = new JComboBox<>(new String[]{"RestoU", "Cafétéria"});
        comboMaisonsPourRestauration = new JComboBox<>();
        boutonAjouterRestauration = new JButton("Ajouter Restauration");
        boutonAffecterRestaurationMaison = new JButton("Affecter à Maison");
        boutonAjouterRestauration.setActionCommand(ACTION_AJOUTER_RESTAURATION);
        boutonAffecterRestaurationMaison.setActionCommand(ACTION_AFFECTER_RESTAURATION_MAISON);
    }
    
    private void configurerLayout() {
        // Panneau principal divisé en deux parties
        JSplitPane splitPrincipal = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        
        // Partie haute - Restaurations et leurs menus
        JPanel panneauHaut = new JPanel(new BorderLayout());
        panneauHaut.setBorder(BorderFactory.createTitledBorder("Restaurations et Menus"));
        
        // Split horizontal pour restaurations et menus
        JSplitPane splitRestaurations = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        
        // Panneau restaurations
        JPanel panneauRestaurations = new JPanel(new BorderLayout());
        panneauRestaurations.setBorder(BorderFactory.createTitledBorder("Restaurations"));
        JScrollPane scrollRestaurations = new JScrollPane(tableRestaurations);
        scrollRestaurations.setPreferredSize(new Dimension(500, 200));
        panneauRestaurations.add(scrollRestaurations, BorderLayout.CENTER);
        
        // Panneau menus de la restauration sélectionnée
        JPanel panneauMenusResto = new JPanel(new BorderLayout());
        panneauMenusResto.setBorder(BorderFactory.createTitledBorder("Menus de la Restauration"));
        JScrollPane scrollMenus = new JScrollPane(tableMenus);
        scrollMenus.setPreferredSize(new Dimension(400, 200));
        panneauMenusResto.add(scrollMenus, BorderLayout.CENTER);
        
        splitRestaurations.setLeftComponent(panneauRestaurations);
        splitRestaurations.setRightComponent(panneauMenusResto);
        splitRestaurations.setDividerLocation(500);
        
        panneauHaut.add(splitRestaurations, BorderLayout.CENTER);
        
        // Partie basse - Formulaires
        JPanel panneauBas = creerPanneauFormulaires();
        
        splitPrincipal.setTopComponent(panneauHaut);
        splitPrincipal.setBottomComponent(panneauBas);
        splitPrincipal.setDividerLocation(400);
        splitPrincipal.setResizeWeight(0.6);
        
        add(splitPrincipal, BorderLayout.CENTER);
    }
    
    private JPanel creerPanneauFormulaires() {
        JPanel panneauPrincipal = new JPanel(new GridLayout(1, 2, 10, 0));
        
        // Panneau gauche - Gestion des restaurations
        JPanel panneauGaucheRestauration = creerFormulaireRestauration();
        
        // Panneau droit - Gestion des menus
        JPanel panneauDroitMenu = creerFormulaireMenu();
        
        panneauPrincipal.add(panneauGaucheRestauration);
        panneauPrincipal.add(panneauDroitMenu);
        
        return panneauPrincipal;
    }
    
    private JPanel creerFormulaireRestauration() {
        JPanel formulaireRestaurant = new JPanel();
        formulaireRestaurant.setLayout(new BoxLayout(formulaireRestaurant, BoxLayout.Y_AXIS));
        formulaireRestaurant.setBorder(BorderFactory.createTitledBorder("Gestion des Restaurations"));
        
        JPanel panneauChamps = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Nom
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        panneauChamps.add(new JLabel("Nom:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panneauChamps.add(champNomRestauration, gbc);
        
        // Capacité
        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE;
        panneauChamps.add(new JLabel("Capacité:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panneauChamps.add(champCapaciteRestauration, gbc);
        
        // Type
        gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE;
        panneauChamps.add(new JLabel("Type:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panneauChamps.add(comboTypeRestauration, gbc);
        
        // Maison
        gbc.gridx = 0; gbc.gridy = 3; gbc.fill = GridBagConstraints.NONE;
        panneauChamps.add(new JLabel("Maison:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panneauChamps.add(comboMaisonsPourRestauration, gbc);
        
        formulaireRestaurant.add(panneauChamps);
        formulaireRestaurant.add(Box.createVerticalStrut(10));
        
        // Boutons
        JPanel panneauBoutons = new JPanel(new FlowLayout());
        panneauBoutons.add(boutonAjouterRestauration);
        panneauBoutons.add(boutonAffecterRestaurationMaison);
        
        formulaireRestaurant.add(panneauBoutons);
        
        return formulaireRestaurant;
    }
    
    private JPanel creerFormulaireMenu() {
        JPanel formulaire = new JPanel();
        formulaire.setLayout(new BoxLayout(formulaire, BoxLayout.Y_AXIS));
        formulaire.setBorder(BorderFactory.createTitledBorder("Gestion des Menus"));
        
        // Panneau des champs
        JPanel panneauChamps = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Ligne 1 - Nom du menu et Prix
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        panneauChamps.add(new JLabel("Nom du menu:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panneauChamps.add(champNomMenu, gbc);
        
        gbc.gridx = 2; gbc.fill = GridBagConstraints.NONE;
        panneauChamps.add(new JLabel("Prix:"), gbc);
        gbc.gridx = 3; gbc.fill = GridBagConstraints.HORIZONTAL;
        panneauChamps.add(champPrix, gbc);
        
        // Ligne 2 - Entrée et Plat
        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE;
        panneauChamps.add(new JLabel("Entrée:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panneauChamps.add(champEntree, gbc);
        
        gbc.gridx = 2; gbc.fill = GridBagConstraints.NONE;
        panneauChamps.add(new JLabel("Plat:"), gbc);
        gbc.gridx = 3; gbc.fill = GridBagConstraints.HORIZONTAL;
        panneauChamps.add(champPlat, gbc);
        
        // Ligne 3 - Dessert
        gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE;
        panneauChamps.add(new JLabel("Dessert:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.gridwidth = 3;
        panneauChamps.add(champDessert, gbc);
        
        formulaire.add(panneauChamps);
        formulaire.add(Box.createVerticalStrut(10));
        
        // Section affectation avec ComboBox des menus existants
        JPanel panneauAffectation = new JPanel(new GridBagLayout());
        panneauAffectation.setBorder(BorderFactory.createTitledBorder("Affectation Menu"));
        GridBagConstraints gbcAffect = new GridBagConstraints();
        gbcAffect.insets = new Insets(5, 5, 5, 5);
        
        // Menu à affecter
        gbcAffect.gridx = 0; gbcAffect.gridy = 0; gbcAffect.anchor = GridBagConstraints.WEST;
        panneauAffectation.add(new JLabel("Menu:"), gbcAffect);
        gbcAffect.gridx = 1; gbcAffect.fill = GridBagConstraints.HORIZONTAL;
        panneauAffectation.add(comboTousLesMenus, gbcAffect);
        
        // Restauration cible
        gbcAffect.gridx = 0; gbcAffect.gridy = 1; gbcAffect.fill = GridBagConstraints.NONE;
        panneauAffectation.add(new JLabel("Restauration:"), gbcAffect);
        gbcAffect.gridx = 1; gbcAffect.fill = GridBagConstraints.HORIZONTAL;
        panneauAffectation.add(comboRestaurations, gbcAffect);
        
        formulaire.add(panneauAffectation);
        formulaire.add(Box.createVerticalStrut(10));
        
        // Panneau des boutons
        JPanel panneauBoutons = new JPanel(new FlowLayout());
        panneauBoutons.add(boutonAjouterMenu);
        panneauBoutons.add(boutonSupprimerMenu);
        panneauBoutons.add(boutonAffecterMenu);
        
        formulaire.add(panneauBoutons);
        
        return formulaire;
    }
    
    //-----------------------------
    // MÉTHODES PUBLIQUES
    //-----------------------------
    
    /**
     * Met à jour le tableau des restaurations
     */
    public void mettreAJourTableauRestaurations(ArrayList<Restauration> restaurations) {
        modeleTableRestaurations.setRowCount(0);
        
        for (Restauration resto : restaurations) {
            String type = (resto instanceof RestoU) ? "RestoU" : "Cafétéria";
            String nomMaison = (resto.getSaMaison() != null) ? resto.getSaMaison().getNom() : "Non affectée";
            
            Object[] ligne = {
                resto.getNom(),
                type,
                resto.getCapacite(),
                resto.getSesMenus().size(),
                nomMaison
            };
            modeleTableRestaurations.addRow(ligne);
        }
    }
    
    /**
     * Met à jour le tableau des menus pour une restauration
     */
    public void mettreAJourTableauMenus(Restauration restauration) {
        modeleTableMenus.setRowCount(0);
        
        if (restauration != null) {
            for (Menu menu : restauration.getSesMenus()) {
                Object[] ligne = {
                    menu.getNom(),
                    menu.getEntree(),
                    menu.getPlat(),
                    menu.getDessert(),
                    String.format("%.2f €", menu.getPrix())
                };
                modeleTableMenus.addRow(ligne);
            }
        }
    }
    
    /**
     * Met à jour la liste des restaurations dans la combobox
     */
    public void mettreAJourComboRestaurations(ArrayList<Restauration> restaurations) {
        comboRestaurations.removeAllItems();
        comboRestaurations.addItem("Sélectionner une restauration");
        
        for (Restauration resto : restaurations) {
            comboRestaurations.addItem(resto.getNom());
        }
    }
    
    /**
     * Met à jour la liste de tous les menus dans la combobox
     */
    public void mettreAJourComboTousLesMenus(ArrayList<Menu> menus) {
        comboTousLesMenus.removeAllItems();
        comboTousLesMenus.addItem("Sélectionner un menu");
        
        for (Menu menu : menus) {
            comboTousLesMenus.addItem(menu.getNom() + " - " + String.format("%.2f €", menu.getPrix()));
        }
    }
    
    /**
     * Met à jour la liste des maisons dans la combobox
     */
    public void mettreAJourComboMaisons(ArrayList<Maison> maisons) {
        comboMaisonsPourRestauration.removeAllItems();
        comboMaisonsPourRestauration.addItem("Sélectionner une maison");
        
        for (Maison maison : maisons) {
            comboMaisonsPourRestauration.addItem(maison.getNom());
        }
    }
    
    /**
     * Vide le formulaire
     */
    public void viderFormulaire() {
        champNomMenu.setText("");
        champEntree.setText("");
        champPlat.setText("");
        champDessert.setText("");
        champPrix.setText("");
        if (comboRestaurations.getItemCount() > 0) {
            comboRestaurations.setSelectedIndex(0);
        }
        if (comboTousLesMenus.getItemCount() > 0) {
            comboTousLesMenus.setSelectedIndex(0);
        }
    }
    
    /**
     * Valide les données du formulaire
     */
    public boolean validerFormulaire() {
        if (champNomMenu.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Le nom du menu est obligatoire", 
                "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (champEntree.getText().trim().isEmpty() || 
            champPlat.getText().trim().isEmpty() || 
            champDessert.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tous les composants du menu sont obligatoires", 
                "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        try {
            Double.parseDouble(champPrix.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Le prix doit être un nombre valide", 
                "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    /**
     * Obtient la restauration sélectionnée dans le tableau
     */
    public int getRestaurationSelectionnee() {
        return tableRestaurations.getSelectedRow();
    }
    
    /**
     * Obtient le menu sélectionné dans le tableau
     */
    public int getMenuSelectionne() {
        return tableMenus.getSelectedRow();
    }
    
    //-----------------------------
    // ACCESSEURS
    //-----------------------------
    
    public JTextField getChampNomMenu() {
        return champNomMenu;
    }
    
    public JTextField getChampEntree() {
        return champEntree;
    }
    
    public JTextField getChampPlat() {
        return champPlat;
    }
    
    public JTextField getChampDessert() {
        return champDessert;
    }
    
    public JTextField getChampPrix() {
        return champPrix;
    }
    
    public JComboBox<String> getComboRestaurations() {
        return comboRestaurations;
    }
    
    public JComboBox<String> getComboTousLesMenus() {
        return comboTousLesMenus;
    }
    
    public JButton getBoutonAjouterMenu() {
        return boutonAjouterMenu;
    }
    
    public JButton getBoutonSupprimerMenu() {
        return boutonSupprimerMenu;
    }
    
    public JButton getBoutonAffecterMenu() {
        return boutonAffecterMenu;
    }
    
    public JTable getTableRestaurations() {
        return tableRestaurations;
    }
    
    public JTable getTableMenus() {
        return tableMenus;
    }
    
    public JTextField getChampNomRestauration() { 
        return champNomRestauration; 
    }
    
    public JTextField getChampCapaciteRestauration() { 
        return champCapaciteRestauration; 
    }
    
    public JComboBox<String> getComboTypeRestauration() { 
        return comboTypeRestauration; 
    }
    
    public JComboBox<String> getComboMaisonsPourRestauration() {
        return comboMaisonsPourRestauration;
    }
    
    public JButton getBoutonAjouterRestauration() { 
        return boutonAjouterRestauration; 
    }
    
    public JButton getBoutonAffecterRestaurationMaison() {
        return boutonAffecterRestaurationMaison;
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