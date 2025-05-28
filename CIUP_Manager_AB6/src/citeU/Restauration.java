package citeU;

import java.util.ArrayList;

public class Restauration {

	protected ArrayList<Menu> sesMenus;
	protected Maison saMaison;
	protected String nom;
	protected int capacite;

	public Restauration(String nom, int capacite) {
		this.nom = nom;
		this.capacite = capacite;
		this.sesMenus = new ArrayList<Menu>();
	}

	public void afficheMenus() {
		System.out.println("Liste des menus de la restauration: " + nom);
		for (Menu menu : sesMenus) {
			System.out.println("Nom: " + menu.getNom() + ", Entrée: " + menu.getEntree() + ", Plat: " + menu.getPlat()
					+ ", Dessert: " + menu.getDessert() + ", Prix: " + menu.getPrix());
		}
	}

	public void ajouteMenu(Menu menu) {
		sesMenus.add(menu);
		ArrayList<Restauration> listeRestauration = menu.getSaRestauration();
		listeRestauration.add(this);
		menu.setSaRestauration(listeRestauration);
	}

	public void retireMenu(Menu menu) {
		sesMenus.remove(menu);
	}

	public ArrayList<Menu> getSesMenus() {
		return sesMenus;
	}

	public void setSesMenus(ArrayList<Menu> sesMenus) {
		this.sesMenus = sesMenus;
	}

	public Maison getSaMaison() {
		return saMaison;
	}

	public void setSaMaison(Maison saMaison) {
		this.saMaison = saMaison;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getCapacite() {
		return capacite;
	}

	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}
}