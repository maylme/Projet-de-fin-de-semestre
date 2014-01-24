package Materiel;

import static org.junit.Assert.*;

import java.util.Date;

import materiel.Caracteristiques;
import materiel.CleInexistanteException;

import org.junit.Test;

import utilisateurs.Eleve;
import utilisateurs.Professeur;

public class MaterielTest {

	@Test
	public void testHashCode() {
		Caracteristiques c = new Caracteristiques();
		c.addCle("Type");
		try{
			c.addSpecification("Type", "Unicorn");
		}catch (CleInexistanteException e){}		
		Materiel m1 = new Materiel (c);
		
		int result = 0;
		result = 31 * result + m1.dureeMaxEmprunt;
        result = 31 * result + (m1.getCaracteristiques() != null ? m1.getCaracteristiques().hashCode() : 0);
        result = 31 * result + m1.getNombre();
        
        if(result != m1.hashCode())
        	fail("mauvais hashCode");
	}

	@Test
	public void testMaterielCaracteristiquesIntInt() {
		Caracteristiques c = new Caracteristiques();
		c.addCle("Type");
		try{
			c.addSpecification("Type", "Unicorn");
		}catch (CleInexistanteException e){}		
		Materiel m1 = new Materiel (c,4,5);	
		
		if (!m1.getCaracteristiques().equals(c))
			fail("caracteristiques");
		if (m1.getDureeMaxEmprunt() != 4)
			fail("duree");
		if (m1.getNombre() != 5)
			fail("nombre d'exemplaire");
	}
	
	@Test
	public void testMaterielCaracteristiquesInt() {
		Caracteristiques c = new Caracteristiques();
		c.addCle("Type");
		try{
			c.addSpecification("Type", "Unicorn");
		}catch (CleInexistanteException e){}		
		Materiel m1 = new Materiel (c,5);	
		
		if (!m1.getCaracteristiques().equals(c))
			fail("caracteristiques");
		if (m1.getDureeMaxEmprunt() != 15)
			fail("duree");
		if (m1.getNombre() != 5)
			fail("nombre d'exemplaire");
	}

	@Test
	public void testMaterielCaracteristiques() {
		Caracteristiques c = new Caracteristiques();
		c.addCle("Type");
		try{
			c.addSpecification("Type", "Unicorn");
		}catch (CleInexistanteException e){}		
		Materiel m1 = new Materiel (c);	
		
		if (!m1.getCaracteristiques().equals(c))
			fail("caracteristiques");
		if (m1.getDureeMaxEmprunt() != 15)
			fail("duree");
		if (m1.getNombre() != 1)
			fail("nombre d'exemplaire");	}

	@Test
	public void testToString() {
		Caracteristiques c = new Caracteristiques();
		c.addCle("Type");
		try{
			c.addSpecification("Type", "Unicorn");
		}catch (CleInexistanteException e){}		
		Materiel m1 = new Materiel (c);
		String expected ="\nCaractéristiques du matériel : " + m1.getCaracteristiques()
                + "\nNombre de matériel de ce type : " + m1.getNombre()
                + "\nDuree max d'emprunt : " + m1.getDureeMaxEmprunt();
		if(!m1.toString().equals(expected))
			fail("string");
	}

	@Test
	public void testGetCaracteristiques() {
		Caracteristiques c = new Caracteristiques();
		c.addCle("Type");
		try{
			c.addSpecification("Type", "Unicorn");
		}catch (CleInexistanteException e){}		
		Materiel m1 = new Materiel (c);
		
		if(!m1.getCaracteristiques().equals(c))
			fail("pas les meme caracteristique");
	}

	@Test
	public void testGetDureeMaxEmprunt() {
		Caracteristiques c = new Caracteristiques();
		c.addCle("Type");
		try{
			c.addSpecification("Type", "Unicorn");
		}catch (CleInexistanteException e){}		
		Materiel m1 = new Materiel (c);
		
		if(m1.getDureeMaxEmprunt()!=(15))
			fail("pas les meme duree max");	
	}

	@Test
	public void testGetNombre() {
		Caracteristiques c = new Caracteristiques();
		c.addCle("Type");
		try{
			c.addSpecification("Type", "Unicorn");
		}catch (CleInexistanteException e){}		
		Materiel m1 = new Materiel (c);
		
		if(m1.getNombre()!=(1))
			fail("pas lameme quantite");		}

	@Test
	public void testSetNombre() {
		Caracteristiques c = new Caracteristiques();
		c.addCle("Type");
		try{
			c.addSpecification("Type", "Unicorn");
		}catch (CleInexistanteException e){}		
		Materiel m1 = new Materiel (c);
		m1.setNombre(5);
		
		if(m1.getNombre()!=(5))
			fail("pas la meme quantite");
	}

	@Test
	public void testIncrNombre() {
		Caracteristiques c = new Caracteristiques();
		c.addCle("Type");
		try{
			c.addSpecification("Type", "Unicorn");
		}catch (CleInexistanteException e){}		
		Materiel m1 = new Materiel (c);
		m1.incrNombre(4);
		
		if(m1.getNombre()!=(5))
			fail("pas la meme quantite");	
		
	}

	@Test
	public void testDecrNombre() {
		Caracteristiques c = new Caracteristiques();
		c.addCle("Type");
		try{
			c.addSpecification("Type", "Unicorn");
		}catch (CleInexistanteException e){}		
		Materiel m1 = new Materiel (c, 6);
		m1.decrNombre(4);
		
		if(m1.getNombre()!=(2))
			fail("pas la meme quantite");		
	}


	@Test
	public void testClone() {
		Caracteristiques c = new Caracteristiques();
		c.addCle("Type");
		try{
			c.addSpecification("Type", "Unicorn");
		}catch (CleInexistanteException e){}		
		Materiel m1 = new Materiel (c);
		Materiel m2 = m1.clone();
		
		if(m1.getNombre()!= m2.getNombre())
			fail("pas la meme quantite");
		if (!m1.getCaracteristiques().equals(m2.getCaracteristiques()))
			fail("caracteristique");
		if (m1.getDureeMaxEmprunt() != m2.getDureeMaxEmprunt())
			fail("duree");
	}


	@Test
	public void testEmpruntable() {
		Caracteristiques c = new Caracteristiques();
		c.addCle("Type");
		try{
			c.addSpecification("Type", "Unicorn");
		}catch (CleInexistanteException e){}		
		Materiel m1 = new Materiel (c);
		
		Date debut = new Date();
		Date fin = new Date("01/02/2014");
		
		Professeur p = new Professeur ("yassine", "bezad");
		
		//cas simple
		if (!m1.empruntable(debut,fin,p))
			fail( "devrai etre empruntable mais là non (cas simple)");
		
		//cas durée trop longue pour le materiel
		fin = new Date ("07/05/2014");
		if (m1.empruntable(debut, fin, p))
			fail ("empruntable alors que durée materielle trop longue");
		
		//cas reservation à l'avance pour eleve
		Eleve e = new Eleve();
		debut = new Date("02/05/2014");
		if (m1.empruntable(debut, fin, e))
			fail ("reservation trop en avance pour un eleve");
		
		//cas duree trop longue pour l'emprunteur
		Materiel m2 = new Materiel (c,30,10);
		debut = new Date();
		if(m2.empruntable(debut, fin, e))
			fail ("duree trop longue pour l'eprunteur");
	}

}
