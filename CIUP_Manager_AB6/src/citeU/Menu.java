package citeU;

import java.util.*;
import java.util.ArrayList;
import java.io.Serializable;

/**
 * Représente un menu proposé dans les restaurations de la Cité Universitaire.
 * 
 * Un menu est composé d'une entrée, d'un plat principal, d'un dessert et d'un prix.
 * Il peut être proposé par plusieurs restaurations simultanément et maintient
 * la liste de ces restaurations.
 */
public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;

    private ArrayList<Restauration> saRestauration;
    private String nom;
    private String entree;
    private String plat;
    private String dessert;
    private double prix;

    /**
     * Construit un nouveau menu avec tous ses composants.
     * 
     * @param nom le nom du menu
     * @param entree la description de l'entrée
     * @param plat la description du plat principal
     * @param dessert la description du dessert
     * @param prix le prix du menu en euros
     */
    public Menu(String nom, String entree, String plat, String dessert, double prix) {
        this.nom = nom;
        this.entree = entree;
        this.plat = plat;
        this.dessert = dessert;
        this.prix = prix;
        this.saRestauration = new ArrayList<Restauration>();
    }

    /**
     * Récupère la liste des restaurations qui proposent ce menu.
     * 
     * @return une nouvelle ArrayList contenant toutes les restaurations
     *         qui proposent ce menu
     */
    public ArrayList<Restauration> disponibiliteRestaurations() {
        ArrayList<Restauration> listeRestaurations = new ArrayList<Restauration>();
        
        for (int i = 0; i < saRestauration.size(); i++) {
            listeRestaurations.add(saRestauration.get(i));
        }
        
        return listeRestaurations;
    }

    /**
     * Récupère la liste des restaurations associées à ce menu.
     * 
     * @return la liste des restaurations
     */
    public ArrayList<Restauration> getSaRestauration() {
        return saRestauration;
    }

    /**
     * Définit la liste des restaurations pour ce menu.
     * 
     * @param saRestauration la nouvelle liste de restaurations
     */
    public void setSaRestauration(ArrayList<Restauration> saRestauration) {
        this.saRestauration = saRestauration;
    }

    /**
     * Récupère le nom du menu.
     * 
     * @return le nom du menu
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom du menu.
     * 
     * @param nom le nouveau nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère la description de l'entrée.
     * 
     * @return la description de l'entrée
     */
    public String getEntree() {
        return entree;
    }

    /**
     * Définit la description de l'entrée.
     * 
     * @param entree la nouvelle description de l'entrée
     */
    public void setEntree(String entree) {
        this.entree = entree;
    }

    /**
     * Récupère la description du plat principal.
     * 
     * @return la description du plat
     */
    public String getPlat() {
        return plat;
    }

    /**
     * Définit la description du plat principal.
     * 
     * @param plat la nouvelle description du plat
     */
    public void setPlat(String plat) {
        this.plat = plat;
    }

    /**
     * Récupère la description du dessert.
     * 
     * @return la description du dessert
     */
    public String getDessert() {
        return dessert;
    }

    /**
     * Définit la description du dessert.
     * 
     * @param dessert la nouvelle description du dessert
     */
    public void setDessert(String dessert) {
        this.dessert = dessert;
    }

    /**
     * Récupère le prix du menu.
     * 
     * @return le prix en euros
     */
    public double getPrix() {
        return prix;
    }

    /**
     * Définit le prix du menu.
     * 
     * @param prix le nouveau prix en euros
     */
    public void setPrix(double prix) {
        this.prix = prix;
    }
}