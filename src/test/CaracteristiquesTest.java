package test;

import static org.junit.Assert.*;

import org.junit.Test;

import Materiel.Caracteristiques;
import Materiel.CleInexistanteException;
/**
 * Test de la classe Caracteristiques
 * @author lyameina
 *
 */
public class CaracteristiquesTest {



	@Test
	public void testAddSpecification() {
		Caracteristiques c = new Caracteristiques();
		try{
			c.addSpecification("type", "ordi");
			
			if (!(c.getResultat().containsKey("type")))
				fail("n'a pas ajouter la cle");
			if (!(c.getResultat().containsValue("ordi")))
				fail("n'a pas ajouter la valeur");

		}
		catch(CleInexistanteException e){
			fail("ne reconnais pas la cle");
		}
		try{
			c.addSpecification("poney", "rose");
			fail("n'a pas vu que la cle n'existait pas");
		}catch(CleInexistanteException e){
			
		}

	}	

	@Test
	public void testAddCle() {
		Caracteristiques c = new Caracteristiques();
		c.addCle("poney");
		try{
		c.addSpecification("poney", "rose");
		}catch(CleInexistanteException e){
			fail("n'ajoute pas la cle");
		}
	}

}
