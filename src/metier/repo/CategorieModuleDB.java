package metier.repo;

import metier.DB;
import metier.DBResult;
import metier.model.CategorieModule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CategorieModuleDB {

	private Connection db = DB.getInstance();

	private CategorieHeureDB categorieHeureDB;

	private PreparedStatement psGetCategoriesModule;
	private PreparedStatement psGetCategorieModuleParId;

	public CategorieModuleDB(){
		this.categorieHeureDB = new CategorieHeureDB();
		try{
			this.psGetCategoriesModule = db.prepareStatement("SELECT * FROM CategorieModule");
			this.psGetCategorieModuleParId = db.prepareStatement("SELECT * FROM CategorieModule WHERE idCatModule = ?");
		} catch ( Exception e ){
			e.printStackTrace();
		}
	}

	public List<CategorieModule> getCategoriesModule(){
		DBResult result = DB.query(this.psGetCategoriesModule);
		List<CategorieModule> categoriesModule = new ArrayList<>();
		for ( Map<String, String> ligne : result.getLignes() ){
			categoriesModule.add(getCategorieModule(ligne));
		}
		return categoriesModule;
	}

	public CategorieModule getCategorieModuleParId(String id){
		try{
			this.psGetCategorieModuleParId.setString(1, id);
			DBResult result = DB.query(this.psGetCategorieModuleParId);
			Map<String, String> ligne = result.getLignes().get(0);
			return getCategorieModule(ligne);
		} catch ( Exception e ){
			return null;
		}
	}

	public CategorieModule getCategorieModule(Map<String, String> ligne){
		return new CategorieModule(
				ligne.get("nom"),
				categorieHeureDB.getCategorieHeureParCategorieModuleName(ligne.get("nom"))
		);
	}

}
