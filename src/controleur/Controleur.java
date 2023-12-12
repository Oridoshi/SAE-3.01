package controleur;
import metier.repo.*;
import java.util.ArrayList;
import java.util.List;

import ihm.FrameIhm;
import metier.model.*;
import metier.DB;

public class Controleur
{
	private DB database;
	private String version;

	private IntervenantDB intervenantDB;

	public Controleur()
	{
		this.intervenantDB = new IntervenantDB();
		//this.database = DB.getInstance();
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

	public List<Intervenant> getIntervenants()
	{
		return this.intervenantDB.getIntervenants();
	}
}