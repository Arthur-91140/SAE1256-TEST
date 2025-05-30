package citeU;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * Représente une maison internationale dans la Cité Universitaire de Paris.
 * 
 * Cette classe hérite de Maison et représente un type spécial de maison
 * qui accueille des étudiants de toutes nationalités, contrairement aux
 * maisons d'étudiants qui sont spécialisées par nationalité.
 */
public class MaisonInternationale extends Maison implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Construit une nouvelle maison internationale.
     * 
     * @param nom le nom de la maison internationale
     * @param localisation l'adresse de la maison
     * @param nomDirecteur le nom du directeur
     */
    public MaisonInternationale(String nom, String localisation, String nomDirecteur) {
        super(nom, localisation, nomDirecteur);
    }
}