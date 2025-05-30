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
    
    private JTextField champNomMaisonEtudiant;
    private JTextField champLocalisationEtudiant;
    private JTextField champDirecteurEtudiant;
    private JTextField champNomMaisonInt;
    private JTextField champLocalisationInt;
    private JTextField champDirecteurInt;
    
    // Boutons
    private JButton boutonAjouterMaisonEtudiant;
    private JButton boutonAjouterMaisonInternationale;
    private JButton boutonVoirDetails;
    
    //-----------------------------
    // CONSTRUCTEUR
    //-----------------------------
    
    /**
     * Construit une nouvelle vue de gestion des maisons.
     * Initialise les composants avec des onglets séparés pour chaque type de maison.
     */
    public VueGestionMaisons() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Gestion des Maisons"));
        
        initialiserComposants();
        configurerLayout();
    }
    
    //-----------------------------
    // MÉTHODES D'INITIALISATION
    //-----------------------------
    
    /**
     * Initialise tous les composants graphiques de la vue.
     * Crée les listes, zones de texte et formulaires pour chaque type de maison.
     */
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
        
        // Champs SÉPARÉS pour maisons d'étudiants
        champNomMaisonEtudiant = new JTextField(20);
        champLocalisationEtudiant = new JTextField(20);
        champDirecteurEtudiant = new JTextField(20);
        champNationalite = new JTextField(20);
        champNombreChambres = new JTextField(20);
        
        // Champs SÉPARÉS pour maisons internationales
        champNomMaisonInt = new JTextField(20);
        champLocalisationInt = new JTextField(20);
        champDirecteurInt = new JTextField(20);
        
        // Champs pour compatibilité avec le contrôleur
        champNomMaison = champNomMaisonEtudiant;
        champLocalisation = champLocalisationEtudiant;
        champDirecteur = champDirecteurEtudiant;
        
        // Boutons
        boutonAjouterMaisonEtudiant = new JButton("Ajouter Maison d'Étudiants");
        boutonAjouterMaisonInternationale = new JButton("Ajouter Maison Internationale");
        boutonVoirDetails = new JButton("Voir Détails");
        
        // Configuration des actions
        boutonAjouterMaisonEtudiant.setActionCommand("AJOUTER_MAISON_ETUDIANT");
        boutonAjouterMaisonInternationale.setActionCommand("AJOUTER_MAISON_INTERNATIONALE");
        boutonVoirDetails.setActionCommand(ACTION_VOIR_DETAILS);
    }
    
    /**
     * Configure la disposition des composants avec des onglets.
     * Crée un onglet pour les maisons d'étudiants et un pour les maisons internationales.
     */
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
    
    /**
     * Crée le panneau pour l'onglet des maisons d'étudiants.
     * Configure la liste, la zone de détails et le formulaire d'ajout.
     * 
     * @return Le panneau configuré pour les maisons d'étudiants
     */
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
    
    /**
     * Crée le panneau pour l'onglet des maisons internationales.
     * Configure la liste, la zone de détails et le formulaire d'ajout.
     * 
     * @return Le panneau configuré pour les maisons internationales
     */
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
    
    /**
     * Crée le formulaire d'ajout pour les maisons d'étudiants.
     * Inclut les champs spécifiques comme nationalité et nombre de chambres.
     * 
     * @return Le panneau contenant le formulaire d'ajout
     */
    private JPanel creerFormulaireAjoutMaisonEtudiant() {
        JPanel formulaire = new JPanel(new GridBagLayout());
        formulaire.setBorder(BorderFactory.createTitledBorder("Ajouter une Maison d'Étudiants"));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Nom
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        formulaire.add(new JLabel("Nom:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        formulaire.add(champNomMaisonEtudiant, gbc);
        
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
        formulaire.add(champLocalisationEtudiant, gbc);
        
        // Directeur
        gbc.gridx = 0; gbc.gridy = 4; gbc.fill = GridBagConstraints.NONE;
        formulaire.add(new JLabel("Directeur:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        formulaire.add(champDirecteurEtudiant, gbc);
        
        // Bouton
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        formulaire.add(boutonAjouterMaisonEtudiant, gbc);
        
        return formulaire;
    }
    
    /**
     * Crée le formulaire d'ajout pour les maisons internationales.
     * Formulaire simplifié avec les champs de base.
     * 
     * @return Le panneau contenant le formulaire d'ajout
     */
    private JPanel creerFormulaireAjoutMaisonInternationale() {
        JPanel formulaire = new JPanel(new GridBagLayout());
        formulaire.setBorder(BorderFactory.createTitledBorder("Ajouter une Maison Internationale"));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Nom
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        formulaire.add(new JLabel("Nom:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        formulaire.add(champNomMaisonInt, gbc);
        
        // Localisation
        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE;
        formulaire.add(new JLabel("Localisation:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        formulaire.add(champLocalisationInt, gbc);
        
        // Directeur
        gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE;
        formulaire.add(new JLabel("Directeur:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        formulaire.add(champDirecteurInt, gbc);
        
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
     * Met à jour la liste des maisons d'étudiants.
     * Affiche le nom, la nationalité et l'occupation de chaque maison.
     * 
     * @param maisons La liste des maisons d'étudiants à afficher
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
     * Met à jour la liste des maisons internationales.
     * Affiche le nom et le nombre de restaurations de chaque maison.
     * 
     * @param maisons La liste des maisons internationales à afficher
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
     * Affiche les détails d'une maison d'étudiants.
     * Montre toutes les informations et la liste des étudiants résidents.
     * 
     * @param maison La maison d'étudiants dont afficher les détails
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
     * Affiche les détails d'une maison internationale.
     * Montre les informations générales et la liste des restaurations.
     * 
     * @param maison La maison internationale dont afficher les détails
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
     * Vide tous les formulaires de la vue.
     * Remet à zéro tous les champs de saisie des deux types de maisons.
     */
    public void viderFormulaire() {
        champNomMaisonEtudiant.setText("");
        champLocalisationEtudiant.setText("");
        champDirecteurEtudiant.setText("");
        champNomMaisonInt.setText("");
        champLocalisationInt.setText("");
        champDirecteurInt.setText("");
        champNationalite.setText("");
        champNombreChambres.setText("");
    }
    
    //-----------------------------
    // ACCESSEURS
    //-----------------------------
    
    /**
     * Obtient la liste des maisons d'étudiants.
     * 
     * @return La JList affichant les maisons d'étudiants
     */
    public JList<String> getListeMaisonsEtudiants() {
        return listeMaisonsEtudiants;
    }
    
    /**
     * Obtient la liste des maisons internationales.
     * 
     * @return La JList affichant les maisons internationales
     */
    public JList<String> getListeMaisonsInternationales() {
        return listeMaisonsInternationales;
    }
    
    /**
     * Obtient le champ de saisie du nom de maison.
     * Retourne le champ approprié selon l'onglet actif.
     * 
     * @return Le champ de texte pour le nom de la maison
     */
    public JTextField getChampNomMaison() {
        // Retourne le champ approprié selon l'onglet actif
        if (ongletsMaisons.getSelectedIndex() == 0) {
            return champNomMaisonEtudiant;
        } else {
            return champNomMaisonInt;
        }
    }
    
    /**
     * Obtient le champ de saisie de la localisation.
     * Retourne le champ approprié selon l'onglet actif.
     * 
     * @return Le champ de texte pour la localisation
     */
    public JTextField getChampLocalisation() {
        // Retourne le champ approprié selon l'onglet actif
        if (ongletsMaisons.getSelectedIndex() == 0) {
            return champLocalisationEtudiant;
        } else {
            return champLocalisationInt;
        }
    }
    

	/**
	 * Obtient le champ de saisie du directeur.
	 * Retourne le champ approprié selon l'onglet actif.
	 * 
	 * @return Le champ de texte pour le nom du directeur
	 */
    public JTextField getChampDirecteur() {
        // Retourne le champ approprié selon l'onglet actif
        if (ongletsMaisons.getSelectedIndex() == 0) {
            return champDirecteurEtudiant;
        } else {
            return champDirecteurInt;
        }
    }
    
    /**
     * Obtient le champ de saisie de la nationalité.
     * Spécifique aux maisons d'étudiants.
     * 
     * @return Le champ de texte pour la nationalité
     */
    public JTextField getChampNationalite() {
        return champNationalite;
    }
    
    /**
     * Obtient le champ de saisie du nombre de chambres.
     * Spécifique aux maisons d'étudiants.
     * 
     * @return Le champ de texte pour le nombre de chambres
     */
    public JTextField getChampNombreChambres() {
        return champNombreChambres;
    }
    

	/**
	 * Obtient le bouton d'ajout de maison d'étudiants.
	 * 
	 * @return Le bouton pour ajouter une maison d'étudiants
	 */
    public JButton getBoutonAjouterMaisonEtudiant() {
        return boutonAjouterMaisonEtudiant;
    }
    

	/**
	 * Obtient le bouton d'ajout de maison internationale.
	 * 
	 * @return Le bouton pour ajouter une maison internationale
	 */
    public JButton getBoutonAjouterMaisonInternationale() {
        return boutonAjouterMaisonInternationale;
    }
    
    /**
     * Obtient le champ nom spécifique aux maisons d'étudiants.
     * 
     * @return Le champ de texte pour le nom des maisons d'étudiants
     */
    public JTextField getChampNomMaisonEtudiant() { return champNomMaisonEtudiant; }
    
    /**
     * Obtient le champ localisation spécifique aux maisons d'étudiants.
     * 
     * @return Le champ de texte pour la localisation des maisons d'étudiants
     */
    public JTextField getChampLocalisationEtudiant() { return champLocalisationEtudiant; }
    
    /**
     * Obtient le champ directeur spécifique aux maisons d'étudiants.
     * 
     * @return Le champ de texte pour le directeur des maisons d'étudiants
     */
    public JTextField getChampDirecteurEtudiant() { return champDirecteurEtudiant; }
    
    /**
     * Obtient le champ nom spécifique aux maisons internationales.
     * 
     * @return Le champ de texte pour le nom des maisons internationales
     */
    public JTextField getChampNomMaisonInt() { return champNomMaisonInt; }
    
    /**
     * Obtient le champ localisation spécifique aux maisons internationales.
     * 
     * @return Le champ de texte pour la localisation des maisons internationales
     */
    public JTextField getChampLocalisationInt() { return champLocalisationInt; }
    
    /**
     * Obtient le champ directeur spécifique aux maisons internationales.
     * 
     * @return Le champ de texte pour le directeur des maisons internationales
     */
    public JTextField getChampDirecteurInt() { return champDirecteurInt; }
    
    //-----------------------------
    // IMPLÉMENTATION INTERFACE
    //-----------------------------
    
    /**
     * Redessine la vue en forçant la mise à jour graphique.
     * Implémentation de l'interface InterfaceVue.
     */
    @Override
    public void redessiner() {
        repaint();
        revalidate();
    }
}