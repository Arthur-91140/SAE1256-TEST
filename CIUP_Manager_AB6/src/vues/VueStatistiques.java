package vues;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * Vue pour l'affichage des statistiques globales
 * @author Arthur Pruvost
 */
public class VueStatistiques extends JPanel implements InterfaceVue {
    
    //-----------------------------
    // ATTRIBUTS
    //-----------------------------
    private JLabel labelNombreEtudiants;
    private JLabel labelNombreMaisons;
    private JLabel labelNombreRestaurations;
    private JLabel labelPrixMoyenMenus;
    
    private JPanel panneauTauxOccupation;
    private JPanel panneauNationalites;
    
    //-----------------------------
    // CONSTRUCTEUR
    //-----------------------------
    public VueStatistiques() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Statistiques Globales"));
        
        initialiserComposants();
        configurerLayout();
    }
    
    //-----------------------------
    // MÉTHODES D'INITIALISATION
    //-----------------------------
    
    private void initialiserComposants() {
        // Labels pour les statistiques générales
        labelNombreEtudiants = new JLabel("0");
        labelNombreMaisons = new JLabel("0");
        labelNombreRestaurations = new JLabel("0");
        labelPrixMoyenMenus = new JLabel("0.00 €");
        
        // Panneaux pour les graphiques
        panneauTauxOccupation = new JPanel();
        panneauTauxOccupation.setLayout(new BoxLayout(panneauTauxOccupation, BoxLayout.Y_AXIS));
        panneauTauxOccupation.setBorder(BorderFactory.createTitledBorder("Taux d'Occupation des Maisons"));
        
        panneauNationalites = new JPanel();
        panneauNationalites.setLayout(new BoxLayout(panneauNationalites, BoxLayout.Y_AXIS));
        panneauNationalites.setBorder(BorderFactory.createTitledBorder("Répartition par Nationalité"));
    }
    
    private void configurerLayout() {
        // Panneau principal avec trois sections
        JPanel panneauPrincipal = new JPanel(new BorderLayout());
        
        // Section du haut - Statistiques générales
        JPanel panneauStats = creerPanneauStatistiquesGenerales();
        panneauPrincipal.add(panneauStats, BorderLayout.NORTH);
        
        // Section du milieu - Graphiques
        JPanel panneauGraphiques = new JPanel(new GridLayout(1, 2, 10, 10));
        panneauGraphiques.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panneauGraphiques.add(panneauTauxOccupation);
        panneauGraphiques.add(panneauNationalites);
        
        panneauPrincipal.add(panneauGraphiques, BorderLayout.CENTER);
        
        add(panneauPrincipal, BorderLayout.CENTER);
    }
    
    private JPanel creerPanneauStatistiquesGenerales() {
        JPanel panneau = new JPanel(new GridLayout(2, 4, 10, 10));
        panneau.setBorder(BorderFactory.createTitledBorder("Statistiques Générales"));
        panneau.setBackground(new Color(240, 248, 255));
        
        // Première ligne - Labels
        JLabel labelTitreEtudiants = new JLabel("Nombre d'Étudiants:", SwingConstants.CENTER);
        labelTitreEtudiants.setFont(new Font("Arial", Font.BOLD, 12));
        panneau.add(labelTitreEtudiants);
        
        JLabel labelTitreMaisons = new JLabel("Nombre de Maisons:", SwingConstants.CENTER);
        labelTitreMaisons.setFont(new Font("Arial", Font.BOLD, 12));
        panneau.add(labelTitreMaisons);
        
        JLabel labelTitreRestaurations = new JLabel("Nombre de Restaurations:", SwingConstants.CENTER);
        labelTitreRestaurations.setFont(new Font("Arial", Font.BOLD, 12));
        panneau.add(labelTitreRestaurations);
        
        JLabel labelTitrePrix = new JLabel("Prix Moyen des Menus:", SwingConstants.CENTER);
        labelTitrePrix.setFont(new Font("Arial", Font.BOLD, 12));
        panneau.add(labelTitrePrix);
        
        // Deuxième ligne - Valeurs
        labelNombreEtudiants.setHorizontalAlignment(SwingConstants.CENTER);
        labelNombreEtudiants.setFont(new Font("Arial", Font.BOLD, 18));
        labelNombreEtudiants.setForeground(new Color(0, 102, 204));
        panneau.add(labelNombreEtudiants);
        
        labelNombreMaisons.setHorizontalAlignment(SwingConstants.CENTER);
        labelNombreMaisons.setFont(new Font("Arial", Font.BOLD, 18));
        labelNombreMaisons.setForeground(new Color(0, 153, 51));
        panneau.add(labelNombreMaisons);
        
        labelNombreRestaurations.setHorizontalAlignment(SwingConstants.CENTER);
        labelNombreRestaurations.setFont(new Font("Arial", Font.BOLD, 18));
        labelNombreRestaurations.setForeground(new Color(204, 102, 0));
        panneau.add(labelNombreRestaurations);
        
        labelPrixMoyenMenus.setHorizontalAlignment(SwingConstants.CENTER);
        labelPrixMoyenMenus.setFont(new Font("Arial", Font.BOLD, 18));
        labelPrixMoyenMenus.setForeground(new Color(153, 0, 153));
        panneau.add(labelPrixMoyenMenus);
        
        return panneau;
    }
    
    //-----------------------------
    // MÉTHODES PUBLIQUES
    //-----------------------------
    
    /**
     * Met à jour les statistiques générales
     */
    public void mettreAJourStatistiquesGenerales(int nbEtudiants, int nbMaisons, 
                                                 int nbRestaurations, double prixMoyen) {
        labelNombreEtudiants.setText(String.valueOf(nbEtudiants));
        labelNombreMaisons.setText(String.valueOf(nbMaisons));
        labelNombreRestaurations.setText(String.valueOf(nbRestaurations));
        labelPrixMoyenMenus.setText(String.format("%.2f €", prixMoyen));
    }
    
    /**
     * Met à jour l'affichage des taux d'occupation
     */
    public void mettreAJourTauxOccupation(java.util.List<String> nomsMaisons, 
                                         java.util.List<Double> tauxOccupation) {
        panneauTauxOccupation.removeAll();
        
        for (int i = 0; i < nomsMaisons.size() && i < tauxOccupation.size(); i++) {
            JPanel ligneMaison = new JPanel(new BorderLayout());
            ligneMaison.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
            
            // Nom de la maison
            JLabel labelNom = new JLabel(nomsMaisons.get(i));
            labelNom.setPreferredSize(new Dimension(150, 25));
            ligneMaison.add(labelNom, BorderLayout.WEST);
            
            // Barre de progression
            JProgressBar barreProgres = new JProgressBar(0, 100);
            barreProgres.setValue((int) Math.round(tauxOccupation.get(i)));
            barreProgres.setStringPainted(true);
            barreProgres.setString(String.format("%.1f%%", tauxOccupation.get(i)));
            
            // Couleur selon le taux
            if (tauxOccupation.get(i) > 90) {
                barreProgres.setForeground(Color.RED);
            } else if (tauxOccupation.get(i) > 70) {
                barreProgres.setForeground(Color.ORANGE);
            } else {
                barreProgres.setForeground(Color.GREEN);
            }
            
            ligneMaison.add(barreProgres, BorderLayout.CENTER);
            panneauTauxOccupation.add(ligneMaison);
            panneauTauxOccupation.add(Box.createVerticalStrut(5));
        }
        
        panneauTauxOccupation.revalidate();
        panneauTauxOccupation.repaint();
    }
    
    /**
     * Met à jour l'affichage de la répartition par nationalité
     */
    public void mettreAJourRepartitionNationalites(Map<String, Integer> repartition) {
        panneauNationalites.removeAll();
        
        int total = repartition.values().stream().mapToInt(Integer::intValue).sum();
        
        if (total > 0) {
            for (Map.Entry<String, Integer> entry : repartition.entrySet()) {
                JPanel ligneNationalite = new JPanel(new BorderLayout());
                ligneNationalite.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
                
                // Nom de la nationalité
                JLabel labelNationalite = new JLabel(entry.getKey());
                labelNationalite.setPreferredSize(new Dimension(100, 25));
                ligneNationalite.add(labelNationalite, BorderLayout.WEST);
                
                // Barre de progression
                double pourcentage = (double) entry.getValue() / total * 100;
                JProgressBar barreProgres = new JProgressBar(0, 100);
                barreProgres.setValue((int) Math.round(pourcentage));
                barreProgres.setStringPainted(true);
                barreProgres.setString(entry.getValue() + " (" + String.format("%.1f%%", pourcentage) + ")");
                barreProgres.setForeground(new Color(0, 102, 204));
                
                ligneNationalite.add(barreProgres, BorderLayout.CENTER);
                panneauNationalites.add(ligneNationalite);
                panneauNationalites.add(Box.createVerticalStrut(5));
            }
        } else {
            JLabel labelAucune = new JLabel("Aucune donnée disponible", SwingConstants.CENTER);
            labelAucune.setForeground(Color.GRAY);
            panneauNationalites.add(labelAucune);
        }
        
        panneauNationalites.revalidate();
        panneauNationalites.repaint();
    }
    
    /**
     * Affiche un message d'information
     */
    public void afficherMessage(String message) {
        JLabel labelMessage = new JLabel(message, SwingConstants.CENTER);
        labelMessage.setFont(new Font("Arial", Font.ITALIC, 14));
        labelMessage.setForeground(Color.BLUE);
        
        JPanel panneauMessage = new JPanel(new FlowLayout());
        panneauMessage.add(labelMessage);
        
        add(panneauMessage, BorderLayout.SOUTH);
        revalidate();
        
        // Supprimer le message après 3 secondes
        Timer timer = new Timer(3000, e -> {
            remove(panneauMessage);
            revalidate();
            repaint();
        });
        timer.setRepeats(false);
        timer.start();
    }
    
    //-----------------------------
    // IMPLÉMENTATION INTERFACE
    //-----------------------------
    
    @Override
    public void redessiner() {
        repaint();
        revalidate();
    }
}