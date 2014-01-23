package Materiel;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import utilisateurs.Emprunteur;

import Materiel.Materiel;
import Materiel.MaterielEmprunte;

public class MaterielEmprunteTest {

	@Test
	public void testMaterielEmprunteMaterielEmprunteurDateInt() {
		Materiel expectedMat = new Materiel("mat", 20);
		Emprunteur expectedEmprunteur = new Emprunteur();
		Date expectedDate = new Date();
		int expectedDureeEmprunt = 4;
		MaterielEmprunte mat = new MaterielEmprunte(expectedMat, expectedEmprunteur, expectedDate, expectedDureeEmprunt);
		if (mat.getMatEmprunt() != expectedMat)
			fail ("mauvais materiel");
		if (mat.getEmprunteur() != expectedEmprunteur)
			fail ("mauvais emprunteur");
		if (mat.getDateEmprunt() != expectedDate)
			fail ("mauvais date d'emprunt");
		if (mat.getDureeEmprunt() != expectedDureeEmprunt)
			fail ("mauvais duree d'emprunt");
		
		//materiel null
		Materiel expectedMat1 = null;
		MaterielEmprunte mat1 = new MaterielEmprunte(expectedMat1, expectedEmprunteur, expectedDate, expectedDureeEmprunt);
		if (mat1.getMatEmprunt() != expectedMat1)
			fail ("mauvais materiel");
		if (mat1.getEmprunteur() != expectedEmprunteur)
			fail ("mauvais emprunteur");
		if (mat1.getDateEmprunt() != expectedDate)
			fail ("mauvais date d'emprunt");
		if (mat1.getDureeEmprunt() != expectedDureeEmprunt)
			fail ("mauvais duree d'emprunt");
		
		//emprunteur null
		Emprunteur expectedEmprunteur2 = null;
		MaterielEmprunte mat2 = new MaterielEmprunte(expectedMat, expectedEmprunteur2, expectedDate, expectedDureeEmprunt);
		if (mat2.getMatEmprunt() != expectedMat)
			fail ("mauvais materiel");
		if (mat2.getEmprunteur() != expectedEmprunteur2)
			fail ("mauvais emprunteur");
		if (mat2.getDateEmprunt() != expectedDate)
			fail ("mauvais date d'emprunt");
		if (mat2.getDureeEmprunt() != expectedDureeEmprunt)
			fail ("mauvais duree d'emprunt");
		
		//date null
		Date expectedDate3 = null;
		MaterielEmprunte mat3 = new MaterielEmprunte(expectedMat, expectedEmprunteur, expectedDate3, expectedDureeEmprunt);
		if (mat3.getMatEmprunt() != expectedMat)
			fail ("mauvais materiel");
		if (mat3.getEmprunteur() != expectedEmprunteur)
			fail ("mauvais emprunteur");
		if (mat3.getDateEmprunt() != expectedDate3)
			fail ("mauvais date d'emprunt");
		if (mat3.getDureeEmprunt() != expectedDureeEmprunt)
			fail ("mauvais duree d'emprunt");
	}

	@Test
	public void testMaterielEmprunte() {
		Materiel expectedMat = new Materiel();
		Emprunteur expectedEmprunteur = new Emprunteur();
		Date expectedDate = new Date();
		int expectedDureeEmprunt = 10;
		MaterielEmprunte mat = new MaterielEmprunte();
		if (mat.getMatEmprunt() != expectedMat)
			fail ("mauvais materiel");
		if (mat.getEmprunteur() != expectedEmprunteur)
			fail ("mauvais emprunteur");
		if (mat.getDateEmprunt() != expectedDate)
			fail ("mauvais date d'emprunt");
		if (mat.getDureeEmprunt() != expectedDureeEmprunt)
			fail ("mauvais duree d'emprunt");
	}

	@Test
	public void testGetMatEmprunt() {
		Materiel expectedMat = new Materiel();
		MaterielEmprunte mat = new MaterielEmprunte (expectedMat, new Emprunteur(), new Date(), 10); 
		if (mat.getMatEmprunt() != expectedMat)
			fail("mauvais materiel");
		Materiel matEmprunt = null;
		MaterielEmprunte mat2 = new MaterielEmprunte (matEmprunt, new Emprunteur(), new Date(), 10);
		if (mat2.getMatEmprunt() != null)
			fail ("mauvais materiel, different de null");
	}

	@Test
	public void testGetEmprunteur() {
		Emprunteur expectedEmprunteur = new Emprunteur();
		MaterielEmprunte mat = new MaterielEmprunte (new Materiel(), expectedEmprunteur, new Date(), 10); 
		if (mat.getEmprunteur() != expectedEmprunteur)
			fail("mauvais emprunteur");
		Emprunteur emprunteur = null;
		MaterielEmprunte mat2 = new MaterielEmprunte (new Materiel(), emprunteur, new Date(), 10);
		if (mat2.getEmprunteur() != null)
			fail ("mauvais emprunteur, different de null");
	}

	@Test
	public void testGetDateEmprunt() {
		Date expectedDate = new Date();
		MaterielEmprunte mat = new MaterielEmprunte (new Materiel(), new Emprunteur(), expectedDate, 10); 
		if (mat.getDateEmprunt() != expectedDate)
			fail("mauvaise date");
		Date date = null;
		MaterielEmprunte mat2 = new MaterielEmprunte (new Materiel(), new Emprunteur(), date, 10);
		if (mat2.getDateEmprunt() != null)
			fail ("mauvaise date, different de null");
	}

	@Test
	public void testGetDureeEmprunt() {
		int expectedDuree = 20;
		MaterielEmprunte mat = new MaterielEmprunte (new Materiel(), new Emprunteur(), new Date(), expectedDuree); 
		if (mat.getDureeEmprunt() != expectedDuree)
			fail("mauvaise duree d'emprunt");
	}

	@Test
	public void testSetDureeEmprunt() {
		int duree = 20;
		MaterielEmprunte mat = new MaterielEmprunte (new Materiel(), new Emprunteur(), new Date(), 0); 
		mat.setDureeEmprunt(duree);
		if (mat.getDureeEmprunt() != duree)
			fail("mauvaise duree d'emprunt");
	}

	@Test
	public void testToString() {
		Materiel expectedMat = new Materiel("mat", 20);
		Emprunteur expectedEmprunteur = new Emprunteur();
		Date expectedDate = new Date();
		int expectedDureeEmprunt = 4;
		MaterielEmprunte mat = new MaterielEmprunte(expectedMat, expectedEmprunteur, expectedDate, expectedDureeEmprunt);
		String expectedString = expectedMat + "\nEmprunteur : "+ expectedEmprunteur.getNom().toUpperCase() + " " + expectedEmprunteur.getPrenom() + "\nDate d'emprunt : " + expectedDate.toString() +"\nDurée d'emprunt : " + expectedDureeEmprunt + " jour(s)\n";
		if (! mat.toString().equals(expectedString))
			fail ("mauvaise chaine de caractere");
	}

}
