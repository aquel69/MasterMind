package fr.alex.main;

import javax.swing.JOptionPane;

public class VerificationEtFonctionnement {
	
	private int choixDuModeJeux;
	private String saisieUtilisateur;
	private boolean lettre, empty,nbCouleur;
		
	Joueurs joueurs = new Joueurs();
	
	public VerificationEtFonctionnement() {
		
	}
	
	/**
	 * 
	 * @param pQuestionPose
	 * @param pReponseSiLettre
	 * @param pReponseSiVide
	 *  permet de vérifier si la saisie correspond à la demande(verfication du caractère et si rien n'est rentré)
	 */
	public void saisieUtilisateur(String pQuestionPose,String pReponseSiLettre, String pReponseSiVide) {
			
		do{
			this.saisieUtilisateur = JOptionPane.showInputDialog(pQuestionPose);
			//verifie si lettre ou chiffre
			this.lettre = verifieSiLettre(this.saisieUtilisateur);
			//verifie si il y a rien de rentré
			this.empty = this.saisieUtilisateur.isEmpty();
									
			if (this.lettre) 
				JOptionPane.showMessageDialog(null, pReponseSiLettre);
			else if (this.empty)
				JOptionPane.showMessageDialog(null, pReponseSiVide);
			
		}while( this.empty == true || this.lettre == true );
		
	}
	
	/**
	 * 
	 * @param pQuestionPose
	 * @param pReponseSiLettre
	 * @param pReponseSiVide
	 * @param p4Reponse
	 * permet de vérifier si la saisie correspond à la demande(verfication du caractère, si rien n'est rentré et si les bons pions sont rentrés)
	 */
	public void saisieUtilisateur(String pQuestionPose,String pReponseSiLettre, String pReponseSiVide, String pPasBonneCouleur, String pNbDeChiffreSaisieMauvais) {
		
		
		do{
			this.saisieUtilisateur = JOptionPane.showInputDialog(pQuestionPose);
			//verifie si lettre ou chiffre
			this.lettre = verifieSiLettre(this.saisieUtilisateur);
			//verifie si il y a rien de rentré
			this.empty = this.saisieUtilisateur.isEmpty();
			
			this.nbCouleur = verifieSiSaisieCorrespondAuNombreDeCouleurPropose(saisieUtilisateur);
						
			if (this.lettre) 
				JOptionPane.showMessageDialog(null, pReponseSiLettre);
			else if (this.empty)
				JOptionPane.showMessageDialog(null, pReponseSiVide);
			else if (this.nbCouleur)
				JOptionPane.showMessageDialog(null, pPasBonneCouleur);
			
		}while( this.empty == true || this.lettre == true || this.nbCouleur == true );
		
	}
	
	public String saisieUtilisateurCombinaisonMystere(String pQuestionPose,String pReponseSiLettre, String pReponseSiVide, String pPasBonneCouleur, String pNbDeChiffreSaisieMauvais) {
		String saisie = "";
		
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
	
	protected int getChoixDuModeJeux() {
		this.choixDuModeJeux = Integer.parseInt(getSaisieUtilisateur());
		return choixDuModeJeux;
	}

	protected void setChoixDuModeJeux(int choixDuModeJeux) {
		this.choixDuModeJeux = choixDuModeJeux;
	}
	
	public String getSaisieUtilisateur() {
		return saisieUtilisateur;
	}

	public void setSaisieUtilisateur(String saisieUtilisateur) {
		this.saisieUtilisateur = saisieUtilisateur;
	}
}
