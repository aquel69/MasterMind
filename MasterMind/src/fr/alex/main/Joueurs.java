package fr.alex.main;

import javax.swing.JOptionPane;

public class  Joueurs {
	private int choixDuModeJeux;
	private String reponse;
	private boolean verite, verite2,verite3;
	RessourcesMaster ressources = new RessourcesMaster();
	
	public Joueurs(){
		
	}
	
	
	public void verificationSaisieUtilisateurMultiple(String p1Demande,String p2Reponse, String p3Reponse) {
		
		do{
			this.reponse = JOptionPane.showInputDialog(p1Demande);
			//verifie si lettre ou chiffre
			this.verite = verifierSiLettreOuNombre(this.reponse);
			//verifie si il y a rien de rentré
			this.verite2 = this.reponse.isEmpty();
									
			if (this.verite) 
				JOptionPane.showMessageDialog(null, p2Reponse);
			else if (this.verite2)
				JOptionPane.showMessageDialog(null, p3Reponse);
			
		}while( this.verite2 == true || this.verite == true );
		
	}
	
public void verificationSaisieUtilisateurMultiple(String p1Demande,String p2Reponse, String p3Reponse, String p4Reponse) {
		
		do{
			this.reponse = JOptionPane.showInputDialog(p1Demande);
			//verifie si lettre ou chiffre
			this.verite = verifierSiLettreOuNombre(this.reponse);
			//verifie si il y a rien de rentré
			this.verite2 = this.reponse.isEmpty();
			
			this.verite3 = verifierSiSaisieCorrespondAuNombreDeCouleurPropose(this.reponse);
						
			if (this.verite) 
				JOptionPane.showMessageDialog(null, p2Reponse);
			else if (this.verite2)
				JOptionPane.showMessageDialog(null, p3Reponse);
			else if (this.verite3)
				JOptionPane.showMessageDialog(null, p4Reponse);
		}while( this.verite2 == true || this.verite == true || this.verite3 == true );
		
	}

	/**
	 * 
	 * @param pReponse2
	 * @return un boolen True si une lettre est rentrée par l'utilisateur
	 */
	private boolean verifierSiLettreOuNombre(String pReponse2) {
		int test = 0;
		boolean verification = false;
		for(int i = 0; i < pReponse2.length(); i++) {
			test = (int) pReponse2.charAt(i);
			if (test < 48 || test > 57)
				verification = true;
							
		}
		return verification;
	}
	
	private boolean verifierSiSaisieCorrespondAuNombreDeCouleurPropose(String pReponse) {
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
	
	public static void affichageDuResultatEtDesIndices(String pReponse, String pResultat) {
		JOptionPane.showMessageDialog(null, pReponse + "\n" + pResultat);
	}


	protected String getReponse() {
		return reponse;
	}


	protected void setReponse(String reponse) {
		this.reponse = reponse;
	}


	protected boolean isVerite() {
		return verite;
	}


	protected void setVerite(boolean verite) {
		this.verite = verite;
	}


	protected boolean isVerite2() {
		return verite2;
	}


	protected void setVerite2(boolean verite2) {
		this.verite2 = verite2;
	}


	protected int getChoixDuModeJeux() {
		
		this.choixDuModeJeux = Integer.parseInt(this.reponse);
		return choixDuModeJeux;
	}


	protected void setChoixDuModeJeux(int choixDuModeJeux) {
		this.choixDuModeJeux = choixDuModeJeux;
		
	}
	
	
}

