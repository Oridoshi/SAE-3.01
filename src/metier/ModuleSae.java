package metier;

import metier.model.Affectation;
import metier.model.CategorieHeure;
import metier.model.Intervenant;
import metier.model.Module;

import java.util.ArrayList;


/**
 * ModuleSae
 * Classe permettant de créer et gérer les différentes sae 
 */
public class ModuleSae extends Module
{
	public static ArrayList<ModuleSae> lstModuleSae = new ArrayList<ModuleSae>();
	private int nbHeuresPn;
	private int nbHeuresTut;
	private ArrayList<Affectation> lstSousModuleSae;

	public ModuleSae(int nbHeuresPn, int nbHeuresTut, String code, String nom, boolean valider)
	{
		super("SAE", code, nom, valider);
		this.nbHeuresPn = nbHeuresPn;
		this.nbHeuresTut = nbHeuresTut;
		this.lstSousModuleSae = new ArrayList<Affectation>();
		lstModuleSae.add(this);
	}

	public void AjouterAffectation(Intervenant Intervenant, CategorieHeure typeH, int nbHeures, String commentaire)
	{
		lstSousModuleSae.add(new Affectation(Intervenant, typeH, nbHeures, commentaire));
	}

	public static ArrayList<ModuleSae> getLstModuleSae(){return lstModuleSae;}
	public int getNbHeuresPn(){return nbHeuresPn;}
	public int getNbHeuresTut(){return nbHeuresTut;}
	public ArrayList<Affectation> getLstSousModuleSae(){return lstSousModuleSae;}
}