package Materiel;

/**
 * Defini le materiel Specifique:
 * Certains types de matériel très spécifiques 
 * existent dans le stock à peu d'exemplaires, 
 * ils servent pour des projets ou des expérimentations
 * @author lyameina
 *
 */
public class MaterielSpecifique extends Materiel{
	
	private static final int DUREE_EMPRUNT_MAX_SPEC = 30;
	
    public MaterielSpecifique() {
        super("Telescope dernier cri", 5);
        this.dureeMaxEmprunt=DUREE_EMPRUNT_MAX_SPEC;
    }
    public MaterielSpecifique(String nomType, int nombreExemplaires) {
        super(nomType, nombreExemplaires);
        this.dureeMaxEmprunt=DUREE_EMPRUNT_MAX_SPEC;
    }
}
