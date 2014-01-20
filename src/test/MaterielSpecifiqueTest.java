package test;

import static org.junit.Assert.*;

import org.junit.Test;

import Materiel.MaterielSpecifique;

public class MaterielSpecifiqueTest {

	@Test
	public void testMaterielSpecifique() {
		String expectedNomType = "Telescope dernier cri";
		int expectedNbrExemplaire = 5;
		int expectedDureeMax = 30;
		MaterielSpecifique mat = new MaterielSpecifique();
		if (mat.getNom() != expectedNomType)
			fail ("mauvais nom");
		if (mat.getNombre() != expectedNbrExemplaire)
			fail ("mauvais nombre d'exemplaire");
		if (mat.getDureeMaxEmprunt() != expectedDureeMax)
			fail ("mauvais duree max d'emprunt");
	}

	@Test
	public void testMaterielSpecifiqueStringInt() {
		String expectedNomType = "Fragile";
		int expectedNombreExemplaire = 3;
		int expectedDureeMax = 30;
		MaterielSpecifique mat = new MaterielSpecifique (expectedNomType, expectedNombreExemplaire);
		if (mat.getNom() != expectedNomType)
			fail("mauvais nom");
		if (mat.getNombre() != expectedNombreExemplaire)
			fail("mauvais nombre d'exemplaire");
		if (mat.getDureeMaxEmprunt() != expectedDureeMax)
			fail("mauvaise duree max d'emprunt");
	}

}
