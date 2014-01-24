package Materiel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utilisateurs.Emprunteur;

import Materiel.Caracteristiques;
import Materiel.CleInexistanteException;
import Materiel.Materiel;
import Materiel.MaterielEmprunte;
import Materiel.Stock;
/***
 * Cette classe est la classe de test de Stock.
 * 
 * @author RABEHASY Riana
 *
 */
public class StockTest {
    Stock stock;
    Caracteristiques c1, c2, c3;
    Materiel m1, m2, m3;
    Calendar cal = Calendar.getInstance();

    @Before
    public void setUp() throws Exception {

        stock = new Stock();
        stock.viderListeStock();
        stock.viderListeEmprunts();
        stock.viderListeReparations();

        c1 = new Caracteristiques();
        c2 = new Caracteristiques();
        c3 = new Caracteristiques();

        try {
            c1.addSpecification("Type", "Tablette");
            c1.addSpecification("Marque", "Samsung");

            c2.addSpecification("Type", "Telephone");
            c2.addSpecification("Marque", "Motorola");

            c3.addSpecification("Type", "Tablette");
            c3.addSpecification("Marque", "Apple");
        } catch (CleInexistanteException e) {

        }
        m1 = new Materiel(c1);
        m2 = new Materiel(c2);
        m3 = new Materiel(c3);

        stock.ajouterNouveauMateriel(m1);
        stock.ajouterNouveauMateriel(m2);
        stock.ajouterNouveauMateriel(m3);
    }

    @After
    public void tearDown() throws Exception {
        stock = null;
    }

    @Test
    public void testStock() {

    }

    @Test
    public void testRechercheIndexMateriel() {

        // Tests de réussite de recherche
        Caracteristiques cTest1 = new Caracteristiques();
        Caracteristiques cTest2 = new Caracteristiques();

        try {
            cTest1.addSpecification("Type", "Tablette");
            cTest1.addSpecification("Marque", "Samsung");

            cTest2.addSpecification("Type", "Tablette");
            cTest2.addSpecification("Marque", "Apple");

        } catch (CleInexistanteException e) {
        }
        Materiel matRech1 = new Materiel(cTest1);
        Materiel matRech2 = new Materiel(cTest2);

        int index1 = stock.rechercheIndexMateriel(matRech1,
                stock.getStockTotal());
        assertEquals(0, index1);

        int index2 = stock.rechercheIndexMateriel(matRech2,
                stock.getStockTotal());
        assertEquals(2, index2);

        // Test d'echec de recherche
        Caracteristiques cTest3 = new Caracteristiques();

        try {
            cTest3.addSpecification("Type", "bizarre");
            cTest3.addSpecification("Marque", "inconnue");
        } catch (CleInexistanteException e) {
        }
        Materiel matRech3 = new Materiel(cTest3);
        int index3 = stock.rechercheIndexMateriel(matRech3,
                stock.getStockTotal());

        assertEquals(-1, index3);
    }

    @Test
    public void testRechercheIndexMaterielEmprunte() {
        stock.viderListeEmprunts();
        MaterielEmprunte me1 = new MaterielEmprunte();
        MaterielEmprunte me2 = new MaterielEmprunte();
        MaterielEmprunte me3 = new MaterielEmprunte();

        stock.emprunter(me1);
        stock.emprunter(me2);
        stock.emprunter(me3);

        String id1 = me2.getId();
        String id2 = "fauxId";
        int indexEmprunt1 = stock.rechercheIndexMaterielEmprunte(id1,
                stock.getListeEmpruntsEtReservs());
        int indexEmprunt2 = stock.rechercheIndexMaterielEmprunte(id2,
                stock.getListeEmpruntsEtReservs());
        assertEquals(1, indexEmprunt1);
        assertEquals(-1, indexEmprunt2);

    }

    @Test
    public void testAjouterNouveauMateriel() {
        Caracteristiques cTest1 = new Caracteristiques();
        try {
            cTest1.addSpecification("Type", "Tablette");
            cTest1.addSpecification("Marque", "Samsung");
        } catch (CleInexistanteException e) {
        }
        Materiel matTest1 = new Materiel(cTest1);

        stock.ajouterNouveauMateriel(matTest1);
        int nb1 = stock.getStockTotal().get(0).getNombre();
        assertEquals(2, nb1);

    }

    /*
     * Cette méthode de test vérifie que l'ajout de matériel se comporte bien :
     * on rajoute un type de matériel s'il est déjà présent, sinon on crée une
     * nouvelle entrée dans le stock.
     */

