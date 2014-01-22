package Materiel;

import java.util.ArrayList;
import Outils.*;
import java.util.Date;
import java.util.HashMap;
import java.io.*;

import utilisateurs.Emprunteur;
import utilisateurs.Personne;

/**
 * La classe Stock est une representation par listes de type ArrayList d'un stock
 * de materiel ainsi que d'un referencement des emprunts et reservations.
 * 
 * @author RABEHASY Riana, TUAL Sonia, MESNIER Maylanie
 * @version 2.1 (4.Dec.2013)
 */

public class Stock {

    private ArrayList<Materiel> stockTotal;
    private ArrayList<Materiel> reparations;
    private ArrayList<MaterielEmprunte> empruntsEtReservs;
    FichierData f;

    /*
     * private ArrayList<Materiel> statistiquesReparations; private
     * ArrayList<Materiel> statistiquesEmprunts;
     */

    /**
     * Constructeur de la classe Stock Il construit le stock avec les 5 listes
     * (disponible, reparations, emprunts, statistiquesReparations,
     * statistiquesEmprunts).
     */
    public Stock() {
        f = new FichierData();
        stockTotal = f.deserialisationListeMateriel("stockTotal");
        reparations = f.deserialisationListeMateriel("reparations");
        empruntsEtReservs = f.deserialisationListeMaterielEmprunte("empruntsEtReservs");
        /*statistiquesReparations =
                deserialisationListeTypeDeMateriel("statistiquesReparations");
        statistiquesEmprunts =
                deserialisationListeTypeDeMateriel("statistiquesEmprunts");
                */

    }
    
    /**
     * Methode permettant d'obtenir l'emplacement d'un certain type de materiel
     * dans une liste de materiel.
     * 
     * @param mat
     *          Le matériel recherché
     * @param liste
     *          La liste a parcourir
     * @return
     *          Un entier correspondant a l'index de l'element dans la liste, ou -1 si le materiel n'est
     *          pas present
     */
    public int rechercheIndexMateriel(Materiel mat, ArrayList<Materiel> liste) {
        for (int i=0; i<liste.size(); ++i) {
            if (liste.get(i).equals((mat))) return i;
        }
        return -1;
    }
    
    /**
     * Methode permettant d'obtenir l'emplacement dans une liste d'emprunts d'un 
     * certain type d'emprunts. 
     * 
     * @param idEmprunt 
     *          l'id de l'emprunt recherche
     * @param liste 
     *          la liste a parcourir
     * @return
     *          Un entier correspondant a l'index de l'element dans la liste, ou
     *          -1 s'il n'est pas present.
     */         
    public int rechercheIndexMaterielEmprunte(String idEmprunt, ArrayList<MaterielEmprunte> liste) {
        for (int i=0; i<liste.size(); ++i) {
            if (idEmprunt.equals(liste.get(i).getId())) return i;
        }
        return -1;
    }
    
    public void ajouterNouveauMateriel(Materiel mat) {
        ajouterMateriel(mat, stockTotal);
    }
    
    /**
     * Methode qui ajoute intelligement du materiel dans une liste de materiel donnee, 
     * 
     * @param mat
     *          le type de materiel a ajouter
     * @param liste
     *          la liste a modifier
     */
    public void ajouterMateriel(Materiel mat, ArrayList<Materiel> liste) {
        int index=rechercheIndexMateriel(mat, liste);
        if (index>=0) liste.get(index).incrNombre(mat.getNombre());
        else liste.add(mat);
    }
    /**
     * Methode permettant de retirer un certain nombre d'exemplaires de materiel
     * d'une liste de materiel, et d'enlever de materiel de la liste si le nombre
     * d'exemplaires est 0
     * 
     * @param mat
     *          Le type de materiel a enlever
     * @param liste
     *          La liste a manipuler
     */
    public void retirerMateriel(Materiel mat, ArrayList<Materiel> liste) {
        int index=rechercheIndexMateriel(mat, liste);
        if (index>=0) {
            liste.get(index).decrNombre(mat.getNombre());
            if (liste.get(index).getNombre() <= 0) {
                liste.remove(index);
            }
        }
    }
    
    /**
     * Methode qui enleve du materiel du stock total pour l'ajouter a la liste
     * des reparations
     * 
     * @param aReparer
     */
    public void aReparer(Materiel aReparer) {
        retirerMateriel(aReparer, stockTotal);
        ajouterMateriel(aReparer, reparations);
        f.serialisationListeMateriel(stockTotal, "stockTotal");
        f.serialisationListeMateriel(reparations, "reparations");
    }
    
    private boolean conflitDates(Date dateDebut, Date dateFin, MaterielEmprunte emprunt) {
        Date dateDebutEmprunt = emprunt.getDateEmprunt();
        Date dateFinEmprunt = emprunt.getDateFin();
        return (((dateDebutEmprunt.compareTo(dateDebut) <= 0 && dateFinEmprunt
                            .compareTo(dateDebut) > 0)
                            || (dateDebutEmprunt.compareTo(dateFin) < 0 && dateFinEmprunt.compareTo(dateFin) >= 0) || (dateDebutEmprunt
                            .compareTo(dateDebut) > 0 && dateFinEmprunt
                            .compareTo(dateFin) < 0)));
                
    }
    
    /**
     * Methode qui retourne la liste de tous les materiels qui sont disponibles
     * en fonction des caracterstiques recherchees et des dates.
     * 
     * @param debut
     * @param fin
     * @param caracs
     * @return
     */
    
