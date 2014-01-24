package base;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import materiel.Caracteristiques;
import materiel.Materiel;
import materiel.MaterielEmprunte;
import materiel.MaterielProfesseur;
import materiel.Stock;
import utilisateurs.Eleve;
import utilisateurs.Emprunteur;
import utilisateurs.Gestionnaire;
import utilisateurs.Personne;
import utilisateurs.Professeur;

import Outils.FichierData;

/**
 * Cette classe fait le lien entre un utilisateur et le stock sur lequel il
 * travaille. Elle contient un ensemble de fonctions qui permettent d'effectuer
 * des actions sur le stock. Certaines fonctions sont reservees aux
 * gestionnaires.
 * 
 * 
 * @author Maylanie Mesnier
 * 
 */

public class Gestion {
    private static final String MOT_DE_PASSE_GESTIONNAIRE = "mdpAdmin";
    private static final long JOUR_EN_MS = (1000 * 60 * 60 * 24);
    private Date dateCourante;
    private DateFormat df = DateFormat.getInstance();
    private Stock stock;
    private ArrayList<MaterielEmprunte> refus;
    private ArrayList<MaterielEmprunte> retards;
    private ArrayList<MaterielEmprunte> rappels;
    private HashMap<Emprunteur, String> hashMapEmprunteur;
    private HashMap<Gestionnaire, String> hashMapGestionnaire;
    private Personne utilisateurCourant;
    private FichierData f;

    /**
     * Constructeur avec paramètres qui cree un nouvel objet Gestion avec un
     * utilisateur et qui lit la sauvegarde utilisateurs
     * 
     */
    public Gestion() {
        f = new FichierData();
        // recuperation du HashMap motDePasse:
        hashMapEmprunteur = f
                .deserialisationHashMapEmprunteur("hashMapEmprunteur");
        hashMapGestionnaire = f
                .deserialisationHashMapGestionnaire("hashMapGestionnaire");

        // création de l'utilisateur courant:
        utilisateurCourant = new Personne();

        // création du stock;
        stock = new Stock();

        refus = f.deserialisationListeMaterielEmprunte("refus");

        retards = new ArrayList<MaterielEmprunte>();
        rappels = new ArrayList<MaterielEmprunte>();
        dateCourante = new Date();

    }

    /**
     * Regarde dans le HashMap si l'utilisateur existe . L'utilisateurCourant
     * change si il existe
     * 
     * @param nom
     *            Le nom de l'utilisateur
     * @param prenom
     *            Le prenom de l'utilisateur
     * @param statut
     *            Le satut de l'utilisateur (false = emprunteur ; true =
     *            gestionnaire).
     */

    public boolean existe(String nom, String prenom, boolean gestionnaire) {
        if (gestionnaire) {
            Gestionnaire aTester = new Gestionnaire(nom, prenom);
            if (hashMapGestionnaire.containsKey(aTester)) {
                utilisateurCourant = aTester;
                return true;
            } else
                return false;
        } else {
            Emprunteur aTester = new Emprunteur(nom, prenom);
            if (hashMapEmprunteur.containsKey(aTester)) {
                utilisateurCourant = aTester;
                return true;
            } else
                return false;
        }
    }

    /**
     * Renvoie true si l'utilisateur est correctement logge. Set definitivement
     * l'utlisateur courant
     * 
     * @param nom
     * @param Prenom
     * @param motDePasse
     * @param gestionnaire
     * @return true su l'utilisateur est loggé
     */
    private boolean isLogged(String nom, String prenom, String motDePasse,
            boolean gestionnaire) {
        if (existe(nom, prenom, gestionnaire)) {
            if (gestionnaire) {
                Gestionnaire aTester = new Gestionnaire(nom, prenom);
                String vraiMotDePasse = hashMapGestionnaire.get(aTester);
                if (vraiMotDePasse.equals(motDePasse)) {
                    for (Gestionnaire g : hashMapGestionnaire.keySet()) {
                        if (g.equals(aTester))
                            utilisateurCourant = g;
                        return true;
                    }
                }

            } else {
                Emprunteur aTester = new Emprunteur(nom, prenom);
                String vraiMotDePasse = hashMapEmprunteur.get(aTester);
                if (vraiMotDePasse.equals(motDePasse)) {
                    for (Emprunteur e : hashMapEmprunteur.keySet()) {
                        if (e.equals(aTester))
                            utilisateurCourant = e;
                        return true;
                    }
                }

            }
        }
        return false;
    }

