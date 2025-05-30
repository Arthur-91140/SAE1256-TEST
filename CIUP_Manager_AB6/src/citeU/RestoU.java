package citeU;

import java.io.Serializable;

/**
 * Représente un restaurant universitaire (RestoU) dans la Cité Universitaire.
 * 
 * Cette classe hérite de Restauration et représente un type spécialisé
 * de service de restauration destiné spécifiquement aux étudiants universitaires.
 * Un RestoU propose généralement des repas complets à prix réduit.
 */
public class RestoU extends Restauration implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Construit un nouveau restaurant universitaire.
     * 
     * @param nom le nom du restaurant universitaire
     * @param capacite la capacité d'accueil en nombre de personnes
     */
    public RestoU(String nom, int capacite) {
        super(nom, capacite);
    }
}