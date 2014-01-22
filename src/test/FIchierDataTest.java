package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Outils.FichierData;

public class FIchierDataTest {

	@Test
	public void testSerialisationListeString() {
		ArrayList<String> s = new ArrayList<String>();
		s.add("poney");
		s.add("Derpy Hooves");
		s.add("Fluttershy");
		FichierData f = new FichierData();
		
		f.serialisationListeString(s, "liste");
		ArrayList<String> str = f.deserialisationListeString("liste");
		if (!(s.equals(str)))
			fail("pas bien");
		
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
		ArrayList<String> s = new ArrayList<String>();
		s.add("poney");
		s.add("Derpy Hooves");
		s.add("Fluttershy");
		FichierData f = new FichierData();
		
		f.serialisationListeString(s, "liste");
		ArrayList<String> str = f.deserialisationListeString("liste");
		if (!(s.equals(str)))
			fail("pas bien");
	}

}
