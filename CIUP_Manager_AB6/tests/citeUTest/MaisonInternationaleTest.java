package citeUTest;

import citeU.MaisonInternationale;
import citeU.Restauration;

public class MaisonInternationaleTest {
    private MaisonInternationale maisonObject;

    public static void main(String[] args) {
        MaisonInternationaleTest test = new MaisonInternationaleTest();
        
        // Tests du constructeur et des getters
        test.constructeur_NormalInput_ExpectedValues();
        test.getNom_NormalInput_ExpectedReturnValue();
        test.getLocalisation_NormalInput_ExpectedReturnValue();
        test.getNomDirecteur_NormalInput_ExpectedReturnValue();
        test.getSesRestaurations_InitialValue_ReturnsEmptyList();
        
        // Tests des méthodes spécifiques
        test.ajouteRestauration_NormalInput_AddsRestaurationToList();
        test.retireRestauration_NormalInput_RemovesRestaurationFromList();
        
        System.out.println("All tests passed.");
    }

    private void setup(String nom, String localisation, String nomDirecteur) {
        maisonObject = new MaisonInternationale(nom, localisation, nomDirecteur);
    }

    private void constructeur_NormalInput_ExpectedValues() {
        setup("Maison Internationale Test", "Localisation Test", "Directeur Test");
        
        assert(maisonObject.getNom().equals("Maison Internationale Test"));
        assert(maisonObject.getLocalisation().equals("Localisation Test"));
        assert(maisonObject.getNomDirecteur().equals("Directeur Test"));
        assert(maisonObject.getSesRestaurations().size() == 0);
        
        System.out.println("constructeur_NormalInput_ExpectedValues passed.");
    }

    private void getNom_NormalInput_ExpectedReturnValue() {
        setup("Maison Internationale 1", "Localisation 1", "Directeur 1");
        
        assert(maisonObject.getNom().equals("Maison Internationale 1"));
        
        System.out.println("getNom_NormalInput_ExpectedReturnValue passed.");
    }

    private void getLocalisation_NormalInput_ExpectedReturnValue() {
        setup("Maison Internationale 2", "Localisation 2", "Directeur 2");
        
        assert(maisonObject.getLocalisation().equals("Localisation 2"));
        
        System.out.println("getLocalisation_NormalInput_ExpectedReturnValue passed.");
    }

    private void getNomDirecteur_NormalInput_ExpectedReturnValue() {
        setup("Maison Internationale 3", "Localisation 3", "Directeur 3");
        
        assert(maisonObject.getNomDirecteur().equals("Directeur 3"));
        
        System.out.println("getNomDirecteur_NormalInput_ExpectedReturnValue passed.");
    }

    private void getSesRestaurations_InitialValue_ReturnsEmptyList() {
        setup("Maison Internationale", "Localisation", "Directeur");
        
        assert(maisonObject.getSesRestaurations().size() == 0);
        
        System.out.println("getSesRestaurations_InitialValue_ReturnsEmptyList passed.");
    }

    private void ajouteRestauration_NormalInput_AddsRestaurationToList() {
        setup("Maison Internationale Test", "Localisation Test", "Directeur Test");
        Restauration restauration = new Restauration("Resto Test", 100);
        
        maisonObject.ajouteRestauration(restauration);
        
        assert(maisonObject.getSesRestaurations().size() == 1);
        assert(maisonObject.getSesRestaurations().get(0) == restauration);
        assert(restauration.getSaMaison() == maisonObject);
        
        System.out.println("ajouteRestauration_NormalInput_AddsRestaurationToList passed.");
    }

    private void retireRestauration_NormalInput_RemovesRestaurationFromList() {
        setup("Maison Internationale Test", "Localisation Test", "Directeur Test");
        Restauration restauration = new Restauration("Resto Test", 100);
        
        maisonObject.ajouteRestauration(restauration);
        assert(maisonObject.getSesRestaurations().size() == 1);
        
        maisonObject.retireRestauration(restauration);
        assert(maisonObject.getSesRestaurations().size() == 0);
        
        System.out.println("retireRestauration_NormalInput_RemovesRestaurationFromList passed.");
    }
}