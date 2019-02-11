package fr.alex.main;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;



public class Combinaison {
	
	//private static Logger loggerComb= LogManager.getLogger(Combinaison.class.getName());
	private String combinaison = "";
	private String recapitulatifDeLaPropositionJoueur;
	private int bienPlace = 0;
	private int malPlace = 0;
	private int inconnuDansLaCombinaison = 0;
	
	RessourcesMaster ressources = new RessourcesMaster();
	
	
	private boolean [] chiffreCombinaisonEnPlace = new boolean[ressources.getNB_DE_CHIFFRE_COMBINAISON()];
	private ArrayList <String> recapitulatifDesPrecedentesPropositions = new ArrayList<String>();
		
	
	
	
	public Combinaison() {
		
	}
	
	public void nombreMystere(Random randomChiffreMystere, int pNB_COMBINAISON) {
		
		char chiffre = ' ';
		//try {
				for (int i = 0; i < pNB_COMBINAISON; i++) {
				int pNb = randomChiffreMystere.nextInt(ressources.getNB_DE_COULEUR() - 1 + 1) + 1;
				chiffre = Integer.toString(pNb).charAt(0);	
				System.out.println(chiffre);
				this.combinaison = this.combinaison + chiffre;
			}
			
		//}catch(Exception e)
		//{Logger.error("erreur sur le nombre mystere", e);}
		
	}
	
	public void comparaisonTableau(String pCombinaison, String pReponse) {
		this.bienPlace = 0;
		this.malPlace = 0;
		this.inconnuDansLaCombinaison = 0;
		char [] tableauReponse = pReponse.toCharArray();
		char [] tableauCombinaison = pCombinaison.toCharArray();
		
		//initialisation du tableau complet en false
		for (int i = 0; i < chiffreCombinaisonEnPlace.length; i++) {
			chiffreCombinaisonEnPlace[i] = false;
		}
		
		// verification des pions bien placés
		for (int i = 0; i < pReponse.length(); i++) {
			if (pReponse.charAt(i) == pCombinaison.charAt(i)) { 
				this.bienPlace++;
				chiffreCombinaisonEnPlace[i] = true;
			}
		}
		
		// comparaison des deux chaines convertis en tableau de char pour verifier les pions mal Placés
		for (int i = 0; i < pReponse.length(); i++) {
			for (int j = 0; j < pReponse.length(); j++) {
				if(tableauReponse[i] == tableauCombinaison[j] && chiffreCombinaisonEnPlace[j] == false && tableauReponse[i] != tableauCombinaison[i]) {
					malPlace++;
					chiffreCombinaisonEnPlace[j] = true;
					break;
				}
			}
			recapitulatifDeLaPropositionJoueur = pReponse + "  BP : " + Integer.toString(bienPlace) + " MP : " + Integer.toString(malPlace);
		}
		
		recapitulatifDesPrecedentesPropositions.add(recapitulatifDeLaPropositionJoueur);
	}
	
	/**public void affichageDuResultatEtDesIndices(String pReponse, int pBienPlace, int pMalPlace, int pInconnuDansLaCombinaison ) {
		JOptionPane.showMessageDialog(null, pReponse + "  BP : " + pBienPlace + " MP : " + pMalPlace);
		
	}*/
	
	public void affichageDuResultatEtDesIndices(ArrayList<String> pRecapitulatifDesPrecedentesPropositions) {
		String resultat = "";
		for(String n : pRecapitulatifDesPrecedentesPropositions)
			resultat = resultat + n + "\n";
		
		JOptionPane.showMessageDialog(null, resultat);
		
	}
	
	
	public int getBienPlace() {
		return bienPlace;
	}
	public void setBienPlace(int bienPlace) {
		this.bienPlace = bienPlace;
	}
	public int getMalPlace() {
		return malPlace;
	}
	public void setMalPlace(int malPlace) {
		this.malPlace = malPlace;
	}
	public int getInconnuDansLaCombinaison() {
		return inconnuDansLaCombinaison;
	}
	public void setInconnuDansLaCombinaison(int inconnuDansLaCombinaison) {
		this.inconnuDansLaCombinaison = inconnuDansLaCombinaison;
	}

	public String getCombinaison() {
		return combinaison;
	}

	public void setCombinaison(String combinaison) {
		this.combinaison = combinaison;
	}

	public ArrayList<String> getRecapitulatifPrecedentesPropositions() {
		return recapitulatifDesPrecedentesPropositions;
	}

	public void setRecapitulatifPrecedentesPropositions(ArrayList<String> recapitulatifPrecedentesPropositions) {
		this.recapitulatifDesPrecedentesPropositions = recapitulatifPrecedentesPropositions;
	}	

}
