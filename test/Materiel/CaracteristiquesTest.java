package Materiel;

import static org.junit.Assert.*;

import org.junit.Test;

import Materiel.Caracteristiques;
import Materiel.CleInexistanteException;

/**
 * Test de la classe Caracteristiques
 * 
 * @author Maylanie MESNIER
 * 
 */
public class CaracteristiquesTest {

    @Test
    public void testAddSpecification() {
        Caracteristiques c = new Caracteristiques();
        try {
            c.addSpecification("Type", "ordi");

            if (!(c.getResultat().containsKey("Type")))
                fail("n'a pas ajouter la cle");
            if (!(c.getResultat().containsValue("ordi")))
                fail("n'a pas ajouter la valeur");

        } catch (CleInexistanteException e) {
            fail("ne reconnais pas la cle");
        }
        try {
            c.addSpecification("Poney", "rose");
            fail("n'a pas vu que la cle n'existait pas");
        } catch (CleInexistanteException e) {

        }

    }

    @Test
    public void testAddCle() {
        Caracteristiques c = new Caracteristiques();
        c.addCle("poney");
        try {
            c.addSpecification("poney", "rose");
        } catch (CleInexistanteException e) {
            fail("n'ajoute pas la cle");
        }
    }

    @Test
    public void testEquals() {
        Caracteristiques c1 = new Caracteristiques();
        Caracteristiques c2 = new Caracteristiques();

        try {
            c1.addSpecification("Type", "voiture");
            c2.addSpecification("Type", "voiture");
        } catch (CleInexistanteException e) {
        }

        if (!c1.equals(c2))
            fail("identique");
        Caracteristiques c3 = new Caracteristiques();
        try {
            c1.addSpecification("Type", "twingo");
        } catch (CleInexistanteException e) {
        }
        if (c1.equals(c3))
            fail("different");

    }

}
