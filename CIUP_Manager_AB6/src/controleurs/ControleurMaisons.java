package controleurs;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import modeles.*;
import vues.*;
import citeU.*;

/**
 * Contrôleur pour la gestion des maisons
 * @author Maxence Bailly
 */
public class ControleurMaisons implements ActionListener, ListSelectionListener {
    
    //-----------------------------
    // ATTRIBUTS
    //-----------------------------
    private GestionnaireCIUP modele;
    private VueGestionMaisons vue;
    
    //-----------------------------
    // CONSTRUCTEUR
    //-----------------------------
    public ControleurMaisons(GestionnaireCIUP modele, VueGestionMaisons vue) {
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
        vue.getBoutonAjouterMaisonEtudiant().addActionListener(this);
        vue.getBoutonAjouterMaisonInternationale().addActionListener(this);
        
        // Écouteurs pour les listes
        vue.getListeMaisonsEtudiants().addListSelectionListener(this);
        vue.getListeMaisonsInternationales().addListSelectionListener(this);
    }
    
    //-----------------------------
    // GESTION DES ÉVÉNEMENTS
    //-----------------------------
    
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
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            if (e.getSource() == vue.getListeMaisonsEtudiants().getSelectionModel()) {
                int index = vue.getListeMaisonsEtudiants().getSelectedIndex();
                if (index >= 0 && index < modele.getMaisonsEtudiants().size()) {
                    MaisonEtudiant maison = modele.getMaisonsEtudiants().get(index);
                    vue.afficherDetailsMaisonEtudiant(maison);
                }
            } else if (e.getSource() == vue.getListeMaisonsInternationales().getSelectionModel()) {
                int index = vue.getListeMaisonsInternationales().getSelectedIndex();
                if (index >= 0 && index < modele.getMaisonsInternationales().size()) {
                    MaisonInternationale maison = modele.getMaisonsInternationales().get(index);
                    vue.afficherDetailsMaisonInternationale(maison);
                }
            }
        }
    }
    
    //-----------------------------
    // MÉTHODES PRIVÉES
    //-----------------------------
    
    private void ajouterMaisonEtudiant() {
        if (validerFormulaireMaisonEtudiant()) {
            String nom = vue.getChampNomMaison().getText().trim();
            String nationalite = vue.getChampNationalite().getText().trim();
            String localisation = vue.getChampLocalisation().getText().trim();
            String directeur = vue.getChampDirecteur().getText().trim();
            
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
    
    private void ajouterMaisonInternationale() {
        if (validerFormulaireMaisonInternationale()) {
            String nom = vue.getChampNomMaison().getText().trim();
            String localisation = vue.getChampLocalisation().getText().trim();
            String directeur = vue.getChampDirecteur().getText().trim();
            
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
    
    private boolean validerFormulaireMaisonEtudiant() {
        if (vue.getChampNomMaison().getText().trim().isEmpty()) {
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
        
        if (vue.getChampLocalisation().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(vue, 
                "La localisation est obligatoire", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (vue.getChampDirecteur().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(vue, 
                "Le nom du directeur est obligatoire", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    private boolean validerFormulaireMaisonInternationale() {
        if (vue.getChampNomMaison().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(vue, 
                "Le nom de la maison est obligatoire", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (vue.getChampLocalisation().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(vue, 
                "La localisation est obligatoire", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (vue.getChampDirecteur().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(vue, 
                "Le nom du directeur est obligatoire", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    //-----------------------------
    // MÉTHODES PUBLIQUES
    //-----------------------------
    
    /**
     * Met à jour la vue complète
     */
    public void mettreAJourVue() {
        vue.mettreAJourMaisonsEtudiants(modele.getMaisonsEtudiants());
        vue.mettreAJourMaisonsInternationales(modele.getMaisonsInternationales());
        vue.redessiner();
    }
}