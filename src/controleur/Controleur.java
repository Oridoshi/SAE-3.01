package controleur;
import metier.repo.*;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import db.Initialisation;
import ihm.FrameIhm;
import metier.model.*;
import metier.model.Module;
import metier.DB;
import metier.IModifiable;

public class Controleur
{
	private String version;

	private List<IModifiable> listSauvegarder;
	private List<IModifiable> listSuppression;

	private static JSONObject fichierParamConnexion;

	public Controleur()
	{
		this.listSauvegarder = new ArrayList<>();
		this.listSuppression = new ArrayList<>();
		this.version = "v1.0.2";

		new FrameIhm(this);
	}

	public void ajouterSauvAttente(IModifiable objet){
		this.listSauvegarder.add(objet);
	}

	public void ajouterSauvAttente(List<IModifiable> objet){
		this.listSauvegarder.addAll(objet);
	}

	public void ajouterSuppAttente(IModifiable objet){
		this.listSuppression.add(objet);
	}

	public void ajouterSuppAttente(List<IModifiable> objet){
		this.listSuppression.addAll(objet);
	}

	public void annuler(){
		this.listSauvegarder = new ArrayList<>();
		this.listSuppression = new ArrayList<>();
	}

	public void sauvegarder(){
		
		for ( IModifiable i : listSauvegarder )
			i.sauvegarder();
		for ( IModifiable i : listSuppression )
			i.supprimer();

		this.listSauvegarder = new ArrayList<>();
		this.listSuppression = new ArrayList<>();
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

	public double getCoefH(String categorieHeure)
	{
		return CategorieHeureDB.getParNom(categorieHeure).getCoef();
	}

	public List<CategorieHeure> getLstCategorieParTypeModule(String typeModule)
	{
		List<CategorieHeure> lstCategorieHeure = new ArrayList<CategorieHeure>();
		for (PatternCategorieModuleItem item : CategorieModuleDB.getParNom(typeModule).getCategorieHeures())
			lstCategorieHeure.add(item.getCategorieHeure());
		return lstCategorieHeure;
	}

	public List<Affectation> getLstAffectationParIntervenant(int id)
	{
		return AffectationDB.getAffectationsParIntervenant(id);
	}

	public List<Intervenant> getLstIntervenantParCode (String code)
	{
		return IntervenantDB.getLstIntervenantParCode(code);
	}

	public CategorieModule getCategorieModule(String string)
	{
		return CategorieModuleDB.getParNom(string);
	}

	public void fermerConnexion()
	{
		DB.fermerConnexion();
	}

	public int getIdNouvIntervenant()
	{
		return IntervenantDB.getDernierId() + 1;
	}

	public int getIdNouvAffectation()
	{
		return AffectationDB.getDernierId() + 1;
	}

	public List<CategorieModule> getLstCategorieModule()
	{
		return CategorieModuleDB.list();
	}

	public static void setJson(JSONObject json)
	{
		Controleur.fichierParamConnexion = json;
	}

	public static JSONObject getJson()
	{
		return Controleur.fichierParamConnexion;
	}

	public List<Module> getLstModule()
	{
		return ModuleDB.list();
	}

	public void initialiserTable()
	{
		Initialisation.initialisationBD();
	}

	public void reset(){
		AffectationDB.reset();
		CategorieHeureDB.reset();
		CategorieIntervenantDB.reset();
		CategorieModuleDB.reset();
		IntervenantDB.reset();
		ModuleDB.reset();
		PatternCategorieModuleItemDB.reset();
		SemestreDB.reset();
	}
}