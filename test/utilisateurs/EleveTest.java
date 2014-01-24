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
		fail("Not yet implemented");
	}

	@Test
	public void testElevePersonne() {
		fail("Not yet implemented");
	}

	@Test
	public void testEleve() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDureeMaxReservation() {
		fail("Not yet implemented");
	}

}
