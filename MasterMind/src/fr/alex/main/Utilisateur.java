package fr.alex.main;

public class Utilisateur{
	String choixDuModeDeveloppeur;
	private String choixDuModeDeJeux;
	private int entierChoixDuModeDeveloppeur;
	
	public Utilisateur() {
		
	}
	public int getChoixDuModeDeveloppeur() {
		this.entierChoixDuModeDeveloppeur = Integer.parseInt(this.choixDuModeDeveloppeur);
		return this.entierChoixDuModeDeveloppeur;
	}

	public void setChoixDuModeDeveloppeur(String choixDuModeDeveloppeur) {
		this.choixDuModeDeveloppeur = choixDuModeDeveloppeur;
	}

	public int getChoixDuModeDeJeux() {
		int choixDuModeDeJeux = Integer.parseInt(this.choixDuModeDeJeux);
		return choixDuModeDeJeux;
	}

	public void setChoixDuModeDeJeux(String choixDuModeDeJeux) {
		this.choixDuModeDeJeux = choixDuModeDeJeux;
	}

				
}