    /**
     * Verifie si un mot de passe est bon. Test le mot de passe gestionnaire et
     * le mot de passe utilisateur
     * 
     * @param motdepasse
     *            le mot de passe à tester
     * @param gestionnaire
     *            si l'utilisateur est un gestionnaire
     * @param creationGestionnaire
     *            si on test le mot de passe de securité gestionnaire
     * @return true si le mot de passe est bon, false sinon.
     */
    public boolean password(String motdepasse, boolean gestionnaire,
            boolean creationGestionnaire) {
        // si c'est la verification lors de la création d'un gestionnaire:
        if (creationGestionnaire) {
            if (motdepasse.equals(MOT_DE_PASSE_GESTIONNAIRE))
                return true;
            return false;
        } else {
            return (this.isLogged(utilisateurCourant.getNom(),
                    utilisateurCourant.getPrenom(), motdepasse, gestionnaire));
        }
    }

    /**
     * Crée un nouveau compte gestionnaire
     * 
     * @param nom
     *            le nom de l'utilisateur
     * @param prenom
     *            le prenom de l'utilisateur
     * @param passwd
     *            le mot de passe de l'utilisateur
     */
    public void createNewGestionnaire(String nom, String prenom, String passwd) {
        utilisateurCourant = new Gestionnaire(nom, prenom);
        hashMapGestionnaire.put((Gestionnaire) utilisateurCourant, passwd);
        f.serialisationHashMapGestionnaire(hashMapGestionnaire,
                "hashMapGestionnaire");

    }

    /**
     * Crée un nouveau compte Professeur
     * 
     * @param nom
     *            le nom de l'utilisateur
     * @param prenom
     *            le prenom de l'utilisateur
     * @param passwd
     *            le mot de passe de l'utilisateur
     */
    public void createNewProf(String nom, String prenom, String passwd) {
        utilisateurCourant = new Professeur(nom, prenom);
        hashMapEmprunteur.put((Emprunteur) utilisateurCourant, passwd);
        f.serialisationHashMapEmprunteur(hashMapEmprunteur, "hashMapEmprunteur");
    }

    /**
     * Crée un nouveau compte Eleve
     * 
     * @param nom
     *            le nom de l'utilisateur
     * @param prenom
     *            le prenom de l'utilisateur
     * @param passwd
     *            le mot de passe de l'utilisateur
     */
    public void createNewEleve(String nom, String prenom, String passwd) {
        utilisateurCourant = new Eleve(nom, prenom);
        hashMapEmprunteur.put((Emprunteur) utilisateurCourant, passwd);
        f.serialisationHashMapEmprunteur(hashMapEmprunteur, "hashMapEmprunteur");

    }

    // Fonctions associees à l'Emprunteur:

    /**
     * Retourne la liste des Emprunts effectues par l'utlisiteur courant
     * 
     * @return la liste des Emprunts effectues par l'utlisiteur courant
     */
    public ArrayList<MaterielEmprunte> listeEmpruntParEmprunteur() {

        return stock.empruntsParEmprunteur((Emprunteur) utilisateurCourant);
    }

    /**
     * Renvoie la liste des Materiels empruntable selon des dates et un mot cle
     * 
     * @param motAChercher
     *            le mot a chercher
     * @param dateDebut
     *            la date de debut souhaiter pour l'emprunt
     * @param dateFin
     *            la date de fin souhaité pour l'emprunt
     * @return la liste des materiels correspondant a la recherche
     */
    public ArrayList<Materiel> listeMaterielEmpruntable(String motAChercher,
            Date dateDebut, Date dateFin) {

        return stock.materielDispo(dateDebut, dateFin, motAChercher);
    }

    /**
     * Rend le Materiel renseigné. et supprimme l'emprunt si tout a été rendu.
     * 
     * @param choix
     *            le Materiel choisit
     * @param nombreRendu
     *            le nombre de materiel rendu
     * @param nombreHS
     *            le nombre de Materiel HS
     * @return true si le rendu s'est bien passé, false sinon
     */

    public boolean rendre(MaterielEmprunte choix, int nombreRendu, int nombreHS) {
        if (nombreRendu <= choix.getMatEmprunt().getNombre()
                && (nombreHS <= nombreRendu)) {
            String idEmprunt = choix.getId();
            stock.renduEmprunt(idEmprunt, nombreRendu, nombreHS);
            return true;
        }
        return false;
    }

    /**
     * Effectue un emprunt
     * 
     * @param choix
     *            le Materiel a emprunte
     * @param nombre
     *            le nombre souhaite
     * @param dateDebut
     *            la date de debut de l'emprunt
     * @param dateFin
     *            la date de fin de l'emprunt
     * @return le message d'erreur ou de bon deroulement des operations
     */
    public String emprunt(Materiel choix, int nombre, Date dateDebut,
            Date dateFin) {
        MaterielEmprunte m = new MaterielEmprunte(choix,
                ((Emprunteur) utilisateurCourant), dateDebut, dateFin);
        if (choix.empruntable(dateDebut, dateFin,
                ((Emprunteur) utilisateurCourant))) {
            if (nombre <= choix.getNombre()) {
                m.getMatEmprunt().setNombre(nombre);
                stock.emprunter(m);
                return "Reservation effectuée";
            } else {
                refus.add(m);
                f.serialisationListeMaterielEmprunte(refus, "refus");
                return "Erreur: Nous n'avons pas autant de materiel (nombre demandé trop grand)\n Nous avons seulement "
                        + choix.getNombre() + " exemplaire(s)";
            }
        }
        refus.add(m);
        f.serialisationListeMaterielEmprunte(refus, "refus");
        return "Erreur: Vous n'êtes pas autorisé(e) à utiliser ce materiel";

    }

