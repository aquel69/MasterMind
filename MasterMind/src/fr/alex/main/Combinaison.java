package fr.alex.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;



public class Combinaison {
	
	//private static Logger loggerComb= LogManager.getLogger(Combinaison.class.getName());
		
	private String combinaison = "";
	private String recapitulatifDeLaPropositionJoueur = "";
	private String recapitulatifDeLaPropositionOrdi = "";
	private int bienPlace = 0;
	private int malPlace = 0;
	private int scoreMax = 0;
		
	private boolean [] chiffreCombinaisonEnPlace = new boolean[RessourcesMaster.nbDeChiffreCombinaison];
	private boolean [] chiffreCombinaisonMystereTrouve = new boolean[RessourcesMaster.nbDeChiffreCombinaison];
	private List <String> recapitulatifDesPrecedentesPropositionsJoueurs = new ArrayList<String>();
	private List <String> recapitulatifDesPrecedentesPropositionsOrdi = new ArrayList<String>();
	private List<Character> sauvegardeDesPionsDejaJoues = new ArrayList<Character>();
	private List <Character> sauvegardeDesPionsPasDansLaCombinaison = new ArrayList<Character>();
	
	
	public Combinaison() {
		
	}
	
	public void generationDuNombreMystere(Random randomChiffreMystere, int pNB_COMBINAISON, int pChoixDuModeDeveloppeur) {
		
		char chiffreChar = ' ';
		for (int i = 0; i < pNB_COMBINAISON; i++) {
			int chiffreMystereEntier = randomChiffreMystere.nextInt(RessourcesMaster.nbDeCouleur - 1 + 1) + 1;
			chiffreChar = Integer.toString(chiffreMystereEntier).charAt(0);	
			System.out.println(chiffreChar);
			this.combinaison = this.combinaison + chiffreChar;
		}
		
		if(pChoixDuModeDeveloppeur == 1) {
			JOptionPane.showMessageDialog(null, "la combinaison mystère est " + this.combinaison);
		}				
				
	}
	
	public void comparaisonTableauCombinaisonJoueur(String pCombinaison, String pReponse) {
		this.bienPlace = 0;
		this.malPlace = 0;
					
		char [] tableauReponse = pReponse.toCharArray();
		char [] tableauCombinaison = pCombinaison.toCharArray();
		
		//initialisation du tableau boolean complet en false
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
		recapitulatifDesPrecedentesPropositionsJoueurs.add(recapitulatifDeLaPropositionJoueur);
	}
	
