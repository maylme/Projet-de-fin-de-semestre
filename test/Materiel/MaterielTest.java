package Materiel;

import static org.junit.Assert.*;

import org.junit.Test;

import Materiel.Materiel;

public class MaterielTest {

	@Test
	public void testMateriel() {
		String expectedNomType = "Tablette Android";
		int expectedNbrExemplaire = 20;
		int expectedDureeMax = 15;
		Materiel mat = new Materiel();
		if (mat.getNom() != expectedNomType)
			fail ("mauvais nom");
		if (mat.getNombre() != expectedNbrExemplaire)
			fail ("mauvais nombre d'exemplaire");
		if (mat.getDureeMaxEmprunt() != expectedDureeMax)
			fail ("mauvais duree max d'emprunt");
	}

	@Test
	public void testMaterielStringInt() {
		String expectedNomType = "Chocolat";
		int expectedNombreExemplaire = 30;
		int expectedDureeMax = 15;
		Materiel mat = new Materiel (expectedNomType, expectedNombreExemplaire);
		if (mat.getNom() != expectedNomType)
			fail("mauvais nom");
		if (mat.getNombre() != expectedNombreExemplaire)
			fail("mauvais nombre d'exemplaire");
		if (mat.getDureeMaxEmprunt() != expectedDureeMax)
			fail("mauvaise duree max d'emprunt");
	}

	@Test
	public void testToString() {
		Materiel mat = new Materiel();
		String expectedString = "\nType de matériel : Tablette Android\nNombre de matériel de ce type : 20" ;
		if (!mat.toString().equals(expectedString))
			fail("chaine de caractere different");
	}

	@Test
	public void testGetNom() {
		Materiel mat = new Materiel(); 
		if (mat.getNom() != "Tablette Android")
			fail("mauvais nom");
		String nom = null;
		Materiel mat2 = new Materiel (nom, 2);
		if (mat2.getNom() != null)
			fail ("mauvais nom, different de null");
	}

	@Test
	public void testGetNombre() {
		Materiel mat = new Materiel(); 
		if (mat.getNombre() != 20)
			fail("mauvais nombre d'exemplaire");
		mat = new Materiel("machin", 30);
		if (mat.getNombre() != 30)
			fail("mauvais nombre d'exemplaire");
	}

	@Test
	public void testSetNombre() {
		int expectedNombreExemplaire = 10;
		Materiel mat = new Materiel(); 
		if (mat.getNombre() != 20)
			fail("mauvais nombre d'exemplaire");
		mat.setNombre(expectedNombreExemplaire);
		if (mat.getNombre() != expectedNombreExemplaire)
			fail("mauvais nombre d'exemplaire");
	}

	@Test
	public void testIncrNombre() {
		int expectedNombreExemplaire = 10;
		Materiel mat = new Materiel("machine", 0);
		mat.incrNombre(expectedNombreExemplaire);
		if (mat.getNombre() != expectedNombreExemplaire)
			fail("mauvais nombre d'exemplaire");
	}

	@Test
	public void testDecrNombre() {
		int expectedNombreExemplaire = 10;
		Materiel mat = new Materiel("machine", 10);
		mat.decrNombre(expectedNombreExemplaire);
		if (mat.getNombre() != 0)
			fail("mauvais nombre d'exemplaire");
	}

}
