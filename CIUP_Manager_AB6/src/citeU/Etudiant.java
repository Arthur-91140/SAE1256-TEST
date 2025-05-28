package citeU;

public class Etudiant {

	private MaisonEtudiant saMaison;
	private String nom;
	private String prenom;
	private String nationalite;

	public Etudiant(String nom, String prenom, String nationalite) {
		this.nom = nom;
		this.prenom = prenom;
		this.nationalite = nationalite;
	}

	public void afficheMaison() {
		System.out.println(saMaison);
	}

	public MaisonEtudiant getSaMaison() {
		return saMaison;
	}

	public void setSaMaison(MaisonEtudiant saMaison) {
		this.saMaison = saMaison;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNationalite() {
		return nationalite;
	}

	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

}