package citeU;

import java.util.ArrayList;

/**
 * Classe de test et démonstration du système de gestion des cités universitaires.
 * 
 * Cette classe contient la méthode main qui teste les fonctionnalités principales
 * du système CIUP, incluant la gestion des étudiants, des maisons, des restaurations
 * et des menus.
 */
public class CIUPFactory {
	
    /**
     * Point d'entrée principal pour tester les fonctionnalités du système CIUP.
     * 
     * Effectue une série de tests comprenant :
     * - Création et gestion d'étudiants
     * - Gestion des maisons étudiantes et internationales
     * - Gestion des restaurations et menus
     * - Test des fonctionnalités de recherche et disponibilité
     * 
     * @param args Arguments de ligne de commande (non utilisés)
     */
	public static void main(String args[]) {
		// Création d'étudiants
		Etudiant etudiant1 = new Etudiant("Bailly", "Maxence", "France");
		Etudiant etudiant2 = new Etudiant("Pruvost", "Arthur", "France");

		// Test d'affichage de la maison d'étudiant
		etudiant1.afficheMaison();

		// Création d'une maison étudiant
		MaisonEtudiant maison1 = new MaisonEtudiant("France", 237, "Maison France", "Batiment 336 bis rue des roses",
				"Elon Musk");

		// Test des fonctions d'ajouts et supressions d'etudiants ainsi que l'arffichage
		// de la liste des etudiants d'une maison
		maison1.ajouteEtudiant(etudiant1);
		maison1.ajouteEtudiant(etudiant2);
		maison1.afficheListeEtudiants();
		maison1.retireEtudiant(etudiant2);
		maison1.afficheListeEtudiants();

		// Test des fonctions de disponibilités des maisons
		System.out.println(maison1.placesDisponibles());
		System.out.println(maison1.verifierDisponibilite());
		maison1.ajouteEtudiant(etudiant2);

		// Test de recherche d'etudiants par noms
		System.out.println(maison1.rechercherEtudiantParNom("Pruvost").getNom());
		try {
			System.out.println(maison1.rechercherEtudiantParNom("Blabla").getNom());
		} catch (RuntimeException e) {
			System.out.println("Erreur: " + e);
		}

		// Création d'une maison internationale
		MaisonInternationale maison2 = new MaisonInternationale("Maison Internationale", "42 rue de la marinière",
				"Jeff Besos");

		// Création d'une Restauration
		Restauration resto1 = new Restauration("la cantine internationale", 300);
		Restauration resto2 = new Restauration("la cantine paris sud", 500);

		// Test des méthodes sur les Restaurations de la classe Maison
		maison2.ajouteRestauration(resto1);
		maison2.ajouteRestauration(resto2);
		maison2.afficheRestaurations();
		maison2.retireRestauration(resto1);
		maison2.afficheRestaurations();

		// Création d'un menu
		Menu menu1 = new Menu("intoxication", "salade composé", "saucisses lentilles", "creme au citron", 3.30);
		Menu menu2 = new Menu("fin année", "friant fromage", "burger frites", "glace fraise", 4.40);

		// Test des méthodes sur les menus dans la classe Restauration
		resto1.ajouteMenu(menu2);
		resto1.ajouteMenu(menu1);
		resto2.ajouteMenu(menu1);
		resto1.afficheMenus();
		resto1.retireMenu(menu2);
		resto1.afficheMenus();

		// Test de la disponibilité d'un menu
		ArrayList<Restauration> listeRestaurantsDuMenu = new ArrayList<Restauration>();
		listeRestaurantsDuMenu = menu1.disponibiliteRestaurations();
		for (Restauration restaurant : listeRestaurantsDuMenu) {
			System.out.println(restaurant.getNom());
		}

	}
}
