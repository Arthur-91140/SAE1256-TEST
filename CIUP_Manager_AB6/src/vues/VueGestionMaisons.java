package vues;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import citeU.*;

/**
 * Vue pour la gestion des maisons avec onglets
 * @author Maxence Bailly
 */
public class VueGestionMaisons extends JPanel implements InterfaceVue {
    
    //-----------------------------
    // CONSTANTES D'ACTIONS
    //-----------------------------
    public static final String ACTION_AJOUTER_MAISON = "AJOUTER_MAISON";
    public static final String ACTION_VOIR_DETAILS = "VOIR_DETAILS";
    
    //-----------------------------
    // ATTRIBUTS
    //-----------------------------
    private JTabbedPane ongletsMaisons;
    
    // Onglet Maisons d'Étudiants
    private JList<String> listeMaisonsEtudiants;
    private DefaultListModel<String> modeleMaisonsEtudiants;
    private JTextArea detailsMaisonEtudiant;
    
    // Onglet Maisons Internationales
    private JList<String> listeMaisonsInternationales;
    private DefaultListModel<String> modeleMaisonsInternationales;
    private JTextArea detailsMaisonInternationale;
    
    // Formulaire ajout maison
    private JTextField champNomMaison;
    private JTextField champLocalisation;
    private JTextField champDirecteur;
    private JTextField champNationalite;
    private JTextField champNombreChambres;
    
    // Boutons
    private JButton boutonAjouterMaisonEtudiant;
    private JButton boutonAjouterMaisonInternationale;
    private JButton boutonVoirDetails;
    
    //-----------------------------
    // CONSTRUCTEUR
    //-----------------------------
    public VueGestionMaisons() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Gestion des Maisons"));
        
