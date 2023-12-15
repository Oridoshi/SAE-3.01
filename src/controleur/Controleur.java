package controleur;
import metier.repo.*;

import java.lang.module.ModuleDescriptor;
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
		return CategorieHeureDB.getParNom(string).getCoef();
	}

	public List<CategorieHeure> getLstCategorieParTypeModule(String typeModule)
	{
		List<CategorieHeure> lstCategorieHeure = new ArrayList<CategorieHeure>();
		for (PatternCategorieModuleItem item : CategorieModuleDB.getParNom(typeModule).getCategorieHeures())
			lstCategorieHeure.add(item.getCategorieHeure());
		return lstCategorieHeure;
	}

	public void ajouterModule() {
	}

	public CategorieModule getCategorieModule(String string)
	{
		return CategorieModuleDB.getParNom(string);
	}
}