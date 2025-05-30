package modeles;

import java.util.*;
import java.io.*;
import citeU.*;
import controleurs.ControleurPrincipal;

/**
 * Modèle principal gérant toutes les données de la CIUP
 * Implémente Serializable pour la sauvegarde
 * @author Maxence Bailly
 */
public class GestionnaireCIUP implements InterfaceModele, Serializable {
    
    private static final long serialVersionUID = 1L;
    private static final String FICHIER_SAUVEGARDE = "ciup_data.dat";
    
    //-----------------------------
    // ATTRIBUTS
    //-----------------------------
    private ArrayList<citeU.Etudiant> etudiants;
    private ArrayList<citeU.MaisonEtudiant> maisonsEtudiants;
    private ArrayList<citeU.MaisonInternationale> maisonsInternationales;
    private ArrayList<citeU.Restauration> restaurations;
    private ArrayList<citeU.Menu> menus;
    
    // Transient pour éviter la sérialisation du contrôleur
    private transient ControleurPrincipal controleurPrincipal;
    
    //-----------------------------
    // CONSTRUCTEUR
    //-----------------------------
    public GestionnaireCIUP() {
        etudiants = new ArrayList<>();
        maisonsEtudiants = new ArrayList<>();
        maisonsInternationales = new ArrayList<>();
        restaurations = new ArrayList<>();
        menus = new ArrayList<>();
        
        chargerDonnees();
    }
    
    //-----------------------------
    // MÉTHODES D'INITIALISATION
    //-----------------------------
    
    /**
     * Initialise les données avec CIUPFactory si aucune sauvegarde n'existe
     */
    public void initialiserDonnees() {
        if (etudiants.isEmpty()) {
            // Création d'étudiants
            citeU.Etudiant etudiant1 = new citeU.Etudiant("Bailly", "Maxence", "France");
            citeU.Etudiant etudiant2 = new citeU.Etudiant("Pruvost", "Arthur", "France");
            citeU.Etudiant etudiant3 = new citeU.Etudiant("Petković", "Tomislav", "Croatie");
            
            etudiants.add(etudiant1);
            etudiants.add(etudiant2);
            etudiants.add(etudiant3);
            
            // Création de maisons d'étudiants
            citeU.MaisonEtudiant maisonFrance = new citeU.MaisonEtudiant("France", 237, "Maison France", 
                "13 bis avenue des Sciences Gifs sur Yvette", "Mark Babić");
            citeU.MaisonEtudiant maisonCroatie = new citeU.MaisonEtudiant("Croatie", 130, "Hrvatska Kuća", 
                "13 bis Avenue des Sciences Gifs sur Yvette", "Miloš Milaković");
            
            maisonsEtudiants.add(maisonFrance);
            maisonsEtudiants.add(maisonCroatie);
            
            // Affectation des étudiants
            maisonFrance.ajouteEtudiant(etudiant1);
            maisonFrance.ajouteEtudiant(etudiant2);
            maisonCroatie.ajouteEtudiant(etudiant3);
            
            // Création de maisons internationales
            citeU.MaisonInternationale maisonInt = new citeU.MaisonInternationale(
                "Maison Internationale", "42 rue de la marinière", "Jeff Bezos");
            maisonsInternationales.add(maisonInt);
            
            // Création de restaurations
            citeU.Restauration resto1 = new citeU.RestoU("Pizzzza", 300);
            citeU.Restauration resto2 = new citeU.Cafet("Eggso", 150);
            
            restaurations.add(resto1);
            restaurations.add(resto2);
            
            // Ajout des restaurations aux maisons
            maisonInt.ajouteRestauration(resto1);
            maisonInt.ajouteRestauration(resto2);
            
            // Création de menus
            citeU.Menu menu1 = new citeU.Menu("Menu Étudiant", "Salade composée", 
                "Saucisses lentilles", "Crème au citron", 3.30);
            citeU.Menu menu2 = new citeU.Menu("Menu Gourmet", "Friand au fromage", 
                "Burger frites", "Glace fraise", 4.40);
            
            menus.add(menu1);
            menus.add(menu2);
            
            // Association menus-restaurants
            resto1.ajouteMenu(menu1);
            resto1.ajouteMenu(menu2);
            resto2.ajouteMenu(menu1);
            
            sauvegarderDonnees();
        }
    }
    
