package fr.alex.main;

import java.util.ResourceBundle;

import javax.swing.JOptionPane;

public class RessourcesMaster {
	public final int NB_DE_CHIFFRE_COMBINAISON;
	public final int NB_DE_COUPS_MAX;
	public final int NB_DE_COULEUR;
	private String nombreDeChiffreCombinaisonString;
	private String nb_de_coups_max_string;
	private String nb_de_couleur_string;
	
	private String reponse;
	private boolean verite;
	private boolean verite2;
	
	public RessourcesMaster(){
		
		
		ResourceBundle bundle = ResourceBundle.getBundle("config");
		nombreDeChiffreCombinaisonString = bundle.getString("masterMind.nbDeChiffreCombinaison");
		nombreDeChiffreCombinaisonString = verificationSaisieUtilisateurMultiple(nombreDeChiffreCombinaisonString, "le parametre nb de Chiffre étant une lettre je l'ai remplacé par 4", "le parametre nb de Chiffre étant vide je l'ai remplacé par 4", "4");
		NB_DE_CHIFFRE_COMBINAISON = Integer.parseInt(nombreDeChiffreCombinaisonString);
		
		nb_de_coups_max_string = bundle.getString("masterMind.nbDeCoupMax");
		nb_de_coups_max_string = verificationSaisieUtilisateurMultiple(nb_de_coups_max_string, "le parametre nb de coups étant une lettre je l'ai remplacé par 9", "le parametre nb de Chiffre étant vide je l'ai remplacé par 9", "9");
		NB_DE_COUPS_MAX = Integer.parseInt(nb_de_coups_max_string);
		
		nb_de_couleur_string = bundle.getString("masterMind.nbDeCouleur");
		nb_de_couleur_string = verificationSaisieUtilisateurMultiple(nb_de_couleur_string, "le parametre nb de couleur étant une lettre je l'ai remplacé par 4", "le parametre nb de Chiffre étant vide je l'ai remplacé par 4", "4");
		NB_DE_COULEUR = Integer.parseInt(nb_de_couleur_string);
		
		
	}
	
	
	
	
	
	
public String verificationSaisieUtilisateurMultiple(String p1Demande,String p2Reponse, String p3Reponse,String pRemplacement) {
		
		do{
			this.reponse =p1Demande;
			//verifie si lettre ou chiffre
			this.verite = verifierSiLettreOuNombre(this.reponse);
			//verifie si il y a rien de rentré
			this.verite2 = this.reponse.isEmpty();
									
			if (this.verite) {
				JOptionPane.showMessageDialog(null, p2Reponse);
				p1Demande = pRemplacement;
			}else if (this.verite2) {
				JOptionPane.showMessageDialog(null, p3Reponse);
			p1Demande = pRemplacement;
			}
		}while( this.verite2 == true || this.verite == true );
		
		return p1Demande;
		
	}

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
	
	protected int getNB_DE_COULEUR() {
		return NB_DE_COULEUR;
	}

	public int getNB_DE_CHIFFRE_COMBINAISON() {
		return NB_DE_CHIFFRE_COMBINAISON;
	}

	public int getNB_DE_COUPS_MAX() {
		return NB_DE_COUPS_MAX;
	}
	
}
