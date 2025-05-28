package citeUTest;

import citeU.Menu;
import citeU.Restauration;
import java.util.ArrayList;

public class MenuTest {
    private Menu menuObject;

    public static void main(String[] args) {
        MenuTest test = new MenuTest();
        
        // Tests du constructeur et des getters
        test.constructeur_NormalInput_ExpectedValues();
        test.getNom_NormalInput_ExpectedReturnValue();
        test.getEntree_NormalInput_ExpectedReturnValue();
        test.getPlat_NormalInput_ExpectedReturnValue();
        test.getDessert_NormalInput_ExpectedReturnValue();
        test.getPrix_NormalInput_ExpectedReturnValue();
        test.getSaRestauration_InitialValue_ReturnsEmptyList();
        
        // Tests des méthodes spécifiques
        test.disponibiliteRestaurations_WithRestaurations_ReturnsList();
        test.disponibiliteRestaurations_WithoutRestaurations_ReturnsEmptyList();
        
        // Tests des setters
        test.setNom_NormalInput_ChangesName();
        test.setEntree_NormalInput_ChangesEntree();
        test.setPlat_NormalInput_ChangesPlat();
        test.setDessert_NormalInput_ChangesDessert();
        test.setPrix_NormalInput_ChangesPrix();
        test.setSaRestauration_NormalInput_ChangesRestaurations();
        
        System.out.println("All tests passed.");
    }

    private void setup(String nom, String entree, String plat, String dessert, double prix) {
        menuObject = new Menu(nom, entree, plat, dessert, prix);
    }
    
    private void constructeur_NormalInput_ExpectedValues() {
        setup("Menu Test", "Entrée Test", "Plat Test", "Dessert Test", 12.5);
        
        assert(menuObject.getNom().equals("Menu Test"));
        assert(menuObject.getEntree().equals("Entrée Test"));
        assert(menuObject.getPlat().equals("Plat Test"));
        assert(menuObject.getDessert().equals("Dessert Test"));
        assert(menuObject.getPrix() == 12.5);
        assert(menuObject.getSaRestauration().size() == 0);
        
        System.out.println("constructeur_NormalInput_ExpectedValues passed.");
    }

    private void getNom_NormalInput_ExpectedReturnValue() {
        setup("Menu Nom", "Entrée", "Plat", "Dessert", 10.0);
        
        assert(menuObject.getNom().equals("Menu Nom"));
        
        System.out.println("getNom_NormalInput_ExpectedReturnValue passed.");
    }

    private void getEntree_NormalInput_ExpectedReturnValue() {
        setup("Menu", "Entrée Test", "Plat", "Dessert", 10.0);
        
        assert(menuObject.getEntree().equals("Entrée Test"));
        
        System.out.println("getEntree_NormalInput_ExpectedReturnValue passed.");
    }

    private void getPlat_NormalInput_ExpectedReturnValue() {
        setup("Menu", "Entrée", "Plat Test", "Dessert", 10.0);
        
        assert(menuObject.getPlat().equals("Plat Test"));
        
        System.out.println("getPlat_NormalInput_ExpectedReturnValue passed.");
    }

    private void getDessert_NormalInput_ExpectedReturnValue() {
        setup("Menu", "Entrée", "Plat", "Dessert Test", 10.0);
        
        assert(menuObject.getDessert().equals("Dessert Test"));
        
        System.out.println("getDessert_NormalInput_ExpectedReturnValue passed.");
    }

    private void getPrix_NormalInput_ExpectedReturnValue() {
        setup("Menu", "Entrée", "Plat", "Dessert", 15.5);
        
        assert(menuObject.getPrix() == 15.5);
        
        System.out.println("getPrix_NormalInput_ExpectedReturnValue passed.");
    }

    private void getSaRestauration_InitialValue_ReturnsEmptyList() {
        setup("Menu", "Entrée", "Plat", "Dessert", 10.0);
        
        assert(menuObject.getSaRestauration().size() == 0);
        
        System.out.println("getSaRestauration_InitialValue_ReturnsEmptyList passed.");
    }

