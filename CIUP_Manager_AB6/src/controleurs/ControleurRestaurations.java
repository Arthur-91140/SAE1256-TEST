package controleurs;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.ArrayList;
import modeles.*;
import vues.*;
import citeU.*;

/**
 * Contrôleur pour la gestion des restaurations et menus
 * @author Arthur Pruvost
 */
public class ControleurRestaurations implements ActionListener, ListSelectionListener {
    private GestionnaireCIUP modele;
    private VueGestionRestaurations vue;
    
    /**
     * Constructeur du contrôleur des restaurations
     * Configure les écouteurs et initialise la vue
     * @param modele le gestionnaire CIUP contenant les données
     * @param vue la vue de gestion des restaurations
     */
    public ControleurRestaurations(GestionnaireCIUP modele, VueGestionRestaurations vue) {
        this.modele = modele;
        this.vue = vue;
        
        configurerEcouteurs();
        mettreAJourVue();
    }
    
    /**
     * Configure les écouteurs d'événements pour les boutons et tableaux
     */
    private void configurerEcouteurs() {
        // Écouteurs pour les boutons
        vue.getBoutonAjouterMenu().addActionListener(this);
        vue.getBoutonSupprimerMenu().addActionListener(this);
        vue.getBoutonAffecterMenu().addActionListener(this);
        vue.getBoutonAjouterRestauration().addActionListener(this);
        vue.getBoutonAffecterRestaurationMaison().addActionListener(this);
        
        // Écouteur pour la sélection dans le tableau des restaurations
        vue.getTableRestaurations().getSelectionModel().addListSelectionListener(this);
    }
    
