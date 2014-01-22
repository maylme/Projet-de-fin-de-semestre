package Outils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import utilisateurs.Personne;

import Materiel.*;

public class FichierData {
	
	public FichierData(){
		
	}

	/** 
	* Methode public serialiser les listes.
	* C'est a dire qui enregistrer dans un fichier (ici un .dat) la liste de type
	* TyperDeMateriel passee en parametre, parce que lors de la fermeture du programme
	* il faut pouvoir recuperer les informations.
	*
	* @param s La liste a serialiser dans un fichier.
	* @param nomListe Une chaine de caractere representant le nom de la liste a serialiser.
	*/
	public  void serialisationListeString(ArrayList<String> s, String nomListe)
	{
		try
		{ 
			FileOutputStream fichierListe = new FileOutputStream("data/" + nomListe + ".dat");
			ObjectOutputStream oos = new ObjectOutputStream(fichierListe);
			oos.writeObject(s);
			oos.flush();
			oos.close();
		}
		catch (java.io.IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/** 
	* Methode public serialiser les listes.
	* C'est a dire qui enregistrer dans un fichier (ici un .dat) la liste de type
	* TyperDeMateriel passee en parametre, parce que lors de la fermeture du programme
	* il faut pouvoir recuperer les informations.
	*
	* @param s La liste a serialiser dans un fichier.
	* @param nomListe Une chaine de caractere representant le nom de la liste a serialiser.
	*/
	public  void serialisationListeMateriel(ArrayList<Materiel> s, String nomListe)
	{
		try
		{ 
			FileOutputStream fichierListe = new FileOutputStream("data/" + nomListe + ".dat");
			ObjectOutputStream oos = new ObjectOutputStream(fichierListe);
			oos.writeObject(s);
			oos.flush();
			oos.close();
		}
		catch (java.io.IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/** 
	* Methode public serialiser les listes.
	* C'est a dire qui enregistrer dans un fichier (ici un .dat) la liste de type
	* TyperDeMateriel passee en parametre, parce que lors de la fermeture du programme
	* il faut pouvoir recuperer les informations.
	*
	* @param s La liste a serialiser dans un fichier.
	* @param nomListe Une chaine de caractere representant le nom de la liste a serialiser.
	*/
	public  void serialisationListeMaterielEmprunte(ArrayList<MaterielEmprunte> s, String nomListe)
	{
		try
		{ 
			FileOutputStream fichierListe = new FileOutputStream("data/" + nomListe + ".dat");
			ObjectOutputStream oos = new ObjectOutputStream(fichierListe);
			oos.writeObject(s);
			oos.flush();
			oos.close();
		}
		catch (java.io.IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/** 
	* Methode public serialiser une HashMap.
	* C'est a dire qui enregistrer dans un fichier (ici un .dat) la hashmap de type
	* Personne et String passee en parametre, parce que lors de la fermeture du programme
	* il faut pouvoir recuperer les informations.
	*
	* @param s La liste a serialiser dans un fichier.
	* @param nomListe Une chaine de caractere representant le nom de la liste a serialiser.
	*/
	public  void serialisationHashMap(HashMap<Personne, String> s, String nomListe)
	{
		try
		{ 
			FileOutputStream fichierListe = new FileOutputStream("data/" + nomListe + ".dat");
			ObjectOutputStream oos = new ObjectOutputStream(fichierListe);
			oos.writeObject(s);
			oos.flush();
			oos.close();
		}
		catch (java.io.IOException e)
		{
			e.printStackTrace();
		}
	}
		
	/** 
	* Methode public de deserialiser un Hasmap Personne String
	* C'est a dire qui a partir d'un fichier (ici un .dat) recupere le Hashmap
	* qui a ete prealablement enregistre. Ou si le fichier est vide ou non present
	* on cree un nouvelle liste.
	*
	* @param nomListe Une chaine de caractere representant le nom de la liste a deserialiser.
	* @return Retourne le hashmap obtenue.
	*/
	public  HashMap<Personne, String> deserialisationHashMap(String nomListe)
	{
		try
		{
			File file = new File("data/" + nomListe + ".dat");
		    if (!file.exists())
		    {
        		return new HashMap<Personne, String>();
    		} 
    		else if (file.length() <= 4)
    		{
        		return new HashMap<Personne, String>();
        	}
        	else
        	{
        		FileInputStream fichierData = new FileInputStream("data/" + nomListe + ".dat");
				ObjectInputStream ois = new ObjectInputStream(fichierData);
				return (HashMap<Personne, String>) ois.readObject();
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
		return new HashMap<Personne, String>();
	}
	/** 
	* Methode public de deserialiser les listes du type MaterielEmprunte
	* C'est a dire qui a partir d'un fichier (ici un .dat) recupere la liste
	* qui a ete prealablement enregistre. Ou si le fichier est vide ou non present
	* on cree un nouvelle liste.
	*
	* @param nomListe Une chaine de caractere representant le nom de la liste a deserialiser.
	* @return Retourne la liste obtenue.
	*/
	public  ArrayList<MaterielEmprunte> deserialisationListeMaterielEmprunte(String nomListe)
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
	* Methode public de deserialiser les listes du type Materiel
	* C'est a dire qui a partir d'un fichier (ici un .dat) recupere les listes
	* qui ont ete prealablement enregistre. Ou si le fichier est vide ou non present
	* on cree un nouvelle liste.
	*
	* @param nomListe Une chaine de caractere representant le nom de la liste a deserialiser.
	* @return Retourne la liste obtenue.
	*/
	public  ArrayList<Materiel> deserialisationListeMateriel(String nomListe)
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
	* Methode public de deserialiser les listes de String
	* C'est a dire qui a partir d'un fichier (ici un .dat) recupere les listes
	* qui ont ete prealablement enregistre. Ou si le fichier est vide ou non present
	* on cree un nouvelle liste.
	*
	* @param nomListe Une chaine de caractere representant le nom de la liste a deserialiser.
	* @return Retourne la liste obtenue.
	*/
	public  ArrayList<String> deserialisationListeString(String nomListe)
	{
		try
		{
			File file = new File("data/" + nomListe + ".dat");
		    if (!file.exists())
		    {
        		return new ArrayList<String>();
    		} 
    		else if (file.length() <= 4) // un fichier vide cree par le programme fait 4 octets.
    		{
        		return new ArrayList<String>();
        	}
        	else
        	{
        		FileInputStream fichierData = new FileInputStream("data/" + nomListe + ".dat");
				ObjectInputStream ois = new ObjectInputStream(fichierData);
				return (ArrayList<String>) ois.readObject();
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
		return new ArrayList<String>();
	}
}
