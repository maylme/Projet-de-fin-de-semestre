package Materiel;
import java.util.ArrayList;
import java.io.*;

import base.MaterielEmprunte;


import utilisateurs.Personne;

/** 
* La class Stock represente un stock qui contient
* plusieurs arraylist.
* 
* @author Sonia Tual et Vincent Montalieu 
* @version 2.1 (4.Dec.2013) 
*/
public class Stock
{
	private ArrayList<Materiel> disponible ;
	private ArrayList<Materiel> reparations ;
	private ArrayList<MaterielEmprunte> emprunts ;
	private ArrayList<Materiel> statistiquesReparations ;
	private ArrayList<Materiel> statistiquesEmprunts ;

/** 
* Constructeur de la classe Stock
* Il construit le stock avec les 5 listes (disponible, reparations, emprunts,
* statistiquesReparations, statistiquesEmprunts).
*/
	public Stock()
	{
		disponible = deserialisationListeTypeDeMateriel("disponible");
		reparations = deserialisationListeTypeDeMateriel("reparations");
		emprunts = deserialisationListeMaterielEmprunte("emprunts");
		statistiquesReparations = deserialisationListeTypeDeMateriel("statistiquesReparations");
		statistiquesEmprunts = deserialisationListeTypeDeMateriel("statistiquesEmprunts");
	}

/** 
* Methode privee de deserialiser les listes du type TypeDeMateriel
* C'est a dire qui a partir d'un fichier (ici un .dat) recupere les listes
* qui ont ete prealablement enregistre. Ou si le fichier est vide ou non present
* on cree un nouvelle liste.
* La methode est privee, car elle est utilisee que dans cette classe.
*
* @param nomListe Une chaine de caractere representant le nom de la liste a deserialiser.
* @return Retourne la liste obtenue.
*/
	private ArrayList<Materiel> deserialisationListeTypeDeMateriel(String nomListe)
	{
		try
		{
			File file = new File("data/" + nomListe + ".dat");
		    if (!file.exists())
		    {
        		return new ArrayList<Materiel>();
    		} 
    		else if (file.length() <= 4) // un fichier vide cree par le programme fait 4 octets.
    		{
        		return new ArrayList<Materiel>();
        	}
        	else
        	{
        		FileInputStream fichierData = new FileInputStream("data/" + nomListe + ".dat");
				ObjectInputStream ois = new ObjectInputStream(fichierData);
				return (ArrayList<Materiel>) ois.readObject();
        	}	
		}
		catch (java.io.IOException e)
		{
			e.printStackTrace();
		}
		catch (java.lang.ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return new ArrayList<Materiel>();
	}

/** 
* Methode privee de deserialiser les listes du type MaterielEmprunte
* C'est a dire qui a partir d'un fichier (ici un .dat) recupere la liste
* qui a ete prealablement enregistre. Ou si le fichier est vide ou non present
* on cree un nouvelle liste.
* La methode est privee, car elle est utilisee que dans cette classe.
*
* @param nomListe Une chaine de caractere representant le nom de la liste a deserialiser.
* @return Retourne la liste obtenue.
*/
	private ArrayList<MaterielEmprunte> deserialisationListeMaterielEmprunte(String nomListe)
	{
		try
		{
			File file = new File("data/" + nomListe + ".dat");
		    if (!file.exists())
		    {
        		return new ArrayList<MaterielEmprunte>();
    		} 
    		else if (file.length() <= 4)
    		{
        		return new ArrayList<MaterielEmprunte>();
        	}
        	else
        	{
        		FileInputStream fichierData = new FileInputStream("data/" + nomListe + ".dat");
				ObjectInputStream ois = new ObjectInputStream(fichierData);
				return (ArrayList<MaterielEmprunte>) ois.readObject();
        	}	
		}
		catch (java.io.IOException e)
		{
			e.printStackTrace();
		}
		catch (java.lang.ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return new ArrayList<MaterielEmprunte>();
	}

/** 
* Methode privee de serialiser les listes.
* C'est a dire qui enregistrer dans un fichier (ici un .dat) la liste de type
* TyperDeMateriel passee en parametre, parce que lors de la fermeture du programme
* il faut pouvoir recuperer les informations.
* La methode est privee, car elle est utilisee que dans cette classe.
*
* @param listeASerialiser La liste a serialiser dans un fichier.
* @param nomListe Une chaine de caractere representant le nom de la liste a serialiser.
*/
	private void serialisationListe(ArrayList<Materiel> listeASerialiser, String nomListe)
	{
		try
		{ 
			FileOutputStream fichierListe = new FileOutputStream("data/" + nomListe + ".dat");
			ObjectOutputStream oos = new ObjectOutputStream(fichierListe);
			oos.writeObject(listeASerialiser);
			oos.flush();
			oos.close();
		}
		catch (java.io.IOException e)
		{
			e.printStackTrace();
		}
	}

/** 
* Methode privee de serialiser les listes.
* C'est a dire qui enregistrer dans un fichier (ici un .dat) la liste de type
* MaterielEmprunte passee en parametre, parce que lors de la fermeture du programme
* il faut pouvoir recuperer les informations.
* La methode est privee, car elle est utilisee que dans cette classe.
*
* @param listeASerialiser La liste a serialiser dans un fichier.
* @param nomListe Une chaine de caractere representant le nom de la liste a serialiser.
*/
	private void serialisationListeEmprunts(ArrayList<MaterielEmprunte> listeASerialiser, String nomListe)
	{
		try
		{ 
			FileOutputStream fichierListe = new FileOutputStream("data/" + nomListe + ".dat");
			ObjectOutputStream oos = new ObjectOutputStream(fichierListe);
			oos.writeObject(listeASerialiser);
			oos.flush();
			oos.close();
		}
		catch (java.io.IOException e)
		{
			e.printStackTrace();
		}
	}

/** 
* Methode public permettant de recuperer le nombre d'exemplaire
* de ce qu'il y a en stock, tous types confondus.
*
* @return Entier representant le nombre de materiel en stock.
*/
	public int exemplairesEnStock()
	{
		int stocktotal = 0 ;

		if(disponible.size()>0)
		{
			for(int i=0 ; i<disponible.size() ; i++)
			{
				stocktotal += disponible.get(i).getNombre() ;
			}
		}

		return stocktotal;
	}

/** 
* Methode public permettant d'ajouter un nouveau materiel a la liste
* disponible, ou s'il est deja present ajoute seulement
* le nombre d'exemplaire au materiel existant.
*
* @param name Le nom du type de materiel a ajouter.
* @param nombre Le nombre d'exemplaire de materiel a ajouter.
*/
	public void ajouterNouveauMateriel(String name, int nombre)
	{
		boolean trouve = false;
		int index = 0;
		int i = 0;

		while(trouve==false && i<disponible.size())
		{
			if(disponible.get(i).getNom().toUpperCase().equals(name.toUpperCase()))
			{
				trouve = true;
				index = i;				
			}

			i++;
		}

		if(trouve)
		{
			disponible.get(index).incrNombre(nombre) ;
		}			

		else
		{
			disponible.add(new Materiel(name,nombre)) ;
		}
		serialisationListe(disponible, "disponible");
	}

/** 
* Methode public permettant de retourner le type de materiel 
* possedant le plus d'exemplaires disponibles
*
* @return Retourne un TypeDeMateriel correspondant au plus grand nombre
* d'exemplaire du type disponible.
*/
	public Materiel typeDeMaterielenPlusGrandNombre()
	{
		int index = 0;

		if(disponible.size()>0)
		{
			for(int i=0 ; i<disponible.size()-1; i++)
			{
				if(disponible.get(index).getNombre() < disponible.get(i+1).getNombre())
				{
					index = i+1;
				}			
	
				else
				{
					index = 0;
				}
			}		
		}

		return disponible.get(index) ;
	}

/** 
* Methode public permettant de retourner le nombre d'exemplaires
* disponibles d'un type preexistant
*
* @param type Le nom du type de materiel a trouver dans la liste disponible.
* @return Le nombre d'exemplaire du type passe en parametre.
*/
	public int exemplairesEnStock(String type)
	{
		int nombretype = 0 ;	

		if(disponible.size()>0)
		{
			for(int i=0 ; i<disponible.size() ; i++)
			{
				if(disponible.get(i).getNom().startsWith(type))
				{
					nombretype += disponible.get(i).getNombre() ;
				}
			}
		}

		return nombretype;		
	}

/** 
* Methode public permettant d'ajouter un nombre d'exemplaires 
* d'un type preexistant a la liste du materiel a reparer
* Met a jour la liste du materiel disponible
*
* @param type Le nom du type de materiel a reparer.
* @param exemplairesAReparer Nombre d'exemplaire a reparer
* @return Retourne -1 si les exemplaires ont bien ete mis en
* reparation, sinon retroune le nombre d'exemplaire disponible (nombre max
* que l'on peut mettre en reparation) ou -2 si erreur
*/
	public int aReparer(String type, int exemplairesAReparer)
	{
		int nombreDisponibles = retirerExemplairesTypeDeMateriel(type, exemplairesAReparer, disponible);

		if(nombreDisponibles==-1)
		{
			ajouterExemplairesTypeDeMateriel(type, exemplairesAReparer, reparations);
			ajouterExemplairesTypeDeMateriel(type, exemplairesAReparer, statistiquesReparations);
		}
		serialisationListe(disponible, "disponible");
		serialisationListe(reparations, "reparations");
		serialisationListe(statistiquesReparations, "statistiquesReparations");

		return nombreDisponibles;
	}

/** 
* Methode public permettant de retirer un nombre d'exemplaires 
* de la liste de reparation et d'ajouter a la liste disponible
*
* @param type Le nom du type de materiel a rendre.
* @param exemplairesARetirer Nombre d'exemplaire a rendre
* @return Retourne 1 si le retour c'est bien passe, sinon retourne 0
*/
	public int retourReparation(String type, int exemplairesARetirer)
	{
		int index = rechercheIndexTypeDeMateriel(type, reparations) ;

		if(index!=-1)
		{
			int nombreRetour = reparations.get(index).getNombre() ;
			int nombreRetourPartiel = nombreRetour - exemplairesARetirer ;		

			if(index>=0 && nombreRetour>=exemplairesARetirer)
			{
				if(nombreRetour==exemplairesARetirer)
				{
					reparations.remove(index) ;
					ajouterExemplairesTypeDeMateriel(type, nombreRetour, disponible);
				}

				else if(nombreRetour>exemplairesARetirer)
				{
					reparations.get(index).setNombre(nombreRetourPartiel) ;
					ajouterExemplairesTypeDeMateriel(type, nombreRetourPartiel, disponible);
				}
				serialisationListe(reparations,"reparations");
				serialisationListe(disponible,"disponible");

				return 1 ;			
			}

			else
			{
				return 0 ;
			}
		}

		else
		{
			return 0 ;
		}
	}

/** 
* Methode privee permettant d'ajouter un nombre d'exemplaires 
* a un type dans une liste de materiel de type TypeDeMateriel.
*
* @param type Le nom du type de materiel.
* @param exemplairesAAjouter Nombre d'exemplaire a ajouter
* @param liste Liste de type TypeDeMateriel dans laquelle il faut
* ajouter le nombre d'exemplaire
*/
	private void ajouterExemplairesTypeDeMateriel(String type, int exemplairesAAjouter, ArrayList<Materiel> liste)
	{
		if(rechercheIndexTypeDeMateriel(type,liste)>=0)
		{
			liste.get(rechercheIndexTypeDeMateriel(type,liste)).setNombre(liste.get(rechercheIndexTypeDeMateriel(type,liste)).getNombre() + exemplairesAAjouter) ;
		}			

		else if(rechercheIndexTypeDeMateriel(type,disponible)>=0)
		{
			liste.add(new Materiel(type,exemplairesAAjouter,disponible.get(rechercheIndexTypeDeMateriel(type,disponible)).getId())) ;
		}		
	}

/** 
* Methode privee permettant de retirer un nombre d'exemplaires
* d'un type de materiel
* Retourne -1 si tous les exemplaires passes ont ete retires
* Retourne le nombre max d'exemplaires pouvant etre retires sinon ou -2 si erreur
*
* @param type Le nom du type de materiel a retirer.
* @param exemplairesARetirer Nombre d'exemplaire a retirer
* @return Retourne -1 si les exemplaires ont bien ete retires,
* sinon retourne le nombre max d'exemplaires pouvant etre retires ou -2 si erreur
*/
	private int retirerExemplairesTypeDeMateriel(String type, int exemplairesARetirer, ArrayList<Materiel> liste)
	{
		if(rechercheIndexTypeDeMateriel(type,liste)>=0)
		{
			if(liste.get(rechercheIndexTypeDeMateriel(type,liste)).getNombre()>=exemplairesARetirer)
			{
				liste.get(rechercheIndexTypeDeMateriel(type,liste)).decrNombre(exemplairesARetirer) ;
				return -1 ;
			}

			else
			{
				return liste.get(rechercheIndexTypeDeMateriel(type,liste)).getNombre() ;
			}
		}

		else
		{
			return -2 ;
		}
	}

/**
* Methode public permettant de creer un emprunt
* 
* @return Retourne -1 si l'emprunt est valide
* Retourne le nombre d'exemplaires pouvant être empruntes sinon
* ou -2 si erreur.
*/
	public int emprunter(String type, int nombreExemplaires, Personne emprunteur, String date, int duree)
	{
		int nombreDisponibles = retirerExemplairesTypeDeMateriel(type, nombreExemplaires, disponible);
		
		if(nombreDisponibles==-1)
		{
			ajouterExemplairesTypeDeMateriel(type, nombreExemplaires, statistiquesEmprunts);
			emprunts.add(new MaterielEmprunte(new Materiel(type, nombreExemplaires, disponible.get(rechercheIndexTypeDeMateriel(type,disponible)).getIdent()), emprunteur, date, duree));
		}
		serialisationListe(disponible, "disponible");
		serialisationListe(statistiquesEmprunts, "statistiquesEmprunts");
		serialisationListeEmprunts(emprunts, "emprunts");
		
		return nombreDisponibles ;
	}

/**
* Methode public permettant de rendre un materiel
* donc retirer de la liste de reparations et mettre dans disponible
* 
* @return Retourne true si le rendu est valide, sinon false
*/
	public boolean rendre(String type, int nombreExemplaires, Personne emprunteur)
	{
		int index = rechercheIndexMaterielEmprunte(type, nombreExemplaires, emprunteur) ;

		if(index>=0)
		{
			int nombreRetour = emprunts.get(index).getMatEmprunt().getNombre() ;
			int nombreRetourPartiel = nombreRetour - nombreExemplaires ;

			if(nombreRetour==nombreExemplaires)
			{
				emprunts.remove(index) ;
				ajouterExemplairesTypeDeMateriel(type, nombreRetour, disponible);
			}

			else if(nombreRetour>nombreExemplaires)
			{
				emprunts.get(index).getMatEmprunt().setNombre(nombreRetourPartiel) ;
				ajouterExemplairesTypeDeMateriel(type, nombreRetourPartiel, disponible);
			}
			serialisationListeEmprunts(emprunts,"emprunts");
			serialisationListe(disponible, "disponible");

			return true ;			
		}

		else
		{
			return false ;
		}
	}

/**
* Methode privee permettant de chercher l'index
* dans la liste passee en parametre correspondant au type passe
* en parametre
* 
* @return Retourne l'index si le type existe
* Retourne -1 sinon
*/
	private int rechercheIndexTypeDeMateriel(String type, ArrayList<Materiel> liste)
	{
		boolean trouve = false;
		int index = 0;
		int i = 0;

		while(!trouve && i<liste.size())
		{
			if(liste.get(i).getNom().toUpperCase().equals(type.toUpperCase()))
			{
				trouve = true;
				index = i;			
			}

			i++;
		}

		if(trouve)
		{
			return index ;
		}			

		else
		{
			return -1 ;
		}
	}

/**
* Methode privee permettant de chercher l'index
* dans la liste de MaterielEmprunte correspondant au type passe
* en parametre
* 
* @return Retourne l'index si le type existe
* Retourne -1 sinon
*/
	private int rechercheIndexMaterielEmprunte(String type, int nombreExemplaires, Personne emprunteur)
	{
		boolean trouve = false;
		int index = 0;
		int i = 0;

		while(trouve==false && i<emprunts.size())
		{
			if(emprunts.get(i).getEmprunteur().equals(emprunteur) && emprunts.get(i).getMatEmprunt().getNom().toUpperCase().equals(type.toUpperCase()) && emprunts.get(i).getMatEmprunt().getNombre()>=nombreExemplaires)
			{
				trouve = true;
				index = i;			
			}

			i++;
		}

		if(trouve)
		{
			return index ;
		}			

		else
		{
			return -1 ;
		}
	}

/** 
* Methode publique permettant de trouver le materiel
* le plus emprunte.
* 
* @return Retourne le TypeDeMateriel le plus emprunte
* sinon retourne null.
*/
	public Materiel materielPlusEmprunte()
	{
		int index = 0;

		if(statistiquesEmprunts.size()>0)
		{
			for(int i=0 ; i<statistiquesEmprunts.size()-1; i++)
			{
				if(statistiquesEmprunts.get(index).getNombre() < statistiquesEmprunts.get(i+1).getNombre())
				{
					index = i+1;
				}			
	
				else
				{
					index = 0;
				}
			}
			return statistiquesEmprunts.get(index) ;		
		}

		else
		{
			return null;
		}
	}

/** 
* Methode publique permettant de trouver le materiel
* le plus repare.
* 
* @return Retourne le TypeDeMateriel le plus repare
* sinon retourne null
*/
	public Materiel materielPlusRepare()
	{
		int index = 0;

		if(statistiquesReparations.size()>0)
		{
			for(int i=0 ; i<statistiquesReparations.size()-1; i++)
			{
				if(statistiquesReparations.get(index).getNombre() < statistiquesReparations.get(i+1).getNombre())
				{
					index = i+1;
				}			
	
				else
				{
					index = 0;
				}
			}
			return statistiquesReparations.get(index) ;		
		}

		else
		{
			return null ;
		}
	}

/** 
* Methode publique permettant de retirer un materiel (un type)
* de la liste disponible.
* 
* @param type Le type de materiel a retirer de la liste
* @return Retourne 1 si le type a bien ete retirer,
* sinon retourne 0.
*/
	public int retirerMaterielDisponible(String type)
	{
		int index = rechercheIndexTypeDeMateriel(type,disponible);

		if(index>=0)
		{
			disponible.remove(index);
			serialisationListe(disponible,"disponible");
			return 1;
		}

		else
		{
			return 0;
		}
	}

/** 
* Methode publique permettant de retirer un materiel (un type)
* de la liste reparations.
* 
* @param type Le type de materiel a retirer de la liste
* @return Retourne 1 si le type a bien ete retirer,
* sinon retourne 0.
*/
	public int retirerMaterielReparation(String type)
	{
		int index = rechercheIndexTypeDeMateriel(type,reparations);

		if(index>=0)
		{
			reparations.remove(index);
			serialisationListe(reparations, "reparations");
			return 1;
		}

		else
		{
			return 0;
		}
	}

/** 
* Methode publique permettant de faire un affichage par defaut
* de la classe, ici c'est un message d'erreur car il n'est pas
* possible d'afficher stock
* 
* @return La chaine de caractere contenant le message qu'il faut 
* afficher lors d'une demande d'affichage de stock.
*/
	public String toString()
	{
		return "Affichage impossible : utilisez l'affichage spécifique (Matériel disponible, Matériel en réparation, Emprunts en cours";
	}

/** 
* Methode publique permettant de faire un affichage de
* la liste disponible.
* 
* @return La chaine de caractere contenant le contenu
* de la liste disponible
*/
	public String afficherStock()
	{
		String retour = "\n     STOCK DISPONIBLE\n" ;

		for(int i = 0 ; i<disponible.size() ; i++)
		{
			retour += disponible.get(i) + "\n" ;
		}

		return retour ;
	}

/** 
* Methode publique permettant de faire un affichage de
* la liste reparations.
* 
* @return La chaine de caractere contenant le contenu
* de la liste reparations
*/
	public String afficherReparations()
	{
		String retour = "\n     STOCK REPARATION\n" ;

		for(int i = 0 ; i<reparations.size() ; i++)
		{
			retour += reparations.get(i) ;
		}

		return retour ;
	}

/** 
* Methode publique permettant de faire un affichage de
* la liste emprunts.
* 
* @return La chaine de caractere contenant le contenu
* de la liste emprunts
*/
	public String afficherEmprunts()
	{
		String retour = "\n     EMPRUNTS EN COURS\n" ;

		for(int i = 0 ; i<emprunts.size() ; i++)
		{
			retour += emprunts.get(i) ;
		}

		return retour ;
	}

/** 
* Methode publique utilisee pour acceder a la 
* liste diponible a partir d'une autre classe.
* 
* @return Retourne la liste de type TypeDeMateriel disponible
*/
	public ArrayList<Materiel> getListeDisponible()
	{
		return disponible;
	}

/** 
* Methode publique utilisee pour acceder a la 
* liste reparations a partir d'une autre classe.
* 
* @return Retourne la liste de type TypeDeMateriel reparations
*/
	public ArrayList<Materiel> getListeReparations()
	{
		return reparations;
	}

/** 
* Methode publique utilisee pour acceder a la 
* liste emprunts a partir d'une autre classe.
* 
* @return Retourne la liste de type MaterielEmprunte emprunts
*/ 
	public ArrayList<MaterielEmprunte> getListeEmprunts()
	{
		return emprunts;
	}
}