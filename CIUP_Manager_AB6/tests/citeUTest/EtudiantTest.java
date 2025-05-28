package citeUTest;

import citeU.Etudiant;
import citeU.MaisonEtudiant;

public class EtudiantTest {
    private Etudiant etudiantObject;

    public static void main(String[] args) {
        EtudiantTest test = new EtudiantTest();
        
        // Tests du constructeur et des getters
        test.constructeur_NormalInput_ExpectedValues();
        test.getNom_NormalInput_ExpectedReturnValue();
        test.getPrenom_NormalInput_ExpectedReturnValue();
        test.getNationalite_NormalInput_ExpectedReturnValue();
        test.getSaMaison_InitialValue_ReturnsNull();
        
        // Tests des setters
        test.setNom_NormalInput_ChangesName();
        test.setPrenom_NormalInput_ChangesFirstName();
        test.setNationalite_NormalInput_ChangesNationality();
        test.setSaMaison_NormalInput_ChangesMaison();
        
        System.out.println("All tests passed.");
    }

    private void setup(String nom, String prenom, String nationalite) {
        etudiantObject = new Etudiant(nom, prenom, nationalite);
    }

    private void constructeur_NormalInput_ExpectedValues() {
        setup("Dupont", "Jean", "Française");
        
        assert(etudiantObject.getNom().equals("Dupont"));
        assert(etudiantObject.getPrenom().equals("Jean"));
        assert(etudiantObject.getNationalite().equals("Française"));
        assert(etudiantObject.getSaMaison() == null);
        
        System.out.println("constructeur_NormalInput_ExpectedValues passed.");
    }

    private void getNom_NormalInput_ExpectedReturnValue() {
        setup("Martin", "Paul", "Française");
        
        assert(etudiantObject.getNom().equals("Martin"));
        
        System.out.println("getNom_NormalInput_ExpectedReturnValue passed.");
    }

    private void getPrenom_NormalInput_ExpectedReturnValue() {
        setup("Bernard", "Marie", "Française");
        
        assert(etudiantObject.getPrenom().equals("Marie"));
        
        System.out.println("getPrenom_NormalInput_ExpectedReturnValue passed.");
    }

    private void getNationalite_NormalInput_ExpectedReturnValue() {
        setup("Smith", "John", "Anglaise");
        
        assert(etudiantObject.getNationalite().equals("Anglaise"));
        
        System.out.println("getNationalite_NormalInput_ExpectedReturnValue passed.");
    }

    private void getSaMaison_InitialValue_ReturnsNull() {
        setup("Petit", "Pierre", "Française");
        
        assert(etudiantObject.getSaMaison() == null);
        
        System.out.println("getSaMaison_InitialValue_ReturnsNull passed.");
    }

    private void setNom_NormalInput_ChangesName() {
        setup("Ancien", "Nom", "Française");
        etudiantObject.setNom("Nouveau");
        
        assert(etudiantObject.getNom().equals("Nouveau"));
        
        System.out.println("setNom_NormalInput_ChangesName passed.");
    }

    private void setPrenom_NormalInput_ChangesFirstName() {
        setup("Nom", "Ancien", "Française");
        etudiantObject.setPrenom("Nouveau");
        
        assert(etudiantObject.getPrenom().equals("Nouveau"));
        
        System.out.println("setPrenom_NormalInput_ChangesFirstName passed.");
    }

    private void setNationalite_NormalInput_ChangesNationality() {
        setup("Nom", "Prenom", "Ancienne");
        etudiantObject.setNationalite("Nouvelle");
        
        assert(etudiantObject.getNationalite().equals("Nouvelle"));
        
        System.out.println("setNationalite_NormalInput_ChangesNationality passed.");
    }

    private void setSaMaison_NormalInput_ChangesMaison() {
        setup("Nom", "Prenom", "Nationalité");
        MaisonEtudiant maison = new MaisonEtudiant("Française", 50, "Maison Test", "Localisation Test", "Directeur Test");
        etudiantObject.setSaMaison(maison);
        
        assert(etudiantObject.getSaMaison() == maison);
        
        System.out.println("setSaMaison_NormalInput_ChangesMaison passed.");
    }
}