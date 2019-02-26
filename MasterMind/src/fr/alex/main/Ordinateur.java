package fr.alex.main;

import java.util.Random;

public class Ordinateur extends Utilisateur{
	private String propositionOrdinateur;
	
	
	public Ordinateur() {
		
	}
	
	public String propositionDeLOrdinateurEnFonctionDesReponse(Random pChiffreAleatoireCombinaison, int pNbDeChiffreCombinaison) {
		String saisie = "";
		char chiffreChar;
		
		for(int i = 0;i < pNbDeChiffreCombinaison;i++){
			int chiffreReponseEntier = pChiffreAleatoireCombinaison.nextInt(RessourcesMaster.nbDeCouleur - 1 + 1) + 1;
			chiffreChar = Integer.toString(chiffreReponseEntier).charAt(0);
			saisie += chiffreChar;
		}
		
		return saisie;
	}
	
	
	protected String getPropositionOrdinateur() {
		return propositionOrdinateur;
	}

	protected void setPropositionOrdinateur(String propositionOrdinateur) {
		this.propositionOrdinateur = propositionOrdinateur;
	}
	
	
	
}