    // Fonctions associees au Gestionnaire:
    /**
     * Affiche le stock total
     * 
     * @return un string du stock total
     */
    public String afficherStockTotal() {
        return stock.afficherStock();
    }

    /**
     * Renvoie la liste de tous les Materiels.
     * 
     * @return la liste du stock de materiel
     */

    public ArrayList<Materiel> getStockTotal() {
        return stock.getStockTotal();
    }

    /**
     * Ajoute des Exemplaire à Un materiel existant.
     * 
     * @param existant
     *            le Materiel existant
     * @param nombre
     *            le nombre d'exemplaire a rajouter
     */
    public void ajouterExemplaire(Materiel existant, int nombre) {
        Materiel mat = existant.clone();
        mat.setNombre(nombre);
        stock.ajouterNouveauMateriel(mat);
    }

    /**
     * Renvoie la liste des cle possible pour les caracteristiques
     * 
     * @return la liste des cle possible pour les caracteristiques
     */
    public ArrayList<String> getCleMat() {
        Caracteristiques c = new Caracteristiques();
        return c.getClePossible();
    }

    /**
     * Regarde si la cle existe
     * 
     * @param cle
     *            la cle a tester
     * @return true si elle existe
     */
    public boolean existeCleCaracteristique(String cle) {
        Caracteristiques c = new Caracteristiques();

        if (c.existeCle(cle)) {
            return true;
        }
        return false;
    }

    /**
     * creation d'une nouvelle cle
     * 
     * @param cle
     *            la nouvelle cle
     */
    public void creationCleCaracteristique(String cle) {
        Caracteristiques c = new Caracteristiques();
        if (!c.existeCle(cle)) {
            c.addCle(cle);
        }
    }

    /**
     * Si la durée max d'emprunt donéne est nulle, on utilise la durée max par
     * defaut
     * 
     * @param caracs
     * @param nombre
     * @param duree
     */
    public void ajoutMaterielStock(HashMap<String, String> caracs, int nombre,
            int duree, String typeMat) {
        // creation des caracteristiques:
        Caracteristiques c = new Caracteristiques(caracs);
        Materiel m;
        if (duree == 0)
            if (typeMat.toUpperCase().equals("PROF"))
                m = new MaterielProfesseur(c, nombre);
            else
                m = new Materiel(c, nombre);
        else if (typeMat.toUpperCase().equals("PROF"))
            m = new MaterielProfesseur(c, duree, nombre);
        else
            m = new Materiel(c, duree, nombre);

        stock.ajouterNouveauMateriel(m);
    }

    /**
     * Enleve du materiel dans le Stock
     * 
     * @param m
     *            le materiel a enlever
     * @param nombre
     *            le nombre de materiel a enlever
     * @return true si l'operation s'est bien deroulee
     */
    public boolean retirerMaterielStock(Materiel m, int nombre) {
        if (nombre > m.getNombre())
            return false;

        Materiel mat = m.clone();
        mat.setNombre(nombre);
        stock.supprimerMaterielStock(mat);
        return true;
    }

    /**
     * Renvoie la liste des Materiel en reparation
     * 
     * @return la liste des Materiels en réparation
     */
    public ArrayList<Materiel> getListeReparation() {
        return stock.getListeReparations();
    }

    /**
     * Supprime definitivement un materiel parti en reparation
     * 
     * @param m
     *            le materiel concernant
     * @param nombre
     *            le nombre de materiel a supprimer
     * @return true si tout s'est bien passe
     */
    public boolean supprimerReparation(Materiel m, int nombre) {
        if (nombre > m.getNombre())
            return false;

        m.setNombre(nombre);
        stock.supprimerMaterielHS(m);
        return true;
    }

    /**
     * Renvoie un Materiel partie en reparation dans le Stock
     * 
     * @param m
     *            le materiel concerne
     * @param nombre
     *            le nombre de materiel repare
     * @return true si tout s'est bien passe
     */
    public boolean terminerReparation(Materiel m, int nombre) {
        if (nombre > m.getNombre())
            return false;

        m.setNombre(nombre);
        stock.retourReparation(m);
        return true;
    }

