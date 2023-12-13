package controleur;

import java.util.ArrayList;

import ihm.FrameIhm;
import metier.model.*;
import metier.DB;

public class Controleur
{
	private DB database;
	private String version;

	public Controleur()
	{
		//this.database = new DB(/*chemin, identifiant, mdp*/ );
		
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
		return new Semestre(1, 50, 60, 92, 14);
	}

	public ArrayList<Intervenant> getLstIntervenants()
	{
		ArrayList<Intervenant> lstIntervenants = new ArrayList<Intervenant>();
		

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
}