package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Outils.FichierData;

public class FIchierDataTest {

	@Test
	public void testSerialisationListe() {
		ArrayList<String> s = new ArrayList<String>();
		s.add("poney");
		s.add("Derpy Hooves");
		s.add("Fluttershy");
		FichierData f = new FichierData();
		
		f.serialisationListe(s, "liste");
		
		
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

}
