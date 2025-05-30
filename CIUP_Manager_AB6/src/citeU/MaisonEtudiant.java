package citeU;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * Représente une maison d'étudiant spécialisée pour une nationalité donnée.
 * 
 * Cette classe étend Maison et ajoute la gestion spécifique des étudiants,
 * incluant la capacité en chambres, la vérification de disponibilité et
 * la recherche d'étudiants. Une maison d'étudiant est typiquement dédiée
 * à une nationalité particulière.
 */
public class MaisonEtudiant extends Maison implements Serializable {
    private static final long serialVersionUID = 1L;

    private ArrayList<Etudiant> sesEtudiants;
    private String nationalite;
    private int nombreChambres;

    /**
     * Construit une nouvelle maison d'étudiant.
     * 
     * @param nationalite la nationalité ciblée par cette maison
     * @param nombreChambres le nombre total de chambres disponibles
     * @param nom le nom de la maison
     * @param localisation l'adresse de la maison
     * @param nomDirecteur le nom du directeur
     */
    public MaisonEtudiant(String nationalite, int nombreChambres, String nom, String localisation,
            String nomDirecteur) {
        super(nom, localisation, nomDirecteur);
        this.nationalite = nationalite;
        this.nombreChambres = nombreChambres;
        sesEtudiants = new ArrayList<Etudiant>();
    }

    /**
     * Affiche la liste complète des étudiants résidant dans cette maison.
     * Pour chaque étudiant, affiche le nom, prénom et nationalité.
     */
    public void afficheListeEtudiants() {
        System.out.println("Liste des etudiants de la maison: " + nom);
        for (Etudiant etudiant : sesEtudiants) {
            System.out.println("Nom: " + etudiant.getNom() + ", Prenom: " + etudiant.getPrenom() + ", Nationalite: "
                    + etudiant.getNationalite());
        }
    }

    /**
     * Ajoute un étudiant à cette maison.
     * Établit la relation bidirectionnelle en définissant cette maison
     * comme maison de l'étudiant.
     * 
     * @param etudiant l'étudiant à ajouter
     */
    public void ajouteEtudiant(Etudiant etudiant) {
        sesEtudiants.add(etudiant);
        etudiant.setSaMaison(this);
    }

    /**
     * Retire un étudiant de cette maison.
     * 
     * @param etudiant l'étudiant à retirer
     */
    public void retireEtudiant(Etudiant etudiant) {
        sesEtudiants.remove(etudiant);
    }

    /**
     * Calcule le nombre de places disponibles dans la maison.
     * 
     * @return le nombre de chambres libres (nombre total - étudiants actuels)
     */
    public int placesDisponibles() {
        return nombreChambres - sesEtudiants.size();
    }

    /**
     * Recherche un étudiant dans cette maison par son nom de famille.
     * 
     * @param nom le nom de famille à rechercher
     * @return l'étudiant trouvé
     * @throws RuntimeException si aucun étudiant n'est trouvé avec ce nom
     */
    public Etudiant rechercherEtudiantParNom(String nom) {
        for (int index = 0; index < sesEtudiants.size(); index++) {
            if (sesEtudiants.get(index).getNom().equals(nom)) {
                return sesEtudiants.get(index);
            }
        }
        throw new RuntimeException("Étudiant non trouvé: " + nom);
    }

    /**
     * Vérifie s'il y a encore des places disponibles dans la maison.
     * 
     * @return true s'il reste au moins une place libre, false sinon
     */
    public boolean verifierDisponibilite() {
        return placesDisponibles() > 0;
    }

    /**
     * Récupère la liste des étudiants de cette maison.
     * 
     * @return la liste des étudiants
     */
    public ArrayList<Etudiant> getSesEtudiants() {
        return sesEtudiants;
    }

    /**
     * Définit la liste des étudiants pour cette maison.
     * 
     * @param sesEtudiants la nouvelle liste d'étudiants
     */
    public void setSesEtudiants(ArrayList<Etudiant> sesEtudiants) {
        this.sesEtudiants = sesEtudiants;
    }

    /**
     * Récupère la nationalité ciblée par cette maison.
     * 
     * @return la nationalité
     */
    public String getNationalite() {
        return nationalite;
    }

    /**
     * Définit la nationalité ciblée par cette maison.
     * 
     * @param nationalite la nouvelle nationalité
     */
    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    /**
     * Récupère le nombre total de chambres de la maison.
     * 
     * @return le nombre de chambres
     */
    public int getNombreChambres() {
        return nombreChambres;
    }

    /**
     * Définit le nombre total de chambres de la maison.
     * 
     * @param nombreChambres le nouveau nombre de chambres
     */
    public void setNombreChambres(int nombreChambres) {
        this.nombreChambres = nombreChambres;
    }
}