	public void comparaisonTableauCombinaisonOrdi(String pCombinaison, String pReponse) {
		this.bienPlace = 0;
		this.malPlace = 0;
		
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
			for (int j = 0; j < pCombinaison.length(); j++) {
				if(tableauReponse[i] == tableauCombinaison[j] && chiffreCombinaisonEnPlace[j] == false && tableauReponse[i] != tableauCombinaison[i]) {
					malPlace++;
					chiffreCombinaisonEnPlace[j] = true;
					break;
				}
			}
			recapitulatifDeLaPropositionOrdi = pReponse + "  BP : " + Integer.toString(bienPlace) + " MP : " + Integer.toString(malPlace);
		}
		recapitulatifDesPrecedentesPropositionsOrdi.add(recapitulatifDeLaPropositionOrdi);
	}
	
	public void comparaisonTableauCombinaisonOrdiMethodeKnuth(String pCombinaison, String pReponse) {
		this.bienPlace = 0;
		this.malPlace = 0;
		setScoreMax(0);
		
		char [] tableauReponse = pReponse.toCharArray();
		char [] tableauCombinaison = pCombinaison.toCharArray();
		
		//initialisation du tableau complet en false
		for (int i = 0; i < chiffreCombinaisonEnPlace.length; i++) {
			chiffreCombinaisonEnPlace[i] = false;
			
		}
		
		// verification des pions bien placés
		for (int i = 0; i < pReponse.length(); i++) {
			if (pReponse.charAt(i) == pCombinaison.charAt(i)) { 
				//setScoreMax(getScoreMax() + 10);
				chiffreCombinaisonEnPlace[i] = true;
				
				this.bienPlace++;
			}
			if(chiffreCombinaisonMystereTrouve[i]==false ){ //&& comparaisonDesPionsDejaJouesAvecListe(tableauReponse, sauvegardeDesPionsDejaJoues)) {
				chiffreCombinaisonMystereTrouve[i] = true;
				sauvegardeDesPionsDejaJoues.add(pCombinaison.charAt(i));
			}
		}
		
		// comparaison des deux chaines convertis en tableau de char pour verifier les pions mal Placés
		for (int i = 0; i < pReponse.length(); i++) {
			for (int j = 0; j < pCombinaison.length(); j++) {
				if(tableauReponse[i] == tableauCombinaison[j] && chiffreCombinaisonEnPlace[j] == false && tableauReponse[i] != tableauCombinaison[i]) {
					//setScoreMax(getScoreMax() + 1);
					chiffreCombinaisonEnPlace[j] = true;
					malPlace++;
					if(chiffreCombinaisonMystereTrouve[j]==false) {
						chiffreCombinaisonMystereTrouve[j] = true;
						sauvegardeDesPionsDejaJoues.add(pCombinaison.charAt(i));
					}		
					break;
				}
				
			}
			recapitulatifDeLaPropositionOrdi = pReponse + "  BP : " + Integer.toString(bienPlace) + " MP : " + Integer.toString(malPlace);
		}
		recapitulatifDesPrecedentesPropositionsOrdi.add(recapitulatifDeLaPropositionOrdi);
	}
	
	
	public boolean comparaisonDesPionsDejaJouesAvecListe(char[] pTableauReponse, ArrayList<Character> pListeDesPionsDejaJoues) {
		boolean pionIdentique = false;
		
		for(int i=0; i<pTableauReponse.length;i++) {
			for(int j=0; i<pListeDesPionsDejaJoues.size();j++) {
				if(pTableauReponse[i]==pListeDesPionsDejaJoues.get(j)) {
					pionIdentique = true;
				}
			}
		}
		
		return pionIdentique;
	}
	
	/**public void affichageDuResultatEtDesIndices(String pReponse, int pBienPlace, int pMalPlace, int pInconnuDansLaCombinaison ) {
		JOptionPane.showMessageDialog(null, pReponse + "  BP : " + pBienPlace + " MP : " + pMalPlace);
		
	}*/
	
	public void affichageDuResultatEtDesIndices(List<String> list) {
		String resultat = "";
		for(String n : list)
			resultat += n + "\n";
		
		JOptionPane.showMessageDialog(null, resultat);
		
	}
	
	
	public String getCombinaison() {
		return combinaison;
	}

	public void setCombinaison(String combinaison) {
		this.combinaison = combinaison;
	}

	public List<String> getRecapitulatifPrecedentesPropositionsJoueurs() {
		return recapitulatifDesPrecedentesPropositionsJoueurs;
	}

	public void setRecapitulatifPrecedentesPropositionsJoueurs(ArrayList<String> recapitulatifPrecedentesPropositionsJoueurs) {
		this.recapitulatifDesPrecedentesPropositionsJoueurs = recapitulatifPrecedentesPropositionsJoueurs;
	}	
	
	public List<String> getRecapitulatifPrecedentesPropositionsOrdi() {
		return recapitulatifDesPrecedentesPropositionsOrdi;
	}

	public void setRecapitulatifPrecedentesPropositionsOrdi(ArrayList<String> recapitulatifPrecedentesPropositionsOrdi) {
		this.recapitulatifDesPrecedentesPropositionsOrdi = recapitulatifPrecedentesPropositionsOrdi;
	}

	public String getRecapitulatifDeLaPropositionJoueur() {
		return recapitulatifDeLaPropositionJoueur;
	}

	public void setRecapitulatifDeLaPropositionJoueur(String recapitulatifDeLaPropositionJoueur) {
		this.recapitulatifDeLaPropositionJoueur = recapitulatifDeLaPropositionJoueur;
	}

	public String getRecapitulatifDeLaPropositionOrdi() {
		return recapitulatifDeLaPropositionOrdi;
	}

	public void setRecapitulatifDeLaPropositionOrdi(String recapitulatifDeLaPropositionOrdi) {
		this.recapitulatifDeLaPropositionOrdi = recapitulatifDeLaPropositionOrdi;
	}

	public int getScoreMax() {
		return scoreMax;
	}

	public void setScoreMax(int scoreMax) {
		this.scoreMax = scoreMax;
	}	

	public List<Character> getSauvegardeDesPionsDejaJoues() {
		return sauvegardeDesPionsDejaJoues;
	}

	public void setSauvegardeDesPionsDejaJoues(List<Character> sauvegardeDesPionsDejaJoues) {
		this.sauvegardeDesPionsDejaJoues = sauvegardeDesPionsDejaJoues;
	}
	
	public List<Character> getSauvegardeDesPionsPasDansLaCombinaison() {
		return sauvegardeDesPionsPasDansLaCombinaison;
	}

	public void setSauvegardeDesPionsPasDansLaCombinaison(List<Character> sauvegardeDesPionsPasDansLaCombinaison) {
		this.sauvegardeDesPionsPasDansLaCombinaison = sauvegardeDesPionsPasDansLaCombinaison;
	}
}
