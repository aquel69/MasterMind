package fr.alex.main;

public class MainMaster {
	
	
	public static void main(String[] args) {
		CorpsPrincipal corps = new CorpsPrincipal();
		RecherchePlusMoins recherche = new RecherchePlusMoins();
		VerificationEtFonctionnement verification = new VerificationEtFonctionnement();
		Utilisateur utilisateur = new Utilisateur();	
		
		
		do {
			utilisateur.setChoixDuJeu(verification.saisieUtilisateur("Entrer :\n1 pour jouer au Recherche +/-\n2 pour jouer au MasterMind : ", "ne tapez pas de lettre, rentrez des chiffres", "veuillez rentrer au moins un chiffre"));
		}while(utilisateur.getChoixDuJeu() < 0 || utilisateur.getChoixDuJeu() > 2 || utilisateur.getChoixDuJeu() == 0);
		
		if(utilisateur.getChoixDuJeu()==1)
			recherche.init();
		else corps.init();
	}

}
