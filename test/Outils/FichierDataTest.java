package Outils;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import materiel.Caracteristiques;
import materiel.CleInexistanteException;
import materiel.Materiel;
import materiel.MaterielEmprunte;

import org.junit.Test;

import utilisateurs.Emprunteur;
import utilisateurs.Gestionnaire;

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
		expected.add("coucou");
		expected.add("au revoir");
		
		if (!(expected.equals(s))){
			fail("pas la meme liste");
		}
	}

	@Test
	public void testSerialisationListeMateriel() {
		ArrayList<Materiel> lM = new ArrayList<Materiel>();
		
		Caracteristiques c = new Caracteristiques();
		c.addCle("Type");
		try{
			c.addSpecification("Type", "Unicorn");
		}catch (CleInexistanteException e){}		
		Materiel m1 = new Materiel (c);
		
		lM.add(m1);
		
		FichierData f = new FichierData();
		f.serialisationListeMateriel(lM, "testSerialisation");
		
		//expected:
		ArrayList<Materiel> expected = f.deserialisationListeMateriel("testSerialisation");
		
		if (!expected.equals(lM)){
			fail("pas les meme liste de materiel");
		}
	}

	@Test
	public void testSerialisationListeMaterielEmprunte() {
		ArrayList<MaterielEmprunte> lM = new ArrayList<MaterielEmprunte>();
		
		Caracteristiques c = new Caracteristiques();
		c.addCle("Type");
		try{
			c.addSpecification("Type", "Unicorn");
		}catch (CleInexistanteException e){}		
		Materiel m1 = new Materiel (c);
		Emprunteur p = new Emprunteur();
		Date d = new Date(0);
		Date d2 =  new Date(2);
		MaterielEmprunte me = new MaterielEmprunte(m1,p,d,d2) ;
		lM.add(me);
		
		FichierData f = new FichierData();
		f.serialisationListeMaterielEmprunte(lM, "testSerialisation");
		
		//expected:
		ArrayList<MaterielEmprunte> expected = f.deserialisationListeMaterielEmprunte("testSerialisation");
		
		if (!expected.equals(lM)){
			fail("pas les meme liste de materiel");
		}	
	}

	@Test
	public void testSerialisationHashMapEmprunteur() {
		HashMap<Emprunteur, String> hm = new HashMap<Emprunteur, String> ();
		Emprunteur e = new Emprunteur();
		hm.put(e, "motdepasse");
		FichierData f = new FichierData();
		f.serialisationHashMapEmprunteur(hm, "testSerialisation");
		HashMap<Emprunteur, String> expected = f.deserialisationHashMapEmprunteur("testSerialisation");
		
		if (!expected.equals(hm)){
			fail("pas les meme hashMap d'emprunteur");
		}	
	}

	@Test
	public void testSerialisationHashMapGestionnaire() {
		HashMap<Gestionnaire, String> hm = new HashMap<Gestionnaire, String> ();
		Gestionnaire e = new Gestionnaire();
		hm.put(e, "motdepasse");
		FichierData f = new FichierData();
		f.serialisationHashMapGestionnaire(hm, "testSerialisation");
		HashMap<Gestionnaire, String> expected = f.deserialisationHashMapGestionnaire("testSerialisation");
		
		if (!expected.equals(hm)){
			fail("pas les meme hashMap d'emprunteur");
		}		
	}

	@Test
	public void testDeserialisationHashMapGestionnaire() {
		HashMap<Gestionnaire, String> hm = new HashMap<Gestionnaire, String> ();
		Gestionnaire e = new Gestionnaire();
		hm.put(e, "motdepasse");
		FichierData f = new FichierData();
		f.serialisationHashMapGestionnaire(hm, "testSerialisation");
		HashMap<Gestionnaire, String> expected = f.deserialisationHashMapGestionnaire("testSerialisation");
		
		if (!expected.equals(hm)){
			fail("pas les meme hashMap d'emprunteur");
		}		
	}	

	@Test
	public void testDeserialisationHashMapEmprunteur() {
		HashMap<Emprunteur, String> hm = new HashMap<Emprunteur, String> ();
		Emprunteur e = new Emprunteur();
		hm.put(e, "motdepasse");
		FichierData f = new FichierData();
		f.serialisationHashMapEmprunteur(hm, "testSerialisation");
		HashMap<Emprunteur, String> expected = f.deserialisationHashMapEmprunteur("testSerialisation");
		
		if (!expected.equals(hm)){
			fail("pas les meme hashMap d'emprunteur");
		}		}

	@Test
	public void testDeserialisationListeMaterielEmprunte() {
		ArrayList<MaterielEmprunte> lM = new ArrayList<MaterielEmprunte>();
		
		Caracteristiques c = new Caracteristiques();
		c.addCle("Type");
		try{
			c.addSpecification("Type", "Unicorn");
		}catch (CleInexistanteException e){}		
		Materiel m1 = new Materiel (c);
		Emprunteur p = new Emprunteur();
		Date d = new Date(0);
		Date d2 =  new Date(2);
		MaterielEmprunte me = new MaterielEmprunte(m1,p,d,d2) ;
		lM.add(me);
		
		FichierData f = new FichierData();
		f.serialisationListeMaterielEmprunte(lM, "testSerialisation");
		
		//expected:
		ArrayList<MaterielEmprunte> expected = f.deserialisationListeMaterielEmprunte("testSerialisation");
		
		if (!expected.equals(lM)){
			fail("pas les meme liste de materiel");
		}	
	}
	

	@Test
	public void testDeserialisationListeMateriel() {
		ArrayList<Materiel> lM = new ArrayList<Materiel>();
		
		Caracteristiques c = new Caracteristiques();
		c.addCle("Type");
		try{
			c.addSpecification("Type", "Unicorn");
		}catch (CleInexistanteException e){}		
		Materiel m1 = new Materiel (c);
		
		lM.add(m1);
		
		FichierData f = new FichierData();
		f.serialisationListeMateriel(lM, "testSerialisation");
		
		//expected:
		ArrayList<Materiel> expected = f.deserialisationListeMateriel("testSerialisation");
		
		if (!expected.equals(lM)){
			fail("pas les meme liste de materiel");
		}	}

	@Test
	public void testDeserialisationListeString() {
		ArrayList<String> s = new ArrayList<String>();
		s.add("coucou");
		s.add("au revoir");
		FichierData f = new FichierData();
		f.serialisationListeString(s, "testSerialisation");
		
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("coucou");
		expected.add("au revoir");
		
		if (!(expected.equals(s))){
			fail("pas la meme liste");
		}
	}

}
