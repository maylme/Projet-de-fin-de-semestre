package outils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import materiel.*;

import utilisateurs.Emprunteur;
import utilisateurs.Gestionnaire;


/**
 * Cette classe permet de faire les sérialisations et déserialisations. Ces
 * méthodes sont utilisées pour que les stocks ne soient pas perdus à chaque fois
 * que l'on quitte le programme. Il permet aux données de persister au dela d'une
 * exécution du programme.
 * 
 * 
 * @author Sonia TUAL
 * @version 1.4 (24.Jav.2014)
 */
public class FichierData {

	/**
	 * Constructeur de FichierData
	 */
	public FichierData() {

	}

	/**
	 * Methode publique pour serialiser les listes, c'est a dire qui enregistre
	 * dans un fichier (ici un .dat) la liste de String passee en parametre,
	 * car lors de la fermeture du programme, il faut pouvoir recuperer les
	 * informations.
	 * 
	 * @param s
	 *            La liste a serialiser dans un fichier.
	 * @param nomListe
	 *            Une chaine de caractere representant le nom de la liste a
	 *            serialiser (nom du fichier).
	 */
	public void serialisationListeString(ArrayList<String> s, String nomListe) {
		try {
			FileOutputStream fichierListe = new FileOutputStream("data/"
					+ nomListe + ".dat");
			ObjectOutputStream oos = new ObjectOutputStream(fichierListe);
			oos.writeObject(s);
			oos.flush();
			oos.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Methode publique permettant de serialiser les listes, c'est-a-dire qui enregistre dans
	 * un fichier (ici un .dat) la liste de type Materiel passee en parametre.
	 * 
	 * @param s
	 *            La liste a serialiser dans un fichier.
	 * @param nomListe
	 *            Une chaine de caractere representant le nom de la liste a
	 *            serialiser.
	 */
	public void serialisationListeMateriel(ArrayList<Materiel> s,
			String nomListe) {
		try {
			FileOutputStream fichierListe = new FileOutputStream("data/"
					+ nomListe + ".dat");
			ObjectOutputStream oos = new ObjectOutputStream(fichierListe);
			oos.writeObject(s);
			oos.flush();
			oos.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Methode publique permmettant de serialiser les listes, c'est-a-dire enregistrer dans
	 * un fichier (ici un .dat) la liste de type MaterielEmprunte passee en
	 * parametre.
	 * 
	 * @param s
	 *            La liste a serialiser dans un fichier.
	 * @param nomListe
	 *            Une chaine de caractere representant le nom de la liste a
	 *            serialiser.
	 */
	public void serialisationListeMaterielEmprunte(
			ArrayList<MaterielEmprunte> s, String nomListe) {
		try {
			FileOutputStream fichierListe = new FileOutputStream("data/"
					+ nomListe + ".dat");
			ObjectOutputStream oos = new ObjectOutputStream(fichierListe);
			oos.writeObject(s);
			oos.flush();
			oos.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Methode publique permettant de serialiser une HashMap, c'est-a-dire enregistrer dans
	 * un fichier (ici un .dat) la hashmap de type Emprunteur et String passee
	 * en parametre.
	 * 
	 * @param s
	 *            HashMap a serialiser dans un fichier.
	 * @param nomListe
	 *            Une chaine de caractere representant le nom du hashmap a
	 *            serialiser.
	 */
	public void serialisationHashMapEmprunteur(HashMap<Emprunteur, String> s,
			String nomListe) {
		try {
			FileOutputStream fichierListe = new FileOutputStream("data/"
					+ nomListe + ".dat");
			ObjectOutputStream oos = new ObjectOutputStream(fichierListe);
			oos.writeObject(s);
			oos.flush();
			oos.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Methode publique permettant de serialiser une HashMap, c'est a dire enregistrer dans
	 * un fichier (ici un .dat) la hashmap de type Gestionnaire et String passee
	 * en parametre.
	 * 
	 * @param s
	 *            HashMap a serialiser dans un fichier.
	 * @param nomListe
	 *            Une chaine de caractere representant le nom du hashmap a
	 *            serialiser.
	 */
	public void serialisationHashMapGestionnaire(
			HashMap<Gestionnaire, String> s, String nomListe) {
		try {
			FileOutputStream fichierListe = new FileOutputStream("data/"
					+ nomListe + ".dat");
			ObjectOutputStream oos = new ObjectOutputStream(fichierListe);
			oos.writeObject(s);
			oos.flush();
			oos.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Methode public de deserialiser un Hasmap Gestionnaire String, c'est a dire
	 * qui a partir d'un fichier (ici un .dat) recupere le Hashmap qui a ete
	 * prealablement enregistre. Si le fichier est vide ou non present, on
	 * cree un nouvelle liste.
	 * 
	 * @param nomListe
	 *            Une chaine de caractere representant le nom du hashmap a
	 *            deserialiser.
	 * @return Retourne le hashmap obtenue.
	 */
	@SuppressWarnings("unchecked")
	public HashMap<Gestionnaire, String> deserialisationHashMapGestionnaire(
			String nomListe) {
		try {
			File file = new File("data/" + nomListe + ".dat");
			if (!file.exists()) {
				return new HashMap<Gestionnaire, String>();
			} else if (file.length() <= 4) {
				return new HashMap<Gestionnaire, String>();
			} else {
				FileInputStream fichierData = new FileInputStream("data/"
						+ nomListe + ".dat");
				ObjectInputStream ois = new ObjectInputStream(fichierData);
				return (HashMap<Gestionnaire, String>) ois.readObject();
			}
		} catch (java.io.IOException e) {
			e.printStackTrace();
		} catch (java.lang.ClassNotFoundException e) {
			e.printStackTrace();
		}
		return new HashMap<Gestionnaire, String>();
	}

	/**
	 * Methode public de deserialiser un Hasmap Emprunteur String, c'est a dire
	 * qui a partir d'un fichier (ici un .dat) recupere le Hashmap qui a ete
	 * prealablement enregistre. Si si le fichier est vide ou non present, on
	 * cree un nouvelle liste.
	 * 
	 * @param nomListe
	 *            Une chaine de caractere representant le nom du hashmap a
	 *            deserialiser.
	 * @return Retourne le hashmap obtenue.
	 */
	@SuppressWarnings("unchecked")
	public HashMap<Emprunteur, String> deserialisationHashMapEmprunteur(
			String nomListe) {
		try {
			File file = new File("data/" + nomListe + ".dat");
			if (!file.exists()) {
				return new HashMap<Emprunteur, String>();
			} else if (file.length() <= 4) {
				return new HashMap<Emprunteur, String>();
			} else {
				FileInputStream fichierData = new FileInputStream("data/"
						+ nomListe + ".dat");
				ObjectInputStream ois = new ObjectInputStream(fichierData);
				return (HashMap<Emprunteur, String>) ois.readObject();
			}
		} catch (java.io.IOException e) {
			e.printStackTrace();
		} catch (java.lang.ClassNotFoundException e) {
			e.printStackTrace();
		}
		return new HashMap<Emprunteur, String>();
	}

	/**
	 * Methode public de deserialiser les listes du type MaterielEmprunte, c'est
	 * a dire qui a partir d'un fichier (ici un .dat) recupere la liste qui a
	 * ete prealablement enregistrees. Si le fichier est vide ou non present, on
	 * cree un nouvelle liste.
	 * 
	 * @param nomListe
	 *            Une chaine de caractere representant le nom de la liste a
	 *            deserialiser.
	 * @return Retourne la liste obtenue.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<MaterielEmprunte> deserialisationListeMaterielEmprunte(
			String nomListe) {
		try {
			File file = new File("data/" + nomListe + ".dat");
			if (!file.exists()) {
				return new ArrayList<MaterielEmprunte>();
			} else if (file.length() <= 4) {
				return new ArrayList<MaterielEmprunte>();
			} else {
				FileInputStream fichierData = new FileInputStream("data/"
						+ nomListe + ".dat");
				ObjectInputStream ois = new ObjectInputStream(fichierData);
				return (ArrayList<MaterielEmprunte>) ois.readObject();
			}
		} catch (java.io.IOException e) {
			e.printStackTrace();
		} catch (java.lang.ClassNotFoundException e) {
			e.printStackTrace();
		}
		return new ArrayList<MaterielEmprunte>();
	}

	/**
	 * Methode public de deserialiser les listes du type Materiel, c'est a dire
	 * qui a partir d'un fichier (ici un .dat) recupere les listes qui ont ete
	 * prealablement enregistrees. Si le fichier est vide ou non present, on
	 * cree un nouvelle liste.
	 * 
	 * @param nomListe
	 *            Une chaine de caractere representant le nom de la liste a
	 *            deserialiser.
	 * @return Retourne la liste obtenue.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Materiel> deserialisationListeMateriel(String nomListe) {
		try {
			File file = new File("data/" + nomListe + ".dat");
			if (!file.exists()) {
				return new ArrayList<Materiel>();
			} else if (file.length() <= 4) // un fichier vide cree par le
											// programme fait 4 octets.
			{
				return new ArrayList<Materiel>();
			} else {
				FileInputStream fichierData = new FileInputStream("data/"
						+ nomListe + ".dat");
				ObjectInputStream ois = new ObjectInputStream(fichierData);
				return (ArrayList<Materiel>) ois.readObject();
			}
		} catch (java.io.IOException e) {
			e.printStackTrace();
		} catch (java.lang.ClassNotFoundException e) {
			e.printStackTrace();
		}
		return new ArrayList<Materiel>();
	}

	/**
	 * Methode public de deserialiser les listes de String, c'est a dire qui a
	 * partir d'un fichier (ici un .dat) recupere les listes qui ont ete
	 * prealablement enregistres. Si le fichier est vide ou non present, on
	 * cree un nouvelle liste.
	 * 
	 * @param nomListe
	 *            Une chaine de caractere representant le nom de la liste a
	 *            deserialiser.
	 * @return Retourne la liste obtenue.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String> deserialisationListeString(String nomListe) {
		try {
			File file = new File("data/" + nomListe + ".dat");
			if (!file.exists()) {
				return new ArrayList<String>();
			} else if (file.length() <= 4) // un fichier vide cree par le
											// programme fait 4 octets.
			{
				return new ArrayList<String>();
			} else {
				FileInputStream fichierData = new FileInputStream("data/"
						+ nomListe + ".dat");
				ObjectInputStream ois = new ObjectInputStream(fichierData);
				return (ArrayList<String>) ois.readObject();
			}
		} catch (java.io.IOException e) {
			e.printStackTrace();
		} catch (java.lang.ClassNotFoundException e) {
			e.printStackTrace();
		}
		return new ArrayList<String>();
	}

	/**
	 * Methode de sauvegarde dans un fichier lisible .txt de l'ensemble des
	 * donnees concernant le programme : stock total, reparations en cours et
	 * emprunts et réservations.
	 * 
	 * @param stockTotal
	 *            Liste contenant le stock total à sauvegarder
	 * @param reparations
	 *            Liste contenant toutes les réparations en cours
	 * @param empruntsEtReservs
	 *            Liste contenant toutes les réservations et emprunts qui ont
	 *            été effectué.
	 */
	public void serialisationFichierLisible(ArrayList<Materiel> stockTotal,
			ArrayList<Materiel> reparations,
			ArrayList<MaterielEmprunte> empruntsEtReservs) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter("data/salleIHM.txt")));
			out.println("liste du stock total :");
			out.println(stockTotal);
			out.println("\nliste du materiel emprunte et des reservations :");
			out.println(empruntsEtReservs);
			out.println("\nliste du materiel en reparation :");
			out.println(reparations);
			out.flush();
			out.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}
}
