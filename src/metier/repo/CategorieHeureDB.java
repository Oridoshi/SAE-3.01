package metier.repo;

import metier.DB;
import metier.DBResult;
import metier.model.CategorieHeure;
import metier.model.CategorieModule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CategorieHeureDB {

	private Connection db = DB.getInstance();

	private PreparedStatement psGetCategoriesHeure;
	private PreparedStatement psGetCategorieHeureParId;
	private PreparedStatement psGetCategorieHeureParCategorieModule;

	public CategorieHeureDB(){
		try{
			this.psGetCategoriesHeure = db.prepareStatement("SELECT * FROM CategorieHeure");
			this.psGetCategorieHeureParId = db.prepareStatement("SELECT * FROM CategorieHeure WHERE nom = ?");
		} catch ( Exception e ){
			e.printStackTrace();
		}
	}

	public List<CategorieHeure> getCategoriesHeure(){
		DBResult result = DB.query(this.psGetCategoriesHeure);
		List<CategorieHeure> categoriesHeure = new ArrayList<>();
		for ( Map<String, String> ligne : result.getLignes() ){
			categoriesHeure.add(ligneToHeures(ligne));
		}
		return categoriesHeure;
	}

	public CategorieHeure getCategorieHeureParId(String nom){
		try{
			this.psGetCategorieHeureParId.setString(1, nom);
			DBResult result = DB.query(this.psGetCategorieHeureParId);
			Map<String, String> ligne = result.getLignes().get(0);
			return ligneToHeures(ligne);
		} catch ( Exception e ){
			return null;
		}
	}

	public List<CategorieHeure> getCategorieHeureParCategorieModuleName(String name){
		try{
			this.psGetCategorieHeureParCategorieModule.setString(1, name);
			DBResult result = DB.query(this.psGetCategorieHeureParCategorieModule);
			List<CategorieHeure> categoriesHeure = new ArrayList<>();
			for ( Map<String, String> ligne : result.getLignes() ){
				categoriesHeure.add(ligneToHeures(ligne));
			}
			return categoriesHeure;
		} catch ( Exception e ){
			return null;
		}
	}

	private CategorieHeure ligneToHeures(Map<String, String> ligne)
	{
		return new CategorieHeure(
				ligne.get("nom"),
				Double.parseDouble(ligne.get("coeffCat"))
		);
	}

}
