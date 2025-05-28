package controleurs;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import modeles.*;
import vues.*;
import citeU.*;

/**
 * Contrôleur pour la gestion des restaurations et menus
 * @author Arthur Pruvost
 */
public class ControleurRestaurations implements ActionListener, ListSelectionListener {
    
    //-----------------------------
    // ATTRIBUTS
    //-----------------------------
    private GestionnaireCIUP modele;
    private VueGestionRestaurations vue;
    
    //-----------------------------
    // CONSTRUCTEUR
    //-----------------------------
    public ControleurRestaurations(GestionnaireCIUP modele, VueGestionRestaurations vue) {
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
        vue.getBoutonAjouterMenu().addActionListener(this);
        vue.getBoutonSupprimerMenu().addActionListener(this);
        vue.getBoutonAffecterMenu().addActionListener(this);
        
        // Écouteur pour la sélection dans le tableau des restaurations
        vue.getTableRestaurations().getSelectionModel().addListSelectionListener(this);
    }
    
    //-----------------------------
    // GESTION DES ÉVÉNEMENTS
    //-----------------------------
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        
        switch (action) {
            case VueGestionRestaurations.ACTION_AJOUTER_MENU:
                ajouterMenu();
                break;
            case VueGestionRestaurations.ACTION_SUPPRIMER_MENU:
                supprimerMenu();
                break;
            case VueGestionRestaurations.ACTION_AFFECTER_MENU:
                affecterMenu();
                break;
        }
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int ligneSelectionnee = vue.getRestaurationSelectionnee();
            if (ligneSelectionnee >= 0 && ligneSelectionnee < modele.getRestaurations().size()) {
                Restauration restauration = modele.getRestaurations().get(ligneSelectionnee);
                vue.mettreAJourTableauMenus(restauration);
            }
        }
    }
    
    //-----------------------------
    // MÉTHODES PRIVÉES
    //-----------------------------
    
    private void ajouterMenu() {
        if (vue.validerFormulaire()) {
            String nom = vue.getChampNomMenu().getText().trim();
            String entree = vue.getChampEntree().getText().trim();
            String plat = vue.getChampPlat().getText().trim();
            String dessert = vue.getChampDessert().getText().trim();
            double prix = Double.parseDouble(vue.getChampPrix().getText().trim());
            
            Menu nouveauMenu = new Menu(nom, entree, plat, dessert, prix);
            modele.ajouterMenu(nouveauMenu);
            
            // Affecter le menu à la restauration sélectionnée
            int indexRestaurant = vue.getComboRestaurations().getSelectedIndex() - 1;
            if (indexRestaurant >= 0) {
                Restauration restauration = modele.getRestaurations().get(indexRestaurant);
                restauration.ajouteMenu(nouveauMenu);
            }
            
            vue.viderFormulaire();
            mettreAJourVue();
            
            JOptionPane.showMessageDialog(vue, 
                "Menu ajouté avec succès !", 
                "Succès", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void supprimerMenu() {
        int ligneRestauration = vue.getRestaurationSelectionnee();
        int ligneMenu = vue.getMenuSelectionne();
        
        if (ligneRestauration < 0) {
            JOptionPane.showMessageDialog(vue, 
                "Veuillez sélectionner une restauration", 
                "Erreur", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (ligneMenu < 0) {
            JOptionPane.showMessageDialog(vue, 
                "Veuillez sélectionner un menu à supprimer", 
                "Erreur", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Restauration restauration = modele.getRestaurations().get(ligneRestauration);
        Menu menu = restauration.getSesMenus().get(ligneMenu);
        
        int choix = JOptionPane.showConfirmDialog(vue,
            "Êtes-vous sûr de vouloir supprimer le menu " + menu.getNom() + " ?",
            "Confirmation de suppression",
            JOptionPane.YES_NO_OPTION);
        
        if (choix == JOptionPane.YES_OPTION) {
            restauration.retireMenu(menu);
            modele.getMenus().remove(menu);
            
            modele.notifierObservateurs();
            mettreAJourVue();
            
            JOptionPane.showMessageDialog(vue, 
                "Menu supprimé avec succès !", 
                "Succès", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void affecterMenu() {
        int ligneMenu = vue.getMenuSelectionne();
        if (ligneMenu < 0) {
            JOptionPane.showMessageDialog(vue, 
                "Veuillez sélectionner un menu dans la liste", 
                "Erreur", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int indexRestaurant = vue.getComboRestaurations().getSelectedIndex();
        if (indexRestaurant <= 0) {
            JOptionPane.showMessageDialog(vue, 
                "Veuillez sélectionner une restauration", 
                "Erreur", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Récupérer le menu depuis la restauration actuellement sélectionnée
        int ligneRestauration = vue.getRestaurationSelectionnee();
        if (ligneRestauration < 0) {
            JOptionPane.showMessageDialog(vue, 
                "Veuillez d'abord sélectionner une restauration dans le tableau", 
                "Erreur", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Restauration restaurationSource = modele.getRestaurations().get(ligneRestauration);
        Menu menu = restaurationSource.getSesMenus().get(ligneMenu);
        
        Restauration restaurationCible = modele.getRestaurations().get(indexRestaurant - 1);
        
        // Vérifier si le menu n'est pas déjà affecté à cette restauration
        if (restaurationCible.getSesMenus().contains(menu)) {
            JOptionPane.showMessageDialog(vue, 
                "Ce menu est déjà affecté à cette restauration", 
                "Information", 
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Affecter le menu à la restauration cible
        restaurationCible.ajouteMenu(menu);
        
        modele.notifierObservateurs();
        mettreAJourVue();
        
        JOptionPane.showMessageDialog(vue, 
            "Menu affecté avec succès à " + restaurationCible.getNom() + " !", 
            "Succès", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    //-----------------------------
    // MÉTHODES PUBLIQUES
    //-----------------------------
    
    /**
     * Met à jour la vue complète
     */
    public void mettreAJourVue() {
        vue.mettreAJourTableauRestaurations(modele.getRestaurations());
        vue.mettreAJourComboRestaurations(modele.getRestaurations());
        
        // Mettre à jour les menus si une restauration est sélectionnée
        int ligneSelectionnee = vue.getRestaurationSelectionnee();
        if (ligneSelectionnee >= 0 && ligneSelectionnee < modele.getRestaurations().size()) {
            Restauration restauration = modele.getRestaurations().get(ligneSelectionnee);
            vue.mettreAJourTableauMenus(restauration);
        }
        
        vue.redessiner();
    }
}