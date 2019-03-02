package fr.alex.main;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class RecherchePlusMoins  {
		
	private static Logger logger = LogManager.getLogger(Combinaison.class.getName());
	
		
		/**
		 * d�claration des variables du programme
		 */
		//Nombre nombrePropose = new Nombre();

		String reponse = " ", reponseModeDeveloppeur = " ",  clavier = "  ",resultat = " ";
		char carac = ' ';
		String choixModeDeJeu = " ";

		int nbreRecherche[] = null;
		int nbrePropose[] = null;

		int nbDeCoupsJoues = 0;
		int nombreModeDeJeu = 0;
		int modeDeveloppeur = 0;
		
		boolean ordinateurGagne = false;
		boolean utilisateurGagne = false;

		ArrayList<Integer> listeSauvegardeCombinaisonOrdi = new ArrayList<Integer>();
		ArrayList<Integer> listeDesChiffresProposeParOrdinateur = new ArrayList<Integer>();
		ArrayList<Integer> listeDesChiffresEntreParUtilisateur = new ArrayList<Integer>();
		ArrayList<Integer> listeDesPrecedentsChiffreProposeParOrdinateur = new ArrayList<Integer>();
		ArrayList<Integer> listeDesChiffresMysteresEntreParLOrdinateur = new ArrayList<Integer>();


		/** d�claration des classes
		 */
		Random random= new Random();
		//RessourcesRecherche ressources = new RessourcesRecherche();
		//Fenetre fenetre = new Fenetre();
		
		//choix du mode d�veloppeur ou normal
		
		public RecherchePlusMoins(){
			
		}
		
	public void init() {
		do {
			reponseModeDeveloppeur = verificationSaisieUtilisateurMultiple("Entrer 1 pour �tre en mode d�veloppeur ou 2 pour �tre en mode normal : ", "ne tapez pas de lettre, rentrez des chiffres", "veuillez rentrer au moins un chiffre");
			modeDeveloppeur = Integer.parseInt(reponseModeDeveloppeur);
		}while(modeDeveloppeur < 0 || modeDeveloppeur > 2 || modeDeveloppeur == 0);
		
		do {
			presentation();
			do {
				do {
					choixModeDeJeu = verificationSaisieUtilisateurMultiple("Menu entrez un chiffre 1 � 5", "ne tapez pas de lettre, rentrez des chiffres", "veuillez rentrer au moins un chiffre");
				}while(choixModeDeJeu == "");
					nombreModeDeJeu = Integer.parseInt(choixModeDeJeu);
			}while(nombreModeDeJeu < 0 || nombreModeDeJeu > 5 || nombreModeDeJeu == 0);

			//switch contenant les Diff�rents modes de jeux
			switch(nombreModeDeJeu) {

			//mode challenger
			case 1 :
			/** Boucle permettant de recommencer une partie
			 */
			do {
				nbreRecherche = nombreMystere(random,RessourcesMaster.nbDeChiffreCombinaison, modeDeveloppeur);
				nbDeCoupsJoues = 0;
				
				/** Boucle permettant de comparer les valeurs des deux tableaux
				 */
				do {
					/**Boucle permettant de s'assurer du nombre de caract�re saisie					
					 */
					do {

						reponse = verificationSaisieUtilisateurMultiple("Veuillez entrer une combinaison � "+ RessourcesMaster.nbDeChiffreCombinaison +  " chiffres", "ne tapez pas de lettre, rentrez des chiffres", "veuillez rentrer au moins un chiffre");

					}while (reponse.length() != RessourcesMaster.nbDeChiffreCombinaison);

					//la chaine de caract�re entr�e par l'utilisateur puis convertie en entier dans un tableau plac�e dans la variable nbrePropose
					nbrePropose = conversionChaineEnEntier(reponse, RessourcesMaster.nbDeChiffreCombinaison);
					resultat = comparaisonTableau(nbrePropose, nbreRecherche);
					affichageDuResultatEtDesIndices(reponse, resultat);

					//ajout � chaque tour d'un coup jou�
					nbDeCoupsJoues++;

				}while(!Arrays.equals(nbrePropose,nbreRecherche) && nbDeCoupsJoues < RessourcesMaster.nbDeCoupMax);

				if(nbDeCoupsJoues<=RessourcesMaster.nbDeCoupMax)
					resultatFinalGagnant(nbrePropose, nbDeCoupsJoues);
				else resultatFinalPerdant(nbreRecherche, nbDeCoupsJoues);

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
				do {

					//remise � zero des listes et du nombre de coups
					nbDeCoupsJoues = 0;
					listeSauvegardeCombinaisonOrdi.clear();
					listeDesChiffresProposeParOrdinateur.clear();
					listeDesChiffresEntreParUtilisateur.clear();
					listeDesPrecedentsChiffreProposeParOrdinateur.clear();

					//Boucle permettant de s'assurer du nombre de caract�re saisie					
					do {
						reponse = verificationSaisieUtilisateurMultiple("Entrer la combinaison � trouver par l'ordinateur", "ne tapez pas de lettre, rentrez des chiffres", "veuillez rentrer au moins un chiffre");
					}while (reponse.length() != RessourcesMaster.nbDeChiffreCombinaison);
					// conversion de 'reponse' en entier et placer dans une liste 
					listeDesChiffresEntreParUtilisateur = conversionChaineEnEntierEtPlacerDansUneList(reponse, RessourcesMaster.nbDeChiffreCombinaison);

					 //Boucle permettant de comparer les listes pour le r�sultat final
					do {

						listeDesChiffresProposeParOrdinateur = remplissageDeLaListeDesChiffresProposeParLOrdinateur(RessourcesMaster.nbDeChiffreCombinaison, nbDeCoupsJoues, listeDesChiffresProposeParOrdinateur, listeDesPrecedentsChiffreProposeParOrdinateur, listeDesChiffresEntreParUtilisateur, listeSauvegardeCombinaisonOrdi, random);

						listeDesPrecedentsChiffreProposeParOrdinateur = transfertDeLaListeDesChiffresProposesDansLaListeDesChiffresPrecedents(listeDesPrecedentsChiffreProposeParOrdinateur, listeDesChiffresProposeParOrdinateur, nbDeCoupsJoues);

						listeSauvegardeCombinaisonOrdi = ajoutDeLaListeDesChiffresProposesDansLaListeDesSauvegardes(listeSauvegardeCombinaisonOrdi, listeDesChiffresProposeParOrdinateur);

						//ajout � chaque tour d'un coup jou�
						nbDeCoupsJoues++;

					}while(!listeDesChiffresProposeParOrdinateur.equals(listeDesChiffresEntreParUtilisateur) && nbDeCoupsJoues < RessourcesMaster.nbDeCoupMax); 

					if(nbDeCoupsJoues <= RessourcesMaster.nbDeCoupMax) 
						resultatFinalGagnantOrdi(listeDesChiffresEntreParUtilisateur, nbDeCoupsJoues);
					else resultatFinalPerdantOrdi(listeDesChiffresEntreParUtilisateur, RessourcesMaster.nbDeCoupMax);

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

			case 3 :
				//Boucle permettant de recommencer une partie
				do {
					//remise � z�ro des listes et du nb de coup jou� pour une nouvelle partie
					nbDeCoupsJoues = 0;
					listeSauvegardeCombinaisonOrdi.clear();
					listeDesChiffresProposeParOrdinateur.clear();
					listeDesChiffresEntreParUtilisateur.clear();
					listeDesPrecedentsChiffreProposeParOrdinateur.clear();
					listeDesChiffresMysteresEntreParLOrdinateur.clear();

					nbreRecherche = nombreMystere(random,RessourcesMaster.nbDeChiffreCombinaison, modeDeveloppeur);

					for(int i = 0; i < nbreRecherche.length; i++) {
						listeDesChiffresMysteresEntreParLOrdinateur.add(i, nbreRecherche[i]);
						
					}
					
					//Boucle permettant de comparer les listes pour le r�sultat final
					do {
						//Boucle permettant de s'assurer du nombre de caract�re saisie
						do {

							reponse = verificationSaisieUtilisateurMultiple("Veuillez entrer une combinaison � "+ RessourcesMaster.nbDeChiffreCombinaison +  " chiffres", "ne tapez pas de lettre, rentrez des chiffres", "veuillez rentrer au moins un chiffre");

						}while (reponse.length() != RessourcesMaster.nbDeChiffreCombinaison);
						
						listeDesChiffresEntreParUtilisateur = conversionChaineEnEntierEtPlacerDansUneList(reponse, RessourcesMaster.nbDeChiffreCombinaison);

						resultat =comparaisonTableau(listeDesChiffresEntreParUtilisateur, listeDesChiffresMysteresEntreParLOrdinateur);

						affichageDuResultatEtDesIndices(reponse, resultat);

						utilisateurGagne = SavoirSiLUtilisateurAGagne(listeDesChiffresEntreParUtilisateur, listeDesChiffresMysteresEntreParLOrdinateur);

						listeDesChiffresProposeParOrdinateur = remplissageDeLaListeDesChiffresProposeParLOrdinateur(RessourcesMaster.nbDeChiffreCombinaison, nbDeCoupsJoues, listeDesChiffresProposeParOrdinateur, listeDesPrecedentsChiffreProposeParOrdinateur, listeDesChiffresMysteresEntreParLOrdinateur, listeSauvegardeCombinaisonOrdi, random);

						listeDesPrecedentsChiffreProposeParOrdinateur = transfertDeLaListeDesChiffresProposesDansLaListeDesChiffresPrecedents(listeDesPrecedentsChiffreProposeParOrdinateur, listeDesChiffresProposeParOrdinateur, nbDeCoupsJoues);

						listeSauvegardeCombinaisonOrdi = ajoutDeLaListeDesChiffresProposesDansLaListeDesSauvegardes(listeSauvegardeCombinaisonOrdi, listeDesChiffresProposeParOrdinateur);

						ordinateurGagne = SavoirSiLOrdinateurAGagne(listeDesChiffresProposeParOrdinateur, listeDesChiffresMysteresEntreParLOrdinateur);
						//ajout � chaque tour d'un coup jou�
						nbDeCoupsJoues++;

					}while(!listeDesChiffresProposeParOrdinateur.equals(listeDesChiffresMysteresEntreParLOrdinateur) && !listeDesChiffresEntreParUtilisateur.equals(listeDesChiffresMysteresEntreParLOrdinateur) && nbDeCoupsJoues < RessourcesMaster.nbDeCoupMax); 
					
					if(nbDeCoupsJoues <= RessourcesMaster.nbDeCoupMax) 
						if (utilisateurGagne == true && ordinateurGagne == true) resultatsEgauxEntreUtilisateurEtOrdinateur();
						else if(utilisateurGagne == true) resultatFinalGagnant(listeDesChiffresEntreParUtilisateur, nbDeCoupsJoues);
						else resultatFinalGagnantOrdi(listeDesChiffresMysteresEntreParLOrdinateur, nbDeCoupsJoues);
					else JOptionPane.showMessageDialog(null, "nb de coup d�pass� pour les deux");

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


			case 4 : 
				regleDuJeu();
			}

		}while(nombreModeDeJeu != 5);
	
		JOptionPane.showMessageDialog(null, "Merci d'avoir jou�!!");
	}

	
	/**
	 * m�thode servant � la pr�sentation
	 * @param frame1 
	 * @param frame1 
	 */
	public static void presentation(){
		JOptionPane.showMessageDialog(null, 
		"---------------------------------------------------\n" +
		"---------- Jeu de Recherche +/-----------\n" +
		"---------------------------------------------------\n" +
		"1 - Mode Challenger\n" + 
		"2 - Mode Defenseur\n" +
		"3 - Mode Duel\n" +
		"4 - R�gles et aides\n" + 
		"5 - Quitter le jeu\n" +
		"---------------------------------------------------");
	}
	
	public static void regleDuJeu() {
		JOptionPane.showMessageDialog(null, 
		"----------R�gles du Jeu---------\n" + 
		"1/ Choisir un mode de jeu\n" + 
		"2/ Suivre les instructions\n" + 
		"3/ Le but �tant de proposer une combinaison de chiffres\n" + 
		"   et de trouver la combinaison secr�te\n" +
		"   dans le nombre de coup imparti\n" +
		"   en se servant des indices donn�s\n" + 
		"---------------------------------------");
	}
	
	/**
	 * 	
	 * @param random1
	 * @return Le nombre myst�re g�n�rer au hasard par l'ordinateur
	 */
	public static int[] nombreMystere(Random random1, int pNbCombinaison,int pModeDeveloppeur) {
		int nbProp[] = new int[pNbCombinaison];
		String NbsEntier = "";
		
		try {
			for (int i = 0; i < nbProp.length; i++) {
				int pNb = random1.nextInt(10);
				nbProp[i] = pNb;
				String Nbs = String.valueOf(pNb);
				NbsEntier = NbsEntier + Nbs;
			}
	
			if (pModeDeveloppeur == 1)
				JOptionPane.showMessageDialog(null, "la solution est : " + NbsEntier);

		}catch(Exception e)
		{logger.error("erreur sur le nombre mystere", e);}
		return nbProp;
	}	


	/**
	 * 
	 * @param p1Demande
	 * @param p2Reponse
	 * @return le nombre entré par l'utilisateur et v�rifie si les caract�res entr�s sont des chiffres ou des lettres ou si il n'y a rien
	 */
	public static String verificationSaisieUtilisateurMultiple(String p1Demande,String p2Reponse, String p3Reponse) {
		String reponse;
		boolean verite, verite2;
		do{
			reponse = JOptionPane.showInputDialog(p1Demande);
			//verifie si lettre ou chiffre
			verite = verifierSiLettreOuNombre(reponse);
			//verifie si aucun caract�re n'est rentr�
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
	 * @return un booleen True si une lettre est rentr�e par l'utilisateur
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
	public static String comparaisonTableau(int pNbrePropose[], int pNbreRecherche[]) {
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
	}


	/**
	 * 
	 * @param pListeDesChiffresUtilisateurs
	 * @param pListeDeLaCombinaisonOrdinateur
	 * @return comparaison des deux listes et retourne un caractere indiquant le + ou - ou = suivant le resultat
	 */
	public static String comparaisonTableau(ArrayList<Integer> pListeDesChiffresUtilisateurs, ArrayList<Integer> pListeDeLaCombinaisonOrdinateur) {
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
						// comparer la liste pr�cedente avec la combinaison a trouver.
						if (pListeDesPrecedentsChiffreProposeParOrdinateur.get(i) > pListeDesChiffresEntreParUtilisateur.get(i)) {
							//boucle permettant que tant que le chiffre al�atoire choisi n'est pas diff�rent que ceux d�ja propos�; recommence du d�but et parcour la liste compl�te en ajoutant avec 'j' le nombre correspondant à touts les chiffres déja joués.
							//On sort lorsque le tableau est completement parcouru et qu'aucun chiffre sauvegard� n'est similaire au propos�
							if (pListeDesPrecedentsChiffreProposeParOrdinateur.get(i) <= 5 ) {
								pListeDesChiffresProposeParOrdinateur.set(i, pRandom.nextInt(pListeDesPrecedentsChiffreProposeParOrdinateur.get(i)));
								do{
									//comparaison du chiffre al�atoire proposé et de la liste sauvegard�e
									if (pListeDesChiffresProposeParOrdinateur.get(i) == pListeSauvegardeCombinaisonOrdi.get(j)/**|| listeDesChiffresProposeParOrdinateur.get(i) > nombreMaximum*/) {

										//nouveau chiffre al�atoire et pour recommencer du d�but et reprendre à l'index i	
										pListeDesChiffresProposeParOrdinateur.set(i, pRandom.nextInt(pListeDesPrecedentsChiffreProposeParOrdinateur.get(i)));

									//sinon ajouter 4 pour comparer avec le prochain	
									}else  j+=pNombreDeChiffreCombinaison;
								}while( j < pListeSauvegardeCombinaisonOrdi.size());

							}else if(pListeDesPrecedentsChiffreProposeParOrdinateur.get(i) >= 5 && pListeDesChiffresEntreParUtilisateur.get(i) > 5){
								pListeDesChiffresProposeParOrdinateur.set(i, pRandom.nextInt(pListeDesPrecedentsChiffreProposeParOrdinateur.get(i) - 6) + 6);
								do{
									if (pListeDesChiffresProposeParOrdinateur.get(i) == pListeSauvegardeCombinaisonOrdi.get(j)/**|| listeDesChiffresProposeParOrdinateur.get(i) > nombreMaximum*/) {
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
									if (pListeDesChiffresProposeParOrdinateur.get(i) == pListeSauvegardeCombinaisonOrdi.get(j)/**|| listeDesChiffresProposeParOrdinateur.get(i) > nombreMaximum*/) {
									//nouveau chiffre al�atoire et pour recommencer du d�but et reprendre à l'index i	
										pListeDesChiffresProposeParOrdinateur.set(i, pRandom.nextInt(10 - pListeDesPrecedentsChiffreProposeParOrdinateur.get(i)) + pListeDesPrecedentsChiffreProposeParOrdinateur.get(i));
										j=i;
									//sinon ajouter 4 pour comparer avec le prochain	
									}else  j+=pNombreDeChiffreCombinaison;
								}while( j < pListeSauvegardeCombinaisonOrdi.size());
							}else{
								pListeDesChiffresProposeParOrdinateur.set(i, pRandom.nextInt(5 - pListeDesPrecedentsChiffreProposeParOrdinateur.get(i)) + pListeDesPrecedentsChiffreProposeParOrdinateur.get(i));
								do{
									if (pListeDesChiffresProposeParOrdinateur.get(i) == pListeSauvegardeCombinaisonOrdi.get(j)/**|| listeDesChiffresProposeParOrdinateur.get(i) > nombreMaximum*/) {
										pListeDesChiffresProposeParOrdinateur.set(i, pRandom.nextInt(5 - pListeDesPrecedentsChiffreProposeParOrdinateur.get(i)) + pListeDesPrecedentsChiffreProposeParOrdinateur.get(i));
										j=i;
									}else  j+=pNombreDeChiffreCombinaison;
								}while( j < pListeSauvegardeCombinaisonOrdi.size());
							}
					}else {
						pListeDesChiffresProposeParOrdinateur.set(i, pListeDesChiffresProposeParOrdinateur.get(i));
					}
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
	public static void affichageDuResultatEtDesIndices(String pReponse, String pResultat) {
		JOptionPane.showMessageDialog(null, pReponse + "\n" + pResultat);
	}



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
	 * affichage du r�sultat trouv� et du nombre de coup qu'il a fallu à l'utilisateur pour y arriver
	 */
	public static void resultatFinalGagnant(int pNbrePropose [], int pNbDeCoups) {
		JOptionPane.showMessageDialog(null, 
		"-----------------------------------------\n" +
		"Vous avez trouv� la r�ponse est bien " +
		renvoiLeResultatEnString(pNbrePropose)+
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
		public static void resultatFinalPerdant(int pNbrePropose [], int pNbCoupJoue) {
		JOptionPane.showMessageDialog(null, 
		"---------------------------------------------------------------\n" +
		"--Vous n'avez pas trouv� la bonne r�ponse!--\n" +
		"Dans le nombre de coup imparti qui �tait de " + pNbCoupJoue +
		"\n----------------La solution �tait : " + renvoiLeResultatEnString(pNbrePropose) + "----------------" +
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
		"Dans le nombre de coup imparti qui était de " + pNbCoupMax +
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

