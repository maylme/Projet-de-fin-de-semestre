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
    
    public MaterielSpecifique() {
        super("Telescope dernier cri", 5);
    }
    public MaterielSpecifique(String nomType, int nombreExemplaires) {
        super(nomType, nombreExemplaires);
        this.dateEmprunt=DUREE_EMPRUNT_MAX_SPEC;
    }
}
