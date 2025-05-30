package controleurs;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import modeles.*;
import vues.*;
import citeU.*;

/**
 * Contrôleur pour la gestion des maisons étudiantes et internationales.
 * 
 * Cette classe implémente le pattern MVC en gérant les interactions entre
 * le modèle de données (GestionnaireCIUP) et la vue (VueGestionMaisons).
 * Elle permet l'ajout et la sélection des maisons étudiantes (spécialisées
 * par nationalité) et des maisons internationales.
 * 
 * @author Maxence Bailly
 */
public class ControleurMaisons implements ActionListener, ListSelectionListener {
    private GestionnaireCIUP modele;
    private VueGestionMaisons vue;
    
    /**
     * Construit un nouveau contrôleur pour la gestion des maisons.
     * 
     * Initialise les références au modèle et à la vue, configure les écouteurs
     * d'événements et met à jour l'affichage initial.
     * 
     * @param modele Le gestionnaire CIUP contenant les données
     * @param vue La vue pour l'interface de gestion des maisons
     */
    public ControleurMaisons(GestionnaireCIUP modele, VueGestionMaisons vue) {
        this.modele = modele;
        this.vue = vue;
        
        configurerEcouteurs();
        mettreAJourVue();
    }
    
    /**
     * Configure les écouteurs d'événements pour l'interface utilisateur.
     * 
     * Associe les actions des boutons d'ajout et la sélection dans les listes
     * des maisons étudiantes et internationales aux méthodes correspondantes.
     */
    private void configurerEcouteurs() {
        // Écouteurs pour les boutons
        vue.getBoutonAjouterMaisonEtudiant().addActionListener(this);
        vue.getBoutonAjouterMaisonInternationale().addActionListener(this);
        
        // Écouteurs pour les listes - IMPORTANT : utiliser directement les listes
        vue.getListeMaisonsEtudiants().addListSelectionListener(this);
        vue.getListeMaisonsInternationales().addListSelectionListener(this);
    }
    
