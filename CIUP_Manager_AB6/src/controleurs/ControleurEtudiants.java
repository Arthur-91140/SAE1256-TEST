package controleurs;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.ArrayList;
import modeles.*;
import vues.*;
import citeU.*;

/**
 * Contrôleur pour la gestion des étudiants dans l'interface graphique.
 * 
 * Cette classe implémente le pattern MVC en gérant les interactions entre
 * le modèle de données (GestionnaireCIUP) et la vue (VueGestionEtudiants).
 * Elle gère les actions utilisateur comme l'ajout, la modification, la suppression
 * et l'affectation des étudiants aux maisons.
 * 
 * @author Arthur Pruvost
 */
public class ControleurEtudiants implements ActionListener, ListSelectionListener {
    
    //-----------------------------
    // ATTRIBUTS
    //-----------------------------
    private GestionnaireCIUP modele;
    private VueGestionEtudiants vue;
    
    //-----------------------------
    // CONSTRUCTEUR
    //-----------------------------
    
    /**
     * Construit un nouveau contrôleur pour la gestion des étudiants.
     * 
     * Initialise les références au modèle et à la vue, configure les écouteurs
     * d'événements et met à jour l'affichage initial.
     * 
     * @param modele Le gestionnaire CIUP contenant les données
     * @param vue La vue pour l'interface de gestion des étudiants
     */
    public ControleurEtudiants(GestionnaireCIUP modele, VueGestionEtudiants vue) {
        this.modele = modele;
        this.vue = vue;
        
        configurerEcouteurs();
        mettreAJourVue();
    }
    
    //-----------------------------
    // MÉTHODES D'INITIALISATION
    //-----------------------------
    
