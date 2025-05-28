package citeU;

import java.util.*;
import java.util.ArrayList;

public class Menu {

	private ArrayList<Restauration> saRestauration;
	private String nom;
	private String entree;
	private String plat;
	private String dessert;
	private double prix;

	public Menu(String nom, String entree, String plat, String dessert, double prix) {
		this.nom = nom;
		this.entree = entree;
		this.plat = plat;
		this.dessert = dessert;
		this.prix = prix;
		this.saRestauration = new ArrayList<Restauration>();
	}

	public ArrayList<Restauration> disponibiliteRestaurations() {

		ArrayList<Restauration> listeRestaurations = new ArrayList<Restauration>();

		for (int i = 0; i < saRestauration.size(); i++) {
			listeRestaurations.add(saRestauration.get(i));
		}

		return listeRestaurations;
	}

	public ArrayList<Restauration> getSaRestauration() {
		return saRestauration;
	}

	public void setSaRestauration(ArrayList<Restauration> saRestauration) {
		this.saRestauration = saRestauration;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEntree() {
		return entree;
	}

	public void setEntree(String entree) {
		this.entree = entree;
	}

	public String getPlat() {
		return plat;
	}

	public void setPlat(String plat) {
		this.plat = plat;
	}

	public String getDessert() {
		return dessert;
	}

	public void setDessert(String dessert) {
		this.dessert = dessert;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}
}