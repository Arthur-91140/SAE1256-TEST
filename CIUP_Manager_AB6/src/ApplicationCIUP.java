/**
 * Classe principale de l'application CIUP Manager
 * Point d'entrée avec main() suivant le modèle MVC
 * @author Maxence Bailly
 */

import javax.swing.*;
import modeles.*;
import vues.*;
import controleurs.*;

public class ApplicationCIUP {
    
    public static void main(String[] args) {
        // Configuration de l'interface utilisateur
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Création du modèle principal
                GestionnaireCIUP modele = new GestionnaireCIUP();
                
                // Chargement des données initiales via CIUPFactory
                modele.initialiserDonnees();
                
                // Création de la vue principale
                VuePrincipale vue = new VuePrincipale();
                
                // Création du contrôleur principal
                ControleurPrincipal controleur = new ControleurPrincipal(modele, vue);
                
                // Affichage de l'application
                vue.setVisible(true);
            }
        });
    }
}