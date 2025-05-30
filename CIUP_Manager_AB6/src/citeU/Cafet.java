package citeU;

import java.io.Serializable;

/**
 * Représente une cafétéria dans la Cité Universitaire de Paris.
 * 
 * Cette classe hérite de Restauration et représente un type spécialisé
 * de service de restauration plus informel qu'un restaurant universitaire.
 * Une cafétéria propose généralement des collations, boissons et repas légers.
 */
public class Cafet extends Restauration implements Serializable {
    
    private static final long serialVersionUID = 1L;

    /**
     * Construit une nouvelle cafétéria.
     * 
     * @param nom le nom de la cafétéria
     * @param capacite la capacité d'accueil en nombre de personnes
     */
    public Cafet(String nom, int capacite) {
        super(nom, capacite);
    }
}