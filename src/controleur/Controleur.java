package controleur;
import metier.repo.*;
import java.util.ArrayList;
import java.util.List;

import ihm.FrameIhm;
import metier.model.*;
import metier.DB;
import metier.IModifiable;

public class Controleur
{
	private String version;

	private List<IModifiable> listSauvegarder;
	private List<IModifiable> listSuppression;


	public Controleur()
	{
		this.listSauvegarder = new ArrayList<>();
		this.listSuppression = new ArrayList<>();
		this.version = "v0.1.4";

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

	public double getCoefH(String string)
	{
		return CategorieHeureDB.getParNom(string).getCoef();
	}

	public List<CategorieHeure> getLstCategorieParTypeModule(String typeModule)
	{
		List<CategorieHeure> lstCategorieHeure = new ArrayList<CategorieHeure>();
		for (PatternCategorieModuleItem item : CategorieModuleDB.getParNom(typeModule).getCategorieHeures())
			lstCategorieHeure.add(item.getCategorieHeure());
		return lstCategorieHeure;
	}

	public void ajouterModule() {
	}
}