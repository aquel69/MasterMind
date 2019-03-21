package fr.alex.main;

import java.util.Random;

public class Ordinateur extends Utilisateur{
	private String propositionOrdinateur;
	
	Combinaison combinaison = new Combinaison();
	
	public Ordinateur() {
		
	}
	
	public String propositionDeLOrdinateurEnFonctionDesReponse(Random pChiffreAleatoireCombinaison, int pNbDeChiffreCombinaison, int pNbDeTourJoue) {
		String saisie = "";
		int saisieEntier = 0;
		char chiffreChar;
		int saisiePrecedente = 0;
		
		
		for(int i = 0;i < pNbDeChiffreCombinaison;i++){
			if (combinaison.getSauvegardeDesPionsDejaJoues().isEmpty()) {
				saisieEntier = pNbDeTourJoue + 1;
				saisie += Integer.toString(saisieEntier);
				saisiePrecedente = saisieEntier;
				}
			else if (!combinaison.getSauvegardeDesPionsDejaJoues().isEmpty()){
				if(i == 0) {
					saisieEntier = saisiePrecedente;
					saisie += Integer.toString(saisieEntier);
				}
				
				
				//chiffreChar = combinaison.getSauvegardeDesPionsDejaJoues().get(0);
				System.out.println("passer");
			}
				/**if(pNbDeTourJoue == 0) {
				saisie += "1";
			}*/
			//else if(pNbDeTourJoue == 1 )
			/**if(combinaison.getSauvegardeDesPionsDejaJoues().contains())
			int chiffreReponseEntier = pChiffreAleatoireCombinaison.nextInt(RessourcesMaster.nbDeCouleur - 1 + 1) + 1;
			chiffreChar = Integer.toString(chiffreReponseEntier).charAt(0);
			saisie += chiffreChar;
			}*/
		}
		
		return saisie;
	}
	
	public String propositionDeLOrdinateurEnFonctionDesReponsesPrecedentes(Random pChiffreAleatoireCombinaison, int pNbDeChiffreCombinaison) {
		String saisie = "";
		char chiffreChar;
		for(int i = 0; i<pNbDeChiffreCombinaison;i++) {
			
		}
		
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