    /**
     * Renvoie la liste des MaterielEmprunte
     * 
     * @return la liste des MaterielEmprunte
     */
    public ArrayList<MaterielEmprunte> getListeResa() {
        return stock.getListeEmpruntsEtReservs();
    }

    /**
     * Modifie la date de debut d'un emprunt
     * 
     * @param m
     *            le materiel concerne
     * @param nvlDate
     *            la nouvelle date de debut d'emprunt
     */
    public void modifDateDebut(MaterielEmprunte m, Date nvlDate) {
        m.setDateEmprunt(nvlDate);
    }

    /**
     * Modifie la date de fin d'un emprunt
     * 
     * @param m
     *            le materiel concerne
     * @param nvlDate
     *            la nouvelle date de fin d'emprunt
     */
    public void modifDateFin(MaterielEmprunte m, Date nvlDate) {
        m.setDateFin(nvlDate);
    }

    /**
     * Modifie le nombre d'exemplaire d'un Materiel Emprunte
     * 
     * @param m
     *            le materiel concerne
     * @param nvnombre
     *            le nouveau nombre de materiel
     */
    public boolean modifNombreExemplaire(MaterielEmprunte m, int nvnombre) {
        if (nvnombre == 0) {
            stock.retirerEmprunt(m.getId());
            return true;
        } else {

            int nombre = stock
                    .getStockTotal()
                    .get(stock.rechercheIndexMateriel(m.getMatEmprunt(),
                            stock.getStockTotal())).getNombre();
            if ((nvnombre > nombre) || (nvnombre < 0))
                return false;
        }
        m.getMatEmprunt().setNombre(nvnombre);
        return true;
    }

    /**
     * Méthode interne qui permet d'ajouter dans liste tous les emprunts qui
     * n'ont pas été rendus à la date indiquée.
     * 
     * @param listeEmprunts
     *            - La liste d'emprunts à parcourir
     */
    private void calculerRetards(ArrayList<MaterielEmprunte> listeEmprunts) {
        retards.clear();
        for (MaterielEmprunte matEmp : listeEmprunts) {
            Date dateFinEmp1 = matEmp.getDateFin();
            if (dateFinEmp1.compareTo(dateCourante) < 0) {
                MaterielEmprunte tempMatEmp = matEmp.clone();
                retards.add(tempMatEmp);
            }
        }
    }

    private void calculerRappels(ArrayList<MaterielEmprunte> listeEmprunts) {
        rappels.clear();
        for (MaterielEmprunte matEmp : listeEmprunts) {
            Date dateFinEmp1 = matEmp.getDateFin();
            long difference = ((dateFinEmp1.getTime() - dateCourante.getTime()) / JOUR_EN_MS);
            if ((difference <= 2) && (difference >= 0)) {
                MaterielEmprunte tempMatEmp = matEmp.clone();
                rappels.add(tempMatEmp);
            }
        }
    }

    public String avancerJour() {
        dateCourante.setTime(dateCourante.getTime() + JOUR_EN_MS);
        return "La date du jour est : " + df.format(dateCourante);
    }

    /**
     * Retourne une String pour afficher les reservation et Emprunt
     * 
     * @return une String pour afficher les reservation et Emprunt
     */
    public String afficherResa() {
        return stock.afficherEmpruntsEtReservs();
    }

    /**
     * Retourne une String pour afficher les reparations
     * 
     * @return une String pour afficher les reparations
     */
    public String afficherReparation() {
        return stock.afficherReparations();
    }

    /**
     * Retourne une String pour afficher les Refus
     * 
     * @return une String pour afficher les Refus
     */
    public String afficherRefus() {
        String retour = "\n     Emprunts Refusés\n";

        for (int i = 0; i < refus.size(); i++) {
            retour += refus.get(i) + "\n";
        }

        return retour;
    }

    /**
     * Méthode qui permet d'afficher la liste des emprunts en retards si
     * celle-ci n'est pas vide
     * 
     * @param listeEmprunts
     *            La liste d'emprunts
     * @return La chaine de caractère contenant la liste
     */
    public String afficherRetards(ArrayList<MaterielEmprunte> listeEmprunts) {
        calculerRetards(listeEmprunts);
        String retour = "";
        if (retards.size() > 0) {
            retour += "\n      Emprunts non rendus\n";
            for (int i = 0; i < retards.size(); i++) {
                retour += retards.get(i) + "\n";
            }
        }
        return retour;
    }

    public String afficherRappels(ArrayList<MaterielEmprunte> listeEmprunts) {
        calculerRappels(listeEmprunts);
        String retour = "";
        if (rappels.size() > 0) {
            retour += "\n      Emprunts à rendre bientôt\n";
            for (int i = 0; i < rappels.size(); i++) {
                retour += rappels.get(i) + "\n";
            }
        } else
            retour += "Pas de matériel à rendre bientôt";
        return retour;

    }
}
