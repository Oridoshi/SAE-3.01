package metier;

import java.util.ArrayList;

public class ModuleStage extends Module
{
	public static ArrayList<ModuleStage> lstModuleStage = new ArrayList<ModuleStage>();
	private int nbHeuresReh;
	private int nbHeuresTut;
	private ArrayList<Affectation> lstSousModuleSae;

	public ModuleStage(int nbHeuresReh, int nbHeuresTut, int semestre, String nom, boolean valider)
	{
		super("Stage", nom, valider);
		this.nbHeuresReh = nbHeuresReh;
		this.nbHeuresTut = nbHeuresTut;
		this.lstSousModuleSae = new ArrayList<Affectation>();
		lstModuleStage.add(this);
	}

	public void AjouterAffectation(Intervenant Intervenant, TypeHeure typeH, int nbHeures, String commentaire)
	{
		lstSousModuleSae.add(new Affectation(Intervenant, typeH, nbHeures, commentaire));
	}

	public static ArrayList<ModuleStage> lstModuleStage(){return lstModuleStage;}
	public int getNbHeuresReh(){return nbHeuresReh;}
	public int getNbHeuresTut(){return nbHeuresTut;}
	public ArrayList<Affectation> getLstSousModuleSae(){return lstSousModuleSae;}
}