    //-----------------------------
    // MÉTHODES CRUD ÉTUDIANTS
    //-----------------------------
    
    public void ajouterEtudiant(citeU.Etudiant etudiant) {
        etudiants.add(etudiant);
        notifierObservateurs();
    }
    
    public void supprimerEtudiant(citeU.Etudiant etudiant) {
        if (etudiant.getSaMaison() != null) {
            etudiant.getSaMaison().retireEtudiant(etudiant);
        }
        etudiants.remove(etudiant);
        notifierObservateurs();
    }
    
    public citeU.Etudiant rechercherEtudiantParNom(String nom) {
        for (citeU.Etudiant etudiant : etudiants) {
            if (etudiant.getNom().equalsIgnoreCase(nom)) {
                return etudiant;
            }
        }
        return null;
    }
    
    //-----------------------------
    // MÉTHODES CRUD MAISONS
    //-----------------------------
    
    public void ajouterMaisonEtudiant(citeU.MaisonEtudiant maison) {
        maisonsEtudiants.add(maison);
        notifierObservateurs();
    }
    
    public void ajouterMaisonInternationale(citeU.MaisonInternationale maison) {
        maisonsInternationales.add(maison);
        notifierObservateurs();
    }
    
    //-----------------------------
    // MÉTHODES CRUD RESTAURATIONS
    //-----------------------------
    
    public void ajouterRestauration(citeU.Restauration restauration) {
        restaurations.add(restauration);
        notifierObservateurs();
    }
    
    public void ajouterMenu(citeU.Menu menu) {
        menus.add(menu);
        notifierObservateurs();
    }
    
    //-----------------------------
    // ACCESSEURS
    //-----------------------------
    
    public ArrayList<citeU.Etudiant> getEtudiants() {
        return etudiants;
    }
    
    public ArrayList<citeU.MaisonEtudiant> getMaisonsEtudiants() {
        return maisonsEtudiants;
    }
    
    public ArrayList<citeU.MaisonInternationale> getMaisonsInternationales() {
        return maisonsInternationales;
    }
    
    public ArrayList<citeU.Restauration> getRestaurations() {
        return restaurations;
    }
    
    public ArrayList<citeU.Menu> getMenus() {
        return menus;
    }
    
    public void setControleurPrincipal(ControleurPrincipal controleur) {
        this.controleurPrincipal = controleur;
    }
    
    //-----------------------------
    // IMPLÉMENTATION INTERFACE
    //-----------------------------
    
    @Override
    public void chargerDonnees() {
        File fichier = new File(FICHIER_SAUVEGARDE);
        if (fichier.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichier))) {
                // Lire uniquement les données, pas l'objet complet
                Object obj = ois.readObject();
                if (obj instanceof GestionnaireCIUP) {
                    GestionnaireCIUP donnees = (GestionnaireCIUP) obj;
                    this.etudiants = donnees.etudiants != null ? donnees.etudiants : new ArrayList<>();
                    this.maisonsEtudiants = donnees.maisonsEtudiants != null ? donnees.maisonsEtudiants : new ArrayList<>();
                    this.maisonsInternationales = donnees.maisonsInternationales != null ? donnees.maisonsInternationales : new ArrayList<>();
                    this.restaurations = donnees.restaurations != null ? donnees.restaurations : new ArrayList<>();
                    this.menus = donnees.menus != null ? donnees.menus : new ArrayList<>();
                }
            } catch (Exception e) {
                System.err.println("Erreur lors du chargement: " + e.getMessage());
                // Initialiser avec des listes vides en cas d'erreur
                etudiants = new ArrayList<>();
                maisonsEtudiants = new ArrayList<>();
                maisonsInternationales = new ArrayList<>();
                restaurations = new ArrayList<>();
                menus = new ArrayList<>();
            }
        }
    }
    
    @Override
    public void sauvegarderDonnees() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHIER_SAUVEGARDE))) {
            oos.writeObject(this);
        } catch (Exception e) {
            System.err.println("Erreur lors de la sauvegarde: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Override
    public void notifierObservateurs() {
        sauvegarderDonnees();
        if (controleurPrincipal != null) {
            controleurPrincipal.rafraichirVues();
        }
    }
}