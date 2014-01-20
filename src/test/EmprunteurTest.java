package test;

import static org.junit.Assert.*;

import org.junit.Test;

import utilisateurs.Emprunteur;

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
		fail("Not yet implemented");
	}

	@Test
	public void testEmprunteur() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDureeMaxDEmprunt() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNbrEmprunt() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetNbrEmprunt() {
		fail("Not yet implemented");
	}

	@Test
	public void testIncrementNbrEmprunt() {
		fail("Not yet implemented");
	}

}
