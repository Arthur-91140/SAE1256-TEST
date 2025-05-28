package citeU;

import java.util.ArrayList;

public class MaisonEtudiant extends Maison {

	private ArrayList<Etudiant> sesEtudiants;
	private String nationalite;
	private int nombreChambres;

	public MaisonEtudiant(String nationalite, int nombreChambres, String nom, String localisation,
			String nomDirecteur) {
		super(nom, localisation, nomDirecteur);
		this.nationalite = nationalite;
		this.nombreChambres = nombreChambres;
		sesEtudiants = new ArrayList<Etudiant>();
	}

	public void afficheListeEtudiants() {
		System.out.println("Liste des etudiants de la maison: " + nom);
		for (Etudiant etudiant : sesEtudiants) {
			System.out.println("Nom: " + etudiant.getNom() + ", Prenom: " + etudiant.getPrenom() + ", Nationalite: "
					+ etudiant.getNationalite());
		}
	}

	public void ajouteEtudiant(Etudiant etudiant) {
		sesEtudiants.add(etudiant);
		etudiant.setSaMaison(this);
	}

	public void retireEtudiant(Etudiant etudiant) {
		sesEtudiants.remove(etudiant);
	}

	public int placesDisponibles() {
		return nombreChambres - sesEtudiants.size();
	}

	public Etudiant rechercherEtudiantParNom(String nom) {
		for (int index = 0; index < sesEtudiants.size(); index++) {
			if (sesEtudiants.get(index).getNom().equals(nom)) {
				return sesEtudiants.get(index);
			}
		}
		throw new RuntimeException("Étudiant non trouvé: " + nom);
	}

	public boolean verifierDisponibilite() {
		return placesDisponibles() > 0;
	}

	public ArrayList<Etudiant> getSesEtudiants() {
		return sesEtudiants;
	}

	public void setSesEtudiants(ArrayList<Etudiant> sesEtudiants) {
		this.sesEtudiants = sesEtudiants;
	}

	public String getNationalite() {
		return nationalite;
	}

	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

	public int getNombreChambres() {
		return nombreChambres;
	}

	public void setNombreChambres(int nombreChambres) {
		this.nombreChambres = nombreChambres;
	}
}