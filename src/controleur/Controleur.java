package controleur;

import java.util.ArrayList;

import ihm.FrameIhm;
import metier.*;

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

	public ArrayList<ModuleResource> getlstRessource(int semestre)
	{
		ArrayList<ModuleResource> lstRessource = new ArrayList<ModuleResource>();
		lstRessource.add(new ModuleResource(semestre, semestre, semestre, semestre, semestre, semestre, semestre, semestre, semestre, "C'est la ressource 2", "R2", false));
		return lstRessource;
	}

	public Semestre getSemestre(int s)
	{
		return new Semestre(1, 50, 60, 92, 14);
	}
}