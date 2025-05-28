package vues;

import javax.swing.*;
import java.awt.*;

/**
 * Vue principale de l'application avec navigation par onglets
 * @author Maxence Bailly
 */
public class VuePrincipale extends JFrame implements InterfaceVue {
    
    //-----------------------------
    // CONSTANTES
    //-----------------------------
    public static final String ACTION_ETUDIANTS = "ETUDIANTS";
    public static final String ACTION_MAISONS = "MAISONS";
    public static final String ACTION_RESTAURATIONS = "RESTAURATIONS";
    public static final String ACTION_STATISTIQUES = "STATISTIQUES";
    
    //-----------------------------
    // ATTRIBUTS
    //-----------------------------
    private JMenuBar menuBar;
    private JMenu menuFichier;
    private JMenu menuAide;
    private JMenuItem menuSauvegarder;
    private JMenuItem menuQuitter;
    private JMenuItem menuAPropos;
    
    private JTabbedPane ongletsPrincipaux;
    private VueGestionEtudiants vueEtudiants;
    private VueGestionMaisons vueMaisons;
    private VueGestionRestaurations vueRestaurations;
    private VueStatistiques vueStatistiques;
    
    //-----------------------------
    // CONSTRUCTEUR
    //-----------------------------
    public VuePrincipale() {
        initialiserInterface();
        configurerFenetre();
    }
    
    //-----------------------------
    // MÉTHODES D'INITIALISATION
    //-----------------------------
    
    private void initialiserInterface() {
        // Création du menu
        creerMenu();
        
        // Création des vues
        vueEtudiants = new VueGestionEtudiants();
        vueMaisons = new VueGestionMaisons();
        vueRestaurations = new VueGestionRestaurations();
        vueStatistiques = new VueStatistiques();
        
        // Création des onglets
        ongletsPrincipaux = new JTabbedPane();
        ongletsPrincipaux.addTab("Étudiants", new ImageIcon(), vueEtudiants, "Gestion des étudiants");
        ongletsPrincipaux.addTab("Maisons", new ImageIcon(), vueMaisons, "Gestion des maisons");
        ongletsPrincipaux.addTab("Restaurations", new ImageIcon(), vueRestaurations, "Gestion des restaurations");
        ongletsPrincipaux.addTab("Statistiques", new ImageIcon(), vueStatistiques, "Statistiques globales");
        
        // Ajout à la fenêtre
        add(ongletsPrincipaux, BorderLayout.CENTER);
        
        // Panneau d'accueil
        ajouterPanneauAccueil();
    }
    
    private void creerMenu() {
        menuBar = new JMenuBar();
        
        // Menu Fichier
        menuFichier = new JMenu("Fichier");
        menuSauvegarder = new JMenuItem("Sauvegarder");
        menuQuitter = new JMenuItem("Quitter");
        
        menuSauvegarder.setActionCommand("SAUVEGARDER");
        menuQuitter.setActionCommand("QUITTER");
        
        menuFichier.add(menuSauvegarder);
        menuFichier.addSeparator();
        menuFichier.add(menuQuitter);
        
        // Menu Aide
        menuAide = new JMenu("Aide");
        menuAPropos = new JMenuItem("À propos");
        menuAPropos.setActionCommand("A_PROPOS");
        menuAide.add(menuAPropos);
        
        // Ajout des menus
        menuBar.add(menuFichier);
        menuBar.add(menuAide);
        
        setJMenuBar(menuBar);
    }
    
    private void ajouterPanneauAccueil() {
        JPanel panneauAccueil = new JPanel(new BorderLayout());
        panneauAccueil.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Titre
        JLabel titre = new JLabel("CIUP Manager", SwingConstants.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 24));
        titre.setForeground(new Color(0, 102, 204));
        panneauAccueil.add(titre, BorderLayout.NORTH);
        
        // Description
        JTextArea description = new JTextArea();
        description.setText("Bienvenue dans le gestionnaire de la Cité Internationale Universitaire de Paris\n\n" +
                          "Cette application vous permet de gérer :\n" +
                          "• Les étudiants et leur affectation\n" +
                          "• Les maisons d'étudiants et internationales\n" +
                          "• Les restaurations et leurs menus\n" +
                          "• Les statistiques globales\n\n" +
                          "Utilisez les onglets ci-dessus pour naviguer dans l'application.");
        description.setEditable(false);
        description.setOpaque(false);
        description.setFont(new Font("Arial", Font.PLAIN, 14));
        description.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
        panneauAccueil.add(description, BorderLayout.CENTER);
        
        // Ajout de l'onglet d'accueil en première position
        ongletsPrincipaux.insertTab("Accueil", null, panneauAccueil, "Page d'accueil", 0);
        ongletsPrincipaux.setSelectedIndex(0);
    }
    
    private void configurerFenetre() {
        setTitle("CIUP Manager - Gestionnaire de la Cité Internationale");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(800, 600));
        
        // Icône de l'application
        try {
            setIconImage(new ImageIcon("logo_ciup.png").getImage());
        } catch (Exception e) {
            // Icône par défaut si le fichier n'existe pas
        }
    }
    
    //-----------------------------
    // ACCESSEURS
    //-----------------------------
    
    public JMenuItem getMenuSauvegarder() {
        return menuSauvegarder;
    }
    
    public JMenuItem getMenuQuitter() {
        return menuQuitter;
    }
    
    public JMenuItem getMenuAPropos() {
        return menuAPropos;
    }
    
    public VueGestionEtudiants getVueEtudiants() {
        return vueEtudiants;
    }
    
    public VueGestionMaisons getVueMaisons() {
        return vueMaisons;
    }
    
    public VueGestionRestaurations getVueRestaurations() {
        return vueRestaurations;
    }
    
    public VueStatistiques getVueStatistiques() {
        return vueStatistiques;
    }
    
    //-----------------------------
    // IMPLÉMENTATION INTERFACE
    //-----------------------------
    
    @Override
    public void redessiner() {
        vueEtudiants.redessiner();
        vueMaisons.redessiner();
        vueRestaurations.redessiner();
        vueStatistiques.redessiner();
        repaint();
    }
}