    /**
     * Gère les événements d'action déclenchés par les boutons.
     * 
     * Dirige l'exécution vers la méthode appropriée selon le type de maison
     * à ajouter (maison étudiante ou maison internationale).
     * 
     * @param e L'événement d'action contenant la commande à exécuter
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        
        switch (action) {
            case "AJOUTER_MAISON_ETUDIANT":
                ajouterMaisonEtudiant();
                break;
            case "AJOUTER_MAISON_INTERNATIONALE":
                ajouterMaisonInternationale();
                break;
        }
    }
    
    /**
     * Gère les changements de sélection dans les listes de maisons.
     * 
     * Identifie quelle liste a été modifiée (maisons étudiantes ou internationales)
     * et affiche les détails de la maison sélectionnée dans le panneau approprié.
     * 
     * @param e L'événement de changement de sélection
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            // Identifier quelle liste a déclenché l'événement
            if (e.getSource() == vue.getListeMaisonsEtudiants()) {
                int index = vue.getListeMaisonsEtudiants().getSelectedIndex();
                if (index >= 0 && index < modele.getMaisonsEtudiants().size()) {
                    MaisonEtudiant maison = modele.getMaisonsEtudiants().get(index);
                    vue.afficherDetailsMaisonEtudiant(maison);
                }
            } else if (e.getSource() == vue.getListeMaisonsInternationales()) {
                int index = vue.getListeMaisonsInternationales().getSelectedIndex();
                if (index >= 0 && index < modele.getMaisonsInternationales().size()) {
                    MaisonInternationale maison = modele.getMaisonsInternationales().get(index);
                    vue.afficherDetailsMaisonInternationale(maison);
                }
            }
        }
    }
    
    /**
     * Ajoute une nouvelle maison étudiante au système.
     * 
     * Valide les données du formulaire (nom, nationalité, nombre de chambres,
     * localisation, directeur), crée une nouvelle maison étudiante et l'ajoute
     * au modèle. Gère la conversion du nombre de chambres en entier.
     */
    private void ajouterMaisonEtudiant() {
        if (validerFormulaireMaisonEtudiant()) {
            // Récupérer les bonnes valeurs depuis les bons champs
            String nom = vue.getChampNomMaisonEtudiant().getText().trim();
            String nationalite = vue.getChampNationalite().getText().trim();
            String localisation = vue.getChampLocalisationEtudiant().getText().trim();
            String directeur = vue.getChampDirecteurEtudiant().getText().trim();
            
            try {
                int nombreChambres = Integer.parseInt(vue.getChampNombreChambres().getText().trim());
                
                MaisonEtudiant nouvelleMaison = new MaisonEtudiant(
                    nationalite, nombreChambres, nom, localisation, directeur);
                
                modele.ajouterMaisonEtudiant(nouvelleMaison);
                vue.viderFormulaire();
                mettreAJourVue();
                
                JOptionPane.showMessageDialog(vue, 
                    "Maison d'étudiants ajoutée avec succès !", 
                    "Succès", 
                    JOptionPane.INFORMATION_MESSAGE);
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vue, 
                    "Le nombre de chambres doit être un nombre entier", 
                    "Erreur", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * Ajoute une nouvelle maison internationale au système.
     * 
     * Valide les données du formulaire (nom, localisation, directeur),
     * crée une nouvelle maison internationale et l'ajoute au modèle.
     */
    private void ajouterMaisonInternationale() {
        if (validerFormulaireMaisonInternationale()) {
            // Récupérer les bonnes valeurs depuis les bons champs
            String nom = vue.getChampNomMaisonInt().getText().trim();
            String localisation = vue.getChampLocalisationInt().getText().trim();
            String directeur = vue.getChampDirecteurInt().getText().trim();
            
            MaisonInternationale nouvelleMaison = new MaisonInternationale(
                nom, localisation, directeur);
            
            modele.ajouterMaisonInternationale(nouvelleMaison);
            vue.viderFormulaire();
            mettreAJourVue();
            
            JOptionPane.showMessageDialog(vue, 
                "Maison internationale ajoutée avec succès !", 
                "Succès", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Valide les données saisies pour une maison étudiante.
     * 
     * Vérifie que tous les champs obligatoires sont remplis et que
     * le nombre de chambres est un entier positif.
     * 
     * @return true si toutes les validations passent, false sinon
     */
    private boolean validerFormulaireMaisonEtudiant() {
        if (vue.getChampNomMaisonEtudiant().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(vue, 
                "Le nom de la maison est obligatoire", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (vue.getChampNationalite().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(vue, 
                "La nationalité est obligatoire", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (vue.getChampNombreChambres().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(vue, 
                "Le nombre de chambres est obligatoire", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        try {
            int nombreChambres = Integer.parseInt(vue.getChampNombreChambres().getText().trim());
            if (nombreChambres <= 0) {
                JOptionPane.showMessageDialog(vue, 
                    "Le nombre de chambres doit être positif", 
                    "Erreur", 
                    JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vue, 
                "Le nombre de chambres doit être un nombre entier", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (vue.getChampLocalisationEtudiant().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(vue, 
                "La localisation est obligatoire", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (vue.getChampDirecteurEtudiant().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(vue, 
                "Le nom du directeur est obligatoire", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    /**
     * Valide les données saisies pour une maison internationale.
     * 
     * Vérifie que tous les champs obligatoires (nom, localisation, directeur)
     * sont remplis.
     * 
     * @return true si toutes les validations passent, false sinon
     */
    private boolean validerFormulaireMaisonInternationale() {
        if (vue.getChampNomMaisonInt().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(vue, 
                "Le nom de la maison est obligatoire", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (vue.getChampLocalisationInt().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(vue, 
                "La localisation est obligatoire", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (vue.getChampDirecteurInt().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(vue, 
                "Le nom du directeur est obligatoire", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    /**
     * Met à jour l'ensemble de l'interface utilisateur.
     * 
     * Actualise les listes des maisons étudiantes et internationales
     * et redessine l'interface pour refléter les dernières modifications.
     */
    public void mettreAJourVue() {
        vue.mettreAJourMaisonsEtudiants(modele.getMaisonsEtudiants());
        vue.mettreAJourMaisonsInternationales(modele.getMaisonsInternationales());
        vue.redessiner();
    }
}