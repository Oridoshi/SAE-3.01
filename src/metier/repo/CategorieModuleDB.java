package metier.repo;

import metier.DB;
import metier.DBResult;
import metier.model.CategorieModule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CategorieModuleDB {

	private static Connection db = DB.getInstance();

	private static List<CategorieModule> categoriesModule;

	private static PreparedStatement psGetAll;
	private static PreparedStatement psDelete;
	private static PreparedStatement psUpdate;
	private static PreparedStatement psCreate;

	static{
		categoriesModule = new ArrayList<>();
		try{
			psGetAll = db.prepareStatement("SELECT * FROM CategorieModule");
			psDelete = db.prepareStatement("DELETE FROM CategorieModule WHERE nom = ?");
			psUpdate = db.prepareStatement("UPDATE CategorieModule SET nom = ? WHERE nom = ?");
			psCreate = db.prepareStatement("INSERT INTO CategorieModule VALUES (?)");
			DBResult result = new DBResult(psGetAll.executeQuery());
			for ( Map<String, String> ligne : result.getLignes() ){
				categoriesModule.add(new CategorieModule(ligne.get("nom")));
			}
			init();
		} catch ( Exception e ){
			e.printStackTrace();
		}
	}

	public static List<CategorieModule> list(){
		return categoriesModule;
	}

	public static CategorieModule getParNom(String nom){
		for ( CategorieModule categorieModule : categoriesModule )
			if ( categorieModule.getNom().equals(nom) ) return categorieModule;
		return null;
	}

	public static boolean delete(CategorieModule categorieModule){
		try{
			psDelete.setString(1, categorieModule.getNom());
			if ( DB.update(psDelete) == 1){
				categoriesModule.remove(categorieModule);
				return true;
			} else {
				return false;
			}
		} catch ( SQLException e ){
			return false;
		}
	}

	public static boolean save(CategorieModule categorieModule){
		if ( categoriesModule.contains(categorieModule) ){
			try{
				psUpdate.setString(1, categorieModule.getNom());
				psUpdate.setString(2, categorieModule.getNomOrigine());
				if ( DB.update(psUpdate) == 1 ){
					categorieModule.setNomOrigine(categorieModule.getNom());
					return true;
				} else {
					return false;
				}
			} catch ( SQLException e){
				return false;
			}
		} else {
			try{
				psCreate.setString(1, categorieModule.getNom());
				if ( DB.update(psCreate) == 1 ){
					categoriesModule.add(categorieModule);
					return true;
				} else {
					return false;
				}
			} catch ( SQLException e ){
				return false;
			}
		}

	}

	private static void init(){
		// for ( CategorieModule categorieModule : categoriesModule ){
		// 	// empty
		// }
	}
	
}
