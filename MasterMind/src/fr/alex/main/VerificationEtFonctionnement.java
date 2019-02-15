package fr.alex.main;

import javax.swing.JOptionPane;

public class VerificationEtFonctionnement {
	
	private int choixDuModeJeux;
	private String verificationSaisie;
	private boolean lettre, empty,nbCouleur;
		
	Joueurs joueurs = new Joueurs();
	
	public VerificationEtFonctionnement() {
		
	}
	
	/**
	 * 
	 * @param pQuestionPose
	 * @param pReponseSiLettre
	 * @param pReponseSiVide
	 *  permet de v�rifier si la saisie correspond � la demande(verfication du caract�re et si rien n'est rentr�)
	 */
	public void verificationSaisieUtilisateurMultiple(String pQuestionPose,String pReponseSiLettre, String pReponseSiVide) {
			
		do{
			this.verificationSaisie = JOptionPane.showInputDialog(pQuestionPose);
			//verifie si lettre ou chiffre
			this.lettre = verifieSiLettre(this.verificationSaisie);
			//verifie si il y a rien de rentr�
			this.empty = this.verificationSaisie.isEmpty();
									
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
	 * permet de v�rifier si la saisie correspond � la demande(verfication du caract�re, si rien n'est rentr� et si les bons pions sont rentr�s)
	 */
	public void verificationSaisieUtilisateurMultiple(String pQuestionPose,String pReponseSiLettre, String pReponseSiVide, String pPasBonneCouleur, String pNbDeChiffreSaisieMauvais) {
		
		
		do{
			this.verificationSaisie = JOptionPane.showInputDialog(pQuestionPose);
			//verifie si lettre ou chiffre
			this.lettre = verifieSiLettre(this.verificationSaisie);
			//verifie si il y a rien de rentr�
			this.empty = this.verificationSaisie.isEmpty();
			
			this.nbCouleur = verifieSiSaisieCorrespondAuNombreDeCouleurPropose(verificationSaisie);
						
			if (this.lettre) 
				JOptionPane.showMessageDialog(null, pReponseSiLettre);
			else if (this.empty)
				JOptionPane.showMessageDialog(null, pReponseSiVide);
			else if (this.nbCouleur)
				JOptionPane.showMessageDialog(null, pPasBonneCouleur);
			
		}while( this.empty == true || this.lettre == true || this.nbCouleur == true );
		
	}

	/**
	 * 
	 * @param pReponse2
	 * @return un boolen True est retourn�, si une lettre est rentr�e par l'utilisateur
	 */
	private boolean verifieSiLettre(String pReponse2) {
		int test = 0;
		boolean verification = false;
		for(int i = 0; i < pReponse2.length(); i++) {
			test = (int) pReponse2.charAt(i);
			if (test < 48 || test > 57)
				verification = true;
							
		}
		return verification;
	}
	
	/**
	 * 
	 * @param pReponse
	 * @return
	 * un booleen true est retourn�, si le nombre de pions saisie ne correspond pas � celui demand�
	 */
	private boolean verifieSiSaisieCorrespondAuNombreDeCouleurPropose(String pSaisie) {
		int j;
		char c = ' ';
		String s ="";
		boolean verification = false;
		
		
			for(int i = 0; i < RessourcesMaster.getNbDeCouleur(); i++) {
				c = pSaisie.charAt(i);
				s = Character.toString(c);
				j = Integer.parseInt(s);
				
				if (j < 1 || j > RessourcesMaster.getNbDeCouleur())
					verification = true;
				System.out.println(j);
			}
		
		
		return verification;
	}
	
	protected int getChoixDuModeJeux() {
		this.choixDuModeJeux = Integer.parseInt(getVerificationSaisie());
		return choixDuModeJeux;
	}


	protected void setChoixDuModeJeux(int choixDuModeJeux) {
		this.choixDuModeJeux = choixDuModeJeux;
		
	}
	
	public String getVerificationSaisie() {
		return verificationSaisie;
	}

	public void setVerificationSaisie(String verificationSaisie) {
		this.verificationSaisie = verificationSaisie;
	}
}
