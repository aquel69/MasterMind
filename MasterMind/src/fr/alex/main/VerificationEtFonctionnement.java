package fr.alex.main;

import javax.swing.JOptionPane;

public class VerificationEtFonctionnement {
	
	private int choixDuModeJeux;
	//private String saisie;
	private boolean lettre, empty,nbCouleur;
	
	RessourcesMaster ressources = new RessourcesMaster();
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
	public String verificationSaisieUtilisateurMultiple(String pQuestionPose,String pReponseSiLettre, String pReponseSiVide) {
			String saisie;	
		do{
			saisie = JOptionPane.showInputDialog(pQuestionPose);
			//verifie si lettre ou chiffre
			this.lettre = verifieSiLettre(saisie);
			//verifie si il y a rien de rentr�
			this.empty = saisie.isEmpty();
									
			if (this.lettre) 
				JOptionPane.showMessageDialog(null, pReponseSiLettre);
			else if (this.empty)
				JOptionPane.showMessageDialog(null, pReponseSiVide);
			
		}while( this.empty == true || this.lettre == true );
		return saisie;
	}
	
	/**
	 * 
	 * @param pQuestionPose
	 * @param pReponseSiLettre
	 * @param pReponseSiVide
	 * @param p4Reponse
	 * permet de v�rifier si la saisie correspond � la demande(verfication du caract�re, si rien n'est rentr� et si les bons pions sont rentr�s)
	 */
	public String verificationSaisieUtilisateurMultiple(String pQuestionPose,String pReponseSiLettre, String pReponseSiVide, String p4Reponse) {
		String saisie;
		
		do{
			saisie = JOptionPane.showInputDialog(pQuestionPose);
			//verifie si lettre ou chiffre
			this.lettre = verifieSiLettre(saisie);
			//verifie si il y a rien de rentr�
			this.empty = saisie.isEmpty();
			
			this.nbCouleur = verifieSiSaisieCorrespondAuNombreDeCouleurPropose(saisie);
						
			if (this.lettre) 
				JOptionPane.showMessageDialog(null, pReponseSiLettre);
			else if (this.empty)
				JOptionPane.showMessageDialog(null, pReponseSiVide);
			else if (this.nbCouleur)
				JOptionPane.showMessageDialog(null, p4Reponse);
		}while( this.empty == true || this.lettre == true || this.nbCouleur == true );
		return saisie;
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
	private boolean verifieSiSaisieCorrespondAuNombreDeCouleurPropose(String pReponse) {
		int j;
		char c = ' ';
		String s ="";
		boolean verification = false;
		
		for(int i = 0; i < ressources.getNB_DE_COULEUR(); i++) {
			c = pReponse.charAt(i);
			s = Character.toString(c);
			j = Integer.parseInt(s);
			if (j < 1 || j > ressources.getNB_DE_COULEUR())
				verification = true;
			System.out.println(j);
		}
		
		return verification;
	}
	
	protected int getChoixDuModeJeux() {
		this.choixDuModeJeux = Integer.parseInt(joueurs.getSaisie());
		return choixDuModeJeux;
	}


	protected void setChoixDuModeJeux(int choixDuModeJeux) {
		this.choixDuModeJeux = choixDuModeJeux;
		
	}
}