    /**
     * Gère les événements des boutons (ajouter menu, supprimer menu, etc.)
     * @param e l'événement d'action déclenché
     */
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
            case VueGestionRestaurations.ACTION_AJOUTER_RESTAURATION:
                ajouterRestauration();
                break;
            case VueGestionRestaurations.ACTION_AFFECTER_RESTAURATION_MAISON:
                affecterRestaurationMaison();
                break;
        }
    }
    
    /**
     * Gère les changements de sélection dans le tableau des restaurations
     * Met à jour automatiquement l'affichage des menus correspondants
     * @param e l'événement de changement de sélection
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int ligneSelectionnee = vue.getRestaurationSelectionnee();
            if (ligneSelectionnee >= 0 && ligneSelectionnee < modele.getRestaurations().size()) {
                Restauration restauration = modele.getRestaurations().get(ligneSelectionnee);
                vue.mettreAJourTableauMenus(restauration);
                // Forcer le rafraîchissement
                vue.redessiner();
            } else {
                // Vider le tableau des menus si aucune sélection
                vue.mettreAJourTableauMenus(null);
            }
        }
    }
    

	/**
	 * Ajoute un nouveau menu après validation du formulaire
	 * Affecte automatiquement le menu à la restauration sélectionnée
	 */
    private void ajouterMenu() {
        if (vue.validerFormulaire()) {
            String nom = vue.getChampNomMenu().getText().trim();
            String entree = vue.getChampEntree().getText().trim();
            String plat = vue.getChampPlat().getText().trim();
            String dessert = vue.getChampDessert().getText().trim();
            double prix = Double.parseDouble(vue.getChampPrix().getText().trim());
        
            Menu nouveauMenu = new Menu(nom, entree, plat, dessert, prix);
            modele.ajouterMenu(nouveauMenu);
        
            vue.viderFormulaire();
            mettreAJourVue();
        
            JOptionPane.showMessageDialog(vue, "Menu ajouté avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Supprime le menu sélectionné après confirmation de l'utilisateur
     * Met à jour le modèle et rafraîchit la vue
     */
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
    

	/**
	 * Affecte un menu existant à une restauration différente
	 * Vérifie que le menu n'est pas déjà présent dans la restauration cible
	 */
    private void affecterMenu() {
        int indexMenu = vue.getComboTousLesMenus().getSelectedIndex();
        int indexRestaurant = vue.getComboRestaurations().getSelectedIndex();
        
        if (indexMenu <= 0) {
            JOptionPane.showMessageDialog(vue, 
                "Veuillez sélectionner un menu", 
                "Erreur", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (indexRestaurant <= 0) {
            JOptionPane.showMessageDialog(vue, 
                "Veuillez sélectionner une restauration", 
                "Erreur", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Menu menu = modele.getMenus().get(indexMenu - 1);
        Restauration restauration = modele.getRestaurations().get(indexRestaurant - 1);
        
        // Vérifier si le menu n'est pas déjà affecté à cette restauration
        if (restauration.getSesMenus().contains(menu)) {
            JOptionPane.showMessageDialog(vue, 
                "Ce menu est déjà affecté à cette restauration", 
                "Information", 
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Affecter le menu à la restauration
        restauration.ajouteMenu(menu);
        
        modele.notifierObservateurs();
        mettreAJourVue();
        
        JOptionPane.showMessageDialog(vue, 
            "Menu affecté avec succès à " + restauration.getNom() + " !", 
            "Succès", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Ajoute une nouvelle restauration (RestoU ou Cafet) selon le type sélectionné
     * Valide les données saisies avant l'ajout
     */
    private void ajouterRestauration() {
        String nom = vue.getChampNomRestauration().getText().trim();
        String capaciteStr = vue.getChampCapaciteRestauration().getText().trim();
        
        if (nom.isEmpty() || capaciteStr.isEmpty()) {
            JOptionPane.showMessageDialog(vue, 
                "Veuillez remplir tous les champs", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            int capacite = Integer.parseInt(capaciteStr);
            String type = (String) vue.getComboTypeRestauration().getSelectedItem();
            
            Restauration nouvelleRestauration;
            if ("RestoU".equals(type)) {
                nouvelleRestauration = new RestoU(nom, capacite);
            } else {
                nouvelleRestauration = new Cafet(nom, capacite);
            }
            
            modele.ajouterRestauration(nouvelleRestauration);
            
            // Affecter directement à une maison si sélectionnée
            int indexMaison = vue.getComboMaisonsPourRestauration().getSelectedIndex();
            if (indexMaison > 0) {
                Maison maison = obtenirMaisonParIndex(indexMaison - 1);
                if (maison != null) {
                    maison.ajouteRestauration(nouvelleRestauration);
                }
            }
            
            // Vider les champs
            vue.getChampNomRestauration().setText("");
            vue.getChampCapaciteRestauration().setText("");
            vue.getComboTypeRestauration().setSelectedIndex(0);
            vue.getComboMaisonsPourRestauration().setSelectedIndex(0);
            
            mettreAJourVue();
            
            JOptionPane.showMessageDialog(vue, 
                "Restauration ajoutée avec succès !", 
                "Succès", 
                JOptionPane.INFORMATION_MESSAGE);
                
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vue, 
                "La capacité doit être un nombre entier", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void affecterRestaurationMaison() {
        int ligneRestauration = vue.getRestaurationSelectionnee();
        int indexMaison = vue.getComboMaisonsPourRestauration().getSelectedIndex();
        
        if (ligneRestauration < 0) {
            JOptionPane.showMessageDialog(vue, 
                "Veuillez sélectionner une restauration dans le tableau", 
                "Erreur", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (indexMaison <= 0) {
            JOptionPane.showMessageDialog(vue, 
                "Veuillez sélectionner une maison", 
                "Erreur", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Restauration restauration = modele.getRestaurations().get(ligneRestauration);
        Maison maison = obtenirMaisonParIndex(indexMaison - 1);
        
        if (maison == null) {
            JOptionPane.showMessageDialog(vue, 
                "Erreur lors de la sélection de la maison", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Retirer de l'ancienne maison si nécessaire
        if (restauration.getSaMaison() != null) {
            restauration.getSaMaison().retireRestauration(restauration);
        }
        
        // Affecter à la nouvelle maison
        maison.ajouteRestauration(restauration);
        
        modele.notifierObservateurs();
        mettreAJourVue();
        
        JOptionPane.showMessageDialog(vue, 
            "Restauration affectée avec succès à " + maison.getNom() + " !", 
            "Succès", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Obtient une maison par son index dans la liste combinée
     */
    private Maison obtenirMaisonParIndex(int index) {
        ArrayList<Maison> toutesLesMaisons = new ArrayList<>();
        toutesLesMaisons.addAll(modele.getMaisonsEtudiants());
        toutesLesMaisons.addAll(modele.getMaisonsInternationales());
        
        if (index >= 0 && index < toutesLesMaisons.size()) {
            return toutesLesMaisons.get(index);
        }
        return null;
    }
    
    /**
     * Met à jour la vue complète
     */
    public void mettreAJourVue() {
        vue.mettreAJourTableauRestaurations(modele.getRestaurations());
        vue.mettreAJourComboRestaurations(modele.getRestaurations());
        vue.mettreAJourComboTousLesMenus(modele.getMenus());
        
        // Créer une liste combinée de toutes les maisons
        ArrayList<Maison> toutesLesMaisons = new ArrayList<>();
        toutesLesMaisons.addAll(modele.getMaisonsEtudiants());
        toutesLesMaisons.addAll(modele.getMaisonsInternationales());
        vue.mettreAJourComboMaisons(toutesLesMaisons);
        
        // Mettre à jour les menus si une restauration est sélectionnée
        int ligneSelectionnee = vue.getRestaurationSelectionnee();
        if (ligneSelectionnee >= 0 && ligneSelectionnee < modele.getRestaurations().size()) {
            Restauration restauration = modele.getRestaurations().get(ligneSelectionnee);
            vue.mettreAJourTableauMenus(restauration);
        }
        
        vue.redessiner();
    }
}