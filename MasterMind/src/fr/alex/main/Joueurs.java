package fr.alex.main;

import javax.swing.JOptionPane;

public class Joueurs {
	
	public Joueurs(){
		
	}
	
	
	public static String verificationSaisieUtilisateurMultiple(String p1Demande,String p2Reponse, String p3Reponse) {
		String reponse;
		boolean verite, verite2;
		do{
			reponse = JOptionPane.showInputDialog(p1Demande);
			//verifie si lettre ou chiffre
			verite = verifierSiLettreOuNombre(reponse);
			//verifie si il y a rien de rentré
			verite2 = reponse.isEmpty();
			
			if (verite) 
				JOptionPane.showMessageDialog(null, p2Reponse);
			else if (verite2)
				JOptionPane.showMessageDialog(null, p3Reponse);
		}while( verite2 == true || verite == true );
		return reponse;

	}

	/**
	 * 
	 * @param pReponse2
	 * @return un boolen True si une lettre est rentrée par l'utilisateur
	 */
	public static boolean verifierSiLettreOuNombre(String pReponse2) {
		int test = 0;
		boolean verification = false;
		for(int i = 0; i < pReponse2.length(); i++) {
			test = (int) pReponse2.charAt(i);
			if (test < 48 || test > 57)
				verification = true;
							
		}
		return verification;
	}
	
	public static void affichageDuResultatEtDesIndices(String pReponse, String pResultat) {
		JOptionPane.showMessageDialog(null, pReponse + "\n" + pResultat);
	}
	
	
}

