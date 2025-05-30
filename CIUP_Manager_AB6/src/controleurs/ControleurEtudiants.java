package controleurs;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.ArrayList;
import modeles.*;
import vues.*;
import citeU.*;

/**
 * Contrôleur pour la gestion des étudiants
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
    public ControleurEtudiants(GestionnaireCIUP modele, VueGestionEtudiants vue) {
        this.modele = modele;
        this.vue = vue;
        
        configurerEcouteurs();
        mettreAJourVue();
    }
    
    //-----------------------------
    // MÉTHODES D'INITIALISATION
    //-----------------------------
    
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
    
    private void mettreAJourTableau() {
        vue.mettreAJourTableau(modele.getEtudiants());
    }
    
    //-----------------------------
    // MÉTHODES PUBLIQUES
    //-----------------------------
    
    /**
     * Met à jour la vue complète
     */
    public void mettreAJourVue() {
        mettreAJourTableau();
        vue.mettreAJourComboMaisons(modele.getMaisonsEtudiants());
        vue.redessiner();
    }
}