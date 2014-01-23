package Outils;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import utilisateurs.Emprunteur;
import utilisateurs.Professeur;

import Outils.FichierData;

public class FIchierDataTest {
	
	@Test
	public void testSerialisationHashMapEmprunteur(){
		HashMap<Emprunteur, String> motDePasse = new HashMap<Emprunteur, String>();
		Emprunteur hadock = new Professeur("Capitaine","Hadock");
		String m2p = "tonnerre de brest";
		motDePasse.put(hadock, m2p);
		
		FichierData f = new FichierData();
		f.serialisationHashMapEmprunteur(motDePasse, "hashMapEmprunteur");
		
		HashMap<Emprunteur, String> recuperation = f.deserialisationHashMapEmprunteur("hashMapEmprunteur");
		
		if (!recuperation.containsKey(hadock))
			fail(recuperation.toString()+ "\n" + motDePasse.toString());
	}

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