    private void disponibiliteRestaurations_WithRestaurations_ReturnsList() {
        setup("Menu Test", "Entrée Test", "Plat Test", "Dessert Test", 12.5);
        
        ArrayList<Restauration> listeRestaurations = new ArrayList<Restauration>();
        Restauration resto1 = new Restauration("Resto 1", 100);
        Restauration resto2 = new Restauration("Resto 2", 200);
        listeRestaurations.add(resto1);
        listeRestaurations.add(resto2);
        
        menuObject.setSaRestauration(listeRestaurations);
        
        ArrayList<Restauration> result = menuObject.disponibiliteRestaurations();
        
        assert(result.size() == 2);
        assert(result.contains(resto1));
        assert(result.contains(resto2));
        
        System.out.println("disponibiliteRestaurations_WithRestaurations_ReturnsList passed.");
    }

    private void disponibiliteRestaurations_WithoutRestaurations_ReturnsEmptyList() {
        setup("Menu Test", "Entrée Test", "Plat Test", "Dessert Test", 12.5);
        
        ArrayList<Restauration> result = menuObject.disponibiliteRestaurations();
        
        assert(result.size() == 0);
        
        System.out.println("disponibiliteRestaurations_WithoutRestaurations_ReturnsEmptyList passed.");
    }

    private void setNom_NormalInput_ChangesName() {
        setup("Ancien Nom", "Entrée", "Plat", "Dessert", 10.0);
        menuObject.setNom("Nouveau Nom");
        
        assert(menuObject.getNom().equals("Nouveau Nom"));
        
        System.out.println("setNom_NormalInput_ChangesName passed.");
    }

    private void setEntree_NormalInput_ChangesEntree() {
        setup("Menu", "Ancienne Entrée", "Plat", "Dessert", 10.0);
        menuObject.setEntree("Nouvelle Entrée");
        
        assert(menuObject.getEntree().equals("Nouvelle Entrée"));
        
        System.out.println("setEntree_NormalInput_ChangesEntree passed.");
    }

    private void setPlat_NormalInput_ChangesPlat() {
        setup("Menu", "Entrée", "Ancien Plat", "Dessert", 10.0);
        menuObject.setPlat("Nouveau Plat");
        
        assert(menuObject.getPlat().equals("Nouveau Plat"));
        
        System.out.println("setPlat_NormalInput_ChangesPlat passed.");
    }

    private void setDessert_NormalInput_ChangesDessert() {
        setup("Menu", "Entrée", "Plat", "Ancien Dessert", 10.0);
        menuObject.setDessert("Nouveau Dessert");
        
        assert(menuObject.getDessert().equals("Nouveau Dessert"));
        
        System.out.println("setDessert_NormalInput_ChangesDessert passed.");
    }

    private void setPrix_NormalInput_ChangesPrix() {
        setup("Menu", "Entrée", "Plat", "Dessert", 10.0);
        menuObject.setPrix(20.0);
        
        assert(menuObject.getPrix() == 20.0);
        
        System.out.println("setPrix_NormalInput_ChangesPrix passed.");
    }

    private void setSaRestauration_NormalInput_ChangesRestaurations() {
        setup("Menu Test", "Entrée Test", "Plat Test", "Dessert Test", 12.5);
        
        ArrayList<Restauration> nouvelleListeRestaurations = new ArrayList<Restauration>();
        Restauration resto1 = new Restauration("Resto 1", 100);
        Restauration resto2 = new Restauration("Resto 2", 200);
        nouvelleListeRestaurations.add(resto1);
        nouvelleListeRestaurations.add(resto2);
        
        menuObject.setSaRestauration(nouvelleListeRestaurations);
        
        assert(menuObject.getSaRestauration() == nouvelleListeRestaurations);
        assert(menuObject.getSaRestauration().size() == 2);
        assert(menuObject.getSaRestauration().contains(resto1));
        assert(menuObject.getSaRestauration().contains(resto2));
        
        System.out.println("setSaRestauration_NormalInput_ChangesRestaurations passed.");
    }
}