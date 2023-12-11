package metier;

import java.util.ArrayList;

/**
 * Semestre
 */
public class Semestre
{
	private int id;
	private ArrayList<Module> lstModule;
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
		lstModule = new ArrayList<Module>();
	}

	public int getId()                     {return id;}
	public ArrayList<Module> getLstModule(){return lstModule;}
	public int getNbGroupeTd()             {return nbGroupeTd;}
	public int getNbGroupeTp()             {return nbGroupeTp;}
	public int getNbEtu()                  {return nbEtu;}
	public int getNbSemaine()              {return nbSemaine;}
	
}