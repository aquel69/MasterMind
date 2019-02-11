package fr.alex.main;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class CorpsPrincipal {
	protected static Logger logger = LogManager.getLogger(CorpsPrincipal.class.getName());
	
	String reponse = " ", reponseModeDeveloppeur = " ",  clavier = "  ",resultat = " ";
	char carac = ' ';
	String choixModeDeJeu = " ";
	

	
	//final int NB_DE_COUP_MAX = 7;
	int nbDeCoupsJoues = 0;
	int nombreModeDeJeu = 0;
	int modeDeveloppeur = 0;
	//int nombreDeChiffreCombinaison = 4;

	boolean ordinateurGagne = false;
	boolean utilisateurGagne = false;
	
	RessourcesMaster ressources = new RessourcesMaster();
	Random random = new Random();
	Combinaison combinaisonMystere = new Combinaison();
	Joueurs joueurs = new Joueurs();
	Utilisateur utilisateur = new Utilisateur();
	VerificationEtFonctionnement fonctionnement = new VerificationEtFonctionnement();
	
	public CorpsPrincipal() {
		
	}
	
	public void init() {
				
			/** d�claration des classes
			 */
				
			do {
				presentation();
				do {
				
					joueurs.setSaisie(fonctionnement.verificationSaisieUtilisateurMultiple("Menu entrez un chiffre 1 � 5", "ne tapez pas de lettre, rentrez des chiffres", "veuillez rentrer au moins un chiffre"));
					
				}while(fonctionnement.getChoixDuModeJeux() < 0 || fonctionnement.getChoixDuModeJeux() > 5 || fonctionnement.getChoixDuModeJeux() == 0);
				
				//switch contenant les Diff�rents jeux
				switch(fonctionnement.getChoixDuModeJeux()) {

				//mode challenger
				case 1 :
				/** Boucle permettant de recommencer une partie
				 */
				do {
					//reinitialisation du nombre myst�re et du nb de coup jou� pour une nouvelle partie
					combinaisonMystere.setCombinaison("");
					nbDeCoupsJoues = 0;
					//initialisation de la combinaison myst�re
					combinaisonMystere.nombreMystere(random , ressources.getNB_DE_CHIFFRE_COMBINAISON());
					
					
					/** Boucle permettant de comparer les valeurs des deux tableaux
					 */
					do {
						/**Boucle permettant de s'assurer du nombre de caract�re saisie					
						 */
						do {

							//saisie d'une proposition par l'utilisateur
							joueurs.setSaisie(fonctionnement.verificationSaisieUtilisateurMultiple("Veuillez entrer une combinaison � "+ ressources.getNB_DE_CHIFFRE_COMBINAISON() +  " chiffres compris entre  1 et " + ressources.getNB_DE_COULEUR(), "ne tapez pas de lettre, rentrez des chiffres", "veuillez rentrer au moins un chiffre", "les pions sont compris entre 1 et " + ressources.getNB_DE_COULEUR()));
						
						}while (joueurs.getSaisie().length() != ressources.getNB_DE_CHIFFRE_COMBINAISON());
						
						combinaisonMystere.comparaisonTableau(combinaisonMystere.getCombinaison(), joueurs.getSaisie());
						combinaisonMystere.affichageDuResultatEtDesIndices(combinaisonMystere.getRecapitulatifPrecedentesPropositions());
						//ajout � chaque tour d'un coup jou�
						nbDeCoupsJoues++;

					}while(!combinaisonMystere.getCombinaison().equals(joueurs.getSaisie()) && nbDeCoupsJoues < ressources.getNB_DE_COUPS_MAX());

					if(nbDeCoupsJoues<ressources.getNB_DE_COUPS_MAX())
						resultatFinalGagnant(combinaisonMystere.getCombinaison(), nbDeCoupsJoues);
					else resultatFinalPerdant(combinaisonMystere.getCombinaison(), nbDeCoupsJoues);

						/** boucle pour relancer le programme, on entre un 'O' pour recommence et un 'N' pour quitter le programme
						 * 	demande d'entrer 'O' ou 'N' dans le Scanner
						 */
					do {
						/**
						 * boucle permettant d'eviter une erreur en s'assurant qu'il n y est qu'un seul caract�re
						 */
						do {
							clavier = saisieRelancePartie();
						}while(clavier.length() != 1);
							carac = clavier.charAt(0);

					}while(carac != 'O' && carac != 'N' && carac != 'Q');

				}while(carac == 'O');
				if (carac == 'N')
				break;
				else {
					nombreModeDeJeu = 5;
					break;
				}

				// mode defenseur
				case 2:
					//Boucle permettant de recommencer une partie
					

				case 3 :
					//Boucle permettant de recommencer une partie
					


				case 4 : 
					JOptionPane.showMessageDialog(null, "R�gle du jeu");
				}

			}while(nombreModeDeJeu != 5);
			//JOptionPane.showConfirmDialog(null, "Voulez vous quitter ?");
			JOptionPane.showMessageDialog(null, "Merci d'avoir jou�!!");
		}
	


/**
 * m�thode servant � la pr�sentation
 */
public static void presentation(){
	JOptionPane.showMessageDialog(null, 
	"---------------------------------------------------\n" +
	"---------- Jeu Master Mind ----------\n" +
	"---------------------------------------------------\n" +
	"1 - Mode Challenger\n" + 
	"2 - Mode Defenseur\n" +
	"3 - Mode Duel\n" +
	"4 - R�gles et aides\n" + 
	"5 - Quitter le jeu\n" +
	"---------------------------------------------------");
}

/**
 * 	
 * @param random1
 * @return Le nombre myst�re g�n�rer au hasard par l'ordinateur
 */
/**public static int[] nombreMystere(Random random1, int pNbCombinaison) {
	int nbProp[] = new int[pNbCombinaison];
	try {
		for (int i = 0; i < nbProp.length; i++) {
			int pNb = random1.nextInt(10);

			nbProp[i] = pNb;
			System.out.println("nombre aleatoire " + pNb);
		}


	}catch(Exception e)
	{logger.error("erreur sur le nombre mystere", e);}
	return nbProp;
}	*/


/**
 * 
 * @param p1Demande
 * @param p2Reponse
 * @return le nombre entr� par l'utilisateur et v�rifie si les caract�res entr�s sont des chiffres ou des lettres ou si il n'y a rien
 */
/**public static String verificationSaisieUtilisateurMultiple(String p1Demande,String p2Reponse, String p3Reponse) {
	String reponse;
	boolean verite, verite2;
	do{
		reponse = JOptionPane.showInputDialog(p1Demande);
		//verifie si lettre ou chiffre
		verite = verifierSiLettreOuNombre(reponse);
		//verifie si il y a rien de rentr�
		verite2 = reponse.isEmpty();
		
		if (verite) 
			JOptionPane.showMessageDialog(null, p2Reponse);
		else if (verite2)
			JOptionPane.showMessageDialog(null, p3Reponse);
	}while( verite2 == true || verite == true );
	return reponse;

}*/

/**
 * 
 * @param pReponse2
 * @return un boolen True si une lettre est rentr�e par l'utilisateur
 */
/**public static boolean verifierSiLettreOuNombre(String pReponse2) {
	int test = 0;
	boolean verification = false;
	for(int i = 0; i < pReponse2.length(); i++) {
		test = (int) pReponse2.charAt(i);
		if (test < 48 || test > 57)
			verification = true;
						
	}
	return verification;
}*/


/**
 * 
 * @param pReponse
 * @param pNbChiffreCombinaison
 * @return une chaine de caractere, puis s�par� en char et d'une conversion en entier puis placer dans un tableau
 */
public static int[] conversionChaineEnEntier(String pReponse, int pNbChiffreCombinaison) {


	int nbreProp[] = new int[pNbChiffreCombinaison];
	for (int i = 0; i < nbreProp.length; i++) {
		char nbRec = pReponse.charAt(i);
		int nombrePropose = Character.getNumericValue(nbRec); 
		nbreProp[i] = nombrePropose;
	}

	return nbreProp;
}

/**
 * 
 * @param pReponse
 * @param pNbDeChiffreCombinaison
 * @return la liste des nombres propos�s par l'utilisateur pour la combinaison en mode d�fenseur
 */
public static ArrayList<Integer> conversionChaineEnEntierEtPlacerDansUneList(String pReponse, int pNbDeChiffreCombinaison){
	ArrayList<Integer> nbreProp = new ArrayList<Integer>();
	for (int i = 0; i <pNbDeChiffreCombinaison; i++) {
		char nbRec = pReponse.charAt(i);
		int nombrePropose = Character.getNumericValue(nbRec); 
		nbreProp.add(i, nombrePropose);
	}
	return nbreProp;
}


/**
 * 
 * @param pNbrePropose
 * @param pNbreRecherche
 * @param pReponse
 * indique le signe sur chaque chiffre correspondant � la diff�rence entre les deux tableaux et indiquant "+","-" ou "=" suivant le r�sultat
 * @return 
 */
/**public static String comparaisonTableau(int pNbrePropose[], int pNbreRecherche[]) {
	String signe = " ";
	String resultat = " ";

	for (int i = 0; i < pNbrePropose.length; i++) {

		if (pNbrePropose[i] < pNbreRecherche[i]) {
			signe = "+";
		}else if (pNbrePropose[i] > pNbreRecherche[i]){
			signe = "-";
		}else {
			signe = "=";
		}
			resultat = resultat + signe;

	}

	return resultat;
}*/


/**
 * 
 * @param pListeDesChiffresUtilisateurs
 * @param pListeDeLaCombinaisonOrdinateur
 * @return comparaison des deux listes et retourne un caractere indiquant le + ou - ou = suivant le resultat
 */
/**public static String comparaisonTableau(ArrayList<Integer> pListeDesChiffresUtilisateurs, ArrayList<Integer> pListeDeLaCombinaisonOrdinateur) {
	String signe = " ";
	String resultat = " ";

	for (int i = 0; i < pListeDesChiffresUtilisateurs.size(); i++) {

		if (pListeDesChiffresUtilisateurs.get(i) < pListeDeLaCombinaisonOrdinateur.get(i)) {
			signe = "+";
		}else if (pListeDesChiffresUtilisateurs.get(i) > pListeDeLaCombinaisonOrdinateur.get(i)){
			signe = "-";
		}else {
			signe = "=";
		}
			resultat = resultat + signe;

	}

	return resultat;
}*/

/**
 * 
 * @param pNombreDeChiffreCombinaison
 * @param pNbDeCoupsJoues
 * @param pListeDesChiffresProposeParOrdinateur
 * @param pListeDesPrecedentsChiffreProposeParOrdinateur
 * @param pListeDesChiffresEntreParUtilisateur
 * @param pListeSauvegardeCombinaisonOrdi
 * @param pRandom
 * @return la liste des chiffres propos�s par l'ordinateur. A chaque tour de boucle, la fonction compare les chiffres des diff�rentes listes et propose une nouvelle combinaison
 */
public static ArrayList<Integer> remplissageDeLaListeDesChiffresProposeParLOrdinateur( int pNombreDeChiffreCombinaison, int pNbDeCoupsJoues,  ArrayList<Integer> pListeDesChiffresProposeParOrdinateur, ArrayList<Integer> pListeDesPrecedentsChiffreProposeParOrdinateur, ArrayList<Integer> pListeDesChiffresEntreParUtilisateur, ArrayList<Integer> pListeSauvegardeCombinaisonOrdi, Random pRandom){
	try {
		if(pNbDeCoupsJoues == 0) {
			for (int i = 0; i < pNombreDeChiffreCombinaison; i++) {
				pListeDesChiffresProposeParOrdinateur.add(i, 5);
			}
		}else {
			//boucle permettant de comparer chaque chiffre et de dire si il est plus grand ou plus petit ou �gal
			for(int i = 0; i < pNombreDeChiffreCombinaison; i++) {	
				//j = i pour que l'index soit le meme pour la liste de jou� et la liste de sauvegarde
				int j = i;
				//boucle permaettant de de parcourir les chiffres sauvegard�s et de les comparer pour ameliorer la proposition de l'ordinateur
				//System.out.println("liste des precedents : " + pListeDesPrecedentsChiffreProposeParOrdinateur.toString());
					// comparer la liste pr�cedente avec la combinaison a trouver.
					if (pListeDesPrecedentsChiffreProposeParOrdinateur.get(i) > pListeDesChiffresEntreParUtilisateur.get(i)) {

						//boucle permettant que tant que le chiffre al�atoire choisi n'est pas diff�rent que ceux d�ja propos�; recommence du d�but et parcour la liste compl�te en ajoutant avec 'j' le nombre correspondant � touts les chiffres d�ja jou�s.
						//On sort lorsque le tableau est compl�tement parcouru et qu'aucun chiffre sauvegard� n'est similaire au propos�
						if (pListeDesPrecedentsChiffreProposeParOrdinateur.get(i) <= 5 ) {

							pListeDesChiffresProposeParOrdinateur.set(i, pRandom.nextInt(pListeDesPrecedentsChiffreProposeParOrdinateur.get(i)));
							do{
								//comparaison du chiffre al�atoire propos� et de la liste sauvegard�e
								if (pListeDesChiffresProposeParOrdinateur.get(i) == pListeSauvegardeCombinaisonOrdi.get(j)/**|| listeDesChiffresProposeParOrdinateur.get(i) > nombreMaximum*/) {

									//nouveau chiffre al�atoire et pour recommencer du d�but et reprendre � l'index i	
									pListeDesChiffresProposeParOrdinateur.set(i, pRandom.nextInt(pListeDesPrecedentsChiffreProposeParOrdinateur.get(i)));

								//sinon ajouter 4 pour comparer avec le prochain	
								}else  j+=pNombreDeChiffreCombinaison;
							}while( j < pListeSauvegardeCombinaisonOrdi.size());

						}else if(pListeDesPrecedentsChiffreProposeParOrdinateur.get(i) >= 5 && pListeDesChiffresEntreParUtilisateur.get(i) > 5){
							pListeDesChiffresProposeParOrdinateur.set(i, pRandom.nextInt(pListeDesPrecedentsChiffreProposeParOrdinateur.get(i) - 6) + 6);
							do{
								//comparaison du chiffre al�atoire propos� et de la liste sauvegard�e
								if (pListeDesChiffresProposeParOrdinateur.get(i) == pListeSauvegardeCombinaisonOrdi.get(j)/**|| listeDesChiffresProposeParOrdinateur.get(i) > nombreMaximum*/) {
								//nouveau chiffre al�atoire et pour recommencer du d�but et reprendre � l'index i	

									pListeDesChiffresProposeParOrdinateur.set(i, pRandom.nextInt(pListeDesPrecedentsChiffreProposeParOrdinateur.get(i) - 6) + 6);

									j=i;
								//sinon ajouter 4 pour comparer avec le prochain	
								}else  j+=pNombreDeChiffreCombinaison;
							}while( j < pListeSauvegardeCombinaisonOrdi.size());
						}
						//m�me chose mais avec chiffre propos� plus petit que celui a trouv�
					}else if(pListeDesPrecedentsChiffreProposeParOrdinateur.get(i) < pListeDesChiffresEntreParUtilisateur.get(i)){

						if (pListeDesPrecedentsChiffreProposeParOrdinateur.get(i) >= 5) {

							pListeDesChiffresProposeParOrdinateur.set(i, pRandom.nextInt(10 - pListeDesPrecedentsChiffreProposeParOrdinateur.get(i)) + pListeDesPrecedentsChiffreProposeParOrdinateur.get(i));
							do{
								//comparaison du chiffre al�atoire propos� et de la liste sauvegard�e
								if (pListeDesChiffresProposeParOrdinateur.get(i) == pListeSauvegardeCombinaisonOrdi.get(j)/**|| listeDesChiffresProposeParOrdinateur.get(i) > nombreMaximum*/) {
								//nouveau chiffre al�atoire et pour recommencer du d�but et reprendre � l'index i	

									pListeDesChiffresProposeParOrdinateur.set(i, pRandom.nextInt(10 - pListeDesPrecedentsChiffreProposeParOrdinateur.get(i)) + pListeDesPrecedentsChiffreProposeParOrdinateur.get(i));

									j=i;
								//sinon ajouter 4 pour comparer avec le prochain	
								}else  j+=pNombreDeChiffreCombinaison;
							}while( j < pListeSauvegardeCombinaisonOrdi.size());
						}else{
							pListeDesChiffresProposeParOrdinateur.set(i, pRandom.nextInt(5 - pListeDesPrecedentsChiffreProposeParOrdinateur.get(i)) + pListeDesPrecedentsChiffreProposeParOrdinateur.get(i));
							do{
								//comparaison du chiffre al�atoire propos� et de la liste sauvegard�e
								if (pListeDesChiffresProposeParOrdinateur.get(i) == pListeSauvegardeCombinaisonOrdi.get(j)/**|| listeDesChiffresProposeParOrdinateur.get(i) > nombreMaximum*/) {
								//nouveau chiffre al�atoire et pour recommencer du d�but et reprendre � l'index i	

									pListeDesChiffresProposeParOrdinateur.set(i, pRandom.nextInt(5 - pListeDesPrecedentsChiffreProposeParOrdinateur.get(i)) + pListeDesPrecedentsChiffreProposeParOrdinateur.get(i));

									j=i;
								//sinon ajouter 4 pour comparer avec le prochain	
								}else  j+=pNombreDeChiffreCombinaison;
							}while( j < pListeSauvegardeCombinaisonOrdi.size());
						}



				}else {
					pListeDesChiffresProposeParOrdinateur.set(i, pListeDesChiffresProposeParOrdinateur.get(i));
				}

				//nombreMaximum = 5;
				//nombreMinimum = 5;


		}

	}
	
	}catch(Exception e) {
		logger.debug("probl�me sur la liste des chiffres propos�s par l'ordinateur", e);
	}
	return pListeDesChiffresProposeParOrdinateur;
}



/**
 * 
 * @param pListeDesPrecedentsChiffreProposeParOrdinateur
 * @param pListeDesChiffresProposeParOrdinateur
 * @param pNbDeCoupsJoues
 * @return la liste des chiffres precedents servant de base au random pour le prochain tour
 */
public static ArrayList<Integer> transfertDeLaListeDesChiffresProposesDansLaListeDesChiffresPrecedents(ArrayList<Integer> pListeDesPrecedentsChiffreProposeParOrdinateur, ArrayList<Integer> pListeDesChiffresProposeParOrdinateur,int pNbDeCoupsJoues){
	for(int i = 0; i < pListeDesChiffresProposeParOrdinateur.size();i++) {
		if(pNbDeCoupsJoues == 0)
		pListeDesPrecedentsChiffreProposeParOrdinateur.add(i, pListeDesChiffresProposeParOrdinateur.get(i));
		else
		pListeDesPrecedentsChiffreProposeParOrdinateur.set(i, pListeDesChiffresProposeParOrdinateur.get(i));
	}
	return pListeDesPrecedentsChiffreProposeParOrdinateur;
}



/**
 * 
 * @param pListeSauvegardeCombinaisonOrdi
 * @param pListeDesChiffresProposeParOrdinateur
 * @return la liste des pr�cedent chiffres jou� pour ensuite que l'ordinateur ne les rejoue pas
 */
public static ArrayList<Integer> ajoutDeLaListeDesChiffresProposesDansLaListeDesSauvegardes(ArrayList<Integer> pListeSauvegardeCombinaisonOrdi, ArrayList<Integer> pListeDesChiffresProposeParOrdinateur ){
	for (int i = 0; i < pListeDesChiffresProposeParOrdinateur.size(); i++) {
		pListeSauvegardeCombinaisonOrdi.add(pListeDesChiffresProposeParOrdinateur.get(i));
	}
	return pListeSauvegardeCombinaisonOrdi;
}



/**
 * 
 * @param pReponse
 * @param pResultat
 */
/**public static void affichageDuResultatEtDesIndices(String pReponse, String pResultat) {
	JOptionPane.showMessageDialog(null, pReponse + "\n" + pResultat);
}*/



/**
 * 
 * @return la r�ponse fournie par l'utilisateur pour red�marrer ou non, une partie
 */
public static String saisieRelancePartie() {

	String clavier = JOptionPane.showInputDialog("Voulez vous faire une autre partie ?\n(O pour 'oui' / N pour 'non' / Q pour 'Quitter')");

	return clavier;
}


/**
 * 
 * @param pListeDesChiffresUtilisateur
 * @param pListeDeLaCombinaisonMystere
 * @return un boolean qui indique si l'utilisateur a r�ussi dans le mode 3
 */
public static boolean SavoirSiLUtilisateurAGagne(ArrayList<Integer> pListeDesChiffresUtilisateur, ArrayList<Integer> pListeDeLaCombinaisonMystere){
	boolean reponse = false;
	if(pListeDesChiffresUtilisateur.equals(pListeDeLaCombinaisonMystere)) {
		reponse = true;
	}
	return reponse;
}



/**
 * 
 * @param pListeDesChiffresOrdinateur
 * @param pListeDeLaCombinaisonMystere
 * @return un boolean qui indique si l'ordinateur a r�ussi dans le mode 3
 */
public static boolean SavoirSiLOrdinateurAGagne(ArrayList<Integer> pListeDesChiffresOrdinateur, ArrayList<Integer> pListeDeLaCombinaisonMystere){
	boolean reponse = false;
	if(pListeDesChiffresOrdinateur.equals(pListeDeLaCombinaisonMystere)) {
		reponse = true;
	}
	return reponse;
}



/**
 * 
 * @param pNbreRecherche
 * @param pNbDeCoups
 * affichage du r�sultat trouv� et du nombre de coup qu'il a fallu � l'utilisateur pour y arriver
 */
public static void resultatFinalGagnant(String pResultat, int pNbDeCoups) {
	JOptionPane.showMessageDialog(null, 
	"-----------------------------------------\n" +
	"Vous avez trouv� la r�ponse est bien " +
	pResultat +
	"\nVous l'avez r�ussi en " + pNbDeCoups + " coups\n" +
	"-----------------------------------------");
}


/**
 * 
 * @param pNbrePropose
 * @param pNbDeCoups
 */
public static void resultatFinalGagnant(ArrayList<Integer> pNbrePropose, int pNbDeCoups) {
	JOptionPane.showMessageDialog(null, 
	"-----------------------------------------\n" +
	"Vous avez trouv� la r�ponse est bien " +
	renvoiLeResultatEnString(pNbrePropose) +
	"\nVous l'avez r�ussi en " + pNbDeCoups + " coups\n" +
	"-----------------------------------------");
}



/**
 * 
 * @param pNbrePropose
 * @param pNbCoupJoue
 */
	public static void resultatFinalPerdant(String pResultat, int pNbCoupJoue) {
	JOptionPane.showMessageDialog(null, 
	"---------------------------------------------------------------\n" +
	"--Vous n'avez pas trouv� la bonne r�ponse!--\n" +
	"Dans le nombre de coup imparti qui �tait de " + pNbCoupJoue +
	"\n----------------La solution �tait : " + pResultat + "----------------" +
	"\n---------------------------------------------------------------");
}


/**
 * 
 * @param pNbreProposeParUtilisateur
 * @param pNbDeCoups
 */
	public static void resultatFinalGagnantOrdi( ArrayList<Integer> pNbreProposeParUtilisateur, int pNbDeCoups) {
	JOptionPane.showMessageDialog(null, 
	"-----------------------------------------------------------\n" +
	"      l'ordinateur a bien trouv� la solution\n                           qui est : " +
	renvoiLeResultatEnString(pNbreProposeParUtilisateur) +
	"\n                  Il a r�ussi en " + pNbDeCoups + " coups\n" +
	"-----------------------------------------------------------");
}


/**
 * 
 * @param pNbreProposeParUtilisateur
 * @param pNbCoupMax
 */
public static void resultatFinalPerdantOrdi(ArrayList<Integer> pNbreProposeParUtilisateur, int pNbCoupMax) {
	JOptionPane.showMessageDialog(null, 
	"---------------------------------------------------------------\n" +
	"L'ordinateur n'a pas trouv� la bonne r�ponse!\n" +
	"Dans le nombre de coup imparti qui �tait de " + pNbCoupMax +
	"\n----------------La solution �tait : " + renvoiLeResultatEnString(pNbreProposeParUtilisateur) + "----------------" +
	"\n---------------------------------------------------------------");
}


/**
 * 
 */
public static void resultatsEgauxEntreUtilisateurEtOrdinateur() {
	JOptionPane.showMessageDialog(null,
	"---------------------------------------------------------------\n" +
	"-----------------------MATCH NUL----------------------\n" +
	"---------------------------------------------------------------");
}



/**
 * 
 * @param pNbrePropose
 * @return renvoi la combinaison finale en chaine de caract�re pour les tableaux
 */
public static String renvoiLeResultatEnString(int[] pNbrePropose) {
	String resultat = " ";
		for(int i = 0;i < pNbrePropose.length;i++) {
			resultat = resultat + Integer.toString(pNbrePropose[i]);
		}
	return resultat;
}


/**
 * 
 * @param pNbreProposeParUtilisateur
 * @return renvoi la combinaison finale en chaine de caract�re pour les listes
 */
public static String renvoiLeResultatEnString(ArrayList<Integer> pNbreProposeParUtilisateur) {
	String resultat = " ";
		for(int i = 0;i < pNbreProposeParUtilisateur.size();i++) {
			resultat = resultat + Integer.toString(pNbreProposeParUtilisateur.get(i));
		}
	return resultat;
}


}