    @Test
    public void testAjouterMateriel() {
        ArrayList<Materiel> liste = new ArrayList<Materiel>();
        Caracteristiques cTest1 = new Caracteristiques();
        try {
            cTest1.addSpecification("Type", "Ordinateur");
            cTest1.addSpecification("Marque", "Toshiba");
        } catch (CleInexistanteException e) {
            e.printStackTrace();
        }
        Materiel matTest1 = new Materiel(cTest1);
        stock.ajouterMateriel(matTest1, liste);
        // On teste l'entrée en stock d'un matériel
        assertEquals(1, liste.get(0).getNombre());

        Materiel matTest2 = new Materiel(cTest1);
        stock.ajouterMateriel(matTest2, liste);
        // On vérifie que le nombre d'ordinateurs Toshiba est bien modifié
        assertEquals(2, liste.get(0).getNombre());

        Caracteristiques cTest2 = new Caracteristiques();
        try {
            cTest2.addSpecification("Marque", "Motorola");
        } catch (CleInexistanteException e) {
            e.printStackTrace();
        }
        Materiel matTest3 = new Materiel(cTest2);
        stock.ajouterMateriel(matTest3, liste);
        // On vérifie que qu'il n'y a pas d'ordinateur Toshiba rajoutés et
        // qu'il y a bien une nouvelle entrée correspondant au materiel de
        // marque Motorola.
        assertEquals(2, liste.get(0).getNombre());
        assertEquals(1, liste.get(1).getNombre());

    }

    /*
     * On vérifie ici que le matériel est bien retiré s'il est présent dans la
     * liste
     */
    @Test
    public void testRetirerMateriel() {
        ArrayList<Materiel> liste = new ArrayList<Materiel>();
        Caracteristiques cTest1 = new Caracteristiques();
        try {
            cTest1.addSpecification("Type", "Ordinateur");
            cTest1.addSpecification("Marque", "Toshiba");
        } catch (CleInexistanteException e) {
            e.printStackTrace();
        }
        Materiel matTest1 = new Materiel(cTest1);
        matTest1.setNombre(5);
        liste.add(matTest1);
        Materiel matTest2 = matTest1.clone();
        matTest2.setNombre(2);
        stock.retirerMateriel(matTest2, liste);
        // On retire du matériel présent dans la liste
        assertEquals(3, liste.get(0).getNombre());

        c1.addCle("Poids");
        try {
            c1.addSpecification("Poids", "200g");
        } catch (CleInexistanteException e) {
            e.printStackTrace();
        }
        Materiel matTest3 = new Materiel(c1);
        stock.retirerMateriel(matTest3, liste);
        // On tente de retirer du matériel absent de la liste,
        // donc on vérifie que la liste n'a pas changé
        assertEquals(3, liste.get(0).getNombre());
    }

    @Test
    public void testAReparer() {
        stock.getStockTotal().get(0).incrNombre(2);
        Caracteristiques cTest1 = new Caracteristiques();
        try {
            cTest1.addSpecification("Type", "Ordinateur");
            cTest1.addSpecification("Marque", "Toshiba");
        } catch (CleInexistanteException e) {
            e.printStackTrace();
        }
        Materiel matTest1 = new Materiel(cTest1);
        matTest1.setNombre(2);
        stock.aReparer(matTest1);

        assertEquals(3, stock.getStockTotal().get(0).getNombre());
        assertTrue(stock.getListeReparations().isEmpty());

        Caracteristiques cTest2 = new Caracteristiques();
        try {
            cTest2.addSpecification("Type", "Tablette");
            cTest2.addSpecification("Marque", "Samsung");
        } catch (CleInexistanteException e) {
            e.printStackTrace();
        }
        Materiel matTest2 = new Materiel(cTest2);
        matTest2.setNombre(2);
        stock.aReparer(matTest2);

        assertEquals(1, stock.getStockTotal().get(0).getNombre());
        assertEquals(2, stock.getListeReparations().get(0).getNombre());
    }

    @Test
    public void testMaterielDispo() {
        m1.setNombre(10);
        m2.setNombre(10);
        m3.setNombre(10);
        Materiel mat1 = m1.clone();
        mat1.setNombre(6);
        Materiel mat2 = m2.clone();
        mat2.setNombre(6);
        cal.set(2014, 5, 4);
        Date dateDebut1 = cal.getTime();
        cal.set(2014, 5, 9);
        Date dateFin1 = cal.getTime();
        Emprunteur emp1 = new Emprunteur("Jacques", "Dupont");

        MaterielEmprunte matEmp1 = new MaterielEmprunte(mat1, emp1, dateDebut1,
                dateFin1);
        MaterielEmprunte matEmp2 = new MaterielEmprunte(mat2, emp1, dateDebut1,
                dateFin1);
        stock.emprunter(matEmp1);
        stock.emprunter(matEmp2);

        cal.set(2014, 5, 10);
        Date dateDebut2 = cal.getTime();
        cal.set(2014, 5, 13);
        Date dateFin2 = cal.getTime();
        ArrayList<Materiel> listeMateriel = stock.materielDispo(dateDebut2,
                dateFin2, "Tablette");
        assertEquals(2, listeMateriel.size());
        assertEquals(10, listeMateriel.get(0).getNombre());
        assertEquals(10, listeMateriel.get(1).getNombre());

        cal.set(2014, 5, 7);
        dateDebut2 = cal.getTime();
        listeMateriel.clear();
        listeMateriel = stock.materielDispo(dateDebut2, dateFin2, "Tablette");
        assertEquals(2, listeMateriel.size());
        assertEquals(4, listeMateriel.get(0).getNombre());
        assertTrue(listeMateriel.get(0).getCaracteristiques()
                .searchValue("Samsung"));
        assertEquals(10, listeMateriel.get(1).getNombre());
        assertTrue(listeMateriel.get(1).getCaracteristiques()
                .searchValue("Apple"));

        listeMateriel.clear();
        mat1.setNombre(10);
        matEmp1 = new MaterielEmprunte(mat1, emp1, dateDebut1, dateFin1);
        stock.viderListeEmprunts();
        stock.emprunter(matEmp1);
        stock.emprunter(matEmp2);

        listeMateriel = stock.materielDispo(dateDebut2, dateFin2, "Tablette");

        assertEquals(1, listeMateriel.size());
        assertEquals(10, listeMateriel.get(0).getNombre());
        assertTrue(listeMateriel.get(0).getCaracteristiques()
                .searchValue("Apple"));
    }

