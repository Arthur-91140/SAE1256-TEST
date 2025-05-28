package citeUTest;

import citeU.Etudiant;
import citeU.MaisonEtudiant;
import java.util.ArrayList;

public class MaisonEtudiantTest {
    private MaisonEtudiant maisonObject;

    public static void main(String[] args) {
        MaisonEtudiantTest test = new MaisonEtudiantTest();
        
        // Tests du constructeur et des getters
        test.constructeur_NormalInput_ExpectedValues();
        test.getNationalite_NormalInput_ExpectedReturnValue();
        test.getNombreChambres_NormalInput_ExpectedReturnValue();
        test.getNom_NormalInput_ExpectedReturnValue();
        test.getLocalisation_NormalInput_ExpectedReturnValue();
        test.getNomDirecteur_NormalInput_ExpectedReturnValue();
        test.getSesEtudiants_InitialValue_ReturnsEmptyList();
        
        // Tests des méthodes spécifiques
        test.ajouteEtudiant_NormalInput_AddsEtudiantToList();
        test.retireEtudiant_NormalInput_RemovesEtudiantFromList();
        test.placesDisponibles_NormalInput_ReturnsCorrectNumber();
        test.verifierDisponibilite_WithRoomsAvailable_ReturnsTrue();
        test.verifierDisponibilite_WithNoRoomsAvailable_ReturnsFalse();
        test.rechercherEtudiantParNom_ExistingStudent_ReturnsStudent();
        test.rechercherEtudiantParNom_NonExistingStudent_ThrowsException();
        
        // Tests des setters
        test.setNationalite_NormalInput_ChangesNationality();
        test.setNombreChambres_NormalInput_ChangesRoomNumber();
        test.setSesEtudiants_NormalInput_ChangesStudentList();
        
        System.out.println("All tests passed.");
    }

    private void setup(String nationalite, int nombreChambres, String nom, String localisation, String nomDirecteur) {
        maisonObject = new MaisonEtudiant(nationalite, nombreChambres, nom, localisation, nomDirecteur);
    }

    private void constructeur_NormalInput_ExpectedValues() {
        setup("Française", 50, "Maison France", "Paris", "Jean Dupont");
        
        assert(maisonObject.getNationalite().equals("Française"));
        assert(maisonObject.getNombreChambres() == 50);
        assert(maisonObject.getNom().equals("Maison France"));
        assert(maisonObject.getLocalisation().equals("Paris"));
        assert(maisonObject.getNomDirecteur().equals("Jean Dupont"));
        assert(maisonObject.getSesEtudiants().size() == 0);
        
        System.out.println("constructeur_NormalInput_ExpectedValues passed.");
    }

    private void getNationalite_NormalInput_ExpectedReturnValue() {
        setup("Anglaise", 40, "British House", "London", "John Smith");
        
        assert(maisonObject.getNationalite().equals("Anglaise"));
        
        System.out.println("getNationalite_NormalInput_ExpectedReturnValue passed.");
    }

    private void getNombreChambres_NormalInput_ExpectedReturnValue() {
        setup("Allemande", 60, "German House", "Berlin", "Hans Müller");
        
        assert(maisonObject.getNombreChambres() == 60);
        
        System.out.println("getNombreChambres_NormalInput_ExpectedReturnValue passed.");
    }

    private void getNom_NormalInput_ExpectedReturnValue() {
        setup("Espagnole", 45, "Casa España", "Madrid", "Carlos Rodríguez");
        
        assert(maisonObject.getNom().equals("Casa España"));
        
        System.out.println("getNom_NormalInput_ExpectedReturnValue passed.");
    }

    private void getLocalisation_NormalInput_ExpectedReturnValue() {
        setup("Italienne", 55, "Casa Italia", "Rome", "Mario Rossi");
        
        assert(maisonObject.getLocalisation().equals("Rome"));
        
        System.out.println("getLocalisation_NormalInput_ExpectedReturnValue passed.");
    }

    private void getNomDirecteur_NormalInput_ExpectedReturnValue() {
        setup("Japonaise", 35, "Japan House", "Tokyo", "Takeshi Yamamoto");
        
        assert(maisonObject.getNomDirecteur().equals("Takeshi Yamamoto"));
        
        System.out.println("getNomDirecteur_NormalInput_ExpectedReturnValue passed.");
    }

    private void getSesEtudiants_InitialValue_ReturnsEmptyList() {
        setup("Chinoise", 70, "China House", "Beijing", "Li Wang");
        
        assert(maisonObject.getSesEtudiants().size() == 0);
        
        System.out.println("getSesEtudiants_InitialValue_ReturnsEmptyList passed.");
    }

    private void ajouteEtudiant_NormalInput_AddsEtudiantToList() {
        setup("Française", 50, "Maison France", "Paris", "Jean Dupont");
        Etudiant etudiant = new Etudiant("Martin", "Paul", "Française");
        
        maisonObject.ajouteEtudiant(etudiant);
        
        assert(maisonObject.getSesEtudiants().size() == 1);
        assert(maisonObject.getSesEtudiants().get(0) == etudiant);
        assert(etudiant.getSaMaison() == maisonObject);
        
        System.out.println("ajouteEtudiant_NormalInput_AddsEtudiantToList passed.");
    }

