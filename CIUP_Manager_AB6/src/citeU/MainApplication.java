package citeU;

/**
 * Point d'entrée principal de l'application CIUP (Cité Internationale Universitaire de Paris)
 * Cette classe initialise l'interface graphique et lance l'application
 */
public class MainApplication {
    
    /**
     * Méthode principale qui démarre l'application
     * @param args arguments de ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        // Utilisation de SwingUtilities.invokeLater pour assurer que la création de l'interface
        // graphique se fait dans le thread d'événements Swing
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Définir le look and feel du système pour une meilleure intégration visuelle
                    javax.swing.UIManager.setLookAndFeel(
                        javax.swing.UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    System.err.println("Impossible de définir le look and feel: " + e);
                }
                
                // Créer et afficher la fenêtre principale
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
                
                // Facultatif: Initialiser les données de test
                initTestData();
            }
        });
    }
    
    /**
     * Initialise des données de test pour l'application
     * Cette méthode peut être utilisée pour charger des données de démonstration
     */
    private static void initTestData() {
        // Cette méthode pourrait utiliser les méthodes de CIUPFactory
        // pour initialiser des données de test si nécessaire
        // Par exemple:
        // CIUPFactory.creerEtudiants();
        // CIUPFactory.creerEtTesterMaisonEtudiant();
    }
}