    @Test
    public void testEmpruntsParEmprunteur() {
        m1.setNombre(10);
        Materiel mat1 = m1.clone();

        cal.set(2014, 5, 4);
        Date dateDebut1 = cal.getTime();
        cal.set(2014, 5, 9);
        Date dateFin1 = cal.getTime();
        Emprunteur emp1 = new Emprunteur("Dupont", "Jacques");

        MaterielEmprunte matEmp1 = new MaterielEmprunte(mat1, emp1, dateDebut1,
                dateFin1);
        stock.emprunter(matEmp1);

        ArrayList<MaterielEmprunte> listeMatEmp = stock
                .empruntsParEmprunteur(emp1);
        assertTrue(!listeMatEmp.isEmpty());
        assertEquals("Jacques", listeMatEmp.get(0).getEmprunteur().getPrenom());

        Emprunteur emp2 = new Emprunteur("Dupont", "Francis");
        listeMatEmp = stock.empruntsParEmprunteur(emp2);
        assertTrue(listeMatEmp.isEmpty());

    }

    @Test
    public void testRenduEmprunt() {
        m1.setNombre(10);
        m2.setNombre(10);
        m3.setNombre(10);
        Materiel mat1 = m1.clone();
        mat1.setNombre(6);
        Materiel mat2 = m2.clone();
        mat2.setNombre(6);
        cal.set(2014, 5, 4);
        Date dateDebut1 = cal.getTime();
        cal.set(2014, 5, 9);
        Date dateFin1 = cal.getTime();
        Emprunteur emp1 = new Emprunteur("Jacques", "Dupont");

        MaterielEmprunte matEmp1 = new MaterielEmprunte(mat1, emp1, dateDebut1,
                dateFin1);
        MaterielEmprunte matEmp2 = new MaterielEmprunte(mat2, emp1, dateDebut1,
                dateFin1);
        stock.emprunter(matEmp1);
        stock.emprunter(matEmp2);

        String idEmprunt1 = matEmp1.getId();
        boolean renduReussi = stock.renduEmprunt(idEmprunt1, 3, 1);
        assertTrue(renduReussi);
        assertEquals(3, stock.getListeEmpruntsEtReservs().get(0)
                .getMatEmprunt().getNombre());
        assertEquals(
                1,
                stock.getListeReparations()
                        .get(stock.rechercheIndexMateriel(m1,
                                stock.getListeReparations())).getNombre());
    }

    @Test
    public void testRetourReparation() {
        m1.setNombre(10);
        m2.setNombre(10);
        m3.setNombre(10);
        Materiel mat1 = m1.clone();
        mat1.setNombre(6);
        stock.aReparer(mat1);
        Materiel mat11 = m1.clone();
        mat11.setNombre(3);
        stock.retourReparation(mat11);
        assertEquals(7, stock.getStockTotal().get(0).getNombre());
        assertEquals(3, stock.getListeReparations().get(0).getNombre());

        stock.retourReparation(mat11);
        assertEquals(10, stock.getStockTotal().get(0).getNombre());
        assertTrue(stock.getListeReparations().isEmpty());
    }

    /*
     * La méthode testée ici est supprimerMaterielHS. On teste ici que le bon
     * nombre de materiel est retiré de la liste des reparations, et que le
     * stock total reste bien modifié.
     */
    @Test
    public void testSupprimerMaterielHS() {
        m1.setNombre(10);
        m2.setNombre(10);
        m3.setNombre(10);
        Materiel mat1 = m1.clone();
        mat1.setNombre(6);
        stock.aReparer(mat1);
        // Il y a à ce stade 4 appareils de type m1 dans le stock,
        // et 6 en réparations
        Materiel mat11 = m1.clone();
        mat11.setNombre(3);
        stock.supprimerMaterielHS(mat11);
        // On jette ici trois de ces appareils qui sont HS, il en reste donc 3
        // en réparation.

        assertEquals(3, stock.getListeReparations().get(0).getNombre());
        assertEquals(4, stock.getStockTotal().get(0).getNombre());
    }

    @Test
    public void testEmprunter() {
        assertTrue(stock.getListeEmpruntsEtReservs().isEmpty());
        MaterielEmprunte matEmprunt = new MaterielEmprunte();
        stock.emprunter(matEmprunt);
        assertTrue(!stock.getListeEmpruntsEtReservs().isEmpty());
    }
}
