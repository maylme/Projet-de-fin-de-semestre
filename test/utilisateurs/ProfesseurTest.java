package utilisateurs;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProfesseurTest {

	@Test
	public void testTypeEmprunteur() {
		Professeur e = new Professeur();
    	if( e.typeEmprunteur()!="Professeur")
    		fail(e.typeEmprunteur());
	}

	@Test
	public void testProfesseurStringString() {
		String prenom = "Twilight";
        String nom = "Sparkle";
        Professeur e = new Professeur(nom, prenom);
        if (!e.getNom().equals(nom))
            fail("nom");
        if (!e.getPrenom().equals(prenom))
            fail("prenom");
        if (e.getDureeMaxDEmprunt() != 30)
            fail("duree");
	}

	@Test
	public void testProfesseurPersonne() {
		String prenom = "Apple";
        String nom = "Jack";
        Personne p = new Personne(nom, prenom);
        Professeur e = new Professeur(p);
        if (e.getDureeMaxDEmprunt() != 30)
            fail("duree");
        if(e.getNom()!= nom)
        	fail ("nom");
        if (e.getPrenom()!=prenom)
        	fail("prenom");
	}

	@Test
	public void testProfesseur() {
		String expectedPrenom = "b";
        String expectedNom = "a";
        int expectedDuree = 30;
        Professeur e = new Professeur();
        if (!e.getNom().equals(expectedNom))
            fail("nom");
        if (!e.getPrenom().equals(expectedPrenom))
            fail("prenom");
        if (e.getDureeMaxDEmprunt() != expectedDuree)
            fail("duree");
	}

}
