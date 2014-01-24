package Materiel;

import java.util.Date;

import utilisateurs.Emprunteur;
import utilisateurs.Professeur;
/**
 * Celle classe représente un objet particulier se référant à Materiel.
 * Cet classe MaterielProfesseur représente le matériel qui est empruntable à condition
 * que l'emprunteur soit un professeur.
 * 
 * @author RABEHASY Riana
 *
 */
@SuppressWarnings("serial")
public class MaterielProfesseur extends Materiel {
    
    /**
     * Constructeur principal de la classe MaterielProfesseur avec toutes les informations nécessaires
     * passées en paramètre.
     * 
     * @param c
     *          Les caracteristiques du matériel
     * @param dureeMax
     *          La durée max d'emprunt
     * @param nbExemplaires
     *          Le nombre d'exemplaires
     *          
     */
    public MaterielProfesseur(Caracteristiques c, int dureeMax, int nbExemplaires) {
        super(c, dureeMax, nbExemplaires);
    }
    
    /**
     * Constructeur de la classe Materiel qui initialise automatiquement la duree 
     * max d'emprunt à 15 jours.
     * 
     * @param c
     *          Les caracteristiques du matériel
     * @param nbExemplaires
     *          Le nombre d'exemplaires
     */
    public MaterielProfesseur(Caracteristiques c, int nbExemplaires) {
        super(c, nbExemplaires);
    }
    
    /**
     * Constructeur de la classe Materiel. Il construit le matériel avec seulement
     * ses caracteristiques en paramètre, la durée emprunt max est initialisée à 15 par
     * défaut, et le nombre d'exemplaires à 1.
     * 
     * @param c
     *            Caractéristiques du materiel.
     */
    public MaterielProfesseur(Caracteristiques c) {
        super(c);
    }

    @Override
    public boolean empruntable(Date dateDebut, Date dateFin, Emprunteur e) {
        if (super.empruntable(dateDebut, dateFin, e)) {
            return (e instanceof Professeur);
        }
        return false;
    }
}
