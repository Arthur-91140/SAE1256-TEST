package citeUTest;
import citeU.Cafet;

public class CafetTest {
    private Cafet cafetObject;

    public static void main(String[] args) {
        CafetTest test = new CafetTest();
        
        // Tests du constructeur et des getters
        test.constructeur_NormalInput_ExpectedValues();
        test.getNom_NormalInput_ExpectedReturnValue();
        test.getCapacite_NormalInput_ExpectedReturnValue();
        test.getSesMenus_InitialValue_ReturnsEmptyList();
        test.getSaMaison_InitialValue_ReturnsNull();
        
        // Tests des setters
        test.setNom_NormalInput_ChangesName();
        test.setCapacite_NormalInput_ChangesCapacity();
        
        System.out.println("All tests passed.");
    }

    private void setup(String nom, int capacite) {
        cafetObject = new Cafet(nom, capacite);
    }

    private void constructeur_NormalInput_ExpectedValues() {
        setup("Cafétéria Campus", 100);
        
        assert(cafetObject.getNom().equals("Cafétéria Campus"));
        assert(cafetObject.getCapacite() == 100);
        assert(cafetObject.getSesMenus().size() == 0);
        assert(cafetObject.getSaMaison() == null);
        
        System.out.println("constructeur_NormalInput_ExpectedValues passed.");
    }

    private void getNom_NormalInput_ExpectedReturnValue() {
        setup("Café des Sciences", 50);
        
        assert(cafetObject.getNom().equals("Café des Sciences"));
        
        System.out.println("getNom_NormalInput_ExpectedReturnValue passed.");
    }

    private void getCapacite_NormalInput_ExpectedReturnValue() {
        setup("Cafet IUT", 75);
        
        assert(cafetObject.getCapacite() == 75);
        
        System.out.println("getCapacite_NormalInput_ExpectedReturnValue passed.");
    }

    private void getSesMenus_InitialValue_ReturnsEmptyList() {
        setup("Cafet Centrale SUP", 120);
        
        assert(cafetObject.getSesMenus().size() == 0);
        
        System.out.println("getSesMenus_InitialValue_ReturnsEmptyList passed.");
    }

    private void getSaMaison_InitialValue_ReturnsNull() {
        setup("Cafet Courte Pause", 40);
        
        assert(cafetObject.getSaMaison() == null);
        
        System.out.println("getSaMaison_InitialValue_ReturnsNull passed.");
    }

    private void setNom_NormalInput_ChangesName() {
        setup("Ancien Nom", 100);
        cafetObject.setNom("Nouveau Nom");
        
        assert(cafetObject.getNom().equals("Nouveau Nom"));
        
        System.out.println("setNom_NormalInput_ChangesName passed.");
    }

    private void setCapacite_NormalInput_ChangesCapacity() {
        setup("Cafet Test", 50);
        cafetObject.setCapacite(150);
        
        assert(cafetObject.getCapacite() == 150);
        
        System.out.println("setCapacite_NormalInput_ChangesCapacity passed.");
    }
}