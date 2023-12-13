package metier.model;
import java.util.ArrayList;
import java.util.List;

import metier.repo.ModuleDB;

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

	public List<Module> getlstRessource()
	{
		ArrayList<Module> dd = new ArrayList<Module>();
		dd.add(new Module("R1.03", this, null, false, "Dev", "Developpement efficace", null));
		dd.add(new Module("R1.01", this, null, false, "Int Dev", "Iniciation Developpement", null));
		dd.add(new Module("R1.02", this, null, false, "Web", "Developpement Web", null));

		return dd;
	}
	
}