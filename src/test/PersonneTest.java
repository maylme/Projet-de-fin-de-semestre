package test;

import static org.junit.Assert.*;

import org.junit.Test;

import utilisateurs.Personne;

public class PersonneTest {

	@Test
	public void testPersonneStringString() {
		String prenom = "toto";
		String nom = "tata";
		Personne p = new Personne (nom, prenom);
		if (p.getNom() != nom)
			fail ("mauvais nom");
		if (p.getPrenom() != prenom)
			fail ("mauvais prenom");
			
	}

	@Test
	public void testPersonne() {
		String expectedPrenom = "b";
		String expectedNom = "a";
		Personne p = new Personne ();
		if (p.getNom() != expectedNom)
			fail ("mauvais nom");
		if (p.getPrenom() != expectedPrenom)
			fail ("mauvais prenom");
	}

	@Test
	public void testGetNom() {
		String prenom = "toto";
		String nom = "tata";
		Personne p = new Personne (nom, prenom);
		if (p.getNom() != nom)
			fail ("mauvais nom");
		nom = null;
		p = new Personne (nom, prenom);
		if (p.getNom() != null)
			fail ("nom null");		
	}

	@Test
	public void testGetPrenom() {
		fail("Not yet implemented");
	}

	@Test
	public void testEqualsPersonne() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

}
