package fr.alex.main;

public class Utilisateur{
	private String choixDuModeDeveloppeur;
	
	private String choixDuModeDeJeux;
	private int entierChoixDuModeDeveloppeur;
	private String choixDuJeu;
	private int entierChoixDuJeu;
	
	
	public Utilisateur() {
		
	}
	public int getChoixDuModeDeveloppeur() {
		this.entierChoixDuModeDeveloppeur = Integer.parseInt(this.choixDuModeDeveloppeur);
		return this.entierChoixDuModeDeveloppeur;
	}

	public void setChoixDuModeDeveloppeur(String choixDuModeDeveloppeur) {
		this.choixDuModeDeveloppeur = choixDuModeDeveloppeur;
	}
	
	public int getChoixDuJeu() {
		this.entierChoixDuJeu = Integer.parseInt(this.choixDuJeu);
		return this.entierChoixDuJeu;
	}

	public void setChoixDuJeu(String choixDuJeu) {
		this.choixDuJeu = choixDuJeu;
	}


	public int getChoixDuModeDeJeux() {
		int choixDuModeDeJeux = Integer.parseInt(this.choixDuModeDeJeux);
		return choixDuModeDeJeux;
	}

	public void setChoixDuModeDeJeux(String choixDuModeDeJeux) {
		this.choixDuModeDeJeux = choixDuModeDeJeux;
	}

				
}
