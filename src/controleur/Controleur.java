package controleur;

import java.util.ArrayList;

import ihm.FrameIhm;
import metier.*;

public class Controleur
{
	private DB database;

	public Controleur()
	{
		//this.database = new DB(/*chemin, identifiant, mdp*/ );
		new FrameIhm(this);
	}

	public static void main(String[] args)
	{
		new Controleur();
	}

	public ArrayList<ModuleResource> getlstRessource(int semestre)
	{
		ArrayList<ModuleResource> lstRessource = new ArrayList<ModuleResource>();
		lstRessource.add(new ModuleResource(semestre, semestre, semestre, semestre, semestre, semestre, semestre, semestre, semestre, "C'est la ressource 2", "R2", false));
		return lstRessource;
	}
}