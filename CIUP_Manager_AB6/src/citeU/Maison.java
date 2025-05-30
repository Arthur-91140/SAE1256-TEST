package citeU;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * Classe abstraite représentant une maison dans la Cité Universitaire de Paris.
 * 
 * Cette classe fournit les fonctionnalités de base communes à tous les types de maisons,
 * notamment la gestion des restaurations associées. Elle sert de classe parent pour
 * les types spécifiques de maisons comme MaisonEtudiant et MaisonInternationale.
 */
public class Maison implements Serializable {
    
    private static final long serialVersionUID = 1L;

    protected ArrayList<Restauration> sesRestaurations;
    protected String nom;
    protected String localisation;
    protected String nomDirecteur;

    /**
     * Construit une nouvelle maison avec les informations spécifiées.
     * 
     * @param nom le nom de la maison
     * @param localisation l'adresse ou localisation de la maison
     * @param nomDirecteur le nom du directeur de la maison
     */
    public Maison(String nom, String localisation, String nomDirecteur) {
        this.nom = nom;
        this.localisation = localisation;
        this.nomDirecteur = nomDirecteur;
        sesRestaurations = new ArrayList<Restauration>();
    }

    /**
     * Affiche la liste de toutes les restaurations associées à cette maison.
     * Pour chaque restauration, affiche le nom et la capacité.
     */
    public void afficheRestaurations() {
        System.out.println("Liste des restaurations de la maison : " + nom);
        for (Restauration restauration : sesRestaurations) {
            System.out.println("nom : " + restauration.getNom() + ", capacité : " + restauration.getCapacite());
        }
    }

    /**
     * Récupère la liste des restaurations de cette maison.
     * 
     * @return la liste des restaurations
     */
    public ArrayList<Restauration> getSesRestaurations() {
        return sesRestaurations;
    }

    /**
     * Définit la liste des restaurations pour cette maison.
     * 
     * @param sesRestaurations la nouvelle liste de restaurations
     */
    public void setSesRestaurations(ArrayList<Restauration> sesRestaurations) {
        this.sesRestaurations = sesRestaurations;
    }

    /**
     * Récupère le nom de la maison.
     * 
     * @return le nom de la maison
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom de la maison.
     * 
     * @param nom le nouveau nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère la localisation de la maison.
     * 
     * @return la localisation
     */
    public String getLocalisation() {
        return localisation;
    }

    /**
     * Définit la localisation de la maison.
     * 
     * @param localisation la nouvelle localisation
     */
    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    /**
     * Récupère le nom du directeur de la maison.
     * 
     * @return le nom du directeur
     */
    public String getNomDirecteur() {
        return nomDirecteur;
    }

    /**
     * Définit le nom du directeur de la maison.
     * 
     * @param nomDirecteur le nouveau nom du directeur
     */
    public void setNomDirecteur(String nomDirecteur) {
        this.nomDirecteur = nomDirecteur;
    }

    /**
     * Ajoute une restauration à cette maison.
     * Établit également la relation bidirectionnelle en définissant cette maison
     * comme maison parent de la restauration.
     * 
     * @param restauration la restauration à ajouter
     */
    public void ajouteRestauration(Restauration restauration) {
        sesRestaurations.add(restauration);
        restauration.setSaMaison(this);
    }

    /**
     * Retire une restauration de cette maison.
     * 
     * @param restauration la restauration à retirer
     */
    public void retireRestauration(Restauration restauration) {
        sesRestaurations.remove(restauration);
    }
}
