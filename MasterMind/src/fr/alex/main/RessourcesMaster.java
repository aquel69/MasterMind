package fr.alex.main;

import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RessourcesMaster {
		
	public static int nbDeChiffreCombinaison = 0;
	public static int nbDeCoupMax = 0;
	public static int nbDeCouleur = 0;
	
	private String nombreDeChiffreCombinaisonString;
	private String nombreDeCoupMaxstring;
	private String nombreDeCouleurstring;
	
	private String valeur;
	private boolean lettre;
	private boolean empty;
	
	VerificationEtFonctionnement verification = new VerificationEtFonctionnement();
	
	private static Logger loggerRessources= LogManager.getLogger(Combinaison.class.getName());
	
	public RessourcesMaster(){
		ResourceBundle bundle = ResourceBundle.getBundle("config");
		try {
		nombreDeChiffreCombinaisonString = bundle.getString("masterMind.nbDeChiffreCombinaison");
		} catch(Exception e)
		{loggerRessources.error("le nombre de chiffre combinaison est manquant, la valeur est de 4", e);
		nombreDeChiffreCombinaisonString = "4";
		}
		nombreDeChiffreCombinaisonString = verificationValeurProperties(nombreDeChiffreCombinaisonString, "le parametre nb de Chiffre étant une lettre je l'ai remplacé par 4", "le parametre nb de Chiffre étant vide je l'ai remplacé par 4", "4");
		nbDeChiffreCombinaison = Integer.parseInt(nombreDeChiffreCombinaisonString);
		
		nombreDeCoupMaxstring = bundle.getString("masterMind.nbDeCoupMax");
		nombreDeCoupMaxstring = verificationValeurProperties(nombreDeCoupMaxstring, "le parametre nb de coups étant une lettre je l'ai remplacé par 12", "le parametre nb de Chiffre étant vide je l'ai remplacé par 12", "12");
		nbDeCoupMax = Integer.parseInt(nombreDeCoupMaxstring);
		
		nombreDeCouleurstring = bundle.getString("masterMind.nbDeCouleur");
		nombreDeCouleurstring = verificationValeurProperties(nombreDeCouleurstring, "le parametre nb de couleur étant une lettre je l'ai remplacé par 6", "le parametre nb de Chiffre étant vide je l'ai remplacé par 6", "6");
		nbDeCouleur = Integer.parseInt(nombreDeCouleurstring);
	}
	
		
	public String verificationValeurProperties(String valeur,String messageSiLettre, String messageSiVide,String valeurDeRemplacement) {
			
			do{
				this.valeur = valeur;
				//verifie si lettre ou chiffre
				this.lettre = verifierSiLettreOuNombre(this.valeur);
				//verifie si il y a rien de rentré
				this.empty = this.valeur.isEmpty();
										
				if (this.lettre) {
					JOptionPane.showMessageDialog(null, messageSiLettre);
					valeur = valeurDeRemplacement;
				}else if (this.empty) {
					JOptionPane.showMessageDialog(null, messageSiVide);
				valeur = valeurDeRemplacement;
				}
			}while( this.empty == true || this.lettre == true );
			
			return valeur;
		}
	
	private boolean verifierSiLettreOuNombre(String valeur) {
		int test = 0;
		boolean verification = false;
		for(int i = 0; i < valeur.length(); i++) {
			test = (int) valeur.charAt(i);
			if (test < 48 || test > 57)
				verification = true;
							
		}
		return verification;
	}
	
}
