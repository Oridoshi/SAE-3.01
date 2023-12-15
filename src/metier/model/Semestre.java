package metier.model;
import java.util.ArrayList;
import java.util.List;

import metier.IModifiable;
import metier.repo.IntervenantDB;
import metier.repo.ModuleDB;
import metier.repo.SemestreDB;


/**
 * Semestre
 * Classe de création des semestres 
 */
public class Semestre implements IModifiable
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

	public void setNbGroupeTd(int nbGroupeTd) {
		this.nbGroupeTd = nbGroupeTd;
	}

	public void setNbGroupeTp(int nbGroupeTp) {
		this.nbGroupeTp = nbGroupeTp;
	}

	public void setNbEtu(int nbEtu) {
		this.nbEtu = nbEtu;
	}

	public void setNbSemaine(int nbSemaine) {
		this.nbSemaine = nbSemaine;
	}

	public List<Module> getlstModules()
	{
		return ModuleDB.getParIdSemestre(this.id);
	}

	public boolean sauvegarder()
	{
		return SemestreDB.save(this);
	}

	public boolean supprimer()
	{
		return SemestreDB.delete(this);
	}
	
}