package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Materiel.Caracteristiques;
import Materiel.Materiel;
import Materiel.Stock;

public class StockTest {
    Stock stock;
    Caracteristiques c1, c2, c3;
    Materiel m1, m2, m3;
    @Before
    public void setUp() throws Exception {
        
        stock=new Stock();
        c1 = new Caracteristiques();
        c2 = new Caracteristiques();
        c3 = new Caracteristiques();
        
        try {
            c1.addSpecification("Type", "tablette");
            c1.addSpecification("Marque", "Android");
            
            c2.addSpecification("Type", "tablette");
            c2.addSpecification("Marque", "Android");
            
            c3.addSpecification("Type", "tablette");
            c3.addSpecification("Marque", "Apple");
        }
        catch (Exception e) {
            
        }
        m1=new Materiel(c1);
        m2=new Materiel(c2);
        m3=new Materiel(c3);
                
        stock.ajouterNouveauMateriel(m1);
        stock.ajouterNouveauMateriel(m2);
        stock.ajouterNouveauMateriel(m3);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testStock() {
        fail("Not yet implemented");
    }

    @Test
    public void testRechercheIndexMateriel() {
        Caracteristiques cTest1 = new Caracteristiques();
        try {
            cTest1.addSpecification("Type", "tablette");
            cTest1.addSpecification("Marque", "Android");
        }
        catch (Exception e) {
        }
        Materiel matRech1 = new Materiel(cTest1);
        Materiel matRech2 = new Materiel(c3);
        int index1 = stock.rechercheIndexMateriel(matRech1, stock.getStockTotal());
        assertEquals(0, index1);
        
        int index2 = stock.rechercheIndexMateriel(matRech2, stock.getStockTotal());
        assertEquals(2, index2);
    }

    @Test
    public void testRechercheIndexMaterielEmprunte() {
        fail("Not yet implemented");
    }

    @Test
    public void testAjouterMateriel() {
        fail("Not yet implemented");
    }

    @Test
    public void testRetirerMateriel() {
        fail("Not yet implemented");
    }

    @Test
    public void testAReparer() {
        fail("Not yet implemented");
    }

    @Test
    public void testMaterielDispo() {
        fail("Not yet implemented");
    }

    @Test
    public void testRenduEmprunt() {
        fail("Not yet implemented");
    }

    @Test
    public void testRetourReparation() {
        fail("Not yet implemented");
    }

    @Test
    public void testSupprimerMaterielHS() {
        fail("Not yet implemented");
    }

    @Test
    public void testEmprunter() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetListeReparations() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetListeEmpruntsEtReservs() {
        fail("Not yet implemented");
    }

}
