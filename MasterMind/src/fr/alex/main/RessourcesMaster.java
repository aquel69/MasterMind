package fr.alex.main;

import java.util.ResourceBundle;

public class RessourcesMaster {
	protected final int NB_DE_CHIFFRE_COMBINAISON;
	protected final int NB_DE_COUP_MAX;
	protected String nombreDeChiffreCombinaisonString;
	protected String nb_de_coup_max_string;
	
	
	
	public RessourcesMaster(){
	ResourceBundle bundle = ResourceBundle.getBundle("config");
	
		
	nombreDeChiffreCombinaisonString = bundle.getString("recherche.nbDeChiffreCombinaison");
	NB_DE_CHIFFRE_COMBINAISON = Integer.parseInt(nombreDeChiffreCombinaisonString);

	nb_de_coup_max_string = bundle.getString("recherche.nbDeCoupMax");
	NB_DE_COUP_MAX = Integer.parseInt(nb_de_coup_max_string);

	}

	public int getNB_DE_CHIFFRE_COMBINAISON() {
		return NB_DE_CHIFFRE_COMBINAISON;
	}

	public int getNB_DE_COUP_MAX() {
		return NB_DE_COUP_MAX;
	}
	
}