    /**
     * Configure tous les écouteurs d'événements pour l'interface utilisateur.
     * 
     * Associe les actions des boutons, la sélection dans le tableau et
     * la recherche en temps réel aux méthodes correspondantes du contrôleur.
     */
    private void configurerEcouteurs() {
        // Écouteurs pour les boutons
        vue.getBoutonAjouter().addActionListener(this);
        vue.getBoutonModifier().addActionListener(this);
        vue.getBoutonSupprimer().addActionListener(this);
        vue.getBoutonAffecter().addActionListener(this);
        vue.getBoutonRechercher().addActionListener(this);
        
        // Écouteur pour la sélection dans le tableau
        vue.getTableEtudiants().getSelectionModel().addListSelectionListener(this);
        
        // Écouteur pour la recherche en temps réel
        vue.getChampRecherche().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    rechercherEtudiant();
                }
            }
        });
    }
    
    //-----------------------------
    // GESTION DES ÉVÉNEMENTS
    //-----------------------------
    
    /**
     * Gère les événements d'action déclenchés par les boutons.
     * 
     * Dirige l'exécution vers la méthode appropriée selon l'action
     * déclenchée (ajouter, modifier, supprimer, affecter, rechercher).
     * 
     * @param e L'événement d'action contenant la commande à exécuter
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        
        switch (action) {
            case VueGestionEtudiants.ACTION_AJOUTER:
                ajouterEtudiant();
                break;
            case VueGestionEtudiants.ACTION_MODIFIER:
                modifierEtudiant();
                break;
            case VueGestionEtudiants.ACTION_SUPPRIMER:
                supprimerEtudiant();
                break;
            case VueGestionEtudiants.ACTION_AFFECTER:
                affecterEtudiant();
                break;
            case VueGestionEtudiants.ACTION_RECHERCHER:
                rechercherEtudiant();
                break;
        }
    }
    
    /**
     * Gère les changements de sélection dans le tableau des étudiants.
     * 
     * Met à jour le formulaire avec les données de l'étudiant sélectionné
     * pour permettre la modification ou l'affectation.
     * 
     * @param e L'événement de changement de sélection
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int ligneSelectionnee = vue.getEtudiantSelectionne();
            if (ligneSelectionnee >= 0 && ligneSelectionnee < modele.getEtudiants().size()) {
                Etudiant etudiant = modele.getEtudiants().get(ligneSelectionnee);
                vue.remplirFormulaire(etudiant);
            }
        }
    }
    
    //-----------------------------
    // MÉTHODES PRIVÉES
    //-----------------------------
    
    /**
     * Ajoute un nouvel étudiant au système.
     * 
     * Valide les données du formulaire, crée un nouvel étudiant et
     * l'ajoute au modèle. Si une maison est sélectionnée et disponible,
     * l'étudiant y est automatiquement affecté.
     */
    private void ajouterEtudiant() {
        if (validerFormulaire()) {
            String nom = vue.getChampNom().getText().trim();
            String prenom = vue.getChampPrenom().getText().trim();
            String nationalite = vue.getChampNationalite().getText().trim();
            
            Etudiant nouvelEtudiant = new Etudiant(nom, prenom, nationalite);
            modele.ajouterEtudiant(nouvelEtudiant);
            
            // Affecter à une maison si sélectionnée
            int maisonSelectionnee = vue.getComboMaisons().getSelectedIndex();
            if (maisonSelectionnee > 0) { // > 0 car index 0 = "Sélectionner une maison"
                MaisonEtudiant maison = modele.getMaisonsEtudiants().get(maisonSelectionnee - 1);
                if (maison.verifierDisponibilite()) {
                    maison.ajouteEtudiant(nouvelEtudiant);
                } else {
                    JOptionPane.showMessageDialog(vue, 
                        "La maison sélectionnée n'a plus de places disponibles", 
                        "Attention", 
                        JOptionPane.WARNING_MESSAGE);
                }
            }
            
            vue.viderFormulaire();
            mettreAJourVue();
            
            JOptionPane.showMessageDialog(vue, 
                "Étudiant ajouté avec succès !", 
                "Succès", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Modifie les informations de l'étudiant sélectionné.
     * 
     * Vérifie qu'un étudiant est sélectionné, valide les nouvelles données
     * et met à jour l'objet étudiant correspondant.
     */
    private void modifierEtudiant() {
        int ligneSelectionnee = vue.getEtudiantSelectionne();
        if (ligneSelectionnee < 0) {
            JOptionPane.showMessageDialog(vue, 
                "Veuillez sélectionner un étudiant à modifier", 
                "Erreur", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (validerFormulaire()) {
            Etudiant etudiant = modele.getEtudiants().get(ligneSelectionnee);
            
            etudiant.setNom(vue.getChampNom().getText().trim());
            etudiant.setPrenom(vue.getChampPrenom().getText().trim());
            etudiant.setNationalite(vue.getChampNationalite().getText().trim());
            
            modele.notifierObservateurs();
            mettreAJourVue();
            
            JOptionPane.showMessageDialog(vue, 
                "Étudiant modifié avec succès !", 
                "Succès", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Supprime l'étudiant sélectionné après confirmation.
     * 
     * Affiche une boîte de dialogue de confirmation avant de procéder
     * à la suppression définitive de l'étudiant du système.
     */
    private void supprimerEtudiant() {
        int ligneSelectionnee = vue.getEtudiantSelectionne();
        if (ligneSelectionnee < 0) {
            JOptionPane.showMessageDialog(vue, 
                "Veuillez sélectionner un étudiant à supprimer", 
                "Erreur", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Etudiant etudiant = modele.getEtudiants().get(ligneSelectionnee);
        
        int choix = JOptionPane.showConfirmDialog(vue,
            "Êtes-vous sûr de vouloir supprimer l'étudiant " + 
            etudiant.getPrenom() + " " + etudiant.getNom() + " ?",
            "Confirmation de suppression",
            JOptionPane.YES_NO_OPTION);
        
        if (choix == JOptionPane.YES_OPTION) {
            modele.supprimerEtudiant(etudiant);
            vue.viderFormulaire();
            mettreAJourVue();
            
            JOptionPane.showMessageDialog(vue, 
                "Étudiant supprimé avec succès !", 
                "Succès", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Affecte l'étudiant sélectionné à une maison.
     * 
     * Vérifie la disponibilité de la maison sélectionnée, retire l'étudiant
     * de son ancienne maison si nécessaire, et l'affecte à la nouvelle.
     */
    private void affecterEtudiant() {
        int ligneSelectionnee = vue.getEtudiantSelectionne();
        if (ligneSelectionnee < 0) {
            JOptionPane.showMessageDialog(vue, 
                "Veuillez sélectionner un étudiant à affecter", 
                "Erreur", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int maisonSelectionnee = vue.getComboMaisons().getSelectedIndex();
        if (maisonSelectionnee <= 0) {
            JOptionPane.showMessageDialog(vue, 
                "Veuillez sélectionner une maison", 
                "Erreur", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Etudiant etudiant = modele.getEtudiants().get(ligneSelectionnee);
        MaisonEtudiant maison = modele.getMaisonsEtudiants().get(maisonSelectionnee - 1);
        
        // Vérifier la disponibilité
        if (!maison.verifierDisponibilite()) {
            JOptionPane.showMessageDialog(vue, 
                "Cette maison n'a plus de places disponibles", 
                "Erreur", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Retirer de l'ancienne maison si nécessaire
        if (etudiant.getSaMaison() != null) {
            etudiant.getSaMaison().retireEtudiant(etudiant);
        }
        
        // Affecter à la nouvelle maison
        maison.ajouteEtudiant(etudiant);
        
        modele.notifierObservateurs();
        mettreAJourVue();
        
        JOptionPane.showMessageDialog(vue, 
            "Étudiant affecté avec succès à " + maison.getNom() + " !", 
            "Succès", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Recherche des étudiants par nom ou prénom.
     * 
     * Filtre la liste des étudiants selon le texte de recherche saisi
     * et met à jour l'affichage du tableau avec les résultats.
     */
    private void rechercherEtudiant() {
        String nomRecherche = vue.getChampRecherche().getText().trim();
        
        if (nomRecherche.isEmpty()) {
            mettreAJourTableau();
            return;
        }
        
        ArrayList<Etudiant> resultats = new ArrayList<>();
        for (Etudiant etudiant : modele.getEtudiants()) {
            if (etudiant.getNom().toLowerCase().contains(nomRecherche.toLowerCase()) ||
                etudiant.getPrenom().toLowerCase().contains(nomRecherche.toLowerCase())) {
                resultats.add(etudiant);
            }
        }
        
        vue.mettreAJourTableau(resultats);
        
        if (resultats.isEmpty()) {
            JOptionPane.showMessageDialog(vue, 
                "Aucun étudiant trouvé pour la recherche : " + nomRecherche, 
                "Recherche", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Valide les données saisies dans le formulaire.
     * 
     * Vérifie que tous les champs obligatoires (nom, prénom, nationalité)
     * sont remplis et affiche des messages d'erreur appropriés.
     * 
     * @return true si toutes les validations passent, false sinon
     */
    private boolean validerFormulaire() {
        if (vue.getChampNom().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(vue, 
                "Le nom est obligatoire", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
            vue.getChampNom().requestFocus();
            return false;
        }
        
        if (vue.getChampPrenom().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(vue, 
                "Le prénom est obligatoire", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
            vue.getChampPrenom().requestFocus();
            return false;
        }
        
        if (vue.getChampNationalite().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(vue, 
                "La nationalité est obligatoire", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
            vue.getChampNationalite().requestFocus();
            return false;
        }
        
        return true;
    }
    
    /**
     * Met à jour l'affichage du tableau avec la liste complète des étudiants.
     */
    private void mettreAJourTableau() {
        vue.mettreAJourTableau(modele.getEtudiants());
    }
    
    //-----------------------------
    // MÉTHODES PUBLIQUES
    //-----------------------------
    
    /**
     * Met à jour l'ensemble de l'interface utilisateur.
     * 
     * Actualise le tableau des étudiants, la liste des maisons disponibles
     * et redessine l'interface pour refléter les dernières modifications.
     */
    public void mettreAJourVue() {
        mettreAJourTableau();
        vue.mettreAJourComboMaisons(modele.getMaisonsEtudiants());
        vue.redessiner();
    }
}