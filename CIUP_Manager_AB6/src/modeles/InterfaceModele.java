package modeles;

/**
 * Interface pour les modèles de données
 * Définit les méthodes communes pour la gestion des données
 * @author Maxence Bailly
 */
public interface InterfaceModele {
    
    /**
     * Charge les données depuis le fichier de sauvegarde
     */
    void chargerDonnees();
    
    /**
     * Sauvegarde les données sur disque
     */
    void sauvegarderDonnees();
    
    /**
     * Notifie les observateurs des changements
     */
    void notifierObservateurs();
}