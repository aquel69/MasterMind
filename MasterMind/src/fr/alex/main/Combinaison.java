package fr.alex.main;

import java.util.Random;



public class Combinaison {
	
	//private static Logger loggerComb= LogManager.getLogger(Combinaison.class.getName());
	
	
	public static String nombreMystere(Random random1, int pNB_COMBINAISON) {
		String combinaison = "";
		char chiffre = ' ';
		//try {
				for (int i = 0; i < pNB_COMBINAISON; i++) {
				int pNb = random1.nextInt(10);
				chiffre = Integer.toString(pNb).charAt(0);	
				System.out.println(chiffre);
				combinaison = combinaison + chiffre;
			}
			
		//}catch(Exception e)
		//{Logger.error("erreur sur le nombre mystere", e);}
		return combinaison;
	}	

}
