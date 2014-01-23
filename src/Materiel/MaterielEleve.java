package Materiel;

import java.util.Date;

import utilisateurs.Eleve;
import utilisateurs.Emprunteur;

@SuppressWarnings("serial")
public class MaterielEleve extends Materiel {
    public MaterielEleve(Caracteristiques c, int dureeMax, int nbExemplaires) {
        super(c, dureeMax, nbExemplaires);
    }

    public MaterielEleve(Caracteristiques c, int nbExemplaires) {
        super(c, nbExemplaires);
    }

    public MaterielEleve(Caracteristiques c) {
        super(c);
    }

    @Override
    public boolean empruntable(Date dateDebut, Date dateFin, Emprunteur e) {
        if (super.empruntable(dateDebut, dateFin, e)) {
            return (e instanceof Eleve);
        }
        return false;
    }
}
