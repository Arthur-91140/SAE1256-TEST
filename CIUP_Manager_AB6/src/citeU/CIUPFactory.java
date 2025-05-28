package citeU;

import java.util.ArrayList;

public class CIUPFactory {
    
    // Attributs pour stocker les instances
    private static Etudiant etudiant1;
    private static Etudiant etudiant2;
    private static MaisonEtudiant maison1;
    private static MaisonInternationale maison2;
    private static Restauration resto1;
    private static Restauration resto2;
    private static Menu menu1;
    private static Menu menu2;
    
    // Méthode pour créer des étudiants
    public static void creerEtudiants() {
        System.out.println("\n=== Création d'étudiants ===");
        etudiant1 = new Etudiant("Bailly", "Maxence", "France");
        etudiant2 = new Etudiant("Pruvost", "Arthur", "France");
        
        // Test d'affichage de la maison d'étudiant
        System.out.println("Affichage de la maison de l'étudiant 1 :");
        etudiant1.afficheMaison();
    }
    
    // Méthode pour créer et tester les maisons d'étudiants
    public static void creerEtTesterMaisonEtudiant() {
        System.out.println("\n=== Test des maisons d'étudiants ===");
        // Création d'une maison étudiant
        maison1 = new MaisonEtudiant("France", 237, "Maison France", "Batiment 336 bis rue des roses",
                "Elon Musk");
        
        // Test des fonctions d'ajouts et supressions d'étudiants
        System.out.println("Ajout des étudiants à la maison :");
        maison1.ajouteEtudiant(etudiant1);
        maison1.ajouteEtudiant(etudiant2);
        
        System.out.println("Liste des étudiants après ajout :");
        maison1.afficheListeEtudiants();
        
        System.out.println("Retrait d'un étudiant :");
        maison1.retireEtudiant(etudiant2);
        
        System.out.println("Liste des étudiants après retrait :");
        maison1.afficheListeEtudiants();
    }
    
    // Méthode pour tester les disponibilités des maisons
    public static void testerDisponibiliteMaison() {
        System.out.println("\n=== Test des disponibilités des maisons ===");
        System.out.println("Places disponibles : " + maison1.placesDisponibles());
        System.out.println("Vérification disponibilité : " + maison1.verifierDisponibilite());
        System.out.println("Ajout de l'étudiant 2 à nouveau :");
        maison1.ajouteEtudiant(etudiant2);
    }
    
    // Méthode pour tester la recherche d'étudiants
    public static void testerRechercheEtudiant() {
        System.out.println("\n=== Test de recherche d'étudiants ===");
        // Test de recherche d'étudiants par noms
        System.out.println("Recherche d'un étudiant par nom 'Pruvost' : " 
                + maison1.rechercherEtudiantParNom("Pruvost").getNom());
        
        try {
            System.out.println("Recherche d'un étudiant inexistant 'Blabla' : " 
                    + maison1.rechercherEtudiantParNom("Blabla").getNom());
        } catch (RuntimeException e) {
            System.out.println("Erreur attendue : " + e);
        }
    }
    
    // Méthode pour créer et tester les maisons internationales
    public static void creerEtTesterMaisonInternationale() {
        System.out.println("\n=== Test des maisons internationales ===");
        // Création d'une maison internationale
        maison2 = new MaisonInternationale("Maison Internationale", "42 rue de la marinière",
                "Jeff Besos");
        
        // Création des restaurations
        resto1 = new Restauration("la cantine internationale", 300);
        resto2 = new Restauration("la cantine paris sud", 500);
        
        // Test des méthodes sur les Restaurations de la classe Maison
        System.out.println("Ajout des restaurations à la maison internationale :");
        maison2.ajouteRestauration(resto1);
        maison2.ajouteRestauration(resto2);
        
        System.out.println("Liste des restaurations après ajout :");
        maison2.afficheRestaurations();
        
        System.out.println("Retrait d'une restauration :");
        maison2.retireRestauration(resto1);
        
        System.out.println("Liste des restaurations après retrait :");
        maison2.afficheRestaurations();
    }
    
    // Méthode pour créer et tester les menus
    public static void creerEtTesterMenus() {
        System.out.println("\n=== Test des menus ===");
        // Création d'un menu
        menu1 = new Menu("intoxication", "salade composé", "saucisses lentilles", "creme au citron", 3.30);
        menu2 = new Menu("fin année", "friant fromage", "burger frites", "glace fraise", 4.40);
        
        // Test des méthodes sur les menus dans la classe Restauration
        System.out.println("Ajout des menus aux restaurations :");
        resto1.ajouteMenu(menu2);
        resto1.ajouteMenu(menu1);
        resto2.ajouteMenu(menu1);
        
        System.out.println("Liste des menus du restaurant 1 après ajout :");
        resto1.afficheMenus();
        
        System.out.println("Retrait d'un menu :");
        resto1.retireMenu(menu2);
        
        System.out.println("Liste des menus du restaurant 1 après retrait :");
        resto1.afficheMenus();
    }
    
    // Méthode pour tester la disponibilité des menus
    public static void testerDisponibiliteMenus() {
        System.out.println("\n=== Test de disponibilité des menus ===");
        
        // Test de la disponibilité d'un menu
        ArrayList<Restauration> listeRestaurantsDuMenu = new ArrayList<Restauration>();
        listeRestaurantsDuMenu = menu1.disponibiliteRestaurations();
        
        System.out.println("Restaurants proposant le menu 'intoxication' :");
        for (Restauration restaurant : listeRestaurantsDuMenu) {
            System.out.println(restaurant.getNom());
        }
    }
    
    // Méthode main originale (maintenue pour compatibilité)
    public static void main(String args[]) {
        creerEtudiants();
        creerEtTesterMaisonEtudiant();
        testerDisponibiliteMaison();
        testerRechercheEtudiant();
        creerEtTesterMaisonInternationale();
        creerEtTesterMenus();
        testerDisponibiliteMenus();
    }
}
