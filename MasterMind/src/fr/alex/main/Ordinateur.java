package fr.alex.main;

import java.util.Random;

public class Ordinateur extends Joueurs{
	protected String propositionOrdinateur;
	
	
	
	public Ordinateur() {
		
	}
	
	
	
	public String propositonDeLOrdinateurEnFonctionDesReponse(Random pChiffreCombinaison) {
		String saisie = "";
		char chiffreChar;
		
		if(CorpsPrincipal.nbDeCoupsJoues == 0) {
			int chiffreReponseEntier = pChiffreCombinaison.nextInt(RessourcesMaster.getNbDeCouleur() - 1 + 1) + 1;
			chiffreChar = Integer.toString(chiffreReponseEntier).charAt(0);
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
