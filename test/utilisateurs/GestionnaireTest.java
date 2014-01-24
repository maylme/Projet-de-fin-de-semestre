package utilisateurs;

import static org.junit.Assert.*;

import org.junit.Test;

public class GestionnaireTest {

	@Test
	public void testGestionnaireStringString() {
		String prenom = "toto";
        String nom = "tata";
        Gestionnaire p = new Gestionnaire(nom, prenom);
        if (p.getNom() != nom)
            fail("mauvais nom");
        if (p.getPrenom() != prenom)
            fail("mauvais prenom");
	}

	@Test
	public void testGestionnairePersonne() {
		String prenom = "toto";
        String nom = "tata";
        Personne p = new Personne(nom, prenom);
        Gestionnaire g = new Gestionnaire(p);
        if (g.getNom() != nom)
            fail("mauvais nom");
        if (g.getPrenom() != prenom)
            fail("mauvais prenom");
	}

	@Test
	public void testGestionnaire() {
		String expectedPrenom = "b";
        String expectedNom = "a";
        Gestionnaire p = new Gestionnaire();
        if (p.getNom() != expectedNom)
            fail("mauvais nom");
        if (p.getPrenom() != expectedPrenom)
            fail("mauvais prenom");	}

}
