package metier.model;

import java.util.ArrayList;

/**
 * Semestre
 * Classe de création des semestres 
 */
public class Semestre
{
	private int id;
	private int nbGroupeTd;
	private int nbGroupeTp;
	private int nbEtu;
	private int nbSemaine;

	public Semestre(int id, int nbGroupeTd, int nbGroupeTp, int nbEtu, int nbSemaine)
	{
		this.id = id;
		this.nbGroupeTd = nbGroupeTd;
		this.nbGroupeTp = nbGroupeTp;
		this.nbEtu = nbEtu;
		this.nbSemaine = nbSemaine;
	}

	public int getId()                     {return id;}
	public int getNbGroupeTd()             {return nbGroupeTd;}
	public int getNbGroupeTp()             {return nbGroupeTp;}
	public int getNbEtu()                  {return nbEtu;}
	public int getNbSemaine()              {return nbSemaine;}

	public ArrayList<Module> getlstRessource()
	{
		return null;
	}
	
}