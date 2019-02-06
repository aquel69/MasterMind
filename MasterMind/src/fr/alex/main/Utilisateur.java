package fr.alex.main;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Utilisateur extends Joueurs {
	
	private int bienPlace = 0;
	private int malPlace = 0;
	private int inconnuDansLaCombinaison = 0;
	
	public Utilisateur() {
		
	}
	public void comparaisonTableau(String pCombinaison, String pReponse, int pNbDeChiffreCombinaison) {
		this.bienPlace = 0;
		this.malPlace = 0;
		this.inconnuDansLaCombinaison = 0;
		
		boolean enPlace = false;
		boolean autrePion = false;
		List<Integer> liste = new ArrayList<>();
		
		
		for (int i = 0; i < pReponse.length(); i++) {
			if (pReponse.charAt(i) == pCombinaison.charAt(i)) { 
				this.bienPlace++;
				liste.add(i);
			}
		}
		/**for(Integer n : liste)
			System.out.println("liste apres " + n);*/
		
		//boucle permettant d'isoler les pions dans la combinaison saisie par l'uitilisateur
		for (int i = 0; i < pReponse.length(); i++) {
			
			enPlace = false;
			autrePion = false;
			
			//compare les deux combinaisons pour savoir si le pion est à la bonne place
			if (pReponse.charAt(i) == pCombinaison.charAt(i)) { 
				enPlace =true;	
			}
			//boucle permettant d'isoler les pions dans la combinaison mystere
			for(int j = 0; j < pCombinaison.length(); j++) {
				//comparaison des pions entre les deux combinaisons ajoutant les mal placés
				if (!enPlace) {	
					if (pReponse.charAt(i) == pCombinaison.charAt(j)) {
						
						for(Integer n : liste)
							System.out.println("liste " + n);
						for(Integer n : liste) {
							if(pReponse.charAt(i) == pCombinaison.charAt(n)) {
								autrePion = true;
							}
							
						}
						if (autrePion==false) {
							malPlace++;
							liste.add(j);
						break;
						}
					}
				}
			}
			//pions restant qui ne sont donc pas dans la combinaison
			inconnuDansLaCombinaison = ressources.getNB_DE_CHIFFRE_COMBINAISON() - (bienPlace + malPlace);
		}
		for(Integer n : liste)
			   System.out.println("liste : " + n);
		
		
	}
	
	public void affichageDuResultatEtDesIndices(String pReponse, int pBienPlace, int pMalPlace, int pInconnuDansLaCombinaison ) {
		JOptionPane.showMessageDialog(null, pReponse + "\n" + "NB de pions bien placés : " + pBienPlace + "\nNB de pions mal placés : " + pMalPlace + "\nNB de pions pas dans la combinaison : " + pInconnuDansLaCombinaison );
		
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
}
