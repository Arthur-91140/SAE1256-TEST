package citeU;

public class CIUP {
    public static void main(String[] args) {
        System.out.println("=== Démarrage des tests CIUP ===");
        
        // Appel des différentes fonctions de CIUPFactory une par une
        System.out.println("\n1. Création des étudiants");
        CIUPFactory.creerEtudiants();
        
        System.out.println("\n2. Création et test des maisons d'étudiants");
        CIUPFactory.creerEtTesterMaisonEtudiant();
        
        System.out.println("\n3. Test des disponibilités des maisons");
        CIUPFactory.testerDisponibiliteMaison();
        
        System.out.println("\n4. Test de recherche d'étudiants");
        CIUPFactory.testerRechercheEtudiant();
        
        System.out.println("\n5. Création et test des maisons internationales");
        CIUPFactory.creerEtTesterMaisonInternationale();
        
        System.out.println("\n6. Création et test des menus");
        CIUPFactory.creerEtTesterMenus();
        
        System.out.println("\n7. Test de disponibilité des menus");
        CIUPFactory.testerDisponibiliteMenus();
        
        System.out.println("\n=== Fin des tests CIUP ===");
    }
}
