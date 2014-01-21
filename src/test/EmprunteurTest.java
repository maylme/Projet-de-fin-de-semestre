package test;

import static org.junit.Assert.*;

import org.junit.Test;

import utilisateurs.Emprunteur;
import utilisateurs.Personne;

public class EmprunteurTest {

	@Test
	public void testEmprunteurStringStringInt() {
		//mode courant
		String prenom = "Twilight";
		String nom = "Sparkle";
		int duree = 1;
		Emprunteur e = new Emprunteur (nom, prenom, duree);
		if (!e.getNom().equals(nom))
			fail("nom");
		if (!e.getPrenom().equals(prenom))
			fail("prenom");
		if (e.getDureeMaxDEmprunt() != duree)
			fail("duree");
		
		//mode avec nom null
		nom = null;
		e = new Emprunteur (nom, prenom, duree);
		if (!(e.getNom()==nom))
			fail("nom null");
		if (!(e.getPrenom()==prenom))
			fail("prenom");
		if (e.getDureeMaxDEmprunt() != duree)
			fail("duree");
		
		//mode avec prenom null
		prenom = null;
		nom = "Sparkle";
		e = new Emprunteur (nom, prenom, duree);
		if (!e.getNom().equals(nom))
			fail("nom");
		if (!(e.getPrenom()==prenom))
			fail("prenom null");
		if (e.getDureeMaxDEmprunt() != duree)
			fail("duree");
		
		//mode avec prenom et nom null
		prenom = null;
		nom = null;
		e = new Emprunteur (nom, prenom, duree);
		if (!(e.getNom()==nom))
			fail("nom null");
		if (!(e.getPrenom()==prenom))
			fail("prenom null");
		if (e.getDureeMaxDEmprunt() != duree)
			fail("duree");

	}

	@Test
	public void testEmprunteurPersonneInt() {
		String prenom = "Apple";
		String nom = "Jack";
		int duree = 1;
		Personne p = new Personne (nom, prenom);
		Emprunteur e = new Emprunteur(p, duree);
		if (e.getDureeMaxDEmprunt() != duree)
			fail("duree");
	}

	@Test
	public void testEmprunteur() {
		String expectedPrenom = "b";
		String expectedNom = "a";
		int expectedDuree = 15;
		Emprunteur e  = new Emprunteur ();
		if (!e.getNom().equals(expectedNom))
			fail("nom");
		if (!e.getPrenom().equals(expectedPrenom))
			fail("prenom");
		if (e.getDureeMaxDEmprunt() != expectedDuree)
			fail("duree");
	}

	@Test
	public void testGetDureeMaxDEmprunt() {
		Emprunteur e  = new Emprunteur ();
		if (e.getDureeMaxDEmprunt() != 15)
			fail("duree");
	}

	@Test
	public void testGetNbrEmprunt() {
		Emprunteur e  = new Emprunteur ();
		if (e.getNbrEmprunt() != 15)
			fail("nb emprunt");
	}

	@Test
	public void testSetNbrEmprunt() {
		Emprunteur e  = new Emprunteur ();
		int expectedResult = 17;
		e.setNbrEmprunt(17);
		if ( 17 != e.getNbrEmprunt())
			fail("nb emprunt");
	}

	@Test
	public void testIncrementNbrEmprunt() {
		fail("Not yet implemented");
	}

}