    private void retireEtudiant_NormalInput_RemovesEtudiantFromList() {
        setup("Française", 50, "Maison France", "Paris", "Jean Dupont");
        Etudiant etudiant = new Etudiant("Martin", "Paul", "Française");
        
        maisonObject.ajouteEtudiant(etudiant);
        assert(maisonObject.getSesEtudiants().size() == 1);
        
        maisonObject.retireEtudiant(etudiant);
        assert(maisonObject.getSesEtudiants().size() == 0);
        
        System.out.println("retireEtudiant_NormalInput_RemovesEtudiantFromList passed.");
    }

    private void placesDisponibles_NormalInput_ReturnsCorrectNumber() {
        setup("Française", 50, "Maison France", "Paris", "Jean Dupont");
        
        assert(maisonObject.placesDisponibles() == 50);
        
        Etudiant etudiant1 = new Etudiant("Martin", "Paul", "Française");
        Etudiant etudiant2 = new Etudiant("Dubois", "Marie", "Française");
        
        maisonObject.ajouteEtudiant(etudiant1);
        maisonObject.ajouteEtudiant(etudiant2);
        
        assert(maisonObject.placesDisponibles() == 48);
        
        System.out.println("placesDisponibles_NormalInput_ReturnsCorrectNumber passed.");
    }

    private void verifierDisponibilite_WithRoomsAvailable_ReturnsTrue() {
        setup("Française", 2, "Maison France", "Paris", "Jean Dupont");
        
        Etudiant etudiant = new Etudiant("Martin", "Paul", "Française");
        maisonObject.ajouteEtudiant(etudiant);
        
        assert(maisonObject.verifierDisponibilite() == true);
        
        System.out.println("verifierDisponibilite_WithRoomsAvailable_ReturnsTrue passed.");
    }

    private void verifierDisponibilite_WithNoRoomsAvailable_ReturnsFalse() {
        setup("Française", 1, "Maison France", "Paris", "Jean Dupont");
        
        Etudiant etudiant = new Etudiant("Martin", "Paul", "Française");
        maisonObject.ajouteEtudiant(etudiant);
        
        assert(maisonObject.verifierDisponibilite() == false);
        
        System.out.println("verifierDisponibilite_WithNoRoomsAvailable_ReturnsFalse passed.");
    }

    private void rechercherEtudiantParNom_ExistingStudent_ReturnsStudent() {
        setup("Française", 50, "Maison France", "Paris", "Jean Dupont");
        
        Etudiant etudiant1 = new Etudiant("Martin", "Paul", "Française");
        Etudiant etudiant2 = new Etudiant("Dubois", "Marie", "Française");
        
        maisonObject.ajouteEtudiant(etudiant1);
        maisonObject.ajouteEtudiant(etudiant2);
        
        Etudiant found = maisonObject.rechercherEtudiantParNom("Martin");
        
        assert(found == etudiant1);
        
        System.out.println("rechercherEtudiantParNom_ExistingStudent_ReturnsStudent passed.");
    }

    private void rechercherEtudiantParNom_NonExistingStudent_ThrowsException() {
        setup("Française", 50, "Maison France", "Paris", "Jean Dupont");
        
        boolean exceptionThrown = false;
        
        try {
            maisonObject.rechercherEtudiantParNom("NonExistant");
        } catch (RuntimeException e) {
            exceptionThrown = true;
            assert(e.getMessage().equals("Étudiant non trouvé: NonExistant"));
        }
        
        assert(exceptionThrown);
        
        System.out.println("rechercherEtudiantParNom_NonExistingStudent_ThrowsException passed.");
    }

    private void setNationalite_NormalInput_ChangesNationality() {
        setup("Ancienne", 50, "Maison Test", "Localisation Test", "Directeur Test");
        
        maisonObject.setNationalite("Nouvelle");
        
        assert(maisonObject.getNationalite().equals("Nouvelle"));
        
        System.out.println("setNationalite_NormalInput_ChangesNationality passed.");
    }

    private void setNombreChambres_NormalInput_ChangesRoomNumber() {
        setup("Française", 50, "Maison Test", "Localisation Test", "Directeur Test");
        
        maisonObject.setNombreChambres(100);
        
        assert(maisonObject.getNombreChambres() == 100);
        
        System.out.println("setNombreChambres_NormalInput_ChangesRoomNumber passed.");
    }

    private void setSesEtudiants_NormalInput_ChangesStudentList() {
        setup("Française", 50, "Maison Test", "Localisation Test", "Directeur Test");
        
        ArrayList<Etudiant> nouvelleListeEtudiants = new ArrayList<Etudiant>();
        Etudiant etudiant1 = new Etudiant("Bernard", "Pierre", "Française");
        Etudiant etudiant2 = new Etudiant("Petit", "Sophia", "Française");
        nouvelleListeEtudiants.add(etudiant1);
        nouvelleListeEtudiants.add(etudiant2);
        
        maisonObject.setSesEtudiants(nouvelleListeEtudiants);
        
        assert(maisonObject.getSesEtudiants() == nouvelleListeEtudiants);
        assert(maisonObject.getSesEtudiants().size() == 2);
        assert(maisonObject.getSesEtudiants().contains(etudiant1));
        assert(maisonObject.getSesEtudiants().contains(etudiant2));
        
        System.out.println("setSesEtudiants_NormalInput_ChangesStudentList passed.");
    }
}