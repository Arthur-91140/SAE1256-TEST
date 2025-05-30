package citeU;

import java.io.Serializable;

/**
 * Représente un étudiant dans le système de gestion de la Cité Universitaire de Paris.
 * 
 * Cette classe gère les informations personnelles d'un étudiant et sa relation
 * avec une maison d'étudiant. Un étudiant peut être associé à une maison spécifique
 * et possède des informations d'identification (nom, prénom, nationalité).
 */
public class Etudiant implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private MaisonEtudiant saMaison;
    private String nom;
    private String prenom;
    private String nationalite;

    /**
     * Construit un nouvel étudiant avec les informations spécifiées.
     * 
     * @param nom le nom de famille de l'étudiant
     * @param prenom le prénom de l'étudiant
     * @param nationalite la nationalité de l'étudiant
     */
    public Etudiant(String nom, String prenom, String nationalite) {
        this.nom = nom;
        this.prenom = prenom;
        this.nationalite = nationalite;
    }

    /**
     * Affiche les informations de la maison à laquelle appartient l'étudiant.
     * Si l'étudiant n'est associé à aucune maison, affiche null.
     */
    public void afficheMaison() {
        System.out.println(saMaison);
    }

    /**
     * Récupère la maison d'étudiant associée à cet étudiant.
     * 
     * @return la maison d'étudiant de l'étudiant, ou null si aucune n'est assignée
     */
    public MaisonEtudiant getSaMaison() {
        return saMaison;
    }

    /**
     * Définit la maison d'étudiant pour cet étudiant.
     * 
     * @param saMaison la maison d'étudiant à associer
     */
    public void setSaMaison(MaisonEtudiant saMaison) {
        this.saMaison = saMaison;
    }

    /**
     * Récupère le nom de famille de l'étudiant.
     * 
     * @return le nom de famille
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom de famille de l'étudiant.
     * 
     * @param nom le nouveau nom de famille
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère le prénom de l'étudiant.
     * 
     * @return le prénom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Définit le prénom de l'étudiant.
     * 
     * @param prenom le nouveau prénom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Récupère la nationalité de l'étudiant.
     * 
     * @return la nationalité
     */
    public String getNationalite() {
        return nationalite;
    }

    /**
     * Définit la nationalité de l'étudiant.
     * 
     * @param nationalite la nouvelle nationalité
     */
    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }
}