package fr.alex.main;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CorpsPrincipal {
	protected static Logger logger = LogManager.getLogger(CorpsPrincipal.class.getName());

	String reponse = " ", reponseModeDeveloppeur = " ", clavier = "  ", resultat = " ";
	char carac = ' ';
	String choixModeDeJeu = " ";

	static int nbDeCoupsJoues = 0;

	int nombreModeDeJeu = 0;
	int modeDeveloppeur = 0;

	boolean ordinateurGagne = false;
	boolean utilisateurGagne = false;

	RessourcesMaster ressources = new RessourcesMaster();
	Random random = new Random();
	Combinaison combinaisonMystere = new Combinaison();
	Joueurs joueurs = new Joueurs();
	Utilisateur utilisateur = new Utilisateur();
	Ordinateur ordinateur = new Ordinateur();
	VerificationEtFonctionnement fonctionnement = new VerificationEtFonctionnement();

	public CorpsPrincipal() {

	}

	public void init() {

		do {
			utilisateur.setChoixDuModeDeveloppeur(fonctionnement.saisieUtilisateur("Entrer 1 pour être en mode développeur ou 2 pour être en mode normal : ", "ne tapez pas de lettre, rentrez des chiffres", "veuillez rentrer au moins un chiffre"));
		}while(utilisateur.getChoixDuModeDeveloppeur() < 0 || utilisateur.getChoixDuModeDeveloppeur() > 2 || utilisateur.getChoixDuModeDeveloppeur() == 0);
		
		do {
			presentation();
								
			do {

				utilisateur.setChoixDuModeDeJeux(fonctionnement.saisieUtilisateur("Menu entrez un chiffre 1 à 5",
						"ne tapez pas de lettre, rentrez des chiffres", "veuillez rentrer au moins un chiffre"));
				System.out.println(utilisateur.getChoixDuModeDeveloppeur());
			} while (utilisateur.getChoixDuModeDeJeux() < 0 || utilisateur.getChoixDuModeDeJeux() > 5
					|| utilisateur.getChoixDuModeDeJeux() == 0);

			// switch contenant les Différents jeux
			switch (utilisateur.getChoixDuModeDeJeux()) {

			// mode challenger
			case 1:
				/**
				 * Boucle permettant de recommencer une partie
				 */
				do {
					// reinitialisation de la liste des precedentes propositions et du nb de coup
					// joué pour une nouvelle partie

					combinaisonMystere.getRecapitulatifPrecedentesPropositionsJoueurs().clear();
					nbDeCoupsJoues = 0;

					// initialisation de la combinaison mystère
					combinaisonMystere.generationDuNombreMystere(random, RessourcesMaster.nbDeChiffreCombinaison, utilisateur.getChoixDuModeDeveloppeur());

					
					//Boucle permettant de comparer les valeurs des deux tableaux
					
					do {
						// Boucle permettant de s'assurer du nombre de caractère saisie
						do {

							// saisie d'une proposition par l'utilisateur
							joueurs.setSaisieDUneProposition(fonctionnement.saisieUtilisateur(
									"Veuillez entrer une combinaison à " + RessourcesMaster.nbDeChiffreCombinaison
											+ " chiffres compris entre  1 et " + RessourcesMaster.nbDeCouleur,
									"ne tapez pas de lettre, rentrez des chiffres",
									"veuillez rentrer au moins un chiffre",
									"les pions sont compris entre 1 et " + RessourcesMaster.nbDeCouleur
											+ "\net la combinaison contient " + RessourcesMaster.nbDeChiffreCombinaison
											+ " chiffres",
									"veuillez rentrer le bon nombre de chiffre demandé, a savoir "
											+ RessourcesMaster.nbDeChiffreCombinaison));

						} while (joueurs.getSaisieDUnePropostion().length() != RessourcesMaster.nbDeChiffreCombinaison);

						combinaisonMystere.comparaisonTableauCombinaisonJoueur(combinaisonMystere.getCombinaison(),joueurs.getSaisieDUnePropostion());
						combinaisonMystere.affichageDuResultatEtDesIndices(combinaisonMystere.getRecapitulatifPrecedentesPropositionsJoueurs());
						nbDeCoupsJoues++;

					} while (!combinaisonMystere.getCombinaison().equals(joueurs.getSaisieDUnePropostion())
							&& nbDeCoupsJoues < RessourcesMaster.nbDeCoupMax);

					if (combinaisonMystere.getCombinaison().equals(joueurs.getSaisieDUnePropostion()))
						resultatFinalGagnant(combinaisonMystere.getCombinaison(), nbDeCoupsJoues);
					else
						resultatFinalPerdant(combinaisonMystere.getCombinaison(), nbDeCoupsJoues);

					/**
					 * boucle pour relancer le programme, on entre un 'O' pour recommence et un 'N'
					 * pour quitter le programme demande d'entrer 'O' ou 'N' dans le Scanner
					 */
					do {
						// boucle permettant d'eviter une erreur en s'assurant qu'il n y est qu'un seul
						// caractère
						do {
							clavier = saisieRelancePartie();
						} while (clavier.length() != 1);

						carac = clavier.charAt(0);

					} while (carac != 'O' && carac != 'N' && carac != 'Q');

				} while (carac == 'O');

				if (carac == 'N')
					break;
				else {
					nombreModeDeJeu = 5;
					break;
				}

				// mode defenseur
			case 2:
				// Boucle permettant de recommencer une partie
				do {

					// reinitialisation de la liste des precedentes proposition ordi et du nb de
					// coup joué pour une nouvelle partie
					combinaisonMystere.getRecapitulatifPrecedentesPropositionsOrdi().clear();
					nbDeCoupsJoues = 0;

					// Boucle permettant de s'assurer du nombre de caractère saisie
					do {

						// saisie de la combinaison mystère par l'utilisateur
						joueurs.setSaisieDeLaCombinaisonMystere(fonctionnement.saisieUtilisateur(
								"Veuillez entrer une combinaison mystère à " + RessourcesMaster.nbDeChiffreCombinaison
										+ " chiffres compris entre  1 et " + RessourcesMaster.nbDeCouleur,
								"ne tapez pas de lettre, rentrez des chiffres", "veuillez rentrer au moins un chiffre",
								"les pions sont compris entre 1 et " + RessourcesMaster.nbDeCouleur
										+ "\net la combinaison contient " + RessourcesMaster.nbDeChiffreCombinaison
										+ " chiffres",
								"veuillez rentrer le bon nombre de chiffre demandé, a savoir "
										+ RessourcesMaster.nbDeChiffreCombinaison));

					} while (joueurs.getSaisieDeLaCombinaisonMystere().length() != RessourcesMaster.nbDeChiffreCombinaison);

					// boucle permettant de comparer les 2 combinaisons
					do {

						ordinateur.setPropositionOrdinateur(ordinateur.propositionDeLOrdinateurEnFonctionDesReponse(random, RessourcesMaster.nbDeChiffreCombinaison));
						combinaisonMystere.comparaisonTableauCombinaisonOrdi(joueurs.getSaisieDeLaCombinaisonMystere(),ordinateur.getPropositionOrdinateur());
						nbDeCoupsJoues++;

					} while (!ordinateur.getPropositionOrdinateur().equals(joueurs.getSaisieDeLaCombinaisonMystere())
							&& nbDeCoupsJoues < RessourcesMaster.nbDeCoupMax);

					combinaisonMystere.affichageDuResultatEtDesIndices(combinaisonMystere.getRecapitulatifPrecedentesPropositionsOrdi());

					if (ordinateur.getPropositionOrdinateur().equals(joueurs.getSaisieDeLaCombinaisonMystere()))
						resultatFinalGagnantOrdi(ordinateur.getPropositionOrdinateur(), nbDeCoupsJoues);
					else
						resultatFinalPerdantOrdi(ordinateur.getPropositionOrdinateur(), nbDeCoupsJoues);

					/**
					 * boucle pour relancer le programme, on entre un 'O' pour recommence et un 'N'
					 * pour quitter le programme demande d'entrer 'O' ou 'N' dans le Scanner
					 */
					do {
						// boucle permettant d'eviter une erreur en s'assurant qu'il n y est qu'un seul caractère

						do {
							clavier = saisieRelancePartie();
						} while (clavier.length() != 1);
						carac = clavier.charAt(0);

					} while (carac != 'O' && carac != 'N' && carac != 'Q');

				} while (carac == 'O');

				if (carac == 'N')
					break;
				else {
					nombreModeDeJeu = 5;
					break;
				}

			case 3:
				do {

					// reinitialisation des deux listes des precedentes propositions et du nb de coup joué pour une nouvelle partie
					combinaisonMystere.getRecapitulatifPrecedentesPropositionsJoueurs().clear();
					combinaisonMystere.getRecapitulatifPrecedentesPropositionsOrdi().clear();
					nbDeCoupsJoues = 0;

					// initialisation de la combinaison mystère
					combinaisonMystere.generationDuNombreMystere(random, RessourcesMaster.nbDeChiffreCombinaison, utilisateur.getChoixDuModeDeveloppeur());

					// Boucle permettant de comparer les listes pour le résultat final
					do {
						// Boucle permettant de s'assurer du nombre de caractère saisie
						do {

							// saisie d'une proposition par l'utilisateur
							joueurs.setSaisieDUneProposition(fonctionnement.saisieUtilisateur(
									"Veuillez entrer une combinaison à " + RessourcesMaster.nbDeChiffreCombinaison
											+ " chiffres compris entre  1 et " + RessourcesMaster.nbDeCouleur,
									"ne tapez pas de lettre, rentrez des chiffres",
									"veuillez rentrer au moins un chiffre",
									"les pions sont compris entre 1 et " + RessourcesMaster.nbDeCouleur
											+ "\net la combinaison contient " + RessourcesMaster.nbDeChiffreCombinaison
											+ " chiffres",
									"veuillez rentrer le bon nombre de chiffre demandé, a savoir "
											+ RessourcesMaster.nbDeChiffreCombinaison));

						} while (joueurs.getSaisieDUnePropostion().length() != RessourcesMaster.nbDeChiffreCombinaison);
						combinaisonMystere.comparaisonTableauCombinaisonJoueur(combinaisonMystere.getCombinaison(),	joueurs.getSaisieDUnePropostion());
						combinaisonMystere.affichageDuResultatEtDesIndices(combinaisonMystere.getRecapitulatifPrecedentesPropositionsJoueurs());

						ordinateur.setPropositionOrdinateur(ordinateur.propositionDeLOrdinateurEnFonctionDesReponse(random, RessourcesMaster.nbDeChiffreCombinaison));

						combinaisonMystere.comparaisonTableauCombinaisonOrdi(combinaisonMystere.getCombinaison(),ordinateur.getPropositionOrdinateur());
						combinaisonMystere.affichageDuResultatEtDesIndices(combinaisonMystere.getRecapitulatifPrecedentesPropositionsOrdi());

						nbDeCoupsJoues++;

					} while (!combinaisonMystere.getCombinaison().equals(ordinateur.getPropositionOrdinateur())
							&& !combinaisonMystere.getCombinaison().equals(joueurs.getSaisieDUnePropostion())
							&& nbDeCoupsJoues < RessourcesMaster.nbDeCoupMax);

					if (nbDeCoupsJoues <= RessourcesMaster.nbDeCoupMax)
						if (combinaisonMystere.getCombinaison().equals(ordinateur.getPropositionOrdinateur())
								&& combinaisonMystere.getCombinaison().equals(joueurs.getSaisieDUnePropostion()))
							resultatsEgauxEntreUtilisateurEtOrdinateur();
						else if (combinaisonMystere.getCombinaison().equals(joueurs.getSaisieDUnePropostion()))
							resultatFinalGagnant(combinaisonMystere.getCombinaison(), nbDeCoupsJoues);
						else
							resultatFinalGagnantOrdi(ordinateur.getPropositionOrdinateur(), nbDeCoupsJoues);
					else
						JOptionPane.showMessageDialog(null, "nb de coup dépassé pour les deux");

					/**
					 * boucle pour relancer le programme, on entre un 'O' pour recommence et un 'N'
					 * pour quitter le programme demande d'entrer 'O' ou 'N' dans le Scanner
					 */
					do {

						// boucle permettant d'eviter une erreur en s'assurant qu'il n y est qu'un seul
						// caractère
						do {
							clavier = saisieRelancePartie();
						} while (clavier.length() != 1);
						carac = clavier.charAt(0);

					} while (carac != 'O' && carac != 'N' && carac != 'Q');

				} while (carac == 'O');

				if (carac == 'N')
					break;
				else {
					nombreModeDeJeu = 5;
					break;
				}

			case 4:
				regleDuJeu();
			}

		} while (nombreModeDeJeu != 5);
		// JOptionPane.showConfirmDialog(null, "Voulez vous quitter ?");
		JOptionPane.showMessageDialog(null, "Merci d'avoir joué!!");
	}

	/**
	 * affichage du menu
	 */
	public static void presentation() {
		JOptionPane.showMessageDialog(null,
		"---------------------------------------------------\n" + "---------- Jeu Master Mind ----------\n"
		+ "---------------------------------------------------\n" + "1 - Mode Challenger\n"
		+ "2 - Mode Defenseur\n" + "3 - Mode Duel\n" + "4 - Règles et aides\n" + "5 - Quitter le jeu\n"
		+ "---------------------------------------------------");
	}
	
	/**
	 * affichage des regles
	 */
	public static void regleDuJeu() {
		JOptionPane.showMessageDialog(null,
		"----------Régles du Jeu---------\n" + "1/ Choisir un mode de jeu\n" + "2/ Suivre les instructions\n"
		+ "3/ Le but étant de proposer une combinaison de chiffres\n"
		+ "   et de trouver la combinaison secrète\n" + "   dans le nombre de coup imparti\n"
		+ "   en se servant des indices donnés\n" + "---------------------------------------");
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
	 * @return la réponse fournie par l'utilisateur pour redémarrer ou non, une
	 *         partie
	 */
	public static String saisieRelancePartie() {

		String clavier = JOptionPane.showInputDialog(
				"Voulez vous faire une autre partie ?\n(O pour 'oui' / N pour 'non' / Q pour 'Quitter')");

		return clavier;
	}

	/**
	 * 
	 * @param pListeDesChiffresUtilisateur
	 * @param pListeDeLaCombinaisonMystere
	 * @return un boolean qui indique si l'utilisateur a réussi dans le mode 3
	 */
	public static boolean SavoirSiLUtilisateurAGagne(ArrayList<Integer> pListeDesChiffresUtilisateur,
			ArrayList<Integer> pListeDeLaCombinaisonMystere) {
		boolean reponse = false;
		if (pListeDesChiffresUtilisateur.equals(pListeDeLaCombinaisonMystere)) {
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
	public static boolean SavoirSiLOrdinateurAGagne(ArrayList<Integer> pListeDesChiffresOrdinateur,
			ArrayList<Integer> pListeDeLaCombinaisonMystere) {
		boolean reponse = false;
		if (pListeDesChiffresOrdinateur.equals(pListeDeLaCombinaisonMystere)) {
			reponse = true;
		}
		return reponse;
	}

	/**
	 * 
	 * @param pNbreRecherche
	 * @param pNbDeCoups     affichage du résultat trouvé et du nombre de coup qu'il a fallu à l'utilisateur pour y arriver
	 */
	public static void resultatFinalGagnant(String pResultat, int pNbDeCoups) {
		JOptionPane.showMessageDialog(null,
		"-----------------------------------------\n" + "Vous avez trouvé la réponse est bien " + pResultat
		+ "\nVous l'avez réussi en " + pNbDeCoups + " coups\n"
		+ "-----------------------------------------");
	}

	/**
	 * 
	 * @param pNbrePropose
	 * @param pNbDeCoups
	 */
	public static void resultatFinalGagnant(ArrayList<Integer> pNbrePropose, int pNbDeCoups) {
		JOptionPane.showMessageDialog(null,
		"-----------------------------------------\n" + "Vous avez trouvé la réponse est bien "
		+ renvoiLeResultatEnString(pNbrePropose) + "\nVous l'avez réussi en " + pNbDeCoups + " coups\n"
		+ "-----------------------------------------");
	}

	/**
	 * 
	 * @param pNbrePropose
	 * @param pNbCoupJoue
	 */
	public static void resultatFinalPerdant(String pResultat, int pNbCoupJoue) {
		JOptionPane.showMessageDialog(null, "---------------------------------------------------------------\n"
		+ "--Vous n'avez pas trouvé la bonne réponse!--\n" + "Dans le nombre de coup imparti qui était de "
		+ pNbCoupJoue + "\n----------------La solution était : " + pResultat + "----------------"
		+ "\n---------------------------------------------------------------");
	}

	/**
	 * 
	 * @param pNbreProposeParUtilisateur
	 * @param pNbDeCoups
	 */
	public static void resultatFinalGagnantOrdi(String pNbreProposeParUtilisateur, int pNbDeCoups) {
		JOptionPane.showMessageDialog(null,
		"-----------------------------------------------------------\n"
		+ "      l'ordinateur a bien trouvé la solution\n                           qui est : "
		+ pNbreProposeParUtilisateur + "\n                  Il a réussi en " + pNbDeCoups + " coups\n"
		+ "-----------------------------------------------------------");
	}

	/**
	 * 
	 * @param pNbreProposeParUtilisateur
	 * @param pNbCoupMax
	 */
	public static void resultatFinalPerdantOrdi(String pNbreProposeParUtilisateur, int pNbCoupMax) {
		JOptionPane.showMessageDialog(null, "---------------------------------------------------------------\n"
		+ "L'ordinateur n'a pas trouvé la bonne réponse!\n" + "Dans le nombre de coup imparti qui était de "
		+ pNbCoupMax + "\n----------------La solution était : " + pNbreProposeParUtilisateur
		+ "----------------" + "\n---------------------------------------------------------------");
	}

	
	public static void resultatsEgauxEntreUtilisateurEtOrdinateur() {
		JOptionPane.showMessageDialog(null,
		"---------------------------------------------------------------\n"
		+ "-----------------------MATCH NUL----------------------\n"
		+ "---------------------------------------------------------------");
	}

	/**
	 * 
	 * @param pNbrePropose
	 * @return renvoi la combinaison finale en chaine de caractère pour les tableaux
	 */
	public static String renvoiLeResultatEnString(int[] pNbrePropose) {
		String resultat = " ";
		for (int i = 0; i < pNbrePropose.length; i++) {
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
		for (int i = 0; i < pNbreProposeParUtilisateur.size(); i++) {
			resultat = resultat + Integer.toString(pNbreProposeParUtilisateur.get(i));
		}
		return resultat;
	}

}
