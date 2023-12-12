package metier;

import metier.model.Affectation;
import metier.model.CategorieHeure;
import metier.model.Intervenant;
import metier.model.Module;

import java.util.ArrayList;

/**
 * ModuleStage
 * Classe permettant de créer et gérer les différents stages 
 */

public class ModuleStage extends Module
{
	public static ArrayList<ModuleStage> lstModuleStage = new ArrayList<ModuleStage>();
	private int nbHeuresReh;
	private int nbHeuresTut;
	private ArrayList<Affectation> lstSousModuleSae;

	public ModuleStage(int nbHeuresReh, int nbHeuresTut, int semestre, String code, String nom, boolean valider)
	{
		super("Stage", code, nom, valider);
		this.nbHeuresReh = nbHeuresReh;
		this.nbHeuresTut = nbHeuresTut;
		this.lstSousModuleSae = new ArrayList<Affectation>();
		lstModuleStage.add(this);
	}

	public void AjouterAffectation(Intervenant Intervenant, CategorieHeure typeH, int nbHeures, String commentaire)
	{
		lstSousModuleSae.add(new Affectation(Intervenant, typeH, nbHeures, commentaire));
	}

	public static ArrayList<ModuleStage> lstModuleStage(){return lstModuleStage;}
	public int getNbHeuresReh(){return nbHeuresReh;}
	public int getNbHeuresTut(){return nbHeuresTut;}
	public ArrayList<Affectation> getLstSousModuleSae(){return lstSousModuleSae;}
}
