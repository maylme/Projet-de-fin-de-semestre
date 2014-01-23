package utilisateurs;

import static org.junit.Assert.*;

import org.junit.Test;

import utilisateurs.Personne;

public class PersonneTest {

    @Test
    public void testPersonneStringString() {
        String prenom = "toto";
        String nom = "tata";
        Personne p = new Personne(nom, prenom);
        if (p.getNom() != nom)
            fail("mauvais nom");
        if (p.getPrenom() != prenom)
            fail("mauvais prenom");

    }

    @Test
    public void testPersonne() {
        String expectedPrenom = "b";
        String expectedNom = "a";
        Personne p = new Personne();
        if (p.getNom() != expectedNom)
            fail("mauvais nom");
        if (p.getPrenom() != expectedPrenom)
            fail("mauvais prenom");
    }

    @Test
    public void testGetNom() {
        String prenom = "toto";
        String nom = "tata";
        Personne p = new Personne(nom, prenom);
        if (p.getNom() != nom)
            fail("mauvais nom");
        nom = null;
        p = new Personne(nom, prenom);
        if (p.getNom() != null)
            fail("nom null");
    }

    @Test
    public void testGetPrenom() {
        String prenom = "toto";
        String nom = "tata";
        Personne p = new Personne(nom, prenom);
        if (p.getPrenom() != prenom)
            fail("mauvais prenom");
        prenom = null;
        p = new Personne(nom, prenom);
        if (p.getPrenom() != null)
            fail("prenom null");
    }

    @Test
    public void testEqualsPersonne() {
        // si les 2 memes:
        String prenom = "Rainbow";
        String nom = "Dash";
        Personne p1 = new Personne(nom, prenom);
        Personne p2 = new Personne(nom, prenom);

        if (!p1.equals(p2))
            fail("exactement pareil nom et prenom");

        // cas avec des majuscule sur le prenom
        String prenom2 = "raInBow";
        p2 = new Personne(nom, prenom2);
        if (!p1.equals(p2))
            fail("prenom en majuscule");

        // cas nom majuscule
        String prenom3 = "Rainbow";
        String nom3 = "DASH";
        p2 = new Personne(nom3, prenom3);
        if (!p1.equals(p2))
            fail("nom en majuscule");
    }

    @Test
    public void testToString() {
        String prenom = "Pinkie";
        String nom = "Pie";
        Personne p = new Personne(nom, prenom);
        String expectedString = nom.toUpperCase() + " " + prenom + "\n";
        if (!(p.toString().equals(expectedString)))
            fail("chaines differentes");
    }

}
