package citeU;

/**
 * Classe principale du système de gestion de la Cité Universitaire de Paris.
 * 
 * Cette classe contient le point d'entrée principal du programme et délègue
 * l'exécution des tests à la classe CIUPFactory. Elle sert de façade pour
 * le lancement de l'application.
 */
public class CIUP {
    /**
     * Point d'entrée principal du programme CIUP.
     * 
     * Lance les tests et démonstrations du système de gestion
     * de la Cité Universitaire en déléguant à CIUPFactory.
     * 
     * @param args les arguments de ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        System.out.println("=== Démarrage des tests CIUP ===");
        
        // Appel direct du main de CIUPFactory
        CIUPFactory.main(args);
        
        System.out.println("\n=== Fin des tests CIUP ===");
    }
}