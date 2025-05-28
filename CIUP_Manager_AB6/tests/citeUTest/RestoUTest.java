package citeUTest;
import citeU.RestoU;

public class RestoUTest {
    private RestoU restoUObject;

    public static void main(String[] args) {
        RestoUTest test = new RestoUTest();
        
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
        restoUObject = new RestoU(nom, capacite);
    }

    private void constructeur_NormalInput_ExpectedValues() {
        setup("RestoU Central Sup", 500);
        
        assert(restoUObject.getNom().equals("RestoU Central Sup"));
        assert(restoUObject.getCapacite() == 500);
        assert(restoUObject.getSesMenus().size() == 0);
        assert(restoUObject.getSaMaison() == null);
        
        System.out.println("constructeur_NormalInput_ExpectedValues passed.");
    }

    private void getNom_NormalInput_ExpectedReturnValue() {
        setup("RestoU Avenue Sciences", 400);
        
        assert(restoUObject.getNom().equals("RestoU Avenue Sciences"));
        
        System.out.println("getNom_NormalInput_ExpectedReturnValue passed.");
    }

    private void getCapacite_NormalInput_ExpectedReturnValue() {
        setup("RestoU 1", 300);
        
        assert(restoUObject.getCapacite() == 300);
        
        System.out.println("getCapacite_NormalInput_ExpectedReturnValue passed.");
    }

    private void getSesMenus_InitialValue_ReturnsEmptyList() {
        setup("RestoU Principal", 450);
        
        assert(restoUObject.getSesMenus().size() == 0);
        
        System.out.println("getSesMenus_InitialValue_ReturnsEmptyList passed.");
    }

    private void getSaMaison_InitialValue_ReturnsNull() {
        setup("RestoU Campus Valee", 350);
        
        assert(restoUObject.getSaMaison() == null);
        
        System.out.println("getSaMaison_InitialValue_ReturnsNull passed.");
    }

    private void setNom_NormalInput_ChangesName() {
        setup("Ancien Nom", 400);
        restoUObject.setNom("Nouveau Nom");
        
        assert(restoUObject.getNom().equals("Nouveau Nom"));
        
        System.out.println("setNom_NormalInput_ChangesName passed.");
    }

    private void setCapacite_NormalInput_ChangesCapacity() {
        setup("RestoU Test", 200);
        restoUObject.setCapacite(600);
        
        assert(restoUObject.getCapacite() == 600);
        
        System.out.println("setCapacite_NormalInput_ChangesCapacity passed.");
    }
}