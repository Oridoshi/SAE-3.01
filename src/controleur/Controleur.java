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
}