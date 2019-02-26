package fr.alex.main;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VerificationEtFonctionnement {
	
	private String saisieUtilisateur;
	private boolean lettre, empty,nbCouleur;
		
	Joueurs joueurs = new Joueurs();
	private static Logger loggerVerif= LogManager.getLogger(Combinaison.class.getName());
	
	public VerificationEtFonctionnement() {
		
	}
	
	/**
	 * 
	 * @param pQuestionPose
	 * @param pReponseSiLettre
	 * @param pReponseSiVide
	 *  permet de vérifier si la saisie correspond à la demande(verfication du caractère et si rien n'est rentré)
	 */
	public String saisieUtilisateur(String pQuestionPose,String pReponseSiLettre, String pReponseSiVide) {
		String saisie = null;
		
		try {
			do{
				saisie = JOptionPane.showInputDialog(pQuestionPose);
				//verifie si lettre ou chiffre
				this.lettre = verifieSiLettre(saisie);
				//verifie si il y a rien de rentré
				this.empty = saisie.isEmpty();
										
				if (this.lettre) 
					JOptionPane.showMessageDialog(null, pReponseSiLettre);
				else if (this.empty)
					JOptionPane.showMessageDialog(null, pReponseSiVide);
				
			}while( this.empty == true || this.lettre == true );
		}catch(Exception e) {
			loggerVerif.error("erreur dans la saisie utilisateur 3 parametres", e);
		}
		
		return saisie;
			
	}
	
	/**
	 * 
	 * @param pQuestionPose
	 * @param pReponseSiLettre
	 * @param pReponseSiVide
	 * @param p4Reponse
	 * permet de vérifier si la saisie correspond à la demande(verfication du caractère, si rien n'est rentré et si les bons pions sont rentrés)
	 */
	public String saisieUtilisateur(String pQuestionPose,String pReponseSiLettre, String pReponseSiVide, String pPasBonneCouleur, String pNbDeChiffreSaisieMauvais) {
		String saisie = null;
		
		try {
			do{
				saisie = JOptionPane.showInputDialog(pQuestionPose);
				//verifie si lettre ou chiffre
				this.lettre = verifieSiLettre(saisie);
				//verifie si il y a rien de rentré
				this.empty = saisie.isEmpty();
				
				this.nbCouleur = verifieSiSaisieCorrespondAuNombreDeCouleurPropose(saisie);
							
				if (this.lettre) 
					JOptionPane.showMessageDialog(null, pReponseSiLettre);
				else if (this.empty)
					JOptionPane.showMessageDialog(null, pReponseSiVide);
				else if (this.nbCouleur)
					JOptionPane.showMessageDialog(null, pPasBonneCouleur);
				
			}while( this.empty == true || this.lettre == true || this.nbCouleur == true );
			}catch(Exception e) {
				loggerVerif.error("erreur dans la saisie utilisateur 5 parametres", e);
			}
		return saisie;
	}
	
	/**
	 * 
	 * @param pSaisie
	 * @return un boolen True est retourné, si une lettre est rentrée par l'utilisateur
	 */
	public boolean verifieSiLettre(String pSaisie) {
		int test = 0;
		boolean verification = false;
		for(int i = 0; i < pSaisie.length(); i++) {
			test = (int) pSaisie.charAt(i);
			if (test < 48 || test > 57)
				verification = true;
							
		}
		return verification;
	}
	
	/**
	 * 
	 * @param pReponse
	 * @return
	 * un booleen true est retourné, si le nombre de pions saisie ne correspond pas à celui demandé
	 */
	public boolean verifieSiSaisieCorrespondAuNombreDeCouleurPropose(String pSaisie) {
		int j;
		char c = ' ';
		String s ="";
		boolean verification = false;
		
		if(pSaisie.length() == RessourcesMaster.nbDeChiffreCombinaison) {	
			for(int i = 0; i < RessourcesMaster.nbDeChiffreCombinaison; i++) {
				c = pSaisie.charAt(i);
				s = Character.toString(c);
				j = Integer.parseInt(s);
				
				if (j < 1 || j > RessourcesMaster.nbDeCouleur) {
					verification = true;
				}
			}
		}else 
			verification = true;
		
		return verification;
	}
	
		
	public String getSaisieUtilisateur() {
		return saisieUtilisateur;
	}

	public void setSaisieUtilisateur(String saisieUtilisateur) {
		this.saisieUtilisateur = saisieUtilisateur;
	}
}
