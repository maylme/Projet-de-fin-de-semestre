package utilisateurs;

import static org.junit.Assert.*;

import org.junit.Test;

public class EleveTest {

	@Test
	public void testTypeEmprunteur() {
		Eleve e = new Eleve();
    	if( e.typeEmprunteur()!="Eleve")
    		fail(e.typeEmprunteur());
	}

	@Test
	public void testEleveStringString() {
		// mode courant
        String prenom = "Twilight";
        String nom = "Sparkle";
        Eleve e = new Eleve(nom, prenom);
        if (!e.getNom().equals(nom))
            fail("nom");
        if (!e.getPrenom().equals(prenom))
            fail("prenom");
        if (e.getDureeMaxDEmprunt() != 15)
            fail("duree");

	}

	@Test
	public void testElevePersonne() {
		String prenom = "Apple";
        String nom = "Jack";
        Personne p = new Personne(nom, prenom);
        Eleve e = new Eleve(p);
        if (e.getDureeMaxDEmprunt() != 15)
            fail("duree");
        if(e.getNom()!= nom)
        	fail ("nom");
        if (e.getPrenom()!=prenom)
        	fail("prenom");
	}

	@Test
	public void testEleve() {
		String expectedPrenom = "b";
        String expectedNom = "a";
        int expectedDuree = 15;
        Eleve e = new Eleve();
        if (!e.getNom().equals(expectedNom))
            fail("nom");
        if (!e.getPrenom().equals(expectedPrenom))
            fail("prenom");
        if (e.getDureeMaxDEmprunt() != expectedDuree)
            fail("duree");	}

	@Test
	public void testGetDureeMaxReservation() {
        Eleve e = new Eleve();
        if(e.getDureeMaxReservation() !=8)
        	fail("dureemax de resa");
	}

}
