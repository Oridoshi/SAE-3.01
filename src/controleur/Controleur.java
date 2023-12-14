package controleur;
import metier.repo.*;
import java.util.ArrayList;
import java.util.List;

import ihm.FrameIhm;
import metier.model.*;
import metier.DB;

public class Controleur
{
	private static final CategorieHeureDB CateHeurDb = new CategorieHeureDB();
	private DB database;
	private String version;

	private IntervenantDB intervenantDB;

	public Controleur()
	{
		this.intervenantDB = new IntervenantDB();
		this.version = "v0.1.4";

		new FrameIhm(this);
	}

	public static void main(String[] args)
	{
		new Controleur();
	}

	public String getVersion() { return this.version; }


	public Semestre getSemestre(int s)
	{
		return SemestreDB.getParId(s);
	}

	public List<Intervenant> getLstIntervenants()
	{
		return IntervenantDB.list();
	}


	public List<CategorieIntervenant> getLstCategorieIntervenant()
	{
		return CategorieIntervenantDB.list();
	}

	public List<CategorieHeure> getLstCategorieHeure()
	{
		return CategorieHeureDB.list();
	}

	public double getCoefH(String string)
	{
		return 1;
		// return (Controleur.CateHeurDb.getCategorieHeureParId(string)).getCoef();
	}
}