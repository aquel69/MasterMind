package fr.alex.test;

import static org.junit.Assert.assertTrue;


import org.junit.jupiter.api.Test;

import fr.alex.main.VerificationEtFonctionnement;

class MasterTest {

	@Test
	void Given_ALetter_When_userSeizure_Then_ReturnTrue() {
		VerificationEtFonctionnement verification = new VerificationEtFonctionnement();
		String saisie = "a";
		assertTrue("une lettre est entrée en saisie", verification.verifieSiLettre(saisie));
	}
	
	
	@Test
	void Given_ABadPawnColor_When_userSeizure_Then_ReturnTrue() {
		VerificationEtFonctionnement verification = new VerificationEtFonctionnement();
		String saisie = "8888";
		assertTrue("le pion ne correspond pas à la demande",verification.verifieSiSaisieCorrespondAuNombreDeCouleurPropose(saisie));
	}
	
	
	
	
	
	
}
