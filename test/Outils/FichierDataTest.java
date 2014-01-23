package Outils;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class FichierDataTest {

	@Test
	public void testFichierData() {
		
	}

	@Test
	public void testSerialisationListeString() {
		ArrayList<String> s = new ArrayList<String>();
		s.add("coucou");
		s.add("au revoir");
		FichierData f = new FichierData();
		f.serialisationListeString(s, "testSerialisation");
		
		ArrayList<String> expected = new ArrayList<String>();
		if (!(expected.equals(s))){
			
		}
	}

	@Test
	public void testSerialisationListeMateriel() {
		fail("Not yet implemented");
	}

	@Test
	public void testSerialisationListeMaterielEmprunte() {
		fail("Not yet implemented");
	}

	@Test
	public void testSerialisationHashMapEmprunteur() {
		fail("Not yet implemented");
	}

	@Test
	public void testSerialisationHashMapGestionnaire() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeserialisationHashMapGestionnaire() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeserialisationHashMapEmprunteur() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeserialisationListeMaterielEmprunte() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeserialisationListeMateriel() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeserialisationListeString() {
		fail("Not yet implemented");
	}

	@Test
	public void testSerialisationFichierLisible() {
		fail("Not yet implemented");
	}

}
