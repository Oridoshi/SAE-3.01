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
		this.version = "v0.0.1";

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

	public ArrayList<Intervenant> getLstIntervenants()
	{
		ArrayList<Intervenant> lstIntervenants = new ArrayList<Intervenant>();
		
		lstIntervenants.add(new Intervenant(1, this.getLstCategorieIntervenant().get(1), "Dupont", "Axelito", 192));

		return lstIntervenants;
	}


	public ArrayList<CategorieIntervenant> getLstCategorieIntervenant()
	{
		ArrayList<CategorieIntervenant> lstCategorieIntervenant = new ArrayList<CategorieIntervenant>();
		lstCategorieIntervenant.add(new CategorieIntervenant("type_prof", "Professeur", 192, 384, 1));
		lstCategorieIntervenant.add(new CategorieIntervenant("type_vac", "Vacataire", 124, 258, 1));

		return lstCategorieIntervenant;
	}

	public ArrayList<CategorieHeure> getLstCategorieHeure()
	{
		ArrayList<CategorieHeure> lstCategorieHeure = new ArrayList<CategorieHeure>();

		lstCategorieHeure.add(new CategorieHeure("TD", 1));
		lstCategorieHeure.add(new CategorieHeure("TP", 2/3));
		lstCategorieHeure.add(new CategorieHeure("CM", 1));

		return lstCategorieHeure;
	}

	public double getCoefH(String string)
	{
		return 1;
		// return (Controleur.CateHeurDb.getCategorieHeureParId(string)).getCoef();
	}
}