package citeU;

import java.util.ArrayList;

public class Maison {

	protected ArrayList<Restauration> sesRestaurations;
	protected String nom;
	protected String localisation;
	protected String nomDirecteur;

	public Maison(String nom, String localisation, String nomDirecteur) {
		this.nom = nom;
		this.localisation = localisation;
		this.nomDirecteur = nomDirecteur;
		sesRestaurations = new ArrayList<Restauration>();
	}

	public void afficheRestaurations() {
		System.out.println("Liste des restaurations de la maison : " + nom);
		for (Restauration restauration : sesRestaurations) {
			System.out.println("nom : " + restauration.getNom() + ", capacité : " + restauration.getCapacite());
		}
	}

	public ArrayList<Restauration> getSesRestaurations() {
		return sesRestaurations;
	}

	public void setSesRestaurations(ArrayList<Restauration> sesRestaurations) {
		this.sesRestaurations = sesRestaurations;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getLocalisation() {
		return localisation;
	}

	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	public String getNomDirecteur() {
		return nomDirecteur;
	}

	public void setNomDirecteur(String nomDirecteur) {
		this.nomDirecteur = nomDirecteur;
	}

	public void ajouteRestauration(Restauration restauration) {
		sesRestaurations.add(restauration);
		restauration.setSaMaison(this);
	}

	public void retireRestauration(Restauration restauration) {
		sesRestaurations.remove(restauration);
	}

}