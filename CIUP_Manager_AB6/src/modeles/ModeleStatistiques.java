package modeles;

import java.util.*;
import citeU.*;

/**
 * Modèle pour le calcul des statistiques de la CIUP
 * @author Maxence Bailly
 */
public class ModeleStatistiques {
    
    private GestionnaireCIUP gestionnaire;
    
    //-----------------------------
    // CONSTRUCTEUR
    //-----------------------------
    
    public ModeleStatistiques(GestionnaireCIUP gestionnaire) {
        this.gestionnaire = gestionnaire;
    }
    
    //-----------------------------
    // MÉTHODES DE CALCUL
    //-----------------------------
    
    /**
     * Calcule le nombre total d'étudiants
     */
    public int getNombreTotalEtudiants() {
        return gestionnaire.getEtudiants().size();
    }
    
    /**
     * Calcule le nombre total de maisons
     */
    public int getNombreTotalMaisons() {
        return gestionnaire.getMaisonsEtudiants().size() + 
               gestionnaire.getMaisonsInternationales().size();
    }
    
    /**
     * Calcule le taux d'occupation d'une maison d'étudiants
     */
    public double getTauxOccupation(MaisonEtudiant maison) {
        if (maison.getNombreChambres() == 0) return 0.0;
        return (double) maison.getSesEtudiants().size() / maison.getNombreChambres() * 100;
    }
    
    /**
     * Calcule la répartition des étudiants par nationalité
     */
    public Map<String, Integer> getRepartitionParNationalite() {
        Map<String, Integer> repartition = new HashMap<>();
        
        for (Etudiant etudiant : gestionnaire.getEtudiants()) {
            String nationalite = etudiant.getNationalite();
            repartition.put(nationalite, repartition.getOrDefault(nationalite, 0) + 1);
        }
        
        return repartition;
    }
    
    /**
     * Calcule le nombre moyen de menus par restaurant
     */
    public double getNombreMoyenMenusParRestaurant() {
        ArrayList<Restauration> restaurants = gestionnaire.getRestaurations();
        if (restaurants.isEmpty()) return 0.0;
        
        int totalMenus = 0;
        for (Restauration restaurant : restaurants) {
            totalMenus += restaurant.getSesMenus().size();
        }
        
        return (double) totalMenus / restaurants.size();
    }
    
    /**
     * Trouve les maisons avec des places disponibles
     */
    public ArrayList<MaisonEtudiant> getMaisonsAvecPlacesDisponibles() {
        ArrayList<MaisonEtudiant> maisonsDisponibles = new ArrayList<>();
        
        for (MaisonEtudiant maison : gestionnaire.getMaisonsEtudiants()) {
            if (maison.verifierDisponibilite()) {
                maisonsDisponibles.add(maison);
            }
        }
        
        return maisonsDisponibles;
    }
    
    /**
     * Calcule le prix moyen des menus
     */
    public double getPrixMoyenMenus() {
        // Utiliser un Set pour éviter de compter les menus en double
        Set<Menu> menusUniques = new HashSet<>();
        
        // Parcourir toutes les restaurations et collecter leurs menus
        for (Restauration restauration : gestionnaire.getRestaurations()) {
            menusUniques.addAll(restauration.getSesMenus());
        }
        
        // Si aucun menu n'existe
        if (menusUniques.isEmpty()) return 0.0;
        
        // Calculer la somme des prix
        double totalPrix = 0.0;
        for (Menu menu : menusUniques) {
            totalPrix += menu.getPrix();
        }
        
        return totalPrix / menusUniques.size();
    }
}