package fr.alex.main;



public class Utilisateur extends Joueurs {
 
	public static String comparaisonTableau(String pCombinaison, String pReponse) {
		String signe = " ";
		String resultat = " ";
		

		for (int i = 0; i < pReponse.length(); i++) {
			for(int j = 0; j < pCombinaison.length(); j++) {
				 System.out.println("reponse : " + pReponse.charAt(i));
					System.out.println("Combinaison : " + pCombinaison.charAt(j));

				 if (pReponse.charAt(i) == pCombinaison.charAt(i)) {
					signe = "=";
					break;
				}else if (pReponse.charAt(i) == pCombinaison.charAt(j)){
					signe = "+";
					break;
				}else {
					signe = "-";
				}
						
			}
			 resultat = resultat + signe;
		}			
		 
		return resultat;
	}
	
	
}
