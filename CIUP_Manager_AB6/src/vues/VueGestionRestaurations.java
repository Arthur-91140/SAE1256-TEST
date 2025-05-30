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
    
    //-----------------------------
    // ATTRIBUTS
    //-----------------------------
    private JTable tableRestaurations;
    private DefaultTableModel modeleTableRestaurations;
    private JTable tableMenus;
    private DefaultTableModel modeleTableMenus;
    
    private JTextField champNomRestauration;
    private JTextField champCapaciteRestauration;
    private JComboBox<String> comboTypeRestauration;
    private JButton boutonAjouterRestauration;
    
    // Formulaire menu
    private JTextField champNomMenu;
    private JTextField champEntree;
    private JTextField champPlat;
    private JTextField champDessert;
    private JTextField champPrix;
    private JComboBox<String> comboRestaurations;
    
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
    
    /**
     * Initialise tous les composants graphiques de la vue.
     * 
     * Cette méthode crée les tableaux, formulaires et boutons nécessaires
     * à la gestion des restaurations et menus.
     */
    private void initialiserComposants() {
        // Tableau des restaurations
        String[] colonnesRestaurations = {"Nom", "Type", "Capacité", "Nb Menus"};
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
        
        // Champs du formulaire
        champNomMenu = new JTextField(15);
        champEntree = new JTextField(15);
        champPlat = new JTextField(15);
        champDessert = new JTextField(15);
        champPrix = new JTextField(15);
        comboRestaurations = new JComboBox<>();
        
        // Boutons
        boutonAjouterMenu = new JButton("Ajouter Menu");
        boutonSupprimerMenu = new JButton("Supprimer Menu");
        boutonAffecterMenu = new JButton("Affecter Menu");
        
        // Configuration des actions
        boutonAjouterMenu.setActionCommand(ACTION_AJOUTER_MENU);
        boutonSupprimerMenu.setActionCommand(ACTION_SUPPRIMER_MENU);
        boutonAffecterMenu.setActionCommand(ACTION_AFFECTER_MENU);
        
        
        champNomRestauration = new JTextField(15);
        champCapaciteRestauration = new JTextField(15);
        comboTypeRestauration = new JComboBox<>(new String[]{"RestoU", "Cafétéria"});
        boutonAjouterRestauration = new JButton("Ajouter Restauration");
        boutonAjouterRestauration.setActionCommand(ACTION_AJOUTER_RESTAURATION);
    }
    
    /**
     * Configure la disposition générale de l'interface utilisateur.
     * 
     * Organise les composants en utilisant des JSplitPane pour diviser
     * l'interface en sections restaurations, menus et formulaires.
     */
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
        scrollRestaurations.setPreferredSize(new Dimension(400, 200));
        panneauRestaurations.add(scrollRestaurations, BorderLayout.CENTER);
        
        // Panneau menus de la restauration sélectionnée
        JPanel panneauMenusResto = new JPanel(new BorderLayout());
        panneauMenusResto.setBorder(BorderFactory.createTitledBorder("Menus de la Restauration"));
        JScrollPane scrollMenus = new JScrollPane(tableMenus);
        scrollMenus.setPreferredSize(new Dimension(400, 200));
        panneauMenusResto.add(scrollMenus, BorderLayout.CENTER);
        
        splitRestaurations.setLeftComponent(panneauRestaurations);
        splitRestaurations.setRightComponent(panneauMenusResto);
        splitRestaurations.setDividerLocation(400);
        
        panneauHaut.add(splitRestaurations, BorderLayout.CENTER);
        
        // Partie basse - Formulaire d'ajout de menu
        JPanel panneauBas = creerFormulaireMenu();
        
        splitPrincipal.setTopComponent(panneauHaut);
        splitPrincipal.setBottomComponent(panneauBas);
        splitPrincipal.setDividerLocation(400);
        splitPrincipal.setResizeWeight(0.6);
        
        add(splitPrincipal, BorderLayout.CENTER);
    }
    

	/**
	 * Crée le formulaire de gestion des menus et restaurations.
	 * 
	 * @return Le panneau contenant les formulaires d'ajout de menu et de restauration
	 */
    private JPanel creerFormulaireMenu() {
        JPanel formulairePrincipal = new JPanel();
        formulairePrincipal.setLayout(new BoxLayout(formulairePrincipal, BoxLayout.Y_AXIS));
        
        // Formulaire restauration
        JPanel formulaireRestaurant = new JPanel(new GridBagLayout());
        formulaireRestaurant.setBorder(BorderFactory.createTitledBorder("Ajouter une Restauration"));
        
        GridBagConstraints gbcResto = new GridBagConstraints();
        gbcResto.insets = new Insets(5, 5, 5, 5);
        
        gbcResto.gridx = 0; gbcResto.gridy = 0;
        formulaireRestaurant.add(new JLabel("Nom:"), gbcResto);
        gbcResto.gridx = 1;
        formulaireRestaurant.add(champNomRestauration, gbcResto);
        
        gbcResto.gridx = 0; gbcResto.gridy = 1;
        formulaireRestaurant.add(new JLabel("Capacité:"), gbcResto);
        gbcResto.gridx = 1;
        formulaireRestaurant.add(champCapaciteRestauration, gbcResto);
        
        gbcResto.gridx = 0; gbcResto.gridy = 2;
        formulaireRestaurant.add(new JLabel("Type:"), gbcResto);
        gbcResto.gridx = 1;
        formulaireRestaurant.add(comboTypeRestauration, gbcResto);
        
        gbcResto.gridx = 0; gbcResto.gridy = 3; gbcResto.gridwidth = 2;
        formulaireRestaurant.add(boutonAjouterRestauration, gbcResto);
        
        formulairePrincipal.add(formulaireRestaurant);
        formulairePrincipal.add(Box.createVerticalStrut(10));
    	
        JPanel formulaire = new JPanel();
        formulaire.setLayout(new BoxLayout(formulaire, BoxLayout.Y_AXIS));
        formulaire.setBorder(BorderFactory.createTitledBorder("Gestion des Menus"));
        
        // Panneau des champs
        JPanel panneauChamps = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Ligne 1 - Nom du menu
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        panneauChamps.add(new JLabel("Nom du menu:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panneauChamps.add(champNomMenu, gbc);
        
        // Ligne 1 suite - Prix
        gbc.gridx = 2; gbc.fill = GridBagConstraints.NONE;
        panneauChamps.add(new JLabel("Prix:"), gbc);
        gbc.gridx = 3; gbc.fill = GridBagConstraints.HORIZONTAL;
        panneauChamps.add(champPrix, gbc);
        
        // Ligne 2 - Entrée
        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE;
        panneauChamps.add(new JLabel("Entrée:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panneauChamps.add(champEntree, gbc);
        
        // Ligne 2 suite - Plat
        gbc.gridx = 2; gbc.fill = GridBagConstraints.NONE;
        panneauChamps.add(new JLabel("Plat:"), gbc);
        gbc.gridx = 3; gbc.fill = GridBagConstraints.HORIZONTAL;
        panneauChamps.add(champPlat, gbc);
        
        // Ligne 3 - Dessert
        gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE;
        panneauChamps.add(new JLabel("Dessert:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panneauChamps.add(champDessert, gbc);
        
        // Ligne 3 suite - Restauration
        gbc.gridx = 2; gbc.fill = GridBagConstraints.NONE;
        panneauChamps.add(new JLabel("Restauration:"), gbc);
        gbc.gridx = 3; gbc.fill = GridBagConstraints.HORIZONTAL;
        panneauChamps.add(comboRestaurations, gbc);
        
        formulaire.add(panneauChamps);
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
     * Vide tous les champs du formulaire de menu.
     * 
     * Remet les champs de saisie à leur état initial et
     * repose la sélection de la combobox sur l'index 0.
     */
    public void mettreAJourTableauRestaurations(ArrayList<Restauration> restaurations) {
        modeleTableRestaurations.setRowCount(0);
        
        for (Restauration resto : restaurations) {
            String type = (resto instanceof RestoU) ? "RestoU" : "Cafétéria";
            
            Object[] ligne = {
                resto.getNom(),
                type,
                resto.getCapacite(),
                resto.getSesMenus().size()
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
     * Vide tous les champs du formulaire de menu.
     * 
     * Remet les champs de saisie à leur état initial et
     * repose la sélection de la combobox sur l'index 0.
     */	
    public void viderFormulaire() {
        champNomMenu.setText("");
        champEntree.setText("");
        champPlat.setText("");
        champDessert.setText("");
        champPrix.setText("");
        comboRestaurations.setSelectedIndex(0);
    }
    
    /**
     * Valide les données saisies dans le formulaire de menu.
     * 
     * Vérifie que tous les champs obligatoires sont remplis,
     * que le prix est un nombre valide et qu'une restauration est sélectionnée.
     * 
     * @return true si le formulaire est valide, false sinon
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
        
        if (comboRestaurations.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une restauration", 
                "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    /**
     * Obtient l'index de la restauration sélectionnée dans le tableau.
     * 
     * @return L'index de la ligne sélectionnée, -1 si aucune sélection
     */
    public int getRestaurationSelectionnee() {
        return tableRestaurations.getSelectedRow();
    }
    
    /**
     * Obtient l'index du menu sélectionné dans le tableau.
     * 
     * @return L'index de la ligne sélectionnée, -1 si aucune sélection
     */
    public int getMenuSelectionne() {
        return tableMenus.getSelectedRow();
    }
    
    //-----------------------------
    // ACCESSEURS
    //-----------------------------
    
    /**
     * Obtient le champ de saisie du nom du menu.
     * 
     * @return Le JTextField pour le nom du menu
     */
    public JTextField getChampNomMenu() {
        return champNomMenu;
    }
    
    /**
     * Obtient le champ de saisie de l'entrée.
     * 
     * @return Le JTextField pour l'entrée
     */
    public JTextField getChampEntree() {
        return champEntree;
    }
    
    /**
     * Obtient le champ de saisie du plat principal.
     * 
     * @return Le JTextField pour le plat
     */
    public JTextField getChampPlat() {
        return champPlat;
    }
    
    /**
     * Obtient le champ de saisie du dessert.
     * 
     * @return Le JTextField pour le dessert
     */
    public JTextField getChampDessert() {
        return champDessert;
    }
    
    /**
     * Obtient le champ de saisie du prix.
     * 
     * @return Le JTextField pour le prix
     */
    public JTextField getChampPrix() {
        return champPrix;
    }
    
    /**
     * Obtient la combobox de sélection des restaurations.
     * 
     * @return La JComboBox contenant la liste des restaurations
     */
    public JComboBox<String> getComboRestaurations() {
        return comboRestaurations;
    }
    
    /**
     * Obtient le bouton d'ajout de menu.
     * 
     * @return Le JButton pour ajouter un menu
     */
    public JButton getBoutonAjouterMenu() {
        return boutonAjouterMenu;
    }
    
    /**
     * Obtient le bouton de suppression de menu.
     * 
     * @return Le JButton pour supprimer un menu
     */
    public JButton getBoutonSupprimerMenu() {
        return boutonSupprimerMenu;
    }
    
    /**
     * Obtient le bouton d'affectation de menu.
     * 
     * @return Le JButton pour affecter un menu
     */
    public JButton getBoutonAffecterMenu() {
        return boutonAffecterMenu;
    }
    
    /**
     * Obtient le tableau des restaurations.
     * 
     * @return La JTable affichant les restaurations
     */
    public JTable getTableRestaurations() {
        return tableRestaurations;
    }
    
	/**
	 * Obtient le tableau des menus.
	 * 
	 * @return La JTable affichant les menus
	 */
    public JTable getTableMenus() {
        return tableMenus;
    }
    
    /**
     * Obtient le champ de saisie du nom de restauration.
     * 
     * @return Le JTextField pour le nom de restauration
     */
    public JTextField getChampNomRestauration() { return champNomRestauration; }
    
    /**
     * Obtient le champ de saisie de la capacité de restauration.
     * 
     * @return Le JTextField pour la capacité
     */
    public JTextField getChampCapaciteRestauration() { return champCapaciteRestauration; }
    
    /**
     * Obtient la combobox de sélection du type de restauration.
     * 
     * @return La JComboBox pour le type (RestoU/Cafétéria)
     */
    public JComboBox<String> getComboTypeRestauration() { return comboTypeRestauration; }
    
    /**
     * Obtient le bouton d'ajout de restauration.
     * 
     * @return Le JButton pour ajouter une restauration
     */
    public JButton getBoutonAjouterRestauration() { return boutonAjouterRestauration; }
    
    //-----------------------------
    // IMPLÉMENTATION INTERFACE
    //-----------------------------
    
    @Override
    public void redessiner() {
        repaint();
        revalidate();
    }
}