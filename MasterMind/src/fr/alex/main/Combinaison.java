package fr.alex.main;

import java.util.Random;



public class Combinaison {
	
	//private static Logger loggerComb= LogManager.getLogger(Combinaison.class.getName());
	private String combinaison = "";
	
	public Combinaison() {
		
	}
	
	public void nombreMystere(Random random1, int pNB_COMBINAISON) {
		
		char chiffre = ' ';
		//try {
				for (int i = 0; i < pNB_COMBINAISON; i++) {
				int pNb = random1.nextInt(10);
				chiffre = Integer.toString(pNb).charAt(0);	
				System.out.println(chiffre);
				this.combinaison = this.combinaison + chiffre;
			}
			
		//}catch(Exception e)
		//{Logger.error("erreur sur le nombre mystere", e);}
		setCombinaison(this.combinaison);
	}

	public String getCombinaison() {
		return combinaison;
	}

	public void setCombinaison(String combinaison) {
		this.combinaison = combinaison;
	}	

}
