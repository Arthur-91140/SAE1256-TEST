package controleurs;

import javax.swing.*;
import java.awt.event.*;
import modeles.*;
import vues.*;

/**
 * Contrôleur principal gérant la navigation et les menus
 * @author Maxence Bailly
 */
public class ControleurPrincipal implements ActionListener {
    
    //-----------------------------
    // ATTRIBUTS
    //-----------------------------
    private GestionnaireCIUP modele;
    private VuePrincipale vue;
    
    // Contrôleurs spécialisés
    private ControleurEtudiants controleurEtudiants;
    private ControleurMaisons controleurMaisons;
    private ControleurRestaurations controleurRestaurations;
    
    //-----------------------------
    // CONSTRUCTEUR
    //-----------------------------
    /**
     * Constructeur du contrôleur principal
     * Initialise les contrôleurs spécialisés, configure les écouteurs et met à jour les vues
     * @param modele le gestionnaire CIUP contenant les données
     * @param vue la vue principale de l'application
     */
    public ControleurPrincipal(GestionnaireCIUP modele, VuePrincipale vue) {
        this.modele = modele;
        this.vue = vue;
        
        // Création des contrôleurs spécialisés
        controleurEtudiants = new ControleurEtudiants(modele, vue.getVueEtudiants());
        controleurMaisons = new ControleurMaisons(modele, vue.getVueMaisons());
        controleurRestaurations = new ControleurRestaurations(modele, vue.getVueRestaurations());
        
        // Configuration des écouteurs
        configurerEcouteurs();
        
        // Mise à jour initiale des vues
        mettreAJourToutesLesVues();
        
        modele.setControleurPrincipal(this);
    }
    
    //-----------------------------
    // MÉTHODES D'INITIALISATION
    //-----------------------------
    
    /**
     * Configure les écouteurs d'événements pour les menus et composants
     */
    private void configurerEcouteurs() {
        // Écouteurs pour le menu principal
        vue.getMenuSauvegarder().addActionListener(this);
        vue.getMenuQuitter().addActionListener(this);
        vue.getMenuAPropos().addActionListener(this);
        
        // Mise à jour des statistiques quand on change d'onglet
        vue.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                mettreAJourStatistiques();
            }
        });
    }
    
    //-----------------------------
    // GESTION DES ÉVÉNEMENTS
    //-----------------------------
    
    /**
     * Gère les événements des menus (sauvegarder, quitter, à propos)
     * @param e l'événement d'action déclenché
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        
        switch (action) {
            case "SAUVEGARDER":
                sauvegarderDonnees();
                break;
            case "QUITTER":
                quitterApplication();
                break;
            case "A_PROPOS":
                afficherAPropos();
                break;
        }
    }
    
    //-----------------------------
    // MÉTHODES PRIVÉES
    //-----------------------------
    
    /**
     * Sauvegarde les données via le modèle et affiche un message de confirmation
     */
    private void sauvegarderDonnees() {
        try {
            modele.sauvegarderDonnees();
            JOptionPane.showMessageDialog(vue, 
                "Données sauvegardées avec succès !", 
                "Sauvegarde", 
                JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vue, 
                "Erreur lors de la sauvegarde : " + e.getMessage(), 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Affiche une boîte de dialogue de confirmation avant de quitter l'application
     * Propose de sauvegarder avant la fermeture
     */
    private void quitterApplication() {
        int choix = JOptionPane.showConfirmDialog(vue,
            "Voulez-vous sauvegarder avant de quitter ?",
            "Quitter l'application",
            JOptionPane.YES_NO_CANCEL_OPTION);
        
        if (choix == JOptionPane.YES_OPTION) {
            modele.sauvegarderDonnees();
            System.exit(0);
        } else if (choix == JOptionPane.NO_OPTION) {
            System.exit(0);
        }
        // Si CANCEL, on ne fait rien
    }
    
    /**
     * Affiche les informations sur l'application et ses développeurs
     */
    private void afficherAPropos() {
        String message = "CIUP Manager v1.0\n\n" +
                        "Application de gestion de la Cité Internationale\n" +
                        "Universitaire de Paris\n\n" +
                        "Développé par :\n" +
                        "• Maxence Bailly\n" +
                        "• Arthur Pruvost\n\n" +
                        "Projet SAE - BUT Informatique\n" +
                        "IUT d'Orsay - Université Paris-Saclay\n" +
                        "2025";
        
        JOptionPane.showMessageDialog(vue, 
            message, 
            "À propos", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Met à jour toutes les vues spécialisées et les statistiques
     */
    private void mettreAJourToutesLesVues() {
        // Mise à jour via les contrôleurs spécialisés
        controleurEtudiants.mettreAJourVue();
        controleurMaisons.mettreAJourVue();
        controleurRestaurations.mettreAJourVue();
        mettreAJourStatistiques();
    }
    
    /**
     * Calcule et met à jour les statistiques affichées dans la vue
     * Comprend les statistiques générales, taux d'occupation et répartition par nationalité
     */
    private void mettreAJourStatistiques() {
        ModeleStatistiques stats = new ModeleStatistiques(modele);
        
        // Statistiques générales
        int nbEtudiants = stats.getNombreTotalEtudiants();
        int nbMaisons = stats.getNombreTotalMaisons();
        int nbRestaurations = modele.getRestaurations().size();
        double prixMoyen = stats.getPrixMoyenMenus();
        
        vue.getVueStatistiques().mettreAJourStatistiquesGenerales(
            nbEtudiants, nbMaisons, nbRestaurations, prixMoyen);
        
        // Taux d'occupation des maisons
        java.util.List<String> nomsMaisons = new java.util.ArrayList<>();
        java.util.List<Double> tauxOccupation = new java.util.ArrayList<>();
        
        for (citeU.MaisonEtudiant maison : modele.getMaisonsEtudiants()) {
            nomsMaisons.add(maison.getNom());
            tauxOccupation.add(stats.getTauxOccupation(maison));
        }
        
        vue.getVueStatistiques().mettreAJourTauxOccupation(nomsMaisons, tauxOccupation);
        
        // Répartition par nationalité
        vue.getVueStatistiques().mettreAJourRepartitionNationalites(
            stats.getRepartitionParNationalite());
    }
    
    //-----------------------------
    // MÉTHODES PUBLIQUES
    //-----------------------------
    
    /**
     * Met à jour toutes les vues après une modification
     */
    public void rafraichirVues() {
        mettreAJourToutesLesVues();
    }
    
    /**
     * Affiche un message d'information dans la vue des statistiques
     */
    public void afficherMessage(String message) {
        vue.getVueStatistiques().afficherMessage(message);
    }
}