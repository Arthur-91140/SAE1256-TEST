package vues;

/**
 * Interface pour toutes les vues de l'application.
 * Définit les méthodes communes de mise à jour de l'affichage.
 * @author Arthur Pruvost
 */
public interface InterfaceVue {
    
    /**
     * Met à jour l'affichage de la vue.
     * Force le redessin des composants graphiques.
     */
    void redessiner();
}