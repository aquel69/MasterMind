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
				
			/** déclaration des classes
			 */
				
			do {
				presentation();
				do {
				
					fonctionnement.verificationSaisieUtilisateurMultiple("Menu entrez un chiffre 1 à 5", "ne tapez pas de lettre, rentrez des chiffres", "veuillez rentrer au moins un chiffre");
					
				}while(fonctionnement.getChoixDuModeJeux() < 0 || fonctionnement.getChoixDuModeJeux() > 5 || fonctionnement.getChoixDuModeJeux() == 0);
				
				//switch contenant les Différents jeux
				switch(fonctionnement.getChoixDuModeJeux()) {

				//mode challenger
				case 1 :
				/** Boucle permettant de recommencer une partie
				 */
				do {
					//reinitialisation du nombre mystère et du nb de coup joué pour une nouvelle partie
					combinaisonMystere.setCombinaison("");
					nbDeCoupsJoues = 0;
					//initialisation de la combinaison mystère
					combinaisonMystere.generationDuNombreMystere(random , RessourcesMaster.getNbDeChiffreCombinaison());
					
					
					/** Boucle permettant de comparer les valeurs des deux tableaux
					 */
					do {
						/**Boucle permettant de s'assurer du nombre de caractère saisie					
						 */
						do {

							//saisie d'une proposition par l'utilisateur
							fonctionnement.verificationSaisieUtilisateurMultiple("Veuillez entrer une combinaison à "+ RessourcesMaster.getNbDeChiffreCombinaison() +  " chiffres compris entre  1 et " + RessourcesMaster.getNbDeCouleur(), "ne tapez pas de lettre, rentrez des chiffres", "veuillez rentrer au moins un chiffre", "les pions sont compris entre 1 et " + RessourcesMaster.getNbDeCouleur(),"veuillez rentrer le bon nombre de chiffre demandé, a savoir " + RessourcesMaster.getNbDeChiffreCombinaison());
						
						}while (fonctionnement.getVerificationSaisie().length() != RessourcesMaster.getNbDeChiffreCombinaison());
						
						combinaisonMystere.comparaisonTableau(combinaisonMystere.getCombinaison(), fonctionnement.getVerificationSaisie());
						combinaisonMystere.affichageDuResultatEtDesIndices(combinaisonMystere.getRecapitulatifPrecedentesPropositions());
						//ajout à chaque tour d'un coup joué
						nbDeCoupsJoues++;

					}while(!combinaisonMystere.getCombinaison().equals(fonctionnement.getVerificationSaisie()) && nbDeCoupsJoues < RessourcesMaster.getNbDeCoupMax());

					if(nbDeCoupsJoues<RessourcesMaster.getNbDeCoupMax())
						resultatFinalGagnant(combinaisonMystere.getCombinaison(), nbDeCoupsJoues);
					else resultatFinalPerdant(combinaisonMystere.getCombinaison(), nbDeCoupsJoues);

						/** boucle pour relancer le programme, on entre un 'O' pour recommence et un 'N' pour quitter le programme
						 * 	demande d'entrer 'O' ou 'N' dans le Scanner
						 */
					do {
						/**
						 * boucle permettant d'eviter une erreur en s'assurant qu'il n y est qu'un seul caractère
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
					JOptionPane.showMessageDialog(null, "Régle du jeu");
				}

			}while(nombreModeDeJeu != 5);
			//JOptionPane.showConfirmDialog(null, "Voulez vous quitter ?");
			JOptionPane.showMessageDialog(null, "Merci d'avoir joué!!");
		}
	


/**
 * méthode servant à la présentation
 */
public static void presentation(){
	JOptionPane.showMessageDialog(null, 
	"---------------------------------------------------\n" +
	"---------- Jeu Master Mind ----------\n" +
	"---------------------------------------------------\n" +
	"1 - Mode Challenger\n" + 
	"2 - Mode Defenseur\n" +
	"3 - Mode Duel\n" +
	"4 - Règles et aides\n" + 
	"5 - Quitter le jeu\n" +
	"---------------------------------------------------");
}


/**
 * 
 * @param pReponse
 * @param pNbChiffreCombinaison
 * @return une chaine de caractere, puis séparé en char et d'une conversion en entier puis placer dans un tableau
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
 * @return la liste des nombres proposés par l'utilisateur pour la combinaison en mode défenseur
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
 * @param pNombreDeChiffreCombinaison
 * @param pNbDeCoupsJoues
 * @param pListeDesChiffresProposeParOrdinateur
 * @param pListeDesPrecedentsChiffreProposeParOrdinateur
 * @param pListeDesChiffresEntreParUtilisateur
 * @param pListeSauvegardeCombinaisonOrdi
 * @param pRandom
 * @return la liste des chiffres proposés par l'ordinateur. A chaque tour de boucle, la fonction compare les chiffres des différentes listes et propose une nouvelle combinaison
 */
public static ArrayList<Integer> remplissageDeLaListeDesChiffresProposeParLOrdinateur( int pNombreDeChiffreCombinaison, int pNbDeCoupsJoues,  ArrayList<Integer> pListeDesChiffresProposeParOrdinateur, ArrayList<Integer> pListeDesPrecedentsChiffreProposeParOrdinateur, ArrayList<Integer> pListeDesChiffresEntreParUtilisateur, ArrayList<Integer> pListeSauvegardeCombinaisonOrdi, Random pRandom){
	try {
		if(pNbDeCoupsJoues == 0) {
			for (int i = 0; i < pNombreDeChiffreCombinaison; i++) {
				pListeDesChiffresProposeParOrdinateur.add(i, 5);
			}
		}else {
			//boucle permettant de comparer chaque chiffre et de dire si il est plus grand ou plus petit ou égal
			for(int i = 0; i < pNombreDeChiffreCombinaison; i++) {	
				//j = i pour que l'index soit le meme pour la liste de joué et la liste de sauvegarde
				int j = i;
				//boucle permaettant de de parcourir les chiffres sauvegardés et de les comparer pour ameliorer la proposition de l'ordinateur
				//System.out.println("liste des precedents : " + pListeDesPrecedentsChiffreProposeParOrdinateur.toString());
					// comparer la liste précedente avec la combinaison a trouver.
					if (pListeDesPrecedentsChiffreProposeParOrdinateur.get(i) > pListeDesChiffresEntreParUtilisateur.get(i)) {

						//boucle permettant que tant que le chiffre aléatoire choisi n'est pas différent que ceux déja proposé; recommence du début et parcour la liste compléte en ajoutant avec 'j' le nombre correspondant à touts les chiffres déja joués.
						//On sort lorsque le tableau est complétement parcouru et qu'aucun chiffre sauvegardé n'est similaire au proposé
						if (pListeDesPrecedentsChiffreProposeParOrdinateur.get(i) <= 5 ) {

							pListeDesChiffresProposeParOrdinateur.set(i, pRandom.nextInt(pListeDesPrecedentsChiffreProposeParOrdinateur.get(i)));
							do{
								//comparaison du chiffre aléatoire proposé et de la liste sauvegardée
								if (pListeDesChiffresProposeParOrdinateur.get(i) == pListeSauvegardeCombinaisonOrdi.get(j)/**|| listeDesChiffresProposeParOrdinateur.get(i) > nombreMaximum*/) {

									//nouveau chiffre aléatoire et pour recommencer du début et reprendre à l'index i	
									pListeDesChiffresProposeParOrdinateur.set(i, pRandom.nextInt(pListeDesPrecedentsChiffreProposeParOrdinateur.get(i)));

								//sinon ajouter 4 pour comparer avec le prochain	
								}else  j+=pNombreDeChiffreCombinaison;
							}while( j < pListeSauvegardeCombinaisonOrdi.size());

						}else if(pListeDesPrecedentsChiffreProposeParOrdinateur.get(i) >= 5 && pListeDesChiffresEntreParUtilisateur.get(i) > 5){
							pListeDesChiffresProposeParOrdinateur.set(i, pRandom.nextInt(pListeDesPrecedentsChiffreProposeParOrdinateur.get(i) - 6) + 6);
							do{
								//comparaison du chiffre aléatoire proposé et de la liste sauvegardée
								if (pListeDesChiffresProposeParOrdinateur.get(i) == pListeSauvegardeCombinaisonOrdi.get(j)/**|| listeDesChiffresProposeParOrdinateur.get(i) > nombreMaximum*/) {
								//nouveau chiffre aléatoire et pour recommencer du début et reprendre à l'index i	

									pListeDesChiffresProposeParOrdinateur.set(i, pRandom.nextInt(pListeDesPrecedentsChiffreProposeParOrdinateur.get(i) - 6) + 6);

									j=i;
								//sinon ajouter 4 pour comparer avec le prochain	
								}else  j+=pNombreDeChiffreCombinaison;
							}while( j < pListeSauvegardeCombinaisonOrdi.size());
						}
						//même chose mais avec chiffre proposé plus petit que celui a trouvé
					}else if(pListeDesPrecedentsChiffreProposeParOrdinateur.get(i) < pListeDesChiffresEntreParUtilisateur.get(i)){

						if (pListeDesPrecedentsChiffreProposeParOrdinateur.get(i) >= 5) {

							pListeDesChiffresProposeParOrdinateur.set(i, pRandom.nextInt(10 - pListeDesPrecedentsChiffreProposeParOrdinateur.get(i)) + pListeDesPrecedentsChiffreProposeParOrdinateur.get(i));
							do{
								//comparaison du chiffre aléatoire proposé et de la liste sauvegardée
								if (pListeDesChiffresProposeParOrdinateur.get(i) == pListeSauvegardeCombinaisonOrdi.get(j)/**|| listeDesChiffresProposeParOrdinateur.get(i) > nombreMaximum*/) {
								//nouveau chiffre aléatoire et pour recommencer du début et reprendre à l'index i	

									pListeDesChiffresProposeParOrdinateur.set(i, pRandom.nextInt(10 - pListeDesPrecedentsChiffreProposeParOrdinateur.get(i)) + pListeDesPrecedentsChiffreProposeParOrdinateur.get(i));

									j=i;
								//sinon ajouter 4 pour comparer avec le prochain	
								}else  j+=pNombreDeChiffreCombinaison;
							}while( j < pListeSauvegardeCombinaisonOrdi.size());
						}else{
							pListeDesChiffresProposeParOrdinateur.set(i, pRandom.nextInt(5 - pListeDesPrecedentsChiffreProposeParOrdinateur.get(i)) + pListeDesPrecedentsChiffreProposeParOrdinateur.get(i));
							do{
								//comparaison du chiffre aléatoire proposé et de la liste sauvegardée
								if (pListeDesChiffresProposeParOrdinateur.get(i) == pListeSauvegardeCombinaisonOrdi.get(j)/**|| listeDesChiffresProposeParOrdinateur.get(i) > nombreMaximum*/) {
								//nouveau chiffre aléatoire et pour recommencer du début et reprendre à l'index i	

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
		logger.debug("problème sur la liste des chiffres proposés par l'ordinateur", e);
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
 * @return la liste des précedent chiffres joué pour ensuite que l'ordinateur ne les rejoue pas
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
 * @return la réponse fournie par l'utilisateur pour redémarrer ou non, une partie
 */
public static String saisieRelancePartie() {

	String clavier = JOptionPane.showInputDialog("Voulez vous faire une autre partie ?\n(O pour 'oui' / N pour 'non' / Q pour 'Quitter')");

	return clavier;
}


/**
 * 
 * @param pListeDesChiffresUtilisateur
 * @param pListeDeLaCombinaisonMystere
 * @return un boolean qui indique si l'utilisateur a réussi dans le mode 3
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
 * @return un boolean qui indique si l'ordinateur a réussi dans le mode 3
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
 * affichage du résultat trouvé et du nombre de coup qu'il a fallu à l'utilisateur pour y arriver
 */
public static void resultatFinalGagnant(String pResultat, int pNbDeCoups) {
	JOptionPane.showMessageDialog(null, 
	"-----------------------------------------\n" +
	"Vous avez trouvé la réponse est bien " +
	pResultat +
	"\nVous l'avez réussi en " + pNbDeCoups + " coups\n" +
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
	"Vous avez trouvé la réponse est bien " +
	renvoiLeResultatEnString(pNbrePropose) +
	"\nVous l'avez réussi en " + pNbDeCoups + " coups\n" +
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
	"--Vous n'avez pas trouvé la bonne réponse!--\n" +
	"Dans le nombre de coup imparti qui était de " + pNbCoupJoue +
	"\n----------------La solution était : " + pResultat + "----------------" +
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
	"      l'ordinateur a bien trouvé la solution\n                           qui est : " +
	renvoiLeResultatEnString(pNbreProposeParUtilisateur) +
	"\n                  Il a réussi en " + pNbDeCoups + " coups\n" +
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
	"L'ordinateur n'a pas trouvé la bonne réponse!\n" +
	"Dans le nombre de coup imparti qui était de " + pNbCoupMax +
	"\n----------------La solution était : " + renvoiLeResultatEnString(pNbreProposeParUtilisateur) + "----------------" +
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
 * @return renvoi la combinaison finale en chaine de caractère pour les tableaux
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
 * @return renvoi la combinaison finale en chaine de caractère pour les listes
 */
public static String renvoiLeResultatEnString(ArrayList<Integer> pNbreProposeParUtilisateur) {
	String resultat = " ";
		for(int i = 0;i < pNbreProposeParUtilisateur.size();i++) {
			resultat = resultat + Integer.toString(pNbreProposeParUtilisateur.get(i));
		}
	return resultat;
}


}
