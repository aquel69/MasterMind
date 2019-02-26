package fr.alex.main;

public class  Joueurs extends Utilisateur{
	private String saisieDUneProposition;
	private String saisieDeLaCombinaisonMystere;
		
	public Joueurs() {
		
	}
		
	public String getSaisieDUnePropostion() {
		return saisieDUneProposition;
	}

	public void setSaisieDUneProposition(String saisieDUneProposition) {
		this.saisieDUneProposition = saisieDUneProposition;
	}
	
	public String getSaisieDeLaCombinaisonMystere() {
		return saisieDeLaCombinaisonMystere;
	}

	public void setSaisieDeLaCombinaisonMystere(String saisieDeLaCombinaisonMystere) {
		this.saisieDeLaCombinaisonMystere = saisieDeLaCombinaisonMystere;
	}

	
}
