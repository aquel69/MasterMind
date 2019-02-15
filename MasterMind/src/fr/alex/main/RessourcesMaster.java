package fr.alex.main;

import java.util.ResourceBundle;

import javax.swing.JOptionPane;

public class RessourcesMaster {
		
	private static int nbDeChiffreCombinaison = 0;
	private static int nbDeCoupMax = 0;
	private static int nbDeCouleur = 0;
	
	private String nombreDeChiffreCombinaisonString;
	private String nombreDeCoupMaxstring;
	private String nombreDeCouleurstring;
	
	private String reponse;
	private boolean verite;
	private boolean verite2;
	
	VerificationEtFonctionnement verification = new VerificationEtFonctionnement();
	
	public RessourcesMaster(){
		ResourceBundle bundle = ResourceBundle.getBundle("config");
		nombreDeChiffreCombinaisonString = bundle.getString("masterMind.nbDeChiffreCombinaison");
		nombreDeChiffreCombinaisonString = verificationSaisieUtilisateurMultiple(nombreDeChiffreCombinaisonString, "le parametre nb de Chiffre étant une lettre je l'ai remplacé par 4", "le parametre nb de Chiffre étant vide je l'ai remplacé par 4", "4");
		nbDeChiffreCombinaison = Integer.parseInt(nombreDeChiffreCombinaisonString);
		
		nombreDeCoupMaxstring = bundle.getString("masterMind.nbDeCoupMax");
		nombreDeCoupMaxstring = verificationSaisieUtilisateurMultiple(nombreDeCoupMaxstring, "le parametre nb de coups étant une lettre je l'ai remplacé par 9", "le parametre nb de Chiffre étant vide je l'ai remplacé par 9", "9");
		nbDeCoupMax = Integer.parseInt(nombreDeCoupMaxstring);
		
		nombreDeCouleurstring = bundle.getString("masterMind.nbDeCouleur");
		nombreDeCouleurstring = verificationSaisieUtilisateurMultiple(nombreDeCouleurstring, "le parametre nb de couleur étant une lettre je l'ai remplacé par 4", "le parametre nb de Chiffre étant vide je l'ai remplacé par 4", "4");
		nbDeCouleur = Integer.parseInt(nombreDeCouleurstring);
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
		
	
	public static int getNbDeChiffreCombinaison() {
		return nbDeChiffreCombinaison;
	}
		
			
	public static void setNbDeChiffreCombinaison(int nbDeChiffreCombinaison) {
		RessourcesMaster.nbDeChiffreCombinaison = nbDeChiffreCombinaison;
	}
		
	
	public static int getNbDeCoupMax() {
		return nbDeCoupMax;
	}
		
	
	public static void setNbDeCoupMax(int nbDeCoupMax) {
		RessourcesMaster.nbDeCoupMax = nbDeCoupMax;
	}
		
	
	public static int getNbDeCouleur() {
		return nbDeCouleur;
	}
		
	
	public static void setNbDeCouleur(int nbDeCouleur) {
		RessourcesMaster.nbDeCouleur = nbDeCouleur;
	}
		
}
