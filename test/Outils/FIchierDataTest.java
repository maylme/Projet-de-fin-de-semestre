package Outils;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import utilisateurs.Eleve;
import utilisateurs.Emprunteur;
import utilisateurs.Personne;
import utilisateurs.Professeur;

import Outils.FichierData;

public class FIchierDataTest {
	
	@Test
	public void testSerialisationHashMap(){
		HashMap<Personne, String> motDePasse = new HashMap<Personne, String>();
		Emprunteur hadock = new Professeur("Capitaine","Hadock");
		String m2p = "tonnerre de brest";
		motDePasse.put(hadock, m2p);
		
		FichierData f = new FichierData();
		f.serialisationHashMap(motDePasse, "motDePasse");
		
		HashMap<Personne, String> recuperation = f.deserialisationHashMap("motDePasse");
		/**for (Personne p : recuperation.keySet()){
			if (p.equals(hadock))
				fail("")
		}
		**/
		
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
