package materiel;

import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;

import materiel.Materiel;

import utilisateurs.Emprunteur;

/**
 * La classe MaterielEmprunte represente une instance d'emprunt/reservation.
 * Un objet MaterielEmprunte contient donc un emprunteur, un objet Materiel contenant
 * le type de matériel et le nombre d'exemplaires emprunté, ainsi que la date de début
 * et la date de din de l'emprunt.
 * 
 * @author Sonia TUAL, Maylanie MESNIER
 * @version 2.1 (24 Jav. 2014)
 */

@SuppressWarnings("serial")
public class MaterielEmprunte implements java.io.Serializable {
    
    private Materiel emprunt;
    private Emprunteur emprunteur;
    private Date dateEmprunt;
    private Date dateFin;
    private String id = UUID.randomUUID().toString();
    private DateFormat df = DateFormat.getInstance();

    /**
     * Constructeur de la classe MaterielEmprunte Il recupere un Materiel, un
     * emprunteur (Emprunteur), une Date pour la date d'emprunt et la duree de
     * l'emprunt en jours.
     * 
     * @param emprunt
     *            Un Materiel represente le materiel emprunte
     * @param emprunteur
     *            Une Personne
     * @param dateEmprunt
     *            une Date representant la date d'emprunt du materiel.
     * @param dureeEmprunt
     *            C'est un entier representant la duree d'emprunt du materiel en
     *            jours.
     */
    public MaterielEmprunte(Materiel emprunt, Emprunteur emprunteur,
            Date dateEmprunt, Date dateFin) {

        this.emprunt = emprunt;
        this.emprunteur = emprunteur;
        this.dateEmprunt = dateEmprunt;
        this.dateFin = dateFin;
    }

    /**
     * Constructeur par defaut de la classe MaterielEmprunte. Par defaut, on
     * construit un nouveau Materiel, un nouvel emprunteur, la date du jour pour
     * une fin d'emprunt à la date du jour.
     */
    public MaterielEmprunte() {
        this(new Materiel(new Caracteristiques()), new Emprunteur(),
                new Date(), new Date());
    }

    /**
     * Methode utilisee pour acceder au materiel emprunte
     * 
     * @return Un Materiel representant le materiel emprunte.
     */
    public Materiel getMatEmprunt() {
        return emprunt;
    }

    /**
     * Methode utilisee pour acceder a l'emprunteur
     * 
     * @return Emprunteur representant l'emprunteur du materiel.
     */
    public Emprunteur getEmprunteur() {
        return emprunteur;
    }

    /**
     * Methode utilisee pour obtenir la date de debut d'emprunt
     * 
     * @return Date contenant la date de l'emprunt.
     */
    public Date getDateEmprunt() {
        return dateEmprunt;
    }

    /**
     * Methode utilisee pour acceder a la date de fin
     * d'emprunt
     * 
     * @return Date representant la fin de l'emprunt.
     */
    public Date getDateFin() {
        return dateFin;
    }

    /**
     * Methode utilisee pour modifier la date de fin d'emprunt.
     * 
     * @param newDateFin
     *            Entier representant la date de fin de l'emprunt que l'on veut
     *            modifier
     */
    public void setDateFin(Date newDateFin) {
        dateFin = newDateFin;
    }

    /**
     * Methodeutilisee pour modifier la date de debut d'emprunt.
     * 
     * @param newDateDebut
     *            Entier representant la nouvelle date de debut de l'emprunt
     */
    public void setDateEmprunt(Date newDateDebut) {
        dateEmprunt = newDateDebut;
    }

    /**
     * Méthode d'accès à l'ID de l'emprunt.
     * 
     * @return l'ID de l'emprunt
     */
    public String getId() {
        return id;
    }

    /**
     * Methode publique utilisee pour afficher un materiel emprunte. Le nom de
     * l'emprunteur apparait en majuscule, puis le prenom de l'emprunteur,
     * ensuite la date d'emprunt et pour finir la duree d'emprunt en jours.
     * 
     * @return La chaine de caractere contenant la description du materiel
     *         emprunte a afficher.
     */
    public String toString() {
        return emprunt + "\nEmprunteur : " + emprunteur.getNom().toUpperCase()
                + " " + emprunteur.getPrenom() + "\nDate d'emprunt : "
                + df.format(dateEmprunt) + "\nDate de fin d'emprunt : "
                + df.format(dateFin) + " \n";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MaterielEmprunte
                && ((MaterielEmprunte) obj).getMatEmprunt().equals(emprunt)
                && ((MaterielEmprunte) obj).getDateEmprunt()
                        .equals(dateEmprunt)
                && ((MaterielEmprunte) obj).dateFin.equals(dateFin)
                && ((MaterielEmprunte) obj).getEmprunteur().equals(emprunteur)
                && ((MaterielEmprunte) obj).getId().equals(id))
            return true;

        else
            return false;
    }
    @Override
    public MaterielEmprunte clone() {
        MaterielEmprunte matEmp = new MaterielEmprunte(emprunt, emprunteur, dateEmprunt, dateFin);
        return matEmp;
    }
    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (dateFin != null ? dateFin.hashCode() : 0);
        result = 31 * result
                + (dateEmprunt != null ? dateEmprunt.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (emprunt != null ? emprunt.hashCode() : 0);
        result = 31 * result + (emprunteur != null ? emprunteur.hashCode() : 0);

        return result;
    }
}