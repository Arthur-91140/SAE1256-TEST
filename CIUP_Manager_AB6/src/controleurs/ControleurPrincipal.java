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
    }
    
    //-----------------------------
    // MÉTHODES D'INITIALISATION
    //-----------------------------
    
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
    
    private void mettreAJourToutesLesVues() {
        // Mise à jour via les contrôleurs spécialisés
        controleurEtudiants.mettreAJourVue();
        controleurMaisons.mettreAJourVue();
        controleurRestaurations.mettreAJourVue();
        mettreAJourStatistiques();
    }
    
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