        initialiserComposants();
        configurerLayout();
    }
    
    //-----------------------------
    // MÉTHODES D'INITIALISATION
    //-----------------------------
    
    private void initialiserComposants() {
        // Création des modèles de listes
        modeleMaisonsEtudiants = new DefaultListModel<>();
        modeleMaisonsInternationales = new DefaultListModel<>();
        
        // Création des listes
        listeMaisonsEtudiants = new JList<>(modeleMaisonsEtudiants);
        listeMaisonsEtudiants.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        listeMaisonsInternationales = new JList<>(modeleMaisonsInternationales);
        listeMaisonsInternationales.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Zones de détails
        detailsMaisonEtudiant = new JTextArea(10, 30);
        detailsMaisonEtudiant.setEditable(false);
        detailsMaisonEtudiant.setBorder(BorderFactory.createTitledBorder("Détails de la Maison"));
        
        detailsMaisonInternationale = new JTextArea(10, 30);
        detailsMaisonInternationale.setEditable(false);
        detailsMaisonInternationale.setBorder(BorderFactory.createTitledBorder("Détails de la Maison"));
        
        // Champs du formulaire
        champNomMaison = new JTextField(20);
        champLocalisation = new JTextField(20);
        champDirecteur = new JTextField(20);
        champNationalite = new JTextField(20);
        champNombreChambres = new JTextField(20);
        
        // Boutons
        boutonAjouterMaisonEtudiant = new JButton("Ajouter Maison d'Étudiants");
        boutonAjouterMaisonInternationale = new JButton("Ajouter Maison Internationale");
        boutonVoirDetails = new JButton("Voir Détails");
        
        // Configuration des actions
        boutonAjouterMaisonEtudiant.setActionCommand("AJOUTER_MAISON_ETUDIANT");
        boutonAjouterMaisonInternationale.setActionCommand("AJOUTER_MAISON_INTERNATIONALE");
        boutonVoirDetails.setActionCommand(ACTION_VOIR_DETAILS);
    }
    
    private void configurerLayout() {
        ongletsMaisons = new JTabbedPane();
        
        // Onglet Maisons d'Étudiants
        JPanel panneauMaisonsEtudiants = creerPanneauMaisonsEtudiants();
        ongletsMaisons.addTab("Maisons d'Étudiants", panneauMaisonsEtudiants);
        
        // Onglet Maisons Internationales
        JPanel panneauMaisonsInternationales = creerPanneauMaisonsInternationales();
        ongletsMaisons.addTab("Maisons Internationales", panneauMaisonsInternationales);
        
        add(ongletsMaisons, BorderLayout.CENTER);
    }
    
    private JPanel creerPanneauMaisonsEtudiants() {
        JPanel panneau = new JPanel(new BorderLayout());
        
        // Panneau gauche - Liste
        JPanel panneauGauche = new JPanel(new BorderLayout());
        panneauGauche.setBorder(BorderFactory.createTitledBorder("Maisons d'Étudiants"));
        
        JScrollPane scrollListe = new JScrollPane(listeMaisonsEtudiants);
        scrollListe.setPreferredSize(new Dimension(300, 200));
        panneauGauche.add(scrollListe, BorderLayout.CENTER);
        
        // Panneau droit - Détails et formulaire
        JPanel panneauDroit = new JPanel();
        panneauDroit.setLayout(new BoxLayout(panneauDroit, BoxLayout.Y_AXIS));
        
        // Zone de détails
        JScrollPane scrollDetails = new JScrollPane(detailsMaisonEtudiant);
        panneauDroit.add(scrollDetails);
        
        panneauDroit.add(Box.createVerticalStrut(10));
        
        // Formulaire d'ajout
        JPanel formulaire = creerFormulaireAjoutMaisonEtudiant();
        panneauDroit.add(formulaire);
        
        // SplitPane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(panneauGauche);
        splitPane.setRightComponent(panneauDroit);
        splitPane.setDividerLocation(350);
        
        panneau.add(splitPane, BorderLayout.CENTER);
        
        return panneau;
    }
    
    private JPanel creerPanneauMaisonsInternationales() {
        JPanel panneau = new JPanel(new BorderLayout());
        
        // Panneau gauche - Liste
        JPanel panneauGauche = new JPanel(new BorderLayout());
        panneauGauche.setBorder(BorderFactory.createTitledBorder("Maisons Internationales"));
        
        JScrollPane scrollListe = new JScrollPane(listeMaisonsInternationales);
        scrollListe.setPreferredSize(new Dimension(300, 200));
        panneauGauche.add(scrollListe, BorderLayout.CENTER);
        
        // Panneau droit - Détails et formulaire
        JPanel panneauDroit = new JPanel();
        panneauDroit.setLayout(new BoxLayout(panneauDroit, BoxLayout.Y_AXIS));
        
        // Zone de détails
        JScrollPane scrollDetails = new JScrollPane(detailsMaisonInternationale);
        panneauDroit.add(scrollDetails);
        
        panneauDroit.add(Box.createVerticalStrut(10));
        
        // Formulaire d'ajout
        JPanel formulaire = creerFormulaireAjoutMaisonInternationale();
        panneauDroit.add(formulaire);
        
        // SplitPane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(panneauGauche);
        splitPane.setRightComponent(panneauDroit);
        splitPane.setDividerLocation(350);
        
        panneau.add(splitPane, BorderLayout.CENTER);
        
        return panneau;
    }
    
    private JPanel creerFormulaireAjoutMaisonEtudiant() {
        JPanel formulaire = new JPanel(new GridBagLayout());
        formulaire.setBorder(BorderFactory.createTitledBorder("Ajouter une Maison d'Étudiants"));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Nom
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        formulaire.add(new JLabel("Nom:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        formulaire.add(champNomMaison, gbc);
        
        // Nationalité
        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE;
        formulaire.add(new JLabel("Nationalité:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        formulaire.add(champNationalite, gbc);
        
        // Nombre de chambres
        gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE;
        formulaire.add(new JLabel("Nb Chambres:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        formulaire.add(champNombreChambres, gbc);
        
        // Localisation
        gbc.gridx = 0; gbc.gridy = 3; gbc.fill = GridBagConstraints.NONE;
        formulaire.add(new JLabel("Localisation:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        formulaire.add(champLocalisation, gbc);
        
        // Directeur
        gbc.gridx = 0; gbc.gridy = 4; gbc.fill = GridBagConstraints.NONE;
        formulaire.add(new JLabel("Directeur:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        formulaire.add(champDirecteur, gbc);
        
        // Bouton
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        formulaire.add(boutonAjouterMaisonEtudiant, gbc);
        
        return formulaire;
    }
    
    private JPanel creerFormulaireAjoutMaisonInternationale() {
        JPanel formulaire = new JPanel(new GridBagLayout());
        formulaire.setBorder(BorderFactory.createTitledBorder("Ajouter une Maison Internationale"));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Nom
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        formulaire.add(new JLabel("Nom:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        formulaire.add(champNomMaison, gbc);
        
        // Localisation
        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE;
        formulaire.add(new JLabel("Localisation:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        formulaire.add(champLocalisation, gbc);
        
        // Directeur
        gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE;
        formulaire.add(new JLabel("Directeur:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        formulaire.add(champDirecteur, gbc);
        
        // Bouton
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        formulaire.add(boutonAjouterMaisonInternationale, gbc);
        
        return formulaire;
    }
    
    //-----------------------------
    // MÉTHODES PUBLIQUES
    //-----------------------------
    
    /**
     * Met à jour la liste des maisons d'étudiants
     */
    public void mettreAJourMaisonsEtudiants(ArrayList<MaisonEtudiant> maisons) {
        modeleMaisonsEtudiants.clear();
        for (MaisonEtudiant maison : maisons) {
            String texte = maison.getNom() + " - " + maison.getNationalite() + 
                          " (" + maison.getSesEtudiants().size() + "/" + 
                          maison.getNombreChambres() + ")";
            modeleMaisonsEtudiants.addElement(texte);
        }
    }
    
    /**
     * Met à jour la liste des maisons internationales
     */
    public void mettreAJourMaisonsInternationales(ArrayList<MaisonInternationale> maisons) {
        modeleMaisonsInternationales.clear();
        for (MaisonInternationale maison : maisons) {
            String texte = maison.getNom() + " - " + 
                          maison.getSesRestaurations().size() + " restaurations";
            modeleMaisonsInternationales.addElement(texte);
        }
    }
    
    /**
     * Affiche les détails d'une maison d'étudiants
     */
    public void afficherDetailsMaisonEtudiant(MaisonEtudiant maison) {
        if (maison != null) {
            StringBuilder details = new StringBuilder();
            details.append("Nom: ").append(maison.getNom()).append("\n");
            details.append("Nationalité: ").append(maison.getNationalite()).append("\n");
            details.append("Directeur: ").append(maison.getNomDirecteur()).append("\n");
            details.append("Localisation: ").append(maison.getLocalisation()).append("\n");
            details.append("Chambres: ").append(maison.getNombreChambres()).append("\n");
            details.append("Occupées: ").append(maison.getSesEtudiants().size()).append("\n");
            details.append("Disponibles: ").append(maison.placesDisponibles()).append("\n\n");
            
            details.append("Étudiants:\n");
            for (Etudiant etudiant : maison.getSesEtudiants()) {
                details.append("- ").append(etudiant.getPrenom()).append(" ")
                       .append(etudiant.getNom()).append("\n");
            }
            
            detailsMaisonEtudiant.setText(details.toString());
        }
    }
    
    /**
     * Affiche les détails d'une maison internationale
     */
    public void afficherDetailsMaisonInternationale(MaisonInternationale maison) {
        if (maison != null) {
            StringBuilder details = new StringBuilder();
            details.append("Nom: ").append(maison.getNom()).append("\n");
            details.append("Directeur: ").append(maison.getNomDirecteur()).append("\n");
            details.append("Localisation: ").append(maison.getLocalisation()).append("\n\n");
            
            details.append("Restaurations:\n");
            for (Restauration resto : maison.getSesRestaurations()) {
                details.append("- ").append(resto.getNom())
                       .append(" (").append(resto.getCapacite()).append(" places)\n");
            }
            
            detailsMaisonInternationale.setText(details.toString());
        }
    }
    
    /**
     * Vide le formulaire
     */
    public void viderFormulaire() {
        champNomMaison.setText("");
        champLocalisation.setText("");
        champDirecteur.setText("");
        champNationalite.setText("");
        champNombreChambres.setText("");
    }
    
    //-----------------------------
    // ACCESSEURS
    //-----------------------------
    
    public JList<String> getListeMaisonsEtudiants() {
        return listeMaisonsEtudiants;
    }
    
    public JList<String> getListeMaisonsInternationales() {
        return listeMaisonsInternationales;
    }
    
    public JTextField getChampNomMaison() {
        return champNomMaison;
    }
    
    public JTextField getChampLocalisation() {
        return champLocalisation;
    }
    
    public JTextField getChampDirecteur() {
        return champDirecteur;
    }
    
    public JTextField getChampNationalite() {
        return champNationalite;
    }
    
    public JTextField getChampNombreChambres() {
        return champNombreChambres;
    }
    
    public JButton getBoutonAjouterMaisonEtudiant() {
        return boutonAjouterMaisonEtudiant;
    }
    
    public JButton getBoutonAjouterMaisonInternationale() {
        return boutonAjouterMaisonInternationale;
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