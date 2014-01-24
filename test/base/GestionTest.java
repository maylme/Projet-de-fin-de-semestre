package base;

import static org.junit.Assert.*;

import org.junit.Test;

import base.Gestion;

public class GestionTest {

    @Test
    public void testExiste() {
        Gestion g = new Gestion();

        fail("Not yet implemented");
    }
	
	@Test
	public void testCreateNewProf(){
		Gestion g = new Gestion();
		if(g.existe("Capitaine", "Hadock", false)){
			fail ("il est au debut, vire le .dat et re essaie");
		}
		g.createNewProf("Capitaine", "Hadock", "tonnerre");
		if (!g.existe("Capitaine", "Hadock", false))
				fail("et ben non il l'ajoute pas");

		
	}
	@Test
	public void testExiste() {
		Gestion g = new Gestion();
		
		if(g.existe("Capitaine", "Hadock", false)){
			fail ("il est au debut, vire le .dat et re essaie");
		}
		else{
			g.createNewProf("Capitaine", "Hadock", "tonnerre");
			if (!g.existe("Capitaine", "Hadock", false)){
				fail ("il y est pas apres l'avoir ajouté! ");
			}
			Gestion g2 = new Gestion();
			if (!g2.existe("Capitaine", "Hadock", false)){
				fail ("il y est pas dans un nouveau Gestion");
			}
			
			
		}
	}
>>>>>>> 4d7ca4ae77e7dc316ecb98a9291ec89350085054

}