    public ArrayList<Caracteristiques> materielDispo(Date debut, Date fin,
            Caracteristiques caracs) {
        ArrayList<Caracteristiques> listeCaracs = new ArrayList<Caracteristiques>();
        int nombreMaterielDispo;
        for (Materiel mat : stockTotal) {
            nombreMaterielDispo=mat.getNombre();
            for (MaterielEmprunte matEmprunt : empruntsEtReservs) {
                if (mat.equals(matEmprunt.getMatEmprunt()) && (!(conflitDates(debut, fin, matEmprunt)))) {
                    nombreMaterielDispo-=matEmprunt.getMatEmprunt().getNombre();
                }
            }
            if (nombreMaterielDispo > 0) listeCaracs.add(mat.getCaracteristiques());
        }
        return listeCaracs;
    }

    /**
     * Methode qui gere le retour de materiel emprunte : un certain nombre de materiel
     * est rendu et la liste des reparations est mise a jour si il y a du materiel
     * defectueux.
     * Si il n'y a plus de materiel associe a l'emprunt, ce dernier est supprime de
     * la liste d'emprunts
     * 
     * @param idEmprunt
     *           - L'id de l'emprunt
     * @param nombreRendus
     *           - Le nombre de materiel rendu         
     * @param HS
     *           - Le nombre de materiel HS parmi le materiel rendu
     * @return Un entier positif si l'id est correct, -1 sinon
     */
    public int renduEmprunt(String idEmprunt, int nombreRendus, int hs) {
        int indexEmprunt = rechercheIndexMaterielEmprunte(idEmprunt, empruntsEtReservs);
        if (indexEmprunt>=0) {
            Materiel mat = empruntsEtReservs.get(indexEmprunt).getMatEmprunt();
            mat.decrNombre(nombreRendus);
            if (mat.getNombre() <= 0) 
                empruntsEtReservs.remove(indexEmprunt);
            if (hs!=0) {
                Materiel matRepar = mat.clone();
                matRepar.setNombre(hs);
                ajouterMateriel(matRepar, reparations);
                retirerMateriel(matRepar, stockTotal);
            }
        }
        f.serialisationListeMateriel(reparations, "reparations");
        f.serialisationListeMateriel(stockTotal, "stockTotal");
        f.serialisationListeMaterielEmprunte(empruntsEtReservs, "empruntsEtReservs");
        return indexEmprunt;
    }
    
    /**
     * Méthode qui replace les éléments passés en paramètre dans le stock total.
     * 
     * @param repaired
     *          Le materiel réparé
     */
    public void retourReparation(Materiel repaired) {
        retirerMateriel(repaired, reparations);
        ajouterMateriel(repaired, stockTotal);
        f.serialisationListeMateriel(reparations, "reparations");
        f.serialisationListeMateriel(stockTotal, "stockTotal");

    }
    
    /**
     * Méthode qui supprime du matériel HS de la liste de réparations
     * 
     * @param matSuppr
     */
    public void supprimerMaterielHS(Materiel matSuppr) {
        retirerMateriel(matSuppr, reparations);
        f.serialisationListeMateriel(reparations, "reparations");
    }
    
    
    /**
     * Méthode qui met à jour la liste des emprunts avec son paramètre.
     * 
     * @param matEmprunte
     *          L'emprunt à rajouter.
     */
    public void emprunter(MaterielEmprunte matEmprunte) {
        empruntsEtReservs.add(matEmprunte);
        f.serialisationListeMaterielEmprunte(empruntsEtReservs, "empruntsEtReservs");
    }
    
    

    

    /**
     * Methode publique permettant de faire un affichage par defaut de la
     * classe, ici c'est un message d'erreur car il n'est pas possible
     * d'afficher stock
     * 
     * @return La chaine de caractere contenant le message qu'il faut afficher
     *         lors d'une demande d'affichage de stock.
     */
    public String toString() {
        return "Affichage impossible : utilisez l'affichage spécifique (Matériel présents en stock, Matériel en réparation, Emprunts en cours";
    }

    /**
     * Methode publique permettant de faire un affichage de la liste disponible.
     * 
     * @return La chaine de caractere contenant le contenu de la liste
     *         disponible
     */
    public String afficherStock() {
        String retour = "\n     STOCK DISPONIBLE\n";

        for (int i = 0; i < stockTotal.size(); i++) {
            retour += stockTotal.get(i) + "\n";
        }

        return retour;
    }

    /**
     * Methode publique permettant de faire un affichage de la liste
     * reparations.
     * 
     * @return La chaine de caractere contenant le contenu de la liste
     *         reparations
     */
    public String afficherReparations() {
        String retour = "\n     STOCK REPARATION\n";

        for (int i = 0; i < reparations.size(); i++) {
            retour += reparations.get(i);
        }

        return retour;
    }

    /**
     * Methode publique permettant de faire un affichage de la liste emprunts.
     * 
     * @return La chaine de caractere contenant le contenu de la liste emprunts
     */
    public String afficherEmprunts() {
        String retour = "\n     EMPRUNTS EN COURS\n";
        /*
        for (int i = 0; i < emprunts.size(); i++) {
            retour += emprunts.get(i);
        }*/

        return retour;
    }

    /**
     * Methode publique utilisee pour acceder a la liste reparations a partir
     * d'une autre classe.
     * 
     * @return Retourne la liste de type TypeDeMateriel reparations
     */
    public ArrayList<Materiel> getListeReparations() {
        return reparations;
    }

    /**
     * Methode publique utilisee pour acceder a la liste emprunts a partir d'une
     * autre classe.
     * 
     * @return Retourne la liste de type MaterielEmprunte emprunts
     */
    public ArrayList<MaterielEmprunte> getListeEmpruntsEtReservs() {
        return empruntsEtReservs;
    }
}