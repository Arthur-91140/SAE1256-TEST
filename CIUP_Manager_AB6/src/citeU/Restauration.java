package citeU;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * Représente un service de restauration dans une maison de la Cité Universitaire.
 * 
 * Cette classe gère les menus proposés par un service de restauration et maintient
 * la relation avec la maison qui l'héberge. Elle sert de classe parent pour les
 * types spécialisés de restauration comme RestoU et Cafet.
 */
public class Restauration implements Serializable {
    private static final long serialVersionUID = 1L;

    protected ArrayList<Menu> sesMenus;
    protected Maison saMaison;
    protected String nom;
    protected int capacite;

    /**
     * Construit un nouveau service de restauration.
     * 
     * @param nom le nom du service de restauration
     * @param capacite la capacité d'accueil en nombre de personnes
     */
    public Restauration(String nom, int capacite) {
        this.nom = nom;
        this.capacite = capacite;
        this.sesMenus = new ArrayList<Menu>();
    }

    /**
     * Affiche la liste complète des menus proposés par cette restauration.
     * Pour chaque menu, affiche le nom, l'entrée, le plat, le dessert et le prix.
     */
    public void afficheMenus() {
        System.out.println("Liste des menus de la restauration: " + nom);
        for (Menu menu : sesMenus) {
            System.out.println("Nom: " + menu.getNom() + ", Entrée: " + menu.getEntree() + ", Plat: " + menu.getPlat()
                    + ", Dessert: " + menu.getDessert() + ", Prix: " + menu.getPrix());
        }
    }

    /**
     * Ajoute un menu à cette restauration.
     * Établit également la relation bidirectionnelle en ajoutant cette restauration
     * à la liste des restaurations qui proposent ce menu.
     * 
     * @param menu le menu à ajouter
     */
    public void ajouteMenu(Menu menu) {
        sesMenus.add(menu);
        ArrayList<Restauration> listeRestauration = menu.getSaRestauration();
        listeRestauration.add(this);
        menu.setSaRestauration(listeRestauration);
    }

    /**
     * Retire un menu de cette restauration.
     * 
     * @param menu le menu à retirer
     */
    public void retireMenu(Menu menu) {
        sesMenus.remove(menu);
    }

    /**
     * Récupère la liste des menus de cette restauration.
     * 
     * @return la liste des menus
     */
    public ArrayList<Menu> getSesMenus() {
        return sesMenus;
    }

    /**
     * Définit la liste des menus pour cette restauration.
     * 
     * @param sesMenus la nouvelle liste de menus
     */
    public void setSesMenus(ArrayList<Menu> sesMenus) {
        this.sesMenus = sesMenus;
    }

    /**
     * Récupère la maison qui héberge cette restauration.
     * 
     * @return la maison parent
     */
    public Maison getSaMaison() {
        return saMaison;
    }

    /**
     * Définit la maison qui héberge cette restauration.
     * 
     * @param saMaison la maison parent
     */
    public void setSaMaison(Maison saMaison) {
        this.saMaison = saMaison;
    }

    /**
     * Récupère le nom de la restauration.
     * 
     * @return le nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom de la restauration.
     * 
     * @param nom le nouveau nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère la capacité d'accueil de la restauration.
     * 
     * @return la capacité en nombre de personnes
     */
    public int getCapacite() {
        return capacite;
    }

    /**
     * Définit la capacité d'accueil de la restauration.
     * 
     * @param capacite la nouvelle capacité
     */
    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }
}